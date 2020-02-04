<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">
		<div class="pre_center" style="position: absolute;">
			<div class="con-mask" style="width:1230px;"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar"
					class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav" id="type1">
						<li class="no1_on" >设置基本信息</li>
						<!-- <li class="no2" >设置调查内容</li>
						<li class="no3" >调查预览</li> -->
					</ul>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>

		<div class="tab-content">
			<div class="tab-pane active">
				<form action="${ctx}/survey/content.html" method="post" id="createForm">


					<div class="con800" style="margin-top: 100px;padding: 20px;">
						<div class="form-horizontal mt20 ">
							<div class="form-group control-group">
								<label for="inputEmail3" class="col-sm-2 control-label"><span
									class="input-title-span">＊</span>问卷名称:</label>
								<div class="col-sm-10">
									<div class="error-div">
										<input type="text" class="form-control" name="surveyName"
											id="surveyName" check-type="required" min-max='0-200'
											required-message="请输入问卷调查名称!" placeholder="问卷调查名称"
									data-callback="checkSurveyName()" call-message="此问卷调查名称已存在" value="${survey.surveyName}">
										<input type="hidden" name="oldSurveyName" id="oldSurveyName" value="${survey.surveyName}">
									
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">问卷描述:</label>
								<div class="col-sm-10">
									<div class="editor" id='quizExaminationDesc'>
										<script type="text/plain" id="content_template"
											style="width: 687px; height: 140px;">${survey.surveyDesc}</script>
									</div>
								</div>
							</div>
							<div class="control-bar form-group" style="height: auto;">
								<label for="inputEmail3" class="col-sm-2 control-label"><span
									class="input-title-span">＊</span>有效起止日期:</label>
								<div class="col-sm-10">
									<div class="w180 pull-left 	error-div"
										style="margin-top: -3px;width:200px;">
										 <input type="text" class="form-control date_btn form_datetime"
											name="startTime" id="startTime" style="height: 36px;width: 200px;"
											check-type="dateYmd dateCompare"
											aria-describedby="basic-addon1" value="<fmt:formatDate value='${survey.startTime}' pattern='yyyy-MM-dd' />"> 
											 
									</div>
									<span class="date_line pull-left"></span>
									<div class="w180 pull-left ml20	error-div"
										style="margin-top: -3px;width:200px;">
										<input type="text" class="form-control date_btn form_datetime"
											name="endTime" id="endTime" style="width: 200px; height: 36px;"
											check-type="dateYmd dateCompare"
											aria-describedby="basic-addon1" value="<fmt:formatDate value='${survey.endTime}' pattern='yyyy-MM-dd' />">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">调查模式:</label>
								<div class="col-sm-10">
									<div class="radio-item">
										<div class="radio-group" style="margin-top:0px;">
											<input type="radio" name="surveyModel" id="anonymous"
												<c:if test="${survey.surveyModel == 0 }"> checked </c:if>
												value="0"> <label class="radio-2" for="anonymous"></label>
										</div>
										<span class="pt8 ml10">匿名模式（所有人都看不到调查结果的具体人名）</span>
									</div>
									<div class="radio-item mt10">
										<div class="radio-group" style="margin-top:0px;">
											<input type="radio" name="surveyModel" id="manager"
												<c:if test="${survey.surveyModel == null || survey.surveyModel == 1 }"> checked </c:if>
												value="1"> <label class="radio-2" for="manager"></label>
										</div>
										<span class="pt8 ml10">管理员模式（问卷调查人包括Group Leader可以看到调查结果的具体人名）</span>
									</div>

								</div>
							</div>
						<div class="form-group">
		                    <label for="inputEmail3" class="col-sm-2 control-label">封面图像：</label>
		                    <div class="col-sm-10">
		                        <div class="user-item">
		                        	
		                            <div class="img pull-left" style="margin-bottom: 10px;">
		                              <img src="<c:if test="${empty attachment.attachUrl}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if>
		                                  <c:if test="${not empty attachment.attachUrl}"> ${imgStatic}${attachment.attachUrl}${attachment.generateName} </c:if>"
		                                  id="preImgId" width="160" height="95">
		                            </div>
		                            <div class="user-buttons">
                                        <div class="f10 ca0a0a0" style="height:50px;width: 290px; margin-bottom: 10px;">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪，大小不超过5M</div>
                                        <div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
                                            <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
                                            <input accept="image/png, image/gif, image/jpg, image/jpeg" id="surveyUploadFile" type="file" name="surveyUploadFile"
                                             multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
                                        </div>
                                        <div style="margin-left: 35px;"><button class="certify" type="button" style="display:block;margin:-34px 100px" onclick="deleteFileImg()">删除</button></div>
                                    </div>
                                    <div class="update-bar border-radius" id="progressBar" style="width: 354px;">
                                        <div class="bar" style="width: 0%; height: 18px;background: #00e4ff;"></div>
                                    </div>
                                    <input type="hidden" id="surveyCover" name="surveyCover" value="${attachment.generateName}">
		                            <input type="hidden" id="attachId" name="attachId" value="${survey.attachId}">
		                        	<input type="hidden" name="surveyId" id="surveyId" value="${survey.id}"> 
		                        </div>
		                    </div>
                        </div>
						<div class="btn_box">
							<button class="btn submit btn_160_36" btn-type='true' type="button">保存／下一步</button>
							<button class="btn cancel btn_160_36" type="button" onclick="backSurveyList();">返回</button>
						</div>
						<input type="hidden" name="surveyDesc" id="surveyDesc">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>





<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/quizs/quiz','modules/survey/survey_profile'],function(){
			window.onbeforeunload = updateSurveyEditState;
	        window.onhashchange = updateSurveyEditState;
		});
	</script>
 <style>
.date_btn{background-position:  165px 2px;}
</style> 
</body>
</html>