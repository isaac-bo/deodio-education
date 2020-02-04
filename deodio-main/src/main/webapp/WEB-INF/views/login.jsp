<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<body class="body-bg">
	 <div class="login_b">
            <div class="l_box">
                <div class="left_b pull-left left_bg">
                    <div class="top_c"></div>
                    <div class="bg_fff">
                        <h2 class="left_h2"><!-- Start you 2 week trail for free -->开启两周的免费之旅</h2>
                        <ul class="left_list">
                            <li>Unlimited uploads and playback</li>
                            <li>HD playback</li>
                            <li>Custom branding</li>
                        </ul>
                    </div>

                    <div class="left_icon2">We use Wipster within our business to help people connect on video projects.It saves us a whole lot of time and we love it.</div>
                    <div class="text-center user_con">
                        <div><img src="img/user_pic.jpg" alt=""></div>
                        <h4>Rachel-Kate Loyd</h4>
                        <p>Global Video Editor at Contiki</p>
                    </div>
                </div>
                <div class="right_b pull-right">
                    <div class="logo_icon text-center">
                        <img src="${ctx}/resources/img/account/logo-2.png" alt="">
                    </div>
                    <div class="login-item login-item-color">
                        <div class="box-size">
                            <div class="input-group wb100">
                                <span class="input-group-addon icon-40px user"></span>
                                <input type="text" class="form-control h40" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="box-size">
                            <div class="input-group wb100">
                                <span class="input-group-addon icon-40px password"></span>
                                <input type="text" class="form-control h40" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="box-size">
                            <div class="input-group" style="width:166px;float:left;">
                                <span class="input-group-addon icon-40px code"></span>
                                <input type="text" class="form-control h40" placeholder="请输入验证码">
                            </div>
                            <div class="box-code" style="float:right;width:175px;height:40px;background:#fff;">
                                <img src="img/code-1.jpg">
                                <a href="javascript:void(0);" class="f12">换一张</a>
                            </div>
                        </div>
                        <div class="checkbox-item box-size" style="margin-top:10px;">
                            <div class="checkbox-group cfff">
                                <input type="checkbox" name="remember" id="remember-password">
                                <label class="checkbox-1" for="remember-password"></label>
                            </div>
                            <div>记住密码</div>
                        </div>

                        <div class="box-size mt10" >
                            <button class="btn btn-default" type="submit">登&nbsp;&nbsp;&nbsp;录</button>
                        </div>
                        <div class="box-size text-center mt20">
                            还没有账号？<a href="register.html" class="ca40000">立即注册</a>
                        </div>
                        <div class="share-icon mt10">
                            <ul class="text-center mt20">
                                <li><a href="#" class="icon-36px weibo"></a></li>
                                <li><a href="#" class="icon-36px qq"></a></li>
                                <li><a href="#" class="icon-36px douban"></a></li>
                                <li><a href="#" class="icon-36px weixin"></a></li>
                                <li><a href="#" class="icon-36px tengxun"></a></li>
                            </ul>
                        </div>
                    </div>

                </div>
                <div class="clearfix"></div>
            </div>
        </div>
	<%-- <div class="container">
		<div class="login-top"></div>
		<div class="login-bgcolor">
			<h3><img src="${ctx}/resources/img/admin_logo.png"></h3>
			<form id="loginForm" role="form">
				<div class="login-bg">
					<div class="login-form">
						<div class="form-group">
							<label><spring:message code="login.user.name"/></label>
							<input type="text" id="username" check-type="required"  data-callback="isExistUserName()" 
								   info-message="<span class='icon-info-sign info'>&nbsp;</span><spring:message code='account.login.username.tip'/>" 
								   call-message="<span class='icon-warning-sign sign'>&nbsp;</span><spring:message code='account.login.username.exist.err.msg'/>"   
								   required-message="<span class='icon-warning-sign sign'>&nbsp;</span><spring:message code='account.login.username.empty.err.msg'/>" >
							<p class="col-sm-6 hint error-custom">
								&nbsp;
							</p>
						</div>
					</div>
					<div class="login-form">
						<div class="form-group">
							<label><spring:message code="login.user.password"/></label>
							<input type="password" id="password" check-type="required" 
								   info-message="<span class='icon-info-sign info'>&nbsp;</span><spring:message code='account.login.password.tip'/>" 
								   required-message="<span class='icon-warning-sign sign'>&nbsp;</span><spring:message code='account.login.password.empty.err.msg'/>" >
							<p class="col-sm-6 hint error-custom">
								&nbsp;
							</p>
						</div>
						
					</div>
					<button type="button" btn-type='true' class="students" id="login"><spring:message code="login.login"/></button>
				</div>
				<p>
					<a href="${ctx}/account/passport.html"><spring:message code="login.forget.password"/></a>
					<a href="${ctx}/account/register.html"><spring:message code="login.sign.in"/></a>
				</p>
				<div class="fimg-icon">
					<a href=""><img src="${ctx}/resources/img/fimg1.png"></a>
					<a href=""><img src="${ctx}/resources/img/fimg2.png"></a>
					<a href=""><img src="${ctx}/resources/img/fimg3.png"></a>
					<a href=""><img src="${ctx}/resources/img/fimg4.png"></a>
				</div>
			</form>
		</div>
		<!-- end of body -->
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	</div> <!-- end of container --> --%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<script type="text/javascript">
		require(['common/login'],function(lg){
			lg.isLoginFunc();
		});
	</script>
	
</body>
</html>
    