<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ taglib uri="/WEB-INF/views/tags/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/views/tags/fn.tld" prefix="fn"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/group/group.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/deodio.header.footer.css">
<%@ page import="com.deodio.core.utils.CookieUtils"%>
<%@ page import="java.net.URLDecoder"%>
<header>
	<c:if test="${isHomepage == true}">
		<div class="header">
			<div class="top_search">
				<div class="search_b">
					<input type="text" class="s_input"><span
						class="icon-search">&nbsp;</span>
				</div>
				<div class="keywords">
					<span style="color: #a40000">热门关键词:</span><a href="">超长关键词</a><a
						href="">关键词</a><a href="">关键词</a><a href="">关键词</a>
				</div>
			</div>
		</div>
	</c:if>
	<nav class="nav header-nav">
		<div class="nav-title">
			<div class="logo"
				<c:if test="${isHomepage == true}"> style="background-color:#fff;" </c:if>
				<c:if test="${isHomepage != true}"> style="background-color:inherit;" </c:if>>
				<a href="${headerLinkUrl}" target="_blank"
					<c:if test="${isHomepage == true}"> style="background-image: url('${ctx}/resources/img/account/logo-1.png'); margin-top: 0px;" </c:if>
					<c:if test="${isHomepage != true}">style="background-image: url('${headerUrl}'); margin-top: 10px;" </c:if>></a>
				<div class="position-pic"></div>
			</div>
			<ul class="_head_menu">
				<input name="roleViewerId" type="hidden" value="${roleViewerId}">
				<input name="roleId" type="hidden" value="${userRoleId }">
			</ul>

		</div>

	</nav>
</header>

<div class="content g_border">

	<div class="g_right" style="margin-left: 0px;">
		<h3 class="r_title r_title_bg7">注册调查问卷</h3>
		<p class="p20 f14">请完成调查问卷并激活您的账号</p>
		<div class="p20">
			<div class="jr_l_box pull-left" id="draggableContent"
				style="min-height: 500px; min-width: 1100px;">
				<c:forEach var="item" items="${surveyList}" varStatus="status">
					<c:choose>
						<c:when test="${item.survey_subject_type==1}">
							<div class="timu mt20" surveytype='1'>
								<h3 class="edit">
									<input type="text" placeholder="请输入问题" class="form-control"
										value="${item.survey_subject}" readonly="readonly">
								</h3>
								<c:set var="itemOptions"
									value="${fn:split(item.item_options, '#')}" />
								<c:forEach var="item" items="${itemOptions}" varStatus="vs">
									<div class="ti">
										<div class="radio-group pull-left pt10">
											<input type="radio" name="survey${status.index}"
												id="${status.index}_${vs.index}" value="1" > <label
												class="radio-2" for="${status.index}_${vs.index}"></label>
										</div>

										<div class="ti_input_w pull-left ml20">
											<input type="text" class="form-control" value="${item}" readonly="readonly">
											<input type="hidden" value="${item}">
										</div>
									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:when test="${item.survey_subject_type==2}">
							<div class="timu mt20" surveytype='2'>
								<h3 class="edit">
									<input type="text" placeholder="请输入问题" class="form-control"
										value="${item.survey_subject}" readonly="readonly">
								</h3>
								<c:set var="itemOptions"
									value="${fn:split(item.item_options, '#')}" />
								<c:forEach var="item" items="${itemOptions}" varStatus="vs">
									<div class="ti">
										<div class="checkbox-group pull-left pt10">
											<input type="checkbox" name="survey${status.index}"
												id="${status.index}_${vs.index}" value="" > <label
												class="checkbox-2" for="${status.index}_${vs.index}"></label>
										</div>
										<div class="ti_input_w pull-left ml20">
											<input type="text" class="form-control" value="${item}" readonly="readonly">
											<input type="hidden" value="${item}">
										</div>
									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:when test="${item.survey_subject_type==3}">
							<div class="timu mt20" surveytype='3'>
								<h3 class="edit">
									<input type="text" placeholder="请输入问题" class="form-control"
										value="${item.survey_subject}" readonly="readonly">
								</h3>
								<div class="ti">
									<div class="radio-group pull-left pt10">
										<input type="radio" name="survey${status.index}"
											id="${status.index}_1" value="1" > <label
											class="radio-2" for="${status.index}_1"></label>
									</div>
									<div class="ti_input_w pull-left ml20">对
									 <input type="hidden" value="Yes">
									</div>

								</div>
								<div class="ti">
									<div class="radio-group pull-left pt10">
										<input type="radio" name="survey${status.index}"
											id="${status.index}_2" value="2"> <label
											class="radio-2" for="${status.index}_2"></label>
									</div>
									<div class="ti_input_w pull-left ml20">错
									 <input type="hidden" value="No">
									</div>
								</div>

							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
			<div class="clearfix"></div>
			<div class="btn_box">
				<button class="btn submit btn_160_36" type="button" onclick="submitBtn()">提交</button>
			</div>
		</div>
	</div>
</div>

<input type="hidden" value="${groupId}" id="groupId">
<input type="hidden" value="${um}" id="um">
<input type="hidden" value="${ur}" id="ur">
<input type="hidden" value="${userExist}" id="userExist">
	<input type="hidden" value="${roleLeaderId}" id="roleLeaderId">
	<input type="hidden" value="${roleViewerId}" id="roleViewerId">
	<input type="hidden" value="${roleCreatorId}" id="roleCreatorId">
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<script type="text/javascript">
	require([ 'modules/group/group', 'modules/group/group_form_register',
			'./modules/init' ], function(obj) {
	});
</script>
</body>
</html>