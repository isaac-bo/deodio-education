
package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.PresentationSyncMedia;
	import com.deodio.elearning.domain.customize.PresentationSyncMediaBo;
	import com.deodio.elearning.libs.enum.StreamingEnum;
	import com.deodio.elearning.libs.event.CommonEvent;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.event.MockEvent;
	import com.deodio.elearning.libs.stream.VideoStreamController;
	import com.deodio.elearning.libs.stream.pool.StreamingPool;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.playbar.ac.IPlayBar;
	import com.deodio.elearning.module.player.playbar.view.ClassRoomPlayBar;
	import com.deodio.elearning.module.player.playbar.view.RichPlayBarContainer;
	import com.deodio.elearning.module.player.playbar.view.SyncMediaPlayBar;
	import com.deodio.elearning.module.player.sync.view.MediaPoints;
	import com.deodio.elearning.service.PresentationService;
	import com.deodio.elearning.service.SystemService;

	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.utils.Timer;

	import mx.collections.ArrayCollection;
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.events.DragEvent;
	import mx.managers.DragManager;

	import spark.components.Group;


	[Bindable]
	public class ProgressBarModelLocator extends AbstractMediaModelLocator {

		private var presentationService:PresentationService;

		private var systemService:SystemService;

		private var _model:ModelLocator=ModelLocator.getInstance();
		private var _currentClipIndex:int=0;
		private var _previousDuration:Number=-1;
		private var _isPress:Boolean=false;
		private var _isRelease:Boolean=false;
		private var _isSeek:Boolean=false;
		private var progressBar:IPlayBar;
		private var _monitor:Timer;
		private var _timer:Timer;
		private var editorBar:RichPlayBarContainer;
		public var currentDuration:Number=0;
		public var container:Canvas;
		public var startDuration:Number=0;
		public var endDuration:Number=0;
		public var clipsLength:Number=0;
		public var isPlay:Boolean=false;

		public var mediaPoints:ArrayCollection=new ArrayCollection();

		private static var _progressBarLocator:ProgressBarModelLocator;

		public function ProgressBarModelLocator() {
			presentationService=new PresentationService();
			systemService=new SystemService();
		}

		public static function getInstance():ProgressBarModelLocator {
			if (_progressBarLocator == null) {
				_progressBarLocator=new ProgressBarModelLocator();
			}
			return _progressBarLocator;
		}

		public function initMedia(progressBar:IPlayBar, editorBar:RichPlayBarContainer):void {
			this.progressBar=progressBar;
			this.editorBar=editorBar;
			this.setClipsLength();
			this.initAbstractEventListener();
			this.initMeidaCover();
		}

		private function initProgressBarEventListener():void {
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_PLAY, onProgressMediaPlay);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_PAUSE, onProgressMediaPause);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_STOP, onProgressMediaStop);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_VOLUMN, onProgressMediaSound);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_SCREEN_FULL_SCREEN, onProgressMediaFullScreen);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_PROGRESS_PRESS, onProgressPressFunc);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_PROGRESS_RELEASE, onProgressReleaseFunc);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MockEvent.MOCK_CHANGE_LAYOUT, onMockChangeLayoutFunc);
		}

		private function initEditorBarEventListener():void {
			if (editorBar != null) {
//				this.editorBar.slider.addEventListener(MediaEvent.MEDIA_PROGRESS_PRESS, onProgressPressFunc);
//				this.editorBar.slider.addEventListener(MediaEvent.MEDIA_PROGRESS_RELEASE, onProgressReleaseFunc);
				this.editorBar.addEventListener(MockEvent.MOCK_CHANGE_LAYOUT, onMockChangeLayoutFunc);
				this.editorBar.addEventListener(MediaEvent.MEDIA_PLAY, onProgressMediaPlay);
				this.editorBar.addEventListener(MediaEvent.MEDIA_PAUSE, onProgressMediaPause);
				this.editorBar.addEventListener(MediaEvent.MEDIA_STOP, onProgressMediaStop);
				this.editorBar.addEventListener(MediaEvent.MEDIA_SCREEN_FULL_SCREEN, onProgressMediaFullScreen);
			}
		}

		override public function initAbstractEventListener():void {
			this.videoStreamingList=StreamingPool.createSteamings(StreamingEnum.STREAMING_VIDEO, this.mediaPoints.length);
			super.initAbstractEventListener();

			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_MAXIMUM, this.clipsLength));
			if (editorBar != null) {
				this.editorBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
				this.editorBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_MAXIMUM, this.clipsLength));
			}
			this.initProgressBarEventListener();
			this.initEditorBarEventListener();

		}

		public function closeStreamings():void {
			for each (var media:PresentationSyncMediaBo in mediaPoints) {
				media.videoStream.closeStream();
				media.videoStream.closeConnection();

			}
		}

		public function initDrawMediaPoints(container:Canvas):void {
			if (mediaPoints != null && mediaPoints.length > 0) {
				this.container=container;
				container.removeAllElements();
				var duration:Number=0;
				for each (var media:PresentationSyncMediaBo in mediaPoints) {
					var temp:Number=duration;
					var point:com.deodio.elearning.module.player.sync.view.MediaPoints=new com.deodio.elearning.module.player.sync.view.MediaPoints();
					point.url=media.mediaCover;
					point.x=114 * temp + 8;
					point.width=114;
					point.height=70;
					container.addElement(point);
					duration+=media.mediaEnd - media.mediaStart;
					//Setting the video stream for every media item.
					media.videoStream=this.videoStreamingList.getItemAt(mediaPoints.getItemIndex(media)) as VideoStreamController;
					media.videoStream.playUrl(media.mediaUrl, media.mediaStart, false);

				}
			}
		}


		public function initMeidaCover():void {
			//Create the cover for the first media item
			if (this.mediaPoints.length > 0) {
				var url:String=getMediaInstance(0, this.mediaPoints).mediaCover;
				this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_CREATE_MEDIA_COVER, url));
			}
		}

		public function destoryMediaCover():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_DESTORY_MEDIA_COVER));
		}

		public function stop():void {
			currentStreaming.closeStream();
			currentStreaming.closeConnection();
			this._currentClipIndex=0;
			this.currentDuration=0;
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).removeEventListener(Event.ENTER_FRAME, progressBarHandler);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
			if (editorBar != null) {
				this.editorBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
			}
