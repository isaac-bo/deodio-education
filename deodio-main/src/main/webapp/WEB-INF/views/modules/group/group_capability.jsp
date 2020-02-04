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
			<div class="title1 mt20 pb10 hdn">
				<h4 class="pull-left">知识工具</h4>
				<button type="button" class="btn btn_blue pull-right" onclick="onPopupCapabilityItem(1)">添加工具</button>
			</div>
			<div class="pt20 pb10 text_c">知识工具等级标注</div>
			<div class="dengji">
				<div class="dj_box bg_c1" id="knowledge_tools_01">
					<h3>了解</h3>
					<p>在指导下实践过相关知识，可以尝试独立操作</p>
					<div>
						<span class="glyphicon glyphicon-star"></span>
					</div>
				</div>
				<div class="dj_box bg_c2" id="knowledge_tools_02">
					<h3>熟悉</h3>
					<p>无需指导，可以独立使用，完成常规性任务</p>
					<div>
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
				</div>
				<div class="dj_box bg_c3" id="knowledge_tools_03">
					<h3>掌握</h3>
					<p>掌握某一领域的知识和原理，能够触类旁通，成功完成大多数的任务</p>
					<div>
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
				</div>
				<div class="dj_box bg_c4" id="knowledge_tools_04">
					<h3>深入掌握</h3>
					<p>全面掌握相关知识和原理，能够融会贯通，灵活应用并指导其他人有效运用</p>
					<div>
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
				</div>
				<div class="dj_box bg_c5" id="knowledge_tools_05">
					<h3>精通</h3>
					<p>在某一领域或者多个领域可以给出专家级的意见，能指导其他人成功应用</p>
					<div>
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
				</div>
			</div>
			<table cellspacing="0" cellpadding="0" class="dj_table mt20">
				<thead id="knowledge_capability_items_header_panel">
				</thead>
				<tbody id="knowledge_capability_items_panel">
					
				</tbody>
			</table>
			<div class="title1 mt20 pb10 hdn">
				<h4 class="pull-left">能力</h4>
				<button type="button" class="btn btn_blue pull-right" onclick="onPopupCapabilityItem(2)">添加能力</button>
			</div>
			<table cellspacing="0" cellpadding="0" class="dj_table mt20">
				<thead id="ability_capability_items_header_panel">
<!-- 					<tr>
						<th><div class="pl10">Name(job title)</div></th>
						<th class="text-center w138 bg1">CL1</th>
						<th class="text-center w138 bg2">CL2</th>
						<th class="text-center w138 bg3">CL3</th>
						<th class="text-center w138 bg4">CL4</th>
						<th class="text-center w138 bg5">CL5</th>
					</tr>
 -->				</thead>
				<tbody id="ability_capability_items_panel">
				</tbody>
			</table>




			<div class="form_line2"></div>
			<input type="hidden" id="capabilityType" value=""/>
			<input type="hidden" id="groupId" value="${groupId}"/>
			<input type="hidden" id="headLength" value="${headLength}"/>
			<div class="btn_box">
				<button class="btn submit btn_160_36" type="button" onclick="onSubmit()">提交</button>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<%@ include file="/WEB-INF/views/modules/group/group_capability_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<script id="capability_item_data_template" type="text/x-dot-template">
<div class="hdn">
    <div class="w240 pull-right">
		<button type="button" class="btn-success btn36">&nbsp;</button>
		<div class="search_input"><input type="text" class="form-control" id="groupToolsKeywords"></div>
	</div>
</div>
<div style="height: 310px;">
	<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20">
  		<thead>
     		<tr>
       			<th style="width:40px;">
         			<div class="checkbox-group">
           				<input type="checkbox" name="allCapabilityItem" id="allCapabilityItem" onclick="onSelectAll(this)">
           				<label class="checkbox-2" for="allCapabilityItem" ></label>
        		 	</div>
       			</th>
       			<th>名称</th>
	   			<th>创建时间</th>
     		</tr>
    	</thead>
    	<tbody id="capabilityBody">
    	</tbody>
	</table>
        <!-- 分页 -->
		<div class="mt20 text-center" id="group_capability_to_select_data_page_Panel">
		</div>
</div>
<div class="modal-footer" id="popupModelFooter">
	<button class="btn submit btn_160_36" type="button" onclick="onSelectCapabilityItem()">添加</button>
	<button class="btn cancel btn_160_36" type="button" data-dismiss="modal">取消</button>
</div>
</script>

<script id="t_head_capability_items_data_template" type="text/x-dot-template">

<tr>
  <th><div class="pl10">Name(job title)</div></th>
  {{~it.data:v:index}}
  <th class="text-center w138 bg{{=index+1}}">{{=v.capabilitylevelfull}}</th>
  {{~}}
  <th  class="text-center" style="width:20px">操作</th>
</tr>

</script>
	
<script id="t_body_capability_items_data_template" type="text/x-dot-template">
{{var tempCapabilityId = '';}}	
{{~it.data:v:index}}
{{?v.capability_id != tempCapabilityId}}
{{tempCapabilityId = v.capability_id;}}
<tr>
	<td><div class="pl10">{{=v.capability_item}}</div></td>
{{?}}

{{? v.capability_type == 1}}
	<td id="{{=v.id}}" name="knowledge_capability_item_{{=v.id}}" class="text-center bg{{=v.capability_level}}">{{?isNullFormat(v.capability_desc) != ''}}<span class="d0{{=isNullFormat(v.capability_desc)}}">{{?isNullFormat(v.capability_desc) == '1'}}了解{{?}}{{?isNullFormat(v.capability_desc) == '2'}}熟悉{{?}}{{?isNullFormat(v.capability_desc) == '3'}}掌握{{?}}{{?isNullFormat(v.capability_desc) == '4'}}深入掌握{{?}}{{?isNullFormat(v.capability_desc) == '5'}}精通{{?}}<button type='button'class='sel_del' onclick='onDelCapabilityItemInTable(this)'>x</span></span>{{?}}</td>
{{??}}
	<td id="{{=v.id}}" name="ability_capability_item_{{=v.id}}" class="bg{{=v.capability_level}}">{{?isNullFormat(v.capability_desc) != ''}}<text class='text'>{{=isNullFormat(v.capability_desc)}}</text>{{?}}</td>
{{?}}
{{?(index+1)%${headLength}==0}}
    <td><a onclick="delCapability('{{=v.capability_id}}');" href="javascript:void(0)" class="icon_btn1"></a></td>
{{?}}
{{~}}
</script>	
	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_capability']);
	</script>
</body>
</html>