define(["jquery","jquery.dot","pagination","webuploader.common","utils","jquery.base"], function($,doT,paging,wbup) {
	
	var init = function(){
//		createSWFObject4LastestVersion("/DeodioUploadMFile.swf", "file_swf_package", "158", "30", {requestUrl:ctx+ '/commons/uploadPackage.html?userId='+$.cookie('CUID')+'%26accountId='+$.cookie('CAID')+'%26presentationId='+$.trim($("#presentationId").val()),uploadType:'package',quality:'high',wmode:'opaque'}, { allowFullScreen:'true',wmode:'transparent'}, {},function(){
//			$("#file_swf_package").css('margin-top','-20px');
//		});
		
		$('#popupModel').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
		});
		
		var presentationId=$.trim($("#presentationId").val());
		var strm="userId="+$.cookie('CUID')+"&accountId="+$.cookie('CAID')+"&drapType=0";
		
		var uploadUrl = '/commons/uploadPackage.html?presentationId='+presentationId+"&"+strm;
		wbup.uploadInit.webUploaderLoaction("filePicker",uploadUrl,'zip,doc,docx,pdf','.zip,.doc,.docx,.pdf',true,function(file){
						$("#packageButton").hide();
					
						var fileId = file.id;
						
						var fileName = file.name;
						$("#tem_file_id").val("file_"+fileId);
						var progressallHtml='<div class="_upload_tbody_package" id="file_'+fileId+'">'+
						'<li>'+
							'<span class="glyphicon glyphicon-picture pull-left f18"></span>'+
							'<div class="pull-left ml10" id="totalWidth_'+fileId+'" style="width:960px;">'+
							'<div class="pull-left filename file_name" style="width:960px" title="'+fileName+'">'+
								'<em class="c_blue pull-right file_percent" id="'+fileId+'_file_percent"><span id="progress_bar_'+fileId+'"></span></em>'+fileName+
							'</div>'+
							'<div class="load pull-left" style="width:960px">'+
								'<div id="percent_'+fileId+'" class="load_blue"></div>'+
							'</div>'+
							'</div>'+
						'</li>'+
					'</div>';
						$("#upload_tbody_package").show().append(progressallHtml);
		},function(file,percentage){
			$("#progress_bar_"+file.id).html(percentage * 100 +"%");
			$("#percent_"+file.id).css('width',percentage * 100 +'%');
		},function(file,data){
			fileName = data.packageName;
			packageId = data.packageId;
			$("#file_"+$("#tem_file_id").val()).attr("id",packageId);		
			 $("body").mask();
	  		$.ajax({
    		    type: 'POST',
    		    url: ctx+"/decompression/package.html",
    		    data: {packagePath:data.packagePath,
    		    		presentationId:data.presentationId,
    		    		packageName:data.packageName,
    		    		userId:data.userId,
    		    		packageId:packageId} ,
    		    dataType: 'json',
    		    success: function(data){
    		    	if(data.status==1){
    		    		 $("body").unmask();
   
    		    		$("#totalWidth_"+file.id).after('<button type="button"  class="pull-right up_del_icon" onclick=onDelPackageFiles("'+packageId+'","'+file.id+'");>'+
            					'<span class="glyphicon glyphicon-trash"></span>'+
            					'</button>');

    		    	}
    		    }
    		});
		});
		
	/*	
		var strm="userId="+$.cookie('CUID')+"&accountId="+$.cookie('CAID')+'&fileId='+fileId+"&drapType="+drapType;
		
		var uploadUrl = '/commons/uploadPackage.html?presentationId='+presentationId+"&"+strm;
		uploadFileJqProcess("presentationUploadFile",uploadUrl,{},
				1,/(.|\/)(pdf|zip)$/i,true,function(e,data){
			fileName = data.result.packageName;
			packageId = data.result.packageId;
			$("#file_"+$("#tem_file_id").val()).attr("id",packageId);
			$("#uploadMethod").val("1");
			 $("body").mask();
	  		$.ajax({
    		    type: 'POST',
    		    url: ctx+"/decompression/package.html",
    		    data: {packagePath:data.result.packagePath,
    		    		fileId:fileId,
    		    		presentationId:data.result.presentationId,
    		    		packageName:data.result.packageName,
    		    		userId:data.result.userId,
    		    		packageId:packageId} ,
    		    dataType: 'json',
    		    success: function(data){
    		    	if(data.status==1){
    		    		 $("body").unmask();
    		    	//	alreadyConvertSwfamount = alreadyConvertSwfamount + 1;
    		    		$("#totalWidth_"+fileId).after('<button type="button"  class="pull-right up_del_icon" onclick=onDelPackageFiles("'+packageId+'");>'+
            					'<span class="glyphicon glyphicon-trash"></span>'+
            					'</button>');
//    		    		if(alreadyConvertSwfamount == convertSwfamount){
//    		    			$("#saveSubmitSyncFiles").prop("disabled","");
//    		    			$("#isRefreshFlag").val(0);
//    		    		}
    		    	}
    		    }
    		});
			
		},function(e,data){
			$("#packageButton").hide();
			$("#uploadMethod").val("0");
			fileId = new UUID().id;
			$("#tem_file_id").val("file_"+fileId);
			var fileName = "";
			  $.each(data.files, function (index, file) {
				  fileName = file.name;
		        });
			var progressallHtml='<div  id="file_'+fileId+'">'+
			'<li>'+
				'<span class="glyphicon glyphicon-picture pull-left f18"></span>'+
				'<div class="pull-left ml10" id="totalWidth_'+fileId+'" style="width:960px;">'+
				'<div class="pull-left filename file_name" style="width:960px" title="'+fileName+'">'+
					'<em class="c_blue pull-right file_percent" id="'+fileId+'_file_percent"><span id="progress_bar_'+fileId+'"></span></em>'+fileName+
				'</div>'+
				'<div class="load pull-left" style="width:960px">'+
					'<div id="percent_'+fileId+'" class="load_blue"></div>'+
				'</div>'+
				'</div>'+
			'</li>'+
		'</div>';
			$("#upload_tbody_package").show().append(progressallHtml);
		
		},function(data){
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$("#progress_bar_"+fileId).html(progress+"%");
			$("#percent_"+fileId).css('width',progress+'%');
		});
		
		*/
		
		
		
	}
	
	init();
	
	//压缩包系统库查询
	onPopupPackageFilesList= function(pageNo){
		var url="/presentation/package/load_package_files.html",data={
				pageNo:pageNo,
				accountId:$.trim($.cookie('CAID')),
				presentationId:$.trim($("#presentationId").val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				var template = doT.template($("#packageFactoryText").text());
				popUpWindow(template(), "系统课件包库",750,500);
				if(result.data.dataList.length<=0){
					$("#packageFileListPanel").hide();
					$("#btn_add_file").hide();
				}
				paging.pageTemplateDiv(result,"packageFileList","package_Factory_data_template","package_data_page_panel","onPopupPackageFilesList");
				$(".pagination").parent().removeClass("pull-center").addClass("pull-left");
				$(".pagination").css('margin-top','0');
				customInput("packageRemember",true);
				drawingChecked();
			}else{
				alertMsg("获取系统幻灯片库失败！");
			}
		});
		
	};
	
	//已选的复选框数据翻页恢复
	drawingChecked = function(){
		var selectedArr;
		var selectedText = $("#selectedPackageList").val();
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
			});  
		}
	 };
	
	//全选/反选(packages)
	onSelectAllFunc = function(obj){
		var selectedArr;
		var selectedText = $("#selectedPackageList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		var selectFlag = $(obj).is(':checked');
		if(selectFlag){
			$("input[name^='packageRemember']").prop("checked","checked"); 
			$("input[name^='packageRemember']").next().attr("class","checkbox-2 checked");
			$('#packageFileList').find('tr').each(function (x,tr) {
				$(this).find('div input').each(function (y,td) {
	            	//当前页选中行
					var selectedNum = $(td).attr("lang");
					if(undefined==selectedNum){
						selectedNum ="";
					}
					var objJson = new Object();
					objJson.pageNum = $('.currPage').children().text();
					objJson.selectedNum = selectedNum;
					objJson.id = $("#id"+selectedNum).text();
					objJson.packageExt = $("#attachExt"+selectedNum).text();
					objJson.presentation_id = $.trim($('#presentationId').val());
					objJson.packageOriginalName = $.trim($("#attachName"+selectedNum).text());
					objJson.attachDir = $.trim($("#attachDir"+selectedNum).text());
					objJson.generateName = $.trim($("#generateName"+selectedNum).text());
					objJson.attachSize = $.trim($("#attachSize"+selectedNum).text());
					selectedArr.push(objJson);
				})
        	});
			$("#selectedPackageList").val(JSON.stringify(selectedArr));
		}else{
			$("input[name^='packageRemember']").prop("checked",""); 
			$("input[name^='packageRemember']").next().attr("class","checkbox-2");
			$("#selectedPackageList").val("{}");
		}
	}
	
	//复选框勾选和勾掉数据存储（packages）
	onSelectOrUnSelectFunc = function(obj){
		//获取已选中的数据
		var selectedArr;
		var selectedText = $("#selectedPackageList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		var checkedLength = $('tbody').find('tr').find('div input').not("input:checked").length;
		var objJson = new Object();
		if($(obj).prop("checked")){
			//当前页选中行
			var selectedNum = $(obj).attr("lang");
			objJson.pageNum = $('.currPage').children().text();
			objJson.selectedNum = selectedNum;
			objJson.id = $("#id"+selectedNum).text();
			objJson.packageExt = $("#attachExt"+selectedNum).text();
			objJson.presentation_id = $.trim($('#presentationId').val());
			objJson.packageOriginalName = $.trim($("#attachName"+selectedNum).text());
			objJson.attachDir = $.trim($("#attachDir"+selectedNum).text());
			objJson.generateName = $.trim($("#generateName"+selectedNum).text());
			objJson.attachSize = $.trim($("#attachSize"+selectedNum).text());
			selectedArr.push(objJson);
			if(checkedLength == 0){
				$(obj).prop("checked","checked"); 
				$(obj).next().attr("class","checkbox-2 checked");
			}
			$("#selectedPackageList").val(JSON.stringify(selectedArr));
		}else{
			var selectedNum = $(obj).attr("lang");
			objJson.pageNum = $('.currPage').children().text();
			objJson.selectedNum = selectedNum;
			objJson.id = $("#id"+selectedNum).text();
			objJson.packageExt = $("#attachExt"+selectedNum).text();
			objJson.presentation_id = $.trim($('#presentationId').val());
			objJson.packageOriginalName = $.trim($("#attachName"+selectedNum).text());
			objJson.attachDir = $.trim($("#attachDir"+selectedNum).text());
			objJson.generateName = $.trim($("#generateName"+selectedNum).text());
			objJson.attachSize = $.trim($("#attachSize"+selectedNum).text());
			var index ;
			$.each(selectedArr, function(i, item){      
				if(item.selectedNum == objJson.selectedNum){
					index = i;
				}      
			});  
			selectedArr.splice(index, 1);
			if(checkedLength < 6){
				if($(obj).prop("checked")){
					$.each(selectedArr, function(i, item){ 
						if(item.selectedNum == ""){
							index = i;
						}      
					});
					selectedArr.splice(index, 1);
				}
				$(obj).prop("checked",false); 
				$(obj).next().attr("class","checkbox-2");
			}
			$(obj).prop("checked",false); 
			$(obj).next().attr("class","checkbox-2");
			$("#selectedPackageList").val(JSON.stringify(selectedArr));
		}
	};
	
	onSavePackageFilesByFactory = function(){
	
		var sysPackageList = [];
		var html = '';
		var selectedArr;
		var selectedText = $("#selectedPackageList").val();
		if("{}"==selectedText){
			selectedArr = [];
		}else{
			selectedArr = JSON.parse(selectedText);
		}
		selectedArr.forEach(function (item) {
			var selectedNum = item.selectedNum;
			if(""!=selectedNum){
				var obj = new Object();
            	var id = item.id;
            	var packageId = new UUID().id;
            	obj.id = id;
            	obj.packageId = packageId;
            	obj.presentation_id = item.presentation_id;
            	var packageExt = item.packageExt;
            	var imgTemp = "picture";
            	console.log(item);
				html += '<div class="_upload_tbody_package" id=file_'+ id + '>'
					    +	'<li><span class="glyphicon glyphicon-' + imgTemp + ' pull-left f18"></span>'
						+ 		'<div class="pull-left ml10" id=totalWidth_' + id + ' style="width:960px;">'
						+ 			'<div class="pull-left filename file_name" style = "width:960px"  title="">'
						+				'<em class="c_blue pull-right file_percent">100%</em>' + item.packageOriginalName
						+ 			'</div>'
						+ 			'<div class="load pull-left" style="width:960px">'
						+				'<div id=percent_' + id + ' class="load_blue" style="width:100%"></div>'
						+			'</div>'
						+ 		'</div>'
						+ 		'<button type="button" class="pull-right up_del_icon" onclick=onDelPackageFiles("'+ id + '");>'
						+ 			'<span class="glyphicon glyphicon-trash"></span>'
						+ 		'</button>' 
						+ 	'</li>'
						+'</div>';
				sysPackageList.push(obj);
			}
        });
		var url="/presentation/package/save_package_files.html",data={
				sysPackageJson : JSON.stringify(sysPackageList),
				presentationId:$.trim($('#presentationId').val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				$("#packageButton").hide();
				$("#packageDiv").attr("class","con bg_fff");
				$("#upload_tbody_package").append(html);
				$("#upload_tbody_package").show();
				closePopWindow();
				$("#selectedPackageList").val("{}");
			}else{
				alertMsg("添加失败！");
			}
		});
	}
	
	//模糊匹配幻灯片媒体库信息
	onSearchPackageFilesList = function(pageNo){
		$("#one").prop("checked",""); 
		$("#one").next().attr("class","checkbox-2");
		$("#selectedPackageList").val("{}");
		var url="/presentation/package/search_package_files.html",data={
				pageNo:pageNo,
				accountId:$.trim($.cookie('CAID')),
				keyWords:$.trim($("#keyWords").val()),
				presentationId:$.trim($("#presentationId").val())
		}
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				//mediaHtmlSplicing(result.data);
				paging.pageTemplateDiv(result,"packageFileList","package_Factory_data_template","package_data_page_panel","onSearchPackageDataList");
				if(result.data.dataList.length<=0){
					$("#packageFileListPanel").hide();
				}else{
					$("#packageFileListPanel").show();
				}
				$(".pagination").parent().removeClass("pull-center").addClass("pull-left");
				$(".pagination").css('margin-top','0');
				customInput("packageRemember");
				drawingChecked();
			}else{
				alertMsg("获取系统多媒体库失败！");
			}
		});
	};
	
	//关闭
	closePackageFilesFactory = function(){
		closePopWindow();
		$("#selectedPackageList").val("{}");
	}
});
