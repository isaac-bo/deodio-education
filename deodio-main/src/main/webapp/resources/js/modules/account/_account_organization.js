define(["jquery","utils.cookie","utils","jquery.base","jquery.validate",
        "bootstrap.select","jquery.scrolltofixed","fileupload.common","bootstrap.datepicker"], function($,cookie) {
	
	var userIconDefaultPic = ctx + '/resources/img/account/homepaage_movie_travel.jpg';
	var userBusinessLicenseDefaultSnapShot = ctx + '/resources/img/account/user-card-1.jpg';
	var userIdCardDefaultSnapShot = ctx + '/resources/img/account/user-card-1.jpg';
	
	var _init = function(){
		$('select').selectpicker();
		$('.form-group-description.error-custom').css('padding-left','50px');
		customInput('userGender');
		//日期控件
		$(".form_datetime").datetimepicker({
	        format: "yyyy-mm-dd",
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
		var uploadCompanyShotUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+cookie.getCookie('CUID')+'&attachRelType='+"8";
		uploadFileJqProcess("uploadCompanyShot",uploadCompanyShotUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"8",isModel:true},
				1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
			
			var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
			var srcimgTemp = data.result.data.generateName;
			$("#businessLicenceImg").attr("src",srcimg);
			$("#businessLicenceImgName").val(srcimgTemp);
			$("#businessLicenceImgId").val(data.result.data.id);
			$("#businessLicenceImg").removeAttr("style");
			
			
		},function(e,data){
			
		});
	
		
		var uploadIdCardUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+cookie.getCookie('CUID')+'&attachRelType='+"7";
		uploadFileJqProcess("uploadIdCardSnapShot",uploadIdCardUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"7",isModel:true},
				1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
			
			var srcAttachTemp=data.result.data.attachUrl;
			var srcimgTemp = data.result.data.generateName;
			var srcimg = imgStatic+srcAttachTemp+srcimgTemp;
		
			$("#userIdCardImg").attr("src",srcimg);
			$("#userIdCardImgName").val(srcimgTemp);
			$("#userIdCardImgId").val(data.result.data.id);
			$("#userIdCardImg").removeAttr("style");
			
		},function(e,data){
			
		});
 	 
		 $(".form_datetime").datetimepicker({
		        format: "yyyy-mm-dd",
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
		
	};

	_init();
	$('#alertModel').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	$('#accountOrganizationForm').myValidate({
		formCall:function(){ updateGroupInfo();},
		isAlert:false,
		formKey:false,
		errorCustom:{customFlag:true,regionText:true},
		errorStyle:{errorRegion:"error-custom",rightClass:"right"}
	});
	
    var updateGroupInfo = function(){
    	if(validateDate()){
    		$("#userSetGender").val(getRadioVal("userGender"));
    		var data= {};
    		$("#accountOrganizationForm").find("[name]").each(function(){
    			var name = $(this).attr("name");
    			var value = $(this).val().trim();
    			if(value){
    				data[name] = value;
    			}
    		});
    		
    		if($("#businessLicenceImgId").val()==""){
    			$("#businessLicenceImg").attr("style","border:1px solid red");
    			//alertMsg("请上传营业执照副本扫描件");
    			return;
    		};
    		
    		if($("#userIdCardImgId").val()==""){
    			$("#userIdCardImg").attr("style","border:1px solid red");
    			//alertMsg("请上传运营者手持身份证件照片");
    			return;
    		};   	
    		var orginialCompanyBusinessLicenseCode=$("#orginialCompanyBusinessLicenseCode").val();
    		var companyBusinessLicenseCode=$("#companyBusinessLicenseCode").val();
    		var orginialBusinessLicenceImg=$("#orginialBusinessLicenceImg").val();
    		var businessLicenceImg=$("#businessLicenceImgName").val();	
    		var orginialCompanyOrganizationCode=$("#orginialCompanyOrganizationCode").val();
    		var companyOrganizationCode=$("#companyOrganizationCode").val();	
    		var orginialCompanyOperationName=$("#orginialCompanyOperationName").val();
    		var nickName=$("#nickName").val();
    		var orginialUserIdCardCode=$("#orginialUserIdCardCode").val();
    		var userIdCardCode=$("#userIdCardCode").val();	
    		var orginialUserIdCardImg=$("#orginialUserIdCardImg").val();
    		var userIdCardImg=$("#userIdCardImgName").val();
    		var companyOperationPosition=$("#companyOperationPosition").val();
    		var orginialCompanyOperationPosition=$("#orginialCompanyOperationPosition").val();
    		var userMobile=$("#userMobile").val();
    		var orginialUserMobilePhone=$("#orginialUserMobilePhone").val();
    		var dataString=JSON.stringify(data);
    		var message="";
    		var flag=false;
    		if(((orginialCompanyBusinessLicenseCode!='' && orginialCompanyBusinessLicenseCode!=companyBusinessLicenseCode)||
    			(orginialBusinessLicenceImg!='' && orginialBusinessLicenceImg!=businessLicenceImg)||
    			(orginialCompanyOrganizationCode!='' && orginialCompanyOrganizationCode!=companyOrganizationCode))&&
    			(( orginialCompanyOperationName!='' && orginialCompanyOperationName!=nickName)||
    			(orginialUserIdCardCode!='' && orginialUserIdCardCode!=userIdCardCode)||
    			(orginialUserIdCardImg!='' && orginialUserIdCardImg!=userIdCardImg)||
    			(orginialCompanyOperationPosition!='' && orginialCompanyOperationPosition!=companyOperationPosition)||
    			(orginialUserMobilePhone!='' && orginialUserMobilePhone!=userMobile))){
    			flag=true;
    			message="您更改了运营者信息和企业信息中的必填项，该账户需要重新审核，审核期间所有人不能登录该账户，确定要修改吗？"
    		}else if(((orginialCompanyBusinessLicenseCode!='' && orginialCompanyBusinessLicenseCode!=companyBusinessLicenseCode)||
    				(orginialBusinessLicenceImg!='' && orginialBusinessLicenceImg!=businessLicenceImg)||
    				orginialCompanyOrganizationCode!=companyOrganizationCode)&&
    				(( orginialCompanyOperationName!='' && orginialCompanyOperationName==nickName)&&
    				(orginialUserIdCardCode!='' && orginialUserIdCardCode==userIdCardCode)&&
    				(orginialUserIdCardImg!='' && orginialUserIdCardImg==userIdCardImg)&&
    				(orginialCompanyOperationPosition!='' && orginialCompanyOperationPosition==companyOperationPosition)&&
    				(orginialUserMobilePhone!='' && orginialUserMobilePhone==userMobile))){
    			flag=true;
    			message="您更改了企业信息中的必填项，该账户需要重新审核，审核期间所有人不能登录该账户，确定要修改吗？"
    		}else if(((orginialCompanyBusinessLicenseCode!='' && orginialCompanyBusinessLicenseCode==companyBusinessLicenseCode)&&
    				(orginialBusinessLicenceImg!='' && orginialBusinessLicenceImg==businessLicenceImg)&&
    				orginialCompanyOrganizationCode==companyOrganizationCode)&&
    				(( orginialCompanyOperationName!='' && orginialCompanyOperationName!=nickName)||
    				(orginialUserIdCardCode!='' && orginialUserIdCardCode!=userIdCardCode)||
    				(orginialUserIdCardImg!='' && orginialUserIdCardImg!=userIdCardImg)||
    				(orginialCompanyOperationPosition!='' && orginialCompanyOperationPosition!=companyOperationPosition)||
    				(orginialUserMobilePhone!='' && orginialUserMobilePhone!=userMobile))){
    			flag=true;
    			message="您更改了运营者信息中的必填项，该账户需要重新审核，审核期间所有人不能登录该账户，确定要修改吗？"
    		}
    		if(flag){
    		     confirmMsg(message, function() {
    			 var url="/account/update_organization_profile.html",data = {
        				uKeyId:$("#uKeyId").val(),
        				extModelJson:dataString
        			};
        		 postAjaxRequest(url, data, function(result){
        			alertMsg("修改成功");
        			go2Page("/logout.html");
        		});
    		});
    		}else{
    		    var url="/account/update_organization_profile.html",data = {
    				uKeyId:$("#uKeyId").val(),
    				extModelJson:JSON.stringify(data)
    			};
    		    postAjaxRequest(url, data, function(result){
    			$("#img_certifed").attr("src",ctx + '/resources/img/account/certifed.png');
    			alertMsg("修改成功");
    			go2Page('/account/layer.html');
    			//loadSubdoFormData();
    			//toNextLink();
    		   });
    		}
    	}
	}
    
    selectProince = function(sourceId,targetId){
		getProvince($("#"+sourceId).val(),targetId);
		
	}
	
	selectCity = function(sourceId,targetId){
		getCity($("#"+sourceId).val(),targetId);
	}
	
	cancleUserIdCardImg = function(){
		$("#userIdCardImg").attr("src",userIdCardDefaultSnapShot);
		$("#userIdCardImgId").val('');
		$("#userIdCardImgName").val('');
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

	
	var checkCompanyEmail = function(){
		if($("#companyEmail").val()==""){
			alertMsg("邮箱不能为空");
		}else{
			var url="/account/checkCompanyEmail.html",data = {
					uKeyId:$("#uKeyId").val(),
					mail:$("#companyEmail").val()
				};
			postAjaxRequest(url, data, function(result){
				alertMsg("修改成功");
				toNextLink();
			});
		}
		
	}
   getValidateCode = function(div){
		
		countDownTime(div,"秒后 重新获取","重新获取验证码");
		
		var url="/commons/getSmsCode.html",data={
				mobileNumber:$.trim($("#userMobile").val())
		};
		postAjaxRequest(url, data,function(result){
			debugger;
			$("#val_code").val(result.data.code);
		})
	};
	validateDomainExists = function(){
		var flag=false;
		var url="/account/validate/domain_exist.html",data={
				subdomain:$.trim($("#accountDomain").val())
		};
		postAjaxRequest(url, data,function(result){
			flag=result.data;
		},false);
		return flag;
	};
	var validateDate = function(){
		var flag = true;
		if($("#companyBusinessLicenseFoundedDate").val()!="" && $("#companyBusinessLicenseBusinessTerm").val()!=""){
			flag = compareToDate($("#companyBusinessLicenseBusinessTerm").val(),$("#companyBusinessLicenseFoundedDate").val());
		}
		if(!flag){
			alertMsg("营业期限不能小于成立日期");
		}
		return flag;
	}
	 checkMobileExsist = function(){
		var flag=false;
		var userVerifiedImg = ctx + '/resources/img/account/certifed.png';
		var userUnverifiedImg = ctx+ '/resources/img/account/uncertifed.png';
		var orginialMobilePhone=$.trim($("#orginialMobilePhone").val());
		var mobilePhone=$.trim($("#userMobile").val());
		if(orginialMobilePhone!=mobilePhone){
		var url="/signin/checkDetailRegisterMobile.html",data={
				userMobile:mobilePhone,
				userId:$("#userId").val()
		};
		postAjaxRequest(url, data,function(result){
			flag=result.data.isExist;
			if(flag){//手机号已存在
				if(result.data.isSelfCheck){//手机号已验证了
					$("#phoneVerifySpan").html("已认证")
					$("#personal_val_code").hide();
					$("#img_certifed").attr("src",userVerifiedImg);
					$("#btn_smscode").attr('disabled',true);//发送验证码按钮禁用
					$("#accountOrganizationForm [name='val_code']").attr("disabled",true);//验证码文本框禁用	
					$("#val_code").removeAttr("check-type");
					$("#val_code").removeAttr("data-callback");
					$("#val_code").removeAttr("call-message");
					$("#val_code").removeAttr("required-message");
					$("#isCheckTel").val("1");	
				}else{
					$("#phoneVerifySpan").html("未认证")
					$("#personal_val_code").show();
					$("#img_certifed").attr("src",userUnverifiedImg);
					$("#btn_smscode").removeAttr("disabled");////发送验证码按钮可点击
					$("#val_code").removeAttr("disabled");
					$("#accountOrganizationForm [name='val_code']").removeAttr("disabled");////验证码文本框可用
					$("#val_code").attr('check-type','required');
					$("#val_code").attr('data-callback',"valOrganizationCode()");
					$("#val_code").attr('call-message',"验证码错误");
					$("#val_code").attr('required-message',"请输入验证码");
					$("#isCheckTel").val("0");
				}	
			}else{
				$("#phoneVerifySpan").html("未认证")
				$("#personal_val_code").show();
				$("#img_certifed").attr("src",userUnverifiedImg);
				$("#btn_smscode").removeAttr("disabled");////发送验证码按钮可点击
				$("#accountOrganizationForm [name='val_code']").removeAttr("disabled");////验证码文本框可用
				$("#val_code").removeAttr("disabled");
				$("#val_code").attr('check-type','required');
				$("#val_code").attr('data-callback',"valOrganizationCode()");
				$("#val_code").attr('call-message',"验证码错误");
				$("#val_code").attr('required-message',"请输入验证码");
				$("#isCheckTel").val("0");
			}
		},false);
		}
		return flag;
	}
	valOrganizationCode = function(){
		if($.trim($("#val_code").val())==$.cookie("MV_CODE")){
			return false;
		}else{
			return true;
		}
	};
	cancleUserIdCardImg=function(){
		$("#userIdCardImg").attr("src",ctx+"/resources/img/account/user-card-1.jpg");
		$("#userIdCardImgId").val("");
	}
	cancleCompanyShotImg=function(){
		$("#businessLicenceImg").attr("src",ctx+"/resources/img/account/user-card-1.jpg");
		$("#businessLicenceImgId").val("");
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
	changeOperationInfo=function(){
		$("#nickName").removeAttr("disabled");
		$("#userIdCardCode").removeAttr("disabled");
		$("#companyOperationPosition").removeAttr("disabled");
		$("#userMobile").removeAttr("disabled");
		$("#uploadUserImageDiv").css("display","block");
		$("#deleteUserImgDiv").css("display","block");
	}
	changeCompanyInfo=function(){
		$("#companyBusinessLicenseCode").removeAttr("disabled");
		//$("#companyBusinessLicenseCode").attr("srcvalue","");
		$("#companyOrganizationCode").removeAttr("disabled");
		$("#companyImageDiv").css("display","block");
		$("#deletecompanyImgDiv").css("display","block");
	}
});
