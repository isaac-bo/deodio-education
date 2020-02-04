<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<form id="accountOrganizationForm">
	<div class="form_bg">
		<ul class="form_list">
			<li><span class="title"><span class="input-title-span">＊</span>公司名称</span>
				<div class="right_con">
					<div class="w350">
						<input type="text" id="companyName" name="companyName"  type="text"  class="form-control error-custom"  check-type="required"  required-message='请输入公司名称' value="${userCompany.companyName}" placeholder="公司名称">
					</div>
					<!-- <p class="f12">4-20位字符,支持汉字、字母、数字及"-"、"_"组合</p> -->
				</div>
				
			</li>
			<li><span class="title">公司域名</span>
				<div class="right_con">
					<div class="w350">
						<input type="text" id="accountDomain" name="accountDomain"  type="text" value="${accountDomain}" class="form-control error-custom" placeholder="公司域名" check-type="char" 
									data-callback="validateDomainExists();" call-message="当前域名已被注册" required-message="请输入公司域名"  >
						
					</div>
				</div>
			</li>
			<li><span class="title">公司电话</span>
				<div class="right_con">
					<div class="w350">
						<input id="companyTel" name="companyTel" type="text"  value="${userCompany.companyTel}"  class="form-control error-custom" placeholder="公司电话">
					</div>
				</div>
			</li>
		   <li><span class="title">公司地址</span>
				<div class="right_con">
					<div class="form-group">
							<div class="ul-select select-group">

								<select class="nice-select selectpicker"
									onchange="selectProince('companyAddressCountry','companyAddressProvince')" name="companyAddressCountry"
									id="companyAddressCountry" data-width="100px">
									<option value="">国家</option>
									<c:forEach items="${countryList}" var="item">
										<option
											<c:if test="${item.countryId ==countryVal}">selected="selected" </c:if>
											value="${item.countryId}">${item.name}</option>

									</c:forEach>
								</select> <select class="nice-select selectpicker" id="companyAddressProvince"
									name="companyAddressProvince" onchange="selectCity('companyAddressProvince','companyAddressCity')"
									data-width="100px">
									<option value="">省</option>
									<c:forEach items="${provinceList}" var="item">
										<option
											<c:if test="${item.provinceId ==provinceVal}">selected="selected" </c:if>
											value="${item.provinceId}">${item.name}</option>
									</c:forEach>

								</select> <select id="companyAddressCity" class="nice-select selectpicker"
									name="companyAddressCity" data-width="100px">
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
		   <li><span class="title">公司地址</span>
				<div class="right_con">
					<div class="w200">
						<input type="text" id="companyAddressDetails" name="companyAddressDetails" value="${userCompany.companyAddressDetails}" class="form-control error-custom"  placeholder="公司地址">
					</div>
				</div>
		   </li>
		    <li><span class="title">邮编</span>
				<div class="right_con">
					<div class="w200">
						<input type="text" id="companyZipCode" name="companyZipCode" value="${userCompany.companyZipCode}"  class="form-control error-custom"  placeholder="邮编地址">
					</div>
				</div>
		   </li>
		    <li style="min-height:15px"><span  style="font-weight:bold;;margin-left:50px;padding:2px"">企业信息:<c:if test="${not empty userCompany.companyBusinessLicenseCode || not empty userCompany.companyBusinessLicenseSnapShot || not empty userCompany.companyOrganizationCode}"> <a onclick="changeCompanyInfo();" style="font-weight:bold;cursor:pointer;">&nbsp;&nbsp;&nbsp;更改</a></c:if> </span>
		   </li>
		    <li><span class="title"><span class="input-title-span">＊</span>企业营业执照注册号</span>
				<div class="right_con">
					<div class="w200">
						<input type="text" id="companyBusinessLicenseCode" check-type="companyBusinessLicenseCode"  required-message='请输入正确企业营业执照注册号' name="companyBusinessLicenseCode" value="${userCompany.companyBusinessLicenseCode}" class="form-control"  placeholder="请输入企业营业执照注册号"
						data-callback="checkCompanyBusinessLicenseCode()" call-message="此营业执照注册号已存在" <c:if test="${not empty userCompany.companyBusinessLicenseCode}">disabled</c:if>>
					</div>
				</div>
		   </li>
		   <li><span class="title">营业执照住所地</span>
				<div class="right_con">
					<div class="form-group">
							<div class="ul-select select-group">

								<select class="nice-select selectpicker"
									onchange="selectProince('companyBusinessLicenseCountry','companyBusinessLicenseProvince')" name="companyBusinessLicenseCountry"
									id="companyBusinessLicenseCountry" data-width="100px">
									<option value="">国家</option>
									<c:forEach items="${countryList}" var="item">
										<option
											<c:if test="${item.countryId ==countryLicenseVal}">selected="selected" </c:if>
											value="${item.countryId}">${item.name}</option>

									</c:forEach>
								</select> <select class="nice-select selectpicker" id="companyBusinessLicenseProvince"
									name="companyBusinessLicenseProvince" onchange="selectCity('companyBusinessLicenseProvince','companyBusinessLicenseCity')"
									data-width="100px">
									<option value="">省</option>
									<c:forEach items="${provinceLicenseList}" var="item">
										<option
											<c:if test="${item.provinceId ==provinceLicenseVal}">selected="selected" </c:if>
											value="${item.provinceId}">${item.name}</option>
									</c:forEach>

								</select> <select id="companyBusinessLicenseCity" class="nice-select selectpicker"
									name="companyBusinessLicenseCity" data-width="100px">
									<option value="">市</option>
									<c:forEach items="${cityLicenseList}" var="item">
										<option
											<c:if test="${item.cityId ==cityLicenseVal}">selected="selected" </c:if>
											value="${item.cityId}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				</div>
		   </li>
			<fmt:formatDate type="date" value="${userCompany.companyBusinessLicenseFoundedDate}" var="companyBusinessLicenseFoundedDate"   pattern="yyyy-MM-dd"/>
			<fmt:formatDate type="date" value="${userCompany.companyBusinessLicenseBusinessTerm}"  var="companyBusinessLicenseBusinessTerm" pattern="yyyy-MM-dd"/>
			<li><span class="title">成立日期</span>
				<div class="right_con">
					<div class="w200">
						<input
						type="text" id="companyBusinessLicenseFoundedDate"  onchange="validateDate()"  name="companyBusinessLicenseFoundedDate" class="form-control h40 form_datetime "   value="${companyBusinessLicenseFoundedDate}"
						style="width: 350px;" >
					</div>
				</div>
		   </li>
		   <li><span class="title">营业期限</span>
				<div class="right_con">
					<div class="w200">
						<input  value="${companyBusinessLicenseBusinessTerm}"
						type="text" id="companyBusinessLicenseBusinessTerm" name="companyBusinessLicenseBusinessTerm" onchange="validateDate()" class="form-control form_datetime"
						style="width: 350px;" >
					</div>
				</div>
		   </li>
		   <li><span class="title"><span class="input-title-span">＊</span>营业执照副本扫描件</span>
				<div class="right_con">
						<div class="form-group">
							<div class="user-item">
								<div class="img">
									<img src="<c:if test="${empty userCompany.companyBusinessLicenseSnapShot}">${ctx}/resources/img/account/user-card-1.jpg</c:if><c:if test="${not empty userCompany.companyBusinessLicenseSnapShot}">${imgStatic}${attachmentBusinessLicense.attachUrl}/${attachmentBusinessLicense.generateName} </c:if>"
										id="businessLicenceImg" width="160" height="90">
								</div>
								<div class="user-buttons">
									<p style="width:250px;height:62px;font-size:10px;">上传企业营业执照副本扫描件，照片所有信息需清晰可见，内容真实有效，不得做任何修改。照片支持.jpg .jpeg .bmp .gif .png格式，大小不超过8M。</p>
