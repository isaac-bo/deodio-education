package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.PresentationSyncPoints;
	import com.deodio.elearning.domain.PresentationSyncSlides;
	import com.deodio.elearning.domain.customize.SyncPoints;
	import com.deodio.elearning.libs.constants.Constants;
	import com.deodio.elearning.libs.event.PointsEvent;
	import com.deodio.elearning.libs.utils.CommonUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.sync.view.SyncPoints;
	import com.deodio.elearning.service.PresentationService;

	import flash.utils.Dictionary;

	import mx.collections.ArrayCollection;
	import mx.containers.Canvas;
	import mx.controls.Alert;

	import spark.components.Group;

	[Bindable]
	public class SyncPointsModelLocator extends AbstractMediaModelLocator {
		private var presentationService:PresentationService;
		private var _model:ModelLocator=ModelLocator.getInstance();
		private var _actionLocator:ActionModelLocator=ActionModelLocator.getInstance();
		public var syncPoints:ArrayCollection=new ArrayCollection();
		public var container:Canvas;
		public var parameterMap:Dictionary;
		public var isSlides:Boolean=false;

		private static var _syncPointsLocator:SyncPointsModelLocator;


		public function SyncPointsModelLocator() {
			presentationService=new PresentationService();
		}

		public function isExistSyncPoint(currentDuration:int, pointId:String):Boolean {
			for each (var syncPoint:PresentationSyncPoints in this.syncPoints) {
				if (syncPoint.pointTime == currentDuration && pointId != syncPoint.id) {
					return true;
				}
			}
			return false;
		}

		public static function getInstance():SyncPointsModelLocator {
			if (_syncPointsLocator == null) {
				_syncPointsLocator=new SyncPointsModelLocator();
			}
			return _syncPointsLocator;
		}

		public function initPointDictionary(parameterMap:Dictionary, slides:ArrayCollection):void {

			if ((syncPoints == null || syncPoints.length <= 0) && isSlides) {
				var sync:PresentationSyncPoints=getSyncPoint(slides.getItemAt(0) as PresentationSyncSlides, 0);
				syncPoints=new ArrayCollection();
				syncPoints.addItem(sync);
				this.setParameterMap(parameterMap, sync);
//
//				var sync:LessonSyncPoints=getSyncPoint(slides.getItemAt(1) as LessonSyncSlides, 4);
//				syncPoints.addItem(sync);
//				this.setParameterMap(parameterMap, sync);

			} else if (syncPoints != null && syncPoints.length > 0) {
				for each (var syncPoint:PresentationSyncPoints in syncPoints) {
					this.setParameterMap(parameterMap, syncPoint);
				}
			}
		}

		public function initDrawSyncPoints(parameterMap:Dictionary, container:Canvas):void {
			if (syncPoints != null && syncPoints.length > 0) {
				this.container=container;
				this.container.removeAllElements();
				for each (var sync:PresentationSyncPoints in syncPoints) {
					drawSyncPoint(parameterMap, container, sync);
				}
			}
		}

		public function drawSyncPoint(parameterMap:Dictionary, container:Canvas, sync:PresentationSyncPoints):void {
			var point:com.deodio.elearning.module.player.sync.view.SyncPoints=new com.deodio.elearning.module.player.sync.view.SyncPoints();
			point.url=sync.pointUrl;
			point.point=sync;
			point.isMicphone=true;
			point.x=114 * sync.pointTime + 8;
			point.width=114;
			point.height=70;
			point.addEventListener(PointsEvent.POINT_REMOVE_POINT, function(event:PointsEvent):void {
				var syncPoint:com.deodio.elearning.module.player.sync.view.SyncPoints=event.data as com.deodio.elearning.module.player.sync.view.SyncPoints;
				container.removeElement(syncPoint);
//				syncPoints.removeItem(syncPoint.point);
				removeParameterMap(parameterMap, syncPoint.point.pointTime, Constants.SYNC_POINT_TYPE_SLIDE);
			});

			point.addEventListener(PointsEvent.POINT_MICPHONE_POINT, function(event:PointsEvent):void {
				var object:Object=new Object();
				object.syncPointId=sync.id;
				_actionLocator.command(Constants.COMMAND_RECORD_VOICEOVER, "", object);
			});

			point.addEventListener(PointsEvent.POINT_MOUSE_DOWN, function(event:PointsEvent):void {
				if (!_model.isPlay) {
					var dragInitiator:com.deodio.elearning.module.player.sync.view.SyncPoints=event.currentTarget as com.deodio.elearning.module.player.sync.view.SyncPoints;
					_model.currentSyncPoint=dragInitiator;
					dragInitiator.startDrag();
//					spark.components.Alert.show("===22=====" + _model.currentSyncPoint.point.pointTime);
				}
			});
			point.addEventListener(PointsEvent.POINT_MOUSE_UP, function(event:PointsEvent):void {
				if (!_model.isPlay) {

					var dragInitiator:com.deodio.elearning.module.player.sync.view.SyncPoints=event.currentTarget as com.deodio.elearning.module.player.sync.view.SyncPoints;
					var x:Number=container.contentMouseX - dragInitiator.contentMouseX;
					var y:Number=container.contentMouseY - dragInitiator.contentMouseY;
					var index:Number=0;
					while ((114 * index + 8) < x) {
						index=index + 1;
					}

					if (isExistSyncPoint(index, _model.currentSyncPoint.point.id)) {
						dragInitiator=_model.currentSyncPoint;
						dragInitiator.x=114 * _model.currentSyncPoint.point.pointTime + 8;
						dragInitiator.y=0;
						dragInitiator.stopDrag();
						_model.selectedSyncPoint=null;
						return;
					}
					dragInitiator.stopDrag();
					dragInitiator.x=114 * index + 8;
					dragInitiator.y=0;
					removeParameterMap(parameterMap, dragInitiator.point.pointTime, Constants.SYNC_POINT_TYPE_SLIDE);
					dragInitiator.point.pointTime=index;
					setParameterMap(parameterMap, dragInitiator.point);
					_model.selectedSyncPoint=null;
				}
			});
//			point.addEventListener(PointsEvent.POINT_MOUSE_MOVE, function(event:PointsEvent) {
//				if (!_model.isPlay) {
//					var dragInitiator:com.deodio.elearning.module.player.sync.view.SyncPoints=event.currentTarget as com.deodio.elearning.module.player.sync.view.SyncPoints;
//					var x:Number=container.contentMouseX - dragInitiator.contentMouseX;
//					var y:Number=container.contentMouseY - dragInitiator.contentMouseY;
//					var index:Number=0;
//					dragInitiator.x=x;
//					media.syncEditor.scrollGroup.horizontalScrollPosition=container.contentMouseX <= 4 * 114 ? 0 : container.contentMouseX - 4 * 114;
//				}
//			});
			container.addElement(point);
		}


		public function setParameterMap(parameterMap:Dictionary, sync:PresentationSyncPoints):void {
			var point:com.deodio.elearning.domain.customize.SyncPoints=new com.deodio.elearning.domain.customize.SyncPoints();
			point.itemId=sync.id;
			point.thumbnail=sync.pointUrl;
			point.syncPoint=sync;
			point.type=Constants.SYNC_POINT_TYPE_SLIDE;
			this.buildParameterMap(parameterMap, sync.pointTime, point);
		}

		public function getSyncPoint(slide:PresentationSyncSlides, start:Number):PresentationSyncPoints {
			var sync:PresentationSyncPoints=new PresentationSyncPoints();
			sync.id=CommonUtils.createUID();
			sync.presentationId=slide.presentationId;
			sync.syncSlideId=slide.id;
			sync.pointUrl=slide.slideUrl;
			sync.pointTime=start;
			sync.pointSize=slide.slideSize;
			sync.pointExt=slide.slideExt;
			sync.pointDir=slide.slideDir;
			sync.createId=slide.createId;
			sync.createTime=new Date();
			sync.updateId=slide.createId;
			sync.updateTime=new Date();

			return sync;
		}

	}
}
