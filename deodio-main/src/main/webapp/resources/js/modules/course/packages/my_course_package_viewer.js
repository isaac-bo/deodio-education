define([ "jquery", "utils.cookie", "utils.list", "jquery.dot", "utils.menu", "pagination", "utils", "jquery.base", "bootstrap.datepicker",
		"jquery.scrolltofixed", "jquery.scroll.pagination" ], function($, cookie, list, doT, menu, paging) {
	
	var choose ={"condition1":'0',"condition2":'0' };
	
	var init=function(){
		$('#logo a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
			$("#logo li").each(function(index) {
				if ($(this).attr("class") == "active") {
					var aElement = $(this).find("a");
					var aElementValue = aElement.attr("data-value");
					if (aElement.attr("data-class") == "choose1") {
						choose.condition1 = aElementValue;
						$('#flag1').val(aElementValue) ;
					} else {
						choose.condition2 = aElementValue;
						$('#flag2').val(aElementValue) ;
					}
				}
			});
			$('#flag').val(choose)
			loadPageData(choose);
		});
		
		$('#logo a:first').click();
		
		
//		$('#packageCourseContent').scrollPagination({
//			'postUrl': ctx+'/course/load_package_course_list.html', 
//			'postParams': {pageNo:1,flag:JSON.stringify(choose)}, 
//			'scrollTarget': $(window),
//			'callBackLoad':function(data){
//				loadPackageCourseList(data);
//			},
//			'beforeLoad': function(){ 
//				this.postParams.pageNo=$("#hidden_page").val();
//				this.postParams.flag=JSON.stringify(choose);
//			}
//			
//		});
	};
	
	var loadPageData=function(choose){
		var url="/course/load_package_course_list.html",data={
				 pageNo:1, 
				 flag:JSON.stringify(choose)
		};
		postAjaxRequest(url, data, function(result){
			var template = doT.template($("#my_course_package_template").text());
			$("#packageCourseContent").html(template({"data":result.data}))
		});
	};
	
	 loadPackageCourseList=function(data,tag){
		var template = doT.template($("#my_course_package_template").text());
		if($("#packageCourseContent li").size()==0){
			$('#packageCourseContent').append(template({"data":data}));
		}else{
			$('#packageCourseContent li.my_data:last').after(template({"data":data}));
		}
//		$("#packageCourseContent").html(template({"data":data}));
		if(data.dataList.length!=0){
			var finalPageNo = Number($("#hidden_page").val())+1;
			$("#hidden_page").val(finalPageNo);			
		}
	};
	
	
	init();

});