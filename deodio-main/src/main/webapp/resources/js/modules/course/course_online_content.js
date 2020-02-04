﻿define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils","jquery.base","jquery.validate","ueditor","jquery.scroll.pagination",
      "jquery.mCustomScrollbar","bootstrap.select","jquery.ui"], function($,tree,cookie,list,doT,paging) {
	
	var selectedTabType = "";
	var currentKeyWord = "";
	var currentTabPane ="";
	var course_presentation_itemId="";
	var contentPageConstants = {
		"id":{"nr01":"nr01","nr02":"nr02","nr03":"nr03"},
		"qtype":{"nr01":"0","nr02":"1","nr03":"2"},
		"intDefault":'0'
	};
	var presentationExistArray=new Array();
	var testQuizExistArray=new Array();
	var surveyExistArray=new Array();
	//设置信息-返回上一级
	goBack = function(courseId){
		go2Page('/course/online/profile.html',"courseId="+courseId+"&courseType=2");
	}
	var selectExistArrayConfigs={
			"0":"presentationExistArray",
			"1":"testQuizExistArray",
			"2":"surveyExistArray"
	}
	var tabPaneContentConfigs= {
		"nr01":{tablePanel:contentPageConstants.id.nr01,
				tableDataTemplate:"course_presentation_tab_pane_template",
				loadDataListUrl:"/presentation/load_list.html",
				tabPaneId:"nr01_tab_pane"},
		"nr02":{tablePanel:contentPageConstants.id.nr02,
				tableDataTemplate:"course_quiz_exmination_tab_pane_template",
//				loadDataListUrl:"/quiz/load_list.html?quizCategory=1&isPublish=1",
				loadDataListUrl:"/quiz/content_load_list.html",
				tabPaneId:"nr02_tab_pane"},
		"nr03":{tablePanel:contentPageConstants.id.nr03,
				tableDataTemplate:"course_survey_tab_pane_template",
				loadDataListUrl:"/survey/load_course_survey_list.html",
				tabPaneId:"nr03_tab_pane"}
	}
	
	//初始化函数
	var init = function(){
		
		currentKeyWord = "";
		selectedTabType = "";
		currentTabPane = "";
		
		initSelectPicker();
		
		list.onFixedItems();
		
		$("#courseOnlineContentForm").myValidate({
			formCall : function(data) {
				if(data == 0)
					submitCourseOnlineContent();
				else 
					publishCourseOnlineContent();
			},
			isAlert : false,
			formKey : false,
			errorCustom : {
				customFlag : true,
				regionText : true
			},
			errorStyle : {
				errorRegion : "error-div",
				errorClass : "wrong-item",
				rightClass : "right-item"
			}
		});
		
		//拖拽排序函数
		$("#draggableContent").sortable({
	    	start:function(event, ui) {                              
//	    	        ui.item.css('border','1px dashed #dddddd');
//	    	        if($("#hiddenContentListSize").val()!=0){
//	    	        	$('.tuoru').hide();
//	    	        }
//	    	        var contentCourse=$(".box_pre p10 mt10 course_content");
//	    	        alert(contentCourse.size())
	    	    },
		    stop :function(event, ui) {  
		    	 ui.item.css('border','');
		    	 refreshCourseContent();
		    	
		    },		
	        receive: function(event, ui) {
	        	var htmlTmpl="",_typeVal = selectedTabType;
	    		switch (_typeVal) {
				case contentPageConstants.qtype.nr01:
					htmlTmpl=$("#course_presetation_template").text();
					break;
				case contentPageConstants.qtype.nr02:
					htmlTmpl=$("#course_examination_template").text();
					break;
				default:
					htmlTmpl=$("#course_survey_template").text();
					break;
				};
				var $draggable = $(this);
	        	var doTtmpl =doT.template(htmlTmpl); 
	        	
	        	//获取被拖拽的对象
	        	var $draggedDiv = $draggable.find("div.ui-draggable");
	        	//设置拖拽后的数据
				var dataOptions={
						uuid:UUID.prototype.createUUID(),
						order:$draggable.find("div.course_content").size()+1,
						name:$draggedDiv.find("[name='itemName']").val(),
						itemDesc:$draggedDiv.find("[name='itemDesc']").text(),
						itemId:$draggedDiv.find("[name='itemId']").val(),
						itemName:$draggedDiv.find("[name='itemName']").val(),
						itemType:selectedTabType,
						course_cover_img:$draggedDiv.find("img").attr("src")
				}
				var html = doTtmpl({"data":dataOptions});
				if($draggable.find("div.course_content").size()==0){
					$draggable.append(html);	        				
				}else{
					$draggable.find("div.course_content:last").after(html);
				}
				$draggedDiv.remove();
	        }
	    });
		//tab pane事件设置
		$('#myTab a').click(function (e) {
			  e.preventDefault()
			  $(this).tab('show')
			  var linkArray = $(this).prop("href").split("#");
			  currentKeyWord = "";
			  var tabPaneId = linkArray[1];
			  //重置检索条件
			  $("#" + tabPaneContentConfigs[tabPaneId].tablePanel).find("[name='keyWord']").val(currentKeyWord);
			  
			  loadTabPaneContent(tabPaneId);
		});
		//加载默认tab页数据
		$("#myTab li.active").children("a").trigger("click");
		$("#period").text( $(".course_content").length);
		$("#draggableContent").bind("DOMNodeInserted", function(){
			$("#period").text( $(".course_content").length);
		})
		
	};
	
	initSelectPicker = function(){
		$('select').selectpicker();	
		$('.bootstrap-select').css('width','120px').css('height','34px').css('top','-1px');
		$('.bootstrap-select button').css('height','34px');
	}
	//页面提交函数
//	onSubmit = function(){
//		go2Page('/course/setting.html');
//	};
	 //拖拽函数
	draggableFun = function(){
		$(".content-resource").draggable({
			helper:"clone",
			cursor: "move",	
			connectToSortable: '#draggableContent',
			start:function(event,ui){
			  //$("#draggableVle").val(value);
			}	
		});	
	};
	//刷新拖拽后数据内容
	refreshCourseContent = function(){
		var courseContents = $('.content-order');
		var len = courseContents.length;
		
		var presentations = [];
		var quizs = [];
		var surveys = [];
		var items = [];
		if(len>0){
			 $('.tuoru').hide();
		}else{
			$('.tuoru').show();
		}
		$(courseContents).each(function(i,v){
			 $(this).text(i+1);
			 var divObj = $(this).parent().parent();
			 var qtype = $(divObj).attr("qtype");
			 var itemId=$(divObj).find('input[type=hidden][name=itemId]').val();
			 course_presentation_itemId="course_presentation_"+itemId;
			 $("#"+course_presentation_itemId).hide();
			 var item = {
					  index:$(divObj).find('.content-order').text(),
					  itemName:$(divObj).find('input[type=hidden][name=itemName]').val(),
					  itemId:$(divObj).find('input[type=hidden][name=itemId]').val(),
					  itemType:qtype == contentPageConstants.qtype.nr01?'章节':qtype==contentPageConstants.qtype.nr02?'测验':'调查'
					 };

			 
			 if(qtype == contentPageConstants.qtype.nr01){
				 presentations.push(item);
			 }else if(qtype == contentPageConstants.qtype.nr02){
				 quizs.push(item);
			 }else{
				 surveys.push(item);
			 }
			 
			 items.push(item);
		});

		$(courseContents).each(function(i,v){
			
			  $(this).text(i+1);
			  var divObj = $(this).parent().parent();
			  var qtype = $(divObj).attr("qtype");
			  var order = $(divObj).find('.content-order').text();
			  
			  var gotoId = $(divObj).find(".goto").prop("id");
			  var goto2Id = $(divObj).find(".goto2").prop("id");
			  var select_1 = $("#" + gotoId + "_select_1");
			  var select_2 = $("#" + goto2Id + "_select_2");
			  
			  var selectItemsAsc = []; //generateSelectOption(len).concat([]);
			  var selectItemsDesc  = [];	
			  var defaultValueAsc = '';
			  var defaultValueDesc = '';
			  
			  //问卷调查，不设置select
			  if(qtype == contentPageConstants.qtype.nr03){
				  return true;
			  }
			  
			  if($('#courseRule').val() == 2){
				  $("#" + gotoId).hide();
				  $("#" + goto2Id).hide();
				  return true;
			  }
			  
			  //只有一个内容不设置select
			  if(len == 1){
				  $("#" + gotoId).hide();
				  $("#" + goto2Id).hide();
				  return true;
			  }
			  
			  if(qtype == contentPageConstants.qtype.nr01){
				  selectItemsAsc = generateSelectItems(order,items,false).concat([]);
				  defaultValueAsc = generateSelectItemDefaultValue(order,items,false);
			  }else if(qtype == contentPageConstants.qtype.nr02){
				  selectItemsAsc = generateSelectItems(order,presentations,false).concat([]);
				  defaultValueAsc = generateSelectItemDefaultValue(order,presentations,false);
				  selectItemsDesc = generateSelectItems(order,presentations,true).concat([]);
				  defaultValueDesc = generateSelectItemDefaultValue(order,presentations,true);
			  }
			  
			  if(selectItemsAsc.length == 0){
				  $("#" + gotoId).hide();
			  }else{
				  $("#" + gotoId).show();
			  }
			  
			  if(selectItemsDesc.length == 0){
				  $("#" + goto2Id).hide();
			  }else{
				  $("#" + goto2Id).show();
			  }
			  
			  setSelectPickerContent(select_1,selectItemsAsc.join(' '),defaultValueAsc);
			  
			  //presentation，不设置select2
			  if(qtype == contentPageConstants.qtype.nr01){
				  return true;
			  }
			  setSelectPickerContent(select_2,selectItemsDesc.join(' '),defaultValueDesc);
		});
		
		initSelectPicker();
	};
	
	//设置 selectpicker内容
	setSelectPickerContent = function(obj,content,defaultValue){
		debugger;
		$(obj).empty();
		$(obj).append(content);
		//更新内容刷新到相应的位置
		$(obj).selectpicker('render');
		$(obj).selectpicker('refresh');
		var valStr = $(obj).attr('value');
		//设置选中值
		if(valStr == 0 || valStr == undefined){
			$(obj).selectpicker("val",defaultValue);
		}else{
			$(obj).selectpicker("val",valStr);
		}
	
	}
	//删除选中拖拽数据
	removeCourseContent = function(obj){
		 debugger;
		 $(obj).parent().parent().remove();
		 refreshCourseContent();
	};
	//生成拖拽后选择下拉列表
	generateSelectOption = function(len){
		var optionArray = new Array();
		for(var i = 1; i <= len ;i++){
			var text = i;
			var value = i;
			optionArray.push("<option value='" + value + "'>"+ text +"</option>");
		}
		return optionArray;
	}
	
	generateSelectItems = function(order,items,desc){
		var selectItems = new Array();
		for(var i=0;i<items.length;i++){
		  if(desc == true){
			  if(parseInt(order) > parseInt(items[i].index)){
				  selectItems.push("<option value='" + items[i].index + "'>"+ items[i].index + ':' +items[i].itemType +':' + items[i].itemName +"</option>");
			  }
		  }else{
			  if(parseInt(order) < parseInt(items[i].index)){
				  selectItems.push("<option value='" + items[i].index + "'>"+ items[i].index + ':' +items[i].itemType +':' + items[i].itemName +"</option>");
			  }
		  }
		}
		return selectItems;
	};
	
	generateSelectItemDefaultValue = function(order,items,desc){
		var des = '';
		for(var i=0;i<items.length;i++){
		  if(desc == true){
			  if(parseInt(order) > parseInt(items[i].index)){
				  des = items[i].index;
			  }
		  }else{
			  if(parseInt(order) < parseInt(items[i].index)){
				  return items[i].index;
			  }
		  }
		}
		
		return des;
	}
	
	//tab-pane 页面数据加载
	loadTabPaneContent = function(tabPaneId,searchWord){	

		var currentTabPaneContentConfig;
		//设定选中的 tab id(变量) 
		switch(tabPaneId){
			case contentPageConstants.id.nr01:
				selectedTabType = contentPageConstants.qtype.nr01; 
				currentTabPaneContentConfig = $.extend({},tabPaneContentConfigs.nr01);
				break;
			case contentPageConstants.id.nr02:
				selectedTabType = contentPageConstants.qtype.nr02;
				currentTabPaneContentConfig = $.extend({},tabPaneContentConfigs.nr02);
				break;
			case contentPageConstants.id.nr03:
				selectedTabType = contentPageConstants.qtype.nr03;
				currentTabPaneContentConfig = $.extend({},tabPaneContentConfigs.nr03);
				break;
		}
		
		//
		
		
		
//		//设定当前tabpanel查询可用
//		setScrollPagination(tabPaneContentConfigs[tabPaneId].tabPaneId);
//		
//		//实现滚动加载列表
//		$("#" + currentTabPaneContentConfig.tabPaneId).scrollPagination({
//			'postUrl' : ctx + currentTabPaneContentConfig.loadDataListUrl,
//			'postParams' : {
//				pageNo : 1,
//				pageSize:9999999,
//				keywords : $("#" + currentTabPaneContentConfig.tablePanel).find("[name='keyWord']").val()
//			},
//			'scrollTarget' : $("#" +currentTabPaneContentConfig.tabPaneId),
//			'callBackLoad' : function(data) {
////					debugger;
//				var template = doT.template($("#" + currentTabPaneContentConfig.tableDataTemplate).text());
//				$("#" + currentTabPaneContentConfig.tabPaneId).append(template({
//					"data" : data.dataList
//				}));
//				if (data.dataList.length != 0) {
//					var finalPageNo = Number($("#hid_default_tab_pane_page").val()) + 1;
//					$("#hid_default_tab_pane_page").val(finalPageNo);
//					draggableFun();
//				}
//			},
//			'beforeLoad' : function() {
//				this.postParams.pageNo = 1;
//				this.postParams.pageSize = 9999999;
//			}
//		});
		
		//设定检索tabelpanel
		currentTabPaneContentConfig.tablePanel = currentTabPaneContentConfig.tabPaneId;
		var course_start_time=$("#course_start_time").val();
		var course_expire_time=$("#course_expire_time").val();
		//设置当前tab-pane编号
		currentTabPane = tabPaneId;	
		var existDataArray=getCourseContentExistList(eval(selectExistArrayConfigs[selectedTabType]),selectedTabType);
		var array = JSON.stringify(existDataArray);
		var params = {
			pageNo:1,
			pageSize:9999999,
			keywords:$.trim(searchWord),
			array:array,
			isPublish:1,
			course_start_time:course_start_time,
			course_expire_time:course_expire_time
		},url=currentTabPaneContentConfig.loadDataListUrl;
		postAjaxRequest(url, params, function(result){
//				debugger;
			paginationFunc(result,currentTabPaneContentConfig.tablePanel,currentTabPaneContentConfig.tableDataTemplate);
		});
		
		//设置默认页数
//		$("#hid_default_tab_pane_page").val(2);
		
	}
	
	//分页回调函数
	paginationFunc = function(result,tablePanel,tableDataTemplate){
		paging.pageTemplateDiv(result, tablePanel, tableDataTemplate);
		draggableFun();
		//设置检索条件显示值
		if(currentKeyWord){
			$("#" + currentTabPane).find("[name='keyWord']").val(currentKeyWord);
		}
	};
	getCourseContentExistList=function(dataArray,type){
		var courseId = $("#courseId").val();
		$("#draggableContent").find(".course_content").each(function(i, v) {
			var itemId = $(this).find("[name='itemId']").val();
			var itemType = $(this).find("[name='itemType']").val();
			if(itemType==type){
				if($.inArray(itemId, dataArray)==-1){
					dataArray.push(itemId);
				}			
			}
		});
		return dataArray;
	}
	//获取拖拽后的数据
	getCourseOnlineContentList = function(){
		var dataArray = new Array();
		var courseId = $("#courseId").val();
		debugger;
		$("#draggableContent").find(".course_content").each(function(i, v) {
			
				var itemId = $(this).find("[name='itemId']").val();
				var itemType = $(this).find("[name='itemType']").val();
				var itemSort = $(this).find(".content-order").text();
				
				var gtDiv = $(this).find(".goto");
				var itemGt = $(gtDiv).find("[name='gtThreshold']").val();
				var itemGtPos = $(gtDiv).find("select").val();
				//为空情况
				if(!itemGt){
					itemGt = '0';
					itemGtPos = '0';
				}
				
				var ltDiv = $(this).find(".goto2");
				var itemLt = $(ltDiv).find("[name='ltThreshold']").val();
				var itemLtPos = $(ltDiv).find("select").val();
				//为空情况
				if(!itemLt){
					itemLt = '0';
					itemLtPos = '0';
				}
				var unit ={
						itemId:itemId,
						itemType:itemType,
						itemSort:itemSort,
						itemGt:itemGt,
						itemGtPos:itemGtPos,
						itemLt:itemLt,
						itemLtPos:itemLtPos,
						courseId:courseId
				};
				
				dataArray.push(unit);
		});
		var dataStr = JSON.stringify(dataArray);
		return dataStr;
	}
	
	//提交数据
	submitCourseOnlineContent = function() {
		var courseId = $("#courseId").val();
		var url = "/course/online/submit_content.html", data = {
			dataStr : getCourseOnlineContentList(),
			courseId : courseId
		};
		postAjaxRequest(url, data, function(result) {
			if(result.status == 1){
				go2Page("/course/setting.html","courseId="+courseId+"&courseType=1");
			}else{
				alertMsg("创建在线课程失败！");
			}
		});
	}
	
	publishCourseOnlineContent = function(){
		if (getCourseOnlineContentList().length == 2) {
			alertMsg("未添加课程内容，课程不可发布！");
			return;
		}
		var courseId = $("#courseId").val();
		var isPublic = $("#isPublic").val();
		var isPublish = 0;
		if (isPublic == 0) {
			isPublish = 1;
		}
		var url = "/course/online/submit_content.html", data = {
			dataStr : getCourseOnlineContentList(),
			courseId : courseId,
			isPublish : isPublish
		};
		postAjaxRequest(url, data, function(result) {
			if(result.status == 1){
				if (result.data.isPublic == 1) {
					courseOnlinePublish();
				} else {
					go2Page('/course/online/list.html');
				}
			}else{
				alertMsg("创建在线课程失败！");
			}
		});
	}
	
	//发布课程
	courseOnlinePublish = function(){
		debugger;
		var courseGroupItemType = 41;
		$("#publishModal").modal("show");
		$('#group_container_type').val(courseGroupItemType);
		$('#item_id').val($("#courseId").val());
	};
	
	//tab-pane 检索按钮功能
	onSearchList = function(e){
		//检索条件
		var keyWord = $(e).next().find("[name='keyWord']").val();
		//获取当前选中的tab页Id
		var tabPaneId = $(e).parents(".tab-pane").attr("id");
		//设置默认页数
		$("#hid_default_tab_pane_page").val(1);
		loadTabPaneContent(tabPaneId,keyWord);
		currentKeyWord = keyWord;
	}
	
	onIsTabPaneHasContent = function(tabPaneId){
		var flag = false;
		var len = $("#" + tabPaneId).find(".content-resource").size();
		if(len > 0){
			flag = true;
		}
		debugger;
		return flag;
	}
	
	setScrollPagination = function(enabledId){
		$(".scrollTab").each(function(){
			$(this).stopScrollPagination();
			$(this).unbindScroll();
		});
	}
	
	publishItemFunc = function(){
		var courseId = $("#courseId").val();
		var url = "/course/online/submit_content.html", data = {
			dataStr : getCourseOnlineContentList(),
			courseId : courseId,
			isPublish : 1
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
//				go2Page("/course/setting.html","courseId=" + courseId + "&courseType=1");
				go2Page('/course/online/list.html');
			}else{
				alertMsg("发布在线课程失败！");
			}
		});
	};
	
	removeQuestion = function(obj){
		//获取itemId和itemType
		var itemId=$(obj).parent().parent().parent().find("[name=itemId]").val();
		var itemType=$(obj).parent().parent().parent().find("[name=itemType]").val();	
		//获取相同的itemType下，已经添加的数组
		var array=getCourseContentExistList(eval(selectExistArrayConfigs[itemType]),itemType);
		//如果数组中存在itemId,则将其移除
		if(array.indexOf(itemId)!=-1){
			array.splice(array.indexOf(itemId), 1);	 		
		}
		//隐藏左侧的div
		$(obj).parent().parent().parent().remove();
		//刷新右侧div的数据
		loadTabPaneContent(currentTabPane,currentKeyWord);
		//刷新课程内容的编号
		refreshQuizNo();
		var courseContents = $('.content-order');
		var len = courseContents.length;
		if(len>0){
			 $('.tuoru').hide();
		}else{
			$('.tuoru').show();
		}
	};
	refreshQuizNo = function(){
		$(".content-order").each(function(i,v){
			  $(this).text(i+1);
		});
	};
	
	init();
});

