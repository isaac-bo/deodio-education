	define(["jquery","utils.dtree","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.validate","ueditor",
	      "jquery.mCustomScrollbar","bootstrap.select"], function($,tree,cookie,doT) {
		
		var init = function(){
			
			//tab pane事件设置
			$('#myTab a').click(function (e) {
				  e.preventDefault()
				  $(this).tab('show')
			});
		};
		
	/*	//跳转到编辑页面
		onEditCourseOnline = function(id){
			go2Page('/course/online/profile.html',"courseId="+id);
		};*/
		//跳转到线上课程设置页面
		onCourseOnlineSetting = function(id,courseType){
			gotoCourseOnlineManagerPage('/course/setting.html','courseId=' + id,'courseType='+ courseType);
		};
		var gotoCourseOnlineManagerPage = function(url,param,param1){
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
				
		init();
	});

