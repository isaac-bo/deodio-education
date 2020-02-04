<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<script id="subject_radio_template" type="text/x-dot-template">
{{~it.data:v:index}}
<div class="chouti mt20 _subjct1" id="{{=v.id}}" subtype="1">
		<div class="edit_title">
			<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
			<span class="pull-left _subject-order">1</span>
			<div style="background:#cdd5d9;border-radius:0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
		</div>
	{{var options = v.item_options.split("#");}}
	{{var answers = v.option_answer.split("#");}}
	 {{ for (var key in options) { }} 
		<div class="ti">
			<div class="radio-group pull-left">
				<input type="radio" {{?answers[key]=='1'}}checked="checked"{{?}}  value="{{=arrayOutIndex(v.option_answer_id,'#',key)}}" id="man"  name="quiz{{=v.id}}">
				<label for="man" class="radio-2 {{?answers[key]=='1'}}checked{{?}}"></label>
			</div>
				<div class="ti_input_w pull-left ml20 pt8">
					{{=options[key]}}
				</div>
	
		</div>
	 {{ } }}
	</div>
{{~}}	
</script>


<script id="subject_checkbox_template" type="text/x-dot-template">
{{~it.data:v:index}}
<div class="chouti mt20 _subjct2"  id="{{=v.id}}" subtype="2">
		<div class="edit_title">
			<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
			<span class="pull-left _subject-order">1</span>
			<div style="background:#cdd5d9;border-radius:0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
		</div>
	{{var options = v.item_options.split("#");}}
	{{var answers = v.option_answer.split("#");}}
	 {{ for (var key in options) { }} 
		<div class="ti">
		<div class="checkbox-group pull-left">
			<input type="checkbox"  {{?answers[key]=='1'}}checked="checked"{{?}} value="{{=arrayOutIndex(v.option_answer_id,'#',key)}}"  id="three2" name="quiz{{=v.id}}">
			<label for="three2" class="checkbox-2 {{?answers[key]=='1'}}checked{{?}}"></label>
		</div>
				<div class="ti_input_w pull-left ml20 pt8">
					{{=options[key]}}
				</div>
	
		</div>
	 {{ } }}
	</div>
{{~}}	
</script>



<script id="subject_alternative_template" type="text/x-dot-template">
{{~it.data:v:index}}
<div class="chouti mt20 _subjct3" id="{{=v.id}}" subtype="3">
		<div class="edit_title">
			<button type="button" onclick="removeQuestion(this);" class="del26 pull-right"></button>
			<span class="pull-left _subject-order">1</span>
			<div style="background:#cdd5d9;border-radius:0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
		</div>
{{var answers = v.option_answer.split("#");}}
	<div class="ti">
			<div class="radio-group pull-left">
				<input type="radio" {{?answers[0]=='1'}}checked="checked"{{?}} name="quiz{{=v.id}}" id="_1" value="{{=arrayOutIndex(v.option_answer_id,'#',0)}}">
				<label class="radio-2 {{?answers[0]=='1'}}checked{{?}}" for="_1"></label>
			</div>
			<div class="ti_input_w pull-left ml20 mt10">
				对
			</div>
		</div>
		<div class="ti">
			<div class="radio-group pull-left">
				<input type="radio" {{?answers[1]=='1'}}checked="checked"{{?}} name="quiz{{=v.id}}" id="{{=it.data.uuid}}_2" value="{{=arrayOutIndex(v.option_answer_id,'#',1)}}">
				<label class="radio-2 {{?answers[1]=='1'}}checked{{?}}" for="_2"></label>
			</div>
			<div class="ti_input_w pull-left ml20 mt10">
				错
			</div>
		</div>

	</div>
{{~}}	
</script>



<script id="subject_order_template" type="text/x-dot-template">
{{~it.data:v:index}}
<div class="chouti mt20 _subjct4"  id="{{=v.id}}" subtype="4">
	<div class="edit_title">
		<button type="button" onclick="removeQuestion(this);"  class="del26 pull-right"></button>
			<span class="pull-left _subject-order">1</span>
			<div style="background:#cdd5d9;border-radius:0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
	</div>

{{var options = v.item_options.split("#");}}
{{ for (var i=0;i<options.length;i++) { }} 
	<div class="ti">
		<div class="pull-left pt8 _order-no">{{=i+1}}</div>
		<div class="ti_input_w pull-left ml20 pt8">
					{{=options[i]}}
		</div>
	</div>
	
{{ } }}

</div>
{{~}}	
</script>


<script id="subject_picture_template" type="text/x-dot-template">
{{~it.data:v:index}}
<div class="chouti mt20 _subjct5"  id="{{=v.id}}" subtype="5">
    <div class="edit_title">
	<button class="del26 pull-right" type="button" onclick="removeQuestion(this);"></button>
	<span class="pull-left _subject-order">1</span>
	<div style="background:#cdd5d9;border-radius:0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
	</div>
	<div class="tuxing p20">
		<div class="l_pic">
			
		</div>
		<textarea class="pic_textarea ml20 pull-left form-control" style="width:650px;" rows="10" cols="30" id="" name="">{{=v.item_options}}</textarea>
		<div class="clearfix"></div>
	</div>
</div>
{{~}}	
</script>

<script id="subject_short_template" type="text/x-dot-template">
{{~it.data:v:index}}
<div class="chouti mt20 _subjct6"  id="{{=v.id}}" subtype="6">
	<div class="edit_title">
		<button class="del26 pull-right" type="button" onclick="removeQuestion(this);"></button>
			<span class="pull-left _subject-order">1</span>
        <div style="background:#cdd5d9;border-radius:0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
		</div>
		<div class="jianda">
			<textarea id="" name="">{{=v.item_options}}</textarea>
		</div>
	</div>
{{~}}	
</script>

<script id="subject_space_template" type="text/x-dot-template">
{{~it.data:v:index}}
<div class="chouti mt20 _subjct7"  id="{{=v.id}}" subtype="7">
									<div class="edit_title">
										<button class="del26 pull-right" type="button" onclick="removeQuestion(this);"></button>
											<span class="pull-left _subject-order">1</span>
			<div style="background:#cdd5d9;border-radius:0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
									</div>
									<div class="jianda">
										<textarea id="" name="">{{=v.item_options}}</textarea>
									</div>
								</div>
{{~}}	
</script>
