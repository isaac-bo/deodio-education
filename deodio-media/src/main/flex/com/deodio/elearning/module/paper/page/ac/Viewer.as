package com.deodio.elearning.module.paper.page.ac {
	import com.adobe.serialization.json.JSON;
	import com.deodio.elearning.caurina.transitions.Tweener;
	import com.deodio.elearning.libs.enum.FitModeEnum;
	import com.deodio.elearning.libs.enum.ViewModeEnum;
	import com.deodio.elearning.libs.event.CurrentPageChangedEvent;
	import com.deodio.elearning.libs.event.CursorModeChangedEvent;
	import com.deodio.elearning.libs.event.DocumentLoadedEvent;
	import com.deodio.elearning.libs.event.ErrorLoadingPageEvent;
	import com.deodio.elearning.libs.event.ExternalLinkClickedEvent;
	import com.deodio.elearning.libs.event.FitModeChangedEvent;
	import com.deodio.elearning.libs.event.InternalLinkClickedEvent;
	import com.deodio.elearning.libs.event.PageLoadedEvent;
	import com.deodio.elearning.libs.event.PageLoadingEvent;
	import com.deodio.elearning.libs.event.ScaleChangedEvent;
	import com.deodio.elearning.libs.event.SelectionCreatedEvent;
	import com.deodio.elearning.libs.event.SwfLoadedEvent;
	import com.deodio.elearning.libs.event.ViewModeChangedEvent;
	import com.deodio.elearning.libs.paper.DupImage;
	import com.deodio.elearning.libs.paper.DupLoader;
	import com.deodio.elearning.libs.paper.IDocumentLoader;
	import com.deodio.elearning.libs.paper.IGenericDocument;
	import com.deodio.elearning.libs.paper.ITextSelectableDisplayObject;
	import com.deodio.elearning.libs.paper.SwfDocument;
	import com.deodio.elearning.libs.paper.ZoomCanvas;
	import com.deodio.elearning.libs.utils.CursorManagerUtils;
	import com.deodio.elearning.libs.utils.StreamUtil;
	import com.deodio.elearning.libs.utils.TextMapUtil;
	import com.deodio.elearning.module.paper.layout.ac.IPageLayout;
	import com.deodio.elearning.module.paper.tools.ac.HighlightMarker;
	import com.deodio.elearning.module.paper.tools.ac.SearchShapeMarker;
	import com.deodio.elearning.module.paper.tools.ac.ShapeMarker;
	import com.deodio.elearning.module.paper.ui.ac.FlowBox;
	import com.deodio.elearning.module.paper.ui.ac.FlowVBox;

	import flash.display.AVM1Movie;
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.DisplayObject;
	import flash.display.InteractiveObject;
	import flash.display.Loader;
	import flash.display.MovieClip;
	import flash.display.SimpleButton;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.events.ProgressEvent;
	import flash.external.ExternalInterface;
	import flash.filters.DropShadowFilter;
	import flash.filters.GlowFilter;
	import flash.geom.Matrix;
	import flash.net.LocalConnection;
	import flash.net.URLRequest;
	import flash.system.System;
	import flash.text.TextSnapshot;
	import flash.ui.Keyboard;
	import flash.ui.Mouse;
	import flash.ui.MouseCursor;
	import flash.utils.ByteArray;
	import flash.utils.Timer;
	import flash.utils.setTimeout;

	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.Image;
	import mx.core.Container;
	import mx.core.SpriteAsset;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.resources.ResourceManager;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;


	[Event(name="onDocumentLoaded", type="com.deodio.elearning.libs.event.DocumentLoadedEvent")]
	[Event(name="onPageLoaded", type="com.deodio.elearning.libs.event.PageLoadedEvent")]
	[Event(name="onPageLoading", type="com.deodio.elearning.libs.event.PageLoadingEvent")]
	[Event(name="onDocumentLoading", type="flash.events.Event")]
	[Event(name="onNoMoreSearchResults", type="flash.events.Event")]
	[Event(name="onMaxSearchResultsExceeded", type="flash.events.Event")]
	[Event(name="onDownloadSearchResultCompleted", type="flash.events.Event")]
	[Event(name="onDownloadingSearchResult", type="flash.events.Event")]
	[Event(name="onLoadingProgress", type="flash.events.ProgressEvent")]
	[Event(name="onScaleChanged", type="com.deodio.elearning.libs.event.ScaleChangedEvent")]
	[Event(name="onExternalLinkClicked", type="com.deodio.elearning.libs.event.ExternalLinkClickedEvent")]
	[Event(name="onInternalLinkClicked", type="com.deodio.elearning.libs.event.InternalLinkClickedEvent")]
	[Event(name="onCurrPageChanged", type="com.deodio.elearning.libs.event.CurrentPageChangedEvent")]
	[Event(name="onViewModeChanged", type="com.deodio.elearning.libs.event.ViewModeChangedEvent")]
	[Event(name="onFitModeChanged", type="com.deodio.elearning.libs.event.FitModeChangedEvent")]
	[Event(name="onCursorModeChanged", type="com.deodio.elearning.libs.event.CursorModeChangedEvent")]
	[Event(name="onDocumentLoadedError", type="flash.events.ErrorEvent")]
	[Event(name="onSelectionCreated", type="com.deodio.elearning.libs.event.SelectionCreatedEvent")]
	[Event(name="onDocumentPrinted", type="com.deodio.elearning.libs.event.DocumentPrintedEvent")]
	[Event(name="onErrorLoadingPage", type="com.deodio.elearning.libs.event.ErrorLoadingPageEvent")]

	public class Viewer extends Canvas {

		private var _swfFile:String="";
		private var _jsonFile:String=null;
		private var _swfFileChanged:Boolean=false;
		private var _loadingInitalized:Boolean=false;
		private var _initialized:Boolean=false;
		private var _loaderptr:Loader;
		private var _libMC:IGenericDocument;
		private var _displayContainer:Container;
		private var _paperContainer:ZoomCanvas;
		private var _swfContainer:Canvas;
		private var _scale:Number=1;
		private var _pscale:Number=1;
		private var _swfLoaded:Boolean=false;
		private var _pageList:Array;
		private var _viewMode:String=Viewer.InitViewMode;
		private var _fitMode:String=FitModeEnum.FITNONE;
		public static var InitViewMode:String=ViewModeEnum.PORTRAIT;
		private var _scrollToPage:Number=0;
		private var _numPages:Number=0;
		private var _currPage:Number=0;
		private var _tweencount:Number=0;
		private var _bbusyloading:Boolean=true;
		private var _zoomtransition:String="easeOut";
		private var _zoomtime:Number=0.6;
		private var _fitPageOnLoad:Boolean=false;
		private var _fitWidthOnLoad:Boolean=false;
		private var _dupImageClicked:Boolean=false;
		private var _docLoader:IDocumentLoader;
		private var _progressiveLoading:Boolean=false;
		private var _encodeURI:Boolean=true;
		private var _repaintTimer:Timer;
		private var _loadTimer:Timer;
		private var _frameLoadCount:int=0;
		private var _adjGotoPage:int=0;
		private var _zoomInterval:Number=0;
		private var _inputBytes:ByteArray;
		private var _textSelectEnabled:Boolean=false;
		private var _cursorsEnabled:Boolean=true;
		private var _grabCursorID:Number=0;
		private var _grabbingCursorID:Number=0;
		private var _pluginList:Array;
		public static var ViewModeExtList:Array;
		private var _currentExtViewMode:IPageLayout;
		private var _minZoomSize:Number=0.3;
		private var _maxZoomSize:Number=5;
		private var _searchMatchAll:Boolean=false;
		private var _provideSearchAbstracts:Boolean=false;
		private var _searchAbstracts:Array;
		private var _searchServiceUrl:String="";
		private var _performSearchOnPageLoad:Boolean=false;
		private var _autoAdjustPrintSize:Boolean=true;
		private var _pendingSearchPage:int=-1;
		private var _skinImg:Bitmap=new Bitmap(); //new MenuIcons.SMALL_TRANSPARENT();
		private var _skinImgc:Bitmap=new Bitmap(); //snew MenuIcons.SMALL_TRANSPARENT_COLOR();
		private var _skinImgDo:Image;

		public function Viewer() {
			super();
		}

		public function get BusyLoading():Boolean {
			return _bbusyloading;
		}

		public function set BusyLoading(b:Boolean):void {
			_bbusyloading=b;
		}

		public function get libMC():IGenericDocument {
			return _libMC;
		}

		public function get IsInitialized():Boolean {
			return _initialized;
		}

		public function get SwfLoaded():Boolean {
			return _swfLoaded;
		}

		public function get DisplayContainer():Container {
			return _displayContainer;
		}

		public function set DisplayContainer(c:Container):void {
			_displayContainer=c;
		}

		public function get PaperContainer():ZoomCanvas {
			return _paperContainer;
		}

		public function get PageList():Array {
			return _pageList;
		}

		public function get DocLoader():IDocumentLoader {
			return _docLoader;
		}

		public function set DocLoader(d:IDocumentLoader):void {
			_docLoader=d;
		}

		[Bindable]
		public function get ViewMode():String {
			return _viewMode;
		}

		[Bindable]
		public function get FitMode():String {
			return _fitMode;
		}

		public function set ViewMode(s:String):void {
			if (s != _viewMode) {
				if (_currentExtViewMode != null) {
					_currentExtViewMode.disposeViewMode();
				}

				for each (var vme:IPageLayout in ViewModeExtList) {
					if (s == vme.Name) {
						_currentExtViewMode=vme;
					}
				}

				if (s == ViewModeEnum.TILE) {
					_pscale=_scale;
					_scale=0.23;
					_paperContainer.verticalScrollPosition=0;
					_fitMode=FitModeEnum.FITNONE;
				} else {
					_scale=_pscale;
				}

				_viewMode=s;

				if (CurrExtViewMode != null) {
					CurrExtViewMode.setViewMode(s, this);
					_viewMode=s;
				}

				reInitialize();
				FitMode=FitModeEnum.FITNONE;

				{
					_paperContainer.visible=true;
				}

				dispatchEvent(new ViewModeChangedEvent(ViewModeChangedEvent.VIEWMODE_CHANGED, _viewMode));
				dispatchEvent(new ScaleChangedEvent(ScaleChangedEvent.SCALE_CHANGED, _scale));
				dispatchEvent(new FitModeChangedEvent(FitModeChangedEvent.FITMODE_CHANGED, _fitMode));
			}
		}

		public function reInitialize():void {
			if (_initialized && _swfLoaded) {
				createDisplayContainer(false);
				createLoaderList();
				if (this._progressiveLoading) {
					this.addInLoadedPages(true);
				} else {
					reCreateAllPages();
				}
				_displayContainer.visible=true;
			}
		}

		public function set FitMode(s:String):void {
			if (_viewMode == ViewModeEnum.TILE) {
				_fitMode=FitModeEnum.FITNONE;
				return
			}

			if (s != _fitMode) {
				_fitMode=s;

				switch (s) {
					case FitModeEnum.FITWIDTH:
						fitWidth();
						break;

					case FitModeEnum.FITHEIGHT:
						fitHeight();
						break;
				}

				dispatchEvent(new FitModeChangedEvent(FitModeChangedEvent.FITMODE_CHANGED, _fitMode));
			}
		}

		public function set ProgressiveLoading(b1:Boolean):void {
			_progressiveLoading=b1;
		}

		[Bindable]
		public function get ProgressiveLoading():Boolean {
			return _progressiveLoading;
		}

		public function set EncodeURI(b1:Boolean):void {
			_encodeURI=b1;
		}

		[Bindable]
		public function get EncodeURI():Boolean {
			return _encodeURI;
		}

		public function set TextSelectEnabled(b1:Boolean):void {
			_textSelectEnabled=b1;
			_displayContainer.doubleClickEnabled=!b1;

			if (_textSelectEnabled && CursorsEnabled)
				dispatchEvent(new CursorModeChangedEvent(CursorModeChangedEvent.CURSORMODE_CHANGED, "TextSelectorCursor"));
			else
				dispatchEvent(new CursorModeChangedEvent(CursorModeChangedEvent.CURSORMODE_CHANGED, "ArrowCursor"));
		}

		[Bindable]
		public function get TextSelectEnabled():Boolean {
			return false; //_textSelectEnabled;
		}

		[Bindable]
		public function get CursorsEnabled():Boolean {
			return false; //_cursorsEnabled;
		}

		[Bindable]
		public function get PerformSearchOnPageLoad():Boolean {
			return _performSearchOnPageLoad;
		}

		public function set PerformSearchOnPageLoad(b:Boolean):void {
			_performSearchOnPageLoad=b;
		}

		[Bindable]
		public function get PendingSearchPage():Number {
			return _pendingSearchPage;
		}

		public function set PendingSearchPage(n:Number):void {
			_pendingSearchPage=n;
		}

		public function set CursorsEnabled(b:Boolean):void {
			_cursorsEnabled=b;
		}

		public function setPaperFocus():void {
			_paperContainer.setFocus();
		}

		public function get ZoomTransition():String {
			return _zoomtransition;
		}

		public function set ZoomTransition(s:String):void {
			_zoomtransition=s;
		}

		public function get ZoomTime():Number {
			return _zoomtime;
		}

		public function set ZoomTime(n:Number):void {
			_zoomtime=n;
		}

		public function get MinZoomSize():Number {
			return _minZoomSize;
		}

		public function set MinZoomSize(n:Number):void {
			_minZoomSize=n;
		}

		public function get MaxZoomSize():Number {
			return _maxZoomSize;
		}

		public function set MaxZoomSize(n:Number):void {
			_maxZoomSize=n;
		}

		public function get PluginList():Array {
			return _pluginList;
		}

		public function set PluginList(p:Array):void {
			_pluginList=p;
		}

		public function get CurrExtViewMode():IPageLayout {
			if (_currentExtViewMode != null && _currentExtViewMode.Name == ViewMode)
				return _currentExtViewMode;
			else {
				for each (var vme:IPageLayout in ViewModeExtList) {
					if (ViewMode == vme.Name) {
						_currentExtViewMode=vme;
					}
				}
			}

			return _currentExtViewMode;
		}

		public function get UsingExtViewMode():Boolean {
			if (ViewMode == ViewModeEnum.PORTRAIT || ViewMode == ViewModeEnum.TILE)
				return false;
			else
				return (CurrExtViewMode != null && ViewMode == CurrExtViewMode.Name);
		}

		public function get ZoomInterval():Number {
			return _zoomInterval;
		}

		public function set ZoomInterval(n:Number):void {
			_zoomInterval=n;
		}

		[Bindable]
		public function get numPages():Number {
			return _numPages;
		}

		private function set numPages(n:Number):void {
			_numPages=n;
		}

		[Bindable]
		public function get numPagesLoaded():Number {
			return (_libMC != null) ? _libMC.framesLoaded : 0;
		}

		private function set numPagesLoaded(n:Number):void {

		}

		[Bindable]
		public function get currPage():Number {
			return _currPage;
		}

		private function set currPage(n:Number):void {
			_currPage=n;
		}

		[Bindable]
		public function get SearchMatchAll():Boolean {
			if (SearchServiceUrl != null && SearchServiceUrl.length > 0 && _docLoader.IsSplit)
				return false;
			else
				return _searchMatchAll;
		}

		public function set SearchMatchAll(b1:Boolean):void {
			_searchMatchAll=b1;
		}

		[Bindable]
		public function get ProvideSearchAbstracts():Boolean {
			return _provideSearchAbstracts;
		}

		public function set ProvideSearchAbstracts(b:Boolean):void {
			_provideSearchAbstracts=b;
		}

		public function set AutoAdjustPrintSize(b1:Boolean):void {
			_autoAdjustPrintSize=b1;
		}

		[Bindable]
		public function get AutoAdjustPrintSize():Boolean {
			return _autoAdjustPrintSize;
		}

		[Bindable]
		public function get SearchServiceUrl():String {
			return _searchServiceUrl;
		}

		public function set SearchServiceUrl(s1:String):void {
			_searchServiceUrl=unescape(s1);
		}

		public function get FitWidthOnLoad():Boolean {
			return _fitWidthOnLoad;
		}

		public function set FitWidthOnLoad(b1:Boolean):void {
			_fitWidthOnLoad=b1;
		}

		public function get FitPageOnLoad():Boolean {
			return _fitPageOnLoad;
		}

		public function set FitPageOnLoad(b2:Boolean):void {
			_fitPageOnLoad=b2;
		}

		public function gotoPage(p:Number, adjGotoPage:int=0, interactive:Boolean=false):void {
			if (adjGotoPage != 0) {
				_adjGotoPage=adjGotoPage;
			}

			if (p < 1 || p - 1 > _pageList.length)
				return;
			else {
				if (ViewMode == ViewModeEnum.PORTRAIT) {
					_paperContainer.verticalScrollPosition=_pageList[p - 1].y + 3 + _adjGotoPage;
				}

				if (UsingExtViewMode)
					CurrExtViewMode.gotoPage(p, adjGotoPage, interactive);

				// retry if y is not set
				if (!UsingExtViewMode && p > 1 && _pageList[p - 1].y == 0) {
					flash.utils.setTimeout(gotoPage, 200, p);
				} else
					_adjGotoPage=0;

				repaint();
			}
		}

		public function mvNext(extmode:Boolean=true):void {
			if (UsingExtViewMode && extmode)
				CurrExtViewMode.mvNext();
			else if (currPage < numPages) {
				gotoPage(currPage + 1);
			}
		}

		public function mvPrev(extmode:Boolean=true):void {
			if (UsingExtViewMode && extmode)
				CurrExtViewMode.mvPrev();
			else {
				if (currPage > 1) {
					gotoPage(currPage - 1);
				}
			}
		}

		public function switchMode(mode:String=null):void {
			if (mode == null) { // no mode passed, just 
				if (ViewMode == ViewModeEnum.PORTRAIT) {
					ViewMode=ViewModeEnum.TILE;
				} else if (ViewMode == ViewModeEnum.TILE) {
					_scale=_pscale;
					ViewMode=ViewModeEnum.PORTRAIT;
				}
			} else {
				if (ViewMode == mode && ViewMode != ViewModeEnum.PORTRAIT) {
					ViewMode=Viewer.InitViewMode;
				} else
					ViewMode=mode;
			}
		}

		public function get PaperVisible():Boolean {
			return _paperContainer.visible;
		}

		public function set PaperVisible(b:Boolean):void {
			_paperContainer.visible=b;
		}

		public function get SwfFile():String {
			return _swfFile;
		}

		public function getSwfFilePerPage(swfFile:String, page:int):String {
			var fileName:String=swfFile;
			var map:String=(fileName.substr(fileName.indexOf("[*,"), fileName.indexOf("]") - fileName.indexOf("[*,") + 1));
			var padding:int=parseInt(map.substr(map.indexOf(",") + 1, map.indexOf("]") - 2));
			fileName=TextMapUtil.StringReplaceAll(fileName, map, padString(page.toString(), padding, "0"));

			return encodeURI(fileName);
		}

		public function loadFromBytes(bytes:ByteArray):void {
			clearPlugins();
			deleteDisplayContainer();
			deletePageList();
			deleteLoaderPtr();
			deleteLoaderList();
			deleteFLoader();
			deleteSelectionMarker();
			TextMapUtil.totalFragments="";
			deleteLibMC();

			_swfFileChanged=true;
			_frameLoadCount=0;
			_loadingInitalized=false;

			ViewMode=Viewer.InitViewMode;

			try {
				new flash.net.LocalConnection().connect('devaldiGCdummy');
				new flash.net.LocalConnection().connect('devaldiGCdummy');
			} catch (e:*) {
			}

			try {
				flash.system.System.gc();
			} catch (e:*) {
			}
			if (_docLoader != null && !_docLoader.hasEventListener(SwfLoadedEvent.SWFLOADED)) {
				_docLoader.addEventListener("onDocumentLoadedError", onDocumentLoadedErrorHandler, false, 0, true);
				_docLoader.addEventListener(SwfLoadedEvent.SWFLOADED, swfComplete, false, 0, true);
			}

			if (_docLoader != null) {
				_docLoader.stream.removeEventListener(ProgressEvent.PROGRESS, onLoadProgress);
				_docLoader.stream.addEventListener(ProgressEvent.PROGRESS, onLoadProgress, false, 0, true);
			}

			_docLoader.loadFromBytes(bytes);

			_paperContainer.verticalScrollPosition=0;
			createDisplayContainer();

			// Changing the SWF file causes the component to invalidate.
			invalidateProperties();
			invalidateSize();
			invalidateDisplayList();
		}

		public function set SwfFile(s:String):void {
			var pagesSplit:Boolean=false;
			if (EncodeURI)
				s=unescape(s);
			if (s.length != 0) {

				if (s.indexOf("{") >= 0 && s.indexOf("}") > 0) {
					numPages=parseInt(s.substr(s.lastIndexOf(",") + 1, s.indexOf("}") - s.lastIndexOf(",") - 1));
					s=TextMapUtil.StringReplaceAll(s, "{", "");
					s=TextMapUtil.StringReplaceAll(s, "," + numPages.toString() + "}", "");
					pagesSplit=true;
				}
				clearPlugins();
				deleteDisplayContainer();
				deletePageList();
				deleteLoaderPtr();
				deleteLoaderList();
				deleteFLoader();
				deleteSelectionMarker();
				TextMapUtil.totalFragments="";
				if (s != _swfFile)
					deleteLibMC();
				_swfFileChanged=true;
				_frameLoadCount=0;
				_currPage=0;
				_loadingInitalized=false;
				_jsonPageData=null;
				if (!pagesSplit) {
					if (EncodeURI)
						_swfFile=encodeURI(s);
					else
						_swfFile=s;
				} else
					_swfFile=s;

				ViewMode=Viewer.InitViewMode;
				try {
					new flash.net.LocalConnection().connect('devaldiGCdummy');
					new flash.net.LocalConnection().connect('devaldiGCdummy');
				} catch (e:*) {
				}

				try {
					flash.system.System.gc();
				} catch (e:*) {
				}
				if (_docLoader != null && !_docLoader.hasEventListener(SwfLoadedEvent.SWFLOADED)) {
					_docLoader.IsSplit=pagesSplit;
					_docLoader.addEventListener("onDocumentLoadedError", onDocumentLoadedErrorHandler, false, 0, true);
					_docLoader.addEventListener(SwfLoadedEvent.SWFLOADED, swfComplete, false, 0, true);
				}

				if (_docLoader != null) {
					_docLoader.stream.removeEventListener(ProgressEvent.PROGRESS, onLoadProgress);
					_docLoader.stream.addEventListener(ProgressEvent.PROGRESS, onLoadProgress, false, 0, true);
				}

				if (_paperContainer != null)
					_paperContainer.verticalScrollPosition=0;
				_swfContainer=new Canvas();
				_swfContainer.visible=false;
				this.addElement(_swfContainer);

				createDisplayContainer();

				// Changing the SWF file causes the component to invalidate.
				invalidateProperties()
				invalidateSize();
				invalidateDisplayList();
				if (_swfFile.length > 0 && pagesSplit) {
					_loadTimer=new Timer(100);
					_loadTimer.addEventListener("timer", loadtimer);
					_docLoader.load(new URLRequest(getSwfFilePerPage(_swfFile, 1)), StreamUtil.getExecutionContext()); //new URLRequest(decodeURI(_swfFile))
				} else if (_swfFile.length > 0 && !pagesSplit) {
					_docLoader.load(new URLRequest(_swfFile), StreamUtil.getExecutionContext());
				}
			}
//
//			if (ExternalInterface.available) {
//				ExternalInterface.call("eb.enableMouseWheelHandler", !pagesSplit);
//			}
		}

		[Bindable]
		public function get JSONFile():String {
			return _jsonFile;
		}

		public function set JSONFile(val:String):void {
			_jsonFile=val;
		}

		[Bindable]
		public function get Scale():String {
			return _scale.toString();
		}

		public function get CurrentlySelectedText():String {
			return _currentlySelectedText;
		}

		public function Zoom(factor:Number, fnOnUpdate:Function=null, fnComplete:Function=null, forceZoom:Boolean=false):void {
			if ((factor < _minZoomSize || factor > _maxZoomSize || factor == _scale) && !(factor > _minZoomSize && factor < _maxZoomSize && forceZoom))
				return;

			if ((!UsingExtViewMode && _viewMode != ViewModeEnum.PORTRAIT) || (UsingExtViewMode && !CurrExtViewMode.doZoom)) {
				return;
			}

			var _target:DisplayObject;
			_paperContainer.CenteringEnabled=(_paperContainer.width > 0);

			_tweencount=_displayContainer.numChildren;

			var fnc:Function=onZoomComplete;
			if (fnComplete != null) {
				fnc=fnComplete;
			}

			for (var i:int=0; i < _displayContainer.numChildren; i++) {
				_target=_displayContainer.getChildAt(i);
				_target.filters=null;

				if (fnOnUpdate != null)
					Tweener.addTween(_target, {scaleX: factor, scaleY: factor, time: _zoomtime, transition: _zoomtransition, onUpdate: fnOnUpdate, onComplete: fnComplete});
				else
					Tweener.addTween(_target, {scaleX: factor, scaleY: factor, time: _zoomtime, transition: _zoomtransition, onComplete: onZoomComplete});
			}

			FitMode=FitModeEnum.FITNONE;
			_scale=factor;

			dispatchEvent(new ScaleChangedEvent(ScaleChangedEvent.SCALE_CHANGED, _scale));
		}

		public function rotate():void {
			var rotatenum:int=getVisibleMidPage();
			if (rotatenum < _pageList.length - 1 && _pageList.length > 2) {
				rotatenum=rotatenum - 1;
			}

			(_displayContainer.getChildAt(rotatenum) as DupImage).paperRotation=90;
		}

		private function degreesToRadians(degrees:Number):Number {
			var radians:Number=degrees * (Math.PI / 180);
			return radians;
		}

		public function getFitWidthFactor():Number {
			_libMC.gotoAndStop(1);
			return (_paperContainer.width / _libMC.width) - 0.032; //- 0.03;
		}

		public function getFitHeightFactor():Number {
			_libMC.gotoAndStop(1);
			return (_paperContainer.height / _libMC.height);
		}

		public function fitWidth():Boolean {
			if (_displayContainer.numChildren == 0) {
				return false;
			}

			var _target:DisplayObject;
			_paperContainer.CenteringEnabled=(_paperContainer.width > 0);
			var factor:Number=getFitWidthFactor();
			_scale=factor;
			_tweencount=_displayContainer.numChildren;

			for (var i:int=0; i < _displayContainer.numChildren; i++) {
				_target=_displayContainer.getChildAt(i);
				_target.filters=null;
				Tweener.addTween(_target, {scaleX: factor, scaleY: factor, time: 0, transition: 'easenone', onComplete: onZoomComplete});
			}

			FitMode=FitModeEnum.FITWIDTH;
			dispatchEvent(new ScaleChangedEvent(ScaleChangedEvent.SCALE_CHANGED, _scale));

			_paperContainer.horizontalScrollPosition=_paperContainer.maxHorizontalScrollPosition / 2;
			_paperContainer.validateDisplayList();

			return factor > 0;
		}

		public function fitHeight():Boolean {
			if (_displayContainer.numChildren == 0) {
				return false;
			}

			var _target:DisplayObject;
			_paperContainer.CenteringEnabled=(_paperContainer.height > 0);
			var factor:Number=getFitHeightFactor();
			_scale=factor;
			_tweencount=_displayContainer.numChildren;

			for (var i:int=0; i < _displayContainer.numChildren; i++) {
				_target=_displayContainer.getChildAt(i);
				_target.filters=null;
				Tweener.addTween(_target, {scaleX: factor, scaleY: factor, time: 0, transition: 'easenone', onComplete: onZoomComplete});
			}

			FitMode=FitModeEnum.FITHEIGHT;
			dispatchEvent(new ScaleChangedEvent(ScaleChangedEvent.SCALE_CHANGED, _scale));

			return factor > 0;
		}

		public function onZoomComplete():void {
			_tweencount--;

			if (_tweencount == 0) {

				if (_loadTimer != null)
					_loadTimer.start();

				repositionPapers();
			}

			if (_tweencount < numPagesLoaded - 2 || _tweencount == 0) {
				PaperVisible=true;
			}
		}

		private function reScaleComplete():void {
			_tweencount--;

			if (_tweencount == 0) {
				if (_displayContainer.numChildren > 0) {
					_paperContainer.verticalScrollPosition=0;

					if (_loadTimer != null)
						_loadTimer.start();

					repositionPapers();
				}

				if (_tweencount < numPagesLoaded - 2 || _tweencount == 0) {
					PaperVisible=true;
				}
			}
		}

		public function set Scale(s:String):void {
			var diff:Number=_scale - new Number(s);
			_scale=new Number(s);
		}

		override protected function createChildren():void {
			// Call the createChildren() method of the superclass.
			super.createChildren();
			this.styleName="viewerBackground";

			// Bind events
			//_loader.contentLoaderInfo.addEventListener(Event.COMPLETE, swfComplete);
			addEventListener(Event.RESIZE, sizeChanged, false, 0, true);
			systemManager.stage.addEventListener(KeyboardEvent.KEY_DOWN, keyboardHandler, false, 0, true);

			// Create a visible container for the swf
			_swfContainer=new Canvas();
			_swfContainer.visible=false;
			this.addElement(_swfContainer);

			// Create a timer to use for repainting
			_repaintTimer=new Timer(5, 0);
			_repaintTimer.addEventListener("timer", repaintHandler);

			createDisplayContainer();
		}

		private function onframeenter(event:Event):void {
			if (!_dupImageClicked) {
				return;
			}

			if (_docLoader != null && _docLoader.IsSplit)
				return;

			if (event.target.content != null) {
				if (event.target.parent is DupImage && event.target.content.currentFrame != (event.target.parent as DupImage).dupIndex && _dupImageClicked) {
					var np:int=event.target.content.currentFrame;
					event.target.content.gotoAndStop((event.target.parent as DupImage).dupIndex);
					gotoPage(np);
				}
			}
		}

		private function updComplete(event:Event):void {
			if (_scrollToPage > 0) {
				_paperContainer.verticalScrollPosition=_pageList[_scrollToPage - 1].y;
				//_paperContainer.horizontalScrollPosition = 0;
				_scrollToPage=0;
			}

			repaint();
			//repositionPapers();
		}

		private function repaintHandler(e:Event):void {
			if (_loadTimer != null) {
				_loadTimer.reset();
				_loadTimer.start();
			}

			repositionPapers();
			_repaintTimer.stop();
			_repaintTimer.delay=5;
		}

		private function loadtimer(e:Event):void {
			if (_loadTimer != null)
				_loadTimer.stop();

			repositionPapers();
		}

		private function bytesLoaded(event:Event):void {
			event.target.loader.loaded=true;

			var bFound:Boolean=false;

			// Split up approach
			if (_docLoader != null && _docLoader.IsSplit) {
				event.target.loader.loading=false;

				if (event.target.loader.content != null && event.target.loader.content is MovieClip) {
					event.target.loader.content.stop();
				}

				for (var i:int=0; i < _docLoader.LoaderList.length; i++) {
					if (_docLoader.LoaderList[i].loading) {
						bFound=true;
						break;
					}
				}

				_frameLoadCount=numPages;
				_bbusyloading=false;
				_displayContainer.visible=true;

				if (UsingExtViewMode)
					dispatchEvent(new PageLoadedEvent(PageLoadedEvent.PAGE_LOADED, event.target.loader.pageStartIndex));

				if (!bFound) {
					if (_fitPageOnLoad && ((_paperContainer.height / _libMC.height) > 0)) {
						FitMode=FitModeEnum.FITHEIGHT;
						_fitPageOnLoad=false;
						_scrollToPage=1;
						_pscale=_scale;
					}
					if (_fitWidthOnLoad && ((_paperContainer.width / _libMC.width) > 0)) {
						FitMode=FitModeEnum.FITWIDTH;
						_fitWidthOnLoad=false;
						_scrollToPage=1;
						_pscale=_scale;
					}
				}

				repaint();
			} else { // normal file approach
				if (event.target.loader.content != null) {
					event.target.loader.content.stop();
				}

				if (!ProgressiveLoading || (ProgressiveLoading && _libMC.framesLoaded == _libMC.totalFrames)) {
					for (var i:int=0; i < _docLoader.LoaderList.length; i++) {
						if (!_docLoader.LoaderList[i].loaded && !(_docLoader.LoaderList[i].parent is DupImage)) {
							_docLoader.LoaderList[i].unloadAndStop(true);
							_docLoader.LoaderList[i].loadBytes(_inputBytes, StreamUtil.getExecutionContext());
							bFound=true;
							break;
						}
					}
				}

				if (!bFound) {
					_bbusyloading=false;
					if (_fitPageOnLoad) {
						FitMode=FitModeEnum.FITHEIGHT;
						_fitPageOnLoad=false;
						_scrollToPage=1;
						_pscale=_scale;
					}
					if (_fitWidthOnLoad) {
						FitMode=FitModeEnum.FITWIDTH;
						_fitWidthOnLoad=false;
						_scrollToPage=1;
						_pscale=_scale;
					}
					_displayContainer.visible=true;

					if (_libMC.framesLoaded == _libMC.totalFrames && _frameLoadCount != _libMC.framesLoaded) {
						dispatchEvent(new DocumentLoadedEvent(DocumentLoadedEvent.DOCUMENT_LOADED, numPages));
						_frameLoadCount=_libMC.framesLoaded;
					}
				} else {
					_bbusyloading=false;
				}
			}
		}

		public function repositionPapers():void {
			if (_docLoader == null) {
				return;
			}
			if (_docLoader != null && _docLoader.LoaderList == null || numPagesLoaded == 0) {
				return;
			}

			{
				var loaderidx:int=0;
				var bFoundFirst:Boolean=false;
				var _thumb:Bitmap;
				var _thumbData:BitmapData;
				var uloaderidx:int=0;
				var p:int=-1;
				var pl:int=0;
				var pp:int=-1;

				for (var i:int=0; i < _pageList.length; i++) {
					if (ViewMode == ViewModeEnum.TILE) {
						if (!bFoundFirst && ((i) * (_pageList[i].height + 6)) >= _paperContainer.verticalScrollPosition) {
							bFoundFirst=true;
							p=i + 1;
						}
					} else if (ViewMode == ViewModeEnum.PORTRAIT) {
						if (!bFoundFirst) {
							var perH:int=0;
							if (_pageList.length > 1) {
								var nowpH:Number=1;
								var nowP:Number=0;

								while (_paperContainer.verticalScrollPosition > nowpH && nowP - 1 < _pageList.length) {
									nowP++;

									if (nowP < _pageList.length)
										nowpH+=_pageList[nowP].y - _pageList[nowP - 1].y;
									else if (nowP >= _pageList.length)
										nowpH+=_pageList[_pageList.length - 1].y - _pageList[_pageList.length - 2].y;
								}

								if (0 < nowP < 0.5)
									p=1;
								else if (nowP >= (_pageList.length - 0.5))
									p=_pageList.length;
								else {
									p=Math.round(nowP);
									if (_pageList.length > p - 1 && _paperContainer.verticalScrollPosition < _pageList[p - 1].y && p != _pageList.length) {
										p-=1;
									}

									if (_pageList.length >= 2 && p == _pageList.length - 1 && (_paperContainer.verticalScrollPosition - _pageList[p - 1].y) > (_pageList[_pageList.length - 1].y - _pageList[p - 1].y) / 2) {
										p=_pageList.length;
									}
								}
								bFoundFirst=true;
							} else {
								bFoundFirst=true;
								p=1;
							}
						}
					}

					if (p > numPages)
						return;

					if (UsingExtViewMode) {
						if (currPage != CurrExtViewMode.currentPage)
							dispatchEvent(new CurrentPageChangedEvent(CurrentPageChangedEvent.PAGE_CHANGED, CurrExtViewMode.currentPage, currPage));

						p=CurrExtViewMode.currentPage;
						currPage=p;
					}

					if (p > 0 && p != _currPage) {
						currPage=p;
						pp=currPage;
						dispatchEvent(new CurrentPageChangedEvent(CurrentPageChangedEvent.PAGE_CHANGED, p, pp));
					}

					if (_libMC != null && checkIsVisible(i)) {
						if (_pageList[i].numChildren < 4) {
							if (ViewMode == ViewModeEnum.PORTRAIT) {

								if (_docLoader.IsSplit)
									uloaderidx=finduloaderIdx(_pageList[i].dupIndex);

								if (!_docLoader.IsSplit || uloaderidx == -1)
									uloaderidx=(i == _pageList.length - 1 && loaderidx + 3 < _docLoader.LoaderList.length) ? loaderidx + 3 : (loaderidx < _docLoader.LoaderList.length) ? loaderidx : 0;

								if (!_docLoader.IsSplit) {
									if (!_bbusyloading && _docLoader.LoaderList != null && _docLoader.LoaderList.length > 0) {
										if (numPagesLoaded >= _pageList[i].dupIndex && _docLoader.LoaderList[uloaderidx] != null && _docLoader.LoaderList[uloaderidx].content == null || (_docLoader.LoaderList[uloaderidx].content != null && _docLoader.LoaderList[uloaderidx].content.framesLoaded < _pageList[i].dupIndex)) {
											_bbusyloading=true;
											_docLoader.LoaderList[uloaderidx].unloadAndStop(true);
											_docLoader.LoaderList[uloaderidx].loadBytes(_inputBytes, StreamUtil.getExecutionContext());
											flash.utils.setTimeout(repositionPapers, 200);
										}
									}

									if ((i < 2 || _pageList[i].numChildren == 0 || _pageList[i].loadedIndex != _pageList[i].dupIndex || (_pageList[i] != null && _docLoader.LoaderList[uloaderidx] != null && _docLoader.LoaderList[uloaderidx].content != null && _docLoader.LoaderList[uloaderidx].content.currentFrame != _pageList[i].dupIndex)) && _docLoader.LoaderList[uloaderidx] != null && _docLoader.LoaderList[uloaderidx].content != null) {
										if (numPagesLoaded >= _pageList[i].dupIndex) {
											_docLoader.LoaderList[uloaderidx].content.gotoAndStop(_pageList[i].dupIndex);
											_pageList[i].addChild(_docLoader.LoaderList[uloaderidx]);
											_pageList[i].loadedIndex=_pageList[i].dupIndex;
											/*
											if(_libMC.width*_scale>0&&_libMC.height*_scale>0){
											_libMC.gotoAndStop(_pageList[i].dupIndex);
											_thumbData = new BitmapData(_libMC.width*_scale, _libMC.height*_scale, false, 0xFFFFFF);
											_thumb = new Bitmap(_thumbData);
											_pageList[i].source = _thumb;
											_thumbData.draw(_libMC,new Matrix(_scale, 0, 0, _scale),null,null,null,true);
											} */
										}
									}

									if (_performSearchOnPageLoad && _pendingSearchPage == _pageList[i].dupIndex) {
										_performSearchOnPageLoad=false;

										if (JSONFile != null)
											searchTextByJSONFile(prevSearchText);
									}
								}

								if (_docLoader.IsSplit) {
									if (!_bbusyloading && _docLoader.LoaderList != null && _docLoader.LoaderList.length > 0) {
										if (_docLoader.LoaderList[uloaderidx] != null && _docLoader.LoaderList[uloaderidx].pageStartIndex != _pageList[i].dupIndex && !_loadTimer.running && !_docLoader.LoaderList[uloaderidx].loading) {
											dispatchEvent(new PageLoadingEvent(PageLoadingEvent.PAGE_LOADING, _pageList[i].dupIndex));
											try {
												_pageList[i].resetPage(_libMC.width, _libMC.height, _scale, true);
												_docLoader.LoaderList[uloaderidx].unloadAndStop(true);
												_docLoader.LoaderList[uloaderidx].loaded=false;
												_docLoader.LoaderList[uloaderidx].loading=true;
												_docLoader.LoaderList[uloaderidx].load(new URLRequest(getSwfFilePerPage(_swfFile, _pageList[i].dupIndex)), StreamUtil.getExecutionContext());
												_docLoader.LoaderList[uloaderidx].pageStartIndex=_pageList[i].dupIndex;
											} catch (err:IOErrorEvent) {
											}

											repaint();

										}
									}

									if ((_pageList[i].numChildren == 0 || (_pageList[i] != null && _docLoader.LoaderList[uloaderidx] != null && _docLoader.LoaderList[uloaderidx].content != null)) && _docLoader.LoaderList[uloaderidx] != null && _docLoader.LoaderList[uloaderidx].content != null && _docLoader.LoaderList[uloaderidx].loaded && _docLoader.LoaderList[uloaderidx].pageStartIndex == _pageList[i].dupIndex) {
										if (numPagesLoaded >= _pageList[i].dupIndex || true) {
											if (_docLoader.LoaderList[uloaderidx].parent != null)
												_docLoader.LoaderList[uloaderidx].parent.removeChild(_docLoader.LoaderList[uloaderidx]);

											_pageList[i].DrawBackground=true;
											_pageList[i].addChild(_docLoader.LoaderList[uloaderidx]);
											_pageList[i].invalidateSize();
											_pageList[i].loadedIndex=_pageList[i].dupIndex;

											if (!_pageList[i].loadedEventDispatched && _loadingInitalized && !UsingExtViewMode) {
												dispatchEvent(new PageLoadedEvent(PageLoadedEvent.PAGE_LOADED, i + 1));
												_pageList[i].loadedEventDispatched=true;
											}

											if ((_performSearchOnPageLoad && _pendingSearchPage == _pageList[i].dupIndex) || (SearchMatchAll && prevSearchText.length > 0)) {
												_performSearchOnPageLoad=false;

												if (SearchServiceUrl != null && SearchServiceUrl.length > 0)
													searchTextByService(prevSearchText);
												else if (JSONFile != null)
													searchTextByJSONFile(prevSearchText);

											}
										}
									}
								}

							} else if (ViewMode == ViewModeEnum.TILE && _pageList[i].source == null && (numPagesLoaded >= _pageList[i].dupIndex || _docLoader.IsSplit)) {
								if (!_docLoader.IsSplit) {
									_libMC.gotoAndStop(_pageList[i].dupIndex);
									_thumbData=new BitmapData(_libMC.width * _scale, _libMC.height * _scale, false, 0xFFFFFF);
									_thumb=new Bitmap(_thumbData);
									_pageList[i].source=_thumb;
									_thumbData.draw(_libMC.getDocument(), new Matrix(_scale, 0, 0, _scale), null, null, null, true);
								} else {

									if (!_bbusyloading && !_loadTimer.running && _docLoader.LoaderList != null && _docLoader.LoaderList.length > 0 && !_docLoader.LoaderList[uloaderidx].loading) {
										dispatchEvent(new PageLoadingEvent(PageLoadingEvent.PAGE_LOADING, _pageList[i].dupIndex));

										try {
											_pageList[i].resetPage(_libMC.width, _libMC.height, _scale, true);
											_docLoader.LoaderList[uloaderidx].loaded=false;
											_docLoader.LoaderList[uloaderidx].loading=true;
											_docLoader.LoaderList[uloaderidx].load(new URLRequest(getSwfFilePerPage(_swfFile, _pageList[i].dupIndex)), StreamUtil.getExecutionContext());
											_docLoader.LoaderList[uloaderidx].pageStartIndex=_pageList[i].dupIndex;
										} catch (err:IOErrorEvent) {
										}

										repaint();
									}

									if (_docLoader.LoaderList[uloaderidx].pageStartIndex == _pageList[i].dupIndex && _pageList[i].loadedIndex != _pageList[i].dupIndex && _docLoader.LoaderList[uloaderidx].content != null) {
										_thumbData=new BitmapData(_docLoader.LoaderList[uloaderidx].width * _scale, _docLoader.LoaderList[uloaderidx].height * _scale, false, 0xFFFFFF);
										_thumb=new Bitmap(_thumbData);
										_pageList[i].source=_thumb;
										_pageList[i].loadedIndex=_pageList[i].dupIndex;
										_thumbData.draw(_docLoader.LoaderList[uloaderidx], new Matrix(_scale, 0, 0, _scale), null, null, null, true);
									}
								}

								if (_pluginList != null && _viewMode != ViewModeEnum.TILE) {
									for (pl=0; pl < _pluginList.length; pl++) {
										_pluginList[pl].drawSelf(i, _thumbData, _scale);
									}
								}
							}
						}

						if (UsingExtViewMode) {
							CurrExtViewMode.renderPage(i);

							//if((_performSearchOnPageLoad && _pendingSearchPage == _pageList[i].dupIndex)||(SearchMatchAll && prevSearchText.length>0)){
							//	_performSearchOnPageLoad = false;
							//searchTextByService(prevSearchText)
							//}

							if (_selectionMarker != null)
								CurrExtViewMode.renderSelection(i, _selectionMarker);
						}

						if ((_viewMode == ViewModeEnum.PORTRAIT) && _selectionMarker != null) {
							if (i + 1 == searchPageIndex && _selectionMarker.parent != _pageList[i]) {
								_pageList[i].addChildAt(_selectionMarker, _pageList[i].numChildren);
							} else if (i + 1 == searchPageIndex && _selectionMarker.parent == _pageList[i]) {
								_pageList[i].setChildIndex(_selectionMarker, _pageList[i].numChildren - 1);
							}
						}

						if (_viewMode != ViewModeEnum.TILE && /* !UsingExtViewMode && */ _markList[i] != null) {

							// check for unitialized highlights
							if (_docLoader.IsSplit && _pageList[i].loadedIndex == _pageList[i].dupIndex) {
								for (var mi:int=0; mi < _markList[i].numChildren; mi++) {
									if (_markList[i].getChildAt(mi) is HighlightMarker) {
										if (!((_markList[i].getChildAt(mi) as HighlightMarker).initialized)) {
											var hmark:HighlightMarker=(_markList[i].getChildAt(mi) as HighlightMarker);

											snap=_pageList[i].textSnapshot;
											var tri:Array=snap.getTextRunInfo(hmark.pos, hmark.pos + hmark.len);
											hmark.initialized=tri.length > 0;

											if (hmark.initialized) {
												drawCurrentSelection(0x0095f7, _markList[i].getChildAt(mi), tri, false, 0.25);
											} else {
												if (snap.charCount > 0 && snap.getTextRunInfo(0, 1).length == 0) // obviously has not initialized yet
													repaint();
											}
										}
									}
								}
							}

							if (!UsingExtViewMode) {
								if (_markList[i].parent != _pageList[i]) {
									_pageList[i].addChildAt(_markList[i], _pageList[i].numChildren);
								} else {
									_pageList[i].setChildIndex(_markList[i], _pageList[i].numChildren - 1);
								}
							}
						}

						if (UsingExtViewMode && _markList[i] != null) {
							CurrExtViewMode.renderMark(_markList[i], i);
						}

						loaderidx++;
					} else {
						if (_pageList[i].numChildren > 0 || _pageList[i].source != null) {
							_pageList[i].source=null;

							if (_tweencount == 0)
								_pageList[i].resetPage(_libMC.width, _libMC.height, _scale);
							else
								_pageList[i].removeAllChildren();

							_pageList[i].loadedIndex=-1;
						}
					}

					// plugins should be rendered even if not visible to allow printing to work
					if (_viewMode != ViewModeEnum.TILE) {
						if (_pluginList != null) {
							for (pl=0; pl < _pluginList.length; pl++) {
								if (_currentExtViewMode != null && _currentExtViewMode.Name == "TwoPage") {
									if (checkIsVisible(i)) {
										_pluginList[pl].drawSelf(i, (i % 2 == 0) ? _pageList[0] : _pageList[1], _scale);
									}
								} else {
									_pluginList[pl].drawSelf(i, _pageList[i], _scale);
								}
							}
						}
					}
				}
			}
		}

		private function padString(_str:String, _n:Number, _pStr:String):String {
			var _rtn:String=_str;
			if ((_pStr == null) || (_pStr.length < 1)) {
				_pStr=" ";
			}

			if (_str.length < _n) {
				var _s:String="";
				for (var i:Number=0; i < (_n - _str.length); i++) {
					_s+=_pStr;
				}
				_rtn=_s + _str;
			}

			return _rtn;
		}

		private function finduloaderIdx(idx:int):int {
			for (var i:int=0; i < _docLoader.LoaderList.length; i++) {
				if (_docLoader.LoaderList[i].pageStartIndex == idx)
					return i;
			}
			for (var i:int=0; i < _docLoader.LoaderList.length; i++) {
				if (!checkIsVisible(_docLoader.LoaderList[i].pageStartIndex))
					return i;
			}
			return -1;
		}

		public function repaint():void {
			_repaintTimer.reset();
			_repaintTimer.start();
		}

		private function getVisibleMidPage():Number {
			var lowPageNum:int=-1;
			var highPageNum:int=-1;

			for (var i:int=0; i < _pageList.length; i++) {
				if (lowPageNum == -1 && checkIsVisible(i)) {
					lowPageNum=i;
				}

				if (lowPageNum != -1 && checkIsVisible(i)) {
					highPageNum=i;
				}
			}

			if (highPageNum - lowPageNum == 1 && _pageList.length < 3) {
				return lowPageNum;
			} else {
				var retval:Number=Math.round(lowPageNum + (highPageNum - lowPageNum) / 2);
				if (retval < 0) {
					retval=0;
				}
				if (retval > _pageList.length - 1) {
					retval=_pageList.length - 1;
				}

				return retval;
			}
		}

		private function checkIsVisible(pageIndex:int):Boolean {
			try {
				if (ViewMode == ViewModeEnum.TILE) {
					return _pageList[pageIndex].parent.y + _pageList[pageIndex].height >= _paperContainer.verticalScrollPosition && (_pageList[pageIndex].parent.y - _pageList[pageIndex].height) < (_paperContainer.verticalScrollPosition + _paperContainer.height);
				} else if (!UsingExtViewMode) {
					if (_paperContainer.height <= 0)
						return false;

					return (_pageList[pageIndex].y + (_pageList[pageIndex].getScaledHeight() + 6)) >= _paperContainer.verticalScrollPosition && (_pageList[pageIndex].y - (_pageList[pageIndex].getScaledHeight() + 6)) < (_paperContainer.verticalScrollPosition + _paperContainer.height);
				}

				if (UsingExtViewMode)
					return CurrExtViewMode.checkIsVisible(pageIndex);

			} catch (e:Error) {
				return false;
			}
			return false;
		}

		public function createDisplayContainer(immidiateVisibility:Boolean=true):void {
			CursorManagerUtils.init();

			if (_skinImgDo != null && _skinImgDo.parent == this) {
				removeElement(_skinImgDo);
				_skinImgDo.removeEventListener(MouseEvent.MOUSE_OVER, skinMouseOver);
				_skinImgDo.removeEventListener(MouseEvent.MOUSE_OUT, skinMouseOut);
				_skinImgDo.removeEventListener(MouseEvent.MOUSE_DOWN, skinMouseDown);
			}

			_skinImgDo=new Image();
			_skinImgDo.source=_skinImg;
			_skinImgDo.x=this.width - _skinImg.width - 27;
			_skinImgDo.y=this.height - _skinImg.height - 10;
			_skinImgDo.addEventListener(MouseEvent.MOUSE_OVER, skinMouseOver, false, 0, true);
			_skinImgDo.addEventListener(MouseEvent.MOUSE_OUT, skinMouseOut, false, 0, true);
			_skinImgDo.addEventListener(MouseEvent.MOUSE_DOWN, skinMouseDown, false, 0, true);
			_skinImgDo.buttonMode=true;
			addElement(_skinImgDo);

			// Add the swf to the invisible container.
			_swfContainer.removeAllChildren();
			var uic:UIComponent=new UIComponent();
			_swfContainer.addChild(uic);

			if (_docLoader != null && _docLoader.DocumentContainer != null)
				uic.addChild(_docLoader.DocumentContainer);

			if (_paperContainer != null && _paperContainer.parent == this) {
				removeElement(_paperContainer);
				_paperContainer.removeEventListener(FlexEvent.UPDATE_COMPLETE, updComplete);

				_paperContainer.removeEventListener(MouseEvent.MOUSE_WHEEL, wheelHandler);
			}

			_paperContainer=new ZoomCanvas();
			_paperContainer.visible=immidiateVisibility;
			_paperContainer.percentHeight=100;
			_paperContainer.percentWidth=100;
			_paperContainer.addEventListener(FlexEvent.UPDATE_COMPLETE, updComplete, false, 0, true);
			_paperContainer.x=(ViewMode == ViewModeEnum.PORTRAIT || ViewMode == ViewModeEnum.TILE) ? 2.5 : 0;
			_paperContainer.addEventListener(MouseEvent.MOUSE_WHEEL, wheelHandler, false, 0, true);
			_paperContainer.setStyle("horizontalGap", 1);
			_paperContainer.setStyle("verticalGap", 0);

			addElementAt(_paperContainer, getChildIndex(_skinImgDo) - 1);

			try {
				new flash.net.LocalConnection().connect('devaldiGCdummy');
				new flash.net.LocalConnection().connect('devaldiGCdummy');
			} catch (e:*) {
			}

			try {
				flash.system.System.gc();
			} catch (e:*) {
			}

			if (_paperContainer.numChildren > 0) {
				_paperContainer.removeAllChildren();
			}

			deleteDisplayContainer();

			if (_viewMode == ViewModeEnum.TILE) {
				_displayContainer=new FlowBox(_scale);
				_displayContainer.setStyle("horizontalAlign", "left");
				_paperContainer.horizontalScrollPolicy="off";
				_scale=0.243;
				_paperContainer.addChild(_displayContainer);
				_paperContainer.childrenDoDrag=true;
				_initialized=true;
			} else if (UsingExtViewMode) {
				_initialized=CurrExtViewMode.initComponent(this);
			} else {
				_displayContainer=new FlowVBox();
				_displayContainer.setStyle("horizontalAlign", "center");
				_paperContainer.addChild(_displayContainer);
				_paperContainer.childrenDoDrag=true;
				_initialized=true;
			}

			_displayContainer.verticalScrollPolicy="off";
			_displayContainer.horizontalScrollPolicy="off";
			_displayContainer.setStyle("verticalAlign", "center");

			if (!UsingExtViewMode)
				_displayContainer.percentHeight=100;

			_displayContainer.percentWidth=(ViewMode == ViewModeEnum.PORTRAIT || ViewMode == ViewModeEnum.CADPAGE) ? 96 : 100;
			_displayContainer.useHandCursor=true;
			_displayContainer.addEventListener(MouseEvent.ROLL_OVER, displayContainerrolloverHandler, false, 0, true);
			_displayContainer.addEventListener(MouseEvent.ROLL_OUT, displayContainerrolloutHandler, false, 0, true);
			_displayContainer.addEventListener(MouseEvent.MOUSE_DOWN, displayContainerMouseDownHandler, false, 0, true);
			_displayContainer.addEventListener(MouseEvent.MOUSE_UP, displayContainerMouseUpHandler, false, 0, true);
			_displayContainer.addEventListener(MouseEvent.DOUBLE_CLICK, displayContainerDoubleClickHandler, false, 0, true);
			//_displayContainer.mouseChildren = false;
			_displayContainer.doubleClickEnabled=true;
		}

		private function displayContainerrolloverHandler(event:MouseEvent):void {

			if (_viewMode == ViewModeEnum.PORTRAIT || (UsingExtViewMode && CurrExtViewMode.supportsTextSelect)) {
				if (TextSelectEnabled && CursorsEnabled) {
					Mouse.cursor=CursorManagerUtils.TEXT_SELECT_CURSOR;
				} else if (CursorsEnabled) {
					resetCursor();
				}
			}
		}

		private function displayContainerMouseUpHandler(event:MouseEvent):void {
			if (_viewMode == ViewModeEnum.PORTRAIT || (UsingExtViewMode && CurrExtViewMode.supportsTextSelect)) {

				if (CursorsEnabled)
					Mouse.cursor=flash.ui.MouseCursor.AUTO;

				if (TextSelectEnabled && CursorsEnabled) {
					Mouse.cursor=CursorManagerUtils.TEXT_SELECT_CURSOR;
				} else if (CursorsEnabled && !(event.target is IPaperControl) || (CursorsEnabled && event.target.parent != null && event.target.parent.parent != null && event.target.parent.parent is IPaperControl)) {
					resetCursor();
				}
			}
		}

		private function displayContainerDoubleClickHandler(event:MouseEvent):void {
			if (TextSelectEnabled) {
				return;
			}
			if (ViewMode == ViewModeEnum.PORTRAIT)
				FitMode=(FitMode == FitModeEnum.FITWIDTH) ? FitModeEnum.FITHEIGHT : FitModeEnum.FITWIDTH;

			if (UsingExtViewMode)
				CurrExtViewMode.handleDoubleClick(event);
		}

		private function displayContainerMouseDownHandler(event:MouseEvent):void {
			if (_viewMode == ViewModeEnum.PORTRAIT || (UsingExtViewMode && CurrExtViewMode.supportsTextSelect)) {

				if (CursorsEnabled)
					Mouse.cursor=flash.ui.MouseCursor.AUTO;

				if (TextSelectEnabled && CursorsEnabled) {
					Mouse.cursor=CursorManagerUtils.TEXT_SELECT_CURSOR;
				} else if (CursorsEnabled) {
					Mouse.cursor=CursorManagerUtils.GRABBING;
				}
			}

			if (UsingExtViewMode)
				CurrExtViewMode.handleMouseDown(event);
		}

		private function displayContainerrolloutHandler(event:Event):void {
			if (CursorsEnabled)
				Mouse.cursor=flash.ui.MouseCursor.AUTO;
		}

		private function wheelHandler(evt:MouseEvent):void {
			_paperContainer.removeEventListener(MouseEvent.MOUSE_WHEEL, wheelHandler);

			var t:Timer=new Timer(1, 1);
			t.addEventListener("timer", addMouseScrollListener, false, 0, true);
			t.start();

			_paperContainer.dispatchEvent(evt.clone());
		}

		private function addMouseScrollListener(e:Event):void {
			_paperContainer.addEventListener(MouseEvent.MOUSE_WHEEL, wheelHandler, false, 0, true);
		}

		private function keyboardHandler(event:KeyboardEvent):void {
			if (event.keyCode == Keyboard.DOWN) {
				_paperContainer.verticalScrollPosition=_paperContainer.verticalScrollPosition + 10;
			}
			if (event.keyCode == Keyboard.UP) {
				_paperContainer.verticalScrollPosition=_paperContainer.verticalScrollPosition - 10;
			}
			if (event.keyCode == Keyboard.PAGE_DOWN) {
				_paperContainer.verticalScrollPosition=_paperContainer.verticalScrollPosition + 300;
			}
			if (event.keyCode == Keyboard.PAGE_UP) {
				_paperContainer.verticalScrollPosition=_paperContainer.verticalScrollPosition - 300;
			}
			if (event.keyCode == Keyboard.HOME) {
				_paperContainer.verticalScrollPosition=0;
			}
			if (event.keyCode == Keyboard.END) {
				_paperContainer.verticalScrollPosition=_paperContainer.maxVerticalScrollPosition;
			}
		}

		private function sizeChanged(evt:Event):void {
			_skinImgDo.source=_skinImg;
			_skinImgDo.x=this.width - _skinImg.width - 27;
			_skinImgDo.y=this.height - _skinImg.height - 10;
		}

		private function skinMouseOver(evt:MouseEvent):void {
			_skinImgDo.addChild(_skinImgc);
		}

		private function skinMouseOut(evt:MouseEvent):void {
			if (_skinImgc.parent == _skinImgDo) {
				_skinImgDo.removeChild(_skinImgc);
			}
		}

		private function skinMouseDown(evt:MouseEvent):void {
			dispatchEvent(new Event("onLogoClicked"));
		}

		override protected function commitProperties():void {
			super.commitProperties();

			if (_swfFileChanged && _swfFile != null && _swfFile.length > 0) { // handler for when the Swf file has changed.
				_swfFileChanged=false;
				dispatchEvent(new Event("onDocumentLoading"));
				_loadingInitalized=true;
			}
		}

		private function resizeMc(mc:MovieClip, maxW:Number, maxH:Number=0, constrainProportions:Boolean=true):void {
			maxH=maxH == 0 ? maxW : maxH;
			mc.width=maxW;
			mc.height=maxH;
			if (constrainProportions) {
				mc.scaleX < mc.scaleY ? mc.scaleY=mc.scaleX : mc.scaleX=mc.scaleY;
			}
		}

		private function onLoadProgress(event:ProgressEvent):void {
			var e:ProgressEvent=new ProgressEvent("onLoadingProgress")
			e.bytesTotal=event.bytesTotal;
			e.bytesLoaded=event.bytesLoaded;
			dispatchEvent(e);
		}

		private function onDocumentLoadedErrorHandler(event:Event):void {
			dispatchEvent(event);
		}

		private function swfComplete(event:SwfLoadedEvent):void {
			if (!ProgressiveLoading) {
				try {
					if (event.swfObject.content != null && (event.swfObject.content is MovieClip || event.swfObject.content is Bitmap))
						_libMC=new SwfDocument(event.swfObject.content as DisplayObject);
					DupImage.paperSource=_libMC.getDocument();
				} catch (e:Error) {
					if (!_docLoader.Resigned) {
						_docLoader.signFileHeader(_docLoader.InputBytes);
						return;
					}
				}

				if ((_libMC == null || (event.swfObject != null && event.swfObject.content != null && event.swfObject.content is AVM1Movie)) && !_docLoader.Resigned) {
					_docLoader.signFileHeader(_docLoader.InputBytes);
					return;
				}

				_inputBytes=_docLoader.InputBytes;

				if (_libMC.height > 0 && _docLoader.LoaderList == null) {
					createLoaderList();
				}

				if (!_docLoader.IsSplit)
					numPages=_libMC.totalFrames;

				_swfLoaded=true
				_libMC.getDocument().cacheAsBitmap=true;
				_libMC.getDocument().opaqueBackground=0xFFFFFF;
				reCreateAllPages();
				_bbusyloading=false;
				repositionPapers();
					//dispatchEvent(new DocumentLoadedEvent(DocumentLoadedEvent.DOCUMENT_LOADED,numPages));
			} else {
				if (event.swfObject.content != null) {
					var mobj:Object=event.swfObject.content;
					var firstLoad:Boolean=false;

					if (mobj is AVM1Movie || _loaderptr != null) {
						_inputBytes=_docLoader.InputBytes;

						if (_loaderptr == null) {
							_docLoader.postProcessBytes(_inputBytes);
							_loaderptr=new Loader();
							_loaderptr.contentLoaderInfo.addEventListener(Event.COMPLETE, swfComplete, false, 0, true);
						}

						_docLoader.signFileHeader(_inputBytes, _loaderptr);
						_loaderptr.unloadAndStop(true);
						_loaderptr.loadBytes(_inputBytes, StreamUtil.getExecutionContext());
					}

					if (mobj is MovieClip) {
						_libMC=new SwfDocument(mobj as MovieClip);
						_libMC.getDocument().cacheAsBitmap=true;
						_libMC.getDocument().opaqueBackground=0xFFFFFF;

						if (_libMC.height > 0 && _docLoader.LoaderList == null) {
							createLoaderList();
						}
						DupImage.paperSource=_libMC.getDocument();

						if (!_docLoader.IsSplit)
							numPages=_libMC.totalFrames;

						firstLoad=_pageList == null || (_pageList.length == 0 && numPages > 0);

						if (_loaderptr == null) {
							_inputBytes=_docLoader.InputBytes;
						} else {
							_inputBytes=_docLoader.InputBytes;
						}

						if (_libMC.framesLoaded > 0)
							addInLoadedPages();

						flash.utils.setTimeout(function():void {
							try {
								var bDocLoaded:Boolean=(_libMC.framesLoaded == _libMC.totalFrames && _frameLoadCount != _libMC.framesLoaded);

								if (_libMC.framesLoaded > _frameLoadCount) {
									repositionPapers();
									if (_docLoader.LoaderList.length > 0 && _viewMode == ViewModeEnum.PORTRAIT) {
										_bbusyloading=true;
										_docLoader.LoaderList[_docLoader.LoaderList.length - 1].unloadAndStop(true);
										_docLoader.LoaderList[_docLoader.LoaderList.length - 1].loadBytes(_docLoader.InputBytes, StreamUtil.getExecutionContext());
									}
									_frameLoadCount=_libMC.framesLoaded;
								}

								if (bDocLoaded)
									dispatchEvent(new DocumentLoadedEvent(DocumentLoadedEvent.DOCUMENT_LOADED, numPages));
							} catch (e:*) {
							}
						}, 500);


						_bbusyloading=false;
						_swfLoaded=true
					}
				}
			}
		}

		private function deleteDisplayContainer():void {
			if (_displayContainer != null) {
				_displayContainer.removeAllChildren();
				_displayContainer.removeEventListener(MouseEvent.ROLL_OVER, displayContainerrolloverHandler);
				_displayContainer.removeEventListener(MouseEvent.ROLL_OUT, displayContainerrolloutHandler);
				_displayContainer.removeEventListener(MouseEvent.MOUSE_DOWN, displayContainerMouseDownHandler);
				_displayContainer.removeEventListener(MouseEvent.MOUSE_UP, displayContainerMouseUpHandler);
				_displayContainer.removeEventListener(MouseEvent.DOUBLE_CLICK, displayContainerDoubleClickHandler);
			}
		}

		private function deleteLoaderList():void {
			if (_docLoader == null) {
				return;
			}
			if (_docLoader.LoaderList != null) {
				for (var i:int=0; i < _docLoader.LoaderList.length; i++) {
					if (_docLoader.LoaderList[i].parent != null) {
						_docLoader.LoaderList[i].parent.removeChild(_docLoader.LoaderList[i]);
					}

					if (_docLoader.LoaderList[i].contentLoaderInfo != null) {
						_docLoader.LoaderList[i].contentLoaderInfo.removeEventListener(Event.COMPLETE, bytesLoaded);
						_docLoader.LoaderList[i].contentLoaderInfo.removeEventListener(IOErrorEvent.IO_ERROR, docLoaderIOErrorListener);
					}

					_docLoader.LoaderList[i].removeEventListener(Event.ENTER_FRAME, onframeenter);
					_docLoader.LoaderList[i].unloadAndStop(true);

					delete(_docLoader.LoaderList[i]);
					_docLoader.LoaderList[i]=null;
				}
			}

			_docLoader.LoaderList=null;
		}

		public function deleteSelectionMarker():void {
			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);

				_selectionMarker=null;
			}
		}

		private function deleteLibMC():void {
			if (_libMC != null) {
				if (_libMC.parent != null && _libMC.parent is Loader) {
					(_libMC.parent as Loader).unloadAndStop(true);
				}

				_libMC=null;
			}
		}

		private function deleteLoaderPtr():void {
			if (_loaderptr != null) {
				if (_loaderptr.parent != null) {
					_loaderptr.removeChild(_loaderptr);
				}

				if (_loaderptr.contentLoaderInfo != null) {
					_loaderptr.contentLoaderInfo.removeEventListener(Event.COMPLETE, swfComplete);
				}

				_loaderptr.unloadAndStop(true);
				_loaderptr=null;
			}
		}

		private function clearPlugins():void {
			if (_pluginList == null) {
				return;
			}

			for (var pl:int=0; pl < _pluginList.length; pl++) {
				_pluginList[pl].clear();
			}
		}

		private function deletePageList():void {
			if (_pageList != null) {
				for (var pl:int=0; pl < _pageList.length; pl++) {

					_pageList[pl].removeEventListener(MouseEvent.MOUSE_OVER, dupImageMoverHandler);
					_pageList[pl].removeEventListener(MouseEvent.MOUSE_OUT, dupImageMoutHandler);
					_pageList[pl].removeEventListener(MouseEvent.CLICK, dupImageClickHandler);
					_pageList[pl].removeEventListener(MouseEvent.MOUSE_DOWN, textSelectorMouseDownHandler);

					if (_pageList[pl].parent != null) {
						_pageList[pl].removeAllChildren();
						_pageList[pl].source=null;
						_pageList[pl].parent.removeChild(_pageList[pl]);
					}

					delete(_pageList[pl]);
					_pageList[pl]=null;
				}
			}

			DupImage.paperSource=null;

			_pageList=null;
		}

		private function deleteFLoader():void {
			if (_docLoader != null) {
				_docLoader.stream.removeEventListener(ProgressEvent.PROGRESS, onLoadProgress);
				_docLoader.resetURLStream();
			}

		/*if(_loader!=null){
		_loader.contentLoaderInfo.removeEventListener(Event.COMPLETE, swfComplete);
		_loader.unloadAndStop(true);
		_loader = null;
		}*/

			//_docLoader = null;

		/*_loader = new Loader();
		if(!_loader.contentLoaderInfo.hasEventListener(Event.COMPLETE))
		_loader.contentLoaderInfo.addEventListener(Event.COMPLETE, swfComplete,false,0,true);*/
		}

		public function addInLoadedPages(recreate:Boolean=false):void {
			if (_libMC == null) {
				return;
			}

			if (recreate) {
				_displayContainer.removeAllChildren();
				deletePageList();
			}

			if (_pageList == null || (_pageList != null && _pageList.length != numPages)) {
				_pageList=new Array(numPages);
				if (_markList == null) {
					_markList=new Array(numPages);
				}

				_displayContainer.visible=false;
				_libMC.stop();

				var w:Number=0;
				var h:Number=0;
				_libMC.gotoAndStop(1);
				w=_libMC.width;
				h=_libMC.height;

				for (var i:int=0; i < numPages; i++) {
					_libMC.gotoAndStop(i + 1);
					createPaper(i + 1, (_libMC.width > 0) ? _libMC.width : w, (_libMC.height > 0) ? _libMC.height : h);
				}

				addPages();

				if (_pluginList != null) {
					for (var p:int=0; p < _pluginList.length; p++) {
						_pluginList[p].init();
					}
				}

				//if(_fitWidthOnLoad){_scale = getFitWidthFactor();}
				//if(_fitPageOnLoad){_scale = getFitHeightFactor();}

				if (_docLoader.LoaderList.length > 0 && UsingExtViewMode) {
					CurrExtViewMode.initOnLoading();
				}
			}

			flash.utils.setTimeout(repositionPapers, 500);
		}

		public function reCreateAllPages():void {
			if (!_swfLoaded || _libMC == null) {
				return;
			}

			_displayContainer.visible=false;
			_displayContainer.removeAllChildren();

			deletePageList();
			_pageList=new Array(numPages);
			if (_markList == null) {
				_markList=new Array(numPages);
			}

			if (_pluginList != null) {
				for (var p:int=0; p < _pluginList.length; p++) {
					_pluginList[p].init();
				}
			}

			_libMC.stop();

			var w:Number=0;
			var h:Number=0;
			_libMC.gotoAndStop(1);
			w=_libMC.width;
			h=_libMC.height;

			for (var i:int=0; i < numPages; i++) {
				_libMC.gotoAndStop(i + 1);
				createPaper(i + 1, (_libMC.width > 0) ? _libMC.width : w, (_libMC.height > 0) ? _libMC.height : h);
			}

			addPages();

			// kick off the first page to load
			if (!_docLoader.IsSplit) {
				if (_docLoader.LoaderList.length > 0) {
					_bbusyloading=true;
					_docLoader.LoaderList[0].unloadAndStop(true);
					_docLoader.LoaderList[0].loadBytes(_docLoader.InputBytes, StreamUtil.getExecutionContext());
				}
			} else {
				if (_docLoader.LoaderList.length > 0 && (_viewMode == ViewModeEnum.PORTRAIT) && _docLoader.InputBytes != null) {
					_bbusyloading=true;
					_docLoader.LoaderList[0].pageStartIndex=1;
					_docLoader.LoaderList[0].loadBytes(_docLoader.InputBytes, StreamUtil.getExecutionContext());
				}
			}

			if (_docLoader.LoaderList.length > 0 && UsingExtViewMode) {
				CurrExtViewMode.initOnLoading();
			}
		}

		private function createLoaderList():void {
			DupLoader.parentLoader=_docLoader;
			_docLoader.LoaderList=new Array(Math.round(getCalculatedHeight(_paperContainer) / (_libMC.height * _minZoomSize)) + (_docLoader.IsSplit) ? 5 : 1);

			if (UsingExtViewMode && CurrExtViewMode.loaderListLength > _docLoader.LoaderList.length)
				_docLoader.LoaderList=new Array(CurrExtViewMode.loaderListLength);

			{
				for (var li:int=0; li < _docLoader.LoaderList.length; li++) {
					_docLoader.LoaderList[li]=new DupLoader();
					_docLoader.LoaderList[li].contentLoaderInfo.addEventListener(Event.COMPLETE, bytesLoaded, false, 0, true);
					_docLoader.LoaderList[li].contentLoaderInfo.addEventListener(IOErrorEvent.IO_ERROR, docLoaderIOErrorListener);
					_docLoader.LoaderList[li].addEventListener(Event.ENTER_FRAME, onframeenter, false, 0, true);

					if (DupLoader.parentLoader.ShouldPreStream) {
						_docLoader.LoaderList[li].resetURLStream();
					}
				}
			}
		}

		public function resetLoadCount():void {
			_frameLoadCount=0;
		}

		private function docLoaderIOErrorListener(e:IOErrorEvent):void {
			if (_docLoader != null && _docLoader.IsSplit) {
				dispatchEvent(new ErrorLoadingPageEvent(ErrorLoadingPageEvent.ERROR_LOADING_PAGE, -1 /*e.target.pageStartIndex*/));
			}
		}

		private function getCalculatedHeight(obj:DisplayObject):Number {
			var pHeight:Number=0;
			var oPercHeight:Number=0;

			pHeight=obj.height;
			if (pHeight > 0) {
				return pHeight;
			}
			if ((obj as Container).percentHeight > 0) {
				oPercHeight=(obj as Container).percentHeight;
			}

			while (obj.parent != null) {
				if (obj.parent.height > 0) {
					pHeight=obj.parent.height * (oPercHeight / 100);
					break;
				}
				obj=obj.parent;
			}

			return pHeight;
		}

		public var snap:TextSnapshot;
		private var searchIndex:int=-1;
		private var searchPageIndex:int=-1;
		private var _selectionMarker:ShapeMarker;
		public var prevSearchText:String="";
		private var prevYsave:Number=-1;
		private var _markList:Array;
		private var prevSearchIndexList:Array;

		public function get MarkList():Array {
			return _markList;
		}

		public function get SelectionMarker():ShapeMarker {
			return _selectionMarker;
		}

		public function set SelectionMarker(sm:ShapeMarker):void {
			_selectionMarker=sm;
		}

		public function get SearchPageIndex():int {
			return searchPageIndex;
		}

		public function set SearchPageIndex(s:int):void {
			searchPageIndex=s;
		}

		private function getJsonPageIndex(searchIndex:int):int {
			var itemIndex:int=-1;

			if (_jsonPageData != null && JSONFile.indexOf("{page}") > -1) {
				var passed:Boolean=false;
				for (var si:int=0; si < _jsonPageData.length; si++) {
					if (_jsonPageData[si].number == searchIndex) {
						itemIndex=si;
					}
				}
			} else if (JSONFile.indexOf("{page}") == -1) {
				itemIndex=searchIndex - 1;
			}

			return itemIndex;
		}

		var _jsonPageData:Object=null;
		var _jsonPageDataFault:String="";

		public function searchTextByJSONFile(text:String):void {
			if (prevSearchText != text) {
				searchPageIndex=-1;
				prevSearchText=text;
				searchIndex=-1;
			}

			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);
				_selectionMarker=null;
			}

			var outOfRange:Boolean=getJsonPageIndex(searchPageIndex) == -1;

			if (_jsonPageData == null || outOfRange) {
				dispatchEvent(new Event("onDownloadingSearchResult"));

				flash.utils.setTimeout(function():void {
					var serve:HTTPService=new HTTPService();
					serve.url=(JSONFile.indexOf("{page}") > -1) ? JSONFile.replace("{page}", (searchPageIndex) + (10 - (searchPageIndex) % 10)) : JSONFile;
					serve.method="GET";
					serve.resultFormat="text";
					serve.addEventListener("result", function searchByJSONResult(evt:ResultEvent):void {
						_jsonPageData=com.adobe.serialization.json.JSON.decode(evt.result.toString());
						dispatchEvent(new Event("onDownloadSearchResultCompleted"));
						performSearchTextByJSON(text);
					});
					serve.addEventListener(FaultEvent.FAULT, function searchByJSONFault(evt:FaultEvent):void {
						_jsonPageDataFault=JSONFile;
						dispatchEvent(new Event("onDownloadSearchResultCompleted"));
						var text:String=prevSearchText;
						prevSearchText="";
						searchText(text);

						if (_docLoader.IsSplit && SearchServiceUrl == null) {
							dispatchEvent(new Event("onNoMoreSearchResults"));
						}
					});
					serve.send();
				}, 100);

			} else {
				performSearchTextByJSON(text);
			}
		}

		private function getCurrentJSONSearchBlob(dataIndex:int):String {
			var searchBlob:String="";

			if (_jsonPageData[dataIndex].hasOwnProperty("text")) {
				for (var spt:int=0; spt < _jsonPageData[dataIndex].text.length; spt++) {
					searchBlob+=(_jsonPageData[dataIndex].text[spt][5]).toString();
				}
			}

			return searchBlob;
		}

		private function performSearchTextByJSON(text:String):void {
			if (searchPageIndex == -1) {
				searchPageIndex=currPage;
			}

			var dataIndex:int=getJsonPageIndex(searchPageIndex);

			if (_jsonPageData[dataIndex] != null) {
				if ((!UsingExtViewMode && searchPageIndex != currPage) && (JSONFile.indexOf("{page}") == -1)) {
					_performSearchOnPageLoad=true;
					_pendingSearchPage=searchPageIndex;
					gotoPage(searchPageIndex);
				} else {
					if (!UsingExtViewMode)
						snap=_pageList[searchPageIndex - 1].textSnapshot;
					else {
						CurrExtViewMode.setTextSelectMode(searchPageIndex - 1);
						snap=CurrExtViewMode.getPageTextSnapshot(searchPageIndex - 1);
						if (snap == null) {
							snap=_pageList[searchPageIndex - 1].textSnapshot;
						}
					}

					//searchIndex = snap.findText((searchIndex==-1?0:searchIndex),adjustSearchTerm(text),false);

					var flashBlob:String=snap.getText(0, snap.charCount);
					var searchBlob:String=getCurrentJSONSearchBlob(dataIndex);
					flashBlob=TextMapUtil.checkUnicodeIntegrityByTextmap(flashBlob, searchBlob);

					searchIndex=flashBlob.toLowerCase().indexOf(adjustSearchTerm(text).toLowerCase(), (searchIndex == -1 ? 0 : searchIndex));

					var tri:Array;

					if (searchIndex > 0) { // found a new match
						_selectionMarker=new ShapeMarker();
						_selectionMarker.isSearchMarker=true;
						_selectionMarker.graphics.beginFill(SearchMatchColor, 0.3);

						tri=snap.getTextRunInfo(searchIndex, searchIndex + text.length - 1);
						if (tri.length > 0) {
							prevYsave=tri[0].matrix_ty;
							drawCurrentSelection(SearchMatchColor, _selectionMarker, tri);
						}

						if (prevYsave > 0) {
							_selectionMarker.graphics.endFill();
							_adjGotoPage=(ViewMode == ViewModeEnum.PORTRAIT) ? (prevYsave) * _scale - 50 : 0;
							gotoPage(searchPageIndex);
						}

						searchIndex=searchIndex + text.length;
					} else {
						if (searchPageIndex + 1 <= numPages) {
							var searchBlob:String="";
							var startIndex:int=(JSONFile.indexOf("{page}") > -1) ? (searchPageIndex % 10) + 1 : searchPageIndex;

							for (var sp:int=startIndex; sp < _jsonPageData.length; sp++) {
								searchBlob="";

								if (_jsonPageData[sp].hasOwnProperty("text")) {
									for (var spt:int=0; spt < _jsonPageData[sp].text.length; spt++) {
										searchBlob+=(_jsonPageData[sp].text[spt]).toString();
									}
								}

								if (searchBlob.toLowerCase().indexOf(text.toLowerCase()) >= 0) {
									searchPageIndex=_jsonPageData[sp].number;
									searchIndex=-1;
									performSearchTextByJSON(prevSearchText);
									return;
								}
							}

							if (JSONFile.indexOf("{page}") > -1) {
								searchPageIndex=searchPageIndex + 1;
								searchIndex=-1;
								searchTextByJSONFile(prevSearchText);
								return;
							} else {
								dispatchEvent(new Event("onNoMoreSearchResults"));
								searchPageIndex=1;
							}
						} else {
							dispatchEvent(new Event("onNoMoreSearchResults"));
							searchPageIndex=1;
						}
					}
				}
			}
		}


		private var _searchExtracts:Array;

		public function searchTextByService(text:String):void {
			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);
				_selectionMarker=null;
			}

			if (prevSearchText != text) {
				_searchExtracts=new Array(numPages);
				searchPageIndex=-1;
				prevSearchText=text;
				searchIndex=-1;
				prevSearchIndexList=new Array();
			}

			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);
			}

			if (searchPageIndex == -1) {
				searchPageIndex=currPage;
			}

			if (_searchExtracts[searchPageIndex - 1] == null && searchPageIndex != currPage) {
				var serve:HTTPService=new HTTPService();
				var url=SearchServiceUrl;
				url=TextMapUtil.StringReplaceAll(url, "[page]", searchPageIndex.toString())
				url=TextMapUtil.StringReplaceAll(url, "[searchterm]", text)
				url=encodeURI(url);

				serve.method="GET";
				serve.url=url;
				serve.resultFormat="text";
				serve.addEventListener("result", searchByServiceResult);
				serve.addEventListener(FaultEvent.FAULT, searchByServiceFault);
				serve.send();
			} else { // perform actual search
				if ((_searchExtracts[searchPageIndex - 1] != null && _searchExtracts[searchPageIndex - 1].length > 0 && Number(_searchExtracts[searchPageIndex - 1]) >= 0) || searchPageIndex == currPage) {
					if ((!UsingExtViewMode && searchPageIndex != currPage) || (ViewMode != "SinglePage" && ViewMode != "TwoPage" && UsingExtViewMode && CurrExtViewMode.translatePageNumber(searchPageIndex) != currPage)) { // last criteria not nessecary for singlepage
						_performSearchOnPageLoad=true;
						_pendingSearchPage=searchPageIndex;
						gotoPage(searchPageIndex);
					} else {
						if (!UsingExtViewMode)
							snap=_pageList[searchPageIndex - 1].textSnapshot;
						else {
							CurrExtViewMode.setTextSelectMode(searchPageIndex - 1);
							snap=CurrExtViewMode.getPageTextSnapshot(searchPageIndex - 1);
							/*
							var loaderIdx:int = finduloaderIdx(searchPageIndex);
							var dl:DupLoader = DocLoader.LoaderList[loaderIdx];
							if(dl!=null && dl.contentLoaderInfo!=null && dl.contentLoaderInfo.content is MovieClip){
							snap = (dl.contentLoaderInfo.content as MovieClip).textSnapshot;
							}*/
						}

						searchIndex=(snap != null) ? snap.findText((searchIndex == -1 ? 0 : searchIndex), adjustSearchTerm(text), false) : -1;
						var tri:Array;

						if (searchIndex > 0) { // found a new match
							_selectionMarker=new ShapeMarker();
							_selectionMarker.isSearchMarker=true;
							_selectionMarker.graphics.beginFill(SearchMatchColor, 0.3);

							tri=snap.getTextRunInfo(searchIndex, searchIndex + text.length - 1);
							if (tri.length > 0) {
								prevYsave=tri[0].matrix_ty;
								drawCurrentSelection(SearchMatchColor, _selectionMarker, tri);
							}

							if (prevYsave > 0) {
								_selectionMarker.graphics.endFill();
								_adjGotoPage=(ViewMode == ViewModeEnum.PORTRAIT) ? (prevYsave) * _scale - 50 : 0;
								gotoPage(searchPageIndex);
							}

							searchIndex=searchIndex + text.length;
						} else {
							if (searchPageIndex + 1 <= numPages) {
								searchPageIndex++;
								searchIndex=-1;

								_performSearchOnPageLoad=true;
								_pendingSearchPage=searchPageIndex;

								searchTextByService(prevSearchText);
							} else {
								dispatchEvent(new Event("onNoMoreSearchResults"));
								searchPageIndex=1;
							}
						}
					}
				} else {
					if (searchPageIndex + 1 <= numPages) {
						searchPageIndex++;
						searchIndex=-1;

						searchTextByService(prevSearchText);
					} else {
						dispatchEvent(new Event("onNoMoreSearchResults"));
						searchPageIndex=1;
					}
				}
			}
		}

		private function searchByServiceResult(evt:ResultEvent):void {
			var jsonRes:Object=com.adobe.serialization.json.JSON.decode(evt.result.toString());
			if (parseInt(jsonRes[0].page) > -1) {
				searchPageIndex=parseInt(jsonRes[0].page);
				_searchExtracts[searchPageIndex - 1]=jsonRes[0].position.toString();
				searchTextByService(prevSearchText);
			} else {
				_searchExtracts[searchPageIndex - 1]=null;
				dispatchEvent(new Event("onNoMoreSearchResults"));
				searchPageIndex=1;
			}
		}

		private function searchByServiceFault(evt:FaultEvent):void {
			dispatchEvent(new Event("onNoMoreSearchResults"));
		}

		private var _searchMatchColor:uint=0x0095f7;

		public function get SearchMatchColor():uint {
			return _searchMatchColor;
		}

		public function set SearchMatchColor(c:uint):void {
			_searchMatchColor=c;
		}

		public function get SearchAbstracts():Array {
			return _searchAbstracts;
		}

		public function searchText(text:String, clearmarklist:Boolean=true):void {
			if (text == null) {
				return;
			}

			if (text.length == 0) {
				return;
			}
			text=text.toLowerCase();

			if (_docLoader.IsSplit && SearchServiceUrl != null && SearchServiceUrl.length > 0)
				return searchTextByService(text);

			if (JSONFile != null && _jsonPageDataFault != JSONFile && !SearchMatchAll) {
				return searchTextByJSONFile(text);
			}

			var tri:Array;

			if (prevSearchText != text) {

				if (UsingExtViewMode) {
					CurrExtViewMode.clearSearch();
				}

				searchIndex=-1;
				prevSearchIndexList=new Array();
				prevSearchText=text;

				if (SearchMatchAll) {
					var spi:int=1;
					var si:int=-1;

					if (clearmarklist) {
						if (_markList != null) {
							for (var i:int=0; i < _markList.length; i++) {
								if (_markList[i] != null && _markList[i].parent != null) {
									_markList[i].parent.removeChild(_markList[i]);
								}
							}
						}

						_markList=new Array(numPages);
					}

					if (text.indexOf("|") > 0) {
						var phrases:Array=text.split("|");
						for (var sf:int=0; sf < phrases.length; sf++) {
							searchText(phrases[sf], false);
						}

						return;
					}

					if (ProvideSearchAbstracts) {
						_searchAbstracts=new Array();
					}

					while ((spi - 1) < _libMC.framesLoaded && ((_searchAbstracts != null && _searchAbstracts.length < 500) || _searchAbstracts == null)) { // limit the amount of searchabstracts returned to avoid timeout
						_libMC.gotoAndStop(spi);

						snap=_libMC.textSnapshot;
						var oldsi:int=si;
						si=snap.findText((si == -1 ? 0 : si), adjustSearchTerm(text), false);
						if (si == -1) {
							si=TextMapUtil.checkUnicodeIntegrity(snap.getText(0, snap.charCount), null, _libMC).toLowerCase().indexOf(text, (oldsi == -1 ? 0 : oldsi));
						}
						//si = searchString(snap.getText(0,snap.charCount),text,si);

						if (si > 0) {
							var sm:SearchShapeMarker=new SearchShapeMarker();
							sm.isSearchMarker=true;
							sm.PageIndex=spi;

							tri=snap.getTextRunInfo(si, si + text.length - 1);
							drawCurrentSelection(SearchMatchColor, sm, tri, false, 0.25);

							if (ProvideSearchAbstracts) {
								_searchAbstracts[_searchAbstracts.length]=new Object();
								_searchAbstracts[_searchAbstracts.length - 1].pageNumber=sm.PageIndex;
								_searchAbstracts[_searchAbstracts.length - 1].SearchMarker=sm;
								_searchAbstracts[_searchAbstracts.length - 1].text=snap.getText((si - 50 > 0) ? si - 50 : 0, (si + 75 < snap.charCount) ? si + 75 : snap.charCount);
								_searchAbstracts[_searchAbstracts.length - 1].text=TextMapUtil.checkUnicodeIntegrity(_searchAbstracts[_searchAbstracts.length - 1].text, null, _libMC)
							}

							if (_markList[spi - 1] == null) {
								_markList[spi - 1]=new UIComponent();
							}

							_markList[spi - 1].addChild(sm);
						}

						if (si == -1)
							spi++;
						else
							si++;
					}

					if (_searchAbstracts != null && _searchAbstracts.length >= 500) {
						dispatchEvent(new Event("onMaxSearchResultsExceeded"));
					}
				}
			}

			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);
			}

			// start searching from the current page
			if (searchPageIndex == -1) {
				searchPageIndex=currPage;
			} else {
				var sio:Object=new Object();
				sio.searchIndex=searchIndex;
				sio.searchPageIndex=searchPageIndex;

				prevSearchIndexList.push(sio);
				searchIndex=searchIndex + text.length;
			}

			_libMC.gotoAndStop(searchPageIndex);

			while ((searchPageIndex - 1) < numPagesLoaded) {
				snap=_libMC.textSnapshot;
				var prevSearchIndex:int=searchIndex;
				searchIndex=snap.findText((searchIndex == -1 ? 0 : searchIndex), adjustSearchTerm(text), false);
				if (searchIndex == -1) {
					searchIndex=TextMapUtil.checkUnicodeIntegrity(snap.getText(0, snap.charCount), text, _libMC).toLowerCase().indexOf(text, (prevSearchIndex == -1 ? 0 : prevSearchIndex));
				}
				//searchIndex = TextMapUtil.checkUnicodeIntegrity(snap.getText(0,snap.charCount),text,_libMC).toLowerCase().indexOf(text,(searchIndex==-1?0:searchIndex));
				//searchIndex = snap.getText(0,snap.charCount).toLowerCase().indexOf(text,(searchIndex==-1?0:searchIndex));
				//searchIndex = searchString(snap.getText(0,snap.charCount),text,searchIndex);

				if (searchIndex > 0) { // found a new match
					_selectionMarker=new ShapeMarker();
					_selectionMarker.isSearchMarker=true;
					_selectionMarker.graphics.beginFill(SearchMatchColor, 0.3);

					tri=snap.getTextRunInfo(searchIndex, searchIndex + text.length - 1);
					if (tri.length > 0) {
						prevYsave=tri[0].matrix_ty;
						drawCurrentSelection(SearchMatchColor, _selectionMarker, tri);
					}

					if (prevYsave > 0) {
						_selectionMarker.graphics.endFill();
						_adjGotoPage=(ViewMode == ViewModeEnum.PORTRAIT) ? (prevYsave) * _scale - 50 : 0;
						gotoPage(searchPageIndex);
						break;
					}

				}

				searchPageIndex++;
				searchIndex=-1;
				_libMC.gotoAndStop(searchPageIndex);
			}

			if (searchIndex == -1) { // searched the end of the doc.
				dispatchEvent(new Event("onNoMoreSearchResults"));
				searchPageIndex=1;
			}
		}

		public function nextSearchMatch(s:String):void {
			if (s != prevSearchText) {
				searchText(s);
			} else {
				searchIndex=searchIndex + prevSearchText.length;
				searchText(prevSearchText);
			}
		}

		public function prevSearchMatch():void {
			if (prevSearchText != null && prevSearchText.length > 0) {
				prevSearchIndexList.pop();

				var sio:Object=prevSearchIndexList.pop();
				if (sio != null) {
					searchIndex=sio.searchIndex - 1;
					searchPageIndex=sio.searchPageIndex;
					searchText(prevSearchText);
				} else {
					searchIndex=0;
					searchPageIndex=1;
					searchText(prevSearchText);
				}
			}
		}

		private function adjustSearchTerm(searchtxt:String):String {
			if (ResourceManager.getInstance().localeChain[0] == 'he_IL') {
				searchtxt=reverseString(searchtxt);
			}

			return searchtxt;
		}

		private function reverseString(string:String):String {
			var reversed:String=new String();
			for (var i:int=string.length - 1; i >= 0; i--)
				reversed+=string.charAt(i);
			return reversed;
		}

		public function highlight(url:String):void {
			var serve:HTTPService=new HTTPService();
			serve.method="GET";
			serve.url=url;
			serve.resultFormat="e4x"
			serve.addEventListener("result", highlightResult);
			serve.send();
		}

		private function highlightResult(evt:ResultEvent):void {
			try {

				for (var i:int=0; i < _markList.length; i++) {
					if (_markList[i] != null && _markList[i].parent != null) {
						_markList[i].parent.removeChild(_markList[i]);
					}
				}

				_markList=new Array(numPages);

				var highlightXML:XML=new XML(evt.result);
				var pg:Number=0;
				var pos:Number=-1;
				var len:Number=-1;
				var tri:Array;
				var color:uint=0x0095f7;
				var text:String;
				color=uint("0x" + String(highlightXML.Body.@color).substring(1, String(highlightXML.Body.@color).length));

				for each (var item:XML in highlightXML.Body.Highlight.loc) {
					snap=null;
					pg=Number(item.@pg);
					pos=Number(item.@pos);
					len=Number(item.@len);

					if (!_docLoader.IsSplit) {
						_libMC.gotoAndStop(pg + 1);
						snap=_libMC.textSnapshot;
					} else {
						if (_pageList[pg] != null && _pageList[pg].loadedIndex == pg)
							snap=_pageList[pg].textSnapshot;
					}

					var sm:HighlightMarker=new HighlightMarker();
					sm.isSearchMarker=false;
					sm.PageIndex=pg + 1;
					sm.pos=pos;
					sm.len=len;

					text=(snap != null) ? snap.getText(0, pos, false) : "";

					for (var ci:int=0; ci < text.length; ci++) {
						if (text.charCodeAt(ci) > 10000) {
							pos=pos - 1;
						}
					}

					tri=(snap != null) ? snap.getTextRunInfo(pos, pos + len) : null;
					sm.initialized=tri != null && tri.length > 0;

					if (sm.initialized)
						drawCurrentSelection(color, sm, tri, false, 0.25);

					if (_markList[pg] == null) {
						_markList[pg]=new UIComponent();
					}

					_markList[pg].addChild(sm);
				}

				repositionPapers();

			} catch (e:*) {
			}
		}

		public function createPaper(index:int, w:Number, h:Number):DupImage {
			var di:DupImage=new DupImage();
			di.scaleX=di.scaleY=_scale;
			di.dupIndex=index;
			di.width=w;
			di.height=h;
			di.init();
			di.DrawBackground=!DocLoader.IsSplit;
			di.NeedsFitting=DocLoader.IsSplit;
			di.RoleModelHeight=_libMC.height;
			di.RoleModelWidth=_libMC.width;
			//di.mouseChildren = false;
			di.addEventListener(MouseEvent.MOUSE_OVER, dupImageMoverHandler, false, 0, true);
			di.addEventListener(MouseEvent.MOUSE_OUT, dupImageMoutHandler, false, 0, true);
			di.addEventListener(MouseEvent.CLICK, dupImageClickHandler, false, 0, true);
			di.addEventListener(MouseEvent.MOUSE_DOWN, textSelectorMouseDownHandler, false, 0, true);

			if (_pluginList != null)
				for (var pl:int=0; pl < _pluginList.length; pl++)
					_pluginList[pl].bindPaperEventHandler(di);

			_pageList[index - 1]=di;
			_pageList[index - 1].resetPage(w, h, _scale);
			return di;
		}

		private function textSelectorDoubleClickHandler(event:MouseEvent):void {
			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);
				_selectionMarker=null;
			}

			try {
				if (event.target is ITextSelectableDisplayObject) {
					(event.target as ITextSelectableDisplayObject).setTextSelectMode();
					_selectionMc=(event.target as ITextSelectableDisplayObject).getMovieClip();
				} else if (!(event.target.content is MovieClip)) {
					if (!(event.target is MovieClip)) {
						return;
					} else {
						_selectionMc=(event.target as MovieClip);
					}
				} else {
					_selectionMc=(event.target.content as MovieClip);
				}
			} catch (e:*) {
				return;
			}

			if (_docLoader.IsSplit) {
				if (_selectionMc.parent is ITextSelectableDisplayObject) {
					_currentSelectionPage=(_selectionMc.parent as ITextSelectableDisplayObject).getPageIndex();
				} else {
					_currentSelectionPage=(_selectionMc.parent as DupLoader).pageStartIndex;
				}
			} else
				_currentSelectionPage=_selectionMc.currentFrame;

			_currentlySelectedText="";
			_firstHitIndex=-1;
			_lastHitIndex=-1;
			snap=_selectionMc.textSnapshot;

			var hitIndex:int=snap.hitTestTextNearPos(event.target.parent.mouseX, event.target.parent.mouseY, 10);
			var searchIdx:int=hitIndex;
			var searchEndIdx:int=hitIndex;
			var searchChar:String="";
			var searchEndChar:String="";

			if (snap.getText(hitIndex, hitIndex, true) != " " && snap.getText(hitIndex, hitIndex, true) != "\n") {
				while (searchIdx > 0 && searchChar != " " && searchChar != "\n") {
					searchIdx=searchIdx - 1;
					searchChar=snap.getText(searchIdx, searchIdx + 1, true).charAt(1);
				}

				while (searchEndIdx < snap.charCount && searchEndChar != " " && searchEndChar != "\n") {
					searchEndIdx=searchEndIdx + 1;
					searchEndChar=snap.getText(searchEndIdx - 1, searchEndIdx, true).charAt(1);
				}
			}

			if (searchChar == " " || searchEndChar == " " || searchChar == "\n" || searchEndChar == "\n") {
				_firstHitIndex=searchIdx + ((searchChar == "\n") ? 1 : 0) + (searchChar == " " ? 2 : 0);
				_lastHitIndex=searchEndIdx - ((searchEndChar == " " || searchEndChar == "\n") ? 1 : 0) + 1;

				snap.setSelectColor(_markerColor);
				snap.setSelected(_firstHitIndex, _lastHitIndex, true);

				stopSelecting(false);
			}

			//snap.setSelected(_firstHitIndex,hitIndex,true);
		}

		private function textSelectorMouseDownHandler(event:MouseEvent):void {
			if (!TextSelectEnabled) {
				return;
			}
			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);
				_selectionMarker=null;
			}

			try {
				if (event.target is ITextSelectableDisplayObject) {
					(event.target as ITextSelectableDisplayObject).setTextSelectMode();
					_selectionMc=(event.target as ITextSelectableDisplayObject).getMovieClip();
				} else if (!(event.target.content is MovieClip)) {
					if (!(event.target is MovieClip)) {
						return;
					} else {
						_selectionMc=(event.target as MovieClip);
					}
				} else {
					_selectionMc=(event.target.content as MovieClip);
				}
			} catch (e:*) {
				return;
			}

			_currentlySelectedText="";
			_firstHitIndex=-1;
			_lastHitIndex=-1;
			_currentSelectionPage=-1;
			snap=_selectionMc.textSnapshot;

			systemManager.addEventListener(MouseEvent.MOUSE_MOVE, textSelectorMoveHandler, true, 0, true);

			systemManager.addEventListener(MouseEvent.MOUSE_UP, textSelectorMouseUpHandler, true, 0, true);

			systemManager.stage.removeEventListener(Event.MOUSE_LEAVE, textSelectorMouseLeaveHandler);
		}

		private var _firstHitIndex:int=-1;
		private var _lastHitIndex:int=-1;
		private var _currentlySelectedText:String="";
		private var _tri:Array;
		private var _currentSelectionPage:int=-1;
		private var _selectionMc:MovieClip
		private var _selecting:Boolean=false;
		public static var DefaultMarkerColor:uint=0xb5deff;
		private var _markerColor:uint=0xb5deff;
		public static var DefaultSelectionColor:uint=0x0095f7;
		private var _selectionColor:uint=0x0095f7;

		public function get MarkerColor():uint {
			return _markerColor;
		}

		public function set MarkerColor(c:uint):void {
			_markerColor=c;
		}

		public function get SelectionColor():uint {
			return _selectionColor;
		}

		public function set SelectionColor(c:uint):void {
			_selectionColor=c;
		}

		public function get CurrentSelectionPage():int {
			return _currentSelectionPage;
		}

		public function get FirstHitIndex():int {
			return _firstHitIndex;
		}

		public function get LastHitIndex():int {
			return _lastHitIndex;
		}

		public function get TextRunInfo():Array {
			return _tri;
		}

		public function set TextRunInfo(a:Array):void {
			_tri=a;
		}

		private function textSelectorMoveHandler(event:MouseEvent):void {
			event.stopImmediatePropagation();

			var hitIndex:int=snap.hitTestTextNearPos(event.target.parent.mouseX, event.target.parent.mouseY, 10) + ((_firstHitIndex == -1) ? 0 : 1);

			if (hitIndex == _lastHitIndex || hitIndex < 0) {
				return;
			}
			if (!(event.target is DupLoader) && !(event.target is ITextSelectableDisplayObject)) {
				return;
			}

			if (_firstHitIndex == -1) {
				_firstHitIndex=hitIndex;
			}

			if (_docLoader.IsSplit) {
				if (_selectionMc.parent is ITextSelectableDisplayObject) {
					_currentSelectionPage=(_selectionMc.parent as ITextSelectableDisplayObject).getPageIndex();
				} else {
					_currentSelectionPage=(_selectionMc.parent as DupLoader).pageStartIndex;
				}
			} else
				_currentSelectionPage=_selectionMc.currentFrame;

			snap.setSelectColor(_markerColor);
			snap.setSelected(0, snap.charCount, false);

			if (_firstHitIndex <= hitIndex) {
				snap.setSelected(_firstHitIndex, hitIndex, true);
			} else {
				snap.setSelected(hitIndex, _firstHitIndex, true);
			}

			if (_selectionMarker != null && _selectionMarker.parent != null) {
				_selectionMarker.parent.removeChild(_selectionMarker);
			}

			if (_docLoader.IsSplit) {
				if (_selectionMc.parent is ITextSelectableDisplayObject) {
					searchPageIndex=(_selectionMc.parent as ITextSelectableDisplayObject).getPageIndex();
				} else {
					searchPageIndex=(_selectionMc.parent as DupLoader).pageStartIndex;
				}
			} else
				searchPageIndex=_selectionMc.currentFrame;

			_lastHitIndex=hitIndex;
		}

		public function drawCurrentSelection(color:uint, shape:Sprite, tri:Array, strikeout:Boolean=false, alpha:Number=0.3):void {
			var ly:Number=-1;
			var li:int;
			var lx:int;
			var miny:int=-1;
			var minx:int=-1;
			var maxy:int=-1;
			var maxx:int=-1;
			snap.setSelected(1, snap.charCount, false);

			shape.graphics.beginFill(color, (strikeout) ? 0.5 : alpha);
			var rect_commands:Vector.<int>;
			rect_commands=new Vector.<int>((tri.length) * 5, true);

			var rect_coords:Vector.<Number>;
			rect_coords=new Vector.<Number>((tri.length) * 10, true);

			for (var i:int=0; i < tri.length - ((tri.length > 1) ? 1 : 0); i++) {
				if (miny == -1 || miny > tri[i].corner1y) {
					miny=tri[i].corner1y;
				}
				if (minx == -1 || minx > tri[li].corner3x) {
					minx=tri[li].corner3x;
				}
				if (maxy == -1 || maxy < tri[i].corner3y) {
					maxy=tri[i].corner3y;
				}
				if (maxx == -1 || maxx < tri[i].corner1x) {
					maxx=tri[i].corner1x;
				}

				if (ly == -1) {
					ly=tri[i].corner1y;
					li=0;
				}

				rect_commands[i * 5]=1;
				rect_commands[i * 5 + 1]=2;
				rect_commands[i * 5 + 2]=2;
				rect_commands[i * 5 + 3]=2;
				rect_commands[i * 5 + 4]=2;

				rect_coords[i * 10]=tri[li].corner3x;
				rect_coords[i * 10 + 1]=tri[i].corner1y + (strikeout ? (tri[i].corner3y - tri[i].corner1y) / 3 : 0);

				rect_coords[i * 10 + 5]=rect_coords[i * 10 + 1] + (tri[i].corner3y - tri[i].corner1y) / ((strikeout) ? 5 : 1); //h

				if (i != tri.length - 2 && tri[i].corner1x > tri[li].corner3x)
					rect_coords[i * 10 + 2]=rect_coords[i * 10] + tri[i].corner1x - tri[li].corner3x;
				else if (i == tri.length - 2 && tri[i + 1].corner1x > tri[li].corner3x)
					rect_coords[i * 10 + 2]=rect_coords[i * 10] + tri[i + 1].corner1x - tri[li].corner3x;
				else if (i == tri.length - 2 && tri[i + 1].corner1x < tri[li].corner3x) {
					rect_coords[i * 10 + 2]=rect_coords[i * 10] + tri[li].corner1x - tri[li].corner3x;
					rect_coords[i * 10]=tri[li].corner3x;

					/* add an extra struct for the last char*/
					rect_commands[(i + 1) * 5]=1;
					rect_commands[(i + 1) * 5 + 1]=2;
					rect_commands[(i + 1) * 5 + 2]=2;
					rect_commands[(i + 1) * 5 + 3]=2;
					rect_commands[(i + 1) * 5 + 4]=2;

					rect_coords[(i + 1) * 10]=tri[(i + 1)].corner3x;
					rect_coords[(i + 1) * 10 + 1]=tri[(i + 1)].corner1y;
					rect_coords[(i + 1) * 10 + 2]=rect_coords[(i + 1) * 10] + tri[i + 1].corner1x - tri[i + 1].corner3x;
					rect_coords[(i + 1) * 10 + 3]=rect_coords[(i + 1) * 10 + 1];
					rect_coords[(i + 1) * 10 + 4]=rect_coords[(i + 1) * 10 + 2];
					rect_coords[(i + 1) * 10 + 5]=rect_coords[(i + 1) * 10 + 1] + tri[i + 1].corner3y - tri[i + 1].corner1y;
					rect_coords[(i + 1) * 10 + 6]=rect_coords[(i + 1) * 10];
					rect_coords[(i + 1) * 10 + 7]=rect_coords[(i + 1) * 10 + 5];
					rect_coords[(i + 1) * 10 + 8]=rect_coords[(i + 1) * 10];
					rect_coords[(i + 1) * 10 + 9]=rect_coords[(i + 1) * 10 + 1];
				}

				rect_coords[i * 10 + 3]=rect_coords[i * 10 + 1];
				rect_coords[i * 10 + 4]=rect_coords[i * 10 + 2];
				rect_coords[i * 10 + 6]=rect_coords[i * 10];
				rect_coords[i * 10 + 7]=rect_coords[i * 10 + 5];
				rect_coords[i * 10 + 8]=rect_coords[i * 10];
				rect_coords[i * 10 + 9]=rect_coords[i * 10 + 1];

				if (tri.length > 1) {
					ly=tri[i + 1].corner1y;
					lx=tri[i + 1].corner3x;
					li=i + 1;
				}
			}
			if (shape is ShapeMarker) {
				(shape as ShapeMarker).minX=minx;
				(shape as ShapeMarker).minY=miny;
				(shape as ShapeMarker).maxX=maxx;
				(shape as ShapeMarker).maxY=maxy;
			}
			shape.graphics.drawPath(rect_commands, rect_coords, "nonZero");
			shape.graphics.endFill();

			// draw a transparent box covering the whole area to increase hitTest accuracy on mousedown
			shape.graphics.beginFill(0xffffff, 0);
			shape.graphics.drawRect(minx, miny, maxx - minx, maxy - miny);
			shape.graphics.endFill();
		}

		private var _doubleClickTimer:Timer=new Timer(200);
		private var _doubleClickTimerTick:int=0;
		private var _doubleClickEvent:MouseEvent;

		private function textSelectorMouseUpHandler(event:MouseEvent):void {
			if (!_doubleClickTimer.running) {
				_doubleClickTimer=new Timer(200);
				_doubleClickTimerTick=1;
				_doubleClickEvent=event;
				_doubleClickTimer.addEventListener("timer", textSelectorMouseUpDoubleClickHandler);
				_doubleClickTimer.reset();
				_doubleClickTimer.start();
			} else {
				_doubleClickTimerTick++;
			}
		}

		private function textSelectorMouseUpDoubleClickHandler(e:Event):void {
			_doubleClickTimer.stop();

			if (_doubleClickTimerTick == 1) {
				stopSelecting();
			} else {
				textSelectorDoubleClickHandler(_doubleClickEvent);
			}
		}

		private function textSelectorMouseLeaveHandler(event:MouseEvent):void {
			if (_firstHitIndex > 0)
				stopSelecting();
		}

		private function stopSelecting(unselect:Boolean=true):void {
			systemManager.removeEventListener(MouseEvent.MOUSE_MOVE, textSelectorMoveHandler, true);

			systemManager.removeEventListener(MouseEvent.MOUSE_UP, textSelectorMouseUpHandler, true);

			systemManager.stage.removeEventListener(Event.MOUSE_LEAVE, textSelectorMouseLeaveHandler);

			var rev:int;
			if (_firstHitIndex > _lastHitIndex) {
				rev=_firstHitIndex;
				_firstHitIndex=_lastHitIndex;
				_lastHitIndex=rev;
			}

			//var totaltext:String = snap.getText(0,snap.charCount,false);

			if (_firstHitIndex >= 0 && _lastHitIndex > 0)
				_currentlySelectedText=snap.getText(_firstHitIndex, _lastHitIndex - 1, false);
			else
				_currentlySelectedText="";


			if (_currentlySelectedText.length == 0 && _firstHitIndex >= 0 && _lastHitIndex > 0) {
				_currentlySelectedText=snap.getText(_firstHitIndex, _lastHitIndex - 1, true);
			}

			if (ResourceManager.getInstance().localeChain[0] != "zh_CN")
				_currentlySelectedText=TextMapUtil.checkUnicodeIntegrity(_currentlySelectedText, null, _libMC);

			/* trace(_currentlySelectedText.charCodeAt(0)+"|");
			trace(_currentlySelectedText.charCodeAt(1)+"|");
			trace(_currentlySelectedText.charCodeAt(2)+"|");
			trace(_currentlySelectedText.charCodeAt(3)+"|");
			*/


			_tri=snap.getTextRunInfo(_firstHitIndex, _lastHitIndex - 1);

			if (_currentSelectionPage > 0) {
				if (_selectionMarker != null && _selectionMarker.parent != null) {
					_selectionMarker.parent.removeChild(_selectionMarker);
				}

				_selectionMarker=new ShapeMarker();
				_selectionMarker.isSearchMarker=false;
				_selectionMarker.PageIndex=_currentSelectionPage;
				drawCurrentSelection(_selectionColor, _selectionMarker, _tri);

				if (unselect)
					snap.setSelected(_firstHitIndex, _lastHitIndex, false);

				if (UsingExtViewMode) {
					CurrExtViewMode.renderSelection(_currentSelectionPage - 1, _selectionMarker);
				} else {
					_pageList[_currentSelectionPage - 1].addChildAt(_selectionMarker, _pageList[_selectionMc.currentFrame - 1].numChildren);
				}
			} else {
				return;
			}

			dispatchEvent(new SelectionCreatedEvent(SelectionCreatedEvent.SELECTION_CREATED, _currentlySelectedText));
			_selectionMc=null;
		}

		private var _scrollToPageHandler:int=-1;

		private function scrollOnViewModeChangeHandler(evt:Event):void {
			this.gotoPage(_scrollToPageHandler);
			this.removeEventListener(ViewModeChangedEvent.VIEWMODE_CHANGED, scrollOnViewModeChangeHandler);
		}

		private function dupImageClickHandler(event:MouseEvent):void {
			stage.stageFocusRect=false;
			stage.focus=event.target as InteractiveObject;
			if ((_viewMode == ViewModeEnum.TILE) && event.target != null && event.target is DupImage) {
				_scrollToPageHandler=(event.target as DupImage).dupIndex;

				this.addEventListener(ViewModeChangedEvent.VIEWMODE_CHANGED, scrollOnViewModeChangeHandler);

				if (_currentExtViewMode == null || (_currentExtViewMode != null && _currentExtViewMode.Name == "TwoPage")) {
					ViewMode='Portrait';
				} else {
					ViewMode=_currentExtViewMode.Name;
				}
			} else {
				_dupImageClicked=true;
				var t:Timer=new Timer(100, 1);
				t.addEventListener("timer", resetClickHandler, false, 0, true);
				t.start();

				if (event.target is SimpleButton && (event.target as SimpleButton).name.indexOf("http") >= 0) {
					dispatchEvent(new ExternalLinkClickedEvent(ExternalLinkClickedEvent.EXTERNALLINK_CLICKED, (event.target as SimpleButton).name.substring((event.target as SimpleButton).name.indexOf("http"))));
				} else if (event.target is SimpleButton && (event.target as SimpleButton).name.indexOf("url:") >= 0) {
					dispatchEvent(new ExternalLinkClickedEvent(ExternalLinkClickedEvent.EXTERNALLINK_CLICKED, (event.target as SimpleButton).name.substring((event.target as SimpleButton).name.indexOf("url:"))));
				} else if (event.target is SimpleButton && (event.target as SimpleButton).name.indexOf("page:") >= 0) {
					var page:int=parseInt((event.target as SimpleButton).name.substring((event.target as SimpleButton).name.indexOf("page:") + 9));
					dispatchEvent(new InternalLinkClickedEvent(InternalLinkClickedEvent.INTERNALLINK_CLICKED, page));
					gotoPage(page);
				}
			}
		}

		private function resetClickHandler(e:Event):void {
			_dupImageClicked=false;
		}

		private function dupImageMoverHandler(event:MouseEvent):void {

			if (_viewMode == ViewModeEnum.TILE && event.target != null && event.target is DupImage) {
				addGlowFilter(event.target as DupImage);
			} else {
				if (event.target is flash.display.SimpleButton || event.target is mx.core.SpriteAsset || (event.target is IPaperControl) || (event.target.parent != null && event.target.parent.parent != null && event.target.parent.parent is IPaperControl)) {
					Mouse.cursor=flash.ui.MouseCursor.AUTO;
				} else {
					if (TextSelectEnabled && CursorsEnabled) {
						Mouse.cursor=CursorManagerUtils.TEXT_SELECT_CURSOR;
					} else if (CursorsEnabled) {
						resetCursor();
					}
				}
			}
		}

		public function resetCursor():void {
			if (CursorsEnabled)
				Mouse.cursor=CursorManagerUtils.GRAB;
		}

		private function dupImageMoutHandler(event:MouseEvent):void {
			if (_viewMode == ViewModeEnum.TILE && event.target != null && event.target is DupImage) {
				(event.target as DupImage).filters=null;
				(event.target as DupImage).addDropShadow();
			}
		}

		private function addPages():void {
			for (var pi:int=0; pi < _pageList.length; pi++) {
				if (!UsingExtViewMode)
					_displayContainer.addChild(_pageList[pi]);
				else
					CurrExtViewMode.addChild(pi, _pageList[pi]);
			}
		}



		private function addGlowFilter(img:Image):void {
			var filter:flash.filters.GlowFilter=new flash.filters.GlowFilter(0x111111, 1, 5, 5, 2, 1, false, false);
			img.filters=[filter];
		}

		private function addDropShadow(img:Image):void {
			var filter:DropShadowFilter=new DropShadowFilter();
			filter.blurX=4;
			filter.blurY=4;
			filter.quality=2;
			filter.alpha=0.5;
			filter.angle=45;
			filter.color=0x202020;
			filter.distance=6;
			filter.inner=false;
			img.filters=[filter];
		}

	}
}
