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
		<%@include file="/WEB-INF/views/modules/account/account_top_menu.jsp"%>

		<div class="content-item border-radius">
			<!-- <title class="bradius2">基础信息</title> -->
			<div class="layout-content">


				<%@include file="/WEB-INF/views/modules/account/account_setting_left_menu.jsp"%>
				<c:choose>
					<c:when test="${layout==1}">
						<div class="right-part">
							<title class="bradius2">商标（自定义）</title>
							<div class="image-area">
								<div class="update-img">
									<div class="update-item">
										<div id="upload_div" style="position: absolute; left: 50%; top: 50%; margin-top: -20px; margin-left: -50px;">
											<input id="uploadFile" name="uploadFile" type="file">
										</div>
										只支持JPG、PNG、GIF，大小不超过5M
										<div id="log_img_div" style="width: 360px; height: 360px; position: absolute; overflow: hidden; top: 0px; left: 0px; display: none;">
											<img id="log_img" style="" alt="" src="">
										</div>
									</div>
									 <div class="form-group p20 text-center">
						                <button class="btn update" type="button" onclick="uploadFun();">上传</button>
						                <button class="btn delete" type="button" onclick="deleteUploadFun(1);">删除</button>
						                <input type="hidden" id="hidAttDir"> 
						                <input type="hidden" id="hidAttUrl">
						              </div>
              
								</div>
								<div
									class="border-radius layout-img layout-img-show layout-logo-on"></div>
							</div>
							 <title class="bradius2">商标（自定义）链接</title>

							<form id="urlForm">
								<div class="form-inline w400 mt20">
									<input type="text" placeholder="请输入http://www.网址.com"
										value="${accountModel.logoLinkUrl}" id="input_url"
										check-type="url" class="form-control" style="width:400px !important;"> 
									<span class="error-custom"></span> 
									<input type="hidden" value="1" id="input_type">
								</div>
								<div class="clearfix"></div>
						        <div class="form-group text-center p20">
									<button class="btn submit  btn_160_36" btn-type="true" type="button">提交</button>
									<button class="btn cancel btn_160_36" type="button">取消</button>
								</div>
							</form>

						</div>
						<input type="hidden" id="x1">
						<input type="hidden" id="y1">

						<input type="hidden" id="w">
						<input type="hidden" id="h">

					</c:when>
					<c:when test="${layout==2}">

						<div class="right-part">
							<title class="bradius2">Header（自定义）</title>
							<div class="image-area">
								<div class="update-img">
									<div class="update-item">
										<div id="upload_div" style="position: absolute; left: 50%; top: 50%; margin-top: -20px; margin-left: -50px;">
											<input id="uploadFile" name="uploadFile" type="file">
										</div>
										只支持JPG、PNG、GIF，大小不超过5M
										<div id="log_img_div" style="width: 360px; height: 360px; position: absolute; overflow: hidden; top: 0px; left: 0px; display: none;">
											<img id="img_id" style="" alt="" src="">
										</div>
									</div>
									 <div class="form-group p20 text-center">
						                <button class="btn update" type="button" onclick="uploadFun();">上传</button>
						                <button class="btn delete" type="button" onclick="deleteUploadFun();">删除</button>
						                <input type="hidden" id="hidAttDir"> 
						                <input type="hidden" id="hidAttUrl">
						              </div>
								</div>
								<div
									class="border-radius layout-img layout-img-show layout-header-on"></div>
							</div>
							<title class="bradius2">Header（自定义）链接</title>

							<form id="urlForm">
								<div class="form-inline w400 mt20">
									<input type="text" placeholder="请输入http://www.网址.com"
										value="${accountModel.headerLinkUrl}" id="input_url"
										check-type="url" class="form-control" style="width:400px !important;">  
									<span class="error-custom"></span> 
									<input type="hidden" value="2" id="input_type">
								</div>
								<div class="clearfix"></div>
						        <div class="form-group text-center p20">
									<button class="btn submit  btn_160_36" btn-type="true" type="button">提交</button>
									<button class="btn cancel btn_160_36" type="button">取消</button>
								</div>
							</form>



						</div>



					</c:when>
					<c:when test="${layout==3}">
						<div class="right-part">
							<title class="bradius2">Banner（自定义）</title>
							<div class="image-area">
								<div class="update-img">
									<div class="update-item">
										<div id="upload_div"
											style="position: absolute; left: 50%; top: 50%; margin-top: -20px; margin-left: -50px;">
											<input id="uploadFile" name="uploadFile" type="file">
										</div>
										只支持JPG、PNG、GIF，大小不超过5M
										<div id="log_img_div"
											style="width: 360px; height: 360px; position: absolute; overflow: hidden; top: 0px; left: 0px; display: none;">
											<img id="img_id" style="" alt="" src="">
										</div>
									</div>
									<div class="form-group p20 text-center">
						                <button class="btn update" type="button" onclick="uploadFun();">上传</button>
						                <button class="btn delete" type="button" onclick="deleteUploadFun();">删除</button>
						                <input type="hidden" id="hidAttDir"> 
						                <input type="hidden" id="hidAttUrl">
						              </div>
								</div>
								<div class="border-radius layout-img layout-img-show layout-banner-on"></div>
							</div>
							<title class="bradius2">Banner（自定义）链接</title>
							<form id="urlForm">
								<div class="form-inline w400 mt20">
									<input type="text" placeholder="请输入http://www.网址.com"
										value="${accountModel.bannerLinkUrl}" id="input_url"
										check-type="url" class="form-control" style="width:400px !important;"> 
									<span class="error-custom"></span> 
									<input type="hidden" value="3" id="input_type">
								</div>
								<div class="clearfix"></div>
						        <div class="form-group text-center p20">
									<button class="btn submit  btn_160_36" btn-type="true" type="button">提交</button>
									<button class="btn cancel btn_160_36" type="button">取消</button>
								</div>
							</form>



						</div>

					</c:when>
					<c:when test="${layout==4}">
						<div class="right-part">
							<title class="bradius2">Footer（自定义）</title>
							<div class="image-area">
								<div class="update-img">
									<div class="update-item">
										<div id="upload_div"
											style="position: absolute; left: 50%; top: 50%; margin-top: -20px; margin-left: -50px;">
											<input id="uploadFile" name="uploadFile" type="file">
										</div>
										只支持JPG、PNG、GIF，大小不超过5M
										<div id="log_img_div"
											style="width: 360px; height: 360px; position: absolute; overflow: hidden; top: 0px; left: 0px; display: none;">
											<img id="img_id" style="" alt="" src="">
										</div>
									</div>
									<div class="form-group p20 text-center">
						                <button class="btn update" type="button" onclick="uploadFun();">上传</button>
						                <button class="btn delete" type="button" onclick="deleteUploadFun();">删除</button>
						                <input type="hidden" id="hidAttDir"> 
						                <input type="hidden" id="hidAttUrl">
						              </div>
								</div>
								<div class="border-radius layout-img layout-img-show layout-footer-on"></div>
							</div>
							<title class="bradius2">Footer（自定义）链接</title>
							<form id="urlForm">
								<div class="form-inline w400 mt20">
									<input type="text" placeholder="请输入http://www.网址.com" value="${accountModel.footerLinkUrl}" id="input_url" check-type="url" class="form-control" style="width:400px !important;"> 
									<span class="error-custom"></span> 
									<input type="hidden" value="4" id="input_type">
								</div>
								<div class="clearfix"></div>
						        <div class="form-group text-center p20">
									<button class="btn submit  btn_160_36" btn-type="true" type="button">提交</button>
									<button class="btn cancel btn_160_36" type="button">取消</button>
								</div>
							</form>

						</div>


					</c:when>

				</c:choose>







			</div>
		</div>
	</div>
	<input type="hidden" value="${uKeyId}" id="uKeyId">
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/account/account','modules/account/account_style']);
	</script>

</body>
</html>