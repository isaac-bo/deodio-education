<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

	<div class="content g_border">


		<%@ include file="/WEB-INF/views/modules/group/group_left_menu.jsp"%>

		<div class="g_right mess_right content-item ">

			<form id="userForm">

				<div class="des_text">
					<span class="icon-info-sign">&nbsp;</span>系统不会为已经存在的账户创建新的账户，该账户会自动与同名账户关联。
				</div>
				<div class="form-inline" style="overflow: visible;">
					<div class="form-group">
						<label class="input-title input-title-color"><span
							class="input-title-span">＊</span>邮箱地址</label> <input type="text"
							class="form-control" id="userMail" check-type="mail"
							required-message="请输入正确的邮箱地址" placeholder="请输入邮箱地址"
							data-callback="validateGroupRoleExist()" call-message="当前用户已加入小组"   onfocus="changeNickStatus();">
						<div class="form-group-description error-custom"></div>
					</div>
					<div class="form-group">
						<label class="input-title input-title-color">用户昵称</label> <input type="text"
							class="form-control"
							onblur="validateUserNameExists()"
                            id="userName" placeholder="请输入用户昵称">
						<div class="form-group-description error-custom"></div>
					</div>
					<div class="form-group">
						<label class="input-title input-title-color">名</label> <input
							type="text" class="form-control" id="firstName"
							placeholder="请输入名">
						<div class="form-group-description error-custom"></div>
					</div>
					<div class="form-group">
						<label class="input-title input-title-color">姓</label> <input
							type="text" class="form-control" id="lastName" placeholder="请输入姓">
						<div class="form-group-description error-custom"></div>
					</div>
					<div class="form-group">
						<label class="input-title input-title-color">电话</label> <input
							type="text" class="form-control" id="mobile" check-type="mobile"  
							required-message="请输入正确电话号码" placeholder="请输入电话号码" non-required="true">
						<div class="form-group-description error-custom"></div>
					</div>
					<div class="form-group">
						<label class="input-title input-title-color"><span
							class="input-title-span">＊</span>角色</label>
						<div name="nice-select"
							style="width: 250px; margin-left: 160px; margin-top: 0px;">
							<select id="role_select" class="selectpicker bootstrap-select"
								check-type="required" required-message="请选择用户角色">
								<option value=" ">--请选择--</option>
								<c:forEach var="item" items="${roleList}">
									<option value="${item.id}">${item.description}</option>
								</c:forEach>
							</select>
							<div class="form-group-description error-custom"></div>
						</div>

					</div>
					<div class="form-group">
						<label class="input-title">&nbsp;</label>
						<div class="system-set"
							style="margin-left: 2px; height: 40px; line-height: 40px;">
							<div class="checkbox-item">
								<div class="checkbox-group">
									<input type="checkbox" name="remember" id="isSendMail">
									<label class="checkbox-2" for="isSendMail"
										onclick="validateSendForm();" id="sendSearchLabel"></label>
								</div>
								<div>同时发送注册问卷调查</div>
								<input type="hidden" name="activeFormStatus"
									id="activeFormStatus" value="${activeFormStatus}">
							</div>
						</div>
					</div>
				</div>
				<div class="form_line2"></div>
				<div class="btn_box">
					<button class="btn submit btn_160_36" btn-type="true" type="button">提交</button>
				</div>

			</form>

		</div>
	</div>

	<input type="hidden" id="groupId" value="${groupId}">
	<input type="hidden" id="lastUserEmail" value="">
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require([ 'modules/group/group', 'modules/group/group_member_single' ]);
	</script>
</body>
</html>