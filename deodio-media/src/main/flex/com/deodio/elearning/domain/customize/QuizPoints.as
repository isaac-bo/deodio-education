package com.deodio.elearning.domain.customize {

	public class QuizPoints extends Points {
		public function QuizPoints() {
			super();
		}

		public var quizName:String;
		public var quizDesc:String;
		public var quizIndex:int;
		public var syncQuiz:PresentationSyncQuizsBo;
	}
}

