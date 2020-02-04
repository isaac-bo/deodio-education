	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils.menu","utils","jquery.scrolltofixed","jquery.scroll.pagination",
	        "bootstrap.select","jquery.base"], function($,tree,cookie,list,doT,paging,menu) {
		
		//初始化函数
		var init = function(){
			$("#share_owner_id").val("");
			$("#preview_type_id").val("");
			//加载当前账号下的所有课程
			
			list.init({
						tablePanel:'classification_panle',
						tableDataTemplate:'classification_data_template',
						loadDataListUrl:'/presentation/load_data_by_classifications.html',
						isChildrenClassificationUrl:'/classification/num_classification.html',
						itemType:1
					 },{
						 paginationFunc:paginationFunc
					 });
			
			list.onLoadDataList(1,[],[]);
			//页码设置
			$("#hid_default_presentation_page").val(2);
			
			//实现滚动加载列表
			$('#classificationContent').scrollPagination({
				'postUrl' : ctx + '/presentation/load_data_by_classifications.html',
				'postParams' : {
					pageNo : 1,
					pageSize:6,
					classificationIdList:[],
					tagIdList:[]
				},
				'scrollTarget' : $(window),
				'callBackLoad' : function(data) {
					loadClassificationsDataList(data);
				},
				'beforeLoad' : function() {
					this.postParams.pageNo = $("#hid_default_presentation_page").val();
					this.postParams.pageSize = 6;
				}
			});
		};
		
		//加载分页数据
		loadPageDataList = function(pageNo){
			var searchWord = $.trim($("#searchWord").val())
			if(!pageNo){
				pageNo = 1;
			}
			var tagIdList = [];
			var classificationIdList = [];
			$.each($('.on'),function(i,item){
				if($(item).prop("id")!=""){
					tagIdList.push($(item).prop("id"));
				}
			})
			$.each($(".filter_condition").children().children().children('.top-tabox-fl'),function(i,item){
				classificationIdList.push($(item).prop("id"));
			})
			list.onLoadDataList(pageNo,classificationIdList,tagIdList,searchWord);
		
			
		}
		
		
		loadClassificationsDataList = function(data) {
			var template = doT.template($("#classification_data_template").text());
			if ($("#classificationContent").size() == 0) {
				$('#classificationContent').append(template({
					"data" : data.dataList
				}));
			} else {
				$('#classificationContent ul').append(template({
					"data" : data.dataList
				}));
			}
			if (data.dataList.length != 0) {
				var finalPageNo = Number($("#hid_default_presentation_page").val()) + 1;
				$("#hid_default_presentation_page").val(finalPageNo);
			}
		
		};
		
		onToPresetationDetail = function(){
			
			go2Page('/presentation/profile.html','presentationId=' + $.trim($('#_item_id').val()));
		}
		
		onDelPresetation = function(){
			confirmMsg("删除后将无法恢复，确定删除该章节吗？",function(){
				var url="/presentation/delete.html",data={
						presentationId:$.trim($('#_item_id').val())
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						if(result.data==0){
							alertMsg("该章节已被引用，请联系课程的创建者取消引用，再执行删除操作");
						}else{
							alertMsg("操作成功");
							//重新加载数据
							loadPageDataList();
						}
					
					}else{
						alertMsg("操作失败");
					}
				});
			});
		}
		
		
		onSelectPresentation = function(id,modelType,publishType,shareOwner,createId,itemName){
			$("#_item_name").val(itemName);
			if(createId==$.cookie('CUID')){
				$("#delete_id").show();
			}else{
				$("#delete_id").hide();
			};
			
			if(createId!=$.cookie('CUID')){
				$("#share_id").hide();
			}else{
				$("#share_id").show();
			};
			
			if(modelType!='2'){
				$("#draw_id").hide();
			}else{
				$("#draw_id").show();
			};
			
			$("#preview_type_id").val(modelType);

			if(publishType=="1"){
				$("#editor_id").hide();
				$("#share_id").hide();
			}else{
				$("#editor_id").show();
				$("#share_id").show();
			};
			$("#share_owner_id").val(shareOwner);
		
			menu.onSelectItem(id);
		};
		
		onFilterByTags = function(obj){
			list.onFilterByTags(obj);
		};
		
		
		paginationFunc = function(result){
			paging.pageTemplateDiv(result, list.setting.opts.tablePanel, list.setting.opts.tableDataTemplate, "presentation_data_page_panel","loadPageDataList");
			if(result.data.currePage == 1 && result.data.dataList.length!=0){
				$(".hid_btn").show();
				$("#classification_panle li:eq(0)").click();
			};
			if(result.data.dataList.length==0){
				$(".hid_btn").hide();
			}
		
		};
		
		onSelectedClassification = function(name,id){
			list.onSelectedClassification(name,id);
		};
		
		onRemoveClassification = function(object){
			list.onRemoveClassification(object);
		};
		
		onDeleteClassification = function(obj){
			list.onDeleteClassification(obj);
		};

		viewDetail = function(id){
			var url="/presentation/view_detail.html",params={
					presentationId:$.trim(id)
			};
			postAjaxRequest(url, params, function(result){
				var template = doT.template($("#abstract_data_template").text());
				popUpWindow(template({data:result.data}),"章节简介",1000,800);
				
			});
		};
		
		setTab = function(id){
			$("._tabs").hide();
			$("#"+id).show();
		}
		presentaPublish = function(status,pid){
			
			if(status=="0"){
				confirmMsg("取消发布后将无法被引用，确定取消发布该章节吗?",function(){
					updateStatus(pid,status);
				});
			}else{
				updateStatus(pid,status);
			}
		
		}
		
		updateStatus = function(pid,status){
			var url="/presentation/updatePresentationStatus.html",params={
					presentationId:$.trim(pid),
					status:$.trim(status)
			};
			postAjaxRequest(url, params, function(result){
				if(result.data==0){
					alertMsg('该章节已被引用，请联系课程的创建者取消引用，再执行取消发布操作');
				}else{
					loadPageDataList(1);
					closePopWindow();
				};
				
			});
		};
		
		
		
		setClickInfo = function(text){
			$("#click_info").text(text);
		}
		
		onSharePresentation =  function(){
			
			if($("#share_owner_id").val()!=''){
				alertMsg('该章节已经被分享，请选择其他章节');
			}else{
				$("#presentationShareModal").modal("show");
			};
			
		};
		openPreview = function(){
			var _methodUrl = "",_type=$("#preview_type_id").val();
			if(_type==""){
				alertMsg("请选择要预览的章节！");
				return;
			};
			switch (Number(_type)) {
			case 0:
				_methodUrl="scrom";
				break;
			case 1:
				_methodUrl="package";	
				break;
			case 2:
				_methodUrl="sync";
				break;
			case 3:
				_methodUrl="external";
				break;
			default:
				break;
			};
			
			window.open("//"+httpCURL+"presentation/"+_methodUrl+"/preview.html?presentationId="+$.trim($('#_item_id').val())+"&type=preview"); 
		};
		
		copyPresentation = function(){
			var _presentation = $('#_item_id').val();
			
			var params ={
					presentationName:$("#_item_name").val(),
					presentationId:$('#_item_id').val()
			}
			
			
			var template = doT.template($("#copy_data_template").text());
			popUpWindow(template({data:params}),"复制章节",650,400);
		};
		
		onSaveCopyPresentation = function(presentationId){
			var url="/presentation/saveCopyPresentation.html",params={
					presentationId:$.trim(presentationId),
					copyName:$.trim($("#copy_presentation").val())
			};
			postAjaxRequest(url, params, function(result){
				if(result.data==1){
					alertMsg("您所输入的章节名称已存在！");
				}else{
					loadPageDataList(1);
					closePopWindow();
				};	
			});
		};
		
		quotesContent = function(){
		
			
			var template = doT.template($("#right_content_presentation_quote_template").text());
			popUpWindow(template(),"章节引用",650,500);
			loadQuoteList(1);
			
		}
	
		loadQuoteList = function(pageNo) {
			if (pageNo == undefined)
				pageNo = 1;
			var params = {
					pageNo : pageNo,
					presentationId : $('#_item_id').val()
			}, url = "/presentation/getPresentationQuote.html";
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					paging.pageTemplateDiv(result, 
						"table_content_presentation_quote",
						"data_presentation_quote_template",
						"presentation_quote_panel", 
						"loadQuoteList");
				}
			});
		}
		
		quotesCourseContent = function(){
		
			
			var template = doT.template($("#right_content_presentation_quote_course_template").text());
			popUpWindow(template(),"章节被引用",750,500);
			loadQuoteCourseList(1);
			
		}
	
		loadQuoteCourseList = function(pageNo) {
			if (pageNo == undefined)
				pageNo = 1;
			var params = {
					pageNo : pageNo,
					presentationId : $('#_item_id').val()
			}, url = "/presentation/findQuoteForCourse.html";
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					paging.pageTemplateDiv(result, 
						"table_content_presentation_course_quote",
						"data_presentation_quote_course_template",
						"presentation_quote_course_panel", 
						"loadQuoteCourseList");
				}
			});
		}
		
		
		
		
		
		
		init();
	});