	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils.menu","utils","jquery.scrolltofixed","jquery.scroll.pagination",
	        "bootstrap.select","jquery.base"], function($,tree,cookie,list,doT,paging,menu) {
		//初始化函数
		var init = function(){
			

			list.init({
						tablePanel:'course_package_list_panel',
						tableDataTemplate:'course_package_list_data_template',
						loadDataListUrl:'/course/packages/load_data_by_classifications_and_tags.html',
						isChildrenClassificationUrl:'/classification/num_classification.html',
						itemType:4
					 },{
						 paginationFunc:paginationFunc
					 });
			
			list.onLoadDataList(1,[],[]);
			
			//页码设置
			$("#hid_default_course_package_page").val(2);
			
			//实现滚动加载列表
			$('#coursePackageContent').scrollPagination({
				'postUrl' : ctx + '/course/packages/load_data_by_classifications_and_tags.html',
				'postParams' : {
					pageNo : 1,
					pageSize:6,
					classificationIdList:[],
					tagIdList:[]
				},
				'scrollTarget' : $(window),
				'callBackLoad' : function(data) {
					loadCoursePackageDataList(data);
				},
				'beforeLoad' : function() {
					this.postParams.pageNo = $("#hid_default_course_package_page").val();
					this.postParams.pageSize = 6;
				}
			});
		}
		
		//tab pane事件设置
		onLinkClick = function (packageId) {
			go2Page('/course/packages/profile.html',"packageId="+packageId);
		}
		
		//加载分页数据
		loadPageDataList = function(pageNo){
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
			list.onLoadDataList(pageNo,classificationIdList,tagIdList);
		}
		
		
		//滚动加载考场列表的回调函数
		loadCoursePackageDataList = function(data) {
			var template = doT.template($("#" + list.setting.opts.tableDataTemplate).text());
				$('#' + list.setting.opts.tablePanel).append(template({
					"data" : data.dataList
				}));
			
			if (data.dataList.length != 0) {
				var finalPageNo = Number($("#hid_default_course_package_page").val()) + 1;
				$("#hid_default_course_package_page").val(finalPageNo);
			}
		};
		
		onFilterByTags = function(obj){
			list.onFilterByTags(obj);
		};
		
		onDetail = function(id){
			go2Page('/course/packages/profile.html',"packageId="+id);
		};
		
		onEditPackagesDetail = function(){
			go2Page('/course/packages/profile.html','packageId=' + $.trim($('#_item_id').val()));
		}
		
		onSelectPackages = function(id){
			menu.onSelectItem(id);
		};
		
		
		onDeleteOnlineCourse = function(){
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
		
		
		paginationFunc = function(result){
			paging.pageTemplateDiv(result, list.setting.opts.tablePanel, list.setting.opts.tableDataTemplate);
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

		init();
	});