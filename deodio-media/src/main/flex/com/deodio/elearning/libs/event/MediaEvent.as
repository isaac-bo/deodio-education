package com.deodio.elearning.libs.event {
	import flash.events.Event;

	public class MediaEvent extends Event {


		public static const MEDIA_PLAY:String="media.play";
		public static const MEDIA_PAUSE:String="media.pause";
		public static const MEDIA_STOP:String="media.stop";
		public static const MEDIA_RESUME:String="media.resume";
		public static const MEDIA_VOLUMN:String="media.volumn";
		public static const MEDIA_VOLUMN_CHANGE:String="media.volumn.change";
		public static const MEDIA_PROGRESS_CHANGE:String="media.progress.change";
		public static const MEDIA_PROGRESS_CLICK:String="media.progress.click";
		public static const MEDIA_PROGRESS_PRESS:String="media.progress.press";
		public static const MEDIA_PROGRESS_RELEASE:String="media.progress.release";
		public static const MEDIA_SCREEN_FULL_SCREEN:String="media.screen.full.screen";
		public static const MEDIA_SCREEN_SWITCH_SCREEN:String="media.screen.switch.screen";
		public static const MEDIA_SCREEN_SHOW_PLAYBAR:String="media.screen.show.playbar";
		public static const MEDIA_SCREEN_HIDE_PLAYBAR:String="media.screen.hide.playbar";
		public static const MEDIA_EXEC_COMMAND:String="media.exec.command";
		public static const MEDIA_COMPLETE:String="meida.complete";


		public static const MEDIA_CREATE_MEDIA_COVER:String="media.create.media.cover";
		public static const MEDIA_DESTORY_MEDIA_COVER:String="media.destory.media.cover";
		public static const MEDIA_CREATE_MEDIA_ITEM:String="media.create.media.item";
		public static const MEDIA_FINISH_MEDIA_ITEM:String="media.finish.media.item";


		public static const MEDIA_BUFFER_EMPTY:String="media.buffer.empty";
		public static const MEDIA_BUFFER_FULL:String="media.buffer.full";
		public static const MEDIA_BUFFER_FLUSH:String="media.buffer.flush";
		public static const MEDIA_NET_STREAM_PLAY_START:String="media.netstream.play.start";
		public static const MEDIA_NET_STREAM_PLAY_COMPLETE:String="media.netstream.play.complete";
		public static const MEDIA_NET_STREAM_PLAY_STOP:String="media.netstream.play.stop";
		public static const MEDIA_NET_STREAM_PLAY_PAUSE:String="media.netstream.play.pause";
		public static const MEDIA_NET_STREAM_SET_URL:String="media.netstream.set.url";
		public static const MEDIA_NET_STREAM_PUBLISH:String="media.netstream.publish.start";
		public static const MEDIA_NET_STREAM_RECORD_START:String="media.netstream.record.strat";
		public static const MEDIA_NET_STREAM_RECORD_STOP:String="media.netstream.record.stop";
		public static const MEDIA_NET_STREAM_PLAY_UNPUBLISH_NOTIFY:String = "media.netstream.play.unpublish.notify";
		public static const MEDIA_NET_STREAM_CONNECT_FAILED:String="media.netstream.connect.failed";

		public static const MEDIA_NET_STREAM_PAUSE:String="media.netstream.pause";
		public static const MEDIA_NET_STREAM_PAUSE_BUFFER_FULL:String="media.netstream.pause.buffer.full";
		public static const MEDIA_SHOW_AUDIO_IMAGE:String="media.show.audio.image";
		public static const MEDIA_CLIENT_COMPLETE:String="media.client.complete";
		public static const MEDIA_CLIENT_INIT:String="media.client.init";
		public static const MEDIA_MP3_DURATION:String="media.mp3.duration";

		public static const MEDIA_CREATE_COMPLETE:String="media.create.complete";
		public static const MEDIA_DELETE_COMPLETE:String="media.delete.complete";

		public static const MEDIA_MICROPHONE_MUTED:String="media.microphone.muted";
		public static const MEDIA_MICROPHONE_UN_MUTED:String="media.microphone.un.muted";

		public static const MEDIA_CONNECT_BEGIN_LOADING:String="media.connect.begin.loading";
		public static const MEDIA_CONNECT_FINISH_LOADING:String="media.connect.finish.loading";

		public static const MEDIA_CONTINUE_PLAY:String="media.continue.play";
		public static const MEDIA_STREAM_COMPLETE:String="media.stream.complete";

		public static const MEDIA_IS_EDIT_PLAYBAR:String="media.is.edit.playbar";
		public static const MEDIA_IS_EDIT_SCREEN:String="media.is.edit.screen";
		public static const MEDIA_SWAP_MEDIA:String="media.swap.media";
		public static const MEDIA_SWAP_RESULT:String="media.swap.result";
		public static const MEDIA_TRIM_MEDIA:String="media.trim.media";
		public static const MEDIA_TRIM_RESULT:String="media.trim.result";

		public var data:Object;

		public function MediaEvent(type:String, data:Object=null, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
			this.data=data;
		}
	}
}
