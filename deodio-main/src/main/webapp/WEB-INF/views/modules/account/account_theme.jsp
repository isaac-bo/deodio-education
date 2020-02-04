<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.info.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">
		<%@include file="/WEB-INF/views/modules/account/account_top_menu.jsp"%>

		<div class="content-item border-radius">
			<!-- <title class="bradius2">基础信息</title> -->
			<div class="layout-content">

				<%@include file="/WEB-INF/views/modules/account/account_setting_left_menu.jsp"%>
				<div class="right-part">
					<title class="bradius2">主题（自定义）</title>
					<div class="image-area form-line">
						<ul>
							<li onclick="changLayout(1,this)" class="theme layout-img layout-theme-1 on"></li>
							<li onclick="changLayout(2,this)" class="theme layout-img layout-theme-2"></li>
							<li onclick="changLayout(3,this)" class="theme layout-img layout-theme-3"></li>
							<li onclick="changLayout(4,this)" class="theme layout-img layout-theme-4"></li>
							<li onclick="changLayout(5,this)" class="theme layout-img layout-theme-5"></li>
							<li onclick="changLayout(6,this)" class="theme layout-img layout-theme-6"></li>
							<li onclick="changLayout(7,this)" class="theme layout-img layout-theme-7"></li>
							<li onclick="changLayout(8,this)" class="theme layout-img layout-theme-8"></li>
						</ul>
					</div>
					<div class="clearfix"></div>
			        <div class="form-group text-center p20">
						<button class="btn submit btn_160_36" btn-type="true" onclick="submitTheme();" type="button">提交</button>
						<button class="btn cancel btn_160_36" type="button">取消</button>
					</div>
				</div>



				<input value="1" id="theme_id" type="hidden">


			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<script type="text/javascript">
		require(['modules/account/account','modules/account/account_theme']);
	</script>
</body>
</html>