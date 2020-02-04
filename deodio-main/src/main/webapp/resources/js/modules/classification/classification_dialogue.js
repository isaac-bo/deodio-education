define([ "jquery","utils.dtree", "utils.cookie", "jquery.dot","utils", "jquery.base",
         "jquery.validate","bootstrap.select"], function($, tree, cookie, doT) {
	
	//弹出所属分类选择窗口   
	//modify by xuxiangdong  20180524 start
	popClassificationPicker = function(setting){
		var template = doT.template($("#classification_data_template").text());
		popUpWindows("1",template(), "所属分类",700,500);
		$('#popupModel1').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
		
		});
		$("#checkedGroup").mCustomScrollbar();
		$("#treeNodeDesc").mCustomScrollbar();
		$("#classificationListPanel").mCustomScrollbar();
		
		//已选择的分类集合
		var selectedArr = [];
		var selecteNamedArr = [];
		$(".sel_btn").each(function(item){
			selectedArr.push($(this).children().attr("classification"));
			selecteNamedArr.push($(this).text().replace('×',''));
			
		});
	

		var url ;
		if(selectedArr.length>0){
			url = ctx +"/classification/get_left_ctree_by_text.html";
			$.each(selectedArr, function(i, item){ 
				console.log(item)
				$("#checkedGroup").append('<span style="cursor:pointer"   onmouseover=displayClassificationDesc("'+item+'") id="'+item+'">'+selecteNamedArr[i]+'</span>');
				displayClassificationDesc(item);
			});
		}else{
			url = ctx +"/classification/get_left_ctree_by_level.html";
		}
		
		tree.init({
			ui:{
				selectedNode:"treeNodeId",
				treeContainer:"presentationClassificationPanel",
				queryText : $.trim($("#queryClassificationText").val()),	//查询条件
				//treeQueryFlag : 0,		//查询标志
				selectedClassification : selectedArr	//已选中的分类
			},
			fn:{
				onLoadData : function(){}
			},
			url:url,
			setting:setting
		});
	}
	
	onSelectClassification = function(classificationId,classificationName){
		var selectdId = '';
		$('#classificationSearchPanel').find('li').each(function(){
		
			if($('#'+$(this).attr('id')+'_check').hasClass('radio_true_full')){
				selectdId = $(this).attr('id');
				$('#'+$(this).attr('id')+'_check').removeClass('radio_true_full').addClass('radio_false_full');
				$('#'+$(this).attr('id')+'_a').removeClass('curSelectedNode');
			}
		}); 
		if(selectdId != classificationId){
			$('#presentationClassificationPanel_'+classificationId+'_check').addClass('radio_true_full').removeClass('radio_false_full');
			$('#presentationClassificationPanel_'+classificationId+'_a').addClass('curSelectedNode').removeClass('radio_false_full');
			$("#checkedGroup").empty();
			$("#checkedGroup").append('<span class="on" style="cursor:pointer" onmouseover=displayClassificationDesc("'+classificationId+'") id="'+classificationId+'">'+classificationName+'</span>');
			displayClassificationDesc(classificationId);
		}
	}
	
	//根据关键字查询节点
	queryClassificationList = function(){
		var queryText = $.trim($("#queryClassificationText").val());
		tree.options.ui.queryText = queryText;
		
		if(queryText==""){
			//get_left_ctree_by_text默认类
			tree.options.url = ctx +"/classification/get_left_ctree_by_level.html";
			var hideNodes = [];
			tree.searchNodes(hideNodes);
			$('#classificationListPanel').show();
			$('#classificationSearchPanel').hide();
		}else{

			var url="/classification/query_reversion_ctree_by_text.html",data={
					accountId : cookie.getCookie('CAID'),
					queryText : queryText
			};
			postAjaxRequest(url, data, function(result){
				
//				
				$('#classificationListPanel').hide();
				$('#classificationSearchPanel').show();
				var template = doT.template($("#classification_search_template").text());
				$('#classificationSearchPanel').html(template({"data" : result}));
				
			});
		}
			
	}
	
	//鼠标滑入显示节点描述
	displayClassificationDesc = function(dtreeId){
		console.log(dtreeId)
		var url="/classification/description.html",data={
				id : dtreeId
		};
		postAjaxRequest(url, data, function(result){
			$("#treeNodeDesc").text(result.classification_desc);
			$("#checkedGroup span").removeClass("on");
			$("#"+dtreeId).addClass("on");
		},undefined,undefined,false);
	};
	
	//鼠标滑出清空
	emptyClassificationDesc = function(){
		$("#treeNodeDesc").text('');
	};
	
	//保存分类List
	saveClassificationList = function(){
		var selectedArr = [];//tree.getCheckedNodes();
			
		$('#checkedGroup').children('span').each(function(){
			selectedArr.push({id:$(this).attr('id'),name:$(this).html()});
			
		});	
			
		$("#presentationGeneres .sel_btn").remove();
		console.log(selectedArr.length);
		if(selectedArr.length<1){
			$("#presentationGeneres").removeClass("right-item");
			$("#presentationGeneres").addClass("wrong-item");
			$("#presentationGeneres").next('div').remove();
			$("#presentationGeneres").after("<div style='margin-left:360px;margin-top:-30px;'><span class='help-inline error'>请选择所属分类！</span></div>");
		}else{
			$("#presentationGeneres").addClass("right-item");
			$("#presentationGeneres").removeClass("wrong-item");
			$("#presentationGeneres").next('div').remove();
		}
		$.each(selectedArr, function(i, item){ 
			$("#genereNodes").before('<span class="sel_btn">'+item.name+'<button type="button" class="sel_del" classification="'+item.id+'" onclick="delClassification(this);">&times;</button></span>');
		});
		closePopWindow("1");
	};
	
	//删除所属分类标签
	delClassification = function(obj){
		var spanDom = $(obj).parent();
		spanDom.remove();
	};

	
});
