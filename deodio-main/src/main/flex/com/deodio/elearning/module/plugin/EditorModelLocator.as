package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.Media;
	import com.deodio.elearning.libs.event.CommonEvent;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.event.MockEvent;
	import com.deodio.elearning.libs.event.RecordEvent;
	import com.deodio.elearning.libs.utils.CommonUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.playbar.view.IPlayBar;
	import com.deodio.elearning.module.player.playbar.view.RichPlayBarContainer;
	import com.deodio.elearning.module.player.playbar.view.SyncMediaPlayBar;

	import flash.events.Event;
	import flash.utils.Timer;

	import mx.containers.Canvas;
	import mx.controls.Alert;

	public class EditorModelLocator extends AbstractEditorModelLocator {

		private static var _editorLocator:EditorModelLocator;
		private var _model:ModelLocator=ModelLocator.getInstance();

		private var progressBar:IPlayBar;

		private var _isPress:Boolean=false;
		private var _isRelease:Boolean=false;
		private var _isSeek:Boolean=false;
		private var _monitor:Timer;
		private var _timer:Timer;
		public var currentDuration:Number=0;
		public var container:Canvas;
		public var startDuration:Number=0;
		public var endDuration:Number=0;
		public var clipsLength:Number=0;
		public var isPlay:Boolean=false;

		public static function getInstance():EditorModelLocator {
			if (_editorLocator == null) {
				_editorLocator=new EditorModelLocator();
			}
			return _editorLocator;
		}

		public function init(progressBar:IPlayBar):void {
			this.progressBar=progressBar;
		}

		public function onStopMedia():void {

		}

		public function onPauseMedia():void {
			if (this._model.isPlay == true) {
				this.videoStream.pause();
			}
			this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_PAUSE_MEDIA));
			this._model.isPlay=false;
		}

		public function onPauseTrimMedia():void {
			if (this._model.isPlay == true) {
				this.videoStream.pause();
			}
			this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBar4TrimHandler);
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_PAUSE_MEDIA));
			this._model.isPlay=false;
		}

		public function onPlayTrimMedia(slides:Array):void {
			this._model.isPlay=true;
			this.progressBar.addEventListener(Event.ENTER_FRAME, progressBar4TrimHandler);
			var media:Media=this._model.editorMedia;
			if (media.mediaType == 3) {
				//this._model.videoWidth=470;
				//this._model.videoHeight=315;
			} else {
				this._model.mediaWidth=media.mediaWidth;
				this._model.mediaHeight=media.mediaHeight;
			}
			this._model.mediaType=media.mediaType;
			this.videoStream.playUrl(media.mediaUrl, slides[0], true);
			//INFO: Pause
		}

		public function onPlayMedia():void {
			this._model.isPlay=true;
			this.progressBar.addEventListener(Event.ENTER_FRAME, progressBarHandler);
			//INFO: Play default
			if (this.progressBar.slider.value == this.progressBar.slider.maximum) {
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));
			}

			if (this.progressBar.slider.value == 0) {
				var media:Media=this._model.editorMedia;
				if (media.mediaType == 3) {
					//							this._model.videoWidth=470;
					//							this._model.videoHeight=315;
				} else {
					this._model.mediaWidth=media.mediaWidth;
					this._model.mediaHeight=media.mediaHeight;
				}
				this._model.mediaType=media.mediaType;
				this.videoStream.playUrl(media.mediaUrl, 0, true);
					//INFO: Pause
			} else {
				if (this._isSeek == true) {
					this._isSeek=false;
					this.videoStream.playUrl(media.mediaUrl, this.progressBar.slider.value, isPlay);
				} else {
					this.videoStream.play();
				}
			}
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_PLAY_MEDIA));
		}

		private function progressBar4TrimHandler(event:Event):void {
			trace(this + "------------ progressBar4TrimHandler-------------" + this.videoStream.getPlayTime());
			//when slider is clicked don't update slider progress
			if (this.videoStream.getPlayTime() != 0) {
				var playDuration:Number=this.videoStream.getPlayTime();
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, playDuration));
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.TRIM_PROGRESSBAR_VALUE, playDuration));
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.TRIM_END_POSITION, playDuration));
			}


		}

		private function progressBarHandler(event:Event):void {
			trace(this + "------------ progressBarHandler-------------" + this.videoStream.getPlayTime());
			//when slider is clicked don't update slider progress
			if (this.videoStream.getPlayTime() != 0) {
				var playDuration:Number=this.videoStream.getPlayTime();
				// ----==============================
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, playDuration - 0));
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.TRIM_END_POSITION, playDuration));
				if (playDuration > this.progressBar.slider.maximum && this._model.isRecord == true) {
					this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_MAXIMUM, playDuration));
				}
			}
		}

		private function onProgressPressFunc(event:MediaEvent):void {
			trace(this + "------------ press progress bar --------------");
			this.startDuration=(event.data as Object).value;
			this._isPress=true;
			if (this._model.isPlay == true) {
				//INFO: pause:
				this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler)
			}
			if (!(event.data as Object).isPlayBar) {
				this.progressBar.removeEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
			}
		}

		private function onProgressReleaseFunc(event:MediaEvent):void {
			trace(this + "------------ release progress bar --------------");
			this.endDuration=(event.data as Object).value;
			this.currentDuration=this.endDuration;
			this._isRelease=true;
			this._isSeek=true;
			if (this.isPlay == true) {
				//INFO: play continue;
				this.progressBar.addEventListener(Event.ENTER_FRAME, progressBarHandler);
				changeSeek(this.isPlay);
			}
			this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, this.currentDuration));
			if (!(event.data as Object).isPlayBar) {
				this.progressBar.addEventListener(MediaEvent.MEDIA_PROGRESS_CHANGE, onProgressChangeFunc);
			}
		}

		private function onProgressChangeFunc(event:MediaEvent):void {

			this.currentDuration=event.data as Number;
			if (this._isPress == true && this._isRelease == false) {
				this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
			} else {
				this._isPress=false;
				this._isRelease=false;
			}
		}

		override public function onVideoCreateCompleteFunc(event:MediaEvent):void {
			if (_model.isPlay == false) {
				videoStream.pause();
			}
			playMedia();
		}

		private function playMedia():void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_CREATE_MEDIA_ITEM, videoStream));
			//			this.destoryMediaCover();
		}

		public function changeSeek(isPlay:Boolean):void {
			var media:Media=this._model.editorMedia;
			if (media.mediaType == 3) {
				//				this._model.videoWidth=470;
				//				this._model.videoHeight=315;
			} else {
				this._model.mediaWidth=media.mediaWidth;
				this._model.mediaHeight=media.mediaHeight;
			}
			this._model.mediaType=media.mediaType;
			if (this.progressBar.slider.value == this.progressBar.slider.maximum) {
				this.progressBar.dispatchEvent(new CommonEvent(CommonEvent.SYNC_PROGRESSBAR_VALUE, 0));

			} else {
//				if (this._isSeek == true) {
//					this._isSeek=false;
//					this.videoStream.playUrl(media.mediaUrl, this.progressBar.slider.value, isPlay);
//				} else {
				this.videoStream.play();
//				}
			}
		}

		override public function onVideoStreamStopFunc(event:MediaEvent):void {
			if (this._model.isPlay == true) {
				this.videoStream.pause();
			}
			this.progressBar.removeEventListener(Event.ENTER_FRAME, progressBarHandler);
			this.dispatchEvent(new RecordEvent(RecordEvent.RECORD_PAUSE_MEDIA));
			this._model.isPlay=false;
		}
	}
}
