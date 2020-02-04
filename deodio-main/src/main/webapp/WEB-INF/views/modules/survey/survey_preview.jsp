<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<%@page import="com.deodio.core.utils.StringUtils"%>
	
	<div class="content">
	 	<div class="pre_center" style="position: absolute;">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar"
					class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav" id="type1">
						<li class="no1_on" >设置基本信息</li>
						<li class="no2_on" >设置调查内容</li>
						<li class="no3_on" >调查预览</li>
					</ul>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active row-fluid" id="tiku">
				<div class="mt20" style="padding: 20px;padding-top: 0px;">
					<div id="draggableContent" class="ti_left" style="min-height:800px;margin-top: 100px;padding: 20px;margin-right: 0px;">


						<c:forEach items="${dataList}" var="items">
						<input id="quiz_subject_type" type="hidden" value="${items.quiz_subject_type}">
							<c:choose>
								<c:when test="${items.survey_subject_type==1}">
									<div class="chouti" qtype="1">
										<div class="edit_title">

											<span class="pull-left _subject-order"></span>
											<div class="pl10"
												style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.survey_subject}</div>
										</div>
										<c:forTokens items="${items.item_options}" delims="#"
											var="options">
											<div class="ti">
												<div class="radio-group pull-left">
													<input type="radio" name="sex" id="man" value=""> <label
														class="radio-2" for="man"></label>
												</div>
												<div class="ti_input_w pull-left ml20 pt8">${options}
												</div>
											</div>
										</c:forTokens>
									</div>
								</c:when>

								<c:when test="${items.survey_subject_type==2}">
									<div class="chouti mt20" qtype="2">
										<div class="edit_title">

											<span class="pull-left _subject-order"></span>
											<div class="pl10"
												style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.survey_subject}</div>
										</div>
										<c:forTokens items="${items.item_options}" delims="#"
											var="options">
											<div class="ti">
												<div class="checkbox-group pull-left">
													<input type="checkbox" name="remember2" id="three2">
													<label class="checkbox-2" for="three2"></label>
												</div>
												<div class="ti_input_w pull-left ml20 pt8">${options}
												</div>

											</div>
										</c:forTokens>


									</div>
								</c:when>

								<c:when test="${items.survey_subject_type==3}">
									<div class="chouti mt20" qtype="3">
										<div class="edit_title">

											<span class="pull-left _subject-order"></span>
											<div class="pl10"
												style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.survey_subject}</div>
										</div>

										<div class="ti">
											<div class="radio-group pull-left">
												<input type="radio" name="sex" id="man" value=""> <label
													class="radio-2" for="man"></label>
											</div>
											<div class="ti_input_w pull-left ml20 mt10">对</div>
										</div>
										<div class="ti">
											<div class="radio-group pull-left">
												<input type="radio" name="sex" id="man" value=""> <label
													class="radio-2" for="man"></label>
											</div>
											<div class="ti_input_w pull-left ml20 mt10">错</div>
										</div>

									</div>
								</c:when>

								<c:when test="${items.survey_subject_type==4}">
									<div class="chouti mt20" qtype="4">
										<div class="edit_title">

											<span class="pull-left _subject-order"></span>
											<div class="pl10"
												style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.survey_subject}</div>
										</div>
										<c:forTokens items="${el:randomFun(items.item_options)}"
											delims="#" var="options" varStatus="c">
											<div class="ti">
												<div class="pull-left pt8">${c.count}</div>
												<div class="ti_input_w pull-left ml20 pt8">${options}
												</div>

											</div>
										</c:forTokens>
									</div>
								</c:when>
								<c:when test="${items.survey_subject_type==6}">
									<div class="chouti mt20" qtype="6">
										<div class="edit_title">

											<span class="pull-left _subject-order"></span>
											<div class="pl10"
												style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.survey_subject}</div>
										</div>
										<div class="jianda mt20">
											<textarea name="" id="">${items.item_options}</textarea>
										</div>
										<%-- <c:forTokens items="${el:randomFun(items.item_options)}"
											delims="#" var="options" varStatus="c">
											<div class="ti">
												<div class="pull-left pt8">${c.count}</div>
												<div class="ti_input_w pull-left ml20 pt8">${options}
												</div>

											</div>
										</c:forTokens> --%>
									</div>
									<%-- <div class="chouti mt20" id="${items.id}" stype="6">
										<div class="edit_title">

											<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
											<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
										</div>
										<div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
										<div class="jianda">
											<textarea name="" id="">${items.item_options}</textarea>
										</div>
									</div> --%>
								</c:when>

							</c:choose>

						</c:forEach>



					</div>





				</div>
				<input name="surveyId" value="${surveyId}" id="surveyId" type="hidden">
				<div class="btn_box">
					<button type="button" class="btn submit btn_160_36" onClick="onSaveSurvey(0)">保存</button>
					<button type="button" class="btn submit btn_160_36" onClick="onSaveSurvey(1)">发布</button>
					<button type="button" class="btn cancel btn_160_36" onclick="backSurveyContent()">返回</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/modules/quizs/quiz_template.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require([ 'modules/quizs/quiz', 'modules/survey/survey_preview' ],function(){
			window.onbeforeunload = updateSurveyEditState;
	        window.onhashchange = updateSurveyEditState;
		});

	</script>


</body>
</html>