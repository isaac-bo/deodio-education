<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.info.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">
		<%@ include file="/WEB-INF/views/modules/account/account_top_menu.jsp" %>
		<div class="content-item border-radius">
		<!-- 	<title class="bradius2">基础信息</title> -->
			<div class="layout-content">
				<%@include file="/WEB-INF/views/modules/account/account_setting_left_menu.jsp"%>

				<form id="subdoForm">
					<div class="right-part">

						<title class="bradius2">二级域名（自定义）</title>
						
						<div class="form-inline c929292 f12">
							<p class="ml20">
							请在下方文本框中定制专属于您或您所属的企业的二级域名：例如：http://abc.deodio.com.cn;<br><br>
							系统默认为登陆所用的登录名作为二级域名。 </p>
						</div>
						
						<div style="min-height:400px;" class="form-inline">
							<button class="btn btn_gray btn40 pull-left">http://</button>
							<input type="text" id="subdomain" check-type="char"
									value="${account.accountDomain}" required-message="请输入正确英文字符"
									data-callback="validateHostExists()" call-message="当前域名已被注册"
									placeholder="请输入二级域名" class="form-control pull-left h40 w400">
							<!-- <span class="pt6">.deodio.com.cn</span> -->
							 <button class="btn btn_gray btn40 pull-left">.deodio.com.cn</button>
						</div>
						<div class="clearfix"></div>
				        <div class="form-group text-center p20">
							<button class="btn submit btn_160_36" btn-type="true" type="button">提交</button>
							<button class="btn cancel btn_160_36" type="button">取消</button>
						</div>
						

						<input type="hidden" id="uKeyId" value="${uKeyId}">

					</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/account/account','modules/account/account_domain']);
	</script>


</body>
</html>