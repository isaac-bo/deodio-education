	define(["jquery","utils.cookie","jquery.dot","utils.list","pagination","utils","jquery.base","jquery.validate","jquery.scrolltofixed","ueditor",
			"ueditor.config","upload.ui","upload.common","upload.handler","bootstrap.select"], function($,cookie,doT,list,paging) {
		
		
		//弹出选择课件窗口-课件切换方法
		hoverModule = function(str){
			var temp = $("div[id^='format']").hide();
			$("#format"+str).show();
		};
		
		chooseModule = function(str){
			var url="/presentation/set_model.html",data={
				presentationId:$.trim($("#presentationId").val()),
				presentationModel:str
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					if(str == 0){
						go2Page('/presentation/scrom/profile.html','presentationId='+$.trim($("#presentationId").val()));
					}
					if(str == 1){
						go2Page('/presentation/package/files.html','presentationId='+$.trim($("#presentationId").val()));
					}
					if(str == 2){
						go2Page('/presentation/sync/profile.html','presentationId='+$.trim($("#presentationId").val()));
					}
					if(str == 3){
						go2Page('/presentation/external/profile.html','presentationId='+$.trim($("#presentationId").val()));
					}
				}else{
					alertMsg("设置失败！");
				}
			});
		};
		
		var init = function(){	
			
			
			
			
			
			if($('#presentationModel').val()!=''){
				chooseModule($.trim($('#presentationModel').val()));
			}else{
				var template = doT.template($("#chooseFlashWin").text());	
				popUpWindow(template(), "选择课件格式",590,406);
				$('.modal-header').css('background','#fff none repeat scroll 0 0').css('color','#41829a').css('border-bottom','0px');
				$('.modal-header .close').css('color','#41829a');
			}
			list.onFixedItems();
		}
		
		init();
		
	});