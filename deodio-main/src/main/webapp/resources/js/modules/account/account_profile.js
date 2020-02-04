	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate"], function($,cookie) {
		
		var _init = function(){
			 customInput('gender');
		};
		

		$('#infoForm').myValidate({
			formCall:function(){ updateUserInfo();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:false},
			errorStyle:{errorRegion:"error-custom",errorClass:"txt-wrong",rightClass:"txt-right"}
		});
		
		
		var updateUserInfo = function(){
			
			var url="/account/update_profile.html",data={
					nickName:$.trim($("#nickName").val()),
					userMail:$.trim($("#userMail").val()),
					lastName:$.trim($("#lastName").val()),
					firstName:$.trim($("#firstName").val()),
					gender:$("input[name='gender']:checked").val(),
					mobilePhone:$.trim($("#mobilePhone").val()),
					telNumber:$.trim($("#telNumber").val()),
					uKeyId:$("#uKeyId").val()
			};
			postAjaxRequest(url, data, function(result){
				alertMsg("修改成功");
			});
		};
		
		
		showIcon = function(){
			$('.user_pic_up').show();
		};
		
		hideIcon = function(){
			$('.user_pic_up').hide();
		};
	
		_init();
		
		
	});

