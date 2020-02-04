package com.deodio.elearning.service {
	import com.deodio.elearning.model.ModelLocator;

	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class PresentationRecordService extends AbstractService {
		private static const PRESENTATION_RECORD_SERVICE_DESTINATION:String="presentationRecordService";

		[Bindable]
		private var _model:ModelLocator=ModelLocator.getInstance();

		public function PresentationRecordService() {
			this.destination=PRESENTATION_RECORD_SERVICE_DESTINATION;
		}

		public function getPresentationSyncRecords(presentationId:String, resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getPresentationSyncRecords(presentationId);
		}

		public function getPresentationSyncRecordsBo(presentationId:String, resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getPresentationSyncRecordsBo(presentationId);
		}
	}
}
