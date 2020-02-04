define(["jquery","jquery.dot","Pagination","utils"], 
	function($,doT,Pagination) { 
	
	var loadDataList = function(pageNo,pageSize,tabType,classificationIdList,tagIdList) {
		var data = {
			pageNo:pageNo,
			pageSize:pageSize,
			tabType:tabType,
			courseName:$('#searchCourseName').val(),
			classificationIdList:classificationIdList,
			tagIdList:tagIdList
		};
		postAjaxRequest(URL.list(), data, function(result){
			console.log(result);
			var myPage = new Pagination($('#dataPage'), {
				length: result.data.totalRow,
				current: result.data.currePage,
				every: 8,
				onClick: function(myPage, current) {
					loadDataList(current,myPage.num.every);
				}
			});
			myPage.show();
			var template = doT.template($('#course_home_data_template').text());
			$('#dataPanel').html(template({data:result.data.dataList}));
		 },false);
	};
	var init = function(){
		$('#tabList a').click(function (e) {
			e.preventDefault();
			$(this).tab('show');
			$("#tabList li").each(function(index){
				if($(this).attr("class")=="active"){
					var tabType = $(this).find("a").attr("data-id");
					loadDataList(1,8,tabType);
				}
			});
		});
		$('#search').click(function (e) {
			loadDataList(1,8);
		})
	};
	onFilterByTags = function (obj) {
		var clazz = $(obj).prop("class");
		if("bule-bnt"==clazz){
			$(obj).prop("class","on");
		}else if("on"==clazz){
			$(obj).prop("class","bule-bnt");
		}
		var tagIdList = [];
		var classificationIdList = [];
		console.log($('.on'));
		$.each($('#classificationsList .on'),function(i,item){
			console.log(item);
			console.log(i);
			if($(item).prop("id")!=""){
				classificationIdList.push($(item).prop("id"));
			}
		});
//		$.each($(".filter_condition").children().children().children('.top-tabox-fl'),function(i,item){
//			classificationIdList.push($(item).prop("id"));
//		});
		loadDataList(1,8,'',classificationIdList,tagIdList);
	};
	
	init();
	loadDataList(1,8);
});

var URL = {
	list : function() {
		return '/courseselect/course_home/list.html';
	}
}