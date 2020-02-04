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
				<ul class="shezhi pl0">
					<li>
						<div class="radio-item pull-left">
            				<div class="radio-group">
								<input type="radio" name="itemTypeRadioGroup" id="isHtml" value="1"
									<c:if test="${presentationModelExternal.externalType == 1}"> checked </c:if>>
								<label class="radio-2" for="isHtml"></label>
							</div>
							HTML
						</div>
						<div class="radio-item pull-left">
            				<div class="radio-group">
								<input type="radio" name="itemTypeRadioGroup" id="isYouku" value="2"
									<c:if test="${presentationModelExternal.externalType == 2}"> checked </c:if>>
								<label class="radio-2" for="isYouku"></label>
							</div>
							优酷
						</div>
						<div class="pull-right">
							<div class="checkbox-group">
								<input type="checkbox" name="isCourse" id="isCourse"
									<c:if test="${presentationMap.is_course == 1}"> checked </c:if>
									<c:if test="${presentationMap.is_course == 0}"> "" </c:if>>
								<label class="checkbox-2" for="isCourse"></label>
							</div>
							发布后自动创建同名的课程
						</div>
					</li>
				</ul>
			</div>
			
			<div class="mt20">
				<!-- 选择要导入的课件 -->
				<ul class="shezhi pl0 b_none">
					<li>
						<div style="border:1px solid #e3e3e3;height: 130px;">
							<div class="pull-left  mt20 ml20" style="width: 830px;" >
								<textarea id="itemUrl" name="itemUrl" class="form-control" style="height:90px;">${presentationExternalItem.externalUrl}</textarea>
							</div>
							<div class="pull-right btn_box w100 mr20">
								<button class="btn btn_green btn36 mb10 w100"  type="button" onclick="javascript:onTestExternalItem()">测试</button>
								<button class="btn btn_red btn36 w100" type="button" onclick="javascript:onDelExternalItem()">清空</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
				
			<div class="mt10" style="margin-top: -30px;">
				<ul class="shezhi pl0 b_none">
					<li>
						<div id="itemBlankPanel" style=" background:#e3e3e3;height: 500px; <c:if test="${presentationModelExternal.externalType == 1 || presentationModelExternal.externalType == 2}"> display:none; </c:if>  ">
							<!-- <iframe class="form-control"></iframe> -->
							<div class="exernal" id="pointDiv" style="overflow: auto;">
								<button type="button" id="pointButton" class="hdp"></button>
								<button type="button" id="mediaButton" class="dmt"></button>
								<div class="wb100"><span class="f18">此区域显示网页或视频</span></div>
							</div>
							
						</div>
						<iframe id="itemFramePanel" class="form-control wb100" style="height: 500px; <c:if test="${presentationModelExternal.externalType != 1}"> display:none; </c:if>" src="<c:if test="${presentationModelExternal.externalType == 1}">${presentationExternalItem.externalUrl}</c:if>">
							您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。
						</iframe>
						
						<div id="itemYoukuPanel" style=" background:#e3e3e3;height: 500px; <c:if test="${presentationModelExternal.externalType != 2}"> display:none; </c:if>">
<!-- 							<EMBED name=movie_player id="movie_player"   -->
<!--                                     pluginspage=http://www.macromedia.com/go/getflashplayer   -->
<%--                                     src='<c:if test="${presentationModelExternal.externalType == 2}">http://player.youku.com/player.php/Type/Folder/Fid/4699493/Ob/1/Pt/51/sid/${StringUtils.getYoukuId(presentationExternalItem.externalUrl)}/v.swf</c:if>' --%>
<!--                                     width=916 height=500 type=application/x-shockwave-flash   -->
<!--                                     flashvars="isShowRelatedVideo=false&showAd=0&show_pre=0&show_next=0&isAutoPlay=false&isDebug=false&UserID=&winType=interior&playMovie=true&MMControl=false&MMout=false&RecordCode=1001,1002,1003,1004,1005,1006,2001,3001,3002,3003,3004,3005,3007,3008,9999"   -->
<!--                                     allowfullscreen="true" quality="high" bgcolor="#000000"   -->
<!--                                     d="movie_player"></EMBED>   -->



                               ${presentationExternalItem.externalUrl} 
                                    
                                
						</div>
    					<input value="" type="hidden" id="hid_youku_url">  
    					<input value="" type="hidden" id="hid_video_url">     
					</li>
				</ul>
			</div>
			<div class="btn_box">
				<button class="btn submit  btn_160_36" type="button" onclick="onSaveExternalItem()">保存/下一步</button>
				<!-- <button class="btn submit  btn_160_36" type="button" btn-type='true' onclick="onPublishExternalItem()">保存/发布</button> -->
				<button class="btn cancel  btn_160_36" onclick="prevStep();" type="button">取消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	require([ 'modules/presentation/presentation','modules/presentation/presentation_external_profile' ],
			function(obj) {

			});
</script>


<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_footer.jsp"%>