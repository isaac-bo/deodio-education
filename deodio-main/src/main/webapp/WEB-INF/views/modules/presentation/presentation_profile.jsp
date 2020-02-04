<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/presentation/presentation.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
<div class="content">
		<div class="pre_center" style="position: absolute;">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar" class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav">
						<li class="no1_on">设置章节信息</li>
						<li class="no2" >设置章节相关</li>
						<li class="no3">设置章节预览</li>
					</ul>
				
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>

		<div class="con900" style="margin-top:120px;">
			<form id="settingInfoForm">
			<!-- <h3 class="mt20">第一步，Presentation基本信息</h3> -->
			<div class="form-horizontal mt20 ">
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>章节名称：</label>
					<div class="col-sm-10">
						<div class="error-div">
							<input type="text" class="form-control" id="presentationName" check-type="required" min-max ='0-200' data-callback="validateExistName()" required-message="请输入章节名称!" call-message="当前章节名称已存在" value="${presentationMap.presentation_name}" >
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->章节描述：</label>
					<div class="col-sm-10">
						<div class="editor" id='presentationDesc'>
							<script type="text/plain" id="presentation_template" style="width: 745px; height:140px;" >${presentationMap.presentation_desc}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>所属分类：</label>
					<div class="col-sm-10 ">
						<div class="fenlei_1" id="presentationGeneres" check-type="classification">
							<button type="button" class="btn_add" onclick="popClassificationPicker();"></button>
							<c:forEach items="${selectedClassificationList}" var="item">
								<span class="sel_btn">
									${item.classification_name}
									<button type="button" class="sel_del" classification="${item.id}" onclick="delClassification(this);">&times;</button></span>
							</c:forEach>
							<div class="clearfix" id="genereNodes"></div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>标签：</label>
					<div class="col-sm-10">
						<div class="fenlei_1">
							<div class="clearfix" id="labelNodes"></div>
							<div class="div-tags" id="tagsDiv">  
								<input style="width:700px;" id="inputTagator" name="inputTagator" value="${presentationMap.tag_name}" onclick="javascript:popTagsPicker();">
								 <button type="button" class="btn_add" id="tagInput"></button> 
							</div>
							<!-- 点加号弹出选择分类 -->
							<div class="up" id="hotTagsList" style="display:none;background-color: #f9f9f9; width: 100.3%;">
							</div>
						</div>
						<!-- <input type="email" class="form-control" id="inputEmail3" placeholder=""> -->
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">封面图像：</label>
					<div class="col-sm-10">
						<div class="user-item">
							<div class="img pull-left"><img height="108"  src="<c:if test="${empty presentationMap.presentation_cover}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if><c:if test="${not empty presentationMap.presentation_cover}"> ${imgStatic}${presentationMap.attach_url}${presentationMap.presentation_cover} </c:if>" id="preImgId" width="160"></div>
							<div class="user-buttons">
								<div class="f10 ca0a0a0" style="height:50px;width: 290px; margin-bottom: 10px;">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪。</div>
							
								
								<div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: -5px;">
                                    <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
                                   <input accept="image/png, image/gif, image/jpg, image/jpeg" id="presentationUploadFile" type="file" name="presentationUploadFile" multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
                                </div>
								
								<div style="margin-left: 40px;"><button class="certify" type="button" style="display:block;margin:-32px 100px" onclick="deleteFileImg()">删除</button></div>
								
								
								
								
								
							</div>
							<div class="update-bar border-radius" id="progressBar">
								  <div class="bar" style="width: 0%; height: 18px;background: #00e4ff;"></div>
							</div>
							<input type="hidden" id="presentationCover" name="presentationCover" value="${presentationMap.presentation_cover}">
							<input type="hidden" id="presentationCoverId" name="presentationCoverTemp" value="${presentationMap.attachment_id}">
							<input type="hidden" id="presentationflag" name="presentationId" value="${presentationId}">
						</div>
					</div>
				</div>
			</div>

			<div class="btn_box">
				<button class="btn submit btn_160_36" type="button" btn-type='true'>提交</button>
				<button class="btn cancel btn_160_36" type="button" onclick="goBack()">返回</button>
			</div>
			</form>
		</div>
	</div>
	<input id="sessionId" type="hidden" value="${pageContext.session.id}"/>  
<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>


<script type="text/javascript">
		require([ 'modules/presentation/presentation_profile' ], function(obj) {
		});
	</script>
</body>
</html>