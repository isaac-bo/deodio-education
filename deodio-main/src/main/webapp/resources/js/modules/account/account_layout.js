	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate","jquery.scrolltofixed"], function($,cookie) {
		
		var _init = function(){
		
			
		};
	
		_init();
		

		changLayout = function(type,obj){
			$(obj).parent().find("li").removeClass("on");			
			$(obj).addClass("on");
			$("#layout_id").val(type);
		};	
		
		
		submitLayout = function(){
			var url="/account/update_layout.html",data={
					uKeyId:$("#uKeyId").val(),
					layval:$("#layout_id").val()
			}
			postAjaxRequest(url, data, function(result){
				alertMsg("设置成功");
			});
		}
		
		
		
	});

