	define(["jquery","utils.dtree","utils.cookie","jquery.dot","libs/step/step_page","pagination","utils","jquery.base","jquery.validate","ueditor",
	      "jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker"], function($,tree,cookie,doT,step) {
		
		var delFlg = '2';
		var insertFlg = '0';
		var updateFlg = '1';
		var startTimeFormat = " 00:00:00";
		var expireTimeFormat = " 23:59:00";
		
		var init = function(){
			//数据测试
			loadCourseOnliveContent();
		};
		
		//刷新页面控件
		refeshPageControl = function(){
			$('select').selectpicker({title:"---请选择---"});	
			$('select').selectpicker('render');
			$('select').selectpicker('refresh');
			$('.bootstrap-select').css('height','34px');
			$('.bootstrap-select button').css('height','34px');
			
			$(".form_datetime.min_min").datetimepicker({
		        format: "yyyy-mm-dd hh:ii",
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 0,
				forceParse: 0
		    }).on('changeDate', function(ev){
		    	var dateStr = $(this).val();
		    	var name = $(this).attr("name");
//		    	debugger;
		    	//设置起始时间
		    	var lableError = isDateTimePickerLabelError(this);
		    	if(lableError){
    				resetDateTimePickerForLweightValidate(this,dateStr);
	    		}
		    	if(name == 'startTime'){
		    		var expireTime = $(this).parent().parent().find("[name='expireTime']");
		    		var expireTimeVal = isDateTimePickerLabelError(expireTime)?"":$(expireTime).val();
		    		$(expireTime).datetimepicker('setStartDate',dateStr);
//		    		debugger;
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
		
		
		var refreshCourseOnliveContent = function(stepNo){
			loadCourseOnliveContent(stepNo);
		}
		
		//调整到编辑页面
		toCourseSetting = function(courseId){
			go2Page('/course/setting.html',"courseId="+courseId+"&courseType=2");
		};
		
		//加载线下课程指定次数内容数据
		loadCourseOnliveContent = function(stepNo,currentStepNo){
			if(stepNo == undefined)
				stepNo = 1;
			var params = {
				stepNo:stepNo,
				courseId:$("#courseId").val(),
			},url="/course/onlive/content/load_list.html";
			postAjaxRequest(url, params, function(result){
				
				var pxshijianTemplate = doT.template($("#course_onlive_dagang_box_pxshijian_data_template").text());
				var szkcTemplate = doT.template($("#course_onlive_dagang_box_sz_add_data_template").text());
				
				var resultObj = divideCourseOnliveContentParentData(result.data);
				//设置主数据
				var dataObj = resultObj.parentData;
				dataObj.trainerList = trainerList;
//				debugger;
				$('#courseOnliveContent').find(".pxshijian").html(pxshijianTemplate(dataObj));
				//设置从数据
				dataObj.data = resultObj.childrenData;
				
				$('#courseOnliveContent').find(".sz_add").remove();
				$('#courseOnliveContent').find(".szkc").append(szkcTemplate(dataObj));
				
				//更新内容刷新到相应的位置
				refeshPageControl();
//				
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
				
				bindFormValidate();

				
				if(currentStepNo == undefined){
					loadStepData(stepNo);
				}
			},null,null,false);
			
		}
		
		var bindFormValidate = function(){
			$("#courseOnliveContentForm").myValidate({
				formCall : function() {
					submitCourseOnliveContent();
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
			
			return pass;
		}
				
		//加载次数数据
		loadStepData = function(stepNo){
			
			if(!stepNo){
				stepNo = 1;
			}
			$("#stepNo").val(stepNo);
			var stepNum = $("#courseUnitCount").val();
			step.stepTemplateDiv("courseOnliveContent","loadCourseOnliveContent",Number(stepNum),stepNo,'第','单元');
		}
		
		//添加行数据
		addRowData = function(e){
			var szkcTemplate = doT.template($("#course_onlive_dagang_box_sz_add_data_template").text());
			var result = {};
			result.trainerList = trainerList;
			result.data = [];
			$(e).parent().after(szkcTemplate(result));
//			$('#courseOnliveContent').find(".szkc").append(szkcTemplate(result));
			//form 重新绑定  lweight 验证函数  （新增未绑定验证js，导致其行为异常）
			bindFormValidate();
			refeshPageControl();
		}
		
		//删除行数据
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
		
		//提交线下课程内容
		submitCourseOnliveContent = function(callback){
			var dataArray = [];
			var courseId = $("#courseId").val();
			var stepNo = $("#stepNo").val();
			$("#courseOnliveContent").find(".pxshijian,.sz_add").each(function(){
				var id = $(this).find("[name='itemId']").val();
				var operateType = $(this).find("[name='operateType']").val();
				//主键为空，并且为删除状态  则不计入数据
				if(!id && operateType == delFlg){
					return true;
				}
				var itemStepNo = stepNo;
				
				var itemStartTime = $(this).find("[name='startTime']").val();
				var itemExpireTime = $(this).find("[name='expireTime']").val();
				
				if($(this).is(".pxshijian")){
					itemStartTime = null;
					itemExpireTime = null;
				}
				
				var itemPeriod = $(this).find("[name='itemPeriod']").val();
				var itemName = $(this).find("[name='itemName']").val();
				var itemTrainers = $(this).find("[name='itemTraines']").val();
				if(itemTrainers){
					itemTrainers = itemTrainers.join();
				}
				var itemType = $(this).find("[name='itemType']").val();
				dataArray.push({
					id:id,operateType:operateType,courseId:courseId,itemStepNo:itemStepNo,
					startTime:itemStartTime,expireTime:itemExpireTime,itemPeriod:itemPeriod,
					itemName:itemName,itemTrainers:itemTrainers,itemType:itemType
				});
			});
			
			var courseId = $("#courseId").val();
			var url = "/course/onlive/content/submit.html", data = {
					contentJson : JSON.stringify(dataArray)
			};
			
			postAjaxRequest(url, data, function(result) {
				if(result.status == 1){
					
					if(callback != undefined){
						callback();
					}else{
						//刷新数据
						refreshCourseOnliveContent(stepNo);
						alertMsg("保存成功！");
					}
				}else{
					alertMsg("设置失败！");
				}
			});
		};
		
		//调整到编辑页面
		toCourseSetting = function(courseId){
			go2Page('/course/setting.html',"courseId="+courseId+"&courseType=3");
		};
		
		courseOnlivePublish = function(){
			var courseGroupItemType = 43;
			$("#publishModal").modal("show");
			$('#group_container_type').val(courseGroupItemType);
			$('#item_id').val($("#courseId").val());
		};
		
		//区分主从数据
		divideCourseOnliveContentParentData = function(dataList){
//			debugger;
			var dataObj = {};
			var childrenData = [];
			var parentData = {};
			for(var item in dataList){
				var itemData = dataList[item];
				//主数据
				if(itemData.item_type == '0'){
					parentData = itemData;
				//从数据
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

