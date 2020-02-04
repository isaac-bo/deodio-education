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
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar"
					class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav" id="type1">
						<li class="no1_on" >设置基本信息</li>
						<li class="no2_on" >设置调查内容</li>
						<!-- <li class="no3" >调查预览</li> -->
					</ul>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>
		
		<form action="${ctx}/survey/preview.html" id="saveForm" method="post">

			<div id="myTabContent" class="tab-content">
				<div class="tab-pane active" id="tiku" style="margin-top: 80px;padding: 20px;">
					<div class="mt20">
						<div class="ti_right">
							<div id="radio_draggable" class="chouqu">
								<img src="${ctx}/resources/img/quizs/s_danxuan.png" alt="">

							</div>
							<div id="checkbox_draggable" class="chouqu">
								<img src="${ctx}/resources/img/quizs/s_duoxuan.png" alt="">

							</div>
							<div id="alternative_draggable" class="chouqu">
								<img src="${ctx}/resources/img/quizs/s_panduan.png" alt="">

							</div>
							<div id="shortAnswer_draggable" class="chouqu">
									<img src="${ctx}/resources/img/quizs/s_jianda.png" alt="">
								
								</div>
						</div>

						<div id="draggableContent" class="ti_left" style="min-height: 980px;">

							<c:forEach items="${dataList}" var="items">

								<c:choose>
									<c:when test="${items.survey_subject_type==1}">
										<div class="chouti" qtype="1">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
												<span class="pull-left _quiz-order"></span>
												<input class="form-control" type="text"
													value="${items.survey_subject}" check-type="required"
													required-message="请输入题目名称！" placeholder="请输入题目名称"
													aria-describedby="basic-addon1">
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="radio-group pull-left">
														<input type="radio"
															<c:if test="${items.survey_subject_answer==c.count}">  checked="checked"</c:if>
															name="quiz${items.id}" id="${items.id}_${c.count}"
															value="${c.count}"> <label
															class="radio-2 <c:if test="${items.quiz_subject_answer==c.count}">checked</c:if>"
															for="${items.id}_${c.count}"></label>
													</div>
													<div class="ti_input_w pull-left ml20">
														<input type="text" class="form-control"
															value="${options}" check-type="required"
															required-message="请输入题目内容！" placeholder="请输入题目内容"
															aria-describedby="basic-addon1">
													</div>
													<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
													<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,1);"></button>
												</div>
											</c:forTokens>
										</div>
									</c:when>

									<c:when test="${items.survey_subject_type==2}">
										<div class="chouti mt20" qtype="2">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
												<span class="pull-left _quiz-order"></span>
												<input class="form-control" type="text"
													value="${items.survey_subject}" check-type="required"
													required-message="请输入题目名称！" placeholder="请输入题目名称"
													aria-describedby="basic-addon1">
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="checkbox-group pull-left">
														<input type="checkbox"
															<c:if test="${fn:contains(items.survey_subject_answer, c.count)}">checked="checked"</c:if>
															name="quiz${items.id}" id="${items.id}_${c.count}"
															value="${c.count}"> <label
															class="checkbox-2 <c:if test="${fn:contains(items.survey_subject_answer, c.count)}">checked</c:if>"
															for="${items.id}_${c.count}"></label>
													</div>
													<div class="ti_input_w pull-left ml20">
														<input type="text" class="form-control" value="${options}"
															check-type="required" required-message="请输入题目内容！"
															placeholder="请输入题目内容" aria-describedby="basic-addon1">
													</div>

													<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
													<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,2);"></button>
												</div>
											</c:forTokens>

										</div>
									</c:when>

									<c:when test="${items.survey_subject_type==3}">
										<div class="chouti mt20" qtype="3">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
												<span class="pull-left _quiz-order"></span>
												<input class="form-control" type="text"
													value="${items.survey_subject}" check-type="required"
													required-message="请输入题目名称！" placeholder="请输入题目名称"
													aria-describedby="basic-addon1">
											</div>

											<div class="ti">
												<div class="radio-group pull-left">
													<input type="radio"
														<c:if test="${items.survey_subject_answer==1}">  checked="checked"</c:if>
														name="quiz${items.id}" id="${items.id}_1" value="1">
													<input type="hidden" name="quiz${items.id}" id="hidden_${items.id}_1" value="1">
													<label class="radio-2"
														<c:if test="${items.survey_subject_answer==1}">  checked="checked"</c:if>
														for="${items.id}_1"></label>
												</div>
												<div class="ti_input_w pull-left ml20 mt10">对</div>
											</div>
											<div class="ti">
												<div class="radio-group pull-left">
													<input type="radio"
														<c:if test="${items.survey_subject_answer==2}">  checked="checked"</c:if>
														name="quiz${items.id}" id="${items.id}_2" value="2">
													<input type="hidden" name="quiz${items.id}" id="hidden_${items.id}_2" value="2">
													<label class="radio-2"
														<c:if test="${items.survey_subject_answer==2}">  checked="checked"</c:if>
														for="${items.id}_2"></label>
												</div>
												<div class="ti_input_w pull-left ml20 mt10">错</div>
											</div>

										</div>
									</c:when>
									<c:when test="${items.survey_subject_type==6}">
										<div class="chouti mt20"  qtype="6">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left _quiz-order"></span>
												<input class="form-control" type="text"
													value="${items.survey_subject}">
											</div>

											<div class="jianda">
												<textarea name="" id="">${items.item_options}</textarea>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>



						</div>
						<input value="" type="hidden" id="hiddataStr" name="dataStr">
						<input value="${surveyId}" type="hidden" id="surveyId" name="surveyId">

					</div>
					<div class="btn_box">
						<button type="button" btn-type="true" class="btn submit btn_160_36">保存／下一步</button>
						<button type="button" class="btn cancel btn_160_36" onclick="onEditSurvey('${surveyId}')">返回</button>
					</div>

				</div>
			</div>
		</form>
	</div>


	<input type="hidden" id="draggableVle" value="">
	<input type="hidden" id="quiz_or_survey" value="survey">

	<%@ include file="/WEB-INF/views/modules/quizs/quiz_template.jsp"%>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/quizs/quiz','modules/survey/survey_content'],function(survey,obj){
			draggableFun('radio_draggable',1);
			draggableFun('checkbox_draggable',2);
			draggableFun('alternative_draggable',3);
			draggableFun('shortAnswer_draggable',6);
			window.onbeforeunload = updateSurveyEditState;
	        window.onhashchange = updateSurveyEditState;
		});
	</script>


</body>
</html>