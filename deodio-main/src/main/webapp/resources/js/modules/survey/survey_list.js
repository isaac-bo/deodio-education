define([ "jquery", 
	"utils.cookie", 
	"utils.menu", 
	"jquery.dot", 
	"pagination", 
	"utils", 
	"jquery.base",
	"jquery.validate", 
	"jquery.scrolltofixed",
	"jquery.mCustomScrollbar",
	"bootstrap.select", 
	"jquery.scroll.pagination" ], function($, cookie, menu,doT, paging) {
	var userId = cookie.getCookie('CUID');
	var nowIconShow;
	var ulliConfig={
		0:'创建问卷调查',
		1:'编辑问卷调查',
		2:'删除问卷调查',
		3:'分享问卷调查',
		4:'复制问卷调查',
		5:'预览问卷调查',
		6:'引用问卷调查',
		7:'仪表盘',
		8:'取消发布问卷调查'
	}
	var _init = function() {
		$("#hid_default_page").val(1);
		_initUI();
//		loadDataList(1,"table_panel", "table_data_template", "smallIconShow");
		mediumIconShow(1);
	};
	
	var _initUI = function() {
		$('select').selectpicker();
		$('#myTab #tab03').addClass('active');
		$('#mediumLeftBar').scrollToFixed({marginTop: 60});
		$('#content').scrollPagination({
			'postUrl' : ctx + '/survey/load_list.html',
			'postParams' : {
				pageNo : $.trim($("#hid_default_page").val()).length <= 0 ? 1 : $("#hid_default_page").val(),
				pageSize:6,
				keywords : $.trim($("#keywords_medium").val())
			},
			'scrollTarget' : $(window),
			'callBackLoad' : function(data) {
				if($("#hid_default_page").val() > 1){
					loadSurveyDataList(data);
				}else{
					$("#hid_default_page").val(Number($("#hid_default_page").val()) + 1);
				}
			},
			'beforeLoad' : function() {
				this.postParams.pageNo = $("#hid_default_page").val();
				this.postParams.pageSize = 6;
				this.postParams.keywords = $.trim($("#keywords_medium").val());
			}
		});
	};
	//设置点击的调查问卷被选中
	onSelectSurvey = function(id,surveyName,isPublish, surveyOwner, isEdit, createId,countQuote){	
		menu.onSelectItem(id);
		$("#_item_name").val(surveyName);
		$("#_item_isPublish").val(isPublish);
		$("#_item_owner").val(surveyOwner);
		$("#_item_edit").val(isEdit);
		$("#_item_createId").val(createId);
		$("#_item_count_quote").val(countQuote);
		changeSurveyIcon(surveyOwner, createId, isPublish);
	};
	//加载问卷调查列表
	loadDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {

		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:$("#smallIconConditionBar").is(":hidden")?6:10,
			keywords : $("#smallIconConditionBar").is(":hidden")?$.trim($('#keywords_medium').val()):$.trim($('#keywords_small').val())
		}, url = "/survey/load_list.html";

		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel,tableDataTemplate, "data_page_Panel", callbackFunc);
				$('.null_table').css('margin-top', '23px');
				var surveyOwner = $("#_item_owner").val();
				var createId = $("#_item_createId").val();
				var isPublish = $("#_item_isPublish").val();
				var countQuote = $("#_item_count_quote").val();
				changeSurveyIcon(surveyOwner, createId, isPublish);
//				if($("#smallIconConditionBar").is(":hidden")){
//					$("#hid_default_page").val(Number(pageNo) + 1);
//					alert('=====' + $("#hid_default_page").val());
//				}
			}
		});
	};
	
	//加载详细列表
	smallIconShow= function(pageNo){
		$('#content').stopScrollPagination();
		$('.table-item').addClass('on');
		$('.pic-item').removeClass('on');
		$('#smallIcon').show();
		$("#content").children().remove();
		$("#mediumIcon").hide();
		$("#data_page_Panel").show();
		$("#hid_default_page").val(Number(pageNo) + 1);
//		$("#hid_default_page").val(2);
		$('#smallIconConditionBar').show();
		$('.pre_center').css('width','1240px');
		$('.pre_left_top_fl').css('width','1205px');
		loadDataList(pageNo,"table_panel", "table_data_template", "smallIconShow");
		nowIconShow=1;
	};
	
	//加载中等图标列表
	mediumIconShow= function(pageNo){
		$('.pic-item').addClass('on');
		$('.table-item').removeClass('on');
		$('#mediumIcon').show();
		$("#table_panle").children().remove();
		$('#smallIcon').hide();
		$("#data_page_Panel").hide();
		$('#smallIconConditionBar').hide();
		$('#content').attr('scrollPagination', 'enabled');
//		$("#hid_default_page").val(2);
		$('.pre_center').css('width','975px');
		$('.pre_left_top_fl').css('width','945px');
		loadDataList(pageNo,"content", "data_template", "mediumIconShow");
		$('#mediumLeftBar').scrollToFixed({marginTop: 60});
		nowIconShow=2;
		$('body').removeClass('modal-open').css('overflow-y','auto');
	};
	
	//滚动加载问卷调查列表的回调函数
	loadSurveyDataList = function(data) {
		var template = doT.template($("#data_template").text());
		if ($("#content li").size() == 0) {
			$('#content').append(template({
				"data" : data.dataList,
				"currentPage":data.currePage
			}));
		} else {
			$('#content li:last').after(template({
				"data" : data.dataList,
				"currentPage":data.currePage
			}));
		}
		
		
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_page").val()) + 1;
			$("#hid_default_page").val(finalPageNo);
		}
	};
	
	//查找问卷调查，并刷新问卷调查列表
	searchSurveyList = function(){
		if($('.table-item').hasClass('on')){
			smallIconShow(1);
		}else{
			mediumIconShow(1);
		}
	};
	//删除该问卷调查
	onDeleteSurvey = function(surveyId){
		if(cookie.getCookie('CUID')==$("#_item_createId").val()){
			if($("#_item_edit").val()==1){
				alertMsg("该调查问卷正在被编辑, 不允许删除");
			}else{
				if($("#_item_count_quote").val()==0){
					deleteSurveyById(surveyId);	
				}else{
					alertMsg('该问卷调查已被引用，请联系课程或章节的创建者取消引用，再执行删除操作。')
				}
			}
		}else{
			alertMsg('你没有权限删除该问卷调查！');
		}				
	};
	//删除调查问卷
	onDeleteSurvey2 = function(){
		onDeleteSurvey($('#_item_id').val());
	};
	deleteSurveyById=function(surveyId){
		confirmMsg('删除后将无法恢复，确认删除该问卷调查吗？',function(){
			var url="/survey/delete.html",
			data={surveyId:surveyId};
			postAjaxRequest(url,data,function(result){
				alertMsg("操作成功");
				if(nowIconShow==2){
					mediumIconShow(1);
				}else{
					smallIconShow(1);
				}

				});
			});
	}
	
	//修改调查问卷
	onEditSurvey = function(surveyId){
		var userId=cookie.getCookie('CUID');
		var ownerId=$("#_item_owner").val();
		var createId=$("#_item_createId").val();
		var isEdit=$("#_item_edit").val();
		if(isEdit==0){
				go2Page('/survey/profile.html','surveyId='+surveyId);		
		}else{
			if(userId==ownerId){
				go2Page("/quiz/profile.html", "quizId="+id+"&navTabs="+type+"&eFlag="+editFlag);
			}else{
				alertMsg('该调查问卷正在被编辑，不允许编辑');
			}			
		}	
	};
	//修改调查问卷
	onEditSurvey2 = function(){
		onEditSurvey($('#_item_id').val());
	};
	//跳转到新建问卷调查界面
	onCreateSurvey = function(){
		go2Page('/survey/profile.html');
	};
	//预览调查问卷
	onPreviewSurvey = function(surveyId){
		$("#_item_id").val(surveyId);
		$("#surveyPreviewModal").modal("show");
	};
	_init();	
	onCancelPublish=function(){
		var surveyId = $('#_item_id').val();
		var countQuote=$('#_item_count_quote').val();
		cancelPublishSurvey(surveyId,countQuote);	
	}
	cancelPublishSurvey=function(surveyId,countQuote){
		if (countQuote>0) {
			alertMsg("该问卷调查已被引用，请联系课程或章节的创建者取消引用，再执行取消发布操作。");
		} else {
			confirmMsg('取消发布后将无法被引用，确定取消发布该问卷调查吗？', function(){
				var url = "/survey/set_publish_status.html",
				data = {
					surveyId : surveyId,
					isPublish : 0
				};
				postAjaxRequest(url, data, function(result) {
					if (result.status == 1) {
						alertMsg("取消发布成功",undefined,undefined,function(){
							if(nowIconShow==2){
								mediumIconShow(1);
							}else{
								smallIconShow(1);
							}
						});
					} else {
						alertMsg("取消发布失败");
					}
				}, false);
			});
		}
		
	}
	//取消分享
	onCancelShare=function(id){
		if((cookie.getCookie('CUID')==$("#_item_createId").val())){
			if($("#_item_edit").val()==0){
				confirmMsg('取消分享后对方将无法编辑，确定取消分享该问卷调查吗？', function(){
					var url = "/survey/cancel_shared.html",
					data = {
							id : id
					};
					postAjaxRequest(url, data, function(result) {
						if (result.status == 1) {
							alertMsg("取消分享成功",undefined,undefined,function(){
								if(nowIconShow==2){
									mediumIconShow(1);
								}else{
									smallIconShow(1);
								}
							});
						} else {
							alertMsg("取消分享失败");
						}
					}, false);
				});
			}else{
				alertMsg('该调查问卷正在被编辑，不允许取消分享');
			}
		}else{
			alertMsg('你没有取消分享该问卷调查的权限');
		}
	}	
	//跳转到被引用的问卷调查界面
	onQuoteSurvey = function(){
		$("#surveyQuoteModal").modal("show");
	};
	//分享调查问卷
	onShareSurvey=function(){	
		var params = {
				surveyId : $.trim($('#_item_id').val())
		}, url = "/survey/getSurveyIsEdit.html";
		postAjaxRequest(url, params, function(result) {
			if (result.status == 1) {
				if(result.data.isCreated==1){
					if(result.data.surveyIsSelf){//编辑权限为自己
						if(result.data.isPublished){//判断已经发布
							alertMsg('该问卷调查已发布,不可再分享');
						}else{
							$("#surveyShareModal").modal("show");
						}
					}else{//编辑权限不为自己
						if(result.data.isPublished){//判断是否已经发布
							if(confirm("该问卷已经发布了,确定要取消分享吗?")){//点击确认
								var params = {
										surveyId : $.trim($('#_item_id').val())
								}, url = "/survey/set_share_survey_trainee.html";
								postAjaxRequest(url, params, function(result) {
									if (result.status == 1) {
										alertMsg('该问卷调查已经取消分享成功');
									}
								});
							}
						}else{//未发布
							if(result.data.surveyIsEdit){//对方正在编辑
								confirmMsg('对方正在编辑中,确定要取消分享吗?',function(){
									var params = {
											surveyId : $.trim($('#_item_id').val())
									}, url = "/survey/set_share_survey_trainee.html";
									postAjaxRequest(url, params, function(result) {
										if (result.status == 1) {
											alertMsg('该问卷调查已经取消分享成功');
										}
									});
								});
							}else{
								confirmMsg("确定要取消分享吗?",function(){
									var params = {
											surveyId : $.trim($('#_item_id').val())
									}, url = "/survey/set_share_survey_trainee.html";
									postAjaxRequest(url, params, function(result) {
										if (result.status == 1) {
											alertMsg('该问卷调查已经取消分享成功');
										}
									});
								});
							}
						}
					}
				}else{
					alertMsg("该问卷调查不是你创建的,你没有权限分享")
				}
			}
		});
		
	}
	//设置分享调查问卷的讲师
	shareSurveyToUser=function(){
		var trainees=$("input[name='surveyTrainee'][checked]");
		var userId="";
		if(trainees.length>0){
			trainees.each(function(){			    
				userId=$(this).attr("id");
			  });
		    var params = {
					userId:userId,
					surveyId : $.trim($('#_item_id').val())
			}, url = "/survey/set_share_survey_trainee.html";
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					alertMsg('该问卷调查已经分享成功');
					if(nowIconShow==2){
						mediumIconShow(1);
					}else{
						smallIconShow(1);
					}
				}
			});
		}else{
			alertMsg('请先选择讲师');
		}
	}
	//复制调查问卷
	onCopySurvey=function(){
		if($("#_item_edit").val()==0){
			$("#surveyCopyModal").modal("show");
		}else{
			alertMsg('该调查问卷正在被编辑, 不允许复制');
		}
		
	}
	//提交复制的调查问卷
	submitNewSurvey=function(){
		var surveyName=$.trim($('#newSurveyName').val())
		url = "/survey/checkSurveyNameExist.html",data={
				surveyName:surveyName
		};postAjaxRequest(url, data, function(result){
			if(result.data){
				alertMsg('该问卷调查名称已经存在')
			}else{
				var url="/survey/copySurvey.html",data={
						surveyId:$.trim($('#_item_id').val()),
						surveyName:surveyName
				};
				postAjaxRequest(url, data, function(result){
					if(result.data.successFlag){
						mediumIconShow(1);
						alertMsg("复制成功");
					}else{
						mediumIconShow(1);
						alertMsg("复制失败");
					}

				});
			}
		});		
	}
	//鼠标悬浮按钮时,显示提示信息
	$("#ul_li_focus_events li").hover(function(){		
		$("#text_tips").html("");		
	})
	$("#ul_li_focus_events li").mouseover(function(){	
		$(this).each(function(){
			$("#text_tips").html(ulliConfig[$(this).index()]);	
		})		
	})
	changeSurveyIcon = function(owner, createId, isPublish) {
			if (typeof(owner) == "undefined") {
				$("#survey_edit").hide();
				$("#survey_delete").hide();
				$("#survey_quote").hide();
				$("#survey_share").hide();
				$("#survey_copy").hide();
				$("#survey_preview").hide();
				$("#li_cancelPublish").hide();
			
			} else {
				$("#survey_quote").show();
				$("#survey_preview").show();
				$("#li_cancelPublish").hide();
				if (createId != cookie.getCookie('CUID')) {
					$("#survey_delete").hide();
					$("#survey_share").hide();
					if (owner == cookie.getCookie('CUID')) {//account下其他人的
						$("#survey_edit").show();
						$("#survey_copy").hide();
						
					}else{//别人分享的
						
						$("#survey_edit").hide();
						$("#survey_copy").show();
					}
				} else {					
					
					$("#survey_copy").show();
					if (isPublish == 1) {//自己发布的
						$("#survey_share").hide();
						$("#survey_edit").hide();
						$("#li_cancelPublish").show();
						$("#survey_delete").hide();
					}else{//自己未发布的
						$("#survey_delete").show();
						
						$("#survey_edit").show();
						$("#li_cancelPublish").hide();
						if (owner == cookie.getCookie('CUID')) {//未分享
							$("#survey_share").show();
						}else{//已分享
							$("#survey_share").hide();
						}
					}
				}	
			}
		}
	
	_init();
});
