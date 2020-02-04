package com.deodio.elearning.module.plugin{
	import com.deodio.elearning.libs.event.CommonEvent;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.event.MockEvent;
	import com.deodio.elearning.libs.event.RecordEvent;
	import com.deodio.elearning.libs.stream.VideoStreamController;
	import com.deodio.elearning.libs.utils.RecordUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.playbar.ac.IPlayBar;
	import com.deodio.elearning.module.player.playbar.view.ClassRoomPlayBar;
	import com.deodio.elearning.module.player.playbar.view.LiveRoomPlayBar;
	import com.deodio.elearning.module.player.playbar.view.LiveStudioPlayBar;
	import com.deodio.elearning.module.player.playbar.view.RichPlayBarContainer;
	import com.deodio.elearning.module.player.playbar.view.SyncMediaPlayBar;
	
	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.media.Video;
	import flash.utils.Timer;
	
	import mx.controls.Alert;

	public class LiveRecordModelLocator extends RecordModelLocator{
		
		private static var _liveRecordLocator:LiveRecordModelLocator;
		
		private var progressBar:IPlayBar;
		
		private var _model:ModelLocator=ModelLocator.getInstance();
		
		public static function getInstance():LiveRecordModelLocator {
			if (_liveRecordLocator == null) {
				_liveRecordLocator=new LiveRecordModelLocator();
			}
			return _liveRecordLocator;
		}
		
		public function initMedia(progressBar:IPlayBar):void {
			this.progressBar=progressBar;
			if(this.progressBar as LiveRoomPlayBar){
				this.liveStream.initCameraAndMic(_model.isHeadsetMicroPhone);
			}else if(this.progressBar as LiveStudioPlayBar){
				this.recordStream._isRecordVideo = true;
				this.recordStream.initCameraAndMic(_model.isHeadsetMicroPhone);
			}
			this.initEventListener();
		}
		
		private function initEventListener():void {
			
			if(progressBar as LiveRoomPlayBar){
				
				(progressBar as LiveRoomPlayBar).addEventListener(RecordEvent.RECORD_START_SELF_MEDIA, onRecordStartSelfMedia);
				(progressBar as LiveRoomPlayBar).addEventListener(RecordEvent.RECORD_STOP_SELF_MEDIA, onRecordStopSelfMedia);
			}else if(progressBar as LiveStudioPlayBar){
			
				(progressBar as LiveStudioPlayBar).addEventListener(RecordEvent.RECORD_START_LIVE_MEDIA, onRecordStartLiveMedia);
				(progressBar as LiveStudioPlayBar).addEventListener(RecordEvent.RECORD_STOP_LIVE_MEDIA, onRecordStopLiveMedia);
			}
			
		}
		
		public function onRecordStartSelfMedia(event:RecordEvent):void {
			if(_model.isRecord == false && 
				this.liveStream.getIsRecordVideo() == true && 
				this.liveStream.camera != null && 
				this.liveStream.microPhone != null){
			}else if(_model.isRecord == false &&
				this.liveStream.getIsRecordVideo() == false &&
				this.liveStream.microPhone != null){
			}
			
			this.liveStream.playUrl(RecordUtils.getLiveMedia4Students(_model.userId,_model.presentationId), 0,true);
		}
		
		public function onRecordStartLiveMedia(event:RecordEvent):void{
			if(_model.isRecord == false && 
				this.recordStream.getIsRecordVideo() == true && 
				this.recordStream.camera != null && 
				this.recordStream.microPhone != null){
			}else if(_model.isRecord == false &&
				this.recordStream.getIsRecordVideo() == false &&
				this.recordStream.microPhone != null){
			}
			this.recordStream.playUrl(RecordUtils.getLiveMedia4Trainer(_model.presentationCreator,_model.presentationId), 0,true);
		}
		
		public function onPlayLiveMedia(url:String):void{
			if (_model.isPlay == false) {
				videoStream.isPlay=true;
				_model.isPlay=true;
				videoStream.playUrl(url, 0, true);
			} 
		}
		
		public function onRecordStopSelfMedia(event:RecordEvent):void{
			
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_STOP_SELF_MEDIA));
			this.liveStream.closeStream();
			this.liveStream.closeConnection();
		}
		
		public function onRecordStopLiveMedia(event:RecordEvent):void{
			
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_STOP_MEDIA));
			this.recordStream.closeStream();
			this.recordStream.closeConnection();
		}

		
		override public function onRecordRecordStartFunc(event:MediaEvent):void {
			
			var video:Video=new Video();
			video.width=750;
			video.height=452;
			
			if(progressBar as LiveRoomPlayBar){
				video.attachCamera(this.liveStream.camera);
			}else if(progressBar as LiveStudioPlayBar){
				video.attachCamera(this.recordStream.camera);
			}
			
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_START_MEDIA, video));
		}
		
		override public function onLiveRecordCreateCompleteFunc(event:MediaEvent):void {
			
		}
		
		override public function onLiveRecordRecordStopFunc(event:MediaEvent):void {
			
		}
		
		override public function onVideoCreateCompleteFunc(event:MediaEvent):void {
			
			var media:Video=new Video();
			media.attachNetStream(videoStream.stream);
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_CREATE_MEDIA_ITEM, media));
		}
		
		override public function onVideoCreateNetStreamCompleteFunc(event:MediaEvent):void{
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_FINISH_MEDIA_ITEM));
		}
		
		override public function onRecordRecordStopFunc(event:MediaEvent):void {
			this.recordStream.closeStream();
			this.recordStream.closeConnection();
		}
	}
}