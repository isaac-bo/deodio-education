package com.deodio.elearning.libs.stream.sprite {
	import flash.display.Sprite;
	import flash.events.TimerEvent;
	import flash.utils.Timer;

	import org.osmf.events.TimeEvent;

	public class RectSprite extends Sprite {

		private var s:Sprite;
		private var z:Sprite;
		private var timer:Timer;
		private var h:Number;

		public function RectSprite(_width:Number, _height:Number, color:uint=0xFFFFFF) {
			s=new Sprite();

			s.graphics.beginFill(color);
			s.graphics.drawRect(0, 0, _width, _height);
			s.graphics.endFill();

			s.rotation=180;
			s.height=0;
			s.y=_height;
			addChild(s);

			h=_height;
			var zHeight:Number=2;
			z=new Sprite();
			z.graphics.beginFill(0x2aeaeb);
			z.graphics.drawRect(0, 0, _width, zHeight);
			z.graphics.endFill();
			z.y=_height - zHeight;
			z.rotation=180;
			addChild(z);

			timer=new Timer(40);
			timer.addEventListener(TimerEvent.TIMER, onTimer);
			timer.start();
		}

		private function onTimer(event:TimeEvent):void {
			if (s.height > 0) {
				var speed:Number=0.02 * h;
				if (speed > s.height) {
					s.height=0;
				} else {
					s.height-=speed;
				}
			}

			if (z.y + z.height >= h - s.height) {
				z.y=h - s.height - z.height;
			} else {
				var zspeed:Number=0.01 * h;
				z.y+=zspeed;
			}

		}

		public function update(percent:Number):void {
			if (percent > 1.0)
				percent=1.0;
			if (s.height < h * percent) {
				s.height=h * percent;
				if (z.y + z.height >= h - s.height) {
					z.y=h - s.height - z.height;
				}
			}
		}
	}
}
