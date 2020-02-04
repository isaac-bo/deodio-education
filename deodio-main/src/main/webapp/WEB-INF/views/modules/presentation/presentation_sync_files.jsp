<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_header.jsp"%>
<%-- <ul class="pre1_tab mt20 pl20" id="myTab">
	<li><a href="${ctx}/presentation/sync/profile.html?presentationId=${presentationMap.id}">设置课件的规则</a></li>
	<li class="active"><a href="javascript:void(0)" >选择要导入的课件</a></li>
	<li><a href="javascript:void(0)" >相关附件</a></li>
</ul> --%>
	<input type="hidden" id="isInitSyncPoint_syn" value="${presentationModelSync.isInitSyncPoint}">
	<input type="hidden" id="isSlideShow_syn" value="${presentationModelSync.isSlideShow}">
	<input type="hidden" id="interval_syn" value="${presentationModelSync.interval}">
	
	
<div class="tab-nav-con">
	<ul class="nav nav-tabs set_tab" role="tablist" style="background-color:#f2f2f5 !important;">
		<li ><a href="${ctx}/presentation/sync/profile.html?presentationId=${presentationMap.id}"  >设置课件的规则</a></li>
		<li class="active" ><a href="javascript:void(0)">选择要导入的课件</a></li>
	</ul>
	<div class="con-corner"></div>
</div>

<div class="pre1_con" style="height: 750px;margin-top: 55px;">
	<div id="myTabContent">
		<!-- 选择要导入的课件 -->
		<div class="tab-pane" id="daoru1">
			<div class="c929292 f12">
				<!-- 组成课件的内容分为两大部分：<br>
				&nbsp;&nbsp;&nbsp;幻灯片或图片（*.png;*.jpg;*.gif;*.tif;*.ppt;*.pptx）; <br>
				&nbsp;&nbsp;&nbsp;多媒体视频或音频（*.flv; *.avi; *.mpeg; *.wmv; *.mpg; *.mov; *.avi; *.mp4; *.3pg; *.3gp; *.mp3; *.wav; *.wma; *.f4v;*.m4v）;
				<br><br> -->
				<span class=" icon-info-signs">&nbsp;</span>您可以有以下三种方式创建或者上传课件所需要的内容文件： 
				<br><br>
				1. 您可以通过拖拽（局限于高版本浏览器）或通过点击本地上传上传您所需要的内容文件；<br>
				2. 您可以选择系统文件库来选择已经上传过的内容文件；<br>
				3. 您可以通过在线录制的方式在线录制多媒体文件内容。<br><br>
				所有最新的文件在上传成功后，系统会根据需求转换成系统所支持的文件格式。
				
			</div>
			<div class="daoru">
				<form class="" id='slides_upload_init' method="post" enctype="multipart/form-data">
					<div class="upload" <c:if test="${presentationModelSync.isSlideShow==0}">style="margin-left: 202px;"</c:if> id="slidesPanel">
						<h2>SLIDES<span class="icon-question-sign" data-toggle='popover'></span><input type="hidden" name="tipHidden" value="幻灯片或图片（*.png;*.jpg;*.gif;*.tif;*.ppt;*.pptx）;"/></h2>
						<c:if test="${!empty syncSlidesList}">
							<div class="con bg_fff" id="pointDiv" style="overflow: auto;">
								<button type="button" id="pointButton" class="hdp" style="display: none">选择需要上传的图片或Power Point</button>
								<ul class="foncom upload_start_area" data-mcs-theme="dark-thin" style='height: 220px;' id='upload_tbody_slides'>
						</c:if>
						<c:if test="${empty syncSlidesList}">
							<div class="con" id="pointDiv" style="overflow: auto;">
								<button type="button" id="pointButton" class="hdp">选择需要上传的图片或Power Point</button>
								<ul class="foncom upload_start_area" data-mcs-theme="dark-thin" style='display: none, height:220px;' id='upload_tbody_slides'>
						</c:if>
						<c:forEach items="${syncSlidesList}" var="item">
							<div class="upload_tbody_slides" id=file_${item.id}>
								<li><c:if test="${item.slideExt=='pptx'||item.slideExt=='ppt'}">
										<span class="glyphicon glyphicon-duplicate pull-left f18"></span>
									</c:if> 
									<c:if test="${item.slideExt!='pptx'&&item.slideExt!='ppt'}">
										<span class="glyphicon glyphicon-picture pull-left f18"></span>
									</c:if>
									<div class="pull-left ml10" id=totalWidth_${item.id} style="width: 430px;">
										<div class="pull-left filename file_name" title="${item.slideName}">
											<em class="c_green pull-right file_percent">100%</em>${item.slideOriginalName}
										</div>
										<div class="load pull-left" style="width: 440px;">
											<div id=percent_${item.id} class="load_green" style="width: 100%"></div>
										</div>
									</div>
									<button type="button" class="pull-right up_del_icon" onclick="delSyncSlide('${item.id}');">
										<span class="glyphicon glyphicon-trash"></span>
									</button></li>
							</div>
						</c:forEach>
						</ul>
					</div>
					<div class="text-right">
						<div class="btn-group bootstrap-select" style="top:3px;">
							<button class="btn dropdown-toggle btn-default" data-toggle="dropdown" type="button" data-id="">
								<span class="filter-option pull-left">本地上传</span> <span class="bs-caret"> <span class="caret"></span></span>
							</button>
							<div class="dropdown-menu open" style="max-height: 600px; overflow: hidden; min-height: 0px;">
								<ul class="dropdown-menu inner" role="menu" style="max-height: 600px; overflow-y: hidden; min-height: 0px;">
									<li data-original-index="0">
										<a class="" data-tokens="null" style="" tabindex="0"> 
											<span class="text" onclick="sysSlideFactory()">系统幻灯片库</span><span class="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
									<li class="selected" data-original-index="1">
										
										<a class="" data-tokens="null" style="height: 30px;" tabindex="0">
