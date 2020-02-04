package com.deodio.elearning.libs.stream.pool {
	import com.deodio.elearning.libs.stream.AudioStreamController;
	import com.deodio.elearning.libs.stream.IStreamController;
	import com.deodio.elearning.libs.stream.RecordStreamController;
	import com.deodio.elearning.libs.stream.VideoStreamController;

	import mx.collections.ArrayCollection;

	public class StreamingPool {

		private static var _stream:IStreamController;

		public static function createSteamings(stream:String, num:Number):ArrayCollection {

			switch (stream) {
				case "record":
					return createRecordStreamings(num);
				case "video":
					return createVideoStreamings(num);
				case "audio":
					return createAudioStreamings(num);
				default:
					return new ArrayCollection();
			}

		}

		private static function createRecordStreamings(num:Number):ArrayCollection {
			var streamings:ArrayCollection=new ArrayCollection();
			for (var index:Number=0; index < num; index++) {
				streamings.addItem(new RecordStreamController());
			}

			return streamings;
		}

		private static function createVideoStreamings(num:Number):ArrayCollection {
			var streamings:ArrayCollection=new ArrayCollection();
			for (var index:Number=0; index < num; index++) {
				streamings.addItem(new VideoStreamController());
			}

			return streamings;
		}

		private static function createAudioStreamings(num:Number):ArrayCollection {
			var streamings:ArrayCollection=new ArrayCollection();
			for (var index:Number=0; index < num; index++) {
				streamings.addItem(new AudioStreamController());
			}

			return streamings;
		}

	}
}

