<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script id="course_classroom_survey_data_template" type="text/x-dot-template">
	<div class="video_con" style="width:100%;">
		<div id="draggableContent" class="ti_left mb20" style="height: 100%; width: 100%;overflow-y: auto;">
			  {{~it:v:index}}

				{{? v.survey_subject_type == 1}}
				<div class="chouti mt20" id="{{=v.subject_id}}" stype="1">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=index+1}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">{{=v.survey_subject}}</div>
					</div>
					{{var item_options = v.item_options.split('#');}}
					{{~item_options:j:jndex}}
						<div class="ti">
							<div class="radio-group pull-left">
								<input type="radio" name="survey_{{=v.subject_id}}" id="{{=v.subject_id}}_{{=jndex+1}}" value="">
								<label class="radio-2" for="{{=v.subject_id}}_{{=jndex+1}}"></label>
							</div>
							<div class="ti_input_w pull-left ml20">
								{{=j}}
							</div>
						</div>	
					{{~}}
				</div>
				{{?}}
				{{? v.survey_subject_type == 2}}
				<div class="chouti mt20" id="{{=v.subject_id}}" stype="2">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=index+1}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">{{=v.survey_subject}}</div>
					</div>
					{{var item_options = v.item_options.split('#');}}
					{{~item_options:j:jndex}}
								<div class="ti">
									<div class="checkbox-group pull-left">
										<input type="checkbox" name="survey_{{=v.subject_id}}" id="{{=v.subject_id}}_{{=jndex+1}}" value="{{=jndex+1}}">
										<label class="checkbox-2" for="{{=v.subject_id}}_{{=jndex+1}}"></label>
									</div>
									<div class="ti_input_w pull-left ml20">
										{{=j}}
									</div>
								</div>
					{{~}}
				</div>
				{{?}}	
				{{? v.survey_subject_type == 3}}
				<div class="chouti mt20" id="{{=v.subject_id}}" stype="3">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=index+1}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">{{=v.survey_subject}}</div>
					</div>
					{{var item_options = v.item_options.split('#');}}
					{{~item_options:j:jndex}}
								<div class="ti">
									<div class="radio-group pull-left">
										<input type="radio" name="survey_{{=v.subject_id}}" id="{{=v.subject_id}}_{{=jndex+1}}" value="{{=jndex+1}}">
										<label class="radio-2" for="{{=v.subject_id}}_{{=jndex+1}}"></label>
									</div>
									<div class="ti_input_w pull-left ml20">
										{{=j}}
									</div>
								</div>
					{{~}}
				</div>
				{{?}}
				{{? v.survey_subject_type == 4}}
				<div class="chouti mt20" id="{{=v.subject_id}}" stype="4">
					<div class="edit_title">
						<span class="pull-left _quiz-order">{{=index+1}}</span>
						<div class="pl10" style="background: #cdd5d9; border-radius: 0 2px 0 0;">{{=v.survey_subject}}</div>
					</div>
					{{var item_options = randomFun(items.item_options);}}
					{{~item_options:j:jndex}}
							<div class="ti">
								<div class="pull-left pt8">
									<input type="text" class="form-control w50 text-center" id="{{=v.subject_id}}_{{=jndex+1}}">
								</div>
								<div class="ti_input_w pull-left ml20 pt8">{{=j}}</div>
							</div>
					{{~}}
				</div>
				{{?}}		
			{{~}}
			<div class="mt20"></div>
		</div>
 </div>
</script>