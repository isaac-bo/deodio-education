<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/login.css">

<body class="body-bg">

<%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>

<input id="userMailHidden" type="hidden" value="">
<input id="userPhoneHidden" type="hidden" value="">
<input id="uKeyId" type="hidden" value="">
	<div class="login_b">
		<div class="l_box" style="width: 500px;">
			<div class="right_b" style="height: 460px; width: 500px;">
				<div class="logo_icon text-center">
					<img src="${ctx}/resources/img/account/logo-2.png" alt="">
				</div>
					<form id="forgetForm">
					<div id="step_1" class="login-item login-item-color item active">
						<div class="step_bg">
							<span>1</span>确认账号
						</div>
						<div class="pt20 text-center">请输入要找回密码的用户名</div>
						<div class="box-size">
							<div class="input-group wb100 error-custom">
								<span class="input-group-addon icons-40px user"></span> 
								<input type="text" placeholder="请输入用户名" id="userName" name="userName" 
								check-type="required"  data-callback="validateUserNameExists()" call-message="当前用户以冻结/不存在."   required-message="用户名不能为空"  class="form-control h40">
							</div>
						</div>
						<div class="box-size">
							<div class="input-group wb100">
								<span class="input-group-addon icons-40px code"></span> 
								<input class="form-control h40" placeholder="请输入验证码" id="val_code" check-type="required"  required-message="请输入验证码" data-callback="valCode()" call-message="验证码错误" type="text" style="width: 120px;"  aria-describedby="basic-addon1" >
								<div class="box-code" style="float:right;width:175px;height:40px;background:#fff;">
									<img id="captcha" src="${ctx}/commons/captcha-image.html">
				                    <a href="javascript:onChangeKaptcha();" class="font-12px"><spring:message code="tips.change.kaptcha"/></a>
								</div>
							</div>
						</div>
						<div class="box-size mt10">
							<button class="btn btn-default" type="button" btn-type="true">下一步</button>
						</div>
					</div>
					<input name="s" type="hidden" value="2">
					</form>


					<div id="step_2" class="login-item login-item-color item" style="height: 301px; display:none;">
						<div class="step_bg">
							<span>2</span>安全验证
						</div>
						<div class="pt20 text-center">请选择以下方式完成身份验证来找回密码。</div>
						<div class="content2">
							<div class="tab-item">
								<ul>
									<li class="li-phone"><a href="javascript:void(0);" id="getPwdByPhone"
										class="a-color on" onclick="choisePhoneFindPwd()"> <span class="icons-48px phone-click"
											style="margin: 0;"></span>
									</a>
										<div class="tab-detail">
											<div class="box-size2" style="margin-top: -30px;">
												<div class="box-size f12" style="margin-bottom:20px;">
													验证密保工具成功后，即可马上设置新密码<br /> 验证方式：<span id="smsMsg" style="color: #03569d;"></span>
												</div>
												<div>
													<div class="input-group pull-left" style="width: 174px;">
														<input type="text" class="form-control h40" id="sms_code" placeholder="请输入验证码" aria-describedby="basic-addon1">
												
													</div>
													<div class="input-group" style="width: 166px; float: right">
														<button class="btn btn-default" type="button" id="btn_smscode" onclick="getSmsCode();">获取验证码</button>
													</div>
													<div class="clearfix"></div>
												</div>
												<div class="pass_text mt20 f12">
													* 如您的密保工具都已经无法使用，请点击此[申诉]. 成功后可更换<br /> *
													申诉为7*24人工处理，90%的用户会在4小时内收到反馈
												</div>
												<div class="mt10">
													<button class="btn btn-default" type="button" onclick="javascript:onChangePwd('phone')">找回密码</button>
												</div>
											</div>

										</div></li>
									<li class="li-email"><a href="javascript:void(0);" id="getPwdByEmail"
										class="a-color" onclick="choiseEmailFindPwd()"> <span class="icons-48px email-"
											style="margin: 0;"></span>
									</a>
										<div class="tab-detail" style="top: -57px; display: none;">
											<div class="box-size2" style="margin-top:-30px;">
												<div class="box-size f12" style="margin-bottom:20px;">
