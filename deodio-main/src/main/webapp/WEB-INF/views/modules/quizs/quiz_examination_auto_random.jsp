<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<body>
	<%@page import="com.deodio.core.utils.StringUtils"%>
	<div class="content">
		<div class="pre_center" style="position: absolute;">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar"
					class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav6">
						<li class="no1_on">设置基本信息</li>
						<li class="no2_on">设置出题规则</li>
						<li class="no3_on">设置必考题目</li>
						<li class="no4_on">设置随机题目</li>
						<li class="no5">试卷预览</li>
					</ul>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>
		<div id="myTabContent" class="tab-content" style="margin-top: 100px;padding: 20px;padding-top: 0px;">
			<div class="c929292 f12 mt10">如不需要设置随机题，请跳到下一步。</div>
				<div class="mt20">
					<div id="draggableContent" class="ti_left"
						style="min-height: 980px; min-width: 1200px;">
						<c:forEach var="items" items="${list}">
							<c:forEach items="${items}" var="item">
								<c:choose>
									<c:when test="${item.quiz_subject_type==1}">
										<div class="chouti mt20" id="${item.id}" sutype="1">
											<div class="edit_title">
												<span class="pull-left _quiz-order"></span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
													class="pl10">${item.quiz_subject_name}</div>
											</div>
											<c:forTokens items="${item.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="radio-group pull-left">
														<input type="radio"
															value="${el:arrayOutIndex(item.option_answer_id,'#',c.index)}"
															<c:if test="${el:isCorrect(item.option_answer,c.index)=='1'}">checked="checked"</c:if>
															id="man" name="subject${item.id}"> <label
															for="man"
															class="radio-2<c:if test="${el:isCorrect(item.option_answer,c.index)=='1'}"> checked</c:if>"></label>
													</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${fn:trim(options)}</div>
												</div>
											</c:forTokens>

										</div>
									</c:when>

									<c:when test="${item.quiz_subject_type==2}">
										<div class="chouti mt20" id="${item.id}" sutype="2">
											<div class="edit_title">
												<span class="pull-left _quiz-order"></span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
													class="pl10">${item.quiz_subject_name}</div>
											</div>
											<c:forTokens items="${item.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="checkbox-group pull-left">
														<input type="checkbox"
															value="${el:arrayOutIndex(item.option_answer_id,'#',c.index)}"
															<c:if test="${el:isCorrect(item.option_answer,c.index)=='1'}">checked="checked"</c:if>
															id="${item.id}_${c.index}" name="subject${item.id}">
														<label for="${item.id}_${c.index}"
															class="checkbox-2 <c:if test="${el:isCorrect(item.option_answer,c.index)=='1'}"> checked</c:if>"></label>
													</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${fn:trim(options)}</div>
												</div>
											</c:forTokens>

										</div>
									</c:when>

									<c:when test="${item.quiz_subject_type==3}">
										<div class="chouti mt20" id="${item.id}" sutype="3">
											<div class="edit_title">
												<span class="pull-left _quiz-order"></span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
													class="pl10">${item.quiz_subject_name}</div>
											</div>
											<div class="ti">
												<div class="radio-group pull-left">
													<input type="radio"
														value="${el:arrayOutIndex(item.option_answer_id,'#',0)}"
														<c:if test="${el:isCorrect(item.option_answer,0)=='1'}">checked="checked"</c:if>
														name="subject${item.id}" id="_1"> <label
														class="radio-2 <c:if test="${el:isCorrect(item.option_answer,0)=='1'}">checked</c:if>"
														for="_1"></label>
												</div>
												<div class="ti_input_w pull-left ml20 mt10">对</div>
											</div>
											<div class="ti">
												<div class="radio-group pull-left">
													<input type="radio"
														value="${el:arrayOutIndex(item.option_answer_id,'#',1)}"
														<c:if test="${el:isCorrect(item.option_answer,1)=='1'}">checked="checked"</c:if>
														name="subject${item.id}" id="_2"> <label
														class="radio-2 <c:if test="${el:isCorrect(item.option_answer,1)=='1'}">checked</c:if>"
														for="_2"></label>
												</div>
												<div class="ti_input_w pull-left ml20 mt10">错</div>
											</div>
										</div>
									</c:when>

									<c:when test="${item.quiz_subject_type==4}">
										<div class="chouti mt20" id="${item.id}" sutype="4">
											<div class="edit_title">
												<span class="pull-left _quiz-order"></span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
													class="pl10">${item.quiz_subject_name}</div>
											</div>
											<c:forTokens items="${item.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="pull-left pt8">${c.count}</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${fn:trim(options)}</div>
												</div>
											</c:forTokens>
										</div>
									</c:when>

									<c:when test="${item.quiz_subject_type==5}">
										<div class="chouti mt20" id="${item.id}" sutype="5">
											<div class="edit_title">
												<span class="pull-left _quiz-order"></span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
													class="pl10">${item.quiz_subject_name}</div>
											</div>
											<div class="tuxing p20">
												<div class="l_pic">
												    <img src="${item.attach_url}" id="userIdCardImg" width="160">
												</div>
												<textarea name="" id="" cols="30" rows="10" readonly="readonly"
													class="pic_textarea ml20 pull-left">${item.item_options}</textarea>
												<div class="clearfix"></div>
											</div>
										</div>
									</c:when>

                                   <c:when test="${item.quiz_subject_type==6}">
										<div class="chouti mt20" id="${item.id}" sutype="6">
											<div class="edit_title">
												<span class="pull-left _quiz-order"></span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
													class="pl10">${item.quiz_subject_name}</div>
											</div>
											<div class="jianda p20">
												<textarea name="" id="" readonly="readonly">${item.item_options}</textarea>
											</div>
										</div>
									</c:when>

									<c:when test="${item.quiz_subject_type==7}">
										<div class="chouti mt20" id="${item.id}" sutype="7">
											<div class="edit_title">
												<span class="pull-left _quiz-order"></span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
													class="pl10">${item.quiz_subject_name}</div>
											</div>
											<div class="jianda p20">
												<textarea name="" id="" readonly="readonly">${item.item_options}</textarea>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:forEach>
					</div>
				</div>
				<input type="hidden" id="quizId" name="quizId" value="${quizId}">
				<input type="hidden" id="eFlag" name="eFlag" value="${eFlag}">
				<input type="hidden" id="subjectIds" name="subjectIds" value="${subjectIds}">
				<input type="hidden" id="answers" name="answers" value="${answers}">
				<input type="hidden" id="bankScope" name="bankScope" value="${bankScope}">
				<input type="hidden" id="navTabs" name="navTabs" value="2"> 
				<div class="btn_box">
					<button type="button" onclick="onPreview();" class="btn submit btn_160_36">保存／下一步</button>
					<button type="button" class="btn cancel btn_160_36" onclick="goBack();">返回</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/quizs/quiz','modules/quizs/quiz_examination_auto_random' ],function(obj){
	        window.onbeforeunload = updateEditState;
	        window.onhashchange = updateEditState;
	    });
	</script>
</body>
</html>