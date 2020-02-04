package com.deodio.media.libs.containers.slider {

	import flash.display.Sprite;

	import mx.core.UIComponent;

	public class DSlider extends UIComponent {
		private var sp:Sprite;
		private var zp:Sprite;
		private var comp:UIComponent;

		public function DSlider() {
			comp=new UIComponent();
			sp=new Sprite();
		}

		public function selfDrawLine(sX:int, sY:int, sWidth:Number, sHeight:int, myColor:uint):void {
			sp.graphics.clear();
			sp.graphics.beginFill(myColor);
			sp.graphics.drawRect(sX, sY, sWidth, sHeight);
			sp.graphics.endFill();
			comp.addChild(sp);
			this.addChild(comp);
		}

	}
}
