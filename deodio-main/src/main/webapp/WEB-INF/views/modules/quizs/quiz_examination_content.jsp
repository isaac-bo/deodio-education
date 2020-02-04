<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/quizs/quiz_examination_content_header.jsp"%>
<ul class="pre1_tab mt20 pl20" id="myTab">
</ul>


<script type="text/javascript">
	require([ 'modules/quizs/quiz_examination_content' ], function(obj) {

	});
</script>

<%@ include file="/WEB-INF/views/modules/quizs/quiz_examination_content_footer.jsp"%>

<script id="chooseFlashWin" type="text/x-dot-template">
	<div class="geshi1 p20">
		<a href="javascript:void(0)" class="btn1" onmouseover="hoverModule(1)"  onclick="chooseModule(0)">手动创建试卷</a>
		<a href="javascript:void(0)" class="btn2" onmouseover="hoverModule(2)" onclick="chooseModule(1)">自动创建试卷</a>
	</div>

	<div class="mt20 f12 c929292 p20" id="format1">
		用户根据自身要求，可以通过拖拽，以及所见即所得的方式手动创建试卷，试题。
	</div>
	<div class="mt20 f12 c929292 p20" id="format2" style="display:none">
		用户根据自身要求，通过选择相关的分类，题库，以及设置必考，随机的题目，使系统自动创建用户所需要的试卷。
	</div>

</script>