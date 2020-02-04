package com.deodio.elearning.controls.receiver {
	import com.deodio.elearning.controls.IExecutor;
	import com.deodio.elearning.controls.spoint.QuizPointExecutor;

	import flash.utils.Dictionary;

	public class QuizPointReceiverCenter extends ReceiverCenter {
		public function QuizPointReceiverCenter(meta:Dictionary) {
			super(meta);
		}

		override protected function createSyncPoint():IExecutor {
			return new QuizPointExecutor();
		}
	}
}
