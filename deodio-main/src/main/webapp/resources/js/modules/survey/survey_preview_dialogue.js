define([ "jquery", "utils.dtree", "utils.cookie", "utils.list", "jquery.dot",
				"pagination", "utils", "jquery.scrolltofixed",
				"jquery.scroll.pagination", "jquery.validate",
				"bootstrap.select", "jquery.base", "bootstrap.datepicker",
				"upload.common", "jquery.mCustomScrollbar","jquery.ui"],
		function($, tree, cookie, list, doT, paging) {
	//modal打开事件
	$('#surveyPreviewModal').on('shown.bs.modal', function () {		
		initloadSurveyPreviewModalContent();
	});
	//modal关闭事件
	$('#surveyPreviewModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		$("#draggableContent").html('');
	});
	var initloadSurveyPreviewModalContent = function(){	
		var surveyId=$("#_item_id").val();
		 //初始化页面
		var params = {
				surveyId :surveyId
			}, url = "/survey/survey_preview_dialogue.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status){
				var template =  doT.template($("#survey_preview_template_modal").text());
				$("#draggableContent").html(template(result));
			}
		});	
	}
});
