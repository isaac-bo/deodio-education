package com.deodio.elearning.libs.stream{
	import com.deodio.elearning.libs.event.MediaEvent;
	
	import mx.controls.Alert;

	public class LiveStreamController extends RecordStreamController{
		public function LiveStreamController(){
			super();
			super._isRecordVideo = true;
		}
		
		public override function setMedia():void {
			stream.attachAudio(microPhone);
			if (super._isRecordVideo == true) {
				stream.attachCamera(camera);
			}
			stream.publish(super.recordUrl, "live");
		}
		
	}
}