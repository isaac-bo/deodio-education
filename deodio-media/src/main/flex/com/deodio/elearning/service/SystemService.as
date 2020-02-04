package com.deodio.elearning.service {
	import com.deodio.elearning.model.ModelLocator;

	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class SystemService extends AbstractService {
		private static const SYSTEM_SERVICE_DESTINATION:String="systemService";

		[Bindable]
		private var _model:ModelLocator=ModelLocator.getInstance();

		public function SystemService() {
			this.destination=SYSTEM_SERVICE_DESTINATION;
		}

		public function getFmsServer(resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getFmsServer();
		}

		public function getFmsServerUrl(resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getFmsServerUrl();
		}

		public function isRed5(resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.isRed5();
		}

		public function getSystemUrl(resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getSystemUrl();
		}

		public function getImageServerUrl(resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getImageServerUrl();
		}
	}
}
