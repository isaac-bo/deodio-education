define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.validate", "bootstrap.datepicker", "jquery.ui",
		"jquery.mCustomScrollbar", "bootstrap.select" ], function($, cookie,
		doT, paging) {
	// modal打开事件
	$('#courseOfflineShareModal').on('shown.bs.modal', function() {
		loadLecturerList();
	});
	
	alertLabel = function(id) {
		$("#" + id).attr("checked","checked");
		$("input[name='lecturerId'][checked]").each(function() {
			if ($(this).attr("id") == id) {
		    	$(this).attr("checked","checked");	    	
		    } else {
		    	$(this).attr("checked",false);
		    }
		});	
	}
	//加载讲师列表数据
	loadLecturerList = function(pageNo) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
				pageNo : pageNo,
				keyWord : $("#lecturerKeyWord").val()
		}, url = "/trainers/find_share_lecturer.html";
		postAjaxRequest(url, params, function(result) {
			debugger;
			if (result.status == 1) {
				paging.pageTemplateDiv(result, 
					"table_content_offline_course_share",
					"right_content_offline_course_share_template",
					"course_setting_offline_course_share_panel", 
					"loadLecturerList");
			}
		});
	}
	//设置分享综合试卷的讲师
	shareOfflineCourse=function(){
		var lecturer = $("input[name='lecturerId'][checked]");
		var courseOwner = "";
		if(lecturer.length>0){
			lecturer.each(function(){			    
				courseOwner=$(this).attr("id");
			  });
		    var params = {
		    		courseOwner:courseOwner,
					courseId : $.trim($('#_item_id').val())
			}, url = "/course/offline/share_course_owner.html";
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					alertMsg('该线下课程已经分享成功');
					reloadPageData();
				}
			});
		}else{
			alertMsg('请先选择讲师');
		}
	}
});