<!-- 									<input id="uploadCompanyShot"  class="user-card" type="file"> -->
<!-- 									<button class="certify pull-left" type="button" style="display: block; margin-top:-16px;margin-left:5px;" onclick="cancleUserIdCardImg()">删除</button> -->
									<div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: -5px;<c:if test='${not empty  userCompany.companyBusinessLicenseSnapShot}'>display:none</c:if>" id="companyImageDiv"">
	                                    <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
	                                   <input accept="image/png, image/gif, image/jpg, image/jpeg" id="uploadCompanyShot" type="file" name="uploadCompanyShot" multiple="" 
	                                   		style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;" 
	                                   		checktype="inputAttach" attachshow="businessLicenceImg"  attachcheckid="businessLicenceImgId">
	                                </div>
									
									<div style="margin-left: 40px;<c:if test='${not empty  userCompany.companyBusinessLicenseSnapShot}'>display:none</c:if>" id="deletecompanyImgDiv"><button class="certify" type="button" style="display:block;margin:-32px 100px" onclick="cancleCompanyShotImg()">删除</button></div>
								</div>								
								<input type="hidden" id="businessLicenceImgId" name="businessLicenceImgId" value="<c:if test="${not empty userCompany.companyBusinessLicenseSnapShot}">${imgStatic}${attachmentBusinessLicense.attachUrl}/${attachmentBusinessLicense.generateName} </c:if>">
								<input type="hidden" id="businessLicenceImgName" name="businessLicenceImgName" value="${attachmentBusinessLicense.generateName}">
							</div>
						</div>
					</div>
			</li>
			<li><span class="title">注册资金</span>
				<div class="right_con">
					<div style="width:350px;display:inline-block;">
						<div class="pull-left" style="width:320px;display:inline-block;">
							<input type="text" name="companyBusinessLicenseRegisteredCapital" id="companyBusinessLicenseRegisteredCapital" class="form-control error-custom"   value="${userCompany.companyBusinessLicenseRegisteredCapital}" style="width: 320px;">
						</div>
						<div style="float:left;padding-top:10px;">
								万元
						</div>
					</div>	
				</div>
		   </li>
		   <li><span class="title"><span class="input-title-span">＊</span>组织机构代码</span>
				<div class="right_con">
					<div class="w200">
						<input  check-type="companyOrganizationCode"  required-message='请输入正确的组织机构代码'  value="${userCompany.companyOrganizationCode}" type="text" id="companyOrganizationCode" name="companyOrganizationCode" class="form-control error-custom" style="width: 350px;"
						data-callback="checkCompanyOrganizationCode()" call-message="此组织机构代码已存在" <c:if test="${not empty userCompany.companyOrganizationCode}">disabled</c:if>>
					</div>
				</div>
		   </li>
		   <li style="min-height:15px"><span style="font-weight:bold;margin-left:50px;padding:2px">运营者信息: <c:if test="${not empty userCompany.companyOperationName || not empty userCompany.companyOperationId || not empty userModel.idCardSnapShot || not empty userCompany.companyOperationPosition || not empty userModel.mobilePhone}"><a onclick="changeOperationInfo();" style="font-weight:bold;cursor:pointer;">&nbsp;&nbsp;&nbsp;更改</a></c:if></span>	
		   </li>
		   <li><span class="title"><span class="input-title-span">＊</span>运营者身份证名称</span>
				<div class="right_con">
					<div class="w200">
						<input  value="${userCompany.companyOperationName}" type="text" id="nickName" name="nickName"  check-type="required"  required-message='请输入运营者身份证名称' class="form-control error-custom" style="width: 350px;" placeholder="运营者身份证名称" <c:if test="${not empty userCompany.companyOperationName}">disabled</c:if>>
					</div>
				</div>
		   </li>
		    <li><span class="title"><span class="input-title-span">＊</span>运营者身份证号</span>
				<div class="right_con">
					<div class="w200">
						<input  value="${userCompany.companyOperationId}" type="text" id="userIdCardCode" name="userIdCardCode" check-type="idCard"  required-message='请输入正确的运营者身份证号' class="form-control error-custom" style="width: 350px;" check-type="idCard" required-message="请输入运营者身份证号码" placeholder="运营者身份证号"
						data-callback="checkIDCard()" call-message="此身份证号码已存在" <c:if test="${not empty userCompany.companyOperationId}">disabled</c:if>>
					</div>
				</div>
		   </li>
		    <li><span class="title">性别</span>
				<div class="right_con">
					<div class="sex">
						<div class="radio-item">
							<div class="radio-group">
								<input type="radio" <c:if test="${userModel.userGender==0}">checked="checked"</c:if>  name="userGender" id="man"
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
									<p style="height:46px;width: 325px; margin-bottom: 10px;">身份证上的所有信息清晰可见，必须能看到清证件号。照片需免冠，建议未化妆，手持证件人的五官清晰可见。照片内容真实有效，不得做任何修改。支持.jpg .jpeg .bmp .gif .png格式照片，大小不超过8M.</p>
