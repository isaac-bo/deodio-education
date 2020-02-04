package com.deodio.elearning.domain {

	[RemoteClass(alias="com.deodio.elearning.persistence.model.PresentationSyncPoints")]
	public class PresentationSyncPoints {
		public var id:String;
		public var presentationId:String;
		public var syncSlideId:String;
		public var pointSize:int;
		public var pointExt:String;
		public var pointUrl:String;
		public var pointDir:String;
		public var pointTime:int;
		public var pointOrder:int;
		public var pointEnd:int;
		public var createId:String;
		public var createTime:Date;
		public var updateId:String;
		public var updateTime:Date;



		public function PresentationSyncPoints() {
		}

	}
}
