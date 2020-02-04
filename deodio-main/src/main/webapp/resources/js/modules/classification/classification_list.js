define([ "jquery", "utils.cookie", "jquery.dot","pagination", "utils.dtree", "utils", "jquery.base", "jquery.validate","bootstrap.datepicker",
         "jquery.scrolltofixed", "jquery.mCustomScrollbar" ], function($, cookie, doT, paging,tree) {
	
	var dTree;
	
	var _init = function() {
		
		refreshBanksList();
		loadDataList(1); 
		$("#classificationPanelDiv").mCustomScrollbar();
		customInput("allClassificationsId");
		checkOrUncheckAll('allClassificationsId','classificationsId');
		 
	};

	//刷新左侧分类菜单列表
	refreshBanksList = function() {
		$("#treeNodeId").val(0);
		$("#treeNodeIdName").val(0);
		tree.setting.check.enable = false;
		tree.init({
			ui:{
				selectedNode:"treeNodeId",
				treeContainer:"classificationPanel"
			},
			fn:{
				onLoadData : loadDataList,
				onClickRootNodeLoadData:loadDataList,
				onClickLeafNodeLoadData:loadDataList
			},
			url:ctx +"/classification/get_left_ctree.html"
		});
		
		//dTree = initClassificationTree('treeNodeId','classificationPanel','/classification/get_left_ctree.html',loadDataList,loadDataList,loadDataList);
	};
	
	
	//加载分类列表
	loadDataList = function(pageNo) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			classificationId : $('#treeNodeId').val(),
			keyword : $.trim($('#keywords').val())
		}, url = "/classification/load_classification_list.html";
		postAjaxRequest(url, params, function(result) {
			paging.pageTemplateDiv(result, "classification_panle", "classification_data_template", "data_page_Panel", "loadDataList");
//			tree.expandNode($("#treeNodeId").val());//指定选中ID节点展开  
			customInput("classificationsId");
			$('body').removeClass('modal-open').css('overflow-y','auto');

		});
	};
	
	//点击展开左侧边栏
	hiddenLeft2 = function(){
		var empx ={left: '+0px'},tables={"cssOptions":{"padding-left":"380px"},"divid":"div_tables"},tables2={"cssOptions":{"padding-left":"380px"},"divid":"div_tables_subject"};
		sidbarOpen('div_hidd','div_sidbar',empx,tables);
		sidbarOpen('div_hidd','div_sidbar',empx,tables2);
	};
	
	//点击滑动收起侧边栏
	closeSidebar2 = function(){
		 var empx ={left: '-380px'},tables={"cssOptions":{"padding-left":"60px"},"divid":"div_tables"},tables2={"cssOptions":{"padding-left":"60px"},"divid":"div_tables_subject"};
		 closeSidebar('div_hidd','div_sidbar',empx,tables);
		 closeSidebar('div_hidd','div_sidbar',empx,tables2);
	};
	
	//新建分类
	createClassification = function(){
		var template = doT.template($("#pop_classification").text());	
		popUpWindow(template({
			id : '',
			classificationName : '',
			classificationDesc : ''
		}),"新建分类");
/*		$('#popupModel').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
		});*/
		$("#createClassification").myValidate({
			formCall:function(){ submitClassification(false);},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});		
	};
//	//名字校验
//	checkName = function(){
//		var flag=false;
//		var url="/classification/getCheck_classification.html",data={
//				classificationName:$.trim($("#classificationName").val())
//		}
//		postAjaxRequest(url, data,function(result){
//			flag=result.data;
//	    },false);
//		return flag;
//	}
	//名字校验
	checkName = function(){
		var newClassificationName=$("#classificationName").val();
		var oldClassificationName=$("#hideenclassificationName").val();
//		console.log(newClassificationName);
//		console.log(oldClassificationName);
		var flag=false;
		if(newClassificationName==oldClassificationName){
			flag=true;
		}else{
			var url="/classification/getCheck_classification.html",data={
					classificationName:newClassificationName
			}
			postAjaxRequest(url, data,function(result){
				flag=result.data;
			},false);
			return flag;
		}
	}
	
	//提交表单， 创建分类
    submitClassification = function(){
		var url="/classification/submit_classification.html",data={
				classificationName:$.trim($("#classificationName").val()),
				classificationDesc:$.trim($("#classificationDesc").val()),
				classificationId:$.trim($("#classificationId").val()),
				classificationParentId:$("#treeNodeId").val()
			};
		postAjaxRequest(url, data, function(result){
			closePopWindow();
			loadDataList(1);
			tree.addNode(result.data,$.trim($("#classificationName").val()),$('#classificationId').val());
			refreshBanksList();
			alertMsg("操作成功");
		});
	};
	//更新提交校验
