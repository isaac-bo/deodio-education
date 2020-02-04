define([ "jquery", "utils.cookie", "jquery.dot","pagination","jquery.tagator", "utils", "jquery.base",
	"jquery.validate","bootstrap.datepicker" ,"fileupload.common" ], function($, cookie, doT, paging,tagator) {
	
	var isInner = false;
	var isTags = false;
	var _init = function() {
		_initUI();
		
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
	};

	var _initUI = function() {
		 $('select').selectpicker();
		 $(".form_datetime").datetimepicker({
		        format: "yyyy-mm-dd",
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
		 $("#subjectLevel").change(function() {
			 loadQuizSubjectDataList(1);
		 });
		
	};
	
	blurTagsPicker = function(){
		if(!$('#hotTagsList').is(':hidden') && isTags == false){
			$('#hotTagsList').hide();
		}
	};
	
	initQuizSubject = function(){
		
		var d2=new Date();
		d2.setFullYear(d2.getFullYear()+1);
		d2.setDate(d2.getDate()-1);
		
		$('#quiz_knowledge').val('');
		customInput("quiz_difficult");
		$("input[name = quiz_difficult]").each(function(){
			$(this).attr('checked',false);
			$(this).parent().find('label').removeClass('checked');
		});
		$('#man21').prop('checked','checked');
		$('#man21').parent().find('label').addClass('checked');
		$('#quiz_score').val('1');
		$('#quiz_expire_date').val('');
		$('#quiz_visible').val('0');
		$('#tagsDiv').removeClass('right-item');
		$('.tagator_tags').html('');
		$('#hiddenQuizSubjectId').val('');
	
	};
	
	loadKnowledgePoints = function(callback){
		var url="/knowledge/get_knowledge_by_classificationId.html",data={
				classificationId : $('#treeNodeId').val()
		};
		postAjaxRequest(url, data,function(result){
			$('select').selectpicker('val', '');
			var template = doT.template($("#options_knowledge_point").text());
			$('#quiz_knowledge').html(template({data:result.data}));
			$('select').selectpicker('refresh');
			
			callback();
			
		});
	};
	
	setQuizSubject = function(data){
		$("#quiz_knowledge").val(data.quizKnowledgePointId).trigger("change");
		$('input[type=radio][name=quiz_difficult]').each(function(){
			customInput('quiz_difficult');
			customInput('quiz');
			if($(this).val() == data.quizSubjectLevel){
				$(this).prop('checked','checked');
			} else {
				$(this).removeProp("checked");
			}
		});
		console.log("data.quizSubjectExpireTime=== ",data.quizSubjectExpireTime);
		$('#quiz_score').val(data.quizSubjectScore).trigger("change");
		$('#quiz_expire_date').val(dateFormat(data.quizSubjectExpireTime,'yyyy-MM-dd'));
		$('#quiz_visible').val(data.quizSubjectVisible).trigger("change");
		$('#hiddenQuizSubjectId').val(data.id);
		$('select').selectpicker('refresh');
	};
	
	initDialogue = function(popType,dialogueType){
		initSettings();
		$("#quiz_type").val(popType);
		$("#myModalQuiz").modal("show");
		$('#myModalQuiz').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
		});
		customInput('quiz');
		customInput('quiz_difficult');
		switch (popType) {
		case 1:
			$("#myQuizType").html("单选题");
			break;
		case 2:
			$("#myQuizType").html("多选题");
			break;
		case 3:
			$("#myQuizType").html("判断题");
			break;
		case 4:
			$("#myQuizType").html("排序题");
			break;
		case 5:
			$("#myQuizType").html("图文简答题");
			console.log("dialogueType   ",dialogueType);
			var id;
			if(dialogueType=="add"){
				//设置上传图片按钮的id
				var picFilePath=$('input[name="uploadFile"]');				
				id=new UUID().id;
				var uploadId="uploadFile_"+id;
				picFilePath.attr("id",uploadId);
				//设置上传图片按钮的外层div的id
				var picFilePathParent=picFilePath.parent();						
				picFilePathParent.attr("id","uploadParent_"+id);
				//设置上传图片按钮的隐藏input的id
				var hiddenFilePath=picFilePath.next();
				hiddenFilePath.attr("id","uploadFilePath_" + id);
			}else{
				//设置上传图片按钮的id
				var picFilePath=$('input[name="uploadFile"]');
				id=picFilePath.attr("id").split("_")[1];
				//设置上传图片按钮的外层div的id
				var picFilePathParent=picFilePath.parent();						
				picFilePathParent.attr("id","uploadParent_"+id);
			}			
	     	uploadInit(id);
			break;
		case 6:
			$("#myQuizType").html("文字简答题");
			break;
		case 7:
			$("#myQuizType").html("填空题");
			break;
		}
	};
	
	
	initPreviewDialogue = function(popType){
		$('#quizs_contents').show();
//		$("#quiz_type").val(popType);
		$("#myModalPreviewQuiz").modal("show");
		$('#myModalPreviewQuiz').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
		});
		customInput('quiz');
		customInput('quiz_difficult');
		
		switch (popType) {
		case 1:
			$("#myQuizPreviewType").html("单选题");
			break;
		case 2:
			$("#myQuizPreviewType").html("多选题");
			break;
		case 3:
			$("#myQuizPreviewType").html("判断题");
			break;
		case 4:
			$("#myQuizPreviewType").html("排序题");
			break;
		case 5:
			$("#myQuizPreviewType").html("图文简答题");
			var id=$('input[name="filePath"]').attr("id").split("_")[1];
	     	uploadInit(id);
			break;
		case 6:
			$("#myQuizPreviewType").html("文字简答题");
			break;
		case 7:
			break;
		}
		
	};
	
	editQuizsDialogue = function(popType,data){
		loadKnowledgePoints(function(){setQuizSubject(data)});
		$("#quizs_contents").html(chooseTemplate(popType)(data));
		initDialogue(popType,"edit");
	};
	//题目预览
	viewQuizsDialogue = function(popType, data) {
		//loadKnowledgePoints(function(){setQuizSubject(data)});
		$("#quizs_preview_contents").html(chooseTemplate2(popType)(data));
		initPreviewDialogue(popType);
	};

	popQuizsDialogue = function(popType){
		
		if ($("#treeNodeId").val() == 1000008 || $("#treeNodeId").val() == 999999) {
			alertMsg('默认题库不能独立创建试题，请选择正确的题库。');
			return;
		}
		var quizContentsTemp=chooseTemplate(popType)('abcdefghijklmnopqrstuvwxyz');
		console.log("quizContentsTemp   ",quizContentsTemp);
		console.log("uuid---   ",new UUID().id);
		$("#quizs_contents").html(quizContentsTemp);
		initDialogue(popType,"add");
		initQuizSubject(popType);
		loadKnowledgePoints(function(){
			
		});
	};
	
	
	chooseTemplate = function(popType){
		var template ="";	
		
		switch (popType) {
		case 1:
			template = doT.template($("#radio_template").text());	
			break;
		case 2:
			template = doT.template($("#checkbox_template").text());	
			break;
		case 3:
			template = doT.template($("#alternative_template").text());	
			break;
		case 4:
			template = doT.template($("#order_template").text());	
			break;
		case 5:
			template = doT.template($("#picture_template").text());	
			break;
		case 6:
			template = doT.template($("#short_answer_template").text());	
			break;
		case 7:
			template = doT.template($("#space_template").text());	
			break;
		}
		
		return template;
	};
	
	chooseTemplate2 = function(popType){
		var template ="";	
		
		switch (popType) {
		case 1:
			template = doT.template($("#radio_template2").text());	
			break;
		case 2:
			template = doT.template($("#checkbox_template2").text());	
			break;
		case 3:
			template = doT.template($("#alternative_template2").text());	
			break;
		case 4:
			template = doT.template($("#order_template2").text());	
			break;
		case 5:
			template = doT.template($("#picture_template2").text());	
			break;
		case 6:
			template = doT.template($("#short_answer_template2").text());	
			break;
		case 7:
			template = doT.template($("#space_template2").text());	
			break;
		}
		
		return template;
	};
	
	
	deleteBankOptions = function(obj,type){
		var $this = $(obj).parent().parent();
		$(obj).parent().remove();
		
		
		
		if(type!=undefined){
			$this.find(".ti div._order-no").each(function(i,v){
				$(this).text(i+1);
			});
		}else{
			$("input[name='quiz']").each(function(i,v){
				   $(this).val(i+1);
			});
		}	
	};
	
	addBankOptions = function(obj,optionsType){
		var _timuDiv = $(obj).parent().parent(),_tiLastDiv = _timuDiv.find("div.ti:last"),_divFind = '',_class='';
		if(optionsType==1){
			_divFind ="input[type='radio']";
			_class='radio-group';
			_inputType="radio";
			
		}else if(optionsType==2){
			_divFind ="input[type='checkbox']";	
			_class='checkbox-group';
			_inputType="checkbox";
		};
		
		if(optionsType!=3){
			var _tiIds = _tiLastDiv.find(_divFind).attr("id").split("_");
			var data={
					tiId : _tiIds[0]+"_"+(Number(_tiIds[1])+1),
					name : "quiz"+_tiIds[0],
					values:(_timuDiv.find("div.ti").size()+1),
					divClass:_class,
					inputType:_inputType
					
			};
			
			var doTtmpl =doT.template($("#options_add_template").text()); 
			var html = doTtmpl({"data":data});
			_tiLastDiv.after(html);
			customInput("quiz");			
		}else{
			
			var doTtmpl =doT.template($("#options_add_text_template").text()); 
			var html = doTtmpl({"data":(_timuDiv.find("._order-no").size()+1)});
			_tiLastDiv.after(html);
		};
		
	};
	
	toggleSettings = function(){
		var empx ={left: '-5px'};
		var emPx2 = {left: '45px'};
		var empx_1 ={left:'694px'};
		var empx2_1 = {left:'740px'};
		toggleSliderBar('toolsBar','settingContents',empx,emPx2,empx_1,empx2_1);
		if($('#toolsBar').css('left') == '694px'){
			$('#quizs_contents').hide();
		}else{
			$('#quizs_contents').show();
		}
	};
	
	initSettings = function(){
		var empx ={left: '-5px'};
		var emPx2 = {left: '45px'};
		var empx_1 ={left:'694px'};
		var empx2_1 = {left:'740px'};
		initSliderBar('toolsBar','settingContents',empx,emPx2,empx_1,empx2_1);
		$('#quizs_contents').show();
	};
	
	submitQuiz = function() {
		var tagsLength = $("#tagator_inputTagator .tagator_tags .tagator_tag").length;
		if (tagsLength < 1) {
			$("#tagsDiv").removeClass("right-item");
			$("#tagsDiv").addClass("wrong-item");
			$("#tagsDiv").parent().next('span').remove();
			$("#tagsDiv").parent().after("<span class='help-inline error' style='margin-left:10px'>请输入标签！</span>");
			tagsFlag = false;
		} else {
			$("#tagsDiv").addClass("right-item");
			$("#tagsDiv").removeClass("wrong-item");
			$("#tagsDiv").parent().next('span').remove();
			tagsFlag = true;
		}

		if(tagsFlag){
			//标签
			var tagsList = [];
			$(".tagator__span").each(function(item){
				var obj = new Object();
				obj.tagName=$.trim($(this).text());
				obj.tagSource = 0;
				tagsList.push(obj);
			});
		}
         
		var dataArray = new Array();
		var _qtype = $('#quiz_type').val();
		var _question = $('input.quiz_title').val();
		var _optionsType = getQtype(_qtype), answers = new Array();
		var optionArray = new Array();
		if (_qtype <= 4) {
			$('#quizs_contents').find('div.ti').each(function() {
				var _options = $(this).find('input[type="text"]');
				_options.each(function() {
					var isCorrect = $(this).parent().siblings("div").find('input').prop("checked") == true ? "1": 0;
					optionArray.push($(this).val()+ "_&_"+ isCorrect);
				});
			});
			$('#quizs_contents').find(_optionsType).each(function() {
				answers.push($(this).parent().parent().find('input[type=text].form-control').val());
			});
			
		} else if (_qtype == 5) {
			var picNode=$('#quizs_contents').find('div.l_pic');
			console.log("picNode   ",picNode.attr('id'))
			var dirId = picNode.attr("id").split("_")[1];
			console.log("dirId   ",dirId);
			optionArray.push($('textarea[name="tuxing"]').val()+"_&_"+$("#uploadFilePath_" + dirId).val());
			/*var dirId = $('#quizs_contents').find('div.l_pic').attr("id").split("_")[1];
			optionArray.push($('textarea[name="tuxing"]').val()+"_&_"+$("#uploadFilePath_" + dirId).val());
*/
		} else if(_qtype == 6){
			answers.push($('#question_answer_id').val());
			optionArray.push($('#question_answer_id').val());
		} else if(_qtype == 7){
			debugger;
			
			var r = /^.+?\【(.+?)\】.+?\【(.+?)\】.*$/;
			textarea = $('#question_answer_id').val();
			var content = r.exec(textarea);
			var correct = "";
			for(var i in content){
				if(i > 0){
					textarea = textarea.replace('【' + content[i] + '】' ,'【          】');
					correct += content[i] + "==@@==";
				}
			}
			
			answers.push(correct);
			optionArray.push(textarea);
		}
		
		var dataStr = _qtype + "_#_" + _question + "_#_" + answers.join("_$_") + "_#_" + optionArray.join("]=[");
		if(($.trim(_question)).length<=0){
			alertMsg('请输入试题的题目！');
			return false;
		}
		
		var isEmpty = true;
		
		for(var index = 0; index < optionArray.length; index++){
			var item = optionArray[index].split('_&_')[0];
			if(($.trim(item)).length <= 0){
				isEmpty = false;
			}
		}
		
		if(isEmpty == false){
			alertMsg('请输入试题选项的内容！');
			return false;
		}
		
		if(_qtype <= 4){
			if (answers.length == 0) {
				alertMsg("请选择试题正确答案！");
				return false;
			}
		}
		
		var params = {
			quizBankId:$('#quizBankId').val(),
			quizSubjectId:$('#hiddenQuizSubjectId').val(),
			quizData : dataStr,
			knowledge:$('#quiz_knowledge').val() == null || $('#quiz_knowledge').val() == ''?'':$('#quiz_knowledge').val(),
			difficult:$('input[name=quiz_difficult][type="radio"]:checked').val(),
			score:$('#quiz_score').val(),
			tags:'default',
			expireDate:$('#quiz_expire_date').val(),
			visible:$('#quiz_visible').val()
		}, url = "/quiz/subject/create_quiz_subject.html";

		postAjaxRequest(url, params, function(result) {
			console.log("result----477   ",result);
			$("#myModalQuiz").modal('hide');
			alertMsg('操作成功！');
			loadSubjectDataList(1);
		});
		
	};
	
	
	
	
	//
	
	onPopupRequiredSubject = function(type,obj,pageNo){
		$("#subjectType").val(type);
		$("#limitCount").val($(obj).siblings('span').text());	
		$("#requiredQuizBanksModal").modal("show");
		$('#requiredQuizBanksModal').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
		});
		loadQuizSubjectDataList(pageNo);
	};
	
	loadQuizSubjectDataList = function(pageNo){
		var subjectArray = new Array();
		$(".chouti").each(function(i, v) {
			subjectArray.push(v.id);
		});
		var params = {
			   pageNo:pageNo,
//			   banksId:$("#bankScope").val(),
			   quizId:$("#quizId").val(),
			   subjectArray:subjectArray,
			   subjectTile:$.trim($("#subjectTile").val()),
			   subjectLevel:$("#subjectLevel").val(),
			   subjectType:$("#subjectType").val(),
			   limitCount:$("#limitCount").val()
		}, url="/quiz/subject/list_by_banks_id.html";
			console.log('params  ',params);
		postAjaxRequest(url, params, function(result){
			paging.pageTemplateDiv(result,"table_panle","table_data_template","data_page_Panel","loadQuizSubjectDataList");
			customInput("subject");
			checkOrUncheckAll('one2','subject');
			$('#data_page_Panel div').addClass("pull-left").css("margin-top","-20px");
		},false);
	};
	
	//热门标签列表
	popTagsPicker = function(){
		listHotTags();
	};
     
     uploadInit = function(id) {
    	 uploadFileJqProcess("uploadFile_"+id,'/commons/uploadAndUpdateAttachment.html?presentationId='
    			 +id+'&attachRelType='+11,
    			 {accountId:cookie.getCookie('CAID'),attachRelType:11,isModel:true},
    				1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
    	        	console.log("data ====",data.result.data)
    	        		var srcAttachTemp=data.result.data.attachUrl;
    	        		var srcimgTemp = data.result.data.generateName;
    	        		var srcimg = imgStatic+srcAttachTemp+srcimgTemp;     		
    					$("#"+uploadParentId).attr('style','background : url(' + srcimg + ') center no-repeat;background-size: 100%  100%')
    	    			$("#uploadFilePath_" + id).val(srcimg);
    	        },function(e,data){
    		
    	        });
		};
	
	_init();
	quizAttachUpload=function(obj,attachRelType){
		console.log("quizAttachUpload---   attachRelType=",attachRelType)
		var uploadId=$(obj).attr('id');
		var uploadParentId=$(obj).parent().attr('id');
		var id=uploadId.split("_")[1];
		console.log('id====',id);
		console.log('uploadId====',uploadId);	
		var hiddenUploadFilePath=$(obj).next();
		console.log('uploadParentId====',uploadParentId);		
		var uploadUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+id+'&attachRelType='+attachRelType;	
		var f=$(obj).val();
		if(f == null || f ==undefined || f == ''){
            return false;
        }
        if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
        {
            $(obj).val('');
            return false;
        }            
        uploadFileJqProcess(uploadId,uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:attachRelType,isModel:true},
			1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
        	console.log("data ====",data.result.data)
        		var srcAttachTemp=data.result.data.attachUrl;
        		var srcimgTemp = data.result.data.generateName;
        		var srcimg = imgStatic+srcAttachTemp+srcimgTemp;     		
				$("#"+uploadParentId).attr('style','background : url(' + srcimg + ') center no-repeat;background-size: 100%  100%')
    			$("#uploadFilePath_" + id).val(srcimg);
        },function(e,data){
	
        });
	}
});
