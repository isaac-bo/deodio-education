define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.scrolltofixed"], function($,cookie,doT) {
	
	saveGroupPermission = function(){
		var groupId = $("#groupId").val();
		if(!groupId){
			alertMsg("小组编号为空，请设置小组编号");
			return;
		}
		$("#permissionForm").submit();
	}
	
	setGroupPermission = function(){
		
		if($('._groupRole').size()>0){
			$('._groupRole').each(function(i,v){
				var $check_box = $("#"+$(this).val());
				$check_box.prop("checked",true);
				$check_box.siblings().addClass("checked");
			});
		}
		if($("#groupModelfunc").size()>0){
			var array = $("#groupModelfunc").val().split(",");
			for(var i=0,z=array.length;i<z;i++){
				var $check_box = $("#"+array[i]);
				$check_box.prop("checked",true);
				$check_box.siblings().addClass("checked");
			}
		}
	};
	
	var _init = function(){
//		 customInput('permission');
//		 customInput('permissionModel');
//		 setGroupPermission();
		loadPermissionDataList();
		loadModelFuncDataList();
	}
	
	var loadPermissionDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {
		
		tablePanel = "funcList1";
		tableDataTemplate = "permission_data_template";
		var checkboxName = "permission";
		var pageSize = 9;
		var rowSize = 3;
		
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:pageSize,
			groupId:$("#groupId").val(),
			groupType:'1'
		}, url = "/group/load_permission_role.html";
		postAjaxRequest(url, params, function(result) {
//			debugger;
			var template = doT.template($("#"+tableDataTemplate).text());
			$('#'+tablePanel).html(template({data:result.data,pageSize:pageSize,rowSize:rowSize}));
			var idsList = result.data.ids;
			if(idsList){
				$("#ids").val(idsList.join());
			}
			customInput(checkboxName);
		});
	}
		
	var loadModelFuncDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {
		
		tablePanel = "modelFuncConstainer";
		tableDataTemplate = "model_function_data_template";
		var checkboxName = "permissionModel";
		var rowSize = 5;
		
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:20,
			groupId:$("#groupId").val(),
			groupType:'1'
		}, url = "/group/load_model_function.html";
		postAjaxRequest(url, params, function(result) {
			var template = doT.template($("#"+tableDataTemplate).text());
			$('#'+tablePanel).html(template({data:result.data,rowSize:rowSize}));
			
			customInput(checkboxName);
		});
	}

	_init();
	
});

