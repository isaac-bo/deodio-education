define(
		[ "jquery", "utils.dtree", "utils.cookie", "utils.list", "jquery.dot",
				"pagination", "utils", "jquery.scrolltofixed",
				"jquery.scroll.pagination", "jquery.validate",
				"bootstrap.select", "jquery.base","fileupload.common", "bootstrap.datepicker",
				"jquery.mCustomScrollbar","jquery.ui"],
		function($, tree, cookie, list, doT, paging) {
			//初始化函数
	
	
	paginationFunc = function(result){
		paging.pageTemplateDiv(result, list.setting.opts.tablePanel, list.setting.opts.tableDataTemplate, "presentation_data_page_panel","loadPageDataList");
	};
	
	delArray = new Array();
	//-------------------------------------------  quiz  start ---------------------------------------------
	
	onClickQuizTabLeft = function(e){
//		debugger;
		var quizId = $(e).find("[name='quizId']").val();
		var quizName = $(e).find("[name='quizName']").val();
		var quizPassScore = $(e).find("[name='quizPassScore']").val();
		var quizFinishTime = $(e).find("[name='quizFinishTime']").val();
		var quizMaxTimes = $(e).find("[name='quizMaxTimes']").val();
		var quizFinallyResultType = $(e).find("[name='quizFinallyResultType']").val();
		var quizPublishResultType = $(e).find("[name='quizPublishResultType']").val();
		
		var quizSafe = $(e).find("[name='quizSafe']").val();
//		debugger;
		$("#quizId").val(quizId);
		$("#quizName").val(quizName);
		$("#quizPassScore").val(quizPassScore);
		$("#quizFinishTime").val(quizFinishTime);
		$("#quizMaxTimes").val(quizMaxTimes);
		setCheckboxChecked(quizFinallyResultType,"quizFinallyResultType");
		setCheckboxChecked(quizPublishResultType,"quizPublishResultType");
		setCheckboxChecked(quizSafe,"quizSafe");
	}		
	//提交考试
	submitQuiz = function(){
		//添加 
		var saveData = new Array();
		$("div[id^='course_quiz_active_']").each(function(){
			var strData =new Array();
			$(this).find(".list_quiz").each(function(){
				strData.push($(this).val()==""?" ":$(this).val());
			});
			saveData.push(strData.join("]#["));
		});		
		 data = {
					quizJson:saveData.join("_#_"),
					courseId:$("#courseId").val(),
					subType:"save"
				};
	if($('#myQuizSettingContent').is(':hidden')==false){
		displayQuizList();
	};
	courseSettingCallBackFunc(data);
	delArray=[];
//		if($('#myQuizSettingContent').is(':hidden')==false){
//			displayQuizList();
//		}
//		
//		var quizData = [];
//		
//		$('div[id^=course_quiz_active_]').each(function(){
//			var id = $(this).attr('id').replace('course_quiz_active_','');
//			var selectId = $(this).find("[name='id']").val();
//			$('#course_quiz_original_'+id).each(function(){
////				alert('52===  quizFinallyResultType  '+$("#myQuizSettingContent input[name='quizFinallyResultType']:checked").val()+
////						'   quizPublishResultType   '+$("#myQuizSettingContent input[name='quizPublishResultType']:checked").val()+
////						'   quizSafe   '+$(this).find("[name='quizSafe']").val())
//				quizDataItem ={
//					id:selectId,
//					courseId:$("#courseId").val(),
//					quizId : $(this).find("[name='quizId']").val(),
//					quizName : $(this).find("[name='quizName']").val(),
//					quizAlias : $(this).find("[name='quizAlias']").val(),
//					quizPassScore : isNullFormat($(this).find("[name='quizPassScore']").val()) == ''?0:isNullFormat($(this).find("[name='quizPassScore']").val()),
//					quizFinishTime : isNullFormat($(this).find("[name='quizFinishTime']").val()) == ''?0:isNullFormat($(this).find("[name='quizFinishTime']").val()),
//					quizMaxTimes : isNullFormat($(this).find("[name='quizMaxTimes']").val()) == ''?0:isNullFormat($(this).find("[name='quizMaxTimes']").val()),
//					quizFinallyResultType :$("#myQuizSettingContent input[name='quizFinallyResultType']:checked").val(),
//					quizPublishResultType :$("#myQuizSettingContent input[name='quizPublishResultType']:checked").val(),
//					quizSafe : $(this).find("[name='quizSafe']").val()
//				};
//				
//				quizData.push(JSON.stringify(quizDataItem));
//			});
//		});
//		var data = {
//			quizJson:quizData.join('_#_')
//		};
////		alert(quizData)
//		courseSettingCallBackFunc(data);
	};
	//监听详细页面值变更
	quizKeyUp =  function(eid,etype,obj){
		var _evalue = $(obj).val();
		if(eid==''){
			eid=$("#myQuizSettingContent input[id='quizId']").val();
		};
		if(etype=='quizAlias'){
			$("#activeCourseQuizPanel input[id='value_quizAlias_"+eid+"']").val(_evalue);
			
		}else if(etype=='quizPassScore'){
			$("#activeCourseQuizPanel input[id='value_quizPassScore_"+eid+"']").val(_evalue);
			
		}else if(etype=='quizFinishTime'){
			$("#activeCourseQuizPanel input[id='value_quizFinishTime_"+eid+"']").val(_evalue);	
			
		}else if(etype=='quizMaxTimes'){
			$("#activeCourseQuizPanel input[id='value_quizMaxTimes_"+eid+"']").val(_evalue);
			
		}else if(etype=='quizFinallyResultType'){
			$("#activeCourseQuizPanel input[id='value_quizFinallyResultType_"+eid+"']").val(_evalue);		
			
		}else if(etype=='quizPublishResultType'){
			$("#activeCourseQuizPanel input[id='value_quizPublishResultType_"+eid+"']").val(_evalue);			
			
		}else if(etype=='quizSafe'){
			var quizSafe = [];
			$('#myQuizSettingContent input[name=quizSafe][type=checkbox]').each(function(){
				if($(this).prop('checked')){
					quizSafe.push($(this).val());
				}
			});	
			var quizSafeChecked=quizSafe.join(",");
			$("#activeCourseQuizPanel input[id='value_quizSafe_"+eid+"']").val(quizSafeChecked);	
		}
	};
	//加载课程考试详细数据
	loadQuizDetail = function(quizId,editFlg){
		var url="/course/quiz/load_data.html",data={
				quizId:quizId
		};
		postAjaxRequest(url, data, function(result){
			var data = result.data;
//			debugger;
			if(editFlg){
				data.modal_tittle = "编辑毕业考试";
			}else{
				data = {};
				data.modal_tittle = "新增毕业考试";
			}
			var template =  doT.template($("#course_quiz_template_modal").text());
			$("#quizModalContent").html(template(data));
			loadQuizExaminationList(1,'quizLeftTab','course_tab_left_quiz_template_modal',null);
			loadCourseQuizList();
			if(editFlg){
				displayQuizSetting(quizId);
			}
//			else{
//				$("#quizFinallyResultType1").prop("checked",true);
//				$("#quizPublishResultType1").prop("checked",true);
//				
//				$("#quizFinallyResultType1").addClass("checked");
//				$("#quizPublishResultType1").addClass("checked");
//			}
			initQuizControl();
		});
	}
	
	loadCourseQuizList = function(){
		var params = {
			keyword:'',
			courseId:$('#courseId').val(),
		},url="/course/quiz/load_added_list.html";
		postAjaxRequest(url, params, function(result){
			var template = doT.template($("#course_select_quiz_template_modal").text());	
			$('#addCourseQuizPanel').before(template(result));
		});
	};
	
	loadQuizExaminationList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:100000,
			keywords : $("#quizModalKeyWord").val(),
			quizCategory : [2,3],
			isPublish:1
		}, url = "/quiz/load_list.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "data_page_Panel", callbackFunc);
				$('#quizLeftTab').mCustomScrollbar();
				
				
				$('div[id^="course_quiz_original_"]').draggable({
					   helper: "clone",	
					   cursor: "move",
					   containment: '#containment-wrapper',
					   scroll: false 
				 });
				
				$('#activeCourseQuizPanel').droppable({
					drop:function(event,ui){
						var str = ui.draggable.clone().attr('id').split("_");
						var isDrop = true;
						$('div[id^=course_quiz_active_]').each(function(){
							var oid = $(this).attr('id').split('_')[3];
							if(oid == str[3]){
								isDrop = false;
							}
						});
						
						if(isDrop){
						
							$('#'+ui.draggable.clone().attr('id')).html();
							var source = '<div id="course_quiz_active_'+str[3]+'" class="box2 pull-left">'+
											$('#'+ui.draggable.clone().attr('id')).html()+
											'<div style="float: right;margin-top: -60px;cursor:pointer;position: relative;"><span class="icon-cog" onclick="javascript:displayQuizSetting(\''+str[3]+'\')"></span><a class="icon-minus" style="color:white;margin-left:10px;"  onclick="javascript:remove(\''+str[3]+'\',\'quiz\')"></a></div>'+
	                            		  '</div>';
							$('#addCourseQuizPanel').before(source);
						}
					}
				});
				
			}
		});
	};
	
	displayQuizSetting = function(id){
		$('#course_quiz_original_'+id).each(function(){
			onClickQuizTabLeft($(this));
		});
			
		
		$('#myQuizSettingContent').slideDown();
		$('#myQuizTabContent').slideUp();
		
		
		
	};
	
	remove = function(id,type){
		console.log('id--',id);
		console.log('type--',type);
		delArray.push(id);
		if(type==undefined){
			console.log('type---undefined');
			$('#'+'group_active_'+id).remove();
		}else{
			$('#course_'+ type +'_active_'+id).remove();
		}
		
	}
	displayQuizList = function(){
		$('#myQuizSettingContent').slideUp();
		$('#myQuizTabContent').slideDown();
		
		var quizId = $('#quizId').val();
		
		$('#course_quiz_original_'+quizId).each(function(){
			$(this).find("[name='quizName']").val($('#quizName').val());
			$(this).find("[name='quizAlias']").val($('#quizAlias').val());
			$(this).find("[name='quizPassScore']").val($('#quizPassScore').val());
			$(this).find("[name='quizFinishTime']").val($('#quizFinishTime').val());
			$(this).find("[name='quizMaxTimes']").val($('#quizMaxTimes').val());
			$(this).find("[name='quizFinallyResultType']").val($('#quizFinallyResultType1').prop('checked')?1:0)
			$(this).find("[name='quizPublishResultType']").val($('#quizPublishResultType1').prop('checked')?1:0);
			var quizSafe = [];
			$('input[name=quizSafe][type=checkbox]').each(function(){
				if($(this).prop('checked')){
					quizSafe.push($(this).val());
				}
			});
			
			$(this).find("[name='quizSafe']").val(quizSafe.join(","));
		});
		
		$('#quizId').val('');
		$('#quizName').val('');
		$('#quizPassScore').val('');
		$('#quizFinishTime').val('');
		$('#quizMaxTimes').val('');
		$('#quizFinallyResultType1').attr('checked',false).parent().find('label').removeClass('checked');
		$('#quizPublishResultType1').attr('checked',false).parent().find('label').removeClass('checked');
		$('input[name=quizSafe][type=checkbox]').attr('checked',false).parent().find('label').removeClass('checked');
	};
	
	//课程作业modal打开事件
	$('#quizModal').on('shown.bs.modal', function () {
		//resetMaterialDatail();
		var modalState = setModalState();
		var editFlg = modalState.editFlg;
		var quizId = modalState.targetId;
		loadQuizDetail(quizId,editFlg);
	});
	//课程作业关闭时触发事件
	$('#quizModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseSettingModelCloseCallBack();
	});
	
	initQuizControl = function(){
		customInput('quizFinallyResultType');
		customInput('quizPublishResultType');
		customInput('quizSafe');
		
		$('#courseQuizForm').myValidate({
			formCall:function(){submitQuiz();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		//页码设置
		$("#hid_default_quiz_template_modal_page").val(2);
		//实现滚动加载列表
//		$('#quizLeftTab').scrollPagination({
//			'postUrl' : ctx + '/quiz/load_list.html',
//			'postParams' : {
//				pageNo : 1,
//				pageSize:6,
//				keywords : $("#quizModalKeyWord").val(),
//				quizCategory : 0
//			},
//			'scrollTarget' : $("#quizLeftTab"),
//			'callBackLoad' : function(data) {
//				loadQuizExaminationDataListAfterScroll(data);
//			},
//			'beforeLoad' : function() {
//				this.postParams.pageNo = $("#hid_default_quiz_template_modal_page").val();
//				this.postParams.pageSize = 6;
//			}
//		});
	}
	
	//查询课程考试
	searchCourseQuiz = function(){
		loadQuizExaminationList(1,'quizLeftTab','course_tab_left_quiz_template_modal',null);
	}
	
	//滚动加载考场列表的回调函数
	loadQuizExaminationDataListAfterScroll = function(data) {
		
		var template = doT.template($("#course_tab_left_quiz_template_modal").text());
		
		$('#quizLeftTab').append(template({
			"data" : data.dataList
		}));
		
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_quiz_template_modal_page").val()) + 1;
			$("#hid_default_quiz_template_modal_page").val(finalPageNo);
		}
	};
	
	
	
	//-------------------------------------------  quiz  end ---------------------------------------------
	
	
	//-------------------------------------------  survey  start ---------------------------------------------
	
	onClickSurveyTabLeft = function(e){
		
		var surveyId = $(e).find("[name='surveyId']").val();
		var surveyName = $(e).find("[name='surveyName']").val();
		var surveyDesc = removeHtmlTags($(e).find("[name='surveyDesc']").val());
//		var surveyStartTime = $(e).find("[name='surveyStartTime']").val();
//		var surveyEndTime = $(e).find("[name='surveyEndTime']").val();
		var surveyStartTime=$('#trained_time_start').val();
		var surveyEndTime=$("#trained_time_expire").val();
		var surveyModel = $(e).find("[name='surveyModel']").val();
//		debugger;
		$("#surveyId").val(surveyId);
		$("#surveyName").val(surveyName);
		$("#oldSurveyName").val(surveyName);
		$("#surveyDesc").val(surveyDesc);
		$("#surveyStartTime").val(surveyStartTime);
		$("#surveyEndTime").val(surveyEndTime);
		setCheckboxChecked(surveyModel,"surveyModel");
	};
	
	//提交问卷调查
	submitSurvey = function(){

			//添加 
			var saveData = new Array();
			$("div[id^='course_survey_active_']").each(function(){
				var strData =new Array();
				$(this).find(".list_survey").each(function(){
					strData.push($(this).val()==""?" ":$(this).val());
				});
				saveData.push(strData.join("]#["));
			});
			
			 data = {
						surveyJson:saveData.join("_#_"),
						courseId:$("#courseId").val(),
						subType:"save"
					};
			 
		if(compareToDate($("#surveyStartTime").val(),$("#surveyEndTime").val())){
			alertMsg("起始时间不得大于结束时间");
			return;
		};
	
		
		if($('#mySurveySettingContent').is(':hidden')==false){
			displaySurveyList();
		};
		
	
	
		courseSettingCallBackFunc(data);
		delArray=[];
	}
	//监听详细页面值变更
	keyUp =  function(eid,etype,obj){
		var _evalue = $(obj).val();
		if(eid==''){
			eid=$("#mySurveySettingContent input[id='surveyId']").val();
		};
		if(etype=='desc'){
			$("#activeCourseSurveyPanel input[id='value_surveyDesc_"+eid+"']").val(_evalue);
			
		}else if(etype=='stime'){
			$("#activeCourseSurveyPanel input[id='value_surveyStartTime_"+eid+"']").val(_evalue);
			
		}else if(etype=='etime'){
			$("#activeCourseSurveyPanel input[id='value_surveyEndTime_"+eid+"']").val(_evalue);
			
		}else{
			$("#activeCourseSurveyPanel input[id='value_surveyModel_"+eid+"']").val(_evalue);
		}
	};
	
	
	
	checkSurveyName = function() {
		
		var flag = false;
		var url = "/survey/check/surveyName.html";
		var surveyName=$("#surveyName").val().trim();
		var oldSurveyName=$("#oldSurveyName").val().trim();
		var data = {
				surveyName : surveyName
					};
		if (surveyName.length>0&&surveyName != oldSurveyName) {
			postAjaxRequest(url, data, function(result){
				if (result.data) {
					flag = true;
				} 
			}, false);
		}
		return flag;
	}
	//加载课程调查问卷详细数据
	loadSurveyDetail = function(id,editFlg){
		var url="/course/survey/load_data.html",data={
				surveyId:id
		};
		postAjaxRequest(url, data, function(result){
			var data = result.data;
			var surveyId;
//			debugger;
			if(editFlg){
				data.modal_tittle = "编辑问卷调查";
				surveyId=result.data.surveyId;
			}else{
				data = {};
				data.modal_tittle = "新增问卷调查";				
			}			
			var template =  doT.template($("#course_survey_template_modal").text());
			$("#surveyModalContent").html(template(data));
			
			loadSurveyList(1,'surveyLeftTab','course_tab_left_survey_template_modal',null);
			loadCourseSurveyList();
			if(editFlg){
				displaySurveySetting(surveyId);
			}
			
			
			initSurveyControl();
		});
	}
	
	loadCourseSurveyList = function(){
		var params = {
				keyword:'',
				courseId:$('#courseId').val(),
			},url="/course/survey/load_added_list.html";
			postAjaxRequest(url, params, function(result){
				var template = doT.template($("#table_select_survey_template_modal").text());	
				$("#addCourseSurveyPanel").before(template(result));
			});
	};
	
	//加载右侧问卷调查列表
	loadSurveyList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {	
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:100000,
			keywords : $("#surveyModalKeyWord").val(),
			isPublish:1,
			course_start_time:$("#trained_time_start").val(),
			course_expire_time:$("#trained_time_expire").val()
		}, url = "/survey/load_course_survey_list.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "data_page_Panel", callbackFunc);
				
				$('#surveyLeftTab').mCustomScrollbar();
				$('div[id^="course_survey_original_"]').draggable({
					   helper: "clone",	
					   cursor: "move",
					   containment: '#containment-wrapper',
					   scroll: false 
				 });
				
				$('#activeCourseSurveyPanel').droppable({
					drop:function(event,ui){
						var str = ui.draggable.clone().attr('id').split("_");
						var isDrop = true;
						$('div[id^=course_survey_active_]').each(function(){
							var oid = $(this).attr('id').split('_')[3];
							if(oid == str[3]){
								isDrop = false;
							}
						});
						
						if(isDrop){
						
							$('#'+ui.draggable.clone().attr('id')).html();
							var source = '<div id="course_survey_active_'+str[3]+'" class="box2 pull-left">'+
											$('#'+ui.draggable.clone().attr('id')).html()+
											'<div style="float: right;margin-top: -45px;cursor:pointer;position: relative;"><span class="icon-cog" onclick="javascript:displaySurveySetting(\''+str[3]+'\')"></span><a class="icon-minus" style="color:white;margin-left:10px;"  onclick="javascript:remove(\''+str[3]+'\',\'survey\')"></a></div>'+
	                            		  '</div>';
							$('#addCourseSurveyPanel').before(source);
							
							var surveyStartTime=$('#trained_time_start').val();
							var surveyEndTime=$("#trained_time_expire").val();
							$("#activeCourseSurveyPanel input[id^='value_surveyStartTime']").val(surveyStartTime);
						    $("#activeCourseSurveyPanel input[id^='value_surveyEndTime']").val(surveyEndTime);
							
							
						}
					}
				});
				
			}
		});
	};
	
	displaySurveySetting = function(id){
		$('#course_survey_original_'+id).each(function(){
			onClickSurveyTabLeft($(this));
		});					
		$('#mySurveySettingContent').slideDown();
		$('#mySurveyTabContent').slideUp();
//		var params = {
//				surveyId:id,
//				courseId:$('#courseId').val()
//			}, url = "/survey/load_add_survey_info.html";
//			postAjaxRequest(url, params, function(result) {
//				
//				if(result.status){				
//					var surveyEndTime=result.data.surveyEndTime
//					if(surveyEndTime==null){
//						$("#surveyEndTime").val($("#trained_time_expire").val());
//					}else{
//						$("#surveyEndTime").val(dateFormat(surveyEndTime));
//					}
//					var surveyStartTime=result.data.surveyStartTime
//					if(surveyStartTime==null){
//						$("#surveyStartTime").val($("#trained_time_start").val());
//					}else{
//						$("#surveyStartTime").val(dateFormat(surveyStartTime));
//					}
//					
//					
//					$("#surveyId").val(result.data.surveyId);
//					$("#surveyName").val(result.data.surveyName).attr("srcvalue",result.data.surveyName);
//					$("#oldSurveyName").val(result.data.surveyName);					
//					$("#surveyDesc").val(result.data.surveyDesc);
//					var surveyModel=result.data.surveyModel;				
//					setCheckboxChecked(surveyModel,"surveyModel");
//				}
//			});			
	};
	
	
	displaySurveyList = function(){
		$('#mySurveySettingContent').slideUp();
		$('#mySurveyTabContent').slideDown();
		
		var surveyId = $('#surveyId').val();
//		alert(surveyId);
		$('#course_survey_original_'+surveyId).each(function(){
			$(this).find("[name='surveyName']").val($('#surveyName').val());
			$(this).find("[name='surveyDesc']").val($('#surveyDesc').val());
			$(this).find("[name='surveyStartTime']").val($('#surveyStartTime').val());
			$(this).find("[name='surveyEndTime']").val($('#surveyEndTime').val());
			$(this).find("[name='surveyModel']").val($('#surveyModel1').prop('checked')?0:1)
		});
		
		
		$('#surveyId').val('');
		$('#surveyName').val('');
		$('#surveyDesc').val('');
		$('#surveyStartTime').val('');
		$('#surveyEndTime').val('');
		$('#surveyModel1').attr('checked',true).parent().find('label').addClass('checked');
		$('#surveyModel2').attr('checked',false).parent().find('label').removeClass('checked');
	};
	
	
	//课程作业modal打开事件
	$('#surveyModal').on('shown.bs.modal', function () {
		var modalState = setModalState();
		var editFlg = modalState.editFlg;
		var surveyId = modalState.targetId;	
		loadSurveyDetail(surveyId,editFlg);
	});
	//课程问卷调查关闭时触发事件
	$('#surveyModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseSettingModelCloseCallBack();
	});
	
	initSurveyControl = function(){
		customInput('surveyModel');
		
		//日期控件
		$(".form_datetime").datetimepicker({
	        format: "yyyy-mm-dd",
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			startDate:$('#trained_time_start').val(),
			endDate:$("#trained_time_expire").val()
	    });
		
		$('#courseSurveyForm').myValidate({
			formCall:function(){submitSurvey();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		//页码设置
		$("#hid_default_survey_template_modal_page").val(2);
		//实现滚动加载列表
//		$('#surveyLeftTab').scrollPagination({
//			'postUrl' : ctx + '/survey/load_survey_list.html',
//			'postParams' : {
//				pageNo : 1,
//				pageSize:6,
//				keywords : $("#surveyModalKeyWord").val()
//			},
//			'scrollTarget' : $("#surveyLeftTab"),
//			'callBackLoad' : function(data) {
//				loadSurveyModalDataListAfterScroll(data);
//			},
//			'beforeLoad' : function() {
//				this.postParams.pageNo = $("#hid_default_survey_template_modal_page").val();
//				this.postParams.pageSize = 6;
//			}
//		});
	}
	
	//查询课程考试
	searchCourseSurvey = function(){
		loadSurveyList(1,'surveyLeftTab','course_tab_left_survey_template_modal',null);
//		loadQuizExaminationList(1,'surveyLeftTab','course_tab_left_survey_template_modal',null);
	}
	
	//滚动加载问卷调查的回调函数
	loadSurveyModalDataListAfterScroll = function(data) {
		
		var template = doT.template($("#course_tab_left_survey_template_modal").text());
		
		$('#surveyLeftTab').append(template({
			"data" : data.dataList
		}));
		
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_survey_template_modal_page").val()) + 1;
			$("#hid_default_survey_template_modal_page").val(finalPageNo);
		}
	};
	//-------------------------------------------  survey  end ---------------------------------------------
	
	
	//-------------------------------------------  material  start ---------------------------------------------
	//提交作业
	submitMaterial = function(){
		
		var materialData = {
				id:$("#materialId").val(),
				courseId:$("#courseId").val(),
				materialSize:$("#materialSize").val(),
				materialName:$("#zipFileName").val(),
				materialAttachName:$("#materialGenerateName").val()
		};
		var data = {
				materialJson:JSON.stringify(materialData),
				attachId:$.trim($("#attachId").val())
		}
		console.log('data-------',data);
		courseSettingCallBackFunc(data);
	}
	//加载课程文件详细数据
	loadMaterialDetail = function(materialId){
		var url="/course/material/load_data.html",data={
				materialId:materialId
		};
		postAjaxRequest(url, data, function(result){
			var data = result.data;
			data.modal_tittle = "编辑文件";
			var template =  doT.template($("#course_material_template_modal").text());
			$("#materialModalContent").html(template(data));
			initMaterialControl();
		});
	}
	
	//初始化（清空）页面数据
	resetMaterialDatail = function(){
		 //初始化页面
		//if('10013'==$("#uploadAttachment").val()){
		var data = {};
		data.modal_tittle = "上传文件";
		var template =  doT.template($("#course_material_template_modal").text());
		$("#materialModalContent").html(template(data));
		 initMaterialControl();
		//}else{
		 //alertMsg("暂无上传权限");
		//}
	}
	//课程作业modal打开事件
	$('#materialModal').on('shown.bs.modal', function () {	
		resetMaterialDatail();
		var modalState = setModalState();
		var editFlg = modalState.editFlg;
		var materialId = modalState.targetId;
		if(editFlg){
			loadMaterialDetail(materialId);
		}else{
			resetMaterialDatail();
		}
	});
	//课程文件关闭时触发事件
	$('#materialModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseSettingModelCloseCallBack();
	});
	
	initMaterialControl = function(){
		$('#courseMaterialForm').myValidate({
			formCall:function(){submitMaterial();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		var url = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#courseMaterialId").val())+'&r=' + Math.floor(Math.random() * 100);
		uploadFileJqProcess('materialUploadFile',url,
			 {accountId:cookie.getCookie('CAID'),attachRelType:"9",isModel:true},
			 1,'*',true,function(e,data){
				 console.log('data=====',data);
				 console.log('data.total=====',data.total);
				$("#zipFileName").val( data.result.data.attachName);
				$("#materialGenerateName").val(data.result.data.generateName);
				$("#attachId").val(data.result.data.id);
				$("#materialSize").val(formatFileSizeOriginal(data.total));
			},function(){
				
			},function(data){
				
//			 	var progress = parseInt(data.loaded / data.total * 100, 10);
//	        	$("#progressBar .bar").css('width',progress+'%');
//	        	
				//var srcimg = imgStatic+data.attachUrl+data.generateName;
//				debugger;
//				var temp = data.generateName;
//				var size = file.size;
//				var formatFileSize = formatFileSizeOriginal(size);
////				debugger;
//				$("#materialGenerateName").val(temp);
//				$("#attachId").val(data.id);
//				$("#materialSize").val(formatFileSize);
			});
		
		
	}
	
	//-------------------------------------------  material  end ---------------------------------------------
	
	
	//-------------------------------------------  homework  start ---------------------------------------------

	//提交作业
	submitHomework = function(){
		
		//截止日期
	
		
	
		
		if($("#homeworkFileName").val()==''){
			alertMsg("请上传作业");
			return;
		};
		
		
		
		var homeworData = {
				id:$("#homeworkId").val(),
				courseId:$("#courseId").val(),
				homeworkName:$("#homeworkName").val(),
				homeworkEndTime:$("#homeworkEndTime").val(),
				homeworkIsDelay:$("input[name='homeworkIsDelay']:checked").val(),
				homeworkAccessAuth:$("input[name='homeworkAccessAuth']:checked").val(),
				homeworkIsPublish:$("input[name='homeworkIsPublish']:checked").val(),
				homeworkRequire:$("#homeworkRequire").val(),
				homeworkAttach:$("#homeworkAttachment").val()
		};
		var data = {
				homeworkJson:JSON.stringify(homeworData),
				attachId:$.trim($("#homeworkAttachmentId").val())
		}
		courseSettingCallBackFunc(data);
	}
	
	//加载课程作业详细数据
	loadHomeworkDetail = function(homeworkId){
		var url="/course/homework/load_data.html",data={
				homeworkId:homeworkId
		};
		postAjaxRequest(url, data, function(result){
			var data = result.data;
			data.modal_tittle = "编辑作业";
			data.uploadFile=$("#downloadAttachment").val();
			var template =  doT.template($("#course_homework_template_modal").text());
			$("#homeworkModalContent").html(template(data));
			initHomeworkControl();
		});
	}
	//初始化（清空）页面数据
	resetHomeworkDatail = function(){
		 //初始化页面
		var data = {};
		data.modal_tittle = "新建作业";
		data.uploadFile=$("#uploadAttachment").val();
		var template =  doT.template($("#course_homework_template_modal").text());
		$("#homeworkModalContent").html(template(data));
		$("#homeworkIsDelay1").prop("checked",true);
		$("#label_homeworkIsDelay1").addClass("checked");
		
		$("#homeworkAccessAuth1").prop("checked",true);
		$("#label_homeworkAccessAuth1").addClass("checked");
		
		$("#homeworkIsPublish1").prop("checked",true);
		$("#label_homeworkIsPublish1").addClass("checked");
		initHomeworkControl();

	}
	//校验名字
	checkName = function(){
		var newHomeworkName=$("#homeworkName").val();
		var oldHomeworkName=$("#hiddenhomeworkName").val();
		var courseId=$("#courseId").val();
		var flag=false;
		if(newHomeworkName.length>0&&newHomeworkName!=oldHomeworkName){
		var url="/course/homework/getCheck_homework.html",data={
				homeworkName:newHomeworkName,
				courseId:courseId
		}
		postAjaxRequest(url, data,function(result){
			flag=result.data;
	    },false);
		return flag;
	}
	}
	//课程作业modal打开事件
	$('#homeworkModal').on('shown.bs.modal', function () {

		var modalState = setModalState();
		var editFlg = modalState.editFlg;
		var homeworkId = modalState.targetId;
		if(editFlg){
			loadHomeworkDetail(homeworkId);
		}else{
			resetHomeworkDatail();
		}
		 
	});
	//课程作业关闭时触发事件
	$('#homeworkModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseSettingModelCloseCallBack();
	});

	initHomeworkControl = function(){
		//作业
		customInput('homeworkIsDelay');
		customInput('homeworkAccessAuth');
		customInput('homeworkIsPublish');
		//日期控件
		$("#homeworkEndTime").datetimepicker({
	        format: "yyyy-mm-dd",
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			startDate:$('#trained_time_start').val(),
			endDate:$("#trained_time_expire").val()
	    });
		
		$('#courseHomeworkForm').myValidate({
			formCall:function(){submitHomework();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		//-------上传作业------------
		var url = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#homeworkId").val())+'&r=' + Math.floor(Math.random() * 100);
		uploadFileJqProcess('xhomeworkUploadFile',url,
			 {accountId:cookie.getCookie('CAID'),attachRelType:"10",isModel:true},
			 1,'*',true,function(e,data){
				 debugger;
				$("#homeworkFileName").val(data.result.data.attachName);
				$("#homeworkAttachment").val(data.result.data.generateName);
				$("#homeworkAttachmentId").val(data.result.data.id);
			},function(){
				
			},function(data){
			});
		
	}
	
	//-------------------------------------------  homework  end ---------------------------------------------
	//-------------------------------------------  notice  start ---------------------------------------------
	
	//提交作业
	submitNotice = function(){
		var noticeData = {
				id:$("#noticeId").val(),
				courseId:$("#courseId").val(),
				noticeName:$("#noticeName").val(),
				noticeContent:$("#noticeContent").val(),
				noticePublishTime:$("#noticePublishTime").val(),
				noticePublishScope:$("input[name='noticePublishScope']:checked").val()
		};
		var data = {
				noticeJson:JSON.stringify(noticeData)
		}
		courseSettingCallBackFunc(data);
	}
	
	//加载课程作业详细数据
	loadNoticeDetail = function(noticeId){
		var url="/course/notice/load_data.html",data={
				noticeId:noticeId
		};
		postAjaxRequest(url, data, function(result){
			var data = result.data;
			data.modal_tittle = "编辑公告";
			var template =  doT.template($("#course_notice_template_modal").text());
			$("#noticeModalContent").html(template(data));
			initNoticeControl();
		});
	}
	//初始化（清空）页面数据
	resetNoticeDatail = function(){
		 //初始化页面
		var data = {};
		data.modal_tittle = "新建公告";
		var template =  doT.template($("#course_notice_template_modal").text());
		$("#noticeModalContent").html(template(data));
		
		
		$("#noticePublishScope1").prop("checked",true);
		$("#label_noticePublishScope1").addClass("checked");
		
		
		initNoticeControl();
	}
	//课程消息modal打开事件
	$('#noticeModal').on('shown.bs.modal', function () {
		var modalState = setModalState();
		var editFlg = modalState.editFlg;
		var noticeId = modalState.targetId;
		if(editFlg){
			loadNoticeDetail(noticeId);
		}else{
			resetNoticeDatail();
		}
		 
	});
	//课程消息关闭时触发事件
	$('#noticeModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseSettingModelCloseCallBack();
	});
	
	initNoticeControl = function(){
		//作业
		customInput('noticePublishScope');
		//日期控件
		$("#noticePublishTime").datetimepicker({
	        format: "yyyy-mm-dd",
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			startDate:new Date(),
			endDate:$("#trained_time_expire").val()
	    });
		
		$('#courseNoticeForm').myValidate({
			formCall:function(){submitNotice();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
	}
	checkCourseNoticeName=function(){
		var flag = false;
		var noticeName=$("#noticeName").val().trim();
		var oldNoticeName=$("#oldNoticeName").val().trim();
		if(noticeName.length>0&&noticeName!=oldNoticeName){
			var url="/course/notice/checkNoticeName.html",data={
					noticeName:noticeName
			};
			postAjaxRequest(url, data, function(result){
				flag = result.data;
			},false);
		}
		return flag;
	}
	//-------------------------------------------  notice  end ---------------------------------------------
	
	//-------------------------------------------  related course  start ---------------------------------------------
	
	
	onClickCourseTabLeft = function(e){
//		debugger;
		var relatedCourseId = $(e).find("[name='courseId']").val();
		var courseName = $(e).find("[name='courseName']").val();
		var courseDesc = $(e).find("[name='courseDesc']").val();
		var courseType = $(e).find("[name='courseType']").val();
		var createName = $(e).find("[name='createName']").val();
		var createTime = $(e).find("[name='createTime']").val();
//		debugger;
		$("#relatedCourseId").val(relatedCourseId);
		$("#courseName").val(courseName);
		$("#courseDesc").val(courseDesc);
		$("#courseType").val(convertCourseType(courseType));
		$("#createName").val(createName);
		$("#createTime").val(createTime);
//		setCheckboxChecked(courseRelatedType,"courseRelatedType");
	}
	
	
	//提交保存相关课程
	submitRelatedCourse = function(){
		
		var relatedCourse = [];
		
		
		
		$('div[id^=course_required_active_]').each(function(){
			var relatedData = {
				id:'',
				courseId:$('#courseId').val(),
				relatedCourseId:$(this).attr('id').split('_')[3],
				courseRelatedType:$(this).attr('type') 
			};
			
			relatedCourse.push(JSON.stringify(relatedData));
		});
		var data = {
			relatedJson:relatedCourse.join("_#_")
		}
		
		courseSettingCallBackFunc(data);
	}
	
	//加载课程相关课程数据
	loadRelatedCourseDetail = function(relatedId,editFlg){
		var url="/course/related/load_data.html",data={
				relatedId:relatedId
		};
		postAjaxRequest(url, data, function(result){
			var data = result.data;
//			debugger;
			if(editFlg){
				data.modal_tittle = "编辑相关课程";
			}else{
			var	data = {};
				data.modal_tittle = "新增相关课程";
			}
//			
			var template =  doT.template($("#course_related_course_template_modal").text());
			$("#relatedCourseModalContent").html(template(data));
			loadCourseDataList(1,'courseLeftTab','course_tab_left_course_template_modal',null);
			loadSelectRelatedCourseDataList(relatedId,editFlg);
			initRelatedCourseControl();
		});
	}
	
	loadSelectRelatedCourseDataList = function(relatedId,editFlg){
		var params = {
			keywords:$.trim($('#relatedKeyWord').val()),
			courseId:$('#courseId').val(),
		},url="/course/related/load_list.html";
		
		postAjaxRequest(url, params, function(result){
			
			var template = doT.template($("#course_select_related_template_modal").text());
			$('#addCourseRelatedPanel').before(template(result));
			if(editFlg){
				$('div[id^=course_required_active_]').each(function(){
					var id = $(this).attr('id').split('_')[3];
					if(id == relatedId){
						if($(this).attr('type') == 1){
							$('#liRequired').removeClass('active');
							$('#liRecommend').addClass('active');
						}else{
							$('#liRequired').addClass('active');
							$('#liRecommend').removeClass('active');
						}
					}
				});
			}
			
			if($('#liRequired').hasClass('active')){
				displayRequiredCourseTabContent();
			}else{
				displayRecommendCourseTabContent();
			}
		});
	};
	
	//相关课程modal打开事件
	$('#relatedCourseModal').on('shown.bs.modal', function () {
		var modalState = setModalState();
		var editFlg = modalState.editFlg;
		var relatedCourseId = modalState.targetId;
		loadRelatedCourseDetail(relatedCourseId,editFlg);
		 
	});
	//课程相关课程关闭时触发事件
	$('#relatedCourseModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseSettingModelCloseCallBack();
	});
	
	//初始化相关课程页面
	initRelatedCourseControl = function(){
		
		customInput('courseRelatedType');
		
		$('#courseRelatedCourseForm').myValidate({
			formCall:function(){submitRelatedCourse();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
	}
	
	//查询课程
	searchCourseCourseDataList = function(){
		loadCourseDataList(1,'courseLeftTab','course_tab_left_course_template_modal',null);
	}
	
	loadCourseDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {		
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:100000,
			isPublish:1,
			isNotEdit:0,
			keywords : $("#courseModalKeyWord").val()
		}, url = "/course/load_list.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "data_page_Panel", callbackFunc);
				$('#courseLeftTab').mCustomScrollbar();
				
				
				$('div[id^="course_related_original_"]').draggable({
					   helper: "clone",	
					   cursor: "move",
					   containment: '#containment-wrapper',
					   scroll: false 
				 });
				
				
				$('.up_right_course_item').droppable({
					drop:function(event,ui){
						var str = ui.draggable.clone().attr('id').split("_");
						var isDrop = true;
						$('input[id^=course_required_active_]').each(function(){
							var oid = $(this).attr('id').split('_')[3];
							if(oid == str[3]){
								isDrop = false;
							}
						});
						
						if(isDrop){
						
							$('#'+ui.draggable.clone().attr('id')).html();
							var type = $('#liRequired').hasClass('active')?0:1;
							
							var obj = $('#'+ui.draggable.clone().attr('id'));
							
							var source = '<tr>'+
											'<td>'+obj.find('input[type=hidden][name=courseName]').val()+'</td>'+
											'<td class="text-center">'+convertCourseType(obj.find('input[type=hidden][name=courseType]').val())+'</td>'+
//											'<td class="text-center">'+obj.find('input[type=hidden][name=createTime]').val()+'</td>'+
//											'<td class="text-center">'+obj.find('input[type=hidden][name=createName]').val()+'</td>'+
											'<td class="text-center">'+
												'<button class="icon_2" type="button" onclick="deleteItemsElements(this);"></button>'+
											'</td>'+
											'<input name="operateType" value="0" type="hidden">'+
											'<input name="id" value="'+setElementId()+'" type="hidden">'+
											'<input id="course_required_active_'+str[3]+'" name="courseId" value="'+obj.find('input[type=hidden][name=courseId]').val()+'" type="hidden">'+
										 '</tr>';
							
							$('#table_content_package_items_elements').html($('#table_content_package_items_elements').html() + source);
						}
					}
				});
				
				
				$('#activeCourseRelatedPanel').droppable({
					drop:function(event,ui){
						
						var str = ui.draggable.clone().attr('id').split("_");
						var isDrop = true;
						$('div[id^=course_required_active_]').each(function(){
							var oid = $(this).attr('id').split('_')[3];
							if(oid == str[3]){
								isDrop = false;
							}
						});
						
						if(isDrop){
						
							$('#'+ui.draggable.clone().attr('id')).html();
							var type = $('#liRequired').hasClass('active')?0:1;
							var source = '<div type="'+type+'" id="course_required_active_'+str[3]+'" class="box2 pull-left " style="margin-left: 5px; margin-right: 5px;">'+
											$('#'+ui.draggable.clone().attr('id')).html()+
		                        		  '</div>';
							$('#addCourseRelatedPanel').before(source);
						}
					}
				});
				
			}
		});
	};
	
	
	displayRequiredCourseTabContent = function(){
		$('#liRequired').addClass('active');
		$('#liRecommend').removeClass('active');
		
		$('div[id^=course_required_active_]').each(function(){
			if($(this).attr('type') == 1){
				$(this).hide();
			}else{
				$(this).show();
			}
		});
	};
	
	displayRecommendCourseTabContent = function(){
		
		$('#liRecommend').addClass('active');
		$('#liRequired').removeClass('active');
		
		$('div[id^=course_required_active_]').each(function(){
			if($(this).attr('type') == 0){
				$(this).hide();
			}else{
				$(this).show();
			}
		});
		
	};
	
	//滚动加载考场列表的回调函数
	loadCourseDataListAfterScroll = function(data) {
		
//		var template = doT.template($("#course_tab_left_course_template_modal").text());
//		
//		$('#courseLeftTab').append(template({
//			"data" : data.dataList
//		}));
//		
//		if (data.dataList.length != 0) {
//			var finalPageNo = Number($("#hid_default_course_template_modal_page").val()) + 1;
//			$("#hid_default_course_template_modal_page").val(finalPageNo);
//		}
	};
	
	
	convertCourseType = function(type){
		var typeName = "";
		switch(type){
		  case '1' : 
		  case  1 : 
			  	typeName = "线上";break;
		  case '2' :
		  case  2  : 
			    typeName = "线下";break;
		  case '3' :
		  case  3 : 
			  	typeName = "直播";break;
		  default:
			  	typeName = "";break;
		}
		return typeName;
	}
	
	//-------------------------------------------  related course  end ---------------------------------------------
	
	
	//-------------------------------------------  upload attachment  start ---------------------------------------------
	//提交作业
	submitAttach = function(){
		var attachData = {
				attachId:$("#attachId").val(),
				attachName:$("#attachName").val(),
				attachUrl:$("#attachUrl").val()
		};
		courseDialogCallBackFun(JSON.stringify(attachData));
	}
	
	//初始化（清空）页面数据
	resetAttachDatail = function(relId,relType){
		 //初始化页面
		var data = {};
		data.modal_tittle = "上传文件";
		var template =  doT.template($("#upload_attachment_template_modal").text());
		$("#uploadAttachmentModalContent").html(template(data));
		initAttachControl(relId,relType);
	}
	//modal打开事件
	$('#uploadAttachmentModal').on('shown.bs.modal', function () {
		var modalState = setModalState();
		var relType = modalState.targetType || '6';
		var relId = modalState.targetId;
		resetAttachDatail(relId,relType);
		
	});
	//modal关闭时触发事件
	$('#uploadAttachmentModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseDialogCallBackFun();
	});
	
	initAttachControl = function(relId,relType){
		$('#uploadAttachmentForm').myValidate({
			formCall:function(){submitAttach();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		uploadFileJqProcess('attachUploadFile',undefined,{accountId:cookie.getCookie('CAID'),attachRelType:relType,isModel:true},ctx+'/commons/uploadAndUpdateAttach.html?presentationId='+$.trim(relId)+'&r=' + Math.floor(Math.random() * 100)
			,function(fileName){
				//alert(fileName);
				$("#zipFileName").val(fileName);
			}
			,function(data,file){
				//var srcimg = imgStatic+data.attachUrl+data.generateName;
				var temp = data.generateName;
//				var size = file.size;
//				var formatFileSize = formatFileSizeOriginal(size);
			
				$("#attachName").val(temp);
				$("#attachId").val(data.id);
				$("#attachUrl").val(data.attachUrl);
			},true,'上传附件');
	}
	
	//-------------------------------------------  upload attachment  end ---------------------------------------------
	
	
//-------------------------------------------  course package items elements start ---------------------------------------------
	
	
	onClickCourseTabLeft = function(e){
//		debugger;
		var courseId = $(e).find("[name='courseId']").val();
		var courseName = $(e).find("[name='courseName']").val();
		var courseDesc = $(e).find("[name='courseDesc']").val();
		var courseType = $(e).find("[name='courseType']").val();
		var createName = $(e).find("[name='createName']").val();
		var createTime = $(e).find("[name='createTime']").val();
//		debugger;
		var  courseInfo = {
			courseId:courseId,
			courseName: courseName,
			courseDesc:courseDesc,
			courseType:courseType,
			createName:createName,
			createTime:createTime
		};
		
		var res = {};
		res.data = [];
		res.data.push(courseInfo);
		
		var template =  doT.template($("#table_course_tab_left_course_template").text());
		$("#table_content_package_items_elements").prepend(template(res));
		
	}
	
	getPackageItemId = function(){
		return $("#coursePackageItemsElementsModal").find("#itemId").val();
	}
	
	savePackageItemId = function(itemId){
		$("#coursePackageItemsElementsModal").find("#itemId").val(itemId);
	}
	
	
	//提交保存相关课程
	submitItemsElements = function(){
		var elementsData = [];
		$("#table_content_package_items_elements").find("tr").each(function(){
			var itemId = getPackageItemId();
			var elementData = {
				id : $(this).find("[name='id']").val(),
				itemId:itemId,
				courseId:$(this).find("[name='courseId']").val(),
				operateType:$(this).find("[name='operateType']").val()
			}
			elementsData.push(elementData);
		});
		
		var data = JSON.stringify(elementsData);
		courseDialogCallBackFun(data);
	}
	
	//加载课程相关课程数据
	loadItemsElementsList = function(itemId){
		var url="/course/packages/content/elements/load_data.html",data={
				itemId:itemId,
				keyWord:$("#courseKeyWord").val()
		};
		postAjaxRequest(url, data, function(result){
			var template =  doT.template($("#table_course_tab_left_course_template").text());
			$("#table_content_package_items_elements").html(template(result));
//			debugger;
			loadCourseDataList(1,'courseLeftTab','course_tab_left_course_template_modal',null);
			initItemsElementsControl();
		});
	}
	
	//modal打开事件
	$('#coursePackageItemsElementsModal').on('shown.bs.modal', function () {
		var modalState = setModalState();
		var itemId = modalState.targetId;
		savePackageItemId(itemId);
		initPageContent();
		loadItemsElementsList(itemId);
		 
	});
	//关闭时触发事件
	$('#coursePackageItemsElementsModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		courseDialogCallBackFun();
	});
	
	deleteItemsElements = function(e){
		var container = $(e).parent().parent();
		var operateType = $(container).find("[name='operateType']");
		if($(operateType).val() == '0'){
			$(container).remove();
		}else{
			$(operateType).val('2');
			$(container).hide();
		}
	}
	
	//设置series 主键编号
	setElementId =function(elementId){
//		debugger;
		if(!elementId){
			elementId = UUID.prototype.createUUID();
		}
		return elementId;
	}
	
	initPageContent = function(){
		 //初始化页面
		var data = {};
		data.modal_tittle = "课程包元素管理";
		var temp =  doT.template($("#course_package_items_elements_template_modal").text());
		$("#coursePackageItemsElementsModalContent").html(temp(data));
	}
	
	//初始化相关课程页面
	initItemsElementsControl = function(){
		
//		customInput('courseRelatedType');
		
		$('#courseItemsElementsForm').myValidate({
			formCall:function(){submitItemsElements();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
//		//页码设置
//		$("#hid_default_course_template_modal_page").val(2);
//		//实现滚动加载列表
//		$('#courseLeftTab').scrollPagination({
//			'postUrl' : ctx + '/course/load_list.html',
//			'postParams' : {
//				pageNo : 1,
//				pageSize:6,
//				keywords : $("#courseModalKeyWord").val()
//			},
//			'scrollTarget' : $("#courseLeftTab"),
//			'callBackLoad' : function(data) {
//				loadCourseDataListAfterScroll(data);
//			},
//			'beforeLoad' : function() {
//				this.postParams.pageNo = $("#hid_default_course_template_modal_page").val();
//				this.postParams.pageSize = 6;
//			}
//		});
	}
	
//	//查询课程
//	searchCourseCourseDataList = function(){
//		loadCourseDataList(1,'courseLeftTab','course_tab_left_course_template_modal',null);
//	}
//	
//	loadCourseDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {
//		if (pageNo == undefined)
//			pageNo = 1;
//		var params = {
//			pageNo : pageNo,
//			pageSize:6,
//			keywords : $("#courseModalKeyWord").val()
//		}, url = "/course/load_list.html";
//		postAjaxRequest(url, params, function(result) {
//			if(result.status == 1){
//				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "data_page_Panel", callbackFunc);
//			}
//		});
//	};
//	
//	//滚动加载考场列表的回调函数
//	loadCourseDataListAfterScroll = function(data) {
//		
//		var template = doT.template($("#course_tab_left_course_template_modal").text());
//		
//		$('#courseLeftTab').append(template({
//			"data" : data.dataList
//		}));
//		
//		if (data.dataList.length != 0) {
//			var finalPageNo = Number($("#hid_default_course_template_modal_page").val()) + 1;
//			$("#hid_default_course_template_modal_page").val(finalPageNo);
//		}
//	};
	
	//-------------------------------------------  course package items elements  end ---------------------------------------------
	
	//-------------------------------------------  group  course start ---------------------------------------------------
	
	
	var initloadGroupCourseModalContent = function(){
		 //初始化页面
		var data = {};
		data.modal_tittle = "小组待选课程";
		var groupId = $("#groupId").val();
		if(!groupId){
			alertMsg("小组编号为空，请设置小组编号");
			return;
		}
		var temp =  doT.template($("#group_course_to_select_template_modal").text());
		$("#groupCourseToSelectModalContent").html(temp(data));
		customInput('allGroupCourseRelatedId');
		checkOrUncheckAll('allGroupCourseRelatedId','groupCourseRelatedIdCheckBox');
		loadGroupCourseALLDataList();
	}
	
	//查询课程
	searchGroupCourseToSelectList = function(){
		loadGroupCourseALLDataList();
	}
	
   loadGroupCourseALLDataList = function(pageNo) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:6,
			keywords : $("#groupCourseKeywords").val(),
			groupId : $("#groupId").val()
		}, url = "/group/load_course_to_select.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, "groupCourseToSelectList", "group_course_to_select_data_template", "group_course_to_select_data_page_Panel", "loadGroupCourseALLDataList");
				customInput('groupCourseRelatedIdCheckBox');
			}
		});
	};
	
	submitGroupCourse = function(){
		
		 var courseIds = getCheckdRowIdVals("groupCourseRelatedIdCheckBox");
		if(!courseIds){
			alertMsg('未选择课程');
			return;
		}else{
			var url="/group/add_course.html",data={
					groupId : $("#groupId").val(),
					courseIds:courseIds
			};
			postAjaxRequest(url, data, function(result){
				if($('.table-item').hasClass('on')){
				smallIconShow(1);
				}else{
				mediumIconShow(1);
				}
			});
		}
	};
	
	//modal打开事件
	$('#groupCourseToSelectModal').on('shown.bs.modal', function () {
		initloadGroupCourseModalContent();
	});
	
	//关闭时触发事件
	$('#groupCourseToSelectModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		groupCourseDialogCallBackFun();
	});

	var getCheckdRowIdVals = function(name){
		var finalValArray = [];
		$("input[name='"+name+"']").each(function(){
		  if($(this).prop('checked')){
			  finalValArray.push($(this).attr("id"));
		   };
		});
		return finalValArray.join();
	};
	//-------------------------------------------  group  course end -----------------------------------------------------
//-------------------------------------------  group  course package start ---------------------------------------------------
	
	
	var initloadGroupCoursePackageModalContent = function(){
		 //初始化页面
		var data = {};
		data.modal_tittle = "小组待选课程包";
		var groupId = $("#groupId").val();
		if(!groupId){
			alertMsg("小组编号为空，请设置小组课程包编号");
			return;
		}
		var temp =  doT.template($("#group_course_package_to_select_template_modal").text());
		$("#groupCoursePackageToSelectModalContent").html(temp(data));
		customInput('allGroupCoursePackageRelatedId');
		checkOrUncheckAll('allGroupCoursePackageRelatedId','groupCoursePackageRelatedIdCheckBox');
		loadGroupCoursePackageDataList();
	}
	
	var loadGroupCoursePackageDataList = function(pageNo) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:6,
			keywords : $("#groupCoursePackageKeywords").val(),
			groupId : $("#groupId").val()
		}, url = "/group/load_course_package_to_select.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, "groupCoursePackageToSelectList", "group_course_package_to_select_data_template", "group_course_package_to_select_data_page_Panel", "loadGroupCoursePackageDataList");
				customInput('groupCoursePackageRelatedIdCheckBox');
			}
		});
	};
	
	submitGroupCoursePackage = function(){
		
		 var coursePackageIds = getCheckdRowIdVals("groupCoursePackageRelatedIdCheckBox");
		debugger;
		if(!coursePackageIds){
			alertMsg('未选择课程');
			return;
		}else{
			var url="/group/add_course_package.html",data={
					groupId : $("#groupId").val(),
					coursePackagesIds:coursePackageIds
			};
			postAjaxRequest(url, data, function(result){
				loadGroupCoursePackageDataList();
			});
		}
	};
	//查询课程包
	searchGroupCoursePackageToSelectList = function(){
		loadGroupCoursePackageDataList();
	}
	//modal打开事件
	$('#groupCoursePackageToSelectModal').on('shown.bs.modal', function () {
		initloadGroupCoursePackageModalContent();
	});
	
	//关闭时触发事件
	$('#groupCoursePackageToSelectModal').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
		groupCourseDialogCallBackFun();
	});
	//-------------------------------------------  group  course package end -----------------------------------------------------
	checkCourseStartTime=function(){
		var homeworkEndTime= $("#homeworkEndTime").val();
		var courseStartTime= $("#startTime").val();
		if(courseStartTime.length>0){
			if(compareToDate(courseStartTime,homeworkEndTime)){
				alertMsg('作业截收时间不能小于课程开始时间');
			}
		}
	}
	//------------上传附件---------------------	
	registerKeyUp=function(obj,showAttach,AttachName,AttachId,attachRelType){
		
		var uploadUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+
			$.trim($("#courseId").val())+'&attachRelType='+attachRelType+ '&r='+ 
			Math.floor(Math.random() * 100);
		var uploadId=$(obj).attr('id');
		var f=$(obj).val();
		if(f == null || f ==undefined || f == ''){
            return false;
        }
		var uploadFile = $(obj)[0].files[0].size;
		var formatFileSize = formatFileSizeOriginal(uploadFile);
//		alert('showAttach:  '+showAttach+"   AttachName:   "+AttachName+"   AttachId:  "+AttachId+"   attachRelType:   "+attachRelType)
        uploadFileJqProcess(uploadId,uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:attachRelType,isModel:true},
			1,/(.|\/)(bmp|PNG|JPG|BMP|GIF|jpg|jpge|gif|png|tif|xls|xlsx|docx|doc|pdf|txt)$/i,true,function(e,data){
        		var srcAttachTemp=data.result.data.attachUrl;
        		var srcimgTemp = data.result.data.attachName;
        		var srcimg = imgStatic+srcAttachTemp+srcimgTemp;
//        		$("#"+showAttach).attr("src",srcimg);
        		$("#"+showAttach).val(srcimgTemp);
        		$("#"+AttachName).val(srcimgTemp);
        		$("#"+AttachId).val(data.result.data.id);
        		$("#materialSize").val(formatFileSize);
        },function(data,file){			
        });
	}
	deleteFileAttach=function(zipFileName,attachName,attachId){
		$("#"+zipFileName).val('');
		$("#"+attachName).val('');
		$("#"+attachId).val('');
	}
});
