
/*
 * deodio tree
 * http://deodio.com.cn/
 *
 *
 * email: isaac.bo@outlook.com
 * Date: 2016-06-06
 */
(function(){
	"use strict";
	var tree = {
			
			opts:{},
			
			options :{
				ui:{
					selectedNode:undefined,
					treeContainer:undefined,
					queryText:undefined,
					selectedClassification:undefined
				},
				url:undefined,
				fn:{
					onLoadData:undefined,
					onClickRootNodeLoadData:undefined,
					onClickLeafNodeLoadData:undefined
				}
			},
			setting : {
				async : {
					enable : true,
					url : undefined
				},
				check : {
					enable : true,
					chkDisabledInherit: true,
					chkboxType: {
						"Y": "p",
						"N": "s"
					}
				},
				data : {
					simpleData : {
						enable : true,
						idKey: "id",
						pIdKey: "pid",
						rootPId: ""
					}
				},
				view : {
					dblClickExpand : true,
					expandSpeed : ""
				},
				callback : {
					beforeExpand : null,
					onAsyncSuccess : null,
					onAsyncError : null,
					onClick : null,
					onCheck:null,
					onExpand : null
				}
			},
			
			version: '1.0.0',
			init: undefined,
			getNode:undefined,
			addNode:undefined,
			updateNode:undefined,
			expandNode:undefined,
			delNode:undefined,
			searchNodes:undefined,
			ajaxGetNodes: undefined,
			getCheckedNodes:undefined,
			selectNode:undefined,
			selectFirstNode:undefined  //add by xuxiangdong 20180525
		},global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = tree;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return tree;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.tree = tree;
	}
	
	//获取tree第一个Node
	tree.selectFirstNode = function(){
		var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		var nodes = treeObj.getNodes();
		if (nodes.length>0) {
			var node = nodes[0];
			treeObj.selectNode(node);
			tree.setting.callback.onClick(null, treeObj.setting.treeId, node);//调用事件 
			return nodes[0];
		}
	};
	
	tree.selectNode = function(nodeId,onClickCallback){
		var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		var node = treeObj.getNodesByParam("id", nodeId, null)[0];
		treeObj.selectNode(node);
		tree.setting.callback.onClick = onClickCallback;//调用事件 
	};
	
	//查询(查询反向结果集进行隐藏)
	tree.searchNodes = function(searchNodes){
		debugger;
		if(tree.options.ui.queryText==""){
			tree.init();
		}else {
			var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
			var nodes = treeObj.getNodesByParam("isHidden", true);
			treeObj.showNodes(nodes);
			var nodes = treeObj.getNodes();
			//循环隐藏
			$.each(searchNodes,function(i,item){
				var thisNode = treeObj.getNodeByParam("id",item.id);
				treeObj.hideNode(thisNode);
			});
			
		}
	};
	
	tree.expandNode = function(nodeId){
		var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		treeObj.expandNode(treeObj.getNodeByParam("id",nodeId), true, false,true);//指定选中ID节点展开  
	};
	
	tree.addNode = function(nodeId,nodeName,originalNodeName,isParent,pNodeId){
		var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		var newNode = {name:nodeName,id:nodeId,isParent:isParent,isparent:isParent};
		newNode = treeObj.addNodes(treeObj.getNodesByParam("name", originalNodeName, null)[0], newNode);
	};
	
	tree.updateNode = function(nodeId,nodeName){
		var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		var newNode = treeObj.getNodesByParam("id", nodeId, null)[0];
		newNode.name=nodeName;
		treeObj.updateNode(newNode);			
	};
	
	tree.getCheckedNodes = function(){
		var selectedArr = [];
		var zTree = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		var nodes = zTree.getCheckedNodes(true);
		$.each(nodes, function(i, item){
			selectedArr.push(item);
		});
		return selectedArr;
	}
	
	tree.delNode = function(nodeId,nodeName){
		var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		var newNode = treeObj.getNodesByParam("id", nodeId, null)[0];
		newNode.name=nodeName;
		treeObj.removeNode(newNode);			
	};
	
	tree.getNode = function(nodeId){
		var treeObj = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		var newNode = treeObj.getNodesByParam("id", nodeId, null)[0];
		return newNode;
	};
	
	tree.init = function(data){
		tree.opts = $.extend(tree.options, data);
		//modify by xuxiangdong  20180524 start
		if(tree.opts.setting){
			var setting = tree.opts.setting();
			tree.setting.check =  setting.check;
		}
		//modify by xuxiangdong  20180524 end
		
		tree.setting.async.url = function(treeId,treeNode){
			var param = tree.opts.url;
			if(treeNode != null){
				$("#"+tree.opts.ui.selectedNode).val(treeNode.id);
				$("#"+tree.opts.ui.selectedNode+"Name").val(treeNode.name);
				param +="?treeId="+treeNode.id+"&queryText="+tree.opts.ui.queryText+"&selectedClassification="+tree.opts.ui.selectedClassification;
			} else {
				$("#"+tree.opts.ui.selectedNode).val(0);
				param +="?treeId=0&queryText="+tree.opts.ui.queryText+"&selectedClassification="+tree.opts.ui.selectedClassification;
			}
			return param;
		};
		tree.setting.callback.beforeExpand = function(treeId, treeNode) {
			if (!treeNode.isAjaxing) {
				var startTime = new Date();
				treeNode.times = 1;
				if(undefined==treeNode.children){
					tree.ajaxGetNodes(treeNode, "refresh");
				}
				return true;
			} else {
				alert("zTree 正在下载数据中，请稍后展开节点。。。");
				return false;
			}
		};
		
		tree.setting.callback.onCheck = function(event, treeId, treeNode) {
			/*if(treeNode.checked){
				$("#checkedGroup").append('<span class="sel_btn" style="cursor:pointer" onmouseout=emptyClassificationDesc() onmouseover=displayClassificationDesc("'+treeNode.id+'") id="'+treeNode.id+'">'+treeNode.name+'&nbsp;&nbsp;&nbsp;&nbsp;</span>');
			}else{
				$("#checkedGroup #"+treeNode.id).remove();
			}*/
			var zTree = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
			var nodes = zTree.getCheckedNodes(true);
			$("#checkedGroup").empty();
//			$("#treeNodeDesc").text("分类描述");
			$.each(nodes,function(i,item){
				//$("#checkedGroup").append('<span class="sel_btn" style="cursor:pointer" onmouseout=emptyClassificationDesc() onmouseover=displayClassificationDesc("'+item.id+'") id="'+item.id+'">'+item.name+'&nbsp;&nbsp;&nbsp;&nbsp;</span>');
				//$("#checkedGroup").append('<span class="sel_btn" style="cursor:pointer" onclick=displayClassificationDesc("'+item.id+'") id="'+item.id+'">'+item.name+'&nbsp;&nbsp;&nbsp;&nbsp;</span>');
				if(i == 0){
					$("#checkedGroup").append('<span class="on" style="cursor:pointer" onmouseover=displayClassificationDesc("'+item.id+'") id="'+item.id+'">'+item.name+'</span>');
					displayClassificationDesc(item.id);
				}else{
					$("#checkedGroup").append('<span style="cursor:pointer" onmouseover=displayClassificationDesc("'+item.id+'") id="'+item.id+'">'+item.name+'</span>');
				}
			});
		};
		
		tree.setting.callback.onAsyncSuccess = function(event, treeId, treeNode, msg) {
			
			if (!msg || msg.length == 0) {
				return;
			}
			var zTree = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
			if(treeNode != null){
				var totalCount = treeNode.count;
				if (treeNode.children.length < totalCount) {
					setTimeout(function() {tree.ajaxGetNodes(treeNode);}, 100);
				} else {
					treeNode.icon = "";
					zTree.updateNode(treeNode);
//					zTree.selectNode(treeNode.children[0]);
				}
			} else {
				//modify by lsj  20180525 start
				var nodes = zTree.getNodes();
				if (nodes.length>0) {
					zTree.selectNode(nodes[0]);
				}
				$('#treeNodeId').val(nodes[0].id);
				$('#treeNodeIdName').val(nodes[0].name);
				//modify by lsj  20180525 end
			}
			
			tree.opts.fn.onLoadData(1);
			
			//循环勾选已选择的分类
			var tempArr = tree.opts.ui.selectedClassification;
			if(undefined != tempArr){
				if(tempArr.length>0){
					$.each(tempArr,function(i,item){
						var thisNode = zTree.getNodeByParam("id",item);
						zTree.expandNode(thisNode, true, true, true);
						zTree.checkNode(thisNode,true,false );
					})
				}
			}
			
			var queryText = tree.opts.ui.queryText;
			if(queryText!=""&&queryText!=null&&queryText!=undefined){
				zTree.expandAll(true);
			}
		};
		tree.setting.callback.onAsyncError = function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			var zTree = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
			alertMsg("异步获取数据出现异常。");
			treeNode.icon = "";
			zTree.updateNode(treeNode);
		};
		
		tree.setting.callback.onClick = function(event,treeId,treeNode){
			var zTree = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
			zTree.expandNode(treeNode);
			$("#"+tree.opts.ui.selectedNode).val(treeNode.id);
			$("#"+tree.opts.ui.selectedNode+"Name").val(treeNode.name);
			if(treeNode.isparent.toString().toUpperCase() == 'FALSE' && $.isFunction(tree.opts.fn.onClickLeafNodeLoadData)){
				tree.opts.fn.onClickLeafNodeLoadData(treeNode.id);
			}else if(treeNode.isparent.toString().toUpperCase() == 'TRUE' && $.isFunction(tree.opts.fn.onClickRootNodeLoadData)){
				tree.opts.fn.onClickRootNodeLoadData();
//				//modify by lsj  20180525 start
//				if ($.isFunction(tree.opts.fn.onLoadData())) {
//					tree.opts.fn.onLoadData(1);
//				}
//				//modify by lsj  20180525 end
			}
			
			var zTree = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
			zTree.expandNode(treeNode);
			
		};
		
		$.fn.zTree.init($("#"+tree.opts.ui.treeContainer), tree.setting, null);
		return $.fn.zTree;
	};
		
	tree.ajaxGetNodes = function(treeNode,reloadType){
		var zTree = $.fn.zTree.getZTreeObj(tree.opts.ui.treeContainer);
		if (reloadType == "refresh") {
			//treeNode.icon = "../../../css/zTreeStyle/img/loading.gif";
			zTree.updateNode(treeNode);
		}
		zTree.reAsyncChildNodes(treeNode, reloadType, true);
	};
	
}());
