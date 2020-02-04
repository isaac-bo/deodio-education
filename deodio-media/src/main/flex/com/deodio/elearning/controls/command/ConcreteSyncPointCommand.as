package com.knoodle.moudle.media.controls.command {
	import com.deodio.elearning.controls.ICommand;
	import com.deodio.elearning.controls.ISyncPointCommand;

	public class ConcreteSyncPointCommand implements com.deodio.elearning.controls.ISyncPointCommand {
		var commandObjectList:Array;

		public function ConcreteSyncPointCommand() {
			this.commandObjectList=new Array();
		}

		public function add(c:ICommand):void {
			commandObjectList.push(c);
		}

		public function remove(c:ICommand):void {
			for (var i:int=0; i < commandObjectList.length; i++) {
				if (commandObjectList[i] === c) {
					commandObjectList.splice(i, 1);
					break;
				}
			}
		}

		public function execute():void {
			for (var i:int=0; i < commandObjectList.length; i++) {
				commandObjectList[i].execute();
			}
		}
	}
}
