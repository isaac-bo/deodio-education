package com.deodio.elearning.domain.customize {
	import com.deodio.elearning.domain.PresentationSyncQuizs;

	public class PresentationSyncQuizsBo extends PresentationSyncQuizs {

		public var currentPage:Number;
		public var totalPage:Number;

		public function PresentationSyncQuizsBo(presentationSyncQuizs:PresentationSyncQuizs) {


			this.id=presentationSyncQuizs.id;
			this.presentationId=presentationSyncQuizs.presentationId;
			this.quizId=presentationSyncQuizs.quizId;
			this.quizTime=presentationSyncQuizs.quizTime;
			this.quizOrder=presentationSyncQuizs.quizOrder;
			this.createId=presentationSyncQuizs.createId;
			this.createTime=presentationSyncQuizs.createTime;
			this.updateId=presentationSyncQuizs.updateId;
			this.updateTime=presentationSyncQuizs.updateTime;
			this.quizName=presentationSyncQuizs.quizName;
			this.quizDesc=presentationSyncQuizs.quizDesc;
		}


		public function getInstance():PresentationSyncQuizs {
			var presentationSyncQuizs:PresentationSyncQuizs=new PresentationSyncQuizs();

			presentationSyncQuizs.id=this.id;
			presentationSyncQuizs.presentationId=this.presentationId;
			presentationSyncQuizs.quizId=this.quizId;
			presentationSyncQuizs.quizTime=this.quizTime;
			presentationSyncQuizs.quizOrder=this.quizOrder;
			presentationSyncQuizs.createId=this.createId;
			presentationSyncQuizs.createTime=this.createTime;
			presentationSyncQuizs.updateId=this.updateId;
			presentationSyncQuizs.updateTime=this.updateTime;
			presentationSyncQuizs.quizName=this.quizName;
			presentationSyncQuizs.quizDesc=this.quizDesc;

			return presentationSyncQuizs;
		}
	}
}
