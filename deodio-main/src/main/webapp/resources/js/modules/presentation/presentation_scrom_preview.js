	define(["jquery","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.validate","jquery.scrolltofixed","ueditor","jquery.mCustomScrollbar",
			"ueditor.config","upload.ui","upload.common","upload.handler","bootstrap.select","utils.scroll","scrom"], function($,cookie,doT,paging) {
		var _init = function(){
//			var a;
//			var b;
//
//			$("#itemList").show();
//			$.each($("#itemList>li"),function(i,v){
//				 a = v.id;
//			});
//			$.each($("#itemList>input"),function(i,v){
//				 b = v.value;
//			});
			
			var bodyHeight = document.body.clientHeight;
			var vidoHeight = $(".top_title").height()+130;
	
			var timeHeight = (bodyHeight-vidoHeight);
			
			$(".left_video_content").css("height",timeHeight+'px');
			
			
			
			
			_onLoadScormItemList();
		};
		var interval,clickFalg=false;
		var _onLoadScormItemList = function(){
			var url="/presentation/scrom/get_item_list.html",data={
					 presentationId:$.trim($("#presentationId").val())
			};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#data_template").text());	
				$('#items').append(template({"data":result.data}));
				
				$("#rightSco").attr("src",imgStatic+result.data[0].launch);
				
			//	var _time  = $("input[name='countdown']:eq(0)").val();
				
				//setTimeout("countDown()",_time*1000);
				
				
				
			});
		};
		
		
		countDown =  function(){
			interval = setInterval("nextItemPlay()", 1000);
		}
		
		nextItemPlay = function(){
			
			if($("#play_content").val() >= $(".conment_more_box").size()){
				$("#count_time").val(0);
				$("#play_content").val(0);
				clickFalg=false;
				window.clearInterval(interval);
				return;
			};
			
			if(clickFalg){
				$("#count_time").val(Number($("#count_time").val())+1);
			};
			var _index = Number($("#play_content").val());
			
			var _time = $("input[name='countdown']:eq("+_index+")").val();
		
			var nextId = $("input[name='countdown']:eq("+_index+")").attr("id");
			
			if($("#count_time").val()==_time){
				clickFalg = false;
			};
			
			if(!clickFalg){
				$("#count_time").val(0);
				$("#play_content").val(Number($("#play_content").val())+1);
				clickFalg = true
				$("#slides_"+nextId).click();
			
			};
			
		};
		
		
		
		initApi();
		
		_init();
		
		
		_onSavePresentation = function(callback){
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
		
		onChooseScromItem = function(url,e,index){
//			setSelectedLiActiveStatus(e);
			$("#rightSco").attr("src",imgStatic+url);
			
//			clickFalg=false;
//			$("#count_time").val(0);
//			$("#play_content").val(index);
//			//获取当前位置开始播放
//			var _time  = $("input[name='countdown']:eq("+index+")").val();
//			nextItemPlay();
//			setTimeout("countDown()",_time*1000);
			
		};
		
		var  setSelectedLiActiveStatus = function(target){
			$("ul li").each(function(){
				var _self = $(this);
				_self.removeClass("active");
			});
			$(target).parent().addClass("active");
		}


		
		goBack = function(){
			go2Page('/presentation/scrom/profile.html','presentationId='+$("#presentationId").val()); 
		};
		
	});