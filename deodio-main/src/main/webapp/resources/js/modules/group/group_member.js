define(["jquery","utils.cookie","jquery.dot","pagination","utils",
        "jquery.base","jquery.validate","bootstrap.select"], function($,cookie,doT,paging) {

	

	var init = function(){
		$('select').selectpicker();
		 
		 $("#tab_Value").val(0);
		 $("#status_type").val('');
		 $("#hid_mails").val('');
	}
	

	init();
	 loadDataList = function(pageNo){
		 var params = {
				   pageNo:pageNo,
				   groupId:$("#groupPkId").val(),
				   status:$("#status_select").val(),
				   roleId:$("#role_select").val(),
				   keywords:$("#keywords").val(),
				   tabValue:$("#tab_Value").val()
			},url="/group/member/list.html";
			
			postAjaxRequest(url, params, function(result){
				paging.pageTemplateDiv(result,"table_panle","member_table_data_template","member_data_page_Panel","loadDataList");
				customInput("remember");
			});
	};
	
	
	selectAllCheckBox = function(obj){
		var $checkbox = $("#table_panle tr:gt(0) input[type='checkbox']");
		if($(obj).prop("checked")){
			$checkbox.prop("checked",true);
			$checkbox.siblings().addClass('checked');
		}else{
			$checkbox.prop("checked",false);
			$checkbox.siblings().removeClass('checked');
		}
	}
	
	singleCheckBox = function(obj){
		var $tableTr = $("#table_panle tr:gt(0)"),total = $tableTr.find("input[type='checkbox']").size(),selectCount = $tableTr.find("input[type='checkbox']:checked").size()
	
		if(selectCount==total){
			$("#select_all").prop("checked",true).siblings().addClass('checked');
		}else{
			$("#select_all").prop("checked",false).siblings().removeClass('checked');
		}
		
	}
	
	searchGroupUser = function(){
		loadDataList(1);
	}
	
	activeUser = function(){
		var tab=$("#tab_Value").val();
		var status=$("#status_type").val();
		var numberTip=parseInt($("#number_tips_1").text());
		var $checkbox = $("#table_panle tr:gt(0) input[type='checkbox']:checked");
		
		var arrays = new Array();
		$checkbox.each(function(i,v){
			arrays.push($(this).val());
		})
		var url="/group/member/action.html",data={
			userData:arrays.join(),
			status:$("#status_type").val(),
			groupId:$("#groupPkId").val(),
			umails:$("#hid_mails").val(),
			isSendMail:$("#mail_box").prop("checked")?"1":0
		};
		postAjaxRequest(url, data,function(result){
			if(result.status == 1){
				if(!result.data){
					alertMsg("待确认的账户不允许激活");
				}else{
				    alertMsg("操作成功");
				}
				//重新加载数据
				loadDataList(1);
				if((tab=="1"||tab=="0" ) && status==0){
				$("#number_tips_1").text(numberTip-1);
				}
				if(tab=="0" && status==1){
					$("#number_tips_1").text(numberTip+1);
				}
			}else{
				alertMsg("操作失败");
			}
		});
	};
	validateOperatePrivilege=function(userData,status,type){
		var flag=true;
		url="/group/member/validateStatus.html",data={
				userData:userData,
				status:status,
				type:type
		}
		postAjaxRequest(url, data,function(result){
			if(result.status == 1){
				if(!result.data){
					if("delete"==type){
					  alertMsg("已激活的数据请暂停后再删除");
					  flag= false;
					}else if(status==1){
					  alertMsg("待确认的账户不允许激活");
					  flag= false;
					}else if(status==0){
					  alertMsg("待确认的账户不允许暂停");
					  flag= false;
					}
				}else{
					 flag= true;
				}
			}
		},false);
		return flag;
	}
	//设定状态值
	var setStatusType = function(type){
		$("#status_type").val(type);
	}
	
	popUpWin = function(type){
		var $checkbox = $("#table_panle tr:gt(0) input[type='checkbox']:checked");
		
		if($checkbox.size()==0){
			alertMsg("请选择数据");
			return;
		};
		var $parentTr = $checkbox.parent().parent().parent(),arrays = new Array(),mails = new Array();
		$parentTr.each(function(i,v){
			var params={};
			params.userid = $(this).find("td:eq(1) a").text();
			params.fullname = $(this).find("td:eq(2)").text()+$(this).find("td:eq(3)").text();
			params.usermail=$(this).find("td:eq(5)").text();
			mails.push(params.usermail);
			arrays.push(params);
		});
		var arraysCheck = new Array();
		$checkbox.each(function(i,v){
			arraysCheck.push($(this).val());
		})
		if(1==type && !validateOperatePrivilege(arraysCheck.join(),type,"")){
			return false;
		}
		if(0==type && !validateOperatePrivilege(arraysCheck.join(),type,"")){
			return false;
		}
		$("#hid_mails").val(mails.join());
		var template = doT.template($("#pop_template").text());	
		popUpWindow(template({"data":arrays}), "请确认，是否更改选中资源的状态");
		customInput("mailbox");
		setStatusType(type);
	}
	
	
	deleteUser = function(){
		
		var $checkbox = $("#table_panle tr:gt(0) input[type='checkbox']:checked");
		var numberTip=parseInt($("#number_tips_0").text());
		if($checkbox.size()==0){
			alertMsg("请选择数据");
			return;
		};
		var arrays = new Array();
		$checkbox.each(function(i,v){
			arrays.push($(this).val());
		})
		var s=validateOperatePrivilege(arrays.join(),"","delete");
		if(!validateOperatePrivilege(arrays.join(),"","delete")){
			return false;
		}
		confirmMsg("确定要删除选中记录吗？删除后无法恢复",function(){
		var url="/group/member/delete.html",data={
			userData:arrays.join()
		};
		postAjaxRequest(url, data,function(result){
			if(result.status == 1){
				if(!result.data){
				   alertMsg("已激活的数据请暂停后再删除!");
				   return false;
				}else{
				  $("#number_tips_0").text(numberTip-1);
				   alertMsg("操作成功");
				}
				//重新加载数据
				go2Page("/group/member.html","groupId="+$("#groupPkId").val());
			}else{
				alertMsg("操作失败");
			}
		});
		})
	};
	
	
	memberTab = function(tabVal){
		$("#tabBtn3").hide();
		$(".nav-tabs li").removeClass('active');
	
		$(".nav-tabs li:eq("+tabVal+")").addClass('active')
		if(tabVal==0){
			$('._tab1').show();
			$("#tabBtn1").show();
			$("#tabBtn2").show();
			$("#tabBtn4").show();
		}else if(tabVal==2){
			$("#tabBtn3").show();
			$('._tab1').hide();
			$("#tabBtn1").hide();
			$("#tabBtn2").hide();
			$("#tabBtn4").show();
		}else{
			$('._tab1').hide();
			$("#tabBtn1").hide();
			$("#tabBtn2").show();
			$("#tabBtn4").hide();
			
		}
		$("#tab_Value").val(tabVal);
		loadDataList(1);
	};
	reNewUser= function(){
		
		var $checkbox = $("#table_panle tr:gt(0) input[type='checkbox']:checked");
		
		if($checkbox.size()==0){
			alertMsg("请选择数据");
			return;
		};
		var $parentTr = $checkbox.parent().parent().parent(),arrays = new Array(),mails = new Array();
		$parentTr.each(function(i,v){
			var params={};
			params.userid = $(this).find("td:eq(1) a").text();
			params.fullname = $(this).find("td:eq(2)").text()+$(this).find("td:eq(3)").text();
			params.usermail=$(this).find("td:eq(5)").text();
			mails.push(params.usermail);
			arrays.push(params);
		});
		
		
		$("#hid_mails").val(mails.join());
		var template = doT.template($("#reinvite_template").text());	
		popUpWindow(template({"data":arrays}), "请确认，是否重新邀请选中的用户");
		customInput("mailbox");
	}
	reInvite = function(){
		var numberTip=parseInt($("#number_tips_2").text());
		var $checkbox = $("#table_panle tr:gt(0) input[type='checkbox']:checked");
		
		var arrays = new Array();
		$checkbox.each(function(i,v){
			arrays.push($(this).val());
		})
		
		var url="/group/member/reInvite.html",data={
			userData:arrays.join(),
			status:$("#status_type").val(),
			groupId:$("#groupPkId").val(),
			umails:$("#hid_mails").val(),
			isSendMail:$("#mail_box").prop("checked")?"1":0
		};
		postAjaxRequest(url, data,function(result){
			if(result.status == 1){
				alertMsg("操作成功");
				$("#number_tips_2").text(numberTip-result.data);
				loadDataList(1);
			}else{
				alertMsg("操作失败");
			}
		});
	};
	validateSendForm = function() {
		var formStatus = $("#activeFormStatus").val();
		if ($("#mail_box").is(":checked") == false) {
			if (!formStatus || formStatus == 0) {
				alertMsg("表单未激活!", 350, 200, function() {
					$("#mailboxLabel").removeClass("checkbox-2 checked");
					$("#mailboxLabel").addClass("checkbox-2")
					$("#mail_box").attr("checked", false);
				});
				return false;
			}
		}
	}
	
	
	return {loadDataList:loadDataList}
	
});

