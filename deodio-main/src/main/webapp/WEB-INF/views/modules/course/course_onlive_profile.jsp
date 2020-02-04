<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content p_border ">
		<ul class="top_nav">
			<li class="no1_on">设置课程信息</li>
			<li class="no2">设置课程内容</li>
			<!-- <li class="no3">设置课程规则</li> -->
		</ul>
		<div class="con900">
			<form id="courseOnliveInfoForm" method="post" >
			<div class="form-horizontal mt20 ">
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>课程名称：</label>
					<div class="col-sm-10">
						<div class="error-div">
						<input type="text" class="form-control" id="courseName" check-type="required" min-max ='0-200' 
							 data-callback="validateExistName()" required-message="请输入课程名!" call-message="当前课程名已存在"  value="${courseMap.course_name}" >
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程描述：</label>
					<div class="col-sm-10">
						<div class="editor" id='courseDesc'>
							<script type="text/plain" id="course_desc_template" style="width: 827px; height:140px;" >${courseMap.course_description}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程目标：</label>
					<div class="col-sm-10">
						<div class="editor" id='courseObjective'>
							<script type="text/plain" id="course_target_template" style="width: 827px; height:140px;" >${courseMap.course_objective}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程须知：</label>
					<div class="col-sm-10">
						<div class="editor" id='courseAttention'>
							<script type="text/plain" id="course_attention_template" style="width: 827px; height:140px;" >${courseMap.course_infomartion}</script>
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
								<input style="width:700px;" id="inputTagator" name="inputTagator" value="${selectedTagsName}" onclick="javascript:popTagsPicker();">
								<button type="button" class="btn_add" id="tagInput"></button> 
							</div>
							<!-- 点加号弹出选择分类 -->
							<div class="up" id="hotTagsList" style="display:none;width: 745px;">
							</div>
						</div>
					</div>
				</div>
				<div class="form_line2"></div>
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>单元数：</label>
					<div class="col-sm-10">
						<div class="error-div">
							<input type="text" class="form-control" id="courseUnitCount" value="${courseMap.course_unit_count}" check-type="number">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">其他：</label>
					<div class="col-sm-10">
						<ul>
							<li>
								<div class="radio-group">
									<input type="radio" name="isPublic" id="radio_is_public1" value='0' <c:if test="${(courseMap.is_public == 0) or (empty courseMap.course_is_public) }"> checked="checked" </c:if> >
									<label class="radio-2" for="radio_is_public1"></label>
								</div>
								<span class="pt8 ml10">公共课程－所有学员课件</span>
							</li>
							<li class="pt10">
								<div class="radio-group">
									<input type="radio" name="isPublic" id="radio_is_public2" value='1' <c:if test="${courseMap.is_public == 1}"> checked="checked" </c:if>>
									<label class="radio-2" for="radio_is_public2"></label>
								</div>
								<span class="pt8 ml10">私有课程</span>
							</li>
						</ul>
						
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">封面图像：</label>
					<div class="col-sm-10">
						<div class="user-item">
							<div class="img"><img src="<c:if test="${empty courseMap.course_cover_img}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if><c:if test="${not empty courseMap.course_cover_img}"> ${imgStatic}${courseMap.attach_url}${courseMap.course_cover_img} </c:if>" id="preImgId" width="160"></div>
							<div class="user-buttons">
								<span class="f12">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪。</span><br>
								<input id="courseUploadFile" name="courseUploadFile" class="user-card" type="file">
								<!-- <button class="user-card" >上传身份</button> -->
								<button class="certify" type="button" style="display:block;margin:-46px 100px" onclick="deleteFileImg()">删除</button>
							</div>
							<div class="update-bar border-radius" id="progressBar">
								<!-- <div class="bar"></div> -->
							</div>
							<input type="hidden" id="courseCover" name="courseCover" value="${courseMap.course_cover_img}">
							<input type="hidden" id="courseCoverId" name="courseCoverTemp">
							<input type="hidden" id="courseId" name="courseId" value="${courseMap.course_id}">
							<input type="hidden" id="courseOnlineId" name="courseOnliveId" value="${courseMap.course_onlive_id}">
							<!-- <input type="hidden" id="coursePassRequireBackup" name="coursePassRequireBackup" value="${courseMap.course_pass_require}"> -->
						</div>
					</div>
				</div>
				
				<div class="form_line2"></div>
				<div class="btn_box">
					<button class="btn submit btn_160_36" type="button" btn-type='true' >提交</button>
					<button class="btn cancel btn_160_36" type="button" onclick="goBack()">取消</button>
				</div>
			</div>
			</form>
		</div>

	</div>
	<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>


	<script type="text/javascript">
		require([ 'modules/course/course_onlive_profile' ], function(obj) {
		});
	</script>
</body>
</html>