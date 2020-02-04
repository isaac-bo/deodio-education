
package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class CurrentPageChangedEvent extends Event {
		public static const PAGE_CHANGED:String="onCurrPageChanged";
		public var pageNum:Number;
		public var prevPageNum:Number;
		public var interactive:Boolean=false;

		public function CurrentPageChangedEvent(type:String, p:Number, pp:Number, inter:Boolean=false) {
			super(type);
			pageNum=p;
			prevPageNum=pp;
			interactive=inter;
		}

		// Override the inherited clone() method.
		override public function clone():Event {
			return new CurrentPageChangedEvent(type, pageNum, prevPageNum);
		}

	}
}
