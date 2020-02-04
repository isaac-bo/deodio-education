define([ "jquery","utils.dtree","utils.cookie","utils.menu","jquery.dot",
	"pagination","utils","jquery.base","jquery.validate","bootstrap.datepicker",
    "jquery.scrolltofixed", "jquery.mCustomScrollbar"], function($, tree, cookie, menu,doT, paging) {
	
	var _init = function() {
		_initTabs();
		$("#classificationPanelDiv").mCustomScrollbar();
		refreshBanksList();
//		loadDataList(1);
	};

	var _initTabs = function() {
		$('#myTab #tab01').addClass('active');
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
	};
	//刷新左侧的题库列表
	refreshBanksList = function() {
//		$("#treeNodeId").val(0);
//		$("#treeNodeIdName").val(0);
		tree.setting.check = {
			enable : false,
			chkDisabledInherit: true,
			chkStyle: "radio",
			radioType : "all"
		}
		tree.init({
			ui:{
				selectedNode:"treeNodeId",
				treeContainer:"classificationPanel"
			},
			fn:{
				onLoadData : loadDataList,
				onClickRootNodeLoadData:manageQuizBankList,
				onClickLeafNodeLoadData:manageQuizSubjectList
			},
			url:ctx +"/quiz/bank/get_left_ctree.html"
		});
	};
	//加载当前题库下的题库列表
	loadDataList = function(pageNo) {
		manageQuizBankList(pageNo);
		
	};
	//点击展开侧边栏
	hiddenLeft2 = function(){
		var empx ={left: '+0px'},tables={"cssOptions":{"padding-left":"380px"},"divid":"div_tables"},tables2={"cssOptions":{"padding-left":"380px"},"divid":"div_tables_subject"};
		sidbarOpen('div_hidd','div_sidbar',empx,tables);
		sidbarOpen('div_hidd','div_sidbar',empx,tables2);
	};
	//点击滑动关闭侧边栏
	closeSidebar2 = function(){
		 var empx ={left: '-400px'},tables={"cssOptions":{"padding-left":"60px"},"divid":"div_tables"},tables2={"cssOptions":{"padding-left":"60px"},"divid":"div_tables_subject"};
		 closeSidebar('div_hidd','div_sidbar',empx,tables);
		 closeSidebar('div_hidd','div_sidbar',empx,tables2);
	};
	//新建题库
	create = function(quizBankId) {
		$('#popupModel').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
		});
		if ($("#treeNodeId").val() == 1000008) {
			alertMsg('默认类下不允许创建和修改题库');
			return;
		}
		if (quizBankId) {
			var url = "/quiz/bank/get_list_by_id.html", data = {quizBankId:quizBankId};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#bank_template").text());	
				popUpWindow(template(result.data),"更新题库");
				customInput('isPrivate');
				$('#quizBankId').val(quizBankId);
				$('#quizBankName').val(result.data.quizBankName);
				$('#oldQuizBankName').val(result.data.quizBankName);
				$('#quizBankDesc').val(result.data.quizBankDesc);
				$('#span_classification_name').html($.trim($("#treeNodeIdName").val()));
				if(result.data.isPrivate == 1){
					$("#isPrivate").prop("checked",true);
					$("#isPrivate").parent().find('label').addClass('checked');
				};
				if(result.data.isPrivate == 0){
					$("#isPrivate").prop("checked",false);
					$("#isPrivate").parent().find('label').removeClass('checked');
				};
			},false);
		} else {
			var classificationId = $.trim($('#treeNodeId').val());
			if(classificationId == 0){
				alertMsg('请选择创建题库所属的分类');
				return;
			}
			$('#quizBankId').val(0); 
			var template = doT.template($("#bank_template").text());	
			popUpWindow(template(""),"新建题库");
			$('#span_classification_name').html($.trim($('#treeNodeIdName').val()));
			customInput('isPrivate');
		}
		$('#createQuizBankForm').myValidate({
			formCall:function(){submitQuizBanks();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
	};
	//隐藏题目列表区域,显示题库列表区域
	manageQuizBankList = function(pageNo){
		$("#div_tables_subject").hide();
		$('#div_tables_subject_bar').hide();
		$("#div_tables").show();
		$('#div_tables_bar').show();
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			classificationId : $('#treeNodeId').val(),
			keyword : $.trim($('#keywords').val())
		}, url = "/quiz/bank/load_list.html";
		postAjaxRequest(url, params, function(result) {
			paging.pageTemplateDiv(result, "table_panle", "table_data_template", "data_page_Panel", "loadDataList");
			customInput('quizBank');
			checkOrUncheckAll('quizBankHead','quizBank');
		});
	};
	// 隐藏题库列表区域,显示题目列表区域
	manageQuizSubjectList = function(id){
		$("#div_tables").hide();
		$('#div_tables_bar').hide();
		$("#div_tables_subject").show();
		$('#div_tables_subject_bar').show();
		$('#quizBankId').val(id);
		
		if($('#div_hidd').css('left') == '0px'){
			hiddenLeft2();
		}
		
		loadSubjectDataList(1);
	};
	//加载当前题库中的题目列表
	loadSubjectDataList = function(pageNo){
		if(pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo:pageNo,
			classificationId:$.trim($("#quizBankId").val()),
			keyword:$.trim($('#subjet_keywords').val()),
			type:$('#quiz_subject_type_select').val(),
			level:$('#quiz_subject_level_select').val()
		},url="/quiz/subject/list.html";
		postAjaxRequest(url, params, function(result){
			paging.pageTemplateDiv(result,"table_panle_subject","table_data_subject_template","data_page_Panel_subject","loadSubjectDataList");
			customInput('quizSubject');
//			customInput('quizBank');
			checkOrUncheckAll('quizSubjectHead','quizSubject');
		});
	};
	//校验题库名称
	checkBankName = function() {
		var flag = false;
		var url="/quiz/bank/check_name.html", data={
			bankName : $("#quizBankName").val()
		};
		if($("#quizBankName").val()!=$("#oldQuizBankName").val()){
			postAjaxRequest(url, data, function(result){
				if (!result.data) {
					flag = true;
				}
			},false)
		}
		return flag;
	};
	//更新或创建题库
	submitQuizBanks = function(){
		if($('#quizBankId').val() != 0){
			updateQuizBank();
		}else{
			addQuizBank();
		}
	};
	//添加题库
	addQuizBank = function(){
		var url="/quiz/bank/create.html",data={
				quizBankName:$.trim($("#quizBankName").val()),
				quizBankDesc:$.trim($("#quizBankDesc").val()),
				classificationId:$.trim($("#treeNodeId").val()),
				isPrivate:$("#isPrivate").prop("checked")?"1":"0"
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				closePopWindow();
				loadDataList(1);
				
				tree.addNode(result.data,$.trim($("#quizBankName").val()),$('#treeNodeIdName').val());
				alertMsg("操作成功");
			}
		})
	};
	//更新题库
	updateQuizBank = function(){
		if($("#isPrivate").prop("checked")){
			confirmMsg("公有题库中可能包含其他讲师创建的题目,是否继续设置为私有题库?",function(){
				updateQuizBankInfo()
			})
		}else{
			updateQuizBankInfo()
		}
		
	};
	updateQuizBankInfo=function(){
		var url="/quiz/bank/update.html",data={
		quizBankId:$.trim($("#quizBankId").val()),
		quizBankName:$.trim($("#quizBankName").val()),
		quizBankDesc:$.trim($("#quizBankDesc").val()),
		classificationId:$.trim($("#treeNodeId").val()),
		isPrivate:$("#isPrivate").prop("checked")?"1":"0"
};
postAjaxRequest(url, data, function(result){
	if(result.status == 1){
		closePopWindow();
		loadDataList(1);
		
		tree.updateNode($("#quizBankId").val(),$.trim($("#quizBankName").val()));
		alertMsg("操作成功");
	}
});
	}
	//删除题库
	deleteQuizBanks = function(quizBankId) {
		if ($("#treeNodeId").val() == 1000008) {
			alertMsg('不允许删除默认题库');
			return;
		}
		confirmMsg('确认要删除该题库吗？将同时删除题库中的所有题目！', function() {
			var url="/quiz/bank/delete.html", data = {
					quizBankId : quizBankId
			};
			postAjaxRequest(url, data, function(result) {
				if (result.data > 0) {
					loadDataList(1);
					tree.delNode(quizBankId,$.trim($("#quizBankName").val()));
					alertMsg("操作成功");
				} else {
					alertMsg("删除失败");
				}
			});
		});
	};
	//批量删除题库
	deleteBatch = function() {
		if ($("#treeNodeId").val() == 1000008) {
			alertMsg('不允许删除默认题库');
			return;
		}
		var arr = new Array();
		$("input[name='quizBank']:checkbox").each(function() { 
			if($(this).next('label').hasClass("checked")) {
				arr.push($(this).attr("id"));
			}
        });
		var quizBankIds = arr.join(",");
		if (quizBankIds) {
			confirmMsg('请确认您是否要删除已选择的题库？', function() {
				var url = "/quiz/bank/batchDelete.html",
					data = {quizBankIds : quizBankIds};
				postAjaxRequest(url, data, function(result) {
					if (result.data > 0) {
						loadDataList(1);
						alertMsg("操作成功");
					} else {
						alertMsg("删除失败");
					}
				})
			})
		} else {
			alertMsg("请先勾选要删除的题库!");
		}
	};
	//更新题目
	editQuizSubjects = function(subjectId){
		var url="/quiz/subject/get_by_id.html",data={
				quizSubjectId:subjectId
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					editQuizsDialogue(result.data.quizSubjectType,result.data);
				}
			});
	};
	//删除题目
	deleteQuizSubjects = function(subjectId){
		confirmMsg('请确认您是否要删除该试题？',function(){
			var url="/quiz/bank/delete_subject.html",data={
				quizSubjectId:subjectId
			};
			postAjaxRequest(url, data, function(result){
				if (result.data > 0) {
					loadSubjectDataList($("#data_page_Panel_subject li.active a[crpageno]").attr("crpageno"));
					alertMsg("操作成功");
				} else {
					alertMsg("删除失败");
				}
			});
		});
	};
	//查看题目
	viewQuizSubjects = function(subjectId) {
		var url = "/quiz/subject/get_by_id.html", data = {
			quizSubjectId : subjectId
		};
		postAjaxRequest(url, data, function(result) {
			if(result.status == 1) {
				viewQuizsDialogue(result.data.quizSubjectType, result.data);
			}
		});
	}
	_init();
	
});
