
package com.deodio.elearning.module.paper.tools.ac {
	import flash.display.Sprite;

	public class ShapeMarker extends Sprite {
		public var PageIndex:int=-1;
		public var data:Object=null;
		public var minX:Number;
		public var minY:Number;
		public var maxX:Number;
		public var maxY:Number;
		public var isSearchMarker:Boolean=false;
		public var flagged:Boolean=false;

		public function ShapeMarker() {
		}
	}
}
