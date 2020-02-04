define([ "jquery", "utils.cookie", "jquery.mCustomScrollbar", "utils",
		"jquery.base", "jquery.scrolltofixed" ], function($, cookie) {
		
		var _init = function(callback){	

			var url="/presentation/external/items.html",data={
				presentationId:$.trim($("#presentationId").val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					callback(result.data);
				}
			});
		};
		
		
		initPresentationExternalItems = function(callback){
			_init(callback);
		};
		
	});