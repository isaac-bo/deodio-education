define([ "jquery", "utils.cookie", "utils.menu","jquery.dot", "pagination", , "utils.list" , "utils", "jquery.base", "jquery.validate", "jquery.scrolltofixed",
		"jquery.mCustomScrollbar", "bootstrap.select", "bootstrap.datepicker", "jquery.scroll.pagination" ], function($, cookie, menu,doT, paging, list) {
	var userId = cookie.getCookie('CUID');
	var _init = function() {
		_initUI();
		mediumIconShow(1);
	};
	
	var _initUI = function() {
		$('select').selectpicker();
		$('#myTab #tab02').addClass('active');
		$('#mediumLeftBar').scrollToFixed({marginTop: 60});
		$("#quizCategory").change(function() {
			searchQuizExaminationList();
		});
	};
	//加载考试试卷列表
	loadDataList = function(pageNo, tablePanel, tableDataTemplate, callbackFunc) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize : $("#smallIconConditionBar").is(":hidden")?6:10,
			keywords : $("#smallIconConditionBar").is(":hidden")?$.trim($('#keywords_medium').val()):$.trim($('#keywords_small').val()),
			quizCategory : [$("#smallIconConditionBar").is(":hidden")?0:$('#quizCategory').val()],
			flag:'load'
		}, url = "/quiz/load_list.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "data_page_Panel", callbackFunc);
				$('.null_table').css('margin-top', '23px');
				if ($("#smallIconConditionBar").is(":hidden")) {
					var quizOwner = $("#_item_owner").val();
					var createId = $("#_item_createId").val();
					var isPublish = $("#_item_isPublish").val();
					changeIcon(quizOwner, createId, isPublish);
					iconDetail();
				}
			}
		});
	};
	//显示考试试卷详细列表
	smallIconShow = function(pageNo) {
		$('#content').stopScrollPagination();
		$('.table-item').addClass('on');
		$('.pic-item').removeClass('on');
		$('#smallIcon').show();
		$("#content").children().remove();
		$("#mediumIcon").hide();
		$("#data_page_Panel").show();
		$("#hid_default_page").val(Number(pageNo) + 1);
		$('#smallIconConditionBar').show();
		$('.pre_center').css('width','1240px');
		$('.pre_left_top_fl').css('width','1205px');
		
		loadDataList(pageNo, "table_panle", "table_data_template", "smallIconShow");
		
	};
	//显示考试试卷中等图标列表
	mediumIconShow = function(pageNo) {
		$('.pic-item').addClass('on');
		$('.table-item').removeClass('on');
		$('#mediumIcon').show();
		$("#table_panle").children().remove();
		$('#smallIcon').hide();
		$("#data_page_Panel").hide();
		$('#smallIconConditionBar').hide();
		
		$('.pre_center').css('width','975px');
		$('.pre_left_top_fl').css('width','945px');
		loadDataList(pageNo, "content", "data_template", "mediumIconShow");
		$('#mediumLeftBar').scrollToFixed({marginTop: 60});
		$('#content').scrollPagination({
			'postUrl' : ctx + '/quiz/load_list.html',
			'postParams' : {
				pageNo : $.trim($("#hid_default_page").val()).length <= 0 ? 1 : $("#hid_default_page").val(),
				pageSize:6,
				keywords : $.trim($("#keywords_medium").val()),
				quizCategory : [0],
				flag:'scroll'
			},
			'scrollTarget' : $(window),
			'callBackLoad' : function(data) {
				if($("#hid_default_page").val() > 1){
					loadExaminationDataList(data);
				}else{
					$("#hid_default_page").val(Number($("#hid_default_page").val()) + 1);
					this.postParams.pageNo = Number($("#hid_default_page").val());
					this.postParams.pageSize = 6;
					this.postParams.keywords = $.trim($("#keywords_medium").val());
					this.postParams.quizCategory = [0];
				}
			},
			'beforeLoad' : function() {
				this.postParams.pageNo = $("#hid_default_page").val();
				this.postParams.keyword = $.trim($("#keywords_medium").val());
			}
		});
	};
	//滚动加载考试试卷列表的回调函数
	loadExaminationDataList = function(data) {
		var template = doT.template($("#data_template").text());
		if ($("#content li").size() == 0) {
			$('#content').append(template({
				"data" : data.dataList
			}));
		} else {
			$('#content li:last').after(template({
				"data" : data.dataList
			}));
		}
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_page").val()) + 1;
			$("#hid_default_page").val(finalPageNo);
		}
	};
	//检索考试试卷，返回考试试卷列表
	searchQuizExaminationList = function() {
		if ($('.table-item').hasClass('on')) {
			smallIconShow(1);
		} else {
			mediumIconShow(1);
		}
	};
	onSelectExamination = function(id, quizName, isPublish, quizOwner, isEdit, createId, groupId, quoteCount) {
		menu.onSelectItem(id);
		$("#_item_name").val(quizName);
		$("#_item_isPublish").val(isPublish);
		$("#_item_owner").val(quizOwner);
		$("#_item_edit").val(isEdit);
		$("#_item_createId").val(createId);
		$("#_item_groupId").val(groupId);
		$("#_item_quoteCount").val(quoteCount);
		changeIcon(quizOwner, createId, isPublish);
		iconDetail();
	};
	//跳转到手动创建考试试卷界面
	onCreateExamination = function(){
		go2Page("/quiz/profile.html")
	};
	//跳转到编辑考试试卷界面
	onEditExamination = function(id, type, editFlag){
		var createId=$("#_item_createId").val();
		var ownerId=$("#_item_owner").val();
		var isEdit=$("#_item_edit").val();
		var userId=cookie.getCookie('CUID');
		if(!id) {
			var id = $("#_item_id").val(),
			type = $("#examination_create_type_"+$("#_item_id").val()).val(),
			editFlag = 1;
		}
		if(isEdit==0){
				go2Page("/quiz/profile.html", "quizId="+id+"&navTabs="+type+"&eFlag="+editFlag);
		}else{
			if(userId==ownerId){
				go2Page("/quiz/profile.html", "quizId="+id+"&navTabs="+type+"&eFlag="+editFlag);
			}else{
				alertMsg('该试卷正在被编辑，不允许编辑');
			}			
		}		
	};
	//删除该考试试卷
	onDeleteExamination = function(quizId){
		if (!quizId) {
			var quizId = $("#_item_id").val();
		}
		
		if(cookie.getCookie('CUID')==$("#_item_createId").val()){
			if($("#_item_edit").val()==1){
				alertMsg("该试卷正在被编辑, 不允许删除");
			}else{
				if($("#_item_quoteCount").val()==0){
					deleteQuizById(quizId);	
				}else{
					alertMsg('该试卷已被引用，请联系课程或章节的创建者取消引用，再执行删除操作。')
				}
			}
		}else{
			alertMsg('你没有权限删除该试卷！');
		}	
		
		
	};
	deleteQuizById=function(quizId){
		confirmMsg('删除后将无法恢复，确认删除该试卷吗？',function(){
			var url = "/quiz/delete.html",
			data = {quizId : quizId};
			postAjaxRequest(url,data,function(result){
				if (result.data > 0) {
					mediumIconShow(1);
					alertMsg("操作成功");
				}
			});
		});
	}
	onViewExamination = function(quizId) {
		$("#_item_id").val(quizId);
		$("#quizPreviewModal").modal("show");
//		var url = "/quiz/view.html",
//		params = {
//			quizId : quizId
//		};
//		postAjaxRequest(url, params, function(result){
//			var template = doT.template($("#view_data_template").text());
//			popUpPreviewWindow(template({data:result.data}),"试卷预览",1000,600);
//		});
	}
