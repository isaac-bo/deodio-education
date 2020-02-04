package com.deodio.elearning.service{
	import com.deodio.elearning.model.ModelLocator;
	
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class MessageService extends AbstractService {
		private static const MESSAGE_SERVICE_DESTINATION:String="messageService";
		
		[Bindable]
		private var _model:ModelLocator=ModelLocator.getInstance();
		
		public function MessageService() {
			this.destination=MESSAGE_SERVICE_DESTINATION;
		}
		
		public function executeLiveStudio(presentationId:String,presentationCreator:String,resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.executeLiveStudio(presentationId,presentationCreator);
		}
		
		public function executeRequestLiveRoom(presentationId:String,userId:String,resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.executeRequestLiveRoom(presentationId,userId);
		}
		
		public function executeCurrentLiveUser(presentationId:String,userId:String,resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.executeCurrentLiveUser(presentationId,userId);
		}
		
		public function stopLiveStudio(presentationId:String,presentationCreator:String,resultFunction:Function):void{
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.stopLiveStudio(presentationId,presentationCreator);
		}
		
		public function getMemcacheValue(key:String,resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getMemcacheValue(key);
		}
	}
}