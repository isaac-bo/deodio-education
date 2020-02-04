define([ "jquery", "utils.cookie","utils", "jquery.base","jquery.scrolltofixed"], function($, cookie, doT) {
	
	var _init = function(callback){
		
		var url="/classroom/survey/present.html",data={
			surveyId:$.trim($("#surveyId").val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				callback(result.data);
			}
		});
	};
	
	
	initSurveyItems = function(callback){
		_init(callback);
	};
});