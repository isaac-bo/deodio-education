define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","jquery.tagator","pagination","utils","jquery.base",
        "jquery.tagsInput","fileupload.common","jquery.validate","ueditor","jquery.scrolltofixed",
        "ueditor.config","jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker"],
        function($,tree,cookie,list,doT,tagator) {
	
	course_desc_um = UM.getEditor('course_desc_template');
	course_target_um = UM.getEditor('course_target_template');
	course_attention_um = UM.getEditor('course_attention_template');
	var isInner = false;
	var isTags = false;
	
	var init = function(){
		//标签控件
		$('#inputTagator').tagator({
			// 输入以下单词自动补全, 支持中文
			autocomplete: ['','']
		});

		customInput('courseRule');
		customInput('coursePassRequire');
		customInput('coursePassScoreIncludeExam');
		customInput('isPublic');
		$('select').selectpicker();
		
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
			startDate:new Date()
			
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
		
		list.onFixedItems();
		
		$("#course_pass_require1").click(function(){
			if (this.checked) {
				$("#coursePassRequireLearningProgress").attr("check-type","scoreNumber");				
			} else {
				$("#coursePassRequireLearningProgress").removeAttr("check-type");
				$("#coursePassRequireLearningProgress").parent().removeClass("wrong-item");
			}
		});
		$("#coursePassRequireLearningProgress").blur(function(){
			if ($("#course_pass_require1").is(":checked")) {
				$("#coursePassRequireLearningProgress").attr("check-type","scoreNumber");	
			}

		})
		$("#course_pass_require2").click(function(){
			if (this.checked) {
				$("#course_pass_require3").removeAttr("disabled");
			} else {
				$("#course_pass_require3").attr("disabled","disabled");
				$("#course_pass_require3").removeAttr("checked");
				$("#coursePassRequire3LabelId").removeClass("checked");
			}
		});
		
	}
	init();
	
	var uploadUrl = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#courseId").val());
	uploadFileJqProcess("courseUploadFile",uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
			1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
		var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
		var srcimgTemp = data.result.data.generateName;
		$("#preImgId").attr("src",srcimg);
		$("#courseCover").val(srcimgTemp);
		$("#courseCoverId").val(data.result.data.id);
	},function(e,data){
		
	});
	
	$('#courseOnlineInfoForm').myValidate({
		formCall:function(){setCourseInfo();},
		isAlert:false,
		formKey:false,
		errorCustom:{customFlag:true,regionText:true},
		errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
	});
	
	var setCourseInfo = function(){
		var classificationFlag = true;
		var tagsFlag = true;
		//获取分类个数
		var classificationLength = $("#inputGeneres .sel_btn").length;
		if(classificationLength<1){
			//分类
			$("#inputGeneres").removeClass("right-item");
			$("#inputGeneres").addClass("wrong-item");
			$("#inputGeneres").next('div').remove();
			$("#inputGeneres").after("<div style='margin-left:360px;margin-top:-30px;'><span class='help-inline error'>请选择所属分类！</span></div>");
			classificationFlag = false;
		}else{
			$("#inputGeneres").addClass("right-item");
			$("#inputGeneres").removeClass("wrong-item");
			$("#inputGeneres").next('div').remove();
			classificationFlag = true;
		}
		//获取标签个数
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
		var descFlag = true, targetFlag = true, attentionFlag = true;
		var courseDesc = course_desc_um.getContentTxt();
		var courseTarget = course_target_um.getContentTxt();
		var courseAttention = course_attention_um.getContentTxt();
		if (courseDesc.length > 255) {
			$("#courseDesc").removeClass("right-item");
			$("#courseDesc").addClass("wrong-item");
			alertMsg("课程描述字数不可超过255字");
			descFlag = false;
		} else {
			$("#courseDesc").addClass("right-item");
			$("#courseDesc").removeClass("wrong-item");
			descFlag = true;
		}
		if (courseTarget.length > 255) {
			$("#courseTarget").removeClass("right-item");
			$("#courseTarget").addClass("wrong-item");
			alertMsg("课程目标字数不可超过255字");
			targetFlag = false;
		} else {
			$("#courseTarget").addClass("right-item");
			$("#courseTarget").removeClass("wrong-item");
			targetFlag = true;
		}
		if (courseAttention.length > 255) {
			$("#courseAttention").removeClass("right-item");
			$("#courseAttention").addClass("wrong-item");
			alertMsg("课程须知字数不可超过255字");
			attentionFlag = false;
		} else {
			$("#courseAttention").addClass("right-item");
			$("#courseAttention").removeClass("wrong-item");
			attentionFlag = true;
		}
		if(classificationFlag&&tagsFlag&&descFlag&&targetFlag&&attentionFlag){
			//标签
			var tagsList = [];
			$(".tagator__span").each(function(item){
				var obj = new Object();
				obj.tagName=$.trim($(this).text());
				obj.tagSource = 0;
				tagsList.push(obj);
			});
			//所属分类
			var classificationList = [];
			$(".sel_btn").each(function(item){
				var obj = new Object();
				obj.classificationId = $(this).children().attr("classification");
				classificationList.push(obj);
			});
			var coursePassRequireLearningProgress = function() {
				if ($("#coursePassRequire1LabelId").hasClass('checked')) {
					return $("#coursePassRequireLearningProgress").val();
				} 
				return "";
			} 
			var courseInfo = {
					courseName : $.trim($("#courseName").val()),
					courseDescription : course_desc_um.getContentTxt(),
					courseObjective : course_target_um.getContentTxt(),
					courseInfomartion : course_attention_um.getContentTxt(),
					courseRule : $("input[name='courseRule']:checked").val(),
//					coursePassTime:$.trim($("#coursePassTime").val()),
					coursePassScore : $.trim($("#coursePassScore").val()),
					coursePassScoreIncludeExam : getCheckVal('coursePassScoreIncludeExamLabelId'),
					coursePassRequireLearningProgress : coursePassRequireLearningProgress(),
					coursePassRequirePassExam : getCheckVal('coursePassRequire2LabelId'),
					coursePassRequirePassExamIncludeExam : getCheckVal('coursePassRequire3LabelId'),
					isPublic : $("input[name='isPublic']:checked").val(),
					id : $.trim($("#courseId").val()),
					courseOnlineId : $.trim($("#courseOnlineId").val()),
					courseCoverImg : $.trim($("#courseCover").val()),
					startTime : $("#startTime").val(),
					expireTime : $("#expireTime").val()
			};
			var url="/course/online/submit_profile.html",data={
					courseInfoJson :JSON.stringify(courseInfo),
					attachId : $.trim($("#courseCoverId").val()),
					tagsJson : JSON.stringify(tagsList),
					classificationJson : JSON.stringify(classificationList)
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					go2Page("/course/online/content.html","courseId="+result.data);
				}else{
					alertMsg("设置失败！");
				}
			});
		}
	}
	
