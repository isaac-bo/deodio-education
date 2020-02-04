;(function($){
	 var i=0;
	 $.fn.scrollPagination = function(options) {
			var opts = $.extend($.fn.defaults, options);  
			var target = opts.scrollTarget;
			if (target == null){
				target = obj; 
		 	}
			opts.scrollTarget = target;
		 
			return this.each(function() {
			  $.fn.scrollPagination.init($(this), opts);
			});

	  };
	
	
	
	 $.fn.defaults = {
	      	 'postUrl' : null,
	     	 'postParams' : {},
			 'beforeLoad': null,
			 'callBackLoad':null,
			 'scrollTarget': null,
			 'lock':true
	 };	

	 $.fn.scrollPagination.init = function(obj,opts){
	
		 var _target = opts.scrollTarget;
		 $(obj).attr('scrollPagination', 'enabled');
		 $(_target).scroll(function(event){
				if ($(obj).attr('scrollPagination') == 'enabled'){
			 		$.fn.scrollLoadData(obj, opts);		
				}
				else {
					event.stopPropagation();	
				}
			 });
			 
		$.fn.scrollLoadData(obj, opts);
	 };

	 $.fn.scrollLoadData = function(obj,opts){
		
		 var _target = opts.scrollTarget;
		 //changed by xu.xiangdong 适应 div滚动加载  start
		 var flag = false;
         var scrollTop =$(_target).scrollTop();//滚动高度  
         var scrollTopMax = $(_target).get(0).scrollHeight - $(_target).get(0).clientHeight;
         
         if(!scrollTopMax){
        	 flag = $(document).height() == $(_target).height() + scrollTop;
         }else{
        	 flag = (scrollTop == scrollTopMax) && (scrollTopMax > 0);
         }
//         debugger;
//       var loadFlag = $(_target).scrollTop() >= $(document).height() - $(_target).height();
//		 if($(document).height() == $(_target).height() + $(_target).scrollTop()){ 
         if(flag && opts.lock){
        //changed by xu.xiangdong 适应 div滚动加载  start  end
		 		 if (opts.beforeLoad != null){
						opts.beforeLoad(); 
				 };
				 //加载数据的时候把lock设为false
				 opts.lock = false;
				 $.ajax({
					  type: 'POST',
					  url: opts.postUrl,
					  data: opts.postParams,
					  dataType: 'json',
					  success: function(restult){
						  opts.lock = true;
						  opts.callBackLoad(restult.data);
						
					  }
					 
				 }); 
	        }  
		
	 }
	 
	 $.fn.unbindScroll = function(){
		 return this.each(function() {
			 	$(this).off("scroll");
			  });
	 }
	 
	 $.fn.stopScrollPagination = function(){
		  return this.each(function() {
		 	$(this).attr('scrollPagination', 'disabled');
		  });
		  
	  }; 

})(jQuery);