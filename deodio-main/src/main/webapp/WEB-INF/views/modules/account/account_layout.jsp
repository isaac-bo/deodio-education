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
					<title class="bradius2">布局（自定义）</title>
					
					<div class="form-inline c929292 f12">
						<p class="ml20">
						请选择希望设置的布局，系统会自动调整站点的整体布局。 </p>
					</div>
					
					
					
					<div class="image-area form-inline" style="min-height:400px;">
						<ul>
							<li onclick="changLayout(1,this)" class="on"><img src="${ctx}/resources/img/account/layout_1.png"/></li>
							<li onclick="changLayout(2,this)" class=""><img src="${ctx}/resources/img/account/layout_2.png"/></li>
							<li onclick="changLayout(3,this)" class=""><img src="${ctx}/resources/img/account/layout_3.png"/></li>
							<li onclick="changLayout(4,this)" class=""><img src="${ctx}/resources/img/account/layout_4.png"/></li>
							<li onclick="changLayout(5,this)" class=""><img src="${ctx}/resources/img/account/layout_5.png"/></li>
						<!-- 	<li onclick="changLayout(6,this)" class="layout-img layout-list-6"></li>
							<li onclick="changLayout(7,this)" class="layout-img layout-list-7"></li>
							<li onclick="changLayout(8,this)" class="layout-img layout-list-8"></li> -->
						</ul>
					</div>
					<div class="clearfix"></div>
			        <div class="form-group text-center p20">
						<button class="btn submit btn_160_36" btn-type="true" onclick="submitLayout();" type="button">提交</button>
						<button class="btn cancel btn_160_36" type="button">取消</button>
					</div>

				</div>

				<input type="hidden" value="1" id="layout_id"> 
				<input type="hidden" value="${uKeyId}" id="uKeyId">
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<script type="text/javascript">
		require(['modules/account/account','modules/account/account_layout']);
	</script>

</body>
</html>