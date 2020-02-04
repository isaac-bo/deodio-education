	define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.validate"], function($,cookie,doT) {
		
		var panelConfig = {
				"1" : {"panelId":"knowledge_capability_panel","capabilityType":"1"},
				"2" : {"panelId":"ability_capability_panel","capabilityType":"2"}
			};
		
		var _init = function(){
			customInput('capabilityLevelDistribution');
			customInput('capabilityCalScore');
			onLoadCapability();
			enablePopover();
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
		
		var enablePopover = function(){
			$('[data-toggle="popover"]').each(function () {
				var element = $(this);
					var formatEelementsName = '<ul><li style="font-size:12px;">' + $(this).parent().find("[name='tipHidden']").val() + '</li></ul>';
					element.popover({
						container:"body",
						trigger: 'manual',
						placement: 'top', //top, bottom, left or right
//						title: "提示",
						html: 'true',
						content: formatEelementsName
					}).on("mouseenter", function () {
		//				debugger;
							var _this = this;
							$(this).popover("show");
							$(this).siblings(".popover").on("mouseleave", function () {
								$(_this).popover('hide');
							});
					}).on("mouseleave", function () {
						var _this = this;
							setTimeout(function () {
								if (!$(".popover:hover").length) {
									$(_this).popover("hide")
								}
							}, 100);
					}).on("mouseout", function () {
						var _this = this;
							setTimeout(function () {
								if (!$(".popover:hover").length) {
									$(_this).popover("hide")
								}
							}, 100);
					});
				});
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
			var typeStr = type.toString();
			var size = $("#" + panelConfig[typeStr].panelId).find(".ti").size();
			if(size > 1){
				$(obj).parent().remove();
			}else{
				$(obj).parent().find("input").val("");
			}
		};
		
		
		_init();
	});

