package com.deodio.elearning.domain {

	[RemoteClass(alias="com.deodio.elearning.persistence.model.Quiz")]
	public class Quiz {
		public var id:String;
		public var quizName:String;
		public var quizDesc:String;
		public var createId:String;
		public var createTime:Date;
		public var updateId:String;
		public var updateTime:Date;
		public var accountId:String;
		public var createType:int;
		public var quizCategory:int;
		public var quizType:int;
		public var finishTime:int;
		public var passScore:int;
		public var quizMaxTimes:int;
		public var finallyQuizResultType:int;
		public var publishResultType:int;
		public var quizContent:String;
		public var quizSafe:int;



		public function Quiz() {
		}

	}
}
