<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
   <div class="modal fade" id="confirmPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
   <div class="modal-dialog" role="document" style="width:400px;">
   <div class="modal-content" id="surveyCopyModalContent">
   <div class="modal-header" >
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >密码确认</h4>
	</div>
	<div class="modal-body hdn" style="height: 150px;" >
		<div class="form-group" style="display:inline-block;">
			<input type="hidden" id="hidden_confirm_password">
			<input type="hidden" id="hidden_confirm_userName">
			<h4>您的企业账户已经提交注册，请输入注册密码，更新或完善企业信息。</h4>
			<label class="input-title input-title-color">请输入密码</label>
			<input type="text" name="input_password" id="input_password"  class="form-control group_title" 
			style="width:202px !important;" check-type="required" required-message="请确认账户密码！"  
			placeholder="请确认账户密码！" aria-describedby="basic-addon1" >			
		</div>
	</div>
	
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" onclick="submitPassword();" btn-type='true' data-dismiss="modal">确认</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</div>
	</div>
</div>
<script type="text/javascript">
	require(['modules/login/signin'],function(){	
	});
</script>
