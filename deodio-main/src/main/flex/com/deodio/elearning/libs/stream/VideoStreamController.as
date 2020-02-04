package com.deodio.elearning.libs.stream {
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.utils.MediaUtils;
	import com.deodio.elearning.service.SystemService;

	import flash.media.Video;

	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class VideoStreamController extends MediaStreamController {
		private var video:Video=null;

		private var videoUrl:String="";

		public function VideoStreamController() {
			systemService=new SystemService();

		}

		public override function setMedia():void {
			Alert.show("--------------");
			if (video != null) {
				video.clear();
			}
			video=new Video();
			video.attachNetStream(stream);
			stream.play(videoUrl);
			if (this.isPlay == false) {
				stream.pause();
			}
			//INFO: Dispatch a new event
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_CREATE_COMPLETE, this));
		}

		public override function playUrl(url:String, seekNumber:Number, isPlay:Boolean=false):void {
			systemService.getFmsServer(function(event:ResultEvent):void {
				serverUrl=event.result as String;
				// get video url 
				mediaUrl=serverUrl + url;
				isPlay=isPlay;
				isSetUrl=true;
				seek=seekNumber;
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
					videoUrl=MediaUtils.formatMediaUrl(mediaUrl, start, end, false);
					//	mediaUrl=serverUrl + mediaUrl.substr(0, start) + videoUrl;
					mediaUrl=serverUrl + videoUrl;
//					mediaUrl="rtmp://192.168.122.97:1935/lms/vod/4714bc52d79d491a83c31d33f771ade3/387a5b97423d40b29e6f8980c7e659ed/1cd6ac15fd6144679e9a0bb05b44252e/2016/5/26/5LsMQdy2jN9mXEweAKAzkn5M52f05T7g/mp4:5LsMQdy2jN9mXEweAKAzkn5M52f05T7g"
					//mediaUrl="rtmp://192.168.122.97:1935/lms/m/mp4:sample1_1000kbps.f4v"
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
					videoUrl=mediaUrl;

					netConnect();
				}
			});
		}

		public override function closeStream():void {
			super.closeStream();

			if (video != null) {
				video.clear();
				video=null;
			}
			//INFO: Dispatch a new event
			//TODO: Dispatch a new event
			//this.parentObj.videoDisplay.removeChild(myVideo);
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_DELETE_COMPLETE));
		}


		public function formatMediaUrl(start:int, end:int):String {
			var extension:String=MediaUtils.getExtension(mediaUrl).toLowerCase();
			var url:String=null;
			if (extension == "f4v" || extension == "mp4") {
				url="mp4:" + mediaUrl.substring(start, end);
			} else if (extension == "mp3") {
				url="mp3:" + mediaUrl.substring(start, end);
			} else {
				url=mediaUrl.substr(start, end - start);
			}
			trace(this + " formatMediaUrl: " + mediaUrl + ", extension: " + extension + ", start: " + start + ", end: " + end + ", new url: " + url);

			return url;
		}


	}
}
