define(["jquery","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.scrolltofixed",
		"upload.ui","upload.common","upload.handler","bootstrap.select"], function($,cookie,doT,paging) {
	
	var init = function(){	
		
		
		loadMediaDataList("medias_table_panle", "medias_table_data_template");
		$('select').selectpicker();	
		$('.bootstrap-select').css('height','36px');
		$('.bootstrap-select button').css('height','36px');
	}
	
	//编辑Slides
	queryMediasListByInfo = function(){
		var listIsShowFlag = $("#mediaList").is(":hidden");
		if(listIsShowFlag){
			mediumIconShow();
		}else{
			smallIconShow();
		}
	}
	
	loadMediaDataList = function(tablePanel,tableDataTemplate) {
		var params = {
			queryMediasInfo : $.trim($('#queryMediasListInfo').val()),
			presentationId : $.trim($('#presentationId').val())
		}, url = "/presentation/sync/edit_medias_list.html";

		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "","");
				customInput("remember");
			}
		});
	};
	
	smallIconShow = function(){
		$("#mediaList").show();
		$('#mediaItem').hide();
		loadMediaDataList("medias_table_panle", "medias_table_data_template");
	}
	
	mediumIconShow = function(){
		$("#mediaList").hide();
		$('#mediaItem').show();
		loadMediaDataList("media_item_panel", "medias_item_data_template");
	}
	
	init();
});