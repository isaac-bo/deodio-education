define([ "jquery", "utils.cookie","utils", "jquery.base",
		"jquery.scrolltofixed","ueditor","ueditor.config","jquery.validate"], function($, cookie, doT) {

	
	var _init = function() {
		
		 customInput("remember");
		 um = UM.getEditor('content_template');
	};
	
	
	$('#submitForm').myValidate({
		formCall:function(){ onSetExaminationRules();},
		isAlert:false,
		formKey:false,
		errorCustom:{customFlag:true,regionText:false},
		errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
	});
	
	
	
	var onSetExaminationRules= function(){
		var url = "/quiz/set_rules.html",data={
			finishTime:$("#radio_finish_time").prop('checked')?$.trim($("#finish_time").val()):0,
			passScore:$.trim($("#finish_time").val()),
			maxTimes:$.trim($("#quiz_max_times").val()),
			finalResut:getCheckVal("finalResut"),
			publishResult:getCheckVal("publisResult"),
			quizSafe:getCheckVal("quizSafe"),
			quizContent:um.getContent(),
			quizId:$("#quizId").val()
		};
		postAjaxRequest(url, data, function(result){
//			confirmMsg("是否要发布此试卷,发布会将无法编辑.",function(){
			onPublish(1);
//			});
		});
	};
	

	var getCheckVal = function(id){
		var finalVal ='';
		$("input[id^='"+id+"']").each(function(){
		  if($(this).prop('checked')){
		        finalVal=$(this).val();
		   };
		});
		  return finalVal;
	};
	_init();

	
});