<!-- 									<input id="uploadIdCardSnapShot"  class="user-card" type="file"> -->
<!-- 									<button class="certify pull-left" type="button" style="display: block; margin-top:-16px;margin-left:5px;" onclick="cancleUserIdCardImg()">删除</button> -->
							
							
								<div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: -5px;<c:if test='${not empty userModel.idCardSnapShot}'>display:none</c:if>" id="uploadUserImageDiv">
	                                    <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
	                                   <input accept="image/png, image/gif, image/jpg, image/jpeg" id="uploadIdCardSnapShot" type="file" name="uploadIdCardSnapShot" multiple="" 
	                                   		style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;" 
	                                   		checktype="inputAttach" attachshow="userIdCardImg" attachcheckid="userIdCardImgId"
	                                   		>
	                                </div>
									
									<div style="margin-left: 40px;<c:if test='${not empty userModel.idCardSnapShot}'>display:none</c:if>" id="deleteUserImgDiv"><button class="certify" type="button" style="display:block;margin:-32px 100px" onclick="cancleUserIdCardImg()">删除</button></div>
			
							
							
							
								</div>
								<input type="hidden" id="userIdCardImgId" name="idCardImgId" value="<c:if test="${not empty userModel.idCardSnapShot}">${imgStatic}${attachmentUserIdCard.attachUrl}/${attachmentUserIdCard.generateName} </c:if>">
								<input type="hidden" id="userIdCardImgName" name="idCardImgName" value="${attachmentUserIdCard.generateName}">
							</div>
						</div>
					</div>
			</li>
			<li><span class="title"><span class="input-title-span">＊</span>职务</span>
				<div class="right_con">
					<div class="w200">
						<input  value="${userCompany.companyOperationPosition}" type="text" id="companyOperationPosition" name="companyOperationPosition" check-type="required"  required-message='请输入职务' class="form-control error-custom" style="width: 350px;"
						<c:if test="${not empty userCompany.companyOperationPosition}">disabled</c:if>>
					</div>
				</div>
		   </li>
		   <li>
		   	<span class="title">
		   		<span class="input-title-span">＊</span>移动电话
		   		<input type="hidden" id="_account_organization">
		   	</span>
				<div class="right_con">
					<div class="w350 pull-left">
						<input  value="${userModel.mobilePhone}"  data-callback="checkMobileExsist()"  call-message="该手机号已被注册，请输入其他号码！" 
						 type="text" id="userMobile" name="userMobile" class="form-control error-custom" 
						 check-type="mobile" required-message="请输入11位正确手机号码！" style="width: 350px;"
						 <c:if test="${not empty userModel.mobilePhone}">disabled</c:if>>
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
		   <%-- 	<li><span class="title">短信验证码</span>
				<div class="right_con">
				  <div class="w350 pull-left">
							<input type="text" <c:if test="${userModel.isCheckTel==0}"> check-type="required" data-callback="valOrganizationCode();" call-message="验证码错误" 
							required-message="请输入验证码!" placeholder="请输入验证码"</c:if> id="val_code" name="val_code" 
							class="form-control error-custom hb100"  aria-describedby="basic-addon1" <c:if test="${userModel.isCheckTel==1}">disabled</c:if>/>	
							<button class="certify certify-inner-button" id="btn_smscode" type="button" onclick="getValidateCode();" <c:if test="${userModel.isCheckTel==1}">disabled</c:if>>获取短信验证码</button>
					</div>
				</div>
		   </li>  --%>
		   <li id="personal_val_code" <c:if test="${userModel.isCheckTel==1}">style="display:none"</c:if>><span class="title">短信验证码</span>
				<div class="right_con">
				  <div class="w350 pull-left">
						<input type="text" check-type="required" data-callback="valOrganizationCode();" call-message="验证码错误" 
							required-message="请输入验证码!" placeholder="请输入验证码" id="val_code" name="val_code" 
							class="form-control error-custom hb100"  aria-describedby="basic-addon1">	
						<button class="certify certify-inner-button" id="btn_smscode" type="button" onclick="getValidateCode();">获取短信验证码</button>
					</div>
				</div>
		   </li>
		   
		   
		   <li><span class="title">固定电话</span>
				<div class="right_con">
					<div class="w200">
						<input  value="${userModel.telNumber}" type="text" id="userTel" name="userTel" class="form-control error-custom" style="width: 350px;">
					</div>
				</div>
		   </li>
		</ul>
		<input value="${uKeyId}" type="hidden" id="uKeyId" name="uKeyId">
		<div class="next_btn" style="margin: 60px auto 0; width: 220px;">
			<button class="btn btn-default" type="button" btn-type="true">保存/下一步</button>
		</div>
	</div>
	<input type="hidden" name="userId" value="${uKeyId}" id="userId">
	<input type="hidden" name="orginialMobilePhone" value="${userModel.mobilePhone}" id="orginialMobilePhone">
	<input type="hidden" name="isCheckTel" id="isCheckTel" value="${userModel.isCheckTel}">
	<input type="hidden" name="orginialCompanyBusinessLicenseCode" value="${userCompany.companyBusinessLicenseCode}" id="orginialCompanyBusinessLicenseCode">
	<input type="hidden" name="orginialBusinessLicenceImg" value="${attachmentBusinessLicense.generateName}" id="orginialBusinessLicenceImg">
	<input type="hidden" name="orginialCompanyOrganizationCode" value="${userCompany.companyOrganizationCode}" id="orginialCompanyOrganizationCode">
	<input type="hidden" name="orginialCompanyOperationName" value="${userCompany.companyOperationName}" id="orginialCompanyOperationName">
	<input type="hidden" name="orginialUserIdCardCode" value="${userCompany.companyOperationId}" id="orginialUserIdCardCode">
	<input type="hidden" name="orginialUserIdCardImg" value="${attachmentUserIdCard.generateName}" id="orginialUserIdCardImg">
    <input type="hidden" name="orginialCompanyOperationPosition" value="${userCompany.companyOperationPosition}" id="orginialCompanyOperationPosition">
    <input type="hidden" name="orginialUserMobilePhone" value="${userModel.mobilePhone}" id="orginialUserMobilePhone">
</form>


	<script type="text/javascript">
		require([ 'modules/account/_account_organization' ], function() {
		});
	</script>