//			this.sfProgressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
			this.initMeidaCover();
			this.isPlay=this._model.isPlay=false;
		}

		public function temporaryPause():void {
			if (this.isPlay == true) {
				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).removeEventListener(Event.ENTER_FRAME, progressBarHandler);
				currentStreaming.pause();
			}
		}

		public function temporaryResume():void {
			trace(this + "-------------- temporaryResume ---------------");
			if (((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).slider.value == this.clipsLength) {
				this.finish();
			} else {
				if (_timer) {
					_timer.stop();
				}
				_timer=new Timer(500);
				_timer.start();
				_timer.addEventListener(TimerEvent.TIMER, function(event:TimerEvent):void {
					currentStreaming.play();
					((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(Event.ENTER_FRAME, progressBarHandler);
					_timer.stop();
					_timer=null;

				});
			}
		}

		public function pause():void {
			if (this.isPlay == true) {
				currentStreaming.pause();
			}

			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).removeEventListener(Event.ENTER_FRAME, progressBarHandler);

			this.isPlay=this._model.isPlay=false;
		}

		public function play():void {
			if (this.isPlay == false) {
				if (((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).slider.value == this.clipsLength) {
					this.finish();
				} else {
					this.isPlay=true;
					_model.isPlay=true;
					((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(Event.ENTER_FRAME, progressBarHandler);
					//INFO: Play default
					if (((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).slider.value == 0) {

						var media:PresentationSyncMediaBo=getMediaInstance(0, this.mediaPoints);
						this._model.currentSyncMediaIndex=0;
						this._model.currentSyncMedia=media;
						if (media.mediaType == 3) {
							//this._model.videoWidth=470;
							//this._model.videoHeight=315;
						} else {
							this._model.mediaWidth=media.mediaWidth;
							this._model.mediaHeight=media.mediaHeight;
						}
						this._model.mediaType=media.mediaType;
						this.currentStreaming=media.videoStream;
						this.currentStreaming.isPlay=true;
						this.currentStreaming.play()
						this.playMedia();
//						this.currentStreaming.playUrl(media.mediaUrl, media.mediaStart, true);
							//INFO: Pause
					} else {
						changeSeek(true);
					}
						//audioPlay(true);

				}
			}
		}

		public function changeSeek(isPlay:Boolean):void {
			var playDuration:Number=0;
			for each (var media:PresentationSyncMediaBo in this.mediaPoints) {
				playDuration+=(media.mediaEnd - media.mediaStart);

				if (playDuration > this.currentDuration) {
					this._currentClipIndex=this.mediaPoints.getItemIndex(media);
					playDuration=playDuration - (media.mediaEnd - media.mediaStart);
					playDuration=this.currentDuration - playDuration + media.mediaStart;

					if (playDuration + 1 == playDuration + (media.mediaEnd - media.mediaStart))
						playDuration+=1;
					break;
				}
			}
//			videoStream.closeStream();
			var media:PresentationSyncMediaBo=getMediaInstance(this._currentClipIndex, this.mediaPoints);
			this._model.currentSyncMediaIndex=this._currentClipIndex;
			this._model.currentSyncMedia=media;
			if (media.mediaType == 3) {
//				this._model.videoWidth=470;
//				this._model.videoHeight=315;
			} else {
				this._model.mediaWidth=media.mediaWidth;
				this._model.mediaHeight=media.mediaHeight;
			}
			this._model.mediaType=media.mediaType;
			this.currentStreaming=media.videoStream;
			if (this.currentDuration == this.clipsLength) {
				this.finish();
			} else {
				if (this._isSeek == true) {
					this._isSeek=false;
					this.currentStreaming.seek=playDuration;
				}
				this.currentStreaming.play();
				this.playMedia();
				this.currentStreaming.isPlay=true;
//				this.setSoundVolumn();
//				this.initCover();
			}
		}

		override public function onVideoCreateCompleteFunc(event:MediaEvent):void {
			//After create media item complete. System will pause the video streaming.
			var videoStreaming:VideoStreamController=event.data as VideoStreamController;
			if (_model.isPlay == false) {
				videoStreaming.pause();
			}

//			this.playMedia();
		}

		private function playMedia():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_CREATE_MEDIA_ITEM, this.currentStreaming));
			this.destoryMediaCover();
		}

		private function setClipsLength():void {
//			for each (var media:PresentationSyncMedia in this.mediaPoints) {
//				this.clipsLength+=media.mediaEnd - media.mediaStart;
//			}
			this.clipsLength = 20;
		}

		private function getMediaInstance(index:Number, mediaPoints:ArrayCollection):PresentationSyncMediaBo {
			var media:PresentationSyncMediaBo;
			if (mediaPoints.length > 0) {
				media=mediaPoints.getItemAt(index) as PresentationSyncMediaBo;
			}
			return media;
		}

		public function getPlayDuration(index:Number, mediaPoints:ArrayCollection):Number {
			var videolen:int=0;
			for (var i:int=0; i < index; i++) {
				videolen+=getMediaInstance(i, mediaPoints).mediaEnd - getMediaInstance(i, mediaPoints).mediaStart;
			}
			return videolen;
		}

		public function isMedia():Boolean {
			var media:PresentationSyncMediaBo=getMediaInstance(this._currentClipIndex, this.mediaPoints);
			if (media != null && media.mediaType == 3) {
				return false;
			} else {
				return true;
			}
		}

		private function progressBarHandler(event:Event):void {
			trace(this + "------------ progressBarHandler -------------" + this.currentStreaming.stream + "--------");
			var duration:Number=getPlayDuration(this._currentClipIndex, this.mediaPoints);
			//when slider is clicked don't update slider progress
			if (this.currentStreaming.getPlayTime() != 0) {
				var playDuration:Number=this.currentStreaming.getPlayTime() + duration - getMediaInstance(this._currentClipIndex, this.mediaPoints).mediaStart;
				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, playDuration));
				if (editorBar != null) {
					this.editorBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, playDuration));
				}
//				this.sfProgressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, playDuration));
			}
