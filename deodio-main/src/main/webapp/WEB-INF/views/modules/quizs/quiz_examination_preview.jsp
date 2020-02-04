<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<body>
	<div class="content">
		<div class="pre_center" style="position: absolute;">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar"
					class="pre_left_top_fl pre_right_top_normal">
					<c:choose>
						<c:when test="${navTabs==1}">
							<ul class="top_nav">
								<li class="no1_on">设置基本信息</li>
								<li class="no2_on">设置题目内容</li>
								<li class="no3_on">试卷预览</li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="top_nav6" id="type2">
								<li class="no1_on">设置基本信息</li>
								<li class="no2_on">设置出题规则</li>
								<li class="no3_on">设置必考题目</li>
								<li class="no4_on">设置随机题目</li>
								<li class="no5_on">试卷预览</li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active" id="tiku">
			<div  class="pull-right" style="margin-right:66px;font-size:16px" >当前试卷总分
			<a style="color:red">：${count_score}</a>
			</div>
				<div style="margin-top: 100px;padding:20px;">
					<div id="draggableContent" class="ti_left" style="min-height: 980px; min-width: 1200px;margin-right:0px;">
						<c:forEach items="${dataList}" var="items" varStatus="status">
							<c:choose>
								<c:when test="${items.quiz_subject_type==1}">
									<div class="chouti mt20">
											<div class="edit_title">
												<span class="pull-left _quiz-order">${status.count}</span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name} 
													<div class="pull-right" style="margin-right:15px">题目分数：
														<a style="color:red">${items.quiz_subject_score}</a>
													</div>
												</div>
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<c:forTokens items="${items.option_answer}" delims="#"
													var="optionAnswer" varStatus="a">
													<c:if test="${c.count==a.count}">
														<div class="ti">
															<div class="radio-group pull-left">
																<input type="radio"
																	
																	name="quiz${items.id}" id="${items.id}_${c.count}"
																	value="${c.count}"> <label
																	class="radio-2 "
																	for="${items.id}_${c.count}"></label>
<%-- 																<input type="radio"
																	<c:if test="${optionAnswer==1}">  checked="checked"</c:if>
																	name="quiz${items.id}" id="${items.id}_${c.count}"
																	value="${c.count}"> <label
																	class="radio-2 <c:if test="${optionAnswer==1}">checked</c:if>"
																	for="${items.id}_${c.count}"></label> --%>
															</div>
															<div class="ti_input_w pull-left ml20 pt8">
																${options}
															</div>
														</div>
													</c:if>
												</c:forTokens>
											</c:forTokens>
										</div>
								</c:when>

								<c:when test="${items.quiz_subject_type==2}">
									<div class="chouti mt20">
											<div class="edit_title">
												<span class="pull-left _quiz-order">${status.count}</span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name} 
													<div class="pull-right" style="margin-right:15px">题目分数：
														<a style="color:red">${items.quiz_subject_score}</a>
													</div>
												</div>
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<c:forTokens items="${items.option_answer}" delims="#"
													var="optionAnswer" varStatus="a">
													<c:if test="${c.count==a.count}">
														<div class="ti">
															<div class="checkbox-group pull-left">
																<input type="checkbox"
																	
																	name="quiz${items.id}" id="${items.id}_${c.count}"
																	value="${c.count}"> <label
																	class="checkbox-2"
																	for="${items.id}_${c.count}"></label>
<%-- 																<input type="checkbox"
																	<c:if test="${optionAnswer==1}">checked="checked"</c:if>
																	name="quiz${items.id}" id="${items.id}_${c.count}"
																	value="${c.count}"> <label
																	class="checkbox-2 <c:if test="${optionAnswer==1}">checked</c:if>"
																	for="${items.id}_${c.count}"></label> --%>
															</div>
															<div class="ti_input_w pull-left ml20 pt8">
																${options}
															</div>
														</div>
													</c:if>
												</c:forTokens>
											</c:forTokens>
										</div>
								</c:when>

								<c:when test="${items.quiz_subject_type==3}">
									<div class="chouti mt20">
											<div class="edit_title">
												<span class="pull-left _quiz-order">${status.count}</span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name} 
													<div class="pull-right" style="margin-right:15px">题目分数：
														<a style="color:red">${items.quiz_subject_score}</a>
													</div>
												</div>
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<c:forTokens items="${items.option_answer}" delims="#"
													var="optionAnswer" varStatus="a">
													<c:if test="${c.count==a.count}">
														<div class="ti">
															<div class="radio-group pull-left">
																<input type="radio"
																	
																	name="quiz${items.id}" id="${items.id}_${c.count}" value="${c.count}">
																<label class="radio-2 "
																	for="${items.id}_${c.count}"></label>
