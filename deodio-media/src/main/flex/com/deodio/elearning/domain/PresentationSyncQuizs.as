package com.deodio.elearning.domain {

	[RemoteClass(alias="com.deodio.elearning.persistence.model.PresentationSyncQuizs")]
	public class PresentationSyncQuizs {
		public var id:String;
		public var presentationId:String;
		public var quizId:String;
		public var quizTime:int;
		public var quizOrder:int;
		public var createId:String;
		public var createTime:Date;
		public var updateId:String;
		public var updateTime:Date;
		public var quizName:String;
		public var quizDesc:String;




		public function PresentationSyncQuizs() {
		}

	}
}
