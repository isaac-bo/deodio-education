
package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class DocumentPrintedEvent extends Event {
		public static const DOCUMENT_PRINTED:String="onDocumentPrinted";

		public function DocumentPrintedEvent(type:String) {
			super(type);
		}

		// Override the inherited clone() method.
		override public function clone():Event {
			return new DocumentPrintedEvent(type);
		}
	}
}
