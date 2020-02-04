define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","jquery.tagator","pagination",
    "pagination","utils","jquery.base","jquery.tagsInput","fileupload.common",
    "jquery.validate","ueditor","jquery.scrolltofixed","ueditor.config",
    "jquery.mCustomScrollbar","bootstrap.select"], function($,tree,cookie,list,doT,tagator,paging) {	
	
	var isInner = false;
	var isTags = false;
	var _init = function() {
		customInput("remember");
		customInput("remember1");
		customInput("remember2");
		$('select').selectpicker();
		um = UM.getEditor('content_template');
		um2 = UM.getEditor('description_template');
		
		if ($.trim($("#quizId").val()).length>0) {
			$(".dropdown-toggle").attr("class","btn dropdown-toggle disabled");
			/*$("#quizType").attr("disabled","disabled");*/  
		}
		$('#inputTagator').tagator({
			// 输入以下单词自动补全, 支持中文
			autocomplete: ['','']
		});
		
		$('.tagator_input').focus(function(){
			
			isInner = true;
			popTagsPicker();
		}).blur(function(){
			blurTagsPicker();
			isInner = false;
		});
		
		$('#hotTagsList').mouseenter(function(){
			isTags = true;
		}).mouseleave(function(){
			isTags = false;
			
			if(isInner == false){
				setTimeout(blurTagsPicker,3000);
			}
			
		});
		
		$("#createForm").myValidate({
			formCall:function(){ createQuizExaminationForm();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		list.onFixedItems();
	};
	
	var createQuizExaminationForm = function(){
		var contentFlag = true, descriptionFlag = true;
		var quizContent = um.getContentTxt();
		var quizDescription = um2.getContentTxt();
		if (quizContent.length > 255) {
			$("#quizExaminationDesc").removeClass("right-item");
			$("#quizExaminationDesc").addClass("wrong-item");
			alertMsg("试卷描述字数不可超过255字");
			contentFlag = false;
		} else {
			$("#courseDesc").addClass("right-item");
			$("#courseDesc").removeClass("wrong-item");
			contentFlag = true;
		}
		if (quizDescription.length > 255) {
			$("#description_template_editor").removeClass("right-item");
			$("#description_template_editor").addClass("wrong-item");
			alertMsg("试卷说明字数不可超过255字");
			descriptionFlag = false;
		} else {
			$("#description_template_editor").addClass("right-item");
			$("#description_template_editor").removeClass("wrong-item");
			descriptionFlag = true;
		}
		
		var tagsLength = $("#tagator_inputTagator .tagator_tags .tagator_tag").length;
		if(tagsLength<1){
			$("#tagsDiv").removeClass("right-item");
			$("#tagsDiv").addClass("wrong-item");
			$("#tagsDiv").parent().next('span').remove();
			$("#tagsDiv").parent().after("<span class='help-inline error' style='margin-left:10px'>请输入标签！</span>");
			tagsFlag = false;
		}else{
			$("#tagsDiv").addClass("right-item");
			$("#tagsDiv").removeClass("wrong-item");
			$("#tagsDiv").parent().next('span').remove();
			tagsFlag = true;
		}

		if(tagsFlag&&contentFlag&&descriptionFlag){
			//标签
			var tagsList = [];
			$(".tagator__span").each(function(item){
				var obj = new Object();
				obj.tagName=$.trim($(this).text());
				obj.tagSource = 0;
				tagsList.push(obj);
			});
			var quizSafes='';
			var len = $("input[id^='quizSafe']:checked").length;
			$("input[id^='quizSafe']:checked").each(function(index,item){
				if(len - 1 == index ){
					quizSafes+= $(item).val();
				}else{
					quizSafes+= $(item).val()+",";
				}
			});
		var url="/quiz/save_profile.html",data={
				quizName:$.trim($("#quizName").val()),
				quizDesc:$.trim(um.getContent()),
				quizType:$("#quizType").val(),
				tagsJson : JSON.stringify(tagsList),
				quizId:$("#hid_quizId").val(),
				/*finishTime:$("#radio_finish_time").prop('checked')?$.trim($("#finish_time").val()):null,*/
				finishTime:$.trim($("#finish_time").val()),
				passScore:$.trim($("#pass_score").val()),
				maxTimes:$.trim($("#quiz_max_times").val()),
				finalResut:getCheckVal("finalResut"),
				publishResult:getCheckVal("publisResult"),
				quizSafes:quizSafes,
				quizContent:$.trim(um2.getContent()),
				attachId : $.trim($("#quizCoverId").val())
		};
		
		postAjaxRequest(url, data,function(result){
			var redirctUrl = '';
			if($("#navTabs").val()=='1'){
				redirctUrl = '/quiz/manual/content.html';
				go2Page(redirctUrl,"eFlag="+$("#hid_editFlag").val()+'&quizId='+result.data+"&navTabs="+$("#navTabs").val());
			}else if($("#navTabs").val()=='2'){
				redirctUrl = '/quiz/auto/content.html';
				go2Page(redirctUrl,"eFlag="+$("#hid_editFlag").val()+'&quizId='+result.data+"&navTabs="+$("#navTabs").val());
			}else {

				$('#quizId').val(result.data);
				$('#hid_quizId').val(result.data);
				initContent();
				$("#oldQuizName").val($("#quizName").val());
				initContent();
//				go2Page("/quiz/content.html","quizId="+result.data+"&eFlag="+$("#hid_editFlag").val());
			};

		});
		}
	};
	
	
	var getCheckVal = function(id){
		var finalVal ='';
		$("input[id^='"+id+"']").each(function(){
		  if($(this).prop('checked')){
		        finalVal=$(this).val();
		   };
		});
		  return finalVal;
	};
	
	onSubmitQuizExaminationProfile = function(){

		
	};
	
	blurTagsPicker = function(){
		if(!$('#hotTagsList').is(':hidden') && isTags == false){
			$('#hotTagsList').hide();
		}
	};

	_init();

	var uploadUrl = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#quizflag").val());
	uploadFileJqProcess("quizUploadFile",uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
			1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
		var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
		var srcimgTemp = data.result.data.generateName;
		$("#preImgId").attr("src",srcimg);
		$("#quizCover").val(srcimgTemp);
		$("#quizCoverId").val(data.result.data.id);
	},function(e,data){
		
	});
	
	checkQuizName = function() {
		var flag = false;
		var url = "/quiz/check/quizname.html";
		var data = {quizName : $("#quizName").val()}
		if ($("#quizName").val() != $("#oldQuizName").val()) {
			postAjaxRequest(url, data, function(result){
				if (!result.data) {
					flag = true;
				} 
			}, false);
		}
		return flag;
	}
	
	examinationType = function(type,obj){
		
		if(type==1){
			$("#type1").show();
			$("#type2").hide();
		}else{
		
			$("#type2").show();
			$("#type1").hide();
		}
		
		$("#hid_quizType").val(type);
		$(".pre1_tab li").removeClass('active');
		$(obj).parent().addClass('active')
		
	};
	//热门标签列表
	popTagsPicker = function(){
		listHotTags($.trim($("#quizName").val()),um.getContent());
	};
	
	goBack = function(){
		go2Page("/quiz/list.html");
	}
	//初始化选择试卷创建方式窗口
	initContent = function() {
		var template = doT.template($("#chooseFlashWin").text());
		popUpWindow(template(), "选择试卷创建方式",340,360);
		$('.modal-header').css('background','#fff none repeat scroll 0 0').css('color','#41829a').css('border-bottom','0px');
		$('.modal-header .close').css('color','#41829a');
	}
	
	hoverModule = function(str){
		var temp = $("div[id^='format']").hide();
		$("#format"+str).show();
	};
	
	chooseModule = function(str){
		quizId=$.trim($("#hid_quizId").val());
		if(str == 0){
			//手动创建
			go2Page("/quiz/manual/content.html","quizId="+quizId+"&eFlag="+"1");
		}
		if(str == 1){
			//自动创建
			go2Page("/quiz/auto/content.html","quizId="+quizId+"&eFlag="+"1");
		}
	};
});
