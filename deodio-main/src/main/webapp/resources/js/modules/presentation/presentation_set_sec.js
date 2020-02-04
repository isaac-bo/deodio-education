	define(["jquery","utils.cookie","utils","jquery.base","bootstrap.select","jquery.validate","jquery.scrolltofixed"], function($,cookie) {
/*		
		var _init = function(){
			
			 $('.nav').scrollToFixed();
			 $('select').selectpicker();		 
			 
		};
	
		_init();*/
		$('#chooseFormatForm').myValidate({
			formCall:function(){ chooseFormat();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:false},
			errorStyle:{errorRegion:"error-custom",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
	var chooseFormat = function(){/*
			var url="/presentation/presentation_set_thi.html",data={
					companyName:$.trim($("#companyName").val()),
					companyTel:$.trim($("#companyTel").val()),
					companyMail:$.trim($("#companyEmail").val()),
					countryId:$.trim($("#select_country").val()),
					provinceId:$.trim($("#province_select").val()),
					cityId:$.trim($("#city_select").val()),
					address:$.trim($("#companyAddressDetails").val()),
					uKeyId:$("#uKeyId").val()
			};
			postAjaxRequest(url, data, function(result){
				alertMsg("修改成功");
			});
		*/}
		
		
	});

