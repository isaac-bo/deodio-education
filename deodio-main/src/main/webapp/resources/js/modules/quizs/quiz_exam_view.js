define(["jquery","utils.dtree","utils.cookie","jquery.dot","libs/step/step_page","utils.menu",
		"pagination","utils","jquery.base","jquery.validate","ueditor",
	    "jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker","jquery.countdown","jquery.ui"], 
	     function($,tree,cookie,doT,step,menu) {
		
	var init = function(){
		refreshQuizNo();
		$("[name^='quiz']").each(function(){
   		 var name = $(this).attr("name");
   		 customInput(name);
   	 	});
	};
	
	init();	
});

var goBack = function() {
	
	go2Page("/course/course_viewer/detail.html", "courseId=" + courseId + "&groupId=" + groupId);
};

var refreshQuizNo = function(){
	$("._quiz-order").each(function(i,v){
		  $(this).text(i+1);
	});
	$("._subject-order").each(function(i,v){
		  $(this).text(i+1);
	});
};


