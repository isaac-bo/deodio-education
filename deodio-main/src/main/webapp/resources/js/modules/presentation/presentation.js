define([ "jquery", "utils.cookie", "utils", "jquery.base" ],function($, cookie) {
		
		
		//返回上一步
		onGo2Step1 = function(){
			go2Page('/presentation/profile.html','presentationId='+$('#presentationId').val());
		};
		
		onPublish = function(){
			publishItemFunc();
			//$("#publishModal").modal("show");
			$('#group_container_type').val(1);
			$('#item_id').val($('#presentationId').val());
		};
		
		onSave = function(saveFunc, callFunc){
			saveFunc(callFunc);
		};
		
		publishItemFunc = function(){
			var url="/presentation/publish.html",data={
				presentationId:$.trim($("#presentationId").val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					go2Page('/presentation/list.html');
				}else{
					alertMsg("抱歉，因为网络问题该Presentation发布失败，请重新发布！");
				}
			});
		};
	});;