package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.libs.enum.StateEnum;
	import com.deodio.elearning.libs.event.MediaEvent;
	import com.deodio.elearning.libs.utils.MediaUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.SyncMedia;
	import com.deodio.elearning.module.player.screen.view.ClassRoomScreen;
	import com.deodio.elearning.module.player.screen.view.SyncMediaScreen;
	import com.deodio.elearning.service.PresentationService;

	import flash.display.Bitmap;
	import flash.media.Video;
	import flash.system.Capabilities;

	import mx.controls.Alert;

	import spark.components.Group;

	[Bindable]
	public class ScreenModelLocator extends AbstractMediaModelLocator {
		private var presentationService:PresentationService;

		private static var _screenLocator:ScreenModelLocator;
		private var _model:ModelLocator=ModelLocator.getInstance();

		public var mediaScreen:Group;

		public function ScreenModelLocator() {
			presentationService=new PresentationService();
		}

		public static function getInstance():ScreenModelLocator {
			if (_screenLocator == null) {
				_screenLocator=new ScreenModelLocator();
			}
			return _screenLocator;
		}

		public function initScreen():void {
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).addEventListener(MediaEvent.MEDIA_PLAY, onPlayMediaFunc);
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.addEventListener(MediaEvent.MEDIA_SCREEN_SWITCH_SCREEN, onScreenSwitchScreenFunc);
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.addEventListener(MediaEvent.MEDIA_SCREEN_SWITCH_SCREEN, onScreenSwitchScreenFunc);
		}

		private function onPlayMediaFunc(event:MediaEvent):void {
			this.dispatchEvent(new MediaEvent(MediaEvent.MEDIA_PLAY));
		}

		private function onScreenSwitchScreenFunc(event:MediaEvent):void {
			var isSlide:Boolean=event.data as Boolean;
			switchScreen(isSlide);
		}

		public function resizeScreen(clipsCanvasWidth:Number, clipsCanvasHeight:Number, slidesCanvasWidth:Number, slidesCanvasHeight:Number):void {

			var clipImage:Bitmap=((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.clipsImage.getChildAt(0) as Bitmap;
			var oClipImage:Object=MediaUtils.middlePosition(clipImage.width, clipImage.height, clipsCanvasWidth - 4, clipsCanvasHeight - 4);
			clipImage.x=oClipImage.x;
			clipImage.y=oClipImage.y;
			clipImage.width=oClipImage.width;
			clipImage.height=oClipImage.height;
			clipImage.smoothing=true;

			var clipMedia:Video=_model.media;
			if (clipMedia != null) {
				var oClipMedia:Object=MediaUtils.middlePosition(clipMedia.width, clipMedia.height, clipsCanvasWidth - 4, clipsCanvasHeight - 4);
				clipMedia.x=oClipMedia.x;
				clipMedia.y=oClipMedia.y;
				clipMedia.width=oClipMedia.width;
				clipMedia.height=oClipMedia.height;
				clipMedia.smoothing=true;
			}

			var slideImage:Bitmap=((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.slideImage.getChildAt(0) as Bitmap;
			var oSlideImage:Object=MediaUtils.middlePosition(slideImage.width, slideImage.height, slidesCanvasWidth - 4, slidesCanvasHeight - 4);
			slideImage.x=oSlideImage.x;
			slideImage.y=oSlideImage.y;
			slideImage.width=oSlideImage.width;
			slideImage.height=oSlideImage.height;
			slideImage.smoothing=true;
		}

//		public function resizeScreen():void {
//
//
//			var clipImage:Bitmap=((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.clipsImage.getChildAt(0) as Bitmap;
//
//			Alert.show((mediaScreen as SyncMediaScreen).clipsCanvas.screenComponent.width + "====aa=====" + clipImage.width + "==========" + clipImage.height);
//
//			var oClipImage:Object=MediaUtils.middlePosition(clipImage.width, clipImage.height, ((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.screenComponent.width - 4, ((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.screenComponent.height - 4);
//			clipImage.x=oClipImage.x;
//			clipImage.y=oClipImage.y;
//			clipImage.width=oClipImage.width;
//			clipImage.height=oClipImage.height;
//			clipImage.smoothing=true;
//
//			Alert.show("=========" + clipImage.width + "==========" + clipImage.height);
//
//			var clipMedia:Video=_model.media;
//			if (clipMedia != null) {
//				var oClipMedia:Object=MediaUtils.middlePosition(clipMedia.width, clipMedia.height, ((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.screenComponent.width - 4, ((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.screenComponent.height - 4);
//				clipMedia.x=oClipMedia.x;
//				clipMedia.y=oClipMedia.y;
//				clipMedia.width=oClipMedia.width;
//				clipMedia.height=oClipMedia.height;
//				clipMedia.smoothing=true;
//			}
//
//
//			var slideImage:Bitmap=((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.slideImage.getChildAt(0) as Bitmap;
//			var oSlideImage:Object=MediaUtils.middlePosition(slideImage.width, slideImage.height, ((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.screenComponent.width - 4, ((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.screenComponent.height - 4);
//			slideImage.x=oSlideImage.x;
//			slideImage.y=oSlideImage.y;
//			slideImage.width=oSlideImage.width;
//			slideImage.height=oSlideImage.height;
//			slideImage.smoothing=true;
//
//			Alert.show("=========" + slideImage.width + "==========" + slideImage.height);
//		}

		public function resumeScreen():void {

			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.x=402;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.y=0;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.width=400;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.height=225;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas._borderVisible=true;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.x=0;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.y=0;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.width=400;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.height=225;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas._borderVisible=true;
			resizeScreen(400, 255, 400, 255);

			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).addElementAt(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas, 0);
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).addElementAt(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas, 1);
		}

		private function resizeScreen4Clips(mediaScreen:Group):void {
			_model.screenType="clips";
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.x=0;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.y=0;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas._borderVisible=false;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.width=flash.system.Capabilities.screenResolutionX;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.height=flash.system.Capabilities.screenResolutionY;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.width=300;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.height=169;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.x=40; //flash.system.Capabilities.screenResolutionX - 40 - mediaScreen.slidesCanvas.width;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.y=40; //flash.system.Capabilities.screenResolutionY - 40 - mediaScreen.slidesCanvas.height;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas._borderVisible=true;

			resizeScreen(flash.system.Capabilities.screenResolutionX, flash.system.Capabilities.screenResolutionY, 300, 169);

			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).addElementAt(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas, 0);
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).addElementAt(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas, 1);
		}

		private function resizeScreen4Slides(mediaScreen:Group):void {
			_model.screenType="slides";

			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.x=0;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.y=0;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.width=flash.system.Capabilities.screenResolutionX;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas.height=flash.system.Capabilities.screenResolutionY;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas._borderVisible=false;

			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.width=300;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.height=169;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.x=40; //flash.system.Capabilities.screenResolutionX - 40 - mediaScreen.clipsCanvas.width;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas.y=40; //flash.system.Capabilities.screenResolutionY - 40 - mediaScreen.clipsCanvas.height;
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas._borderVisible=true;
			resizeScreen(300, 169, flash.system.Capabilities.screenResolutionX, flash.system.Capabilities.screenResolutionY);
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).addElementAt(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).slidesCanvas, 0);
			((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).addElementAt(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).clipsCanvas, 1);

		}

