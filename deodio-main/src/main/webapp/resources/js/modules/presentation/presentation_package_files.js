	define(["jquery","utils.cookie","jquery.dot","utils.list","pagination","utils","jquery.base","jquery.validate","jquery.scrolltofixed","ueditor","jquery.mCustomScrollbar",
			"ueditor.config","upload.ui","upload.common","upload.handler","bootstrap.select"], function($,cookie,doT,list,paging) {
		
		
		var msg="正在解压缩，离开页面可能导致数据丢失，确定离开？";
		var init = function(){	
			
			var packageHandler = new UploadHandler('package_upload_init', 'upload_tbody_package');
			packageHandler.initPackagePageEvent();
			window.onbeforeunload=function(event){
			      event=event || window.event;
			      if($("#isRefreshFlag").val()!=0){
			    	  event.returnValue=msg;
			    	  return msg; 
			      }
			}
			
			list.onFixedItems();
		};
		
		init();
		
		onSavePackageItemToProfile = function(){
			
		
			
			
			onSave(submitPackageDefaultProfile,function(){
				go2Page('/presentation/package/profile.html','presentationId='+$('#presentationId').val());
			});
		}
		
		onSavePackageItem = function(){
			onSave(submitPackageDefaultProfile,function(){
				go2Page('/presentation/package/preview.html','presentationId='+$('#presentationId').val());
			});
		};	
		
		onPublishPackageItem = function(){
			onSave(submitPackageDefaultProfile,onPublish);
		};
		
		//提交压缩包默认设置
		var  submitPackageDefaultProfile = function(callBack){
			if($("._upload_tbody_package").size()==0){
				alertMsg("请上传文件！");
				return;
			}
			
			var url="/presentation/package/sumbit_profile.html",data={
					presentationId:$.trim($('#presentationId').val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					callBack();
				}else{
					alertMsg("设置失败！");
				}
			});
		}
		
		//删除课件包
		onDelPackageFiles = function(presentationFilesId,loaclId){
			var url="/presentation/package/del_package_files.html",data={
					presentationFilesId : presentationFilesId,
					presentationId:$.trim($('#presentationId').val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					//alertMsg("删除成功!");
					
					if(loaclId!=undefined){
						$("#file_"+loaclId).remove();
					}else{
						$("#file_"+presentationFilesId).remove();
					}
					
					
					var domCount = $("#upload_tbody_package").find("li").length;
					if("0"==domCount){
						$("#packageButton").show();
						$("#packageDiv").attr("class","con");
						$("#upload_tbody_package").hide();
					}
				}else{
					alertMsg("删除失败！");
				}
			});
		}
		localFile = function(){
			$("#uploadMethod").val("0");
			drapType=0;
		};
		
		goBack = function(){
			 go2Page('/presentation/profile.html','presentationId='+$.trim($("#presentationId").val())); 
		}
		

	});