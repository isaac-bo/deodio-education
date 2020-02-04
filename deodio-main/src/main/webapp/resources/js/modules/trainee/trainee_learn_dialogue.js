define([ "jquery", "utils.dtree", "utils.cookie", "utils.list", "jquery.dot",
				"pagination", "utils", "jquery.scrolltofixed",
				"jquery.scroll.pagination", "jquery.validate",
				"bootstrap.select", "jquery.base", "bootstrap.datepicker",
				"upload.common", "jquery.mCustomScrollbar","jquery.ui"],
		function($, tree, cookie, list, doT, paging) {
	//modal打开事件
	$('#quizLearnModal').on('shown.bs.modal', function () {	
		initQuizBaseInfo();
		initloadQuizPreviewModalContent();	
	});
	
	//modal关闭事件
	$('#quizLearnModal').on('hidden.bs.modal', function () {
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
				var template =  doT.template($("#quiz_learn_template_modal").text());
				$("#quizDraggableContent").html(template(result));
				initCheckBox();
			}
		});	
	}
	var initQuizBaseInfo=function(){
		 //初始化页面
		var quizId=$("#_item_id").val();
		var params = {
				quizId :quizId
			}, url = "/trainee/getQuizBaseInfo.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status){
				$("#finishTime").text(result.data.quizBo.finishTime);
				$("#passScore").text(result.data.quizBo.passScore);
				$("#countScore").text(result.data.countScore);
			}
		},false);	
	}
	var initCheckBox=function(){
		$("input[name^='quiz']").each(function() {
			var name = $(this).attr("name");
			customInput(name);
		});
	}
	traineeSubmitTest=function(){
		var listStr = getQuizList();
		if (!listStr) {
			return;
		}
		var quizId = $("#_item_id").val();
        var courseId= $("#hiddenCourseId").val();
		var url = "/trainee/submitQuiz.html", data = {
			quizId : quizId,
			dataStr : listStr,
			courseId:courseId,
			startTime : new Date(),
			status:1

		};
		postAjaxRequest(url, data, function(result) {
			alertMsg("提交试卷成功")
			go2Page("/course/course_viewer/online_detail.html", "courseId=" + $("#hiddenCourseId").val());
		});
	}
	traineeSaveTest=function(){
		var listStr = getQuizList();
		if (!listStr) {
			return;
		}
		var quizId = $("#_item_id").val();
        var courseId= $("#hiddenCourseId").val();
		var url = "/trainee/saveQuiz.html", data = {
			quizId : quizId,
			dataStr : listStr,
			courseId:courseId,
			startTime : new Date(),
			status:0

		};
		postAjaxRequest(url, data, function(result) {
			alertMsg("保存试卷成功")
			go2Page("/course/course_viewer/online_detail.html", "courseId=" + $("#hiddenCourseId").val());
		});
	}
	var getQuizList = function(){
		var dataArray = new Array();
		$(".chouti").each(function(i, v) {
				var _qtype = $(this).attr('qtype');
				var _question = $(this).find('div.edit_title .pl10').text();
				var textarea = "";
				var continueFlg = true;
				var optionArray = new Array();
				if (_qtype <= 3) {
					$(this).find('div.ti').each(function() {
							var isCorrect = $(this).find('input').prop("checked") == true ? "1": 0;
							optionArray.push($(this).text()+ "_&_"+ isCorrect);
						});
				} else if (_qtype == 4) {
					$(this).find('div.ti').each(function() {
							var isCorrect = $(this).find('input').val();
							optionArray.push($(this).find('.pt8').text()+ "_&_"+ isCorrect);
					});
				} else {
					if (_qtype == 5) {
					//图形题
					var dirId = $(this).find('div.uploadify').attr("id").split("_")[1];
						textarea = $(this).find('div.tuxing textarea').val()+"_&_"+$("#uploadFilePath_" + dirId).val();
					} else if (_qtype == 7) {
						textarea = $(this).find('div.tiankong textarea').val();
					}else if (_qtype == 6) {
						textarea = $(this).find('div.jianda textarea').val();
					}
					
					if(!textarea){
						continueFlg = false;
					}
					
					optionArray.push(textarea);
				}
				var dataStr = _qtype + "_#_" + _question + "_#_" + optionArray.join("]=[");
				dataArray.push(dataStr);
		});
		
		return dataArray.join("_@_");
	}
});
