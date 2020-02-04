package com.deodio.elearning.libs.enum {

	public class StateEnum {
		public static const INIT_STATE:String=new StateEnum(0).toString();
		public static const HIDE_STATE:String=new StateEnum(1).toString();
		public static const SHOW_STATE:String=new StateEnum(2).toString();
		public static const SHOW_RECORD_PICKER_STATE:String=new StateEnum(3).toString();
		public static const HIDE_RECORD_PICKER_STATE:String=new StateEnum(4).toString();
		public static const EDIT_VIDEO_STATE:String=new StateEnum(5).toString();
		public static const EDIT_AUDIO_STATE:String=new StateEnum(6).toString();

		private static var _limit:Boolean=limit();

		private var _value:Object;

		public function StateEnum(value:Object) {
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
					return "initState";
				case 1:
					return "hideState";
				case 2:
					return "showState";
				case 3:
					return "showRecordPicker";
				case 4:
					return "hideRecordPicker";
				case 5:
					return "editVideo";
				case 6:
					return "editAudio";
			}
			return "Undefined!";
		}
	}
}
