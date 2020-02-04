define(["jquery","utils.cookie", "jquery.dot","utils.menu", "utils.list", "utils","jquery.base","jquery.ui"], function($,cookie,doT,menu,list) {
	
	var init = function(){
		menu.onSelectMenu(3);
		refreshQuizNo();
		
		//
		 $("#draggableContent").sortable({
			 	
			 	cancel: '.l_pic,input,textarea',
			 	
		    	start:function(event, ui) {                              
		    	        ui.item.css('border','1px dashed #dddddd');
		    	    },
			    stop :function(event, ui) {   
			    	 
			    	ui.item.css('border','');
			    	 
			    	if(!$('#quiz_or_survey') || $('#quiz_or_survey').val() != 'survey'){
			    		$("[name^='quiz']").each(function(){
				    		 var  name = $(this).attr("name");
				    		 customInput(name);
				    	 });
			    	} 
			    	 
			    	 refreshQuizNo();
			    },		
		        receive: function(event, ui) {
		        	var htmlTmpl="",_typeVal = $("#draggableVle").val();
		    		switch (Number(_typeVal)) {
					case 1:
						htmlTmpl=$("#radio_template").text();
						break;
					case 2:
						htmlTmpl=$("#checkbox_template").text();
						break;
					case 3:
						htmlTmpl=$("#alternative_template").text();
						break;
					case 4:
						htmlTmpl=$("#order_template").text();
						break;
					case 5:
						htmlTmpl=$("#picture_template").text();
						break;
					case 6:
						htmlTmpl=$("#short_answer_template").text();
						break;
					case 7:
						htmlTmpl=$("#space_template").text();
						break;
					};
					var $draggable = $(this);
						
						
		        	var doTtmpl =doT.template(htmlTmpl); 
					var dataOptions={
							uuid:UUID.prototype.createUUID(),
							order:$draggable.find("div.chouti").size()+1
					}
					var html = doTtmpl({"data":dataOptions});
					if($draggable.find("div.chouti").size()==0){
						$draggable.append(html);	        				
					}else{
						$draggable.find("div.chouti:last").after(html);
						
					}
					if(_typeVal==5){
						uploadInit(dataOptions.uuid);
					}
					$draggable.find("div.ui-draggable").remove();
					
		        }
		    });
		 
//		 list.onFixedItems();

	};
	
	examinationType = function(type){
		confirmMsg("切换试卷后，当前创建内容将会丢失", function(){
			go2Page("/quiz/profile.html","navTabs="+type);
		});
	};


	refreshQuizNo = function(){
		$("._quiz-order").each(function(i,v){
			  $(this).text(i+1);
		});
		
		$("._subject-order").each(function(i,v){
			  $(this).text(i+1);
		});
	};

	
	 //拖拽函数
	draggableFun = function(id,value){
		$("#"+id).draggable({
			helper:"clone",
			cursor: "move",	
			connectToSortable: '#draggableContent',
			start:function(event,ui){
			  $("#draggableVle").val(value);
			}	
		});	
	};

	
	
	removeQuestion = function(obj) {
		if ($(obj).parent().parent().attr("subtype")) {
			$(".chouqu span[subtype="+$(obj).parent().parent().attr("subtype")+"]")
			.text(parseInt($(".chouqu span[subtype="+$(obj).parent().parent().attr("subtype")+"]").text()) + 1);
		}
		$(obj).parent().parent().remove();
		refreshQuizNo();
	};
	
	
	addOptions = function(obj,optionsType){
		var _timuDiv = $(obj).parent().parent(),_tiLastDiv = _timuDiv.find("div.ti:last"),_divFind = '',_class='';		

		if(optionsType==1){
			_divFind ="input[type='radio']";
			_class='radio-group';
			_inputType="radio";
			
		}else{
			_divFind ="input[type='checkbox']";	
			_class='checkbox-group';
			_inputType="checkbox";
		};
		
		
		if(optionsType!=3){
			var _tiIds = _tiLastDiv.find(_divFind).attr("id").split("_");
			var data={
					tiId : _tiIds[0]+"_"+(Number(_tiIds[1])+1),				
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
	
	deleteOptions = function(obj,type){
		var $this = $(obj).parent().parent();
		$(obj).parent().remove();
		if(type!=undefined){
			$this.find(".ti div._order-no").each(function(i,v){
				$(this).text(i+1);
			});
		};		
	};
	
	
	getQuestionList = function(){
		var dataArray = new Array();
		var continueFlg = true;
		
		$(".chouti").each(function(i, v) {
			var _qtype = $(this).attr('qtype');
			var _question = $(this).find('div.edit_title input').val();
			var textarea = "";
			var _correct = 0;
			var optionArray = new Array();
			var correctArray = new Array();
			if (_qtype <= 2) {
				$(this).find('div.ti').each(function() {
					var _options = $(this).find('input[type="text"]');
					_options.each(function() {
						var isCorrect = $(this).parent().siblings("div").find('input').prop("checked") == true ? "1": 0;
						optionArray.push($(this).val()+ "_&_"+ isCorrect);
						if(isCorrect == '1'){
							_correct += 1;
						}
					});
					
//						correctArray.push(_correct);
				});
			} else if(_qtype == 3) {
				$(this).find('div.ti').each(function() {
					var _options = $(this).find('input[type="hidden"]');
					_options.each(function() {
						var isCorrect = $(this).parent().siblings("div").find('input').prop("checked") == true ? "1": 0;
						optionArray.push($(this).val()+ "_&_"+ isCorrect);
						if(isCorrect == '1'){
							_correct += 1;
						}
					});
					
//					correctArray.push(_correct);

				});
			} else if (_qtype==4) {
				$(this).find('div.ti').each(function() {
					var _options = $(this).find('input[type="text"]');
					_options.each(function() {

						var isCorrect = $(this).parent().siblings("div").text();
						optionArray.push($(this).val()+ "_&_"+ isCorrect);
						if(isCorrect == '1'){
							_correct += 1;
						}
					});
					
//						correctArray.push(_correct);
				});
			}else if(_qtype == 5) {
				var dirId = $(this).find('div.tuxing input')[0].id.split("_")[1];
				_correct += 1;
				var textarea=$(this).find('div.tuxing textarea').val();		
				if(textarea==''){				
					textarea=" ";
				}
				var uploadFilePath=$("#uploadFilePath_" + dirId).val();
				if(uploadFilePath==''){				
					uploadFilePath=" ";
				}
				text = textarea+"_&_"+uploadFilePath;
				optionArray.push(text);
			} else if(_qtype == 6) {
				_correct += 1;
				textarea = $(this).find('div.jianda textarea').val();
				optionArray.push(textarea);
			} else if(_qtype == 7) {
				_correct += 1;
				var r = /^.+?\【(.+?)\】.+?\【(.+?)\】.*$/;
				textarea = $(this).find('div.tiankong textarea').val();
				var content = r.exec(textarea);
				var correct = "";
				for(var i in content){
					if(i > 0){
						textarea = textarea.replace('【' + content[i] + '】' ,'【          】');
						correct += content[i] + "==@@==";
					}
				}
				optionArray.push(textarea + "_&_" + correct);
			}
			
			
//			if(_qtype > 4 && !textarea){
//				continueFlg = false;
//			}
//			
//			
			//判断内容项目不能未空
			console.log("optionArray",optionArray)
			if(optionArray.length == 0 ){
				alertMsg("请设置第" + (i+1) + "题目内容！");
				//出现错误直接置空
				dataArray = new Array();
				continueFlg = false;
				return false;
			}
			console.log("_correct",_correct)
			if(_correct == 0){
				alertMsg("请设置第" + (i+1) + "题目的正确答案！");
				continueFlg = false;
				return false;
			}
			
			
			var dataStr = _qtype + "_#_" + _question + "_#_" + optionArray.join("]=[");
			dataArray.push(dataStr);
		});
		if(dataArray.length <= 0 && continueFlg){
			alertMsg("请添加试卷内容！");
			return false;
		}else if(dataArray.length > 0 && !continueFlg){
			return false;
		}
		
		return dataArray.join("_@_");
	}

	onPublish = function(isQuiz){
		if(isQuiz == 1){
			var url="/quiz/publish.html",data={
					quizId:$("#quizId").val()
				};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					go2Page('/quiz/list.html');
				}else{
					alertMsg("抱歉，因为网络问题该试卷发布失败，请重新发布！");
				}
			});
		}else{
			var url="/survey/publish.html",data={
					surveyId:$.trim($("#surveyId").val())
				};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					go2Page('/survey/list.html');
				}else{
					alertMsg("抱歉，因为网络问题该问卷调查发布失败，请重新发布！");
				}
			});
		}
	};
	
	onSave = function(type){
		var url="/quiz/publish.html",data={
				quizId:$("#quizId").val(),
				type:'0'
			};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				go2Page('/quiz/list.html');
			}else{
				alertMsg("抱歉，因为网络问题该试卷发布失败，请重新发布！");
			}
		});
	}
	//返回 设置调查内容 界面
	backSurveyContent = function(){
		var _surveyId = $.trim($("#surveyId").val());
//		alert(_surveyId)
		window.location.href=ctx+"/get/survey/content.html?surveyId="+_surveyId;
	}
