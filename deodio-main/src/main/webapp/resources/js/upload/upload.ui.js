function updateProgressing(id,bytesLoaded,bytesTotal){
	var loadedPercent = parseInt(bytesLoaded / bytesTotal * 100, 10) + 1;
	$("#"+id).parent().show();
	$("#"+id).attr('aria-valuenow',loadedPercent).css('width',loadedPercent + '%');
}

function updateProgressed(id){
	$("#"+id).attr('aria-valuenow','100').css('width','100%');
	setInterval(function(){
		$("#"+id).attr('aria-valuenow','0').css('width','0%');
		$("#"+id).parent().hide();
	},3000);
}


function setProgressBar(fileId, percent){
	var idCenterControl = new idCenter();
	if (percent >= 100) {
	   percent = 100;
	   $(idCenterControl.getDeleteIconId(fileId)).show();
	   $(idCenterControl.getCancelIconId(fileId)).show();
   }
   if (percent < 0) percent = 0;
   if (percent >= 0 && percent < 100) {
   	   $(idCenterControl.getDeleteIconId(fileId)).hide();
   	   $(idCenterControl.getCancelIconId(fileId)).show();
   }
   $(idCenterControl.getLoadingBarId(fileId)).attr('aria-valuenow',percent).css('width',percent+'%');//.css("background-position", position + "px 0");
   var percentTxt = '0';
   if(percent < 10)
	   percentTxt = '<span>&nbsp;&nbsp;&nbsp;'  + percent + '%&nbsp;</span>';
   else if(percent >= 10 && percent < 100)
	   percentTxt = '<span>&nbsp;&nbsp;'  + percent + '%&nbsp;</span>';
   else
	   percentTxt = '<span>&nbsp;'  + percent + '%&nbsp;</span>';
   $(idCenterControl.getFilePercentId(fileId)).html(percentTxt);
   $("#percent_"+fileId).css("width",percent+"%");
}

function getUploadArea(type) {
	var slideTypes = ['png', 'jpg', 'gif', 'tif', 'ppt', 'pptx', 'JPG'];
	var multimediaTypes = ['flv', 'avi', 'mpeg', 'wmv', 'mpg', 'mov', 'mp4', '3pg', 'mp3', 'wav', 'wma', 'f4v', 'm4v', '3gp','mkv'];
//	var packageTypes = ['rar','zip',"ppt","PPT","pptx","PPTX","xls","XLS","xlsx","XLSX","doc","DOC","docx","DOCX","txt","TXT","pdf","PDF"];
	var packageTypes = ['rar','zip',"doc","DOC","docx","DOCX","pdf","PDF"];
	if (jQuery.inArray(type.toLowerCase(), slideTypes) != -1) {
		return 'slides';
	} else if (jQuery.inArray(type.toLowerCase(), multimediaTypes) != -1) {
		return 'media';
	}else if (jQuery.inArray(type.toLowerCase(), packageTypes) != -1) {
		return 'package';
	} else {
		return '';
	} 
}

/**
 * conversionFlag shang
 */
