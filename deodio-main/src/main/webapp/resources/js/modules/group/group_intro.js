define([ "jquery", 
         "utils.cookie", 
         "utils.list", 
         "jquery.dot",
		 "utils.menu", 
		 "pagination", 
		 "utils", 
		 "jquery.base",
		 "bootstrap.datepicker", 
		 "jquery.scrolltofixed",
		 "jquery.scroll.pagination" ], function($, cookie, list, doT,menu, paging) {
	var courseType=1;
    var _init = function(){
    	$('#groupCourseContent').scrollPagination({
			'postUrl' : ctx + '/group/load_course_list.html',
			'postParams' : {
				pageNo : 1,
				courseType:courseType,
				groupId:$('#groupId').val()
				
			},
			'scrollTarget' : $(window),
			'callBackLoad' : function(data) {
				loadGroupCourseDataList(data);
			},
			'beforeLoad' : function() {
				this.postParams.pageNo = $("#hid_default_course_online_page").val();
				//this.postParams.pageSize = 6;
			}

		});
		menu.onSelectMenu(2);
		list.onFixedItems();
		loadDataList();
	};
	//加载小组课程列表
	loadDataList = function() {
		var params = {
			pageNo : 1,
			courseType:courseType,
			groupId:$('#groupId').val()
		}, url = "/group/load_course_list.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				loadGroupCourseDataList(result.data);
				
			}
			
		});
	};
	fingGroupCourseListByName=function(){
		var courseNameKeyWord=$("#courseNameKeyWord").val();
		console.log(" ladsjflasjflsajf   ",courseNameKeyWord);
		var params = {
				pageNo : 1,
				courseType:courseType,
				groupId:$('#groupId').val(),
				courseNameKeyWord:courseNameKeyWord
			}, url = "/group/load_course_list.html";
			postAjaxRequest(url, params, function(result) {
				var resultData=result.data.dataList;
				if(result.status == 1){
					var template = doT.template($("#classification_data_template").text());
					$('#groupCourseContent').html(template({
						"data" : resultData
					}));
				}
			});
	}
//	loadGroupCourseList=function(obj,flag){
//		$(obj).parent().attr('class','active');
//		$(obj).parent().siblings().removeClass('active');
//		var params = {
//				pageNo : 1,
//				groupId:$('#groupId').val(),
//				flag:flag
//			}, url = "/group/load_course_list.html";
//			postAjaxRequest(url, params, function(result) {
//				if(result.status == 1){
//					loadGroupCourseDataList(result.data);
//				}
//			});
//	}
	//滚动加载小组课程列表的回调函数
	loadGroupCourseDataList = function(data) {
		var template = doT.template($("#classification_data_template").text());
		if ($("#groupCourseContent li").size() == 0) {
			$('#groupCourseContent').append(template({
				"data" : data.dataList
			}));
		} else {
			$('#groupCourseContent li.my_data:last').after(template({"data":data.dataList}));
		}
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_course_online_page").val()) + 1;
			$("#hid_default_course_online_page").val(finalPageNo);
		}
	};
	//跳转到小组课程界面
	detailGroupCourse = function(courseId,type){
		var url = "",params = "";
		switch(type){
		case '1' : url = '/course/course_viewer/online_detail.html';break;//线上课程
		case '2' : url = '/course/course_viewer/offline_detail.html';break;//线下课程
		case '3': url = '/course/onlive/profile.html';;break;//直播课程，保留
		
		}
		go2Page(url,'courseId='+courseId);
		//window.open(ctx+url+"?"+params+courseId);
	};
	
	loadGroupCourseList=function(selectCourseType){
		courseType=selectCourseType;
		console.log("courseType    ",courseType);
		var params = {
				pageNo : 1,
				courseType:courseType,
				groupId:$('#groupId').val()
			}, url = "/group/load_course_list.html";
			postAjaxRequest(url, params, function(result) {
				var resultData=result.data.dataList;
				console.log("resultData   ",resultData);
				if(result.status == 1){
					var template = doT.template($("#classification_data_template").text());
					$('#groupCourseContent').html(template({
						"data" : resultData
					}));
				}
			});
	}
	groupCourseDialogCallBackFun = function(){
		_init();
	}
	_init();
	
});
