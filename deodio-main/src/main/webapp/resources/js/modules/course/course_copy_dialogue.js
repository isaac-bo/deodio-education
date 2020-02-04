define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar"],
		function($, cookie, doT, paging) {
	$('#courseCopyModal').on('shown.bs.modal', function () {
		$("#copyTitle").val("复制课程");
		$('#newCourseName').focus(function(){
			$('#errorMsg').empty();
		});
		var url = "/course/online/getBaseInfo.html",
		params = {
			courseId : $('#_item_id').val(),
			courseName : $("#_item_course_name").val()
		};
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1) {
				$("#newCourseName").val(result.data.course.courseName+"("+result.data.num+")");
			}
		},false);
	});
	$('#courseCopyModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	submitNewCourse = function() {
		if ($.trim($("#newCourseName").val())) {
			if (validateCourseName()) {
				var url="/course/online/copy_course.html",
				data = {
					courseId : $.trim($('#_item_id').val()),
					courseName : $.trim($('#newCourseName').val())
				};
				postAjaxRequest(url, data, function(result){
					$('#courseCopyModal').modal('hide');
					alertMsg("复制成功");
					reloadPageData();
				},false);
			} else {
				$('#errorMsg').text("此课程名称已存在");
			}
		} else {
			$('#newCourseName').attr('placeholder',"请输入课程名称");
		}
	}
	
	validateCourseName = function() {
		var url = "/course/online/validate_course_name.html", 
		data = {courseName : $.trim($("#newCourseName").val())},
		flag = true;
		postAjaxRequest(url, data, function(result){
			if (!result.data) {
				flag = false;
			} 
		}, false);
		return flag;
	}
});
