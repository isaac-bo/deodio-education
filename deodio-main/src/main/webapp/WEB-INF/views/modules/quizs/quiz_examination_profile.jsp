<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<body>
	<div class="content">
		<div class="pre_center" style="position:absolute;">
				<div class="con-mask"></div>
				<div class="con" style="min-height: 20px;">
					<div id="div_tables_bar" class="pre_left_top_fl pre_right_top_normal" >
						<c:if test="${empty navTabs}">
					<ul class="top_nav">
						<li class="no1_on">设置基本信息</li>
					</ul>
				</c:if>
				<c:if test="${navTabs==1}">
					<ul class="top_nav">
						<li class="no1_on">设置基本信息</li>
						<li class="no2">设置题目内容</li>
						<li class="no3">试卷预览</li>
						<!-- <li class="no4">设置试卷规则</li> -->
					</ul>
				</c:if>
				<c:if test="${navTabs==2}">
					<ul class="top_nav6" id="type2">
						<li class="no1_on">设置基本信息</li>
						<li class="no2">设置出题规则</li>
						<li class="no3">设置必考题目</li>
						<li class="no4">设置随机题目</li>
						<li class="no5">试卷预览</li>
						<!-- <li class="no6">设置试卷规则</li> -->
					</ul>
				</c:if>
					</div>
					<!--pre_left_top_fl end-->
					<div class="clearfix"></div>
					<div class="con-corner"></div>
				</div>
		   </div>
		<div class="tab-content" style="margin-top:130px;">
			<div class="tab-pane active">
				
