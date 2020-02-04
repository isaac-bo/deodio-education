	define(["jquery", "utils.cookie", "utils", "jquery.base", "jquery.validate"], function($, cookie) {
		
		var registerTypeThreePartner = '4';
		var registerTypeCompany = '3';
		var registerTypePhone = '1';
		var registerTypeEmail = '2';
		var userRegisterType='0';
		
		var _init = function(){
			$('#mobileForm').myValidate(
					{
						formCall:function(){submitSigin();},
						isAlert:false,
						formKey:false,
						errorCustom:{customFlag:true,regionText:true},
						errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
				});	
			
			$('#emailForm').myValidate(
					{
						formCall:function(){submitSigin();},
						isAlert:false,
						formKey:false,
						errorCustom:{customFlag:true,regionText:true},
						errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
				});	
			
			$('#companyForm').myValidate(
					{
						formCall:function(){submitSigin();},
						isAlert:false,
						formKey:false,
						errorCustom:{customFlag:true,regionText:true},
						errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
				});	
			

		};
		submitSigin = function(){
			var _tabValue = $(".a-color.on").attr("id"),userName='',password='',companyIndustry='',registerType='';
			if(_tabValue=="email"){
				userName = $.trim($("#emailName").val());
				password = $.trim($("#emailPassWord").val());
				registerType = registerTypeEmail;
				userRegisterType=registerTypeEmail;
			}else if(_tabValue=="phone"){
				userName = $.trim($("#mobileNumber").val());
				password = $.trim($("#mobilePassword").val());
				registerType = registerTypePhone;
				userRegisterType=registerTypePhone;
			}else if(_tabValue=="company"){
				userName = $.trim($("#companyName").val());
				password = $.trim($("#companyPassWord").val());
				companyIndustry = $.trim($("#companyIndustry").val());
				registerType = registerTypeCompany;
				userRegisterType=registerTypeCompany;
			};
			
			var registerData = {
				userName:userName,
				password:password,
				companyIndustry:companyIndustry,
				registerType:registerType
			}
			if(registerType==registerTypeEmail){
				debugger;
				var url = "/signin/sendRegisterEmailVerify.html", data = {
						userEmail : userName,
						projectSrc:ctx,
						password:password,
						verifyUrl:'/signin/userRegEmailVerified.html?le='
					};
					postAjaxRequest(url, data, function(result) {
						if (result.status == 1) {
//							alert("已经向" + email + "的邮箱发送了验证链接,请在邮箱内进行激活");
//							onSignin();
							$('#signin-panel').hide();
							$('#signin-success').show();
							$('#registerSuccessTip').html('请到你的邮箱中点击确认链接完成邮箱认证');
							$('#registerBoxSize').hide();
						}else{
							alert("向" + email + "的邮箱发送验证失败");
						}
					});
//				var url="/signin/insertUserByRegister.html",data={
//						registerJson:JSON.stringify(registerData)
//					};
//					postAjaxRequest(url, data, function(result){
//						var RegisterUserId=result.data.userId;
//						$("#uKeyId").val(RegisterUserId);
//						var url = "/signin/sendRegisterEmailVerify.html", data = {
//								userEmail : userName,
//								projectSrc:ctx,
//								userId:RegisterUserId,
//								verifyUrl:'/signin/userRegEmailVerified.html?email='
//							};
//							postAjaxRequest(url, data, function(result) {
//								if (result.status == 1) {
////									alert("已经向" + email + "的邮箱发送了验证链接,请在邮箱内进行激活");
////									onSignin();
//									$('#signin-panel').hide();
//									$('#signin-success').show();
//									$('#registerSuccessTip').html('请到你的邮箱中点击确认链接完成邮箱认证');
//									$('#registerBoxSize').hide();
//								}else{
//									alert("向" + email + "的邮箱发送验证失败");
//								}
//							});
//						});
//				toRegister(registerData);
//				sendEmailVerify(userName);			
//				$("#registerSuccessTip").html("邮箱注册成功,请先在邮箱中完成验证");
				
			}else{
				toRegister(registerData);
				onSignin()
			}
				
		};
//		sendEmailVerify=function(email){
//			var url = "/signin/sendRegisterEmailVerify.html", data = {
//					userEmail : email,
//					projectSrc:ctx,
//					userId:$("#uKeyId").val()
//				};
//				postAjaxRequest(url, data, function(result) {
//					if (result.status == 1) {
//						alert("已经向" + email + "的邮箱发送了验证链接,请在邮箱内进行激活");
//					}else{
//						alert("向" + email + "的邮箱发送验证失败");
//					}
//				});
//		}
//		checkEmailIsVerified();	
//		checkEmailIsVerified=function(){
//			var userVirifiedEmail=$.trim($("#userVirifiedEmail").val());
//			if(userVirifiedEmail.length>0){
//				
//			}
//		}
		toRegister=function(registerData){
			var url="/signin/insertUserByRegister.html",data={
					registerJson:JSON.stringify(registerData)
				};
				postAjaxRequest(url, data, function(result){
					$("#uKeyId").val(result.data.userId);
//						var registerInfo = result.data;
//						var userId = registerInfo.userId;
//						var registerType = registerInfo.registerType;
//						
//						//企业账户
//						if(registerType == registerTypeCompany){
//							onSignin()
//							$("#uKeyId").val(registerInfo.userId);
//						}else{
//							$('#signin-panel').hide();
//							$("#signin-success-login").show();
//						}
					});
		}
		getValidateCode = function(){
			
			countDownTime("btn_smscode","秒后 重新获取","重新获取验证码");
			
			var url="/commons/getSmsCode.html",data={
					mobileNumber:$.trim($("#mobileNumber").val())
			};
			postAjaxRequest(url, data,function(result) {
				alert("手机注册验证码是"+result.data.code);
			});

		};
		valCode = function(){
			
			var _tabValue = $(".a-color.on").attr("id"),in_code='',ser_Code='';
			
			if(_tabValue=="email"){
				in_code = $.trim($("#email_val_code").val());
				ser_Code = cookie.getCookie("P_CODE");
			}else if(_tabValue=="phone"){
				in_code = $.trim($("#val_code").val());
				ser_Code = cookie.getCookie("MV_CODE");
				
			};
	
			if(in_code==ser_Code){
				return false;
			}else{
				return true;
			}
		}

	validateUserNameExists = function(){
		
		var _tabValue = $(".a-color.on").attr("id"),userName='',registerType='';
		
/*		
 * 之前的代码
  	if(_tabValue=="email"){
			userName = $.trim($("#emailName").val());
			registerType = registerTypeEmail;
		}else if(_tabValue=="phone"){
			userName = $.trim($("#mobileNumber").val());
			registerType = registerTypePhone;
		}else if(_tabValue=="company"){
			userName = $.trim($("#companyName").val());
			registerType = registerTypeCompany;
		};*/
		//修改之后的代码
		var name;
		debugger;
		if(_tabValue=="email"){
			name = $("#emailName");
			registerType = registerTypeEmail;
			console.log("registerTypeEmail--",registerTypeEmail);
		}else if(_tabValue=="phone"){
			name = $("#mobileNumber");
			registerType = registerTypePhone;
			console.log("registerTypePhone--",registerTypePhone);
		}else if(_tabValue=="company"){
			name =$("#companyName");
			registerType = registerTypeCompany;
			console.log("registerTypeCompany--",registerTypeCompany);
		};
		var userName=$.trim(name.val());
		name.attr('call-message',"当前企业已被注册");
		if(registerType=="3"){
			if(userName.length>30){
				name.attr('call-message',"公司名称不能大于30个字");
				return true;
			}			
		}
		/*原来的代码*/
//		var url="/signin/validateUserNameExists.html",data={
//			userName:userName,
//			registerType:registerType
//		};
		/*修改的代码*/
		console.log("_tabValue--",_tabValue);
		console.log("registerType--",registerType);
		return checkAccountUserNameExist(userName,registerType);
	}
	checkAccountUserNameExist=function(userName,registerType){
		var flag=false;
		var url="/signin/checkAccountUserNameExists.html",data={
				userName:userName,
				registerType:registerType
		};
		postAjaxRequest(url, data,function(result){
			var datas=result.data;
			var accountInfo=datas.accountInfo;
			var userInfo=datas.userInfo;						
			console.log('userInfo---',userInfo);
			console.log('accountInfo---',accountInfo);
			debugger;
			if(accountInfo!=undefined){
				flag=true;
				if(accountInfo.isValid!=1){
					var d=new Date();
					var dTime=d.getTime();
					var cTime=accountInfo.createTime;
					var registerBetweenDay=(dTime-cTime)/(1000*60*60*24);
					console.log("dTime ",dTime);
					console.log("cTime ",cTime);
					console.log("registerBetweenDay ",registerBetweenDay);
					if (registerBetweenDay>=30) {
						if(registerType=='3'){							
							$("#confirmPasswordModal").modal("show");
							$("#hidden_confirm_password").val(userInfo.passWord);
							$("#hidden_confirm_userName").val(userInfo.userName);
						}else{
							flag=false;
						}
					}else{
						alertMsg("您注册的账户正在审核中，我们会尽快处理，请您耐心等待..");
					}
				}			
			}else{
				flag=false;
				if (userInfo!=undefined&&registerType=='3') {
					$("#confirmPasswordModal").modal("show");
					$("#hidden_confirm_password").val(userInfo.passWord);
				}
			}
		},false);
		return flag;
	}
	$('#confirmPasswordModal').on('shown.bs.modal', function () {	
		$("#input_password").val('');
	});
	//modal关闭事件
	$('#confirmPasswordModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});

	submitPassword=function(){
		var backFlag=false;
		var inputPassword=$.trim($("#input_password").val());
		var hiddenConfirmPassword=$.trim($("#hidden_confirm_password").val());
		console.log("submitPassword---hiddenConfirmPassword   ",hiddenConfirmPassword);
		console.log("submitPassword---inputPassword   ",inputPassword);
		var url = "/signin/getMd5Digest.html", data = {
				password : inputPassword
			};
			postAjaxRequest(url, data, function(result) {
				var passwordMd5=result.data;
				console.log("submitPassword--result.data   "+passwordMd5);
				if(passwordMd5==hiddenConfirmPassword){
					companyInfoValidPass();
				}else{
//					confirmMsg("密码不一致,是否重新输入?",function(){
//						$("#confirmPasswordModal").modal("show");
//					},400,240);
					alertMsg("你输入的密码不正确");
				}
			},false);
	}
	var companyInfoValidPass=function(){
		
		var chooiceRegisterType=$(".a-color.on").attr("id");
		console.log('chooiceRegisterType',chooiceRegisterType);	
		var inputText;
		if(chooiceRegisterType=="email"){
			inputText=$("#emailName");
		}else if(chooiceRegisterType=="phone"){
			inputText=$("#mobileNumber");	
		}else if(chooiceRegisterType=="company"){
			inputText=$("#companyName");		
		};
		inputText.removeAttr('lable-error');
		console.log('companyInfoValidPass inputText.parent()---1',inputText.parent());
		inputText.parent().removeClass('wrong-item').addClass('right-item');
		console.log('companyInfoValidPass inputText.parent()---2',inputText.parent());
		inputText.val($.trim($("#hidden_confirm_userName").val()));
	}
		onTabs = function(tabName){
			
			$('.a-color').each(function(){
				var id = $(this).attr("id");
				$(this).removeClass('on');
				$(this).find("span").each(function(){
					$(this).removeClass(id+"-click");
					$(this).addClass(id+"-");
				});
				$(this).next().hide();
			});
			
			$("#"+tabName).addClass('on');
			$("#"+tabName).next().show();
			
		};
		
		onChangeKaptcha = function(){
			$("#captcha").attr('src',ctx+'/commons/captcha-image.html?'+ Math.floor(Math.random() * 100));
		}
		
		onSignin = function(){
			$('#signin-panel').hide();
			$('#signin-success').show();
		};
		
		onCancel = function(){
			/*alertMsg("确定关闭吗？有效身份信息未填写，可能会影响您的审核结果！");
			var confirmCancel1=confirmMsg("确定关闭吗？有效身份信息未填写，可能会影响您的审核结果！");*/
			debugger;
			confirmMsg("确定关闭吗？有效身份信息未填写，可能会影响您的审核结果！",function(){
				window.location.href = ctx + "/login.html?r=" + Math.floor(Math.random() * 100);
			});
		};
		
		onDetails = function(){
			var userId=$("#uKeyId").val();

			if(userRegisterType=='2'){
				var url = "/signin/checkEmailIsVerified.html", data = {
						userId : userId
					};
					postAjaxRequest(url, data, function(result) {
						if(result.data){
							window.location.href = ctx + "/signin/classification.html?uKeyId="+$("#uKeyId").val()+"&r=" + Math.floor(Math.random() * 100);
						}else{
							alertMsg("请先完成邮箱验证");
						}
					});
			}else{
				window.location.href = ctx + "/signin/classification.html?uKeyId="+$("#uKeyId").val()+"&r=" + Math.floor(Math.random() * 100);
			}
			
		};
	
		
		_init();
		
	});

