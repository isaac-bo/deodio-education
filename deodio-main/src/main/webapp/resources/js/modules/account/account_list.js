	define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.scrolltofixed"], function($,cookie,doT) {
		
		var _init = function(){
			 $('.header').scrollToFixed();
		};
		
	
		
		_init();
		
		
		
		 onloadDataList = function(pageNo){
			var url = "/account/load_list.html",data={
				pageNo:pageNo
			}
			postAjaxRequest(url, data,function(result){
				console.log("result---",result);
				var userType = $("#userType").val();
				var accountRole = $.cookie('ACCOUNT');
				//加入小组数据为空时是
				if(result.data.length == 0 || (result.data.length == 1 && result.data[0].length == 0 && accountRole!='ACCOUNT')){
					if(userType == '2'){
						go2Page("/account/group_role.html");
					}else{
						alertMsg("您尚未加入任何账户",350,200,function(){
							go2Page('/logout.html');
//							window.location.href = ctx + "/login.html";
						});
											
					}
					return;
				}
				
				if(result.data.length > 0||accountRole=='ACCOUNT'){
					/*原来的代码
//					if(userType == '2'){
//						$('.left_b').animate({'margin-left':'0px','width':'360px'}, "normal",function(){
//							var template = doT.template($("#table_data_template").text());	
//							$('#data_panel').html(template({data:result.data}));
//							$('.right_b').show();
//						});
//					}else{
//						//非公司账号且只有一条数据，进入默认数据页中
//						if(result.data.length == 1 && result.data[0].length == 1 ){
//							go2Page("/account/group_role.html","accountId="+result.data[0][0].account_id);
//						}else{
//							var template = doT.template($("#table_data_template").text());	
//							$('#data_panel').html(template({data:result.data}));
//							$('.right_b').show().removeClass("pull-right");
//						}
//					}
					 	*/
					
					//账号只有一条数据，进入默认数据页中
					if(result.data.length == 1 && result.data[0].length == 1 && accountRole!='ACCOUNT'){
						go2Page("/account/group_role.html","accountId="+result.data[0][0].account_id);
					}else{
						if(userType == '2'){
							$('.left_b').animate({'margin-left':'0px','width':'360px'}, "normal",function(){
								var template = doT.template($("#table_data_template").text());	
								$('#data_panel').html(template({data:result.data}));
								$('.right_b').show();
							});
						}else{
							var template = doT.template($("#table_data_template").text());	
							$('#data_panel').html(template({data:result.data}));
							$('.right_b').show().removeClass("pull-right");
						}								
					}
				}
			});
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
//					 /account/group_role.html?accountId=
					 window.location.href = ctx + "/account/group_role.html?accountId="+id+"&r=" + Math.floor(Math.random() * 100);
					}else {
						 
						 if($.cookie("SELF_ACCOUNT_ID")==id){
							 alertMsg("您的账户正在审核中，审核通过后即可登录");
						 }else{
							 alertMsg("您加入的账户正在审核中，审核通过后即可登录");
						 }					
					}	
				});		
		 }
		
		 
		return {onloadDataList:onloadDataList};
		 
	});

