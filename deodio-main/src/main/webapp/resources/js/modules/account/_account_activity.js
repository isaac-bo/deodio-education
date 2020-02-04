define(["jquery","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.validate"], function($,cookie,doT,paging) {
	
	_init=function(){
		loadOnlineCourseList(1);
		loadOfflineCourseList(1);
		loadQuizList(1);
		loadSurveyList(1);
	};
	searchItems = function(){
		$('#items_keywords').val();
		loadOnlineCourseList(1);
		loadOfflineCourseList(1);
		loadQuizList(1);
		loadSurveyList(1);
	};
	
	
	loadOnlineCourseList = function(pageNo){
		var url="/account/activity/load_online_course.html",data={
				pageNo:pageNo,
				keywords:$('#items_keywords').val()
		};
		postAjaxRequest(url, data, function(result){
			paging.pageTemplateDiv(result,"online_course_table_panel","online_course_table_data_template","online_course_table_page_panle","loadOnlineCourseList");
		});
	};
	
	loadOfflineCourseList = function(pageNo){
		var url="/account/activity/load_offline_course.html",data={
				pageNo:pageNo,
				keywords:$('#items_keywords').val()
		};
		postAjaxRequest(url, data, function(result){
			paging.pageTemplateDiv(result,"offline_course_table_panel","offline_course_table_data_template","offline_course_table_page_panle","loadOfflineCourseList");
		});
	};
	
	loadQuizList=function(pageNo){
		var url="/account/activity/load_quizs.html",data={
				pageNo:pageNo,
				keywords:$('#items_keywords').val()
		};
		postAjaxRequest(url, data, function(result){
			paging.pageTemplateDiv(result,"quiz_table_panle","quiz_table_data_template","quiz_table_page_panle","loadQuizList");
		});
	};
	
	loadSurveyList=function(pageNo){
		var url="/account/activity/load_survey.html",data={
				pageNo:pageNo,
				keywords:$('#items_keywords').val()
		};
		postAjaxRequest(url, data, function(result){
			paging.pageTemplateDiv(result,"survey_table_panle","survey_table_data_template","survey_table_page_panle","loadSurveyList");
		});
	};
	
	_init();
	
}); 