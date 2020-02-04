/*
 *  jQuery HTML5 File Upload 
 *  
 *  Ajax File Upload that use real xhr,
 *  built with getAsBinary, sendAsBinary, FormData, FileReader, ArrayBuffer, BlobBuilder and etc.
 *
 *  when browser do not support HTML5, we use flex program to execute file upload. so we can get 
 *  file name, file size and file upload progress.
 *  
 *  works in Ie7+ Firefox 3+ Safari 5 and higher
 *
 */

(function($) {
    UploadHandler = function(dropZone, upload_tbody) {

        /* define page variable*/
        this._dropZone_div = dropZone;

        this._upload_tbody = upload_tbody;
        
        this._upload_type = 0; // 0:normal 1:ppt 2:media 3:非标准课件包

        var uploadHandler = this;
        
        var _uploadProperty = new uploadProperty();
        
        var _idCenter = new idCenter();
        
        var convertSwfamount = 0;
        
        var alreadyConvertSwfamount = 0;
		
        this.validateFile = function(files) {
        
        	var errorInfo = '';
	        var slidTypes = _uploadProperty.imgType.concat(_uploadProperty.pptType);
	        var multimediaTypes = _uploadProperty.videoType.concat(_uploadProperty.audioType);
	        var totalSupportTypes = slidTypes.concat(multimediaTypes);
            var validateFlag = true;
            var tmpFiles = new Array();
            var unsupportedFileTypeFlg = 0;            

            $.each(files, function(index) {
                var file = files[index];
                var fileName = file.name;
                var removeFlag = true;
                var fileNameParts = fileName.split('.');
                var fileResult ;
                //压缩包和文件格式判断
                if(UploadHandler._upload_type == 3){
                	fileResult = jQuery.inArray((fileNameParts[fileNameParts.length - 1]).toLowerCase(), _uploadProperty.packageType);
                }else{
                	fileResult = jQuery.inArray((fileNameParts[fileNameParts.length - 1]).toLowerCase(), totalSupportTypes);
                }
                if (fileNameParts.length <= 1 ||fileResult  == -1) {
                    //errorInfo = "Knoodle supports PowerPoint files and most image, video and audio formats, but unfortunately not that one.Please visit <a onclick=\"openLink(\'http://knoodle.assistly.com/\')\">support</a> for a complete list of file types you can use.";
                   
                    if(UploadHandler._upload_type == 3){
                    	 errorInfo = '非标准类型课件支持PDF，MS Word文件格式，或将多个PDF，MS Word文件打包压缩成*.rar，*.zip格式，但是很不幸，不包括您所上传文件类型';
                    }else{
                    	 errorInfo = $.i18n.prop('fe.error.files.upload.type');
                    };
                    
                    
                	validateFlag = false;
                    removeFlag = false;
                    unsupportedFileTypeFlg = 1;
                } else if(fileNameParts[0].length > 100){
                	
                	//errorInfo = "File names cannot exceed 100 characters. Please rename using a shorter file name.";
                	errorInfo = $.i18n.prop('fe.error.files.name.length');
                    validateFlag = false;
                    removeFlag = false;
                }else if (new utils().isMoreThan1024M(file.size)) {
                	//errorInfo = 'One or more of the files you are attempting to drag into this window is larger than 500M and will not be uploaded.'
                	errorInfo=$.i18n.prop('fe.error.files.max.size');
                    validateFlag = false;
                    removeFlag = false;
                }
                if (removeFlag) {
                    tmpFiles.push(file);
                }
            });
            if (!validateFlag) {
            	//closeWelcomeAndTellYouWindows();
            	if (unsupportedFileTypeFlg) {
            		//alertMessageWarning(errorInfo,function(){;},400, 'Unsupported File Type');
            		alertMsg(errorInfo);
            	} else {
            		//alertMessageWarning(errorInfo);
            		alertMsg(errorInfo);
            	}
//                
            	  //dialog show...
                $('[role="dialog"]:visible').unbind('dragover').bind('dragover', function(e){
                	e.preventDefault();
                	e.stopPropagation();
                }).unbind('drop').bind('drop',function(e){
                	e.preventDefault();
                	e.stopPropagation();
                });
                
                //$("#upload_init_area").removeClass('backgroud_drag');
            }
            return tmpFiles;
        };

        /** preventing some standard events. 
         *  when drop event happen , file upload automatically.
         *  after user select files by file select dialog, change event happen and file upload automatically.
         */
        this.initSlidesPageEvent = function() {
        	UploadHandler._upload_type = 1;
            $("#" + this._dropZone_div).bind("drop",function(e) {
            	
            	var next = function (e) {
            		$("#slides_upload_init").find(".upload_init_area").hide();
            		$("#slides_upload_init").find(".upload_start_area").show();
                    uploadHandler.handleFiles(e, files);
            	}

                //we can get files user draging by dataTransfer object.
                if (true && _uploadProperty.isSuppotHtml5) {

                    //switch div. init div change to upload div.
                    var dataTransfer = e.originalEvent.dataTransfer;
                    var files = uploadHandler.validateFile(dataTransfer.files);
                    if (files.length > 0) {	 
                    	next(e);
	               	}
                    
                }
                e.preventDefault();
            });

        };
        
        //非标准课件包上传解压
        this.initPackagePageEvent = function(){
        	UploadHandler._upload_type = 3;
    		$("#" + this._dropZone_div).bind("drop",function(e) {
    			var next = function (e) {
    				$("#upload_tbody_package").show();
    				$("#saveSubmitSyncFiles").prop("disabled","disabled");
                    uploadHandler.handleFiles(e, files);
            	}
    			 //we can get files user draging by dataTransfer object.
                if (true && _uploadProperty.isSuppotHtml5) {
                    //switch div. init div change to upload div.
                    var dataTransfer = e.originalEvent.dataTransfer;
                    var files = uploadHandler.validateFile(dataTransfer.files);
                    if (files.length > 0) {	 
                    	next(e);
	               	}
                    
                }
                e.preventDefault();
            });
        }
        
        this.initMediaPageEvent = function() {
        	UploadHandler._upload_type = 2;
			$("#" + this._dropZone_div).bind("drop",function(e) {
				var next = function(e) {
					$("#media_upload_init").find(".upload_init_area").hide();
					$("#media_upload_init").find(".upload_start_area").show();
					uploadHandler.handleFiles(e, files);
				}

				// we can get files user draging by dataTransfer object.
				if (true && _uploadProperty.isSuppotHtml5) {

					// switch div. init div change to upload div.
					var dataTransfer = e.originalEvent.dataTransfer;
					var files = uploadHandler.validateFile(dataTransfer.files);

					if (files.length > 0) {
						next(e);
					}
				}
				e.preventDefault();
			});

      };

        this.handleFiles = function(e, files) {
            $.each(files,
            function(index) {
                //var fileId = new UUID().id;
            	var fileId = new UUID().id;
                var file = files[index];
                if(UploadHandler._upload_type==3){
                	convertSwfamount = convertSwfamount + 1;
                	$("#isRefreshFlag").val(1);
                }
                uploadHandler.handlerUploadFile(e, file, index, fileId);
            });
        };

        /**
        * handle file upload have three steps:
        * 1. bind progress event for every file which is going to upload.
        * 2. build a row append to table to display upload progress and status.
        * 3. upload file by HTML5 file object.
        */
        this.handlerUploadFile = function(e, file, index, fileId) {
            var xhr = new XMLHttpRequest();
            uploadHandler.initProgressEvent(e, file, index, fileId, xhr );
            //画进度
            uploadHandler.buildRow(e, file, index, fileId, xhr,0);
            //上传
            uploadHandler.uploadFile(e, file, index, xhr, fileId);
        };

        this.initProgressEvent = function(e, file, index, fileId, xhr) {
            xhr.upload.onprogress = function(e) {
                uploadHandler.setProgress(fileId, parseInt(e.loaded / e.total * 100, 10) + 1);
            };
            
            xhr.onreadystatechange = function(e) {
            	 $("body").mask();
                if (xhr.readyState == 4 && xhr.status == 200) {
                	$("#uploadMethod").val("1");
                    uploadHandler.setProgress(fileId, 100);
                    if(UploadHandler._upload_type==3){
                    	uploadHandler.decompressPackage(xhr.responseText,fileId);
                    	
                    }else{
                    	uploadHandler.buildRow(e, file, index, fileId, xhr,1);
                    	uploadHandler.convertFileFunc(xhr.responseText,fileId);
                    	 $("body").unmask();
                    }
                }
            };

        };
        
        //解压缩包
        this.decompressPackage = function(rtnTxt,fileId){
        	var _rtnObj = $.parseJSON(rtnTxt);
        	if(_rtnObj.status=="1"){
        		var packagePath = _rtnObj.packagePath;
        		$.ajax({
        		    type: 'POST',
        		    url: ctx+"/decompression/package.html",
        		    data: {packagePath:packagePath,fileId:fileId,presentationId:_rtnObj.presentationId,packageName:_rtnObj.packageName,
        		    	userId:_rtnObj.userId,packageId:_rtnObj.packageId} ,
        		    dataType: 'json',
        		    success: function(data){
        		    	if(data.status==1){
        		    		alreadyConvertSwfamount = alreadyConvertSwfamount + 1;
        		    		$("#totalWidth_"+fileId).after('<button type="button"  class="pull-right up_del_icon" onclick=onDelPackageFiles("'+fileId+'");>'+
                					'<span class="glyphicon glyphicon-trash"></span>'+
                					'</button>');
        		    		if(alreadyConvertSwfamount == convertSwfamount){
        		    			$("#saveSubmitSyncFiles").prop("disabled","");
        		    			$("#isRefreshFlag").val(0);
        		    		}
        		    		 $("body").unmask();
        		    	}
        		    }
        		});
        	}else{
        		
        	}
        }
        
        //多媒体和ppt文件转换
        this.convertFileFunc = function(rtnTxt,fileId){
        	var _rtnObj = $.parseJSON(rtnTxt);
        	if(_rtnObj.status=="1"){
        		if(_rtnObj.uploadType==1){
        			if("false"==_rtnObj.ifImage){
        				convert2png(_rtnObj.logicFilePath,_rtnObj.presentationId,_rtnObj.slideId,_rtnObj.userId);
        			}else{
        				uploadHandler.setProgress(fileId,100);
			    		$("#button_"+fileId).remove();
			    		$("#totalWidth_"+fileId).after('<button id="button_'+fileId+'" type="button" class="pull-right up_del_icon" onclick=delSyncSlide("'+_rtnObj.slideId+'","'+_rtnObj.presentationId+'");><span class="glyphicon glyphicon-trash"></span></button>');
			    		return ;
        			}
        		}else if(_rtnObj.uploadType==2){
        			uploadHandler.setProgress(fileId,100);
			    	$("#totalWidth_"+fileId).after('<button id="button_'+fileId+'" type="button" class="pull-right up_del_icon" onclick=delMedia("'+_rtnObj.fileId+'");><span class="glyphicon glyphicon-trash"></span></button>');
			    	
        			 //MP4文件不需要转换
        			//transcoding(_rtnObj.filePath,fileId,_rtnObj.presentationId,_rtnObj.id);
        		}
        	}else{
        		//alertMsg("fail")
        	}
        }
        
        //多媒体转换公共方法
        transcoding = function(filePath,fileId,presentationId,mediaId){
        	$.ajax({
    		    type: 'POST',
    		    url: ctx+"/convert/transcoding.html",
    		    data: {filePath:filePath,fileId:fileId,presentationId:presentationId} ,
    		    dataType: 'json',
    		    success: function(data){
    		    	var mProcess = data.data.mProcess;
    		    	var rProcess = data.data.rProcess;
    		    	//转换成功
    		    	if("-1"!=mProcess&&"-1"!=rProcess){
    		    		var completeProcess = 100*mProcess+100*rProcess;
    			    	if(completeProcess<100){
    			    		console.log("##############################继续查询");
    			    		uploadHandler.setProgress(fileId,Math.floor(completeProcess));
        			    	$("#totalWidth_"+fileId).after('<button id="button_'+fileId+'" type="button" class="pull-right up_del_icon" onclick=delMedia("'+mediaId+'","'+data.data.jobId+'","'+filePath+'","'+fileId+'");><span class="glyphicon glyphicon-trash"></span></button>');
        			    	//增加传入参数presentationId  add by xuxiangdong 20161117 start
    			    		runningFuncstion = setInterval(allRunFunction(filePath,data.data.jobId,fileId,mediaId,presentationId),5000);
    			    		//增加传入参数presentationId  add by xuxiangdong 20161117 start
    			    		//循环查询进度直到100%
    			    	}else{
    			    		console.log("##############################转换完成更新");
    			    		uploadHandler.setProgress(fileId,100);
    			    		$("#totalWidth_"+fileId).after('<button id="button_'+fileId+'" type="button" class="pull-right up_del_icon" onclick=delMedia("'+mediaId+'","'+data.data.jobId+'","","'+fileId+'");><span class="glyphicon glyphicon-trash"></span></button>');
    			    		return ;
    			    	}
    		    	}else{//转换失败
    		    		alertMsg("转换失败!");
    		    		$("#file_"+fileId).remove();
    		    		delMedia(mediaId,data.data.jobId,filePath,fileId);
    		    	}
    		    }
    		});
        }
        
        //ppt转换公共方法
        convert2png = function(filePath,presentationId,slideId,userId){
        	$("body").mask();
        	$.ajax({
    		    type: 'POST',
    		    url: ctx+"/convert/convert2png.html",
    		    data: {filePath:filePath,presentationId:presentationId,slideId:slideId,userId:userId,isSyncPoint:$("#isInitSyncPoint_syn").val(),isSlideShow:$("#isSlideShow_syn").val(),interval:$("#interval_syn").val()} ,
    		    dataType: 'json',
    		    success: function(data){
    		    	 $("body").unmask();
    		    	if(data.status==1){
    		    		//alertMsg("成功!");
    			    	uploadHandler.setProgress(slideId,100);
    		    		$("#button_"+slideId).remove();
    		    		$("#totalWidth_"+slideId).after('<button id="button_'+slideId+'" type="button" class="pull-right up_del_icon" onclick=delSyncSlide("'+slideId+'","'+presentationId+'");><span class="glyphicon glyphicon-trash"></span></button>');
    		    		return ;
    		    	}else if(data.status==0){
    		    		alertMsg("失败!");
    		    		//删除源文件和slides表数据
    		    		$("#file_"+slideId).remove();
    		    		var domCount = $("#upload_tbody_slides").find("li").length;
    		    		if("0"==domCount){
    						$("#pointButton").show();
    						$("#pointDiv").attr("class","con");
    						$("#upload_tbody_slides").hide();
    					}
    		    	}
    		    	
    		    }
    		});
        }
        
        //查询转换进度
        //增加传入参数presentationId  add by xuxiangdong 20161117 
        allRunFunction = function (filePath,jobId,fileId,id,presentationId) {
        	/*
    		$.ajax({
			    type: 'POST',
			    url: ctx+"/media/queryProcess.html",
			    //增加传入参数presentationId  add by xuxiangdong 20161117
			    data: {filePath:filePath,jobId:jobId,fileId:fileId,presentationId:presentationId} ,
			    dataType: 'json',
			    success: function(result){
			    	if("RUNNING"==result.data.mrStat){
			    		completeProcess = 100*result.data.mProcess+100*result.data.rProcess;
    			    	if(completeProcess<100){
    			    		$("#button_"+fileId).remove();
    			    		$("#totalWidth_"+fileId).after('<button id="button_'+fileId+'" type="button" class="pull-right up_del_icon" onclick=delMedia("'+id+'","'+jobId+'","'+filePath+'","'+fileId+'");><span class="glyphicon glyphicon-trash"></span></button>');
    			    		uploadHandler.setProgress(fileId,Math.floor(completeProcess));
    			    		//增加传入参数presentationId  add by xuxiangdong 20161117 start
    			    		runningFuncstion = setInterval(allRunFunction(filePath,jobId,fileId,id,presentationId),5000);
    			    		//增加传入参数presentationId  add by xuxiangdong 20161117 end
    			    	}else{
    			    		//更新转换后的文件格式
    			    		//updateTranscodingType(filePath,fileId);
    			    		//updateMediaInfo(result,fileId,id,jobId);
    			    	}
			    	}else if("SUCCEEDED"==result.data.mrStat){
			    		updateMediaInfo(result,fileId,id,jobId);
			    	}else if(null==result.data.mrStat){
			    		setTimeout('allRunFunction("'+filePath+'","'+jobId+'","'+fileId+'","'+id+'")',5000);
			    	}else{
			    		clearInterval(runningFuncstion);
			    	}
			    }
			});
			*/
		}
        
        //更新成功状态
        updateMediaInfo = function(result,fileId,id,jobId){
        	console.log("^^^^^^^^^^^^^^^^^^^执行状态更新");
    		//更新转换状态
    		var url="/presentation/updateMediaConvertStatus.html",paramData={
    				syncMediaId:id
			}
			postAjaxRequest(url, paramData, function(result){
				if(result.status == 1){
		    		uploadHandler.setProgress(fileId,100);
		    		clearInterval(runningFuncstion);
		    		$("#button_"+fileId).remove();
		    		$("#totalWidth_"+fileId).after('<button id="button_'+fileId+'" type="button" class="pull-right up_del_icon" onclick=delMedia("'+id+'","'+jobId+'","","'+fileId+'");><span class="glyphicon glyphicon-trash"></span></button>');
		    		return ;
				}
			});
    	}
      //增加传入参数presentationId  add by xuxiangdong 20161117 start
        refreshInfo = function(filePath,jobId,fileId,id,presentationId){
        	runningFuncstion = setInterval(allRunFunction(filePath,jobId,fileId,id,presentationId),5000);
    	}
      //增加传入参数presentationId  add by xuxiangdong 20161117 end
        this.buildRow = function(e, file, index, fileId, xhr,conversionFlag) {
         
         	var fileName = file.name;
            var fileNameParts = fileName.split('.');
   			drawFileField(fileId, fileName, fileNameParts[fileNameParts.length - 1], file.size, function(e) {
                uploadHandler.cancelHandler(e, file, index, fileId, xhr);
           	},conversionFlag);
	    };

	    //拖拽上传调用
        this.uploadFile = function(e, file, index, xhr, fileId) {
        	//处理拖拽和jquery file upload 同时调用问题
        	$("#uploadMethod").val("1");
        	/*
        	 * 原代码出现问题，拖拽图片到多媒体区，存储的表数据出现混乱   start
        	 * 
        	 * var fileType = '';
        	 * if('upload_tbody_slides' == upload_tbody){
        		fileType = 'slides';
        	}else if('upload_tbody_media' == upload_tbody){
        		fileType = 'media';
        	}
        	end
        	*/
        	var fileNameParts = file.name.split('.');
        	var fileType = getUploadArea(fileNameParts[fileNameParts.length - 1]);
        	var urlTemp = "";
        	if($("#presentationId").length>0){
        		urlTemp = '&presentationId='+$.trim($("#presentationId").val());
        	}
        	var urlStr = "uploadFiles";
        	if(UploadHandler._upload_type==3){
        		urlStr = "uploadPackage"
        	}
            xhr.open('POST', ctx + '/commons/'+urlStr+'.html?userId='+$.cookie('CUID')+'&accountId='+$.cookie('CAID')+urlTemp+'&fileId=' + fileId+'&fileType='+fileType, true);
            xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
            var formData = new FormData();
            formData.append("uploadFile", file);
            xhr.send(formData);
        };

        /**
        * supporting HTML5 brower, xhr.abort work well.
        * in another brower like IE, it do not support HTML5, cancel file upload by call flex f.cancel() function.
        */
        this.cancelHandler = function(e, file, index, fileId, xhr) {
            if (xhr != null) {
                var readyState = xhr.readyState;
                xhr.abort();
            } else {
            	var fileProgressId = _idCenter.getFileProgressId(fileId);
				var tooltip = $(fileProgressId).attr('title');
				var extName = '';
				if (tooltip == '') {
					var text = $(fileProgressId).text();
					extName = text.substring(text.lastIndexOf('.') + 1);
				} else {					
					extName = tooltip.substring(tooltip.lastIndexOf('.') + 1)
				} 
				
                document.getElementById(_idCenter.getFlshId(extName)).cancelUpload(fileId);
            }
            listener.removeFileFromUploadingAndConvertSuccQueue(fileId);
            listener.deleteRowForCancel(fileId);
        };

        this.setProgress = function(fileId, percent) {
            setProgressBar(fileId, percent);
        };
    };

    var handler = null;
    this.buildRowsForNotSupportHtml5 = function(data, uploadType) {
        var filesVarray = data.split("::");
        if (uploadType == 'slides') {
            handler = new UploadHandler('slides_upload_init', 'upload_tbody_slides');
        } else {
            handler = new UploadHandler('media_upload_init', 'upload_tbody_media');
        }
        for (i = 0; i < filesVarray.length; i++) {
            if (filesVarray[i] == '') {
                break;
            }
            var fileVarray = filesVarray[i].split(":");
            if (fileVarray.length = 3) {
                var fileName = fileVarray[0];
                var fileSize = Number(fileVarray[1]);
                var fileId = fileVarray[2];
                FileObject = function(fileName, fileSize) {
                    this.name = fileName;
                    this.size = fileSize;
                }
                if(fileName.length > 100){
                	
                	//alertMessageWarning("File names cannot exceed 100 characters. Please rename using a shorter file name.");
                	alertMsg($.i18n.prop('fe.error.files.name.length'));
                    //$("#upload_init_area").removeClass('backgroud_drag');
                	
                }else if (new utils().isMoreThan1024M(fileSize)) {
                    //alertMessageWarning("One or more of the files you are attempting to upload is larger than 500M and will not be uploaded.");
                	alertMsg($.i18n.prop('fe.error.files.max.size'));
                    //$("#upload_init_area").removeClass('backgroud_drag');
                    
                } else {

                    if (uploadType == 'slides') {
                        $('#slides_upload_init').find('.upload_init_area').hide();
                        $('#slides_upload_init').find('.upload_start_area').show();
                    } else {
                    	//添加隐藏右侧上传button样式
                    	$('#media_upload_init').find('.upload_init_area').hide();
                        $('#media_upload_init').find('.upload_start_area').show();
                    }
                    handler.buildRow(null, new FileObject(fileName, fileSize), null, fileId, null,0);
                }

            }

        }
    };

    /**
   * flex call this function to update progress real time .
   */
    this.updataProgressing = function(uuid, bytesLoaded, bytesTotal) {
        handler.setProgress(uuid, parseInt(bytesLoaded / bytesTotal * 100, 10) + 1);
    };
  /**
   * flex call this function to notify progress upload has finished.'\
   * type:slides,media
   */
    this.updataProgressed = function(uuid,type) {
        handler.setProgress(uuid, 100);
        console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$上传完成");
        //弹出窗口选择上传多媒体添加转换方法和进度条
        //ppt转换信息查询
        if("slides" == type){
        	$.ajax({
    		    type: 'POST',
    		    url: ctx+"/presentation/convert_slides_info.html",
    		    data: {slideId:uuid} ,
    		    dataType: 'json',
    		    success: function(data){
    		    	if(data.status==1){
    		    		var slides = data.data;
    		    		var fileType = slides.slideExt;
    		    		//这里的xhr可能会存在问题
        				drawFileField(slides.id, slides.slideOriginalName, slides.slideExt, slides.slideSize, function(e) {
        	                uploadHandler.cancelHandler(e, file, index, slides.id, xhr);
        	           	},1);
    		    		if(fileType=="ppt"||fileType=="pptx"||fileType=="PPT"||fileType=="PPTX"){
    		    			var xhr = new XMLHttpRequest();
            				convert2png((slides.slideDir+"/"+slides.slideName),$("#presentationId").val(),slides.id,slides.createId);
    		    		}else{
    		    			handler.setProgress(slides.id,100);
    			    		$("#button_"+slides.id).remove();
    			    		$("#totalWidth_"+slides.id).after('<button id="button_'+slides.id+'" type="button" class="pull-right up_del_icon" onclick=delSyncSlide("'+slides.id+'","'+$("#presentationId").val()+'");><span class="glyphicon glyphicon-trash"></span></button>');
    			    		return ;
            			}
    		    	}else if(data.status==0){}
    		    }
    		});
        }
        //多媒体转换信息查询
        else if("media" == type){
        	console.log("*****************************执行转换");
        	$.ajax({
    		    type: 'POST',
    		    url: ctx+"/presentation/convert_medias_info.html",
    		    data: {mediaId:uuid,presentationId:$("#presentationId").val()} ,
    		    dataType: 'json',
    		    success: function(data){
    		    	if(data.status==1){
    		    		var media = data.data;
    		    		$("#file_"+media.mediaId).remove();
    		    		drawFileField(media.media_id, media.media_original_name, media.media_ext, media.media_size, function(e) {
        	                uploadHandler.cancelHandler(e, file, index, media.media_id, xhr);
        	           	},1);
    		    		var mediaDir = media.media_dir.split("/"+media.media_name)[0]+"."+media.media_original_name.split(".")[1];
    		    		//add by p0085398  阿里云视频点播适配修改 start
    		    		if(openAliyun == 'true'){
    		    			mediaDir = media.media_dir;
    		    		}
    		    		//end
    		    		transcoding(mediaDir,media.media_id,$("#presentationId").val(),media.id);
    		    	}else if(data.status==0){}
    		    }
    		});
        }
        
    };

    /*utility function define.*/
    var utils = function() {
        this.formatFileName = function(name, fileNamelength) {
        
            return formatFileNameOriginal(name, fileNamelength);
        };

        this.isXHRUploadCapable = function() {
            return (window.FileReader && window.FormData);
        };

        this.formatFileSize = function(bytes) {
            
            return formatFileSizeOriginal(bytes);
        };

        this.isMoreThan1024M = function(bytes) {

            return (bytes / 1000000).toFixed(1) > 1024 ? true: false;
        };

    };

    //deal with IE
} (jQuery));

var text;