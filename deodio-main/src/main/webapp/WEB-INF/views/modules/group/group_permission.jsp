<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<div class="content g_border">


<%@ include file="/WEB-INF/views/modules/group/group_left_menu.jsp"%>


	<div class="g_right mess_right">
		<div class="des_text">
			<span class="icon-info-sign">&nbsp;</span>您可以依据以下内容设置相关角色的访问权限。
		</div>
		<form action="${ctx}/group/permission/save.html" method="post" id="permissionForm">
			<div id = "funcList1"></div>
			<div id = "modelFuncConstainer"></div>
			<input type="hidden" id="groupId" name="groupId" value="${groupId}">
			<input type="hidden" id="ids" name="functionIds">
		</form>		
	
		<div class="form_line2"></div>
		<div class="btn_box">
			<button class="btn submit btn_160_36" onclick="saveGroupPermission();" type="button">提交</button>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<script id="permission_data_template" type="text/x-dot-template">
{{ for(var i = 0;i < (it.pageSize)/it.rowSize;i++) { }}
	<table cellpadding="0" cellspacing="0" class="table_fix table course-table table-striped td_h60">
		{{~it.data.dataList:v:index}}
			{{?index ==0 }}
			<thead>
			<tr>
				<th>用户权限</th>
					{{ for(var j = i*it.rowSize + 1;j <= (i+1)*it.rowSize;j++) { }}
						<th>{{=isNullFormat(v['s_name_' + j])}}</th>
					{{ } }}
			</tr>
			</thead>
			<tbody>
			{{?}}
				<tr>
                    {{?v['role_name']!='小组管理员'}}
					<td>{{=v['role_name']}}</td>
					{{ for(var j = i*it.rowSize + 1;j <= (i+1)*it.rowSize;j++) {  }}
						<td>
							{{? j <= it.pageSize }}
								<div class="checkbox-group">
									<input type="checkbox" name="permission" value="{{=v['s_id_' + j]}}" id="{{=v['s_id_' + j]}}" {{?v['s_check_' + j] == '1'}} checked="checked" {{?}}>
									<label class="checkbox-2" for="{{=v['s_id_' + j]}}" ></label>
								</div>
							{{?}}
						</td>
					{{ } }}
                   {{?}}
				</tr> 
		{{~}}
		</tbody>		
	</table>
{{ } }}
</script>

<script id="model_function_data_template" type="text/x-dot-template">

	<table cellpadding="0" cellspacing="0" class="table course-table g3 table-striped td_h60">
		<thead>
			<tr>
				{{for(var i=0;i<it.rowSize;i++) { }}
					{{? i == 0}}
						<th>启用组内工具模块</th>
					{{??}}
						<th></th>
					{{?}}
				{{ } }}
			</tr>
		</thead>
		<tbody>
			{{~it.data.dataList:v:index}}
				{{?index % it.rowSize == 0}}	
					</tr>
				{{?}}
				{{? index % it.rowSize == 0 }}
					<tr>
				{{?}}
						<td>
							<div class="checkbox-group">
								<input type="checkbox" name="permissionModel" value="{{=v.id}}" id={{=v.id}} {{?v.is_checked == '1'}}checked="checked"{{?}}>
								<label class="checkbox-2" for="{{=v.id}}"></label>
							</div>&emsp;
							<span class="mdl">{{=v.name}}</span>
						</td>
			{{~}}

			{{ for(var i =0;i<it.rowSize-it.data.dataList.length%it.rowSize;i++){ }}
						<td></td>
			{{ } }}
			
			{{?it.data.dataList.length > 0}}
				</tr>
			{{?}}
		</tbody>
	</table>
</script>
	
<script type="text/javascript">
		require(['modules/group/group_permission','modules/group/group'],function(obj){
		});
	</script>
</body>
</html>