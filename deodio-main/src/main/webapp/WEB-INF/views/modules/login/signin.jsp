<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/login.css">

<body class="body-bg">

	<div class="content" style="margin-top: 1px; background: inherit;">
		<div id="signin-panel" class="tab-item" 
			<c:choose>
				<c:when test="${emailVerified==1}">
					style="display:none"
				</c:when>
				<c:otherwise>
					style="display:block"
				</c:otherwise>
			</c:choose> >
			<div class="logo_icon text-center">
				<img src="${ctx}/resources/img/account/logo-2.png" alt="">
			</div>
			<ul>
				<li style="margin: 0;" class="li-phone"><a id="phone"
					class="a-color on" onclick="onTabs('phone')"> <span
						class="icons-48px phone-click"></span> <b class="b">手机注册</b>
				</a>
					<div class="tab-detail" style="display: block;">
						<div class="box-size">
							<div class="pull-left f18">手机注册</div>
							<div class="pull-right">
								已有账号？<a class="point-font" href="javascript:go2Page('/login.html')">马上登录</a>
							</div>
						</div>
						<form action="${ctx}/userSiginIn.html" method="post"
							id="mobileForm">
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px phone"></span>
									<div class="error-div h40" style="width: 308px;">
										<input type="text" class="form-control hb100" id="mobileNumber"
											check-type="mobile" data-callback="validateUserNameExists()"
											call-message="当前号码已被注册" required-message="请输入11位正确手机号码！"
											placeholder="请输入手机号码" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group pull-left" style="width: 166px; float: left;">
									<span class="input-group-addon icons-40px code"></span>
									<div class="error-div h40">
										<input type="text" id="val_code" class="form-control hb100"
											check-type="required" data-callback="valCode()"
											call-message="验证码错误" required-message="请输入验证码"
											placeholder="请输入验证码" aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="input-group" style="float: right; width: 166px; height: 40px;">
									<button class="btn btn-default" id="btn_smscode" type="button"
										onclick="getValidateCode();">获取短信验证码</button>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px lock"></span>
									<div class="error-div h40" style="width: 308px;">
										<input type="password" class="form-control  hb100"
											id="mobilePassword" check-type="passWord"
											required-message="密码长度必须在6~16之间！" placeholder="设置密码"
											aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group wb100" >
									<span class="input-group-addon icons-40px lock"></span>
									<div class="error-div h40" style="width: 308px;">
										<input type="password" class="form-control  hb100"
											check-type="confirmPwd" required-message="请确认两次密码一致"
											placeholder="确认密码" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<button class="btn btn-default" type="button" btn-type="true">注&nbsp;&nbsp;&nbsp;册</button>
							</div>
						</form>
					</div></li>
				<li class="li-email">
					<a id="email" onclick="onTabs('email');" class="a-color"> 
						<span class="icons-48px email-"></span> <b class="b">邮箱注册</b>
					</a>
					
					<div class="tab-detail" style="top: -129px; display: none;">
						<div class="box-size">
							<div class="pull-left f18">邮箱注册</div>
							<div class="pull-right">
								已有账号？<a class="point-font" href="javascript:go2Page('/login.html')">马上登录</a>
							</div>
						</div>
						<form action="${ctx}/userSiginIn.html" method="post"
							id="emailForm">
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px email"></span>
									<div class="error-div h40" style="width: 308px;">
										<input type="text" class="form-control hb100" id="emailName"
											check-type="mail" data-callback="validateUserNameExists()"
											call-message="当前邮箱已被注册" placeholder="请输入邮箱"
											aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px lock"></span> 
									<div class="error-div h40" style="width: 308px;">
										<input	type="password" class="form-control  hb100" id="emailPassWord"
											check-type="passWord" required-message="密码长度必须在6~16之间！"
											placeholder="设置密码" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px lock"></span> 
									<div class="error-div h40" style="width: 308px;">
										<input type="password" class="form-control hb100"
											check-type="confirmPwd" required-message="请确认两次密码一致"
											placeholder="确认密码" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size" style="margin-top: 82px;">
								<button class="btn btn-default" type="button" btn-type="true">注&nbsp;&nbsp;&nbsp;册</button>
							</div>
						</form>
						<input type="hidden" id="userVerifiedEmail" name="userVerifiedEmail" value="${userVerifiedEmail}"/>
						<input type="hidden" id="errorVerifyCode" name="errorVerifyCode" value="${errorVerifyCode}"/>
					</div>
				</li>
				<li class="li-other">
					<a id="company" onclick="onTabs('company');" class="a-color"> 
						<span class="icons-48px other-"></span><b class="b">企业注册</b>
					</a>
					<div class="tab-detail" style="top: -258px; display: none;">
						<div class="box-size">
							<div class="pull-left f18">企业注册</div>
							<div class="pull-right">
								已有账号？<a class="point-font" href="javascript:go2Page('/login.html')">马上登录</a>
							</div>
						</div>
						<form action="${ctx}/userSiginIn.html" method="post"
							id="companyForm">
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px user"></span>
									<div class="error-div h40" style="width: 308px;">
										<input type="text" class="form-control hb100" id="companyName"
											data-callback="validateUserNameExists()"   check-type="required"
											call-message="当前企业已被注册" placeholder="请输入企业名称或编号"
											aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px lock"></span> 
									<div class="error-div h40" style="width: 308px;">
										<input	type="password" class="form-control  hb100" id="companyPassWord"
										check-type="passWord" required-message="密码长度必须在6~16之间！"
										placeholder="设置密码" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px lock"></span> 
									<div class="error-div h40" style="width: 308px;">
										<input type="password" class="form-control hb100"
										check-type="confirmPwd" required-message="请确认两次密码一致"
										placeholder="确认密码" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size">
								<div class="input-group wb100">
									<span class="input-group-addon icons-40px lock"></span>
									<div class="error-div h40" style="width: 308px;">
										<input type="text" class="form-control hb100" id="companyIndustry"
											placeholder="请输入企业行业" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="box-size" >
								<button class="btn btn-default" type="button" btn-type="true">注&nbsp;&nbsp;&nbsp;册</button>
							</div>
						</form>
					</div>
					<!-- <div class="tab-detail" style="top: -240px; display: none;">
						<div class="box-size"
							style="margin-top: 85px; height: 145px; text-align: center;">
							<h1 class="h1">注册成功</h1>
							<p class="p text-left f16 mt20">
								如果需要注册成Account，或完成详细注册，请点击<span class="ca40000">详细注册</span>按钮来完成详细注册。
							</p>
						</div>
						<div class="box-size">
							<button class="btn btn-default pull-left" type="submit"
								style="width: 150px;"
								onclick="javascrtpt:window.location.href='detail.html'">详细注册</button>
							<button class="btn btn-default pull-right" type="submit"
								style="width: 150px; background: #b9d3e5; color: #4b5f74;">关闭</button>
						</div>
					</div> -->
				</li>
			</ul>
		</div>
		<div id="signin-success" class="tab-item signin-success" 
			<c:choose>
				<c:when test="${emailVerified==1}">
					style="display:block;width:440px;"
				</c:when>
				<c:otherwise>
					style="display:none;width:440px;"
				</c:otherwise>
			</c:choose> > 
			<div class="logo_icon text-center">
				<img src="${ctx}/resources/img/account/logo-2.png" alt="">
				<%-- <img src="${ctx}/resources/img/login_logo.png" alt=""> --%>
			</div>
			<div class="box-size" style="margin-top: 85px; height: 145px;">
				<span style="font-size:24px;" id="registerSuccessTip">提交成功，账户审核结果将通过邮件或短信发送给您。</span>
