	define(["jquery","utils.cookie","jquery.dot","utils.list","utils","jquery.base","jquery.validate","jquery.scrolltofixed","jquery.mCustomScrollbar",
			"upload.ui","upload.common","upload.handler","bootstrap.select"], function($,cookie,doT,list) {
		
		var init = function(){	
			
			$('#popupModel').on('hidden.bs.modal', function () {
				$('body').removeClass('modal-open').css('overflow-y','auto');
			});
			
	        
	        //课件导入注册 start
	        var slidesHandler = new UploadHandler('slides_upload_init', 'upload_tbody_slides');
		    slidesHandler.initSlidesPageEvent();
		    var mediaHandler = new UploadHandler('media_upload_init', 'upload_tbody_media');
		    mediaHandler.initMediaPageEvent();
		    //课件导入注册 end
		    
		    //刷新查询进度
		    $("div [id^='test']").click();
		    
		    //是否隐藏多媒体
		    var isSlideShow = $("#isSlideShow").val();
		    if(isSlideShow==1){
		    	$("#syncMedia").show();
		    }else if(isSlideShow==0){
		    	$("#syncMedia").hide();
		    	$("#slidesPanel").css('margin-left','202px');
		    }
		    
		    enablePopover();
		    
		    list.onFixedItems();
		}
		
		var enablePopover = function(){
			$('[data-toggle="popover"]').each(function () {
				var element = $(this);
					var formatEelementsName = '<ul><li style="font-size:12px;">' + $(this).parent().find("[name='tipHidden']").val() + '</li></ul>';
					element.popover({
						container:"body",
						trigger: 'manual',
						placement: 'top', //top, bottom, left or right
//						title: "提示",
						html: 'true',
						content: formatEelementsName
					}).on("mouseenter", function () {
		//				debugger;
							var _this = this;
							$(this).popover("show");
							$(this).siblings(".popover").on("mouseleave", function () {
								$(_this).popover('hide');
							});
					}).on("mouseleave", function () {
						var _this = this;
							setTimeout(function () {
								if (!$(".popover:hover").length) {
									$(_this).popover("hide")
								}
							}, 100);
					}).on("mouseout", function () {
						var _this = this;
							setTimeout(function () {
								if (!$(".popover:hover").length) {
									$(_this).popover("hide")
								}
							}, 100);
					});
				});
		}
		
		init();
		
		//返回上一步
		prevStep = function(){
			go2Page('/presentation/profile.html','presentationId='+$('#presentationId').val());
		}
		
		saveSubmitSyncFiles = function(){
			
			if($("#isSlideShow_syn").val()==1 && ($(".upload_tbody_slides").size()==0 && $(".upload_tbody_media").size()==0)){
				alertMsg("请上传 SLIDES课件 和 多媒体课件！");
				return;
			};
			
			
			if($(".upload_tbody_slides").size()==0){
				alertMsg("请上传SLIDES课件！");
				return;
			};
			if($("#isSlideShow_syn").val()==1 && $(".upload_tbody_media").size()==0){
				alertMsg("请上传多媒体课件！");
					return;
			};
			
			
			
			
			go2Page('/presentation/sync/media.html','presentationId='+$('#presentationId').val()+"&showType="+$("#isSlideShow_syn").val());
		}		
		
		
		
		//删除多媒体
		delMedia = function(mediaId,localId){
			/*console.log(syncMediaId);
			console.log(jobId);
			console.log(logicFilePath);
			console.log(mediaId);*/
			var url="/presentation/delMedia.html",data={
					mediaId:mediaId
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					//alertMsg("删除成功!");
					if(localId!=undefined){
						$("#file_"+localId).remove();
					}else{
						$("#file_"+mediaId).remove();
					}
					
					if($("#upload_tbody_media li").size()==0){
						$("#mediaButton").show();
						$("#mediaDiv").attr("class","con");
						$("#upload_tbody_media").hide();
					}
				}else{
					alertMsg("删除失败！");
				}
			});
		}
		
		//删除幻灯片
		delSyncSlide = function(slideId,localId,sysFlag){
		
			var url="/presentation/delSyncSlide.html",data={
					slideId : slideId,
					presentationId:$.trim($("#presentationId").val()),
					sysFlag:localId!=undefined?sysFlag:0
					
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					//alertMsg("删除成功!");
					if(localId!=undefined && localId!=''){
						$("#file_"+localId).remove();
					}else{
						$("#file_"+slideId).remove();
					}
					if($("#upload_tbody_slides li").size()==0){
						$("#pointButton").show();
						$("#pointDiv").attr("class","con");
						$("#upload_tbody_slides").hide();
					}
				}else{
					alertMsg("删除失败！");
				}
			});
		};
		
		goBack = function(){
			go2Page('/presentation/sync/profile.html','presentationId='+$.trim($("#presentationId").val()));
		};
		
		
		
	});