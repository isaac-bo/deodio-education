<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<form id="informationForm">
	<div class="form_bg">
		<ul class="form_list">
			<li><span class="title"><span class="input-title-span">＊</span>用户昵称</span>
				<div class="right_con">
					<div class="w350">
						<input type="text" value="${userModel.nickName}" class="form-control error-custom" id="nickName" name="nickName" check-type="required" min-max="4-20" data-callback="validateExistName()" call-message="当前用户昵称已被注册"   required-message='请填写用户昵称!' placeholder="请输入用户昵称">
					</div>
					<!-- <p class="f12">4-20位字符,支持汉字、字母、数字及"-"、"_"组合</p> -->
				</div>
				
			</li>
			<li><span class="title">联系电话</span>
			<input type="hidden" id="_noaccount_organization">
				<div class="right_con">
					<div class="w350 pull-left">
						<input type="hidden" value="${userModel.mobilePhone}" id="hiddenOldMobile">
						<input type="text" value="${userModel.mobilePhone}" class="form-control error-custom" id="mobilePhone"  
						data-callback="checkMobileExsist();"  call-message="该手机号已被注册，请输入其他号码！" 
						name="mobilePhone" non-required="true" check-type="mobile" required-message="请输入11位正确手机号码！" placeholder="请输入联系电话">
					</div>
					<div class="pull-left">
					<input id="hiddenIsCheckTel" type="hidden" value="${userModel.isCheckTel}">
					<c:if test="${userModel.isCheckTel==0 && not empty userModel.mobilePhone}">
						<img id="phoneVerifyImg" src="${ctx}/resources/img/account/uncertifed.png">
						<span id="phoneVerifySpan">未认证</span></c:if>
					<c:if test="${userModel.isCheckTel==1 && not empty userModel.mobilePhone}">
						<img id="phoneVerifyImg"  src="${ctx}/resources/img/account/certifed.png">
						<span id="phoneVerifySpan">已认证</span></c:if>
					<c:if test="${empty userModel.mobilePhone}">
						<img id="phoneVerifyImg" src="${ctx}/resources/img/account/uncertifed.png"/>
						<span id="phoneVerifySpan">未认证</span>
					</c:if>
					</div>
				</div>
			</li>
			<li id="personal_val_code" <c:if test="${userModel.isCheckTel==1}">style="display:none"</c:if>><span class="title">短信验证码</span>
				<div class="right_con">
				  <div class="w350 pull-left">
							<input type="text" id="val_code" name="valCode" class="form-control error-custom hb100"  aria-describedby="basic-addon1" 
							<c:if test="${userModel.isCheckTel==0 && userModel.isCheckMail==0}"> check-type="required" data-callback="valCode('btn_smscode')" call-message="验证码错误" required-message="请输入验证码!" placeholder="请输入验证码"</c:if>>	
							<button class="certify certify-inner-button" id="btn_smscode" type="button" onclick="getValidateCode();">获取短信验证码</button>
					</div>
				</div>
		   </li>	
			<%-- <li id="personal_val_code" <c:if test="${userModel.isCheckTel==1}">style="display:none"</c:if>>
				<span class="title">短信验证码</span>
				<div class="right_con">
				  <div class="w350 pull-left">
						<input type="text" check-type="required" data-callback="valOrganizationCode();" call-message="验证码错误" 
							required-message="请输入验证码!" placeholder="请输入验证码" id="val_code" name="val_code" 
							class="form-control error-custom hb100"  aria-describedby="basic-addon1">	
						<button class="certify certify-inner-button" id="btn_smscode" type="button" onclick="getValidateCode();">获取短信验证码</button>
					</div>
				</div>
		    </li> --%>
		   
