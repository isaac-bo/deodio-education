package com.deodio.elearning.libs.effects{ 
	import mx.effects.Fade;
	import mx.events.EffectEvent;

	public class FadeEffect{
		
		private var _fadeOut:Fade;
		private var _fadeIn:Fade;
		private var _target:Object;
		private var _isPlaying:Boolean;
		
		
		public function get isPlaying():Boolean{
			return (this._fadeIn.isPlaying || this._fadeOut.isPlaying);
		}
		
		public function FadeEffect(target:Object){
			this._target = target;
			this._fadeIn = createFadeIn(target);
			this._fadeOut = createFadeOut(target);
			this._fadeIn.addEventListener(EffectEvent.EFFECT_START,function (e:EffectEvent):void{
				e.target.target.visible = true;
			});
			this._fadeOut.addEventListener(EffectEvent.EFFECT_END,function (e:EffectEvent):void{
				e.target.target.visible = false;
			});
		}
		
		public function fadeOut(duration:Number = 1000):void{
			this._fadeOut.duration = duration;
			this.endPlay();
			this._fadeOut.play();
		}
		
		public function fadeIn(duration:Number = 1000):void{
			this._fadeIn.duration = duration;
			this.endPlay();
			this._fadeIn.play();
		}
		
		public function endPlay():void{
			if(this._fadeIn.isPlaying) this._fadeIn.end();
			if(this._fadeOut.isPlaying) this._fadeOut.end();
		}
		
		protected function createFadeIn(target:Object):Fade{
			var result:Fade = new Fade();
			result.alphaFrom = 0;
			result.alphaTo = 1;
			result.target = target;
			return result;
		}
		
		protected function createFadeOut(target:Object):Fade{
			var result:Fade = new Fade();
			result.alphaFrom = 1;
			result.alphaTo = 0;
			result.target = target;
			return result;
		}
	}
}