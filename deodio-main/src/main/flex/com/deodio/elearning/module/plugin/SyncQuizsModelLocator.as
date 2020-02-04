package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.PresentationSyncQuizs;
	import com.deodio.elearning.domain.PresentationSyncSlides;
	import com.deodio.elearning.domain.customize.PresentationSyncQuizsBo;
	import com.deodio.elearning.domain.customize.QuizExaminationBo;
	import com.deodio.elearning.domain.customize.QuizPoints;
	import com.deodio.elearning.libs.constants.Constants;
	import com.deodio.elearning.libs.event.PointsEvent;
	import com.deodio.elearning.libs.utils.CommonUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.sync.view.QuizPoints;
	import com.deodio.elearning.service.PresentationService;

	import flash.events.MouseEvent;
	import flash.utils.Dictionary;

	import mx.collections.ArrayCollection;
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.events.DragEvent;

	import spark.components.Group;
	import spark.components.List;

	[Bindable]
	public class SyncQuizsModelLocator extends AbstractMediaModelLocator {
		private var presentationService:PresentationService;
		private var _model:ModelLocator=ModelLocator.getInstance();
		private var _actionLocator:ActionModelLocator=ActionModelLocator.getInstance();
		public var syncQuizs:ArrayCollection=new ArrayCollection();
		public var quizs:ArrayCollection=new ArrayCollection();
		public var quizs_2:ArrayCollection=new ArrayCollection();
		public var container:Canvas;
		public var parameterMap:Dictionary;
		public var _isClick:Boolean=false;
		private var _selectedIndex:int=-1;
		private var _selectedItem:Object=null;

		private static var _syncQuizsLocator:SyncQuizsModelLocator;


		public function SyncQuizsModelLocator() {
			presentationService=new PresentationService();
		}

		public static function getInstance():SyncQuizsModelLocator {
			if (_syncQuizsLocator == null) {
				_syncQuizsLocator=new SyncQuizsModelLocator();
			}
			return _syncQuizsLocator;
		}

		public function initPointDictionary(parameterMap:Dictionary):void {

			if (syncQuizs != null && syncQuizs.length > 0) {
				for each (var sync:PresentationSyncQuizsBo in syncQuizs) {
					this.setParameterMap(parameterMap, sync);
				}
			}
		}

		public function initDrawSyncPoints(parameterMap:Dictionary, container:Canvas):void {
			if (syncQuizs != null && syncQuizs.length > 0) {
				this.container=container;
				this.container.removeAllElements();
				for each (var sync:PresentationSyncQuizsBo in syncQuizs) {
					drawSyncPoint(parameterMap, container, sync);
				}
			}
		}


		public function drawSyncPoint(parameterMap:Dictionary, container:Canvas, sync:PresentationSyncQuizsBo):void {
			var point:com.deodio.elearning.module.player.sync.view.QuizPoints=new com.deodio.elearning.module.player.sync.view.QuizPoints();
			point.point=sync;
			point.x=114 * sync.quizTime + 8;
			point.width=114;
			point.height=70;
			point.quizName.text=sync.quizName;
			point.quizDesc.text=sync.quizDesc;

			point.addEventListener(PointsEvent.POINT_REMOVE_POINT, function(event:PointsEvent):void {
				var quizPoint:com.deodio.elearning.module.player.sync.view.QuizPoints=event.data as com.deodio.elearning.module.player.sync.view.QuizPoints;
				container.removeElement(quizPoint);
				syncQuizs.removeItem(quizPoint.point);
				removeParameterMap(parameterMap, quizPoint.point.quizTime, Constants.SYNC_POINT_TYPE_QUIZE);
				initQuizPointsUI();
			});

			point.addEventListener(PointsEvent.POINT_MOUSE_DOWN, function(event:PointsEvent):void {
				if (!_model.isPlay) {
					var dragInitiator:com.deodio.elearning.module.player.sync.view.QuizPoints=event.currentTarget as com.deodio.elearning.module.player.sync.view.QuizPoints;
					dragInitiator.startDrag();
//					initQuizPointsUI();
				}
			});
			point.addEventListener(PointsEvent.POINT_MOUSE_UP, function(event:PointsEvent):void {
				if (!_model.isPlay) {
					var dragInitiator:com.deodio.elearning.module.player.sync.view.QuizPoints=event.currentTarget as com.deodio.elearning.module.player.sync.view.QuizPoints;
					var x:Number=container.contentMouseX - dragInitiator.contentMouseX;
					var y:Number=container.contentMouseY - dragInitiator.contentMouseY;
					var index:Number=0;

					while ((114 * index + 8) < x) {
						index=index + 1;
					}
					dragInitiator.stopDrag();
					dragInitiator.x=114 * index + 8;
					dragInitiator.y=0;
					removeParameterMap(parameterMap, dragInitiator.point.quizTime, Constants.SYNC_POINT_TYPE_QUIZE);
					dragInitiator.point.quizTime=index;
					setParameterMap(parameterMap, dragInitiator.point);
					initQuizPointsUI();
				}
			});
			point.addEventListener(PointsEvent.POINT_PRVIEW_POINT, function(event:PointsEvent):void {
				if (!_model.isPlay) {
					var quizPoint:com.deodio.elearning.module.player.sync.view.QuizPoints=event.data as com.deodio.elearning.module.player.sync.view.QuizPoints;
					Alert.show("=================" + quizPoint.point.id);
					_actionLocator.command(Constants.COMMAND_PREVIEW_SINGLE_QUIZ, quizPoint.point.id, null);
				}
			});

			point.addEventListener(PointsEvent.POINT_PREVIOUS_POINT, function(event:PointsEvent):void {
				if (!_model.isPlay) {
					var quizPoint:com.deodio.elearning.module.player.sync.view.QuizPoints=event.data as com.deodio.elearning.module.player.sync.view.QuizPoints;
					if (quizPoint.point.currentPage > 1) {
						getCurrentQuizPointUI(quizPoint.point.currentPage - 1, quizPoint.point.quizTime);
					}
					quizPoint.addEventListener(MouseEvent.MOUSE_UP, quizPoint.onMouseUp);
					quizPoint.addEventListener(MouseEvent.MOUSE_DOWN, quizPoint.onMouseDown);

				}
			});

			point.addEventListener(PointsEvent.POINT_NEXT_POINT, function(event:PointsEvent):void {
				if (!_model.isPlay) {
					var quizPoint:com.deodio.elearning.module.player.sync.view.QuizPoints=event.data as com.deodio.elearning.module.player.sync.view.QuizPoints;
					if (quizPoint.point.currentPage < quizPoint.point.totalPage) {
						getCurrentQuizPointUI(quizPoint.point.currentPage + 1, quizPoint.point.quizTime);
					}

				}
			});
			container.addElement(point);
			initQuizPointsUI();
		}


		public function setParameterMap(parameterMap:Dictionary, sync:PresentationSyncQuizsBo):void {
			var point:com.deodio.elearning.domain.customize.QuizPoints=new com.deodio.elearning.domain.customize.QuizPoints();
			point.itemId=sync.id;
			point.quizName=sync.quizName;
			point.quizDesc=sync.quizDesc;
			point.syncQuiz=sync;
			point.type=Constants.SYNC_POINT_TYPE_QUIZE;
			this.buildParameterMap(parameterMap, sync.quizTime, point);
		}

		public function getSyncPoint(quiz:QuizExaminationBo, lessonId:String, start:Number):PresentationSyncQuizsBo {
			var sync:PresentationSyncQuizs=new PresentationSyncQuizs();
			sync.id=CommonUtils.createUID();
			sync.presentationId=lessonId;
			sync.quizId=quiz.id;
			sync.quizTime=start;
			sync.quizName=quiz.quizName;
			sync.quizDesc=quiz.quizDesc;
			sync.createId=quiz.createId;
			sync.createTime=new Date();
			sync.updateId=quiz.updateId;
			sync.updateTime=new Date();
			return new PresentationSyncQuizsBo(sync);
		}



		/////////

		public function quizThumbsDragEnter(event:DragEvent, list:List):void {
			trace("------------slideThumbsDragEnter--------------");
			_selectedIndex=list.selectedIndex;
			_selectedItem=list.selectedItem;


		}

		public function quizThumbsDragExit(event:DragEvent, list:List):void {
			//			Alert.show("------------slideThumbsDragExit--------------");
			_selectedIndex=-1;
			_selectedItem=null;
		}

		public function quizThumbsDragComplete(event:DragEvent, list:List):void {
			trace("------------slideThumbsDragComplete--------------");
			_model.selectedQuiz=null;
			_model.isQuizPicker=false;
			this.quizs=quizs_2;
			list.dataProvider=this.quizs_2;
		}

		public function quizThumbsDragDrop(event:DragEvent, list:List):void {
			trace("------------slideThumbsDragDrop--------------");
			_selectedIndex=list.selectedIndex;
			_selectedItem=list.selectedItem;
		}

		public function quizThumbsDragStart(event:DragEvent, list:List):void {
			//			_synchronize.playBar.isPoint=false;
			//			this.slideThumbs.dropEnabled=true;
			//			_isDelete=false;
			//			_synchronize.playBar.initCurrentPoint();
			//		
			_model.isQuizPicker=true;
			_model.selectedQuiz=list.selectedItem as QuizExaminationBo;
			quizs_2=new ArrayCollection();
			for each (var obj:Object in this.quizs) {
				quizs_2.addItem(obj);
			}
		}

		public function initQuizPointsUI():void {
			var dictionary:Dictionary=new Dictionary();
			for each (var quizPoint:PresentationSyncQuizsBo in this.syncQuizs) {
				if (!dictionary[quizPoint.quizTime] || dictionary[quizPoint.quizTime] == undefined) {
					var quizs:ArrayCollection=new ArrayCollection();
					var object:Object=new Object();
					object.id=quizPoint.id;
					object.point=quizPoint;
					quizs.addItem(object);
					dictionary[quizPoint.quizTime]=quizs;
				} else {
					var object:Object=new Object();
					object.id=quizPoint.id;
					object.point=quizPoint;
					(dictionary[quizPoint.quizTime] as ArrayCollection).addItem(object);
				}
			}

			for (var index:Number=0; index < container.numElements; index++) {
				if (container.getElementAt(index) as com.deodio.elearning.module.player.sync.view.QuizPoints) {
					var quizPoint2:com.deodio.elearning.module.player.sync.view.QuizPoints=container.getElementAt(index) as com.deodio.elearning.module.player.sync.view.QuizPoints;
					var quizs:ArrayCollection=dictionary[quizPoint2.point.quizTime] as ArrayCollection;
					for each (var object:Object in quizs) {
						if (object.id == quizPoint2.point.id) {
							quizPoint2.point.currentPage=quizs.getItemIndex(object) + 1;
							quizPoint2.point.totalPage=quizs.length;
							quizPoint2.setQuizPointsTips(quizs.getItemIndex(object) + 1, quizs.length);
						}
					}
				}
			}

		}

		public function getCurrentQuizPointUI(currentPage:Number, duration:Number):void {
			var quizPoint2:com.deodio.elearning.module.player.sync.view.QuizPoints=null;
			for (var index:Number=0; index < container.numElements; index++) {
				if (container.getElementAt(index) as com.deodio.elearning.module.player.sync.view.QuizPoints) {
					var quizPoint:com.deodio.elearning.module.player.sync.view.QuizPoints=container.getElementAt(index) as com.deodio.elearning.module.player.sync.view.QuizPoints;
					if (quizPoint.point.quizTime == duration && quizPoint.point.currentPage == currentPage) {
						quizPoint2=quizPoint;
					}
				}
			}
			container.removeElement(quizPoint2);
			container.addElement(quizPoint2);
		}

		public function isExistQuizPoint(currentDuration:int, pointId:String):Boolean {
			for each (var quizPoint:PresentationSyncQuizsBo in this.syncQuizs) {
				if (quizPoint.quizTime == currentDuration && pointId == quizPoint.quizId) {
					return true;
				}
			}
			return false;
		}

	}
}


