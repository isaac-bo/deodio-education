package com.deodio.elearning.libs.utils {
	import com.deodio.elearning.model.ModelLocator;

	import mx.utils.UIDUtil;

	public class CommonUtils {

		private static const PATTERN_UNDERLINE:RegExp=/-/g;

		[Bindable]
		public var _model:ModelLocator=ModelLocator.getInstance();

		public static function createUID():String {
			return UIDUtil.createUID().replace(PATTERN_UNDERLINE, "");
		}

		public static function formatTimes(time:int):String {

			var seco_dig:Number=time % 60;
			var mins_dig:Number=0;
			var hour_dig:Number=0;

			var seco:String="";
			var mins:String="";
			var hour:String="";

			if (time / 60 >= 60) {
				mins_dig=Math.floor(time / 60) % 60;
				hour_dig=Math.floor(Math.floor(time / 60) / 60);
			} else {
				mins_dig=Math.floor(time / 60);
				hour_dig=0;
			}

			hour=hour_dig >= 10 ? hour_dig + "" : "0" + hour_dig;
			mins=mins_dig >= 10 ? mins_dig + "" : "0" + mins_dig;
			seco=seco_dig >= 10 ? seco_dig + "" : "0" + seco_dig;

			return hour + ":" + mins + ":" + seco;
		}
	}
}
