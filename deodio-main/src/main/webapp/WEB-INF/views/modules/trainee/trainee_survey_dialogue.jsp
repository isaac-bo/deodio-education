<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 问卷调查预览弹出 -->
<div class="modal fade" id="surveyLearnModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:85%;">
		<div class="modal-content" >
			<div class="modal-header" style="background:#43b4c6">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" >调查问卷</h4>
			</div>
			<div class="modal-body hdn">
				<div id="draggableContent" class="ti_left" 
					style="min-height: 980px; min-width: 1200px;">
				</div>
			</div>		
			<div class="modal-footer">
				<div class="btn_box">
				    <button type="button" class="btn btn_green btn_160_36" onclick="traineeSaveServey();" btn-type='true' data-dismiss="modal">保存</button>
					<button type="button" class="btn btn_green btn_160_36" onclick="traineeSubmitServey();" btn-type='true' data-dismiss="modal">提交</button>
					<button type="button" class="btn cancel btn_160_36" data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
      <input type="hidden" id="hiddenCourseId">
	</div>
</div>



<!-- 问卷调查预览模板 -->
<script id="survey_learn_template_modal"  type="text/x-dot-template">
	{{~it.data:v:index}}
		<input id="quiz_subject_type" type="hidden" value="{{=v.survey_subject_type}}">
        <div id="course_quiz_original_{{=v.id}}" class="box1 mt10 pt10 pb10 pl10 pr10  ui-draggable ui-draggable-handle">
        	{{? v.survey_subject_type == 1}}
				<div class="chouti" qtype="1"> 
					<div class="edit_title" style="width:100%">
						<span class="pull-left _subject-order">{{=(index+1)}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">{{=isNullFormat(v.survey_subject)}}</div>
					</div>
					{{var options = v.item_options.split("#");}}			
			 		{{ for (var key in options) { }} 
						<div class="ti">
							<div class="radio-group pull-left">
								<input type="radio"  value="" id="{{=v.subject_id}}_{{=key}}"  name="quiz{{=v.subject_id}}">
								<label for="{{=v.subject_id}}_{{=key}}" class="radio-2 "></label>
							</div>
							<div class="ti_input_w pull-left ml20 pt8">
								{{=options[key]}}
							</div>
						</div>
					{{ } }}
				</div>

			{{?? v.survey_subject_type == 2}}
				<div class="chouti mt20" qtype="2">
					<div class="edit_title"  style="width:100%">
						<span class="pull-left _subject-order">{{=(index+1)}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">{{=isNullFormat(v.survey_subject)}}</div>
					</div>
					{{var options = v.item_options.split("#");}}			
			 		{{ for (var key in options) { }} 
						<div class="ti">
							<div class="checkbox-group pull-left">
								<input type="checkbox"  name="quiz{{=v.subject_id}}" id="{{=v.subject_id}}_{{=key}}">
								<label class="checkbox-2" for="{{=v.subject_id}}_{{=key}}"></label>
							</div>
							<div class="ti_input_w pull-left ml20 pt8">
								{{=options[key]}}
							</div>
						</div>
					{{ } }}
					<c:forTokens items="${items.item_options}" delims="#" var="options">
						<div class="ti">
							<div class="checkbox-group pull-left">
								<input type="checkbox"  name="quiz{{=v.subject_id}}" id="three2">
								<label class="checkbox-2" for="three2"></label>
							</div>
							<div class="ti_input_w pull-left ml20 pt8">${options}</div>
						</div>
					</c:forTokens>
				</div>

			{{?? v.survey_subject_type == 3}}
				<div class="chouti mt20" qtype="3">
					<div class="edit_title"  style="width:100%">
						<span class="pull-left _subject-order">{{=(index+1)}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">
							{{=isNullFormat(v.survey_subject)}}
						</div>
					</div>
					<div class="ti">
						<div class="radio-group pull-left">
						<input type="radio"  name="quiz{{=v.subject_id}}" id="{{=v.subject_id}}_0" value=""> 
						<label class="radio-2" for="{{=v.subject_id}}_0"></label>
						</div>
						<div class="ti_input_w pull-left ml20 mt10">对</div>
					</div>
					<div class="ti">
						<div class="radio-group pull-left">
							<input type="radio" name="quiz{{=v.subject_id}}" id="{{=v.subject_id}}_1" value=""> 
							<label class="radio-2" for="{{=v.subject_id}}_1"></label>
						</div>
						<div class="ti_input_w pull-left ml20 mt10">错</div>
					</div>
				</div>

			{{?? v.survey_subject_type == 6}}
				<div class="chouti mt20" qtype="6">
					<div class="edit_title" style="width:100%">
						<span class="pull-left _subject-order">{{=(index+1)}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">{{=v.survey_subject}}</div>
					</div>
					<div class="jianda" style="margin-top:20px;">
						<textarea name="" id=""></textarea>
					</div>
				</div>
			{{?}}      
      </div>
	{{~}}
</script>






<script type="text/javascript">
	require(['modules/trainee/trainee_survey_dialogue'],function(){
			
	});
</script>