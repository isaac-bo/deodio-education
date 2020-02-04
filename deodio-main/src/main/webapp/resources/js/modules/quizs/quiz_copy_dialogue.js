define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils", "jquery.base", "jquery.validate", "bootstrap.datepicker", 
	"jquery.ui", "jquery.mCustomScrollbar" ,"bootstrap.select"], function($, cookie, doT, paging) {
	$('#quizCopyModal').on('shown.bs.modal', function () {
		$("#copyTitle").val("复制综合试卷");
		$('#newQuizName').focus(function(){
			$('#errorMsg').empty();
		});
		var url = "/quiz/getBaseInfo.html",
		params = {
			quizId : $('#_item_id').val(),
			quizName : $("#_item_name").val()
		};
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				$("#newQuizName").val(result.data.quiz.quizName+"("+result.data.num+")");
			}
		},false);
	});

	$('#quizCopyModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	
	submitNewQuiz = function(){
		if ($.trim($("#newQuizName").val())) {
			if (validateQuizName()) {
				var url="/quiz/copyQuiz.html",data={
					quizId : $.trim($('#_item_id').val()),
					quizName : $.trim($('#newQuizName').val())
				};
				postAjaxRequest(url, data, function(result){
					$('#quizCopyModal').modal('hide');
					alertMsg("复制成功");
					mediumIconShow(1);
				},false);
			} else {
				$('#errorMsg').text("此试卷名称已存在");
			}
		} else {
			$('input').attr('placeholder',"请输入试卷名称");
		}
	}
	
	validateQuizName = function() {
		var url = "/quiz/check/quizname.html", 
		data = {quizName : $.trim($("#newQuizName").val())},
		flag = true;
		postAjaxRequest(url, data, function(result){
			if (!result.data) {
				flag = false;
			} 
		}, false);
		return flag;
	}
});
