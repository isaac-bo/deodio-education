<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ taglib uri="/WEB-INF/views/tags/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/views/tags/fn.tld" prefix="fn"%>

<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

	<div class="content g_border">

		<div class="g_right" style="margin-left: 0px;">
			<h3 class="r_title r_title_bg7">注册调查问卷</h3>
			<p class="p20 f14">该用户填写的表单情况</p>
			<div class="p20">
				<div class="jr_l_box pull-left" id="draggableContent"
					style="min-height: 500px; min-width: 1100px;">
					<c:forEach var="item" items="${surveyList}" varStatus="status">
						<c:choose>
							<c:when test="${item.type==1}">
								<div class="timu mt20" surveytype='1'>
									<h3 class="edit">
										<input type="text" placeholder="请输入问题" class="form-control"
											value="${item.survey_subject_name}">
									</h3>
									<c:set var="itemOptions"
										value="${fn:split(item.item_options, '#')}" />
									<c:set var="checkFlagOptions"
										value="${fn:split(item.check_flag, '#')}" />
									<c:forEach var="item" items="${itemOptions}" varStatus="vs">
										<div class="ti">
											<div class="radio-group pull-left pt10">
												<c:if test="${checkFlagOptions[vs.index]==0}">
													<input type="radio" name="survey${status.index}"
														id="${status.index}_${vs.index}" value="1">
													<label class="radio-2" for="${status.index}_${vs.index}"></label>
												</c:if>
												<c:if test="${checkFlagOptions[vs.index]==1}">
													<input type="radio" name="survey${status.index}"
														id="${status.index}_${vs.index}" value="1">
													<label class="radio-2 checked"
														for="${status.index}_${vs.index}"></label>
												</c:if>

											</div>

											<div class="ti_input_w pull-left ml20">
												<input type="text" class="form-control" value="${item}">
												<input type="hidden" value="${item}">
											</div>
										</div>
									</c:forEach>
								</div>
							</c:when>
							<c:when test="${item.type==2}">
								<div class="timu mt20" surveytype='2'>
									<h3 class="edit">
										<button type="button" onclick="removeQuestion(this);"
											class="del_icon pull-right"></button>
										<input type="text" placeholder="请输入问题" class="form-control"
											value="${item.survey_subject_name}">
									</h3>
									<c:set var="itemOptions"
										value="${fn:split(item.item_options, '#')}" />
									<c:set var="checkFlagOptions"
										value="${fn:split(item.check_flag, '#')}" />
									<c:forEach var="item" items="${itemOptions}" varStatus="vs">
										<div class="ti">
											<div class="checkbox-group pull-left pt10"> 
												<c:if test="${checkFlagOptions[vs.index]==0}">
													<input type="checkbox" name="survey${status.index}"
														id="${status.index}_${vs.index}" value="1">
													<label class="checkbox-2" for="${status.index}_${vs.index}"></label>
												</c:if>
												<c:if test="${checkFlagOptions[vs.index]==1}">
													<input type="checkbox" name="survey${status.index}"
														id="${status.index}_${vs.index}" value="1">
													<label class="checkbox-2 checked"
														for="${status.index}_${vs.index}"></label>
												</c:if>
											</div>
											<div class="ti_input_w pull-left ml20">
												<input type="text" class="form-control" value="${item}">
											</div>
										</div>
									</c:forEach>
								</div>
							</c:when>
							<c:when test="${item.type==3}">
								<div class="timu mt20" surveytype='3'>
									<h3 class="edit">
										<input type="text" placeholder="请输入问题" class="form-control"
											value="${item.survey_subject_name}">
									</h3>
									<c:set var="checkFlagOptions"
										value="${fn:split(item.check_flag, '#')}" />
									<c:set var="itemOptions"
										value="${fn:split(item.item_options, '#')}" />
									<c:forEach var="item" items="${itemOptions}" varStatus="vs">
									<div class="ti">
										<div class="radio-group pull-left pt10">
											<c:if test="${checkFlagOptions[vs.index]==0}">
												<input type="radio" name="survey${status.index}"
													id="${status.index}_${vs.index}" value="1">
												<label class="radio-2" for="${status.index}_${vs.index}"></label>
											</c:if>
											<c:if test="${checkFlagOptions[vs.index]==1}">
												<input type="radio" name="survey${status.index}"
													id="${status.index}_${vs.index}" value="1">
												<label class="radio-2 checked" for="${status.index}_${vs.index}"></label>
											</c:if>
										</div>
										<div class="ti_input_w pull-left ml20">${item}</div>

									</div>
									</c:forEach>

								</div>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="form_line2"></div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>






	<script type="text/javascript">
		/* 		require(['modules/group/group','modules/group/group_form_user'],function(obj){
		
		 }); */
	</script>
</body>
</html>