//
//		private function execResizeScreen(func:Function, isSlide:Boolean):void {
//			func(mediaScreen, isSlide);
//		}

		public function switchScreen(isMedia:Boolean):void {
			isMedia ? resizeScreen4Clips(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen))) : resizeScreen4Slides(((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)));
		}

		public function resizePlayIcon():void {
			if (this._model.isFullScreen) {
				(mediaScreen as SyncMediaScreen).playIcon.x=(flash.system.Capabilities.screenResolutionX - this.(mediaScreen is SyncMediaScreen).playIcon.width) / 2;
				(mediaScreen as SyncMediaScreen).playIcon.y=(flash.system.Capabilities.screenResolutionY - this.(mediaScreen is SyncMediaScreen).playIcon.height) / 2;
			} else {

				if (mediaScreen is SyncMediaScreen) {
					if (((mediaScreen as SyncMediaScreen).parentDocument as SyncMedia).currentState == StateEnum.HIDE_STATE) {
						(mediaScreen as SyncMediaScreen).playIcon.x=462.5;
						(mediaScreen as SyncMediaScreen).playIcon.y=75.5;
					} else {
						(mediaScreen as SyncMediaScreen).playIcon.x=362.5;
						(mediaScreen as SyncMediaScreen).playIcon.y=75.5;
					}
				}
			}
		}
		
//		public function resizeClassRoomPlayIcon():void {
//			if (this._model.isFullScreen) {
////				this.((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).playIcon.x=(flash.system.Capabilities.screenResolutionX - this.((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).playIcon.width) / 2;
////				this.((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).playIcon.y=(flash.system.Capabilities.screenResolutionY - this.((mediaScreen is SyncMediaScreen) ? (mediaScreen as SyncMediaScreen) : (mediaScreen as ClassRoomScreen)).playIcon.height) / 2;
//			} else {
//				
//				(mediaScreen as ClassRoomScreen).playIcon.x=10;
//				(mediaScreen as ClassRoomScreen).playIcon.y=103;
//			}
//		}
	}
}
