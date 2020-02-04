<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<form id="accountInformationForm">
	<div class="form_bg">
		<ul class="form_list">
			<li><span class="title"><span class="input-title-span">＊</span>用户昵称</span>
				<div class="right_con">
					<div class="w350 pull-left">
						<input type="text" value="${userModel.nickName}" class="form-control error-custom" id="nickName" name="nickName" check-type="required" min-max="4-20"    required-message='请填写用户昵称!' placeholder="请输入用户昵称">
					</div>
					<!-- <p class="f12">4-20位字符,支持汉字、字母、数字及"-"、"_"组合</p> -->
				</div>
				
			</li>
			<li><span class="title">用户ID</span>
				<div class="right_con">
					<div class="w350 pull-left">
						<input type="text" value="${accountDomain}" class="form-control error-custom" id="accountDomain" name="accountDomain" non-required="true" check-type="char" required-message="请输入正确英文字符" data-callback="validateExistDomain()" call-message="当前用户ID已被注册"   placeholder="请输入用户ID">
					</div>
				</div>
			</li>
			<li><span class="title">联系电话</span>
			<input type="hidden" id="_account_information">
				<div class="right_con">
					<div class="w350 pull-left">
						<input type="hidden" value="${userModel.mobilePhone}" id="hiddenOldMobile">
						<input type="text" value="${userModel.mobilePhone}"" class="form-control error-custom" id="mobilePhone"  
						data-callback="checkMobileExsist()"  call-message="该手机号已被注册，请输入其他号码！" 
						name="mobilePhone" non-required="true" check-type="mobile" required-message="请输入11位正确手机号码！" 
						placeholder="请输入联系电话">
					</div>
					<div class="pull-left">
					<input id="hiddenIsCheckTel" value="${userModel.isCheckTel}" type="hidden">
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
			<li><span class="title">联系邮箱</span>
				<div class="right_con">
				 <div class="w350 pull-left">
				 	<input type="hidden" value="${userModel.userMail}" id="hiddenOldMail">
					<input type="text" value="${userModel.userMail}"  class="form-control error-custom hb100" check-type="mail" non-required="true"
						required-message="请输入正确的邮箱地址" id="userMail" name="userMail" data-callback="validateEmailExists()" call-message="当前邮箱已被注册">
					
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
		   </li>
		   <li><span class="title">联系地址</span>
				<div class="right_con">
					<div class="form-group">
							<div class="ul-select select-group">

								<select class="nice-select selectpicker"
									onchange="selectProince('addressCountry','addressProvince')" name="addressCountry"
									id="addressCountry" data-width="100px">
									<option value="">国家</option>
									<c:forEach items="${countryList}" var="item">
										<option
											<c:if test="${item.countryId ==countryVal}">selected="selected" </c:if>
											value="${item.countryId}">${item.name}</option>

									</c:forEach>
								</select> <select class="nice-select selectpicker" id="addressProvince"
									name="addressProvince" onchange="selectCity('addressProvince','addressCity')"
									data-width="100px">
									<option value="">省</option>
									<c:forEach items="${provinceList}" var="item">
										<option
											<c:if test="${item.provinceId ==provinceVal}">selected="selected" </c:if>
											value="${item.provinceId}">${item.name}</option>
									</c:forEach>

								</select> <select id="addressCity" class="nice-select selectpicker"
									name="addressCity" data-width="100px">
									<option value="">市</option>
									<c:forEach items="${cityList}" var="item">
										<option
											<c:if test="${item.cityId ==cityVal}">selected="selected" </c:if>
											value="${item.cityId}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				</div>
		   </li>
		   <li><span class="title">联系地址</span>
				<div class="right_con">
					<div class="w350">
						<input type="text" id="addressDetail" name="addressDetail" value="${userModel.addressDetail}" class="form-control">
					</div>
				</div>
		   </li>
		    <li style="min-height:15px"><span style="font-weight:bold;margin-left:50px;padding:2px">运营者信息: <c:if test="${not empty userModel.idCardName|| not empty userModel.idCardCode || not empty userModel.idCardSnapShot }"><a  onclick="changeOperationInfo();" style="font-weight:bold;cursor:pointer;">&nbsp;&nbsp;&nbsp;更改</a></c:if></span>	
		   </li>
		   <li><span class="title"><span class="input-title-span">＊</span>运营者身份证名称</span>
				<div class="right_con">
					<div class="w350">
						<input  value="${userModel.idCardName}" type="text" id="idCardName" check-type="required" required-message="运营者身份证名称" name="idCardName" class="form-control" <c:if test="${not empty userModel.idCardName}">disabled</c:if>>
					</div>
				</div>
		   </li>
		    <li><span class="title"><span class="input-title-span">＊</span>运营者身份证号</span>
				<div class="right_con">
					<div class="w350">
						<input  value="${userModel.idCardCode}" type="text" id="idCardCode" name="idCardCode" class="form-control" check-type="idCard" data-callback="validateExistIdCard()" 
						call-message="当前身份证号已被注册"  placeholder="请输入运营者身份证号" <c:if test="${not empty userModel.idCardCode}">disabled</c:if>>
					</div>
				</div>
		   </li>
		    <li><span class="title">性别</span>
				<div class="right_con">
					<div class="sex">
						<div class="radio-item">
							<div class="radio-group">
								<input type="radio" <c:if test="${userModel.userGender==0}">checked="checked"</c:if> name="userGender" id="man"
									value="0"> <label class="radio-1 <c:if test="${userModel.userGender==0}">checked</c:if>" for="man"></label>
							</div>
							<div>男</div> 
						</div>
						<div class="radio-item">
							<div class="radio-group">
								<input type="radio" <c:if test="${userModel.userGender==1}">checked="checked"</c:if> name="userGender" id="woman" value="1">
								<label class="radio-1 <c:if test="${userModel.userGender==1}">checked</c:if>" for="woman"></label>
							</div>
							<div>女</div>
						</div>
						<input type="hidden" id="userSetGender" name="userSetGender">
					</div>
				</div>
		   </li>
			<li><span class="title"><span class="input-title-span">＊</span>运营者手持身份证件照片</span>
				<div class="right_con">
						<div class="form-group">
							<div class="user-item">
								<div class="img">
									<img src="<c:if test="${empty userModel.idCardSnapShot}">${ctx}/resources/img/account/user-card-1.jpg</c:if><c:if test="${not empty userModel.idCardSnapShot}">${imgStatic}${attachmentUserIdCard.attachUrl}/${attachmentUserIdCard.generateName} </c:if>"
										id="userIdCardImg" width="160" height="90">
								</div>
								<div class="user-buttons">
                                        <div class="f10 ca0a0a0" style="height:50px;width: 290px; margin-bottom: 10px;">身份证上的所有信息清晰可见，必须能看到清证件号。照片需免冠，建议未化妆，手持证件人的五官清晰可见。照片内容真实有效，不得做任何修改。支持.jpg .jpeg .bmp .gif .png格式照片，大小不超过8M.</div>
                                        <div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;<c:if test='${not empty userModel.idCardSnapShot}'>display:none</c:if>" id="uploadUserImageDiv"">
                                            <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
                                            <input accept="image/png, image/gif, image/jpg, image/jpeg" id="uploadIdCardSnapShot" type="file" name="uploadIdCardSnapShot"
                                             multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;" checktype="inputAttach" attachshow="userIdCardImg" attachcheckid="userIdCardImgId">
                                        </div>
                                        <div style="margin-left: 40px;margin-top:-34px;<c:if test='${not empty userModel.idCardSnapShot}'>display:none</c:if>" id="deleteUserImgDiv"><button class="certify" type="button" style="display:block;margin:-32px 100px" onclick="cancleUserIdCardImg()">删除</button></div>
                                        <!-- <button class="certify" type="button" onclick="deleteFileImg()" style="margin-left: 5px;margin-top: -6px;">删除</button> -->
                                    </div>
                               <input type="hidden" id="userIdCardImgId" name="idCardImgId" value="<c:if test="${not empty userModel.idCardSnapShot}">${imgStatic}${attachmentUserIdCard.attachUrl}/${attachmentUserIdCard.generateName} </c:if>">
								<input type="hidden" id="userIdCardImgName" name="idCardImgName" value="${attachmentUserIdCard.generateName}">
							</div>
						</div>
					</div>
			</li>
		</ul>
		<input value="${uKeyId}" type="hidden" id="uKeyId" name="uKeyId">
		<input value="${userModel.idCardCode}" type="hidden" id="idCardCodeOld" name="idCardCodeOld">
		<input value="${userModel.userMail}" type="hidden" id="userMailOriginal" name="userMailOriginal">	
		<input type="hidden" name="isCheckTel" id="isCheckTel" value="${userModel.isCheckTel}">
		<input type="hidden" name="isCheckMail" id="isCheckMail" value="${userModel.isCheckMail}">
		<input type="hidden" name="orginialMobilePhone" value="${userModel.mobilePhone}" id="orginialMobilePhone">	
		<input type="hidden" name="orginialIdCardName" value="${userModel.idCardName}" id="orginialIdCardName">	
		<input type="hidden" name="orginialIdCardCode" value="${userModel.idCardCode}" id="orginialIdCardCode">		
		<input type="hidden" name="orginialIdCardSnapShot" value="${attachmentUserIdCard.generateName}" id="orginialIdCardSnapShot">	
		<div class="next_btn" style="margin: 140px auto 0; width: 220px;">
			<button class="btn btn-default" type="button" btn-type="true">保存/下一步</button>
		</div>
	</div>
</form>

	<script type="text/javascript">
		require([ 'modules/account/_account_information' ], function() {

		});
	</script>
