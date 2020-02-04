(function() {
	"use strict";
	
	var list = {
			
			setting : {
				opts : {
					tablePanel:'',
					tableDataTemplate:'',
					loadDataListUrl:'',
					isChildrenClassificationUrl:'',
					itemType:1
				},
				call:{
					paginationFunc:undefined,
					selectClassificationFunc:undefined,
					
				}
			},
			version: '1.0.0',
			init:undefined,
			onFixedItems:undefined,
			onFilterByTags:undefined,
			onLoadDataList:undefined,
			onLoadChildrenClassification:undefined,
			onRemoveClassification:undefined,
			onDeleteClassification:undefined, 
			onSelectedClassification:undefined
		}, global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = list;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return list;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.list = list;
	}
	
	
	
	list.init = function(data,callBack){
		$('.top-tabox').bind("mouseleave",function(){
			$('.pre_left_top_fl_main').removeClass('pre_left_top_fl_main_hover');
		});
		
		$(".top-tabox-fl").bind("mouseover",function(){
			list.onLoadChildrenClassification(this);
		});
		list.onFixedItems();
		list.setting.opts=data;
		list.setting.call = callBack;
	};
	
	list.onFixedItems = function(){
		$('.pre_right').scrollToFixed({
			marginTop: $('.nav').outerHeight(true) + 10
	 	 });
		
		$('.con-mask').scrollToFixed({
			marginTop: $('.nav').outerHeight(true) + 10
		});
		$('.con').scrollToFixed({
			marginTop: $('.nav').outerHeight(true) + 10
		});

	};
	
	list.onFilterByTags = function(obj){
		var clazz = $(obj).prop("class");
		if("bule-bnt"==clazz){
			$(obj).prop("class","on");
		}else if("on"==clazz){
			$(obj).prop("class","bule-bnt");
		}
		var tagIdList = [];
		var classificationIdList = [];
		$.each($('.on'),function(i,item){
			if($(item).prop("id")!=""){
				tagIdList.push($(item).prop("id"));
			}
		});
		$.each($(".filter_condition").children().children().children('.top-tabox-fl'),function(i,item){
			classificationIdList.push($(item).prop("id"));
		});
		list.onLoadDataList(1,classificationIdList,tagIdList);
	};
	
	
	list.onLoadDataList = function(pageNo,classificationIdList,tagIdList,keyword,callback){
		$('#classificationContent').attr('scrollPagination', 'enabled');
		var params = {
				pageNo:pageNo,
				pageSize:6,
				classificationIdList:classificationIdList,
				tagIdList:tagIdList,
				keyword:keyword
		}, url = list.setting.opts.loadDataListUrl;

		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paginationFunc(result);
//				paging.pageTemplateDiv(result, list.setting.opts.tablePanel, list.setting.opts.tableDataTemplate, "presentation_data_page_panel","loadPageDataList");
				if(callback != 'undefined' && callback instanceof Function){
					callback();
				}
				

			}
		});
	};
	
	list.onLoadChildrenClassification = function(object){
		var obj = $(object);
		var url="/classification/list_by_id.html",data={
				classificationId:$.trim(obj.prop("id")),
				itemType:list.setting.opts.itemType
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				if(result.data.length>0){
					obj.parent().parent().parent().addClass('pre_left_top_fl_main_hover');
					var htmlText = ""
					$.each(result.data,function(i,item){
						htmlText = htmlText + '<a href="javascript:void(0);" onclick=onSelectedClassification("'+item.classification_name+'","'+item.id+'")>'+item.classification_name+'</a>';
					});
					obj.parent().next().empty();
					obj.parent().next().append(htmlText);
				}
			}else{
				console.log("获取子标签失败！");
			}
		},undefined,undefined,false);
	};
	
	list.onRemoveClassification = function(object){
		var obj = $(object);
		obj.parent().parent().parent().removeClass('pre_left_top_fl_main_hover');
	};
	
	list.onDeleteClassification = function(obj){
		$(obj).parent().parent().parent().remove();
		var classificationIdList = [];
		var tagIdList = [];
		//分类查询条件
		$.each($(".filter_condition").children().children().children('.top-tabox-fl'),function(i,item){
			classificationIdList.push($(item).prop("id"));
		});
		//标签查询条件
		$.each($('.on'),function(i,item){
			if($(item).prop("id")!=""){
				tagIdList.push($(item).prop("id"));
			}
		});
		list.onLoadDataList(1,classificationIdList,tagIdList);
	};
	
	list.onSelectedClassification = function(name,id){
		//判断是否含有子节点
		var url=list.setting.opts.isChildrenClassificationUrl,data={
				classificationId:id,
				itemType:list.setting.opts.itemType,
				
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				var html = '';
				if(result.data>0){
					html = '<div class="pre_left_top_fl_main filter_condition">'+
									'<dl class="top-tabox">'+
										'<dt><span class="top-tabox-fl" id='+id+'>'+name+'</span><span class="top-tabox-close" style="cursor:pointer" onmouseover="onRemoveClassification(this)" onclick="onDeleteClassification(this)">x</span></dt>'+
										'<dd></dd>'+
									'</dl>'+
								'</div>';
				}else{
					html = '<div class="pre_left_top_fl_main-default filter_condition">'+
									'<dl class="top-tabox">'+
										'<dt><span class="top-tabox-fl" id='+id+'>'+name+'</span><span class="top-tabox-close" style="cursor:pointer" onclick="onDeleteClassification(this)">x</span></dt>'+
										'<dd></dd>'+
									'</dl>'+
								'</div>';
				}
				$(".pre_left_top_fl_tab").append(html);
				
				if(result.data > 0){
					$("#"+id).bind("mouseover",function(){
						list.onLoadChildrenClassification(this);
					});
				}
				
				var classificationIdList = [];
				var tagIdList = [];
				//分类查询条件
				$.each($(".filter_condition").children().children().children('.top-tabox-fl'),function(i,item){
					classificationIdList.push($(item).prop("id"));
				});
				//标签查询条件
				$.each($('.on'),function(i,item){
					if($(item).prop("id")!=""){
						tagIdList.push($(item).prop("id"));
					}
				});
				list.onLoadDataList(1,classificationIdList,tagIdList);
			}
		},undefined,undefined,false);
	};
	
}());	
