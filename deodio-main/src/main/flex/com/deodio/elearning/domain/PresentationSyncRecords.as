package com.deodio.elearning.domain {
	import com.deodio.elearning.libs.stream.AudioStreamController;

	[RemoteClass(alias="com.deodio.elearning.persistence.model.PresentationSyncRecords")]
	public class PresentationSyncRecords {
		public var id:String;
		public var presentationId:String;
		public var pointId:String;
		public var recordName:String;
		public var recordSize:int;
		public var recordExt:String;
		public var recordUrl:String;
		public var recordDir:String;
		public var recordLength:Number;
		public var recordOrder:int;
		public var createId:String;
		public var createTime:Date;
		public var updateId:String;
		public var updateTime:Date;
		public var audioStream:AudioStreamController;



		public function PresentationSyncRecords() {
		}

	}
}
