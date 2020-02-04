define(["jquery","utils.cookie","utils",
        "jquery.base","bootstrap.select","pagination"], function($,cookie) {


	var init = function(){
		$('select').selectpicker();
		$('.selectpicker').selectpicker('val', $("#defualtValue").val());
	}
	

	init();
	updateRole = function(){
		var url="/group/member/update_role.html",data={
				roleId:$("#role_select").val(),
				relId:$("#relid").val(),
				groupId:$("#groupPkId").val()
		}
		postAjaxRequest(url, data, function(result){
			//alertMsg("成功");
			go2Page("/group/member.html","groupId="+result.data);
		})
	}
});

