function uploadProperty(){
	this.isSuppotHtml5 = window.FileReader && window.FormData;
	this.imgUrl = ctx + '/js/upload/css/';
	this.imgType=['png','jpg','gif','tif'];
	this.pptType=['ppt','pptx'];
	this.videoType=['flv', 'avi', 'mpeg', 'wmv','mpg', 'mov', 'mp4', '3gp' ,'f4v','m4v','mkv'];
	this.audioType=['mp3', 'wav', 'wma' ];
//	this.packageType = ['rar','zip',"ppt","PPT","pptx","PPTX","xls","XLS","xlsx","XLSX","doc","DOC","docx","DOCX","txt","TXT","pdf","PDF"];
	this.packageType = ['rar','zip',"doc","DOC","docx","DOCX","pdf","PDF"];
	//this.loginUserId='';
}

function formatFileNameOriginal(name, fileNamelength) {
	   var tmpName = name.replace(/^.*[\/\\]/, '');
   return tmpName.length > fileNamelength ? tmpName.substr(0, fileNamelength) + '...': tmpName;
}

function formatFileSizeOriginal(bytes) {
	 if (typeof bytes !== 'number' || bytes === null) {
		 return bytes;
	 }
	 if (bytes >= 1073741824) {
		 return (bytes / 1073741824).toFixed(1) + ' GB';
	 }
	 if (bytes >= 1048576) {
		 return (bytes / 1048576).toFixed(1) + ' MB';
	 }
	 if (bytes >= 1024) {
		 return Math.round((bytes / 1024).toFixed(2)) + ' KB';
	 }
	 if (bytes == 0) {
	 	return 'unknown';
	 } else {
	 	return '1 KB';
	 }
}


function idCenter(){
	
	this.getRowId = function(id){
		return '#' + id;
	};
	
	this.getFilePercentId = function(id){
		return this.getRowId(id) + '_file_percent';
	};
	
	this.getLoadingBarId = function(id){
		return this.getRowId(id) + '_loading_bar';
	};
	
	this.getFileStatusId = function(id){
		return this.getRowId(id) + '_file_status';	
	};
	
	this.getFileStatusImgId = function(id){
		return this.getRowId(id) + '_file_status_img';
	};
	
	this.getFileProgressId = function(id){
		return this.getRowId(id) + '_file_progress';
	};
	
	this.getFileFileSizeId = function(id){
		return this.getRowId(id) + '_file_size';
	};
	
	this.getCancelIconId = function(id){
		return this.getRowId(id) + '_cancel_icon';
	};
	
	this.getDeleteIconId = function(id){
		return this.getRowId(id) + '_delete_icon';
	};
	
	this.getFlshId = function(extName){
	
		if (getWindowArea(extName) == 'slide') {
			return 'file_swf_slides';
		}else{
			return 'file_swf_multimedia';
		}
	};
	
	this.getRowId4NoHashkey = function(id){
		return id;
	};
	
	this.getLoadingBarId4NoHashkey = function(id){
		return id + '_loading_bar';
	};
	
	this.getFileStatusId4NoHashkey = function(id){
		return id + '_file_status';	
	};
	
	this.getFileStatusImgId4NoHashkey = function(id){
		return id + '_file_status_img';
	};
	
	this.getFileProgressId4NoHashkey = function(id){
		return id + '_file_progress';
	};
	
	this.getFileFileSizeId4NoHashkey = function(id){
		return id + '_file_size';
	};
	
	this.getCancelIconId4NoHashkey = function(id){
		return id + '_cancel_icon';
	};
	
	this.getDeleteIconId4NoHashkey = function(id){
		return id + '_delete_icon';
	};
	
	this.createIdFromReuseFlieNoHashkey = function(id){	 
		return id + 'reuse' + (new UUID().id);
	};
	
	this.getIdFromReuseFlieNoHashkey = function(id){	
		var id = id.split('reuse'); 
		return id[0];
	};
}





