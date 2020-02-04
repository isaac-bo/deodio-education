package com.deodio.elearning.service {
	import com.deodio.elearning.domain.Media;
	import com.deodio.elearning.model.ModelLocator;
	
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class MediaService extends AbstractService {
		private static const MEDIA_SERVICE_DESTINATION:String="mediaService";

		[Bindable]
		private var _model:ModelLocator=ModelLocator.getInstance();

		public function MediaService() {
			this.destination=MEDIA_SERVICE_DESTINATION;
		}

		public function getMedia(mediaId:String, resultFunction:Function):void {

			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getMedia(mediaId);
		}
		
		public function saveMedia(media:Media,lessonId:String,resultFunction:Function):void{
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.saveMedia(media,lessonId);
		}
	}
}
