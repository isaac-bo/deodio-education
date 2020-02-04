package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.controls.ICommand;
	import com.deodio.elearning.controls.command.CommandInvoker;
	import com.deodio.elearning.controls.command.ConcreteCommand;
	import com.deodio.elearning.controls.receiver.ReceiverCenter;
	import com.deodio.elearning.controls.receiver.SyncPointCenterFactory;
	import com.deodio.elearning.domain.customize.Points;
	import com.deodio.elearning.libs.constants.Constants;

	import flash.utils.Dictionary;

	import mx.collections.ArrayCollection;
	import mx.controls.Alert;

	[Bindable]
	public class CommandModelLocator extends AbstractMediaModelLocator {

		private static var _commandLocator:CommandModelLocator;

		public var parameterMap:Dictionary;
		public var duration:Number=0;
		public var container:Object;
		public var systemUrl:String;

		public static function getInstance():CommandModelLocator {
			if (_commandLocator == null) {
				_commandLocator=new CommandModelLocator();
			}
			return _commandLocator;
		}

		public function executeCommand(parameterMap:Dictionary, duration:Number):void {
			this.parameterMap=parameterMap;
			this.duration=duration;
			executeTask(executeTask4SyncPointsFunc, callBackTask4SyncPointsFunc);
			executeTask(executeTask4QuizPointsFunc, callBackTask4QuizPointsFunc);
		}

		private function executeTask(taskFunc:Function, callBackFunc:Function):void {
			taskFunc(callBackFunc);
		}

		private function executeTask4QuizPointsFunc(call:Function):void {
			var _quizPoints:ArrayCollection=new ArrayCollection();
			var isQuiz:Boolean=false;
			if (parameterMap[Math.floor(duration)]) {
				var _innerPoints:ArrayCollection=parameterMap[Math.floor(duration)];

				for each (var points:Points in _innerPoints) {
					if (points.type == Constants.SYNC_POINT_TYPE_QUIZE) {
						_quizPoints.addItem(points);
						isQuiz=true;
					}
				}
			}

			if (isQuiz) {
				call(_quizPoints);
			}
		}

		private function executeTask4SyncPointsFunc(call:Function):void {
//			for (var index:int=duration; index >= 0; index--) {
//				var isFlag:Boolean=false;
			if (parameterMap[Math.floor(duration)]) {
				var _innerPoints:ArrayCollection=parameterMap[Math.floor(duration)];

				for each (var points:Points in _innerPoints) {
					if (points.type == Constants.SYNC_POINT_TYPE_SLIDE) {
						call(points);
//						isFlag=true;
					}
				}
			}
//
//				if (isFlag)
//					break;
//			}
		}

		private function callBackTask4QuizPointsFunc(points:ArrayCollection):void {
			var instance:Dictionary=new Dictionary();
			instance["points"]=points;
			instance["duration"]=duration;
			execute(Constants.SYNC_POINT_TYPE_QUIZE, instance);
		}

		private function callBackTask4SyncPointsFunc(point:Points):void {
			var instance:Dictionary=new Dictionary();
			instance["container"]=this.container;
			instance["point"]=point;
			instance["duration"]=duration;
			instance["systemUrl"]=systemUrl;
			execute(point.type, instance);
		}


		private function execute(type:Number, instance:Dictionary):void {
			var pointCenter:ReceiverCenter=SyncPointCenterFactory.getInstance(type, instance);
			var command:ICommand=new ConcreteCommand(pointCenter);
			var invoker:CommandInvoker=new CommandInvoker();
			invoker.setCommand(command);
			command.execute();
		}
	}
}
