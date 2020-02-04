define(["jquery","jquery.dot"], function($,doT) {
var pageTemplateDiv = function(result,dataPanel,tableTmp,pagePanel,dataFun,paramData) {
	if(dataPanel==undefined){
		dataPanel ="dataPanel";
	}
	if(dataFun==undefined){
		dataFun ="loadDataList";
	}
	if(tableTmp==undefined){
		tableTmp ="data_table_template";
	}
	if(pagePanel==undefined){
		pagePanel ="data_page_Panel";
	}
	
	
	var template = doT.template($("#"+tableTmp).text());	
	$('#'+dataPanel).html(template({data:result.data.dataList,param:paramData}));
	
	
	
	var pageTemplate = doT.template($("#table_pages").text());
	var curPage = result.data.currePage;
	var total = result.data.totalRow;
	var totalPage = result.data.totalPage;

	var page = pagaNum(curPage, totalPage, total);

	var array = numListPage(totalPage, total, curPage);
	if(result.data.dataList.length==0){
		$("#no_Data").remove();
		$("#"+dataPanel).parent().parent().find('.null_table').remove();
		$("#"+dataPanel).parent().after('<div class="null_table" style="">'+
								 'NULL'+
								 '<span class="null_t">暂时没有相关数据</span>'+
								 '</div>');
		page.noDate=0;
	}else{ 
		$("#"+dataPanel).parent().parent().find('.null_table').remove();
		page.noDate=1;
	}	
	page.pageNo = array;
	page.dataFun = dataFun;
	$('#'+pagePanel).html(pageTemplate(page));
};

/**
 * currePage 
 * totalPage 
 * totalRow
 * 
 */
var pagaNum = function(currePage, totalPage, totalRow) {
	var page = {};
	if (currePage > 1) {
		page.pre = currePage - 1;
	} else {
		page.pre = -1;
	}
	if (currePage < totalPage) {
		page.next = currePage + 1;
	} else {
		page.next = -1;
	}
	page.current = currePage;
	page.totalPage = totalPage;
	
	
	
	
	return page;
};
/**
 * Calculating the number of list the total number of pages, the number of records, the current page, the maximum display page
 * 
 */
var numListPage = function(totalPage, totalRow, currePage) {
	var maxStep = 10;
	var startPageNo = (currePage - Math.ceil(maxStep / 2));
	if (startPageNo < 1) {
		startPageNo = 1;
	}
	var endPageNo = currePage + maxStep / 2;

	if (endPageNo > totalPage) {
		endPageNo = totalPage + 1;
	}
	var array = new Array();
	for ( var i = startPageNo; i < endPageNo; i++) {
		array[i - startPageNo] = i;
	}
	return array;
};
	return{
		pageTemplateDiv:pageTemplateDiv
	}
});