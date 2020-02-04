define(["jquery","utils.cookie","utils.menu","utils","jquery.base","jquery.validate","ueditor",
		"ueditor.config","upload.common","jquery.scrolltofixed"], function($,cookie) {
	
	um = UM.getEditor('content_template');
	

	uploadFileJqProcess('uploadFile','*.jpg;*.png;',{accountId:cookie.getCookie('CAID'),attachRelType:"4"},ctx+'/commons/uploadAttach.html?r=' + Math.floor(Math.random() * 100)
			,function(fileName){
				$("#uploadFileName").html(fileName);
			}
			,function(data){
				$("#hidUploadId").val(data.id)
			},true);
	
	
	
	createForm = function(){
		var groupId=$("#hidgoupid").val();
		var url="/group/groupNameExsit.html",data={
				groupName:$("#groupName").val()
			};
			postAjaxRequest(url, data,function(result){
				if(result.status == 1){
					$("#hidContent").val(um.getContentTxt());
					$("#createForm").submit();
				}else{
					if(groupId.length==0){
					   alertMsg("小组名称已存在!");
					}else{
						$("#hidContent").val(um.getContentTxt());
						$("#createForm").submit();
					}
				}
			});
		
	}
	
	onFocusGroupName = function(){
		alert(1);
	}
	
	setUeditorContent = function(){
		if($("#hid_Content").val().length!=0){
			um.ready(function(){
				um.setContent($("#hid_Content").val(),true);
			});
			
		}
	};
	
	var init = function(){
	
		 $('#createForm').myValidate(
			{
				formCall:function(){createForm();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:true,regionText:true},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});	
		 
		setUeditorContent();
	}

	init();
	
});

