<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<div class="content p_no_border p20" style="background:none;">
				<ul class="pre1_tab pl20"  >
						<li <c:if test="${navTabs==1}"> class="active"</c:if> ><a href="javascript:void(0);" onclick="examinationType(1,this)">手动创建试卷</a></li>
					<li <c:if test="${navTabs==2}"> class="active"</c:if>><a href="javascript:void(0);" onclick="examinationType(2,this)">自动创建试卷</a></li>

				</ul>
		
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane active" id="tiku">

		<c:choose>
			<c:when test="${navTabs==1}">
						<ul class="top_nav">
							<li class="no1_on">设置基本信息</li>
							<li class="no2_on">设置题目内容</li>
							<li class="no3_on">试卷预览</li>
							<!-- <li class="no4">设置试卷规则</li> -->
						</ul>

			</c:when>
			<c:otherwise>
				
						<ul class="top_nav6" id="type2">
							<li class="no1_on">设置基本信息</li>
							<li class="no2_on">设置出题规则</li>
							<li class="no3_on">设置必考题目</li>
							<li class="no4_on">设置随机题目</li>
							<li class="no5_on">试卷预览</li>
							<!-- <li class="no6">设置试卷规则</li> -->
						</ul>
				
			
			</c:otherwise>
	   </c:choose>

						<h3 class="h3_title mt20">第四步，试卷试卷规则</h3>
						<div class="c929292 f12 mt10">如不需要设置必考题，请跳到下一步。</div>
				<form id="submitForm">
