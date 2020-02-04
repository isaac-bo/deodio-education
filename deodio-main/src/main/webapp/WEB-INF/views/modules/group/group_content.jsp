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
		<h3 class="r_title r_title_bg8">内容管理</h3>
			<div class="hdn mt20">
				<div class="pull-right w240">
					<button type="button" onclick=""  class="btn-success btn36">&nbsp;</button>
					<div class="search_input">
						<input type="text" id="keywords" class="form-control">
					</div>
					<div class="clearfix"></div>
				</div>		
				<div class="nice-select pull-right mr20" name="nice-select">
					<input type="text" value="用户名" readonly>
					<span class="select-down"></span>
					<ul>
						<li data-value="1">用户名</li>
					</ul>
				</div>
			</div>
			<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20">
				<thead>
					<tr>
						<th>用户名</th>
						<th>课件名称</th>
						<th>类型</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="">lucas.shi</a></td>
						<td>lucas</td>
						<td>Presentation</td>
						<td>Published</td>
						<td><button type="button" class="btn_act">Manage</button></td>
					</tr>
					<tr>
						<td><a href="">lucas.shi</a></td>
						<td>lucas</td>
						<td>Presentation</td>
						<td>Published</td>
						<td><button type="button" class="btn_act">Manage</button></td>
					</tr>
					<tr>
						<td><a href="">lucas.shi</a></td>
						<td>lucas</td>
						<td>Presentation</td>
						<td>Published</td>
						<td><button type="button" class="btn_act">Manage</button></td>
					</tr>
					<tr>
						<td><a href="">lucas.shi</a></td>
						<td>lucas</td>
						<td>Presentation</td>
						<td>Published</td>
						<td><button type="button" class="btn_act">Manage</button></td>
					</tr>
					<tr>
						<td><a href="">lucas.shi</a></td>
						<td>lucas</td>
						<td>Presentation</td>
						<td>Published</td>
						<td><button type="button" class="btn_act">Manage</button></td>
					</tr>
				</tbody>
			</table>

<!-- 		<div class="form_line2"></div>
		<div class="btn_box">
			<button class="btn submit btn_160_36" type="button">提交</button>
			<button class="btn cancel btn_160_36" type="button">返回</button>
		</div> -->
	</div>
</div>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_content']);
	</script>
</body>
</html>