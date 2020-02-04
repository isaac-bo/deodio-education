define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils", "jquery.base", "jquery.validate", "jquery.scrolltofixed",
		"jquery.mCustomScrollbar", "bootstrap.select",  "fileupload.common","bootstrap.datepicker", "jquery.scroll.pagination" ], function($, cookie, doT, paging) {
	var userIdCardDefaultSnapShot = ctx
	+ '/resources/img/account/user-card-1.jpg';
	var _init = function() {
		
		_initUI();
		
//		loadDataList(1,"table_panle", "table_data_template", "smallIconShow");
		customInput("allTrainersId");
		checkOrUncheckAll('allTrainersId','trainersId');
		mediumIconShow(1);
	};
	//
/*	uploadProcess('trainerUploadFile','*.jpg;*.png;',{accountId:cookie.getCookie('CAID')},ctx+'/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#trainerId").val())+'&r=' + Math.floor(Math.random() * 100)
			,function(fileName){
				//alert(fileName);
			}
			,function(data){
				//imgStatic = "http://210.83.216.71:5000/deodioStatic"
				var attachUrl = replaceAll(data.attachUrl,"//","/");
				var srcimg = imgStatic+attachUrl;
				$("#trainerPortraitUrl").attr("src",srcimg);
			},true,"上传头像");	
*/
	
	var _initUI = function() {
		$('select').selectpicker();
		$('#myTab #tab02').addClass('active');
	};
	
	//加载讲师列表
	loadDataList = function(pageNo,tablePanel,tableTmp,callbackFunc) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:$("#data_page_Panel").is(":hidden")?6:10,
			keywords : $.trim($('#keywords').val())
		}, url = "/trainers/load_trainer_data_list.html";
		postAjaxRequest(url, params, function(result) {
			paging.pageTemplateDiv(result, tablePanel, tableTmp, "data_page_Panel", callbackFunc);
			customInput("trainersId");
			
//			var userId=$("#userId").val();
//			console.log(userId+"........");
//			console.log(result.data.dataList);
//			var dataArray=new Array();
//			dataArray=result.data.dataList;
//			var btns = $('.icon_1');
//			var btns = $('.icon_2');
//			for(var i=0;i<dataArray.length;i++){
//				if(userId == dataArray[i].create_id){
//					console.log(dataArray[i].create_id+"........");
//					$(btns[i]).hide();
//			     }
//		     }
		});
	};
	
	//加载详细列表
	smallIconShow = function(pageNo) {
		$('#content').stopScrollPagination();
		$('.table-item').addClass('on');
		$('.pic-item').removeClass('on');
		$('#smallIcon').show();
		$("#content").children().remove();
		$("#mediumIcon").hide();
		$("#data_page_Panel").show();
		$("#hid_default_page").val(Number(pageNo) + 1);
		$("#delAllTrainers").show();
		loadDataList(pageNo,"table_panle", "table_data_template", "smallIconShow");
	};
	
	//加载中等图标列表
	mediumIconShow = function(pageNo) {
		$('.pic-item').addClass('on');
		$('.table-item').removeClass('on');
		$('#mediumIcon').show();
		$("#table_panle").children().remove();
		$('#smallIcon').hide();
		$("#data_page_Panel").hide();
		$("#delAllTrainers").hide();
		
		loadDataList(pageNo,"content", "data_template","mediumIconShow");
		//实现滚动加载列表
		$('#content').scrollPagination({
			'postUrl' : ctx + '/trainers/load_trainer_data_list.html',
			'postParams' : {
				pageNo : 1,
				pageSize:6,
				keywords : $.trim($("#keywords").val())
			},
			'scrollTarget' : $(window),
			'callBackLoad' : function(data) {
				
				loadTrainerCallBackList(data);
			},
			'beforeLoad' : function() {
				this.postParams.pageNo = $("#hid_default_page").val();
				this.postParams.pageSize = 6;
				this.postParams.keywords = $.trim($("#keywords").val());
			}

		});	
//		$('#content').attr('scrollPagination', 'enabled');
	};

	//滚动加载讲师列表的回调函数
	loadTrainerCallBackList = function(data) {
		
		var template = doT.template($("#data_template").text());
		var finalPageNo = 0;
		if ($("#content").size() == 0) {
			$('#content').append(template({
				"data" : data.dataList
			}));
		} else if($("#hid_default_page").val()!=1){
			$('#content div.row:last').after(template({
				"data" : data.dataList
			}));
		}
		if (data.dataList.length != 0) {
			$("#hid_default_page").val(Number($("#hid_default_page").val()) + 1);
		}
	};

	//查找讲师，并刷新讲师列表
	searchTrainerList = function() {
		if ($('.table-item').hasClass('on')) {
			smallIconShow(1);
		} else {
			mediumIconShow(1);
		}
	};

	//删除培训教师
	delTrainer = function(trainerId){
		confirmMsg('请确认您是否要删除该培训教师？',function(){
			var url="/trainers/del_trainer.html",
			data={trainerId:trainerId};
			postAjaxRequest(url,data,function(result){
					searchTrainerList();
					alertMsg("操作成功");
			});
		});
	};
	
	//批量删除培训教师
	delAllTrainers = function(){
		
			var arr = new Array();
			$("input[name='trainersId']:checkbox").each(function(){ 
            	if($(this).next('label').hasClass("checked")){
            		arr.push($(this).attr("id"));
            	}
            });
	        var trainerIds = arr.join(",");
	        if(trainerIds){
	        confirmMsg('请确认您是否要删除该培训教师？',function(){
	        var url="/trainers/del_all_trainers.html",
			data={trainerIds:trainerIds};
			postAjaxRequest(url,data,function(result){
				searchTrainerList();
				alertMsg("操作成功");
			});
		});
		}else {
	    	 alertMsg("请先勾选要删除的培训教师!");
	     }
	};
	
	//更新讲师
	editTrainer = function(trainerId){
		var url="/trainers/get_trainer.html",
		data={trainerId:trainerId};
		postAjaxRequest(url,data,function(result){
			var template = doT.template($("#pop_template").text());	
			popUpWindow(template({data:result.data}),"更新讲师","900px","550px");
			customInput("trainerType");
			customInput("trainerGender");
			customInput("isRecommended");
			customInput("trainerLevel");
			$('#trainerId').val(trainerId);
//			uploadPortraitPic();
			$("#trainerForm").myValidate({
				formCall:function(){ submitTrainer();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:true,regionText:true},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		});
		customInput("trainerType");
		customInput("trainerGender");
		customInput("isRecommended");
		customInput("trainerLevel");
	};
	
	_init();
	//预览讲师
	previewTrainer = function(trainerId){
		var url="/trainers/get_preview_trainer.html",
		data={trainerId:trainerId};
		postAjaxRequest(url,data,function(result){
			var template = doT.template($("#pop_view_template").text());	
			popUpWindow(template({data:result.data}),"预览讲师","900px","550px");
			customInput("trainerType");
			customInput("trainerGender");
			customInput("isRecommended");
			customInput("trainerLevel");
			$('#trainerId').val(trainerId);
		});
		customInput("trainerType");
		customInput("trainerGender");
		customInput("isRecommended");
		customInput("trainerLevel");
	};

	
	//弹出创建讲师窗口
	popTrainerDialogue = function(){
		var template = doT.template($("#pop_template").text());	
		popUpWindow(template({
			data : {
				id : '',
				trainerType : -1,
				trainerGender : -1,
				trainerName : '',
				trainerTitle : '',
				trainerMobilePhone : '',
				trainerMail : '',
				trainerLevel : -1,
				isRecommended : 0,
				trainerOrganization : '',
				trainerDesc : ''
			}
		}), "创建讲师", "900px", "550px");
//		$("#trainerGender").attr("checked","checked");
		customInput("trainerType");
		customInput("trainerGender");
		customInput("isRecommended");
		customInput("trainerLevel");
		$('#attachId').val('');
		$('#trainerId').val('');
		$("#internalTrainer").prop('checked', true);
		$('label[for='+$("#internalTrainer").attr('id')+']').addClass('checked');
		$("#trainerLevelJunior").prop('checked', true);
		$('label[for='+$("#trainerLevelJunior").attr('id')+']').addClass('checked');
		$("#male").prop('checked', true);
		$('label[for='+$("#male").attr('id')+']').addClass('checked');
//		uploadPortraitPic();
		$("#trainerForm").myValidate({
			formCall:function(){ submitTrainer();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			
		});	
	};
	//弹出创建考场地点窗口
	popLocations = function(){
		console.log(trainerId+".........");
		var template = doT.template($("#pop_template").text());
		popUpWindow(template({data:{id:'',locationName:'',locationAddress:'',locationDesc:'',isEnabled:0}}),"创建考场地点","900px","470px");
		$('#locationId').val('');
		customInput('isEnabled');
		$('select').selectpicker();	
		setTimeout(_dMapInit('allmap'),1000);
		$("#locationForm").myValidate({
			formCall:function(){ submitLocations();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});	
	};
	//校验讲师名字
//	checkName = function(){
//		var newTrainerName=$("#trainerName").val();
//		var oldTrainerName=$("#hiddentrainerName").val();
//		var flag=false;
//		if(newTrainerName==oldTrainerName){
//			flag=true;
//		}else{
//		var url="/trainers/getCheck_trainers.html",data={
//				trainerName:newTrainerName
//		}
//		postAjaxRequest(url, data,function(result){
//			flag=result.data;
//	    },false);
//		return flag;
//	}
//	}
	//提交表单，创建讲师
	submitTrainer = function(){
		var trainerName=$("#trainerName").val();
		var trainerTitle=$("#trainerTitle").val();
		var trainerOrganization=$("#trainerOrganization").val();
		var trainerDesc=$("#trainerDesc").val();
		var trainerNameflag=true;
		var trainerTitleflag=true;
		var trainerOrganizationflag=true;
		var trainerDescflag=true;
		/*$("#trainerDesc").val(trainerDesc);*/
//		alert(trainerDesc.length)
		if(trainerName.length>50){
			alert('文本长度不能大于50个字符')
			trainerNameflag=false;
		}else{
			trainerNameflag=true;
		}
		if(trainerTitle.length>50){
			alert('文本长度不能大于50个字符')
			trainerTitleflag=false;
		}else{
			trainerTitleflag=true;
		}
		if(trainerOrganization.length>50){
			alert('文本长度不能大于50个字符')
			trainerOrganizationflag=false;
		}else{
			trainerOrganizationflag=true;
		}
		
		if(trainerDesc.length>255){
			alert('问卷描述内容过多')
			trainerDescflag=false;
		}else{
			trainerDescflag=true;
		}
		if(trainerNameflag&&trainerTitleflag&&trainerOrganizationflag&&trainerDescflag){
			createForm();
		}
		
	};
	createForm=function(){
		var url="/trainers/submit_trainer.html",data={
				trainerId:$('#trainerId').val(),
				trainerType:$('input[type=radio][name=trainerType]:checked').val(),
				trainerGender:$('input[type=radio][name=trainerGender]:checked').val(),
				trainerName:$('#trainerName').val(),
				trainerTitle:$('#trainerTitle').val(),
				trainerMobilePhone:$('#trainerMobilePhone').val(),
				trainerEmail:$('#trainerEmail').val(),
				trainerLevel:$('input[type=radio][name=trainerLevel]:checked').val(),
				trainerOrganization:$('#trainerOrganization').val(),
				isRecommended:$("#trainerIsRecommended").prop("checked")?"1":"0",
				trainerDesc:$('#trainerDesc').val(),
				trainerPortraitUrl:$.trim($("#trainerPortraitUrl").attr('src'))
		};
		postAjaxRequest(url, data, function(result){
			closePopWindow();
			//searchTrainerList();
			alertMsg("操作成功");
			$('body').removeClass('modal-open').css('overflow-y','auto');
			go2Page("/trainers/list.html");
		});
	}

	var uploadUrl = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#trainerId").val());
	registerKeyUp=function(obj,showImg,ImgName,ImgId){
		var uploadId=$(obj).attr('id');
		uploadFileJqProcess(uploadId,uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
			1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
	var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
	var srcimgTemp = data.result.data.generateName;
	alert(srcimg)
	$("#"+showImg).attr("src",srcimg);
	$("#"+ImgName).val(srcimgTemp);
	$("#"+ImgId).val(data.result.data.id);
},function(e,data){
	
});
}
	deleteFileImg=function(showImg,ImgName,ImgId,defaultPic){
		$("#"+showImg).attr("src",eval(defaultPic));
		$("#"+ImgName).val('');
		$("#"+ImgId).val('');
	}
	
	//上传头像
	
//	uploadPortraitPic = function(){
//		var uploadUrl = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#trainerId").val());
//		uploadProcess("trainerFile",uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
//				1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
//			var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
//			var srcimgTemp = data.result.data.generateName;
//			$("#preImgId").attr("src",srcimg);
//			$("#trainerCover").val(srcimgTemp);
//			$("#attachId").val(data.result.data.id);
//		},function(e,data){
//			
//		});
	
	
//	};
//		uploadProcess('trainerUploadFile','*.jpg;*.png;',{accountId:cookie.getCookie('CAID')},ctx+'/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#trainerId").val())+'&r=' + Math.floor(Math.random() * 100)
//			,function(fileName){
//				//alert(fileName);
//			}
//			,function(data){
//				//imgStatic = "http://210.83.216.71:5000/deodioStatic"
//				var attachUrl = replaceAll(data.attachUrl,"//","/");
//				var srcimg = imgStatic+attachUrl;
//				$("#trainerPortraitUrl").attr("src",srcimg);
//			},true,"上传头像");
	
	
    //删除讲师头像
    delPortraitPic = function(){
    	$("#trainerPortraitUrl").attr("src",ctx+"/resources/img/trainers/pic2.jpg");
    };
   
});