function drawFileField(id, name, type, size, cancelHandle,conversionFlag){
	var multimediaTypes = ['flv', 'avi', 'mpeg', 'wmv', 'mpg', 'mov', 'mp4', '3pg', 'mp3', 'wav', 'wma', 'f4v', 'm4v', '3gp','mkv'];
	var area = getUploadArea(type);
	if (area == '') {
		return false;
	}
	var tempIcon = '';
	//上传文件格式判断 0:ppt;1图片;2压缩包;3:ppt,pdf等可以转为swf文件的文件
	var resultType = judgeFormat(type);
	if(resultType==0){
		tempIcon = '<span class="glyphicon glyphicon-duplicate pull-left f18"></span>';
	}else if(resultType==1){
		tempIcon = '<span class="glyphicon glyphicon-picture pull-left f18"></span>';
	}else if($.inArray(type, multimediaTypes)!=-1){
		tempIcon = '<span class="glyphicon glyphicon-facetime-video pull-left f18"></span>';
	}else if(resultType==2){
		tempIcon = '<span class="glyphicon glyphicon-facetime-video pull-left f18"></span>';
	}else if(resultType==3){
		tempIcon = '<span class="glyphicon glyphicon-picture pull-left f18"></span>';
	}
	
	var template = '';
	//0:上传;1:转换
	if(0==conversionFlag){
		if(resultType == 2||resultType == 3){
			template = '<li>'+tempIcon+
				'<div class="pull-left ml10" id=totalWidth_'+id+' style="width:960px;">'+
					'<div class="pull-left filename file_name" style = "width:960px"  title=""><em class="c_blue pull-right file_percent"></em></div>'+
					'<div class="load pull-left" style="width:100%"><div id=percent_'+id+' class="load_blue" style="width:0%"></div></div>'+
				'</div>'+
			'</li>';
		}else{
			template = '<li>'+tempIcon+
			'<div class="pull-left ml10" id=totalWidth_'+id+' style="width:430px;">'+
				'<div class="pull-left filename file_name" title=""><em class="c_blue pull-right file_percent"></em></div>'+
				'<div class="load pull-left" style="width: 430px;"><div id=percent_'+id+' class="load_blue" style="width:0%"></div></div>'+
			'</div>'+
		'</li>';
		}
	}else if(1==conversionFlag){
		template = '<li>' + tempIcon +
				'<div class="pull-left ml10" id=totalWidth_'+id+' style="width:430px;">'+
					'<div class="pull-left filename file_name" title=""><em class="c_green pull-right file_percent"></em></div>'+
					'<div class="load pull-left" style="width: 430px;"><div id=percent_'+id+' class="load_green" style="width:0%"></div></div>'+
				'</div>'+
			'</li>'
		$('#file_'+id).remove();
	}	
	var rows = $('#'+area+"_upload_init");
	this._upload_tbody = 'upload_tbody_' + area;
	//切换幻灯片背景
	if(area=="slides"){
		$("#pointButton").hide();
		$("#pointDiv").attr("class","con bg_fff");
	}else if(area=="media"){//切换多媒体背景
		$("#mediaButton").hide();
		$("#mediaDiv").attr("class","con bg_fff");
	}else if(area=="package"){
		$("#packageButton").hide();
		$("#packageDiv").attr("class","con bg_fff");
	}
	$('#' + this._upload_tbody).append('<div class="'+this._upload_tbody+'" id="file_' + id + '">' + template + '</div>');
    var newRow = $('#file_'+id);
	//set new row properties.
    var length = 20;
    if(resultType==2){
    	length = 30;
    }
	var fileName = formatFileNameOriginal(name, length);
	newRow.find('.file_name').append(fileName).attr("title", name);
	newRow.find('.progress-bar').attr("id", id + '_loading_bar');
	newRow.find('.cancel_icon').attr("id", id + '_cancel_icon');
	newRow.find('.delete_icon').attr("id", id + '_delete_icon');
	newRow.find('.file_percent').attr("id", id + '_file_percent');
	newRow.find('.file-text').attr("id", id + '_file-text');
	newRow.find('.cancel_icon').click(function(e) {
		cancelHandle();
	});
	newRow.find('.delete_icon').hide();

	var rowNum = rows.children().length;
	setProgressBar(id, 0);
}

//文件格式判断0:ppt;1图片;2压缩包;3:ppt,pdf等可以转为swf文件的文件
function judgeFormat(type){
	if('ppt'==type||'pptx'==type||'PPT'==type||'PPTX'==type){
		return 0;
	}else if('png'==type||'jpg'==type||'gif'==type||'tif'==type||'JPG'==type||'PNG'==type||'GIF'==type||'TIF'==type){
		return 1;
	}else if('zip'==type||'ZIP'==type||'rar'==type||'RAR'==type){
		return 2;
	}else if("ppt"==type||"PPT"==type||"pptx"==type||"PPTX"==type||"xls"==type||"XLS"==type||"xlsx"==type||"XLSX"==type||"doc"==type||"DOC"==type
			||"docx"==type||"DOCX"==type||"txt"==type||"TXT"==type||"pdf"==type||"PDF"==type){
		return 3;
	}
}