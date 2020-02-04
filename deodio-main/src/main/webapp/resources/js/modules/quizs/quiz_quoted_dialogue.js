define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar" ,"bootstrap.select"],
		function($, cookie, doT, paging) {
	//modal打开事件
	$('#quizQuoteModal').on('shown.bs.modal', function () {		
		initloadQuizQuoteModalContent();
	});
	
	$('#quizQuoteModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	
	initloadQuizQuoteModalContent = function(pageNo){
		 //初始化页面
//		$("#copyTitle").val("调查问卷");
		if (pageNo == undefined)
			pageNo = 1;
			var data = {
					pageNo : pageNo,
					quizId : $("#_item_id").val()
				}, url = "/quiz/quote_quiz.html";
			postAjaxRequest(url, data, function(result) {
				if(result.status == 1){
					paging.pageTemplateDiv(result, 
						"table_content_quiz_quote",
						"right_content_quiz_quote_template", 
						"course_setting_quiz_quote_Panel", 
						"initloadQuizQuoteModalContent");
				}
			});

	}
});
