package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.PresentationSyncRecords;
	import com.deodio.elearning.domain.customize.DubbingAudio;
	import com.deodio.elearning.libs.event.CommonEvent;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.playbar.view.DubbingPlayBar;
	import com.deodio.elearning.module.player.playbar.view.IPlayBar;

	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.utils.Dictionary;
	import flash.utils.Timer;

	import mx.collections.ArrayCollection;
	import mx.controls.Alert;

	[Bindable]
	public class AudioModelLocator extends AbstractAudioModelLocator {

		private static var _audioLocator:AudioModelLocator;
		[Bindable]
		public var isPlay:Boolean=false;
		private var _presentationSyncRecords:ArrayCollection;
		private var progressBar:DubbingPlayBar;
		private var _model:ModelLocator=ModelLocator.getInstance();
		private var _timer:Timer=new Timer(10);
		private var _currentIndex:int=0;

		private var _isPress:Boolean=false;
		private var _isRelease:Boolean=false;
		private var _isSeek:Boolean=false;

		public var startDuration:Number=0;
		public var endDuration:Number=0;
		public var currentDuration:Number=0;
		public var recordDictionary:Dictionary;

		public static function getInstance():AudioModelLocator {
			if (_audioLocator == null) {
				_audioLocator=new AudioModelLocator();
			}
			return _audioLocator;
		}

		public function init(progressBar:DubbingPlayBar):void {
			this.progressBar=progressBar;
			this.progressBar.addEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
			this.progressBar.addEventListener(MediaEvent.MEDIA_PROGRESS_PRESS, onProgressPressFunc);
			this.progressBar.addEventListener(MediaEvent.MEDIA_PROGRESS_RELEASE, onProgressReleaseFunc);
		}

		public function setItemsDuration(presentationSyncRecords:ArrayCollection):void {
			this._presentationSyncRecords=presentationSyncRecords;
		}

		override public function finishStream():void {
			if (progressBar.slider.value == progressBar.slider.maximum) {
				this.initStream();
			}
		}

		override public function initStream():void {
			this.initAudioStream();
			this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
			this.stopTimer();
			this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
		}

		public function initAudioStream():void {
			this._model.isPlay=false;
			this.audioStream.closeStream();
			this.audioStream.closeConnection();
		}

		public function onPlay():void {
			if (this._model.isPlayRecord == false) {
				this.isPlay=true;
				if (this.recordDictionary[this._model.syncPointId]) {
					if ((this.recordDictionary[this._model.syncPointId] as ArrayCollection).length <= 0) {
						execTimer();
					} else {
						if (this._currentAudioStream != null) {
							this._currentAudioStream.isPlay=false;
							this._currentAudioStream.playUrl(this._currentAudioStream.url, 0, false);
						}

						this._currentIndex=0;
						var record:PresentationSyncRecords=(this.recordDictionary[this._model.syncPointId] as ArrayCollection).getItemAt(0) as PresentationSyncRecords;
						this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
						this._currentAudioStream=record.audioStream;
						record.audioStream.isPlay=true;
						record.audioStream.play();
						this.progressBar.addEventListener(Event.ENTER_FRAME, progressBarHandler);
					}
				} else {
					execTimer();
				}
			}
		}

		public function onPlayRecord(presentationSyncRecord:PresentationSyncRecords):void {
			if (this._model.isRecord == false && _model.isPlayRecord == false) {
				presentationSyncRecord.audioStream.isPlay=true;
				_model.isPlayRecord=true;
				presentationSyncRecord.audioStream.resume();
			}
		}

		public function onPause():void {
			try {
				if (this._model.isPlayRecord == false) {
					if (this._currentAudioStream.isPlay == true) {
						this._currentAudioStream.isPlay=false;
						this._currentAudioStream.playUrl(this._currentAudioStream.url, 0, false);
						this.isPlay=false;
					}
					this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
					this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
					this.stopTimer();
				}
			} catch (e:Error) {
				Alert.show("-------" + e.getStackTrace());
			}
		}

		public function onStop():void {
			this.initStream();
		}

		private function progressBarHandler(event:Event):void {
			trace(this + "------------ progressBarHandler -------------" + this._currentAudioStream.getPlayTime());
//			this.finishStream();
			//when slider is clicked don't update slider progress
			var playDuration:Number=this._currentAudioStream.getPlayTime() + this.getPlayBackDuration();
			trace(this + "------------ playDuration -------------" + playDuration);
			this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, playDuration));
		}

		public function switchClipHandler():void {
			var records:ArrayCollection=this.recordDictionary[this._model.syncPointId] as ArrayCollection;
			if (this._currentIndex < records.length - 1) {
				trace("-------------- switchClipHandler 1 ------------------");
				this._currentAudioStream.isPlay=false;
				this._currentAudioStream.seek=0;
				this._currentAudioStream.playUrl(this._currentAudioStream.url, 0, false);
				this._currentIndex+=1;
				var record:PresentationSyncRecords=records.getItemAt(this._currentIndex) as PresentationSyncRecords;
				this.progressBar.addEventListener(Event.ENTER_FRAME, progressBarHandler);
				this._currentAudioStream=record.audioStream;
				this._currentAudioStream.isPlay=true;
				this._currentAudioStream.play();
			} else {
//				this.audioPause();
//				this.finish();
				trace("-------------- switchClipHandler 2 ------------------");
				this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
				this.execTimer();
			}
		}

		private function finish():void {
			this._model.isPlay=this._model.isRecord=false;
			this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
			this.audioStream.closeStream();
			this.audioStream.closeConnection();
			if (_timer != null) {
				_timer.stop();
				_timer=null;
			}

		}

		private function execTimer():void {
			this.audioStream.closeStream();
			this.audioStream.closeConnection();
			_timer.start();
			if (progressBar.slider.value == progressBar.slider.maximum - 1) {
				_timer.stop();
			}
			_timer.addEventListener(TimerEvent.TIMER, function(event:TimerEvent):void {
				progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, progressBar.slider.value+=0.01));
				finishStream();
			});
		}

		private function stopTimer():void {
			if (_timer) {
				_timer.stop();
			}
		}


		public function getPlayBackDuration():Number {
			var records:ArrayCollection=this.recordDictionary[this._model.syncPointId] as ArrayCollection;
			var recordLength:Number=0;
			for each (var record:PresentationSyncRecords in records) {
				if (records.getItemIndex(record) < this._currentIndex) {
					recordLength+=record.recordLength;
				} else {
					break;
				}
			}

			return recordLength;
		}


		private function onProgressPressFunc(event:MediaEvent):void {
		}

		private function onProgressReleaseFunc(event:MediaEvent):void {
		}

		private function onProgressChangeFunc(event:MediaEvent):void {
		}

		public function changeSeek(isPlay:Boolean):void {
		}
	}
}
