package com.deodio.elearning.domain.customize {
	import com.deodio.elearning.domain.PresentationSyncPoints;

	public class SyncPoints extends Points {
		public function SyncPoints() {
			super();
		}

		public var thumbnail:String;
		public var slideIndex:int;
		public var syncPoint:PresentationSyncPoints;
	}
}
