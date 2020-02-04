	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","jquery.tagator","fileupload.common",		
	        "pagination","utils","jquery.base","jquery.tagsInput",
	        "jquery.validate","ueditor","jquery.scrolltofixed","ueditor.config",
	        "jquery.mCustomScrollbar","bootstrap.select"], function($,tree,cookie,list,doT,tagator) {
		
		um = UM.getEditor('presentation_template');
		
		var isInner = false;
		var isTags = false;
		
		var init = function(){
			//标签控件
			$('#inputTagator').tagator({
				// 输入以下单词自动补全, 支持中文
				autocomplete: ['','']
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
		};
		
		init();
		
		$('#settingInfoForm').myValidate({
			formCall:function(){setPresentationInfo();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
//		
//		uploadProcess('presentationUploadFile','*.jpg;*.png;',{accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},ctx+'/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#presentationflag").val())+'&r=' + Math.floor(Math.random() * 100)
//			,function(fileName){
//				//alert(fileName);
//			}
//			,function(data){
//				var srcimg = imgStatic+data.attachUrl+data.generateName;
//				var srcimgTemp = data.generateName;
//				$("#preImgId").attr("src",srcimg);
//				$("#presentationCover").val(srcimgTemp);
//				$("#presentationCoverId").val(data.id);
//			},true);

		var uploadUrl = '/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#presentationflag").val());
		uploadFileJqProcess("presentationUploadFile",uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"5",isModel:true},
				1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
			var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
			var srcimgTemp = data.result.data.generateName;
			$("#preImgId").attr("src",srcimg);
			$("#presentationCover").val(srcimgTemp);
			$("#presentationCoverId").val(data.result.data.id);
		},function(e,data){
			
		});
	
		
		
		
		//热门标签列表
		popTagsPicker = function(){
			if($('#hotTagsList').is(':hidden')){
				listHotTags($.trim($("#presentationName").val()),um.getContent());
			}
		};
		
		blurTagsPicker = function(){
			if(!$('#hotTagsList').is(':hidden') && isTags == false){
				$('#hotTagsList').hide();
			}
		};

		var setPresentationInfo = function(){
			var classificationFlag = true;
			var tagsFlag = true;
			//获取分类个数
			var classificationLength = $("#presentationGeneres .sel_btn").length;
			if(classificationLength<1){
				//分类
				$("#presentationGeneres").removeClass("right-item");
				$("#presentationGeneres").addClass("wrong-item");
				$("#presentationGeneres").next('div').remove();
				$("#presentationGeneres").after("<div style='margin-left:360px;margin-top:-30px;'><span class='help-inline error'>请选择所属分类！</span></div>");
				classificationFlag = false;
			}else{
				$("#presentationGeneres").addClass("right-item");
				$("#presentationGeneres").removeClass("wrong-item");
				$("#presentationGeneres").next('div').remove();
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
				var url="/presentation/submit_profile.html",data={
						presentationName:$.trim($("#presentationName").val()),
						presentationDesc:um.getContentTxt(),
						presentationId: $.trim($("#presentationflag").val()),
						presentationCover: $.trim($("#presentationCover").val()),
						attachId : $.trim($("#presentationCoverId").val()),
						tagsJson : JSON.stringify(tagsList),
						classificationJson : JSON.stringify(classificationList)
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						go2Page("/presentation/content.html","presentationId="+result.data);
					}else{
						alertMsg("设置失败！");
					}
				});
			}
		}
		
		//关闭分类窗口
		closeClassificationWin = function(){
			closePopWindow();
		};
		
//		
//		//标签
//		$("#inputEmail3").blur(function(obj){
//			var comboText = $(this).val();
//			if(""!=comboText){
//				$("#labelNodes").before('<span class="sel_btn">'+$(this).val()+'<button type="button" class="sel_del" onclick="delClassification(this);">&times;</button></span>');
//			}
//		});
		
		//设置presentation信息-返回上一级
		goBack = function(){
			window.location.href=ctx+'/presentation/list.html?&r=' + Math.floor(Math.random() * 100);
		};
		
		//删除身份图片
		deleteFileImg = function(){
			$("#preImgId").attr("src",ctx+"/resources/img/account/user-card-1.jpg");
			$("#presentationCover").val("");
		};
		
		//验证课程名唯一
		validateExistName = function(){
			var url="/presentation/isExistName.html",data={
				presentationName : $.trim($("#presentationName").val())
			};
			var flag = false;
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var presentationId = $.trim($("#presentationflag").val());
					var count = result.data.length;
					if(count>0){
						if(presentationId!=result.data[0].id){
							console.log("存在");
							flag = true;
						}
					}
				}
			},false);
			return flag;
		}
		
		

		
		
		
	});

