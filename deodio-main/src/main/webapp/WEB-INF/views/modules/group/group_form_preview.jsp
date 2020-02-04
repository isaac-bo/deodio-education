<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<div class="content g_border">
   
		
	<div class="g_right" style="margin-left: 0px;" >
		<h3 class="r_title r_title_bg7">注册表单
		<a style="cursor:pointer;padding-left: 58em" onclick="backLastPage();">返回</a>
		</h3>
		<p class="p20 f14">
			给所有的注册人员创建一个注册表单 （例如：问卷调查），该注册表单能够帮助我们了解一些注册用户的基本想法和信息。
			
		</p>
		<div class="p20">
			<div class="jr_l_box pull-left" id="draggableContent" style="min-height: 500px;min-width: 1100px;">					
					<%@include file="/WEB-INF/views/modules/group/group_form_template.jsp" %><%-- ${preData}	 --%>
								
			</div>
			
			<div class="clearfix"></div>
		</div>
		<div class="form_line2"></div>
		
	</div>
	<input type="hidden" value="${preData}" id="preData">
	<input type="hidden" value="${groupId}" id="groupId">
</div>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>





	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_form_preview'],function(obj){
		
		});
	</script>
</body>
</html>