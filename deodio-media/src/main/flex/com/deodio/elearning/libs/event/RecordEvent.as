package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class RecordEvent extends Event {

		public static const RECORD_PLAY_LENGTH:String="record.play.length";
		public static const RECORD_PLAY_INIT:String="record.play.init";
		public static const RECORD_CREATE_COMPLETED:String="record.create.completed";
		public static const RECORD_START:String="record.start";
		public static const RECORD_STOP:String="record.stop";
		public static const RECORD_REMOVE_ALL:String="record.remove.all";
		public static const RECORD_REMOVE_ITEM:String="record.remove.item";
		public static const RECORD_SET_MODEL:String="record.set.model";
		public static const RECORD_PLAY:String="record.play";
		public static const RECORD_START_MEDIA:String="record.start.media";
		public static const RECORD_STOP_MEDIA:String="record.stop.media";
		public static const RECORD_PLAY_MEDIA:String="record.play.media";
		public static const RECORD_PAUSE_MEDIA:String="record.pause.media";

		public static const REORD_CLICK_RECORD_MEDIA:String="record.click.record.media";
		public static const RECORD_START_SELF_MEDIA:String="record.start.self.media";
		public static const RECORD_STOP_SELF_MEDIA:String="record.stop.self.media";
		public static const RECORD_START_LIVE_MEDIA:String="record.start.live.media";
		public static const RECORD_STOP_LIVE_MEDIA:String="record.stop.live.media";
		public static const RECORD_SAVE_MEDIA:String="record.save.media";

		public var data:Object;

		public function RecordEvent(type:String, data:Object=null, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
			this.data=data;
		}
	}
}
