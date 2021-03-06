package com.deodio.elearning.service {
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;

	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;


	[Event(name="serviceError", type="flash.events.Event")]
	public class AbstractService extends EventDispatcher {

		public static const SERVICE_ERROR:String="serviceError";

		public function AbstractService(target:IEventDispatcher=null) {
			super(target);
		}

		protected function getServiceInstance():RemoteObject {
			var service:RemoteObject=new RemoteObject();
			service.destination=destination;
			service.makeObjectsBindable=true;
			//service.showBusyCursor=true;
			return service;
		}

		protected function getResponsder(resultFunction:Function, faultFuntion:Function=null):IResponder {

			var appSealFlag:Boolean=false;
			var successFunction:Function=function(data:Object):void {
				appSealFlag=true;
				if (resultFunction != null) {
					resultFunction.call(this, data);
				}
//				Application.application.enabled=appSealFlag;
			};
			var onFault:Function=function(faultEvent:FaultEvent):void {
				if (faultFuntion != null) {
					faultFuntion.call(this, faultEvent);
				}
				dispatchEvent(new Event(AbstractService.SERVICE_ERROR));
				Alert.show(faultEvent.fault.faultString, "Error");
			};
			return new Responder(successFunction, onFault);
		}
		protected var destination:String;
	}
}
