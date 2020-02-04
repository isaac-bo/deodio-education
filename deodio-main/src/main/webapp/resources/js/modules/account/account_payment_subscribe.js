	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate","jquery.scrolltofixed"], function($,cookie) {
		
		var _init = function(){
			 customInput('remember');
			 
		};
		
		onMouseoverCVV = function(){
			$('.cvv-tip').show();
		};
		
		onMouseoutCVV = function(){
			$('.cvv-tip').hide();
		};
	
		_init();
		
	
		
		
		
	});

