<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_header.jsp"%>
<ul class="pre1_tab mt20 pl20" id="myTab">
</ul>
<div class="pre1_con">
	<div id="myTabContent">
		<input type="hidden" name="presentationModel" id="presentationModel" value="${presentation.presentationModel}"/>
	</div>
</div>


<script type="text/javascript">
	require([ 'modules/presentation/presentation_content' ], function(obj) {

	});
</script>

<%@ include file="/WEB-INF/views/modules/presentation/presentation_content_footer.jsp"%>

<script id="chooseFlashWin" type="text/x-dot-template">
	<div class="geshi1 p20">
		<a href="javascript:void(0)" class="btn1" onmouseover="hoverModule(1)"  onclick="chooseModule(0)">SCORM 标准课件</a>
		<a href="javascript:void(0)" class="btn2" onmouseover="hoverModule(2)" onclick="chooseModule(1)">非标准课件包</a>
		<a href="javascript:void(0)" class="btn3" onmouseover="hoverModule(3)" onclick="chooseModule(2)">自主设置课件包</a>
		<a href="javascript:void(0)" class="btn4" onmouseover="hoverModule(4)" onclick="chooseModule(3)">外部链接课件</a>
	</div>
	<div class="mt20 f12 c929292 p20" id="format1">
		SCORM 共享内容对象参考模型（Sharable Content Object Reference Model）是由美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息请访问SCORM官方网站 ：www.adl.net.gov
	</div>
	<div class="mt20 f12 c929292 p20" id="format2" style="display:none">
		知识对传统的文件课件包的兼容。将您制作好的PDF文件，MS Word文件打包成zip格式，然后上传到该系统。学习者通过该平台学习相关知识，平台自动跟踪记录学习数据。
	</div>
	<div class="mt20 f12 c929292 p20" id="format3" style="display:none">
		通过自行设置课件包，可以设置同步点，同步Slides和多媒体文件.
	</div>
	<div class="mt20 f12 c929292 p20" id="format4" style="display:none">
		直接填入外部链接的课件，可以是网页的首页地址，也可以是一个纯视频播放的URL。
	</div>
</script>