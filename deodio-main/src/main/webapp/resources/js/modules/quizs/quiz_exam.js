define(["jquery","utils.dtree","utils.cookie","jquery.dot","libs/step/step_page","utils.menu",
		"pagination","utils","jquery.base","jquery.validate","ueditor",
	    "jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker","jquery.countdown","jquery.ui"], 
	     function($,tree,cookie,doT,step,menu) {
		
	var init = function(){
		countdown(finishTime);
		refreshQuizNo();
		initPageImg();
		
		$("[name^='quiz']").each(function(){
   		 var name = $(this).attr("name");
   		 customInput(name);
   	 	});
		$("#saveQuizForm").myValidate({
		
			formCall : function() {
				saveQuiz();
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

var countdown = function(finishTime){
	var timeBox = $('#time-box');
	var endTime = new Date(finishTime);
	timeBox.countdown(endTime,function(event){
		//时间格式
		var format = event.strftime('倒计时： %H时 %M分 %S秒');			
		timeBox.html(format);
	}).on('finish.countdown', function(){ //时间完成后回调事件
		saveQuiz();
	});
}

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
			} else if (_qtype == 4) {
				$(this).find('div.ti').each(function() {
					var _options = $(this).find('input[type="hidden"]');
					_options.each(function() {
						var isCorrect = $(this).parent().siblings("div").find('input').val();
						optionArray.push($(this).val()+ "_&_"+ isCorrect);
					});

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
			//判断内容项目不能未空
//			if(optionArray.length == 0 || !continueFlg){
//				alertMsg("请设置第" + (i+1) + "题目内容！");
//				//出现错误直接置空
//				dataArray = new Array();
//				return false;
//			}
			
			debugger;
			var dataStr = _qtype + "_#_" + _question + "_#_" + optionArray.join("]=[");
			dataArray.push(dataStr);
	});
	
	return dataArray.join("_@_");
}

var uploadInit = function(id) {
	uploadProcess('uploadFile_' + id, '*.jpg;*.png;',{accountId:cookie.getCookie('CAID'),attachRelType:"20",isModel:true},
			ctx + '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#quizId").val())+'&r=' + Math.floor(Math.random() * 100), 
			function(fileName) {

	}, function(data) {
		var srcimg = imgStatic+data.attachUrl+data.generateName;
			var srcimgTemp = data.generateName;
			$(".l_pic").css({"background":"url("+srcimg+") center no-repeat"});
		$("#uploadFilePath_" + id).val(srcimg);
	}, true, '题目图片');
};

var goBack = function() {
	
	go2Page("/course/course_viewer/detail.html", "courseId="+courseId+"&groupId="+groupId);
};

var initPageImg = function(){
	$('div[qtype="5"]').each(function(){
		var id=$(this).find('input[name="filePath"]').attr("id").split("_")[1];
		uploadInit(id);
	});
};

var refreshQuizNo = function(){
	$("._quiz-order").each(function(i,v){
		  $(this).text(i+1);
	});
	$("._subject-order").each(function(i,v){
		  $(this).text(i+1);
	});
};

var saveQuiz = function() {
	var listStr = getQuestionList();
	if(!listStr){
		return;
	}
	debugger;
	var url = "/quiz/exam/save.html", data = {
		examId : examId,
		examType : 1,
		courseId : courseId,
		startTime : new Date(startTime),
		dataStr : listStr
	};
	
	postAjaxRequest(url, data, function(result) {
		debugger;
		console.log(result);
		go2Page("/quiz/exam/answer_detail.html", "quizId="+examId+"&courseId="+courseId+"&score="+result.data.score);
	});
}
