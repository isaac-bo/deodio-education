package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.Media;
	import com.deodio.elearning.domain.PresentationSyncRecords;
	import com.deodio.elearning.domain.customize.PresentationSyncRecordsBo;
	import com.deodio.elearning.libs.constants.Constants;
	import com.deodio.elearning.libs.event.CommonEvent;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.event.RecordEvent;
	import com.deodio.elearning.libs.stream.AudioStreamController;
	import com.deodio.elearning.libs.stream.VideoStreamController;
	import com.deodio.elearning.libs.utils.CommonUtils;
	import com.deodio.elearning.libs.utils.RecordUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.playbar.view.IPlayBar;

	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.media.Video;
	import flash.utils.Dictionary;
	import flash.utils.Timer;

	import mx.collections.ArrayCollection;
	import mx.controls.Alert;

	[Bindable]
	public class RecordModelLocator extends AbstractRecordModelLocator {

		private static var _recordLocator:RecordModelLocator;
		private var _model:ModelLocator=ModelLocator.getInstance();
		private var _isPress:Boolean=false;
		private var _isRelease:Boolean=false;
		private var _isSeek:Boolean=false;

		private var progressBar:IPlayBar;
		public var recordDictionary:Dictionary;
		public var records:ArrayCollection=new ArrayCollection();
		public var isRecord:Boolean=false;

		public var startDuration:Number=0;
		public var endDuration:Number=0;
		public var currentDuration:Number=0;

		[Bindable]
		private var _audioModelLocator:AudioModelLocator=AudioModelLocator.getInstance();


		public static function getInstance():RecordModelLocator {
			if (_recordLocator == null) {
				_recordLocator=new RecordModelLocator();
			}
			return _recordLocator;
		}

		public function setIsRecording(isRecordVideo:Boolean=false):void {
			_recordLocator.recordStream.setIsRecordVideo(isRecordVideo);
		}

		public function init(progressBar:IPlayBar):void {
			this.progressBar=progressBar;
			recordStream.initCameraAndMic(_model.isHeadsetMicroPhone);
		}

		public function addRecord4SyncPoint(syncPointId:String, duration:Number, fileName:String):void {
			var record:PresentationSyncRecords=this.createNewRecord(syncPointId, duration, fileName);
			this.addRecord(record, recordDictionary[syncPointId] == null);
		}

		public function addRecord(record:PresentationSyncRecords, isNew:Boolean):void {
			var records:ArrayCollection=new ArrayCollection();
			if (!isNew) {
				records=recordDictionary[record.pointId];
			}
			records.addItem(record);
			recordDictionary[record.pointId]=records;
		}

		public function createNewRecord(syncPointId:String, duration:Number, fileName:String):PresentationSyncRecords {
			var record:PresentationSyncRecords=new PresentationSyncRecords();
			record.id=CommonUtils.createUID();
			record.presentationId=_model.presentationId;
			record.pointId=syncPointId;
			record.recordName=fileName.substring(fileName.lastIndexOf("/") + 1);
			record.recordDir=fileName;
			record.recordUrl=fileName;
			record.recordSize=0;
			record.recordExt=Constants.RECORDING_MEDIA_TYPE;
			record.recordLength=Number(duration.toFixed(3));
			record.createId=_model.userId;
			record.updateId=_model.userId;
			record.createTime=new Date();
			record.updateTime=new Date();
			record.audioStream=new AudioStreamController();
			record.audioStream.playUrl(record.recordUrl, 0, false);
			record.audioStream.addEventListener(MediaEvent.MEDIA_CREATE_COMPLETE, onAudioStreamCreateCompleteFunc);
			record.audioStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_COMPLETE, onAudioStreamPlayCompleteFunc);
			return record;
		}

		public function createNewMedia(duration:Number, fileName:String):Media {
			var media:Media=new Media();
			media.id=CommonUtils.createUID();
			media.mediaName=_model.recordMediaName;
			media.mediaDesc=_model.recordMediaDesc;
			media.mediaSize=0;
			media.mediaExt=Constants.RECORDING_MEDIA_TYPE;
			media.mediaUrl=this.recordStream.serverUrl + fileName + "." + Constants.RECORDING_MEDIA_TYPE;
			media.mediaDir=this.recordStream.serverUrl + fileName + "." + Constants.RECORDING_MEDIA_TYPE;
			media.mediaLength=duration;
			media.mediaType=0;
			media.mediaCover="";
			media.mediaWidth=1086;
			media.mediaHeight=600;
			media.createId=_model.userId;
			media.updateId=_model.userId;
			media.createTime=new Date();
			media.updateTime=new Date();
			return media;
		}

		public function onRecordSettingMedia():void {
			this._model.isUnusedMedia=true;
			this.recordStream.playUrl(RecordUtils.getUnusedMediaName(), 0, true);
		}

		public function onRecordMedia():void {
			this._model.recordName=RecordUtils.getMediaName(_model.userId);
			this.isRecord=this._model.isRecord=true;
			this.recordStream.playUrl(_model.recordName, 0);
			this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_MAXIMUM, 0));
			this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
		}

		public function stopRecordMedia():void {
			if (this._model.isRecord == true) {

				if (this.recordStream.getPlayTime() > 0) {
					var duration:Number=this.recordStream.getPlayTime();

					var media:Media=createNewMedia(duration, _model.recordName);
					this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_CREATE_COMPLETED, media));
				}
				this.isRecord=this._model.isRecord=false;
				this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_STOP_MEDIA, this.recordStream.getIsRecordVideo()));

				var timer:Timer=new Timer(2000);
				timer.addEventListener(TimerEvent.TIMER, function(event:TimerEvent):void {
					recordStream.stop();
				});
				timer.start();
					//				
			}
		}


		public function onRecordDubbing():void {
			this._model.recordName=RecordUtils.getRecordName(_model.userId, _model.presentationId);
			this.isRecord=this._model.isRecord=true;
			this.recordStream.playUrl(_model.recordName, 0);
		}

		public function onStopDubbing():void {
			if (this._model.isRecord == true) {
				if (this.recordStream.getPlayTime() > 0) {
					var duration:Number=this.recordStream.getPlayTime();
					this.addRecord4SyncPoint(this._model.syncPointId, duration, this._model.recordName);
					this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_CREATE_COMPLETED, this.recordDictionary));
				}
				this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
				this.isRecord=this._model.isRecord=false;
				this.recordStream.closeStream();
				this.recordStream.closeConnection();
			}
		}

		override public function onRecordRecordStopFunc(event:MediaEvent):void {
			this.recordStream.closeStream();
			this.recordStream.closeConnection();
		}

		public function removeAllRecord():void {
			this.recordDictionary[this._model.syncPointId]=null;
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_REMOVE_ALL));
		}

		public function removeRecord(recordId:String):void {
			var newRecords:ArrayCollection=new ArrayCollection();
			var records:ArrayCollection=this.recordDictionary[this._model.syncPointId] as ArrayCollection;
			for each (var record:PresentationSyncRecords in records) {
				if (record.id != recordId) {
					newRecords.addItem(record);
				}
			}

			if (newRecords.length <= 0) {
				this.recordDictionary[this._model.syncPointId]=null;
			} else {
				this.recordDictionary[this._model.syncPointId]=newRecords;
			}
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_REMOVE_ITEM));
		}

		//After recording, system will re-calc the relationshop between the records and sync point.
		public function setItemsDuration(presentationSyncRecords:ArrayCollection):void {

			var durationBeg:Number=0;
			var durationEnd:Number=0;
			for each (var syncPointRecord:PresentationSyncRecordsBo in presentationSyncRecords) {
				var duration4Slides:Number=syncPointRecord.pointEnd - syncPointRecord.pointTime;
				var duraiton4Records:Number=0;
				var duration:Number=0;
				if (this.recordDictionary[syncPointRecord.id]) {
					var records:ArrayCollection=this.recordDictionary[syncPointRecord.id] as ArrayCollection;
					syncPointRecord.syncRecords=records;
					for each (var record:PresentationSyncRecords in records)
						duration+=record.recordLength;

				} else {
					syncPointRecord.syncRecords=new ArrayCollection();
				}

				durationBeg+=durationEnd;
				durationEnd+=duration;
				syncPointRecord.pointEnd=durationEnd;
				syncPointRecord.pointTime=durationBeg;
				if (this._model.syncPointId == syncPointRecord.id && duration >= this.progressBar.slider.maximum)
					this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_PLAY_LENGTH, duration));
			}
		}

		private function progressBarHandler(event:Event):void {
			trace(this + "------------ progressBarHandler-------------" + this.recordStream.getPlayTime());
			//when slider is clicked don't update slider progress
			if (this.recordStream.getPlayTime() != 0 && this._model.isRecord == true) {
				var playDuration:Number=this.recordStream.getPlayTime() + getPlayDuration();
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, playDuration));
				if (playDuration > this.progressBar.slider.maximum) {
					this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_MAXIMUM, playDuration));
				}
			}

		}

		private function getPlayDuration():Number {
			var recordDuration:Number=0;
			if (!this.recordDictionary[this._model.syncPointId]) {
				return 0;
			}
			var records:ArrayCollection=this.recordDictionary[this._model.syncPointId] as ArrayCollection;
			for each (var record:PresentationSyncRecords in records) {
				recordDuration+=record.recordLength
			}
			return recordDuration;
		}



		override public function onRecordCreateCompleteFunc(event:MediaEvent):void {
		}

		override public function onRecordRecordStartFunc(event:MediaEvent):void {
			if (this.recordStream.getIsRecordVideo() == true && this._model.isUnusedMedia == false) {
				var video:Video=new Video();
				video.width=640;
				video.height=360;
				video.attachCamera(this.recordStream.camera);
				if(this.progressBar != null){
					this.progressBar.addEventListener(Event.ENTER_FRAME, progressBarHandler);
				}
				this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_START_MEDIA, video));
			}

			if (this.recordStream.getIsRecordVideo() == false && this._model.isUnusedMedia == false) {
				this.progressBar.addEventListener(Event.ENTER_FRAME, progressBarHandler);
				this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_START_MEDIA));
			}

			if (this._model.isUnusedMedia == true) {
				this._model.isUnusedMedia=false;
				this.recordStream.closeStream();
				this.recordStream.closeConnection();
			}
		}

		override public function onVideoStreamStopFunc(event:MediaEvent):void {

		}




		private function onPlayVMedia():void {

		}


		private function onAudioStreamCreateCompleteFunc(event:MediaEvent):void {
			var audioStream:AudioStreamController=event.data as AudioStreamController;
			audioStream.pause();
		}

		private function onAudioStreamPlayCompleteFunc(event:MediaEvent):void {
			var audioStream:AudioStreamController=event.data as AudioStreamController;
			try {
				if (this._audioModelLocator.isPlay == false) {
					audioStream.isPlay=false;
					_model.isPlayRecord=false;
					audioStream.playUrl(audioStream.url, 0, false);
				} else {
					this._audioModelLocator.switchClipHandler();
				}
			} catch (e:Error) {
			}

		}

	}
}
