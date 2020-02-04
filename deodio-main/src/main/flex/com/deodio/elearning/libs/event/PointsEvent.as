package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class PointsEvent extends Event {


		public static const POINT_REMOVE_POINT:String="point.remove.point";
		public static const POINT_MOUSE_DOWN:String="point.mouse.down";
		public static const POINT_MOUSE_UP:String="point.mouse.up";
		public static const POINT_MOUSE_MOVE:String="point.mouse.move";
		public static const POINT_DRAG_ENTER:String="point.drag.enter";
		public static const POINT_DRAG_COMPLETE:String="point.drag.complete";
		public static const POINT_SELECTED_POINTID:String="point.selected.point.id";
		public static const POINT_MICPHONE_POINT:String="point.micphone.point";
		public static const POINT_SAVE_POINT:String="point.save.point";
		public static const POINT_PRVIEW_POINT:String="point.preview.point";
		public static const POINT_PREVIOUS_POINT:String="point.previous.point";
		public static const POINT_NEXT_POINT:String="point.next.point";

		public var data:Object;

		public function PointsEvent(type:String, data:Object=null, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
			this.data=data;
		}
	}
}
