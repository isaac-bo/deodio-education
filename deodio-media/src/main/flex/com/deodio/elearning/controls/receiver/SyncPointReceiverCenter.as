package com.deodio.elearning.controls.receiver {
	import com.deodio.elearning.controls.IExecutor;
	import com.deodio.elearning.controls.spoint.SyncPointExecutor;

	import flash.utils.Dictionary;

	public class SyncPointReceiverCenter extends ReceiverCenter {
		public function SyncPointReceiverCenter(meta:Dictionary) {
			super(meta);
		}

		override protected function createSyncPoint():IExecutor {
			return new SyncPointExecutor();
		}
	}
}
