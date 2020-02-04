package com.deodio.elearning.libs.stream.client {
	import com.deodio.elearning.libs.event.CommonEvent;

	import flash.events.EventDispatcher;

	import mx.controls.Alert;

	public class CustomClient extends EventDispatcher {
		public static const STREAME_COMPLETE:String="NetStream.Play.Complete";
		public static const CUSTOMCLIENT_INIT:String="CustomClient.onMetaData.Init";

		public function onMetaData(info:Object):void {
			var event:CommonEvent=new CommonEvent(CUSTOMCLIENT_INIT);
			trace("metadata: duration=" + info.duration + " width=" + info.width + " height=" + info.height + " framerate=" + info.framerate);
			event.data=info;
			this.dispatchEvent(event);
		}

		public function onCuePoint(info:Object):void {
			trace("cuepoint: time=" + info.time + " name=" + info.name + " type=" + info.type);
		}

		//onPlayStatus
		public function onPlayStatus(info:Object):void {
			var event:CommonEvent=new CommonEvent(STREAME_COMPLETE);
			trace("onPlayStatus: time=" + info.time + " name=" + info.name + " code=" + info.code);
			event.data=info;
			this.dispatchEvent(event);
		}

		public function onLastSecond(info:Object):void {
			trace("onLastSecond: time=" + info.time + " name=" + info.name + " code=" + info.code);
		}
	}
}

