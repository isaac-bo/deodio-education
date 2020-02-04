package com.deodio.elearning.libs.skins {
	import flash.display.GradientType;
	import flash.display.Graphics;
	import flash.display.InterpolationMethod;
	import flash.display.SpreadMethod;
	import flash.geom.Matrix;

	import mx.skins.ProgrammaticSkin;

	public class DVBoxDividerSkin extends ProgrammaticSkin {
		public function DVBoxDividerSkin() {
			super();
		}

		override protected function updateDisplayList(w:Number, h:Number):void {
			var g:Graphics=graphics;
			var matrix:Matrix=new Matrix();
			if (isNaN(w) || isNaN(h))
				return;
			g.clear();
			g.lineStyle(1, 0xb8b8b8, 1);
			g.beginFill(0xb8b8b8, 0);
			g.drawRect(0, 0, w - 1, h - 1);
			g.endFill();
			matrix.createGradientBox(w - 2, h - 2, 0, 0, 0);
			g.lineStyle(0, 0x000000, 0);
			g.beginGradientFill(GradientType.LINEAR, [0xb8b8b8], [1], [0], matrix, SpreadMethod.PAD, InterpolationMethod.LINEAR_RGB, 0);
			g.drawRect(1, 1, w - 2, h - 2);
			g.endFill();
		}


	}
}
