define([ "jquery", "utils.cookie","utils.menu", "jquery.dot", "pagination", "utils", "jquery.base", "jquery.validate", "jquery.scrolltofixed",
		"jquery.mCustomScrollbar", "bootstrap.select", "jquery.scroll.pagination" ], function($, cookie, menu,doT, paging) {

	//	10000	Group Leader
	//	10001	Content Creator
	//	10002	Viewer
	var hiddenColumnsIndexConfg = {
		"10000" : [5],
		"10001" : [2,5],
		"10002" : [2,3]
	}
	
	var currePage = 1;
	
	var _init = function() {
		menu.onSelectMenu(4);
		$('select').selectpicker();
		loadDataList(1,"table_panel", "table_data_template", "smallIconShow");
	};
	
	//加载问卷调查列表
	loadDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {
		var groupRole = $.trim($('#groupRole').val());
		var groupId = $.trim($('#groupId').val());
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:10,
			keywords : $.trim($('#keywords').val()),
			groupRole : groupRole,
			groupId : groupId
		}, url = "/course/shortcut/load_list.html";
//		debugger;
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
//				debugger;
				paging.pageTemplateDiv(result, tablePanel,tableDataTemplate, "data_page_Panel", callbackFunc);
				//隐藏table中的列
//				debugger;
				showHideColumnByGroupRole(hiddenColumnsIndexConfg[groupRole],"smallIcon");
				currePage = result.currePage;
			}
		});
	};
	
	//加载详细列表
	smallIconShow= function(pageNo){
		$('#smallIcon').show();
		$("#data_page_Panel").show();
//		$("#hid_default_page").val(2);
		$('#smallIconConditionBar').show();
		loadDataList(pageNo,"table_panel", "table_data_template", "smallIconShow");
	};
	
	//查找问卷调查，并刷新问卷调查列表
	searchCourseList = function(){
		smallIconShow(1);
	};
	
	//跳转到编辑该该问卷调查界面
	onGoToCourseDetail = function(courseId,courseType){
		var targetUrl = "/course/online/detail.html";
		if(courseType != '1'){
			targetUrl = "/course/offline/detail.html";
		}
		go2Page(targetUrl,'courseId='+courseId);
	};
	
	convertToCourseTypeName = function(courseType){
		var result = "";
		if(courseType){
			if(courseType == '1'){
				result = "线上课程";
			}else if(courseType == '2'){
				result = "线下课程";
			}else if(courseType == '3'){
				result = "直播课程";
			}else if(courseType == '4'){
				result = "课程包";
			}
		}
		return result;
	}
	
	//隐藏指定表格列数据   columnIndex 从0开始(0标识第一列)
	var showHideColumnByGroupRole = function(columnIndexArray,tableId){
//		debugger;
		//判断表格编号是佛为空,不为空，显示全部列数据;否则终止执行
		if(tableId){
			$("*","#" + tableId + " tr").show();
		}else{
			return;
		}
		//判断隐藏列索引是否合理
		var size = $.isArray(columnIndexArray)?columnIndexArray.length:0;
		if(size == 0){
			return;
		}
		//拼接隐藏列数据过滤条件
		var filterArray = new Array();
		for(var index in columnIndexArray){
			var columnIndex = columnIndexArray[index];
			filterArray.push(":eq(" + columnIndex + ")"); 
		}
		var filterStr = filterArray.join();
		//隐藏列数据
		$(filterStr,"#" + tableId + " tr").hide();
	}
	
	onSelectCourse = function(courseId,courseType,groupId){
		var data = {
				itemId : courseId,
				itemType:courseType,
				groupId:groupId
			};
		var params = {
				queryJson:JSON.stringify(data)
			}, url = "/course/course_viewer/select_course.html";
	
			postAjaxRequest(url, params, function(result) {
				if(result.status == 1){
					smallIconShow(currePage);
					alertMsg("操作成功");
				}else{
					alertMsg("操作失败");
				}
			});
	}
	
	_init();
});
