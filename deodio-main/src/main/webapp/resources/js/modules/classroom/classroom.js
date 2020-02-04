define(["jquery","utils.cookie","jquery.dot","pagination","utils.math","utils","jquery.base","jquery.validate","jquery.scrolltofixed"], function($,cookie,doT,paging,math) {
	
	
	var items = [];
	
	var _init = function(){
		
		_loadItems();
	};
	
	
	var _loadItems = function(){
		
	    var url="/classroom/itemList.html",data={
	    		courseId:$.trim($('#hiddenCourseId').val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				var template = doT.template($("#courser_viewer_course_content_list_data_template").text());
				$("#agenda").empty().append(template(result));
				items = result.data;
				_getItem($('#hiddenCourseItemIndex').val(),function(){
					toggleNextAnaPreBtn();
				});
			}else{
				alertMsg("抱歉，因为网络问题该Presentation发布失败，请重新发布！");
			}
		});
	};
	
	
	var _getItem = function(itemIndex,callback){
		$('#hiddenCourseItemIndex').val(itemIndex);
		var li = $('.contents_list li').removeClass('active').eq(itemIndex-1);
		li.addClass('active');
		
		for(index = 0; index < items.length; index++){
			if(index == itemIndex-1){
				$('.item_name').html(items[index].item_name);
				if(items[index].item_type == 0){
					$('.item_type').html('章节<em>'+itemIndex+'</em>');
					$('#presentationId').val(items[index].item_id);
					$('#hiddenCourseItemId').val(items[index].item_id);
					$('#quizSurvey_learning_type').hide();
					$('#presentation_learning_type').show();
					
					if(items[index].item_type_detail == 2){
						var template = doT.template($("#course_classroom_presentation_sync_data_template").text());
						$(".video_con").empty().append(template());
						$('.right_bar li').css('width','33.3%');
						$('.sync_comment_button').show();
						$('.comments_tab').show();
						$('.items_tab').hide();
						
						
						initPresentationSyncItems();
					}else if(items[index].item_type_detail == 0){
						var template = doT.template($("#course_classroom_presentation_scrom_data_template").text());
						$(".video_con").empty().append(template());
						$('.right_bar li').css('width','33.3%');
						$('.sync_comment_button').hide();
						$('.comments_tab').hide();
						$('.items_tab').show();
						
						initPresentationScromItems();
					}else if(items[index].item_type_detail == 1){
						var template = doT.template($("#course_classroom_presentation_pack_data_template").text());
						$(".video_con").empty().append(template());
						$('.right_bar li').css('width','33.3%');
						$('.sync_comment_button').hide();
						$('.comments_tab').hide();
						$('.items_tab').show();
						
						initPresentationPackItems();
						
					}else if(items[index].item_type_detail == 3){
						
						$('.right_bar li').css('width','50%');
						$('.sync_comment_button').hide();
						$('.comments_tab').hide();
						$('.items_tab').hide();
						
						initPresentationExternalItems(function(data){
							var template = doT.template($("#course_classroom_presentation_external_data_template").text());
							$(".video_con").empty().append(template(data));
						});
					}
					
				}else if(items[index].item_type == 1){
					$('.item_type').html('测试<em>'+itemIndex+'</em>');
					$('#quizId').val(items[index].item_id);
					$('#hiddenCourseItemId').val(items[index].item_id);
					
					$('.right_bar li').css('width','50%');
					$('.sync_comment_button').hide();
					$('.comments_tab').hide();
					$('.items_tab').hide();
					
					$('#quizSurvey_learning_type').show();
					$('#presentation_learning_type').hide();
					
					initQuizItems(function(data){
						var template = doT.template($("#course_classroom_quiz_data_template").text());
						$(".video_con").empty().append(template(data));
						
						$("[name^='quiz_']").each(function(){
				    		 var  name = $(this).attr("name");
				    		 customInput(name);
				    	 });
					});
//					
				}else if(items[index].item_type == 2){
					$('.item_type').html('调查<em>'+itemIndex+'</em>');
					$('#surveyId').val(items[index].item_id);
					$('#hiddenCourseItemId').val(items[index].item_id);
					
					$('.right_bar li').css('width','50%');
					$('.sync_comment_button').hide();
					$('.comments_tab').hide();
					$('.items_tab').hide();
					
					$('#quizSurvey_learning_type').show();
					$('#presentation_learning_type').hide();
					
					initSurveyItems(function(data){
						var template = doT.template($("#course_classroom_survey_data_template").text());
						$(".video_con").empty().append(template(data));
						
						$("[name^='survey_']").each(function(){
				    		 var  name = $(this).attr("name");
				    		 customInput(name);
				    	 });
					});
				}
				
			}
		}
		
		if(callback != undefined){
			callback();
		}
	
	};
	
	var _slideItemUp = function(){
		var _leftContainerCss_BEG = {top:'-900px'};
		$(".item_container").animate(_leftContainerCss_BEG, "normal",function(){
			$(".item_container").css('top',$('.video_bg').height() + 'px');
			_getItem(parseInt($('#hiddenCourseItemIndex').val()) + 1,function(){
				toggleNextAnaPreBtn();
				$(".item_container").animate({top:'0px'}, "normal");
			});
			
		});
//		$('.btn_ti').show();
//		$(".btm_edit_bar").show();
//		$('#comment_content').val('');
	};
	
	var _slideItemDown = function(){
		var _leftContainerCss_BEG = {top:$('.video_bg').height() + 'px'};
		$(".item_container").animate(_leftContainerCss_BEG, "normal",function(){
			$(".item_container").css('top','-900px');
			_getItem(parseInt($('#hiddenCourseItemIndex').val()) - 1,function(){
				toggleNextAnaPreBtn();
				$(".item_container").animate({top:'0px'}, "normal");
			});
			
		});
			
	};
	
	
	//查询前一个课程课时
	onPreItem = function(){
		_slideItemDown();
		
	};
	//查询后一个课程课时
	onNextItem = function(){
		_slideItemUp();
	};
	
	
	var toggleNextAnaPreBtn = function(){
		var currentItemIndex = $("#hiddenCourseItemIndex").val();
		var itemCount = $("#hiddenItemsCount").val(); 
		if(currentItemIndex <= 1){
			$("#btnPreItem").hide();
		}else if(itemCount <= currentItemIndex){
			$("#btnNextItem").hide();
		}else{
			$("#btnPreItem").show();
			$("#btnNextItem").show();
		}
	}
	
	//获取课程指定偏移量的课时信息
	var _loadCourseItemOffset = function(queryParams){
		var url="/course/load_course_item_by_offset.html",data = queryParams;
		postAjaxRequest(url, data, function(result){
			debugger;
			if(result.status == 1){
				//数据加载成功跳转到对应页面
				var targetItemId = result.data.item_id;
				var targetItemType = result.data.item_type;
				var targetItemIndex = result.data.item_sort;
				var targetCourseId = result.data.course_id;
				_toPageUrl(targetItemId,targetItemType,targetItemIndex,targetCourseId);
			}else{
				alertMsg("课程章节信息加载失败！");
			}
		});
	}
	
	goBack = function(){
		var courseId = $("#hiddenCourseId").val();
		var groupId = $("#hiddenGroupId").val();
		go2Page('/course/course_viewer/detail.html','courseId=' + courseId + '&groupId=' + groupId);
	}
	
	
	_init();
	
});