<!-- 											<div> -->
<!-- 												<span class="text">本地上传</span> -->
<!-- 											</div> -->
<!-- 											<div id="file_swf_slides"></div> <span class="glyphicon glyphicon-ok check-mark"></span> -->
											
												<div id="uploader-demo">
											    <!--用来存放item-->
											    <div id="fileList" class="uploader-list"></div>
											    <div id="filePicker" >本地上传</div>
											</div>
											
											
											
										</a>
										
										
										
										
										
										
										
										
									</li>
								</ul>
							</div>
						</div>
					</div>
			</div>
			</form>
			<form class="" id='media_upload_init' method="post" enctype="multipart/form-data">
				<div <c:if test="${presentationModelSync.isSlideShow==0}">style="display: none;"</c:if>  class="upload" id="syncMedia">
					<h2>多媒体<span class="icon-question-sign" data-toggle='popover'></span><input type="hidden" name="tipHidden" value="多媒体视频或音频（*.flv; *.avi; *.mpeg; *.wmv; *.mpg; *.mov; *.avi; *.mp4; *.3pg; *.3gp; *.mp3; *.wav; *.wma; *.f4v;*.m4v）;"/></h2>                                                                                                                                                                       </span></h2>
						<c:if test="${!empty presentationSyncMediaList}">
							<div class="con bg_fff" id="mediaDiv" style="overflow: auto;">
							<button type="button" id="mediaButton" class="dmt" style="display: none">选择需要上传的视频或音频文件</button>
							<ul class="foncom upload_start_area" data-mcs-theme="dark-thin" style='height: 220px;' id='upload_tbody_media'>
						</c:if>
						<c:if test="${empty presentationSyncMediaList}">
							<div class="con" id="mediaDiv" style="overflow: auto;">
							<button type="button" id="mediaButton" class="dmt">选择需要上传的视频或音频文件</button>
							<ul class="foncom upload_start_area" data-mcs-theme="dark-thin" style='display: none,height: 220px;' id='upload_tbody_media'>
						</c:if>
						<!-- 上传进度 -->
						
							<c:forEach items="${presentationSyncMediaList}" var="item" varStatus="status">
								<div class="upload_tbody_media" id=file_${item.mediaId}>
									<li><c:if test="${item.mediaExt=='mp3'}">
											<span class="glyphicon glyphicon-music pull-left f18"></span>
										</c:if> 
										<c:if test="${item.mediaExt!='mp3'}">
											<span class="glyphicon glyphicon-facetime-video pull-left f18"></span>
										</c:if> 
									
											<div class="pull-left ml10" id=totalWidth_${item.mediaId} style="width: 430px;">
												<div class="pull-left filename file_name" title="${item.mediaName}">
													<em class="c_green pull-right file_percent">100%</em>${item.mediaOriginalName}
												</div>
												<div class="load pull-left"  style="width: 440px;">
													<div id="percent_${item.mediaId}" class="load_green" style="width: 100%"></div>
												</div>
											</div>
											<button type="button" class="pull-right up_del_icon" onclick="delMedia('${item.mediaId}');">
												<span class="glyphicon glyphicon-trash"></span>
											</button>
										
								
										</li>
								</div>
							</c:forEach>
						</ul>
					</div>
					<div class="text-right">
						<div class="btn-group bootstrap-select" style="top:3px;">
							<button class="btn dropdown-toggle btn-default" data-toggle="dropdown" type="button" data-id="">
								<span class="filter-option pull-left">本地上传</span> <span class="bs-caret"> <span class="caret"></span></span>
							</button>
							<div class="dropdown-menu open" style="max-height: 559px; overflow: hidden; min-height: 0px;">
								<ul class="dropdown-menu inner" role="menu" style="max-height: 547px; overflow-y: hidden; min-height: 0px;">
									<li data-original-index="0">
										<a class="" data-tokens="null" style="" tabindex="0"> 
											<span class="text" onclick="sysMediaFactory()">系统多媒体库</span> <spanclass="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
									<li class="selected" data-original-index="1">
										<a class="" data-tokens="null" style="height: 30px;" tabindex="0">
												<div id="uploader-demo">
											    <!--用来存放item-->
												    <div id="fileList" class="uploader-list"></div>
												    <div id="filePickerMedia" >本地上传</div>
												</div>
											<div id="file_swf_multimedia"></div> <span class="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
									<li data-original-index="0">
										<a class="" data-tokens="null" style="" tabindex="0"> 
											<span class="text">在线录制</span> <span class="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="form_line2"></div>
<div class="btn_box">
	<button class="btn submit  btn_160_36" type="button" onclick="saveSubmitSyncFiles()">保存/下一步</button>
	<button class="btn cancel  btn_160_36" onclick="goBack()" type="button">取消</button>
</div>

</div>
</div>
<script type="text/javascript">
	require([ 'modules/presentation/presentation_sync_files' ], function(obj) {

	});
</script>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_sync_slides_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_sync_media_dialogue.jsp"%>

<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_footer.jsp"%>