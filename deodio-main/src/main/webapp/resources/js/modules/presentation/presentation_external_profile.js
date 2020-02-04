define([ "jquery", "utils.cookie","utils.list", "utils.validate", "jquery.dot", "upload.common",
		"pagination", "utils", "jquery.base", "jquery.validate",
		"jquery.scrolltofixed", "bootstrap.select" ],function($, cookie, list,validate, doT, paging) {
		
		var itemType = 0;
		
		var onSetItemType = function(){
			if($("#isHtml").prop("checked")){
				itemType = 1;
			}else if($("#isYouku").prop("checked")){
				itemType = 2;
			}
		};
		
		var init = function(){	
			
			
			onSetItemType();
			customInput("isCourse");
			customInput("itemTypeRadioGroup");
			
			$("input[type=radio][name=itemTypeRadioGroup]").change(function(){
				onSetItemType();
			});
			
			 list.onFixedItems();
		};
		
		
		init();
		
		onDelExternalItem = function(){
			
			$('#itemFramePanel').hide();
			$('#itemBlankPanel').show();
			$('#itemYoukuPanel').hide();
			$("#itemUrl").val('');
			document.getElementById('itemFramePanel').src = "";
			$('#movie_player').attr('src','');
		};
	
		onTestExternalItem = function(){
			var itemUrl = $.trim($("#itemUrl").val());
			
			if(itemType == 0){
				alertMsg('请选择您要设置的课件来源，直接引用HTML或引用优酷视频？');
				return;
			}
			
			if(itemUrl.length<=0){
				alertMsg('请您设置的课件来源地址！');
				return;
			}
			
			if(itemType == 1 && validate.isUrl(itemUrl)){
				
				$('#itemFramePanel').show();
				$('#itemBlankPanel').hide();
				$('#itemYoukuPanel').hide();
				document.getElementById('itemFramePanel').src =itemUrl;
				$('#movie_player').attr('src','');
			}else if(itemType == 1 && !validate.isUrl(itemUrl)){
				
				alertMsg('请检查您的HTML URL地址是否正确？正确地址如下例：http://www.deodio.com.cn');
				onDelExternalItem();
				
			}else if(itemType == 2&&validate.isYouku(itemUrl)){
				
				$('#itemFramePanel').hide();
				$('#itemBlankPanel').hide();
				$('#itemYoukuPanel').show();
//				document.getElementById('itemFramePanel').src = "";
//				$('#movie_player').attr(
//							'src',
//							"http://player.youku.com/player.php/Type/Folder/Fid/4699493/Ob/1/Pt/51/sid/"
//									+ validate.trimYouku(
//											$.trim($("#itemUrl").val())) + "/v.swf")
				var re = /src=[\'|\"]([^\"]*?)[\'|\"]/i;
				var youkuUrl = itemUrl.match(re)[1];
				var showVideoHtml="";
				//判断是否 iframe 或者
				if(itemUrl.indexOf("iframe")>-1){
					showVideoHtml = "<iframe height=498 width=1018 src='"+youkuUrl+"' frameborder=0 'allowfullscreen'></iframe>";
				}else{
					showVideoHtml = "<embed src='"+youkuUrl+"' " +
							"allowFullScreen='true' quality='high' width='1018' height='498' align='middle' " +
							"allowScriptAccess='always' type='application/x-shockwave-flash'></embed>";
				};
			//通过优酷分享方式
				$("#itemYoukuPanel").html(showVideoHtml);							
											
				$("#hid_youku_url").val(showVideoHtml);
				$("#hid_video_url").val(youkuUrl);
				
			}else if(itemType == 2&& !validate.isYouku($.trim($("#itemUrl").val()))){
				alertMsg('请检查您的Youku URL地址是否正确？点击优酷分享复制相关链接');
				onDelExternalItem();
			}
			
			
		};
//		 
		
		onSubmitExternalItem = function(call){
			
			var type = $("input[type=radio][name=itemTypeRadioGroup]:checked").val();
			
			
			var url = "/presentation/external/save_external_info.html", data = {
					presentationId : $.trim($("#presentationId").val()),
					externalType : type,
					isCourse : $("#isCourse").prop("checked") ? 1: 0,
					externalUrl : type==1?$.trim($("#itemUrl").val()):$.trim($("#hid_youku_url").val()),
					externalVideoUrl : type==1?$.trim($("#itemUrl").val()):$("#hid_video_url").val()
				};
				postAjaxRequest(url, data, function(result) {
					if (result.status == 1) {
						call();
					} else {
						alertMsg("设置失败！");
					}
				});
		};
		
		onSaveExternalItem = function(){
			onSave(onSubmitExternalItem, function() {
				go2Page('/presentation/external/preview.html','presentationId='+ $.trim($("#presentationId").val()));
			});
		};
		
		onPublishExternalItem = function(){
			onSave(onSubmitExternalItem, onPublish);
		};
		
		onGetYoukuUrl = function(url){
			return "http://player.youku.com/player.php/Type/Folder/Fid/4699493/Ob/1/Pt/51/sid/" + validate.trimYouku(url) + "/v.swf"
		};
		//返回上一步
		prevStep = function(){
			go2Page('/presentation/profile.html','presentationId='+$('#presentationId').val());
		};
	});