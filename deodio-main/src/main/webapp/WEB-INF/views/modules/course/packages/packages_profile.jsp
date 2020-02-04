<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp" %>
<body>
	<div class="content p_border p40">
		<ul class="top_nav">
			<li class="no1_on">设置课程包信息</li>
			<li class="no2">设置课程包内容</li>
		</ul>
		<div class="con900">
			<!-- <h3 class="mt20">第一步，设置课程信息</h3> -->
			<div class="form-horizontal mt20 ">
			<form id="coursePackageProfileForm" method="post" >
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>课程包名称：</label>
					<div class="col-sm-10">
						<div class="error-div">
							<input type="text" class="form-control" id="packageName" check-type="required" min-max ='0-200' 
								 data-callback="validateExistName()" required-message="请输入课程包名!" call-message="当前课程包名已存在"  value="${packageMap.packageName}" >
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程包规则：</label>
					<div class="col-sm-10" style="height:38px;line-height:38px;">
						<ul>
							<li>
								<div class="radio-group">
									<input type="radio" name="packageType" id="packageType1" value="1" <c:if test="${packageMap.packageType == 1}"> checked="checked" </c:if>>
									<label class="radio-2" for="packageType1"></label>
								</div>
								<span class="pt8 ml10">有序</span>
								<div class="radio-group">
									<input type="radio" name="packageType" id="packageType2" value="2" <c:if test="${packageMap.packageType == 2}"> checked="checked" </c:if>>
									<label class="radio-2" for="packageType2"></label>
								</div>
								<span class="pt8 ml10">无序</span>
							</li>
						</ul>
						
					</div>
				</div>
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>课程包等级数：</label>
					<div class="col-sm-10">
						<div class="w100 error-div">
							<input type="text" class="form-control w100" id="packageSeries" value="${packageMap.packageSeries}" check-type="number">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程包描述：</label>
					<div class="col-sm-10">
						<div class="editor" id='packageDesc'>
							<script type="text/plain" id="package_desc_template" style="width: 827px; height:140px;" >${packageMap.packageDesc}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程包目标：</label>
					<div class="col-sm-10">
						<div class="editor" id='packageObjective'>
							<script type="text/plain" id="package_objective_template" style="width: 827px; height:140px;" >${packageMap.packageObjective}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程包须知：</label>
					<div class="col-sm-10">
						<div class="editor" id='packageInfomartion'>
							<script type="text/plain" id="package_infomartion_template" style="width: 827px; height:140px;" >${packageMap.packageInfomartion}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>所属分类：</label>
					<div class="col-sm-10 ">
						<div class="fenlei_1 w400" id="inputGeneres" check-type="classification">
							<button type="button" class="btn_add" onclick="popClassificationPicker();"></button>
							<c:forEach items="${selectedClassificationList}" var="item">
								<span class="sel_btn">
									${item.classification_name}
									<button type="button" class="sel_del" classification="${item.id}" onclick="delLabel(this);">&times;</button></span>
							</c:forEach>
							<div class="clearfix" id="genereNodes"></div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>标签：</label>
					<div class="col-sm-10">
						<div class="fenlei_1 w400">
							<div class="clearfix" id="labelNodes"></div>
							<div class="div-tags" id="tagsDiv">  
								<input style="width:700px;" id="inputTagator" name="inputTagator" value="${selectedTagsName}">
								 <button type="button" class="btn_add" id="tagInput" onclick="showHotTagsList()"></button> 
							</div>
							<!-- 点加号弹出选择分类 -->
							<div class="up" id="hotTagsList" style="display:none">
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">封面图像：</label>
					<div class="col-sm-10">
						<div class="user-item">
							<div class="img"><img src="<c:if test="${empty packageMap.packageCoverImg}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if><c:if test="${not empty packageMap.packageCoverImg}"> ${imgStatic}${uploadedAttachment.attachUrl}${packageMap.packageCoverImg} </c:if>" id="preImgId" width="160"></div>
							<div class="user-buttons">
								<span class="f12">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪。</span><br>
								<input id="packageUploadFile" name="packageUploadFile" class="user-card" type="file">
								<!-- <button class="user-card" >上传身份</button> -->
								<button class="certify" type="button" style="display:block;margin:-46px 100px" onclick="deleteFileImg()">删除</button>
							</div>
							<div class="update-bar border-radius" id="progressBar">
								<!-- <div class="bar"></div> -->
							</div>
						</div>
					</div>
					<input type="hidden" id="packageCover" name="packageCover" value="${packageMap.packageCoverImg}">
					<input type="hidden" id="packageCoverId" name="packageCoverTemp">
					<input type="hidden" id="packageId" name="packageId" value="${packageMap.id}">
				</div>
				<div class="form_line2"></div>
				<div class="btn_box">
					<button class="btn submit btn_160_36" type="button" btn-type='true'>保存</button>
					<button class="btn cancel btn_160_36" type="button" onclick="goBack()">取消</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>


	<script type="text/javascript">
		require([ 'modules/course/packages/packages_profile' ], function(obj) {
		});
	</script>
</body>
</html>