//
var uploadProcess = function(fileId,fileTypeExts,formData,uploadUrl,onSelectFunc,callBackFunc,auto,btnText,itemTemplate,btnClass,height,width){
	if(btnText==undefined){
		btnText='上传身份';
	};
	if(fileTypeExts==undefined){
		fileTypeExts='*.jpg;*.jpge;*.gif;*.png;*.tif;*.xls;*.xlsx;*.docx;*.doc;*.pdf;*.txt;';
	};
	
	if(itemTemplate == undefined){
		itemTemplate = '<div id="${fileID}" class="update-bar border-radius" style="margin-left:-165px;margin-top:1px;">\
						<div class="uploadify-progress-bar" ></div>\
						</div>';
	}
	
	$("#"+fileId).uploadify({  	
                'swf' : ctx+'/resources/js/libs/uploadify/uploadify.swf',  
                'uploader' : uploadUrl, // Handler Class
                'cancelImg' : ctx+'/resources/js/libs/uploadify/uploadify-cancel.png', // Cancel Button Image
                'fileObjName' : 'file',    
                'fileSizeLimit' : '51200',    
                'buttonText' :btnText,     
                'auto' : auto, // Upload automatically
                'multi' : false, 
                'width' : width | 90,
                'height' : height | 34,
                'buttonClass' : btnClass,
                /*'itemTemplate':'<div id="${fileID}" class="uploadify-queue-item">\
										<div class="cancel">\
										<a href="javascript:$(\'#${instanceID}\').uploadify(\'cancel\', \'${fileID}\')">X</a>\
									</div>\
									<span class="fileName">asdfasdfadsfasdfads${fileName} (${fileSize})</span><span class="data"></span>\
									<div class="uploadify-progress">\
										<div class="uploadify-progress-bar"><!--Progress Bar--></div>\
									</div>\
								</div>',*/
                /*'itemTemplate':'<div id="${fileID}" class="update-bar border-radius">\
                	<div class="uploadify-progress">\
					<div class="uploadify-progress-bar"><!--Progress Bar--></div>\
				</div>\
					</div>',*/
                'itemTemplate': itemTemplate,
                'debug':false,
                'successTimeout':300,  //超时时间设置  ,add by xuxiangdong  20180521
                'formData':formData,
                'removeComplete' : true,  
                'fileTypeDesc':'Format：',
                'fileTypeExts':fileTypeExts,
                'preventCaching':true,
                'overrideEvents': ['onSelectError','onDialogClose'], 
                'barId':'progressBar',
                'onSelect' : function(file) {
                		$("#"+fileId).uploadify('settings','formData',formData);
                		if(onSelectFunc!=undefined){
                			onSelectFunc(file.name);
                		}
                },
                'onSelectError':function(file, errorCode, errorMsg){
                	 switch(errorCode) {
                     case -110:
                    	 alertMsg("file ["+file.name+"] Size exceeds the system limit！");
                    	 break;
                     case -130:
                    	 alertMsg("file ["+file.name+"] type is not correct！");
                          break;
                 }
                },
               	'onFallback':function(){
               		//alertMsg($.i18n.prop('fe.warrning.support.flash'));
           		},
                'onUploadSuccess' : function(file, data, response) {
//                	debugger;
                	var jsonData = $.parseJSON(data);
                	if(jsonData.status==1){
                		if(callBackFunc!=undefined){
                			callBackFunc(jsonData.data,file);
                		}
                	}else{ 
                		alertMsg('Server file upload error');
                	}
                 },
                 'onUploadStart':function(){
                	 //debugger;
                	 $(".progress-bar").parent().show();
                	 $(".progress-bar").attr('aria-valuenow','0').css('width','0%');
                 },
                 'onUploadComplete':function(file, fileBytesLoaded, fileTotalBytes){
                	 $(".progress-bar").attr('aria-valuenow','100').css('width','100%');
                	 setInterval(function(){
                		 	$(".progress-bar").attr('aria-valuenow','0').css('width','0%');
                		 	$(".progress-bar").parent().hide();
                	 },3000);
                	 
                 },
                 'onUploadProgress' : function(file, fileBytesLoaded, fileTotalBytes) {
         			var lapsedBytes      = fileBytesLoaded - this.bytesLoaded;
         			this.bytesLoaded     = fileBytesLoaded;
         			var percentage       = Math.round(fileBytesLoaded / fileTotalBytes * 100);
         			
         			$(".progress-bar").attr('aria-valuenow',percentage).css('width',percentage+'%');
                 },
	           	 'onError': function(errorType) {
	           		alertMsg('The error was: ' + errorType);
	             }
                
            });  
};

