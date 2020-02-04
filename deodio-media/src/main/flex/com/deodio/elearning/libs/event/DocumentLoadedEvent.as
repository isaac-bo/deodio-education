
package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class DocumentLoadedEvent extends Event {
		public static const DOCUMENT_LOADED:String="onDocumentLoaded";

		public var totalPages:Number;

		public function DocumentLoadedEvent(type:String, p:Number) {
			super(type);
			totalPages=p;
		}

		// Override the inherited clone() method.
		override public function clone():Event {
			return new DocumentLoadedEvent(type, totalPages);
		}

	}
}
