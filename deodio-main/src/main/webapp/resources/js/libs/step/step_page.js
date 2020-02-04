define(["jquery","jquery.dot","commons/change"], function($,doT,change) {
var stepTemplateDiv = function(dataPanel,dataFun,totalStep,currentStep,customizeFormatPrefix,customizeFormatSuffix) {
	if(dataPanel==undefined){
		dataPanel ="dataPanel";
	}
	if(dataFun==undefined){
		dataFun ="loadDataList";
	}
		
	//加载模板
	var leftLevTemplate = doT.template($("#dagang_box_left_lev_data_template").text());
	var centerConTemplate = doT.template($("#dagang_box_center_con_data_template").text());
	
	var step = stepNum(currentStep, totalStep);

	var array = numListPage(totalStep, null, currentStep,customizeFormatPrefix,customizeFormatSuffix);
	
	step.stepNo = array;
	step.dataFun = dataFun;
	
	$('#'+dataPanel).find(".dagang_box").find(".left_lev").remove();
	$('#'+dataPanel).find(".dagang_box").prepend(leftLevTemplate(step));
	
	$('#'+dataPanel).find(".dagang_box").find(".center_con").remove();
	$('#'+dataPanel).find(".right_cont").prepend(centerConTemplate(step));
//	debugger;
	$('#'+dataPanel).find(".current_step_no").html(change.changeNumToChinese(currentStep,customizeFormatPrefix,customizeFormatSuffix) +"：");
	
	//设置 num_bg位置
	setNumBgPosition(dataPanel,currentStep,totalStep);
	
};

/**
 * 设置当前次位置(num_bg)
 */
var setNumBgPosition = function(dataPanel,curreStep,totalStep){
//	debugger;
	var centerCon = $('#'+dataPanel).find(".dagang_box").find(".center_con");
	var pre = $(centerCon).children(".pre");
	var nex = $(centerCon).children(".nex");
	var numBg = $(centerCon).children(".num_bg");
	var curreNumBgOffset = $(numBg).offset();
	
	var fromTop =  $(pre).offset().top + $(pre).get(0).clientHeight;
	var toTop =  $(nex).offset().top - $(numBg).get(0).clientHeight;
	
	var distanctBetweenStep = (toTop - fromTop)/(totalStep-1);
	var curreStepPosition = fromTop + (curreStep - 1) * distanctBetweenStep;
//	$(numBg).offset({
//		top:curreStepPosition,
//		left:curreNumBgOffset.left
//	});
}

/**
 * 设置次数数据   当前次数，下一个次数，前一个次数，总次数
 */
var stepNum = function(curreStep, totalStep) {
	var step = {};
	if (curreStep > 1) {
		step.pre = curreStep - 1;
	} else {
		step.pre = 0;
	}
	if (curreStep < totalStep) {
		step.nex = curreStep + 1;
	} else {
		step.nex = totalStep;
	}
	step.current = curreStep;
	step.totalStep = totalStep;
	return step;
};
/**
 * Calculating the number of list the total number of pages, the number of records, the current page, the maximum display page
 * 
 */
var numListPage = function(totalStep, totalRow, curreStep,customizeFormatPrefix,customizeFormatSuffix) {
	var maxStep = 10;
	var startStepNo = (curreStep - 1);
	if (startStepNo < 1) {
		startStepNo = 1;
	}
	var endStepNo = curreStep + maxStep / 2;

	if (endStepNo > totalStep) {
		endStepNo = totalStep + 1;
	}
	var array = new Array();
	for ( var i = 0; i < totalStep; i++) {
		array[i] = {
			  num :i+1, chinese:change.changeNumToChinese(i+1,customizeFormatPrefix,customizeFormatSuffix)
		};
	}
	return array;
};
	return{
		stepTemplateDiv:stepTemplateDiv
	}
});