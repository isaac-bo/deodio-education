define(["jquery","utils.cookie","utils","jquery.base","jquery.validate","upload.common"], function($,cookie) {
	var userVerifiedImg = ctx + '/resources/img/account/certifed.png';
	var userUnverifiedImg = ctx + '/resources/img/account/uncertifed.png';
	$('.form-group-description.error-custom').css('padding-left','50px');

	$('#informationForm').myValidate({
		formCall:function(){ updateUserInfo();},
		isAlert:false,
		formKey:false,
		errorCustom:{customFlag:true,regionText:true},
		errorStyle:{errorRegion:"error-custom",errorClass:"txt-wrong",rightClass:"txt-right"}
	});

	var updateUserInfo=function(){
		var data= {};
		$("#informationForm").find("[name]").each(function(){
			var name = $(this).attr("name");
			var value = $(this).val().trim();
			if(value){
				data[name] = value;
			}
		});
		data.userGender =$("input[name='userGender']:checked").val();
		var extModelJson=JSON.stringify(data);
		console.log('extModelJson  ',extModelJson);
		var url="/account/update_information_profile_noaccount.html",data = {
				uKeyId:$("#uKeyId").val(),
				extModelJson:extModelJson
			};
		postAjaxRequest(url, data, function(result){
			if(result.status==1){
			alertMsg("修改成功");
			//go2Page('/user/profile.html');
			//toNextLink();
			}
		});
	};
	
	validateExistName=function(){
		var flag = false;
		var url="/account/isExistNickName.html",data={
				nickName : $.trim($("#nickName").val()),
				userId : $("#uKeyId").val()
		};
		postAjaxRequest(url, data,function(result){
			flag=result.data;
		},false);
		return flag;
	}
	
//	$("#val_code").blur(function() {
//		console.log('val_code:   '+$("#val_code").val());
//		console.log('MV_CODE:   '+$.cookie("MV_CODE"));
//		var newMobile=$.trim($("#mobilePhone").val())
//		if ($("#val_code").val() == $.cookie("MV_CODE")) {
//			var url = "/signin/userPhoneVerified.html", data = {
//				userId : $("#uKeyId").val(),
//				userMobile : newMobile
//			};
//			postAjaxRequest(url, data, function(result) {
//				if (result.status) {
//					$("#phoneVerifyImg").attr("src", userVerifiedImg);
//					$("#phoneVerifySpan").html("已认证")
//					$("#personal_val_code").hide();
//					$("#orginialMobilePhone").val(newMobile);
//				}
//			});
//		}
//	});	
	
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
	
	 getValidateCode = function() {

			countDownTime("btn_smscode", "秒后 重新获取", "重新获取验证码");

			var url = "/commons/getSmsCode.html", data = {
				mobileNumber : $.trim($("#mobilePhone").val())
			};
			postAjaxRequest(url, data, function(result) {
				alert('手机验证码是 : ' + result.data.code);
				
			})
		};
	
	   checkMobileExsist = function(){
		   var orginialMobilePhone = $.trim($("#orginialMobilePhone").val());
		   var mobilePhone = $.trim($("#mobilePhone").val());
		   if (orginialMobilePhone != mobilePhone) {
			   var flag=false;;
			   var url = "/signin/checkDetailRegisterMobile.html", data = {
						userMobile : mobilePhone,
						userId : $("#uKeyId").val()
					};
				postAjaxRequest(url, data,function(result){
					flag = result.data.isExist;
					if (flag) {// 手机号已存在
						if (result.data.isSelfCheck) {// 手机号已验证了
							flag=false;
							$("#personal_val_code").hide()
							$("#phoneVerifySpan").html("已认证")
							$("#phoneVerifyImg").attr("src",userVerifiedImg);
							$("#isCheckTel").val("1");
						} else {
							$("#phoneVerifySpan").html("未认证")
							$("#personal_val_code").show()
							$("#phoneVerifyImg").attr("src",
									userUnverifiedImg);
							$("#isCheckTel").val("0");
						}
					} else {
						$("#phoneVerifySpan").html("未认证")
						$("#personal_val_code").show()
						$("#phoneVerifyImg").attr("src",userUnverifiedImg);
						$("#isCheckTel").val("0");

					}
				},false);

		   }else{
				$("#phoneVerifySpan").html("已认证")
				$("#personal_val_code").hide()
				$("#phoneVerifyImg").attr("src",
						userVerifiedImg);
				$("#isCheckTel").val("1");
			}
		
		
		return flag;
	}
		validateEmailExists = function() {
			var flag=false;
			var userMailOriginal= $.trim($("#userMailOriginal").val());
			var userMail= $.trim($("#userMail").val())
			if(userMailOriginal!=userMail){
			    var url = "/signin/validateUserNameExists.html", data = {
				userName : $.trim($("#userMail").val()),
				registerType : 2
			    };
			    postAjaxRequest(url, data, function(result) {
				if (result.data) {
					 flag=result.data;
				} else {
					return flag;
				}
			    },false);
			}
			if(!flag){
				$("#emailCertifedImg").hide();
				$("#sendMail").show();
			}
	       return flag;
		}
		sendEmailVerify=function(){
			var email = $("#userMail").val().trim();
			var userId = $("#uKeyId").val().trim();
			var url = "/signin/sendRegisterEmailVerify.html", data = {
					userEmail : email,
					projectSrc:ctx,
					userId:userId,
					verifyUrl:'/signin/userEmailVerified.html?email='
				};
			postAjaxRequest(url, data, function(result) {
				if (result.status == 1) {
					alertMsg("已经向" + email + "的邮箱发送了验证链接,请通过链接进行邮箱激活");
					checkEmailAndPhoneIsVerify();
				}
			});
		}
});

