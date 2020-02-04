package com.deodio.elearning.service {
	import com.deodio.elearning.model.ModelLocator;

	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class QuizExaminationService extends AbstractService {
		private static const QUIZ_EXAMINATION_SERVICE_DESTINATION:String="quizExaminationService";

		[Bindable]
		private var _model:ModelLocator=ModelLocator.getInstance();

		public function QuizExaminationService() {
			this.destination=QUIZ_EXAMINATION_SERVICE_DESTINATION;
		}

		public function getQuizExaminationList(accountId:String, resultFunction:Function):void {
			var remote:RemoteObject=getServiceInstance();
			remote.addEventListener(ResultEvent.RESULT, resultFunction);
			remote.getQuizExaminationList(accountId);
		}

	}
}