<%-- 																<input type="radio"
																	<c:if test="${optionAnswer==1}">  checked="checked"</c:if>
																	name="quiz${items.id}" id="${items.id}_${c.count}" value="${c.count}">
																<label class="radio-2 <c:if test="${optionAnswer==1}">  checked</c:if>"
																	for="${items.id}_${c.count}"></label> --%>
															</div>
															<div class="ti_input_w pull-left ml20 mt10">${options}</div>
														</div>
													</c:if>
												</c:forTokens>
											</c:forTokens>
										</div>
								</c:when>

								<c:when test="${items.quiz_subject_type==4}">
									<div class="chouti mt20" id="${items.id}" stype="4">
									
									<input type="hidden" id="hidden-------------------">
										<div class="edit_title">
											<span class="pull-left _quiz-order">${status.count}</span>
											<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name} 
													<div class="pull-right" style="margin-right:15px">题目分数：
														<a style="color:red">${items.quiz_subject_score}</a>
													</div>
												</div>
										</div>
										<c:forTokens items="${items.item_options}" delims="#" var="options" varStatus="c">

											<div class="ti">
												<div class="pull-left pt8">${c.count}</div>
												<div class="ti_input_w pull-left ml20">
													<input type="text" class="form-control" value="${options}">
												</div>
											</div>
										</c:forTokens>
									</div>
								</c:when>
								
								



                               <c:when test="${items.quiz_subject_type==5}">
									<div class="chouti mt20" id="${items.id}" stype="5">
										<div class="edit_title">
											<span class="pull-left _quiz-order">${status.count}</span>
											<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name} 
													<div class="pull-right" style="margin-right:15px">题目分数：
														<a style="color:red">${items.quiz_subject_score}</a>
													</div>
												</div>
										</div>
										<div class="tuxing p20">
											<div class="l_pic" style="background:url(${items.attach_url}) center no-repeat;background-size: 100%  100%;">
											
											</div>
											<textarea name="" id="" cols="30" rows="10" class="pic_textarea ml20 pull-left">${items.item_options}</textarea>
											<div class="clearfix"></div>
										</div>
									</div>
								</c:when>

								<c:when test="${items.quiz_subject_type==6}">
									<div class="chouti mt20" id="${items.id}" stype="6">
										<div class="edit_title">
											<span class="pull-left _quiz-order">${status.count}</span>
											<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
										</div>
										<div class="jianda mt20">
											<textarea name="" id="">${items.item_options}</textarea>
										</div>
									</div>
								</c:when>
								
								<c:when test="${items.quiz_subject_type==7}">
									<div class="chouti mt20" id="${items.id}" stype="7">
										<div class="edit_title">
											<span class="pull-left _quiz-order">${status.count}</span>
											<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
										</div>
										<div class="tiankong mt20">
											<textarea name="" id="">${items.item_options}</textarea>
										</div>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<div class="btn_box">
				    <button type="button" onclick="onSave(0);" class="btn submit btn_160_36">保存</button>
					<button type="button" onclick="onSave(1);" class="btn submit btn_160_36">发布</button>
					<button type="button" class="btn cancel btn_160_36" onclick="goBack();">返回</button>
				</div>
                <input type="hidden" id="quizId" name="quizId" value="${quizId}">
                <input type="hidden" id="eFlag" name="eFlag" value="${eFlag}">
                <input type="hidden" id="navTabs" name="navTabs" value="${navTabs}">
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/quizs/quiz_examination_preview']);
	</script>
</body>
</html>