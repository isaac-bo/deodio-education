define([ "jquery","utils.dtree", "utils.cookie", "jquery.dot","utils", "jquery.base",
         "jquery.validate","bootstrap.select"], function($, tree, cookie, doT) {
	
	
	listHotTags = function(name,desc){
		if($("#hotTagsList").is(":hidden")){
			$("#hotTagsList").show();
			var url="/tags/hot/list.html",data={
					name:name,
					desc:desc//um.getContent()
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					$("#hotTagsList").empty();
					$.each(result.data,function(i,item){
						$("#hotTagsList").append("<button type='button' onclick='selectHotTag(this)'>"+item.tag_name+"</button>");
						
					});
				}else{
					console.log("获取热门标签失败！");
				}
			});
		}else{
			$("#hotTagsList").hide();
		}
	};
	
	//选择热点标签
	selectHotTag = function(obj){
		//选择一个热点标签添加显示
		var e = jQuery.Event("keydown");//模拟一个键盘事件 
		e.keyCode = 188;//keyCode=188是逗号
		$('.tagator_input').val($(obj).text())
		$(".tagator_input").trigger(e);//模拟按下逗号建
	};

});
