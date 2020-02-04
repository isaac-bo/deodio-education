	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils","jquery.scrolltofixed","jquery.scroll.pagination",
	        "bootstrap.select","jquery.base"], function($,tree,cookie,list,doT,paging) {

		var relateId = null;
		//默认公告字符串长度，若公告内容长度大于该长度，则显示简要字符串
		var txtLength = 50;
		var _init = function(){
			 $('.tab_area a').click(function (e) {
		          e.preventDefault();//阻止a链接的跳转行为
		          var href = $(this).attr("href");
		          switch(href){
			          case '#frontPage':  
			        	  //获取课程章节信息
			        	  onLoadCourseItemsInfo();break;
			          case '#discussPage': 
			        	  //获取课程评论信息
			        	  onLoadCourseDiscussionList();break;
			          case '#relatedPage':
			        	  //获取课程作业与考试
			        	  onLoadTraineeCourseRelatedInfo();
			        	  break;
			          default:alert(href);
		          }
		     });
			 //加载课程章节数据
			 onLoadCourseItemsInfo();
			 //加载课程公告数据
			 onLoadCourseNoticeDataList();
			 //默认设置第一个li为激活状态
			 $(".tab_area li").removeClass("active").first().addClass("active");
			 //加载必须课程
			 loadReleateCourseNecessaryDataList();
			 //加载推荐课程
			 loadRelatedCourseRecommandDataList();
			 
		};
		//生成课程章节信息页面
		var _generateFrontPageData = function(result){
		
			var template = doT.template($("#courser_viewer_front").text());
			$(".discuss_left").empty().append(template(result));
		};
		//生成课程评论数据页面
		var _generateDiscussPageData = function(result){
//			debugger;
			var template = doT.template($("#courser_viewer_discuss").text());
			$(".discuss_left").empty().append(template(result));
		};
		
		//生成课程章节信息页面
		var _generateRelatedPageData = function(result){
//			debugger;
			var template = doT.template($("#courser_viewer_related").text());
			$(".discuss_left").empty().append(template(result));
		};
		
		//生成课程评论回复数据页面
		var _generateDiscussReplyData = function(result,target){
//			debugger;
			var template = doT.template($("#courser_viewer_discuss_reply_data_template").text());
//			$(".discuss_left").empty().append(template(result));
			target.find(".replaybox").remove();
			target.append(template(result));
		}
		
		//生成课程公告数据页面
		var _generateNewslistPageData = function(result){
			var template = doT.template($("#courser_viewer_newslist_data_template").text());
			$(".newslist").empty().append(template(result));
			//当前页面数据不足页面数据个数时，‘查看更多’按钮隐藏
			var pageSize = Number(result.data.pageSize);
			var currentSize = Number(result.data.currentSize);
			if(pageSize > currentSize){
				$("#btn_more").hide();
			}
		}
		
		//获取课程章节信息
		var onLoadCourseItemsInfo = function(){
			var url="/course/online/item_list.html",data={
					courseId:$.trim($('#hiddenCourseId').val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					//加载数据
					_generateFrontPageData(result);
				}else{
					alertMsg("操作失败");
				}
			});
		}
		
		//获取学员课程 作业与考试数据
		var onLoadTraineeCourseRelatedInfo = function(){
			var url="/course/online/get_homework_quiz.html",data={
					courseId:$.trim($('#hiddenCourseId').val())
			};
			postAjaxRequest(url, data, function(result){
//				debugger;
				if(result.status == 1){
					//加载数据
					_generateRelatedPageData(result);
				}else{
					alertMsg("操作失败");
				}
			});
		}
		
		//获取课程评论信息
		var onLoadCourseDiscussionList = function(){
			var url="/course/discussion/load_list.html",data={
					courseId:$.trim($('#hiddenCourseId').val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
//					debugger;
					//加载数据
					_generateDiscussPageData(result);
				}else{
					alertMsg("操作失败");
				}
			});
		}
		//获取课程评论回复信息
		var onLoadCourseDiscussionReplys = function(discussionId,appendTarget){
			var url="/course/discussion/load_reply.html",data={
					discussionId:$.trim(discussionId)
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
//					debugger;
					//加载数据
					_generateDiscussReplyData(result,appendTarget);
				}else{
					alertMsg("数据加载失败");
				}
			});
		}
		
		var onLoadCourseNoticeDataList = function(){
			var courseNoticePageNo = $("#hiddenCourseNoticePageNo");
			var pageNo = courseNoticePageNo.val();
			if(!pageNo)
				pageNo = 1;
			var params = {
				pageNo:pageNo,
				courseId:$('#hiddenCourseId').val(),
			},url="/course/notice/load_list.html";
			postAjaxRequest(url, params, function(result){
//				debugger;
				if(result.status == 1){
					//生成页面数据
					_generateNewslistPageData(result);
					//更新pageNo
					courseNoticePageNo.val(pageNo);
				}
			});
		};
		
		
		//发布课程评论
		courseTraineeReply = function(){
			
			var discussionText = $.trim($('#courseDiscussionText').val());
			if(!discussionText ){
				alertMsg("内容不能为空！");
				return;
			}
			
			var courseDiscussion = {
				courseId:$.trim($('#hiddenCourseId').val()),
				discussionText:discussionText,
				relatedId:relateId
			};
			
			var url="/course/discussion/reply.html",data={
				discussionJson:JSON.stringify(courseDiscussion)
			};
			postAjaxRequest(url, data, function(result){
//				debugger;
				if(result.status == 1){
					alertMsg("发起讨论成功！");
					//重置页面数据
					resetPageData();
					//刷新课程评论数据
					onLoadCourseDiscussionList();
				}else{
					alertMsg("发起讨论失败！");
				}
			});
		}
		
		//回复课程评论
		onReplyCourseDiscussion = function(discussionId,e){
			//定位至回复窗口
			window.location.href = "#replyAnchor";
			//设置回复评论数据
			relateId =  discussionId;
		}
		
		//赞同课程评论
		onAgreeCourseDiscussion = function(discussionId,e){
			var _self = $(e);
			
			var url="/course/discussion/agree.html",data={
				discussionId:discussionId
			};
			postAjaxRequest(url, data, function(result){
//					debugger;
				if(result.status == 1){
					updateCourseDiscussionNum(_self,1);
				}else{
					alertMsg("发起讨论失败！");
				}
			});
		}
		
		var updateCourseDiscussionNum = function(e,addNum){
//			debugger;
			var regex = /([0-9]+)/;
			var nextElement = e.next();
			var spanVal= nextElement.text();
			var num = spanVal.match(regex)[0];
			var  replaceTxt = spanVal.replace(regex,Number(num) + addNum);
			nextElement.text(replaceTxt);
		}
		
		//重置页面数据
		var resetPageData = function(){
			//重置关联评论编号数据
			relateId = null;
			//清空页面回复文字
			$('#courseDiscussionText').val("");
		}
		
		//显示当前评论的所有回复
		onShowAllReplysInDiscussion = function(e,discussionId){
//			debugger;
			var _self = $(e);
			//获取当前评论元素
			var article = _self.parents("article");
			//显示所有回复
//			article.find(".replaybox").show();
			//按钮隐藏
			_self.hide();
			//重新加载课程评论所有回复
			onLoadCourseDiscussionReplys(discussionId,article);
		}
		
		courseHasVideo = function(){
			var hasVideo = $("#hiddenHasVideo").val();
			return 1 == hasVideo;
		}
		
		//显示截取超长字符串，若字符串长度不足，则显示原字符串
		showPartNoticeTxt = function(noticeContent){
			var partTxt = '';
			var noticeTxt = isNullFormat(noticeContent);
			if('' != noticeTxt && noticeTxt.length > txtLength){
				partTxt = noticeTxt.substr(0,txtLength);
				partTxt +='...';
			}else{
				partTxt = noticeTxt;
			}
			return partTxt;
		}
		
		//判断是否显示简要公告内容
		isShowPartNoticeTxt = function(noticeContent){
			var result = false;
			var noticeTxt = isNullFormat(noticeContent);
			if('' != noticeTxt && noticeTxt.length > txtLength){
				result = true;
			}
			return result;
		}
		
		
		//显示公告内容
		onShowNoticeTxt = function(detailBtnObj,isShowFullTxt){
			var detailBtn = $(detailBtnObj);
			var containerLi = detailBtn.parents("li.newslistItem");
			var noticeTxt = "";
			var fullTxt =  containerLi.find("[name='hiddenFullNoticeTxt']").val();
			if(isShowFullTxt){
				noticeTxt = fullTxt;
			}else{
				noticeTxt = showPartNoticeTxt(fullTxt);
			}
			//设置显示的文字内容
			containerLi.find("[name='noticeTxt']").empty().append(noticeTxt);
			//通知详细与收起按钮的显示与隐藏
			containerLi.find("[name='showFullTxtBtn']").toggle();
			containerLi.find("[name='showPartTxtBtn']").toggle();
		}
		
		/*推荐课程*/
		var loadRelatedCourseRecommandDataList = function(){
			loadRelatedCourseDataList("1","recommandCourse");
		}
		/*必须课程*/
		var loadReleateCourseNecessaryDataList = function(){
			loadRelatedCourseDataList("0","necessaryCourse");
		}
		
		//加载相关课程列表
		var loadRelatedCourseDataList = function(relateType,divContainerId,pageNo){
//			debugger;
			var params = {
				keywords:"",
				courseId:$('#hiddenCourseId').val(),
				relatedType:relateType
			},url="/course/related/load_list.html";
			postAjaxRequest(url, params, function(result){
				var template = doT.template($("#course_viewer_course_relate_to_list_data_template").text());
//				debugger;
				$("#" + divContainerId).empty().html(template(result));
			});
		};
		
		toggleCourseFavor = function(){
				var hasFavor = $("#hiddenHasFavor").val();
//				INSERT = 0;UPDATE = 1;DEL = 2;
				debugger;
				var operateType ;
				if(hasFavor == "true"){
					operateType = "2";
				}else{
					operateType = "0";
				}
				
				var courseInfo = {
						contentId:$('#hiddenCourseId').val(),
						operateType:operateType
				};
//				debugger;
				var url="/course/favorite/save.html",data={
						queryJson :JSON.stringify(courseInfo)
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						//更新页面ui
						$("#hiddenHasFavor").val(hasFavor=="true"?"false":"true")
						$("#favorContainer").empty();
						if("0" == operateType){
							$("#favorContainer").html("已关注");
						}
					}else{
						alertMsg("关注失败！");
					}
				});
		}
		
		onExam = function(examId, courseId, examType){
			debugger;
			go2Page('/quiz/exam/start.html',"examId="+examId+"&courseId="+courseId+"&examType="+examType);
		};
		
		_init();
		
	});