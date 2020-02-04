define(
		[ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar" ,"bootstrap.select"],
		function($, cookie, doT, paging) {
	//modal打开事件
	$('#surveyQuoteModal').on('shown.bs.modal', function () {	
		initloadSurveyQuoteModalContent();		
	});
	//modal关闭事件
	$('#surveyQuoteModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	var initloadSurveyQuoteModalContent = function(pageNo){
		 //初始化页面
//		$("#copyTitle").val("调查问卷");
		if (pageNo == undefined)
			pageNo = 1;
			var params = {
					pageNo:pageNo,
					surveyId : $("#_item_id").val()
				}, url = "/survey/quote_survey.html";
			postAjaxRequest(url, params, function(result) {
				if(result.status == 1){
					paging.pageTemplateDiv(result, "table_content_survey_quote", "right_content_survey_quote_template", "course_setting_survey_quote_Panel", "initloadSurveyQuoteModalContent");
				}
			});

	}
});
