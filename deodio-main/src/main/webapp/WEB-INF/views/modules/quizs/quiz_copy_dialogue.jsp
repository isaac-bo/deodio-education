<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<div class="modal fade" id="quizCopyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document" style="width:400px;">
        <div class="modal-content" id="quizCopyModalContent">
        <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">复制综合试卷</h4>
        </div>
		<div class="modal-body hdn" style="height: 150px;" >
			<div class="clearfix">
				<label class="input-title input-title-color">综合试卷名称</label>
				<input type="text" name="newQuizName" id="newQuizName" 
					class="form-control group_title" style="width:202px !important;" aria-describedby="basic-addon1"
					placeholder="试卷名称" autocomplete="off">
				<span id="errorMsg" style="color:red;"></span>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn_green btn_160_36" onclick="submitNewQuiz();">提交</button>
			<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	require(['modules/quizs/quiz_copy_dialogue'],function(){	
	});
</script>
