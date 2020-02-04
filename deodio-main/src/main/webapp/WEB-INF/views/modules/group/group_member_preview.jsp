<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<div class="content g_border">


<%@ include file="/WEB-INF/views/modules/group/group_left_menu.jsp"%>
<div class="g_right">
		<h3 class="r_title r_title_bg4">小组成员</h3>
		<dl class="g_table1">
			<dt>名:</dt>
			<dd>${userMap.first_name}</dd>
			<dt>姓:</dt>
			<dd>${userMap.last_name}</dd>
			<dt>用户昵称:</dt>
			<dd>${userMap.nick_name}</dd>
			<dt>邮箱:</dt>
			<dd>${userMap.user_mail}</dd>
			<dt>加入时间:</dt>
			<dd>${userMap.create_time}</dd>
			 <dt>角色:</dt>
			<dd>
			<%-- <div class="pull-left" name="nice-select1">
				<select id="role_select" class="selectpicker ">
					<c:forEach var="item" items="${roleList}">
					<option value="${item.id}">${item.description}</option>
					</c:forEach>
				</select>
				</div>
				 <div style="clear:both;"></div> --%>
			
				<div class="pull-left js-select" name="nice-select1">
					<select id="role_select" class="selectpicker " >
						<!-- <option value=" ">--请选择--</option> -->
						<c:forEach var="item" items="${roleList}">
						<option value="${item.id}">${item.description}</option>
						</c:forEach>
					</select>
				</div>
			 </dd>   
		<table cellpadding="0" cellspacing="0" class="table course-table">
			<thead>
							<tr>
								<th>问题</th>
								<th>答案</th>
							</tr>
						</thead>
						<tbody id="form_table_panel">
						<c:forEach var="item" items="${surveyList}" varStatus="status">
						<c:set var="itemOptions" value="${fn:split(item.survey_item_name, '#')}" /> 
						<tr>
                        <td>${item.survey_subject_name}</td>
                        <td>
                        <c:forEach var="itemr" items="${itemOptions}" varStatus="vs">
		                ${itemr} <br>
		                </c:forEach>
		               
		                </td>
	                    </tr>
						</c:forEach>
						</tbody>
		</table>     
		<div class="mt20 text_center" id="form_table_page_panel">
						<ul class="pagination pagination-lg"></ul>
		</div>
		<div class="form_line2"></div>
		<div class="btn_box">
			<button class="btn submit" onclick="updateRole();" type="button">提交</button>
			<button class="btn cancel" type="button" onclick="go2Page('/group/member.html','groupId=${groupId}')">取消</button>
		</div>




	</div>
</div>

</div>
	<input value="${userMap.group_role_id}" id="defualtValue" type="hidden">
	<input value="${userMap.id}" id="relid" type="hidden">
	<input value="${userId}" id="userId" type="hidden">
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<input value="" type="hidden" id="hid_mails">
<input value="${groupId}" type="hidden" id="groupPkId">
	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_member_preview']);
	</script>
	<style>
	.js-select .dropdown-menu li a{text-indent: 0;
    padding-left: 10px;}
	</style>
</body>
</html>