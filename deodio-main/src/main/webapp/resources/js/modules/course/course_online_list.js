define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils.menu","utils","jquery.scrolltofixed","jquery.scroll.pagination",
        "bootstrap.select","jquery.base"], function($,tree,cookie,list,doT,paging,menu) {
	var userId = cookie.getCookie('CUID');
	//初始化函数
	var init = function(){
		
		list.init({
					tablePanel:'classification_panle',
					tableDataTemplate:'classification_data_template',
					loadDataListUrl:'/course/online/load_data_by_classifications_and_tags.html',
					isChildrenClassificationUrl:'/classification/num_classification.html',
					itemType:21
				 },{
					 paginationFunc:paginationFunc
				 });
		
		reloadPageData();
		
		//实现滚动加载列表
		$('#classificationContent').scrollPagination({
			'postUrl' : ctx + '/course/online/load_data_by_classifications_and_tags.html',
			'postParams' : {
				pageNo : 1,
				pageSize : 6,
				classificationIdList : [],
				tagIdList : [],
				keyword :$.trim($('#keyword').val())
			},
			'scrollTarget' : $(window),
			'callBackLoad' : function(data) {
				loadClassificationsDataList(data);
			},
			'beforeLoad' : function() {
				this.postParams.pageNo = $("#hid_default_course_online_page").val();
				this.postParams.pageSize = 6;
				this.postParams.keyword = $.trim($('#keyword').val());
			}
		});
		
		iconDetail();
		
	}
	//重新加载数据
	reloadPageData = function(){
		list.onLoadDataList(1,[],[],'',function(){
			$('.null_table').css('margin','20px');
			if($('.null_table').length>0){
				$('.con-corner').css('margin-top','5px');
			}
			var courseOwner = $("#_item_course_owner").val();
			var createId = $("#_item_create_id").val();
			var isPublish = $("#_item_is_publish").val();
			changeIcon(courseOwner, createId, isPublish);
		});
		//页码设置
		$("#hid_default_course_online_page").val(2);
	};
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
		var keyword = $.trim($('#keyword').val());
		list.onLoadDataList(pageNo,classificationIdList,tagIdList,keyword,function(){
			$('.null_table').css('margin','20px');
			if($('.null_table').length>0){
				$('.con-corner').css('margin-top','5px');
			}
		});
	}
	//滚动加载课程列表的回调函数
	loadClassificationsDataList = function(data) {
		var template = doT.template($("#classification_data_template").text());
		if ($("#classificationContent").size() == 0) {
			$('#classificationContent').append(template({
				"data" : data.dataList,
				"currentPage" : data.currePage
			}));
		} else {
			$('#classificationContent ul').append(template({
				"data" : data.dataList,
				"currentPage" : data.currePage
			}));
		}
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_course_online_page").val()) + 1;
			$("#hid_default_course_online_page").val(finalPageNo);
		}
	};
	
	onFilterByTags = function(obj) {
		list.onFilterByTags(obj);
	};
	
	onDetail = function(id) {
//		go2Page('/course/online/detail.html',"courseId="+id);
		var url = "/course/online/detail.html",
		params = {
			courseId : id
		};
		postAjaxRequest(url, params, function(result){
			var template = doT.template($("#lecturer_detail_template").text());
			popUpPreviewWindow(template({data:result.data}),"课程简介",1000,600);
			
			 $('#myTab a:first').tab('show');//初始化显示哪个tab
		     $('#myTab a').click(function (e) {
		    	 e.preventDefault();//阻止a链接的跳转行为
		    	 $(this).tab('show');//显示当前选中的链接及关联的content
		     });
		});
	};
	
	
	//跳转到线上课程设置页面
	onCourseOnlineSetting = function(id,courseType){
		gotoCourseOnlineManagerPage('/course/setting.html','courseId=' + id,'courseType='+ courseType);
	};
	
	gotoCourseOnlineManagerPage = function(url,param,param1){
		if(param&&param1){
			window.location.href = ctx + url+"?"+param+"&"+param1+"&r=" + Math.floor(Math.random() * 100);
		}else{
			window.location.href = ctx + url+"?r=" + Math.floor(Math.random() * 100);
		}
	};
	//发布课程
	courseOnlinePublish = function(id) {
		if ($("#isEdit").val() == 1) {
			alertMsg('该线上课程正在被编辑，不允许发布');
			return false;
		}
		if ($("#coursewareCount").text() == 0) {
			alertMsg('未添加课程内容，课程不可发布');
			return false;
		}
		if ($("#isPublic").val() == 1) {
			var courseGroupItemType = 41;
			$("#publishModal").modal("show");
			$('#group_container_type').val(courseGroupItemType);
			$('#item_id').val(id);
		} else {
			var url = "/course/online/publish.html",
			params = {
				courseId : id,
				isPublish : 1
			}
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					alertMsg("发布成功", function() {
						go2Page("/course/online/list.html");
					})
				} else {
					alertMsg("发布失败");
				}
			}) 
		}
	};
	// 取消发布课程
	cancelCourseOnlinePublish = function(id) {
		if ($("#traineeCount").text() > 0) {
			alertMsg("该课程已有学员学习，不能取消发布");
		} else {
			confirmMsg("确定要取消发布当前课程?", function() {
				var url = "/course/online/publish.html", params = {
					courseId : id,
					isPublish : 0
				}
				postAjaxRequest(url, params, function(result) {
					if (result.status == 1) {
						alertMsg("取消发布成功",function(){
							go2Page("/course/online/list.html");
						});
					} else {
						alertMsg("取消发布失败");
					}
				});
			});
		}
	}
	onSelectOnlineCourse = function(id, courseName, isPublish, courseOwner, isEdit, createId, studentNum) {
		menu.onSelectItem(id);
		$("#_item_course_name").val(courseName);
		$("#_item_is_publish").val(isPublish);
		$("#_item_course_owner").val(courseOwner);
		$("#_item_is_edit").val(isEdit);
		$("#_item_create_id").val(createId);
		$("#_item_student_num").val(studentNum);
		changeIcon(courseOwner, createId, isPublish);
	};
	//
	onSearchCourse = function() {
		loadPageDataList(1);
		var speed=200;//滑动的速度
		$('body,html').animate({ scrollTop: 0 }, speed);
	}
	//跳转到线上课程详情页面
	onToOnlineCourseDetail = function(){
		go2Page('/course/online/detail.html','courseId=' + courseId);
	}
	//跳转到线上课程编辑页面
	onToOnlineCourseEdit = function(){
		if($("#_item_is_edit").val() == 1) {
			alertMsg('该课程正在被编辑, 不允许同时编辑');
			return;
		}
		var id = $.trim($('#_item_id').val());
		initContent();
		if ($("#_item_is_publish").val() == 1) {
			$('#format1').show();
			if ($("#_item_student_num").val() > 0 ) {
				$('#format1').hide();
				$('#format2').show();
				$('#detailEntrance').attr('disabled',"true");
			}
		}
	};
	//跳转到线上课程删除页面
	onDeleteOnlineCourse = function(){
		var courseId = $.trim($('#_item_id').val());
		if ($("#_item_is_edit").val() == 1) {
			alertMsg('该课程正在被编辑, 不允许删除');
			return;
		}
		confirmMsg("确定要删除当前课程",function(){
			var url="/course/delete.html",data={
					courseId:courseId
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
	//跳转到线上课程引用页面
	onQuoteOnlineCourse = function() {
		$("#courseQuoteModal").modal("show");
	}
	//跳转到线上课程复制页面
	onCopyOnlineCourse = function() {
		if($("#_item_is_edit").val() == 1) {
			alertMsg('该课程正在被编辑, 不允许复制');
			return;
		}
		$("#courseCopyModal").modal("show");
	}
	//跳转到线上课程分享页面
	onShareOnlineCourse = function() {
		if($("#_item_course_owner").val() != userId) {
			if($("#_item_is_edit").val() == 1) {
				alertMsg('该课程正在被编辑，不允许取消分享');
				return;
			} else {
				confirmMsg('该课程已被分享，确认取消分享吗', function() {
					onUpdateCourseOwner();
//						$("#courseShareModal").modal("show");
				});
			}
		} else {
			$("#courseShareModal").modal("show");
		}
	}
	//跳转到线上课程预览页面
	onPreviewOnlineCourse = function() {
		go2Page('/course/online/preview.html',"courseId="+$('#_item_id').val());
//			var url = "/course/online/preview_course.html",
//				params = {
//					courseId : $('#_item_id').val()
//				};
//			postAjaxRequest(url, params, function(result){
//				var template = doT.template($("#preview_data_template").text());
//				popUpWindow(template({data:result.data}),"课程预览",1000,800);
//				
//			});
	}
	
	paginationFunc = function(result) {
		paging.pageTemplateDiv(result, list.setting.opts.tablePanel, list.setting.opts.tableDataTemplate);
	};
	
	onSelectedClassification = function(name,id) {
		list.onSelectedClassification(name,id);
	};
	
	onRemoveClassification = function(object) {
		list.onRemoveClassification(object);
	};
	
	onDeleteClassification = function(obj) {
		list.onDeleteClassification(obj);
	};
	//更新课程拥有者
	onUpdateCourseOwner = function() {
		var url = "/course/online/share_course_owner.html",
		data = {
			courseOwner : userId,
			courseId : $('#_item_id').val()
		};
		postAjaxRequest(url, data, function(result) {
			reloadPageData();
		},false);
	}
	
	iconDetail = function() {
		urlConfig = {
			0:'创建一个新的课程',
			1:'编辑课程信息和内容',
			2:'删除此课程',
			3:'查看课程引用的课件',
			4:'课程编辑权限共享',
			5:'复制此课程，快速生成一个相似课程',
			6:'课程预览'
		}
		$("#ul_li_focus_events li").mouseover(function(){	
			$(this).each(function(){
				$("#text_tips").html(urlConfig[$(this).index()]);	
			})		
		})
		$("#ul_li_focus_events li").mouseout(function(){	
			$(this).each(function(){
				$("#text_tips").html("");	
			})		
		})
	}
	//初始化选择试卷创建方式窗口
	initContent = function() {
		var template = doT.template($("#chooseEntrance").text());
		popUpWindow(template(), "",340,360);
		$('.modal-header').css('background','#fff none repeat scroll 0 0').css('color','#41829a').css('border-bottom','0px');
		$('.modal-header .close').css('color','#41829a');
		$('#format1').hide();
		$('#format2').hide();
	}
	
	chooseModule = function(str){
		var id = $.trim($('#_item_id').val());
		if(str == 0){
			//课程管理
			go2Page("/course/setting.html","courseId="+id+"&courseType=1");
		}
		if(str == 1){
			//课程详细
			go2Page('/course/online/profile.html',"courseId="+id+"&isPublish=1");
		}
	};
	
	init();
});