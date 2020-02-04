<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/login.css">

<body class="body-bg">
	<div class="login_b">
		<div class="l_box">
			<div class="left_b pull-left left_bg">
				<div class="top_c"></div>
				<div class="bg_fff">
					<h2 class="left_h2"><!-- Start you 2 week trail for free -->认识世界眼中的自己</h2>
					<ul class="left_list">
						<li>多种教学模式</li>
						<li>自定义样式</li>
						<li>精准的能力分析模型</li>
					</ul>
				</div>

				<div class="left_icon2">We use Wipster within our business to
					help people connect on video projects.It saves us a whole lot of
					time and we love it.</div>
				<div class="text-center user_con">
					<div>
						<img src="${ctx}/resources/img/user_pic.jpg" alt="">
					</div>
					<h4>Rachel-Kate Loyd</h4>
					<p>Global Video Editor at Contiki</p>
				</div>
			</div>
			<div class="right_b pull-right">
				<div class="logo_icon text-center">
					<img src="${logoUrl}" alt="">
				</div>
				<div class="login-item login-item-color">
					<form id="loginForm">
					<div class="box-size">
						<div class="input-group wb100">
							<span class="input-group-addon icons-40px user"></span>
							<div class="h40 error-div" style="width: 308px;">
								<input type="text" class="form-control hb100" check-type="required"  id="userName" required-message="<spring:message code="tips.user.name"/>"  placeholder="<spring:message code="tips.user.name"/>" aria-describedby="basic-addon1" onmouseout="mouseOutUserName()">
							</div>
						</div>
					</div>
					<div class="box-size">
						<div class="input-group wb100">
							<span class="input-group-addon icons-40px password"></span> 
							<div class="h40 error-div" style="width: 308px;">
								<input type="password" class="form-control hb100" check-type="required"  
								id="passWord"  required-message="<spring:message code="tips.user.password"/>" 
								placeholder="<spring:message code="tips.user.password"/>" aria-describedby="basic-addon1"
								onclick="changeInputType(this)">
							</div>
						</div>
					</div>
					<div class="box-size">
						<div class="input-group" style="width: 166px; float: left;">
							<span class="input-group-addon icons-40px code"></span> 
							<div class="h40 error-div">
								验证暂时停用
								<%-- <input type="text" class="form-control hb100" id="val_code" check-type="required"  required-message="请输入验证码" data-callback="valCode()" call-message="验证码错误" placeholder="<spring:message code="tips.user.kaptcha"/>" aria-describedby="basic-addon1"> --%>
							</div>
						</div>
						<div class="box-code" style="float: right; width: 175px; height: 40px; background: #fff;">
							<img id="captcha" src="${ctx}/commons/captcha-image.html"> 
							<a href="javascript:onChangeKaptcha();" class="f12"><spring:message code="tips.change.kaptcha"/></a>
						</div>
					</div>
					<div class="checkbox-item box-size" style="margin-top: 10px;">
						<div class="checkbox-group cfff">
							<input type="checkbox" name="remember-password" id="remember-password">
							<label class="checkbox-1" for="remember-password" id="remember-password-label"></label>
						</div>
						<div><spring:message code="common.remember.password"/></div>
					</div>
					
					<div class="box-size mt70">
						<button class="btn btn-default" type="button" btn-type="true"><spring:message code="login.message"/></button>
					</div>
					</form>
					<div class="box-size mt20">
		    			<div class="input-group pull-left" style="width:166px;">
						    <span class="glyphicon glyphicons-info-sign pr5" style="top:2px;color:#555;"></span><a class="point-font" href="${ctx}/signin/forget_pwd.html" class="ca40000"><spring:message code="common.forget.password"/></a>
						</div>
						<div class="box-code pull-right">
							<span class="glyphicon glyphicons-question-sign pr5" style="top:2px;color:#555;"></span><spring:message code="login.signin"/>
						</div>
		    		</div>
					<div class="share-icon pt10 mt10">
						<ul class="text-center">
							<li><span href="#" class="icons-36px weibo"></span></li>
							<li><span href="#" class="icons-36px qq"></span></li>
							<li><span href="#" class="icons-36px douban"></span></li>
							<li><span href="#" class="icons-36px weixin"></span></li>
							<li><span href="#" class="icons-36px tengxun"></span></li>
						</ul>
					</div>
				</div>

			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<script type="text/javascript">
		require(['modules/login/login']);
	</script>

</body>
</html>
