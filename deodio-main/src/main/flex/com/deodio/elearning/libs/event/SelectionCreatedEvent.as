
package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class SelectionCreatedEvent extends Event {
		public static const SELECTION_CREATED:String="onSelectionCreated";
		public var text:String;

		public function SelectionCreatedEvent(type:String, s:String) {
			super(type);
			text=s;
		}

		// Override the inherited clone() method.
		override public function clone():Event {
			return new SelectionCreatedEvent(type, text);
		}

	}
}
