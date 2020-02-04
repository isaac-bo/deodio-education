package com.deodio.elearning.controls {
	import com.deodio.elearning.controls.ICommand;

	public interface ISyncPointCommand extends ICommand {
		public function add(c:ICommand):void;
		public function remove(c:ICommand):void;
	}
}
