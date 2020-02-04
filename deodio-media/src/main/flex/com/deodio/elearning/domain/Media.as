package com.deodio.elearning.domain {

	[RemoteClass(alias="com.deodio.elearning.persistence.model.Media")]
	public class Media {
		public var id:String;
		public var mediaName:String;
		public var mediaDesc:String;
		public var mediaSize:int;
		public var mediaExt:String;
		public var mediaUrl:String;
		public var mediaDir:String;
		public var mediaLength:Number;
		public var mediaType:int;
		public var mediaCover:String;
		public var mediaWidth:int;
		public var mediaHeight:int;
		public var createId:String;
		public var createTime:Date;
		public var updateId:String;
		public var updateTime:Date;
		public var mediaStart:int;
		public var mediaEnd:int;



		public function Media() {
		}

	}
}
