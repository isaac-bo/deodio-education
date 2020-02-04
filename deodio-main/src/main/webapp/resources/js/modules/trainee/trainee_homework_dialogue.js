define([ "jquery", "utils.dtree", "utils.cookie", "utils.list", "jquery.dot",
				"pagination", "utils", "jquery.scrolltofixed",
				"jquery.scroll.pagination", "jquery.validate",
				"bootstrap.select", "jquery.base", "bootstrap.datepicker",
	           "jquery.mCustomScrollbar","jquery.ui","fileupload.common"],
		function($, tree, cookie, list, doT, paging) {
	//modal打开事件
	$('#homeworkModal').on('shown.bs.modal', function () {	
		initloadHomeworkModalContent();	
	});
	
	//modal关闭事件
	$('#homeworkModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		$("#course_homework_template_modal").html('');
	});
	var initloadHomeworkModalContent = function(){	
		var homeworkId=$("#_item_id").val();
		 //初始化页面
		var params = {
				homeworkId :homeworkId
			}, url = "/course/get_homework_info.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status){
				var data= result.data[0];
				data.modal_tittle = "作业";
				var template =  doT.template($("#course_homework_template_modal").text());
				$("#homeworkModalContent").html(template(data));
				//-------上传作业------------
				var url = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#_item_id").val())+'&r=' + Math.floor(Math.random() * 100);
				uploadFileJqProcess('xhomeworkUploadFile',url,
					 {accountId:cookie.getCookie('CAID'),attachRelType:"12",isModel:true},
					 1,'*',true,function(e,data){
						$("#homeworkFileName").val(data.result.data.attachName);
						$("#homeworkAttachment").val(data.result.data.generateName);
						$("#homeworkAttachmentId").val(data.result.data.id);
					},function(){
						
					},function(data){
					});
			}
		});	
	}
	deleteFileAttach=function(zipFileName,attachName,attachId){
		$("#"+zipFileName).val('');
		$("#"+attachName).val('');
		$("#"+attachId).val('');
	}
	traineeSubmitHomework=function(){
		var homeworkData = {
				courseId:$("#hiddenCourseId").val(),
				homeworkId:$("#homeworkId").val(),
				attachUpload:$("#homeworkFileName").val(),
				remark:$("#remark").val()
		};
		var url = "/trainee/submitHomework.html", data = {
				homeworkJson:JSON.stringify(homeworkData),
				attachId:$("#homeworkAttachmentId").val()
		}
		postAjaxRequest(url, data, function(result) {
			alertMsg("提交试卷成功")
			go2Page("/course/course_viewer/online_detail.html", "courseId=" + $("#hiddenCourseId").val());
		});
	}
});
