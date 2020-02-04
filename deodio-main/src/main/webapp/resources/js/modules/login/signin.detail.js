	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate",
	        "bootstrap.select","jquery.scrolltofixed","upload.common","bootstrap.datepicker"], function($,cookie) {
		
		var _init = function(){
			
	         $('#myTab a').click(function (e) {
	        	 e.preventDefault();//阻止a链接的跳转行为
	        	 $(this).tab('show');//显示当前选中的链接及关联的content
	        	 loadData(this);
	        });
		};
		
		toNextLink = function(){
			var currentTab = $("#myTab li.active");
			var nextTab = currentTab.next();
			nextTab.find("a").tab("show");
			loadData(nextTab.find("a"));
			$('body').removeClass('modal-open').css('overflow-y','auto');
		}
		
		var loadData = function(jqueryObj){
			var paneId = $(jqueryObj).attr("href");
			if('#personalCapability' == paneId){
				onLoadCapability();
			}
		}
		
		_init();
		
	});

