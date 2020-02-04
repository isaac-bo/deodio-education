<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_header.jsp"%>
<ul class="pre1_tab mt20 pl20" id="myTab">
	<!--<li class="active"><a href="javascript:void(0)">设置课件的规则</a></li>-->
	<!--<li><a href="javascript:void(0)">选择要导入的课件</a></li>-->
	<!--<li><a href="javascript:void(0)">相关附件</a></li>-->
	<!-- <li class="active"><a href="#geshi1">选择课件格式</a></li> -->
</ul>

<div class="pre1_con">
	<!-- 设置课件的规则 -->
	<div class="tab-pane" id="shezhi1">
		<form id="scromProfileForm">
			<div class="mt10">
				<!-- 选择要导入的课件 -->
				<ul class="shezhi pl0 b_none">
					<li>
						<div style="border:1px solid #e3e3e3;height: 90px;">
							
							<div class="col-sm-12 mt20">
								<div class="pull-left">
									<input type="text" id="zipFileName"  disabled="disabled"  name="zipFileName" value="${presentationFiles.fileOriginalName}" class="form-control w400"> 
									
								</div>
								<div class="user-item pull-left" style="height: 35px;">
									<div class="user-buttons">
										
								<div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
                                    <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
                                   <input accept="application/x-zip-compressed" id="presentationUploadFile" type="file" name="presentationUploadFile" multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
                                </div>							
										
								<div><button class="certify" type="button" style="display: block; margin: -33px 0px 0px 136px" onclick="onDelScromItem();">删除</button></div>
									</div>
									<input type="hidden"  id="uploadDir" name="uploadDir">
								</div>
								<div class="c929292 f12 pull-left mt10 ml10">
									上传课件的内容需符合Scrom 1.2 或 2004 标准
								</div>
							</div>
							<div>
								<div class="update-bar border-radius progress-bar" id="progressBar" style="margin: 0 auto 0 15px;position: relative;top: 10px;width: 886px;">
									<div class="bar" style="width: 0%; height: 18px;background: #00e4ff;"></div>
								</div>
							</div>
							
						</div>
					</li>
				</ul>
			</div>
			<div class="mt20">
<!-- 				<ul id="itemPanel" class="shezhi pl0" style="display:none;padding-bottom: 0px;border-bottom:0px;"> -->
<!-- 					<li> -->
<!-- 						<div class="r_con2"> -->
<!-- 							<div class="checkbox-group"> -->
<%-- 								<input type="checkbox" name="isCountDown" id="isCountDown" onChange="onChooseCountDown()" <c:if test="${presentationModelScrom.isCountDown == 1}"> checked </c:if>> --%>
<%-- 								<label class="checkbox-2 <c:if test="${presentationModelScrom.isCountDown == 1}"> checked </c:if>" for="isCountDown"></label> --%>
<!-- 							</div> -->
<!-- 							设置该Presentation下各章节学习时长 -->
<!-- 						</div> -->
<!-- 					</li> -->
<!-- 					<li id="itemListPanel" class="items-con"  -->
<%-- 						<c:if test="${presentationModelScrom.isCountDown == 1}"> style="display:block" </c:if> --%>
<%-- 						<c:if test="${presentationModelScrom.isCountDown != 1}"> style="display:none" </c:if> > --%>
<!-- 						<div class="pull-left text-right w150">设置规定完成时间：</div> -->
<!-- 						<div id="itemList" style="margin-top: -20px;"> -->
<!-- 						</div> -->
<!-- 					</li> -->
<!-- 				</ul> -->
				<ul class="shezhi pl0">
<!-- 					<li> -->
<!-- 						<div class="pull-left text-right w150">达标学习进度百分比：</div> -->
<!-- 						<div class="r_con2"> -->
<!-- 							<span>60</span> % -->
<!-- 							<input type="text" name="persentationPercentage" check-type="number" min-max="0-2" required-message="达标学习进度百分比!" -->
<%-- 								id="persentationPercentage" value="${presentation.persentationPercentage}" onchange="onCompareItemList()" class="text form-control"><span id="listSize">&nbsp;/&nbsp;y&nbsp;</span> --%>
<!-- 						</div> -->
<!-- 					</li> -->
					<li>
						<div class="pull-left"><span class="input-title-span">＊</span>Presentation通过条件：</div>
						<div class="r_con2">
<!-- 							<div class="checkbox-group"> -->
<!-- 								<input type="checkbox" name="isPassHours" id="one2" -->
<%-- 									<c:if test="${presentationMap.is_pass_percentage == 1}"> checked </c:if> --%>
<%-- 									<c:if test="${presentationMap.is_pass_percentage == 0}"> "" </c:if>> --%>
<!-- 								<label class="checkbox-2" for="one2"></label> -->
<!-- 							</div> -->
							学习进度达到要求(<input type="text" name="persentationPercentage" 
								id="persentationPercentage"   value="" class="text form-control"><span id="listSize">&nbsp;/&nbsp;y&nbsp;</span>)
						</div>
					</li>
<!-- 					<li> -->
<!-- 						<div class="r_con2"> -->
<!-- 							<div class="checkbox-group"> -->
<!-- 								<input type="checkbox" name="isPassElements" id="one3" -->
<%-- 									<c:if test="${presentationMap.is_pass_elements == 1}"> checked </c:if> --%>
<%-- 									<c:if test="${presentationMap.is_pass_elements == 0}"> "" </c:if>> --%>
<!-- 								<label class="checkbox-2" for="one3"></label> -->
<!-- 							</div> -->
<!-- 							通过所有课件包内章节 -->
<!-- 						</div> -->
<!-- 					</li> -->
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
								<label id="lable_isPassHours" class="checkbox-2" for="one5"></label>
							</div>
							发布后自动创建同名的课程
						</div>
					</li>
				</ul>
			</div>
			<div class="form_line2"></div>
			<div class="btn_box">
				<button class="btn submit  btn_160_36" type="button" onclick="onSubmitScromItem()">保存/下一步</button>
<!-- 				<button class="btn submit  btn_160_36" type="button" btn-type='true' onclick="onPublishScromItem()">保存/发布</button> -->
				<button class="btn cancel  btn_160_36" onclick="goBack()" type="button">取消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	require([ 'modules/presentation/presentation','modules/presentation/presentation_scrom_profile' ],
			function(obj) {

			});
</script>

<script id="data_template" type="text/x-dot-template">

 {{~it.data:v:index}}
	<div class='r_con2 error-div mt20'>
		<input id='{{=v.id}}' class='text form-control'	type='text' value='{{=isNullFormat(v.timeAllowed)}}' check-type="number" onchange='isCorrectScromTime(this.id,this.value)'> 秒 - {{=v.identifier}} : {{=v.title}}
	</div>
{{~}}	

</script>

<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_footer.jsp"%>