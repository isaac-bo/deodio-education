<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

	<div class="content g_border">


		<%@ include file="/WEB-INF/views/modules/group/group_left_menu.jsp"%>


		<div class="g_right mess_right">
			<div class="bradius2" id="members">
				<div id="form_list">
			<div class="top_btn">
			  <div class="control-bar gfl">
			  </div>
			  <div class="gfr">
			    <button type="button" class="btn2 btn-success" onclick="toManageForm();">管理表单</button>
			  </div>
		    </div>
					<table class="table payment-table table-striped td_h60"
						cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户昵称</th>
								<th>邮箱</th>
								<th>问卷状态</th>
							</tr>
						</thead>
						<tbody id="form_table_panel">
						</tbody>
					</table>

					<div class="mt20 text_center" id="form_table_page_panel">
						<ul class="pagination pagination-lg"></ul>
					</div>

				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="groupId" value="${groupId}">
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<script id="group_detail_table_data_template"
		type="text/x-dot-template">
 	{{~it.data:v:index}}
		<tr>
		  <td >{{=isNullFormat(v.row_number)}}</td>
          <td ><a href="#" onclick="toUserForm('{{=isNullFormat(v.id)}}');">{{=isNullFormat(v.nick_name)}}</a></td>
		  <td >{{=isNullFormat(v.user_mail)}}</td>
		  <td class="text-center">已提交</td>
	   </tr>
 {{~}}	
</script>

	<script type="text/javascript">
		require(['modules/group/group_from_list' ]);
	</script>