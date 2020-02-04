define(["jquery","jquery.dot","Pagination","utils"], 
	function($,doT,Pagination) { 
	
	var loadDataList = function(pageNo,pageSize,classificationIdList,tagIdList) {
		var data = {
			pageNo:pageNo,
			pageSize:pageSize,
			classificationIdList:classificationIdList,
			tagIdList:tagIdList
		};
		postAjaxRequest(URL.list(), data, function(result){
			console.log(result);
			var myPage = new Pagination($('#dataPage'), {
				length: result.data.totalRow,
				current: result.data.currePage,
				every: 6,
				onClick: function(myPage, current) {
					loadDataList(current,myPage.num.every);
				}
			});
			myPage.show();
			var template = doT.template($('#online_course_data_template').text());
			$('#dataPanel').html(template({data:result.data.dataList}));
		 },false);
	};
	var init = function(){
		loadDataList(1,6);
	};
	init();
});

var URL = {
		list : function() {
			return '/course/online/load_data.html';
		}
	}