<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content p_border p40">
		<div>
			<div class="video">
				<img src="<c:if test="${empty courseMap.course_cover_img}">${ctx}/resources/img/course/title_pic.png</c:if><c:if test="${not empty courseMap.course_cover_img}"> ${imgStatic}${courseMap.attach_url}/${courseMap.course_cover_img} </c:if>" alt="">
			</div>
			<div class="video_r">
				<h3 class="title pb10">${courseMap.course_name}</h3>
				<div class="mt10">
			        <ul class="course_des">
			          <li>评分：<div  
									<c:choose>
										<c:when test="${courseMap.course_star==2}">class="star2"</c:when>
										<c:when test="${courseMap.course_star==3}">class="star3"</c:when>
										<c:when test="${courseMap.course_star==4}">class="star4"</c:when>
										<c:when test="${courseMap.course_star==5}">class="star5"</c:when>
										<c:otherwise>class="star1"</c:otherwise>
									</c:choose>	>
			          		  </div></li>
			          <li>课程类型：<span>
			          	<c:if test="${courseMap.course_type==1}">
							线上课程
						</c:if>
						<c:if test="${courseMap.course_type==2}">
							线下课程
						</c:if>
						<c:if test="${courseMap.course_type==3}">
							直播课程
						</c:if>
			          	
			          </span></li>
			          <li>培训课时：<span>${courseMap.course_duration}</span></li>
			          <li>培训起至时间：<span>
			          	<c:choose>
			          		<c:when test="${not empty courseMap.start_time &&  not empty courseMap.expire_time}">
			          			<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd"/>至<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${courseMap.expire_time}"/>
			          		</c:when>
			          		<c:when test="${empty courseMap.start_time &&  not empty courseMap.expire_time}">
			          			 _至<fmt:formatDate type="date" value="${courseMap.expire_time}" pattern="yyyy-MM-dd"/>
			          		</c:when>
			          		<c:when test="${not empty courseMap.start_time && empty courseMap.expire_time}">
			          			<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd"/>至_
			          		</c:when>
			          		<c:otherwise>
			          			未设置
			          		</c:otherwise>
			          	</c:choose>
			          		</span>
			          </li>
			          <li>拟定人数：<span>12?</span></li>
			          <li>学员总数：<span>6?</span></li>
			          <li>创建时间：<span> <fmt:formatDate type="date" value="${courseMap.create_time}"  pattern="yyyy-MM-dd"/></span></li>
			          <li>创建人：<span>${courseMap.nick_name}</span></li>
			          <li class="wb100">所属分类：
						<c:forEach items="${selectedClassificationList}" var="item">
							<em>${item.classification_name}</em>
						</c:forEach>
			          </li>
			        </ul>
			        <div class="text-right">
<%-- 			          <button type="button" class="btn_b mt20 mr20" onclick ="onEditCourseOnline('${courseMap.id}')">开始编辑</button>
 --%>			          <button type="button" class="btn_b mt20 mr20" onclick ="onCourseOnlineSetting('${courseMap.id}','1')">课程管理</button> 
			          <c:if test="${courseMap.is_publish!=1}">
			          	<button type="button" class="btn_b mt20" onclick="courseOnlinePublish()">发布</button>
			          </c:if>
			        </div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<input type="hidden" id="courseId" name="courseId" value="${courseMap.id}">
		
		<ul  id="myTab"  class="nav nav-tabs set_tab mt20" role="tablist" style="padding-left: 0px; background-color: #e1eaee ! important;">
			<li class="active" role="presentation">
				<a href="#jieshao" role="tab" data-toggle="tab">课程介绍</a></li>
			<li role="presentation">
				<a href="#dagang" role="tab" data-toggle="tab">课程大纲</a></li>
			<li role="presentation">
				<a href="#pingjia" role="tab" data-toggle="tab">课程评价</a></li>
		</ul>
		
		<div id="myTabContent" class="tab-content">
			<div class="pre1_con tab-pane active" id="jieshao" style="padding: 60px;">
				<h3 class="kc_title">
					<span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程简介
				</h3>
				<div class="kc_des mt20">${courseMap.course_description}</div>
				<h3 class="kc_title mt20">
					<span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程内容
				</h3>
				<div class="kc_des mt20">adsfadsf</div>
				<h3 class="kc_title mt20">
					<span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程须知
				</h3>
				<div class="kc_des mt20">${courseMap.course_infomartion}</div>


			</div>
			<div class="pre1_con tab-pane" id="dagang">
				courses_dagang.html</div>
			<div class="pre1_con tab-pane" id="pingjia">3</div>



		</div>

	</div>
	<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<script type="text/javascript">
		require([ 'modules/course/course_common','modules/course/course_online_detail' ], function(obj) {
		});
	</script>
</body>
</html>