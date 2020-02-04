<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--单选题  -->
<script id="radio_template" type="text/x-dot-template">
	<div class="chouti mt20" qtype="1">
	<div class="edit_title">
		<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
		<span class="pull-left _quiz-order">{{=it.data.order}}</span>
		<input type="text" class="form-control" value="题目一单选题" check-type="required" required-message="请输入题目名称！" placeholder="请输入题目名称" aria-describedby="basic-addon1">
	</div>
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_1" value="1">
			<label class="radio-2" for="{{=it.data.uuid}}_1"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目一" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,1);"></button>
	</div>
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_2" value="2">
			<label class="radio-2" for="{{=it.data.uuid}}_2"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目二" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,1);"></button>
	</div>
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_3" value="3">
			<label class="radio-2" for="{{=it.data.uuid}}_3"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目三" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,1);"></button>
	</div>
	<div class="ti">
		<div class="radio-group pull-left">
			<input type="radio" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_4" value="4">
			<label class="radio-2" for="{{=it.data.uuid}}_4"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目四" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,1);"></button>
	</div>
</div>
</script>

<!--多选题  -->
<script id="checkbox_template" type="text/x-dot-template">
<div class="chouti mt20" qtype="2">
	<div class="edit_title">
		<button type="button" onclick="removeQuestion(this);"  class="del26 pull-right"></button>
		<span class="pull-left _quiz-order">{{=it.data.order}}</span>
		<input type="text" class="form-control" value="题目二多选题" check-type="required" required-message="请输入题目名称！" placeholder="请输入题目名称" aria-describedby="basic-addon1">
	</div>
	<div class="ti">
		<div class="checkbox-group pull-left">
			<input type="checkbox" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_1" value="1">
			<label class="checkbox-2" for="{{=it.data.uuid}}_1"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目一" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,2);"></button>
	</div>
	<div class="ti">
		<div class="checkbox-group pull-left">
			<input type="checkbox" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_2" value="2">
			<label class="checkbox-2" for="{{=it.data.uuid}}_2"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目二" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,2);"></button>
	</div>
	<div class="ti">
		<div class="checkbox-group pull-left">
			<input type="checkbox" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_3" value="3">
			<label class="checkbox-2" for="{{=it.data.uuid}}_3"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目三" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,2);"></button>
	</div>
	<div class="ti">
		<div class="checkbox-group pull-left">
			<input type="checkbox" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_4" value="4">
			<label class="checkbox-2" for="{{=it.data.uuid}}_4"></label>
		</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control" value="题目四" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
			<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,2);"></button>
	</div>
</div>
</script>
	
<!--判断题  -->				
<script id="alternative_template" type="text/x-dot-template">								
	<div class="chouti mt20" qtype="3">
		<div class="edit_title">
			<button type="button" onclick="removeQuestion(this);"  class="del26 pull-right"></button>
			<span class="pull-left _quiz-order">{{=it.data.order}}</span>
			<input type="text" class="form-control" value="题目三判断题" check-type="required" required-message="请输入题目名称！" placeholder="请输入题目名称" aria-describedby="basic-addon1">
		</div>
		<div class="ti">
			<div class="radio-group pull-left">
				<input type="radio" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_1" value="1">
				<label class="radio-2" for="{{=it.data.uuid}}_1"></label>
			</div>
			<div class="ti_input_w pull-left ml20 mt10">
				<label>对</label>
				<input type="hidden" class="form-control" value="对" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
			</div>
		</div>
		<div class="ti">
			<div class="radio-group pull-left">
				<input type="radio" name="quiz{{=it.data.uuid}}" id="{{=it.data.uuid}}_2" value="2">
				<label class="radio-2" for="{{=it.data.uuid}}_2"></label>
			</div>
			<div class="ti_input_w pull-left ml20 mt10">
				<label>错</label>
				<input type="hidden" class="form-control" value="错" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
			</div>
		</div>
	</div>			
</script>		

