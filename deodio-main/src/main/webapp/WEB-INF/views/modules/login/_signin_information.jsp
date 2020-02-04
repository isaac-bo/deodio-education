<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<form method="post" id="userInfoForm">
		<div class="base-info-item base-info-bg">
			<div class="form-inline" style="width:900px;">
				<h4>详细信息登记</h4>
				<div class="error-custom mt20">deodio(滴石)在线教育平台致力于打造真实，合法，有效的在线教育内容推广，精准测评平台，我们有志于诚信守约、进取担当的第三方合作伙伴携手并进，简历和维护良性互动，健康有序的平台秩序，为了更好的保障您和广大平台用户的利益，请您认真填写以下登记信息。</div>
				<div class="error-custom">用户信息登记后您可以：
					<ul>
						<li>1. 使用该平台的相关功能;</li>
						<li>2. 提高账户可信任度</li>
					
					</ul>
				</div>
			</div>
		</div>
		
		<div class="base-info-item base-info-bg" style="height: 430px;">
		
			<div class="pull-left left-user-icon">
				<div class="set-ip-left">
					<div class="setup-leftpic text-center">
						<img src="<c:if test="${empty userModel.userIcon}">${ctx}/resources/img/account/homepaage_movie_travel.jpg</c:if><c:if test="${not empty userModel.userIcon}"> ${imgStatic}${attachmentUserIcon.attachUrl}/${attachmentUserIcon.generateName} </c:if>"  id="userIconPic"/>
					</div>
					<!--setup-leftpic end-->
					<button type="button" class="btn btn-gray btn-lg btn-block btn_head_icon mt20" onclick="javascript:showIcon();">
						<span><img src="${ctx}/resources/img/account/camera.png" /></span>&nbsp;&nbsp;&nbsp;上传照片
					</button>
					<div class="user_pic_up" style="top: 410px; left: 620px; display:none;" id="userIcon">
						<h4>
							改变自己的照片<a href="javascript:hideIcon();"><img src="${ctx}/resources/img/close_icon.png" alt=""></a>
						</h4>
						<div class="pic_con">
						
						</div>
						<!-- 未上传时状态 -->
						<div class="pic_con1" style="display:none;">
							<img src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" alt="" id="userIconImg">
							<div class="user-buttons" id="uploadImgDiv">
								<div style="width:233px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: 10px">
                        			<span style="display:block;color:#fff;cursor:pointer;">上传图片</span>
                       				<input accept="image/png, image/gif, image/jpg, image/jpeg" id="uploadUserIcon" type="file" name="uploadUserIcon"
                        				multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;" 
                        				onchange="registerKeyUp(this,'userIconImg','userIconName','userIconId','3')">
                       	 		</div>
<!--                         	<div style="margin-left: 35px;"><button class="certify" type="button" style="display:block;margin:-34px 100px;width: 60px;background:rgb(177,205,92);" onclick="cancleUserIcon()">删除</button></div>-->
 <!-- 						<button class="btn default" type="button"  onclick="cancleUserIcon()">使用默认头像</button> -->
							</div>
						</div>
						<input type="hidden" id="userIconId" name="userIconId">
						<input type="hidden" id="userIconName" name="userIconName">
						<!-- 上传带照片状态 -->
						<!-- <button type="button" class="btn up_color" onclick="uploadUserIcon();">上传新照片</button>
						<button type="button" class="btn default">使用默认头像</button> -->
