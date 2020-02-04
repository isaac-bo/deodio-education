	define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.validate",
	        "bootstrap.select","jquery.scrolltofixed","upload.common","bootstrap.datepicker"], function($,cookie,doT) {
		
		var panelConfig = {
			"1" : {"panelId":"knowledge_capability_panel","capabilityType":"1"},
			"2" : {"panelId":"ability_capability_panel","capabilityType":"2"}
		};
		
		
		$('#capabilityInfoForm').myValidate({
			formCall:function(){ updateCapabilityInfo();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:false},
			errorStyle:{errorRegion:"error-custom",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		updateCapabilityInfo = function(){
			
			var url="/signin/enterprise/capability.html",data = {
					uKeyId: $("#capabilityInfoForm [name='userId']").val(),
					knowledgeItem:_calCapabilityItem('knowledge_capability_panel'),
					capabilityItem:_calCapabilityItem('ability_capability_panel'),
					capabilityPrefix:$("#capabilityInfoForm [name='capabilityPrefix']").val(),
					capabilityLevel:$("#capabilityInfoForm [name='capabilityLevel']").val()
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						alertMsg("保存成功！");
						toNextLink();
					}else{
						alertMsg("设置失败！");
					}
				});
		}
		
		onLoadCapability = function(){
			var url="/signin/load_capability.html",data={
				uKeyId: $("#capabilityInfoForm [name='userId']").val()
			};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#signin_data_template").text());	
				$('#knowledge_capability_panel').html(template({data:convertItemsData(result.data.knowledge,1)}));
				
				var template2 = doT.template($("#signin_data_template").text());	
				$('#ability_capability_panel').html(template2({data:convertItemsData(result.data.capability,2)}));
			});
		};
		
		var convertItemsData = function(array,capabilityType){
			var length = array.length;
			if(length == 0){
				for(var i = 0;i<4;i++){
					var object = {capabilityType:capabilityType,id:randomString(32),capabilityItem:''};
					array.push(object);
				}
			}
			return array;
		}
		
		
		onRemoveCapabilityItem = function(source,type){
			
			var typeStr = type.toString();
			var size = $("#" + panelConfig[typeStr].panelId).find(".ti").size();
			if(size > 1){
				$(source).parent().remove();
			}else{
				$(source).parent().find("[name='capabilityItem']").val("");
			}
		}
		
		onAddCapabilityItem = function(source,type){
			var container = $(source).parent().parent();
			var data = {};
			data.capabilityType = type;
			var template = doT.template($("#signin_capability_data_template").text());
			container.prepend(template(data));
		}
		
		_calCapabilityItem = function(panelId){
			var item = '';
			$('#'+panelId).find('div').each(function(){
				if($(this).hasClass('ti')){
					var id = $(this).find('div input').attr('id') || randomString(32);
					var value = $(this).find('div input').val();
					if(value){
						item += id + "_@_" + value + "_#_";
					}
				}
			});
			return item;
		};
		checkLevelNumber=function(){
			var capabilityLevel=$("#capabilityLevel").val();
			if(capabilityLevel>0&&capabilityLevel<=5){
				return false;
			}else{
				return true;
			}
		}
	});

