define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils", "jquery.base", "jquery.validate", "jquery.scrolltofixed", "ueditor",
		"ueditor.config", "upload.ui", "upload.common", "upload.handler", "bootstrap.select" ], function($, cookie, doT, paging) {
	
	var init = function() {
		var template = doT.template($("#chooseFlashWin").text());
		popUpWindow(template(), "选择试卷创建方式",590,406);
		$('.modal-header').css('background','#fff none repeat scroll 0 0').css('color','#41829a').css('border-bottom','0px');
		$('.modal-header .close').css('color','#41829a');
	}
	
	hoverModule = function(str){
		var temp = $("div[id^='format']").hide();
		$("#format"+str).show();
	};
	
	chooseModule = function(str){
		if(str == 0){
			//手动创建
			quizId=$.trim($("#quizId").val());
			go2Page("/quiz/manual/content.html","quizId="+$.trim($("#quizId").val())+"&eFlag="+"1");
		}
		if(str == 1){
			//自动创建
			go2Page("/quiz/auto/content.html","quizId="+$.trim($("#quizId").val())+"&eFlag="+"1");
		}
	};

	init();

});