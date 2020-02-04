<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
   <div class="modal fade" id="surveyCopyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
   <div class="modal-dialog" role="document" style="width:400px;">
   <div class="modal-content" id="surveyCopyModalContent">
   <div class="modal-header" >
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >复制调查问卷</h4>
	</div>
	<div class="modal-body hdn" style="height: 150px;" >
		<div class="form-group">
			<label class="input-title input-title-color">调查问卷名称</label>
			<input type="text" name="newSurveyName" id="newSurveyName"  class="form-control group_title" 
			style="width:202px !important;" check-type="required" required-message="请输入问卷调查名称！"  
			placeholder="请输入问卷调查名称！" aria-describedby="basic-addon1" >			
		</div>
	</div>
	
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" onclick="submitNewSurvey();" btn-type='true' data-dismiss="modal">提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</div>
	</div>
</div>
<script type="text/javascript">
	require(['modules/survey/survey_copy_dialogue','modules/survey/survey_list'],function(){	
	});
</script>
