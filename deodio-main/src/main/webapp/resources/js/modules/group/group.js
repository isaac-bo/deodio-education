define(["jquery","utils.cookie","utils.menu","utils","jquery.base","fileupload.common"], function($,cookie,menu) {
	
	var init = function(){
		menu.onSelectMenu(2);
		menu.onLeftMenu();
		
//		$('.mess_left').scrollToFixed({
//			 marginTop: $('.nav').outerHeight(true) + 10,
//			 limit:function(){
//				 limit = $('.footer').offset().top - $(this).outerHeight(true) - 10;
//				 return limit;
//			 },
//			 zIndex:999
//	 	 });
	};
	
	
	onGroupPicEdit = function(){
		showIcon();
		var uploadUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+cookie.getCookie('CGID')+'&attachRelType='+"4";
		uploadFileJqProcess("uploadGroupFile",uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:"4",isModel:true},
				1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){	
			var srcimg = imgStatic+data.result.data.attachUrl+data.result.data.generateName;
			var srcimgTemp = data.result.data.generateName;
				    $("#groupIconImg").attr("src",srcimg);
					$("#groupIconName").val(srcimgTemp);
 					$("#groupIconId").val(data.id)
					$(".pic_con").hide();
					$(".pic_con1").show();
					updateUserInfo(srcimg);
		},function(e,data){
			
		});
	}
	
	showIcon = function(){
		$("#groupIcon").show();
		var groupIconPicSrc = $("#groupIconPic").attr("src");
		if(!groupIconPicSrc){
			$(".pic_con").show();
			$(".pic_con1").hide();
		}else{
			$(".pic_con1").show();
			$(".pic_con").hide();
			$("#groupIconImg").attr("src",groupIconPicSrc);
			$("#groupIconImg").css("height","250px");
			$("#groupIconImg").css("width","500px");
		}
	}
	
	hideIcon = function(){
		$("#groupIcon").hide();
		var groupIconSrc = $("#groupIconImg").attr("src");
		if(groupIconSrc){
			$("#groupIconPic").attr("src",groupIconSrc);
		}
	}
	
	updateUserInfo=function(srcimg){
		var url="/group/updateImg.html",data={
				groupId:$("#hidgoupid").val(),
				coverImg:srcimg,
			};
			postAjaxRequest(url, data,function(result){
				if(result.status == 1){
					//alertMsg("操作成功");
				}else{
					//alertMsg("修改失败");
				}
			});
	}

	init();
	
});

