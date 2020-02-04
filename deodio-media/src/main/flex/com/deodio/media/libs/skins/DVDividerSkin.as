package com.deodio.media.libs.skins {

	import flash.display.Graphics;

	import mx.skins.ProgrammaticSkin;

	public class DVDividerSkin extends ProgrammaticSkin {

		public function DVDividerSkin() {
			super();
		}

		override public function get measuredWidth():Number {
			return 23;
		}

		override public function get measuredHeight():Number {
			return 6;
		}

		override protected function updateDisplayList(w:Number, h:Number):void {
			var g:Graphics=this.graphics;
			var i:int=0;
			g.clear();
			g.lineStyle(0, 0x000000, 0);
			g.beginFill(0xFFFFFF, 1);
			g.drawRect(6, 2, 10, 1);
			g.endFill();

			g.beginFill(0xFFFFFF, 1);
			g.drawRect(6, 4, 10, 1);
			g.endFill();

			g.endFill();

		}

	}

}