<!-- 						<input id="uploadUserIcon" name="uploadUserIcon" class="btn up_color" type="file" onchange="registerKeyUp(this)">-->

					</div>
				</div>
			</div>
			
			
			
			<div class="form-inline pull-left">
			<!-- registerType==1手机号码注册 -->
			<c:if test = "${userModel.registerType==1}">
			<div class="form-group">
					<label class="input-title input-title-color">手机号码</label> 
					<input value="${userModel.mobilePhone}" disabled="disabled"
						type="text" name="companyTel" class="form-control h40" >
						<img id="companyPhoneVerifyImg" alt="" src="${ctx}/resources/img/account/certifed.png">
						<label id="companyPhoneMessage">已认证</label>
				</div>
				
				<div class="form-group">
					<label class="input-title input-title-color">邮箱</label> 
					<input type="text" id="email" value="${userModel.userMail}"
						name="userEmailVerify" class="form-control h40" style="width: 148px;">
					<button class="send-validate-code" type="button" id ="sendEmailValidateCodeBtn" onclick="sendEmailVerify()">发送</button>
					<img id="emailVerifyImg" alt="" src="">
					<span id="emailVerifySpan"></span>
					<span class="error-custom"></span> 
				</div>
				<div class="form-group" >
					<label class="input-title input-title-color">联系地址</label>

					<div class="ul-select select-group">

						<select class="selectpicker" onchange="selectProince('companyAddressCountry','companyAddressProvince')"
							name="companyAddressCountry" id="companyAddressCountry" data-width="100px">
							<option value="">国家</option>
							<c:forEach items="${countryList}" var="item">
								<option value="${item.countryId}" <c:if test = "${companyModel.companyAddressCountry == item.countryId}">selected</c:if>>${item.name}</option>
							</c:forEach>
						</select> <select class="selectpicker" id="companyAddressProvince"
							name="companyAddressProvince" onchange="selectCity('companyAddressProvince','companyAddressCity')"
							data-width="100px">
							<option value="">省</option>
							<c:forEach items="${provinceList}" var="item">
								<option value="${item.provinceId}" <c:if test = "${companyModel.companyAddressProvince == item.provinceId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select> <select id="companyAddressCity" class="selectpicker" name="companyAddressCity"
							data-width="100px">
							<option value="">市</option>
							<c:forEach items="${cityList}" var="item">
								<option value="${item.cityId}" <c:if test = "${companyModel.companyAddressCity == item.cityId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<div class="form-group" >
					<label class="input-title input-title-color">联系地址</label> <input  value="${companyModel.companyAddressDetails}"
						type="text" name="companyAddressDetails" class="form-control h40"
						style="width: 350px;">
				</div>
			</c:if>
			<!-- registerType==2邮箱注册 -->		
			<c:if test = "${userModel.registerType==2||userModel.registerType==5}">
				<div class="form-group">
					<label class="input-title input-title-color">邮箱</label> 
					<input value="${userModel.userMail}" disabled="disabled"
						type="text" name="companyEmail" class="form-control h40" >
					<img id="emailVerifyImg" alt="" src="${ctx}/resources/img/account/certifed.png">
					<span id="emailVerifySpan">已认证</span>
				</div>
				
				<div class="form-group">
					<label class="input-title input-title-color">手机号码</label> 
					<input type="hidden" id="hiddenOldMobile" value="${userModel.mobilePhone}"> 
					<input type="text"  value="${userModel.mobilePhone}"
						name="userMobile"  id="userMobile" check-type="mobile"
						required-message="请输入正确的手机号码"  onmouseout="checkMobileVerifyEnable()"
						call-message="此手机号已存在" class="form-control h40" non-required="true">
						
						<input type="hidden" name="is_check_tel" id="is_check_tel" value="0">
					<c:if test="${userModel.isCheckTel==0 && not empty userModel.mobilePhone}">
						<img id="phoneVerifyImg" src="${ctx}/resources/img/account/uncertifed.png">
						<span id="phoneVerifySpan">未认证</span>
					</c:if>
					<c:if test="${userModel.isCheckTel==1 && not empty userModel.mobilePhone}">
						<img id="phoneVerifyImg"  src="${ctx}/resources/img/account/certifed.png">
						<span id="phoneVerifySpan">已认证</span>
					</c:if>
					<span class="error-custom" id="errorUserMobile"></span> 
				</div>
				 <c:if test = "${userModel.isCheckTel!=1}"> 
				<div class="form-group" id="userIsCheckTel">
					<label class="input-title input-title-color">短信验证码</label> 
					<input type="text" data-callback="checkValidateCode()" check-type="number" required-message="请输入验证码" call-message="验证码错误"  disabled="disabled"
						name="userMobileVerify" class="form-control h40" style="width: 148px;" id="userMobileVerify">
					<button class="send-validate-code" type="button" id ="sendValidateCodeBtn" onclick="javascript:sendValidateCode(this)" disabled="disabled">发送验证码</button>
					<span class="error-custom"></span> 
				</div>
				 </c:if> 
				<div class="form-group" >
					<label class="input-title input-title-color">联系地址</label>

					<div class="ul-select select-group">

						<select class="selectpicker" onchange="selectProince('companyAddressCountry','companyAddressProvince')"
							name="companyAddressCountry" id="companyAddressCountry" data-width="100px">
							<option value="">国家</option>
							<c:forEach items="${countryList}" var="item">
								<option value="${item.countryId}" <c:if test = "${companyModel.companyAddressCountry == item.countryId}">selected</c:if>>${item.name}</option>
							</c:forEach>
						</select> <select class="selectpicker" id="companyAddressProvince"
							name="companyAddressProvince" onchange="selectCity('companyAddressProvince','companyAddressCity')"
							data-width="100px">
							<option value="">省</option>
							<c:forEach items="${provinceList}" var="item">
								<option value="${item.provinceId}" <c:if test = "${companyModel.companyAddressProvince == item.provinceId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select> <select id="companyAddressCity" class="selectpicker" name="companyAddressCity"
							data-width="100px">
							<option value="">市</option>
							<c:forEach items="${cityList}" var="item">
								<option value="${item.cityId}" <c:if test = "${companyModel.companyAddressCity == item.cityId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<div class="form-group" >
					<label class="input-title input-title-color">联系地址</label> <input  value="${companyModel.companyAddressDetails}"
						type="text" name="companyAddressDetails" class="form-control h40"
						style="width: 350px;">
				</div>
			</c:if>
			<!-- registerType==3企业账户注册 -->			
			<c:if test = "${userModel.registerType==3}">
				<div class="form-group">
					<label class="input-title input-title-color">公司名称</label> 
					<input value= "${companyModel.companyName}" disabled="disabled"
						type="text" name="companyName" class="form-control h40" check-type="required" required-message="请输入公司名称">
					<span class="error-custom"></span> 
				</div>
				<div class="form-group">
					<label class="input-title input-title-color">公司电话</label>
					 <input value="${companyModel.companyTel}"
						type="text" name="companyTel" class="form-control h40">
						<img id="companyPhoneVerifyImg" alt="" src="">
						<label id="companyPhoneMessage"></label>
				</div>
