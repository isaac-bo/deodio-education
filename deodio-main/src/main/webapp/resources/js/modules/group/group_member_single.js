define([ "jquery", "utils.cookie", "utils", "jquery.base", "bootstrap.select",
		"jquery.scrolltofixed", "jquery.validate" ], function($, cookie) {

	var _init = function() {
		$('select').selectpicker();

		customInput('remember');
	};

	_init();

	$('#userForm').myValidate({
		formCall : function() {
			submitSigin();
		},
		isAlert : false,
		formKey : false,
		errorCustom : {
			customFlag : true,
			regionText : false
		},
		errorStyle : {
			errorRegion : "error-custom",
			errorClass : "txt-wrong",
			rightClass : "txt-right"
		}
	});

	var submitSigin = function() {
		var groupId = $("#groupId").val();
		if (!groupId) {
			alertMsg("小组编号为空，请设置小组编号");
			return;
		}
		var url = "/group/member/submit_single.html", data = {
			userName : $.trim($("#userName").val()),
			firstName : $.trim($("#firstName").val()),
			lastName : $.trim($("#lastName").val()),
			userMail : $.trim($("#userMail").val()),
			mobile : $.trim($("#mobile").val()),
			groupRole : $.trim($("#role_select").val()),
			groupId : $.trim($("#groupId").val()),
			isSendMail : $("#isSendMail").prop("checked") ? 1 : 0,
			activeAccount : 2
		};
		postAjaxRequest(url, data, function(result) {
			alertMsg("操作成功");
		})
	}
   
	validateUserNameExists = function() {
		if(!$.trim($("#userName").val())){
			return false;
		}else{
		var flag = false;	
		var url = "/signin/validateUserNameExists.html", data = {
			userName : $.trim($("#userName").val()),
			registerType : 3
		};
		postAjaxRequest(url, data, function(result) {
			if (result.data) {
				var url = "/user/getUserInfoByUserName.html", data = {
					userName : $.trim($("#userName").val()),
					registerType : 3
				};
				postAjaxRequest(url, data, function(result) {
					var data = result.data;
					$("#userName").val(data.nickName);
					$("#firstName").val(data.firstName);
					$("#lastName").val(data.lastName);
					$("#userMail").val(data.userMail);
					$("#mobile").val(data.mobilePhone);
				});
			}else{
				$("#userMail").val($("#lastUserEmail").val());
			}
		});
		}

	}
	validateEmailExists = function() {
		var url = "/signin/validateUserNameExists.html", data = {
			userName : $.trim($("#userMail").val()),
			registerType : 2
		};
		postAjaxRequest(url, data, function(result) {
			if (result.data) {
				var url = "/user/getUserInfoByUserName.html", data = {
					userName : $.trim($("#userMail").val()),
					registerType : 2
				};
				postAjaxRequest(url, data, function(result) {
					var data = result.data;
					$("#userName").val(data.nickName);
					$("#firstName").val(data.firstName);
					$("#lastName").val(data.lastName);
					$("#mobile").val(data.mobilePhone);
				},false);
				if($("#userName").val().length!=0){
				  $("#userName").attr("readOnly", "readOnly");
				}else{
				  $("#lastUserEmail").val($("#userMail").val());
				}
			} else {
				$("#lastUserEmail").val($("#userMail").val());
				$("#userName").removeAttr("readOnly");
			}
		});

	}
	validateGroupRoleExist=function(){
		var flag = false;
		var url = "/group/validateGroupRoleExist.html", data = {
				userName : $.trim($("#userMail").val()),
				groupId : $.trim($("#groupId").val())
			};
			postAjaxRequest(url, data, function(result) {
				flag=result.data;
				if(!flag){
					validateEmailExists();
				}
			},false);
			return flag;
	}
	validateMobileExists = function() {
		var flag = false;
		var url = "/signin/validateUserNameExists.html", data = {
			userName : $.trim($("mobile").val())
		};
		postAjaxRequest(url, data, function(result) {
			flag = result.data;
		}, false);
		return flag;
	}
	validateSendForm = function() {
		var formStatus = $("#activeFormStatus").val();
		if ($("#isSendMail").is(":checked") == false) {
			if (!formStatus || formStatus == 0) {
				alertMsg("表单未激活!", 350, 200, function() {
					$("#sendSearchLabel").removeClass("checkbox-2 checked");
					$("#sendSearchLabel").addClass("checkbox-2")
					$("#isSendMail").attr("checked", false);
				});
				return false;
			}
		}
	}
	changeNickStatus=function(){
		$("#userName").removeAttr("readOnly");
	}
	cancel = function() {
		$(':input', '#userForm').val('');
	}

});