<!-- 						<ul class="shezhi"> -->
<!-- 							<li> -->
<!-- 								<div class="pull-left text-right w200">&nbsp;</div> -->
<!-- 								<div class="checkbox-group"> -->
<!-- 									<input type="checkbox" name="remember" id="one1"> -->
<!-- 									<label class="checkbox-2" for="one1"></label> -->
<!-- 								</div> -->
<!-- 								<span class="pt8 ml10">Make this test optional - Viewers can skip this test</span> -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								<div class="pull-left text-right w200">&nbsp;</div> -->
<!-- 								<div class="checkbox-group"> -->
<!-- 									<input type="checkbox" name="remember" id="one2"> -->
<!-- 									<label class="checkbox-2" for="one2"></label> -->
<!-- 								</div> -->
<!-- 								<span class="pt8 ml10">Only show this test once - Do not diplay again when Viewer returns to course / presentation.</span> -->
<!-- 							</li> -->
<!-- 						</ul> -->
						<ul class="shezhi">
							<li>
								<div class="pull-left text-right w200">&nbsp;</div>
								<div class="checkbox-group">
									<input type="checkbox" <c:if test="${quizModel.finishTime!=null}">checked="checked"</c:if>  name="remember" id="radio_finish_time">
									<label class="checkbox-2 <c:if test="${quizModel.finishTime!=null}"> checked</c:if>" for="radio_finish_time"></label>
								</div>
								<span class="pt8 ml10">测验／试卷必须在<input type="text" value="${quizModel.finishTime}" id="finish_time" class="text form-control">分钟内完成。</span>
							</li>
						</ul>
						<ul class="shezhi">
							<li>
								<div class="pull-left text-right w200 pt10">通过分数：</div>
								<input type="text" id="pass_score" value="${quizModel.passScore}" class="form-control w400">
							</li>
							<li>
								<div class="pull-left text-right w200 pt10">允许考试最大次数：</div>
								<input type="text"  id="quiz_max_times"  value="${quizModel.quizMaxTimes}" class="form-control w400">
							</li>
							<li>
								<div class="pull-left text-right w200 pt8">多次考试最终成绩确定方式：</div>
								<div class="radio-group pull-left">
									<input type="radio" name="remember1" <c:if test="${quizModel.finallyQuizResultType==1}">checked="checked"</c:if>  id="finalResut1" value="1">
									<label class="radio-2 <c:if test="${quizModel.finallyQuizResultType==1}"> checked</c:if>" for="finalResut1"></label>
								</div>
								<span class="pt8 ml10 mr20 pull-left">最后一次考试成绩</span>
								<div class="radio-group pull-left">
									<input type="radio" name="remember1" <c:if test="${quizModel.finallyQuizResultType==2}">checked="checked"</c:if> id="finalResut2" value="2">
									<label class="radio-2 <c:if test="${quizModel.finallyQuizResultType==2}"> checked</c:if>" for="finalResut2"></label>
								</div>
								<span class="pt8 ml10 mr20 pull-left">最高分</span>
								<div class="radio-group pull-left">
									<input type="radio" name="remember1" <c:if test="${quizModel.finallyQuizResultType==3}">checked="checked"</c:if> id="finalResut3" value="3">
									<label class="radio-2 <c:if test="${quizModel.finallyQuizResultType==3}"> checked</c:if>" for="finalResut3"></label>
								</div>
								<span class="pt8 ml10 mr20 pull-left">平均分</span>
							</li>
							<li>
								<div class="pull-left text-right w200 pt8">成绩公布：</div>
								<div class="r_con">
									<div class="mb10">
										<div class="radio-group pull-left">
											<input type="radio" name="remember2" <c:if test="${quizModel.publishResultType==1}">checked="checked"</c:if> id="publisResult1" value="1">
											<label class="radio-2 <c:if test="${quizModel.publishResultType==1}"> checked</c:if>" for="publisResult1"></label>
										</div>
										<span class="pt8 ml10 mr20 pull-left">考试分数保密</span>
										<div class="clearfix"></div>
									</div>
									<div class="mb10">
										<div class="radio-group pull-left">
											<input type="radio" name="remember2" <c:if test="${quizModel.publishResultType==2}">checked="checked"</c:if> id="publisResult2" value="2">
											<label class="radio-2 <c:if test="${quizModel.publishResultType==2}"> checked</c:if>" for="publisResult2"></label>
										</div>
										<span class="pt8 ml10 mr20 pull-left">交卷后即时显示分数成绩</span>
										<div class="clearfix"></div>
									</div>
									<div class="mb10">
										<div class="radio-group pull-left">
											<input type="radio" name="remember2" <c:if test="${quizModel.publishResultType==3}">checked="checked"</c:if> id="publisResult3" value="3">
											<label class="radio-2 <c:if test="${quizModel.publishResultType==3}"> checked</c:if>" for="publisResult3"></label>
										</div>
										<span class="pt8 ml10 mr20 pull-left">交卷后不显示考试分数成绩，待管理员确定公布成绩后才显示</span>
										<div class="clearfix"></div>
									</div>
								</div>
							</li>
							<li>
								<div class="pull-left text-right w200 pt8">试卷安全：</div>
								<div class="r_con">
									<div class="mb10">
										<div class="checkbox-group">
											<input type="checkbox" <c:if test="${quizModel.quizSafe==1}">checked="checked"</c:if> name="remember" id="quizSafe1" value="1">
											<label class="checkbox-2 <c:if test="${quizModel.quizSafe==1}"> checked</c:if>" for="quizSafe1"></label>
										</div>
										<span class="pt8 ml10 mr20">允许考生提交后查看其提交的答案和标准答案</span>
										<div class="clearfix"></div>
									</div>
									<div class="mb10">
										<div class="checkbox-group">
											<input type="checkbox" <c:if test="${quizModel.quizSafe==2}">checked="checked"</c:if>  name="remember" id="quizSafe2" value="2">
											<label class="checkbox-2 <c:if test="${quizModel.quizSafe==2}"> checked</c:if>" for="quizSafe2"></label>
										</div>
										<span class="pt8 ml10 mr20">允许学员成绩合格后继续参加考试</span>
										<div class="clearfix"></div>
									</div>
								</div>
							</li>
							<li>
								<div class="pull-left text-right w200 pt8">其他选项：</div>
								<div class="checkbox-group">
									<input type="checkbox" name="remember" id="one3">
									<label class="checkbox-2" for="one3"></label>
								</div>
								<span class="pt8 ml10 mr20">允许考生提交后查看其提交的答案和标准答案</span>
							</li>
							<li>
								<div class="pull-left text-right w200 pt8">试卷说明：</div>
								<div class="r_con">
									<script type="text/plain" id="content_template" style="width: 591px; height:260px;">${quizModel.quizContent}</script>
								</div>
							</li>
						</ul>
						<div class="form_line2"></div>
						<div class="btn_box">
							<button class="btn submit btn_160_36" btn-type="true" type="button">发布</button>
							<button class="btn cancel btn_160_36" type="button">取消</button>
						</div>
					</form>	
						
					</div>
				</div>
			</div>		
	<input type="hidden" value="${quizId}" id="quizId">
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/quizs/quiz','modules/quizs/quiz_examination_rule']);
	</script>


</body>
</html>