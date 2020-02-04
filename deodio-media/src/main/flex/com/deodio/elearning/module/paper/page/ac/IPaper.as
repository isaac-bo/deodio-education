package com.deodio.elearning.module.paper.page.ac {
	import com.deodio.elearning.libs.paper.DupImage;

	import flash.display.MovieClip;

	[Event(name="onPageNavigate", type="flash.events.Event")]

	public interface IPaper {
		function drawSelf(pageIndex:int, drawingObject:Object, scale:Number):void;
		function init():void;
		function registerCallbackMethods():void;
		function clear():void;
		function bindPaperEventHandler(d:DupImage):void;
	}
}
