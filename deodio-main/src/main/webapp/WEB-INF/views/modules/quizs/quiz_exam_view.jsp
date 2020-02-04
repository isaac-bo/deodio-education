<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">

<div class="q_content" style="background:none;">
<form>
	<div class="panel-body">
		<h2 class="text-danger">
			<span>考生得分:${score}</span>
		</h2>
	</div>
    <div class="ti_left">
    <c:forEach items="${dataList}" var="items">
        <c:choose>
            <%-- 单选题 --%>
            <c:when test="${items.quiz_subject_type==1}">
            <div class="chouti" qtype="1">
                <div class="edit_title">
                    <span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
                    <input class="form-control" type="text" value="${items.quiz_subject_name}" readonly="readonly">
                </div>
                <c:forTokens items="${items.item_options}" delims="#" var="options" varStatus="c">
                    <c:forTokens items="${items.option_answer}" delims="#" var="optionAnswer" varStatus="a">
                        <c:if test="${c.count==a.count}">
                            <div class="ti">
                                <div class="radio-group pull-left">
                                    <input type="radio" <c:if test="${optionAnswer==1}">  checked="checked"</c:if>
                                    name="quiz${items.id}" id="${items.id}_${c.count}" value="${c.count}">
                                    <label class="radio-2" for="${items.id}_${c.count}"></label>
                                </div>
                                <div class="ti_input_w pull-left ml20 pt8">
                                    ${options}
                                    <input type="hidden" value="${options}">
                                </div>
                            </div>
                        </c:if>
                    </c:forTokens>
                </c:forTokens>
            </div>
            </c:when>
            <%-- 多选题 --%>
            <c:when test="${items.quiz_subject_type==2}">
            <div class="chouti" qtype="2">
                <div class="edit_title">
                    <span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
                    <input class="form-control" type="text" value="${items.quiz_subject_name}" readonly="readonly">
                </div>
                <c:forTokens items="${items.item_options}" delims="#"
                    var="options" varStatus="c">
                    <c:forTokens items="${items.option_answer}" delims="#"
                        var="optionAnswer" varStatus="a">
                        <c:if test="${c.count==a.count}">
                            <div class="ti">
                                <div class="checkbox-group pull-left">
                                    <input type="checkbox" <c:if test="${optionAnswer==1}">checked="checked"</c:if>
                                    name="quiz${items.id}" id="${items.id}_${c.count}" value="${c.count}">
                                    <label class="checkbox-2" <c:if test="${optionAnswer==1}">checked</c:if> 
                                    for="${items.id}_${c.count}"></label>
                                </div>
                                <%-- <div class="ti_input_w pull-left ml20">
                                    <input type="text" class="form-control" value="${options}" readonly="readonly">
                                </div> --%>
                                <div class="ti_input_w pull-left ml20 pt8">
                                    ${options}
                                    <input type="hidden" value="${options}">
                                </div>
                            </div>
                        </c:if>
                    </c:forTokens>
                </c:forTokens>
            </div>
            </c:when>
            <%-- 判断题 --%>
            <c:when test="${items.quiz_subject_type==3}">
            <div class="chouti" qtype="3">
                <div class="edit_title">
                    <span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
                    <input class="form-control" type="text" value="${items.quiz_subject_name}" readonly="readonly">
                </div>
                <c:forTokens items="${items.item_options}" delims="#" var="options" varStatus="c">
                    <c:forTokens items="${items.option_answer}" delims="#"
                        var="optionAnswer" varStatus="a">
                        <c:if test="${c.count==a.count}">
                            <div class="ti">
                                <div class="radio-group pull-left">
                                    <input type="radio" <c:if test="${optionAnswer==1}">  checked="checked"</c:if>
                                    name="quiz${items.id}" id="${items.id}_${c.count}" value="${c.count}">
                                    <label class="radio-2" <c:if test="${optionAnswer==1}">  checked="checked"</c:if>
                                    for="${items.id}_${c.count}"></label>
                                </div>
                                <%-- <div class="ti_input_w pull-left ml20">
                                    <input type="text" class="form-control" value="${options}" readonly="readonly"> 
                                </div> --%>
                                <div class="ti_input_w pull-left ml20 pt8">
                                    ${options}
                                    <input type="hidden" value="${options}">
                                </div>
                            </div>
                        </c:if>
                    </c:forTokens>
                </c:forTokens>
            </div>
            </c:when>
            <%-- 排序题 --%>
            <c:when test="${items.quiz_subject_type==4}">
            <div class="chouti" qtype="4">
                <div class="edit_title">
                    <span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
                    <input class="form-control" type="text" value="${items.quiz_subject_name}" readonly="readonly">
                </div>
                <c:forTokens items="${el:randomFun(items.item_options)}" delims="#" var="options" varStatus="c">
                    <div class="ti">
                        <div class="pull-left pt8">
                            <input type="text" class="form-control w50 text-center" id="${items.id}_${c.count}">
                        </div>
                        <div class="ti_input_w pull-left ml20 pt8">
                            ${options}
                            <input type="hidden" value="${options}">
                        </div>
                        
                        <%-- <div class="pull-left pt8">${c.count}</div>
                        <div class="ti_input_w pull-left ml20">
                            <input type="text" class="form-control" value="${options}" readonly="readonly">
                        </div> --%>
                    </div>
                </c:forTokens>
            </div>
            </c:when>
            <%-- 图片题 --%>
            <c:when test="${items.quiz_subject_type==5}">
            <div class="chouti" qtype="5">
                <div class="edit_title">
                    <span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
                    <input class="form-control" type="text" value="${items.quiz_subject_name}" readonly="readonly">
                </div>
                <div class="tuxing p20">
                    <div class="l_pic">
                        <input type="file" name="uploadFile" class="btn up_color"  id="uploadFile_${items.id}">
                        <input type="hidden" name="filePath" id="uploadFilePath_${items.id}" value="${items.attach_url}">
                        <img src="${items.attach_url}" id="userIdCardImg" width="160">
                    </div>
                    <textarea name="" id="" cols="30" rows="10"
                        class="pic_textarea ml20 pull-left">${items.item_options}</textarea>
                    <div class="clearfix"></div>
                </div>
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
                    <textarea name="" id="">${items.item_options}</textarea>
                </div>
            </div>
            </c:when>
            <%-- 填空题 --%>
            <c:when test="${items.quiz_subject_type==7}">
            <div class="chouti" qtype="7">
                <div class="edit_title">
                    <span class="pull-left _quiz-order">${items.quiz_subject_order}</span>
                    <input class="form-control" type="text" value="${items.quiz_subject_name}" readonly="readonly">
                </div>
                <div class="tiankong">
                    <textarea name="" id="">${items.item_options}</textarea>
                </div>
            </div>
            </c:when>
        </c:choose>
    </c:forEach>
    <div class="btn_box">
        <!-- <button type="button"  class="btn submit btn_160_36"  onclick="submitExam()">提交</button> -->
        <button tyqpe="button" class="btn cancel btn_160_36" onclick="goBack();">返回</button>
    </div>
</div>
</form>
</div>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<script type="text/javascript">
    var quizId = '${quizId}';
    var courseId = '${courseId}';
    var groupId = '${groupId}';
    require([ 'modules/quizs/quiz_exam_view' ], function(obj) {
    }); 
</script>
</body>
</html>