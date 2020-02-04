<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_header.jsp"%>
<div class="tab-nav-con">
	<ul class="nav nav-tabs set_tab" role="tablist" style="background-color:#f2f2f5 !important;">
		<li class="" role="presentation"><a onclick="goBack();" href="${ctx}/presentation/package/files.html?presentationId=${presentationMap.id}" role="tab" data-toggle="tab">选择要导入的课件</a></li>
		<li class="active" role="presentation"><a href="javascript:void(0);" role="tab" data-toggle="tab">设置课件的规则</a></li>
	</ul>
	<div class="con-corner"></div>
</div>

<div class="pre1_con" style="margin-top: 55px;">
<!-- 设置课件的规则 -->
<div class="tab-pane" id="shezhi1">
	<form id="packageProfileForm">
	<div class="mt20">

		<ul class="shezhi pl0">
			<li>
				<div class="r_con2">
					<div class="checkbox-group">
						<input type="checkbox" name="isCountDown" id="isCountDown" onclick="setPackageVisible();"
						<c:if test="${presetationPackage.isCountDown == 1}"> checked </c:if>
						<c:if test="${presetationPackage.isCountDown != 1}"> "" </c:if>>
						<label class="checkbox-2" for="isCountDown"></label>
					</div>
					启动规定时间内完成该章节
				</div>
			</li>
			
			<li class="items-con package-profile-container" <c:if test="${presetationPackage.isCountDown != 1}"> style="display:none;"  </c:if>>
				<div class="pull-left text-right w150">设置规定完成时间：</div>
				<div id="itemList" style="margin-top: -20px;">
					<c:forEach items="${packageItemList}" var="item">
					<div class='r_con2 error-div mt20 package-profile'>
						<input type="text" name="countDown" check-type="number" required-message="请设置规定完成时间!" value="${item.countDown}"  class="text form-control">&nbsp;秒 - ${item.packageName}
						<input type="hidden" name="packageItemId" value="${item.id}">
					</div>
					</c:forEach>
				</div>
			</li>
		</ul>
		<ul class="shezhi pl0">
<!-- 			<li> -->
<!-- 				<div class="pull-left text-right w150">达标学习进度百分比：</div> -->
<!-- 				<div class="r_con2"> -->
<!-- 					<span>60</span> % -->
<%-- 					<input type="text" name="persentationPercentage" check-type="number" min-max="0-3" min-max-num="0-100" required-message="达标学习进度百分比!" id="persentationPercentage" value="${presentationMap.persentation_percentage}" class="text form-control">&nbsp;% --%>
<!-- 				</div> -->
<!-- 			</li> -->
			<li>
				<div class="pull-left"><span class="input-title-span">＊</span>章节通过条件：</div>
				<div class="r_con2">
<!-- 					<div class="checkbox-group"> -->
<!-- 						<input type="checkbox" name="isPassHours" id="one2" -->
<%-- 						<c:if test="${presentationMap.is_pass_hours == 1}"> checked </c:if> --%>
<%-- 						<c:if test="${presentationMap.is_pass_hours == 0}"> "" </c:if>> --%>
<!-- 						<label class="checkbox-2" for="one2"></label> -->
<!-- 					</div> -->
					学习进度达到要求(<input type="text" name="persentationPercentage" check-type="number" 
					min-max="0-3" min-max-num="0-100" required-message="请输入达标学习进度百分比!"
					
					 id="persentationPercentage" value="${presentationMap.persentation_percentage}" class="text form-control">&nbsp;%)
				</div>
			</li>
<!-- 			<li> -->
<!-- 				<div class="r_con2"> -->
<!-- 					<div class="checkbox-group"> -->
<!-- 						<input type="checkbox" name="isPassElements" id="one3" -->
<%-- 						<c:if test="${presentationMap.is_pass_elements == 1}"> checked </c:if> --%>
<%-- 						<c:if test="${presentationMap.is_pass_elements == 0}"> "" </c:if>> --%>
<!-- 						<label class="checkbox-2" for="one3"></label> -->
<!-- 					</div> -->
<!-- 					阅读所有文件包内课件 -->
<!-- 				</div> -->
<!-- 			</li> -->
			<%-- <li>

				<div class="r_con2">
					<div class="checkbox-group">
						<input type="checkbox" name="isPassQuizs" id="one4"
						<c:if test="${presentationMap.is_pass_quizs == 1}"> checked </c:if>
						<c:if test="${presentationMap.is_pass_quizs == 0}"> "" </c:if>>
						<label class="checkbox-2" for="one4"></label>
					</div>
					通过Presentation的测验
				</div>
			</li> --%>
		</ul>
	</div>
	<div class="mt20">
		<ul class="shezhi pl0 b_none">
			<li>

				<div class="r_con2">
					<div class="checkbox-group">
						<input type="checkbox" name="isCourse" id="one5"
						<c:if test="${presentationMap.is_course == 1}"> checked </c:if>
						<c:if test="${presentationMap.is_course == 0}"> "" </c:if>>
						<label class="checkbox-2" for="one5"></label>
					</div>
					发布后自动创建同名的课程
				</div>
			</li>
		</ul>
	</div>
	<div class="btn_box">
		<button class="btn submit  btn_160_36" type="button" btn-type='true' onclick="onSavePackageItemProfile()">保存/下一步</button>
<!-- 		<button class="btn submit  btn_160_36" type="button" btn-type='true' onclick="onPublishPackageItemProfile()">保存/发布</button> -->
		<button class="btn cancel  btn_160_36" onclick="goBack();" type="button">取消</button>
	</div>	
	</form>
</div>
</div>
<script type="text/javascript">
	require([ 'modules/presentation/presentation','modules/presentation/presentation_package_profile' ], function(obj) {
		
	});
</script>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_footer.jsp"%>