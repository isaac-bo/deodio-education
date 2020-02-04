package com.deodio.elearning.controls.spoint {
	import com.deodio.elearning.controls.IExecutor;
	import com.deodio.elearning.domain.customize.SyncPoints;
	import com.deodio.elearning.libs.utils.MediaUtils;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.player.ClassRoom;
	import com.deodio.elearning.module.player.SyncMedia;
	import com.deodio.elearning.module.player.screen.view.Screen;

	import flash.utils.Dictionary;

	public class SyncPointExecutor implements IExecutor {

		[Bindable]
		public var _model:ModelLocator=ModelLocator.getInstance();

//		public function SyncPoint() {
//		}

		public function action(meta:Dictionary):void {
			action4Synchronize(meta);
//			action4Preview(meta);
//			action4VoiceOver(meta);
		}

		private function action4Synchronize(meta:Dictionary):void {
			var container:Object=null;
			if (meta["container"] is SyncMedia) {
				container=meta["container"] as SyncMedia;
				changeSlides(container, meta);
			} else if (meta["container"] is ClassRoom) {
				container=meta["container"] as ClassRoom;
				changeSlides(container, meta);
			}
		}


		private function changeSlides(container:Object, meta:Dictionary):void {
			var syncPoint:SyncPoints=meta["point"] as SyncPoints;
			var systemUrl:String=meta["systemUrl"] as String;
			var screen:Screen=container.getSlidesContainer() as Screen;
			if (this._model.slidePath != syncPoint.thumbnail) {
				this._model.slidePath=syncPoint.thumbnail;
//				this._model.isImage=true;
				MediaUtils.changeImage(systemUrl + syncPoint.thumbnail, screen.slideImage, screen.width - 4, screen.height - 4, null);
			}
//				this.common.setCurrentScreenType();
//				_classRoom.screenBar.setWindowChange(this._model.currentType);
		}
	}
}
