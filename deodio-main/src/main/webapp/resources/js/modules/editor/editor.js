define(["jquery","utils.cookie","jquery.dot","pagination","utils.math","utils","jquery.base","jquery.validate","jquery.scrolltofixed","ueditor",
			"ueditor.config","upload.ui","upload.common","upload.handler"], function($,cookie,doT,paging,math) {
	/*默认图片位置*/
	var _param = {
		openToolBarRightStyle :"300px",
		closeToolBarRightStyle : "-300px"
	}
	
	var _init = function(){
		var _height=$("body").height();//获取当前窗口的高度
		var _width=$("body").width();//获取当前窗口的宽度
		$('.catalog_box').css('height',_height - $('.top_con').height() - $('.catalog').height()+'px');
		
		$('.height_auto').css('width','100%');
		$('._time_line').css('width', $('.video_con').width() - 180 + 'px');
		$('.right_con_height').css('width', $('.video_con').width() - 200 + 'px');
	};
	
	var _onOpenRightToolBar = function(w){
//		debugger;
		var _leftContainerCss_BEG = {right:'0px','display':'block'};
		var _leftButtonCss_BEG = {right:_param.openToolBarRightStyle};
		
		$("#toolsBar").animate(_leftButtonCss_BEG, "normal");
		$("#settingContents").animate(_leftContainerCss_BEG, "normal");
		if(w != undefined){
			$('.left_content').animate({'padding-right':w +'px'}, "normal");
			$('.right_con_height').animate({width:$('.right_con_height').width() - w +'px'},"normal");
		}else{
		//	$('.left_content').animate({'padding-right':'350px'}, "normal");
			$('.right_con_height').animate({width:$('.right_con_height').width() - 350 +'px'},"normal");
		}
		
		$('#toolsBar span').removeClass('icon-double-angle-left').addClass('icon-double-angle-right');
		$("#settingContents").show();
	};
	
	var _onCloseRightToolBar = function(w){
//		debugger;
		var _leftContainerCss_BEG = {right:_param.closeToolBarRightStyle,'display':'none'};
		var _leftButtonCss_BEG = {right:'0px'};
		
		$("#toolsBar").animate(_leftButtonCss_BEG, "normal");
		$("#settingContents").animate(_leftContainerCss_BEG, "normal");
		//$('.left_content').animate({'padding-right':'0px'}, "normal");
		if(w != undefined){
			$('.right_con_height').animate({width:$('.right_con_height').width() + w +'px'},"normal");
		}else{
			$('.right_con_height').animate({width:$('.right_con_height').width() + 350 +'px'},"normal");
		}
		$('#toolsBar span').removeClass('icon-double-angle-right').addClass('icon-double-angle-left');
		$("#settingContents").hide(100);
	};
	
	_init();
	
	
	onToggleRightToolBar = function(w){
		
		if($('#settingContents').css('right') == '0px'){
			_onCloseRightToolBar(w);
		}else{
			_onOpenRightToolBar(w);
		}
	};
	
	var _initToolBarRight = function(settingParam){
		var suffixStr = 'px';
//		debugger;
		if(math.isDigit(settingParam)){
			_param.openToolBarRightStyle = settingParam + suffixStr;
			_param.closeToolBarRightStyle = 0 - settingParam + suffixStr ;
		 }
	}
	
	return {
		initToolBarRight:_initToolBarRight
	}
	
});
		