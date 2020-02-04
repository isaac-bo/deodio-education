package com.deodio.elearning.controls.spoint {
	import com.deodio.elearning.controls.IExecutor;
	import com.deodio.elearning.domain.customize.QuizPoints;
	import com.deodio.elearning.libs.constants.Constants;
	import com.deodio.elearning.libs.utils.MediaUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.plugin.ActionModelLocator;
	
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;

	public class QuizPointExecutor implements IExecutor {

		[Bindable]
		public var _model:ModelLocator=ModelLocator.getInstance();

		[Bindable]
		public var _actionLocator:ActionModelLocator=ActionModelLocator.getInstance();

		//		public function SyncPoint() {
		//		}

		public function action(meta:Dictionary):void {
			action4Preview(meta);
		}

		private function action4Preview(meta:Dictionary):void {
			var points:ArrayCollection=meta["points"] as ArrayCollection;

			if (this._model.quizCollection.length != points.length) {
				executeQuizs(points);
			} else {
				var isFlag:Boolean=false;
				for each (var quiz:QuizPoints in points) {
					for each (var quiz2:QuizPoints in this._model.quizCollection) {
						if (quiz.itemId == quiz2.itemId) {
							isFlag=true;
						}

						if (isFlag)
							break;
					}

					if (isFlag)
						break;
				}

				if (!isFlag) {
					executeQuizs(points);
				}
			}
		}


		private function executeQuizs(points:ArrayCollection):void {
			this._model.quizCollection=points;
			for each (var point:QuizPoints in points) {
				Alert.show("---------------" + point.itemId);
			}
			var param:Object=new Object();
			param.points=points;
			_actionLocator.command(Constants.COMMAND_PREVIEW_MULTIPLE_QUIZ, "", param.points);
		}
	}
}

