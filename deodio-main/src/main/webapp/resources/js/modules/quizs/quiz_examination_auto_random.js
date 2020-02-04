define([ "jquery", "utils.cookie","pagination","jquery.dot","utils.list","utils",
	"jquery.base","bootstrap.select"], function($, cookie, paging,doT,list) {
	
	var _init = function() {
		
		list.onFixedItems();
	};

	_init();

	
	onPreview = function() {
		var url = "/quiz/save/random.html",
		quiz = {
			id : $("#quizId").val(),
			subjectList : new Array()
		};
		$(".chouti").each(function(i, v) {
			subject = {
				id : v.id,
				quizSubjectOrder : $(this).find('._quiz-order').html()
			}
			quiz.subjectList[i] = subject;
		});
		postAjaxRequest(url, JSON.stringify(quiz), function(result) {
			go2Page("/quiz/preview.html", "quizId=" + $("#quizId").val() + "&navTabs=2");
		},false,"application/json");
//		var arrays = new Array(); 
//		var orders=new Array();
//		$(".chouti").each(function(){
//			arrays.push($(this).attr("id"));
//		});
//		var url="/quiz/auto/sumbit.html",data={
//				subjectIds:arrays,
//				quizId:$("#quizId").val(),
//				answers:getAnswer()
//		};
//		postAjaxRequest(url, data, function(result){
//			$("#mustForm").submit();
//		});
	};
	
	goBack = function() {
		go2Page("/quiz/auto/required.html", "eFlag=" + 1 + "&returnFlag=" + 1 + "&quizId=" + $("#quizId").val());
	}
	
});
