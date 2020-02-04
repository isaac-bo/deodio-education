package com.deodio.elearning.controls.command {
	import com.deodio.elearning.controls.ICommand;

	public class CommandInvoker {
		var currentCommand:ICommand

		public function CommandInvoker() {
		}

		public function setCommand(c:ICommand):void {
			this.currentCommand=c;
		}

		public function executeCommand():void {
			currentCommand.execute();
		}
	}
}
