package com.deodio.elearning.libs.enum {

	public class StreamingEnum {
		public static const STREAMING_VIDEO:String=new StreamingEnum(0).toString();
		public static const STREAMING_AUDIO:String=new StreamingEnum(1).toString();
		public static const STREAMING_RECORD:String=new StreamingEnum(2).toString();

		private static var _limit:Boolean=limit();

		private var _value:Object;

		public function StreamingEnum(value:Object) {
			if (_limit) {
				throw new Error("Cannot initialize Enum outside!");
				return;
			}
			this._value=value;
		}


		private static function limit():Boolean {
			return true;
		}

		public function toString():String {
			switch (this._value as int) {
				case 0:
					return "video";
				case 1:
					return "audio";
				case 2:
					return "record";
			}
			return "Undefined!";
		}
	}
}