/*	uploadProcess('courseUploadFile','*.jpg;*.png;',{accountId:cookie.getCookie('CAID'),attachRelType:"6",isModel:true},ctx+'/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#courseId").val())+'&r=' + Math.floor(Math.random() * 100)
			,function(fileName){
				//alert(fileName);
			}
			,function(data){
				var srcimg = imgStatic+data.attachUrl+data.generateName;
				var srcimgTemp = data.generateName;
				$("#preImgId").attr("src",srcimg);
				$("#courseCover").val(srcimgTemp);
				$("#courseCoverId").val(data.id);
			},true,"上传附件");*/
	
	//热门标签列表
	showHotTagsList = function(){
		var isHide = $("#hotTagsList").is(":hidden");
		if(isHide){
			$("#hotTagsList").show();
			//所属分类
			var classificationList = [];
			$(".sel_btn").each(function(item){
				var obj = new Object();
				obj.classificationId = $(this).children().attr("classification");
				classificationList.push(obj);
			});
			var url="/tags/hot/list.html",data={
				itemType:21
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					//console.log(result.data);
					$("#hotTagsList").empty();
					$.each(result.data,function(i,item){
						//$("#hotTagsList").append("<span style=cursor:pointer' id='10003'>"+item.tag_name+"</span>");
						$("#hotTagsList").append("<button type='button' onclick='setHotTag(this)'>"+item.tag_name+"</button>");
						
					});
				}else{
					console.log("获取热门标签失败！");
				}
			});
		}else{
			$("#hotTagsList").hide();
		}
	}