<!-- 				<div class="form-group">
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>公司邮箱</label> 
					<input type="text" id="email"  check-type="required" required-message="请输入邮箱"  data-callback="checkEmailValidate()"   
						name="userEmailVerify" class="form-control h40" style="width: 148px;">
					<button class="send-validate-code" type="button" id ="sendEmailValidateCodeBtn" onclick="sendEmailVerify()">发送</button>
					<img id="emailVerifyImg" alt="" src="">
					<span id="emailVerifySpan"></span>
					<span class="error-custom"></span> 
				</div> -->
					<div class="form-group" >
					<label class="input-title input-title-color">公司地址</label>

					<div class="ul-select select-group">

						<select class="selectpicker" onchange="selectProince('companyAddressCountry','companyAddressProvince')"
							name="companyAddressCountry" id="companyAddressCountry" data-width="100px">
							<option value="">国家</option>
							<c:forEach items="${countryList}" var="item">
								<option value="${item.countryId}" <c:if test = "${companyModel.companyAddressCountry == item.countryId}">selected</c:if>>${item.name}</option>
							</c:forEach>
						</select> <select class="selectpicker" id="companyAddressProvince"
							name="companyAddressProvince" onchange="selectCity('companyAddressProvince','companyAddressCity')"
							data-width="100px">
							<option value="">省</option>
							<c:forEach items="${provinceList}" var="item">
								<option value="${item.provinceId}" <c:if test = "${companyModel.companyAddressProvince == item.provinceId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select> <select id="companyAddressCity" class="selectpicker" name="companyAddressCity"
							data-width="100px">
							<option value="">市</option>
							<c:forEach items="${cityList}" var="item">
								<option value="${item.cityId}" <c:if test = "${companyModel.companyAddressCity == item.cityId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<div class="form-group" >
					<label class="input-title input-title-color">公司地址</label> <input  value="${companyModel.companyAddressDetails}"
						type="text" name="companyAddressDetails" class="form-control h40"
						style="width: 350px;">
				</div>
				
			</c:if>
			
				<div class="form-group" >
					<label class="input-title input-title-color">邮编</label> <input value="${companyModel.companyZipCode}"
						type="text" name="companyZipCode" class="form-control h40" id="companyZipCode"
						style="width: 350px;">
						<span class="error-custom" id="companyZipCodeError"></span> 
				</div>
			</div>
		</div>
		<c:if test = "${userModel.registerType==3}">
		<div class="base-info-item base-info-bg">
			<div class="form-inline">
				<div class="form-group" >
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>企业营业执照注册号</label> 
						<input  value="${companyModel.companyBusinessLicenseCode}"
						type="text" check-type="companyBusinessLicenseCode" name="companyBusinessLicenseCode" 
						required-message="请输入企业营业执照注册号" class="form-control h40 error-custom"
						style="width: 350px;" id="companyBusinessLicenseCode" 
						data-callback="checkCompanyBusinessLicenseCode()" call-message="此营业执照注册号已存在">
				</div>
				<div class="form-group" >
					<label class="input-title input-title-color">营业执照住所地</label>

					<div class="ul-select select-group">

						<select class="selectpicker" onchange="selectProince('companyBusinessLicenseCountry','companyBusinessLicenseProvince')"
							name="companyBusinessLicenseCountry" id="companyBusinessLicenseCountry" data-width="100px">
							<option value="">国家</option>
							<c:forEach items="${countryList}" var="item">
								<option value="${item.countryId}" <c:if test = "${companyModel.companyBusinessLicenseCountry == item.countryId}">selected</c:if>>${item.name}</option>
							</c:forEach>
						</select> <select class="selectpicker" id="companyBusinessLicenseProvince"
							name="companyBusinessLicenseProvince" onchange="selectCity('companyBusinessLicenseProvince','companyBusinessLicenseCity')"
							data-width="100px">
							<option value="">省</option>
							<c:forEach items="${provinceList}" var="item">
								<option value="${item.provinceId}" <c:if test = "${companyModel.companyBusinessLicenseProvince == item.provinceId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select> <select id="companyBusinessLicenseCity" class="selectpicker" name="companyBusinessLicenseCity"
							data-width="100px">
							<option value="">市</option>
							<c:forEach items="${cityList}" var="item">
								<option value="${item.cityId}" <c:if test = "${companyModel.companyBusinessLicenseCity == item.cityId}">selected</c:if>>${item.name}</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<fmt:formatDate type="date" value="${companyModel.companyBusinessLicenseFoundedDate}" var="companyBusinessLicenseFoundedDate"   pattern="yyyy-MM-dd"/>
				<fmt:formatDate type="date" value="${companyModel.companyBusinessLicenseBusinessTerm}"  var="companyBusinessLicenseBusinessTerm" pattern="yyyy-MM-dd"/>
				<div class="form-group" >
					<label class="input-title input-title-color">成立日期</label> 
					<input type="text" name="companyBusinessLicenseFoundedDate" 
						class="form-control h40 form_datetime"   value="${companyBusinessLicenseFoundedDate}"
						id="companyBusinessLicenseFoundedDate"	style="width: 350px;">
						<span class="error-custom" id="errorCompanyBusinessLicenseFoundedDate"></span> 
				</div>
				
				<div class="form-group" >
					<label class="input-title input-title-color">营业期限</label> <input  value="${companyBusinessLicenseBusinessTerm}"
						type="text" name="companyBusinessLicenseBusinessTerm" class="form-control h40 form_datetime"
						id="companyBusinessLicenseBusinessTerm" style="width: 350px;">
						<span class="error-custom" id="errorCompanyBusinessLicenseBusinessTerm"></span>
				</div>
				
				<div class="form-group">
		             <label class="input-title input-title-color pull-left">
						<span class="input-title-span">＊</span>营业执照副本扫描件</label>
		             	<div class="user-item pull-left" style="width: 300px;">
		                	<div class="user-item">
		                		<div class="img pull-left" style="margin-bottom: 10px;">
		                			<img src="<c:if test="${empty companyModel.companyBusinessLicenseSnapShot}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if>
		                                  <c:if test="${not empty companyModel.companyBusinessLicenseSnapShot}"> ${imgStatic}${attachmentBusinessLicense.attachUrl}${attachmentBusinessLicense.generateName} </c:if>"
		                                  id="businessLicenceImg" width="160" height="95">
		                            </div>
		                            <div class="user-buttons">
                                        <div class="f10 ca0a0a0" style="height:50px;width: 375px; margin-bottom: 10px;">上传企业营业执照副本扫描件，照片所有信息需清晰可见，内容真实有效，不得做任何修改。
											照片支持.jpg .jpeg .bmp .gif .png格式，大小不超过8M。</div>
                                        <div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
                                            <span style="display:block;color:#fff;cursor:pointer;">上传图片</span>
                                            <input accept="image/png, image/gif, image/jpg, image/jpeg" id="uploadBusinessLicenceSnapShot" type="file" name="uploadBusinessLicenceSnapShot"
                                             multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;" 
                                             onchange="registerKeyUp(this,'businessLicenceImg','businessLicenceImgName','businessLicenceImgId','8')" checktype="inputAttach" 
                                             attachshow="businessLicenceImg" attachcheckid="businessLicenceImgId">
                                        </div>
                                        <div style="margin-left: 35px;">
                                        <button class="certify" type="button" style="display:block;margin:-34px 100px" onclick="deleteFileImg('businessLicenceImg','businessLicenceImgName','businessLicenceImgId','userBusinessLicenseDefaultSnapShot')">删除</button></div>
                                        <!-- <button class="certify" type="button" onclick="deleteFileImg()" style="margin-left: 5px;margin-top: -6px;">删除</button> -->
                                    </div>
		                            <!-- <div class="update-bar border-radius" id="progressBar">
                                        <div class="bar"></div>
                                    </div> -->
                                    <div class="update-bar border-radius" id="businessLicenceImgProgressBar" style="width: 354px;">
                                        <div class="bar" style="width: 0%; height: 18px;background: #00e4ff;"></div>
                                    </div>
 									<input type="hidden" id="businessLicenceImgId" name="businessLicenceImgId" value="${attachmentBusinessLicense.attachUrl}">
									<input type="hidden" id="businessLicenceImgName" name="businessLicenceImgName">
		                        </div>
		                    </div>
                        </div>
				
				
				<div class="form-group" >
					<label class="input-title input-title-color">注册资本</label> <input value="${companyModel.companyBusinessLicenseRegisteredCapital}"
						type="text" name="companyBusinessLicenseRegisteredCapital" class="form-control h40">
						<div class="form-group-description error-custom">万元</div>
				</div>
				
				<div class="form-group" >
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>组织机构代码</label> <input value="${companyModel.companyOrganizationCode}"
						type="text" name="companyOrganizationCode" check-type="companyOrganizationCode" required-message="请输入正确的组织机构代码" class="form-control h40 error-custom"
						id="companyOrganizationCode" data-callback="checkCompanyOrganizationCode()" call-message="此组织机构代码已存在">
				</div>
			</div>
		</div>
		</c:if>
		<div class="base-info-item base-info-bg">
			<div class="form-inline">
				<div class="form-group">
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>运营者身份证名称</label> 
						<input name="idCardName" value="${userModel.idCardName}"
						type="text" check-type="required" required-message="请输入正确的运营者身份证名称"
						class="form-control h40 error-custom">
				</div>
				<div class="form-group">
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>运营者身份证号码</label>
						 <input name="userIdCardCode" id="userIdCardCode" value="${userModel.idCardCode}"
						type="text" check-type="idCard" required-message="请输入正确的身份证号码"
						class="form-control h40 error-custom" data-callback="checkIDCard()" call-message="此身份证号码已存在">
				</div>
				<div class="form-group">
					<label class="input-title input-title-color">性 别</label>
					<div class="sex">
					<input type="hidden" value="${userModel.userGender}" id="hiddenUserSex">
						<div class="radio-item">
							<div class="radio-group">
								<input type="radio" <c:if test="${userModel.userGender==0}"> checked</c:if>  name="userGender" id="man"
									value="0"> <label class="radio-1" for="man"></label>
							</div>
							<div>男</div>
						</div>
						<div class="radio-item">
							<div class="radio-group">
								<input type="radio" <c:if test="${userModel.userGender==1}"> checked</c:if> name="userGender" id="woman" value="1">
								<label class="radio-1" for="woman"></label>
							</div>
							<div>女</div>
						</div>
						
					</div>

				</div>
				<div class="form-group">
		             <label class="input-title input-title-color pull-left">
						<span class="input-title-span">＊</span>运营者手持身份证件照片</label>
		             	<div class="user-item pull-left" style="width: 300px;">
		                	<div class="user-item">
		                		<div class="img pull-left" style="margin-bottom: 10px;">
		                			<img src="<c:if test="${empty userModel.idCardSnapShot}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if>
		                                  <c:if test="${not empty userModel.idCardSnapShot}"> ${imgStatic}${attachmentUserIdCard.attachUrl}${attachmentUserIdCard.generateName} </c:if>"
		                                  id="userIdCardImg" width="160" height="95">
		                            </div>
		                            <div class="user-buttons">
                                        <div class="f10 ca0a0a0" style="height:50px;width: 375px; margin-bottom: 10px;">身份证上的所有信息清晰可见，必须能看到清证件号。
											照片需免冠，建议未化妆，手持证件人的五官清晰可见。照片内容真实有效，不得做任何修改。支持.jpg .jpeg .bmp .gif .png格式照片，大小不超过8M.</div>
                                        <div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
                                            <span style="display:block;color:#fff;cursor:pointer;">上传图片</span>
                                            <input accept="image/png, image/gif, image/jpg, image/jpeg" id="userIdCardUploadFile" type="file" name="userIdCardUploadFile"
                                             multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;" 
                                             onchange="registerKeyUp(this,'userIdCardImg','userIdCardImgName','userIdCardImgId','7')" checktype="inputAttach" 
                                             attachshow="userIdCardImg" attachcheckid="userIdCardImgId">
                                        </div>
                                        <div style="margin-left: 35px;"><button class="certify" type="button" style="display:block;margin:-34px 100px" onclick="deleteFileImg('userIdCardImg','userIdCardImgName','userIdCardImgId','userIdCardDefaultSnapShot')">删除</button></div>
                                        <!-- <button class="certify" type="button" onclick="deleteFileImg()" style="margin-left: 5px;margin-top: -6px;">删除</button> -->
                                    </div>
		                            <!-- <div class="update-bar border-radius" id="progressBar">
                                        <div class="bar"></div>
                                    </div> -->
                                    <div class="update-bar border-radius" id="userIdCardImgProgressBar" style="width: 354px;">
                                        <div class="bar" style="width: 0%; height: 18px;background: #00e4ff;"></div>
                                    </div>
									<input type="hidden" id="userIdCardImgId" name="idCardImgId" value="${attachmentUserIdCard.attachUrl}">
									<input type="hidden" id="userIdCardImgName" name="idCardImgName">
		                        </div>
		                    </div>
                        </div>

				<c:if test = "${userModel.registerType==3}">
				<div class="form-group">
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>职务</label> <input name="companyOperationPosition" value="${companyModel.companyOperationPosition}"
						type="text" check-type="required" required-message="请输入职务"
						class="form-control h40 error-custom ">
				</div>
				<div class="form-group">
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>移动电话</label> 
					<input type="hidden" id="hiddenOldMobile" value="${userModel.mobilePhone}">
					<input type="text"  value="${userModel.mobilePhone}"
						name="userMobile" check-type="mobile" 
						required-message="请输入正确的手机号码" onmouseout="checkMobileVerifyEnable()"
						class="form-control h40 error-custom" 
						id="userMobile"  call-message="此手机号已存在">
						<input type="hidden" name="is_check_tel" id="is_check_tel" value="0">
						<img id="phoneVerifyImg" alt="" src="">
					<span id="phoneVerifySpan"></span>
					<span class="error-custom" id="errorUserMobile"></span>
				</div>
				 <c:if test = "${userModel.isCheckTel!=1}"> 
				<div class="form-group" id="userIsCheckTel">
					<label class="input-title input-title-color">短信验证码</label> 
					<input type="text" data-callback="checkValidateCode()" check-type="number" required-message="请输入验证码"  call-message="验证码错误"  disabled="disabled"
						name="userMobileVerify" class="form-control h40" style="width: 148px;" id="userMobileVerify">
					<button class="send-validate-code" type="button" id ="sendValidateCodeBtn" onclick="javascript:sendValidateCode(this)" disabled="disabled">发送验证码</button>
					<span class="error-custom"></span> 
				</div>
				
				</c:if>		
				<div class="form-group">
					<label class="input-title input-title-color">固定电话</label> <input value="${userModel.telNumber}"
						type="text" name="userTel" class="form-control h40">
				</div>
				</c:if>
			</div>
		</div>

		<input type="hidden" name="userId" value="${uKeyId}" id="userId">
		<input id="userRegisterTypeHidden" type="hidden" value="${userModel.registerType}">
		<input id="userMobilePhoneHidden" type="hidden" value="${userModel.mobilePhone}">
		<input id="userMailHidden" type="hidden" value="${userModel.userMail}">
		<div class="base-info-item base-info-bg">
			<div class="form-inline">
				<div class="form-group" style="margin: 0;">
					<button class="btn" type="button" btn-type="true">提交</button>
					<button class="btn cancel"  type="button" onclick="returnToLogin()">取消</button>
				</div>
			</div>
		</div>
</form>
<script type="text/javascript">
	require(['modules/login/_signin.information'],function(){
		
	});
</script>