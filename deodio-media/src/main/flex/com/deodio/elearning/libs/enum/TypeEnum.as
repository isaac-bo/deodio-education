package com.deodio.elearning.libs.enum {

	public class TypeEnum {
		public static const TYPE_CLIPS:String=new TypeEnum(0).toString();
		public static const TYPE_SLIDES:String=new TypeEnum(1).toString();

		private static var _limit:Boolean=limit();

		private var _value:Object;

		public function TypeEnum(value:Object) {
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
					return "clips";
				case 1:
					return "slides";
			}
			return "Undefined!";
		}
	}
}

