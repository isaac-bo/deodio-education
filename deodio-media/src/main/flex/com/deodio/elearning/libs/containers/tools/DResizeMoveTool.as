package com.deodio.elearning.libs.containers.tools {

	import flash.display.Bitmap;
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	import flash.media.Video;

	import mx.controls.Alert;
	import mx.core.UIComponent;

	import spark.components.BorderContainer;

	public class DResizeMoveTool {

		private var container:BorderContainer;

		private var widget:DisplayObject;

		private var object:Object;

		private var rects:Array=[];

		private var currRect:UIComponent;

		private var sizeable:Boolean;

		public function DResizeMoveTool(container:BorderContainer, object:Object) {
			this.container=container;
			this.object=object;
			rects=[];
			for (var i:int=0; i < 9; i++) {
				var rect:UIComponent=createRectHandler();
				rect.addEventListener(MouseEvent.MOUSE_DOWN, evt_rect_mouse_down);
				rect.addEventListener(MouseEvent.MOUSE_UP, evt_rect_mouse_up);
				rect.buttonMode=rect.useHandCursor=true;

				rects[i]=rect;
			}
			container.addEventListener(MouseEvent.MOUSE_UP, evt_container_mouse_up);
		}


		public function removeHandler():void {
			var i:int;
			var handler:UIComponent;
			var start:int=sizeable ? 0 : 8;
			for (i=start; i < rects.length; i++) {
				handler=rects[i];
				container.removeElement(handler);
			}
		}

		public function addHandler(widget:DisplayObject, sizeable:Boolean=true):void {
			addWidget(widget, sizeable);
		}

		private function addWidget(_widget:DisplayObject, sizeable:Boolean):void {
			if (widget && widget.parent) {
				widget.parent.removeChild(widget);
			}

			this.sizeable=sizeable;
			widget=_widget;
			var p1:Point=new Point(object.x + 100, object.y + 100);
			var p2:Point=new Point(object.x + object.width - 100, object.y + object.height - 100);
			widget.addEventListener(MouseEvent.CLICK, evt_change_focus);
			setRectsPos(p1, p2);
			setFocus(this);
		}

		private function evt_change_focus(e:MouseEvent):void {
			setFocus(this);
		}

		private function evt_container_mouse_up(e:MouseEvent):void {
			if (currRect) {
				currRect.stopDrag();
			}

			container.removeEventListener(MouseEvent.MOUSE_MOVE, evt_mouse_move);
		}

		private function evt_rect_mouse_down(e:MouseEvent):void {
			var rect:UIComponent=e.currentTarget as UIComponent;
			rect.startDrag();
			currRect=rect;
			container.addEventListener(MouseEvent.MOUSE_MOVE, evt_mouse_move);
		}

		private function evt_rect_mouse_up(e:MouseEvent):void {
			var rect:UIComponent=e.currentTarget as UIComponent;
			rect.stopDrag();
			currRect=null;
			container.removeEventListener(MouseEvent.MOUSE_MOVE, evt_mouse_move);
		}

		private function evt_mouse_move(e:MouseEvent):void {
			if (currRect) {
				updatePosition(currRect);
			}
		}

		private function updatePosition(rect:UIComponent):void {
			var ind:int=rects.indexOf(rect);
			var p1:Point=new Point(rects[0].x, rects[0].y);
			var p2:Point=new Point(rects[7].x, rects[7].y);

			if (ind == 0) { //左上角  
				p1.x=rect.x < object.x ? object.x : rect.x;
				p1.y=rect.y < object.y ? object.y : rect.y;

				if (rect.x < object.x || rect.y < object.y) {
					rect.stopDrag();
				}

			} else if (ind == 1) { //上部  
				p1.y=rect.y < object.y ? object.y : rect.y;
				if (rect.y < object.y) {
					rect.stopDrag();
				}

			} else if (ind == 2) { //右上角  
				p2.x=rect.x > (object.width + object.x) ? object.width + object.x : rect.x;
				p1.y=rect.y < object.y ? object.y : rect.y;

				if (rect.x > (object.width + object.x) || rect.y < object.y) {
					rect.stopDrag();
				}

			} else if (ind == 3) { //左边  
				p1.x=rect.x < object.x ? object.x : rect.x;
				if (rect.x < object.x) {
					rect.stopDrag();
				}


			} else if (ind == 4) { //右边  
				p2.x=rect.x > (object.width + object.x) ? object.width + object.x : rect.x;
				if (rect.x > (object.width + object.x)) {
					rect.stopDrag();
				}

			} else if (ind == 5) { //左下角  
				p1.x=rect.x < object.x ? object.x : rect.x;
				p2.y=rect.y > object.height + object.y ? object.height + object.y : rect.y;

				if (rect.x < object.x || rect.y > object.height + object.y) {
					rect.stopDrag();
				}

			} else if (ind == 6) { //下边  
				p2.y=rect.y > object.height + object.y ? object.height + object.y : rect.y;
				if (rect.y > object.height + object.y) {
					rect.stopDrag();
				}

			} else if (ind == 7) { //右下角  
				p2.x=rect.x > object.width + object.x ? object.width + object.x : rect.x;
				p2.y=rect.y > object.height + object.y ? object.height + object.y : rect.y;

				if (rect.x > (object.width + object.x) || rect.y > (object.height + object.y)) {
					rect.stopDrag();
				}

			} else if (ind == 8) { //中间的  
				var w:Number=p2.x - p1.x;
				var h:Number=p2.y - p1.y;
				p1.x=(rect.x - w / 2) < object.x ? object.x : rect.x - w / 2;
				p1.y=(rect.y - h / 2) < object.y ? object.y : rect.y - h / 2;

				p2.x=(p1.x + w) > object.width + object.x ? object.width + object.x : p1.x + w;
				p2.y=(p1.y + h) > object.height + object.y ? object.height + object.y : p1.y + h;

				if ((rect.x - w / 2) < object.x || (rect.y - h / 2) < object.y || (p1.x + w) > (object.width + object.x) || (p1.y + h) > (object.height + object.y)) {
					rect.stopDrag();
				}

			} else {
				throw new Error("Swap Function throws Eexception....");
			}
			setRectsPos(p1, p2);
		}



		private function setOperating(value:Boolean):void {
			var i:int;
			var handler:UIComponent;
			var start:int=sizeable ? 0 : 8;
			if (value) { //可操作  
				for (i=start; i < rects.length; i++) {
					handler=rects[i]
					container.addElement(handler);
				}
			} else { //不可操作  
				for (i=start; i < rects.length; i++) {
					handler=rects[i]
					if (handler.parent) {
//						(handler.parent as Screen).removeElement(handler);
					}
				}
			}
		}


		private function createRectHandler():UIComponent {
//			Alert.show('---------');
			var size:int=8;
			var uiComponet:UIComponent=new UIComponent();
			uiComponet.graphics.beginFill(0x000000, 0.4);
//			uiComponet.graphics.beginFill(0x526c8d, 1);
			uiComponet.graphics.drawRect(-size / 2, -size / 2, size, size);
			uiComponet.graphics.endFill();
			uiComponet.graphics.lineStyle(1, 0xFFFFFF);
			uiComponet.graphics.drawRect(-size / 2, -size / 2, size, size);

			return uiComponet;
		}


		private function setRectsPos(p1:Point, p2:Point):void {
			if (p1.x > p2.x) { //判定，并且保证 p1 在 p2 的左上方  
				var value:Number=p1.x;
				p1.x=p2.x;
				p2.x=value;
			}
			if (p1.y > p2.y) { //判定，并且保证 p1 在 p2 的左上方  
				value=p1.y;
				p1.y=p2.y;
				p2.y=value;
			}
			rects[0].x=p1.x;
			rects[0].y=p1.y;

			rects[1].x=(p1.x + p2.x) / 2;
			rects[1].y=p1.y;

			rects[2].x=p2.x;
			rects[2].y=p1.y;

			rects[3].x=p1.x;
			rects[3].y=(p1.y + p2.y) / 2;

			rects[4].x=p2.x;
			rects[4].y=(p1.y + p2.y) / 2;

			rects[5].x=p1.x;
			rects[5].y=p2.y;

			rects[6].x=(p1.x + p2.x) / 2;
			rects[6].y=p2.y;

			rects[7].x=p2.x;
			rects[7].y=p2.y;

			rects[8].x=(p1.x + p2.x) / 2;
			rects[8].y=(p1.y + p2.y) / 2;

			//改变对象大小  
			widget.x=p1.x;
			widget.y=p1.y;
			widget.width=p2.x - p1.x;
			widget.height=p2.y - p1.y;
		}


		private function setFocus(value:DResizeMoveTool):void {
			setOperating(true);
//			if (resizeMoveTool) {
//				resizeMoveTool.setOperating(false);
//			}
//			resizeMoveTool=value;
//			if (resizeMoveTool) {
//				resizeMoveTool.setOperating(true);
//			}
		}


	}
}

class Single {
}
