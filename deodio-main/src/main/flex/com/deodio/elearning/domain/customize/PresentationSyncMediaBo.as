package com.deodio.elearning.domain.customize {
	import com.deodio.elearning.domain.PresentationSyncMedia;
	import com.deodio.elearning.libs.stream.VideoStreamController;

	public class PresentationSyncMediaBo extends PresentationSyncMedia {

		public var videoStream:VideoStreamController;

		public function PresentationSyncMediaBo(presentationSyncMedia:PresentationSyncMedia) {

			this.id=presentationSyncMedia.id;
			this.presentationId=presentationSyncMedia.presentationId;
			this.mediaId=presentationSyncMedia.mediaId;
			this.mediaName=presentationSyncMedia.mediaName;
			this.mediaSize=presentationSyncMedia.mediaSize;
			this.mediaExt=presentationSyncMedia.mediaExt;
			this.mediaUrl=presentationSyncMedia.mediaUrl;
			this.mediaDir=presentationSyncMedia.mediaDir;
			this.mediaLength=presentationSyncMedia.mediaLength;
			this.mediaType=presentationSyncMedia.mediaType;
			this.mediaOrder=presentationSyncMedia.mediaOrder;
			this.mediaCover=presentationSyncMedia.mediaCover;
			this.mediaStart=presentationSyncMedia.mediaStart;
			this.mediaEnd=presentationSyncMedia.mediaEnd;
			this.mediaWidth=presentationSyncMedia.mediaWidth;
			this.mediaHeight=presentationSyncMedia.mediaHeight;
			this.createId=presentationSyncMedia.createId;
			this.createTime=presentationSyncMedia.createTime;
			this.updateId=presentationSyncMedia.updateId;
			this.updateTime=presentationSyncMedia.updateTime;
			this.mediaOriginalName=presentationSyncMedia.mediaOriginalName;
			this.mediaConvertStatus=presentationSyncMedia.mediaConvertStatus;
			this.mediaJobId=presentationSyncMedia.mediaJobId;

		}

		public function getInstance():PresentationSyncMedia {
			var presentationSyncMedia:PresentationSyncMedia=new PresentationSyncMedia();
			presentationSyncMedia.id=this.id;
			presentationSyncMedia.presentationId=this.presentationId;
			presentationSyncMedia.mediaId=this.mediaId;
			presentationSyncMedia.mediaName=this.mediaName;
			presentationSyncMedia.mediaSize=this.mediaSize;
			presentationSyncMedia.mediaExt=this.mediaExt;
			presentationSyncMedia.mediaUrl=this.mediaUrl;
			presentationSyncMedia.mediaDir=this.mediaDir;
			presentationSyncMedia.mediaLength=this.mediaLength;
			presentationSyncMedia.mediaType=this.mediaType;
			presentationSyncMedia.mediaOrder=this.mediaOrder;
			presentationSyncMedia.mediaCover=this.mediaCover;
			presentationSyncMedia.mediaStart=this.mediaStart;
			presentationSyncMedia.mediaEnd=this.mediaEnd;
			presentationSyncMedia.mediaWidth=this.mediaWidth;
			presentationSyncMedia.mediaHeight=this.mediaHeight;
			presentationSyncMedia.createId=this.createId;
			presentationSyncMedia.createTime=this.createTime;
			presentationSyncMedia.updateId=this.updateId;
			presentationSyncMedia.updateTime=this.updateTime;
			presentationSyncMedia.mediaOriginalName=this.mediaOriginalName;
			presentationSyncMedia.mediaConvertStatus=this.mediaConvertStatus;
			presentationSyncMedia.mediaJobId=this.mediaJobId;
			return presentationSyncMedia;
		}
	}
}
