define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.ui", "jquery.mCustomScrollbar"], 
	function($, cookie, doT, paging) {
	// modal打开事件
	$('#courseQuoteModal').on('shown.bs.modal', function() {
		loadQuoteList();
	});
	//加载引用课件列表数据
	loadQuoteList = function(pageNo) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
				pageNo : pageNo,
				courseId : $('#_item_id').val()
		}, url = "/course/online/find_quote_courseware.html";
		postAjaxRequest(url, params, function(result) {
			debugger;
			if (result.status == 1) {
				paging.pageTemplateDiv(result, 
					"table_content_course_quote",
					"right_content_course_quote_template",
					"course_setting_course_quote_panel", 
					"loadQuoteList");
			}
		});
	}
});
