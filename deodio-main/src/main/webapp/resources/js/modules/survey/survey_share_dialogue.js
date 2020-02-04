define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.validate", "bootstrap.datepicker", "jquery.ui",
		"jquery.mCustomScrollbar", "bootstrap.select" ], function($, cookie,
		doT, paging) {
	// modal打开事件
	$('#surveyShareModal').on('shown.bs.modal', function() {
		loadLecturerList(1);
	});
	//modal关闭事件
	$('#surveyShareModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	//加载讲师列表数据
	loadLecturerList = function(pageNo) {
		if (pageNo == undefined) {
			pageNo = 1;
		}
		var params = {
			pageNo : pageNo,
			keyWord : $("#lecturerKeyWord").val(),
			groupId : $("#_item_groupId").val()
		}, url = "/trainers/find_share_lecturer.html";
		postAjaxRequest(url, params, function(result) {
			console.log('result---- ',result);
			if (result.status == 1) {
				paging.pageTemplateDiv(result,
						"table_content_survey_share",
						"right_content_survey_share_template", 
						"course_setting_survey_share_Panel",
						"loadLecturerList");
			}
		});
	}
	
	alertLabel=function(userId){
		$("#"+userId).attr("checked","checked");
		$("input[name='surveyTrainee'][checked]").each(function(){
			if($(this).attr("id")==userId){
		    	$(this).attr("checked","checked");	    	
		    }else{
		    	$(this).attr("checked",false);
		    }
		  });	
	}
});
