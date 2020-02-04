	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils.menu","utils","jquery.scrolltofixed","jquery.scroll.pagination",
	        "bootstrap.select","jquery.base"], function($,tree,cookie,list,doT,paging,menu) {
		var userId = cookie.getCookie('CUID');
		var ulliConfig = {
				0 : '创建线下课程',
				1 : '编辑线下课程',
				2 : '删除此线下课程',
				3 : '分享此线下课程',
				4 : '复制此课程，快速生成一个相似课程',
				5 : '预览此线下课程',
				6 : '审批线下课程注册用户'
			}
		/**
		 * 获取检索条件  标签
		 */
		getTagIdList = function(){
			var tagIdList = [];
			$.each($('.on'),function(i,item){
				if($(item).prop("id")!=""){
					tagIdList.push($(item).prop("id"));
				}
			})
			return tagIdList;
		}
		
		// 跳转到线下课程设置页面
		onCourseOfflineSetting = function(id, courseType) {
			gotoCourseOfflineManagerPage('/course/setting.html', 'courseId=' + id,
					'courseType=' + courseType);
		};
		var gotoCourseOfflineManagerPage = function(url, param, param1) {
			if (param && param1) {
				window.location.href = ctx + url + "?" + param + "&" + param1
						+ "&r=" + Math.floor(Math.random() * 100);
			} else {
				window.location.href = ctx + url + "?r="
						+ Math.floor(Math.random() * 100);
			}
		};
		
		
		// 发布课程
		courseOfflinePublish = function(id,setTimes,isPublic) {
			var trainTimes = 0;
			var isPublic=$("#isPublic").val();
			if($("#_item_is_edit").val() == 1) {
				alertMsg('该线下课程正在被编辑，不允许发布');
				return false;
			}
			var url = "/course/offline/CourseOfflineItemCount.html", params = {
				courseId : id
			}
			postAjaxRequest(url, params, function(result) {
				if (result.status == 1) {
					trainTimes = result.data;
					if (trainTimes != setTimes) {
						alertMsg("培训次数与设置不符请确认!");
						return false;
					} else {
						if(isPublic==1){
							var courseGroupItemType = 42;
							$("#publishModal").modal("show");
							$('#group_container_type').val(courseGroupItemType);
							$('#item_id').val($("#courseId").val());
							}else{				
								publishItemFunc();
								go2Page("/course/offline/list.html");
							}
					}
				}
			});

		};
		// 取消发布课程
		cancelCourseOfflinePublish = function(id) {
			if(validateCourseEdit()){
				alertMsg("距离课程开始日期不足5天，不能取消发布!");
				return;
			}else{
			confirmMsg("确定要取消发布当前课程?", function() {
				var url = "/course/cancelPublish.html", params = {
					courseId : id
				}
				postAjaxRequest(url, params, function(result) {
					if (result.status == 1) {
						alertMsg("取消发布成功",function(){
							go2Page("/course/offline/list.html");
						});
						
					} else {
						alertMsg("取消发布失败");
					}
				});
			});
			}
		}
			
		/**
		 * 获取检索条件  分类
		 */
		getClassificationIdList = function(){
			var classificationIdList = [];
			$.each($(".filter_condition").children().children().children('.top-tabox-fl'),function(i,item){
				classificationIdList.push($(item).prop("id"));
			})
			
			return classificationIdList;
		}
		
		//初始化函数
	   init = function(){
			list.init({
						tablePanel:'course_offline_panle',
						tableDataTemplate:'course_offline_data_template',
						loadDataListUrl:'/course/offline/load_data_by_classifications_and_tags.html',
						isChildrenClassificationUrl:'/classification/num_classification.html',
						itemType:22
					 },{
						 paginationFunc:paginationFunc
					 });
//			list.onLoadDataList(1,[],[]);
//			//页码设置
//			$("#hid_default_course_offline_page").val(2);
			reloadPageData();
			
			//实现滚动加载列表
			$('#courseOfflineContent').scrollPagination({
				'postUrl' : ctx + '/course/offline/load_data_by_classifications_and_tags.html',
				'postParams' : {
					pageNo : 1,
					pageSize:6,
					classificationIdList:[],
					tagIdList:[],
					keyword : $.trim($('#keyWords').val())
				},
				'scrollTarget' : $(window),
				'callBackLoad' : function(data) {
					loadCourseOfflineDataList(data);
				},
				'beforeLoad' : function() {
					this.postParams.pageNo = $("#hid_default_course_offline_page").val();
					this.postParams.pageSize = 6;
					this.postParams.classificationIdList = getClassificationIdList();
					this.postParams.tagIdList = getTagIdList();
					this.postParams.keyword = $.trim($('#keyWords').val());
				}
			});
		}
		
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
			$("#hid_default_course_offline_page").val(2);
			$('#courseCopyModal').on('hidden.bs.modal', function () {
				$('body').removeClass('modal-open').css('overflow-y','auto');
			});
		}

	   searchData = function() {
		  loadPageDataList(1);
		  var speed=200;//滑动的速度
		  $('body,html').animate({ scrollTop: 0 }, speed);
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
			var keyword = $.trim($('#keyWords').val());
			list.onLoadDataList(pageNo,classificationIdList,tagIdList,keyword,function(){
				$('.null_table').css('margin','20px');
				if($('.null_table').length>0){
					$('.con-corner').css('margin-top','5px');
				}
			});
		}
		//滚动加载线下课程列表的回调函数
		loadCourseOfflineDataList = function(data) {
			var template = doT.template($("#course_offline_data_template").text());
			if ($("#courseOfflineContent").size() == 0) {
				$('#courseOfflineContent').append(template({
					"data" : data.dataList,
					"currentPage":data.currePage
				}));
			} else {
				$('#courseOfflineContent ul').append(template({
					"data" : data.dataList,
					"currentPage" : data.currePage
				}));
			}
			if (data.dataList.length != 0) {
				var finalPageNo = Number($("#hid_default_course_offline_page").val()) + 1;
				$("#hid_default_course_offline_page").val(finalPageNo);
			}
		};
		
		onFilterByTags = function(obj){
			list.onFilterByTags(obj);
		};
		
		onDetail = function(id){
			//go2Page('/course/offline/detail.html',"courseId="+id);
			var url = "/course/offline/detail.html",
			params = {
				courseId : id
			};
			postAjaxRequest(url, params, function(result){
				var template = doT.template($("#offline_detail_template").text());
				popUpPreviewWindow(template({data:result.data}),"课程简介",1000,600);
				
				 $('#myTab a:first').tab('show');//初始化显示哪个tab
			     $('#myTab a').click(function (e) {
			    	 e.preventDefault();//阻止a链接的跳转行为
			    	 $(this).tab('show');//显示当前选中的链接及关联的content
			     });
			});
		};
		
		
		onToApprovalOfflineCourse = function(id){
			go2Page('/course/offline/approval.html','courseId='+$('#_item_id').val());
		};
		
		
		onToOfflineCourseDetail = function(){
			
			var courseId = $.trim($('#_item_id').val());
			if(!courseId){
				alertMsg('请选择课程！');
				return;
			}
			
			go2Page('/course/offline/detail.html','courseId=' + courseId);
		}
		//跳转到编辑页面
		onToOfflineCourseEdit = function(){
			if($("#_item_is_edit").val() == 1) {
				alertMsg('该课程正在被编辑, 不允许同时编辑');
				return;
			}
			var id = $.trim($('#_item_id').val());
			initContent();
			if ($("#_item_is_publish").val() == 1) {
				$('#format1').show();
				if(validateCourseEdit()){//当前课程距离培训开始时间小于等于5天时，课程不能取消发布
					$('#format3').show();
					$('#format1').hide();
					$('#detailEntrance').attr('disabled',"true");
				}
			}
		};
		onToOfflineCourseDetail= function(){
			var courseId = $.trim($('#_item_id').val());
			if(!courseId){
				alertMsg('请选择课程！');
				return;
			}
			go2Page('/course/offline/detail.html','courseId=' + courseId);
		}
		onToPreviewOfflineCourse=function(courseId){
			   var courseId = $.trim($('#_item_id').val());
				if(!courseId){
					alertMsg('请选择课程！');
					return;
				}
	        	go2Page("/course/offline/preview.html","courseId="+courseId);
	        }
		validateDeleteStatus=function(courseId){
			var flag=true;
			var url="/course/offline/profile/baseInfo.html",data={
					 courseId :courseId
				};
				postAjaxRequest(url, data, function(result){
					if (result.data.isPublish== 1) {
						flag= false;
					}
				});
				return flag;
		}
		onDeleteOfflineCourse = function(){
			
			var courseId = $.trim($('#_item_id').val());
			if(!courseId){
				alertMsg('请选择课程！');
				return;
			}
			var course_owner =$("#_item_course_owner").val();
			if( course_owner!= userId&&course_owner.length>0) {
				if($("#_item_is_edit").val() == 1) {
					alertMsg('该线下课程正在被编辑，不允许删除');
					return false;
				} 
			} 
			var flag=validateDeleteStatus(courseId);
			if(!flag){
				alertMsg('请先取消发布该线下培训，再执行删除操作');
				return false;
			}
			confirmMsg("确定要删除当前课程吗?",function(){
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
		copyOfflineCourseDetail=function(){	
			var course_owner =$("#_item_course_owner").val();
			if( course_owner!= userId&&course_owner.length>0) {
				if($("#_item_is_edit").val() == 1) {
					alertMsg('该线下课程正在被编辑，不允许复制');
					return false;
				}else{
					 $("#courseCopyModal").modal("show");
				}
			}else{
			 $("#courseCopyModal").modal("show");
			}
		}
		submitNewCourse=function(){
			    if(validateExistName()){
			    	alertMsg("课程名称已存在!");
			    	return false;
			    }
				var url="/course/offline/profile/copy.html",data={
						courseId:$.trim($('#_item_id').val()),
						couseName:$.trim($('#newCourseName').val())
						
				};
				postAjaxRequest(url, data, function(result){
					go2Page("/course/offline/list.html");
				});
		}
		//验证课程名唯一
		validateExistName = function(){
			var url="/course/isExistName.html",data={
					courseName : $.trim($("#newCourseName").val()),
			};
			var flag = false;
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var courseId = $.trim($("#courseId").val());
					var count = result.data.length;
					if(count>0){
						if(courseId!=result.data[0].id){
							console.log("存在");
							flag = true;
						}
					}
				}
			},false);
			return flag;
		}
		onSelectOfflineCourese = function(id, courseName, isPublish, courseOwner, isEdit, createId, studentNum,startTime){
			menu.onSelectItem(id);
			$("#_item_course_name").val(courseName);
			$("#_item_is_publish").val(isPublish);
			$("#_item_course_owner").val(courseOwner);
			$("#_item_is_edit").val(isEdit);
			$("#_item_create_id").val(createId);
			$("#_item_student_num").val(studentNum);
			$("#_item_start_time").val(startTime);
			changeIcon(courseOwner, createId, isPublish);
		};
		
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
	
		//跳转到线上课程分享页面
		onShareOfflineCourse = function(){
			if($("#_item_is_publish").val() == 1) {
				alertMsg('该线下课程已发布,不允许分享');
				return;
			}
			var course_owner =$("#_item_course_owner").val();
			if( course_owner!= userId&&course_owner.length>0) {
				if($("#_item_is_edit").val() == 1) {
					alertMsg('该线下课程正在被编辑，不允许取消分享');
						return;
				} else {
					confirmMsg('该线下课程已被分享，确认取消分享吗', function(){
						onUpdateCourseOwner();
						//$("#courseOfflineShareModal").modal("show");
					});
				}
			} else {
				$("#courseOfflineShareModal").modal("show");
			}
		}
		$('#courseOfflineShareModal').on('hidden.bs.modal',function() {
					$('body').removeClass('modal-open').css('overflow-y', 'auto');

				});
		//更新课程拥有者
		onUpdateCourseOwner = function() {
			var url = "/course/offline/share_course_owner.html",
			data = {
				courseOwner : userId,
				courseId : $('#_item_id').val()
			};
			postAjaxRequest(url, data, function(result) {
				reloadPageData();
			},false);
		}
		$("#ul_li_focus_events li").mouseover(function() {
			$(this).each(function() {
				$("#text_tips").html(ulliConfig[$(this).index()]);
			})
		})
		$("#ul_li_focus_events li").mouseout(function(){	
				$(this).each(function(){
					$("#text_tips").html("");	
				})		
			})
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
					go2Page("/course/setting.html","courseId="+id+"&courseType=2");
				}
				if(str == 1){
					//课程详细
					go2Page('/course/offline/profile.html',"courseId="+id+"&isPublish="+$("_item_is_publish").val());
				}
			};
		validateCourseEdit = function() {
				var trainStartTime = $("#_item_start_time").val();
				var dateFrom = new Date(trainStartTime);
				var nowDay=dateFormat(new Date());
				var dateTo = new Date(nowDay);
				var diffStart = dateFrom.valueOf() - dateTo.valueOf();
				var diff_day_start = parseInt(diffStart / (1000 * 60 * 60 * 24));
				if (diff_day_start <=5) {
					return true;
				} else {
					return false;
				}
			};
		init();
	});