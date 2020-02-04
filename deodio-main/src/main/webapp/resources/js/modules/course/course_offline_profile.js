 	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","jquery.tagator","pagination","utils","jquery.base",
	        "jquery.tagsInput","fileupload.common","jquery.validate","ueditor","jquery.scrolltofixed",
	        "ueditor.config","jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker"], function($,tree,cookie,list,doT,tagator) {
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
				forceParse: 0
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
		
		$('#courseOfflineInfoForm').myValidate({
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
			
			if(classificationFlag&&tagsFlag){
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
				if(!validateInputContent()){
					return false;
				}else{
				var courseRule=getRadioVal('courseRule');
				var courseInfo = {
						courseName:$.trim($("#courseName").val()),
						courseDescription:course_desc_um.getContentTxt(),
						courseObjective:course_target_um.getContentTxt(),
						courseInfomartion:course_attention_um.getContentTxt(),
						courseRule:courseRule,
						courseTrainTimes:$.trim($("#courseTrainTimes").val()),
						courseTraineeNum:$.trim($("#courseTraineeNum").val()),
						courseContactName:$.trim($("#courseContactName").val()),
						courseContactTel:$.trim($("#courseContactTel").val()),
						isPublic:getRadioVal('isPublic'),
						id: $.trim($("#courseId").val()),
						courseOfflineId: $.trim($("#courseOfflineId").val()),
						courseCoverImg: $.trim($("#courseCover").val()),
						startTime:$("#startTime").val(),
						expireTime:$("#expireTime").val(),
						courseSubstituteNumber:$("#substituteNumber").val()
				};
//				debugger;
				var url="/course/offline/profile/submit.html",data={
						courseInfoJson :JSON.stringify(courseInfo),
						attachId : $.trim($("#courseCoverId").val()),
						tagsJson : JSON.stringify(tagsList),
						classificationJson : JSON.stringify(classificationList)
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						go2Page("/course/offline/content.html","courseId="+result.data+"&trainerType="+courseRule);
					}else{
						alertMsg("设置失败！");
					}
				});
			}
		  }
		}
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
						itemType:22
					};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						$("#hotTagsList").empty();
						$.each(result.data,function(i,item){
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
			updateEditState();
			window.location.href=ctx+'/course/offline/list.html?&r=' + Math.floor(Math.random() * 100);
		}
		
		//删除身份图片
		deleteFileImg = function(){
			$("#preImgId").attr("src",ctx+"/resources/img/account/user-card-1.jpg");
			$("#courseCover").val("");
		}
		
		//验证课程名唯一
		validateExistName = function(){
			var url="/course/isExistName.html",data={
					courseName : $.trim($("#courseName").val()),
					courseId : $.trim($("#courseId").val()),
					courseType:2
			};
			var flag = false;
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var courseId = $.trim($("#courseId").val());
					var count = result.data.length;
					if(count>0){
						if(courseId!=result.data[0].id){
							console.log("存在");
							flag = true;
						}
					}
				}
			},false);
			return flag;
		}
		//课程开始日期必须是7天后
		validateStartDate=function(){
			var startTime=$.trim($("#startTime").val());
			var nowDay=dateFormat(new Date());
			var dateFrom = new Date(nowDay);
			var dateTo = new Date(startTime);
			var diff = dateTo.valueOf() - dateFrom.valueOf();
			var diff_day = parseInt(diff/(1000*60*60*24));
			if(diff_day<7){
				return true;
			}
		}
		validateInputContent=function(){
			var courseDesc=course_desc_um.getContentTxt();
			var courseTarget=course_target_um.getContentTxt();
			var courseAttention=course_attention_um.getContentTxt();
			if(courseDesc.length>255){
				alertMsg("课程描述字数不可超过255字");
				return false;
			}
			if(courseTarget.length>255){
				alertMsg("课程目标字数不可超过255字");
				return false;
			}
			if(courseAttention.length>255){
				alertMsg("课程须知字数不可超过255字");
				return false;
			}else{
				return true;
			}
		}
		validateSubstitudeNum=function(){
			var substituteNumber=$.trim($("#substituteNumber").val());
			var reg=/(^[1-9][0-9]$|^[0-9]$|^100$)/;
			if(!reg.test(substituteNumber)){
				return true;
			}
		}
	});

