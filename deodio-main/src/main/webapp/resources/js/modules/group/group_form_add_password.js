define(["jquery","utils.cookie","jquery.dot","utils",
        "jquery.base","jquery.scrolltofixed","jquery.ui","jquery.validate"], function($,cookie,doT) {
	
//	$("#groupCopyModal").modal("show");
//	//关闭时触发事件跳转到系统中角色对应的小组页面
//	$('#groupCopyModal').on('hidden.bs.modal', function () {
//		setRolePage();
//	});
//	cancelSetPwd=function (){
//	    setRolePage();
//	}
	setRolePage=function(){
		var ur=$("#ur").val();
		if(ur==$("#roleLeaderId").val()){
		go2Page("/group/profile.html","groupId="+$("#gp").val()+"&groupRoleId="+ur);
		}
		if(ur==$("#roleCreatorId").val()){
		go2Page("/group/features.html","groupId="+$("#gp").val()+"&groupRoleId="+ur);
		}
		if(ur==$("#roleViewerId").val()){
		 go2Page("/group/intro.html","groupId="+$("#gp").val()+"&groupRoleId="+ur);
		}
	}
	$('#setPwdForm').myValidate({
		formCall:function(){ setPwd();},
		isAlert:false,
		formKey:false,
		errorCustom:{customFlag:true,regionText:false},
		errorStyle:{errorRegion:"error-custom",errorClass:"txt-wrong",rightClass:"txt-right"}
	});
	
	var setPwd=function(){
		var password=$("#password").val();
		var conFirmPassword=$("#conFirmPassword").val();
		debugger;
		if(conFirmPassword.length<4 || password.length<4){
			alertMsg("密码应为6-16位!");
			return false;
		}
		if(conFirmPassword!=password){
			alertMsg("两次输入的密码不一致,请确认!");
			return false;
		}
		var url="/group/setPwd/submit.html",data = {
				userId:$("#uKeyId").val(),
				nickName:$("#nickName").val(),
				password:$("#password").val(),
				gp:$("#gp").val()
			};
		postAjaxRequest(url, data, function(result){
			//alertMsg("修改成功",function(){setRolePage()});
			setRolePage();
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
});

