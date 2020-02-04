<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/presentation/presentation.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<div class="content">
	<div class="pre_center" style="position: absolute;">
		<div class="con-mask"></div>
		<div class="con" style="min-height: 20px;">
			<div id="div_tables_bar" class="pre_left_top_fl pre_right_top_normal">
				<ul class="top_nav">
					<li class="no1_on" onclick="javascript:onGo2Step1();" style="cursor: pointer;">设置章节信息</li>
					<li class="no2_on" >设置章节相关</li>
					<li class="<c:if test="${isPreview == true}">no3_on</c:if><c:if test="${isPreview != true}">no3</c:if>">设置章节预览</li>
				</ul>
			
			</div>
			<!--pre_left_top_fl end-->
			<div class="clearfix"></div>
			<div class="con-corner"></div>
		</div>
	</div>
		
	<div class="con900" style="margin-top:120px;width:1100px;">
		<!-- <h3 class="mt20">第二步，章节基本内容</h3> -->
		<div class="basic hdn mt20">
		
			<div class="header-panel">
		
				<div class="pull-left">
				<c:choose>
					<c:when test="${empty presentation.attachUrl}">
					<img src="${ctx}/resources/img/presentation/video.png" alt="">
				
					</c:when>
					<c:otherwise>
						<img src="${imgStatic}/${presentation.attachUrl}/${presentation.generateName}" alt="">
					</c:otherwise>
				</c:choose>
				
				</div>
				<div class="right_con">
					<ul>
						<li>章节名称：<span>${presentation.presentationName}</span></li>
						<li>创建日期：<span><fmt:formatDate value="${presentation.createTime}" type="both" pattern="yyyy-MM-dd"/></span></li>
						<li>章节分类：
								<%-- <c:forEach items="${presentationTagsList}" var="item" varStatus="status">
									<c:if test="${status.index==0}">
										${item.tag_name}
									</c:if>
									<c:if test="${status.index!=0}">
										,${item.tag_name}
									</c:if>
								</c:forEach> --%>
								<span>${presentationMap.classification_name}</span>
						</li>
						<li>章节标签：
								<%-- <c:forEach items="${presentationTagsList}" var="item" varStatus="status">
									<c:if test="${status.index==0}">
										${item.tag_name}
									</c:if>
									<c:if test="${status.index!=0}">
										,${item.tag_name}
									</c:if>
								</c:forEach> --%>
								<span>${presentationMap.tag_name}</span>
						</li>
					</ul>
					<div class="f12">简介：<span>${presentationMap.presenationDescShortString}</span></div>
				</div>
			</div>
	
