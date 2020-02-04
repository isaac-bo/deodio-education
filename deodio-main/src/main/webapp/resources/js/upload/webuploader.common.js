define(['jquery','webuploader'], function($,WebUploader,extensions) {
	

	
	uploadInit ={
			webUploaderLoaction:function(btnId,uploadUrl,extensions,mimeTypes,auto,fileQueued,uploadProgress,uploadSuccess,acceptError){
				uploader = WebUploader.create({
					// swf文件路径
				    swf: ctx+'/resources/js/libs/webuploader/Uploader.swf',

				    // 文件接收服务端。
				    server: ctx + uploadUrl,

				    // 选择文件的按钮。可选。
				    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
				    pick: '#'+btnId,
				    multiple:false,	
				    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
				    resize: false,
				    accept: {  
				    	   title: 'intoTypes',
				    	   	extensions: extensions,
			                mimeTypes: mimeTypes
				    },
				    auto:auto,
				    duplicate:true
				    
				});
				
				uploader.on('uploadProgress', function( file, percentage ) {
					uploadProgress(file,percentage);
				});
				uploader.on('fileQueued', function( file) {
					fileQueued(file);
				});
				uploader.on( 'uploadError', function( file ) {
					$("#file_"+file.id).remove()
					alertMsg("文件上传失败！");
				});
				uploader.on( 'uploadSuccess', function(file,data) {
					uploadSuccess(file,data);
				});
				uploader.on('error',function(code){
					if(code=='Q_TYPE_DENIED'){
						alertMsg(acceptError != undefined ? acceptError : "请上传指定类型文件");
					};
				})
				
			}
	}

	
	
	return {
		uploadInit:uploadInit
	};
});