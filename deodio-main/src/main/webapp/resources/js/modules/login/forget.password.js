	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate"], function($,cookie) {
		
		var _init = function(){
		
			$(".tab-item ul li .a-color").each(function(i,obj){
				$(obj).click(function(){
					$(".tab-item ul li a.a-color").each(function(){
						$(this).find("span").first().removeClass($(this).attr("id")+"-click").addClass($(this).attr("id")+"-");
					});
					$(this).find("span").first().removeClass($(this).attr("id")+"-").addClass($(this).attr("id")+"-click");
					$(".tab-detail").css("display","none").eq(i).css("display","block");
					$(".tab-item ul li a.a-color").removeClass("on").eq(i).addClass("on");
					
				});
			});
		};
	
	
		
		$('#forgetForm').myValidate(
				{
					formCall:function(){submitForgetForm();},
					isAlert:false,
					formKey:false,
					errorCustom:{customFlag:true,regionText:true},
					errorStyle:{errorRegion:"error-custom",errorClass:"wrong-word",rightClass:"right-item"}
		});	
			
		
		$('#updateForm').myValidate(
				{
					formCall:function(){updateForm();},
					isAlert:false,
					formKey:false,
					errorCustom:{customFlag:true,regionText:false},
					errorStyle:{errorRegion:"error-custom",errorClass:"wrong-word",rightClass:"right-item"}
		});	
			
		
		var updateForm = function(){
			
			var url="/signin/update_pwd.html",data={
					uKeyId:$("#uKeyId").val(),
					password:$.trim($("#pwd_id").val())
			};
			
			postAjaxRequest(url, data, function(result){
				
				if(result.status){
					alertMsg('更改密码成功',350,200,function(){
						go2Page('/login.html');
					});
//					alert('更改密码成功')
//					open2Page('/login.html');
				}else{
					alertMsg('更改密码失败，请重新尝试。',350,200,function(){
//						open2Page('/login.html');
					});
				}
				
			});
		}
		
		
		
		var submitForgetForm = function(){
			
			var url="/signin/forget_pwd_2.html",data={
					s:2,
					uKeyId:$("#uKeyId").val(),
					userName:$.trim($("#userName").val())
			};
			postAjaxRequest(url, data, function(result){
				$('div[id^=step_]').hide();
				$('#step_2').show();
//				if($.trim($("#userMailHidden").val())==''){
//					$("#getPwdByEmail").hide();
//				}
//				if($.trim($("#userPhoneHidden").val())==''){
//					$("#getPwdByPhone").hide();
//				}
				$('#smsMsg').html('短信验证:'+result.data.mobilePhone.substring(0,3)+'****'+result.data.mobilePhone.substring(8,11));
				
				var len =  result.data.userMail.split('@')[0].length;
				$('#emailMsg').html('密保邮箱:****'+result.data.userMail.substring(len-2,len)+'@'+result.data.userMail.split('@')[1]);
			});
		};
		
		
		getSmsCode = function(){
			
			countDownTime("btn_smscode","秒后 重新获取","重新获取验证码");
			var url="/commons/getSmsCode.html",data={
					mobileNumber:$.trim($("#uvmobile").val())
			};
			postAjaxRequest(url, data,function(result){
//				alert('验证码是:'+result.data.code);
				alertMsg('验证码是:'+result.data.code);
				
			});
		};
		
		getValidateCode = function(){
			
			countDownTime("btn_mailcode","秒后 重新发送","重新发送邮件");
			
			var url="/commons/getEmailCode.html",data={
					emailUrl:$.trim($("#userMailHidden").val())
			};
			postAjaxRequest(url, data,function(result){
				
			});
			
		};
		
		
		
		
		validateUserNameExists = function(){
			
			var flag=false;
			var url="/signin/getUserBaseInfo.html",data={
				userName:$.trim($("#userName").val())
			};
			postAjaxRequest(url, data,function(result){
				flag=result.data.isExisted;
				if(flag){
					$("#userMailHidden").val(result.data.userModel.userMail);
					$("#userPhoneHidden").val(result.data.userModel.mobilePhone);
					$("#uKeyId").val(result.data.userModel.id);
				}
				
			},false);
			return !flag;
		};
		choiseEmailFindPwd=function(){
			
			var emailValueHidden=$("#userMailHidden").val();
			if(emailValueHidden==null){
				alertMsg('你的账户未绑定邮箱,请更换其他方式');
			}
		}
		choisePhoneFindPwd=function(){
			
			var phoneValueHidden=$("#userPhoneHidden").val();
			if(phoneValueHidden==null||phoneValueHidden==''){
				alertMsg('你的账户未绑定手机号码,请更换其他方式');
			}
		}
		onChangePwd = function(type){
			debugger
			var _tabValue = $(".a-color.on").attr("id"),in_code='',ser_Code='';
			
			if(type=="email"){
				in_code = $.trim($("#mail_code").val());
				ser_Code = cookie.getCookie("P_CODE");
			}else if(type=="phone"){
				in_code = $.trim($("#sms_code").val());
				ser_Code = cookie.getCookie("MV_CODE");
			};
			
			if($.trim(in_code).length==0){
				alertMsg("请输入验证码");
				return;
			};
				
			if(in_code!=ser_Code){
				alertMsg("验证码错误");
				return;
			}else{
				var url="/signin/forget_pwd_3.html",data={
						s:3,
						uKeyId:$("#uKeyId").val(),
						userName:$.trim($("#userName").val())
				};
				postAjaxRequest(url, data, function(result){
					$('div[id^=step_]').hide();
					$('#step_3').show();
				});
			}
			
			
		};
		
		
		onChangeKaptcha = function(){
			$("#captcha").attr('src',ctx+'/commons/captcha-image.html?'+ Math.floor(Math.random() * 100));
		};
		valCode = function(){
			if($.trim($("#val_code").val())==cookie.getCookie("P_CODE")){
				return false;
			}else{
				return true;
			}
		};
		
		onSelectCurrent = function(step){
			$("div[id^=step]").each(function(){
				$(this).hide();
			});
			
			$("li[id^=tab]").each(function(){
				$(this).removeClass("on").find("label").first().removeClass("on");
			});
			
			$("#step-"+step).show();
			$("#tab-"+step).addClass("on").find("label").first().addClass("on");
		}
		
		_init();
		
	});

