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
				<div id="div_tables_bar" class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav6">
						<li class="no1_on">设置基本信息</li>
						<li class="no2_on">设置出题规则</li>
						<li class="no3_on">设置必考题目</li>
						<li class="no4">设置随机题目</li>
						<li class="no5">试卷预览</li>
					</ul>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active" id="tiku" style="margin-top: 100px;padding: 20px;padding-top: 0px;">
				<div class="c929292 f12 mt10">如不需要设置必考题，请跳到下一步。</div>
				<div class="mt20">
					<div class="ti_right">
						<div class="chouqu">
							<c:choose>
								<c:when test='${maps["1"]==null}'>
									<img alt="" src="${ctx}/resources/img/quizs/pic_danxuan.png">
									<span subtype="1">0</span>
								</c:when>
								<c:otherwise>
									<img onclick="onPopupRequiredSubject(1,this,1);" alt=""
										src="${ctx}/resources/img/quizs/pic_danxuan.png">
									<span subtype="1">${maps["1"]}</span>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="chouqu">
							<c:choose>
								<c:when test='${maps["2"]==null}'>
									<img alt="" src="${ctx}/resources/img/quizs/pic_duoxuan.png">
									<span subtype="2">0</span>
								</c:when>
								<c:otherwise>
									<img onclick="onPopupRequiredSubject(2,this,1);" alt=""
										src="${ctx}/resources/img/quizs/pic_duoxuan.png">
									<span subtype="2">${maps["2"]}</span>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="chouqu">
							<c:choose>
								<c:when test='${maps["3"]==null}'>
									<img alt="" src="${ctx}/resources/img/quizs/pic_panduan.png">
									<span subtype="3">0</span>
								</c:when>
								<c:otherwise>
									<img onclick="onPopupRequiredSubject(3,this,1);" alt=""
										src="${ctx}/resources/img/quizs/pic_panduan.png">
									<span subtype="3">${maps["3"]}</span>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="chouqu">
							<c:choose>
								<c:when test='${maps["4"]==null}'>
									<img alt="" src="${ctx}/resources/img/quizs/pic_paixu.png">
									<span subtype="4">0</span>
								</c:when>
								<c:otherwise>
									<img onclick="onPopupRequiredSubject(4,this,1);" alt=""
										src="${ctx}/resources/img/quizs/pic_paixu.png">
									<span subtype="4">${maps["4"]}</span>
								</c:otherwise>
							</c:choose>
						</div>
						<c:if test="${quizCategory==2}">
						<div class="chouqu">
							<c:choose>
								<c:when test='${maps["5"]==null}'>
									<img alt="" src="${ctx}/resources/img/quizs/pic_tuxing.png">
									<span subtype="5">0</span>
								</c:when>
								<c:otherwise>
									<img onclick="onPopupRequiredSubject(5,this,1);" alt=""
										src="${ctx}/resources/img/quizs/pic_tuxing.png">
									<span subtype="5">${maps["5"]}</span>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="chouqu">
							<c:choose>
								<c:when test='${maps["6"]==null}'>
									<img alt="" src="${ctx}/resources/img/quizs/pic_jianda.png">
									<span subtype="6">0</span>
								</c:when>
								<c:otherwise>
									<img onclick="onPopupRequiredSubject(6,this,1);" alt=""
										src="${ctx}/resources/img/quizs/pic_jianda.png">
									<span subtype="6">${maps["6"]}</span>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="chouqu">
							<c:choose>
								<c:when test='${maps["7"]==null}'>
									<img alt="" src="${ctx}/resources/img/quizs/pic_tiankong.png">
									<span subtype="7">0</span>
								</c:when>
								<c:otherwise>
									<img onclick="onPopupRequiredSubject(7,this,1);" alt=""
										src="${ctx}/resources/img/quizs/pic_tiankong.png">
									<span subtype="7">${maps["7"]}</span>
								</c:otherwise>
							</c:choose>
						</div>
						</c:if>
					</div>
					<form action="${ctx}/quiz/auto/random.html" id="saveForm" method="post">
						<div id="draggableContent" class="ti_left" style="min-height: 1025px;">
							<c:forEach items="${dataList}" var="items">
								<c:choose>
									<c:when test="${items.quiz_subject_type==1}">
										<div class="chouti _subjct1" id="${items.id}" subtype="1">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left  _subject-order">${items.quiz_subject_order}</span>
												<div class="pl10"
													style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="radio-group pull-left">
														<input type="radio"
															<c:if test="${el:isCorrect(items.option_answer,c.index)=='1'}">checked="checked"</c:if>
															name="quiz${items.id}" id="quiz${items.id}_${c.index}"
															value="${el:arrayOutIndex(items.option_answer_id,'#',c.index)}">
														<label
															class="radio-2<c:if test="${el:isCorrect(items.option_answer,c.index)=='1'}"> checked</c:if>"
															for="quiz${items.id}_${c.index}"></label>
													</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${options}</div>
												</div>
											</c:forTokens>
										</div>
									</c:when>
									<c:when test="${items.quiz_subject_type==2}">
										<div class="chouti mt20 _subjct2" id="${items.id}" subtype="2">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left  _subject-order">${items.quiz_subject_order}</span>
												<div class="pl10"
													style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="checkbox-group pull-left">
														<input type="checkbox"
															value="${el:arrayOutIndex(items.option_answer_id,'#',c.index)}"
															<c:if test="${el:isCorrect(items.option_answer,c.index)=='1'}">checked="checked"</c:if>
															name="quiz${items.id}" id="quiz${items.id}_${c.index}">
														<label
															class="checkbox-2 <c:if test="${el:isCorrect(items.option_answer,c.index)=='1'}">checked</c:if>"
															for="quiz${items.id}_${c.index}"></label>
													</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${options}</div>

												</div>
											</c:forTokens>
										</div>
									</c:when>
									<c:when test="${items.quiz_subject_type==3}">
										<div class="chouti mt20 _subjct3" id="${items.id}" subtype="3">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left  _subject-order">${items.quiz_subject_order}</span>
												<div class="pl10"
													style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
											</div>
											<div class="ti">
												<div class="radio-group pull-left">
													<input type="radio"
														value="${el:arrayOutIndex(items.option_answer_id,'#',0)}"
														<c:if test="${el:isCorrect(items.option_answer,0)=='1'}">checked="checked"</c:if>
														name="quiz${items.id}1" id="quiz${items.id}_1"> <label
														class="radio-2 <c:if test="${el:isCorrect(items.option_answer,0)=='1'}">checked</c:if> "
														for="quiz${items.id}_1"></label>
												</div>
												<div class="ti_input_w pull-left ml20 mt10">对</div>
											</div>
											<div class="ti">
												<div class="radio-group pull-left">
													<input type="radio"
														value="${el:arrayOutIndex(items.option_answer_id,'#',1)}"
														<c:if test="${el:isCorrect(items.option_answer,1)=='1'}">checked="checked"</c:if>
														name="quiz${items.id}2" id="quiz${items.id}_2"> <label
														class="radio-2 <c:if test="${el:isCorrect(items.option_answer,1)=='1'}">checked</c:if>"
														for="quiz${items.id}_2"></label>
												</div>
												<div class="ti_input_w pull-left ml20 mt10">错</div>
											</div>
										</div>
									</c:when>
									<c:when test="${items.quiz_subject_type==4}">
										<div class="chouti mt20 _subjct4" id="${items.id}" subtype="4">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left  _subject-order">${items.quiz_subject_order}</span>
												<div class="pl10"
													style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
											</div>
											<c:forTokens items="${el:randomFun(items.item_options)}"
												delims="#" var="options" varStatus="c">
												<div class="ti">
													<div class="pull-left pt8">${c.count}</div>
													<div class="ti_input_w pull-left ml20 pt8">
														${options}</div>
												</div>
											</c:forTokens>
										</div>
									</c:when>
									<c:when test="${items.quiz_subject_type==5}">
										<div class="chouti mt20 _subjct5" id="${items.id}" subtype="5">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left  _subject-order">${items.quiz_subject_order}</span>
												<div class="pl10"
													style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
											</div>
											<div class="tuxing p20">
												<div class="l_pic">
												    <img src="${items.attach_url}" id="userIdCardImg" width="160">
													<!-- <button type="button" class="hdp">选择需要上传的图片</button> -->
												</div>
												<textarea name="" id="" cols="30" rows="10"
													class="pic_textarea ml20 pull-left form-control" style="width:650px;">${items.item_options}</textarea>
												<div class="clearfix"></div>
											</div>
										</div>
									</c:when>
									<c:when test="${items.quiz_subject_type==6}">
										<div class="chouti mt20 _subjct6" id="${items.id}" subtype="6">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left  _subject-order">${items.quiz_subject_order}</span>
												<div class="pl10"
													style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
											</div>
											<div class="jianda">
												<textarea name="" id="">${items.item_options}</textarea>
											</div>
										</div>
									</c:when>
									<c:when test="${items.quiz_subject_type==7}">
										<div class="chouti mt20 _subjct7" id="${items.id}" subtype="7">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left  _subject-order">${items.quiz_subject_order}</span>
												<div class="pl10"
													style="background: #cdd5d9; border-radius: 0 2px 0 0;">${items.quiz_subject_name}</div>
											</div>
											<div class="jianda">
												<textarea name="" id="">${items.item_options}</textarea>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
						<input value="${quizId}" type="hidden" id="quizId" name="quizId">
						<input value="" type="hidden" id="subjectIds" name="subjectIds">
						<input value="" type="hidden" id="subjectAnswer" name="answers">
						<input value="" type="hidden" id="quizSubjectOrder" name="quizSubjectOrder"> 
						<input value="${eFlag}" type="hidden" id="eFlag" name="eFlag">
                       <input type="hidden" value="${bankScope}" id="bankScope" name="bankScope">
					</form>
				</div>
				<div class="btn_box">
					<button type="button" onclick="submitRequiredQuizSubject();"
						class="btn submit btn_160_36">保存／下一步</button>
					<button type="button" class="btn cancel btn_160_36"
						onclick="goBack();">返回</button>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" value="" id="subjectType">
	<input type="hidden" value="" id="limitCount">
	
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_subject_template.jsp"%>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<div class="checkbox-group">
            	<input type="checkbox" value="{{=v.id}}" id="checkbox_{{=index}}" name="subject" onclick="isSelectAll('one2','subject');">
             	<label for="checkbox_{{=index}}" class="checkbox-2"></label>
		     </div>
		</td>
			<td class="text-center">
				{{?v.quiz_subject_type==1}}
					单选题
				{{?? v.quiz_subject_type==2}}
					多选题
				{{?? v.quiz_subject_type==3}}
					判断题
				{{?? v.quiz_subject_type==4}}
					排序题
				{{?? v.quiz_subject_type==5}}
					简单图片题
				{{?? v.quiz_subject_type==6}}
					简答题
				{{??}}
					填空题
				{{?}}
			</td>
			<td class="text-center"title="{{=isNullFormat(v.quiz_subject_name)}}">{{=subString(isNullFormat(v.quiz_subject_name),15)}}</td>
			<td class="text-center">
				{{? v.quiz_subject_level==1}}
					容易
				{{?? v.quiz_subject_level==2}}
					较易
				{{?? v.quiz_subject_level==3}}
					中等
				{{?? v.quiz_subject_level==4}}
					较难
				{{?? v.quiz_subject_level==5}}
					难
				{{?}}
			</td>
			<td class="text-center">{{=v.quiz_subject_score}}</td>
			<td class="text-center">
				<button class="icon_3" type="button" onclick="viewQuizBankDetail('{{=v.id}}');"></button>
			</td>
	</tr>
 {{~}}	
</script>

<script type="text/javascript">
	require([ 'modules/quizs/quiz','modules/quizs/quiz_examination_auto_required' ],function(obj){
        window.onbeforeunload = updateEditState;
        window.onhashchange = updateEditState;
    });
</script>

</body>
</html>