<%-- 			<c:if test="${userModel.isCheckTel==0}">
			<li><span class="title">短信验证码</span>
				<div class="right_con">
				  <div class="w350 pull-left">
							<input type="text" id="val_code" class="form-control error-custom hb100" placeholder="请输入验证码" aria-describedby="basic-addon1">	
							<button class="certify certify-inner-button" id="btn_smscode" type="button" onclick="getValidateCode();" >获取短信验证码</button>
					</div>
				</div>
				<!-- <div class="right_con">
					<div style="width:350px;display:inline-block;">
						<div class="pull-left w200">
							<input type="text" id="val_code" class="form-control hb100"
												check-type="required" data-callback="valCode()"
												call-message="验证码错误" required-message="请输入验证码"
												placeholder="请输入验证码" aria-describedby="basic-addon1">
							<input type="text" id="val_code" name="valCode" class="form-control hb100" check-type="required" required-message="请输入验证码" placeholder="请输入验证码" aria-describedby="basic-addon1">	
							<div class="form-group-description error-custom"></div>
						</div>
						<div class="pull-left ml10">
								<button class="btn btn-default" id="btn_smscode" type="button"
									onclick="getValidateCode();">获取短信验证码</button>
									<button class="btn btn-default" id="btn_smscode" type="button"
									onclick="alert(123456)">获取短信验证码</button>
						</div>
					</div>
				</div> -->
		   </li>
		   </c:if> --%>
		   <c:if test="${userModel.isCheckTel==1}"><input type="hidden" name="valCode" value="noneed"></c:if>
			<li><span class="title"><span class="input-title-span">＊</span>联系邮箱</span>
				<div class="right_con">
				 <div class="w350 pull-left">
				 	<input type="hidden" value="${userModel.userMail}" id="hiddenOldMail">
					<input type="text" value="${userModel.userMail}"  class="form-control error-custom hb100" check-type="mail" required-message="请输入联系邮箱！" id="userMail" name="userMail" data-callback="validateEmailExists()" call-message="当前邮箱已被注册">
				  		<c:if test="${userModel.isCheckMail==0}"><button class="certify certify-inner-button" type="button" onclick="sendEmailVerify()" style="width:114px;" id="sendMail">发送验证邮件</button></c:if>
				        <c:if test="${userModel.isCheckMail==1}"><button class="certify certify-inner-button" type="button" onclick="sendEmailVerify()" style="width:114px;display:none" id="sendMail">发送验证邮件</button></c:if>
				</div>
				<div class="pull-left">
					<c:if test="${userModel.isCheckMail==1 && not empty userModel.isCheckMail}">
						<img id="userMailVerifyImg" src="${ctx}/resources/img/account/certifed.png">
						<span id="userMailVerifySpan">已认证</span>
					</c:if>
					<c:if test="${userModel.isCheckMail==0 && not empty userModel.isCheckMail}">
						<img id="userMailVerifyImg" src="${ctx}/resources/img/account/uncertifed.png">
						<span id="userMailVerifySpan">未认证</span>
					</c:if>
				</div>
				</div>
				<%-- <div class="right_con">
					<div style="width:350px;display:inline-block;">
						<div class="pull-left w200">
							<input type="text" value="${userModel.userMail}" class="form-control" id="userMail" name="userMail" non-required="true" check-type="mail" required-message="邮箱格式不正确！" placeholder="请输入联系邮箱">
							<div class="form-group-description error-custom"></div>
						</div>
						<c:if test="${userModel.isCheckMail==0}">
						<div class="pull-left ml10">
								<button class="btn btn-default" type="button" onclick="checkCompanyEmail()">发送</button>
						</div>
						</c:if>
						<c:if test="${userModel.isCheckMail==0}"><img src="${ctx}/resources/img/account/uncertifed.png"></c:if>
						<c:if test="${userModel.isCheckMail==1}"><img src="${ctx}/resources/img/account/certifed.png"></c:if>
					</div>	
				</div> --%>
		   </li>
		</ul>
		<input value="${uKeyId}" type="hidden" id="uKeyId" name="uKeyId">
		<input value="${userModel.userMail}" type="hidden" id="userMailOriginal" name="userMailOriginal">
		<div class="next_btn" style="margin: 60px auto 0; width: 220px;">
			<button class="btn btn-default" type="button" btn-type="true">保存/下一步</button>
		</div>
	</div>
</form>

	<script type="text/javascript">
		require([ 'modules/account/_noaccount_information' ], function() {

		});
	</script>
