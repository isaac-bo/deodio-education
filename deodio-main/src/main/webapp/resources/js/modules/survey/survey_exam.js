define(["jquery","utils.dtree","utils.cookie","jquery.dot","libs/step/step_page","utils.menu",
		"pagination","utils","jquery.base","jquery.validate","ueditor",
	    "jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker","jquery.countdown","jquery.ui"], 
	     function($,tree,cookie,doT,step,menu) {
		
	var init = function(){
		refreshSurveyNo();
		
		$("[name^='survey']").each(function(){
   		 var name = $(this).attr("name");
   		 customInput(name);
   	 	});
		
		$("#saveSurveyForm").myValidate({
			formCall : function() {
				saveSurvey();
			},
			isAlert : false,
			formKey : false,
			errorCustom : {
				customFlag : true,
				regionText : true
			},
			errorStyle : {
				errorRegion : "error-div",
				errorClass : "wrong-item",
				rightClass : "right-item"
			}
		});
	};
	init();
});

var getQuestionList = function(){
	var dataArray = new Array();
	$(".chouti").each(function(i, v) {
			var _qtype = $(this).attr('qtype');
			var _question = $(this).find('div.edit_title input').val();
//			var _order=$(this).find('._quiz-order').html();
			var textarea = "";
			var continueFlg = true;
			var optionArray = new Array();
			if (_qtype <= 3) {
				$(this).find('div.ti').each(function() {
					var _options = $(this).find('input[type="hidden"]');
//					var _options = $(this).find('input[type="text"]');
//					var _options = $(this).find('div.ti_input_w pull-left ml20 pt8');
					_options.each(function() {
						var isCorrect = $(this).parent().siblings("div").find('input').prop("checked") == true ? "1": 0;
						optionArray.push($(this).val()+ "_&_"+ isCorrect);
					});

				});
			} 
				
			//判断内容项目不能未空
//			if(optionArray.length == 0 || !continueFlg){
//				alertMsg("请设置第" + (i+1) + "题目内容！");
//				//出现错误直接置空
//				dataArray = new Array();
//				return false;
//			}
			
			var dataStr = _qtype + "_#_" + _question + "_#_" + optionArray.join("]=[");
			dataArray.push(dataStr);
	});
	
	return dataArray.join("_@_");
}

var goBack = function() {
	
	go2Page("/course/course_viewer/detail.html", "courseId=" + courseId + "&groupId=" + groupId);
};

var refreshSurveyNo = function(){
	$("._quiz-order").each(function(i,v){
		  $(this).text(i+1);
	});
	$("._subject-order").each(function(i,v){
		  $(this).text(i+1);
	});
};

var saveSurvey = function() {
	var listStr = getQuestionList();
	if(!listStr){
		return;
	}
	debugger;
	var url = "/quiz/exam/save.html", data = {
		examId : examId,
		examType : 2,
		courseId : courseId,
		dataStr : listStr
	};
	postAjaxRequest(url, data, function(result) {
		debugger;
		console.log(result);
		go2Page("/course/course_viewer/detail.html", "courseId=" + courseId + "&groupId=" + groupId);
	});
}
