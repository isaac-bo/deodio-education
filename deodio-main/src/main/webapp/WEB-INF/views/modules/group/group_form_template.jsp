<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ taglib uri="/WEB-INF/views/tags/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/views/tags/fn.tld" prefix="fn" %>
<c:forEach var="item" items="${surveyList}" varStatus="status">
	<c:choose>
		<c:when test="${item.survey_subject_type==1}">
			<div class="timu mt20" surveytype='1'>
				<h3 class="edit">
					<button type="button" onclick="removeQuestion(this);" class="del_icon pull-right"></button>
					<input type="text" placeholder="请输入问题" class="form-control" value="${item.survey_subject}">
				</h3>
			<c:set var="itemOptions" value="${fn:split(item.item_options, '#')}" /> 
			<c:forEach var="item" items="${itemOptions}" varStatus="vs">
				<div class="ti">
					<div class="radio-group pull-left pt10">
						<input type="radio" name="survey${status.index}" id="${status.index}_${vs.index}" value="1"> <label class="radio-2" for="${status.index}_${vs.index}"></label>
					</div>
					<div class="ti_input_w pull-left ml20">
						<input type="text" class="form-control" value="${item}">
					</div>
					<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
					<button type="button" onclick="addOptions(this,1);"class="btn_add_18 pull-left mt10 ml10"></button>
				</div>
			</c:forEach>
			</div>
		</c:when>
		<c:when test="${item.survey_subject_type==2}">
			<div class="timu mt20" surveytype='2'>
				<h3 class="edit"><button type="button" onclick="removeQuestion(this);"  class="del_icon pull-right"></button>
				<input type="text" placeholder="请输入问题" class="form-control" value="${item.survey_subject}"></h3>
			<c:set var="itemOptions" value="${fn:split(item.item_options, '#')}" /> 
			<c:forEach var="item" items="${itemOptions}"  varStatus="vs">
				<div class="ti">
					<div class="checkbox-group pull-left pt10">
						<input type="checkbox" name="survey${status.index}" id="${status.index}_${vs.index}" value="1">
						<label class="checkbox-2" for="${status.index}_${vs.index}"></label>
					</div>
					<div class="ti_input_w pull-left ml20">
						<input type="text" class="form-control" value="${item}">
					</div>
					<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
					<button type="button" onclick="addOptions(this,2);"class="btn_add_18 pull-left mt10 ml10"></button>
				</div>	
			</c:forEach>			
			</div>
		</c:when>
		<c:when test="${item.survey_subject_type==3}">
			<div class="timu mt20" surveytype='3'>
				<h3 class="edit">
					<button type="button" onclick="removeQuestion(this);" class="del_icon pull-right"></button>
					<input type="text" placeholder="请输入问题" class="form-control" value="${item.survey_subject}">
				</h3>
				<div class="ti">
					<div class="radio-group pull-left pt10">
						<input type="radio" name="survey${status.index}" id="${status.index}_1" value="1"> <label class="radio-2" for="${status.index}_1"></label>
					</div>
					<div class="ti_input_w pull-left ml20">对</div>

				</div>
				<div class="ti">
					<div class="radio-group pull-left pt10">
						<input type="radio" name="survey${status.index}" id="${status.index}_2" value="2"> <label class="radio-2" for="${status.index}_2"></label>
					</div>
					<div class="ti_input_w pull-left ml20">错</div>

				</div>

			</div>
		</c:when>
	</c:choose>
</c:forEach>