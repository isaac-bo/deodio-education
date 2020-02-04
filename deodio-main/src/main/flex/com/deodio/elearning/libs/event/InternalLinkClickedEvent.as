
package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class InternalLinkClickedEvent extends Event {
		public var pagenumber:int;
		public static const INTERNALLINK_CLICKED:String="onInternalLinkClicked";

		public function InternalLinkClickedEvent(type:String, lpagenumber:int) {
			super(type);
			pagenumber=lpagenumber;
		}

		// Override the inherited clone() method.
		override public function clone():Event {
			return new InternalLinkClickedEvent(type, pagenumber);
		}
	}
}
