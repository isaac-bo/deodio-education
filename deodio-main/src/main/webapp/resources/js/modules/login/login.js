	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate"], function($,cookie) {
		
		var _init = function(){
			
			$('#loginForm').myValidate({
				formCall:function(){ onLogin();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:true,regionText:true},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item"}
			});
			
			customInput('remember-password');
			checkIsRemenberPwd();
		};
		checkIsRemenberPwd=function(){
			if ($.cookie("rmbuser") == "true") {
				$("#userName").val($.cookie("username")); 
				$("#passWord").val(uncompile($.cookie("password"))); 
				$("#remember-password").prop("checked",true);
				$("#remember-password-label").removeClass('checkbox-1').addClass('checkbox-1 checked');
			}else{
				$("#userName").val(''); 
				$("#passWord").val(''); 
				$("#remember-password").prop("checked",false);
				$("#remember-password-label").removeClass('checkbox-1 checked').addClass('checkbox-1');
			}
		}
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
		
		onLogin = function(){
			
			var userName=$.trim($("#userName").val());
			var passWord=$.trim($("#passWord").val());
			var isRemeber=$("#remember-password").prop("checked");
			var url= "/login/submit.html", data={
					userName :userName ,
					passWord :passWord,
					isRemeber: isRemeber					
			};
			postAjaxRequest(url, data, function(result){
				console.log('result---',result);
				if(result.data){
//					console.log("ACCSTA",$.cookie("ACCSTA"))
					 if(isRemeber){ 
						$.cookie("rmbuser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie 
						$.cookie("username", userName, { expires: 7 }); // 存储一个带7天期限的 cookie 
						$.cookie("password", compile(passWord), { expires: 7 }); // 存储一个带7天期限的 cookie 
						$.cookie(userName, compile(passWord), { expires: 7 }); // 存储一个带7天期限的 cookie 						
					 }else{
						$.cookie("rmbuser", "false", { expires: -1 }); 
						$.cookie("username", '', { expires: -1 }); 
						$.cookie("password", '', { expires: -1 }); 	
					   }
						checkClassfication();					
				}else{
					$("#userName").val("用户名/密码不存在").parents(".error-div").addClass("wrong-item");
					$('#userName').css('color','#cccccc');
				}
			});
		};	
		//对密码加密
		function compile(code)  
		{    
		   var c=String.fromCharCode(code.charCodeAt(0)+code.length);  
		   for(var i=1;i<code.length;i++){  
		   c+=String.fromCharCode(code.charCodeAt(i)+code.charCodeAt(i-1));  
		   }  
		   return(escape(c));  
		}  
		//对密码解密
		function uncompile(code)  
		{  
		   code=unescape(code); 
		   var c=String.fromCharCode(code.charCodeAt(0)-code.length);  
		   for(var i=1;i<code.length;i++){  
		   c+=String.fromCharCode(code.charCodeAt(i)-c.charCodeAt(i-1));  
		   }  
		   return c;  
		}  
		//检验课程类别
		function checkClassfication(){
			var checkClassficationUrl="/login/checkClassfication.html",data={
					userName : $.trim($("#userName").val())
			};
			postAjaxRequest(checkClassficationUrl, data, function(result){
				var uKeyId=result.data.userid;
				console.log('checkresult----',result.data);		
				debugger;
				if(result.data.valid){//账户有效
					if(result.data.emailVerifyFlag){//邮箱验证
						if(result.data.flag){//选择课程类别						
							if(result.data.accountMap.mapList.length == 1){//账户为account，且未加入其他有效account
								var account = result.data.accountMap.mapList[0];
								toAccountRolePage(account.account_id);
								cookie.setCookie('CAID',account.account_id,600);
//								loadGroupList();
							}else{
								window.location.href = ctx + "/account/list.html?r=" + Math.floor(Math.random() * 100);
							}	
						}else{
							window.location.href = ctx + "/signin/classification.html?uKeyId="+uKeyId+"&r=" + Math.floor(Math.random() * 100);
						}
					}else{
						alert('请到邮箱中点击确认链接完成邮箱验证');
						cookie.setCookie('CAID','',600);
						//alertMsg('请到邮箱中点击确认链接完成邮箱验证');				
					}
				}else{	
					if(result.data.accountMap.mapList.length > 1){//存在加入其他有效账户
//						var account = result.data.accountMap.mapList[0];
//						cookie.setCookie('CAID',account.account_id,600);
//						loadGroupList();
						window.location.href = ctx + "/account/list.html?r=" + Math.floor(Math.random() * 100);
					}else{//账户未审核，且未加入其他有效账户
						if(result.data.completeSelfInfo){
							if(result.data.accountType=="user"){
								alert("您加入的账户正在审核中，审核通过后即可登录");
								cookie.setCookie('CAID','',600);
							}else {
								alert("您的账户正在审核中，审核通过后即可登录");
								cookie.setCookie('CAID','',600);
							}
						}else{
							alert("您尚未加入任何account");
							cookie.setCookie('CAID','',600);
						}						
					}	
				}
			});
		};
//		onLoadAccountList = function(userId){
//			//alert(username + " " + password + '--11---' + $.i18n.prop('fe.error.files.upload.type'));
//			cookie.setCookie('CUID',userId,60);
//			var url= "/account/load_login_list.html?r=" + Math.floor(Math.random() * 100),data={userId:userId},flag=false;
//			postAjaxRequest(url, data, function(result){
//				if(!result.data.completeSelfInfo){
////					open2Page('/account/layer.html');
////					alert('===1')
//					go2Page('/account/layer.html');
//				}else{
//					if(result.data.mapList.length == 1){
//						var account = result.data.mapList[0];
//						cookie.setCookie('CAID',account.account_id,60);
//						loadGroupList();
//					}else{
////						alert('===2')
//						window.location.href = ctx + "/account/list.html?r=" + Math.floor(Math.random() * 100);
//					}
//				}			
//			},false);
//			return flag;
//		};		
	     loadGroupList=function(){
	    	var url= "/group/load_list.html?r=" + Math.floor(Math.random() * 100),data={pageNo:1};
			postAjaxRequest(url, data, function(result){
				console.log("loadGroupList--result",result);
				debugger;
				if(result.data.dataList.length <= 0){	
//					alert('===3')
					window.location.href = ctx + "/account/profile.html?r=" + Math.floor(Math.random() * 100);
				}else if(result.data.dataList.length == 1){	
//					alert('===4')
					window.location.href = ctx + "/group/profile.html?groupId="+result.data.dataList[0].id+"&r=" + Math.floor(Math.random() * 100);
				}else{		
//					alert('===5')
					window.location.href = ctx + "/account/group_role.html?accountId="+ cookie.getCookie('CAID') + "&r=" + Math.floor(Math.random() * 100);
				}
			},false);
	    }
	     //判断account状态
	     toAccountRolePage=function(id){
			 var url = "/account/load_account_status.html",data={
					 accountId:id
				}
			 postAjaxRequest(url, data,function(result){
				 console.log('toAccountRolePage---result   ',result);
				 console.log("cookieAccountId",$.cookie("SELF_ACCOUNT_ID"));
				 console.log("accountId",id);
				 debugger;
				 if(result.data==1){
					 cookie.setCookie('CAID',id,600);
//					 /account/group_role.html?accountId=
					 window.location.href = ctx + "/account/group_role.html?accountId="+id+"&r=" + Math.floor(Math.random() * 100);
					}else {
						 
						 if($.cookie("SELF_ACCOUNT_ID")==id){
							 alert("您的账户正在审核中，审核通过后即可登录");
//							 alertMsg("您的账户正在审核中，审核通过后即可登录");
						 }else{
							 alert("您加入的账户正在审核中，审核通过后即可登录");
//							 alertMsg("您加入的账户正在审核中，审核通过后即可登录");
						 }					
					}	
				});		
		 }
		 mouseOutUserName=function(){
			 var pwd=$("#passWord");
			 var pwdtype=pwd.attr("type");
			 if(pwdtype=='text'){
				 pwd.attr('type','password');
			 }
			 var isRemeber=$("#remember-password").prop("checked");
			 if(isRemeber){
				 var userName=$("#userName").val().trim();
				 var unpass=$.cookie(userName);
				 
				
					if(unpass!=null){
						$("#passWord").val(uncompile(unpass));
					}else{
						$("#passWord").val('');
						$("#passWord").removeAttr('lable-error');
					}
			 }

		}
	changeInputType=function(obj){
		var type=$(obj).attr('type');
		if(type=='text'){
			$(obj).attr('type','password')
		}
	}
		_init();
		
	});

