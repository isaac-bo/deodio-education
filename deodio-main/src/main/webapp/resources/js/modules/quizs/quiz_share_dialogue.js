define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.validate", "bootstrap.datepicker", "jquery.ui",
		"jquery.mCustomScrollbar", "bootstrap.select" ], function($, cookie,
		doT, paging) {
	// modal打开事件
	$('#quizShareModal').on('shown.bs.modal', function() {
		loadLecturerList(1);
	});
	
	$('#quizShareModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	
	alertLabel = function(id) {
		$("#"+id).attr("checked","checked");
		$("input[name='lecturerId'][checked]").each(function() {
			if($(this).attr("id")==id){
		    	$(this).attr("checked","checked");	    	
		    }else{
		    	$(this).attr("checked",false);
		    }
		});	
	}
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
			if (result.status == 1) {
				paging.pageTemplateDiv(result, 
					"table_content_quiz_share",
					"right_content_quiz_share_template",
					"course_setting_quiz_share_panel", 
					"loadLecturerList");
			}
		});
	}
	//设置分享综合试卷的讲师
	shareQuizToUser = function() {
		var lecturer = $("input[name='lecturerId'][checked]");
		var quizOwner="";
		if (lecturer.length>0) {
			lecturer.each(function() {			    
				quizOwner = $(this).attr("id");
			  });
		    var params = {
		    		quizOwner : quizOwner,
					quizId : $.trim($('#_item_id').val())
			}, url = "/quiz/update_quiz_owner.html";
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					alertMsg('该综合试卷已经分享成功');
					loadDataList(1, "content", "data_template", "mediumIconShow");
				}
			});
		} else {
			alertMsg('请先选择讲师');
		}
	}
});
