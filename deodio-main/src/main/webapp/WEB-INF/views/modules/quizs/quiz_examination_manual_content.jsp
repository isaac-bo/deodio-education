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
					<ul class="top_nav">
						<li class="no1_on">设置基本信息</li>
						<li class="no2_on">设置题目内容</li>
						<li class="no3">试卷预览</li>
					</ul>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>

		<form method="post" id="saveQuizForm">

			<div id="myTabContent" class="tab-content"  style="margin-top:80px;padding:20px;">
				<div class="tab-pane active" id="tiku">

				<!-- 	<h3 class="h3_title mt20">第二步，设置题目内容</h3> -->
					<div class="c929292 f12 mt10">通过拖拽右侧各种提醒题型，手动创建试卷。</div>
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
							<div id="order_draggable" class="chouqu">
								<img src="${ctx}/resources/img/quizs/s_paixu.png" alt="">

							</div>
							<c:if test="${quiz.quizCategory==2}">
							<div id="pictrue_draggable" class="chouqu">
								<img src="${ctx}/resources/img/quizs/s_tuxing.png" alt="">

							</div>
							<div id="shortAnswer_draggable" class="chouqu">
								<img src="${ctx}/resources/img/quizs/s_jianda.png" alt="">

							</div>
							<div id="space_draggable" class="chouqu">
								<img src="${ctx}/resources/img/quizs/s_tiankong.png" alt="">

							</div>
							</c:if>
						</div>

						<div id="draggableContent" class="ti_left" style="min-height: 1020px;">
							<c:forEach items="${dataList}" var="items">

								<c:choose>
									<c:when test="${items.quiz_subject_type==1}">
										<div class="chouti mt20" qtype="1">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<input class="form-control" type="text" value="${items.quiz_subject_name}" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
											</div>
											<c:forTokens items="${items.item_options}" delims="#" var="options" varStatus="c">
												<c:forTokens items="${items.option_answer}" delims="#" var="optionAnswer" varStatus="a">
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
															<div class="ti_input_w pull-left ml20">
																<input type="text" class="form-control" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1" value="${options}">
															</div>
															<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
															<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,1);"></button>
														</div>
													</c:if>
												</c:forTokens>
											</c:forTokens>
										</div>
									</c:when>

									<c:when test="${items.quiz_subject_type==2}">
										<div class="chouti mt20" qtype="2">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<input class="form-control" type="text" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1"
													value="${items.quiz_subject_name}">
											</div>
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
															<div class="ti_input_w pull-left ml20">
																<input type="text" class="form-control" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1"
																	value="${options}">
															</div>

															<button type="button"
																class="btn_del_18 pull-left mt10 ml10"
																onclick="deleteOptions(this);"></button>
															<button type="button"
																class="btn_add_18 pull-left mt10 ml10"
																onclick="addOptions(this,2);"></button>
														</div>
													</c:if>
												</c:forTokens>
											</c:forTokens>

										</div>
									</c:when>

									<c:when test="${items.quiz_subject_type==3}">
										<div class="chouti mt20" qtype="3">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<input class="form-control" type="text" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1"
													value="${items.quiz_subject_name}">
											</div>
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
																	value="${c.count}"> <label class="radio-2"
																	<c:if test="${optionAnswer==1}">  checked="checked"</c:if>
																	for="${items.id}_${c.count}"></label>
															</div>
															<div class="ti_input_w pull-left ml20 mt10">
																<label>${options}</label>
															     <input type="hidden" class="form-control" value="${options}" check-type="required" 
															     required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
															</div>
														</div>
													</c:if>
												</c:forTokens>
											</c:forTokens>
										</div>
									</c:when>

									<c:when test="${items.quiz_subject_type==4}">
										<div class="chouti mt20" qtype="4">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<input class="form-control" type="text" check-type="required" 
															     required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1"
													value="${items.quiz_subject_name}">
											</div>
											<c:forTokens items="${items.item_options}" delims="#"
												var="options" varStatus="c">
												<div class="ti">
													<div class="pull-left pt8">
													<!-- ${c.count} -->
													<input type="text" style="width:36px; height:28px;">
													</div>
													<div class="ti_input_w pull-left ml20">
														<input type="text" class="form-control" value="${options}" check-type="required" 
															     required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
													</div>
													<button type="button"
														class="btn_del_18 pull-left mt10 ml10"
														onclick="deleteOptions(this,1);"></button>
													<button type="button"
														class="btn_add_18 pull-left mt10 ml10"
														onclick="addOptions(this,3);"></button>
												</div>
											</c:forTokens>

										</div>
									</c:when>

									<c:when test="${items.quiz_subject_type==5}">
										<div class="chouti mt20" qtype="5">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<input class="form-control" type="text" check-type="required" 
															     required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1"
													value="${items.quiz_subject_name}">
											</div>
											
											
