package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.stream.LiveStreamController;
	import com.deodio.elearning.libs.stream.RecordStreamController;
	import com.deodio.elearning.libs.stream.VideoStreamController;
	
	import flash.events.EventDispatcher;
	
	import mx.controls.Alert;
	

	public class AbstractRecordModelLocator extends AbstractModelLocator {

		[Bindable]
		public var isRecordVideo:Boolean=false;

		[Bindable]
		public var recordStream:RecordStreamController=new RecordStreamController();

		[Bindable]
		public var videoStream:VideoStreamController=new VideoStreamController();

		[Bindable]
		public var audioStream:VideoStreamController=new VideoStreamController();
		
		[Bindable]
		public var liveStream:LiveStreamController = new LiveStreamController();

		public function AbstractRecordModelLocator() {
			
			recordStream.addEventListener(MediaEvent.MEDIA_CREATE_COMPLETE, onRecordCreateCompleteFunc);
			recordStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_RECORD_START, onRecordRecordStartFunc);
			recordStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_RECORD_STOP, onRecordRecordStopFunc);
			
//			recordStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PUBLISH, onLiveRecordRecordStartFunc);
//			recordStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_RECORD_STOP, onLiveRecordRecordStopFunc);
			
			liveStream.addEventListener(MediaEvent.MEDIA_CREATE_COMPLETE, onLiveRecordCreateCompleteFunc);
			liveStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PUBLISH, onRecordRecordStartFunc);
			liveStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_RECORD_STOP, onLiveRecordRecordStopFunc);
			
			videoStream.addEventListener(MediaEvent.MEDIA_CREATE_COMPLETE, onVideoCreateCompleteFunc);
			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_STOP, onVideoStreamStopFunc);
			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_UNPUBLISH_NOTIFY, onVideoCreateNetStreamCompleteFunc);
			

//			videoStream.addEventListener(MediaEvent.MEDIA_SHOW_AUDIO_IMAGE, onVideoShowAudioImageFunc);
//			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PAUSE, onVideoCreateNetStreamPauseFunc);
//			videoStream.addEventListener(MediaEvent.MEDIA_CONNECT_BEGIN_LOADING, onVideoCreateConnectBeginFunc);
//			videoStream.addEventListener(MediaEvent.MEDIA_CONNECT_FINISH_LOADING, onVideoCreateConnectFinishFunc);
//			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_COMPLETE, onVideoCreateNetStreamCompleteFunc);
//			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_CONNECT_FAILED, onVideoCreateNetStreamConnectFailedFunc);
		}

		public function onRecordRecordStartFunc(event:MediaEvent):void {

		}

		public function onRecordCreateCompleteFunc(event:MediaEvent):void {

		}

		public function onRecordRecordStopFunc(event:MediaEvent):void {

		}
		
		public function onLiveRecordRecordStartFunc(event:MediaEvent):void {
		}
		
		public function onLiveRecordCreateCompleteFunc(event:MediaEvent):void {
			
		}
		
		public function onLiveRecordRecordStopFunc(event:MediaEvent):void {
			
		}

		public function onVideoCreateCompleteFunc(event:MediaEvent):void {

		}

		public function onVideoStreamStopFunc(event:MediaEvent):void {

		}
		
		public function onVideoCreateNetStreamCompleteFunc(event:MediaEvent):void{
			
		}
	}
}
