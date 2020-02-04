package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class CommonEvent extends Event {

		public static const SYNC_PROGRESSBAR_MAXIMUM:String="sync.progressbar.maximum";
		public static const SYNC_PROGRESSBAR_VALUE:String="sync.progressbar.value";
		public static const TRIM_PROGRESSBAR_VALUE:String="trim.progressbar.value";
		public static const TRIM_END_POSITION:String="trim.end.position";
		public static const COMPONENT_CREATE_COMPLETE:String="component.create.complete";

		public var data:Object;

		public function CommonEvent(type:String, data:Object=null, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
			this.data=data;
		}
	}
}
