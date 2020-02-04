<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<div class="content g_border">


<%@ include file="/WEB-INF/views/modules/group/group_left_menu.jsp"%>


	<div class="g_right mess_right">
		<div class="des_text">
			<span class="icon-info-sign">&nbsp;</span>给所有的注册人员创建一个注册表单 （例如：问卷调查），该注册表单能够帮助我们了解一些注册用户的基本想法和信息。
		</div>
		<form action="${ctx}/group/form/preview.html" id="preViewForm" method="post" target="_blank">
		<input type="hidden" name="preData" id="pre_data">
		<input value="${groupId}" id="groupId" type="hidden" name="groupId">
		<div class="p20">
		
			<div class="jr_l_box pull-left" id="draggableContent" style="min-height: 500px;">					
				<%@include file="/WEB-INF/views/modules/group/group_form_template.jsp" %>				
			</div>
			<div class="jr_r_box pull-right">
				<a id="radio_draggable" href="javascript:;"><img src="${ctx}/resources/img/group/ti1.png" alt="" class="mb20"></a>
				<a id="checbox_draggable" href="javascript:;"><img src="${ctx}/resources/img/group/ti2.png" alt="" class="mb20"></a>
				<a id="validation_draggable" href="javascript:;"><img src="${ctx}/resources/img/group/ti3.png" alt=""></a>
			</div>
			<div class="clearfix"></div>
			<div>
				<div class="checkbox-group pull-left pt10">
					<input  type="checkbox" name="activestatus" id="active_status">
					<label class="checkbox-2" for="active_status"></label>
				</div><span class="mt10 ml10">激活该注册表单</span>
			</div>
		</div>
	
		<div class="form_line2"></div>
		<div class="btn_box">
			<button class="btn preview btn_160_36" onclick="previewBtn();" type="button">预览</button>
			<button class="btn submit btn_160_36" btn-type="true" type="button">提交</button>
			<!-- <button class="btn cancel btn_160_36" type="button">返回</button> -->
			
		</div>
		</form>
	</div>
</div>



<input value="" id="draggableVle" type="hidden">

<input value="${activeFormStatus}" id="activeFormStatus" type="hidden">
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<script id="radio_draggable_template" type="text/x-dot-template">

<div class="timu mt20" surveytype='1'>
<h3 class="edit"><button type="button" onclick="removeQuestion(this);" class="del_icon pull-right"></button>
<input type="text" placeholder="请输入问题" class="form-control" value="" check-type="required"  required-message='请输入问题!'></h3>
<div class="ti">
	<div class="radio-group pull-left pt10">
		<input type="radio" name="survey{{=it.data}}" id="{{=it.data}}_1" value="1">
		<label class="radio-2" for="{{=it.data}}_1"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="题目一" value="" check-type="required"  required-message='请输入题目内容!'>
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	<button type="button" onclick="addOptions(this,1);"class="btn_add_18 pull-left mt10 ml10"></button>
</div>
<div class="ti">
	<div class="radio-group pull-left pt10">
		<input type="radio" name="survey{{=it.data}}" id="{{=it.data}}_2" value="2">
		<label class="radio-2" for="{{=it.data}}_2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="题目二" check-type="required"  required-message='请输入题目内容!'>
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	<button type="button" onclick="addOptions(this,1);"class="btn_add_18 pull-left mt10 ml10"></button>
</div>
<div class="ti">
	<div class="radio-group pull-left pt10">
		<input type="radio" name="survey{{=it.data}}" id="{{=it.data}}_3" value="3">
		<label class="radio-2" for="{{=it.data}}_3"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="题目三" check-type="required"  required-message='请输入题目内容!'>
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	<button type="button" onclick="addOptions(this,1);"class="btn_add_18 pull-left mt10 ml10"></button>
</div>
</div>





</script>


<script id="checkbox_draggable_template" type="text/x-dot-template">

<div class="timu  mt20" surveytype='2'>
<h3 class="edit"><button type="button" onclick="removeQuestion(this);"  class="del_icon pull-right"></button><input type="text" placeholder="请输入问题" class="form-control" value="" check-type="required"  required-message='请输入问题!'></h3>
<div class="ti">
	<div class="checkbox-group pull-left pt10">
		<input type="checkbox" name="survey{{=it.data}}" id="{{=it.data}}_1" value="1">
		<label class="checkbox-2" for="{{=it.data}}_1"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="题目一" check-type="required"  required-message='请输入题目内容!'>
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	<button type="button" onclick="addOptions(this,2);"class="btn_add_18 pull-left mt10 ml10"></button>
</div>
<div class="ti">
	<div class="checkbox-group pull-left pt10">
		<input type="checkbox" name="survey{{=it.data}}" id="{{=it.data}}_2" value="2">
		<label class="checkbox-2" for="{{=it.data}}_2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="题目二" check-type="required"  required-message='请输入题目内容!'>
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	<button type="button" onclick="addOptions(this,2);"class="btn_add_18 pull-left mt10 ml10"></button>
</div>
<div class="ti">
	<div class="checkbox-group pull-left pt10">
		<input type="checkbox" name="survey{{=it.data}}" id="{{=it.data}}_3" value="3">
		<label class="checkbox-2" for="{{=it.data}}_3"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="题目三" check-type="required"  required-message='请输入题目内容!'>
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	<button type="button" onclick="addOptions(this,2);"class="btn_add_18 pull-left mt10 ml10"></button>
</div>
</div>


</script>


<script id="validation_draggable_template" type="text/x-dot-template">

<div class="timu  mt20" surveytype='3'>
<h3 class="edit"><button type="button" onclick="removeQuestion(this);" class="del_icon pull-right"></button><input type="text" placeholder="请输入问题" class="form-control" value="" check-type="required"  required-message='请输入问题!'></h3>
<div class="ti">
	<div class="radio-group pull-left pt10">
		<input type="radio" name="survey{{=it.data}}" id="{{=it.data}}_1" value="1">
		<label class="radio-2" for="{{=it.data}}_1"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		对
	</div>
	
</div>
<div class="ti">
	<div class="radio-group pull-left pt10">
		<input type="radio" name="survey{{=it.data}}" id="{{=it.data}}_2" value="2">
		<label class="radio-2" for="{{=it.data}}_2"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		错
	</div>
	
</div>

</div>

</script>


<script id="options_add_template" type="text/x-dot-template">
<div class="ti">
	<div class="{{=it.data.divClass}} pull-left pt10">
		<input type="{{=it.data.inputType}}" name="{{=it.data.name}}" id="{{=it.data.tiId}}" value="{{=it.data.values}}">
		<label class="{{=it.data.inputType}}-2" for="{{=it.data.tiId}}"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="" check-type="required"  required-message='请输入题目内容!'>
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>

	{{? it.data.inputType == 'checkbox'}}
		<button type="button" onclick="addOptions(this,2);"class="btn_add_18 pull-left mt10 ml10"></button>
	{{??}}
		<button type="button" onclick="addOptions(this,1);"class="btn_add_18 pull-left mt10 ml10"></button>
	{{?}}
</div>
</script>



	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_form'],function(group,obj){
			obj.draggableFun('radio_draggable',1);
			obj.draggableFun('checbox_draggable',2);
			obj.draggableFun('validation_draggable',3);
		});
	</script>
</body>
</html>