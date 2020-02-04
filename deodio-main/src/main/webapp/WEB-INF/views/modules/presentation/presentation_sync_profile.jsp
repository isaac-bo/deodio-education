<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_header.jsp"%>
<!-- <ul class="pre1_tab mt20 pl20" id="myTab">
	<li class="active"><a href="javascript:void(0)">设置课件的规则</a></li>
	<li><a href="javascript:void(0)">选择要导入的课件</a></li>
	<li><a href="javascript:void(0)">相关附件</a></li>
	<li class="active"><a href="#geshi1">选择课件格式</a></li>
</ul -->

<div class="tab-nav-con">
	<ul class="nav nav-tabs set_tab" role="tablist" style="background-color: #f2f2f5 !important;">
		<li class="active" role="presentation"><a href="javascript:void(0)" >设置课件的规则</a></li>
		<li onclick="setFlashRule();" ><a href="javascript:void(0)" >选择要导入的课件</a></li>
	</ul>
	<div class="con-corner"></div>
</div>

<div class="pre1_con" style="margin-top: 55px;">
<!-- 设置课件的规则 -->
<div class="tab-pane" id="shezhi1">
	<form id="syncProfileForm">
	<div>花费您几秒钟时间，请选择您要创建的类型: Slide Show 还是 Sync Media ? </div>
	<ul class="shezhi">
		<li>
			<div class="radio-group pull-left">
				<input type="radio" name="isSlideShow" id="man7" value="1" <c:if test="${empty presentationModelSync.isSlideShow}"> checked </c:if> <c:if test="${presentationModelSync.isSlideShow == 1}"> checked </c:if>>
				<label class="radio-2 " for="man7"></label>
			</div>
			<span class="pull-left ml10">Sync Media</span>
			<div class="r_con f10">
				<span class="glyphicon glyphicon-info-sign" style="color:#ccc;"></span>
				课件既包括您上传的图片, MS PowerPoint（slide），也包括多媒体（media）； 您可以通过设置slide和media在时间轴上同步的方式编辑您的课件。
			</div>
		</li>
		<li>
			<div class="radio-group pull-left">
				<input type="radio" name="isSlideShow" id="man8" value="0" <c:if test="${presentationModelSync.isSlideShow == 0}"> checked </c:if>>
				<label class="radio-2" for="man8"></label>
			</div>
			<span class="pull-left ml10">Slide Show</span>
			<div class="r_con">
				<h4></h4>
				<span class="glyphicon glyphicon-info-sign" style="color:#ccc;"></span>
				课件只包含MS PowerPoint, 各种图片，当然您可以给您的每一张图片（slide）录制语音，以方便您的成员学习；
			</div>
		</li>
	</ul>
	
	<div id="slidesShowSettingPanel" style="display:none;">
	<div class="mt20 pt10" id="slideDiv">是否需要系统自动设置slide播放时间频率和方式（自动调节），或者您也可以选择手动方式调节（手动调节）slide播放时间频率和播放方式。</div>
	<ul class="shezhi" id="slideUl">
		<li>
			<div class="radio-group pull-left">
				<%-- <c:if test="${empty presentationModelSync.isManually}"><c:set value="0" target="${presentationModelSync}" property="isManually"/> </c:if> --%>
				<input type="radio" name="isManually" id="man9" check-type="required"  value="0" <c:if test="${presentationModelSync.isManually == 0}"> checked </c:if>>
				<label class="radio-2 checked" for="man9"></label>
			</div>
			<span class="pull-left ml10">自动调节</span>
			<div class="r_con2 error-div">
				<!-- 设置Slide播放间隔：<span>60</span> 秒 -->
				设置Slide播放间隔：<input type="text" name="interval"  check-type="number" required-message="请输入播放时间间隔!" id="slideLength" class="text form-control"  <c:if test="${presentationModelSync.isManually == 1}"> disabled </c:if> value=<c:if test="${presentationModelSync.isManually == 0}"> "${presentationModelSync.interval}" </c:if>> 秒
			</div>
		</li>
		<li>
			<div class="radio-group pull-left">
				<input type="radio" name="isManually" id="man6" value="1" <c:if test="${presentationModelSync.isManually == 1}"> checked </c:if>>
				<label class="radio-2 checked" for="man6"></label>
			</div>
			<span class="pull-left ml10">手动调节</span>
			<div class="r_con2">
				<!-- 初始化片长：<span>60</span> 秒 -->
				初始化片长：<input type="text" name="length" id="initialLength" non-required="true" check-type="number" required-message="请输入初始化片长!"  class="text form-control" <c:if test="${presentationModelSync.isManually == 0}"> disabled </c:if> value=<c:if test="${presentationModelSync.isManually == 1}"> "${presentationModelSync.length}" </c:if>> 秒
			</div>
		</li>
	</ul>
	</div>
	<div class="mt20">

		
		<ul class="shezhi pl0">
			<li>
			<div class="pull-left text-right w150">&nbsp;</div>
				<div class="checkbox-group">
					<input type="checkbox" name="isInitSyncPoint" id="one1" 
						<c:if test="${presentationModelSync.isInitSyncPoint == 1}"> checked </c:if>
						<c:if test="${presentationModelSync.isInitSyncPoint == 0}"> "" </c:if>
					>
					<label class="checkbox-2" for="one1"></label>
				</div>
				课件起点是否设置初始化Slide，需要初始化。0s位置自动添加第一张Slide
			</li>
