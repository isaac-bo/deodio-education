package com.deodio.elearning.libs.utils {
	import mx.controls.Alert;

	public class RecordUtils extends CommonUtils {

		public static function getRecordName(userId:String, presentationId:String):String {
			return "d/" + userId + "/" + presentationId + "/" + (new Date()).getTime().toString();
		}

		public static function getMediaName(userId:String):String {
			return "m/" + userId + (new Date()).getTime().toString();
		}

		public static function getUnusedMediaName():String {
			return "u/UNUSED_MEDIA_" + (new Date()).getTime().toString();
		}
		
		public static function getLiveMedia4Students(userId:String, presentationId:String):String{
			return "l/s/" + userId +"/"+presentationId; 
		}
		
		public static function getLiveMedia4Trainer(userId:String, presentationId:String):String{
			return "l/s/" + userId +"/"+presentationId; 
		}
	}
}
