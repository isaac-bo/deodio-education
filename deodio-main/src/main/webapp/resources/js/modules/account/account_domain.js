	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate","jquery.scrolltofixed"], function($,cookie) {
		
		var _init = function(){
			 
			
		};
	
		_init();
		

		$('#subdoForm').myValidate(
				{
					formCall:function(){subdoForm();},
					isAlert:false,
					formKey:false,
					errorCustom:{customFlag:false,regionText:false},
					errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		
	
		validateHostExists = function(){
			var flag=false;
			var url="/account/validate/domain_exist.html",data={
				subdomain:$.trim($("#subdomain").val())
			};
			postAjaxRequest(url, data,function(result){
				flag=result.data;
			},false);
			return flag;
		};
		
		
		var subdoForm = function(){
			var url="/account/update_domain.html",data = {
				subdomain:$.trim($("#subdomain").val()),
				uKeyId:$("#uKeyId").val()
			};
			
			postAjaxRequest(url, data,function(result){
				alertMsg("提交成功");
			});
			
			
		};
		
		
		
		
	});