<!-- 				<span style="font-size:24px;">注册成功</span>
				<p class="p mt20" id="registerSuccessTip">
				</p>
				<p class="p mt20">
					如果需要注册成Account，或完成详细注册，请点击<span class="ca40000">详细注册</span>按钮来完成详细注册。
				</p> -->
			</div>
			<div class="box-size" id="registerBoxSize">
				<button class="btn btn-default pull-left" type="button"
					style="width: 150px;"
					onclick="onDetails()">详细注册</button>
				<button class="btn btn-default pull-right" type="button"
					style="width: 150px; background: #b9d3e5; color: #4b5f74;" onclick="onCancel()">关闭</button>
			</div>
		</div>
		
		<div id="signin-success-login" class="tab-item signin-success" style="display: none;width:440px;">
			<div class="logo_icon text-center">
				<img src="${ctx}/resources/img/account/logo-2.png" alt="">
			</div>
			<div class="box-size" style="margin-top: 85px; height: 145px;">
				<span style="font-size:24px;">注册成功</span>
				<p class="p mt20">
					注册成功，跳转到登录页面进行登录。
				</p>
			</div>
			<div class="box-size">
				<button class="btn btn-default pull-left" type="button"
					style="width: auto;"
					onclick="onCancel()">重新登录</button>
			</div>
		</div>
	</div>


	<%-- <div class="body-bg register-body-bg-color"></div>
	<div class="header">
		<a class="logo" href="javascript:void(0);"></a>
	</div>
	<div class="content">
		<div id="signin-panel" class="tab-item">
			<ul>
				<li style="margin: 0;" class="li-phone">
					<a id="phone" href="javascript:onTabs('phone');" class="a-color on"> 
						<span class="icons-48px phone-click"></span> 
						<b class="b">手机注册</b>
					</a>
					<div class="tab-detail" style="display: block;">
						<div class="box-size cfff">
							<div class="pull-left f18">手机注册</div>
							<div class="pull-right">
								已有账号？<a class="point-font ca40000" href="login.html">马上登录</a>
							</div>
						</div>
						<form action="${ctx}/userSiginIn.html" method="post" id="mobileForm">
						<div class="box-size error-div">
							<div class="input-group wb100"">
								<span class="input-group-addon icons-40px phone" id="basic-addon1"></span> 
								<input type="text" class="form-control h40" id="mobileNumber" check-type="mobile" data-callback="validateUserNameExists()" call-message="当前号码已被注册" required-message="请输入11位正确手机号码！"  placeholder="请输入手机号码" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="box-size error-div">
							<div class="input-group" style="width: 174px; float: left;">
								<span class="input-group-addon icons-40px code" id="basic-addon1"></span>
								<input type="text" id="val_code" class="form-control  h40" check-type="required" data-callback="valCode()" call-message="验证码错误" required-message="请输入验证码" placeholder="请输入验证码" aria-describedby="basic-addon1">
							</div>
							<div class="input-group" style="width: 166px; float: right">
								<button class="btn btn-default" id="btn_smscode" type="button" onclick="getValidateCode();">获取短信验证码</button>
							</div>
						</div>
						<div class="box-size error-div">
							<div class="input-group wb100">
								<span class="input-group-addon icons-40px lock" id="basic-addon1"></span>
								<input type="password" class="form-control  h40" id="mobilePassword" check-type="passWord" required-message="密码长度必须在6~16之间！"  placeholder="设置密码" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="box-size error-div">
							<div class="input-group wb100">
								<span class="input-group-addon icons-40px lock" id="basic-addon1"></span>
								<input type="password" class="form-control  h40" check-type="confirmPwd" required-message="请确认两次密码一致" placeholder="确认密码" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="box-size">
							<button class="btn btn-default" type="button" btn-type="true" >注&nbsp;&nbsp;&nbsp;册</button>
						</div>
						</form>
					</div>
				</li>
				<li class="li-email">
					<a id="email" href="javascript:onTabs('email');" class="a-color"> 
						<span class="icons-48px email-"></span> 
						<b class="b">邮箱注册</b>
					</a>
					<div class="tab-detail" style="top: -120px; display: none; height:390px;">
						<div class="box-size cfff">
							<div class="pull-left f18">邮箱注册</div>
							<div class="pull-right">
								已有账号？<a class="point-font ca40000" href="login.html">马上登录</a>
							</div>
						</div>
						<form action="${ctx}/userSiginIn.html" method="post" id="emailForm">
						<div class="box-size error-div">
							<div class="input-group wb100">
								<span class="input-group-addon icons-40px email" id="basic-addon1"></span> 
								<input type="text" class="form-control h40" id="emailName" check-type="mail" data-callback="validateUserNameExists()"  call-message="当前邮箱已被注册"  placeholder="请输入邮箱" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="box-size error-div">
							<div class="input-group" style="width: 154px; float: left;">
								<span class="input-group-addon icons-40px code" id="basic-addon1"></span>
								<input type="text" id="email_val_code" class="form-control  h40" check-type="required" data-callback="valCode()" call-message="验证码错误" placeholder="请输入验证码" aria-describedby="basic-addon1">
							</div>
							<div class="input-group" style="width: 192px; float: right">
								<img id="captcha" src="${ctx}/commons/captcha-image.html">
								<button class="btn btn-default" type="button" style="width:59px; padding:0px;" onclick="javascript:onChangeKaptcha()"><spring:message code="tips.change.kaptcha"/></button>
							</div>
						</div>
						<div class="box-size error-div">
							<div class="input-group  wb100">
								<span class="input-group-addon icons-40px lock" id="basic-addon1"></span>
								<input type="password" class="form-control  h40" id="emailPassWord" check-type="passWord" required-message="密码长度必须在6~16之间！" placeholder="设置密码" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="box-size error-div">
							<div class="input-group  wb100">
								<span class="input-group-addon icons-40px lock" id="basic-addon1"></span>
								<input type="password" class="form-control h40" check-type="confirmPwd" required-message="请确认两次密码一致"  placeholder="确认密码" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="box-size">
							<button class="btn btn-default" type="button" btn-type="true" >注&nbsp;&nbsp;&nbsp;册</button>
						</div>
					</form>	
					</div>
				</li>
				<li class="li-other">
					<a id="other" href="javascript:void(0);" class="a-color"> 
						<span class="icons-48px other-"></span> 
						<b class="b">其他注册</b>
					</a>
					<div class="tab-detail" style="top: -240px; display: none;">
						<div class="box-size font-color-white" style="margin-top: 85px; height: 145px; text-align: center;">
							<h1 class="h1">注册成功</h1>
							<p class="p text-left font-18px">
								如果需要注册成Account，或完成详细注册，请点击<span class="point-font">详细注册</span>按钮来完成详细注册
							</p>
						</div>
						<div class="box-size">
							<button class="btn btn-default float-left" type="submit" style="width: 150px;" onclick="javascrtpt:window.location.href='detail.html'">详细注册</button>
							<button class="btn btn-default float-right" type="submit" style="width: 150px; background: #b9d3e5; color: #4b5f74;">关闭</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div id="signin-success" class="tab-item" style="display: none;width:440px;">
			<div class="register-finish">
				<div class="box-size cfff" style="margin-top: 85px; height: 145px;">
					<h1 class="h1">注册成功</h1>
					<p class="p">
						恭喜您，已经成功注册成为Account，如需填写详细注册信息，请点击<span class="ca40000">详细注册</span>
					</p>
				</div>
				<div class="box-size">
					<button class="btn btn-default pull-left" type="button" style="width: 150px;" onclick="javascript:onDetails()">详细注册</button>
					<button class="btn btn-default pull-right" type="button" style="width: 150px; background: #b9d3e5; color: #4b5f74;" onclick="javascript:onCancel()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="footer footer-color-gray position-bottom">
		<%@ include file="/WEB-INF/views/commons/_copyright.jsp"%>
	</div> --%>	
	<input value="${uKeyId}" type="hidden" id="uKeyId">
	<input value="${emailVerified}" type="hidden" id="emailVerify">
	<%@ include file="/WEB-INF/views/modules/login/confirm_password_dialogue.jsp"%>
	<script type="text/javascript">
		require(['modules/login/signin'],function(){
			
		});
	</script>

</body>
</html>