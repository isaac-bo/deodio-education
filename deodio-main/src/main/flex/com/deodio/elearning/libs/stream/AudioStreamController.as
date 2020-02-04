package com.deodio.elearning.libs.stream {
	import com.deodio.elearning.libs.constants.Constants;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.utils.MediaUtils;
	import com.deodio.elearning.service.SystemService;

	import flash.net.ObjectEncoding;

	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class AudioStreamController extends MediaStreamController {
		public var isEnding:Boolean=false;

		public var url:String="";

		private var audioUrl:String="";

		private var userId:String="";

		public function AudioStreamController() {
//			this.objectEncoding=ObjectEncoding.AMF3;
			this.systemService=new SystemService();
		}

		override public function getAudioUrl(url:String):String {
			return url.replace(getFmsServerPath(), "");
		}

		public function playstaus(item:Object):void {
			if (item.code == "NetStream.Play.Complete") {
				isEnding=true;
			} else {
				isEnding=false;
			}
		}

		public override function setMedia():void {
			stream.receiveAudio(true);
			stream.receiveVideo(true);
			stream.play(audioUrl);

		}

		public override function playUrl(url:String, seek:Number, isPlay:Boolean=false):void {

			trace(this + "----- play url :------- " + serverUrl + url);
			this.isPlay=isPlay;
			this.isSetUrl=true;
			this.seek=seek;
			this.url=url;
			systemService.getFmsServer(function(event:ResultEvent):void {
				serverUrl=event.result as String;

				// get video url 
				mediaUrl=serverUrl + url + "." + Constants.RECORDING_MEDIA_TYPE;
//				Alert.show("========" + mediaUrl);
				//INFO: using rtmp to access fms
				if (mediaUrl.substring(0, 4).toLowerCase() == "rtmp") {
					isRmtp=true;
					// Modify media and video urls based on FMS config and video types
					//  - remove server path
					//  - for .flv videos, remove extension
					//  - for .mp4 videos, add "mp4:" in front of video path
					mediaUrl=mediaUrl.replace(serverUrl, "");
					//	var start:int=mediaUrl.lastIndexOf("/") + 1;
					var start:int=0;
					var end:int=mediaUrl.lastIndexOf(".");
					audioUrl=MediaUtils.formatMediaUrl(mediaUrl, start, end, false);
					//	mediaUrl=serverUrl + mediaUrl.substr(0, start) + videoUrl;
					mediaUrl=serverUrl + audioUrl;
					try {
						if (!isConnect) {
							netConnect();
						} else {
							streamConnect();
						}
					} catch (e:Error) {

					}
				} else if (mediaUrl.substring(0, 4).toLowerCase() == "http") {
					isRmtp=false;
					bufferTime=seek + 2;

					serverUrl=null;
					audioUrl=mediaUrl;

					netConnect();
				}
			});
		}

	}
}
