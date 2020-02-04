<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 问卷调查预览弹出 -->
<div class="modal fade" id="quizLearnModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header" style="background:#43b4c6">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">考试</h4>
			</div>
			<div class="modal-body hdn">
			    <div>考试时间：<span  id="finishTime"></span>分钟  <span  style="width:150px"></span>总分数：<span id="countScore"></span> <span  style="width:100px"></span>通过分数：<span id="passScore"></span></div> 
				<div id="quizDraggableContent" class="ti_left"
					style="min-height: 500px; min-width: 100%;"></div>
			</div>
			<div class="modal-footer">
				<div class="btn_box">
				<button type="button" class="btn btn_green btn_160_36" onclick="traineeSaveTest();" btn-type='true' data-dismiss="modal">保存</button>
				<button type="button" class="btn btn_green btn_160_36" onclick="traineeSubmitTest();" btn-type='true' data-dismiss="modal">提交</button>
				<button type="button" class="btn cancel btn_160_36" data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
      <input type="hidden" id="hiddenCourseId">
	</div>
</div>



<!-- 试卷模板 -->
<script id="quiz_learn_template_modal" type="text/x-dot-template">
	{{~ it.data:v:index}}
		{{? v.quiz_subject_type == 1}}	
			<div class="chouti mt20" qtype="1">
				<div class="edit_title" style="width:100%">
					<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
					<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
				</div>
				{{
					var items = v.item_options.split('#');
					var answs = v.option_answer.split('#');
				}}
				{{
					for(var jndex in items){
				}}
					{{
						for(var zndex in answs){
					}}
						
					{{? jndex == zndex}}
						<div class="ti">
							<div class="radio-group pull-left">
								<input type="radio"   name="quiz{{=v.id}}" id="{{=v.id}}_{{=jndex}}" value="{{=zndex}}"> 
								<label class="radio-2 " for="{{=v.id}}_{{=jndex}}"></label>
							</div>
							<div class="ti_input_w pull-left ml20 pt8">
								{{=items[jndex]}}
							</div>
						</div>
					{{?}}
					{{
						}
					}}
				{{
					}
				}}
				</div>
			{{?}}
			{{? v.quiz_subject_type == 2}}
				<div class="chouti mt20" qtype="2">
					<div class="edit_title"  style="width:100%">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>
					{{
						var items = v.item_options.split('#');
						var answs = v.option_answer.split('#');
					}}
					{{
						for(var jndex in items){
					}}

					{{
						for(var zndex in answs){
					
					}}
						
						{{? jndex == zndex}}
								<div class="ti">
									<div class="checkbox-group pull-left">
										<input type="checkbox"   name="quiz{{=v.id}}" id="{{=v.id}}_{{=jndex}}" value="{{=zndex}}"> 
										<label class="checkbox-2" for="{{=v.id}}_{{=jndex}}"></label>
									</div>
									<div class="ti_input_w pull-left ml20 pt8">
										{{=items[jndex]}}
									</div>
								</div>
							{{?}}

					{{
						}
					}}
					{{
						}
					}}


				</div>
											

			{{?}}


			{{? v.quiz_subject_type == 3}}
				
				<div class="chouti mt20" qtype="3">
					<div class="edit_title"  style="width:100%">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>
					{{
						var items = v.item_options.split('#');
						var answs = v.option_answer.split('#');
					}}
					{{
						for(var jndex in items){
					}}

					{{
						for(var zndex in answs){
					
					}}
						
						{{? jndex == zndex}}
								<div class="ti">
									<div class="radio-group pull-left">
										<input type="radio"  name="quiz{{=v.id}}" id="{{=v.id}}_{{=jndex}}" value="{{=zndex}}"> 
										<label class="radio-2" for="{{=v.id}}_{{=jndex}}"></label>
									</div>
									<div class="ti_input_w pull-left ml20 pt8">
										{{=items[jndex]}}
									</div>
								</div>
							{{?}}

					{{
						}
					}}
					{{
						}
					}}


				</div>
											

			{{?}}


			{{? v.quiz_subject_type == 4}}
				
				<div class="chouti mt20" qtype="4">
					<div class="edit_title"  style="width:100%">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>
					{{
						var items = v.item_options.split('#');
					}}
					{{
						for(var jndex in items){
					}}

						<div class="ti">
							<div class="pull-left pt8">
								<input type="text" class="form-control w50 text-center" id="{{=v.id}}_{{=jndex}}">
							</div>
							<div class="ti_input_w pull-left ml20 pt8">{{=items[jndex]}} </div>

						</div>
						

					{{
						}
					}}



				</div>
											

			{{?}}

			{{? v.quiz_subject_type == 5}}
				
				<div class="chouti mt20" qtype="5">
					<div class="edit_title"  style="width:100%">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>

					<div class="tuxing p20">
						<div class="l_pic" style="background:url({{=v.attach_url}}) center no-repeat;background-size: 100%  100%;">
						</div>
						<textarea name="" id="" cols="30" rows="10" class="pic_textarea ml20 pull-left" style="width: 632px; ">{{=v.item_options}}</textarea>
						<div class="clearfix"></div>
					</div>

				</div>
											

			{{?}}

			{{? v.quiz_subject_type == 6}}
				
				<div class="chouti mt20" qtype="6">
					<div class="edit_title" style="width:100%">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>

					<div class="jianda mt10">
						<textarea name="" id="">{{=isNullFormat(v.item_options)}}</textarea>
					</div>

				</div>
											

			{{?}}


			{{? v.quiz_subject_type == 7}}
				
				<div class="chouti mt20" qtype="7">
					<div class="edit_title" style="width:100%">
						<span class="pull-left _quiz-order">{{=v.quiz_subject_order + 1}}</span>
						<div style="background: #cdd5d9; border-radius: 0 2px 0 0;" class="pl10">{{=v.quiz_subject_name}}</div>
					</div>

					<div class="jianda mt10">
						<textarea name="" id="">{{=v.item_options}}</textarea>
					</div>

				</div>
											

			{{?}}
		{{~}}
</script>






<script type="text/javascript">
	require([ 'modules/trainee/trainee_learn_dialogue' ], function() {

	});
</script>