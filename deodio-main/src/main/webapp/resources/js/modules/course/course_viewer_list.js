define([ "jquery", "utils.dtree", "utils.cookie", "utils.list", "jquery.dot","utils.menu", "pagination", "utils", "jquery.scrolltofixed",
		"jquery.scroll.pagination", "bootstrap.select", "jquery.base" ], function($, tree, cookie, list, doT, menu,paging) {
	
	var condition ={"condition1":'0',"condition2":'0' };
	
	var choose ={"condition1":'0',"condition2":'0' };
	
	var init=function(){
		
		$('#tabList a').click(function (e) {
		       e.preventDefault();
		       $(this).tab('show');
		       $("#tabList li").each(function(index){
		    	   if($(this).attr("class")=="active"){
		    		   var aElement = $(this).find("a");
		    		   var dataId = aElement.attr("data-id");
		    		   $(window).unbindScroll();
		    		   switch(dataId){
		    		   //我的課程
		    		   case "content" :
		    			   $('#content').scrollPagination({
		    					'postUrl': ctx+'/course/load_course_viewer_list.html', 
		    					'postParams': {pageNo:1,flag:JSON.stringify(condition)}, 
		    					'scrollTarget': $(window),
		    					'callBackLoad':function(data){
		    						loadCourseViewerDataList(data);
		    					},
		    					'beforeLoad': function(){ 
		    						this.postParams.pageNo=$("#hid_default_page").val();
		    						this.postParams.flag=JSON.stringify(condition);
		    					}
		    					
		    				});
		    		       break;
		    		   //我的课程包
		    		   case "packageCourseContent" :
		    				$('#packageCourseContent').scrollPagination({
		    					'postUrl': ctx+'/course/load_package_course_list.html', 
		    					'postParams': {pageNo:1,flag:JSON.stringify(choose)}, 
		    					'scrollTarget': $(window),
		    					'callBackLoad':function(data){
		    						loadPackageCourseList(data,0);
		    					},
		    					'beforeLoad': function(){ 
		    						this.postParams.pageNo=$("#hidden_page").val();
		    						choose.condition1=$('#flag1').val();
		    						choose.condition2=$('#flag2').val();
		    						this.postParams.flag=JSON.stringify(choose);
		    					}
		    					
		    				});
		    			   break;
		    		   }
		    	   }
		       });
		    });
			
		$('#tabList a:first').click()
		
		$('#myCourse a').click(function (e) {
	       e.preventDefault();
	       $(this).tab('show');
	       $("#myCourse li").each(function(index){
	    	   if($(this).attr("class")=="active"){
	    		   var aElement = $(this).find("a");
	    		   var aElementValue = aElement.attr("data-value");
	    		   if(aElement.attr("data-class")=="condition1"){
	    			   condition.condition1 = aElementValue;
	    		   }else{
	    			   condition.condition2 = aElementValue;
	    		   } 
	    	   }
	       });
	       reloadPageData(condition);
	    });
		
		$('#myCourse a:first').click()
	
		
	};
	
	var loadCourseViewerDataList=function(data){
		var template = doT.template($("#data_template").text());
		if($("#content li").size()==0){
			$('#content').append(template({"data":data}));
		}else{
			$('#content li.my_data:last').after(template({"data":data}));
		}
//		$("#content").html(template({"data":data}));
		if(data.dataList.length!=0){
			var finalPageNo = Number($("#hid_default_page").val())+1;
			$("#hid_default_page").val(finalPageNo);			
		}
	};

	   
	var reloadPageData=function(condition){
		var url="/course/load_course_viewer_list.html",data={
				 pageNo:1, 
				 flag:JSON.stringify(condition)
		};
		postAjaxRequest(url, data, function(result){
			console.log("result--- ",result);

			var template = doT.template($("#data_template").text());
			$("#content").html(template({"data":result.data}));
		});
	};
	
	
	init();
	
});