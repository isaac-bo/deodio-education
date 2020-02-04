<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<body>
	<div class="content">
		<div class="pre_center" style="position: absolute;">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar" class="pre_left_top_fl pre_right_top_normal">
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
	<form method="post" id="courseOnlineContentForm">
   	<div class="con900" style="margin-top:120px;">
		<div class="content-header-panel">
			<div class="pull-left">
				<img style="height: 130px;" src="<c:if test="${empty courseMap.course_cover_img}">${ctx}/resources/img/course/title_pic.png</c:if>
				<c:if test="${not empty courseMap.course_cover_img}">${imgStatic}${courseMap.attach_url}/${courseMap.course_cover_img}</c:if>" alt="">
			</div>
			<div class="pull-right right_content" style="width:890px;">
				<h3 class="title pb10">${courseMap.course_name}</h3>
				<div class="mt10">
			        <ul class="course_des">
			          <li>课程类型：<span>
			          	<c:if test="${courseMap.course_type==1}">
							线上课程
						</c:if>
						<c:if test="${courseMap.course_type==2}">
							线下课程
						</c:if>
						<c:if test="${courseMap.course_type==3}">
							直播课程
						</c:if></span>
			          </li>
			          <li>培训课时 <span id="period"></span></li>
			          <li>有效起至时间：<span>
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
			          <%-- <li>所属分类：
						<c:forEach items="${classificationList}" var="item">
							<em>${item.classification_name}</em>
						</c:forEach>
			          </li>
			          <li>标签：
						<c:forEach items="${tagList}" var="item">
							<em>${item.tag_name}</em>
						</c:forEach>
			          </li> --%>
			        </ul>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
    <div class="pt20 pl40 pr40" >
    	<input type="hidden" name="courseRule" id="courseRule" value="${courseMap.course_rule}"/>
        <div id="draggableContent" class="nr_left pull-left" style="min-height: 850px;">
        	<c:if test="${contentList.size() <= 0}">
        	<input value="${contentList.size()}" type="hidden" id="hiddenContentListSize">
        		 <div class="tuoru">
        		 选择右侧内容拖拽到此处
        		 	<!-- DRAG&DROP COURSEWARE --> 
					<!-- <span class="null_t">选择右侧内容拖拽到此处</span> -->
        		</div>
        	</c:if>
            <c:forEach items="${contentList}" var="item">
            	<c:choose>
            		<c:when test="${item.item_type==0}">
						 <div class="box_pre p10 mt10 course_content" qtype="0">
						 
							<div class="edit_title">
								
								<span class="tip pull-left content-order">${item.item_sort}</span>
							
								<div class="title" style="display: inline-block;">
								<a>章节名称：${item.item_name} </a>
								<button type="button" class="del26" onclick="removeQuestion(this);">
								</button>
								</div>
								
								<input value="${item.item_id}" type="hidden"  name="itemId">
								<input value="${item.item_name}" type="hidden"  name="itemName">
								<input value="${item.item_type}" type="hidden"  name="itemType">
							</div>
			                <div class="nr_pic pull-left">
			                	<img src="<c:if test="${empty item.attach_url}">${ctx}/resources/img/course/pic.png</c:if>
			                	<c:if test="${not empty item.attach_url}"> ${imgStatic}${item.attach_url}/${item.generate_name} </c:if>" alt="">
			                </div>
			                <div class="nr_des pull-left ml20">
			                    <p class="mt10">章节描述：${item.item_desc}</p>
			                </div>
			               <%--  <div class="nr_sz pull-right pl20">章节不跳转
			                    <div class="goto" id="${item.id}_1" style="display:none;">
			                        <span class="pr10 pull-left" style="margin-top: 7px;">&gt;=</span>
			                        <input type="text" class="form-control w50 pull-left" value="${item.item_gt}" name="gtThreshold">
			                        <span class="pl10 pr10 pull-left" style="margin-top: 7px;">go to</span>
			                        <select name="" id="${item.id}_1_select_1" class="pull-left selectpicker" value="${item.item_gt_pos}">
			                        </select>
			                    </div>
			                </div> --%>
				     	</div>	
			     	</c:when>
					<c:when test="${item.item_type==1}">
						 <div class="box_pre p10 mt10 course_content" qtype="1">
							<div class="edit_title">
								<span class="tip pull-left content-order" style="background-color:#f06159;">${item.item_sort}</span>
								<div class="title" style="display: inline-block;">
									<a>测验名称：${item.item_name} </a>
									<button type="button" class="del26" onclick="removeQuestion(this);">
									</button>
								</div>
								<input value="${item.item_id}" type="hidden"  name="itemId">
								<input value="${item.item_name}" type="hidden"  name="itemName">
								<input value="${item.item_type}" type="hidden"  name="itemType">
							</div>
			                <div class="nr_pic pull-left">
			                	<img src="<c:if test="${empty item.attach_url}">${ctx}/resources/img/course/pic.png</c:if>
			                	<c:if test="${not empty item.attach_url}"> ${imgStatic}${item.attach_url}/${item.generate_name} </c:if>" alt="">
			                </div>
			                <div class="nr_des pull-left ml20">
			                    <p class="mt10">测验描述：${item.item_desc}</p>
			                </div>
			                <div class="nr_sz pull-right pl20">
			                    <div class="goto" id="${item.id}_1" style="display:none;">
			                        <span class="pr10 pull-left" style="margin-top: 7px;">&gt;=</span>
			                        <input type="text" class="form-control w50 pull-left" value="${item.item_gt}" name="gtThreshold">
			                        <span class="pl10 pr10 pull-left" style="margin-top: 7px;">go to</span>
			                        <select name="" id="${item.id}_1_select_1" class="pull-left selectpicker" value="${item.item_gt_pos}">
			                        </select>
			                    </div>
								<div class="goto goto2" id="${item.id}_2" style="display:none;">
			                        <span class="pr10 pull-left" style="margin-top: 7px;">&lt;=</span>
			                        <input type="text" class="form-control w50 pull-left" value="${item.item_lt}" name="ltThreshold">
			                        <span class="pl10 pr10 pull-left" style="margin-top: 7px;">go to</span>
			                        <select name="" id="${item.id}_2_select_2" class="pull-left selectpicker" value="${item.item_lt_pos}">
			                        </select>
			                    </div>
			                </div>
				     	</div>	
			     	</c:when>
			     	<c:otherwise>
			     		<div class="box_pre p10 mt10 course_content" qtype="2">
							<div class="edit_title">
								<span class="tip pull-left content-order" style="background-color:#a6c3cd;">${item.item_sort}</span>
								<div class="title" style="display: inline-block;">
									<a>调查名称：${item.item_name} </a>
									<button type="button" class="del26" onclick="removeQuestion(this);">
									</button>
								</div>
								<input value="${item.item_id}" type="hidden"  name="itemId">
								<input value="${item.item_name}" type="hidden"  name="itemName">
								<input value="${item.item_type}" type="hidden"  name="itemType">
							</div>
			               <div class="nr_pic pull-left">
			                	<img src="<c:if test="${empty item.attach_url}">${ctx}/resources/img/course/pic.png</c:if>
			                	<c:if test="${not empty item.attach_url}"> ${imgStatic}${item.attach_url}/${item.generate_name} </c:if>" alt="">
			                </div>
			                <div class="nr_des pull-left ml20">
			                    <p class="mt10">调查描述：${item.item_desc}</p>
			                </div>
			                <div class="nr_sz pull-right pl20">
			                </div>
				     	</div>	
			     	</c:otherwise>
		     	</c:choose>						
		</c:forEach>	
        </div>
        <div class="nr_right pull-right" >
			<ul class="nav nav-tabs set_tab" id="myTab" role="tablist" style="padding-left: 0px; background-color: #e1eaee ! important;">
        		<li class="active"><a href="#nr01" role="tab" data-toggle="tab">章节</a></li>
        		<li><a href="#nr02" role="tab" data-toggle="tab">测验</a></li>
        		<li><a href="#nr03" role="tab" data-toggle="tab">问卷调查</a></li>
        	</ul>
            <div id="myTabContent" class="tab-content">
                <div class="pre1_con tab-pane active" id="nr01">
                    <div class="hdn hdn_course">
                        <div class="w240 pull-right">
        				    <button type="button" class="btn-success btn36" onclick="onSearchList(this)">&nbsp;</button>
        				    <div class="search_input"><input type="text" class="form-control" name="keyWord"></div>
        		    	</div>
                        <!-- <button type="button" class="pull-right btn btn_green btn36" data-toggle="modal" data-target="#myModal">创建课件</button> -->
                    </div>
                    <div id="nr01_tab_pane"  style="overflow-y:auto;max-height:688px" class="box_pre mt10 scrollTab">
                    </div>
                </div>
                <div class="pre1_con tab-pane" id="nr02" >
                    <div class="hdn hdn_course">
                        <div class="w240 pull-right">
        				    <button type="button" class="btn-success btn36" onclick="onSearchList(this)">&nbsp;</button>
        				    <div class="search_input"><input type="text" class="form-control" name="keyWord"></div>
        		    	</div>
                       <!--  <button type="button" class="pull-right btn btn_green btn36" data-toggle="modal" data-target="#myModal">创建课件</button> -->
                    </div>
                    <div id="nr02_tab_pane" style="overflow-y:auto;max-height:688px" class="box_pre mt10 scrollTab">
                    </div>
                </div>
                <div class="pre1_con tab-pane" id="nr03">
                    <div class="hdn hdn_course">
                        <div class="w240 pull-right">
        				    <button type="button" class="btn-success btn36" onclick="onSearchList(this)">&nbsp;</button>
        				    <div class="search_input"><input type="text" class="form-control" name="keyWord"></div>
        		    	</div>
                       <!--  <button type="button" class="pull-right btn btn_green btn36" data-toggle="modal" data-target="#myModal">创建课件</button> -->
                    </div>
                    <div id="nr03_tab_pane"  style="overflow-y:auto;max-height:688px" class="box_pre mt10 scrollTab">
                    </div>
                </div>
                <input type="hidden" id="hid_default_tab_pane_page" value="1">
            </div>
        </div>
        <div class="clearfix"></div>
			<div class="btn_box">
				<button class="btn submit btn_160_36" type="button" btn-type='true' btn-val='0'>保存</button>
				<c:if test="${courseMap.create_id == currentUserId}">
				<button class="btn submit btn_160_36" type="button" btn-type='true' btn-val='1'>保存/发布</button>
				</c:if>
				<button class="btn cancel btn_160_36" type="button" onclick="goBack('${courseId}')">取消</button>
				<!-- <button class="btn submit btn_160_36" type="button" btn-type='true'>提交</button>
				<button class="btn submit btn_160_36" type="button" btn-type='true'>提交</button>
				<button class="btn cancel btn_160_36" type="button" onclick="goBack()">取消</button> -->
			</div>
		</div>
		<input value="${courseMap.id}" type="hidden" id="courseId" name="courseId">
		<input value="${courseMap.is_public}" type="hidden" id="isPublic" name="isPublic">
		<input value="${courseMap.expire_time}" type="hidden" id="course_expire_time" name="expire_time">
		<input value="${courseMap.start_time}" type="hidden" id="course_start_time" name="start_time">
		<input id="group_container_type" type="hidden" value=""/>
		<!-- <input value="${eFlag}" type="hidden"  id="eFlag" name="eFlag"> -->
	</form>
	</div>
	
	<%@ include file="/WEB-INF/views/modules/course/course_template.jsp"%>
	<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require( ['modules/course/course_online_content','modules/course/course_common'], function(obj) {
			$(document).ready(function(){
				draggableFun();
				refreshCourseContent();
			});
			window.onbeforeunload = updateEditState;
            window.onhashchange = updateEditState;
		});
	</script>
</body>
</html>
