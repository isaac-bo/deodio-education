<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

	<div class="content p_no_border p20" style="background:none;">
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active" id="shijuan">
				<div class="top_form">
					<div class="pull-left" id="smallIconConditionBar">
							<div class="select_b mt10">
								<span>选择角色：</span>
								<div class="w240 ">
									<select name="groupRole" id="groupRole" style="width: 150px">
										<c:forEach items="${roleList}" var="item">
											<option value="${item.id}" <c:if test="${item.id == groupRoleId}">selected</c:if>>${item.name}</option>
										</c:forEach>
									</select>
								</div>
								<span>选择小组：</span>
								<div class="w240 ">
									<select name="groupId" id="groupId" style="width: 150px">
										<option value="">选择小组</option>
										<c:forEach items="${groupList}" var="item">
											<option value="${item.id}" <c:if test="${item.id == groupId}">selected</c:if>>${item.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="w240">
									<button type="button" class="btn-success btn36" onClick="searchCourseList();">&nbsp;</button>
									<div class="search_input">
										<input type="text" id ="keywords" class="form-control" placeholder="请输入课程名或者小组名称" >
									</div>
								</div>
							</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<table id="smallIcon" cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
					<thead>
						<tr>
							<th class="text-center" style="width:30%;">课程名称</th>
							<th class="text-center">课程类别</th>
							<th class="text-center">所属小组</th>
							<th class="text-center">创建人</th>
							<th class="text-center">创建时间</th>
							<th class="text-center">状态</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody id="table_panel">
					</tbody>
				</table>
				<!-- 分页 -->
				<div class="mt20 text-center" id="data_page_Panel">
				</div>
		</div>
	</div>
</div>

<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
		<tr>
			<td class="text-center">{{=isNullFormat(v.course_name)}}</td>
			<td class="text-center">{{=convertToCourseTypeName(v.course_type)}}</td>
			<td class="text-center">{{=isNullFormat(v.group_name)}}</td>
			<td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
			<td class="text-center">{{=dateFormat(v.create_time)}}</td>
			
			<td class="text-center">
				{{?isNullFormat(v.is_select) == '1'}}
					已选择
				{{??}}
					未选择
				{{?}}
			</td>

			<td class="text-center">
				{{?isNullFormat(v.group_role_id) == '10002'}}
					<button type="button" {{?isNullFormat(v.is_select) == '1'}} disabled {{?}}  onClick="javascript:onSelectCourse('{{=isNullFormat(v.course_id)}}','{{=isNullFormat(v.course_type)}}','{{=isNullFormat(v.group_id)}}')">选课</button>
				{{??}}
					<button type="button" class="icon_1" onClick="javascript:onGoToCourseDetail('{{=isNullFormat(v.course_id)}}','{{=isNullFormat(v.course_type)}}')"></button>
				{{?}}
				<input type="hidden" value="{{=dateFormat(v.course_id)}}" name ="courseId">
			</td>
		</tr>
 {{~}}	
</script>

<%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<script type="text/javascript">
	require(['modules/course/course_shortcut_list']);
</script>
</body>
</html>