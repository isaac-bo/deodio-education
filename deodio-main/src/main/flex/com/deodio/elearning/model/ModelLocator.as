package com.deodio.elearning.model {
	import com.deodio.elearning.domain.Media;
	import com.deodio.elearning.domain.PresentationSyncMedia;
	import com.deodio.elearning.domain.PresentationSyncPoints;
	import com.deodio.elearning.domain.PresentationSyncSlides;
	import com.deodio.elearning.domain.customize.QuizExaminationBo;
	import com.deodio.elearning.module.player.sync.view.SyncPoints;
	
	import flash.media.Video;
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class ModelLocator {

		private static var _model:ModelLocator;

		public var slidePath:String;
		public var sound:Number=0.5;
		public var isPlay:Boolean;
		public var isRecord:Boolean;
		public var recordName:String;
		public var isHeadsetMicroPhone:Boolean=false;
		public var isSlidePicker:Boolean=false;
		public var isQuizPicker:Boolean=false;
		public var isPlayFlag:Boolean=false;
		public var isFullScreen:Boolean=false;
		public var isSettingSound:Boolean=false;
		public var screenType:String="clips";
		public var playDuration:Number=0;
		public var mediaType:Number=3;
		public var num:int=0;
		public var media:Video;
		public var editorMedia:Media;
		public var isEditMedia:Boolean=false;
		public var mediaWidth:Number=640;
		public var mediaHeight:Number=377;
		public var syncPointId:String;
		public var currentSyncPoint:SyncPoints
		public var selectedSyncPoint:PresentationSyncPoints=null;
		public var selectedSyncSlide:PresentationSyncSlides=null;
		public var currentSyncMedia:PresentationSyncMedia=null;
		public var currentSyncMediaIndex:Number=0;
		public var selectedQuiz:QuizExaminationBo;
		public var isResizeSlideImage:Boolean=false;
		public var isResizeClipsImage:Boolean=false;
		public var isResizeClipsMedia:Boolean=false;

		public var presentationId:String;
		public var userId:String;
		public var presentationCreator:String;
		public var isPlayingRecordUrl:String=null;
		public var isPlayRecord:Boolean=false;
		public var isSwap:Boolean=false;
		public var isTrim:Boolean=false;
		public var isUnusedMedia:Boolean=false;

		public var streamLength:Number=-1;
		public var streamCount:Number=0;

		public var recordMediaName:String=null;
		public var recordMediaDesc:String=null;

		public var slidesValues:Array=new Array();
		public var quizCollection:ArrayCollection=new ArrayCollection();
		public var caches:Dictionary=new Dictionary();

		public function ModelLocator() {
		}

		public static function getInstance():ModelLocator {
			if (_model == null) {
				_model=new ModelLocator();
			}
			return _model;
		}
	}
}