//		
	//选择热点标签
	setHotTag = function(obj){
		//选择一个热点标签添加显示
		var e = jQuery.Event("keydown");//模拟一个键盘事件 
		e.keyCode = 188;//keyCode=188是逗号
		$('.tagator_input').val($(obj).text())
		$(".tagator_input").trigger(e);//模拟按下逗号建
	}
	
	popTagsPicker = function(){
		if($('#hotTagsList').is(':hidden')){
			showHotTagsList();
		}
	};
	
	blurTagsPicker = function(){
		if(!$('#hotTagsList').is(':hidden') && isTags == false){
			$('#hotTagsList').hide();
		}
	};
	
	
	//关闭分类窗口
	closeClassificationWin = function(){
		closePopWindow();
	}
	
	//删除所属分类标签
	delLabel = function(obj){
		var spanDom = $(obj).parent();
		spanDom.remove();
	}
	
	//新增标签
	addLabel = function(obj){
		var comboText = $(obj).text();
		$("#labelNodes").before('<span class="sel_btn">'+comboText+'<button type="button" class="sel_del" onclick="delLabel(this);">&times;</button></span>');
	}
	//设置presentation信息-返回上一级
	goBack = function(){
		window.location.href=ctx+'/course/online/list.html?&r=' + Math.floor(Math.random() * 100);
	}
	
	//删除身份图片
	deleteFileImg = function(){
		$("#preImgId").attr("src",ctx+"/resources/img/account/user-card-1.jpg");
		$("#courseCover").val("");
	}
	
	//验证课程名唯一
//		validateExistName = function(){
//			var url="/course/isExistName.html",data={
//					courseName : $.trim($("#courseName").val()),
//					courseId : $.trim($("#courseId").val())
//			};
//			var flag = false;
//			postAjaxRequest(url, data, function(result){
//				if(result.status == 1){
//					var courseId = $.trim($("#courseId").val());
//					var count = result.data.length;
//					if(count>0){
//						if(courseId!=result.data[0].id){
//							console.log("存在");
//							flag = true;
//						}
//					}
//				}
//			},false);
//			return flag;
//		}
	validateCourseName = function() {
		var flag = false;
		var url = "/course/online/validate_course_name.html", 
		data = {courseName : $.trim($("#courseName").val())};
		if ($.trim($("#courseName").val()) != $("#oldCourseName").val()) {
			postAjaxRequest(url, data, function(result){
				if (!result.data) {
					flag = true;
				} 
			}, false);
		}
		return flag;
	}
	//有效开始时间必须是当前日期及以后
	validateStartDate = function() {
		var startTime = $.trim($("#startTime").val());
		var dateFrom = new Date((new Date()).toLocaleDateString());
		var dateTo = new Date(startTime);
		if ((dateTo.valueOf() - dateFrom.valueOf()) < 1 ) {
			return true;
		}
	}
	
//	validateVal = function(e) {
//		if(!/^[0-9]\d*$/.test($.trim(e.val()))){
//			flag = true;
//		}else{
//			if(e.val() < 0 || e.val() > 100){
//				flag = true;
//			}
//		}
//		return flag;
//	}
//	getCheckVal = function(name){
//		var finalValArray = [];
//		$("input[name='"+name+"']").each(function(){
//		  if($(this).prop('checked')){
//			  finalValArray.push($(this).val());
//		   };
//		});
//		return finalValArray.join();
//	};
	
	getCheckVal = function(id) {
		if ($("#"+id).hasClass('checked')) {
			return 1;
		}
		return 0;
	} 
});

