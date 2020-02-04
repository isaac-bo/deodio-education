define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.validate",
        "bootstrap.select","jquery.scrolltofixed","upload.common","bootstrap.datepicker"], function($,cookie) {
	
	var _init = function(){
		$("#step").show();
		$("#step1").hide();
		$("#step2").hide();
		$("#step3").hide();
	};
	
	toPaySubStep1Page=function(){
		$("#step").hide();
		$("#step1").show();
		$("#step2").hide();
		$("#step3").hide();
	};
	
	toPaySubStep2Page=function(){
		$("#step").hide();
		$("#step1").hide();
		$("#step2").show();
		$("#step3").hide();
	};
	
	toPaySubStep3Page=function(){
		$("#step").hide();
		$("#step1").hide();
		$("#step2").hide();
		$("#step3").show();
	};
	
	onMouseoverCVV = function(){
		$('.cvv-tip').show();
	};
	
	onMouseoutCVV = function(){
		$('.cvv-tip').hide();
	};
	
	
	_init();
	
});