//			trace(this + " play time: " + this.videoStream.getPlayTime() + ", instance: " + getMediaInstance(this._currentClipIndex, this.mediaPoints).mediaEnd);
//			if (this.currentStreaming.getPlayTime() >= getMediaInstance(_currentClipIndex, this.mediaPoints).mediaEnd) {
//				switchClipHandler();
//			}
//
//			if (this.clipsLength <= ((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).slider.value) {
//				this.finish();
//			}

		}

		private function switchClipHandler():void {

			if (this._model.currentSyncMediaIndex == this.mediaPoints.length - 1) {
				this.finish();
			} else {
				var preMedia:PresentationSyncMediaBo=getMediaInstance(this._model.currentSyncMediaIndex, this.mediaPoints);
				preMedia.videoStream.pause();

				var media:PresentationSyncMediaBo=getMediaInstance(this._model.currentSyncMediaIndex + 1, this.mediaPoints);
				this._currentClipIndex=this._model.currentSyncMediaIndex + 1;
				this._model.currentSyncMediaIndex=this._model.currentSyncMediaIndex + 1;
				this._model.currentSyncMedia=media;
				if (media.mediaType == 3) {
					//this._model.videoWidth=470;
					//this._model.videoHeight=315;
				} else {
					this._model.mediaWidth=media.mediaWidth;
					this._model.mediaHeight=media.mediaHeight;
				}
				this._model.mediaType=media.mediaType;
				this.currentStreaming=media.videoStream;
				this.currentStreaming.isPlay=true;
				this.currentStreaming.play()
				this.playMedia();
			}
		}

		private function finish():void {
			this.isPlay=this._model.isPlay=false;
			this.closeStreamings();
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).removeEventListener(Event.ENTER_FRAME, progressBarHandler);
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
			if (editorBar != null) {
				this.editorBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
			}
		}

		private function onProgressPressFunc(event:MediaEvent):void {
			trace(this + "------------ press progress bar --------------");
			this.startDuration=(event.data as Object).value;
			this._isPress=true;
			if (this.isPlay == true) {
				//INFO: pause:
				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).removeEventListener(Event.ENTER_FRAME, progressBarHandler)
			}
			if (!(event.data as Object).isPlayBar) {
//				if (editorBar != null) {
//					this.editorBar.slider.addEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
//				}
				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).removeEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
			}
		}

		private function onProgressReleaseFunc(event:MediaEvent):void {
			trace(this + "------------ release progress bar --------------");
			this.endDuration=(event.data as Object).value;
			this.currentDuration
			this._isRelease=true;
			this._isSeek=true;
			if (this.isPlay == true) {
				//INFO: play continue;
				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(Event.ENTER_FRAME, progressBarHandler);
//				if (this._model.isRecording != true)
				this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_EXEC_COMMAND, this.currentDuration));
				changeSeek(this.isPlay);
