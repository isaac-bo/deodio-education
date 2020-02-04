define(
		[ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar" ,"bootstrap.select"],
		function($, cookie, doT, paging) {
	//modal打开事件
	$('#groupCopyModal').on('shown.bs.modal', function () {
		initloadGroupModalContent();
	});
	var initloadGroupModalContent = function(){
		 //初始化页面
		$("#copyTitle").val("复制小组");
		
		loadGroupData();
	}
    loadGroupData = function() {
		var params = {
			groupId : $("#_item_id").val()
		}, url = "/group/getGroupNameNextNumber.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				$("#newGroupName").val(result.data.groupModel.groupName+"("+result.data.num+")");
			}
		});
	};
});

