define([ "jquery", "utils.cookie", "utils.menu","jquery.dot", "pagination", "utils", "jquery.base", "jquery.validate", "jquery.scrolltofixed",
		"jquery.mCustomScrollbar", "bootstrap.select", "bootstrap.datepicker", "jquery.scroll.pagination" ], function($, cookie, menu,doT, paging) {

	var _init = function() {
//		_initUI();
		
		loadDataList(1,"table_panle", "table_data_template", "smallIconShow");
	};
	
	var _initUI = function() {
//		$('#myTab #tab02').addClass('active');
//		$('#mediumLeftBar').scrollToFixed({marginTop: 60});
	};
	
	//加载小组课程列表
	loadDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:$("#smallIcon").is(":hidden")?6:10,
			groupId:$('#groupId').val()
		}, url = "/group/load_course_list.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "data_page_Panel", callbackFunc);
			}
		});
	};
	
	//显示小组课程详细列表
	smallIconShow = function(pageNo) {
		$('#content').stopScrollPagination();
		$('.table-item').addClass('on');
		$('.pic-item').removeClass('on');
		$('#smallIcon').show();
		$("#content").children().remove();
		$("#mediumIcon").hide();
		$("#data_page_Panel").show();
		$("#hid_default_page").val(2);
		loadDataList(pageNo,"table_panle", "table_data_template", "smallIconShow");
		
	};
	
	//显示考试试卷中等图标列表
	mediumIconShow = function(pageNo) {
		$('.pic-item').addClass('on');
		$('.table-item').removeClass('on');
		$('#mediumIcon').show();
		$("#table_panle").children().remove();
		$('#smallIcon').hide();
		$("#data_page_Panel").hide();
		loadDataList(pageNo,"content", "data_template", "mediumIconShow");
		$('#content').scrollPagination({
			'postUrl' : ctx + '/group/load_course_list.html',
			'postParams' : {
				pageNo : 1,
				pageSize:6
			},
			'scrollTarget' : $(window),
			'callBackLoad' : function(data) {
				loadGroupCourseDataList(data);
			},
			'beforeLoad' : function() {
				this.postParams.pageNo = $("#hid_default_page").val();
				this.postParams.pageSize = 6;
			}

		});

	};

	//滚动加载小组课程列表的回调函数
	loadGroupCourseDataList = function(data) {
		var template = doT.template($("#data_template").text());
		if ($("#content li").size() == 0) {
			$('#content').append(template({
				"data" : data.dataList
			}));
		} else {
			$('#content li.bgon:last').after(template({
				"data" : data.dataList
			}));
		}
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_page").val()) + 1;
			$("#hid_default_page").val(finalPageNo);
		}
	};

	//删除小组课程
	delGroupCourse = function(relId){
		confirmMsg('请确认您是否要删除该课程？',function(){
			var url="/group/delete_course.html",
			data={
					relId:relId,
					groupId:$('#groupId').val()	
			};
			postAjaxRequest(url,data,function(result){
				if($('#smallIcon').is(":hidden")){
					mediumIconShow(1);
				}else{
					smallIconShow(1);
				}
				
					alertMsg("操作成功");
			});
		});
	};
	
	//跳转到考试试卷编辑界面
	detailGroupCourse = function(courseId,type){
//		window.location.href=ctx+"/quizs/get_quiz.html?quizId="+quizId;
		var url = "",params = "";
		switch(type){
		//case '1': url = "/course/online/detail.html";params="courseId=";break;//线上课程
		//case '2': url = '/course/offline/detail.html';params="courseId=";break;//线下课程
		case '3': url = '/course/onlive/profile.html';params="courseId=";break;//直播课程，保留
		default : url = '/course/course_viewer/detail.html';params="courseId=";break;
	    //url ='/course/packages/profile.html';params="packageId=";break;//课程包
		}
//		go2Page(url,params + courseId);
//		window.location.href=ctx+url+"?"+params+courseId;
		window.open(ctx+url+"?"+params+courseId);
	};
	
	addGroupCourse = function(){
		$("#groupCourseToSelectModal").modal("show");
	}
	addGroupCoursePackage= function(){
		$("#groupCoursePackageToSelectModal").modal("show");
	}
	groupCourseDialogCallBackFun = function(){
		_init();
	}
	_init();
	
});
