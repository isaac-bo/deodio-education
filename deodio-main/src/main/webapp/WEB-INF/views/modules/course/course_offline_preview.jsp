<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body style="overflow-y: auto;font-size:13px">
	
	<div class="content p40">
	    <div class="course_topbox">
	        <div class="left_pic">
	        	<%-- <img src="${ctx}/resources/img/viewer/course_pic.jpg" alt=""> --%>
	        	<img src="<c:if test="${empty courseMap.course_cover_img}">${ctx}/resources/img/viewer/course_pic.jpg</c:if><c:if test="${not empty courseMap.courseOfflineProfileInfo.course_cover_img}"> ${imgStatic}${courseMap.courseOfflineProfileInfo.attach_url}/${courseMap.courseOfflineProfileInfo.course_cover_img} </c:if>" alt="">
	        </div>
	        
	        <div class="right_box">
	            <h3><span><strong>课程名称：</strong></span>${courseMap.course_name}</h3>
	            <ul class="course_icon">
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon4.png" alt=""></a></li>
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon3.png" alt=""></a></li>
	                <li><a href="javascript:void(0);" onclick="javascript:toggleCourseFavor()"><img src="${ctx}/resources/img/viewer/course_icon2.png" alt="收藏"></a></li>
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon1.png" alt=""></a></li>
	            </ul>
	            <div class="number_people">
	                <span class="user_icon"><img src="${ctx}/resources/img/viewer/user_icon.png" alt=""></span><span>${courseMap.traineeCount}人学习</span>
	                <c:if test="${courseMap.starNum == 0}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：暂无评分</c:if>
	                <c:if test="${courseMap.starNum == 1}">${courseMap.traineeCount}<div class="star1"></div></c:if>	
	                <c:if test="${courseMap.starNum == 2}">${courseMap.traineeCount}<div class="star2"></div></c:if>
	                <c:if test="${courseMap.starNum == 3}">${courseMap.traineeCount}<div class="star3"></div></c:if>
	                <c:if test="${courseMap.starNum == 4}">${courseMap.traineeCount}<div class="star4"></div></c:if>
	                <c:if test="${courseMap.starNum == 5}">${courseMap.traineeCount}<div class="star5"></div></c:if>
	                <span>(${courseMap.appraiseCount}人评价)</span>
	            </div>
	            <c:if test="${courseMap.trainee_num != 1}">
					<div class="course_description">
	                	<strong>课程描述：</strong>${courseMap.course_description}
	            	</div>	            
	            </c:if>
	            <%--  <c:if test="${courseMap.start_time != null}"> --%>
	             <fmt:formatDate type="date" value="${courseMap.start_time}" var="enrollStartTime"   pattern="yyyy-MM-dd"/>
				 <fmt:formatDate type="date" value="${courseMap.expire_time}"  var="enrollExpireDate" pattern="yyyy-MM-dd"/>	
					<div class="course_description pull-left" style="width:50%;">
	                	<strong>报名起止时间：</strong><c:if test="${courseMap.start_time != null}">${enrollStartTime}至${enrollExpireDate}</c:if><c:if test="${courseMap.start_time == null}">未设置</c:if>
	            	</div>	            
	           <%--  </c:if>
	             <c:if test="${courseMap.trainStartTime != null}"> --%>
					<div class="course_description pull-left" style="width:50%;">
	                	<strong>培训起止时间：</strong>${courseMap.trainStartTime}至${courseMap.trainEndTime}
	            	</div>	            
	            <%-- </c:if>
	             <c:if test="${courseMap.locationName != null}"> --%>
					<div class="course_description pull-left" style="width:50%;">
	                	<strong>培训城市：</strong>${courseMap.locationName}
	            	</div>	            
	          <%--   </c:if>
	             <c:if test="${courseMap.trainee_num != null}"> --%>
					<div class="course_description pull-left" style="width:50%;">
	                	<strong>拟定人数：</strong>${courseMap.course_trainee_num}
	            	</div>	            
	          <%--   </c:if> --%>
                 <!--  <button class="btn btn_blue" type="button" disabled="disabled">我要报名</button>
                  <button class="btn btn_blue" type="button" disabled="disabled" style="color:red;">我要退课</button> -->
                  </div>
	            <input type="hidden" id="hiddenGroupId" value="${courseMap.groupId}">
	            <input type="hidden" id="hiddenCourseId" value="${courseMap.id}">
	            <input type="hidden" id="hiddenHasVideo" value="${courseMap.hasVideo}">
	            <input type="hidden" id="hiddenHasFavor" value="${courseMap.hasFavor}">
	            <div class="clearfix"></div>
	        </div>

	    <div class=" mb20">
	        <ul class="pull-left tab_bar_left tab_area nav nav-tabs set_tab" role="tablist" style="background-color:#FFFFFF ">
	            <li class="active"><a href="#frontPage" data-toggle="tab" role="tab" >主页</a></li>
	            	<!-- 学员未选择禁止以下tab -->
            	<!-- <li><a href="#discussPage" data-toggle="tab">讨论区</a></li> -->
            	<li><a href="#relatedPage" data-toggle="tab" role="tab" >作业与考试</a></li>
	        </ul>
	        <div class="clearfix"></div>
	    </div>
	    <div class="discuss_box">
	        <div class="discuss_left">
	        </div>
	        <div class="discuss_right">
	            <h3 class="h3_title">公告</h3>
	            <input type="hidden" id="hiddenCourseNoticePageNo">
	            <ul class="newslist">
	            </ul>
	            <!-- <div class="text-center" id="btn_more">
	                <button class="btn btm_more" type="button">查看更多</button>
	            </div> -->
	        </div>
	        <div class="clearfix"></div>
	    </div>  
	</div>
	
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	
<%-- 	<%@ include file="/WEB-INF/views/modules/courseselect/course_remark_tem.jsp"%> --%>
	
	<script id = "courser_viewer_front" type="text/x-dot-template">
		<h3 class="h3_title">目录</h3>
	    <ul class="contents_list">
			{{~it.data:v:index}}
				{{? index == 0}}
				{{for(var i = 0; i< v.course_train_times; i++){ }}
	        	<li>
	            	<span class="lession text-right">课时{{= i+1 }}</span>
	            	<span class="round_b" style="background:#fff;">
							<em class="round_con" style=""></em>
					</span>
					{{~it.data:a:zndex}}
					{{? a.item_step_no == i+1 && a.item_type == 0}}
	            	<h4 class="h4_title">
						<a>
							<em class="green">
									【课程】
							</em>{{=isNullFormat(a.item_train_name)}}
						</a>
					</h4>
					<button class="btn pull-right">{{? isNullFormat(a.item_trainers).length ==0 }}0{{??}}{{=isNullFormat(a.item_trainers)}}{{?}}/{{=a.trainee_join_num}}</button>
					<span class="pull-right green mr10">{{=dateFormat(a.start_time)}} ~ {{=dateFormat(a.expire_time)}}</span>
					{{?}}
					{{~}}
	            	<div class="line"></div>
	        	</li>

				{{~ it.data:c:jndex}}
					{{? c.item_step_no == i+1 && c.item_type != 0}}
				<li>
	            	<span class="lession text-right"> </span>
	            	<span class="round_b" style="background:#fff;">
							<em class="round_con" style="width:4px;border-radius:50% 0 0 50%;"></em>
					</span>
	            	<h4 class="h4_title">
						<a>
							<em class="green">
								{{? c.item_type == '1'}}
									【活动】
								{{?}}
							</em>{{=isNullFormat(c.item_train_name)}}
						</a>
					</h4>
	            	<div class="line"></div>
	        	</li>
					{{?}}
				{{~}}

				{{ } }}
				{{?}}
	        {{~}}
	    </ul>

	</script>
	
	
	
	<script id = "courser_viewer_related" type="text/x-dot-template">
		<h3 class="h3_title">目录</h3>
	    <ul class="contents_list">
			{{? it.data.length > 0}}
			{{~it.data:v:index}}
	        	<li>
					<span class="lession text-right">内容{{= index+1 }}</span>
	            	<span class="round_b" style="background:#fff;">
							<em class="round_con" style=""></em>
					</span>
	            	<h4 class="h4_title">
						<em class="green">
							{{? v.item_type == '1'}}
								【课后作业】
							{{?? v.item_type == '2'}}
								【毕业考试】
							{{?? v.item_type == '3'}}
								【问卷调查】
							{{??}}
								【附		件】
							{{?}}
						</em>{{=isNullFormat(v.item_name)}}
					</h4>
					<time class="pull-right">
						{{? '' != isNullFormat(v.item_finish_time)}}
							截止日期：{{=dateFormat(v.item_finish_time)}}
						{{?}}
					</time>
							
	            	<div class="line"></div>
					<input type="hidden" name="itemId" value="{{=isNullFormat(v.item_id)}}">
	        	</li>
	        {{~}}
			{{??}}
				<div class="null_table" style="border:0px;">NULL<span class="null_t">暂时没有相关数据</span></div>
			{{?}}
	    </ul>

	</script>
	
	<script id = "courser_viewer_discuss_reply_data_template" type="text/x-dot-template">
		{{~it.data:vitem:vindex}}
			<div class="replaybox">
            	<div class="replaybox_top">
         			{{? isNullFormat(vitem.userIcon) == ''}}
						<img src="${ctx}/resources/img/viewer/user_pho.png" alt="" class="user_pho">
					{{??}}
						<img src="${imgStatic}{{=vitem.attachUrl}}{{=vitem.userIcon}}" alt="" class="user_pho">
					{{?}}
                    <span class="ml10">{{=isNullFormat(vitem.nickName)}}</span>
                    <time class="ml10">{{=dateFormat1(vitem.createTime)}}</time>
        		</div>
                <div class="replay_con">{{=isNullFormat(vitem.discussionText)}}</div>
      		</div>
		{{~}}
	</script>
	
	<script id = "courser_viewer_newslist_data_template" type="text/x-dot-template">
		{{~it.data.dataList:v:index}}
			<li class="newslistItem">
				<div name="noticeTxt">
					{{=showPartNoticeTxt(v.notice_content)}}
				</div>
				<input name="hiddenFullNoticeTxt" value="{{=isNullFormat(v.notice_content)}}" type="hidden">
	        	<div class="time_b">
	        		<time class="pull-left">{{=dateFormat1(v.notice_publish_time)}}</time>
					{{? isShowPartNoticeTxt(v.notice_content) }}
						<a href="javascript:void(0)" name="showFullTxtBtn" onclick="javascript:onShowNoticeTxt(this,1)" class="pull-right">详细&gt;&gt;</a>
						<a href="javascript:void(0)" name="showPartTxtBtn" hidden onclick="javascript:onShowNoticeTxt(this,0)" class="pull-right">收起&gt;&gt;</a>
					{{?}}
	        	</div>
	    	</li>
		{{~}}
	 </script>
	 
	<script id="course_viewer_course_relate_to_list_data_template" type="text/x-dot-template">
		{{~it.data:v:index}}
		<div class="col-md-3 col-xs-6 col-sm-6">
        	<div class="kc_box">
                  <a href="${ctx}/course/course_viewer/detail.html?courseId={{=v.id}}&groupId=${courseMap.groupId}">
						{{? !v.course_cover_img}}
							<img src="${ctx}/resources/img/course/p_pic.jpg" alt="">
						{{??}}
							<img src="${imgStatic}{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.course_cover_img)}}"   alt="">		
						{{?}}
				  </a>
                  <ul class="kc_box_item">
                       <li>
                          <span class="left_title">课程名称：</span>{{=isNullFormat(v.course_name)}}
                       </li>
                       <li>
                          <span class="left_title">讲师：</span>{{=isNullFormat(v.nick_name)}}
                       </li>
                       <li>6 人学习 &nbsp; | &nbsp; 26? 人预览</li>
                   </ul>
             	  <div class="kc_box_btn text-right">
                  	<a href="${ctx}/course/course_viewer/detail.html?courseId={{=v.id}}&groupId=${courseMap.groupId}"><button type="button" class="btn btn_green">OPEN</button></a>
                 	<button type="button" class="btn btn_blue">简介</button>
             	  </div>
             </div>
          </div>
		{{~}}
	</script>
	
	<script type="text/javascript">
		require([ 'modules/course/course_offline_preview' ], function(obj) {
		}); 
	</script>
</body>
</html>