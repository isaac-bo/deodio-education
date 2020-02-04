package com.deodio.elearning.libs.utils {
	import com.deodio.elearning.model.ModelLocator;

	import flash.display.Bitmap;
	import flash.display.Loader;
	import flash.events.Event;
	import flash.net.URLRequest;
	import flash.system.LoaderContext;

	import mx.controls.Alert;
	import mx.controls.Image;

	public class MediaUtils extends CommonUtils {

		public static function middlePosition(contentWidth:Number, contentHeight:Number, containerWidth:Number, containerHeight:Number):Object {
			var w_percent:Number=contentWidth / containerWidth;
			var h_percent:Number=contentHeight / containerHeight;
			var _x:Number=0;
			var _y:Number=0;
			var _width:Number;
			var _height:Number;
			if (w_percent > h_percent) {
				_width=containerWidth;
				_height=contentHeight / w_percent;
				_x=0;
				_y=(containerHeight - _height) / 2;
			}

			if (w_percent < h_percent) {
				_height=containerHeight;
				_width=contentWidth / h_percent;
				_y=0;
				_x=(containerWidth - _width) / 2;
			}

			if (w_percent == 1 && h_percent == 1) {
				_height=contentHeight;
				_width=contentWidth;
				_y=0;
				_x=0;
			}

			if (w_percent == h_percent && w_percent != 1) {
				_height=containerHeight;
				_width=containerWidth;
				_y=0;
				_x=0;
			}

			var object:Object=new Object();
			if (contentWidth != 0 && contentHeight != 0) {
				object.x=(containerWidth - Math.floor(_width)) / 2;
				object.y=(containerHeight - Math.floor(_height)) / 2;
				object.width=Math.floor(_width);
				object.height=Math.floor(_height);
			} else {
				//				object.x=isAudio == "true" ? 0 : display.x;
				//				object.y=isAudio == "true" ? 0 : display.y;
				//				object.width=isAudio == "true" ? containerWidth : display.width;
				//				object.height=isAudio == "true" ? containerHeight : display.height;
			}
			return object;
		}

		public static function changeImage(path:String, slideThumbs:Image, width:Number, height:Number, callBack:Function):void {
			var bitmap:Bitmap;
			var _model:ModelLocator=ModelLocator.getInstance();
			if (!_model.caches[path]) {
				var request:URLRequest=new URLRequest(path);
				var loader:Loader=new Loader();
				loader.load(request, new LoaderContext(true));
				loader.contentLoaderInfo.addEventListener(Event.COMPLETE, function(event:Event):void {
					bitmap=Bitmap(loader.content);
					var object:Object=middlePosition(bitmap.width, bitmap.height, width, height);
					bitmap.x=object.x;
					bitmap.y=object.y;
					bitmap.width=object.width;
					bitmap.height=object.height;
					bitmap.smoothing=true;
					_model.caches[path]=bitmap;
					if (slideThumbs.numChildren > 0 && slideThumbs.getChildAt(0) is Bitmap) {
						slideThumbs.removeChildAt(0);
					}
					slideThumbs.addChild(bitmap);
				});
			} else {
				bitmap=_model.caches[path] as Bitmap;
				var object:Object=middlePosition(bitmap.width, bitmap.height, width, height);
				bitmap.x=object.x;
				bitmap.y=object.y;
				bitmap.width=object.width;
				bitmap.height=object.height;
				bitmap.smoothing=true;
				if (slideThumbs.numChildren > 0 && slideThumbs.getChildAt(0) is Bitmap) {
					slideThumbs.removeChildAt(0);
				}
				slideThumbs.addChild(bitmap);
			}
		}

		public static function isMp3(videoUrl:String):Boolean {
			var condition:Boolean=false;
			getExtension(videoUrl).toLowerCase() == "mp3" ? condition=true : condition=false;
			return condition;
		}

		public static function getExtension(filePath:String):String {
			var i:int=filePath.lastIndexOf('.');
			if ((i > -1) && (i < (filePath.length))) {
				return filePath.substring(i + 1, filePath.length);
			}
			return "";
		}

		// Format the media URL by removing .flv extension or adding "mp4:" ("mp3:") depending on media type
		public static function formatMediaUrl(mediaUrl:String, start:int, end:int, isRed5:Boolean):String {
			var extension:String=MediaUtils.getExtension(mediaUrl).toLowerCase();
			if (!isRed5) {

				var url:String=null;
				if (extension == "mp4") {
					url="mp4:" + mediaUrl.substring(start, end);
				} else if (extension == "f4v") {
					url="mp4:" + mediaUrl.substring(start, end) + "." + extension;
				} else if (extension == "mp3") {
					url="mp3:" + mediaUrl.substring(start, end);
				} else {
					url=mediaUrl.substr(start, end - start);
				}
			} else {
				url=mediaUrl.substring(start, end) + "." + extension;
			}
			trace("MediaUtils ------------ Formated media url: " + mediaUrl + ", extension: " + extension + ", start: " + start + ", end: " + end + ", new url: " + url);
			return url;
		}


	}
}
