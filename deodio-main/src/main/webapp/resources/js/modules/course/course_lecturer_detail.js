define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","jquery.tagator","pagination","utils","jquery.base",
        "jquery.tagsInput","fileupload.common","jquery.validate","ueditor","jquery.scrolltofixed",
        "ueditor.config","jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker"], function($,tree,cookie,list,doT,tagator) {
	
	var init = function() {
		 $('#myTab a:first').tab('show');//初始化显示哪个tab
	     $('#myTab a').click(function (e) {
	    	 e.preventDefault();//阻止a链接的跳转行为
	    	 $(this).tab('show');//显示当前选中的链接及关联的content
	     })
	     
	     $("#period").text( $(".contents_list li").length);
	    
	}
	init();
	
	//跳转到线上课程设置页面
	onCourseOnlineSetting = function(id,courseType){
		gotoCourseOnlineManagerPage('/course/setting.html','courseId=' + id,'courseType='+ courseType);
	};
	
	gotoCourseOnlineManagerPage = function(url,param,param1){
		if(param&&param1){
			window.location.href = ctx + url+"?"+param+"&"+param1+"&r=" + Math.floor(Math.random() * 100);
		}else{
			window.location.href = ctx + url+"?r=" + Math.floor(Math.random() * 100);
		}
	};
	//发布课程
	courseOnlinePublish = function(){
		var courseGroupItemType = 41;
		$("#publishModal").modal("show");
		$('#group_container_type').val(courseGroupItemType);
		$('#item_id').val($("#courseId").val());
	};
});