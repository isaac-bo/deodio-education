define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.validate", "bootstrap.datepicker", "jquery.ui",
		"jquery.mCustomScrollbar", "bootstrap.select" ], function($, cookie,
		doT, paging) {
	// modal打开事件
	$('#presentationShareModal').on('shown.bs.modal', function() {
		loadLecturerList();
	});
	
	
	$('#presentationShareModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
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
			
			if (result.status == 1) {
				if(result.data.dataList.length==0){
					$("#btn_submit_share").hide();
				};
				
				paging.pageTemplateDiv(result, 
					"table_content_presentation_share",
					"right_content_presentation_share_template",
					"presentation_setting_presentation_share_panel", 
					"loadLecturerList");
			}
		});
	}
	//设置分享综合试卷的讲师
	shareCourse = function() {
		var lecturer = $("input[name='lecturerId'][checked]");
		var courseOwner = "";
		if(lecturer.length>0){
			lecturer.each(function(){			    
				courseOwner=$(this).attr("id");
			  });
		    var params = {
		    	teachUserId:courseOwner,
	    		presentationId : $.trim($('#_item_id').val())
			}, url = "/presentation/insertPresentationOwner.html";
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					if(result.data==1){
						alertMsg('该章节已经被分享，请选择其他章节');
					}else{
						alertMsg('该章节已经分享成功');
						reloadPageData();
					}
				}
			});
		}else{
			alertMsg('请先选择讲师');
		}
	}
});
