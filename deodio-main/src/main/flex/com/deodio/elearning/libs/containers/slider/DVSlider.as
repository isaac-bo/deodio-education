package com.deodio.elearning.libs.containers.slider {
	import flash.events.MouseEvent;

	import spark.components.VSlider;

	public class DVSlider extends VSlider {

		[Bindable]
		public var allowTrackClick:Boolean=true;

		public function DVSlider() {
		}

		override protected function track_mouseDownHandler(event:MouseEvent):void {
			if (!enabled || !allowTrackClick)
				return;

			super.track_mouseDownHandler(event);
		}
	}
}
