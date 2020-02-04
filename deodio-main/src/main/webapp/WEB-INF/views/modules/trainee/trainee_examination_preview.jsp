<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 试卷预览弹出 -->
<div class="modal fade" id="quizLearnModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header" style="background: #43b4c6">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">考试</h4>
			</div>
			<div class="modal-body hdn">
				<div id="quizDraggableContent" class="ti_left"
					style="min-height: 500px; min-width: 100%;">
					<c:forEach items="${dataList}" var="items" varStatus="status">
						<c:choose>
							<c:when test="${items.quiz_subject_type==1}">
								<div class="chouti mt20" qtype="1">
									<div class="edit_title" style="width: 100%">
										<span class="pull-left _quiz-order">${items.quiz_subject_order + 1}</span>
										<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
											class="pl10">${items.quiz_subject_name}</div>
									</div>
									<c:forTokens items="${items.item_options}" delims="#"
										var="options" varStatus="c">
										<c:forTokens items="${items.option_answer}" delims="#"
											var="optionAnswer" varStatus="a">
											<c:if test="${c.count==a.count}">
												<div class="ti">
													<div class="radio-group pull-left">
														<input type="radio" name="quiz${items.id}"
															id="${items.id}_${c.count}" value="${c.count}"> <label
															class="radio-2 " for="${items.id}_${c.count}"></label>
													</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${options}</div>
												</div>
											</c:if>
										</c:forTokens>
									</c:forTokens>
								</div>
							</c:when>
							<c:when test="${items.quiz_subject_type==2}">
								<div class="chouti mt20" qtype="2">
									<div class="edit_title" style="width: 100%">
										<span class="pull-left _quiz-order">${items.quiz_subject_order + 1}</span>
										<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
											class="pl10">${items.quiz_subject_name}</div>
									</div>
									<c:forTokens items="${items.item_options}" delims="#"
										var="options" varStatus="c">
										<c:forTokens items="${items.option_answer}" delims="#"
											var="optionAnswer" varStatus="a">
											<c:if test="${c.count==a.count}">
												<div class="ti">
													<div class="checkbox-group pull-left">
														<input type="checkbox" name="quiz${items.id}"
															id="${items.id}_${c.count}" value="${c.count}"> <label
															class="checkbox-2" for="${items.id}_${c.count}"></label>
														<%-- 																<input type="checkbox"
																	<c:if test="${optionAnswer==1}">checked="checked"</c:if>
																	name="quiz${items.id}" id="${items.id}_${c.count}"
																	value="${c.count}"> <label
																	class="checkbox-2 <c:if test="${optionAnswer==1}">checked</c:if>"
																	for="${items.id}_${c.count}"></label> --%>
													</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${options}</div>
												</div>
											</c:if>
										</c:forTokens>
									</c:forTokens>


								</div>


							</c:when>


							<c:when test="${items.quiz_subject_type==3}">

								<div class="chouti mt20" qtype="3">
									<div class="edit_title" style="width: 100%">
										<span class="pull-left _quiz-order">${items.quiz_subject_order + 1}</span>
										<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
											class="pl10">${items.quiz_subject_name}</div>
									</div>
									<c:forTokens items="${items.item_options}" delims="#"
										var="options" varStatus="c">
										<c:forTokens items="${items.option_answer}" delims="#"
											var="optionAnswer" varStatus="a">
											<c:if test="${c.count==a.count}">
												<div class="ti">
													<div class="radio-group pull-left">
														<input type="radio" name="quiz${items.id}"
															id="${items.id}_${c.count}" value="${c.count}"> <label
															class="radio-2 " for="${items.id}_${c.count}"></label>
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

								<div class="chouti mt20" qtype="4">
									<div class="edit_title" style="width: 100%">
										<span class="pull-left _quiz-order">${items.quiz_subject_order + 1}</span>
										<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
											class="pl10">${items.quiz_subject_name}</div>
									</div>
									<c:forTokens items="${items.item_options}" delims="#"
										var="options" varStatus="c">

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

								<div class="chouti mt20" qtype="5">
									<div class="edit_title" style="width: 100%">
										<span class="pull-left _quiz-order">${items.quiz_subject_order + 1}</span>
										<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
											class="pl10">${items.quiz_subject_name}</div>
									</div>

									<div class="tuxing p20">
										<div class="l_pic"
											style="background:url(${items.attach_url}) center no-repeat;background-size: 100%  100%;">
										</div>
										<textarea name="" id="" cols="30" rows="10"
											class="pic_textarea ml20 pull-left" style="width: 632px;">${items.item_options}</textarea>
										<div class="clearfix"></div>
									</div>

								</div>


							</c:when>

							<c:when test="${items.quiz_subject_type==6}">

								<div class="chouti mt20" qtype="6">
									<div class="edit_title" style="width: 100%">
										<span class="pull-left _quiz-order">${items.quiz_subject_order + 1}</span>
										<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
											class="pl10">${items.quiz_subject_name}</div>
									</div>

									<div class="jianda mt10">
										<textarea name="" id="">${items.item_options}</textarea>
									</div>

								</div>


							</c:when>

							<c:when test="${items.quiz_subject_type==7}">

								<div class="chouti mt20" qtype="7">
									<div class="edit_title" style="width: 100%">
										<span class="pull-left _quiz-order">${items.quiz_subject_order + 1}</span>
										<div style="background: #cdd5d9; border-radius: 0 2px 0 0;"
											class="pl10">${items.quiz_subject_name}</div>
									</div>

									<div class="jianda mt10">
										<textarea name="" id="">${items.item_options}</textarea>
									</div>

								</div>
							</c:when>
						</c:choose>
					</c:forEach>

				</div>
			</div>
			<div class="modal-footer">
				<div class="btn_box">
					<button type="button" class="btn cancel btn_160_36"
						data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
		<input type="hidden" id="hiddenCourseId">
	</div>
</div>





<script type="text/javascript">
	require([ 'modules/trainee/trainee_learn_dialogue' ], function() {

	});
</script>