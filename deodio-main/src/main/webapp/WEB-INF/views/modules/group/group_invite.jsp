<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

	<div class="content g_border">
		<%@ include file="/WEB-INF/views/modules/group/group_left_menu.jsp"%>
		<div class="g_right mess_right" id="srcContent_div">
			<div class="user-buttons">
					
						
			</div>
			<div class="des_text">
				<span class="icon-info-sign">&nbsp;</span>
				您可以通过以下的表单，发送邮件给您希望邀请加入到小组的用户。这种方式能够扩展您与您通过分享小组内容（兴趣小组）到的朋友的在线交流方式和保持联系。
				<br /><br /> 
				(请负责任的使用此功能，不要频繁的发送以免被当作垃圾邮件，从而导致您的账户失效)<br /><br /> 
				输入多个邮件地址，并且用“；”分隔。例如：john.smith@domain.com;jane.doe@domain.com;
			</div>
			
			<div class="des_text">
				<span class="icon-info-sign pull-left">&nbsp;</span>
				<span class="pull-left mr10">您可以通过点击</span>
				<button class="up_del pull-left" type="button" style="display:block;margin: -10px 0px 0px;" onclick="downloadTemplate();">模板下載</button>
				<span class="pull-left mr10 ml10">下载相关模版并根据要求点击</span>
				<input id="uploadExcel" name="uploadExcel" class="user-card pull-left" type="file" style="margin-bottom: 0px; margin-top: -10px;"></input>
				<span class="pull-left mr10 ml10" style="margin-top: -23px; margin-right: 6px; margin-left: 465px !important;">上传附件，批量邀请相关人员。</span>
			</div>
			

			<form action="${ctx}/group/preview_join_mail.html" method="post" id="previewMailForm">
				
				<div class="form-group" style="min-height: 60px;">
					<label for="inputEmail3" class="col-sm-2 pt6 text-right"><span class="input-title-span">＊</span>被邀请人</label>
					<div class="col-sm-9">
						<textarea name="" id="user_mail" cols="30" rows="10" class="tarea_1 error-custom" check-type="required"  required-message="请输入被邀请人"></textarea>
					</div>
				</div>
				<div class="form-group" style="height: 37px;">
					<label for="inputEmail3" class="col-sm-2 pt6 text-right"><span class="input-title-span">＊</span>请选择角色</label>
					<div class="col-sm-10 select-group" name="nice-select"> 
						<select id="role_select" class="selectpicker bootstrap-select pull-left" check-type="required"  required-message="请选择角色">
							<c:forEach var="item" items="${roleList}" varStatus="status">
								<option value="${item.id}" >${item.description}</option>
							</c:forEach>
						</select>
						<div class="form-group-description error-custom pull-left"></div>
					</div>
				</div>
				
				<div class="form-group" style="height: 37px;">
        			<label for="inputEmail3" class="col-sm-2 pt6 text-right"><span class="input-title-span">＊</span>主题</label>
        			<div class="col-sm-9">
        			
						<input type="text" class="form-control yq_title_input h40 error-custom" id="subTitle" placeholder="主题" value="${mailSubject}" check-type="required"  required-message="请输入主题">
					</div>
				</div>
				<div class="form-group" style="height: 313px;">
					<label for="inputEmail3" class="col-sm-2 pt6 text-right"></label>
					<div class="col-sm-9">
						<div style="border:1px solid #cdd5d9;">
							<script type="text/plain" id="content_template" style="width: 676px; height: 260px;">${mailContent}</script>
						</div>
					</div>
					
				</div>
			  <div class="form-group"  style="height: 37px;">
				<label for="inputEmail3" class="col-sm-2 pt6 text-right"></label>
				<div class="col-sm-9">
					<div class="checkbox-item" >
			            <div class="checkbox-group">
			              <input type="checkbox" name="remember" id="sendSearch">
			              <label class="checkbox-2" for="sendSearch" onclick="validateSendForm();" id="sendSearchLabel"></label>
			              
			            </div>
			            <div>同时发送注册问卷调查</div>
			            <input type="hidden" name="activeFormStatus" id = "activeFormStatus" value="${activeFormStatus}">
			        </div>
				</div>
			  </div>
				<div class="form_line2"></div>
				<div class="btn_box">
					<button class="btn submit btn_160_36" btn-type="true" type="button">提交</button>
				</div>
			</form>

		</div>



		<div class="g_right mess_right" id="div_preview" style="display: none;">
			<form action="${ctx}/group/invite/mail.html" id="joinForm"
				method="post">
				<div class="des_text">
				<span class="icon-info-sign">&nbsp;</span>请仔细检查您的邀请邮件内容。<br/><br/>
				您不能取消发送一旦该邀请邮件被提交，请点击"编辑"按钮，如果您希望做任何的修改；请点击"邀请"按钮，如果您已经准备好发送邀请邮件。
				</div>
				<div class="cy_tab_box m20 f14 lh28" style="min-height: 450px;">
					<dl>
						<dt>发件人:</dt>
						<dd id="mailSender">${mailFrom}</dd>
					</dl>
					<dl>
						<dt>主题:</dt>
						<dd id="mailSubject">Invitation to join kakome’s group:Watch a sunrise</dd>
					</dl>
				
					<div class="form_line2" style="margin-top:10px;"></div>
				
					<p id="mailContent">Kakome has invited you to join Watch a sunrisel</p><br />点击
					<a href="#">加入小组</a><br />
				</div>
				
				<div class="form_line2"></div>
				<div class="btn_box">
					<button class="btn submit btn_160_36" onclick="submitJoin()" type="button">提交</button>
					<button class="btn cancel btn_160_36" type="button" onclick="contentBack()">返回</button>
				</div>
				
				<input type="hidden" name="umails" id="eamil_values"> 
				<input type="hidden" name="joinTitle" id="joinTitle"> 
				<input type="hidden" name="userRole" id="userRole"> 
				<input type="hidden" name="joinContent" id="joinContent"> 
				<input type="hidden" name="groupId" id = "groupId" value="${groupId}">
				<input type="hidden" name="sendForm" id="sendForm" value="1"> 
			</form>



		</div>


	</div>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/group/group','modules/group/group_invite']);
	</script>
</body>
</html>