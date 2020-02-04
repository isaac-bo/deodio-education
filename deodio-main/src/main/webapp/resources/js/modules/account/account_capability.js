	define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.validate"], function($,cookie,doT) {
		
		var _init = function(){
			customInput('capabilityLevelDistribution');
			customInput('capabilityCalScore');
			onLoadCapability();
		};
		
		var _calCapabilityItem = function(panelId){
			var item = '';
			$('#'+panelId).find('div').each(function(){
				if($(this).hasClass('ti')){
					item += $(this).find('div input').attr('id') + "_@_" + $(this).find('div input').val() + "_#_";
				}
			});
			
			return item;
		};
		
		onSubmit = function(){

			var url="/account/update_capability.html",data={
					capabilityPrefix:$.trim($("#prefixCapability").val()),
					capabilityLevel:$.trim($("#numCapability").val()),
					capabilityLevelDistribution:$("input[name='capabilityLevelDistribution']:checked").val(),
					capabilityCalScore:$("input[name='capabilityCalScore']:checked").val(),
					capabilityCustomizeScore:$.trim($("#capabilityCustomizeScore").val()),
					knowledgeItem:_calCapabilityItem('knowledge_capability_panel'),
					capabilityItem:_calCapabilityItem('ability_capability_panel')
			};
			postAjaxRequest(url, data, function(result){
				alertMsg("修改成功");
			});
		};
		
		onLoadCapability = function(){
			var url="/account/load_capability.html",data={
			};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#knowledge_data_template").text());	
				$('#knowledge_capability_panel').html(template({data:result.data.knowledge}));
				
				var template2 = doT.template($("#ability_data_template").text());	
				$('#ability_capability_panel').html(template2({data:result.data.capability}));
			});
		};
		
		onAddCapabilityItem = function(obj,type){
			var template = doT.template($("#capability_item_data_template").text());	
			$(obj).parent().parent().append(template(type));
		};
		
		onRemoveCapabilityItem = function(obj,type){
			$(obj).parent().remove();
		};
		
		_init();
	});

