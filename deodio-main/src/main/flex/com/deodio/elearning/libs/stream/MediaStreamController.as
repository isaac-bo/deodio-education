package com.deodio.elearning.libs.stream {

	import com.deodio.elearning.libs.event.CommonEvent;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.stream.client.CustomClient;
	import com.deodio.elearning.libs.utils.MediaUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.service.SystemService;
	
	import flash.events.AsyncErrorEvent;
	import flash.events.EventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.events.NetStatusEvent;
	import flash.events.SecurityErrorEvent;
	import flash.media.Camera;
	import flash.media.Microphone;
	import flash.media.SoundTransform;
	import flash.net.NetConnection;
	import flash.net.NetStream;
	import flash.net.ObjectEncoding;
	
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class MediaStreamController extends EventDispatcher implements IStreamController {


		public var _model:ModelLocator=ModelLocator.getInstance();

		public var connection:NetConnection=null;
		public var stream:NetStream=null;
		[Bindable]
		public var camera:Camera;
		[Bindable]
		public var microPhone:Microphone;

		//INFO: For example:rtmp://[ip]:1935/ulearn
		public var serverUrl:String="";

		public var mediaUrl:String="";

		protected var fmsServerPath:String="";

		public var seek:Number=0;

		public var isSetUrl:Boolean=false;

		public var isConnect:Boolean=false;

		public var isRed5:Boolean=true;

		//INFOR: stream proxy type, rmtp or http.
		public var isRmtp:Boolean=false;

		//INFO: Default value is AMF0
		public var objectEncoding:uint=ObjectEncoding.AMF3;

		//INFO: Buffer time for stream, Default value is 2
		public var bufferTime:int=0.01;

		//INFO: Incremental value 
		private var incBufferLength:Number=0.01;

		//INFO: New buffer size
		private var newBufferLength:Number=0.01;

		//INFO: Max buffer size
		private var maxBufferLength:Number=0.01;

		private var duration:Number=0;

		public var systemService:SystemService;

		[Bindable]
		public var isPlay:Boolean=false;

		public var sound:Number=1;

		//INFO: Constructor
		public function MediaStreamController() {
		}

		public function init():void {
		}

		public function initCameraAndMic(isLoopBack:Boolean):void {

		}

		public function closeCameraAndMic():void {

		}

		public function begin():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_CONNECT_BEGIN_LOADING));
		}

		public function finish():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_CONNECT_FINISH_LOADING));
		}

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		/*		Core Function                                                  */

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
		//INFO: connect
		public function netConnect():void {
			try {
				begin();
				connection=new NetConnection();
				connection.objectEncoding=objectEncoding;
				connection.addEventListener(NetStatusEvent.NET_STATUS, netConnectStatusHandler);
				connection.addEventListener(SecurityErrorEvent.SECURITY_ERROR, netConnectSecurityErrorHandler);
				connection.addEventListener(AsyncErrorEvent.ASYNC_ERROR, netConnectAsyncErrorStatusHandler);
				connection.client=this;
				connection.connect(this.serverUrl);
			} catch (e:Error) {
				trace(this + " Stream connect fail...");
			}
		}

		public function streamConnect():void {
			try {
				_netConnect();
				begin();
				stream=new NetStream(this.connection);
				stream.bufferTime=bufferTime
				stream.addEventListener(NetStatusEvent.NET_STATUS, netConnectStatusHandler);
				stream.client=new CustomClient;
				stream.client.addEventListener(CustomClient.STREAME_COMPLETE, clientStreamCompleteHandler);
				stream.client.addEventListener(CustomClient.CUSTOMCLIENT_INIT, clientStreamInitHandler);
				setSound(_model.sound);
				setMedia();
			} catch (e:Error) {
				trace(this + " Stream connect fail...");
			}
		}

		public function setMedia():void {
			trace(this + " set media url...");
		}

		public function playUrl(url:String, seek:Number, isPlay:Boolean=true):void {
			trace(this + " set play url...");
		}

		public function switchProtocol():void {
			var protocal:String=this.serverUrl.substring(0, this.serverUrl.indexOf("/") - 1);
			switch (protocal.toLowerCase()) {
				case "rtmp":
					serverUrl=serverUrl.replace("rtmp", "rtmpt").replace(":1935/", ":80/");
					netConnect();
					break;
				case "rtmpt":
					serverUrl=serverUrl.replace("rtmpt", "rtmps").replace(":80/", ":443/");
					netConnect();
					break;
				case "rtmps":
					serverUrl=serverUrl.replace("rtmps", "http").replace(":443/", ":8080/");
					netConnect();
					break;
				case "http":
					callBackNetConnectionFailedFunc();
					break;

			}
		}

		public function closeConnection():void {
			if (connection != null) {
				connection.close();
				connection=null;
			}
		}

		public function closeStream():void {
			if (this.stream != null) {
				this.stream.play(false);
				this.stream.close();
				this.stream=null;
			}
		}

		public function stop():void {
			if (this.stream != null)
				this.stream.close();
		}

		public function play():void {
			if (this.stream != null) {
				if (seek > 0) {
					this.pause();
					stream.seek(seek);
					seek=0;
				}
				this.stream.resume();
			}
		}

		public function resume():void {
			if (this.stream != null) {
				stream.seek(0);
				this.stream.resume();
			}
		}

		public function pause():void {
			if (this.stream != null) {
				this.stream.pause();
			}
		}

		public function togglePause():void {
			if (this.stream != null) {
				this.stream.togglePause();
			}
		}

		public function setSound(num:Number):void {
			if (this.stream != null) {
				var transform:SoundTransform=stream.soundTransform;
				transform.volume=num;
				stream.soundTransform=transform;
			}
		}

		public function getPlayTime():Number {
			if (this.stream != null) {
				return this.stream.time;
			} else {
				return 0;
			}
		}


		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		/*		Stream Event Function                                          */

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
		public function netConnectSuccess():void {
			trace(this + " Net connect success...");
			isConnect=true;
			streamConnect();
		}

		public function netConnectionFailed():void {

			trace(this + " Net connect failed...");
			isConnect=false;
			switchProtocol();
		}

		public function netConnectClosed():void {
			trace(this + " Net connect closed...");
			isConnect=false;
		}

		public function streamNotFound():void {
			trace(this + "Stream not found...");
		}

		public function streamPlayComplete():void {
			trace(this + " Stream play complete...");
			callBackStreamPlayCompleteFunc();
		}

		public function streamReset():void {
			trace(this + " Stream reset...");
			if (isPlay == false) {
				this.pause();
			}
		}

		public function streamPlay():void {
			trace(this + " Stream play start...");
			trace("isPlay ------" + isPlay);
			//INFO: init stream play..
			if (isPlay == true) {
				callBackStreamPlayFunc();
			} else {
				this.pause();
				callBackStreamPauseFunc();
			}
			if (seek > 0) {
				this.pause();
				stream.seek(seek);
				seek=0;
			}
		}

		public function streamStop():void {
			trace(this + " Stream play stop...");
			callBackStreamStopFunc();
		}

		public function streamPublish():void {
			trace(this + " Stream publish start...");
			callBackStreamPublishFunc();
		}

		public function streamRecordStart():void {
			trace(this + " Stream record start...");
			callBackStreamRecordStartFunc();
		}

		public function streamRecordStop():void {
			trace(this + "Stream record stop....");
			callBackStreamRecordStopFunc();
		}
		
		public function streamPlayUnpublishNotify():void {
			trace(this + "Stream play unpublish notify....");
			callBackStreamPlayUnpublishNotifyFunc();
		}

		public function streamUnPublish():void {
			trace(this + " Stream unpublish success...");
			callBackStreamUnPublishFunc();
		}

		public function streamSeekNotify():void {
			trace(this + " Stream seek notify...");
			if (this.isPlay != true) {
				this.pause();
			} else {
				this.play();
			}
		}

		public function streamPause():void {
			trace(this + "Stream pause...");
		}

		public function streamUnPause():void {
			trace(this + "Stream un pause...");
		}

		public function streamBufferEmpty():void {
			trace(this + "Stream buffer empty...");
			if (stream != null)
				stream.bufferTime=this.bufferTime;
			if (isPlay != true) {
				this.pause();
			}
		}

		public function streamBufferFull():void {
			trace(this + "Stream buffer full...");

			//INFO : Set dynamic buffer...
			if (newBufferLength < maxBufferLength) {
				newBufferLength=newBufferLength + incBufferLength;
				trace(this + " newBufferLength = " + newBufferLength);
			}
			stream.bufferTime=newBufferLength;

			if (duration > 0 && stream.bufferTime > Math.floor(duration - getPlayTime())) {
				stream.bufferTime=Math.floor(duration - getPlayTime());
			}

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

		public function streamBufferStart():void {
			trace(this + "Stream buffer start...");
		}

		public function streamBufferFlush():void {
			trace(this + "Stream buffer flush...");
			if (this.isPlay == false) {
				this.pause();
			}
			callBackDispathFlush();
		}



		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		/*		Event Handler                                                  */

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
		private function netConnectStatusHandler(event:NetStatusEvent):void {
			trace(this + "----event.info.code-------" + event.info.code);
			switch (event.info.code) {
				case "NetConnection.Connect.Success":
					netConnectSuccess();
					break;
				case "NetConnection.Connect.Closed":
					finish();
					netConnectClosed();
					break;
				case "NetConnection.Connect.Rejected":
					break;
				case "NetConnection.Connect.Failed":
					finish();
					netConnectionFailed();
					break;
				case "NetStream.Play.Complete":
					finish();
					streamPlayComplete();
					break;
				case "NetStream.Play.StreamNotFound":
					finish();
					streamNotFound();
					break;
				case "NetStream.Play.Reset":
					finish();
					streamReset();
					break;
				case "NetStream.Play.Start":
					finish();
					streamPlay();
					break;
				case "NetStream.Play.Stop":
					finish();
					streamStop();
					break;
				case 'NetStream.Publish.Start':
					finish();
					streamPublish();
					break;
				case 'NetStream.Unpublish.Success':
					finish();
					streamUnPublish();
					break;
				case "NetStream.Seek.Notify":
					finish();
					streamSeekNotify();
					break;
				case "NetStream.Unpause.Notify":
					finish();
					streamUnPause();
					break;
				case "NetStream.Pause.Notify":
					finish();
					streamPause();
					break;
				case "NetStream.Buffer.Empty":
					begin();
					streamBufferEmpty();
					break;
				case "NetStream.Buffer.Full":
					finish();
					streamBufferFull();
					break;
				case "NetStream.Buffer.start":
					finish();
					streamBufferStart();
					break;
				case "NetStream.Buffer.Flush":
					finish();
					streamBufferFlush();
					break;
				case "NetStream.Record.Start":
					finish();
					streamRecordStart();
					break;
				case "NetStream.Record.Stop":
					finish();
					streamRecordStop();
					break;
				case "NetStream.Play.UnpublishNotify":
					finish();
					streamPlayUnpublishNotify();
					break;
				
			}

		}

		private function netConnectSecurityErrorHandler(event:SecurityErrorEvent):void {
			trace("securityErrorHandler: " + event);
		}

		private function netConnectAsyncErrorStatusHandler(event:AsyncErrorEvent):void {
			trace("asyncErrorHandler: " + event);
		}

		private function clientStreamCompleteHandler(event:CommonEvent):void {
			trace("clientStreamCompleteHandler");
			finish();
			streamPlayComplete();
		}

		private function clientStreamInitHandler(event:CommonEvent):void {
			trace("clientStreamInitHandler");
			var info:Object=event.data;
			duration=info.duration;
		}


		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		/*		Call back functions                                             */

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		// Callback for bandwidth check done
		public function onBWDone(... rest):void {
			var bandWidth:Number;
			if (rest.length > 0) {
				bandWidth=rest[0];
			}
			trace(this + " bandwidth = " + bandWidth + " Kbps");
		}

		// Callback for bandwidth check
		public function onBWCheck(... rest):Number {
			var bandWidth:Number;
			if (rest.length > 0) {
				bandWidth=rest[0];
			}
			trace(this + " bandwidth check = " + bandWidth + " Kbps");
			return 0; // value doesn't matter
		}

		private function callBackNetConnectionFailedFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_CONNECT_FAILED));
		}

		private function callBackStreamPlayCompleteFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PLAY_COMPLETE, this));
		}

		private function callBackStreamPlayFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PLAY_START));
		}

		private function callBackStreamStopFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PLAY_STOP));
		}

		private function callBackStreamPublishFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PUBLISH));
		}

		private function callBackStreamRecordStartFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_RECORD_START));
		}

		private function callBackStreamRecordStopFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_RECORD_STOP));
		}
		
		private function callBackStreamPlayUnpublishNotifyFunc():void{
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PLAY_UNPUBLISH_NOTIFY));
		}

		private function callBackStreamUnPublishFunc():void {
			isConnect=false;
		}

		private function callBackStreamBufferEmptyFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_BUFFER_EMPTY));
		}

		public function callBackStreamPauseFunc():void {
			var condition:Boolean=MediaUtils.isMp3(mediaUrl);

			if (condition) {
				this.stream.pause();
				this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_SHOW_AUDIO_IMAGE));
			} else {
				this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PAUSE));
			}
		}

		private function callBackDispatchStopFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PLAY_STOP));
		}

		private function callBackDispatchPlayCompleteFunc():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_NET_STREAM_PLAY_COMPLETE, this));
		}

		private function callBackDispatchBufferFull():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_BUFFER_FULL));
		}

		private function callBackDispathFlush():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_BUFFER_FLUSH));
		}

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		/*		Common Function                                                */

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
		private function _netConnect():void {

			if (connection == null) {
				netConnect();
			}
		}


		// override in subclass
		public function getAudioUrl(url:String):String {
			return null;
		}

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		/*		Bean Function                                                  */

		/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

		public function getServerUrl():String {
			return serverUrl;
		}

		public function getFmsServerPath():String {
			return fmsServerPath;
		}

		public function setObjectEncoding(objectEncoding:uint):void {
			this.objectEncoding=objectEncoding;
		}

		public function setBufferTime(bufferTime:int):void {
			this.bufferTime=bufferTime;
		}

		public function micActiveFunc(call:Function):void {

		}

	}
}
