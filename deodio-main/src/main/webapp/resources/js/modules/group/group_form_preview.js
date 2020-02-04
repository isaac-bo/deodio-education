define(["jquery","utils.cookie","jquery.dot","utils",
        "jquery.base","jquery.scrolltofixed","jquery.ui"], function($,cookie,doT) {

	

	var init = function(){
		
		 
		
		 customInput("survey");
		 $(".del_icon").remove();
		 $("button").remove();
		 $("#draggableContent h3").removeClass("edit");

		 $("input[type='text']").each(function(){
		     $(this).parent().text($(this).val());

		 });
	}
	init();
   backLastPage=function(){
	  var preData=$("#preData").val();
	  preData=preData.replace(new RegExp("#",("gm"))," %23");
	  go2Page('/group/form/manage.html','preData='+preData+'&groupId='+$("#groupId").val());
   }
	
	

	
});

