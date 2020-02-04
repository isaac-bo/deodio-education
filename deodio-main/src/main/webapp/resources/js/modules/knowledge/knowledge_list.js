define([ "jquery", "utils.cookie", "jquery.dot","pagination", "utils", "jquery.base", "jquery.validate","bootstrap.datepicker",
         "jquery.scrolltofixed", "jquery.mCustomScrollbar"], function($, cookie, doT, paging) {
	
	var _init = function() {
		var returnflag=$('#returnflag').val();
		if(returnflag=="true"){
			$('#returnbutton').hide();
		}
		loadDataList(1);
		customInput("allKnowledgePointsId");
		checkOrUncheckAll('allKnowledgePointsId','knowledgePointsId');
	};
	
	//add by xuxiangdong  20180524 start
	treeLocalSetting = function(){
		return {
			check : {
				enable : true,
				chkDisabledInherit: true,
				chkStyle: "radio",
				radioType : "all"
			}
		}
	}
	//add by xuxiangdong  20180524 end
	
	onJump2Classification = function(){
		go2Page('/classification/list.html');
	};
//	onJump3Classification = function(){
//		go2Page('/classification/load_list.html');
//	};
	
	//加载知识点列表
	loadDataList = function(pageNo) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			classificationId : $('#classificationId').val(),
			keyword : $.trim($('#keywords').val())
		}, url = "/knowledge/load_knowledge_list.html";
		postAjaxRequest(url, params, function(result) {
			paging.pageTemplateDiv(result, "table_panle", "knowledge_data_template", "data_page_Panel", "loadDataList");
			customInput("knowledgePointsId");
			$('body').removeClass('modal-open').css('overflow-y','auto');

		});
	};
	
	//弹出新建知识点窗口
	createKnowledgePoint = function(){
		var template = doT.template($("#pop_template").text());	
		popUpWindow(template({id:'',knowledgePointName:'',knowledgePointDesc:'',classificationName:$("#classificationName").val(),classificationId:$("#classificationId").val()}),"新建知识点",578,386);
		var name=$("#classificationName").val();
//		$("#genereNodes").text(name);
		$("#delclassbutton").attr('disabled',false);
//		#knowledgePointsForm
		$("#knowledgePointsForm").myValidate({
			formCall:function(){ submitKnowledgePoint();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});	
	};
	//名字校验
	checkName = function(){
		var newKnowledgePointName=$("#knowledgePointName").val();
		var oldKnowledgePointName=$("#hiddenknowledgePointName").val();
		var flag=false;
		if(newKnowledgePointName==oldKnowledgePointName){
			flag=true;
		}else{
		var url="/trainers/getCheck_trainers.html",data={
				knowledgePointName:newKnowledgePointName
		}
		postAjaxRequest(url, data,function(result){
			flag=result.data;
	    },false);
		return flag;
	}
	}

	//提交表单，创建知识点
	submitKnowledgePoint = function(){
		//名字校验
//		console.log($.trim($("#knowledgePointName").val()));
		var knowledgePointName = $.trim($("#knowledgePointName").val())
		if($(".sel_btn").length > 0){
			var classificationList = [];
			$(".sel_btn").each(function(item){
				classificationList.push($(this).children().attr("classification"));
			});
//			console.log($.trim($("#knowledgePointId").val()));
		var nameFlag;
		var url="/knowledge/getCheck_knowledge.html",data={
				knowledgePointName:$.trim($("#knowledgePointName").val())
		};
//		var data={
//				knowledgePointName:knowledgePointName
//		};
			
//		postAjaxRequest(url, data,function(result){
//			if(result.data==true){
//				nameFlag=true;
//			}else{
//				nameFlag=false;
//			}
//	    },false);
//		if(nameFlag==false){
//			console.log(name,"......");
//			alertMsg("知识点已存在");
//			return;
//		}
		
//	if($(".sel_btn").length > 0){
//			
//			var classificationList = [];
//			$(".sel_btn").each(function(item){
//				classificationList.push($(this).children().attr("classification"));
//			});
//			console.log($.trim($("#knowledgePointId").val()));
			var url="/knowledge/submit_knowledge.html",data={
					knowledgePointId:$.trim($("#knowledgePointId").val()),
					knowledgePointName:$.trim($("#knowledgePointName").val()),
					knowledgePointDesc:$.trim($("#knowledgePointDesc").val()),
				classificationIds:classificationList.join(","),
				//classificationId:$("#treeNodeId").val()
			};
			postAjaxRequest(url, data, function(result){
				closePopWindow();
				loadDataList(1);
//				tree.addNode(result.data,$.trim($("#knowledgePointName").val()),$('#knowledgePointId').val());
				alertMsg("操作成功");
			});
	}
	};
	
	//弹出更新知识点窗口
	editKnowledgePoint = function(id,cid){
		var url="/knowledge/get_knowledge.html",data={
				knowledgePointId:id,
				classificationId:cid
		};
		postAjaxRequest(url, data, function(result){
//			console.log(result.data.knowledgePoints.knowledgePointName);
			var template = doT.template($("#pop_template").text());
			popUpWindow(template({id:result.data.knowledgePoints.id,
				knowledgePointName:result.data.knowledgePoints.knowledgePointName,
				knowledgePointDesc:result.data.knowledgePoints.knowledgePointDesc,
				updateTime:result.data.knowledgePoints.updateTime,
				//赋值
				classificationId:result.data.classification.id,			
				classificationName:result.data.classification.classificationName}),"更新知识点",578,386);
			$("#btnPopClassificationPicker").attr('disabled',false);
			$("#knowledgePointsForm").myValidate({
				formCall:function(){ submitKnowledgePoint();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:true,regionText:true},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		});
	};
	
	//删除知识点
	delKnowledgePoint = function(id){
		confirmMsg('删除后将无法恢复，确定删除该知识点吗？',function(){
			var url="/knowledge/del_knowledge.html",data={
					knowledgePointId:id
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					loadDataList(1);
					alertMsg("操作成功");
				}
			});
		});
	};
	
	//批量删除知识点
	delAllKnowledgePoints = function(){
			var arr = new Array();
			$("input[name='knowledgePointsId']:checkbox").each(function(){ 
//				console.log(1)
				if($(this).next('label').hasClass("checked")){
					arr.push($(this).attr("id"));
				}
            });
	        var knowledgePointIds = arr.join(",");
	        if (knowledgePointIds){
	        	confirmMsg('请确认您是否要删除已选择的知识点？',function(){
	        var url="/knowledge/del_all_knowledge_points.html",
			data={knowledgePointIds:knowledgePointIds};
			postAjaxRequest(url,data,function(result){
				loadDataList(1);
				alertMsg("操作成功");
			});
		});
		}else {
	    	 alertMsg("请先勾选要删除的知识点!");
	     }
	};
	
	//搜索返回知识点列表
	searchKnowledgePointList = function(){
		loadDataList(1);
	};
	
	_init();
	
});