//	onSaveSurvey = function(){
//		alert(getSurveyQuestionList());
////		var url="/survey/saveSurvey.html",data={
////				surveyId:$.trim($("#surveyId").val())
////			};
////		postAjaxRequest(url, data, function(result){
////			if(result.status == 1){
////				go2Page('/survey/list.html');
////			}else{
////				alertMsg("抱歉，因为网络问题该问卷调查保存失败，请重试！");
////			}
////		});
//	}
	//更新编辑状态
	updateEditState = function() {
		var url = "/quiz/update_edit_state.html",
			data = {
				quizId : $.trim($("#quizId").val()),
				isEdit : 0
			}
		postAjaxRequest(url, data, function(result){
			
		});
	}
	
	//更新问卷调查编辑状态
	updateSurveyEditState = function() {
		var url = "/survey/update_edit_state.html",
			data = {
				surveyId : $.trim($("#surveyId").val()),
				isEdit : 0
			}
		postAjaxRequest(url, data, function(result){
			
		});
	}
	//显示试卷拥有权限
	changeIcon = function(owner, createId, isPublish) {
		//初始页面，列表无数据
		if (typeof(owner) == "undefined") {
			var html = '<li id="li_create"><button type="button" style="background-color:#43b4c6"' +  
				'onClick="javascript:onCreateExamination()"><span class="icon-file-alt"/></button></li>';
			$("#ul_li_focus_events").html(html);
		} else {
			//account下其他人的试卷
			if ((createId != cookie.getCookie('CUID')) && (owner != cookie.getCookie('CUID'))) {
				var html = '<li id="li_create"><button type="button" style="background-color:#43b4c6"' +  
				'onClick="javascript:onCreateExamination()"><span class="icon-file-alt"/></button></li>' + 
				'<li id="li_copy"><button type="button" style="background-color:#ee625e;"' + 
				'onclick="javascript:onCopyQuiz()"><span class="icon-copy"/></button></li>' +
				'<li id="li_preview"><button type="button" style="background-color:rgb(113,174,145);"' + 
				'data-toggle="modal" data-target="#quizPreviewModal"><span class="icon-eye-open"/></button></li>' + 
				'<li id="li_quote"><button type="button" style="background-color:rgb(101,170,221);"' +
				'onclick="javascript:onQuoteQuiz()"><span class="icon-sitemap"></button></li>';
				$("#ul_li_focus_events").html(html);
			//别人分享过来的试卷
			} else if ((createId != cookie.getCookie('CUID')) && (owner == cookie.getCookie('CUID'))) {
				var html = '<li id="li_create"><button type="button" style="background-color:#43b4c6"' +  
				'onClick="javascript:onCreateExamination()"><span class="icon-file-alt"/></button></li>' + 
				'<li id="li_edit"><button type="button" style="background-color:#ee625e;"' + 
				'onClick="javascript:onEditExamination()"><span class="icon-edit"/></button></li>' +
				'<li id="li_preview"><button type="button" style="background-color:rgb(113,174,145);"' + 
				'data-toggle="modal" data-target="#quizPreviewModal"><span class="icon-eye-open"/></button></li>' +
				'<li id="li_quote"><button type="button" style="background-color:rgb(101,170,221);"' +
				'onclick="javascript:onQuoteQuiz()"><span class="icon-sitemap"></button></li>';
				$("#ul_li_focus_events").html(html);
			//自己发布的试卷
			} else if (isPublish == 1) {
				var html = '<li id="li_create"><button type="button" style="background-color:#43b4c6"' +  
				'onClick="javascript:onCreateExamination()"><span class="icon-file-alt"/></button></li>' + 
				'<li id="li_delete"><button type="button" style="background-color:rgb(113,174,145);"' +
				'onClick="javascript:onDeleteExamination()"><span class="icon-trash"/></button></li>' +
				'<li id="li_copy"><button type="button" style="background-color:#ee625e;"' + 
				'onclick="javascript:onCopyQuiz()"><span class="icon-copy"/></button></li>' +
				'<li id="li_preview"><button type="button" style="background-color:rgb(113,174,145);"' + 
				'data-toggle="modal" data-target="#quizPreviewModal"><span class="icon-eye-open"/></button></li>' +
				'<li id="li_quote"><button type="button" style="background-color:rgb(101,170,221);"' +
				'onclick="javascript:onQuoteQuiz()"><span class="icon-sitemap"></button></li>' +
				'<li id="li_cancelPublish"><button type="button" style="background-color:rgb(101,170,221);"' +
				'onclick="javascript:onCancelPublish()"><span class="icon-paper-clip"></button></li>';
				$("#ul_li_focus_events").html(html);
			//自己未发布的试卷
			} else {
				if (owner == cookie.getCookie('CUID')) {//未分享
				var html = '<li id="li_create"><button type="button" style="background-color:#43b4c6"' +  
				'onClick="javascript:onCreateExamination()"><span class="icon-file-alt"/></button></li>' + 
				'<li id="li_edit"><button type="button" style="background-color:#ee625e;"' + 
				'onClick="javascript:onEditExamination()"><span class="icon-edit"/></button></li>' +
				'<li id="li_delete"><button type="button" style="background-color:rgb(113,174,145);"' +
				'onClick="javascript:onDeleteExamination()"><span class="icon-trash"/></button></li>' +
				'<li id="li_share"><button type="button" style="background-color:#43b4c6;"' +
				'onclick="javascript:onShareQuiz()"><span class="icon-share-alt"/></button></li>' + 
				'<li id="li_copy"><button type="button" style="background-color:#ee625e;"' + 
				'onclick="javascript:onCopyQuiz()"><span class="icon-copy"/></button></li>' +
				'<li id="li_preview"><button type="button" style="background-color:rgb(113,174,145);"' + 
				'data-toggle="modal" data-target="#quizPreviewModal"><span class="icon-eye-open"/></button></li>' +
				'<li id="li_quote"><button type="button" style="background-color:rgb(101,170,221);"' +
				'onclick="javascript:onQuoteQuiz()"><span class="icon-sitemap"></button></li>';
				$("#ul_li_focus_events").html(html);
				}else{//已分享
					var html = '<li id="li_create"><button type="button" style="background-color:#43b4c6"' +  
					'onClick="javascript:onCreateExamination()"><span class="icon-file-alt"/></button></li>' + 
					'<li id="li_edit"><button type="button" style="background-color:#ee625e;"' + 
					'onClick="javascript:onEditExamination()"><span class="icon-edit"/></button></li>' +
					'<li id="li_delete"><button type="button" style="background-color:rgb(113,174,145);"' +
					'onClick="javascript:onDeleteExamination()"><span class="icon-trash"/></button></li>' +
					'<li id="li_copy"><button type="button" style="background-color:#ee625e;"' + 
					'onclick="javascript:onCopyQuiz()"><span class="icon-copy"/></button></li>' +
					'<li id="li_preview"><button type="button" style="background-color:rgb(113,174,145);"' + 
					'data-toggle="modal" data-target="#quizPreviewModal"><span class="icon-eye-open"/></button></li>' +
					'<li id="li_quote"><button type="button" style="background-color:rgb(101,170,221);"' +
					'onclick="javascript:onQuoteQuiz()"><span class="icon-sitemap"></button></li>';
					$("#ul_li_focus_events").html(html);
				}
			}	
		}
	}
	//试卷权限描述
	iconDetail = function() {
		$("#li_create").mouseover(function(){	
			$("#text_tips").html('创建一个新的试卷');	
		})
		$("#li_edit").mouseover(function(){	
			$("#text_tips").html('编辑试卷信息和内容');	
		})
		$("#li_delete").mouseover(function(){	
			$("#text_tips").html('删除此试卷');	
		})
		$("#li_share").mouseover(function(){	
			$("#text_tips").html('试卷编辑权限共享');	
		})
		$("#li_copy").mouseover(function(){	
			$("#text_tips").html('复制此试卷，快速生成一个相似试卷');	
		})
		$("#li_preview").mouseover(function(){	
			$("#text_tips").html('试卷学习预览');	
		})
		$("#li_quote").mouseover(function(){	
			$("#text_tips").html('查看引用此试卷的课程列表');	
		})
		$("#li_cancelPublish").mouseover(function(){	
			$("#text_tips").html('取消发布该试卷');	
		})
		$("#ul_li_focus_events li").mouseout(function(){	
			$(this).each(function(){
				$("#text_tips").html("");	
			})		
		})
	}
	
	init();
});

