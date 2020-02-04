package com.deodio.elearning.service {
	import com.deodio.elearning.domain.customize.PresentationBo;
	import com.deodio.elearning.domain.Presentation;
	import com.deodio.elearning.model.ModelLocator;

	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class PresentationService extends AbstractService {
		private static const PRESENTATION_SERVICE_DESTINATION:String="presentationService";

		[Bindable]
		private var _model:ModelLocator=ModelLocator.getInstance();

		public function PresentationService() {
			this.destination=PRESENTATION_SERVICE_DESTINATION;
		}

		public function getPresentation(presentationId:String, resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getPresentation(presentationId);
		}

		public function savePresentation(presentation:PresentationBo, resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.addEventListener(FaultEvent.FAULT, function(event:FaultEvent) {
				Alert.show("-----------" + event.message);
			});
			remote.savePresentation(presentation);
		}
	}
}
