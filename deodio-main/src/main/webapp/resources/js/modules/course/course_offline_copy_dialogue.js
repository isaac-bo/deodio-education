define(
		[ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar" ,"bootstrap.select"],
		function($, cookie, doT, paging) {
	//modal打开事件
	$('#courseCopyModal').on('shown.bs.modal', function () {
		initloadCourseModalContent();
	});
	$('#courseCopyModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	var initloadCourseModalContent = function(){
		 //初始化页面
		$("#copyTitle").val("复制课程");
		loadCourseData();
	}
	var loadCourseData = function() {
		var params = {
			courseId : $("#_item_id").val()
		}, url = "/course/offline/profile/baseInfo.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				$("#newCourseName").val(result.data.courseName+"(1)");
			}
		});
	};
});
