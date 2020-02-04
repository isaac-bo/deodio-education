define([ "jquery", "utils.cookie","utils.list","utils", "jquery.base", "jquery.scrolltofixed"], function($, cookie, list,doT) {
	
	var _init = function() {
		list.onFixedItems();
	};
	
	_init();

	//返回 设置调查内容 界面
	backSurveyContent = function(){
		var _surveyId = $.trim($("#surveyId").val());
		window.location.href=ctx+"/get/survey/content.html?surveyId="+_surveyId;
	};
	
	getSurveyQuestionList = function(){
		var dataArray = new Array();
		$(".chouti").each(function(i, v) {
			var _qtype = $(this).attr('qtype');
			var _question = $(this).find('div.edit_title div.pl10').html();
			
//			if (_question == "") {
//				alertMsg("请输入第"+(i+1)+"题题目！");
//				return false;
//			}
//				var _order=$(this).find('._quiz-order').html();
			var textarea = "";
			var continueFlg = true;
			var optionArray = new Array();
			if (_qtype <= 4 && _qtype != 3) {
				$(this).find('div.ti').each(function() {
					var _options = $(this).find('div.ti_input_w');
					
					_options.each(function() {
						
						optionArray.push($(this).html()+ "_&_");
					});

				});
			}else if(_qtype == 3){
				$(this).find('div.ti').each(function() {
					var _options = $(this).find('div.ti_input_w');
					_options.each(function() {
						optionArray.push($(this).html()+ "_&_");
					});

				});
			}else if(_qtype == 6){
				textarea = $(this).find('div.jianda textarea').val();
				optionArray.push(textarea);
			}
			
			
//			if(_qtype > 4 && !textarea){
//				continueFlg = false;
//			}
//			
//			
			//判断内容项目不能未空
			if(optionArray.length == 0 || !continueFlg){
				alertMsg("请设置第" + (i+1) + "题目内容！");
				//出现错误直接置空
				dataArray = new Array();
				return false;
			}
			
			var dataStr = _qtype + "_#_" + _question + "_#_" + optionArray.join("]=[");
			dataArray.push(dataStr);
		});
		
		return dataArray.join("_@_");
	}
	
	onSaveSurvey= function(isPublish){
		var url="/survey/saveSurvey.html",data={
				isPublish:isPublish,
				surveyId:$.trim($("#surveyId").val()),
				contentStr:getSurveyQuestionList()
			};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				go2Page('/survey/list.html');
			}else{
				alertMsg("抱歉，因为网络问题该问卷调查保存失败，请重试！");
			}
		});
	}

});
