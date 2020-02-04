	define(["jquery","utils.dtree","utils.cookie","jquery.dot","jquery.tagator","pagination","utils","jquery.base",
	        "jquery.tagsInput","upload.common","jquery.validate","ueditor","jquery.scrolltofixed",
	        "ueditor.config","jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker"], function($,tree,cookie,doT,tagator) {
		
		var package_desc_um = UM.getEditor('package_desc_template');
		var package_objective_um = UM.getEditor('package_objective_template');
		var package_infomartion_um = UM.getEditor('package_infomartion_template');
		
		var init = function(){
			
			//标签控件
			$('#inputTagator').tagator({
				// 输入以下单词自动补全, 支持中文
				autocomplete: ['','']
			});
			
			customInput('packageType');
			$('select').selectpicker();
		}
		init();
		
		$('#coursePackageProfileForm').myValidate({
			formCall:function(){setCoursePackageInfo();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		var setCoursePackageInfo = function(){
//			debugger;
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
				
				var coursePackageInfo = {
						packageName:$.trim($("#packageName").val()),
						packageType:getRadioVal('packageType'),
						packageSeries:$.trim($("#packageSeries").val()),
						packageDesc:package_desc_um.getContentTxt(),
						packageObjective:package_objective_um.getContentTxt(),
						packageInfomartion:package_infomartion_um.getContentTxt(),
						id: $.trim($("#packageId").val()),
						packageCoverImg: $.trim($("#packageCover").val())
				};
//				debugger;
				var url="/course/packages/submit_profile.html",data={
						packageInfoJson :JSON.stringify(coursePackageInfo),
						attachId : $.trim($("#packageCoverId").val()),
						tagsJson : JSON.stringify(tagsList),
						classificationJson : JSON.stringify(classificationList)
				};
				
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						go2Page("/course/packages/content.html","packageId="+result.data);
					}else{
						alertMsg("设置失败！");
					}
				});
			}
		}
		
		uploadProcess('packageUploadFile','*.jpg;*.png;',{accountId:cookie.getCookie('CAID'),attachRelType:"6",isModel:true},ctx+'/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#packageId").val())+'&r=' + Math.floor(Math.random() * 100)
				,function(fileName){
					//alert(fileName);
				}
				,function(data){
					var srcimg = imgStatic+data.attachUrl+data.generateName;
					var srcimgTemp = data.generateName;
					$("#preImgId").attr("src",srcimg);
					$("#packageCover").val(srcimgTemp);
					$("#packageCoverId").val(data.id);
				},true,"上传身份");
		
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
					itemType:4
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
		
		//选择热点标签
		setHotTag = function(obj){
			//选择一个热点标签添加显示
			var e = jQuery.Event("keydown");//模拟一个键盘事件 
			e.keyCode = 188;//keyCode=188是逗号
			$('.tagator_input').val($(obj).text())
			$(".tagator_input").trigger(e);//模拟按下逗号建
		}
		
		
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
			window.location.href=ctx+'/course/packages/list.html?&r=' + Math.floor(Math.random() * 100);
		}
		
		//删除身份图片
		deleteFileImg = function(){
			$("#preImgId").attr("src",ctx+"/resources/img/account/user-card-1.jpg");
			$("#packageCoverId").val("");
		}
		
		//验证课程名唯一
		validateExistName = function(){
			var url="/course/packages/isExistName.html",data={
					packageName : $.trim($("#packageName").val()),
					packageId : $.trim($("#packageId").val())
			};
			var flag = false;
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var count = result.data;
					if(count>0){
						flag = true;
					}
				}
			},false);
			return flag;
		}
		
	});

