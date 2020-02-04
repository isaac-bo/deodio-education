define([ "jquery", "utils.dtree", "utils.cookie", "jquery.dot",
		"libs/step/step_page", "pagination", "utils", "jquery.base",
		"jquery.validate", "ueditor", "jquery.mCustomScrollbar",
		"bootstrap.select", "upload.common" ], function($, tree, cookie, doT,
		step, paging) {

	var currentPageNo = 1;

	var managerTraineeDataConfig = {
		"active" : {// 0
			"url" : "/course/trainee/active.html",
			"successMsg" : "激活成功！",
			"errorMsg" : "激活失败！"
		},
		"substitute" : {// 2
			"url" : "/course/trainee/substitute.html",
			"successMsg" : "替补成功！",
			"errorMsg" : "替补失败！"
		},
		"suspend" : {// 1
			"url" : "/course/trainee/suspend.html",
			"successMsg" : "拒绝成功！",
			"errorMsg" : "拒绝失败！"
		},
		"delete" : {
			"url" : "/course/trainee/delete.html",
			"successMsg" : "删除成功！",
			"errorMsg" : "删除失败！"
		}

	}

	var init = function() {
		$('select').selectpicker();
		$('.bootstrap-select').css('height', '36px').css('top', '-1px');
		$('.bootstrap-select button').css('height', '34px');
		$('.w260 .bootstrap-select').css('width','180px');
		$('.w160 .bootstrap-select').css('width', '150px');

//		$('#myTab a:first').tab('show');// 初始化显示哪个tab
//		$('#myTab a').click(function(e) {
//			e.preventDefault();// 阻止a链接的跳转行为
//			$(this).tab('show');// 显示当前选中的链接及关联的content
//			selectedTabId = $(this).attr("href").substr(1);
//			if (selectedTabId == 'dagang' || selectedTabId == 'baomingshenpi') {
//				loadCourseOfflineContent();
//			}
//		});
		
		loadCourseOfflineContent();

		checkOrUncheckAll('allRelatedId', 'relatedIdCheckBox');
		checkOrUncheckAll('allRelatedIdCouserUser',
				'relatedIdCheckBoxCouserUser');
		// 报名是否结束
		if (isNotStartTime(addDate($('#stepStartTime').val(), -2))) {
			$('#uploadExcel').css('display', 'none');
		} else {
			$('#uploadExcel').css('margin-bottom', '0px').css('margin-top','1px').css('margin-right', '10px');
			$('.uploadify-button-text').css('margin-left', '');
		}
		if($("#isPublish").val()==0){
			$("#publishBtn").show();
		}
		//取消发布
		if(!validateCanclePublish()&& $("#isPublish").val()==1){
			$("#cancelPublishBtn").show();
		}else{
			$("#cancelPublishBtn").hide();
		}
	};

	// 跳转到线下课程设置页面
	onCourseOfflineSetting = function(id, courseType) {
		gotoCourseOfflineManagerPage('/course/setting.html', 'courseId=' + id,
				'courseType=' + courseType);
	};
	var gotoCourseOfflineManagerPage = function(url, param, param1) {
		if (param && param1) {
			window.location.href = ctx + url + "?" + param + "&" + param1
					+ "&r=" + Math.floor(Math.random() * 100);
		} else {
			window.location.href = ctx + url + "?r="
					+ Math.floor(Math.random() * 100);
		}
	};
	// 发布课程
	courseOfflinePublish = function() {
		var trainTimes = 0;
		var setTimes = $("#courseTrainTimes").val();
		var isPublic=$("#isPublic").val();
		var url = "/course/offline/CourseOfflineItemCount.html", params = {
			courseId : $("#courseId").val()
		}
		postAjaxRequest(url, params, function(result) {
			if (result.status == 1) {
				trainTimes = result.data;
				if (trainTimes != setTimes) {
					alertMsg("培训次数与设置不符请确认!");
					return false;
				} else {
					if(isPublic==1){
						var courseGroupItemType = 42;
						$("#publishModal").modal("show");
						$('#group_container_type').val(courseGroupItemType);
						$('#item_id').val($("#courseId").val());
						}else{				
							publishItemFunc();
							go2Page("/course/offline/list.html");
						}
				}
			}
		});

	};
	// 取消发布课程
	cancelCourseOfflinePublish = function() {
		confirmMsg("确定要取消发布当前课程?", function() {
			var url = "/course/cancelPublish.html", params = {
				courseId : $("#courseId").val()
			}
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					alertMsg("取消发布成功");
					go2Page("/course/offline/detail.html","courseId="+$("#courseId").val());
				} else {
					alertMsg("取消发布失败");
				}
			});
		});
	}
	validateCanclePublish = function() {
		var trainStartTime = $("#trainStartTime").val();
		var dateFrom = new Date(trainStartTime);
		var nowDay=dateFormat(new Date());
		var dateTo = new Date(nowDay);
		var diffStart = dateFrom.valueOf() - dateTo.valueOf();
		var diff_day_start = parseInt(diffStart / (1000 * 60 * 60 * 24));
		if (diff_day_start <=5) {
			return true;
		} else {
			return false;
		}
	};
	

	// 加载线下课程指定次数内容数据
	loadCourseOfflineContent = function(stepNo) {
		if (stepNo == undefined)
			stepNo = 1;
		var params = {
			stepNo : stepNo,
			itemType : '2',
			courseId : $("#courseId").val(),
		}, url = "/course/offline/content/load_list.html";
		postAjaxRequest(url, params, function(result) {
			$("#stepNo").val(stepNo);
			var pxshijianTemplate = doT.template($(
					"#dagang_box_pxshijian_data_template").text());
			var resultObj = divideCourseOffContentParentData(result.data);
			// 设置主数据
			var dataObj = resultObj.parentData;
			// dataObj.trainerList = trainerList;
			dataObj.locationList = locationList;
			$('#baomingshenpi').find(".pxshijian").html(pxshijianTemplate(dataObj));

			loadCourseOfflineTraineeList(null,stepNo);

			$('.submit-item').remove();

			loadStepData(stepNo, "loadCourseOfflineContent");
		});

	}
	validateImport = function() {
		var enrollExpireTime = $("#enrollExpireTime").val();
		var enrollStartTime = $("#enrollStartTime").val();
		var enrollExpireDay=dateFormat(enrollExpireTime)+" 00:00:00";
		var enrollStartDay=dateFormat(enrollStartTime)+" 00:00:00";
		var dateExpireFrom = new Date(enrollExpireDay);
		var dateStartFrom = new Date(enrollStartDay);
		var nowDay=dateFormat(new Date())+" 00:00:00";
		var dateTo = new Date(nowDay);
		var diffExpire = dateExpireFrom.valueOf() - dateTo.valueOf();
		var diff_day_expire = parseInt(diffExpire / (1000 * 60 * 60 * 24));
		var diffStart = dateStartFrom.valueOf() - dateTo.valueOf();
		var diff_day_start = parseInt(diffStart / (1000 * 60 * 60 * 24));
		if (diff_day_expire <0||diff_day_start>0) {
			return true;
		} else {
			return false;
		}
	};
	// 加载线下课程指定次数内容数据
	loadCourseOfflineTraineeList = function(pageNo,stepNo) {
		if (pageNo == undefined)
			pageNo = 1;
		if (stepNo == undefined) {
			stepNo = $("#stepNo").val();
		}
		currentPageNo = pageNo;
		var params = {
			stepNo : stepNo,
			pageNo : pageNo,
			traineeStatus : $.trim($("#traineeStatus").val()),
			registeType : $.trim($("#registeType").val()),
			keywords : $.trim($("#keywords").val()),
			courseId : $("#courseId").val(),
			joinTime:$("#joinTime").val()
		}, url = "/course/offline/trainee/load_list.html";
		postAjaxRequest(url, params, function(result) {
			// $("#traineeStepNo").val(stepNo);
			paging.pageTemplateDiv(result, "courseOfflineTraineeList",
					"course_offline_strainee_data_template",
					"course_offline_trainee_data_page_Panel",
					"loadCourseOfflineTraineeList");
			// 更新内容刷新到相应的位置
			refeshPageControl();
		});
		var n = $("#stepNo").val() - 1;
		
		var isPublish = $("#isPublish").val();
		if (!validateImport() && isPublish == 1) {
			var itemId = $("input[name=itemId]").val();
			$("#itemIdValue").val(itemId);
			uploadProcess('uploadExcel', '*.xlsx;*.xls;', {}, ctx
					+ '/course/upload_excel_template.html?courseId='
					+ $("#courseId").val() + "&itemId="
					+ $("#itemIdValue").val(), function(fileName) {
				$("#tempUp1").uploadify('settings', 'formData', {});
				
			},
			// 回调函数
			function(result) {
				if (result.status == 1) {
					// alertMsg("用户邮箱未注册"+result.message);
					loadCourseOfflineContent(stepNo);
				} else {
					if (result.message != null) {
						alertMsg(result.message);
					} else {
						alertMsg("上传失败");
					}

				}
			}, true, "批量导入");
			
			$('.uploadify').css('margin-top','13px').css('margin-right','5px');
			$('.uploadify-button ').css('height','36px').css('line-height','36px');
		}else{
			
			$('.tools').css('margin-top','-2px')
			$('#uploadExcel').css('display', 'none');
		}
	}

	// 加载次数数据
	loadStepData = function(stepNo, dataFunName) {
		if (!stepNo) {
			stepNo = 1;
		}
		var stepNum = $("#courseTrainTimes").val();
		step.stepTemplateDiv('baomingshenpi', dataFunName, Number(stepNum),
				stepNo);
	}

	// 区分主从数据
	divideCourseOffContentParentData = function(dataList) {
		var dataObj = {};
		var childrenData = [];
		var parentData = {};
		for ( var item in dataList) {
			var itemData = dataList[item];
			// 主数据
			if (itemData.item_type == '0') {
				parentData = itemData;
				// 从数据
			} else {
				childrenData.push(itemData);
			}
		}
		dataObj.parentData = parentData;
		dataObj.childrenData = childrenData;
		return dataObj;
	}

	// 刷新页面控件
	refeshPageControl = function() {
		$("[name='startTime'],[name='endTime']").attr("disabled", true);
		$('select').attr("disabled", true);
		$("#traineeStatus").attr("disabled", false);
		$("#registeType").attr("disabled", false);
		$("#joinTime").attr("disabled", false);
		$('select').selectpicker('render');
		$('select').selectpicker('refresh');
		$('.bootstrap-select').css('height', '36px');
		$('.bootstrap-select button').css('height', '34px');
		$('.w260 .bootstrap-select').css('width','180px');
		customInput('allRelatedId');
		customInput('relatedIdCheckBox');
		customInput('allRelatedIdCouserUser');
		customInput('relatedIdCheckBoxCouserUser');
		// 移除学员标题头checked选中状态
		checkOrUncheckAll('allRelatedId','relatedIdCheckBox');
        checkOrUncheckAll('allRelatedIdCouserUser','relatedIdCheckBoxCouserUser');
		//disSelectCheckboxChecked("allRelatedId");
		//disSelectCheckboxChecked("allRelatedIdCouserUser");
		// 分页控件宽度不够，导致显示异常，手动设置其宽度
		$(".pagination").css("width", '500px');
	}

	// 管理课程学员(激活，暂停，替补，删除)
	manageTrainee = function(actionName) {
		var relatedId = getSelectedRowId("relatedIdCheckBoxCouserUser");
		var count =relatedId.split(",").length;
		if (!relatedId && relatedId.length == 0) {
			alertMsg("请选择至少选择一项！");
			return;
		}
		if(!validateApproveDateFrom()){
			alertMsg("已超过审批时间,不可审批");
			return ;
		}
		if(validateApproveDateTo()){
			alertMsg("未到审批时间，不可审批");
			return ;
		}
		var trainNumber = $("#baomingshenpi").find("[name='trainNumber']").val();
		var courseSubstituteNum=$("#courseSubstituteNumber").val();
		var substituteNum=parseInt(trainNumber)*parseInt(courseSubstituteNum)*0.01;
		// 剩余培训名额
		if (actionName == "active")
			var hasActive=getSubstitudeNum(0);
			if((relatedId.split(",").length+ hasActive)> trainNumber) {
			alertMsg("剩余培训名额不足");
			return;
		}
		// 剩余替补名额
		if (actionName == "substitute"){
			var hasSubstitude=getSubstitudeNum(2);
			if((relatedId.split(",").length+hasSubstitude) > substituteNum) {
			alertMsg("剩余替补名额不足");
			return;
			}
		}
		// 开课之前48小时内不允许审核
		if (isNotStartTime(addDate($('#stepStartTime').val(), -2))) {
			alertMsg("审批期已过");
			return;
		}
	
		// 获取当前操作配置参数
		var currentDataConfig = managerTraineeDataConfig[actionName];
		var url = currentDataConfig.url, data = {
			relatedId : relatedId
		};
		postAjaxRequest(url, data, function(result) {
			if (result.status == 1) {
				alertMsg(currentDataConfig.successMsg);
				if (actionName == "active"){
					$("#traineeNumber").text(parseInt($("#traineeNum").val())+count);
				    }
				// 重新检索数据
			    $('body').removeClass('modal-open').css('overflow-y','auto');
				loadCourseOfflineTraineeList(currentPageNo,null);
				
			} else {
				alertMsg(currentDataConfig.errorMsg);
			}
		});
	}
    getSubstitudeNum=function(userStatus){
    	var substitudeNum;
    	var url = "/course/offline/getSubstitudeNum.html", data = {
    			itemIdValue : $("#itemId").val(),
    			courseId:$("#courseId").val(),
    			userStatus:userStatus
    		};
    		postAjaxRequest(url, data, function(result) {
    			if (result.status == 1) {
    				substitudeNum= result.data;
    			} 
    		},false);
    	return substitudeNum;
    }
	// 获取选中行的id编号
	getSelectedRowId = function(name) {
		var finalValArray = [];
		$("input[name='" + name + "']").each(function() {
			if (($(this).is('[type=checkbox]')) && ($(this).prop('checked'))) {
				finalValArray.push($(this).attr("id"));
			}
			;
		});
		return finalValArray.join();
	};

	// 取消checkbox选中状态
	//取消checkbox选中状态
	var disSelectCheckboxChecked = function(checkBoxName){
		$("input[name='" + checkBoxName + "']").each(function(){
		  if($(this).is('[type=checkbox]')){
			var id = $(this).prop("id");
			$("#" + id).prop("checked",false);
			$("#" + id).next('label').removeClass('checked');
		  }
		});
	}
	//课程情况
	validateStartDate=function(){
		var startTime=$.trim($("#trainStartTime").val());
		var nowDay=dateFormat(new Date());
		var dateFrom = new Date(nowDay);
		var dateTo = new Date(startTime);
		var diff = dateTo.valueOf() - dateFrom.valueOf();
		var diff_day = parseInt(diff/(1000*60*60*24));
		if(diff_day<=0){
			return "正常";
		}else{
			return "未开始";
		}
	}
	validateApproveDateFrom=function(){
		var startTime=$.trim($("#trainStartTime").val());
		var nowDay=dateFormat(new Date());
		var dateFrom = new Date(nowDay);
		var dateTo = new Date(startTime);
		var diff = dateTo.valueOf() - dateFrom.valueOf();
		var diff_day = parseInt(diff/(1000*60*60*24));
		if(diff_day<=0){
			return false;
		}else{
			return true;
		}
	}
	validateApproveDateTo = function() {
		var enrollExpireTime = $("#enrollExpireTime").val();
		var day=dateFormat(enrollExpireTime)+" 00:00:00";
		var dateFrom = new Date(day);
		var nowDay=dateFormat(new Date())+" 00:00:00";
		var dateTo = new Date(nowDay);
		var diffStart = dateFrom.valueOf() - dateTo.valueOf();
		var diff_day_start = parseInt(diffStart / (1000 * 60 * 60 * 24));
		if (diff_day_start >=0) {
			return true;
		} else {
			return false;
		}
	};
	
   downloadTemplate = function() {
		go2Page("/course/down_excel_template.html");
	}
	
	init();
	
});

