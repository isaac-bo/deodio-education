package com.deodio.elearning.libs.skins {
	import mx.skins.halo.SliderHighlightSkin;

	public class DMXSliderHighlightSkin extends SliderHighlightSkin {
		public function DMXSliderHighlightSkin() {
		}

		override public function get measuredHeight():Number {
			return 2;
		}

		override protected function updateDisplayList(w:Number, h:Number):void {
			super.updateDisplayList(w, h);

//			var themeColor:int=getStyle("themeColor");

			graphics.clear();

			// Highlight 0xed4e40 0x526C8D
			drawRoundRect(0, 4, w, 2, 0, 0xed4e40, 1);

		}

	}
}
