define(["jquery","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.scrolltofixed",
		"upload.ui","upload.common","upload.handler","bootstrap.select"], function($,cookie,doT,paging) {
	
	var init = function(){	
		
		
		
		loadSlidesDataList("slides_table_panle", "slides_table_data_template");
		$('select').selectpicker();	
		$('.bootstrap-select').css('height','36px');
		$('.bootstrap-select button').css('height','36px');
	}
	
	//编辑Slides
	querySlidesListByInfo = function(){
		//go2Page('/presentation/sync/toEditSlidesPage.html','presentationId='+$('#presentationId').val()+'&querySlidesInfo='+$('#querySlidesListInfo').val());
		var listIsShowFlag = $("#slideList").is(":hidden");
		if(listIsShowFlag){
			mediumIconShow();
		}else{
			smallIconShow();
		}
	
	}
	
	loadSlidesDataList = function(tablePanel,tableDataTemplate) {
		var params = {
			querySlidesInfo : $.trim($('#querySlidesListInfo').val()),
			presentationId : $.trim($('#presentationId').val())
		}, url = "/presentation/sync/edit_slides_list.html";

		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "","");
				customInput("remember");
			}
		});
	};
	
	smallIconShow = function(){
		$("#slideList").show();
		$('#slideItem').hide();
		loadSlidesDataList("slides_table_panle", "slides_table_data_template");
	}
	
	mediumIconShow = function(){
		$("#slideList").hide();
		$('#slideItem').show();
		loadSlidesDataList("slides_item_panel", "slides_item_data_template");
	}
	
	init();
});