<!-- 			<h3 class="h3_title mt20">第一步，试卷基本信息</h3> -->
				<form id="createForm">
					<div class="con800">
						<div class="form-horizontal mt20 ">
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								<span class="input-title-span">＊</span>试卷名称：
							</label>
							<div class="col-sm-10">
								<div class="error-div">
								<input type="text" class="form-control" name="quizName" id="quizName" 
									check-type="required" required-message="请输入试卷名称!" placeholder="试卷名称"
									data-callback="checkQuizName()" call-message="此试卷名称已存在" 
									value="${quizModel.quizName}"  maxlength="15" autocomplete="off">
								</div>
								<input type="hidden" name="quizName" id="oldQuizName" value="${quizModel.quizName}">
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">试卷描述：</label>
							<div class="col-sm-10">
								<div class="editor" id='quizExaminationDesc'>
									<script type="text/plain" id="content_template" style="width: 720px; height:140px;">
										${quizModel.quizDesc}
									</script>
								</div>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								<span class="input-title-span">＊</span>试卷类型:
							</label>
							<div class="col-sm-10">
							<div class="jj_bjq pull-left">
								<select name="quizType" id="quizType">
									<option
										<c:if test="${quizModel.quizCategory==1}">  selected="selected" </c:if>
										value="1">测验</option>
									<option
										<c:if test="${quizModel.quizCategory==2}">  selected="selected" </c:if>
										value="2">考试</option>
									<option
										<c:if test="${quizModel.quizCategory==3}">  selected="selected" </c:if>
										value="3">测验和考试</option>
								</select>
							</div>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								<span class="input-title-span">＊</span>标签:</label>
							<div class="col-sm-10">
								<div class="fenlei_1">
									<div class="clearfix" id="labelNodes"></div>
									<div class="div-tags" id="tagsDiv">  
										<input style="width:680px;" id="inputTagator" name="inputTagator" 
											value="${quizModel.tagName}" onclick="popTagsPicker()">
										<button type="button" class="btn_add" id="tagInput" onclick="popTagsPicker()"></button> 
									</div>
									<!-- 点加号弹出选择分类 -->
									<div class="up" id="hotTagsList" style="display:none;background-color: #f9f9f9; width: 100%;">
									</div>
								</div>
							</div>
						</div>
						<div class="seprate_line">
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								考试时间:
							</label>
							<div class="col-sm-10">
							    <div class="w120 error-div pull-left">
                                    <input type="text" class="form-control" id="finish_time" name="finish_time"
                                    check-type="number" min-max-num="1-120"  required-message="请填写1-120的数字！" non-required="true" value="${quizModel.finishTime}">
                                </div><span class="pt8 ml10 mr20 pull-left">分钟</span>
								<%-- <div class="jj_bjq pull-left">
									<ul class="shezhi_normal" style="margin-top:-20px;">
										<li>
											<div class="checkbox-group">
												<input type="checkbox"
													<c:if test="${quizModel.finishTime!=null}">checked="checked"</c:if>
													name="remember" id="radio_finish_time"> 
												<label class="checkbox-2 <c:if test="${quizModel.finishTime!=null}"> checked</c:if>"
													for="radio_finish_time"></label>
											</div> <span class="pt8 ml10">测验／试卷必须在<input type="text"
												value="${quizModel.finishTime}" id="finish_time"
												class="text form-control">分钟内完成。
										</span>
										</li>
									</ul>
								</div> --%>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								<span class="input-title-span">＊</span>通过分数:
							</label> 
							<div class="col-sm-10">
								<div class="error-div">
									<input type="text" class="form-control" name="pass_score" placeholder="通过分数"
										id="pass_score" value="${quizModel.passScore}"
										check-type="scoreNumber">
								</div>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								<span class="input-title-span">＊</span>允许考试次数:
							</label> 
							<div class="col-sm-10">
								<div class="error-div">
									<input type="text" class="form-control" name="quiz_max_times" placeholder="允许考试次数"
										id="quiz_max_times" value="${quizModel.quizMaxTimes}"
										check-type="number">
								</div>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								<span class="input-title-span">＊</span>成绩确定方式:
							</label> 
							<div class="col-sm-10">
							<div class="jj_bjq pull-left">
								<div class="radio-group pull-left">
									<input type="radio" name="remember1" checked="checked"
										<c:if test="${quizModel.finallyQuizResultType==1}">checked="checked"</c:if>
										id="finalResut1" value="1"> <label
										class="radio-2 <c:if test="${quizModel.finallyQuizResultType==1}"> checked</c:if>"
										for="finalResut1"></label>
								</div><span class="pt8 ml10 mr20 pull-left">最后一次考试成绩</span>
								<div class="radio-group pull-left">
									<input type="radio" name="remember1"
										<c:if test="${quizModel.finallyQuizResultType==2}">checked="checked"</c:if>
										id="finalResut2" value="2"> <label
										class="radio-2 <c:if test="${quizModel.finallyQuizResultType==2}"> checked</c:if>"
										for="finalResut2"></label>
								</div> <span class="pt8 ml10 mr20 pull-left">最高分</span>
								<div class="radio-group pull-left">
									<input type="radio" name="remember1"
										<c:if test="${quizModel.finallyQuizResultType==3}">checked="checked"</c:if>
										id="finalResut3" value="3"> <label
										class="radio-2 <c:if test="${quizModel.finallyQuizResultType==3}"> checked</c:if>"
										for="finalResut3"></label>
								</div> <span class="pt8 ml10 mr20 pull-left">平均分</span>
							</div>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								<span class="input-title-span">＊</span>成绩公布方式:
							</label> 
							<div class="col-sm-10">
							<div class="pull-left">
								<div class="r_con">
									<div class="mb10">
										<div class="radio-group pull-left">
											<input type="radio" name="remember2" checked="checked"
												<c:if test="${quizModel.publishResultType==1}">checked="checked"</c:if>
												id="publisResult1" value="1"> <label
												class="radio-2 <c:if test="${quizModel.publishResultType==1}"> checked</c:if>"
												for="publisResult1"></label>
										</div>
										<span class="pt8 ml10 mr20 pull-left">考试分数保密</span>
										<div class="clearfix"></div>
									</div>
									<div class="mb10">
										<div class="radio-group pull-left">
											<input type="radio" name="remember2"
												<c:if test="${quizModel.publishResultType==2}">checked="checked"</c:if>
												id="publisResult2" value="2"> <label
												class="radio-2 <c:if test="${quizModel.publishResultType==2}"> checked</c:if>"
												for="publisResult2"></label>
										</div>
										<span class="pt8 ml10 mr20 pull-left">交卷后即时显示分数成绩</span>
										<div class="clearfix"></div>
									</div>
									<%-- 
									<div class="mb10">
										<div class="radio-group pull-left">
											<input type="radio" name="remember2"
												<c:if test="${quizModel.publishResultType==3}">checked="checked"</c:if>
												id="publisResult3" value="3"> <label
												class="radio-2 <c:if test="${quizModel.publishResultType==3}"> checked</c:if>"
												for="publisResult3"></label>
										</div>
										<span class="pt8 ml10 mr20 pull-left">交卷后不显示考试分数成绩，待管理员确定公布成绩后才显示</span>
										<div class="clearfix"></div>
									</div>
									 --%>
								</div>
							</div>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">试卷安全:</label> 
							<div class="col-sm-10">
							<div class="pull-left">
								<div class="r_con">
									<div class="mb10">
										<div class="checkbox-group">
											<input type="checkbox"
												<c:if test="${quizModel.quizSafe=='1' or quizModel.quizSafe=='1,2'}">checked="checked"</c:if>
												name="remember" id="quizSafe1" value="1"> <label
												class="checkbox-2 <c:if test="${quizModel.quizSafe=='1'  or quizModel.quizSafe=='1,2'}"> checked</c:if>"
												for="quizSafe1"></label>
										</div>
										<span class="pt8 ml10 mr20">允许考生提交后查看其提交的答案和标准答案</span>
										<div class="clearfix"></div>
									</div>
									<div class="mb10">
										<div class="checkbox-group">
											<input type="checkbox"
												<c:if test="${quizModel.quizSafe=='2'  or quizModel.quizSafe=='1,2'}">checked="checked"</c:if>
												name="remember" id="quizSafe2" value="2"> <label
												class="checkbox-2 <c:if test="${quizModel.quizSafe=='2'  or quizModel.quizSafe=='1,2'}"> checked</c:if>"
												for="quizSafe2"></label>
										</div>
										<span class="pt8 ml10 mr20">允许学员成绩合格后继续参加考试</span>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							</div>
						</div>
						<div class="form-group control-group">
							<label for="inputEmail3" class="col-sm-2 control-label">试卷说明:</label>
							<div class="col-sm-10">
								<div class="editor" id='description_template_editor'>
									<script type="text/plain" id="description_template" style="width: 720px; height:140px;" >${quizModel.quizContent}</script>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="form-group">
		                    <label for="inputEmail3" class="col-sm-2 control-label">封面图像：</label>
		                    <div class="col-sm-10">
		                        <div class="user-item">
		                            <div class="img pull-left mb10">
		                              <img src="<c:if test="${empty quizModel.generateName}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if>
		                                  <c:if test="${not empty quizModel.generateName}"> ${imgStatic}${quizModel.attachUrl}${quizModel.generateName} </c:if>"
		                                  id="preImgId" width="160" height="99px;">
		                            </div>
		                            <div class="user-buttons">
                                        <div class="f10 ca0a0a0" style="height:60px;width: 290px; margin-bottom: 5px;">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪，大小不超过5M</div>
                                        <div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
                                            <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
                                            <input accept="image/png, image/gif, image/jpg, image/jpeg" id="quizUploadFile" type="file" name="quizUploadFile"
                                             multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
                                        </div>
                                        <div style="margin-left: 40px;"><button class="certify" type="button" style="display:block;margin:-34px 100px" onclick="deleteFileImg()">删除</button></div>
                                    </div>
                                    <div class="update-bar border-radius" id="progressBar" style="width:360px;">
                                        <div class="bar"></div>
                                    </div>
                                    <input type="hidden" id="quizCover" name="quizCover" value="${quizModel.generateName}">
		                            <input type="hidden" id="quizCoverId" name="quizCoverTemp" value="${quizModel.attachId}">
		                            <input type="hidden" id="quizflag" name="quizId" value="${quizId}">
		                        </div>
		                    </div>
                        </div>
					</div>
					<c:choose>
						<c:when test="${quizModel.createType!=null}">
							<input type="hidden" name="createType" id="hid_quizType"
								value="${quizModel.createType}">
						</c:when>
						<c:otherwise>
							<input type="hidden" name="createType" id="hid_quizType"
								value="${navTabs}">
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="quizId" id="hid_quizId" value="${quizId}"> 
					<input type="hidden" name="quizId" id="quizId" value="${quizId}"> 
					<input type="hidden" name="editFlag" id="hid_editFlag" value="${eFlag}">
					<input type="hidden" name="navTabs" id="navTabs" value="${navTabs}">
					<div class="form_line2"></div>
					<div class="btn_box">
						<button class="btn submit btn_160_36" type="button" btn-type='true'>提交</button>
						<button class="btn cancel btn_160_36" type="button" onclick="goBack();">返回</button>
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<script id="chooseFlashWin" type="text/x-dot-template">
	<div class="geshi1 p20">
		
		<div class="items" onmouseover="hoverModule(1)"  onclick="chooseModule(0)"><img src="${ctx}/resources/img/quizs/manual_normal.png"></img></div>
		<div class="items" onmouseover="hoverModule(2)" onclick="chooseModule(1)"><img src="${ctx}/resources/img/quizs/auto_normal.png"></img></div>

		<div class="items-span"  onmouseover="hoverModule(1)"  onclick="chooseModule(0)"><span>手动创建试卷</span></div>
		<div class="items-span" onmouseover="hoverModule(2)" onclick="chooseModule(1)"><span>自动创建试卷</span></div>


		<!--a href="javascript:void(0)" class="btn1" onmouseover="hoverModule(1)"  onclick="chooseModule(0)">手动创建试卷</a>
		<a href="javascript:void(0)" class="btn2" onmouseover="hoverModule(2)" onclick="chooseModule(1)">自动创建试卷</a-->
	</div>
	<div class="mt20 f12 c929292 p20" id="format1">
		用户根据自身要求，可以通过拖拽，以及所见即所得的方式手动创建试卷，试题。
	</div>
	<div class="mt20 f12 c929292 p20" id="format2" style="display:none">
		用户根据自身要求，通过选择相关的分类，题库，以及设置必考，随机的题目，使系统自动创建用户所需要的试卷。
	</div>
</script>
<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<script type="text/javascript">
	require([ 'modules/quizs/quiz', 'modules/quizs/quiz_examination_profile' ], function() {
		window.onbeforeunload = updateEditState;
        window.onhashchange = updateEditState;
	});
</script>
</body>
</html>