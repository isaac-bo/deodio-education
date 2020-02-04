	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils","jquery.scrolltofixed","jquery.scroll.pagination",
	        "bootstrap.select","jquery.base"], function($,tree,cookie,list,doT,paging) {
		//初始化函数
		var init = function(){
			

			list.init({
						tablePanel:'course_onlive_panle',
						tableDataTemplate:'course_onlive_data_template',
						loadDataListUrl:'/course/onlive/load_data_by_classifications_and_tags.html',
						isChildrenClassificationUrl:'/classification/num_classification.html'
					 },{
						 paginationFunc:paginationFunc
					 });
			
			list.onLoadDataList(1,[],[],'',function(){
				$('.null_table').css('margin','20px');
				if($('.null_table').length>0){
					$('.con-corner').css('margin-top','5px');
				}
				
			});
			
			//页码设置
			$("#hid_default_course_onlive_page").val(2);
			
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
					this.postParams.pageNo = $("#hid_default_course_onlive_page").val();
					this.postParams.pageSize = 6;
				}
			});
			
		};
		
		//加载分页数据
		loadPageDataList = function(pageNo){
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
			list.onLoadDataList(pageNo,classificationIdList,tagIdList,'',function(){
				$('.null_table').css('margin','20px');
				if($('.null_table').length>0){
					$('.con-corner').css('margin-top','5px');
				}
			});
			

		};
		
		
		//滚动加载考场列表的回调函数
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
				var finalPageNo = Number($("#hid_default_course_onlive_page").val()) + 1;
				$("#hid_default_course_onlive_page").val(finalPageNo);
			}
		};
		
		onFilterByTags = function(obj){
			list.onFilterByTags(obj);
		};
		
		
		paginationFunc = function(result){
			paging.pageTemplateDiv(result, list.setting.opts.tablePanel, list.setting.opts.tableDataTemplate, "presentation_data_page_panel","loadPageDataList");
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
		
		onToOnliveCourseDetail = function(){
			go2Page('/course/onlive/profile.html','courseId=' + $.trim($('#_item_id').val()));
		}
		
		onDeleteOnliveCourse = function(){
			confirmMsg("确定要删除当前课程",function(){
				var url="/course/delete.html",data={
						courseId:$.trim($('#_item_id').val())
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						alertMsg("操作成功");
						//重新加载数据
						reloadPageData();
					}else{
						alertMsg("操作失败");
					}
				});
			});
		}

		//重新加载数据
		var reloadPageData = function(){
			list.onLoadDataList(1,[],[],'',function(){
				$('.null_table').css('margin','20px');
				if($('.null_table').length>0){
					$('.con-corner').css('margin-top','5px');
				}
			});
			//页码设置
			$("#hid_default_course_onlive_page").val(2);
		}
		
		init();
	});