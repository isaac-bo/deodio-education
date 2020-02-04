package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.stream.AudioStreamController;
	import com.deodio.elearning.libs.stream.VideoStreamController;

	public class AbstractEditorModelLocator extends AbstractModelLocator {

		[Bindable]
		public var isRecordVideo:Boolean=false;

		[Bindable]
		public var videoStream:VideoStreamController=new VideoStreamController();

		[Bindable]
		public var audioStream:AudioStreamController=new AudioStreamController();

		public function AbstractEditorModelLocator() {
			videoStream.addEventListener(MediaEvent.MEDIA_CREATE_COMPLETE, onVideoCreateCompleteFunc);
			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_STOP, onVideoStreamStopFunc);
			//			videoStream.addEventListener(MediaEvent.MEDIA_SHOW_AUDIO_IMAGE, onVideoShowAudioImageFunc);
			//			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PAUSE, onVideoCreateNetStreamPauseFunc);
			//			videoStream.addEventListener(MediaEvent.MEDIA_CONNECT_BEGIN_LOADING, onVideoCreateConnectBeginFunc);
			//			videoStream.addEventListener(MediaEvent.MEDIA_CONNECT_FINISH_LOADING, onVideoCreateConnectFinishFunc);
			//			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_COMPLETE, onVideoCreateNetStreamCompleteFunc);
			//			videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_CONNECT_FAILED, onVideoCreateNetStreamConnectFailedFunc);
		}

		public function onRecordCreateCompleteFunc(event:MediaEvent):void {

		}

		public function onVideoCreateCompleteFunc(event:MediaEvent):void {

		}

		public function onVideoStreamStopFunc(event:MediaEvent):void {

		}
	}
}


