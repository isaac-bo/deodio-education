	define(["jquery","utils.cookie","utils","jquery.base","jquery.scrolltofixed",
		"fileupload.common","jquery.Jcrop","jquery.validate"], function($,cookie) {
		var defaultLogoImgSnap=ctx
		+"/resources/img/account/uplogo.png";
		var tabPanelConfig = {
			"name":{loadData:function(){loadSubdoFormData();},initPage:function(){}},	//二级域名
			"logo":{loadData:function(){loadStyleFormData();},initPage:function(){initStylePage('logo');}},	//标志
			"header":{loadData:function(){loadStyleFormData();},initPage:function(){initStylePage('header');}},	//头部
			"banner":{loadData:function(){loadStyleFormData();},initPage:function(){initStylePage('banner');}},	//广告
			"footer":{loadData:function(){loadStyleFormData();},initPage:function(){initStylePage('footer');}},	//底部
			"layout":{loadData:function(){loadLayoutDataFun();},initPage:function(){ininLayoutThemePage();}},	//布局
			"theme":{loadData:function(){loadThemeDataFun();},initPage:function(){ininLayoutThemePage();}}	//主题
		};
		
		var  accountStyleConfig = {
			"logo":{type:1,imgDivId:'logo_img_div',uploadDivId:'logo_upload_div',imgId:'logo_img',snapImgId:'logo_img_snap',formId:"logoForm",uploadFileId:"logoUploadFile",linkUrl:'logoLinkUrl',jcropParams:{defaultW:360,defaultH:360,cutImgW:240,cutImgH:78}},	//标志
			"header":{type:2,imgDivId:'header_img_div',uploadDivId:'header_upload_div',imgId:'header_img',snapImgId:'header_img_snap',formId:"headerForm",uploadFileId:"headerUploadFile",linkUrl:'headerLinkUrl',jcropParams:{defaultW:360,defaultH:360,cutImgW:214,cutImgH:214}},	//头部
			"banner":{type:3,imgDivId:'banner_img_div',uploadDivId:'banner_upload_div',imgId:'banner_img',snapImgId:'banner_img_snap',formId:"bannerForm",uploadFileId:"bannerUploadFile",linkUrl:'bannerLinkUrl',jcropParams:{defaultW:360,defaultH:360,cutImgW:214,cutImgH:214}},	//广告
			"footer":{type:4,imgDivId:'footer_img_div',uploadDivId:'footer_upload_div',imgId:'footer_img',snapImgId:'footer_img_snap',formId:"footerForm",uploadFileId:"footerUploadFile",linkUrl:'footerLinkUrl',jcropParams:{defaultW:875,defaultH:360,cutImgW:214,cutImgH:214}}	//底部
		};
		
		var currentAccountStyleId = "";
		
		var img_div = "";
		var show_div = "";
		
		var _init = function(){
			
			 $('#accountSetting a').click(function (e) {
		          e.preventDefault();//阻止a链接的跳转行为
		          $(this).tab('show');//显示当前选中的链接及关联的content
		          var selectedTabId = $(this).attr("href").substr(1);
		          initPageData(selectedTabId);
		        });
			 $('#accountSetting a:first').click();
		};
		
		//初始化页面数据
		initPageData = function(selectedTabId){
			tabPanelConfig[selectedTabId].initPage();
			tabPanelConfig[selectedTabId].loadData();
		}
		
		
		//---------------------------域名 start-----------------------------
		$('#domainForm').myValidate({
					formCall:function(){subdoForm();},
					isAlert:false,
					formKey:false,
					errorCustom:{customFlag:false,regionText:false},
					errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		
	
		validateHostExists = function(){
			var flag=false;
			var url="/account/validate/domain_exist.html",data={
				subdomain:$.trim($("#subdomain").val())
			};
			postAjaxRequest(url, data,function(result){
				flag=result.data;
			},false);
			return flag;
		};
		
		
		var subdoForm = function(){
			var url="/account/update_domain.html",data = {
				subdomain:$.trim($("#subdomain").val()),
				uKeyId:$("#uKeyId").val()
			};
			
			postAjaxRequest(url, data,function(result){
				if(result.status == 1){
					alertMsg("提交成功");
				}else{
					alertMsg("提交失败或者权限不足")
				}
			});
		};
		
		var loadSubdoFormData = function(){
			var url="/account/query_domain.html",data = {
			};
			postAjaxRequest(url, data,function(result){
				var subdomain = result.data;
				if(subdomain){
					$("#subdomain").val(subdomain);
				}
			});
		};
		
		//---------------------------域名 end-----------------------------
		
		//---------------------------style start-----------------------------
		initStylePage = function(tabPaneId){
			
			currentAccountStyleId = tabPaneId;
			var currentAccountStyleConfig = accountStyleConfig[currentAccountStyleId];
			if(!currentAccountStyleConfig){
				return;
			}
			
			
			var uploadUrl = '/commons/uploadAttach.html?r=' + Math.floor(Math.random() * 100);
			uploadFileJqProcess(currentAccountStyleConfig.uploadFileId,uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"6",isModel:true},
					1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
					$("#hidAttDir").val(data.result.data.attachDir+data.result.data.generateName);
					$("#hidAttUrl").val(data.result.data.attachUrl+data.result.data.generateName);
					var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
					img_div = currentAccountStyleConfig.imgDivId;
					show_div = currentAccountStyleConfig.uploadDivId;
//					$("#log_img_div").show();
//					$("#upload_div").hide();
//					$("#log_img").attr("src",srcimg);
//					jcropFunc("log_img",srcimg,360,360,214,38);		
					
					$("#"+currentAccountStyleConfig.imgDivId).show();
					$("#"+currentAccountStyleConfig.uploadDivId).hide();
					$("#"+currentAccountStyleConfig.imgId).attr("src",srcimg);
					
//					defaultW:360,defaultH:360,cutImgW:214,cutImgH:214
					var defaultW = currentAccountStyleConfig.jcropParams.defaultW;
					var defaultH = currentAccountStyleConfig.jcropParams.defaultH;
					var cutImgW = currentAccountStyleConfig.jcropParams.cutImgW;
					var cutImgH = currentAccountStyleConfig.jcropParams.cutImgH;
					jcropFunc(currentAccountStyleConfig.imgId,srcimg,defaultW,defaultH,cutImgW,cutImgH);	
			},function(e,data){
				
			});
			
		/*	
			uploadProcess(currentAccountStyleConfig.uploadFileId,'*.jpg;*.png;',{accountId:cookie.getCookie('CAID'),attachRelType:"6",isModel:true},ctx+'/commons/uploadAttach.html?r=' + Math.floor(Math.random() * 100)
	 				,function(fileName){
//						$("#uploadFileName").html(fileName);
	 				}
	 				,function(data){
	 					$("#hidAttDir").val(data.attachDir+data.generateName);
						$("#hidAttUrl").val(data.attachUrl+data.generateName);
						var srcimg = imgStatic+data.attachUrl+data.generateName;
						img_div = currentAccountStyleConfig.imgDivId;
						show_div = currentAccountStyleConfig.uploadDivId;
//						$("#log_img_div").show();
//						$("#upload_div").hide();
//						$("#log_img").attr("src",srcimg);
//						jcropFunc("log_img",srcimg,360,360,214,38);		
						
						$("#"+currentAccountStyleConfig.imgDivId).show();
						$("#"+currentAccountStyleConfig.uploadDivId).hide();
						$("#"+currentAccountStyleConfig.imgId).attr("src",srcimg);
						
//						defaultW:360,defaultH:360,cutImgW:214,cutImgH:214
						var defaultW = currentAccountStyleConfig.jcropParams.defaultW;
						var defaultH = currentAccountStyleConfig.jcropParams.defaultH;
						var cutImgW = currentAccountStyleConfig.jcropParams.cutImgW;
						var cutImgH = currentAccountStyleConfig.jcropParams.cutImgH;
						jcropFunc(currentAccountStyleConfig.imgId,srcimg,defaultW,defaultH,cutImgW,cutImgH);		
						
	 				},true,'选择图片','','',32,90);

			
			
			*/
			
			
			
			$("#hidInputType").val(currentAccountStyleConfig.type);
			
			$('#'+currentAccountStyleConfig.formId).myValidate(
					{
						formCall:function(){saveStyleForm();},
						isAlert:false,
						formKey:false,
						errorCustom:{customFlag:false,regionText:false},
						errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
				});	
		}
		
		//保存成功后，更新图片缩略图
		saveStyleForm =  function(){
			var imgSnap = accountStyleConfig[currentAccountStyleId].snapImgId;
			if(saveImgFun(imgSnap)){
				submitUrl();
			}
		}
		//删除图片
		deleteStyleUploadFun = function(){
			var imgDiv = accountStyleConfig[currentAccountStyleId].imgDivId;
			var uploadDiv = accountStyleConfig[currentAccountStyleId].uploadDivId;
			var imgSnap = accountStyleConfig[currentAccountStyleId].snapImgId;
//			deleteImgUploadFun("log_img_div","upload_div","log_img_pic");

			deleteImgUploadFun(imgDiv,uploadDiv,imgSnap);
	
			resetAccountStyleParam();

			saveImgFun(imgSnap,true);

			
		}
		
		var loadStyleFormData = function(){
//			loadImgDataFun("log_img_pic");
			var imgSnap = accountStyleConfig[currentAccountStyleId].snapImgId;
			var linkUrl = accountStyleConfig[currentAccountStyleId].linkUrl;
			loadImgDataFun(imgSnap,linkUrl);
		};
		
		//---------------------------style end-----------------------------
		
		/*------------------------我的设置  Log,header,footer,banner 共通函数  start------------------------*/
		
		var loadImgDataFun = function(snapImgId,linkUrlId){
			var url="/account/load_style_log.html",data = {
				setType:$("#hidInputType").val()
			};
			postAjaxRequest(url, data,function(result){
				var imgUrl = result.data.imgUrl;
				if(imgUrl){
					$("#"+snapImgId).attr("src",imgStatic + imgUrl);
				}
				var linkUrl = result.data.linkUrl;
				if(linkUrl){
					$("#"+linkUrlId).val(linkUrl);
				}
			});
		};
		
		saveImgFun =  function(snapImgId,delFlg){
			var url="/account/update_style_logo.html",data={
					attachDir:$("#hidAttDir").val(),
					attachUrl:$("#hidAttUrl").val(),
					x:Math.round($("#x1").val()),
					y:Math.round($("#y1").val()),
					logWidth:Math.round($("#w").val()),
					logHeight:Math.round($("#h").val()),
					setType:$("#hidInputType").val()
			};
			
			if(!delFlg && !data.attachUrl){
				alertMsg("图片为设置!");
				return false;
			}
//			if((data.logWidth==0 || data.logHeight==0) ){
//				alertMsg("请截取图片!");
//				return false;
//			}else{
				postAjaxRequest(url, data, function(result){
//					var imgSrc = imgStatic+data.attachUrl+"?r="+new Date().getTime();
					var imgSrc = imgStatic+data.attachUrl;
					if(delFlg){
						$("#"+snapImgId).attr("src",defaultLogoImgSnap);					
//						$("#"+img_div).html("");
						//$("#"+show_div).show();
					}else{
						$("#"+snapImgId).attr('src',imgSrc);
					}
				});
//			}
			return true;
		}
		
		var submitUrl = function(){
			var linkUrlId = accountStyleConfig[currentAccountStyleId].linkUrl;
			var url="/account/update_style_url.html",data={
					url:$.trim($("#"+linkUrlId).val()),
					type:$("#hidInputType").val(),
					uKeyId:$("#uKeyId").val()
			}
			postAjaxRequest(url, data, function(result){
				alertMsg("保存成功");
			});
		}
		
		deleteImgUploadFun = function(imgDivId,uploadDivId,snapImgId){
			var url="/account/delete_style_img.html",data={
				attachDir:$("#hidAttDir").val()	
			};
			
			postAjaxRequest(url, data, function(result){
				$("#"+imgDivId).hide();
				$("#"+uploadDivId).show();
//				jcrop_api.destroy();
			})
		}
		
		var showCoords =  function(c){
			  $('#x1').val(c.x);
			  $('#y1').val(c.y);
			  $('#w').val(c.w);
			  $('#h').val(c.h);
		};
		
		var  jcrop_api;
		 jcropFunc  = function(imgId,imgSrc,defaultH,defaultW,cutImgH,cutImgW,imgDivId){
			var realHeight = 0,realWidth = 0;
			$("#"+imgId).attr("src",imgSrc).load(function(){
				realWidth = this.width;
				realHeight = this.height;
				if(realHeight>=defaultH){
					$("#"+imgId).attr("height",defaultH);
				};
				if(realWidth>=defaultW){
					$("#"+imgId).attr("width",defaultW);
				};
				
				$("#"+imgDivId).show();
				
				$("#"+imgId).Jcrop({					
				     aspectRatio: cutImgH / cutImgW,
				     addClass:"image-center",
				     onChange: showCoords,
				     onSelect: showCoords
				},function(){
					 jcrop_api = this;
					this.setSelect([0, 0, 214, 38]);
				});
				
				$(".image-center").css("top",(defaultH-$(".jcrop-holder").height())/2+"px")
				
			});
		}
		 
		 var resetAccountStyleParam = function(){
			 $("#hidAttDir").val("");
			 $("#hidAttUrl").val("");
			 $("#x1").val("");
			 $("#y1").val("");
			 $("#w").val("");
			 $("#h").val("");
		 }
		/*------------------------我的设置  Log,header,footer,banner 共通函数  end------------------------*/
		 
		 /*-------------------------------------主题 theme start-------------------------------------*/
		 
		 $('#themeForm').myValidate({
				formCall:function(){saveThemeFun();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:false,regionText:false},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		 
		 var loadThemeDataFun = function(){
				var url="/account/load_theme.html",data = {
				};
				postAjaxRequest(url, data,function(result){
					var themeType = result.data;
					if(themeType){
						var themeList = $("#themeForm .themelist li");
						setSelectedElementActiveByIndex(themeType,themeList);
					}
				});
			};
			
			saveThemeFun =  function(){
				var themeType = getSelectedElementType($("#themeForm .themelist li"));
				
				if(!themeType){
					alertMsg("请设置主题！");
				}
				
				var url="/account/update_theme.html",data={
						uKeyId:$("#uKeyId").val(),
						theme:themeType
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						alertMsg("设置成功！");
					}else{
						alertMsg("设置失败！");
					}
				});
			}
		 
		 /*-------------------------------------主题 theme end-------------------------------------*/
		 
		 /*-------------------------------------布局 layout start-------------------------------------*/
			
			$('#layoutForm').myValidate({
				formCall:function(){saveLayoutFun();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:false,regionText:false},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		 
			var loadLayoutDataFun = function(){
				var url="/account/load_layout.html",data = {
				};
				postAjaxRequest(url, data,function(result){
					var layoutType = result.data;
					if(layoutType){
						var layoutList = $("#layoutForm .themelist li");
						setSelectedElementActiveByIndex(layoutType,layoutList);
					}
				});
			};
			
			saveLayoutFun =  function(){
				var layoutType = getSelectedElementType($("#layoutForm .themelist li"));
				
				if(!layoutType){
					alertMsg("请设置布局！");
				}
				
				var url="/account/update_layout.html",data={
						uKeyId:$("#uKeyId").val(),
						layval:layoutType
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						alertMsg("设置成功！");
					}else{
						alertMsg("设置失败！");
					}
				});
			}
		 
		 /*-------------------------------------布局 layout end-------------------------------------*/
		 
			
		setSelectedElementActiveByIndex = function(type,queryObj){
			var typeNumber = Number(type) - 1;
			var targetList = $(queryObj);
			targetList.removeClass('active');
			targetList.has("span").find("span").remove();
			var targetLi = targetList.eq(typeNumber);
			targetLi.addClass("active");
			targetLi.append('<span class="on"></span>');
		}
		
		setSelectedElementActiveByItem = function(item,queryObj){
			queryObj.removeClass('active');
			queryObj.has("span").find("span").remove();;
			item.addClass("active");
			item.append('<span class="on"></span>');
		}
		
		getSelectedElementType = function(queryObj){
			var type = "";
			$(queryObj).each(function(index){
				if($(this).is(".active")){
					type = index + 1 ;
					return false;
				}
			});
			return type;
		}
		
		ininLayoutThemePage = function(){
			$("#layoutForm,#themeForm").find("ul li").on("mouseenter",function(e){
				var _this = $(this);
				var container = $(_this).is("li")?$(_this):$(_this).parents("li");
				$(container).css("border","1px solid #3faad0");
			}).on("mouseleave",function(e){
				var _this = $(this);
				var container = $(_this).is("li")?$(_this):$(_this).parents("li");
				$(container).css("border","");
			}).on("click",function(){
				var _this = $(this);
				var container = $(_this).is("li")?$(_this):$(_this).parents("li");
				var form = container.parent().parent();
				
				var formId = form.attr("id");
				setSelectedElementActiveByItem(container,$("#"+formId).find("li"));
			});
		}
		 
		_init();
	});

	