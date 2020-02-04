
package com.deodio.elearning.libs.paper {
	import com.deodio.elearning.libs.skins.DVScrollBarSkin;

	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.utils.getDefinitionByName;

	import mx.containers.Canvas;
	import mx.controls.Alert;

	/**
	 * ZoomCanvas; Provides basic center zooming functionality.
	 *             Use CenteringEnabled(true/false) to turn on and off.
	 */
	public class ZoomCanvas extends Canvas {

		public var CenteringEnabled:Boolean=false;
		private var rX:Number;
		private var rY:Number;
		private var lVscrollPos:Number;
		private var lHscrollPos:Number;
		private var _childrenDoDrag:Boolean=true;

		public function ZoomCanvas() {
			super();
			this.setStyle("verticalScrollBarStyleName", "vScrollBar");
			this.setStyle("horizontalScrollBarStyleName", "vScrollBar");
		}

		override protected function createChildren():void {
			super.createChildren();

			this.addEventListener(MouseEvent.MOUSE_DOWN, startDragging);
		}

		public function get childrenDoDrag():Boolean {
			return this._childrenDoDrag;
		}

		public function set childrenDoDrag(value:Boolean):void {
			this._childrenDoDrag=value;
		}

		protected function startDragging(event:MouseEvent):void {
			if (event.target.parent == this.verticalScrollBar || event.target.parent == this.horizontalScrollBar) {
				return;
			}

			if (_childrenDoDrag || event.target == this) {
				rX=event.stageX;
				rY=event.stageY;

				lVscrollPos=this.verticalScrollPosition;
				lHscrollPos=this.horizontalScrollPosition;

				systemManager.addEventListener(MouseEvent.MOUSE_MOVE, systemManager_mouseMoveHandler, true);

				systemManager.addEventListener(MouseEvent.MOUSE_UP, systemManager_mouseUpHandler, true);

				systemManager.stage.addEventListener(Event.MOUSE_LEAVE, stage_mouseLeaveHandler);

				systemManager.stage.addEventListener(MouseEvent.ROLL_OVER, stage_mouseOverHandler);

				systemManager.stage.addEventListener(MouseEvent.ROLL_OUT, stage_mouseOutHandler);

			}
		}

		private function systemManager_mouseMoveHandler(event:MouseEvent):void {
			event.stopImmediatePropagation();

			this.verticalScrollPosition=lVscrollPos - (event.stageY - rY);
			this.horizontalScrollPosition=lHscrollPos - (event.stageX - rX);
		}

		private function systemManager_mouseUpHandler(event:MouseEvent):void {
			if (!isNaN(rX))
				stopDragging();
		}

		private function stage_mouseLeaveHandler(event:Event):void {
			if (!isNaN(rX))
				stopDragging();
		}

		private function stage_mouseOverHandler(event:Event):void {
		}

		private function stage_mouseOutHandler(event:Event):void {
		}

		protected function stopDragging():void {
			systemManager.removeEventListener(MouseEvent.MOUSE_MOVE, systemManager_mouseMoveHandler, true);

			systemManager.removeEventListener(MouseEvent.MOUSE_UP, systemManager_mouseUpHandler, true);

			systemManager.stage.removeEventListener(Event.MOUSE_LEAVE, stage_mouseLeaveHandler);

			rX=NaN;
			rY=NaN;
		}

		override public function validateDisplayList():void {

			var centerPercentX:Number=0;
			var centerPercentY:Number=0;

			if (maxHorizontalScrollPosition > 0) {
				centerPercentX=horizontalScrollPosition / maxHorizontalScrollPosition;
			} else {
				centerPercentX=0.5;
			}

			if (maxVerticalScrollPosition > 0) {
				centerPercentY=verticalScrollPosition / maxVerticalScrollPosition;
			} else {
				centerPercentY=0.5;
			}

			super.validateDisplayList();
			if (CenteringEnabled) {

				if (maxHorizontalScrollPosition > 0) {
					var newHScrollPosition:Number=maxHorizontalScrollPosition * centerPercentX;
					newHScrollPosition=newHScrollPosition > 0 ? newHScrollPosition : 0;
					newHScrollPosition=newHScrollPosition < maxHorizontalScrollPosition ? newHScrollPosition : maxHorizontalScrollPosition;
					horizontalScrollPosition=newHScrollPosition;
				}

				if (maxVerticalScrollPosition > 0) {
					var newVScrollPosition:Number=maxVerticalScrollPosition * centerPercentY;
					newVScrollPosition=newVScrollPosition > 0 ? newVScrollPosition : 0;
					newVScrollPosition=newVScrollPosition < maxVerticalScrollPosition ? newVScrollPosition : maxVerticalScrollPosition;
					verticalScrollPosition=newVScrollPosition;
				}
			}
		}
	}
}
