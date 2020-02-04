define(
		[ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar" ,"bootstrap.select"],
		function($, cookie, doT, paging) { 
			
				//modal打开事件
			$('#groupToolModal').on('shown.bs.modal', function () {	
				initloadCapabilityToolItemModalContent();
				
			});
			$('#groupCapabilityModal').on('shown.bs.modal', function () {
				    initloadCapabilityItemModalContent();
			});
			
			$('#groupToolModal').on('hidden.bs.modal', function () {	
				$('body').removeClass('modal-open').css('overflow-y','auto');
				
			});
			$('#groupCapabilityModal').on('hidden.bs.modal', function () {
				$('body').removeClass('modal-open').css('overflow-y','auto');
			});
			
			var initloadCapabilityToolItemModalContent = function(){
				 //初始化页面
				var data = {};
				data.modal_tittle = "添加知识工具";
				var temp =  doT.template($("#capability_item_tool_data_template").text());
				$("#groupToolModalContent").html(temp(data));
				customInput('allCapabilityToolItem');
				checkOrUncheckAll('allCapabilityToolItem','capabilityToolItem');
				loadCapabilityToolItemDataList();
			}
			var initloadCapabilityItemModalContent = function(){
				 //初始化页面
				var data = {};
				data.modal_tittle = "添加知识能力";
				var temp =  doT.template($("#capability_item_data_template").text());
				$("#groupCapabilityModalContent").html(temp(data));
				customInput('allCapabilityItem');
				checkOrUncheckAll('allCapabilityItem','capabilityItem');
				loadCapabilityItemDataList();
			}
			//查询知识能力
			searchCapabilityItemToSelectList = function(){
				loadCapabilityItemDataList();
			}
			//查询知识工具
			searchCapabilityItemToolToSelectList = function(){
				loadCapabilityToolItemDataList();
			}
			
		  loadCapabilityToolItemDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					pageSize:6,
					keywords : $("#groupToolsKeywords").val(),
					capabilityType : 1,
					groupId : $("#groupId").val()
				}, url="/group/capability_list.html";
				postAjaxRequest(url, params, function(result) {
					if(result.status == 1){
						paging.pageTemplateDiv(result, "capabilityToolBody", "capability_item_tool_data_content_template", "group_capability_tool_to_select_data_page_Panel", "loadCapabilityToolItemDataList");
						customInput('capabilityToolItem');
					
					}
				});
			};
		 loadCapabilityItemDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					pageSize:6,
					keywords : $("#groupCapabilityKeywords").val(),
					capabilityType : 2,
					groupId : $("#groupId").val()
				}, url="/group/capability_list.html";
				postAjaxRequest(url, params, function(result) {
					if(result.status == 1){
						paging.pageTemplateDiv(result, "capabilityBody", "capability_item_data_content_template", "group_capability_to_select_data_page_Panel", "loadCapabilityItemDataList");
						customInput('capabilityItem');
					}
				});
			};
	
});
