define(["jquery","jquery.dot","pagination","webuploader.common","utils","jquery.base"], function($,doT,paging,wbup) {
	
	var init = function(){
//		createSWFObject4LastestVersion("/DeodioUploadMFile.swf", "file_swf_slides", "158", "30", 
//				{requestUrl:ctx+ '/commons/uploadFiles.html?userId='+$.cookie('CUID')+'%26accountId='+$.cookie('CAID')+'%26presentationId='+$.trim($("#presentationId").val()),uploadType:'slides',quality:'high',wmode:'opaque'},
//				{ allowFullScreen:'true',wmode:'transparent'}, {},function(){
//			$("#file_swf_slides").css('margin-top','-20px');
//		});
//		
//		
	
		var uploadUrl = '/commons/uploadFiles.html?userId='+$.cookie('CUID')+'&accountId='+$.cookie('CAID')+'&presentationId='+$.trim($("#presentationId").val())+"&fileType=slides&quality=high&wmode=opaque&";
		
		
		wbup.uploadInit.webUploaderLoaction("filePicker",uploadUrl,'ppt,pptx,png,jpeg,jpg','.ppt,.pptx,.png,.jpeg,.jpg',true,function(file){
			$("#pointButton").hide();
			var fileId = file.id;
			
			var fileName = file.name;
			
			
		var progressallHtml='<div class="upload_tbody_slides"  id="file_'+fileId+'">'+	
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
			$("#upload_tbody_slides").show().append(progressallHtml);
			},function(file,percentage){
				$("#percent_load_"+file.id).html(percentage * 100 +"%");
				$("#percent_"+file.id).css('width',percentage * 100 +'%');
			},function(file,data){
				var deleteHtml ='<button type="button" class="pull-right up_del_icon" onclick="delSyncSlide(\''+data.slideId+'\',\''+file.id+'\');">'+	
				'<span class="glyphicon glyphicon-trash"></span></button>';	
				$("#totalWidth_"+file.id).after(deleteHtml);
				
				
				postAjaxRequest("/convert/convert2png.html", {filePath:data.logicFilePath,presentationId:data.presentationId,slideId:data.slideId,userId:$.cookie('CUID'),
    		    	isSyncPoint:$("#isInitSyncPoint_syn").val(),isSlideShow:$("#isSlideShow_syn").val(),interval:$("#interval_syn").val()}, function(result){
					if(result.status == 1){
						alertMsg("上传&转换 成功");
					}else{
						alertMsg("PPT转换失败");
					}
				});
				
			},'自主设置课件包支持MS PowerPoint和各种图片（slide）、多媒体（media）格式的文件，但是很不幸，不包括您所上传的文件类型');
	
		
	}
	
	init();
	
	//幻灯片系统库查询
	sysSlideFactory = function(){
		loadSlideDataList(1);
	};
	loadSlideDataList = function(pageNo){
		var url="/presentation/querySlideFactory.html",data={
				pageNo:pageNo,
				accountId:$.trim($.cookie('CAID')),
				presentationId:$.trim($("#presentationId").val())
		}
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				var template = doT.template($("#slideFactoryText").text());
				popUpWindow(template({"dataLength":result.data.dataList.length}), "系统幻灯片库",750,500);
				paging.pageTemplateDiv(result,"slideFileList","slideFactory_data_template","slide_data_page_panel","loadSlideDataList");
				$(".pagination").parent().removeClass("pull-center").addClass("pull-left");
				$(".pagination").css('margin-top','0');
				customInput("slideRemember",true);
				if(result.data.dataList.length<=0){
					$("#slidesFileListPanel").hide();
				}else{
					$("#slidesFileListPanel").show();
				}
				drawingChecked();
			}else{
				alertMsg("获取系统幻灯片库失败！");
			}
		});
	}
	
	//已选的复选框数据翻页恢复
	drawingChecked = function(){
		var selectedArr;
		var selectedText = $("#selectedList").val();
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
	
	//全选/反选(slides)
	selectAll = function(obj){
		var selectedArr;
		var selectedText = $("#selectedList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		var selectFlag = $(obj).is(':checked');
		if(selectFlag){
			$("input[name^='slideRemember']").prop("checked","checked"); 
			$("input[name^='slideRemember']").next().attr("class","checkbox-2 checked");
			$('#slideFileList').find('tr').each(function (x,tr) {
				$(this).find('div input').each(function (y,td) {
	            	//当前页选中行
					var selectedNum = $(td).attr("lang");
					if(undefined==selectedNum){
						selectedNum ="";
					}
					var objJson = new Object();
					objJson.pageNum = $('.currPage').children().text();
					objJson.selectedNum = selectedNum;
					objJson.slideId = $("#slideId"+selectedNum).text();
					objJson.slideExt = $("#slideExt"+selectedNum).text();
					objJson.presentation_id = $.trim($('#presentationId').val());
					objJson.slideOriginalName = $.trim($("#slideOriginalName"+selectedNum).text());
					selectedArr.push(objJson);
				})
        	});
			$("#selectedList").val(JSON.stringify(selectedArr));
		}else{
			$("input[name^='slideRemember']").prop("checked",""); 
			$("input[name^='slideRemember']").next().attr("class","checkbox-2");
			$("#selectedList").val("{}");
		}
	}
	
	//复选框勾选和勾掉数据存储（slides）
	selectOrUnSelect = function(obj){
		//获取已选中的数据
		var selectedArr;
		var selectedText = $("#selectedList").val();
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
			objJson.slideId = $("#slideId"+selectedNum).text();
			objJson.slideExt = $("#slideExt"+selectedNum).text();
			objJson.presentation_id = $.trim($('#presentationId').val());
			objJson.slideOriginalName = $.trim($("#slideOriginalName"+selectedNum).text());
			selectedArr.push(objJson);
			if(checkedLength == 0){
				$("#one").prop("checked","checked"); 
				$("#one").next().attr("class","checkbox-2 checked");
			}
			$("#selectedList").val(JSON.stringify(selectedArr));
		}else{
			var selectedNum = $(obj).attr("lang");
			objJson.pageNum = $('.currPage').children().text();
			objJson.selectedNum = selectedNum;
			objJson.slideId = $("#slideId"+selectedNum).text();
			objJson.slideExt = $("#slideExt"+selectedNum).text();
			objJson.presentation_id = $.trim($('#presentationId').val());
			objJson.slideOriginalName = $.trim($("#slideOriginalName"+selectedNum).text());
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
			$("#selectedList").val(JSON.stringify(selectedArr));
		}
	}
	
	//从系统库添加添加幻灯片保存
	saveSlideByFactory = function(){
		var sysSlideList = [];
		var html = '';
		var selectedArr;
		var selectedText = $("#selectedList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		selectedArr.forEach(function (item) {
			var selectedNum = item.selectedNum;
			if(""!=selectedNum){
				var obj = new Object();
            	var id = item.slideId;
            	obj.id = id;
            	obj.presentation_id = item.presentation_id;
            	var slideExt = item.slideExt;
            	var imgTemp = "";
            	if("ppt"==slideExt||"pptx"==slideExt){
            		imgTemp = "duplicate";
            	}else{
            		imgTemp = "picture";
            	}
				html +='<div class="upload_tbody_slides" id=file_'+id+'>'+
	                     	'<li> <span class="glyphicon glyphicon-'+imgTemp+' pull-left f18"></span>'+
							'<div class="pull-left ml10" id=totalWidth_'+id+' style="width:430px;">'+
								'<div class="pull-left filename file_name" title=""><em class="c_green pull-right file_percent">100%</em>'+item.slideOriginalName+'</div>'+
								'<div class="load pull-left" style="width: 440px;"><div id=percent_'+id+' class="load_green" style="width:100%"></div></div>'+
							'</div>'+
							'<button type="button" class="pull-right up_del_icon" onclick=delSyncSlide("'+id+'","","1");><span class="glyphicon glyphicon-trash"></span></button>'+
						'</li>'+
	                 '</div>';	
				sysSlideList.push(obj);
			}
        });
		var url="/presentation/saveSysSlides.html",data={
				sysSlideJson : JSON.stringify(sysSlideList),
				presentationId:$.trim($('#presentationId').val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				$("#pointButton").hide();
				$("#pointDiv").attr("class","con bg_fff");
				$("#upload_tbody_slides").append(html);
				$("#upload_tbody_slides").show();
				closePopWindow();
				$("#selectedList").val("{}");
			}else{
				alertMsg("添加失败！");
			}
		});
	}
	
	//模糊匹配幻灯片媒体库信息
	querySlideList = function(){
		querySlideDataList(1);
	}
	
	//幻灯片媒体库
	querySlideDataList = function(pageNo){
		$("#one").prop("checked",""); 
		$("#one").next().attr("class","checkbox-2");
		$("#selectedList").val("{}");
		var url="/presentation/querySlideInfoList.html",data={
				pageNo:pageNo,
				accountId:$.trim($.cookie('CAID')),
				querySlideInfo:$.trim($("#querySlideInfo").val()),
				presentationId:$.trim($("#presentationId").val())
		}
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				//mediaHtmlSplicing(result.data);
				paging.pageTemplateDiv(result,"slideFileList","slideFactory_data_template","slide_data_page_panel","querySlideDataList");
				$(".pagination").parent().removeClass("pull-center").addClass("pull-left");
				$(".pagination").css('margin-top','0');
				customInput("slideRemember",true);
				drawingChecked();
				if(result.data.dataList.length<=0){
					$("#slidesFileListPanel").hide();
				}else{
					$("#slidesFileListPanel").show();
				}
			}else{
				alertMsg("获取系统多媒体库失败！");
			}
		});
	
	}
	
	//关闭
	closeSlideFactory = function(){
		closePopWindow();
		$("#selectedList").val("{}");
	}
});
