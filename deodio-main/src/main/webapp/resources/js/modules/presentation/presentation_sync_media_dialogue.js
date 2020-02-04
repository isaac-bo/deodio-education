define(["jquery","jquery.dot","pagination","webuploader.common","utils","jquery.base"], function($,doT,paging,wbup) {
	
	var init = function(){
//		createSWFObject4LastestVersion("/DeodioUploadMFile.swf", "file_swf_multimedia", "158", "30", 
		//{requestUrl:ctx+ '/commons/uploadFiles.html?userId='+$.cookie('CUID')+'%26accountId='+$.cookie('CAID')+'%26presentationId='+$.trim($("#presentationId").val()),uploadType:'media',quality:'high',wmode:'opaque'}, { allowFullScreen:'true',wmode:'transparent'}, {},function(){
//			$("#file_swf_multimedia").css('margin-top','-20px');
//		});
//		
		
	var uploadUrl = '/commons/uploadFiles.html?userId='+$.cookie('CUID')+'&accountId='+$.cookie('CAID')+'&presentationId='+$.trim($("#presentationId").val())+"&fileType=media&quality=high&wmode=transparent&allowFullScreen=true";
		
		
		wbup.uploadInit.webUploaderLoaction("filePickerMedia",uploadUrl,'mp4,MP4','.mp4,.MP4',true,function(file){
			$("#mediaButton").hide();
			var fileId = file.id;
			
			var fileName = file.name;
			 $("body").mask();
			
		var progressallHtml='<div class="upload_tbody_media" id="file_'+fileId+'">'+	
				'<li>'+
					'<span class="glyphicon glyphicon-duplicate pull-left f18"></span>'+	
				'<div class="pull-left ml10" id="totalWidth_'+fileId+'" style="width: 430px;">'+	
					'<div class="pull-left filename file_name" >'+	
						'<em id="percent_load_'+fileId+'" class="c_green pull-right file_percent"></em>'+fileName+	
					'</div>'+	
					'<div class="load pull-left" style="width: 440px;">'+	
						'<div id="percent_'+fileId+'" class="load_green" ></div>'+	
					'</div>'+	
				'</div>'+	
				'</li>'+	
		   '</div>';	
			
			$("#upload_tbody_media").show().append(progressallHtml);
			
			},function(file,percentage){
				$("#percent_load_"+file.id).html(percentage * 100 +"%");
				$("#percent_"+file.id).css('width',percentage * 100 +'%');
			},function(file,data){
				var _presentationId = $("#presentationId").val();
				var deleteHtml ='<button type="button" class="pull-right up_del_icon" onclick="delMedia(\''+data.fileId+'\',\''+file.id+'\');">'+	
								'<span class="glyphicon glyphicon-trash"></span></button>';	
				$("#totalWidth_"+file.id).after(deleteHtml);
				 $("body").unmask();
			},'请上传多媒体课件！');	
		
		
		
	}
	
	init();
	
	//系统多媒体库
	sysMediaFactory = function(){
		loadMediaDataList(1);
	}
	loadMediaDataList = function(pageNo){
		var url="/presentation/queryMediaFactory.html",data={
				pageNo:pageNo,
				accountId:$.trim($.cookie('CAID')),
				presentationId:$.trim($("#presentationId").val())
		}
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				var template = doT.template($("#mediaFactoryText").text());
				popUpWindow(template({"dataLength":result.data.dataList.length}), "系统多媒体库",750,500);
				paging.pageTemplateDiv(result,"mediaFileList","mediaFactory_data_template","media_data_page_panel","loadMediaDataList");
				customInput("mediaRemember",true);
				$(".pagination").parent().removeClass("pull-center").addClass("pull-left");
				$(".pagination").css('margin-top','0');
				if(result.data.dataList.length<=0){
					$("#mediasFileListPanel").hide();
				}else{
					$("#mediasFileListPanel").show();
				}
				drawingMediaChecked();
			}else{
				alertMsg("获取系统多媒体库失败！");
			}
		});
	}
	
	//根据文件名模糊查询媒体库
	queryMediaList = function(){
		queryMediaDataList(1);
	}
	
	//查询多媒体库
	queryMediaDataList = function(pageNo){
		$("#one").prop("checked",""); 
		$("#one").next().attr("class","checkbox-2");
		$("#selectedMediaList").val("{}");
		var url="/presentation/queryMediaInfoList.html",data={
				pageNo:pageNo,
				accountId:$.trim($.cookie('CAID')),
				queryMediaInfo:$.trim($("#queryMediaInfo").val()),
				presentationId:$.trim($("#presentationId").val())
		}
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				paging.pageTemplateDiv(result,"mediaFileList","mediaFactory_data_template","media_data_page_panel","queryMediaDataList");
				customInput("mediaRemember",true);
				$(".pagination").parent().removeClass("pull-center").addClass("pull-left");
				$(".pagination").css('margin-top','0');
				if(result.data.dataList.length<=0){
					$("#mediasFileListPanel").hide();
				}else{
					$("#mediasFileListPanel").show();
				}
				drawingMediaChecked();
			}else{
				alertMsg("获取系统多媒体库失败！");
			}
		});
	
	}
	
	//从媒体库添加添加多媒体保存
	saveMediaByFactory = function(){
		var sysMediaList = [];
		var html = '';
		var selectedArr;
		var selectedText = $("#selectedMediaList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		selectedArr.forEach(function (item) {
			var selectedNum = item.selectedNum;
			if(""!=selectedNum){
				var obj = new Object();
            	var id = item.mediaId;
            	obj.id = id;
            	obj.presentation_id = item.presentation_id;
            	var mediaExt = item.mediaExt;
            	var imgTemp = "";
            	if("mp3"==mediaExt){
            		imgTemp = "music";
            	}else{
            		imgTemp = "facetime-video";
            	}
				html +='<div class="upload_tbody_media" id=file_'+id+'>'+
	                     	'<li> <span class="glyphicon glyphicon-facetime-video pull-left f18"></span>'+
							'<div class="pull-left ml10" id=totalWidth_'+id+' style="width:430px;">'+
								'<div class="pull-left filename file_name" title=""><em class="c_green pull-right file_percent">100%</em>'+item.mediaOriginalName+'</div>'+
								'<div class="load pull-left style="width:440px;""><div id=percent_'+id+' class="load_green" style="width:100%"></div></div>'+
							'</div>'+
							'<button type="button" class="pull-right up_del_icon" onclick=delMedia("'+id+'");><span class="glyphicon glyphicon-trash"></span></button>'+
						'</li>'+
	                 '</div>';
				sysMediaList.push(obj);
			}
        });
		var url="/presentation/saveSysMedia.html",data={
				sysMediaJson : JSON.stringify(sysMediaList),
				presentationId:$.trim($('#presentationId').val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				//$("#upload_tbody_media").append(html);
				$("#mediaButton").hide();
				$("#mediaDiv").attr("class","con bg_fff");
				$("#upload_tbody_media").append(html);
				$("#upload_tbody_media").show();
				$("#selectedMediaList").val("{}");
				closePopWindow();
			}else{
				alertMsg("添加失败！");
			}
		});
	}
	
	//全选/反选(slides)
	selectAllMedia = function(obj){
		var selectedArr;
		var selectedText = $("#selectedMediaList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		var selectFlag = $(obj).is(':checked');
		if(selectFlag){
			$("input[name^='mediaRemember']").prop("checked","checked"); 
			$("input[name^='mediaRemember']").next().attr("class","checkbox-2 checked");
			$('#mediaFileList').find('tr').each(function (x,tr) {
				$(this).find('div input').each(function (y,td) {
	            	//当前页选中行
					var selectedNum = $(td).attr("lang");
					if(undefined==selectedNum){
						selectedNum ="";
					}
					var objJson = new Object();
					objJson.pageNum = $('.currPage').children().text();
					objJson.selectedNum = selectedNum;
					//objJson.id = $("#slideId"+selectedNum).text();
					objJson.mediaId = $.trim($("#mediaId"+selectedNum).text());
					objJson.mediaExt = $("#mediaExt"+selectedNum).text();
					objJson.presentation_id = $.trim($('#presentationId').val());
					objJson.mediaOriginalName = $.trim($("#mediaOriginalName"+selectedNum).text());
					selectedArr.push(objJson);
				})
        	});
			$("#selectedMediaList").val(JSON.stringify(selectedArr));
		}else{
			$("input[name^='mediaRemember']").prop("checked",""); 
			$("input[name^='mediaRemember']").next().attr("class","checkbox-2");
			$("#selectedMediaList").val("{}");
		}
	}
	
	//复选框勾选和勾掉数据存储（medias）
	selectMediaOrUnSelect = function(obj){
		//获取已选中的数据
		var selectedArr;
		var selectedText = $("#selectedMediaList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		var checkedLength = $('tbody').find('tr').find('div input').not("input:checked").length;
		var objJson = new Object();
		if($(obj).is(':checked')){
			//当前页选中行
			var selectedNum = $(obj).attr("lang");
			objJson.pageNum = $('.currPage').children().text();
			objJson.selectedNum = selectedNum;
			objJson.mediaId = $.trim($("#mediaId"+selectedNum).text());
			objJson.mediaExt = $("#mediaExt"+selectedNum).text();
			objJson.presentation_id = $.trim($('#presentationId').val());
			objJson.mediaOriginalName = $.trim($("#mediaOriginalName"+selectedNum).text());
			selectedArr.push(objJson);
			if(checkedLength == 0){
				$("#one").prop("checked","checked"); 
				$("#one").next().attr("class","checkbox-2 checked");
			}
			$("#selectedMediaList").val(JSON.stringify(selectedArr));
		}else{
			var selectedNum = $(obj).attr("lang");
			objJson.pageNum = $('.currPage').children().text();
			objJson.selectedNum = selectedNum;
			objJson.mediaId = $("#mediaId"+selectedNum).text();
			objJson.mediaExt = $("#mediaExt"+selectedNum).text();
			objJson.presentation_id = $.trim($('#presentationId').val());
			objJson.mediaOriginalName = $.trim($("#mediaOriginalName"+selectedNum).text());
			var index ;
			$.each(selectedArr, function(i, item){      
				if(item.selectedNum == objJson.selectedNum){
					index = i;
				}      
			});  
			selectedArr.splice(index, 1);
			if(checkedLength < 6){
				if($("#one").prop("checked")){
					$.each(selectedArr, function(i, item){ 
						if(item.selectedNum == ""){
							index = i;
						}      
					});
					selectedArr.splice(index, 1);
				}
				$("#one").prop("checked",""); 
				$("#one").next().attr("class","checkbox-2");
			}
			$("#selectedMediaList").val(JSON.stringify(selectedArr));
		}
	}
	
	drawingMediaChecked =function(){
		var selectedArr;
		var selectedText = $("#selectedMediaList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			//当前页码获取
			var pageNum = $('.currPage').children().text();
			//选中的数据获取
			selectedArr = JSON.parse(selectedText);
			selectedArr.forEach(function(item){  
			    if(item.pageNum == pageNum){
			    	//勾选并添加样式
			    	$("#one"+(item.selectedNum)).prop("checked","checked");
			    	$("#one"+(item.selectedNum)).next().attr("class","checkbox-2 checked");
			    	var checkedLength = $('tbody').find('tr').find('div input').not("input:checked").length;
			    	if(checkedLength == 0){
						$("#one").prop("checked","checked"); 
						$("#one").next().attr("class","checkbox-2 checked");
					}
			    }
			})  
		}
	 }
	
	//关闭
	closeMediaFactory = function(){
		closePopWindow();
		$("#selectedMediaList").val("{}");
	}
});
