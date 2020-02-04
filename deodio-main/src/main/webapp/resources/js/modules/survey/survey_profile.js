define([ "jquery", "utils.cookie", "utils.list","jquery.dot","pagination","fileupload.common", "utils", "jquery.base",
		"jquery.scrolltofixed","ueditor","bootstrap.select","bootstrap.datepicker",
		"ueditor.config","jquery.validate"], function($, cookie, list,doT, paging) {
	um = UM.getEditor('content_template');
	
	var _init = function() {
		
		customInput('surveyModel');
		//日期控件
		$(".form_datetime").datetimepicker({
	        format: "yyyy-mm-dd",
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			startDate:new Date()
	    });
		$('select').selectpicker();
		$("#startTime").val(dateFormat($("#startTime").val()));
		$("#endTime").val(dateFormat($("#endTime").val()));
		
		list.onFixedItems();
	};
	
	//表单提交验证
	$("#createForm").myValidate({
		formCall:function(){ createForm();},
		isAlert:false,
		formKey:false,
		errorCustom:{customFlag:true,regionText:true},
		errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
	});
	
	_init();
	
	//创建问卷调查
	var createForm = function(){
		var surveyDesc=um.getContent();
		$("#surveyDesc").val(surveyDesc);
		if(removeHtmlTags(surveyDesc).length>255){
			alertMsg('问卷描述内容长度不能大于255字符')
		}else{
			$("#createForm").submit();
		}
		
	};
	checkSurveyName = function() {
		var flag = false;
		var url = "/survey/check/surveyName.html";
		var surveyName=$("#surveyName").val();
		var data = {
				surveyName : surveyName
					}
		if (surveyName.length>0&&surveyName != $("#oldSurveyName").val()) {
			postAjaxRequest(url, data, function(result){
				if (result.data) {
					flag = true;
				} 
			}, false);
		}
		return flag;
	}
	//返回调查问卷列表
	backSurveyList = function(){
		go2Page('/survey/list.html');
	};


	var uploadUrl = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#surveyId").val());
	
	uploadFileJqProcess("surveyUploadFile",uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
			1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
		var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
		var srcimgTemp = data.result.data.generateName;
		$("#preImgId").attr("src",srcimg);
		$("#surveyCover").val(srcimgTemp);
		$("#attachId").val(data.result.data.id);
	},function(e,data){
		
	});
	deleteFileImg=function(){
		$("#preImgId").attr("src",ctx + '/resources/img/account/user-card-1.jpg');
		$("#surveyCover").val('');
		$("#attachId").val('');
	}
//	$("#surveyName").blur(function(){
//		var oldSurveyName=$("#oldSurveyName").val().trim();
//		var surveyName=$("#surveyName").val().trim();
//		if(oldSurveyName.length>0){
//			if(surveyName.length==0){
//				$("#surveyName").val(oldSurveyName)
//				$("#surveyName").attr('style','')
//				$("#surveyName").removeAttr('lable-error')
//			}
//		}
//	})
});
