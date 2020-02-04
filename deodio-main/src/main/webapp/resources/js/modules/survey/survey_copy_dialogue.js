define(
		[ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar" ,"bootstrap.select"],
		function($, cookie, doT, paging) {
	//modal打开事件
	$('#surveyCopyModal').on('shown.bs.modal', function () {	
		initloadSurveyModalContent();
	});
	//modal关闭事件
	$('#surveyCopyModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	var initloadSurveyModalContent = function(){
		 //初始化页面
		$("#copyTitle").val("复制调查问卷");
		var oldSurveyName=$("#_item_name").val();	
		var oldSurveyId=$("#_item_id").val();
		var params = {
				surveyId : oldSurveyId,
				surveyName:oldSurveyName
			}, url = "/survey/getBaseInfo.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				$("#newSurveyName").val(result.data.survey.surveyName+"("+result.data.num+")");
			}
		});
	}
});
