	define(["jquery","utils.dtree","utils.cookie","jquery.dot","libs/step/step_page","pagination","utils","jquery.base","jquery.validate","ueditor",
	      "jquery.mCustomScrollbar","bootstrap.select","bootstrap.datepicker"], function($,tree,cookie,doT,step) {
		
		//发布课程回调函数
		publishItemFunc = function(courseId){
			var url="/course/publish.html",data={
				courseId:courseId //$.trim($("#courseId").val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					//alertMsg("课程发布完成！");
				}else{
					alertMsg("抱歉，因为网络问题该course发布失败，请重新发布！");
				}
			});
		};
		//适配lweight 验证方式-- 重置 datetimepicker 
		resetDateTimePickerForLweightValidate = function(obj,dateStr){
			$(obj).attr("srcvalue",dateStr);
			$(obj).removeAttr("lable-error");
			$(obj).removeAttr("style");
		}	
		
		//查看元素是否含有label-error属性
		isDateTimePickerLabelError = function(e){
			return $(e).attr("lable-error");
		} 
		
		updateEditState = function() {
			var url = "/course/online/update_edit_state.html",
				data = {
					courseId : $.trim($("#courseId").val()),
					isEdit : 0
				}
			postAjaxRequest(url, data, function(result){
				
			});
		}
		
		changeIcon = function(owner, createId, isPublish) {
			if (typeof(owner) == "undefined") {
				$("#li_edit").hide();
				$("#li_delete").hide();
				$("#li_quote").hide();
				$("#li_share").hide();
				$("#li_copy").hide();
				$("#li_preview").hide();
				$("#li_approval").hide();
			} else {
				$("#li_edit").show();
				$("#li_delete").show();
				$("#li_quote").show();
				$("#li_share").show();
				$("#li_copy").show();
				$("#li_preview").show();
				$("#li_approval").hide();
				if (createId != cookie.getCookie('CUID')) {
					$("#li_edit").hide();
					$("#li_delete").hide();
					$("#li_share").hide();
					$("#li_copy").show();
					if (owner == cookie.getCookie('CUID')) {
						$("#li_edit").show();
						$("#li_copy").hide();
					}
				} else {
					$("#li_edit").show();
					$("#li_delete").show();
					$("#li_share").show();
					$("#li_copy").show();
					if (isPublish == 1) {
						$("#li_share").hide();
						$("#li_delete").hide();
						$("#li_approval").show();
					}
				}	
			}
		}
		
	});