<!-- 			<li> -->
<!-- 				<div class="pull-left text-right w150">达标学时百分比：</div> -->
<!-- 				<div class="r_con2"> -->
<!-- 					<span>60</span> % -->
<%-- 					<input type="text" name="persentationPercentage" check-type="number" min-max="0-3" required-message="请输入达标学时百分比!" id="persentationPercentage" value="${presentationMap.persentation_percentage}" class="text form-control">% --%>
<!-- 				</div> -->
<!-- 			</li> -->
			<li>
				<div class="pull-left"><span class="input-title-span">＊</span>Presentation通过条件：</div>
				<div class="r_con2">
					<div class="checkbox-group">
						<input type="checkbox" name="isPassHours" id="one2"
						<c:if test="${presentationMap.is_pass_hours == 1}"> checked </c:if>
						<c:if test="${presentationMap.is_pass_hours == 0}"> "" </c:if>>
						<label class="checkbox-2" for="one2"></label>
					</div>
					学习进度到达要求<input type="text" name="persentationPercentage" non-required='true' check-type="number" min-max="0-3" min-max-num="0-100" required-message="请输入0~100之间的整数" id="persentationPercentage" value="${presentationMap.persentation_percentage}" class="text form-control">%
					
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
<!-- 					通过所有的课件学习和测验 -->
<!-- 				</div> -->
<!-- 			</li> -->
			<li>

				<div class="r_con2">
					<div class="checkbox-group">
						<input type="checkbox" name="isPassQuizs" id="one4"
						<c:if test="${presentationMap.is_pass_quizs == 1}"> checked </c:if>
						<c:if test="${presentationMap.is_pass_quizs == 0}"> "" </c:if>>
						<label class="checkbox-2" for="one4"></label>
					</div>
					通过Presentation的测验
				</div>
			</li>
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
		<!-- <button class="btn submit  btn_160_36" type="button" onclick="setFlashRule()">保存/下一步</button> -->
		<button class="btn submit  btn_160_36" type="button" btn-type='true'>保存/下一步</button>
		<button class="btn cancel  btn_160_36" onclick="goBack()" type="button">取消</button>
	</div>	
	</form>
</div>
</div>
<script type="text/javascript">
	require([ 'modules/presentation/presentation_sync_profile' ], function(obj) {
		
	});
</script>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_footer.jsp"%>