<!-- 排序题 -->		
<script id="order_template" type="text/x-dot-template">
<div class="chouti mt20" qtype="4">
	<div class="edit_title">
		<button type="button" onclick="removeQuestion(this);"  class="del26 pull-right"></button>
		<span class="pull-left _quiz-order">{{=it.data.order}}</span>
		<input type="text" class="form-control" value="排序题" check-type="required" required-message="请输入题目名称！" placeholder="请输入题目名称" aria-describedby="basic-addon1">
	</div>
	<div class="ti">
		<div class="pull-left pt8 _order-no">1</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control h36 br2" value="题目一" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this,1);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,3);"></button>
	</div>
	<div class="ti">
		<div class="pull-left pt8 _order-no">2</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control h36 br2" value="题目二" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this,1);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,3);"></button>
	</div>
	<div class="ti">
		<div class="pull-left pt8 _order-no">3</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control h36 br2" value="题目三" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this,1);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,3);"></button>
	</div>
	<div class="ti">
		<div class="pull-left pt8 _order-no">4</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control h36 br2" value="题目四" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this,1);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,3);"></button>
	</div>
</div>
</script>					
		
<!-- 图形题 -->
<script id="picture_template" type="text/x-dot-template">
<div class="chouti mt20" qtype="5">
	<div class="edit_title">
		<button type="button" onclick="removeQuestion(this);"  class="del26 pull-right"></button>
		<span class="pull-left _quiz-order">{{=it.data.order}}</span>
		<input type="text" class="form-control" value="图形题" check-type="required" required-message="请输入题目名称！" placeholder="请输入题目名称" aria-describedby="basic-addon1">
	</div>
	<div class="tuxing p20">
		<div class="l_pic" id="quiz_option_{{=it.data.uuid}}">
			<input type="file" name="uploadFile" class="btn up_color"  id="uploadFile_{{=it.data.uuid}}" onchange="quizAttachUpload(this,'11','{{=it.data.uuid}}')" style="width:20px;height:34px;cursor:pointer;">
			<input type="hidden" name="filePath" id="uploadFilePath_{{=it.data.uuid}}">				
		</div>
		<textarea name="" id="" cols="30" rows="10" class="pic_textarea ml20 pull-left form-control" style="width:650px;"></textarea>
		<div class="clearfix"></div>
	</div>
</div>
</script>			
			
<!-- 简答题 -->						
<script id="short_answer_template" type="text/x-dot-template">
<div class="chouti mt20" qtype="6">
	<div class="edit_title">
		<button type="button" onclick="removeQuestion(this);"  class="del26 pull-right"></button>
		<span class="pull-left _quiz-order">{{=it.data.order}}</span>
		<input type="text" class="form-control" value="简答题" check-type="required" required-message="请输入题目名称！" placeholder="请输入题目名称" aria-describedby="basic-addon1">
	</div>
	<div class="jianda mt20">
		<textarea name="" id=""></textarea>
	</div>
</div>
</script>									

<!-- 填空题 -->
<script id="space_template" type="text/x-dot-template">
<div class="chouti mt20" qtype="7">
	<div class="edit_title">
		<button type="button" onclick="removeQuestion(this);"  class="del26 pull-right"></button>
		<span class="pull-left _quiz-order">{{=it.data.order}}</span>
		<input type="text" class="form-control" value="填空题" check-type="required" required-message="请输入题目名称！" placeholder="请输入题目名称" aria-describedby="basic-addon1">
	</div>
	<div class="tiankong mt20">
		<textarea name="" id="" ></textarea>
	</div>
</div>
</script>	

<script id="options_add_text_template" type="text/x-dot-template">
<div class="ti">
		<div class="pull-left pt8 _order-no">{{=it.data}}</div>
		<div class="ti_input_w pull-left ml20">
			<input type="text" class="form-control h36 br2" value="题目" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="deleteOptions(this,1);"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="addOptions(this,3);"></button>
	
	</div>
</script>	

<script id="options_add_template" type="text/x-dot-template">
<div class="ti">
	<div class="{{=it.data.divClass}} pull-left">
		<input type="{{=it.data.inputType}}" name="{{=it.data.name}}" id="{{=it.data.tiId}}" value="{{=it.data.values}}">
		<label class="{{=it.data.inputType}}-2" for="{{=it.data.tiId}}"></label>
	</div>
	<div class="ti_input_w pull-left ml20">
		<input type="text" class="form-control" value="题目" check-type="required" required-message="请输入题目内容！" placeholder="请输入题目内容" aria-describedby="basic-addon1">
	</div>
	<button type="button" onclick="deleteOptions(this);" class="btn_del_18 pull-left mt10 ml10"></button>
	{{? it.data.inputType == 'checkbox'}}
		<button type="button" onclick="addOptions(this,2);"class="btn_add_18 pull-left mt10 ml10"></button>
	{{??}}
		<button type="button" onclick="addOptions(this,1);"class="btn_add_18 pull-left mt10 ml10"></button>
	{{?}}
</div>
</script>
								
