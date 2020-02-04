define([ "jquery", "utils.cookie", "jquery.mCustomScrollbar", "utils",
		"jquery.base", "jquery.scrolltofixed" ], function($, cookie) {
		
		$.ready(function(){
			$("#itemFramePanel").mCustomScrollbar();
		});
		var _init = function(){	
//			$("#itemFramePanel").mCustomScrollbar();
			// 初始化高度
			//初始化时间轴高度
			var bodyHeight = document.body.clientHeight;
			var vidoHeight = $(".top_title").height()+130;
	
			var timeHeight = (bodyHeight-vidoHeight);
			
			$(".video_box2").css("height",timeHeight+'px');
			
			
		};
		
		
		var _onSavePresentation = function(callback){
//			var url="/presentation/sync/save.html",data={
//					presentationId:$.trim($("#presentationId").val()),
//					currentUserId:cookie.getCookie('CUID'),
//					pointsJson:JSON.stringify(points),
//					mediasJson:JSON.stringify(medias)
//				};
//				postAjaxRequest(url, data, function(result){
//					if(result.status == 1){
//						callback();
//					}
//				});
			callback();
		};
		
		onSavePresentation = function(){
			
			onSave(_onSavePresentation,function(){
				 go2Page('/presentation/list.html'); 
			});
		};
		
		onPublishPresentation = function(){
			onSave(_onSavePresentation,onPublish);
		};
		
		goBack = function(){
			go2Page('/presentation/external/profile.html','presentationId='+$.trim($("#presentationId").val())); 
		}
		_init();
	});