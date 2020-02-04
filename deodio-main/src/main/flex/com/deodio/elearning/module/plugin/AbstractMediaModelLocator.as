package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.PresentationSyncQuizs;
	import com.deodio.elearning.domain.customize.Points;
	import com.deodio.elearning.domain.customize.PresentationSyncQuizsBo;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.stream.VideoStreamController;

	import flash.errors.IllegalOperationError;
	import flash.events.EventDispatcher;
	import flash.utils.Dictionary;

	import mx.collections.ArrayCollection;
	import mx.controls.Alert;

	public class AbstractMediaModelLocator extends AbstractModelLocator {

		[Bindable]
		public var videoStreamingList:ArrayCollection=new ArrayCollection();

		[Bindable]
		public var currentStreaming:VideoStreamController=new VideoStreamController();

		public function initAbstractEventListener():void {
			for each (var videoStream:VideoStreamController in videoStreamingList) {
				videoStream.addEventListener(MediaEvent.MEDIA_CREATE_COMPLETE, onVideoCreateCompleteFunc);
				videoStream.addEventListener(MediaEvent.MEDIA_SHOW_AUDIO_IMAGE, onVideoShowAudioImageFunc);
				videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PAUSE, onVideoCreateNetStreamPauseFunc);
				videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_STOP, onVideoCreateNetStreamStopFunc);
				videoStream.addEventListener(MediaEvent.MEDIA_CONNECT_BEGIN_LOADING, onVideoCreateConnectBeginFunc);
				videoStream.addEventListener(MediaEvent.MEDIA_CONNECT_FINISH_LOADING, onVideoCreateConnectFinishFunc);
				videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_PLAY_COMPLETE, onVideoCreateNetStreamCompleteFunc);
				videoStream.addEventListener(MediaEvent.MEDIA_NET_STREAM_CONNECT_FAILED, onVideoCreateNetStreamConnectFailedFunc);
			}
		}


		public function removeParameterMap4Quizs(parameterMap:Dictionary, key:int, type:int, point:PresentationSyncQuizsBo):void {
			var plist:ArrayCollection=parameterMap[key] as ArrayCollection;
			for each (var ps:Points in plist) {
				if (ps.type == type && ps.itemId == point.id) {
					plist.removeItemAt(plist.getItemIndex(ps));
				}
			}
		}

		public function removeParameterMap(parameterMap:Dictionary, key:int, type:int):void {
			var plist:ArrayCollection=parameterMap[key] as ArrayCollection;
			for each (var ps:Points in plist) {
				if (ps.type == type) {
					plist.removeItemAt(plist.getItemIndex(ps));
				}
			}
		}

		public function buildParameterMap(parameterMap:Dictionary, key:int, value:Points):void {
			if (!parameterMap[key] || !(parameterMap[key] as ArrayCollection)) {
				parameterMap[key]=new ArrayCollection();
			}

			var plist:ArrayCollection=parameterMap[key];
			plist.addItem(value);
			parameterMap[key]=plist;
		}


		public function onVideoCreateNetStreamCompleteFunc(event:MediaEvent):void {
//			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
		}

		public function onVideoCreateCompleteFunc(event:MediaEvent):void {
//			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
		}

		public function onVideoShowAudioImageFunc(event:MediaEvent):void {
//			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
		}

		public function onVideoCreateNetStreamPauseFunc(event:MediaEvent):void {
//			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
		}

		public function onVideoCreateNetStreamStopFunc(event:MediaEvent):void {
//			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
		}

		public function onVideoCreateConnectBeginFunc(event:MediaEvent):void {
//			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
		}

		public function onVideoCreateConnectFinishFunc(event:MediaEvent):void {
//			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
		}

		public function onVideoCreateNetStreamConnectFailedFunc(event:MediaEvent):void {
//			videoStream.closeStream();
//			videoStream.closeConnection();
			throw new IllegalOperationError("Sorry, the system is unable to stream media for you to view. Either your network connection speed is too slow or a firewall is blocking the stream media.");
		}
	}
}
