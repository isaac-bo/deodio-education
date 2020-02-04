package com.deodio.elearning.libs.stream.sprite {
	import flash.display.Sprite;
	import flash.events.TimerEvent;
	import flash.media.SoundMixer;
	import flash.utils.ByteArray;
	import flash.utils.Timer;

	public class Spectrum extends Sprite {

		private var s:Sprite=new Sprite();
		private var main:Sprite;
		private var spectrum:ByteArray=new ByteArray();
		private var timer:Timer;

		private var m_width:Number;
		private var m_height:Number;

		public function Spectrum(_width:Number=200, _height:Number=100) {
			addChild(s);
			m_width=_width;
			m_height=_height;
			prismatical(_width, _height);
		}

		private function prismatical(_width:Number, _height:Number):void {
			main=new Sprite();
			s.addChild(main);
			for (var i:uint=0; i < 64; i++) {
				var r:RectSprite=new RectSprite(5, _height);
				r.y=0;
				r.x=6 * i;
				main.addChild(i);
				r.name="r_" + i;
			}
			main.width=_width;
			timer=new Timer(200);
			if (!SoundMixer.areSoundsInaccessible()) {
				timer.addEventListener(TimerEvent.TIMER, prismatical_update);
				timer.start();
			}
		}

		private function prismatical_update(event:TimerEvent):void {
			SoundMixer.computeSpectrum(spectrum, true);
			for (var i:uint=0; i < 64; i++) {
				var a:Number=0.0;
				spectrum.position=i * 16;
				a=spectrum.readFloat() + spectrum.readFloat() + spectrum.readFloat() + spectrum.readFloat();
				spectrum.position = 1024 + i*16;
				a+=spectrum.readFloat() + spectrum.readFloat() + spectrum.readFloat() + spectrum.readFloat();
				
				var r:RectSprite = new RectSprite(main.getChildByName("r_"+i);
				r.update(a/6);
			}
		}
	}
}
