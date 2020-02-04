package com.deodio.elearning.domain {
	import com.deodio.elearning.libs.stream.VideoStreamController;

	import flash.media.Video;

	[RemoteClass(alias="com.deodio.elearning.persistence.model.PresentationSyncMedia")]
	public class PresentationSyncMedia {
		public var id:String;
		public var presentationId:String;
		public var mediaId:String;
		public var mediaName:String;
		public var mediaSize:Number;
		public var mediaExt:String;
		public var mediaUrl:String;
		public var mediaDir:String;
		public var mediaLength:Number;
		public var mediaType:int;
		public var mediaOrder:int;
		public var mediaCover:String;
		public var mediaStart:Number;
		public var mediaEnd:Number;
		public var mediaWidth:int;
		public var mediaHeight:int;
		public var createId:String;
		public var createTime:Date;
		public var updateId:String;
		public var updateTime:Date;
		public var mediaOriginalName:String;
		public var mediaConvertStatus:int;
		public var mediaJobId:String;

		public function PresentationSyncMedia() {
		}

	}
}