<!-- <div class="c929292 f12 mb10"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;备注</div>
<input type="text" value="标题" class="quiz_title form-control h36"> -->
<%-- <div class="mt20">
	<div class="l_pic">
		<!--<button class="hdp" type="button">选择需要上传的图片</button>-->
        <input type="file" name="uploadFile" class="btn up_color"  id="uploadFile_${items.id}">
		<input type="hidden" name="filePath" id="uploadFilePath_${items.id}" value="${items.attach_url}">
		<img src="${items.attach_url}" id="userIdCardImg" width="160">
	</div>
	<textarea class="form-control pic_textarea ml20 pull-left" rows="10" cols="30" id="tuxing" name="tuxing" style="width: 450px;">${items.item_options}</textarea>
	<div class="clearfix"></div>
</div> --%>
											
											
											
											<div class="tuxing p20">
												<div class="l_pic" style="background:url(${items.attach_url}) center no-repeat;background-size: 100%  100%" id="quiz_option_${items.id}">								
													
													<input type="file" name="uploadFile" class="btn up_color" style="width:20px;height:34px;cursor:pointer;"
														id="uploadFile_${items.id}" onchange="quizAttachUpload(this,'11','${items.id}')"> 
													<input type="hidden"
														name="filePath" id="uploadFilePath_${items.id}"
														value="${items.attach_url}"> 
												</div>
												<textarea name="" id="" cols="30" rows="10"
													class="pic_textarea ml20 pull-left">${items.item_options}</textarea>
												<div class="clearfix"></div>
											</div> 
										</div>
									</c:when>

									<c:when test="${items.quiz_subject_type==6}">
										<div class="chouti mt20" qtype="6">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<input class="form-control" type="text" check-type="required" 
															     required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1"
													value="${items.quiz_subject_name}">
											</div>

											<div class="jianda">
												<textarea name="" id="">${items.item_options}</textarea>
											</div>
										</div>
									</c:when>

									<c:when test="${items.quiz_subject_type==7}">
										<div class="chouti mt20" qtype="7">
											<div class="edit_title">
												<button type="button" onclick="removeQuestion(this);"
													class="del26 pull-right"></button>
												<span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
												<input class="form-control" type="text" check-type="required" 
															     required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1"
													value="${items.quiz_subject_name}">
											</div>

											<div class="tiankong">
												<textarea name="" id="">${items.item_options}</textarea>
											</div>
										</div>
									</c:when>


								</c:choose>

							</c:forEach>



						</div>

						<input value="${quizId}" type="hidden" id="quizId" name="quizId">
						<input value="${eFlag}" type="hidden" id="eFlag" name="eFlag">

					</div>
					<div class="btn_box">
						<button type="button" class="btn submit btn_160_36" btn-type="true">保存／下一步</button>
						<button type="button" class="btn cancel btn_160_36" onclick="goBack();">返回</button>
					</div>
				</div>
			</div>
		</form>
	</div>


	<input type="hidden" id="draggableVle" value="">

	<%@ include file="/WEB-INF/views/modules/quizs/quiz_template.jsp"%>

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/quizs/quiz','modules/quizs/quiz_examination_manual_content'],function(quiz,obj){
			draggableFun('radio_draggable',1);
			draggableFun('checkbox_draggable',2);
			draggableFun('alternative_draggable',3);
			draggableFun('order_draggable',4);
			draggableFun('pictrue_draggable',5);
			draggableFun('shortAnswer_draggable',6);
			draggableFun('space_draggable',7);
			window.onbeforeunload = updateEditState;
		    window.onhashchange = updateEditState;
		});
	</script>
<style>
.ti_left .chouti .tuxing .pic_textarea{width:648px;}
</style>

</body>
</html>