s													验证密保工具成功后，即可马上设置新密码<br /> 验证方式：<span id="emailMsg" style="color: #03569d;"></span>
												</div>
												<div>
													<div class="input-group pull-left" style="width: 174px;">
														<input type="text" class="form-control h40" placeholder="请输入验证码" id="mail_code"
													aria-describedby="basic-addon1">
													</div>
													<div class="input-group" style="width: 166px; float: right">
														<button class="btn btn-default" id="btn_mailcode" onclick="getValidateCode();" type="button">获取验证码</button>
													</div>
													
													<div class="clearfix"></div>
												</div>
												<div class="pass_text mt20 f12">
													* 如您的密保工具都已经无法使用，请点击此[申诉]. 成功后可更换<br /> *
													申诉为7*24人工处理，90%的用户会在4小时内收到反馈
												</div>
												<div class="mt10">
													<button class="btn btn-default" type="button" onclick="javascript:onChangePwd('email')">找回密码</button>
												</div>

											</div>
										</div>
									</li>
								</ul>
							</div>

						</div>
					</div>
					<form id="updateForm">
					<div id="step_3" class="login-item login-item-color item" style="display:none;">
						<div class="step_bg">
							<span>3</span>重置密码
						</div>
						<div class="pt20 text-center">请输入新密码</div>
						<div class="box-size">
							<div class="input-group wb100">
								<span class="input-group-addon icons-40px lock"></span> 
								 <input type="password" id="pwd_id" check-type="passWord" required-message="密码长度必须在6~16之间！" placeholder="设置密码"  class="form-control h40">
							</div>
							<div class="input-group wb100 mt20">
								<span class="input-group-addon icons-40px lock"></span> 
								<input type="password" check-type="confirmPwd" required-message="请确认两次密码一致"  placeholder="确认密码"  class="form-control h40">
							</div>
						</div>
						<div class="box-size mt10">
							<button class="btn btn-default" type="button" btn-type="true">提交</button>
						</div>
					</div>
					</form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>



		<%-- <div class="header">
		<a class="logo" href="javascript:void(0);"></a>
	</div>
	<nav class="nav nav-bg">
		<ul class="nav-ul">
			<li id="tab-1" <c:if test="${ empty hidStep  || hidStep=='1'}"> class="on"</c:if>>
	          <label class="step-1 <c:if test="${ empty hidStep  || hidStep=='1'}"> on</c:if>">确认账号</label>
	          <span class="hang-pic"></span>
	        </li>
	        <li id="tab-2" <c:if test="${hidStep=='2'}"> class="on"</c:if>>
	          <label class="step-2<c:if test="${hidStep=='2'}"> on</c:if>">安全验证</label>
	          <span class="hang-pic"></span>
	        </li>
	        <li id="tab-3" <c:if test="${hidStep=='3'}"> class="on"</c:if>>
	          <label class="step-3<c:if test="${hidStep=='3'}"> on</c:if>">重设密码</label>
	          <span class="hang-pic"></span>
	        </li>
		</ul>
	</nav>
	<div class="content">
	
	

	
	<c:choose>
	
		<c:when test="${ empty hidStep  || hidStep=='1'}">
			<form action="${ctx}/signin/forget_pwd.html" method="post" id="forgetForm">
				<div id="step-1">
					<h6>找回密码描述文字找回密码描述文字找回密码描述文字找回密码描述文字</h6>
					<div class="form-inline" style="width: 420px; margin: 0 auto;">
						<div class="form-group " style="margin-top: 0px;">
							<label class="input-title input-title-color"   style="color: #3f8dcf;">用户名</label>
							<input type="text" id="userName" name="userName" check-type="required"  data-callback="validateUserNameExists()" call-message="当前用户以冻结/不存在."   required-message="用户名不能为空"  class="form-control h40">
							<div class="form-group-description error-custom">
								<span class="icons-14px"></span>
							</div>
						</div>
						<div class="form-group " style="margin-top: 10px;">
							<label class="input-title input-title-color"   style="color: #3f8dcf;">验证码</label>
							<input class="form-control h40" id="val_code" check-type="required"  required-message="请输入验证码" data-callback="valCode()" call-message="验证码错误" type="text" style="width: 75px;"  aria-describedby="basic-addon1" >
								<div class="box-code" style="float:right;width:175px;height:40px;background:#fff;">
									<img id="captcha" src="${ctx}/commons/captcha-image.html">
				                    <a href="javascript:onChangeKaptcha();" class="font-12px"><spring:message code="tips.change.kaptcha"/></a>
								</div>
								<div class="form-group-description error-custom">
									<span class="icons-14px"></span>
								</div>
						</div>
					</div>
					<input name="s" type="hidden" value="2">
					<div class="form-inline text-center" style="margin-top: 45px;">
						<button type="button" class="btn next" btn-type="true">下一步</button>
						<button type="button" class="btn cancel">取消</button>
					</div>
				</div>			
			</form>			
		</c:when>
		
		<c:when test="${hidStep == '3'}">
			<form action="${ctx}/signin/forget_pwd.html" method="post" id="forgetForm">
				<div id="step-2" >
					<h6>为了您的帐号安全，请选择以下方式完成身份验证来找回密码。</h6>
					<div class="tab-item">
						<ul>
							<li style="margin: 0;" class="li-phone">
								<a href="javascript:void(0);" id="phone" class="a-color on"> 
									<span class="icons-48px phone-click"></span> <b class="b">短信找回</b>
								</a>
								<div class="tab-detail" style="display: block;">
									<div class="box-size f14" style="line-height: 33px;">
										验证密保工具成功后，即可马上设置新密码<br /> 验证方式：<span style="color: #03569d;">短信验证:${fn:substring(userModel.mobilePhone,'0','3')}****${fn:substring(userModel.mobilePhone,'8','11')}</span>
									</div>
									<div class="box-size">
										<form class="form-inline">
											<div class="input-group">
												<input type="text" class="form-control h40" id="sms_code" placeholder="请输入验证码" aria-describedby="basic-addon1" style="width: 170px;">&nbsp;&nbsp;
												<button class="btn" type="button" id="btn_smscode" onclick="getSmsCode();">发送验证码</button>
											
											</div>
										</form>
									</div>
		
									<div class="box-size f12">
										* 如您的密保工具都已经无法使用，请点击此[申诉]更换。<br /> 
										* 申诉为<span style="color: red;">7*24</span>人工处理，90%的用户会在4小时内收到反馈。
									</div>
								</div></li>
							<li class="li-email">
								<a href="javascript:void(0);" id="email"  class="a-color"> 
									<span class="icons-48px email-"></span> <b class="b">邮件找回</b>
								</a>
								<div class="tab-detail" style="top: -120px; display: none;">
									<div class="box-size f14" style="line-height: 33px;">
									
									<c:set var="strLength" value="${fn:length(fn:substringBefore(userModel.userMail, '@'))}"/>
										验证密保工具成功后，即可马上设置新密码<br /> 验证方式：<span style="color: #03569d;">密保邮箱:****${fn:substring(fn:substringBefore(userModel.userMail, '@'),strLength-2,strLength)}@${fn:substringAfter(userModel.userMail, '@')}</span>
									</div>
									<div class="box-size">
										<form class="form-inline">
											<div class="input-group">
												<input type="text" class="form-control h40" placeholder="请输入验证码" id="mail_code"
													aria-describedby="basic-addon1"
													style="width: 170px;">&nbsp;&nbsp;
												<button class="btn" id="btn_mailcode" onclick="getValidateCode();" type="button">发送验证邮件</button>
											</div>
										</form>
									</div>
		
									<div class="box-size f12">
										* 如您的密保工具都已经无法使用，请点击此[申诉]更换。<br /> 
										* 申诉为<span style="color: red;">7*24</span>人工处理，90%的用户会在4小时内收到反馈。
									</div>
								</div></li>
		
						</ul>
					</div>
					<input name="s" type="hidden" value="3">
					<div class="form-inline" style="text-align: center;margin-top:45px;">
					
						<button type="button" class="btn next" onclick="onNextStep();">下一步</button>
						<button type="button" class="btn cancel">取消</button>
					</div>
				</div>
				<input name="uKeyId" type="hidden" value="${userModel.id}">
				<input name="uvmail" id="uvmail" type="hidden" value="${userModel.userMail}">
				<input name="uvmobile"  id="uvmobile" type="hidden" value="${userModel.mobilePhone}">
		</form>
		</c:when>
		<c:when test="${hidStep=='2'}">
			<form id="updateForm">
			    <div id="step-3" >
			        <h6>为了您的帐号安全，请选择以下方式完成身份验证来找回密码。</h6>
			        <div class="form-inline" style="width:420px;margin:0 auto;">
				        <div class="form-group" style="margin-top:0px;">
				          <label class="input-title input-title-color" style="color: #3f8dcf;">新密码</label>
				          <input type="password" id="pwd_id" check-type="passWord" required-message="密码长度必须在6~16之间！" placeholder="设置密码"  class="form-control h40">
				        <div class="error-custom">
				        
				        </div>
				        
				        </div>
				        <div class="form-group" style="margin-top:16px;">
				          <label class="input-title input-title-color" style="color:#3f8dcf;">确认密码</label>
				          <input type="password" check-type="confirmPwd" required-message="请确认两次密码一致"  placeholder="确认密码"  class="form-control h40">
					       	 <div class="error-custom">
					        
					        </div>
				       
				        </div>
			        </div>
			        <div class="form-inline" style="text-align:center;margin-top:45px;">
				      
				        <button type="button" class="btn next" btn-type="true">提交</button>
				        <button type="button" class="btn cancel">取消</button>
			        </div>
			    </div>
				<input name="uKeyId" type="hidden" value="${uKeyId}">
		</form>		
		</c:when>
	</c:choose>
	
	
		
		
		
	</div>
	
	
	
	<div class="footer footer-color-blue position-bottom">
		<%@ include file="/WEB-INF/views/commons/_copyright.jsp"%>
	</div> --%>
	
		<script type="text/javascript">
			require([ 'modules/login/forget.password' ], function() {

			});
		</script>
</body>
</html>