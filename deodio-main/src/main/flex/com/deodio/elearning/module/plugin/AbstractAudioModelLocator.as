package com.deodio.elearning.module.plugin {

	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.stream.AudioStreamController;

	import flash.events.EventDispatcher;

	public class AbstractAudioModelLocator extends AbstractModelLocator {

		[Bindable]
		public var audioStream:AudioStreamController=new AudioStreamController();

		[Bindable]
		public var _currentAudioStream:AudioStreamController;

		public function AbstractAudioModelLocator() {
			audioStream.addEventListener(MediaEvent.MEDIA_CREATE_COMPLETE, onAudioCreateCompleteFunc);
			audioStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_COMPLETE, onAudioPlayCompleteFunc);
		}

		public function onAudioCreateCompleteFunc(event:MediaEvent):void {

		}

		public function onAudioPlayCompleteFunc(event:MediaEvent):void {

		}

	}
}