//var uploadScromFile = function(fileId,fileTypeExts,formData,uploadUrl,onSelectFunc,callBackFunc,auto,btnText){
//	if(btnText==undefined){
//		btnText='课件上传';
//	};
//	if(fileTypeExts==undefined){
//		fileTypeExts='*.zip;*.rar;';
//	};
//	$("#"+fileId).uploadify({  	
//                'swf' : ctx+'/resources/js/libs/uploadify/uploadify.swf',  
//                'uploader' : uploadUrl, // Handler Class
//                'cancelImg' : ctx+'/resources/js/libs/uploadify/uploadify-cancel.png', // Cancel Button Image
//                'fileObjName' : 'file',    
//                'fileSizeLimit' : '51200',    
//                'buttonText' :btnText,     
//                'auto' : auto, // Upload automatically
//                'multi' : false, 
//                'width' : 90,
//                'height' : 32,
//                'itemTemplate': '<div id="${fileID}" class="update-bar border-radius" style="margin-left:-165px;margin-top:1px;">\
//					<div class="uploadify-progress-bar" ></div>\
//    				</div>',
//                'debug':false,
//                'formData':formData,
//                'removeComplete' : true,  
//                'fileTypeDesc':'Format：',
//                'fileTypeExts':fileTypeExts,
//                'preventCaching':true,
//                'overrideEvents': ['onSelectError','onDialogClose'], 
//                'barId':'progressBar',
//                'onSelect' : function(file) {
//                		$("#"+fileId).uploadify('settings','formData',formData);
//                		if(onSelectFunc!=undefined){
//                			onSelectFunc(file.name);
//                		}
//                },
//                'onSelectError':function(file, errorCode, errorMsg){
//                	 switch(errorCode) {
//                     case -110:
//                    	 alertMsg("file ["+file.name+"] Size exceeds the system limit！");
//                    	 break;
//                     case -130:
//                    	 alertMsg("file ["+file.name+"] type is not correct！");
//                          break;
//                 }
//                },
//               	'onFallback':function(){
//               		alertMsg($.i18n.prop('fe.warrning.support.flash'));
//           		},
//                'onUploadSuccess' : function(file, data, response) {
//                	var jsonData = $.parseJSON(data);
//                	if(jsonData.status==1){
//                		if(callBackFunc!=undefined){
//                			callBackFunc(jsonData.data);
//                		}
//                	}else{ 
//                		alertMsg(jsonData.exceptionMessage);
//                	}
//                 },
//                 'onUploadStart':function(){
//                	 //debugger;
//                	 $(".progress-bar").parent().show();
//                	 $(".progress-bar").attr('aria-valuenow','0').css('width','0%');
//                 },
//                 'onUploadComplete':function(file, fileBytesLoaded, fileTotalBytes){
//                	 $(".progress-bar").attr('aria-valuenow','100').css('width','100%');
//                	 setInterval(function(){
//                		 	$(".progress-bar").attr('aria-valuenow','0').css('width','0%');
//                		 	$(".progress-bar").parent().hide();
//                	 },3000);
//                	 
//                 },
//                 'onUploadProgress' : function(file, fileBytesLoaded, fileTotalBytes) {
//         			var lapsedBytes      = fileBytesLoaded - this.bytesLoaded;
//         			this.bytesLoaded     = fileBytesLoaded;
//         			var percentage       = Math.round(fileBytesLoaded / fileTotalBytes * 100);
//         			
//         			$(".progress-bar").attr('aria-valuenow',percentage).css('width',percentage+'%');
//                 },
//	           	 'onError': function(errorType) {
//	           		alertMsg('The error was: ' + errorType);
//	             }
//                
//            });  
//};