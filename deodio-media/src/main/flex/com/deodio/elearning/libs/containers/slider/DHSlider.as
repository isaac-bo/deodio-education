package com.deodio.elearning.libs.containers.slider {
	import flash.events.MouseEvent;

	import spark.components.HSlider;

	public class DHSlider extends HSlider {

		[Bindable]
		public var allowTrackClick:Boolean=true;

		public function DHSlider() {
			super();
		}

		override protected function track_mouseDownHandler(event:MouseEvent):void {
			if (!enabled || !allowTrackClick)
				return;

			super.track_mouseDownHandler(event);
		}
	}
}
