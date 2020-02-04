define(
		[ "jquery", "utils.cookie", "utils", "jquery.base", "jquery.validate",
				"bootstrap.select", "jquery.scrolltofixed", "fileupload.common",
				"bootstrap.datepicker" ],
		function($, cookie) {
			var userDefaultHeaderPic=ctx+'/resources/img/close_icon.png';
			var userIconDefaultPic = ctx
					+ '/resources/img/account/homepaage_movie_travel.jpg';
			var userBusinessLicenseDefaultSnapShot = ctx
					+ '/resources/img/account/user-card-1.jpg';
			var userIdCardDefaultSnapShot = ctx
					+ '/resources/img/account/user-card-1.jpg';
			var userVerifiedImg = ctx + '/resources/img/account/certifed.png';
			var userUnverifiedImg = ctx
					+ '/resources/img/account/uncertifed.png';
			var validateCode = "";

			var resetPageData = function() {
				validateCode = "";
				$("#userInfoForm [name='userMobileVerify']").val(validateCode);
			}
			var emailUnVerifyShow=function(){
				$("#emailVerifyImg").show();
				$("#emailVerifyImg").attr("src",
						userUnverifiedImg);
				$("#emailVerifySpan").html("未认证")
			}
			var emailIsVerifyShow=function(){
				$("#emailVerifyImg").show();
				$("#emailVerifyImg").attr("src",
						userVerifiedImg);
				$("#emailVerifySpan").html("已认证")
				
			}
			var phoneUnVerifyShow=function(){
				$("#phoneVerifyImg").attr("src",
						userUnverifiedImg);
				$("#phoneVerifySpan").html("未认证");
				$("#userIsCheckTel").show()
				$("#userMobileVerify").val('')
			}
			var phoneIsVerifyShow=function(){
				$("#phoneVerifyImg").attr("src",
						userVerifiedImg);
				$("#phoneVerifySpan").html("已认证")
				$("#userIsCheckTel").hide()
			}
			//显示认证图标
			var phoneNumberHasVerify=function(){
				$("#phoneVerifyImg").show();
				$("#phoneVerifySpan").show();
			}
			//隐藏认证图标
			var phoneNumberNoVerify=function(){
				$("#phoneVerifyImg").hide();
				$("#phoneVerifySpan").hide();
			}
			var setUserMobileVerifyDisabled = function() {
				var mobileNumber = $("#userInfoForm [name='userMobile']").val();
				if (mobileNumber) {
					$("#userInfoForm [name='userMobileVerify']").attr(
							"disabled", false);
				}
			}
			var checkEmailAndPhoneIsVerify=function(){
				var email=$("#userInfoForm [name='userEmailVerify']").val();
				var phone=$("#userInfoForm [name='userMobile']").val();
				if(email!=null&&email!=''){
					checkEmailIsVerify(email)
				}
				if(phone!=null&&phone!=''){
					checkPhoneIsVerify(phone)
//					checkValidateMobile();
				}else{
					phoneNumberNoVerify();
					
				}
			}
			var checkEmailIsVerify=function(email){
				var url = "/signin/checkUserEmailIsVerified.html", data = {
						email : email
					};
					postAjaxRequest(url, data, function(result) {
						var flag=result.data;
						if(flag){
							emailIsVerifyShow();
						}else{
							emailUnVerifyShow();
						}
					});							
			}
			var checkPhoneIsVerify=function(phone){
				var url = "/signin/checkUserPhoneIsVerified.html", data = {
						phone : phone
				};
				postAjaxRequest(url, data, function(result) {
					var flag=result.data;
					if(flag){
						phoneIsVerifyShow();
					}else{
						phoneUnVerifyShow();
					}						
				});						
			}

			var _init = function() {
				$('.position').scrollToFixed();
				customInput('userGender');
				$('select').selectpicker();

				uploadFileJqProcess('uploadUserIcon', '*.jpg;*.png;', {
					accountId : cookie.getCookie('CAID'),
					attachRelType : "6",
					isModel : true
				}, ctx + '/commons/uploadAndUpdateAttach.html?presentationId='
						+ $.trim($("#courseId").val()) + '&r='
						+ Math.floor(Math.random() * 100), function(fileName) {
					// alert(fileName);
				},function(data) {
					var srcimg = imgStatic + data.attachUrl+ data.generateName;
					var srcimgTemp = data.generateName;
					$("#userIconImg").attr("src", srcimg);
					$("#userIconName").val(srcimgTemp);
					$("#userIconId").val(data.id);
					$(".pic_con").hide();
					$(".pic_con1").show();
				}, true, '上传新图片', '', 'btn up_color', 40, 240); 

				// $("#uploadUserIcon").hide();

				uploadFileJqProcess('userIdCardUploadFile', '*.jpg;*.png;', {
					accountId : cookie.getCookie('CAID'),
					attachRelType : "6",
					isModel : true
				}, ctx + '/commons/uploadAndUpdateAttach.html?presentationId='
						+ $.trim($("#courseId").val()) + '&r='
						+ Math.floor(Math.random() * 100), function(fileName) {
					// alert(fileName);
				},function(data) {
					var srcimg = imgStatic + data.attachUrl+ data.generateName;
					var srcimgTemp = data.generateName;
					$("#userIdCardImg").attr("src", srcimg);
					$("#userIdCardImgName").val(srcimgTemp);
					$("#userIdCardImgId").val(data.id);
				}, true, "上传");
				uploadFileJqProcess('uploadBusinessLicenceSnapShot','*.jpg;*.png;',
						{
							accountId : cookie.getCookie('CAID'),
							attachRelType : "6",
							isModel : true
						},
						ctx
								+ '/commons/uploadAndUpdateAttach.html?presentationId='
								+ $.trim($("#courseId").val()) + '&r='
								+ Math.floor(Math.random() * 100), function(
								fileName) {
							// alert(fileName);
						}, function(data) {
							var srcimg = imgStatic + data.attachUrl+ data.generateName;
							var srcimgTemp = data.generateName;
							$("#businessLicenceImg").attr("src", srcimg);
							$("#businessLicenceImgName").val(srcimgTemp);
							$("#businessLicenceImgId").val(data.id);
						}, true, "上传");
				
				// 日期控件
				$(".form_datetime").datetimepicker({
					format : "yyyy-mm-dd",
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					minView : 2,
					forceParse : 0
				});

				checkMobileVerifyEnable=function(){
					var userMobile=$.trim($("#userMobile").val());	
					if(isMobilePhone(userMobile)){
						//发送验证码按钮可点击
						$("#sendValidateCodeBtn").removeAttr("disabled");
						//验证码文本框可用
						$("#userInfoForm [name='userMobileVerify']").removeAttr("disabled");
					}else{
						//发送验证码按钮禁用
						$("#sendValidateCodeBtn").attr('disabled',true);
						//验证码文本框禁用
						$("#userInfoForm [name='userMobileVerify']").attr("disabled",true);
					}
				}
				
				$("#userMobile").on('input propertychange', function() {
					if($("#userMobile").val()!=$("#hiddenOldMobile").val()){
						$("#phoneVerifySpan").html("未认证");
						$("#userIsCheckTel").show();
						$("#phoneVerifyImg").attr("src",userUnverifiedImg);
					}else{
						
						$("#phoneVerifySpan").html("已认证");
						$("#userIsCheckTel").hide();
						$("#phoneVerifyImg").attr("src",userVerifiedImg);
					}

				});
				
				$("#userMobile").change(function() {
					var validateFlag=false;
					var userMobile=$.trim($("#userMobile").val());							
					if(isMobilePhone(userMobile)){
						var userId=$("#userId").val();
						$("#errorUserMobile").show();
						var url="/signin/checkDetailRegisterMobile.html",data={
								userMobile:userMobile,
								userId:userId
						};
						postAjaxRequest(url,data,function(result){
							if(result.data.isExist){
								if(result.data.isSelfCheck){
									phoneIsVerifyShow();
									phoneNumberUnExist();
									$("#is_check_tel").val("1");
								}else{
									//发送验证码按钮禁用
									$("#sendValidateCodeBtn").attr('disabled',true);
									//验证码文本框禁用
									$("#userInfoForm [name='userMobileVerify']").attr("disabled",true);
									$("#is_check_tel").val("0");
									validateFlag=true;
									//手机号码已存在
									phoneNumberIsExist();
									//不需要显示是否认证图标
									phoneNumberNoVerify();											
								}										
							}else{
								//发送验证码按钮可点击
								$("#sendValidateCodeBtn").removeAttr("disabled");
								//验证码文本框可用
								$("#userInfoForm [name='userMobileVerify']").removeAttr("disabled");
								$("#is_check_tel").val("0");
								phoneNumberUnExist();
								phoneNumberHasVerify();
								phoneUnVerifyShow();
							}
						},false);
					}else{
							//发送验证码按钮禁用
							$("#sendValidateCodeBtn").attr('disabled',true);
							//验证码文本框禁用
							$("#userInfoForm [name='userMobileVerify']").attr("disabled",true);
							$("#errorUserMobile").hide();
							phoneNumberNoVerify();									
						}
				});
				setUserMobileVerifyDisabled();
				checkEmailAndPhoneIsVerify();
			};
			$('#userInfoForm').myValidate({
				formCall : function() {
					updateUserInfo();
				},
				isAlert : false,
				formKey : false,
				errorCustom : {
					customFlag : true,
					regionText : true
				},
				errorStyle : {
					errorRegion : "error-custom",
					errorClass : "wrong-item",
					rightClass : "right-item"
				}
			});
			//验证码文本框输入完毕事件
			$("#userInfoForm [name='userMobileVerify']").blur(function(){
				if(!checkValidateCode()){
					var url = "/signin/userPhoneVerified.html", data = {
							userId : $("#userId").val().trim(),
							userMobile:$("#userMobile").val().trim()
					};
					postAjaxRequest(url, data, function(result) {
						if(result.status){
							phoneIsVerifyShow();
						}
					});				
				}
			});
			_init();
			updateUserInfo = function() {
					$("#userSetGender").val(getRadioVal("userGender"));
					var data = {};
					$("#userInfoForm").find("[name]").each(function() {
						var name = $(this).attr("name");
						var value = $(this).val().trim();
						if (value) {
							data[name] = value;
						}
					});
					var url = "/signin/enterprise/detail.html", data = {
						uKeyId : $("#userId").val(),
						extModelJson : JSON.stringify(data)
					};
					postAjaxRequest(url, data, function(result) {
						if (result.status == 1) {
							alertMsg("保存成功！");
							resetPageData();
							toNextLink();
						} else {
							alertMsg("设置失败！");
						}
					});
			}
			
			showIcon = function() {
				$("#userIcon").show();
				var userIconPicSrc = $("#userIconPic").attr("src");
				if (!userIconPicSrc) {
					$(".pic_con").show();
					$(".pic_con1").hide();
				} else {
					$(".pic_con1").show();
					$(".pic_con").hide();
					$("#userIconImg").attr("src", userIconPicSrc);
				}
			}

			hideIcon = function() {
				$("#userIcon").hide();
				var userIconSrc = $("#userIconImg").attr("src");
				if (userIconSrc != userIconDefaultPic) {
					$("#userIconPic").attr("src", userIconSrc);
				}
			}
			
			cancleUserIcon = function() {
				$("#userIconImg").attr("src", userIconDefaultPic);
				$("#userIconId").val('');
				$("#userIconName").val('');
			}
			//		
			cancleBusinessLicenseSnapShot = function() {
				$("#businessLicenceImg").attr("src",
						userBusinessLicenseDefaultSnapShot);
				$("#businessLicenceImgId").val('');
				$("#businessLicenceImgName").val('');
			}

			cancleUserIdCardImg = function() {
				$("#userIdCardImg").attr("src", userIdCardDefaultSnapShot);
				$("#userIdCardImgId").val('');
				$("#userIdCardImgName").val('');
			}

			selectProince = function(sourceId, targetId) {
				getProvince($("#" + sourceId).val(), targetId);

			}

			selectCity = function(sourceId, targetId) {
				getCity($("#" + sourceId).val(), targetId);
			}

			sendValidateCode = function(e) {
				$("#userMobileVerify").removeAttr("disabled");
				var mobilePhoneNumber=$("input[name='userMobile']").val().trim();
	             if(mobilePhoneNumber == ''){
	            	 alertMsg("手机号码不能为空！");
	             }else if(mobilePhoneNumber.length !=11){
	            	 alertMsg("请输入有效的手机号码！");
	             }else{
	 				var url = "/signin/checkUserPhoneIsVerified.html", data = {
	 						phone : mobilePhoneNumber
					};
					postAjaxRequest(url, data, function(result) {
						if (result.status == 1) {
							if (result.data) {
								alertMsg("该" + mobilePhoneNumber + "的手机号已经注册过了");
							}else {
								var _this = $(e);
								var btnId = _this.attr("id");
								// 调用发送验证码服务
								var url = "/commons/getSmsCode.html", data = {
									mobileNumber : mobilePhoneNumber
								};
								postAjaxRequest(url, data, function(result) {
									if (result.status == 1) {
										// $("#userInfoForm
										// [name='userMobileVerify']").removeAttr("disabled");
										countDownTime(btnId, "秒后获取验证码", "重新获取验证码");
										// 保存已获取的验证码
										validateCode = cookie.getCookie("MV_CODE");
										alertMsg('手机验证码是'+validateCode)
//										$("input[name='userMobileVerify']").val(validateCode);
//										checkEmailAndPhoneIsVerify();
									} else {
										alertMsg("发送验证码失败！");
									}
								});
							}
						}
					});	            	 	            	 	            	
	             }		
			}
			returnToLogin=function(){
				go2Page('/login.html');

			}
			checkValidateCode = function() {
				var result = true;
				var inputValidateCode = $.trim($(
						"#userInfoForm [name='userMobileVerify']").val());
				var isValid = (validateCode)
						&& (validateCode == inputValidateCode);
				if (isValid) {
					result = false;
					$("#is_check_tel").val("1")
				}else{
					result = true;
					$("#is_check_tel").val("0")
				}				
				return result;
			}
			checkCompanyBusinessLicenseCode=function(){
				var flag = false;
				var companyBusinessLicenseCode=$("#companyBusinessLicenseCode").val();
				var url="/signin/checkCompanyRegisterCode.html",data={
						companyNeedCheckCode:companyBusinessLicenseCode,
						type:"licenseCode",
						uKeyId : $("#userId").val()
				};
				postAjaxRequest(url,data,function(result){
					 flag = result.data;
				}, false);
				return flag;
			}
			checkCompanyOrganizationCode=function(){
				var flag = false;
				var companyOrganizationCode=$("#companyOrganizationCode").val();
				var url="/signin/checkCompanyRegisterCode.html",data={
						companyNeedCheckCode:companyOrganizationCode,
						type:"organizationCode",
						uKeyId : $("#userId").val()
				};
				postAjaxRequest(url,data,function(result){
					 flag = result.data;
				}, false);
				return flag;
			}
			checkIDCard=function(){
				var flag = false;
				var userIdCardCode=$("#userIdCardCode").val().trim();
				var url="/signin/checkUserIdCardCode.html",data={
						userIdCardCode:userIdCardCode,
						uKeyId : $("#userId").val()
				};
				postAjaxRequest(url,data,function(result){
					flag = result.data;
				}, false);
				return flag;
			}
//			$("#userMobile").blur(function(){
//				
//
//			});

			//手机号码不可用
			var phoneNumberIsExist=function(){
				var errorUserMobile=$("#errorUserMobile");
				errorUserMobile.html('手机号码已经存在');
				errorUserMobile.removeClass('error-custom').addClass('error-custom wrong-item');
			}
			//手机号码可用
			phoneNumberUnExist=function(){
				var errorUserMobile=$("#errorUserMobile");
				errorUserMobile.html('');
				errorUserMobile.removeClass('error-custom wrong-item').addClass('error-custom');
			}
			$("#email").blur(function(){
				var email=$.trim($("#email").val());
				var userId=$.trim($("#userId").val());
				if(email.length>0){
					if (CheckMail(email)) {
						var url = "/signin/selectUserByExample.html", data = {
								userEmail : email
						};
						postAjaxRequest(url, data, function(result) {
							if (result.status == 1) {
								var data=result.data;
								if (data.isExisted) {
									if(data.userInfo!=null){
										if(data.userInfo.id!=userId){										
											$("#emailVerifyImg").hide();
											$("#emailVerifySpan").html("该邮箱已经注册了");
										}
									}
								}else{
									$("#emailVerifySpan").html("");
									emailUnVerifyShow();
								}
							}
						});
					}else{
						$("#emailVerifySpan").html("邮箱格式不正确")
					}
				}else{
					$("#emailVerifySpan").html('')
				}
			});
			sendEmailVerify = function() {
				var companyEmail = $("#email").val().trim();
				var userId = $("#userId").val().trim();
				if (companyEmail.length > 0) {
					if (CheckMail(companyEmail)) {
						sendEmailVerifyUri(companyEmail, userId);
					}
				} else {
					alertMsg("邮箱不能为空！");
				}
			}
			// 发送邮箱验证链接
			var sendEmailVerifyUri = function(email, userId) {				
				var url = "/signin/selectUserByExample.html", data = {
						userEmail : email
				};
				postAjaxRequest(url, data, function(result) {
					if (result.status == 1) {
						if (result.data.isExisted) {
							if(result.data.userInfo.id==userId){
								if(result.data.userInfo.isCheckMail==0){
									sendVerifyCodeToEmail(email,userId);
								}else{
									emailIsVerifyShow();
								}								
							}else{
								alertMsg("该" + email + "的邮箱已经注册过了");
								$("#email").val('');
							}
						} else {
							sendVerifyCodeToEmail(email,userId);
						}
					}
				});

			}
			sendVerifyCodeToEmail=function(email,userId){
				var url = "/signin/sendRegisterEmailVerify.html", data = {
						userEmail : email,
						projectSrc:ctx,
						userId:userId,
						verifyUrl:'/signin/userEmailVerified.html?email='
					};
				postAjaxRequest(url, data, function(result) {
					if (result.status == 1) {
						alertMsg("已经向" + email + "的邮箱发送了验证链接,请通过链接" + "进行邮箱激活");
						checkEmailAndPhoneIsVerify();
					}
				});
			}
			// 判断邮箱格式
			CheckMail = function(mail) {
				var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if (filter.test(mail))
					return true;
				else {
					alertMsg('您的电子邮件格式不正确');
					return false;
				}
			}
			//判断邮编格式
			checkEmailCode=function(mailCode){
				var mailCodeValue= /^[1-9][0-9]{5}$/;
				if(mailCodeValue.test(mailCode)){
					return true;
				}else{
					return false;
				}
			}
			$("#companyZipCode").blur(function(){
				var companyZipCode=$("#companyZipCode").val();
				if(companyZipCode.length>0){
					if(!checkEmailCode(companyZipCode)){
						$("#companyZipCodeError").removeClass('error-custom').addClass('error-custom wrong-item')
						$("#companyZipCodeError").html("邮编格式不对")
					}else{
						$("#companyZipCodeError").removeClass('error-custom wrong-item').addClass('error-custom')
						$("#companyZipCodeError").html("")
					}
				}else{
					$("#companyZipCodeError").removeClass('error-custom wrong-item').addClass('error-custom')
					$("#companyZipCodeError").html("")
				}
				
			});
//			$("#userIdCardCode").blur(function(){
//				var userIdCardCode=$("#userIdCardCode").val().trim();
//				var userIdCardCodeError=$("#userIdCardCodeError");
//				if(userIdCardCode.length>0){
//					var url = "/signin/checkUserIdCardCode.html", data = {
//							userIdCardCode:userIdCardCode
//							};
//						postAjaxRequest(url, data, function(result) {
//							if (result.data) {
//								userIdCardCodeError.removeClass('error-custom').addClass('error-custom wrong-item');
//								userIdCardCodeError.html('该身份信息已经注册过');
//							}
//						});
//				}else{
//					userIdCardCodeError.removeClass('error-custom').addClass('error-custom wrong-item');
//					userIdCardCodeError.html('身份证号码不能为空');
//				}
//				
//				
//			});
			
			$("#companyBusinessLicenseFoundedDate").change(function(){
				var foundedDate=$("#companyBusinessLicenseFoundedDate").val()
				if(foundedDate.length>0){
					var businessTerm=$("#companyBusinessLicenseBusinessTerm").val();
					if(businessTerm.length>0){
						var errorDateTips=$("#errorCompanyBusinessLicenseFoundedDate");
						var errorTermTips=$("#errorCompanyBusinessLicenseBusinessTerm");
						if(compareToDate(foundedDate,businessTerm)){
							foundDateCompareTermDateError(errorDateTips,errorTermTips);

						}else{
							foundDateCompareTermDatePass(errorDateTips,errorTermTips);
						}
					}
				}
			
			})
			
			$("#companyBusinessLicenseBusinessTerm").change(function(){
				var businessTerm=$("#companyBusinessLicenseBusinessTerm").val();
				if(businessTerm.length>0){
					var errorDateTips=$("#errorCompanyBusinessLicenseFoundedDate");
					var foundedDate=$("#companyBusinessLicenseFoundedDate").val();
					if(foundedDate.length>0){
						var errorTermTips=$("#errorCompanyBusinessLicenseBusinessTerm");
							if(compareToDate(foundedDate,businessTerm)){
								foundDateCompareTermDateError(errorDateTips,errorTermTips);
							}else{
								foundDateCompareTermDatePass(errorDateTips,errorTermTips);
							}		
					}
				}

			});
			foundDateCompareTermDateError=function(errorDateTips,errorTermTips){
				errorTermTips.removeClass('error-custom').addClass('error-custom wrong-item');
				errorTermTips.html('营业期限不能小于成立日期');
				errorDateTips.removeClass('error-custom').addClass('error-custom wrong-item');
				errorDateTips.html('成立日期不能大于营业期限');
			}
			var foundDateCompareTermDatePass=function(errorDateTips,errorTermTips){
				errorDateTips.removeClass('error-custom wrong-item').addClass('error-custom');
				errorDateTips.html('');
				errorTermTips.removeClass('error-custom wrong-item').addClass('error-custom');
				errorTermTips.html('');
			}
			
			registerKeyUp=function(obj,showAttach,attachName,attachId,attachRelType){
				var uploadUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+$.trim($("#userId").val())+'&attachRelType='+attachRelType;
				var uploadId=$(obj).attr('id');
				var f=$(obj).val();
				if(f == null || f ==undefined || f == ''){
	                return false;
	            }
	            if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
	            {
	                $(obj).val('');
	                return false;
	            }            
	            uploadFileJqProcess(uploadId,uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:attachRelType,isModel:true},
					1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
	            		var srcAttachTemp=data.result.data.attachUrl;
	            		var srcimgTemp = data.result.data.generateName;
	            		var srcimg = imgStatic+srcAttachTemp+srcimgTemp;
	            		$("#"+showAttach).attr("src",srcimg);
	            		$("#"+attachName).val(srcimgTemp);
	            		$("#"+attachId).val(data.result.data.id);
	            		if(uploadId=='uploadUserIcon'){
	            			$(".pic_con").hide();
	            			$(".pic_con1").show();
	            			cookie.setCookie('CUHP',(srcAttachTemp+srcimgTemp),600);
	            		}
	            		$("#"+showAttach).removeAttr("style");
	            },function(e,data){
			
	            });
			}
			deleteFileImg=function(showAttach,AttachName,AttachId,defaultPic){
				$("#"+showAttach).attr("src",eval(defaultPic));
				$("#"+AttachName).val('');
				$("#"+AttachId).val('');
			}

		});
