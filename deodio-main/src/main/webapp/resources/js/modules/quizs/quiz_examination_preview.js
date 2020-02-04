define([ "jquery", "utils.cookie","utils", "jquery.base","jquery.scrolltofixed"], function($, cookie, doT) {

	
	var _init = function() {
		
	};
	
	_init();

	onSave = function(type) {
		var url = "/quiz/publish.html", 
		data = {
			quizId : $("#quizId").val(),
			type : type,
			navTabs : $("#navTabs").val()
		};
		postAjaxRequest(url, data, function(result) {
			if(result.status == 1) {
				go2Page('/quiz/list.html');
			} else {
				alertMsg("抱歉，因为网络问题该试卷发布失败，请重新发布！");
			}
		});
	}
	
//	onPreview = function() {		
//		$("#quizContent").val($("#draggableContent").html());
//		
//		var url="/quiz/submit.html", data={
//				quizId:$("#quizId").val(),			
//				quizContent:$("#draggableContent").html()
//		};
//		
//		postAjaxRequest(url, data,function(result){
//			go2Page("/quiz/rules.html","eFlag="+$("#eFlag").val()+"&quizId="+$("#quizId").val()+"&navTabs="+$("#hid_navTabs").val());
//		});
//		
//		
//	};
	
	goBack = function() {
		if($("#navTabs").val() == "1") {
			go2Page("/quiz/manual/content.html","quizId="+$("#quizId").val()+"&eFlag="+$("#eFlag").val()+"&navTabs="+$("#navTabs").val());
		} else if($("#navTabs").val() == "2") {
			go2Page("/quiz/auto/random.html","quizId=" + $("#quizId").val() +"&eFlag=1" + "&navTabs="+$("#navTabs").val());
		} 
	}
});
