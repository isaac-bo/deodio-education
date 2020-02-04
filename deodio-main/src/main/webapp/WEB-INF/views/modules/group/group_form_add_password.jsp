<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/login.css">


<body class="body-bg">
	<div class="login_b">
		<div class="l_box">
			<div class="right_b" style="height: 415px;">
				<div class="top_c">
				</div>
				<div class="login-item login-item-color">
					<form id="setPwdForm">
					<div class="box-size">
						<span class="icon-info-sign">&nbsp;</span>请设置您的用户昵称和密码，以便您能顺利的登录系统。
					</div>
		
					<div class="box-size">
						<div class="input-group wb100">
							<span class="input-group-addon" style="border-right: 1px solid #ccc;font-size: 12px;"><span class="input-title-span">＊</span>用户昵称</span>
							<div class="h40 error-custom" style="width: 308px;">
								<input type="text"  class="form-control hb100" style="width:295px;" id="nickName" name="nickName" check-type="required"  data-callback="validateExistName()" call-message="当前用户昵称已被注册"   required-message='请填写用户昵称!' placeholder="请输入用户昵称"  value='<c:if test="${not empty nickName}">${nickName}</c:if>'>
							</div>
						</div>
					</div>
					<div class="box-size">
						<div class="input-group wb100">
							<span class="input-group-addon" style="border-right: 1px solid #ccc;font-size: 12px;"><span class="input-title-span">＊</span>最新密码</span> 
							<div class="h40 error-custom" style="width: 308px;">
								<input type="password"  class="form-control hb100" style="width:295px;"id="password" name="password" check-type="required" required-message="请输入密码！" placeholder="请输入密码"  maxlength="16">
							</div>
						</div>
					</div>
					<div class="box-size">
						<div class="input-group wb100">
							<span class="input-group-addon" style="border-right: 1px solid #ccc;font-size: 12px;"><span class="input-title-span">＊</span>确认密码</span> 
							<div class="h40 error-custom" style="width: 308px;">
								<input type="password"  class="form-control hb100" style="width:295px;" id="conFirmPassword" name="conFirmPassword" check-type="required" required-message="请输入确认密码" placeholder="请输入确认密码"  maxlength="16">
							</div>
						</div>
					</div>
					
					<div class="box-size mt70">
						<button class="btn btn-default" type="button" btn-type="true">提交</button>
					</div>
					</form>
					<div class="share-icon pt10 mt10">
						<ul class="text-center">
							<input value="${uKeyId}" type="hidden" id="uKeyId" name="uKeyId">
							<input value="${gp}" type="hidden" id="gp" name="gp">
							<input type="hidden" value="${ur}" id="ur">
							<input type="hidden" value="${roleLeaderId}" id="roleLeaderId">
							<input type="hidden" value="${roleViewerId}" id="roleViewerId">
							<input type="hidden" value="${roleCreatorId}" id="roleCreatorId">
						</ul>
					</div>
				</div>

			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
	<script type="text/javascript">
		require(['modules/group/group','modules/group/group_form_add_password'],function(obj){
		
		});
	</script>
</body>
</html>