//	//跳转到预览界面
//	onViewQuiz = function() {
//		var url = "/quiz/view.html",
//		params = {
//			quizId : $("#_item_id").val()
//		};
//		postAjaxRequest(url, params, function(result){
//			var template = doT.template($("#view_data_template").text());
//			popUpPreviewWindow(template({data:result.data}),"试卷预览",1000,600);
//		});
//	};
	//跳转到复制试卷界面
	onCopyQuiz=function(){	
		if($("#_item_edit").val()==0){
			$("#quizCopyModal").modal("show");
		}else{
			alertMsg('该试卷正在被编辑, 不允许复制');
		}
	}
	//跳转到被引用的试卷界面
	onQuoteQuiz = function(){
		$("#quizQuoteModal").modal("show");
	};
	//跳转到分享试卷界面
	onShareQuiz = function(){
		if($("#_item_owner").val() != userId) {
			if($("#_item_edit").val() == 1) {
				confirmMsg('该试卷已被分享且正在被编辑，确认取消分享吗', function(){
					onUpdateQuizOwner();
					return;
				});
			} else {
				confirmMsg('该试卷已被分享，确认取消分享吗', function(){
					onUpdateQuizOwner();
					loadDataList(1, "content", "data_template", "mediumIconShow");
				});
			}
		} else {
			$("#quizShareModal").modal("show");
		}
	}
	//取消发布
	onCancelPublish = function(quizId) {
		if (typeof(quizId) == "undefined") {
			quizId = $('#_item_id').val();
		}
		if ($('#_item_quoteCount').val() > 0) {
			alertMsg("该试卷已被引用，请联系课程或章节的创建者取消引用，再执行取消发布操作。");
		} else {
			confirmMsg('取消发布后将无法被引用，确定取消发布该试卷吗？', function(){
				var url = "/quiz/publish.html",
				data = {
					quizId : quizId,
					type : '0'
				};
				postAjaxRequest(url, data, function(result) {
					if (result.status == 1) {
						alertMsg("取消发布成功",function(){
							go2Page("/quiz/list.html");
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
				confirmMsg('取消分享后对方将无法编辑，确定取消分享该试卷吗？', function(){
					var url = "/quiz/cancel_shared.html",
					data = {
							id : id
					};
					postAjaxRequest(url, data, function(result) {
						if (result.status == 1) {
							alertMsg("取消分享成功",undefined,undefined,function(){
								mediumIconShow(1);
							});
						} else {
							alertMsg("取消分享失败");
						}
					}, false);
				});
			}else{
				alertMsg('该试卷正在被编辑，不允许取消分享');
			}
		}else{
			alertMsg('你没有取消分享该试卷的权限');
		}
	}	
	//更新试卷拥有者
	onUpdateQuizOwner = function() {
		var url = "/quiz/update_quiz_owner.html",
		data = {
			quizOwner : userId,
			quizId : $('#_item_id').val()
		};
		postAjaxRequest(url, data, function(result) {
			
		},false);
	}
	paginationFunc = function(result){
		paging.pageTemplateDiv(result, list.setting.opts.tablePanel, list.setting.opts.tableDataTemplate);
	};
	_init();
});
