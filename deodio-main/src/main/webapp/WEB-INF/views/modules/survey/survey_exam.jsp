<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">

<div class="q_content" style="background:none;">
    
<form method="post" id="saveSurveyForm">
    <div class="ti_left">
    <c:forEach items="${dataList}" var="items">
        <c:choose>
            <%-- 单选题 --%>
            <c:when test="${items.survey_subject_type==1}">
            <div class="chouti" qtype="1">
                <div class="edit_title">
                    <span class="pull-left _survey-order">${items.survey_subject_order}</span>
                    <input class="form-control" type="text" value="${items.survey_subject}" readonly="readonly">
                </div>
                <c:forTokens items="${items.item_options}" delims="#" var="options" varStatus="c">
                    <div class="ti">
                        <div class="radio-group pull-left">
                            <input type="radio" name="survey${items.id}" id="${items.id}_${c.count}" value="${c.count}">
                            <label class="radio-2" for="${items.id}_${c.count}"></label>
                        </div>
                        <div class="ti_input_w pull-left ml20 pt8">
                            ${options}
                            <input type="hidden" value="${options}">
                        </div>
                    </div>
                </c:forTokens>
            </div>
            </c:when>
            <%-- 多选题 --%>
            <c:when test="${items.survey_subject_type==2}">
            <div class="chouti" qtype="2">
                <div class="edit_title">
                    <span class="pull-left _survey-order">${items.survey_subject_order}</span>
                    <input class="form-control" type="text" value="${items.survey_subject}" readonly="readonly">
                </div>
                <c:forTokens items="${items.item_options}" delims="#" var="options" varStatus="c">
                    <div class="ti">
                        <div class="checkbox-group pull-left">
                            <input type="checkbox" name="survey${items.id}" id="${items.id}_${c.count}" value="${c.count}">
                            <label class="checkbox-2" for="${items.id}_${c.count}"></label>
                        </div>
                        <div class="ti_input_w pull-left ml20 pt8">
                            ${options}
                            <input type="hidden" value="${options}">
                        </div>
                    </div>
                </c:forTokens>
            </div>
            </c:when>
            <%-- 判断题 --%>
            <c:when test="${items.survey_subject_type==3}">
            <div class="chouti" qtype="3">
                <div class="edit_title">
                    <span class="pull-left _survey-order">${items.survey_subject_order}</span>
                    <input class="form-control" type="text" value="${items.survey_subject}" readonly="readonly">
                </div>
                <c:forTokens items="${items.item_options}" delims="#" var="options" varStatus="c">
                    <div class="ti">
                        <div class="radio-group pull-left">
                            <input type="radio" name="survey${items.id}" id="${items.id}_${c.count}" value="${c.count}">
                            <label class="radio-2" for="${items.id}_${c.count}"></label>
                        </div>
                        <div class="ti_input_w pull-left ml20 pt8">
                            ${options}
                            <input type="hidden" value="${options}">
                        </div>
                    </div>
                </c:forTokens>
            </div>
            </c:when>
            <%-- 简答题 --%>
            <c:when test="${items.quiz_subject_type==6}">
            <div class="chouti" qtype="6">
                <div class="edit_title">
                    <span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
                    <input class="form-control" type="text" value="${items.quiz_subject_name}" readonly="readonly">
                </div>
                <div class="jianda">
                    <textarea name="" id=""></textarea>
                </div>
            </div>
            </c:when>
        </c:choose>
    </c:forEach>
    <div class="btn_box">
        <button type="button"  class="btn submit btn_160_36"  btn-type="true">提交</button>
        <button type="button" class="btn cancel btn_160_36" onclick="goBack();">返回</button>
    </div>
</div>
</form>
</div>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<script type="text/javascript">
    var examId = '${examId}'; 
    var courseId = '${courseId}';
    var groupId = '${groupId}';
    require([ 'modules/survey/survey_exam' ], function(obj) {
    }); 
</script>
</body>
</html>