package com.deodio.elearning.controls.receiver {
	import flash.utils.Dictionary;

	public class SyncPointCenterFactory {
		private static var _syncPointCenter:ReceiverCenter

		public static function getInstance(type:int, meta:Dictionary):ReceiverCenter {
			if (type === 1) //Slides
				return new SyncPointReceiverCenter(meta);
			else if (type === 2) //Quizs
				return new QuizPointReceiverCenter(meta);
//			else if (type === 3) //Notes
//				return new NoteSyncPointCenter(meta);
//			else if (type === 4)
//				return new ScreenPointCenter(meta);
//			else if (type === 5)
//				return new ImageSyncPointCenter(meta);
			return null;
		}
	}
}
