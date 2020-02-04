<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">
<%@ include file="/WEB-INF/views/commons/group_header.jsp"%>
<body>

<div class="content">
	<div class="pre_left">
		<div class="con-mask"></div>
		<div class="con" style="min-height: 20px;">
				<div class="pre_left_top_fl">
					<div class="pre_left_top_fl_tittle"></div>
					<div class="pre_left_top_fl_tab">
					</div>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
	   <div>
	   		<ul class="group_list neirong ml12" id="content">
				
		    </ul>
	   </div>
   </div>
   <div class="pre_right mr20">
		<h3>点击以下按钮操作</h3>
		<div class="wb100 search">
			<button type="button" class="btn-success btn36" onClick="javascript:reloadPageData();">&nbsp;</button>
			<div class="search_input">
				<input type="text" class="form-control" id="keyWords">
			</div>
			<div class="clearfix"></div>
		</div>
		<ul class="p10 pre_t_btn" id="ul_li_focus_events">
			<li id="createGroup" style="display:none"><button style="background-color:#43b4c6" type="button" onClick="javascript:go2Page('/group/profile.html');"><span class="icon-file-alt"/></button></li>
			<li id="editGroup" style="display:none"><button type="button" style="background-color:#ee625e;" onClick="javascript:onEditGroup()" ><span class="icon-edit"/></button></li>
			<li id="deleteGroup" style="display:none"><button type="button" style="background-color:rgb(113,174,145);"  onClick="javascript:onDeleteGroup()"><span class="icon-trash"/></button></li>
			<li id="copyGroup" style="display:none"><button type="button" style="background-color:rgb(101,170,221);" onclick="javascript:onCopyGroup()"><span class="icon-copy"/> </button></li>			
		    <li id="teacherEdit" style="display:none"><button type="button" style="background-color:#ee625e;" onClick="javascript:onEditTeacherGroup()" ><span class="icon-edit"/></button></li>
		</ul>
		<div class="text" id="text_tips"></div>

	</div>
	<div class="clearfix"></div>
</div>
<input type="hidden" value="${roleLeaderId}" id="roleLeaderId">
<input type="hidden" value="${roleViewerId}" id="roleViewerId">
<input type="hidden" value="${roleCreatorId}" id="roleCreatorId">
<input type="hidden" value="ACCOUNT" id="roleAccount">
<script id="data_template" type="text/x-dot-template">
{{~it.data.dataList:v:index}}
    {{? ${roleViewerId} != v.group_role_id}}
	<li id="{{=isNullFormat(v.id)}}" {{? '${cookie.CGID.value}' == v.id}} class="item_select" {{?}}  onclick="javascript:onSelectGroup('{{=isNullFormat(v.id)}}','{{=isNullFormat(v.group_role_id)}}')">
    {{??}}
	<li id="{{=isNullFormat(v.id)}}" {{? '${cookie.CGID.value}' == v.id}} class="item_select"{{?}} onclick="javascript:toGroupDetail('{{=isNullFormat(v.id)}}')" >
    {{?}}	
	<div class="pic">
		{{? isNullFormat(v.attach_url) == ''}}
		<img src="${ctx}/resources/img/presentation/p_pic.jpg" alt="">
		{{??}}
		<img src="{{=isNullFormat(v.attach_url)}}" alt="">
		{{?}}
		</div>
		<div class="mt10 wid-100">
			<dl class="mt10">
				<dt>小组名称：</dt>
				<dd class="w200"title="{{=isNullFormat(v.group_name)}}">{{=subString(isNullFormat(v.group_name),15)}}</dd>
			</dl>
		</div>
		<div class="mt10 f12">
			<dl>
				<dt>小组成员：</dt>
				<dd>{{=isNullFormat(v.activecount)}}</dd>
			</dl>
			<dl>
				<dt>邀请人数：</dt>
				<dd>{{=isNullFormat(v.allcount)}}</dd>
			</dl>
		</div>
		<!--div class="mt10 f12">

			<dl>
				<dt>创建时间：</dt>
				<dd>{{=dateFormat(v.create_time)}}</dd>
			</dl>
			<dl>
				<dt>修改时间：</dt>
				<dd>{{=dateFormat(v.update_time)}}</dd>
			</dl>
		</div-->
		<div class="mt10 wid-100">

			<dl>
				<dt style="vertical-align: top;">简介：</dt>
				<dd class="neirong-max-height f12 w200"title="{{=isNullFormat(v.group_description)}}">{{=subString(removeHtmlTags(isNullFormat(v.group_description)),30)}}</dd>
			</dl>
		</div>
		<div class="neirong-bottom">
			<button type="button" class="btn btn_red mr10">可编辑</button>
		</div> <!--neirong-bottom end-->
	</li>
{{~}}
		
{{~it.data.dataList:v:index}}
	{{? index == 0}}
		<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
	{{?}}	
{{~}}
</script>
<input type="hidden" id="hid_default_page" value="1">
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<%@ include file="/WEB-INF/views/modules/group/group_copy_dialogue.jsp"%>
	<script type="text/javascript">
		require(['modules/group/group','modules/group/group_list','modules/group/group_copy_dialogue']);
	</script>
</body>
</html>