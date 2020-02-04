<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<div class="modal fade" id="quizViewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width: 800px;">
		<div class="modal-content" id="viewShareModalContent">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">试卷预览</h4>
			</div>
			<div class="modal-body hdn">
				<div id="right_content_quiz_view">
					<div id="draggableContent" class="ti_left" style="min-height: 980px; min-width: 1200px;">
						<c:forEach items="${dataList}" var="items">
							<c:choose>
								<c:when test="${items.quiz_subject_type==1}">
									<div class="chouti mt20">
											<div class="edit_title">
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name}</div>
											</div>
											<div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<c:forTokens items="${items.option_answer}" delims="#"
													var="optionAnswer" varStatus="a">
													<c:if test="${c.count==a.count}">
														<div class="ti">
															<div class="radio-group pull-left">
																<input type="radio"
																	<c:if test="${optionAnswer==1}">  checked="checked"</c:if>
																	name="quiz${items.id}" id="${items.id}_${c.count}"
																	value="${c.count}"> <label
																	class="radio-2 <c:if test="${optionAnswer==1}">checked</c:if>"
																	for="${items.id}_${c.count}"></label>
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
<!-- 												<button type="button" onclick="removeQuestion(this);" -->
<!-- 													class="del26 pull-right"></button> -->
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name}</div>
											</div>
											<div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<c:forTokens items="${items.option_answer}" delims="#"
													var="optionAnswer" varStatus="a">
													<c:if test="${c.count==a.count}">
														<div class="ti">
															<div class="checkbox-group pull-left">
																<input type="checkbox"
																	<c:if test="${optionAnswer==1}">checked="checked"</c:if>
																	name="quiz${items.id}" id="${items.id}_${c.count}"
																	value="${c.count}"> <label
																	class="checkbox-2 <c:if test="${optionAnswer==1}">checked</c:if>"
																	for="${items.id}_${c.count}"></label>
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
<!-- 												<button type="button" onclick="removeQuestion(this);" -->
<!-- 													class="del26 pull-right"></button> -->
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">${items.quiz_subject_name}</div>
											</div>
											<div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<c:forTokens items="${items.option_answer}" delims="#"
													var="optionAnswer" varStatus="a">
													<c:if test="${c.count==a.count}">
														<div class="ti">
															<div class="radio-group pull-left">
																<input type="radio"
																	<c:if test="${optionAnswer==1}">  checked="checked"</c:if>
																	name="quiz${items.id}" id="${items.id}_${c.count}" value="${c.count}">
																<label class="radio-2 <c:if test="${optionAnswer==1}">  checked</c:if>"
																	for="${items.id}_${c.count}"></label>
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
										<div class="edit_title">
											<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
											<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
										</div>
										<div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
										<c:forTokens items="${el:randomFun(items.item_options)}"
											delims="#" var="options" varStatus="c">
											<div class="ti">
												<div class="pull-left pt8">
													<input type="text" class="form-control w50 text-center" id="${items.id}_${c.count}">
												</div>
												<div class="ti_input_w pull-left ml20 pt8">${options}
												</div>

											</div>
										</c:forTokens>
									</div>
								</c:when>
                               <c:when test="${items.quiz_subject_type==5}">
									<div class="chouti mt20" id="${items.id}" stype="5">
										<div class="edit_title">
											<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
											<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
										</div>
										<div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
										<div class="tuxing p20">
											<div class="l_pic" style="background:url(${items.attach_url}) center no-repeat;background-size: 100%  100%">
										
											</div>
											<textarea name="" id="" cols="30" rows="10" class="pic_textarea ml20 pull-left">${items.item_options}</textarea>
											<div class="clearfix"></div>
										</div>
									</div>
								</c:when>
								<c:when test="${items.quiz_subject_type==6}">
									<div class="chouti mt20" id="${items.id}" stype="6">
										<div class="edit_title">
											<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
											<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
										</div>
										<div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
										<div class="jianda">
											<textarea name="" id="">${items.item_options}</textarea>
										</div>
									</div>
								</c:when>
								<c:when test="${items.quiz_subject_type==7}">
									<div class="chouti mt20" id="${items.id}" stype="7">
										<div class="edit_title">
											<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
											<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
										</div>
										<div class="tiankong">
											<textarea name="" id="">${items.item_options}</textarea>
										</div>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="btn_box">
					<button type="button" class="btn cancel btn_160_36" data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
	</div>
</div>




<script type="text/javascript">
	require(['modules/quizs/quiz_view_dialogue'],function(){	
	});
</script>