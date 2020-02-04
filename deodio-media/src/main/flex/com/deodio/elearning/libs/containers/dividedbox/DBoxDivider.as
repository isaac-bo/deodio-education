package com.deodio.elearning.libs.containers.dividedbox {
	import flash.display.DisplayObject;

	import mx.containers.DividedBox;
	import mx.containers.DividerState;
	import mx.containers.dividedBoxClasses.BoxDivider;
	import mx.core.mx_internal;
	import mx.skins.ProgrammaticSkin;

	use namespace mx_internal;

	public class DBoxDivider extends BoxDivider {
		public function DBoxDivider() {
			super();
		}

		private var divider:DisplayObject=null;

		private var deodio:DisplayObject=null;

		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
			if (isNaN(width) || isNaN(height))
				return;
			if (!parent)
				return;
			graphics.clear();
			var color:Number=0;
			var alpha:Number=1.0;
			var thickness:Number=getStyle("dividerThickness");
			var vertical:Boolean=DividedBox(owner).isVertical();
			var gap:Number=vertical ? DividedBox(owner).getStyle("verticalGap") : DividedBox(owner).getStyle("horizontalGap");
			var dividerClass:Class=null;
			var deodioClass:Class=null;
			if (state != DividerState.DOWN) {
				dividerClass=Class(vertical ? getStyle("vSkin") : getStyle("hSkin"));
				deodioClass=Class(vertical ? getStyle("vDividerSkin") : getStyle("hDividerSkin"));
				if (!dividerClass && !deodioClass) {
					super.updateDisplayList(unscaledWidth, unscaledHeight);
					return;
				}
				if (!divider) {
					if (dividerClass)
						divider=new dividerClass();
					if (divider)
						addChildAt(divider, 0);
				} else {
					if (dividerClass && !(divider is dividerClass)) {
						removeChild(divider);
						divider=new dividerClass();
						if (divider)
							addChildAt(divider, 0);
					}
				}
				if (divider) {
					divider.width=unscaledWidth;
					divider.height=unscaledHeight;
					if (divider is ProgrammaticSkin) {
						(divider as ProgrammaticSkin).invalidateSize();
						(divider as ProgrammaticSkin).invalidateDisplayList();
					}
				}
				if (gap >= 6) {
					if (!deodio) {
						if (deodioClass)
							deodio=new deodioClass();
						if (deodio)
							addChild(deodio);
					} else {
						if (deodioClass && !(deodio is deodioClass)) {
							removeChild(deodio);
							deodio=new deodioClass();
							if (deodio)
								addChild(deodio);
						}
					}
					if (deodio) {
						deodio.x=Math.round((width - deodio.width) / 2);
						deodio.y=Math.round((height - deodio.height) / 2);
					}
				}
				return;
			}
			color=getStyle("dividerColor");
			alpha=getStyle("dividerAlpha");
			graphics.beginFill(color, alpha);
			if (vertical) {
				var visibleHeight:Number=thickness;
				if (visibleHeight > gap)
					visibleHeight=gap;
				var y:Number=(height - visibleHeight) / 2;
				graphics.drawRect(0, y, width, visibleHeight);
			} else {
				var visibleWidth:Number=thickness;
				if (visibleWidth > gap)
					visibleWidth=gap;
				var x:Number=(width - visibleWidth) / 2;
				graphics.drawRect(x, 0, visibleWidth, height);
			}
			graphics.endFill();
		}
	}
}