//				createSlide();
			}
			this.editorBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, this.currentDuration));
			((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, this.currentDuration));
//			this.sfProgressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, this.currentDuration));
			if (!(event.data as Object).isPlayBar) {
//				this.sfProgressBar.slider.removeEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
//				if (editorBar != null) {
//					this.editorBar.slider.removeEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
//				}
				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).addEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
			}
		}

		private function onMockChangeLayoutFunc(event:MockEvent):void {
			this.dispatchEvent(new MockEvent(MockEvent.MOCK_CHANGE_LAYOUT));
		}

		private function onProgressChangeFunc(event:MediaEvent):void {

			trace(this + "------------- change progress bar -------------- " + event.data + "--------:" + this._previousDuration);
			this.currentDuration=event.data as Number;
			if (editorBar != null) {
				this.editorBar.scrollGroup.horizontalScrollPosition=this.currentDuration * 114 - (this.editorBar.slider.slider.width / (this.editorBar.slider.slider.maximum + 1)) * this.currentDuration;
			}

			if (this._isPress == true && this._isRelease == false) {
				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) : (progressBar as ClassRoomPlayBar)).removeEventListener(Event.ENTER_FRAME, progressBarHandler);
				if (editorBar != null) {
					this.editorBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, this.currentDuration));
				}
//				this.sfProgressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, this.currentDuration));
//				((progressBar is SyncMediaPlayBar) ? (progressBar as SyncMediaPlayBar) :  (progressBar as ClassRoomPlayBar)).dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, this.currentDuration));
			} else {
				this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_EXEC_COMMAND, this.currentDuration));
				this._isPress=false;
				this._isRelease=false;
			}
		}


		public function progressBarDragEnter(event:DragEvent):void {
			trace(this + "---------------- progressBarDragEnter -----------------" + event.target);
			DragManager.acceptDragDrop(RichPlayBarContainer(event.target));
//			if (this.isDragSyncPoint == true) {
//				this.isDragSyncPoint=false
//				this._isDelete=true;
//			} else {
//				this._isDelete=false;
//			}
		}

		public function progressBarDragExit(event:DragEvent):void {
			trace(this + "---------------- progressBarDragExit -----------------");
		}

		public function progressBarDragDrop(event:DragEvent):void {
			trace(this + "---------------- progressBarDragDrop -----------------");
		}

		public function onProgressMediaPlay(event:MediaEvent):void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_PLAY));
		}

		public function onProgressMediaPause(event:MediaEvent):void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_PAUSE));
		}

		public function onProgressMediaStop(event:MediaEvent):void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_STOP));
		}

		public function onProgressMediaSound(event:MediaEvent):void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_VOLUMN));
		}

		public function onProgressMediaFullScreen(event:MediaEvent):void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_SCREEN_FULL_SCREEN));
		}

		override public function onVideoCreateNetStreamStopFunc(event:MediaEvent):void {
			this.switchClipHandler();
		}
	}
}
