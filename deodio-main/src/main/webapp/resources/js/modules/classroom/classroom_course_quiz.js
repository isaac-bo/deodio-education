define([ "jquery", "utils.cookie","utils", "jquery.base","jquery.scrolltofixed"], function($, cookie, doT) {
	
	var _init = function(callback){
		
		var url="/quiz/classroom/present.html",data={
			quizId:$.trim($("#quizId").val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				callback(result.data);
			}
		});
	};
	
	
	initQuizItems = function(callback){
		_init(callback);
	};
});