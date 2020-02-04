package com.deodio.elearning.libs.containers {
	import mx.containers.BoxDirection;
	import mx.core.mx_internal;

	public class DVDividedBox extends DDividedBox {
		public function DVDividedBox() {
			super();
			mx_internal::layoutObject.direction=BoxDirection.VERTICAL;
		}

		override public function set direction(value:String):void {
		}
	}
}
