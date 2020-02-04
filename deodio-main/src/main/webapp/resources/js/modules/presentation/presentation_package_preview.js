define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.validate", "jquery.scrolltofixed",
		"jquery.mCustomScrollbar" ], function($, cookie, doT, paging) {
		var init = function(){
			
			  
			
//			onMenu();
			onLoadPackageItemList();
			var _screen = window.screen.height;
			if(_screen <= 800){
				// 初始化高度
				//初始化时间轴高度
				var bodyHeight = document.body.clientHeight;
				var vidoHeight = $(".top_title").height()+130;
		
				var timeHeight = (bodyHeight-vidoHeight);
				
				$(".left_video_content").css("height",timeHeight+'px');
			}
			
	
			
			
			
//			createSWFObject4LastestVersion("/DeodioPaperViewer.swf", "packageContent", "800", "600", {presentationId:$.trim($("#presentationId").val()),accountId:$.cookie('CAID'),quality:'high',wmode:'opaque'}, { allowFullScreen:'true',wmode:'transparent'}, {},function(){
//	        	$("#file_swf_panel").css('margin-left','-40px');
//	        });
		};
		var interval,interval2,clickFalg=false;
		onLoadPackageItemList = function(){
			var url="/presentation/package/get_item_list.html",data={
					 presentationId:$.trim($("#presentationId").val())
			};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#data_template").text());	
				$('#items').append(template({"data":result.data}));
				
//				for(var index = 0; index < result.data.length; index++){
//					if(index == 0){
//						//onChoosePackageItem($('#'+result.data[index].id));
//						onChoosePackageItem(result.data[index].id);
//					};
//				};
				
				$("#itemFramePanel").attr('src',htmlDir = ctx + "/commons/getLocalHtmlForId.html?itemId=" + result.data[0].id);
				
				if($("#auto_play_id").val()==1){
					var _time  = $("input[name='countdown']:eq(0)").val();
					
					setTimeout("countDown()",_time*1000);
				};
			
			});
		};
		
		countDown =  function(){
			var int = setInterval("nextItemPlay()", 1000);
			interval = int;
		}
		
		nextItemPlay = function(){
		
			$("#count_time").val(Number($("#count_time").val())+1);
			
			var _index = Number($("#play_content").val());
			var _time = $("input[name='countdown']:eq("+_index+")").val();
			
			if($("#count_time").val()==_time){
				clickFalg = false;
			};
			
			if(!clickFalg){
				$("#count_time").val(0);
				$("#play_content").val(Number($("#play_content").val())+1);
			
				var nextId = $("input[name='countdown']:eq("+ Number($("#play_content").val())+")").attr("id");
			
				clickFalg = true;
					
				$("#"+nextId).click();
			
			};
			
		};
		
		
		onChoosePackageItem = function(ppItemId,index){
						
			htmlDir = ctx + "/commons/getLocalHtmlForId.html?itemId=" + ppItemId;
			$("#itemFramePanel").attr('src',htmlDir);
			
			if($("#auto_play_id").val()==1){
				
				$("#count_time").val(0);
				$("#play_content").val(index);
				if($("#play_content").val() >= ($(".conment_more_box").size()-1)){
					clearInterval(interval);
					clearInterval(interval2);
					clickFalg=false;
					
				};
				var nextId = $("input[name='countdown']:eq("+index+")").attr("id");
				$("#"+nextId).click(function(event){
					if(event.originalEvent){
						$("#play_content").val(index);
						clickFalg = true;
						var int = setInterval("nextItemPlay()", 1000);
						interval2 = int;
					}else{
						
					};
				});
			};
		}
		
		
		
		//update show html content for dataId 
//		onChoosePackageItem=function(obj){
////			setSelectedLiActiveStatus(obj);
//			var htmlDir=null;
//			var fileSrc=$(obj).attr("data-src");
//			var fileExt=$(obj).attr("data-package-ext");
//			var fileName=$(obj).attr("data-file-name");
//			
//			if(fileExt=="doc" || fileExt=="docx" || fileExt=="pdf"){
////				htmlDir=fileSrc+getFileNameNoEx(fileName)+"/"+getFileNameNoEx(fileName)+".html";
//				//合成文件地址
//				var localPath =fileSrc+getFileNameNoEx(fileName)+"\\"+getFileNameNoEx(fileName);
//				htmlDir = ctx + "/commons/getLocalHtml.html?htmlUrl=" + localPath;
//			}
//			console.log(htmlDir);
//			$("#itemFramePanel").attr('src',htmlDir);
//		};
		
		
		
		
		
		
		
		getFileNameNoEx=function(filename){
			if ((filename != null) && (filename.length> 0)) {
				var dot = filename.lastIndexOf('.'); 
				if ((dot >-1) && (dot < (filename.length))) { 
					return filename.substring(0, dot); 
				}
			}
			return filename; 
		};
		
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
		
		goBack = function(){
			go2Page('/presentation/package/profile.html','presentationId='+$("#presentationId").val()); 
		};
		
		
		
		var  setSelectedLiActiveStatus = function(target){
			$("ul li").each(function(){
				var _self = $(this);
				_self.removeClass("active");
			});
			$(target).parent().addClass("active");
		};
		

		
		init();

	});