define([ "jquery", "utils.dtree", "utils.cookie", "utils.list", "jquery.dot",
				"pagination", "utils", "jquery.scrolltofixed",
				"jquery.scroll.pagination", "jquery.validate",
				"bootstrap.select", "jquery.base", "bootstrap.datepicker",
				"upload.common", "jquery.mCustomScrollbar","jquery.ui"],
		function($, tree, cookie, list, doT, paging) {
	//modal打开事件
	$('#surveyLearnModal').on('shown.bs.modal', function () {		
		initloadSurveyPreviewModalContent();
	});
	//modal关闭事件
	$('#surveyLearnModal').on('hidden.bs.modal', function () {
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
				var template =  doT.template($("#survey_learn_template_modal").text());
				$("#draggableContent").html(template(result));
				initCheckBox();
			}
		});	
	}
	var initCheckBox=function(){
		$("input[name^='quiz']").each(function() {
			debugger;
			var name = $(this).attr("name");
			customInput(name);
		});
	}
	traineeSubmitServey=function(){
		var listStr = getSurveyData();
		if (!listStr) {
			return;
		}
		var surveyId = $("#_item_id").val();
        var courseId= $("#hiddenCourseId").val();
		var url = "/trainee/submitSurvey.html", data = {
			surveyId : surveyId,
			dataStr : listStr,
			courseId:courseId,
			startTime : new Date(startTime),
			status:1

		};
		postAjaxRequest(url, data, function(result) {
			alertMsg("提交调查问卷成功")
			go2Page("/course/course_viewer/online_detail.html", "courseId=" + $("#hiddenCourseId").val());
		});
	}
	traineeSaveServey=function(){
		var listStr = getSurveyData();
		if (!listStr) {
			return;
		}
		var surveyId = $("#_item_id").val();
        var courseId= $("#hiddenCourseId").val();
		var url = "/trainee/saveSurvey.html", data = {
			surveyId : surveyId,
			dataStr : listStr,
			courseId:courseId,
			startTime : new Date(startTime),
			status:0
		};
		postAjaxRequest(url, data, function(result) {
			alertMsg("保存调查问卷成功")
			go2Page("/course/course_viewer/online_detail.html", "courseId=" + $("#hiddenCourseId").val());
		});
	}
	var getSurveyData = function(){
		var dataArray = new Array();
		debugger;
		$(".chouti").each(function(i,v){
		    var _qtype=$(this).attr('qtype');
		    var _question = $(this).find('div.edit_title .pl10').text();
		    var _optionsType = getQtype(_qtype),answers = new Array();
		    $(this).find(_optionsType).each(function(){
		    	answers.push($(this).val());
		    });
		    var optionArray =  new Array();
		    if(_qtype==1||_qtype==2){
		    	$(this).find('div.ti').each(function() {
					var isCorrect = $(this).find('input').prop("checked") == true ? "1": 0;
					optionArray.push($(this).text()+ "_&_"+ isCorrect);	     
			 })	  
		    }
		    if(_qtype==3){
		    	$(this).find('div.ti').each(function() {
					var isCorrect = $(this).find('input').prop("checked") == true ? "1": 0;
					optionArray.push($(this).text()+ "_&_"+ isCorrect);
				});
		    }
		    if(_qtype==6){
		    	textarea = $(this).find('div.jianda textarea').val();
		    	optionArray.push(textarea);
		    }
		    var dataStr = _qtype + "_#_" + _question + "_#_" + optionArray.join("]=[");
			dataArray.push(dataStr);    
		});
		return dataArray.join("_@_");
	}
	//获取题目类型
	var getQtype = function(qtype){
		var _type='';
		if(qtype==1 || qtype==3){
			_type='input[type="radio"]:checked';
		}else if(qtype==2){  
			_type='input[type="checkbox"]:checked';
		}
		return _type;
	};
});
