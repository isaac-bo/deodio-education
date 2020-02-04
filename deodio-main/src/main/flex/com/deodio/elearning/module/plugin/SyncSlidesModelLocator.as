package com.deodio.elearning.module.plugin {
	import com.deodio.elearning.domain.PresentationSyncSlides;
	import com.deodio.elearning.model.ModelLocator;
	import com.deodio.elearning.module.plugin.AbstractMediaModelLocator;
	import com.deodio.elearning.service.PresentationService;

	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.events.DragEvent;

	import spark.components.List;

	[Bindable]
	public class SyncSlidesModelLocator extends AbstractMediaModelLocator {

		private var presentationService:PresentationService;
		private var _model:ModelLocator=ModelLocator.getInstance();
		private var _selectedIndex:int=-1;
		private var _selectedItem:Object=null;

		public var slides:ArrayCollection=new ArrayCollection();
		public var slides_2:ArrayCollection=new ArrayCollection();
		private static var _slidesLocator:SyncSlidesModelLocator;

		public function SyncSlidesModelLocator() {
			presentationService=new PresentationService();
		}

		public static function getInstance():SyncSlidesModelLocator {
			if (_slidesLocator == null) {
				_slidesLocator=new SyncSlidesModelLocator();
			}
			return _slidesLocator;
		}


		public function slideThumbsDragEnter(event:DragEvent, list:List):void {
			trace("------------slideThumbsDragEnter--------------");
			_selectedIndex=list.selectedIndex;
			_selectedItem=list.selectedItem;


		}

		public function slideThumbsDragExit(event:DragEvent, list:List):void {
//			Alert.show("------------slideThumbsDragExit--------------");
			_selectedIndex=-1;
			_selectedItem=null;
		}

		public function slideThumbsDragComplete(event:DragEvent, list:List):void {
			trace("------------slideThumbsDragComplete--------------");
			_model.selectedSyncSlide=null;
			_model.isSlidePicker=false;
			this.slides=slides_2;
			list.dataProvider=this.slides;

		}

		public function slideThumbsDragDrop(event:DragEvent, list:List):void {
			trace("------------slideThumbsDragDrop--------------");
			_selectedIndex=list.selectedIndex;
			_selectedItem=list.selectedItem;
		}

		public function slideThumbsDragStart(event:DragEvent, list:List):void {
//			_synchronize.playBar.isPoint=false;
//			this.slideThumbs.dropEnabled=true;
//			_isDelete=false;
//			_synchronize.playBar.initCurrentPoint();
//			
			_model.isSlidePicker=true;
			_model.selectedSyncSlide=list.selectedItem as PresentationSyncSlides;
			slides_2=new ArrayCollection();
			for each (var obj:Object in this.slides) {
				slides_2.addItem(obj);
			}
		}
	}
}
