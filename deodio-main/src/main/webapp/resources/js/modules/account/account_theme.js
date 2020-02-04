	define(["jquery","utils.cookie","utils","jquery.base","jquery.validate","jquery.scrolltofixed"], function($,cookie) {
		
		var _init = function(){
			
			
		};
	
		_init();
		

		changLayout = function(type,obj){
			$(obj).parent().find("li").removeClass("on");			
			$(obj).addClass("on");
			$("#theme_id").val(type);
		};	
		
		
		
		
		submitTheme = function(){
			var url="/account/update_theme.html",data={
					uKeyId:$("#uKeyId").val(),
					themeval:$("#theme_id").val()
			}
			postAjaxRequest(url, data, function(result){
				alertMsg("设置成功");
			});
		}
		
		
		
		
		
	});

