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
			<span class="icon-info-sign">&nbsp;</span>您可以填写学习小组或兴趣小组的基本信息。
		</div>
		
		<form action="${ctx}/group/submit.html" method="post" id="createForm">
		
		<div class="form-inline">
			<div class="form-group">
				<label class="input-title input-title-color" style="text-align:center;"><span
							class="input-title-span">＊</span>小组名称</label>
				<input type="text" name="groupName"  id="groupName" value="${groupModel.groupName}"  class="form-control group_title" check-type="required" required-message="小组名称不能为空！" min-max="0-30"  placeholder="请输入小组名称" aria-describedby="basic-addon1">
				
			</div>
			<div class="form-group">
				<label class="input-title input-title-color" style="text-align:center;">小组描述</label>
				<div id="editor" class="jj_bjq pull-left" style="text-align:left;">
					<script type="text/plain" id="content_template" style="width: 591px; height:260px;"></script>
				</div>

			</div>

			
			<%-- <div class="form-group">
				<label class="input-title input-title-color">Group 图片</label>
				<div class="user-item">
					<div class="img"><img src="${ctx}/resources/img/account/user-card-1.jpg" width="160"></div>
					<div class="user-buttons">
						<span class="f12">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪。</span><br/>
						<input id="uploadFile" name="uploadFile" class="user-card" type="file">
						<button class="up_del" style="display:block;margin:-47px 100px">删除</button>
					</div>
					<div class="update-bar border-radius">
						<div class="bar"></div>
					</div>
				</div>
				<input id="hidUploadId" name="attachId" type="hidden">
				<input id="hidContent" name=groupContent type="hidden">
				<input id="hidgoupid" name=groupPkId type="hidden" value="${groupModel.id}">
			</div> --%>
			<input id="hidUploadId" name="attachId" type="hidden">
			<input id="hidContent" name=groupContent type="hidden">
			<input id="hidgoupid" name=groupPkId type="hidden" value="${groupModel.id}">
			<div class="form_line"></div>
			<div class="btn_box">
				<button class="btn submit btn_160_36" btn-type="true" type="button">提交</button>
			</div>


		</div>
</form>
<input value="${groupModel.groupDescription}" type="hidden" id="hid_Content">
	</div>
</div>


<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_profile'],function(obj){
			window.onbeforeunload = $.cookie('cgrid', null);
	        window.onhashchange = $.cookie('cgrid', null);
		});
		
	</script>
</body>
</html>