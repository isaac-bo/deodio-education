<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<script type="text/javascript">
	var trainerList = eval('${trainerListJson}');
	var locationList = eval('${locationListJson}');
</script>
<body>
    <form id="courseOfflineContentForm" method="post">
	<div class="content">
		<div class="pre_center" style="position: absolute;">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar"
					class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav">
						<li class="no1_on">设置课程信息</li>
						<li class="no2_on">设置课程内容</li>
					</ul>

				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>
		<div class="con900" style="margin-top: 120px;">
			<div class="content-header-panel">
				<div class="pull-left">
					<img style="height: 130px;"
						src="<c:if test="${empty courseMap.course_cover_img}">${ctx}/resources/img/course/title_pic.png</c:if><c:if test="${not empty courseMap.course_cover_img}"> ${imgStatic}${courseMap.attach_url}/${courseMap.course_cover_img} </c:if>"
						alt="">
				</div>
				<div class="pull-right right_content" style="width: 890px;">
					<h3 class="title pb10">${courseMap.course_name}</h3>
					<div class="mt10">
						<ul class="course_des">
							<li>评分：
								<div
									<c:choose>
										<c:when test="${courseMap.course_star==2}">class="star2"</c:when>
										<c:when test="${courseMap.course_star==3}">class="star3"</c:when>
										<c:when test="${courseMap.course_star==4}">class="star4"</c:when>
										<c:when test="${courseMap.course_star==5}">class="star5"</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>>
								</div>
							</li>
							<li>课程类型：<span> <c:if
										test="${courseMap.course_type==1}">
							线上课程
						</c:if> <c:if test="${courseMap.course_type==2}">
							线下课程
						</c:if> <c:if test="${courseMap.course_type==3}">
							直播课程
						</c:if>

							</span></li>
							<li>培训课时：<span>${courseMap.course_train_times}</span></li>
							<li>培训起至时间：<span> <c:choose>
										<c:when
											test="${not empty courseMap.start_time &&  not empty courseMap.expire_time}">
											<fmt:formatDate type="date" value="${courseMap.start_time}"
												pattern="yyyy-MM-dd" />至<fmt:formatDate type="date"
												pattern="yyyy-MM-dd" value="${courseMap.expire_time}" />
										</c:when>
										<c:when
											test="${empty courseMap.start_time &&  not empty courseMap.expire_time}">
			          			 _至<fmt:formatDate type="date"
												value="${courseMap.expire_time}" pattern="yyyy-MM-dd" />
										</c:when>
										<c:when
											test="${not empty courseMap.start_time && empty courseMap.expire_time}">
											<fmt:formatDate type="date" value="${courseMap.start_time}"
												pattern="yyyy-MM-dd" />至_
			          		</c:when>
										<c:otherwise>
			          			未设置
			          		</c:otherwise>
									</c:choose>
							</span>
							</li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="dg_title_b">
			<div class="dg_title_b" style="margin-left:150px;">
					<div class="zb_title">
					   <br>
						<span class="glyphicon glyphicon-info-sign mr10"></span>若要在前台“课程中心”中显示该培训，您必须设置该培训项目为可报名状态且在允许报名的时间内。
					</div>
					<div class="pxshijian mt20 pb10 wb100">
						<div class="checkbox-group" style="margin-top: 11px">
							<input type="checkbox" name="isRegisteDesk" id="isRegisteDesk"
								value="1" <c:if test="${courseRegisteRule.isRegisteDesk== 1}">checked="checked"</c:if>>
							<label class="checkbox-2" for="isRegisteDesk"></label>
						</div>
						<span class="w200" style="width: 220px ! important;">开启学员前台报名（需要审核）</span>
						<span class="w100 text-right">报名起至时间：</span>
						<fmt:formatDate type="date" value="${courseRegisteRule.startTime}" var="enrollStartTime"   pattern="yyyy-MM-dd"/>
				        <fmt:formatDate type="date" value="${courseRegisteRule.expireTime}"  var="enrollExpireDate" pattern="yyyy-MM-dd"/>
						<div class="w240 error-div" >
							<input type="text" class="form-control date_btn form_datetime"
								name="enrollStartTime" id="enrollStartTime" style="height: 36px;"
								aria-describedby="basic-addon1" check-type="dateYmd dateCompare"
								value="${enrollStartTime}">
						</div>
						<div class="line50"></div>
						<div class="w240 error-div">
							<input type="text" class="form-control date_btn form_datetime"
								name="enrollExpireTime" id="enrollExpireTime" style="height: 36px;"
								aria-describedby="basic-addon1" check-type="dateYmd dateCompare"
								value="${enrollExpireDate}" data-callback="validateEnrollExpireDate();" call-message="距离课程开始时间至少大于7天!">
						</div>
					   	<input type="hidden" value="${courseRegisteRule.id}"
							id="registedUsersId">
					</div>
			</div>
		</div>

	
		<div class="pt20" id="courseOfflineContent"
			style="margin-top: 20px; border-top: 1px solid rgb(225, 234, 238); margin-left: 40px; margin-right: 40px;">
			<div class="dagang_box">
				<!-- 次数列表  start-->
				<!-- 次数列表  end-->
				<div class="right_cont">
					<!-- a 上下翻页  start-->
					<!-- a 上下翻页  end-->
					<div class="dg_title_b">
						<h5 class="pull-left">
							<span class="current_step_no">第一次：</span><em></em>
						</h5>
						<div class="pxshijian"></div>
						<div class="clearfix"></div>

					</div>
					<div class="szkc mt20">
						<div class="sz_thead mt20">
							<span class="w400">起始时间</span> <span>事件</span> <span>培训讲师</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form_line2" style="width: 100%; margin-left: 5px;"></div>
			<input type="hidden" value="${courseMap.course_train_times}" id="courseTrainTimes"> 
			<input type="hidden" value="${courseMap.course_id}" id="courseId">
			<input type="hidden" value="1" id="stepNo"> 
			<input type="hidden" value="${courseMap.start_time}" id="trainStartTime"> 
			<input type="hidden" value="${courseMap.expire_time}" id="trainEndTime">
			<input type="hidden" value="${courseMap.course_rule}" id="courseRule">
			<input type="hidden" value="${courseMap.course_trainee_num}" id="traineeNum">
			<input type="hidden" value="${courseMap.is_public}" id="isPublic">
			<div class="btn_box">
				<button class="btn submit  btn_160_36" type="button"
					onClick="toCourseSetting('${courseMap.course_id}');">下一步</button>
			  <c:if test="${courseMap.create_id == currentUserId}">
				<button class="btn submit  btn_160_36" type="button"
					onClick="courseOfflinePublish()">发布</button>
				</c:if>
				<button class="btn cancel btn_160_36" type="button"
					onClick="goBack('${courseMap.course_id}');">取消</button>
			</div>
		</div>
			</div>
	</form>

	<%@ include file="/WEB-INF/views/modules/course/course_template.jsp"%>
	<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require([ 'modules/course/course_offline_content',
				'modules/course/course_common' ], function(obj) {
			window.onbeforeunload = updateEditState;
	        window.onhashchange = updateEditState;
		});
	</script>
</body>
</html>
