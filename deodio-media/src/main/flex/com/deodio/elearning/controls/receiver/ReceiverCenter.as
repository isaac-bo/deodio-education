package com.deodio.elearning.controls.receiver {

	import com.deodio.elearning.controls.IExecutor;

	import flash.errors.IllegalOperationError;
	import flash.utils.Dictionary;

	public class ReceiverCenter {
		protected var meta:Dictionary;

		public function ReceiverCenter(meta:Dictionary) {
			this.meta=meta;
		}

		protected function createSyncPoint():IExecutor {
			throw new IllegalOperationError("Abstract method: must be overridden in a subclass");
			return null;
		}

		public function action():void {
			var syncPoint:IExecutor=this.createSyncPoint();
			syncPoint.action(this.meta);
		}
	}
}
