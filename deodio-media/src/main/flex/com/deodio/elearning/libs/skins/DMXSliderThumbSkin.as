package com.deodio.elearning.libs.skins {
	import mx.controls.sliderClasses.SliderThumb;

	public class DMXSliderThumbSkin extends SliderThumb {
		override protected function measure():void {
			super.measure();
			measuredWidth=10;
			measuredHeight=15;
		}
	}
}
