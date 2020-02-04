<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_header.jsp"%>

<div class="tab-nav-con">
	<ul class="nav nav-tabs set_tab" role="tablist">
		<li class="active" role="presentation"><a href="${ctx}/presentation/package/files.html?presentationId=${presentationMap.id}" role="tab" data-toggle="tab">选择要导入的课件</a></li>
		<li role="presentation"><a href="javascript:;" role="tab" data-toggle="tab">设置课件的规则</a></li>
	</ul>
	<div class="con-corner"></div>
</div>

<div class="pre1_con" style="height: 920px;margin-top: 55px;">
	<div id="myTabContent"  style="height:560px">
		<!-- 选择要导入的课件 -->
		<div class="tab-pane" id="daoru1">
			<div class="c929292 f12">
				请上传您要制作课件的PDF, MS Word文件，可以单独上传，亦可以通过*.rar, *.zip等压缩文件上传。
				<br><br>
				您可以有以下三种方式创建或者上传课件所需要的内容文件： 
				<br><br>
				1. 您可以通过拖拽（局限于高版本浏览器）或通过点击本地上传上传您所需要的内容文件；<br>
				2. 您可以选择系统文件库来选择已经上传过的内容文件；<br>
				所有最新的文件在上传成功后，系统会根据需求转换成系统所支持的文件格式。
			</div>
			<div class="daoru">
				<form class="" id='package_upload_init' method="post" enctype="multipart/form-data">
					<div class="upload mCustomScrollbar" id="packagePanel" style="height: 485px; width: 100%;">
						<h2>非标准课件包</h2>
						<c:if test="${!empty presentationFilesList}">
							<div class="con bg_fff" id="packageDiv" style="overflow: auto;height:400px;">
								<button type="button" id="packageButton" class="hdp" style="display: none">选择需要上传的课件包拖拽到此处</button>
								<ul class="foncom upload_start_area " data-mcs-theme="dark-thin" style='height: 395px;' id='upload_tbody_package'>
						</c:if>
						<c:if test="${empty presentationFilesList}">
							<div class="con" id="packageDiv" style="overflow: auto;height:400px;">
								<button type="button" id="packageButton" style="margin-top:100px" class="hdp">选择需要上传的课件包拖拽到此处</button>
								<ul class="foncom upload_start_area " data-mcs-theme="dark-thin" style='display: none, height:220px;' id='upload_tbody_package'>
						</c:if>
						<c:forEach items="${presentationFilesList}" var="item">
						<c:if test="${item.package_count!=0}">
							<div id="file_${item.id}" class="package-info _upload_tbody_package">
								<li>
									<span class="glyphicon glyphicon-picture pull-left f18"></span>
									<div class="pull-left ml10" id="totalWidth_${item.id}" style="width: 960px;">
										<div class="pull-left filename file_name" style = "width:960px" title="${item.file_name}">
											<em class="c_blue pull-right file_percent">100%</em>${item.file_original_name}
											<input type="hidden" value="${imgStatic}${item.file_dir}" id="fileDir" name="fileDir">
										</div>
										<div class="load pull-left" style="width:960px">
											<div id="percent_${item.id}" class="load_blue" style="width: 100%"></div>
										</div>
									</div>
									<input type="hidden" name="packageId" value="${item.id}">
									<button type="button" style="padding-top:-10px;" class="pull-right up_del_icon"   onclick="onDelPackageFiles('${item.id}');">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								</li>
							</div>
							</c:if>
						</c:forEach>
						</ul>
					</div>
					<div class="text-right">
						<div id="dropdown_div" class="btn-group bootstrap-select dropup" style="top:3px;">
							<button class="btn dropdown-toggle btn-default" data-toggle="dropdown" type="button" data-id="">
								<span class="filter-option pull-left">本地上传</span> <span class="bs-caret"> <span class="caret"></span></span>
							</button>
							<div class="dropdown-menu open" style="max-height: 559px; overflow: hidden; min-height: 0px;">
								<ul class="dropdown-menu inner" role="menu" style="max-height: 547px; overflow-y: hidden; min-height: 0px;">
									<li data-original-index="0">
										<a class="" data-tokens="null" style="" tabindex="0"> 
											<span style="width: 100%;display: block;" class="text" onclick="onPopupPackageFilesList(1)">系统文件库</span><span class="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
									<li class="selected" data-original-index="1">
										<a class="" data-tokens="null" style="height: 30px;" tabindex="0">
											
									
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
			<input type="hidden" id="presentationId" value="${presentationMap.id}">
			</form>
		</div>
	</div>
</div>
<div style="margin-top:200px"></div>
<div class="btn_box">
	<button class="btn submit  btn_160_36" type="button" onclick="onSavePackageItemToProfile()">保存/下一步</button>
<!-- 	<button class="btn submit  btn_160_36" type="button" onclick="onSavePackageItem()">保存/预览</button>
	<button class="btn submit  btn_160_36" type="button" btn-type='true' onclick="onPublishPackageItem()">保存/发布</button> -->
	<button class="btn cancel  btn_160_36"  onclick="goBack()" type="button">取消</button>
		
</div>

</div>
</div>
<input type="hidden" id="isRefreshFlag" name="isRefreshFlag" value="0">
<script type="text/javascript">
	require(['modules/presentation/presentation', 'modules/presentation/presentation_package_files'], function(obj) {

	});
</script>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_package_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_footer.jsp"%>