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
					<ul class="top_nav6">
						<li class="no1_on">设置基本信息</li>
						<li class="no2_on">设置出题规则</li>
						<li class="no3">设置必考题目</li>
						<li class="no4">设置随机题目</li>
						<li class="no5">试卷预览</li>
					</ul>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>
		
		<div class="tab-content" style="margin-top:120px;padding:20px;padding-top:0px;padding-right:40px;">
			<div class="tab-pane active">
			<form action="${ctx}/quiz/auto/required.html" method="post" id="createForm">
					<div class="ti_set mt20">
						<span class="left_title pull-left text-right">出题范围</span>
						<div class="right_con">
							<div id="banks_div" class="fenlei_1 fenlei_w510 pull-left"
								style="width: 100%;min-height: 75px;height: auto;">
								<c:forEach var="item" items="${tagList}">
									<span class="sel_btn" banksid="${item.id}" id="${item.id}">
										${item.quiz_bank_name}
										<button class="sel_del" onclick="selDelQuizBank('${item.id}');" type="button">×</button>
									</span>
								</c:forEach>
							</div>
							<div class="clearfix"></div>
							<div class="mt10">
								<!-- <button class="btn submit" data-target="#quizBanksModal" data-toggle="modal" type="button">题库中心</button> -->
								<button class="btn submit" onclick="setQuizBank()" type="button">题库中心</button>
							</div>
						</div>
					</div>
					<div class="ti_set mt20">
						<span class="left_title pull-left text-right">题目设置</span>
						<div class="right_con">
							<table cellpadding="0" cellspacing="0" class="table table-striped td_h60">
								<thead>
									<tr>
										<th class="text-center" style="width: 12%">大题题型</th>
										<th class="text-center" style="width: 12%;">显示顺序</th>
										<th class="text-center" style="width: 12%;">每题分数</th>
										<th class="text-center" style="width: 12%;">题目数</th>
										<th>题型说明</th>
									</tr>
								</thead>
								<tbody id="subjectSet">
									<tr>
										<td>
											<div class="checkbox-group">
												<input type="checkbox" <c:forEach var="item" items="${subjectList}"> <c:if test="${item.subjectType==1}">checked="checked"</c:if> </c:forEach>class="_checked1" name="quizSet" id="one1" value="1"> 
												<label class="checkbox-2 _lable1" for="one1"></label>
											</div> <span>单选题</span>
										</td>
										<td><input type="text" class="form-control _order1" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==1}">value="${item.subjectOrder}"</c:if></c:forEach>></td>
										<td><input type="text" class="form-control _score1" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==1}">value="${item.subjectScore}"</c:if></c:forEach>></td>
										<td>
											<span style="width: 75%">
												<input type="text" class="form-control _opcounts1" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==1}">value="${item.subjectItemsCount}"</c:if></c:forEach>>
											</span>
											<span>/</span><span id="subjctType1">0</span>
										</td>
										<td><input type="text" class="form-control _mark1" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==1}">value="${item.subjectRemark}"</c:if></c:forEach>></td>
									</tr>
									<tr>
										<td>
											<div class="checkbox-group">
												<input type="checkbox" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==2}">checked="checked"</c:if></c:forEach> class="_checked2" name="quizSet" id="one2" value="2"> 
												<label class="checkbox-2 _lable2" for="one2"></label>
											</div> <span>多选题</span>
										</td>
										<td><input type="text" class="form-control _order2" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==2}">value="${item.subjectOrder}"</c:if></c:forEach>></td>
										<td><input type="text" class="form-control _score2" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==2}">value="${item.subjectScore}"</c:if></c:forEach>></td>
										<td><span style="width: 75%"><input type="text" class="form-control _opcounts2" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==2}">value="${item.subjectItemsCount}"</c:if></c:forEach>></span>
											<span>/</span><span id="subjctType2">0</span>
										</td>
										<td><input type="text" class="form-control _mark2" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==2}">value="${item.subjectRemark}"</c:if></c:forEach>></td>
									</tr>
									<tr>
										<td>
											<div class="checkbox-group">
												<input type="checkbox" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==3}">checked="checked"</c:if></c:forEach> class="_checked3" name="quizSet" id="one3" value="3"> 
												<label class="checkbox-2 _lable3" for="one3"></label>
											</div> <span>判断题</span>
										</td>
										<td><input type="text" class="form-control _order3" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==3}">value="${item.subjectOrder}"</c:if></c:forEach>></td>
										<td><input type="text" class="form-control _score3" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==3}">value="${item.subjectScore}"</c:if></c:forEach>></td>
										<td><span style="width: 75%"><input type="text" class="form-control _opcounts3" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==3}">value="${item.subjectItemsCount}"</c:if></c:forEach>></span>
											<span>/</span><span id="subjctType3">0</span>
										</td>
										<td><input type="text" class="form-control _mark3" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==3}">value="${item.subjectRemark}"</c:if></c:forEach>></td>
									</tr>
									<tr>
										<td>
											<div class="checkbox-group">
												<input type="checkbox" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==4}">checked="checked"</c:if></c:forEach> class="_checked4" name="quizSet" id="one4" value="4"> 
												<label class="checkbox-2 _lable4" for="one4"></label>
											</div> <span>排序题</span>
										</td>
										<td><input type="text" class="form-control _order4" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==4}">value="${item.subjectOrder}"</c:if></c:forEach>></td>
										<td><input type="text" class="form-control _score4" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==4}">value="${item.subjectScore}"</c:if></c:forEach>></td>
										<td><span style="width: 75%"><input type="text" class="form-control _opcounts4" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==4}">value="${item.subjectItemsCount}"</c:if></c:forEach>></span>
											<span>/</span><span id="subjctType4">0</span>
										</td>
										<td><input type="text" class="form-control _mark4" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==4}">value="${item.subjectRemark}"</c:if></c:forEach>></td>
									</tr>
									<c:if test="${quiz.quizCategory==2}">
									<tr>
										<td>
											<div class="checkbox-group">
												<input type="checkbox" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==5}">checked="checked"</c:if></c:forEach> class="_checked5" name="quizSet" id="one5" value="5"> 
												<label class="checkbox-2 _lable5" for="one5"></label>
											</div> <span>图形题</span>
										</td>
										<td><input type="text" class="form-control _order5" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==5}">value="${item.subjectOrder}"</c:if></c:forEach>></td>
										<td><input type="text" class="form-control _score5" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==5}">value="${item.subjectScore}"</c:if></c:forEach>></td>
										<td><span style="width: 75%"><input type="text" class="form-control _opcounts5" name="quizRequired"  <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==5}">value="${item.subjectItemsCount}"</c:if></c:forEach>></span>
											<span>/</span><span id="subjctType5">0</span>
										</td>
										<td><input type="text" class="form-control _mark5" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==5}">value="${item.subjectRemark}"</c:if></c:forEach>></td>
									</tr>
									<tr>
										<td>
											<div class="checkbox-group">
												<input type="checkbox" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==6}">checked="checked"</c:if></c:forEach> class="_checked6" name="quizSet" id="one6" value="6"> 
												<label class="checkbox-2 _lable6" for="one6"></label>
											</div> <span>简答题</span>
										</td>
										<td><input type="text" class="form-control _order6" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==6}">value="${item.subjectOrder}"</c:if></c:forEach>></td>
										<td><input type="text" class="form-control _score6" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==6}">value="${item.subjectScore}"</c:if></c:forEach>></td>
										<td><span style="width: 75%"><input type="text" class="form-control _opcounts6" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==6}">value="${item.subjectItemsCount}"</c:if></c:forEach>></span>
											<span>/</span><span id="subjctType6">0</span>
										</td>
										<td><input type="text" class="form-control _mark6" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==6}">value="${item.subjectRemark}"</c:if></c:forEach>></td>
									</tr>
									<tr>
										<td>
											<div class="checkbox-group">
												<input type="checkbox" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==7}">checked="checked"</c:if></c:forEach> class="_checked7" name="quizSet" id="one7" value="7"> 
												<label class="checkbox-2 _lable7" for="one7"></label>
											</div> <span>填空题</span>
										</td>
										<td><input type="text" class="form-control _order7" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==7}">value="${item.subjectOrder}"</c:if></c:forEach>></td>
										<td><input type="text" class="form-control _score7" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==7}">value="${item.subjectScore}"</c:if></c:forEach>></td>
										<td><span style="width: 75%"><input type="text" class="form-control _opcounts7" name="quizRequired" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==7}">value="${item.subjectItemsCount}"</c:if></c:forEach>></span>
											<span>/</span><span id="subjctType7">0</span>
										</td>
										<td><input type="text" class="form-control _mark7" <c:forEach var="item" items="${subjectList}"><c:if test="${item.subjectType==7}">value="${item.subjectRemark}"</c:if></c:forEach>></td>
									</tr>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
					<div class="ti_set mt20">
						<span class="left_title pull-left text-right pt8">分数设置</span>
						<div class="right_con">
							<div class="mb10">
								<div class="radio-group pull-left">
									<input type="radio" name="scoreSet" id="scoreSet1" value="0" checked="checked" <c:if test="${quiz.scoreSet==0}">checked="checked"</c:if>>
									<label class="radio-2" for="scoreSet1"></label>
								</div>
								<span class="pt8 ml10">忽略试题默认分数，按题型指定分数</span>
							</div>
							<div class="mb10">
								<div class="radio-group pull-left">
									<input type="radio" name="scoreSet" id="scoreSet2" value="1" <c:if test="${quiz.scoreSet==1}">checked="checked"</c:if>>
									<label class="radio-2" for="scoreSet2"></label>
								</div>
								<span class="pt8 ml10">使用题库中的分数</span>
							</div>
						</div>
					</div>
					<div class="btn_box">
						<!-- <button class="btn submit btn_160_36" onclick="submitBanksRule()" type="button">提交</button> -->
						<button class="btn submit btn_160_36" type="button" btn-type="true">保存／下一步</button>
						<button class="btn cancel btn_160_36" type="button" onclick="goBack();">返回</button>
					</div>
					<input type="hidden" id="bankScope" name="bankScope"> 
					<input type="hidden" id="subjectSets" name="subjectSets"> 
					<input type="hidden" id="quizId" name="quizId" value="${quizId}">
					<input type="hidden" id="eFlag" name="eFlag" value="${eFlag}">
					<input type="hidden" id="navTabs" name="navTabs" value="${navTabs}">
				</form>
			</div>
		</div>
	</div>
<script id="banks_template" type="text/x-dot-template">
 {{~it.data:v:index}}
<tr class="row_{{=v.classificationId}}">
	<td class="text-center" style="width:8%">
		<div class="checkbox-group">
        	<input type="checkbox" id="one_{{=v.id}}" name="remember" value="{{=v.id}}_{{=v.quizBankName}}" onclick="isSelectAll('questionall','remember');">
         	<label for="one_{{=v.id}}" class="checkbox-2"></label>
      	</div>
	</td>
	<td class="text-center" style="width:22%">{{=v.quizBankName}}</td>
	<td class="text-center">{{=v.quizBankDesc}}</td>
</tr>
 {{~}}	
</script>

<%@ include file="/WEB-INF/views/modules/quizs/quiz_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<script type="text/javascript">
	require(['modules/quizs/quiz','modules/quizs/quiz_examination_auto_content'],function(obj){
		window.onbeforeunload = updateEditState;
	    window.onhashchange = updateEditState;
		/* if($("#eFlag").val()=="1"){
			obj.getBanksInfo();
		};  */
	}); 
</script>
</body>
</html>