<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/account/account.info.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">

		<%@ include file="/WEB-INF/views/modules/account/account_top_menu.jsp"%>

		<div class="content-item border-radius">
			<!--  <title class="bradius2">我的信息</title> -->
			<form id="infoForm" style="height:1035px;">

				<div class="pull-left left-user-icon">
					<div class="set-ip-left">
						<div class="setup-leftpic text-center">
							<img
								src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" />
						</div>
						<!--setup-leftpic end-->
						<button type="button" class="btn btn-gray btn-lg btn-block btn_head_icon mt20" onclick="javascript:showIcon();">
							<span><img src="${ctx}/resources/img/account/camera.png" /></span>&nbsp;&nbsp;&nbsp;上传照片
						</button>
						<div class="user_pic_up" style="top: 200px; left: 640px; display:none;">
							<h4>
								改变自己的照片<a href="javascript:hideIcon();"><img src="${ctx}/resources/img/close_icon.png" alt=""></a>
							</h4>
							<div class="pic_con"></div>
							<!-- 未上传时状态 -->
							<div class="pic_con1" style="display:none;">
								<img src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" alt="">
							</div>
							<!-- 上传带照片状态 -->
							<button type="button" class="btn up_color">上传新照片</button>
							<button type="button" class="btn default">使用默认头像</button>
						</div>
					</div>
				</div>

				<div class="pull-left">
					<div class="form-inline" style="width: 800px;">
						<div class="form-group">
							<label class="input-title input-title-color"><span
								class="input-title-span">＊</span>用户昵称</label> <input type="text"
								id="nickName" name="nickName" check-type="required"
								required-message='4-20位字符,支持汉字、字母、数字及"-"、"_"组合'
								value="${userModel.nickName}" class="form-control h40">
							<div class="form-group-description error-custom"></div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color"><span
								class="input-title-span">＊</span>常用邮箱</label> <input type="text"
								id="userMail" name="userMail" check-type="mail"
								required-message="邮箱格式不正确！" value="${userModel.userMail}"
								class="form-control">
							<button class="btn btn_light_green authentication">认证</button>
							<div class="form-group-description error-custom"></div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">移动电话</label> 
							
							<input type="text" id="mobilePhone" name="mobilePhone"
								value="${userModel.mobilePhone}" class="form-control"
								style="width: 180px" check-type="mobile"
								required-message="请输入11位正确手机号码！" placeholder="请输入手机号码">
							<button class="btn btn_light_green authentication">认证</button>

							<input type="text" id="valicateCode" name="valicateCode"
								class="form-control ml3" style="width: 165px">
							<button class="btn btn_blue authentication-phone">发送验证码</button>
							<div class="form-group-description error-custom"></div>
						</div>

					</div>

					<div class="form-inline" style="width: 800px;">
						<div class="form-group">
							<label class="input-title input-title-color">用户 姓</label> <input
								type="text" value="${userModel.lastName}" id="lastName"
								name="lastName" class="form-control">
							<div class="form-group-description"></div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">用户 名</label> <input
								type="text" value="${userModel.firstName}" id="firstName"
								name="firstName" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">性 别</label>
							<div class="sex">
								<div class="radio-item">
									<div class="radio-group">
										<input type="radio" name="gender"
											<c:if test="${userModel.userGender==1}">checked="checked"</c:if>
											id="man" value="1"> <label class="radio-2" for="man"></label>
									</div>
									<div>男</div>
								</div>
								<div class="radio-item">
									<div class="radio-group">
										<input type="radio" name="gender"
											<c:if test="${userModel.userGender==0}">checked="checked"</c:if>
											id="woman" value="0"> <label class="radio-2"
											for="woman"></label>
									</div>
									<div>女</div>
								</div>

							</div>

						</div>
						<div class="form-group">
							<label class="input-title input-title-color">固定电话</label> <input
								type="text" id="telNumber" name="telNumber"
								value="${userModel.telNumber}" class="form-control"
								check-type="telephone" required-message="请输入正确固定电话号码！"
								placeholder="请输入固定电话号码" aria-describedby="basic-addon1">
							<div class="form-group-description error-custom"></div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">身份证号</label> <input
								type="text" id="" name=""
								value="" class="form-control"
								placeholder="请输入身份证号" aria-describedby="basic-addon1">
							<div class="form-group-description error-custom"></div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">身份认证</label>
							<div class="user-item">
								<div class="img">
									<img src="${ctx}/resources/img/account/user-card-1.jpg"
										width="160" height="90">
								</div>

								<div class="user-buttons">
									<div class="f10 ca0a0a0" style="width: 290px; margin-bottom: 10px;">身份证上的所有信息清晰可见，必须能看清证件号。照片需免冠，建议未化妆，手持证件人的五官清晰可见。照片内容真实有效，不得做任何修改。支.jpg .jpeg .bmp .gif格式照片，大小不超过5M</div>
									<button class="user-card">上传身份</button>
									<button class="certify">认证</button>
								</div>

								<div class="update-bar border-radius">
									<div class="bar"></div>
								</div>

							</div>

						</div>
					</div>
					<c:if test='${isAccountOwner != false}'>
						<div class="form-inline" style="width: 800px;">

							<div class="form-group">
								<label class="input-title input-title-color">系统设置</label>
								<div class="system-set">
									<div class="checkbox-item">
										<div class="checkbox-group">
											<input type="checkbox" name="remember" id="one"> <label
												class="checkbox-2" for="one"></label>

										</div>
										<div>Show welcome message when logging in</div>
									</div>
									<div class="checkbox-item">
										<div class="checkbox-group">
											<input type="checkbox" name="remember" id="two"> <label
												class="checkbox-2" for="two"></label>

										</div>
										<div>Display helpful hint for new users</div>
									</div>
									<div class="checkbox-item">
										<div class="checkbox-group">
											<input type="checkbox" name="remember" id="three"> <label
												class="checkbox-2" for="three"></label>

										</div>
										<div>Display Welcome when I view the Home page</div>
									</div>

								</div>
							</div>
						</div>
					</c:if>
					<div class="form-inline last-child" style="width: 800px;">
						<div class="form-group" style="margin: 0;">
							<button class="btn submit btn_160_36" btn-type="true"
								type="button">提交</button>
							<button class="btn cancel btn_160_36" type="button">取消</button>
						</div>
					</div>
					<input value="${uKeyId}" type="hidden" id="uKeyId" name="uKeyId">
				</div>
			</form>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/account/account','modules/account/account_profile'],function(){
			
		});
	</script>


</body>
</html>