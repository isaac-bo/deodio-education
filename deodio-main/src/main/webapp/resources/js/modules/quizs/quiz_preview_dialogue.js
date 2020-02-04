define([ "jquery", "utils.dtree", "utils.cookie", "utils.list", "jquery.dot",
				"pagination", "utils", "jquery.scrolltofixed",
				"jquery.scroll.pagination", "jquery.validate",
				"bootstrap.select", "jquery.base", "bootstrap.datepicker",
				"upload.common", "jquery.mCustomScrollbar","jquery.ui"],
		function($, tree, cookie, list, doT, paging) {
	//modal打开事件
	$('#quizPreviewModal').on('shown.bs.modal', function () {		
		initloadQuizPreviewModalContent();
	});
	//modal关闭事件
	$('#quizPreviewModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		$("#quizDraggableContent").html('');
	});
	var initloadQuizPreviewModalContent = function(){	
		var quizId=$("#_item_id").val();
		 //初始化页面
		var params = {
				quizId :quizId
			}, url = "/quiz/view.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status){
				var template =  doT.template($("#quiz_preview_template_modal").text());
				console.log("template(result)===",template(result))
				$("#quizDraggableContent").html(template(result));
			}
		});	
	}
});
