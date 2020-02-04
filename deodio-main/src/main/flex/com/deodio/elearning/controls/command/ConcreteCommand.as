package com.deodio.elearning.controls.command {
	import com.deodio.elearning.controls.ICommand;
	import com.deodio.elearning.controls.receiver.ReceiverCenter;

	public class ConcreteCommand implements ICommand {
		var receiver:ReceiverCenter;

		public function ConcreteCommand(rec:ReceiverCenter) {
			receiver=rec;
		}

		public function execute():void {
			receiver.action();
		}
	}
}
