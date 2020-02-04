package com.deodio.elearning.domain.customize {
	import com.deodio.elearning.domain.PresentationSyncPoints;

	import mx.collections.ArrayCollection;

	[RemoteClass(alias="com.deodio.elearning.persistence.model.customize.PresentationSyncRecordsBo")]
	public class PresentationSyncRecordsBo extends PresentationSyncPoints {

		public var syncRecords:ArrayCollection;

		public function PresentationSyncRecordsBo() {
		}
	}
}