//	updateClassification = function(){
//			var newClassificationName=$("#classificationName").val();
//			var oldClassificationName=$("#hideenclassificationName").val();
////			console.log(oldClassificationName);
////			console.log(newClassificationName);
//			if(oldClassificationName!=newClassificationName){
//				//名字校验
//				var nameFlag;
//				var url="/classification/getUpdate_classification.html",data={
//						newClassificationName:newClassificationName,
//						oldClassificationName:oldClassificationName
//				}
//				postAjaxRequest(url, data,function(result){
////					console.log(result);
//					if(result.data==true){
//						nameFlag=true;
//					}else{
//						nameFlag=false;
//					}
//				},false);
////				console.log(nameFlag);
//				if(nameFlag==false){
//					closePopWindow();
//					alertMsg("分类名已存在");
//					return;
//				}else{
//					var url="/classification/submit_classification.html",data={
//							classificationName:$.trim($("#classificationName").val()),
//							classificationDesc:$.trim($("#classificationDesc").val()),
//							classificationId:$.trim($("#classificationId").val())
////							classificationParentId:$("#treeNodeId").val()
//						};
//					postAjaxRequest(url, data, function(result){
//						closePopWindow();
//						loadDataList(1);
////						tree.addNode(result.data,$.trim($("#classificationName").val()),$('#classificationId').val());
//						alertMsg("操作成功");
//					});
//				}
//			}
	
	//更新分类
	editClassification = function(classificationId){
		var url="/classification/get_classification.html",data={
				classificationId:classificationId
		};
		postAjaxRequest(url, data, function(result){
			var template = doT.template($("#pop_classification").text());
			popUpWindow(template(result.data),"更新分类");
			$("#createClassification").myValidate({
				formCall:function(){ submitClassification(true);},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:true,regionText:true},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		});
	};
	
	//查找更新分类列表
	searchClassificationList = function(){
		loadDataList(1);
	};
	
	//删除分类
	delClassification = function(classificationId,classificationName){
		confirmMsg('确定要删除该分类吗？  将同时删除所有子分类和该分类下的所有知识点！？',function(){
			var url="/classification/del_classification.html",
			data={classificationId:classificationId};
			postAjaxRequest(url,data,function(result){
				loadDataList(1);
				tree.delNode(classificationId,classificationName);
				alertMsg("操作成功");
			});
		});
	};
	
	//批量删除分类
	delAllClassifications = function(){
		var arr = new Array();
		var classifications = new Array();
        $("input[name='classificationsId']:checkbox").each(function(){ 
     	  
            if($(this).next('label').hasClass("checked")){
         	   arr.push($(this).attr("id"));
         	   console.log($(this).attr("id"))
            }
         });
     var classificationIds = arr.join(",");
     if (classificationIds) {
    	 confirmMsg('确定要删除该分类吗？  将同时删除所有子分类和该分类下的所有知识点！',function(){
 	        var url="/classification/del_all_classifications.html",
 			data={classificationIds:classificationIds};
 			postAjaxRequest(url,data,function(result){
 				loadDataList(1);
				for(var index=0;index<classifications.length;index++){
					tree.delNode(classifications[index].id,classifications[index].name);
				}
 				alertMsg("操作成功");
 			});
 		});
     } else {
    	 alertMsg("请先勾选要删除的分类!");
     }
		
	};
	
	_init();
	
	//跳转到该分类的知识点列表界面
	manageKnowledgeList = function(id,name){
		console.log(id);
		console.log(name);
		go2Page("/knowledge/list.html","classificationId="+id+"&classificationName="+name);
	};
	
});
