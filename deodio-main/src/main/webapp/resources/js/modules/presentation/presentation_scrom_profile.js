	define(["jquery","utils.cookie","utils.list","utils.math","jquery.dot","fileupload.common","pagination",
		"utils","jquery.base","jquery.validate","jquery.scrolltofixed","bootstrap.select"], function($,cookie,list,math,doT,paging) {
		
		var init = function(){	
//			$("#isPassHours").prop("checked",true);
			$("#lable_isPassHours").addClass("checked");
			customInput("isCourse");
//			customInput("isPassHours");
			customInput("isCountDown");
			customInput("isPassQuizs");
			customInput("isPassElements");
//			$("#itemList").hide();
			loadScormItemList();
			list.onFixedItems();
		};
		var flag = 0;
		var toPage = 0;
		//scrom课件上传
//		uploadProcess('presentationUploadFile',
//					  '*.zip;*.rar;',
//					  {userId:cookie.getCookie('CUID'),accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
//					  ctx+'/commons/uploadScromFiles.html?presentationId='+$.trim($("#presentationId").val())+'&userId='+cookie.getCookie('CUID')+'&accountId='+cookie.getCookie('CAID')+'&r=' + Math.floor(Math.random() * 100)
//				,function(fileName){
//					$('#zipFileName').val(fileName);
//				}
//				,function(data){
//					flag = 1;
//					$("#isCountDown").removeAttr("checked");
//					var label = $("label[for='isCountDown']");
//					label.removeClass("checked");
//					loadScormItemList();
//				},true,'课件上传',
//				'<div id="${fileID}" class="update-bar border-radius" style="display:block;margin-left:-417px;top:58px;width:910px">\
//					<div class="uploadify-progress-bar" ></div>\
//				</div>');
		
		
		var uploadUrl = '/commons/uploadScromFiles.html?presentationId='+$.trim($("#presentationId").val())+'&userId='+cookie.getCookie('CUID')+'&accountId='+cookie.getCookie('CAID')+'&r=' + Math.floor(Math.random() * 100)
		uploadFileJqProcess("presentationUploadFile",uploadUrl,{userId:cookie.getCookie('CUID'),accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
				1,/(.|\/)(zip|rar)$/i,true,function(e,data){
				flag = 1;
				$("#isCountDown").removeAttr("checked");
				var label = $("label[for='isCountDown']");
				label.removeClass("checked");
				loadScormItemList();
		},function(e,data){
			  $.each(data.files, function (index, file) {
			     $('#zipFileName').val(file.name);
			  });
		});	
		
		
		
		onChooseCountDown = function(){
			if($('input[type=checkbox][name=isCountDown]').is(':checked')){
				$('#itemListPanel').slideDown();
			}else{
				$('#itemListPanel').slideUp();
			}
		};
		
		//获取scrom课件章节信息
		var listSize;
		
		var loadScormItemList = function(){
			var url="/presentation/scrom/get_item_list.html",data={
				presentationId:$.trim($("#presentationId").val())
			};
			
			postAjaxRequest(url, data, function(result){
				if(result.data.length > 0){
					$('#itemPanel').show();
					$("#listSize").html("&nbsp;/&nbsp;"+result.message);
					listSize = result.message;
					if(result.message!=0){
						flag=1;
					}
					var template = doT.template($("#data_template").text());	
					$('#itemList').append(template({"data":result.data}));
					
					//设置默认达标学习进度百分比
					var presentationPercentage = $("#persentationPercentage").val(); 
					if(!presentationPercentage){
						$("#persentationPercentage").val(listSize);
					}
				}
			});
		};
		
		init();
		 
		isCorrectScromTime = function(id,value){
			 if(!math.isDigit(value)){
				 alertMsg("请输入正确的时间值!");
				 $("#"+id).val("0");
			 }
		 };
		 
		 onCompareItemList = function(id){
			
			if(Number($("#persentationPercentage").val())>listSize){
				
				 $("#persentationPercentage").val("");
				 return true;
			 }else{
				return false; 
			 };
		 };
		 var checkScoreNumber = function(value){
				var flag=false;
				if(!/^[0-9]\d*$/.test($.trim(value))){
					flag=true;
				}else{
					if(value<0||value>100){
						flag=true;
					}
				}
				return flag;
			};
		 onSubmitScromItem = function(){
			 
			 
			 if( $('#zipFileName').val()==""){
				 alertMsg("请先上传scrom标准课件！");
				 return;
			 };
			 
			 if(checkScoreNumber($('#persentationPercentage').val())){
				 alertMsg("学习进度达到要求请输入0-13之间的整数。");
				 return;
			 };
			 
			 
			 if(onCompareItemList()){
				 alertMsg("数字不得大于章节数字！");
				 return;
			 };
			 
			 
			 var itemIdList =[];
				var itemTimeList = [];
				
				$('#itemList').find("input[type=text]").each(function(){
					itemIdList.push($(this).attr('id'));
					itemTimeList.push($(this).val());
				});
				
			
				var url="/presentation/scrom/save_scrom_info.html",data={
					presentationId:$.trim($("#presentationId").val()),
//					isCountDown:$("#isCountDown").prop("checked")?1:0,
					isCountDown:0,
//					isPassPercentage:$("#one2").prop("checked")?1:0,
					persentationPercentage:$.trim($("#persentationPercentage").val()),
					isPassElements:$("#one3").prop("checked")?1:0,
					isCourse:$("#one5").prop("checked")?1:0,
					itemIdList:itemIdList,
					itemTimeList:itemTimeList
				};
				if(flag==1){
					postAjaxRequest(url, data, function(result){
						if(result.status == 1){
							 go2Page('/presentation/scrom/preview.html','presentationId='+$.trim($("#presentationId").val())); 
						}else{
							alertMsg("设置失败！");
						}
					});
				}else{
					alertMsg("请先上传scrom标准课件！");
				}
		 };
		 
	
		
		//删除课件包
		onDelScromItem = function(){
			
			var url="/presentation/scrom/deleteFile.html",data={
					presentationId:$.trim($("#presentationId").val())
				};
				
				postAjaxRequest(url, data, function(result){
					$('#zipFileName').val("");
					$("#persentationPercentage").val("");
					$("#listSize").text("/y");
					flag=0;
				});
		}
		goBack = function(){
			 go2Page('/presentation/profile.html','presentationId='+$.trim($("#presentationId").val())); 
		}
	});