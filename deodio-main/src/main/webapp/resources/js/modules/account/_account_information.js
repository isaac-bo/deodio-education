define(
		[ "jquery", "utils.cookie", "utils", "jquery.base", "jquery.validate",
				"fileupload.common" ],
		function($, cookie) {
			var userVerifiedImg = ctx + '/resources/img/account/certifed.png';
			var userUnverifiedImg = ctx + '/resources/img/account/uncertifed.png';
			var _init = function() {

				$('.form-group-description.error-custom').css('padding-left',
						'50px');
				$('select').selectpicker();
				customInput('userGender');

				var uploadUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='
						+ cookie.getCookie('CUID') + '&attachRelType=' + "7";
				uploadFileJqProcess("uploadIdCardSnapShot", uploadUrl, {
					accountId : cookie.getCookie('CAID'),
					attachRelType : "7",
					isModel : true
				}, 1, /(.|\/)(jpe?g|png)$/i, true, function(e, data) {
					var srcAttachTemp = data.result.data.attachUrl;
					var srcimgTemp = data.result.data.generateName;
					var srcimg = imgStatic + srcAttachTemp + srcimgTemp;
					$("#userIdCardImg").attr("src", srcimg);
					$("#userIdCardImgName").val(srcimgTemp);
					$("#userIdCardImgId").val(data.result.data.id);
					$("#userIdCardImg").removeAttr("style");
				}, function(e, data) {

				});
				$('#alertModel').on(
						'hidden.bs.modal',
						function() {
							$('body').removeClass('modal-open').css(
									'overflow-y', 'auto');

						});

			};

			_init();

			$('#accountInformationForm').myValidate({
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
					errorClass : "txt-wrong",
					rightClass : "txt-right"
				}
			});

			var updateUserInfo = function() {
				if(validateExistName()){
					alertMsg("当前用户昵称已存在!");
					return faslse
				}
				var data = {};
				if ($("#mobilePhone").val() == "" && $("#userMail").val() == "") {
					alertMsg("手机号和邮箱不能同时为空!");
					return faslse

				}
				if ($("#userIdCardImgId").val() == "") {
					alertMsg("请上传运营者手持身份证件照片");
					return;
				}
				;
				$("#accountInformationForm").find("[name]").each(function() {
					var name = $(this).attr("name");
					var value = $(this).val().trim();
					if (value) {
						data[name] = value;
					}
				});
				data.userGender = $("input[name='userGender']:checked").val();
				var orginialIdCardName=$("#orginialIdCardName").val();
	    		var orginialIdCardCode=$("#orginialIdCardCode").val();
	    		var orginialIdCardSnapShot=$("#orginialIdCardSnapShot").val();
	    		var idCardName=$("#idCardName").val();	
	    		var idCardCode=$("#idCardCode").val();	
	    		var userIdCardImgName=$("#userIdCardImgName").val();
	    		var idCardCode=$("#idCardCode").val();	
	    		var dataString=JSON.stringify(data);
				if((orginialIdCardName!='' && orginialIdCardName!=idCardName)||
				   (orginialIdCardCode!='' && orginialIdCardCode!=idCardCode)||
				   (orginialIdCardSnapShot!='' && orginialIdCardSnapShot!=userIdCardImgName)){
				confirmMsg("您更改了运营者信息中的必填项，该账户需要重新审核，审核期间所有人不能登录该账户，确定要修改吗？",function(){
					var url = "/account/update_information_profile.html", data = {
							uKeyId : $("#uKeyId").val(),
							orginialIdCardSnapShot:$("#orginialIdCardSnapShot").val(),
							extModelJson : dataString
						};
						postAjaxRequest(url, data, function(result) {
							alertMsg("修改成功");
							go2Page("/logout.html");
						});
				})}else{
				var url = "/account/update_information_profile.html", data = {
					uKeyId : $("#uKeyId").val(),
					orginialIdCardSnapShot:$("#orginialIdCardSnapShot").val(),
					extModelJson : dataString
				};
				postAjaxRequest(url, data, function(result) {
					alertMsg("修改成功");
					toNextLink();
					loadSubdoFormData();
					$("#header_nickName").text($("#nickName").val());
				});
			}
			};
			validateEmailExists = function() {
				var flag = false;
				var userMailOriginal = $.trim($("#userMailOriginal").val());
				var userMail = $.trim($("#userMail").val())
				if (userMailOriginal != userMail) {
					var url = "/signin/validateUserNameExists.html", data = {
						userName : $.trim($("#userMail").val()),
						registerType : 2
					};
					postAjaxRequest(url, data, function(result) {
						if (result.data) {
							flag = result.data;
						} else {
							return flag;
						}
					}, false);
				}
				return flag;
			}
			validateExistName = function() {
				var flag = false;
				var url = "/account/isExistNickName.html", data = {
					nickName : $.trim($("#nickName").val()),
					userId : $("#uKeyId").val()
				};
				postAjaxRequest(url, data, function(result) {
					flag = result.data;
				}, false);
				return flag;
			}

			validateExistDomain = function() {
				var flag = false;
				var url = "/account/validate/domain_exist.html", data = {
					subdomain : $.trim($("#accountDomain").val())
				};
				postAjaxRequest(url, data, function(result) {
					flag = result.data;
				}, false);
				return flag;
			}
			validateExistIdCard = function() {
				var idCardCodeOld = $.trim($("#idCardCodeOld").val());
				var idCardCode = $.trim($("#idCardCode").val());
				var flag = false;
				if (idCardCodeOld != idCardCode) {
					var url = "/account/validate/idCard_exist.html", data = {
						idCardCode : $.trim($("#idCardCode").val())
					};
					postAjaxRequest(url, data, function(result) {
						flag = result.data;
					}, false);
					return flag;
				} else {
					return flag;
				}
			}
			selectProince = function(sourceId, targetId) {
				getProvince($("#" + sourceId).val(), targetId);

			}

			selectCity = function(sourceId, targetId) {
				getCity($("#" + sourceId).val(), targetId);
			}
			checkValCode=function(code){
				return (/^\d{6}$/.test(code));
			}
			$("#val_code").blur(function() {
				var valCode=$.trim($("#val_code").val());
				console.log('val_code:   '+valCode);
				console.log('MV_CODE:   '+$.cookie("MV_CODE"));
				if(checkValCode(valCode)){
					var newMobile=$.trim($("#mobilePhone").val())
					if ( valCode== $.cookie("MV_CODE")) {
						var url = "/signin/userPhoneVerified.html", data = {
							userId : $("#uKeyId").val(),
							userMobile : newMobile
						};
						postAjaxRequest(url, data, function(result) {
							if (result.status) {
								$("#phoneVerifyImg").attr("src", userVerifiedImg);
								$("#phoneVerifySpan").html("已认证")
								$("#personal_val_code").hide();
								$("#orginialMobilePhone").val(newMobile);
							}
						});
					}else{
						$("#val_code").val('');
						$("#val_code").attr("placeholder","请输入验证码");
					}
				}else{
					console.log("验证码不是六位数字");
				}
				
			});

		 getValidateCode = function() {

				countDownTime("btn_smscode", "秒后 重新获取", "重新获取验证码");

				var url = "/commons/getSmsCode.html", data = {
					mobileNumber : $.trim($("#mobilePhone").val())
				};
				postAjaxRequest(url, data, function(result) {
					alert('手机验证码是 : ' + result.data.code);
					
				})
			};

			checkMobileExsist = function() {
				var flag = false;
				var orginialMobilePhone = $.trim($("#orginialMobilePhone")
						.val());
				var mobilePhone = $.trim($("#mobilePhone").val());
				console.log("checkMobileExsist---  orginialMobilePhone",orginialMobilePhone)
				console.log("checkMobileExsist---  mobilePhone",mobilePhone)
				var isCheckMail = $.trim($("#isCheckMail").val());
//				debugger;
				if (mobilePhone) {
					if (orginialMobilePhone != mobilePhone) {
						var url = "/signin/checkDetailRegisterMobile.html", data = {
							userMobile : mobilePhone,
							userId : $("#uKeyId").val()
						};
						postAjaxRequest(
								url,
								data,
								function(result) {
									flag = result.data.isExist;
									if (flag) {// 手机号已存在
										if (result.data.isSelfCheck) {// 手机号已验证了
											$("#personal_val_code").hide()
											$("#phoneVerifySpan").html("已认证")
											$("#phoneVerifyImg").attr("src",
													userVerifiedImg);
											$("#btn_smscode").attr('disabled',
													true);// 发送验证码按钮禁用
											$(
													"#accountOrganizationForm [name='val_code']")
													.attr("disabled", true);// 验证码文本框禁用
											$("#val_code").removeAttr(
													"check-type");
											$("#val_code").removeAttr(
													"data-callback");
											$("#val_code").removeAttr(
													"call-message");
											$("#val_code").removeAttr(
													"required-message");
											$("#isCheckTel").val("1");
										} else {
											$("#phoneVerifySpan").html("未认证")
											$("#personal_val_code").show()
											$("#phoneVerifyImg").attr("src",
													userUnverifiedImg);
											$("#btn_smscode").removeAttr(
													"disabled");// //发送验证码按钮可点击
											$("#val_code").removeAttr(
													"disabled");
											$(
													"#accountOrganizationForm [name='val_code']")
													.removeAttr("disabled");// //验证码文本框可用
											if (isCheckMail == 0) {
												$("#val_code").attr(
														'check-type',
														'required');
												$("#val_code").attr(
														'data-callback',
														valCode('btn_smscode'));
												$("#val_code")
														.attr('call-message',
																"验证码错误");
												$("#val_code").attr(
														'required-message',
														"请输入验证码");
											}
											$("#isCheckTel").val("0");
										}
									} else {
										$("#phoneVerifySpan").html("未认证")
										$("#personal_val_code").show()
										$("#phoneVerifyImg").attr("src",
												userUnverifiedImg);
										$("#btn_smscode")
												.removeAttr("disabled");// //发送验证码按钮可点击
										$("#val_code").removeAttr("disabled");
										$(
												"#accountOrganizationForm [name='val_code']")
												.removeAttr("disabled");// //验证码文本框可用
										if (isCheckMail == 0) {
											$("#val_code").attr('check-type',
													'required');
											$("#val_code").attr(
													'data-callback',
													valCode('btn_smscode'));
											$("#val_code").attr('call-message',
													"验证码错误");
											$("#val_code").attr(
													'required-message',
													"请输入验证码");
										}
										$("#isCheckTel").val("0");

									}
								}, false);
					}else{
						$("#phoneVerifySpan").html("已认证")
						$("#personal_val_code").hide()
						$("#phoneVerifyImg").attr("src",
								userVerifiedImg);
						$("#isCheckTel").val("1");
					}
				}
				return flag;
			}
			
			$("#mobilePhone").on('input propertychange', function() {
				var mobilePhone=$.trim($("#mobilePhone").val());
				if(checkMobile(mobilePhone)){
					$("#personal_val_code").show();
				}else{
					$("#personal_val_code").hide();
				}
				if($("#mobilePhone").val()!=$("#hiddenOldMobile").val()||$("#hiddenIsCheckTel").val()==0){
					$("#phoneVerifySpan").html("未认证");
					$("#phoneVerifyImg").attr("src",
							userUnverifiedImg);
				}else{
					$("#phoneVerifyImg").attr("src",
							userVerifiedImg);
					$("#phoneVerifySpan").html("已认证");
				}

			});
			
			var checkMobile=function(value){
				return (/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(value));
			}
			
			$("#userMail").on('input propertychange', function() {
				if($("#userMail").val()!=$("#hiddenOldMail").val()||$("#hiddenOldMail").val()==0){
					$("#userMailVerifySpan").html("未认证");
					$("#userMailVerifyImg").attr("src",
							userUnverifiedImg);
					$("#sendMail").show();
				}else{
					$("#userMailVerifySpan").html("已认证");
					$("#userMailVerifyImg").attr("src",
							userVerifiedImg);
					$("#sendMail").hide();
				}
			});

			
			valCode = function() {
				var valCode = $.trim($("#val_code").val());
				var flag = false;
				if (!valCode) {
					if ($.trim($("#val_code").val()) == $.cookie("P_CODE")) {
						flag = false;
					} else {
						flag = true;
					}
				}
				return flag;
			};
			cancleUserIdCardImg = function() {
				$("#userIdCardImg").attr("src",
						ctx + "/resources/img/account/user-card-1.jpg");
				$("#userIdCardImgId").val("");
			}
			changeOperationInfo=function(){
				$("#idCardName").removeAttr("disabled");
				$("#idCardCode").removeAttr("disabled");
				$("#uploadUserImageDiv").css("display","block");
				$("#deleteUserImgDiv").css("display","block");
			}

		});