define([ "jquery", "utils.cookie","utils.list", "jquery.dot","utils", "jquery.base", "jquery.validate", "jquery.scrolltofixed","jquery.ui","upload.common"], function($, cookie, list,doT) {	
	var _init = function() {

//		customInput("quiz");
		$("#saveForm").myValidate({
			formCall:function(){ saveSurvey();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});	
		
		list.onFixedItems();
	};
	
	_init();

	//获取题目类型
	var getQtype = function(qtype){
		var _type='';
		if(qtype==1 || qtype==3){
			_type='input[type="radio"]:checked';
		}else if(qtype==2){  
			_type='input[type="checkbox"]:checked';
		}
		return _type;
	};
	
	//获取问卷调查的所有题目内容信息
	var getSurveyData = function(){
		var dataArray = new Array();
		$(".chouti").each(function(i,v){
		    var _qtype=$(this).attr('qtype');
		    var _question = $(this).find('div.edit_title input').val();
		    var _optionsType = getQtype(_qtype),answers = new Array();
		    $(this).find(_optionsType).each(function(){
		    	answers.push($(this).val());
		    });
		    var optionArray =  new Array();
		    if(_qtype==1||_qtype==2){
		    	$(this).find('div.ti').each(function(){
			    	var _options = $(this).find('input[type="text"]');
			    	_options.each(function(){
				        optionArray.push($(this).val());
				    });
			    });		     
			}	  
		    if(_qtype==3){
		    	$(this).find('div.ti').each(function(){
			    	var _options = $(this).find('input[type="radio"]');
			    	_options.each(function(){
				        optionArray.push($(this).val());
				    });
			    });	
		    }
		    if(_qtype==6){
		    	textarea = $(this).find('div.jianda textarea').val();
		    	optionArray.push(textarea);
		    }
		    var dataStr = _qtype + "_#_" + _question + "_#_" + optionArray.join("]=[");
			dataArray.push(dataStr);    
		});
		return dataArray.join("_@_");
	}
	
	//创建问卷调查内容
	saveSurvey = function(){
		console.log("saveSurvey---getSurveyData",getSurveyData())
		$("#hiddataStr").val(getSurveyData());
		var surveyContents=$("#hiddataStr").val();	
		if(surveyContents.length<1){
			alertMsg('问卷调查的内容不能为空');
		}else{
			$("#saveForm").submit();
		}
//		$("#hiddataStr").val(getQuestionList());
//		var tagsLength=$("#hiddataStr").val();
//		
//		if(tagsLength.length<1){
//			alertMsg('问卷调查的内容不能为空');
//		}else{
//			$("#saveForm").submit();
//		}

	};
	onEditSurvey=function(surveyId){
		go2Page('/survey/profile.html','surveyId='+surveyId);
	}
//	return {draggableFun:draggableFun}
});
