package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class MockEvent extends Event {

		public static const MOCK_RESIZE_SCREEN:String="mock.resize.screen";
		public static const MOCK_RESIZE_SCREEN_ING:String="mock.resize.screen.ing";
		public static const MOCK_CHANGE_LAYOUT:String="mock.change.layout";
		public static const MOCK_CHANGE_SYNC_LAYOUT:String="mock.change.sync.layout";
		public static const MOCK_CHANGE_RECORD_LAYOUT:String="mock.change.record.layout";
		public static const MOCK_CHNAGE_MODEL:String="mock.change.model";

		public var data:Object;

		public function MockEvent(type:String, data:Object=null, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
			this.data=data;
		}
	}
}
