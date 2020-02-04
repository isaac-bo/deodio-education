	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","libs/step/step_page","pagination","utils","jquery.base","jquery.validate","ueditor",
	      "jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker"], function($,tree,cookie,list,doT,step) {
		
		var delFlg = '2';
		var insertFlg = '0';
		var updateFlg = '1';
		var startTimeFormat = " 00:00";
		var expireTimeFormat = " 23:59";
		
		var init = function(){
			// 数据测试
			loadCourseOfflineContent();
			list.onFixedItems();
			customInput('isRegisteDesk');
			//日期控件
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
		
		// 刷新页面控件
		refeshPageControl = function(){
			// $('select').selectpicker({title:"---请选择---"});
			$('select').selectpicker('render');
			$('select').selectpicker('refresh');
			$('.bootstrap-select').css('height','34px');
			$('.bootstrap-select').css('width','180px');
			$('.bootstrap-select button').css('height','32px');
			
			// 日期控件
			$(".form_datetime.min_day").datetimepicker({
		        format: "yyyy-mm-dd",
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    }).on('changeDate', function(ev){
		    	// 获取当前时间
		    	var dateStr = $(this).val();
		    	// 获取Id
		    	var id = $(this).attr("id");
		    	// 获取时间的开始时间（yyyy-mm-dd hh:mm:ss）
		    	var dateStartStr = dateStr + startTimeFormat;
		    	// 获取时间的结束时间（yyyy-mm-dd hh:mm:ss）
		    	var dateEndStr = dateStr + expireTimeFormat;
		    	var lableError = isDateTimePickerLabelError(this);
		    	// 为错误状态，重置并设定本身数值
		    	if(lableError){
    				resetDateTimePickerForLweightValidate(this,dateStr);
	    		}
		    	// 设置起始时间
		    	if(id == 'stepStartTime'){
		    		// 设置从数据的起始时间
		    		$(".form_datetime.min_min").each(function(){
		    			var _self = $(this);
		    			var name = $(this).attr("name");
		    			if(name == 'expireTime'){
		    				var startTime = $(this).datetimepicker('getFormattedStartDate');
		    				// 设定值早于原设定值，则值不变
		    				if(dateStartStr < startTime){
		    					return true;
		    				}
		    			}
		    			$(this).datetimepicker('setStartDate',dateStartStr);
		    		});
		    		var stepExpireTime = $("#stepExpireTime");
		    		// 设定主数据的结束时间选择控件的开始时间
		    		$(stepExpireTime).datetimepicker('setStartDate',dateStartStr);
		    		if(dateStr > $(stepExpireTime).val()){
		    			$(stepExpireTime).val(dateStr);
		    			$(stepExpireTime).trigger("changeDate");
		    			// 为错误状态，重置并设定stepExpireTime数值
		    			if(lableError){
		    				resetDateTimePickerForLweightValidate(stepExpireTime,dateStr);
			    		}
		    		}
		    	}else{
		    		// 设置终止时间
		    		var target = $(".form_datetime.min_min");
		    		target.datetimepicker('setEndDate',dateEndStr);
		    	}
		    	valiateChildrenDateAfterParentTimeModify();
		    });
			
			$(".form_datetime.min_min").datetimepicker({
		        format: "yyyy-mm-dd hh:ii",
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 0,
				forceParse: 0,
				startDate :$("#stepStartTime").val() + startTimeFormat,
				endDate:$("#stepExpireTime").val() + expireTimeFormat
		    }).on('changeDate', function(ev){
		    	var dateStr = $(this).val();
		    	var name = $(this).attr("name");
		    	var lableError = isDateTimePickerLabelError(this);
		    	if(lableError){
    				resetDateTimePickerForLweightValidate(this,dateStr);
	    		}
		    	// 设置起始时间
		    	if(name == 'startTime'){
		    		var expireTime = $(this).parent().parent().find("[name='expireTime']");
		    		var expireTimeVal = $(expireTime).val();
		    		$(expireTime).datetimepicker('setStartDate',dateStr);
		    		if(dateStr > expireTimeVal){
		    			$(expireTime).val(dateStr);
		    			if(lableError){
		    				resetDateTimePickerForLweightValidate(expireTime,dateStr);
		    			}
		    			$(".form_datetime.min_min").datetimepicker('update');
		    		}
		    	}
		    });
		}
		
		// 主数据时间变更校验从数据时间，若开始时间早已住数据时间，自动更正为主数据开始时间;若从数据结束时间晚于主数据结束时间，则从数据结束时间自动更改为主数据结束时间
		valiateChildrenDateAfterParentTimeModify = function(){
			var parentStartTime = dateFormat($("#stepStartTime").val()) + startTimeFormat;
			var parentEndTime = dateFormat($("#stepExpireTime").val()) + expireTimeFormat;
			$(".form_datetime.min_min").each(function(){
				var name = $(this).attr("name");
				var value = $(this).val();
				if(name == 'startTime'){
					if(value < parentStartTime){
						$(this).val(parentStartTime)
						$(this).trigger("changeDate");
					}
					
					if(value > parentEndTime){
						$(this).val(parentEndTime)
						$(this).trigger("changeDate");
					}
					
				}else{
					if(value > parentEndTime){
						$(this).val(parentEndTime);
					}
				}
				
			});
			$(".form_datetime").datetimepicker('update');
		}
		
		// 调整到编辑页面
		toCourseSetting = function(courseId){
			go2Page('/course/setting.html',"courseId="+courseId+"&courseType=2");
		};
		
		// 设置信息-返回上一级
		goBack = function(courseId){
			go2Page('/course/offline/profile.html',"courseId="+courseId+"&courseType=2");
		}
		
		var  refreshCourseOfflineContent = function(stepNo){
			loadCourseOfflineContent(stepNo);
		}
		
		// 加载线下课程指定次数内容数据
		loadCourseOfflineContent = function(stepNo,currentStepNo){	
			if(stepNo == undefined)
				stepNo = 1;
			var params = {
				stepNo:stepNo,
				courseId:$("#courseId").val(),
			},url="/course/offline/content/load_list.html";
			postAjaxRequest(url, params, function(result){
				
				var pxshijianTemplate = doT.template($("#dagang_box_pxshijian_data_template_1").text());
				var szkcTemplate = doT.template($("#dagang_box_sz_add_data_template").text());
				
				var resultObj = divideCourseOffContentParentData(result.data);
				// 设置主数据
				var dataObj = resultObj.parentData;
				dataObj.trainerList = trainerList;
				dataObj.locationList = locationList;
				$('#courseOfflineContent').find(".pxshijian").html(pxshijianTemplate(dataObj));
				// 设置从数据
				dataObj.data = resultObj.childrenData;
				
				$('#courseOfflineContent').find(".sz_add").remove();
				$('#courseOfflineContent').find(".szkc").append(szkcTemplate(dataObj));
				
				// 更新内容刷新到相应的位置
				refeshPageControl();
				if(stepNo < currentStepNo && currentStepNo != undefined){
					$('.num_bg_pre').show();
					$('.num_bg_cur').animate({bottom:'-4px'},'normal',function(){
						$(this).fadeOut(500);
						loadStepData(stepNo);
					});
					$('.num_bg_pre').animate({top:'0px'}, "normal");
					
				}else if(stepNo > currentStepNo && currentStepNo != undefined){
					
					$('.num_bg_nex').show();
					$('.num_bg_cur').animate({top:'-14px'},'normal',function(){
						$(this).fadeOut(500);
						loadStepData(stepNo);
					});
					$('.num_bg_nex').animate({top:'0px'}, "normal");
					
				}
				
				bindCourseOfflineContentFormWithValidate();
				
				if(currentStepNo == undefined){
					loadStepData(stepNo);
				}
			},null,null,false);
			
		}
		submitRegistedUsers = function(){
			var data={
					id:$("#registedUsersId").val(),
					courseId:$("#courseId").val(),
					isRegisteDesk:getRadioVal("isRegisteDesk") || 0 ,
					startTime:$("#enrollStartTime").val(),
					expireTime:$("#enrollExpireTime").val()
			};
			var dataJson = {
					dataJson:JSON.stringify(data)
			}
			courseSettingCallBackFunc(dataJson);
		}
		//课程规则设置回调函数
		courseSettingCallBackFunc = function(dataObj){
			var url = "/course/registed_users/submit.html";
			var data=dataObj;
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
				}else{
					alertMsg("抱歉，因为网络问题该相关课程设置失败，请重试！");
				}
			});
		};
		var bindCourseOfflineContentFormWithValidate = function(){
			
			$("#courseOfflineContentForm").myValidate({
				formCall : function() {
					submitCourseOfflineContent();
				},
				isAlert : false,
				formKey : false,
				errorCustom : {
					customFlag : true,
					regionText : true
				},
				errorStyle : {
					errorRegion : "error-div",
					errorClass : "wrong-item",
					rightClass : "right-item"
				}
			});
		}
		
		
		validateForm = function(){
			var pass = true;
			
			if($.trim($('#stepStartTime').val()).length <=0 ){
				pass = false;
				$('#stepStartTime').parent().addClass('wrong-item').removeClass('right-item');
			}else{
				$('#stepStartTime').parent().addClass('right-item').removeClass('wrong-item');
			}
			
			if($.trim($('#stepExpireTime').val()).length <=0 ){
				
				pass = false;
				$('#stepExpireTime').parent().addClass('wrong-item').removeClass('right-item');
			}else{
				$('#stepExpireTime').parent().addClass('right-item').removeClass('wrong-item');
			}
            if($.trim($('#trainLocation').val()).length <=0 ){
				
				pass = false;
				$('#trainLocation').parent().addClass('wrong-item').removeClass('right-item');
			}else{
				$('#trainLocation').parent().addClass('right-item').removeClass('wrong-item');
			}
           if($.trim($('#itemTraines').val()).length <=0 ){
				
				pass = false;
				$('#itemTraines').parent().addClass('wrong-item').removeClass('right-item');
			}else{
				$('#itemTraines').parent().addClass('right-item').removeClass('wrong-item');
			}
			return pass;
		}
				
		// 加载次数数据
		loadStepData = function(stepNo){
			
			if(!stepNo){
				stepNo = 1;
			}
			$("#stepNo").val(stepNo);
			var stepNum = $("#courseTrainTimes").val();
			step.stepTemplateDiv("courseOfflineContent","loadCourseOfflineContent",Number(stepNum),stepNo);
			$('.right_cont').css('min-height',$('.left_lev').height() + 'px');
		}
		
		// 添加行数据
		addRowData = function(e){
			var szkcTemplate = doT.template($("#dagang_box_sz_add_data_template").text());
			var result = {};
			result.trainerList = trainerList;
			result.data = [];
			$(e).parent().after(szkcTemplate(result));
			// 新增行与已存行 js行为不同，重新绑定验证js
			bindCourseOfflineContentFormWithValidate();
// $('#courseOfflineContent').find(".szkc").append(szkcTemplate(result));
			refeshPageControl();
		}
		
		// 删除行数据
		delRowData = function(e){
			var szSize = $(".sz_add").size();
			if(szSize < 2 ){
				alertMsg("删除失败，请确认至少保存一条数据！");
			}else{
				var container = $(e).parent();
				$(container).find("[name='operateType']").val(delFlg);
				$(container).hide();
			}
			
		}
		
		// 提交线下课程内容
		submitCourseOfflineContent = function(callback){
			var dataArray = [];
			var courseId = $("#courseId").val();
			var stepNo = $("#stepNo").val();
			var flag=false,trainFlag=true;
			var count=0;
			$("#courseOfflineContent").find(".pxshijian,.sz_add").each(function(){
				var id = $(this).find("[name='itemId']").val();
				var operateType = $(this).find("[name='operateType']").val();
				// 主键为空，并且为删除状态 则不计入数据
				if(!id && operateType == delFlg){
					return true;
				}
				var itemStepNo = stepNo;
				var itemStartTime = $(this).find("[name='startTime']").val();
				var itemExpireTime = $(this).find("[name='expireTime']").val();
				
				if($(this).is(".pxshijian")){
					itemStartTime = itemStartTime + " 00:00";
					itemExpireTime = itemExpireTime + " 00:00";
				}
				flag=validateTrainneTime(itemStartTime,itemExpireTime);
				if(flag && itemStartTime){
					alertMsg("培训时间不在课程设置的培训时间范围内,请确认!");
					trainFlag=false;
					return false;
				}else{
				var itemTrainLocation = $(this).find("[name='trainLocation']").val();
				var itemTrainName = $(this).find("[name='trainName']").val();
				var itemTrainers = $(this).find("[name='itemTraines']").val();
				var trainNumber = $(this).find("[name='trainNumber']").val();
				if(trainNumber){
				   count=parseInt(trainNumber);
				}
				if(itemTrainers){
					itemTrainers = itemTrainers.join();
				}
				var itemType = $(this).find("[name='itemType']").val();
				dataArray.push({
					id:id,operateType:operateType,courseId:courseId,itemStepNo:itemStepNo,
					startTime:itemStartTime,expireTime:itemExpireTime,itemTrainLocation:itemTrainLocation,
					itemTrainName:itemTrainName,itemTrainers:itemTrainers,itemType:itemType,
					traineeJoinNum:trainNumber
				});
				}
			});
		if(trainFlag){
			var traineeNum = parseInt($("#traineeNum").val());
			count=count+getSumTrainee();
			if(traineeNum<count){
					alertMsg("总共制定的人数"+count+"人,超过设定的人数"+traineeNum+"人,请修改!");
					return false;
			}
			var courseId = $("#courseId").val();
			var url = "/course/offline/content/submit.html", data = {
					contentJson : JSON.stringify(dataArray)
			};
			
			postAjaxRequest(url, data, function(result) {
				if(result.status == 1){
					if(callback != undefined){
						callback();
					}else{
						refreshCourseOfflineContent(parseInt(stepNo));
						$('body').removeClass('modal-open').css('overflow-y','auto');
						alertMsg("保存成功！");
					}
				}else{
					alertMsg("设置失败！");
				}
			});
		 }
		submitRegistedUsers();//保存课程规则
		};
		getSumTrainee=function(){
			var count=0;
			var url="/course/offline/content/getSumTrainee.html";data = {
					courseId : $("#courseId").val(),
					stepNo :$("#stepNo").val()
			};
			postAjaxRequest(url, data, function(result) {
				if(result.status == 1){
					count=result.data;
				}
			},false);
			return count;
		}
		validateTrainneTime=function(startTime,expireTime){
			    var trainStartTime=$("#trainStartTime").val();
			    var trainEndTime=$("#trainEndTime").val();
				var dateFrom = new Date(startTime);
				var dateTo = new Date(expireTime);
				var startTime=new Date(trainStartTime);
				var endTime=new Date(trainEndTime);
				var diffStart = dateFrom.valueOf() - startTime.valueOf();
				var diffEnd = dateTo.valueOf() - endTime.valueOf();
				var diff_day_start = parseInt(diffStart/(1000*60*60*24));
				var diff_day_end = parseInt(diffEnd/(1000*60*60*24));
				if(diff_day_start<0){
					return true;
				}if(diff_day_end>0){
					return true;
				}else{
					return false;
				}
		}
		validateEnrollExpireDate=function(){
			    var trainStartTime=$("#trainStartTime").val();
			    var enrollExpireTime=$("#enrollExpireTime").val()+" 00:00:00.0";
				var dateFrom = new Date(trainStartTime);
				var dateTo = new Date(enrollExpireTime);
				var diffStart = dateFrom.valueOf() - dateTo.valueOf();
				var diff_day_start = parseInt(diffStart/(1000*60*60*24));
				if(diff_day_start>6){
					return false;
				}else{
					return true;
				}
		}
		validateFormLocation = function(){
			var pass = true;
            if($.trim($('#trainLocation').val()).length <=0 ){
				
				pass = false;
				$('#trainLocation').parent().addClass('wrong-item').removeClass('right-item');
			}else{
				$('#trainLocation').parent().addClass('right-item').removeClass('wrong-item');
			}
           if($.trim($('#itemTraines').val()).length <=0 ){
				
				pass = false;
				$('#itemTraines').parent().addClass('wrong-item').removeClass('right-item');
			}else{
				$('#itemTraines').parent().addClass('right-item').removeClass('wrong-item');
			}
			return pass;
		}

		
		courseOfflinePublish = function(){
			var publishFlag=true;
			var enrollStartTime=$("#enrollStartTime").val();
			var enrollExpireTime=$("#enrollExpireTime").val();
			if(enrollStartTime.length==0||enrollExpireTime.length==0){
				alertMsg("报名起止时间未设置,不能发布!");
				return false;
			}else{
				var startDate = Number(enrollStartTime.replace(/-/g,'')),endDate=Number(enrollExpireTime.replace(/-/g,''));	
				if(isNaN(startDate)||isNaN(endDate)){
					alertMsg("报名起止时间未设置,不能发布!");
					return false;
				}
			}
			if(validateEnrollExpireDate()){
				alertMsg("距离课程开始时间至少大于7天,不能发布!");
				return false;
			}
			$("#courseOfflineContent").find(".pxshijian,.sz_add").each(function(){
				var itemStartTime = $(this).find("[name='startTime']").val();
				var itemExpireTime = $(this).find("[name='expireTime']").val();
				
				if($(this).is(".pxshijian")){
					itemStartTime = itemStartTime + " 00:00";
					itemExpireTime = itemExpireTime + " 00:00";
				}
				var flag=validateTrainneTime(itemStartTime,itemExpireTime);
				if(flag && itemStartTime){
					alertMsg("培训时间不在课程设置的培训时间范围内,无法发布!");
					publishFlag=false;
					return false;
				}
			});
			if(publishFlag){
			var trainTimes=0;
			var setTimes=$("#courseTrainTimes").val();
			var isPublic=$("#isPublic").val();
			var url="/course/offline/CourseOfflineItemCount.html",params={
					courseId:$("#courseId").val()
			}
			postAjaxRequest(url, params, function(result) {
				if(result.status == 1){
					trainTimes= result.data;
					if(trainTimes!=setTimes){
						alertMsg("培训次数与设置不符请确认!");
						return false;
					}else{
						submitRegistedUsers();
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
			}
		};

		// 区分主从数据
		divideCourseOffContentParentData = function(dataList){
			var dataObj = {};
			var childrenData = [];
			var parentData = {};
			for(var item in dataList){
				var itemData = dataList[item];
				// 主数据
				if(itemData.item_type == '0'){
					parentData = itemData;
				// 从数据
				}else{
					childrenData.push(itemData);
				}
				
			}
			dataObj.parentData = parentData;
			dataObj.childrenData = childrenData;
			return dataObj;
		}
		init();
	});

