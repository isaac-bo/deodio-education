	define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.validate",
	        "bootstrap.select","jquery.scrolltofixed","fileupload.common","bootstrap.datepicker"], function($,cookie,doT) {
		
		
		var _init = function(){
			 $('#accountMenu a').click(function (e) {
		          e.preventDefault();//阻止a链接的跳转行为
		          $(this).tab('show');//显示当前选中的链接及关联的content
		        });
			 
			 
			 $('.mess_left').scrollToFixed({
				 marginTop: $('.nav').outerHeight(true) + 10,
				 limit:function(){
					 limit = $('.footer').offset().top - $(this).outerHeight(true) - 10;
					 return limit;
				 },
				 zIndex:999
		 	 });
		};
		
		toNextLink = function(){
			var currentTab = $("#accountMenu li.active");
			var nextTab = currentTab.next();
			nextTab.find("a").tab("show");
		}
		
		onUserPicEdit = function(){
			showIcon();
			
			
			var uploadUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+cookie.getCookie('CUID')+'&attachRelType='+"3";
			uploadFileJqProcess("uploadUserIcon",uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"3",isModel:true},
					1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
				
				var srcAttachTemp=data.result.data.attachUrl;
				var srcimgTemp = data.result.data.generateName;
				var srcimg = imgStatic+srcAttachTemp+srcimgTemp;
				var userId=$("#uKeyId").val();
					$("#userIconImg").attr("src",srcimg);
 					$("#userIconName").val(srcimgTemp);
 					$("#userIconId").val(data.id);
 					$(".pic_con").hide();
 					$(".pic_con1").show();
 					$("#img"+userId).attr("src",srcimg);
 					//更新数据
// 					updateGroupInfo();
 					
 					cookie.setCookie('CUHP',(srcAttachTemp+srcimgTemp),600);
			},function(e,data){
				
			});
			
			
//			
//			uploadProcess('uploadUserIcon','*.jpg;*.png;',{accountId:cookie.getCookie('CAID'),attachRelType:"6",isModel:true},ctx+'/commons/uploadAndUpdateAttach.html?presentationId='+$.trim($("#courseId").val())+'&r=' + Math.floor(Math.random() * 100)
//	 				,function(fileName){
//	 					//alert(fileName);
//	 				}
//	 				,function(data){
//	 					var srcimg = imgStatic+data.attachUrl+data.generateName;
//	 					var srcimgTemp = data.generateName;
//	 					$("#userIconImg").attr("src",srcimg);
//	 					$("#userIconName").val(srcimgTemp);
//	 					$("#userIconId").val(data.id);
//	 					$(".pic_con").hide();
//	 					$(".pic_con1").show();
//	 					//更新数据
//	 					updateGroupInfo();
//	 				},true,'上传新图片','','btn up_color',40,240);
//			
			
			
			
			
		}
		
		showIcon = function(){
			$("#userIcon").show();
			var userIconPicSrc = $("#userIconPic").attr("src");
			if(!userIconPicSrc){
				$(".pic_con").show();
				$(".pic_con1").hide();
			}else{
				$(".pic_con1").show();
				$(".pic_con").hide();
				$("#userIconImg").attr("src",userIconPicSrc);
				$("#userIconImg").css("height","250px");
				$("#userIconImg").css("width","500px");
			}
		}
		
		hideIcon = function(){
			$("#userIcon").hide();
			var userIconSrc = $("#userIconImg").attr("src");
			if(userIconSrc){
				$("#userIconPic").attr("src",userIconSrc);
				$("#userIconPicHead").attr("src",userIconSrc);
				$("#userIconPicGroupHead").attr("src",userIconSrc);
			}
		}
		
		var updateGroupInfo = function(){
			var dataObj = {
					userId: $("#uKeyId").val(),
					userIconId:$("#userIconId").val(),
					userIconImg:$("#userIconImg").val(),
					userIconName:$("#userIconName").val()
			};
			var url="/signin/enterprise/detail.html",data = {
					uKeyId: $("#uKeyId").val(),
					extModelJson:JSON.stringify(dataObj)
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						alertMsg("保存成功！");
					}else{
						alertMsg("设置失败！");
					}
				});
		}
		
		_init();
	});
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

