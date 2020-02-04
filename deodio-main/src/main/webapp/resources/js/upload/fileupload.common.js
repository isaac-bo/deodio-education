
var uploadFileJqProcess = function(btnId,url,formData,maxNumberOfFiles,acceptFileTypes,autoUpload,callBackDone,addFun,progressall){

	$('#'+btnId).fileupload({
	        dataType: 'json',
	        url: ctx+url,
	        formData:formData,
	        maxNumberOfFiles: maxNumberOfFiles,//最大上传文件数目
	        acceptFileTypes: acceptFileTypes,//文件格式限制
	        autoUpload: autoUpload,//是否自动上传
	        done: function (e, data) {
	        	$("#progressBar .bar").css('width','0%');
	        	callBackDone(e,data);
	        },
	        progressall:function(e,data){
	        	
	        	if(progressall!=undefined){
	        		progressall(data);
	        	}else{
	        	   	var progress = parseInt(data.loaded / data.total * 100, 10);
		        	console.log(progress)
		        	$("#progressBar .bar").css('width',progress+'%');
	        	}
	        },
	        change:function(e,data){
	        	
	        	addFun(e,data);	        		
	        },
	        processfail:function (e, data){
	        	 var currentFile = data.files[data.index];
	        	    if (data.files.error && currentFile.error) {
	        	 
	        	        console.log(currentFile.error);
	        	    }
	        }
	        
	    });
	
}	