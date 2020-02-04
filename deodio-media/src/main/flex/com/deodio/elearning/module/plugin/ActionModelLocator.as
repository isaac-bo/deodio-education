package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.libs.constants.Constants;
	import com.deodio.elearning.libs.event.PointsEvent;
	import com.deodio.elearning.model.ModelLocator;

	import flash.external.ExternalInterface;

	import mx.controls.Alert;

	public class ActionModelLocator extends AbstractMediaModelLocator {

		private static var _toolsLocator:ActionModelLocator;

		[Bindable]
		private var _model:ModelLocator=ModelLocator.getInstance();

		[Bindable]
		private var _prorgess:ProgressBarModelLocator=ProgressBarModelLocator.getInstance();

		public function ActionModelLocator() {
			ExternalInterface.addCallback("command", command);
			ExternalInterface.addCallback("resume", resume);
		}

		public static function getInstance():ActionModelLocator {
			if (_toolsLocator == null) {
				_toolsLocator=new ActionModelLocator();
			}
			return _toolsLocator;
		}


		public function command(item:String, message:String, param:Object):Boolean {
			this.pause();
			return switchTools(item, message, param);
		}

		public function resume():Boolean {
			if (this._model.isPlay == true) {
				_prorgess.temporaryResume();
			}
			return true;
		}

		public function pause():Boolean {
			if (this._model.isPlay == true) {
				_prorgess.temporaryPause();
			}
			return true;
		}

		private function switchTools(item:String, message:String, param:Object):Boolean {

			switch (item) {
				//Popup window to record voicover...
				case Constants.COMMAND_RECORD_VOICEOVER:
					return popupVoiceOver(param);
					break;
				case Constants.COMMAND_SAVE_SYNC_CONTENT:
					return saveSyncContent();
					break;
				case Constants.COMMAND_SAVE_SYNC_SUCCESS:
					return saveSyncSuccess();
					break;
				case Constants.COMMAND_PREVIEW_SINGLE_QUIZ:
					return popupPreviewSingleQuiz(param);
					break;
				case Constants.COMMAND_PREVIEW_MULTIPLE_QUIZ:
					return popupPreviewMultipleQuiz(param);
					break;
				case Constants.COMMAND_LIST_MEDIA:
					return listMediaFiles(param);
					break;
				default:
					break;
			}
			return true;
		}


		private function popupVoiceOver(param:Object):Boolean {
			ExternalInterface.call("popupVoiceOver", param.syncPointId);
			return true;
		}

		private function popupPreviewSingleQuiz(param:Object):Boolean {
			ExternalInterface.call("popupPreviewSingleQuiz", param.quizId);
			return true;
		}

		private function popupPreviewMultipleQuiz(param:Object):Boolean {

			ExternalInterface.call("popupPreviewMultipleQuiz", param.quizId);
			return true;
		}

		private function saveSyncSuccess():Boolean {
			ExternalInterface.call("saveSyncSuccess");
			return true;
		}

		private function listMediaFiles(param:Object):Boolean {
			ExternalInterface.call("listMediaFiles", param.mediaId, param.mediaName, param.mediaType, param.mediaSize);
			return true;
		}

		private function saveSyncContent():Boolean {
			this.dispatchEvent(new PointsEvent(PointsEvent.POINT_SAVE_POINT, null));
			return true;
		}

	}
}
