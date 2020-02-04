package com.deodio.elearning.libs.stream {

	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.utils.MediaUtils;
	import com.deodio.elearning.service.SystemService;
	
	import flash.events.ActivityEvent;
	import flash.events.StatusEvent;
	import flash.media.Camera;
	import flash.media.Microphone;
	import flash.net.NetConnection;
	
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class RecordStreamController extends MediaStreamController {

		public var recordUrl:String;
		public var _call:Function;
		public var _isRecordVideo:Boolean=false;

		public function RecordStreamController() {
			this.systemService=new SystemService();
			this.bufferTime=0;
		}

		public function setIsRecordVideo(isRecordVideo:Boolean=false):void {
			this._isRecordVideo=isRecordVideo;
		}

		public function getIsRecordVideo():Boolean {
			return this._isRecordVideo;
		}

		public override function setMedia():void {
			stream.attachAudio(microPhone);
			if (_isRecordVideo == true) {
				stream.attachCamera(camera);
			}
			stream.publish(recordUrl, "append");
		}

		public override function playUrl(url:String, seek:Number, isPlay:Boolean=false):void {
			this.isPlay=isPlay;
			this.isSetUrl=true;
			this.seek=seek;
			this.systemService.getFmsServer(function(event:ResultEvent):void {
				serverUrl=event.result as String;
				recordUrl=url;
//					recordUrl="mp4:aaa.mp4";
				if (!isConnect) {
					netConnect();
				} else {
					streamConnect();
				}
			});
		}


		public override function initCameraAndMic(isLoopBack:Boolean):void {
			NetConnection.defaultObjectEncoding=flash.net.ObjectEncoding.AMF3;
			if (this._isRecordVideo == true) {
				camera=Camera.getCamera();
				if (camera != null) {
					camera.addEventListener(StatusEvent.STATUS, cameraStatusHandler);
					camera.setMode(1086, 600, 30);
					camera.setQuality(0, 100);
				}
			}
			microPhone=Microphone.getMicrophone();
			if (microPhone != null) {
				microPhone.addEventListener(StatusEvent.STATUS, micStatusHandler);
				microPhone.addEventListener(ActivityEvent.ACTIVITY, micActiveHandler);
				microPhone.setLoopBack(isLoopBack);
				microPhone.setUseEchoSuppression(true);
				//INFO: Set the mic activate.
				microPhone.setSilenceLevel(0, 2000);
				//INFO: Set the gain
				microPhone.gain=50;
				microPhone.rate=44;
			}
		}

		public override function closeCameraAndMic():void {
			if (microPhone != null) {

				microPhone.removeEventListener(StatusEvent.STATUS, micStatusHandler);
				microPhone.removeEventListener(ActivityEvent.ACTIVITY, micActiveHandler);
				microPhone.setLoopBack(false);
				microPhone.setUseEchoSuppression(true);
				//INFO: Set the mic activate.
				microPhone.setSilenceLevel(0, 0);
				//INFO: Set the gain
				microPhone.gain=100;
				microPhone.rate=44;
				microPhone=Microphone.getMicrophone(null);
				microPhone=null;
			}

			if (camera != null) {
				camera.removeEventListener(StatusEvent.STATUS, cameraStatusHandler);
				camera.setMode(1086, 600, 30);
				camera.setQuality(0, 100);
				camera=Camera.getCamera(null);
				camera=null;
			}
		}

		private function micActiveHandler(event:ActivityEvent):void {
			if (_call != null)
				_call.call(this);
		}

		private function cameraStatusHandler(event:StatusEvent):void {

		}

		public override function micActiveFunc(callFunc:Function):void {
			_call=callFunc;
		}

		private function micStatusHandler(event:StatusEvent):void {
			switch (event.code) {
				case "Microphone.Muted":
					micMuted();
					break;
				case "Microphone.Unmuted":
					micUnMuted();
					break;
			}
		}

		private function micMuted():void {

			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_MICROPHONE_MUTED));
			if (microPhone != null) {
				microPhone.removeEventListener(StatusEvent.STATUS, micStatusHandler);
			}
		}

		private function micUnMuted():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_MICROPHONE_UN_MUTED));
		}

		override public function streamPause():void {
			this.callBackStreamPauseFunc();
		}

		override public function streamBufferFull():void {
			var condition:Boolean=MediaUtils.isMp3(mediaUrl);

			if (isPlay != true) {
				this.pause();
				if (condition) {
					this.stream.pause();
					this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_SHOW_AUDIO_IMAGE));
				} else {
					this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PAUSE_BUFFER_FULL));
				}
			}
		}

	}
}
