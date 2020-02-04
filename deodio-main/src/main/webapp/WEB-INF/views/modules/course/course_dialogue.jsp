<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 新建题库弹出 -->
<div class="modal fade" id="quizModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id = "quizModalContent">
		</div>
	</div>
</div>


<!-- 新建问卷调查弹出 -->
<div class="modal fade" id="surveyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id = "surveyModalContent">
		</div>
	</div>
</div>

<!-- 上传相关附件 -->
<div class="modal fade" id="materialModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:674px;">
		<div class="modal-content" id = "materialModalContent">
		</div>
	</div>
</div>

<!-- 新建作业弹出 -->
<div class="modal fade" id="homeworkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id = "homeworkModalContent" data-callback="checkName()" call-message="当前作业已被注册">
		</div>
	</div>
</div>


<!-- 新建消息弹出 -->
<div class="modal fade" id="noticeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:750px;">
		<div class="modal-content" id="noticeModalContent" data-callback="checkName()" call-message="当前通知已被注册">
			
		</div>
	</div>
</div>


<!-- 新建关联课程弹出 -->
<div class="modal fade" id="relatedCourseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id="relatedCourseModalContent">
		</div>
	</div>
</div>


<!-- 上传附件弹出 -->
<div class="modal fade" id="uploadAttachmentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id="uploadAttachmentModalContent">
		</div>
	</div>
</div>

<!-- 新建关联课程弹出 -->
<div class="modal fade" id="coursePackageItemsElementsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id="coursePackageItemsElementsModalContent">
		</div>
		<input type="hidden" name="itemId" id="itemId" >
	</div>
</div>

<!-- 小组课程弹出 -->
<div class="modal fade" id="groupCourseToSelectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id="groupCourseToSelectModalContent">
		</div>
		<input type="hidden" name="itemId" id="itemId" >
	</div>
</div>
<!-- 小组课程包弹出 -->
<div class="modal fade" id="groupCoursePackageToSelectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id="groupCoursePackageToSelectModalContent">
		</div>
		<input type="hidden" name="itemId" id="itemId" >
	</div>
</div>
<!-- 课程题库列表模板 -->
<script id="course_tab_left_quiz_template_modal"  type="text/x-dot-template">
	{{~it.data:v:index}}
      <div id="course_quiz_original_{{=v.id}}" class="box1 mt10 pt10 pb10 pl10 pr10  ui-draggable ui-draggable-handle">
        	{{? isNullFormat(v.attachUrl) == ''}}
			<img src="${ctx}/resources/img/course/up_pic1.png" alt="">
			{{??}}
			<img src="${imgStatic}/{{=isNullFormat(v.attachUrl)}}{{=isNullFormat(v.generateName)}}" alt="">
			{{?}}
        <div class="box_r">
			<div style="color:#f97e37;">{{=isNullFormat(v.quizName)}}</div>
            <div class="f12">问题总数：<span name="quizNum">{{=isNullFormat(v.quizNum)}}</span></div>
            <div class="f12">问题总分：<span class="c888" name="score">{{=isNullFormat(v.score)}}</span></div>

			<input class="list_quiz" type="hidden" name="quizId" value="{{=isNullFormat(v.id)}}">
			<input class="list_quiz" type="hidden" name="quizName" value="{{=isNullFormat(v.quizName)}}">
			<input class="list_quiz" type="hidden" id="value_quizAlias_{{=v.id}}" name="quizAlias" value="{{=isNullFormat(v.quizAlias)}}">
			<input class="list_quiz" type="hidden" id="value_quizPassScore_{{=v.id}}" name="quizPassScore" value="{{=isNullFormat(v.passScore)}}">
			<input class="list_quiz" type="hidden" id="value_quizFinishTime_{{=v.id}}" name="quizFinishTime" value="{{=isNullFormat(v.finishTime)}}">
			<input class="list_quiz" type="hidden" id="value_quizMaxTimes_{{=v.id}}" name="quizMaxTimes" value="{{=isNullFormat(v.quizMaxTimes)}}">
			<input class="list_quiz" type="hidden" id="value_quizFinallyResultType_{{=v.id}}" name="quizFinallyResultType" value="{{=isNullFormat(v.finallyQuizResultType)}}">
			<input class="list_quiz" type="hidden" id="value_quizPublishResultType_{{=v.id}}" name="quizPublishResultType" value="{{=isNullFormat(v.publishResultType)}}">
			<input class="list_quiz" type="hidden" id="value_quizSafe_{{=v.id}}" name="quizSafe" value="{{=isNullFormat(v.quizSafe)}}">
        </div>
      </div>
	{{~}}
</script>


<script id="course_select_quiz_template_modal" type="text/x-dot-temlate">
{{~it.data:v:index}}
	<div id="course_quiz_active_{{=v.quiz_id}}" class="box2 pull-left">
		{{? isNullFormat(v.attach_url) == ''}}
		<img src="${ctx}/resources/img/course/up_pic1.png" alt="">
		{{??}}
		<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="">
		{{?}}
	<div class="box_r">
		<div style="color:#f97e37;">{{=isNullFormat(v.quiz_name)}}</div>
		<div class="f12">问题总数：<span name="quizNum">{{=isNullFormat(v.quiz_num)}}</span></div>
		<div class="f12">问题总分：<span class="c888" name="score">{{=isNullFormat(v.score)}}</span></div>

		<input class="list_quiz" type="hidden"  name="quizId" value="{{=isNullFormat(v.quiz_id)}}">
		<input class="list_quiz" type="hidden" name="quizName" value="{{=isNullFormat(v.quiz_name)}}">
		<input class="list_quiz" type="hidden" id="value_quizAlias_{{=v.quiz_id}}" name="quizAlias" value="{{=isNullFormat(v.quiz_alias)}}">
		<input class="list_quiz" type="hidden" id="value_quizPassScore_{{=v.quiz_id}}" name="quizPassScore" value="{{=isNullFormat(v.quiz_pass_score)}}">
		<input class="list_quiz" type="hidden" id="value_quizFinishTime_{{=v.quiz_id}}" name="quizFinishTime" value="{{=isNullFormat(v.quiz_finish_time)}}">
		<input class="list_quiz" type="hidden" id="value_quizMaxTimes_{{=v.quiz_id}}" name="quizMaxTimes" value="{{=isNullFormat(v.quiz_max_times)}}">
		<input class="list_quiz" type="hidden" id="value_quizFinallyResultType_{{=v.quiz_id}}" name="quizFinallyResultType" value="{{=isNullFormat(v.quiz_finally_result_type)}}">
		<input class="list_quiz" type="hidden" id="value_quizPublishResultType_{{=v.quiz_id}}" name="quizPublishResultType" value="{{=isNullFormat(v.quiz_publish_result_type)}}">
		<input class="list_quiz" type="hidden" id="value_quizSafe_{{=v.quiz_id}}" name="quizSafe" value="{{=isNullFormat(v.quiz_safe)}}">
		<input type="hidden" name="id" value="{{=isNullFormat(v.id)}}">
	</div>
	<div style="float: right;margin-top: -60px;cursor:pointer;position: relative;">
		<span class="icon-cog" onclick="javascript:displayQuizSetting('{{=isNullFormat(v.quiz_id)}}')"></span>
        <a class="icon-minus" style="color:white;margin-left:10px;"  onclick="javascript:remove('{{=isNullFormat(v.quiz_id)}}','quiz')"></a>
	</div>
</div>
{{~}}
</script>

<!-- 课程调查问卷列表模板 -->
<script id="course_tab_left_survey_template_modal"  type="text/x-dot-template">
	{{~it.data:v:index}}
      <div id="course_survey_original_{{=v.id}}" class="box1 mt10 pt10 pb10 pl10 pr10" ui-draggable ui-draggable-handle">
		{{? isNullFormat(v.attach_url) == ''}}
		<img src="${ctx}/resources/img/course/up_pic1.png" alt="">
		{{??}}
		<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="">
		{{?}}
        <div class="box_r">

        	<div style="color:#f97e37;">{{=isNullFormat(v.survey_name)}}</div>
            <div>问题数：<span name="surveynum">{{=isNullFormat(v.count_issue)}}</span></div>

			<input class="list_survey" type="hidden" name="surveyId" value="{{=isNullFormat(v.id)}}">
			<input class="list_survey" type="hidden" name="surveyName" value="{{=isNullFormat(v.survey_name)}}">
			<input class="list_survey" type="hidden" id="value_surveyDesc_{{=v.id}}" name="surveyDesc" value="{{=isNullFormat(v.survey_desc)}}">
			<input class="list_survey" type="hidden" id="value_surveyStartTime_{{=v.id}}" name="surveyStartTime" value="{{=dateFormat(v.start_time)}}">
			<input class="list_survey" type="hidden" id="value_surveyEndTime_{{=v.id}}"name="surveyEndTime" value="{{=dateFormat(v.end_time)}}">
			<input class="list_survey" type="hidden" id="value_surveyModel_{{=v.id}}" name="surveyModel" value="{{=isNullFormat(v.survey_model)}}">
        </div>
      </div>
	{{~}}
</script>


<!-- 课程调查问卷列表模板 -->
<script id="table_select_survey_template_modal"  type="text/x-dot-template">
	{{~it.data:v:index}}
      <div id="course_survey_active_{{=v.survey_id}}" class="box2 pull-left">
     		{{? isNullFormat(v.attach_url) == ''}}
		<img src="${ctx}/resources/img/course/up_pic1.png" alt="">
		{{??}}
		<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="">
		{{?}}
        <div class="box_r">

        	<div style="color:#f97e37;">{{=isNullFormat(v.survey_name)}}</div>
            <div>问题数：<span name="surveynum">{{=isNullFormat(v.survey_num)}}</span></div>

			<input type="hidden"  class="list_survey" name="surveyId" value="{{=isNullFormat(v.survey_id)}}">
			<input type="hidden" class="list_survey" name="surveyName" value="{{=isNullFormat(v.survey_name)}}">
			<input type="hidden" class="list_survey" id="value_surveyDesc_{{=v.survey_id}}" name="surveyDesc" value="{{=isNullFormat(v.survey_desc)}}">
			<input type="hidden" class="list_survey" id="value_surveyStartTime_{{=v.survey_id}}" name="surveyStartTime" value="{{=dateFormat(v.survey_start_time)}}">
			<input type="hidden" class="list_survey" id="value_surveyEndTime_{{=v.survey_id}}" name="surveyEndTime" value="{{=dateFormat(v.survey_end_time)}}">
			<input type="hidden" class="list_survey" id="value_surveyModel_{{=v.survey_id}}" name="surveyModel" value="{{=isNullFormat(v.survey_model)}}">
			<input type="hidden" name="id" value="{{=isNullFormat(v.id)}}">
        </div>
		<div style="float: right;margin-top: -45px;cursor:pointer;position: relative;">
			<span class="icon-cog" onclick="javascript:displaySurveySetting('{{=isNullFormat(v.survey_id)}}')"></span>
            <a class="icon-minus" style="color:white;margin-left:10px;"  onclick="javascript:remove('{{=isNullFormat(v.survey_id)}}','survey')"></a>
		</div>
      </div>
	{{~}}

</script>




<!-- 课程调查问卷列表模板 -->
<script id="course_tab_left_course_template_modal"  type="text/x-dot-template">
	{{~it.data:v:index}}
	  <div id="course_related_original_{{=v.id}}" class="box1 mt10 pt10 pb10 pl10 pr10" ui-draggable ui-draggable-handle">
        <img src="${ctx}/resources/img/course/up_pic1.png" alt="" >
        <div class="box_r">
        	<div style="color:#f97e37;">{{=subString(isNullFormat(v.course_name),10)}}</div>
            <div class="f12">类型：<span>{{? v.course_type == 1}} 线上 {{?? v.course_type == 2}} 线下 {{??}} 直播 {{?}}</span></div>
            <div class="f12">创建：<span class="c888">{{=isNullFormat(v.nick_name)}}</span></div>
			
			<input type="hidden" name="courseId" value="{{=isNullFormat(v.id)}}">
			<input type="hidden" name="courseName" value="{{=isNullFormat(v.course_name)}}">
			<input type="hidden" name="courseDesc" value="{{=isNullFormat(v.course_description)}}">
			<input type="hidden" name="courseType" value="{{=isNullFormat(v.course_type)}}">
			<input type="hidden" name="createName" value="{{=isNullFormat(v.nick_name)}}">
			<input type="hidden" name="courseStartTime" value="{{=dateFormat(v.course_start_time)}}">
			<input type="hidden" name="courseEndTime" value="{{=dateFormat(v.course_end_time)}}">
			<input type="hidden" name="createTime" value="{{=dateFormat(v.create_time)}}">
			
        </div>
      </div>
	{{~}}
</script>

<script id="course_select_related_template_modal"  type="text/x-dot-template">
{{~it.data:v:index}}
<div id="course_required_active_{{=v.related_id}}" class="box2 pull-left " type="{{=v.course_related_type}}" style="margin-left: 5px; margin-right: 5px;">
	<img class="mCS_img_loaded" src="/deodio-main/resources/img/course/up_pic1.png" alt="">
	<div class="box_r">
		<div style="color:#f97e37;">{{=isNullFormat(v.course_name)}}</div>
		<div class="f12">类型：<span>{{? v.course_type == 1}} 线上 {{?? v.course_type == 2}} 线下 {{??}} 直播 {{?}}</span></div>
		<div class="f12">创建：<span class="c888">{{=isNullFormat(v.nick_name)}}</span></div>
		<input name="courseId" value="{{=v.related_id}}" type="hidden">
		<input name="courseName" value="{{=isNullFormat(v.course_name)}}" type="hidden">
		<input name="courseDesc" value="{{=isNullFormat(v.course_description)}}" type="hidden">
		<input name="courseType" value="{{=isNullFormat(v.course_type)}}" type="hidden">
		<input name="createName" value="{{=isNullFormat(v.nick_name)}}" type="hidden">
		<input name="courseStartTime" value="{{=dateFormat(v.course_start_time)}}" type="hidden">
		<input name="courseEndTime" value="{{=dateFormat(v.course_end_time)}}" type="hidden">
		<input name="createTime" value="{{=dateFormat(v.create_time)}}" type="hidden">
		<input name="id" value="{{=isNullFormat(v.id)}}" type="hidden">
	</div>
</div>
{{~}}
</script>



<script id="table_course_tab_left_course_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
	    <td>{{=isNullFormat(v.courseName)}}</td>
	    <td class="text-center">
			{{?v.courseType == 1}}线上
			{{??v.courseType == 2}}线下
			{{??}}直播
			{{?}}
		</td>
	    <!--td class="text-center">{{=dateFormat(v.createTime)}}</td>
	    <td class="text-center">{{=isNullFormat(v.createName)}}</td-->
	                        
	    <td class="text-center">
	        <button type="button" class="icon_2" onclick="deleteItemsElements(this);"></button>
	    </td>
		<input name="operateType" value="{{=setOperateType(v.id)}}" type="hidden">
		<input name="id" value={{=setElementId(v.id)}} type="hidden" >
		<input id="course_required_active_{{=isNullFormat(v.courseId)}}" name="courseId" value={{=isNullFormat(v.courseId)}} type="hidden" >
	 </tr>
 {{~}}	
</script>

<!-- 课程题库模板 -->
<script id="course_quiz_template_modal"  type="text/x-dot-template">
<form id="courseQuizForm" method="post">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">{{=it.modal_tittle}}</h4>
	</div>
	<div class="modal-body hdn">
		<!-- 这里开始是弹出内容 -->
		<div class="_up_left pull-left">
			<div class="w240">
				<button type="button" class="btn-success btn36"
					onclick="searchCourseQuiz();">&nbsp;</button>
				<div class="search_input">
					<input type="text" id="quizModalKeyWord" class="form-control">
				</div>
			</div>
			<div class="tab-content" style="min-height:460px">
				<div class="tab-data">
					<div class="left_box mt10" id="quizLeftTab"
						style="max-height: 510px; overflow-y: auto; overflow-x: hidden;">
					</div>
					<input type="hidden" id="hid_default_quiz_template_modal_page" value="1">
				</div>
			</div>
		</div>
		<div class="_up_right pull-right" style="background: #f3f3f3 none repeat scroll 0 0;border: 1px dashed #cdd5d9;padding-bottom:0px;">
			<div id="myQuizTabContent" class="tab-content" style="min-height:510px">
				<div id="activeCourseQuizPanel"
					class="pre1_con pre1_con_group tab-pane active ui-droppable"
					style="min-height: 513px;border: 0px">
					<div id="addCourseQuizPanel" class="box3 pull-left">
						
						<label style="margin-left:2px;margin-top:40px;font-size:16px">拖拽添加考试</label>
					</div>

				</div>
			</div>
			<div id="myQuizSettingContent" class="tab-content" style="display: none; min-height:510px;" >
				<div class="pre1_con pull-right" id="nr01" style="height: 510px; width: 590px;overflow-y: auto;">
					<div class="go_back" onclick="displayQuizList()">
						<span class="icon-double-angle-down"></span>
					</div>
					<ul class="shezhi">
						<li>
							<div class="pull-left text-right w200 pt10">
							<span class="input-title-span">＊</span>考试名称： </div>
							<input id="quizName" type="text" class="form-control w230 pull-left" disabled="disabled"
							check-type="required" required-message="请输入考试名称" value="{{=isNullFormat(it.quizName)}}">
						</li>
						<li>
							<div class="pull-left text-right w200 pt10">考试别名：</div> <input
							 id="quizAlias" type="text" class="form-control w230  pull-left"
							 onkeyup="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizAlias',this)"
							 value="{{=isNullFormat(it.quizAlias)}}">
						</li>
						<li>
							<div class="pull-left text-right w200 pt10"><span class="input-title-span">＊</span>通过分数（百分制）：</div> <input
							 id="quizPassScore" type="text" class="form-control w230  pull-left" 
							 onkeyup="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizPassScore',this)"
							 check-type="scoreNumber" min-max-num="0-100"  value="{{=isNullFormat(it.quizPassScore)}}">
						</li>
						<li>
							<div class="pull-left text-right w200 pt10">答题时间限制：</div> <input
							 id="quizFinishTime" type="text" class="form-control w230  pull-left"
							 onkeyup="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizFinishTime',this)"
							check-type="required number"  non-required='true' value="{{=isNullFormat(it.quizFinishTime)}}">
						</li>
						<li>
							<div class="pull-left text-right w200 pt10"><span class="input-title-span">＊</span>允许测验最大次数：</div> <input
							 id="quizMaxTimes" type="text" class="form-control w230  pull-left" 
							 onkeyup="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizMaxTimes',this)"
							 check-type="number" min="1" value="{{=isNullFormat(it.quizMaxTimes)}}">
						</li>
						<li>
							<div class="pull-left text-right w200 pt8"><span class="input-title-span">＊</span>多次考试最终成绩确定方式：</div>
							<div class="r_con">
								<input id="hiddenQuizFinallyResultType" type="hidden" value="{{=isNullFormat(it.quizFinallyResultType)}}">
								<div class="mb10">
									<div class="radio-group pull-left">
										<input type="radio" name="quizFinallyResultType"
											id="quizFinallyResultType1" value="1" 
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizFinallyResultType',this)"
											{{?it.quizFinallyResultType==1}} checked="checked"{{?}} >
										<label class="radio-2" for="quizFinallyResultType1"></label>
									</div> 
									<span class="pt8 ml10">最后一次考试成绩</span>
								</div>
								<div class="mb10">
									<div class="radio-group pull-left">
										<input type="radio" name="quizFinallyResultType" 
											id="quizFinallyResultType2" value="2"
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizFinallyResultType',this)"
											{{?it.quizFinallyResultType==2}} checked="checked"{{?}}>
										<label class="radio-2" for="quizFinallyResultType2"></label>
									</div>
									<span class="pt8 ml10">最高分</span>
								</div>
								<div class="mb10">
									<div class="radio-group pull-left">
										<input type="radio" name="quizFinallyResultType" 
											id="quizFinallyResultType3" value="3"  
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizFinallyResultType',this)"
											{{?it.quizFinallyResultType==3}} checked="checked"{{?}}>
										<label class="radio-2" for="quizFinallyResultType3"></label>
									</div>
									<span class="pt8 ml10">平均分</span>
								</div>
							</div>
						</li>
						<li>
							<div class="pull-left text-right w200 pt8">成绩公布：</div>
							<div class="r_con">
								<div class="mb10">
									<div class="radio-group">
										<input type="radio" name="quizPublishResultType"
											id="quizPublishResultType1" value="1"  
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizPublishResultType',this)"
											{{?it.quizPublishResultType== 1}} checked="checked"{{?}} >
										<label class="radio-2" for="quizPublishResultType1"></label>
									</div>
									<span class="pt8 ml10">考试分数保密</span>
									<div class="clearfix"></div>
								</div>
								<div class="mb10">
									<div class="radio-group">
										<input type="radio" name="quizPublishResultType" 
											id="quizPublishResultType2" value="2"  
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizPublishResultType',this)"
											{{?it.quizPublishResultType== 2}} checked="checked"{{?}} >
										<label class="radio-2" for="quizPublishResultType2"></label>
									</div>
									<span class="pt8 ml10">交卷后即时显示分数成绩</span>
									<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="pull-left text-right w200 pt8">试卷安全：</div>
							<div class="r_con">
								<div class="mb10">
									<div class="checkbox-group">
										<input type="checkbox" name="quizSafe" id="quizSafe1"  
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizSafe',this)"
											value="1" {{? isSubStr(it.quizSafe,'1')}} checked="checked"{{?}} >
										<label class="checkbox-2" for="quizSafe1"></label>
									</div>
									<!-- 允许考生提交后查看其提交的答案和标准答案 -->
									<span class="pt8 ml10">允许提交后查看其提交的答案和标准答案</span>
									<div class="clearfix"></div>
								</div>
								<div class="mb10">
									<div class="checkbox-group">
										<input type="checkbox" name="quizSafe" id="quizSafe2"  
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizSafe',this)"
											value="2" {{? isSubStr(it.quizSafe,'2')}} checked="checked"{{?}} >
										<label class="checkbox-2" for="quizSafe2"></label>
									</div>
									<span class="pt8 ml10">允许学员成绩合格后继续参加考试</span>
									<div class="clearfix"></div>
								</div>
								<div class="mb10">
									<div class="checkbox-group">
										<input type="checkbox" name="quizSafe" id="quizSafe3"  
											onclick="quizKeyUp('{{=isNullFormat(it.quizId)}}','quizSafe',this)" 
											value="3" {{? isSubStr(it.quizSafe,'3')}} checked="checked"{{?}} >
										<label class="checkbox-2" for="quizSafe3"></label>
									</div>
									<span class="pt8 ml10">考题显示随机顺序</span>
									<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<input type="hidden" id="quiz_id" name="id" value="{{=isNullFormat(it.id)}}">
						<input type="hidden" id="quizId" name="quizId" value="{{=isNullFormat(it.quizId)}}">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
</form>
</script>

<!-- 课程调查问卷模板 -->
<script id="course_survey_template_modal"  type="text/x-dot-template">
	<form id="courseSurveyForm" method="post" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >{{=it.modal_tittle}}</h4>
	</div>
	<div class="modal-body hdn">
		<!-- 这里开始是弹出内容 -->
		<div class="_up_left pull-left">
            <div class="w240">
				<button type="button" class="btn-success btn36" onclick="searchCourseSurvey();">&nbsp;</button>
				<div class="search_input"><input type="text" class="form-control" id="surveyModalKeyWord"></div>
			</div>
			<div class="tab-content" style="min-height:460px">
				<div class="tab-data">
            		<div class="left_box mt10" id="surveyLeftTab" style="max-height: 510px;overflow-y:auto"></div>
					<input type="hidden" id="hid_default_survey_template_modal_page" value="1">
				</div>
			</div>
		</div>
        <div class="_up_right pull-right" style="background: #f3f3f3 none repeat scroll 0 0;border: 1px dashed #cdd5d9;padding-bottom:0px;">
			<div id="mySurveyTabContent" class="tab-content" style="min-height:510px">
				<div id="activeCourseSurveyPanel"
					class="pre1_con pre1_con_group tab-pane active ui-droppable"
					style="height: 513px;border: 0px"">
					<div id="addCourseSurveyPanel" class="box3 pull-left">

						<label style="margin-left:2px;margin-top:40px;font-size:16px">拖拽添加调查</label>
					</div>
					
				</div>
			</div>
			<div id="mySurveySettingContent" class="tab-content" style="display: none;min-height:510px;">
                <div class="pre1_con pull-right" id="nr01" style="height: 513px;;width:590px">
					<div class="go_back" onclick="displaySurveyList()">
						<span class="icon-double-angle-down"></span>
					</div>
                    <ul class="shezhi">
                        <li>
                            <div class="pull-left text-right w100 pt10">
							<span class="input-title-span">＊</span>问卷名称： </div>
                            <input  type="text" id="surveyName" check-type="required"  min-max='0-200'
								required-message="请输入问卷调查名称!" placeholder="问卷调查名称"
								data-callback="checkSurveyName()" call-message="此问卷调查名称已存在"
								class="form-control w230" value="{{=isNullFormat(it.surveyName)}}" disabled="disabled">
							<input type="hidden" id="oldSurveyName" value="{{=isNullFormat(it.surveyName)}}">
                        </li>
                        <li>
                            <div class="pull-left text-right w100 pt10">问卷描述：</div>
                            <textarea  onkeyup="keyUp('{{=isNullFormat(it.surveyId)}}','desc',this)" id="surveyDesc" rows="8" cols="" class="form-control w230">{{=isNullFormat(it.surveyDesc)}}</textarea>
                        </li>
                        <li>
                            <div class="pull-left text-right w100 pt10">
							<span class="input-title-span">＊</span>有效起至时间：</div>
                            <div class="w180 pull-left ml3" style="margin-top: -3px;">
								<input onchange="keyUp('{{=isNullFormat(it.surveyId)}}','stime',this)" type="text" class="form-control date_btn form_datetime"	name="surveyStartTime" id="surveyStartTime" style="height: 36px;"
									check-type="dateYmd startSurveyDateCompare" required-message="请选择有效的开始日期"
									aria-describedby="basic-addon1" value="{{=dateFormat(it.surveyStartTime)}}">
							</div>
							<span class=" pull-left pl10 pr10 pt10">～</span>
							<div class="w180 pull-left ml20" style="margin-top: -3px;">
								<input onchange="keyUp('{{=isNullFormat(it.surveyId)}}','etime',this)" type="text" class="form-control date_btn form_datetime"
									name="surveyEndTime" id="surveyEndTime" style="height: 36px;"
									check-type="dateYmd endSurveyDateCompare" required-message="请选择有效的结束日期"
									aria-describedby="basic-addon1" value="{{=dateFormat(it.surveyEndTime)}}">
							</div>
                        </li>
                        <li>
                            <div class="pull-left text-right w100 pt8">调查模式：</div>
                            <div class="r_con">
                                <div class="mb10">
                                    <div class="radio-group">
                                        <input type="radio" onclick="keyUp('{{=isNullFormat(it.surveyId)}}','smodel',this)" name="surveyModel" id="surveyModel1" value="0" {{? it.surveyModel == 0}} checked="checked" {{?}}>
                                        <label class="radio-2" for="surveyModel1"></label>
                                    </div>
                                    <span class="pt8 ml10">匿名模式</span>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="mb10">
                                   <div class="radio-group">
                                        <input type="radio" onclick="keyUp('{{=isNullFormat(it.surveyId)}}','smodel',this)"  name="surveyModel" id="surveyModel2" value="1" {{? it.surveyModel == 1}} checked="checked" {{?}}>
                                        <label class="radio-2" for="surveyModel2"></label>
                                    </div>
                                    <span class="pt8 ml10">管理员模式</span>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </li>
                     </ul>
					<input type="hidden" id="course_survey_id" name="course_survey_id" value="{{=isNullFormat(it.id)}}">
					<input type="hidden" id="surveyId" name="surveyId" value="{{=isNullFormat(it.surveyId)}}">
                </div>
            </div>
        </div>
	</div>
	
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</form>
</script>

<!-- 课程附件模板 -->
<script id="course_material_template_modal"  type="text/x-dot-template">
		<form id="courseMaterialForm" method="post" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">上传文件</h4>
		</div>
		<div class="modal-body hdn">
			<div style="border:1px solid #e3e3e3;height: 90px;">
				<div class="col-sm-12 mt20">
					<div class="pull-left">
						<input type="text" id="zipFileName" name="zipFileName" check-type="required" value="{{=isNullFormat(it.materialName)}}" class="form-control w400" > 
						
					</div>
					<div class="user-item pull-left" style="height: 35px;">
						<div class="user-buttons">
							<!-- <input id="materialUploadFile" name="materialUploadFile" class="user-card" type="file"> -->
							<!-- <button class="certify" type="button" style="display: block; margin: -16px 0px 0px 95px;" onclick="materialUploadFile()">选择文件</button> -->
							<div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
	                            <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
	                            <input accept="image/png, image/gif, image/jpg, image/jpeg" id="materialUploadFile" type="file" name="materialUploadFile"
	                             multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
	                            <!--input id="materialUploadFile" type="file" name="materialUploadFile"
	                             	multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;"
								 	onchange="registerKeyUp(this,'zipFileName','courseMaterialGenerateName','courseAttachId','9')"-->
                            </div>
							<div style="margin-left: 40px;"><button class="certify" type="button" style="display:block;margin:-34px 93px;margin-right:0px;width:70px;background:rgb(177,205,92)" 
								onclick="deleteFileAttach('zipFileName','courseMaterialGenerateName','courseAttachId')">删除</button>
							</div>
							<!-- <button class="certify" type="button" style="display: block; margin: -16px 0px 0px 95px;" onclick="cancleFile()">删除</button>-->
						</div>
						<input type="hidden" id="uploadDir" name="uploadDir">
						<input type="hidden" id="courseMaterialGenerateName" name="courseMaterialGenerateName" value = "{{=it.materialAttachName}}">
						<input type="hidden" id="courseMaterialId" name="courseMaterialId" value="{{=isNullFormat(it.id)}}">
						<input type="hidden" id="attachId" name="attachId">
						<input type="hidden" id="materialSize" name="materialSize">
					</div>
					<div class="c929292 f12 pull-left mt10 ml10">
						请上传任意您希望上传的相关附件。
					</div>
				</div>
				
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
			<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
		</div>
		</form>
</script>

<!-- 课程作业模板 -->
<script id="course_homework_template_modal"  type="text/x-dot-template">
			<form id="courseHomeworkForm" method="post" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">{{=it.modal_tittle}}</h4>
			</div>
			<div class="modal-body hdn">
                <div class="">
                    <div id="myHomeworkTabContent" class="tab-content" style="min-height:510px">
                        <div class="pre1_con tab-pane active" id="nr01" style="height: 513px;">
                            <ul class="shezhi">
                                <li>
                                    <div class="pull-left text-right w200 pt10">
										<span class="input-title-span">＊</span>作业名称：
									</div>
                                    <input type="text" name="homeworkName" value="{{=isNullFormat(it.homework_name)}}" 
										class="form-control w230" data-callback="checkName()"  call-message="此作业名称已存在" id="homeworkName" check-type="required" 
										required-message="请输入作业名称" >
									<input type="hidden" value="{{=isNullFormat(it.homework_name)}}" id="hiddenhomeworkName"> 
                                </li>
                                <li>
                                    <div class="pull-left text-right w200 pt10">
										<span class="input-title-span">＊</span>截收日期：
									</div>
                                    <div class="w180 pull-left ml3" style="margin-top: -3px;">
										<input type="text" name="homeworkEndTime" id="homeworkEndTime" 
												class="form-control date_btn form_datetime" aria-describedby="basic-addon1"
												check-type="dateYmd" value="{{=dateFormat(it.homework_end_time)}}" 
												style="height: 36px;" >
									</div>
                                </li>
                                <li>
                                    <div class="pull-left text-right w200 pt8">
										<span class="input-title-span">＊</span>	是否允许延交：
									</div>
                                    <div class="r_con">
                                        <div class="mb10">
                                            <div class="radio-group">
                                                <input type="radio" name="homeworkIsDelay" id="homeworkIsDelay1" value="1"   {{?it.homework_is_delay == 1}} checked="checked"  {{?}}>
                                                <label class="radio-2" id="label_homeworkIsDelay1" for="homeworkIsDelay1"></label>
                                            </div>
                                            <span class="pt8 ml10">是，允许在截止时间之后提交作业</span>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="mb10">
                                           <div class="radio-group">
                                                <input type="radio" name="homeworkIsDelay" id="homeworkIsDelay2" value="0" {{?it.homework_is_delay == 0}} checked="checked"  {{?}}>
                                                <label class="radio-2" for="homeworkIsDelay2"></label>
                                            </div>
                                            <span class="pt8 ml10">否，不许在截止时间之后提交作业</span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </li>
                                 <li>
                                    <div class="pull-left text-right w200 pt8">
										<span class="input-title-span">＊</span>提交作业访问权限：
									</div>
                                    <div class="r_con">
                                        <div class="mb10">
                                            <div class="radio-group">
                                                <input type="radio" name="homeworkAccessAuth" id="homeworkAccessAuth1" value="0" {{?it.homework_access_auth == 0}} checked="checked" {{?}}>
                                                <label class="radio-2"  id="label_homeworkAccessAuth1" for="homeworkAccessAuth1"></label>
                                            </div>
                                            <span class="pt8 ml10">只有GroupLeader,内容创建者及提交者可见</span>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="mb10">
                                           <div class="radio-group">
                                                <input type="radio" name="homeworkAccessAuth" id="homeworkAccessAuth2" value="1"  {{?it.homework_access_auth == 1}} checked="checked" {{?}}>
                                                <label class="radio-2" for="homeworkAccessAuth2"></label>
                                            </div>
                                            <span class="pt8 ml10">组内用户均可见</span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="pull-left text-right w200 pt10">
										<span class="input-title-span">＊</span>作业要求：
									</div>
                                    <textarea rows="4" name="homeworkRequire" id="homeworkRequire" cols="" class="form-control w230"
										check-type="required" required-message="请输入作业要求" >{{=isNullFormat(it.homework_require)}}</textarea>
                                </li>
                               {{?it.uploadFile=='10013'}}
                                <li>
                                    <div class="pull-left text-right w200 pt10">
										<span class="input-title-span">＊</span>上传附件：
									</div>
                                    <div class="pull-left">
										<div class="pull-left">
											<input type="text" id="homeworkFileName" name="homeworkFileName" class="form-control w230" readonly value="{{=isNullFormat(it.attach_name)}}"> 
										</div>
										<div class="user-item pull-left" style="height: 35px;">


											<div style="width:90px;height:36px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 36px;margin-left: 5px;">
	                           					 <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
	                            				<input accept="image/png, image/gif, image/jpg, image/jpeg,.doc,.csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,
																text/plain,.doc,.pdf" id="xhomeworkUploadFile" type="file" name="xhomeworkUploadFile"
	                             					multiple="" style="position:absolute;opacity:0;width:400px;hright:36px;right:0;top:0;cursor:pointer;">
                            				</div>
											<div style="margin-left: 40px;">
												<button class="certify" type="button" style="display:block;margin:-36px 60px;height: 36px;width:70px;background:rgb(177,205,92)" 
													onclick="deleteFileAttach('homeworkFileName','homeworkAttachment','homeworkAttachmentId')">删除</button>
											</div>
											<input type="hidden" id="uploadDir" name="uploadDir">
											<input type="hidden" id="homeworkAttachment" name="homeworkAttachment" value = "{{=it.homework_attach}}">
											<input type="hidden" id="homeworkId" name="homeworkId" value="{{=isNullFormat(it.id)}}">
											<input type="hidden" id="homeworkAttachmentId" name="homeworkAttachmentId">
											
										</div>
									</div>
                                </li>
                                {{?}}
                                <li>
                                    <div class="pull-left text-right w200 pt8">
										<span class="input-title-span">＊</span>是否发布该作业：
									</div>
                                    <div class="r_con">
                                        <div class="mb10">
                                            <div class="radio-group">
                                                <input type="radio" name="homeworkIsPublish" id="homeworkIsPublish1" value="1" {{?it.homework_is_publish == 1}} checked="checked" {{?}}>
                                                <label class="radio-2" id="lable_homeworkIsPublish1" for="homeworkIsPublish1"></label>
                                            </div>
                                            <span class="pt8 ml10">是</span>
                                            <div class="radio-group ml20">
                                                <input type="radio" name="homeworkIsPublish" id="homeworkIsPublish2" value="0" {{?it.homework_is_publish == 0}} checked="checked" {{?}}>
                                                <label class="radio-2" for="homeworkIsPublish2"></label>
                                            </div>
                                            <span class="pt8 ml10">否</span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </li>
                              </ul>
                        </div>
                    </div>
                    <input type="hidden" id="homeworkAttachment" name="homeworkAttachment" value="{{=isNullFormat(it.homework_attach)}}">
					<input type="hidden" id="homeworkAttachmentId" name="homeworkAttachmentId">
					<input type="hidden" id="homeworkId" name="homeworkId" value="{{=isNullFormat(it.id)}}">
                </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
				<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
			</div>
			</form>
</script>

<!-- 课程通告模板 -->
<script id="course_notice_template_modal"  type="text/x-dot-template">
			<form id="courseNoticeForm" method="post" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">{{=it.modal_tittle}}</h4>
			</div>
			<div class="modal-body hdn" style="height: 380px;">
                <div class="">
                    <div id="myNoticeTabContent" class="tab-content" style="min-height:510px">
                        <div class="pre1_con tab-pane active" id="nr01" style="height: 513px;">
                            <ul class="shezhi">
                                <li>
                                    <div class="pull-left text-right w200 pt10">
									<span class="input-title-span">＊</span>公告名称：</div>
                                    <input type="text" id="noticeName" name="noticeName"  
										value="{{=isNullFormat(it.notice_name)}}" class="form-control w230" 
										required-message="请输入公告名称!" check-type="required" 
										data-callback="checkCourseNoticeName()" call-message="此公告名称已存在">
									<input type="hidden" id="oldNoticeName" value="{{=isNullFormat(it.notice_name)}}"> 
                                </li>
                                <li>
                                    <div class="pull-left text-right w200 pt10">
									<span class="input-title-span">＊</span>发布日期：</div>
                                    <div class="w180 pull-left ml3" style="margin-top: -3px;">
										<input id ="noticePublishTime" name="noticePublishTime" type="text" class="form-control date_btn form_datetime" aria-describedby="basic-addon1" 
												value = "{{=dateFormat(it.notice_publish_time)}}" style="height: 36px;" check-type="dateYmd">
									</div>
                                </li>
                                <li>
                                    <div class="pull-left text-right w200 pt10">
									<span class="input-title-span">＊</span>公告内容：</div>
                                    <textarea id="noticeContent" name="noticeContent" rows="4" cols="" 
										class="form-control w230" check-type="required" required-message="请输入公告内容!">{{=isNullFormat(it.notice_content)}}</textarea>
                                </li>
                                <li>
                                    <div class="pull-left text-right w200 pt8">
									<span class="input-title-span">＊</span>发布范围：</div>
                                    <div class="r_con">
                                        <div class="mb10">
                                            <div class="radio-group">
                                                <input type="radio" name="noticePublishScope" id="noticePublishScope1" value="0" {{?it.notice_publish_scope == 0}} checked="checked" {{?}} >
                                                <label class="radio-2" for="noticePublishScope1"></label>
                                            </div>
                                            <span class="pt8 ml10">未开始</span>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="mb10">
                                           <div class="radio-group">
                                                <input type="radio" name="noticePublishScope" id="noticePublishScope2" value="1" {{?it.notice_publish_scope == 1}} checked="checked" {{?}} >
                                                <label class="radio-2 checked" for="noticePublishScope2"></label>
                                            </div>
                                            <span class="pt8 ml10">学习中</span>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="mb10">
                                           <div class="radio-group">
                                                <input type="radio" name="noticePublishScope" id="noticePublishScope3" value="2" {{?it.notice_publish_scope == 2}} checked="checked" {{?}} >
                                                <label class="radio-2" for="noticePublishScope3"></label>
                                            </div>
                                            <span class="pt8 ml10">所有</span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </li>
                               </ul>
                        </div>
                    </div>
                </div>
			</div>
			<input type="hidden" id="noticeId" name="noticeId" value="{{=isNullFormat(it.id)}}">
			<div class="modal-footer">
				<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
				<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
			</div>
			</form>
</script>

<!-- 课程相关模板 -->
<script id="course_related_course_template_modal"  type="text/x-dot-template">
	<form id="courseRelatedCourseForm" method="post" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">{{=it.modal_tittle}}</h4>
	</div>
	<div class="modal-body hdn">
		<!-- 这里开始是弹出内容 -->
		<div class="_up_left pull-left" style="min-height:510px;">
            <div class="w240">
      			<button type="button" class="btn-success btn36" onclick="searchCourseCourseDataList();">&nbsp;</button>
      			<div class="search_input"><input type="text" class="form-control" id="courseModalKeyWord"></div>
      		</div>
			<div class="tab-content" style="min-height:510px">
				<div class="tab-data">
                    <div class="left_box mt10" id="courseLeftTab" style="overflow-x: hidden;max-height: 450px;overflow-y:auto;">
                  	</div>
				  	<input type="hidden" id="hid_default_course_template_modal_page" value="1">
				</div>
			</div>
		</div>
           <div class="_up_right pull-right"  style="min-height:510px">
				
             <ul class="nav nav-tabs set_tab" role="tablist" style= "background-color:#FFFFFF ">
        		<li id="liRequired" role="presentation" class="active"><a href="javascript:displayRequiredCourseTabContent()">必要</a></li>
        		<li id="liRecommend" role="presentation"><a href="javascript:displayRecommendCourseTabContent()">推荐</a></li>
    		 </ul>
			 <div class="tab-content" id="courseTabContent"  style="min-height:400px">
					<div id="activeCourseRelatedPanel"
						class="pre1_con pre1_con_group tab-pane active ui-droppable"
						style="height: 456px;">
						<div id="addCourseRelatedPanel" class="box3 pull-left">

							<label style="margin-left: -6px;">拖拽添加必要课程</label>
						</div>

					</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</form>
</script>

<!-- 课程附件模板 -->
<script id="upload_attachment_template_modal"  type="text/x-dot-template">
		<form id="uploadAttachmentForm" method="post" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">上传文件</h4>
		</div>
		<div class="modal-body hdn">
			<div style="border:1px solid #e3e3e3;height: 90px;">
				<div class="col-sm-12 mt20">
					<div class="pull-left">
						<input type="text" id="zipFileName" name="zipFileName" check-type="required" class="form-control w400" > 
					</div>
					<div class="user-item pull-left" style="height: 35px;">
						<div class="user-buttons">
							<input id="attachUploadFile" name="attachUploadFile" class="user-card" type="file">
							<button class="certify" type="button" style="display: block; margin: -46px 0px 0px 95px;" onclick="deleteFile('zipFileName','homeworkAttachment','homeworkAttachmentId')">删除</button>
						</div>
					</div>
					<div class="c929292 f12 pull-left mt10 ml10">
						支持JPG, PNG, GIF 格式的图片。
					</div>
				</div>
				<div>
					<div class="update-bar border-radius" id="progressBar" style="top: 716px; width: 910px; margin: 0px auto 0px 3px;">
						<!-- <div class="bar"></div> -->
					</div>
				</div>
				<input type="hidden" id="attachName" name="attachName">
				<input type="hidden" id="attachId" name="attachId">
				<input type="hidden" id="attachUrl" name="attachUrl">
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
			<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
		</div>
		</form>
</script>


<!-- 课程包元素模板数据 -->
<script id="course_package_items_elements_template_modal"  type="text/x-dot-template">
	<form id="courseItemsElementsForm" method="post" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">{{=it.modal_tittle}}</h4>
	</div>
	<div class="modal-body hdn">
		<!-- 这里开始是弹出内容 -->
		<div class="up_left pull-left" style="min-height:510px;">
                 <div class="w240">
      				<button type="button" class="btn-success btn36" onclick="searchCourseCourseDataList();">&nbsp;</button>
      				<div class="search_input"><input type="text" class="form-control" id="courseModalKeyWord"></div>
      			</div>
				<div class="tab-content" style="min-height:510px">
					<div class="tab-data">
                  		<div class="left_box mt10" id="courseLeftTab" style="max-height: 450px;overflow-y:auto">
                  		</div>
				  		<input type="hidden" id="hid_default_course_template_modal_page" value="1">
					</div>
				</div>
		</div>

		<div class="pull-left up_right_course_item">	
           <div id="right_content_course_package_elements" >
        		<div class="hdn">
	                <div class="w240 pull-right">
					    <button type="button" class="btn-success btn36" onclick="loadItemsElementsList();">&nbsp;</button>
					    <div class="search_input"><input type="text" id ="courseKeyWord"  class="form-control"></div>
			    	</div>
	            </div>
	            <table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt10">
	                <thead>
						<tr>
							<th class="text-center" style="width:60%;">课程名称</th>
							<th class="text-center">线上／线下</th>
							<!--th class="text-center">创建日期</th>
							<th class="text-center">创建人</th-->
							<th class="text-center">操作</th>
						</tr>
					</thead>
	                <tbody id="table_content_package_items_elements">
	                </tbody>
	            </table>
	            <!-- 分页 -->
				<div class="mt20 text-center" id="table_content_package_items_elements_data_page_panel">
				</div>
        	</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" btn-type='true'>提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</form>
</script>

<!-- 课程元素模板数据 -->
<script id="group_course_to_select_template_modal"  type="text/x-dot-template">

<form id="groupCourseForm" method="post" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >{{=it.modal_tittle}}</h4>
	</div>
	<div class="modal-body hdn" style="height: 513px;">
		<div class="select_b mt10">
			<div class="w240 pull-right">
				<button type="button" class="btn-success btn36" onClick="searchGroupCourseToSelectList();">&nbsp;</button>
				<div class="search_input">
					<input type="text" id ="groupCourseKeywords" class="form-control">
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<table id="course_item_table" cellpadding="0" cellspacing="0"	class="table table-striped table-hover td_h60 mt10">
			<thead>
				<tr>
					<th style="width: 8%;" class="text-center">
						<div class="checkbox-group">
							<input type="checkbox" name="allGroupCourseRelatedId" id="allGroupCourseRelatedId"> 
							<label class="checkbox-2" for="allGroupCourseRelatedId"></label>
						</div>
					</th>
					<th class="text-center" style="width: 35%;">课程名称</th>
					<th class="text-right" style="width: 15%;">课程类型</th>
					<th class="text-center" style="width: 15%;">创建人</th>
					<th class="text-center" >创建时间</th>
				</tr>
			</thead>
			<tbody id="groupCourseToSelectList">
			</tbody>
		</table>
		<!-- 分页 -->
		<div class="mt20 text-center" id="group_course_to_select_data_page_Panel">
		</div>
	</div>
	
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" onclick="submitGroupCourse();" btn-type='true' data-dismiss="modal">提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</form>
</script>
<!-- 课程包元素模板数据 -->
<script id="group_course_package_to_select_template_modal"  type="text/x-dot-template">

<form id="groupCoursePackageForm" method="post" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >{{=it.modal_tittle}}</h4>
	</div>
	<div class="modal-body hdn" style="height: 513px;">
		<div class="select_b mt10">
			<div class="w240 pull-right">
				<button type="button" class="btn-success btn36" onClick="searchGroupCoursePackageToSelectList();">&nbsp;</button>
				<div class="search_input">
					<input type="text" id ="groupCoursePackageKeywords" class="form-control">
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<table id="course_item_table" cellpadding="0" cellspacing="0"	class="table table-striped table-hover td_h60 mt10">
			<thead>
				<tr>
					<th style="width: 8%;" class="text-center">
						<div class="checkbox-group">
							<input type="checkbox" name="allGroupCoursePackageRelatedId" id="allGroupCoursePackageRelatedId"> 
							<label class="checkbox-2" for="allGroupCoursePackageRelatedId"></label>
						</div>
					</th>
					<th class="text-center" style="width: 35%;">课程包名称</th>
					<th class="text-right" style="width: 15%;">课程包描述</th>
					<th class="text-center" style="width: 15%;">创建人</th>
					<th class="text-center" >创建时间</th>
				</tr>
			</thead>
			<tbody id="groupCoursePackageToSelectList">
			</tbody>
		</table>
		<!-- 分页 -->
		<div class="mt20 text-center" id="group_course_package_to_select_data_page_Panel">
		</div>
	</div>
	
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" onclick="submitGroupCoursePackage();" btn-type='true' data-dismiss="modal">提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</form>
</script>
<script id="group_course_package_to_select_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<div class="checkbox-group">
				<input type="checkbox" name="groupCoursePackageRelatedIdCheckBox" id="{{=isNullFormat(v.id)}}" onclick="isSelectAll('allGroupCoursePackageRelatedId','groupCoursePackageRelatedIdCheckBox');"> 
				<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
			</div>
		</td>
		<td class="text-right">{{=isNullFormat(v.package_name)}}</td>
		<td class="text-right">
			{{=isNullFormat(v.package_desc)}}
		</td>
		<td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
		<td class="text-center">{{=dateFormat(v.create_time)}}</td>
		<input type="hidden" name="courseId" value="{{=isNullFormat(v.id)}}">
	</tr>
{{~}}
</script>


<script id="lecturer_detail_template" type="text/x-dot-template">
			<div class="p20">
                <div class="video">
					{{? it.data.courseMap.course_cover_img == ''}}
						<img src="${ctx}/resources/img/course/title_pic.png" alt="" width="300px">
					{{??}}
						<img src="${imgStatic}{{=isNullFormat(it.data.courseMap.attach_url)}}/{{=isNullFormat(it.data.courseMap.generate_name)}}" alt=""  width="300px" height="168.2px">
					{{?}}
                </div>
                <div class="video_r">
                    <h3 class="title pb10" title="{{=isNullFormat(it.data.courseMap.course_name)}}">{{=subString(isNullFormat(it.data.courseMap.course_name),15)}}</h3>

					 <div class="text-right" style="margin-top: -40px;margin-bottom: 20px;height: 29px">
                           <!--  <button type="button" class="btn btn_blue mr10" onclick ="onCourseOnlineSetting('{{=it.data.courseMap.id}}','1')">课程管理</button> -->
                            {{? it.data.courseMap.create_id == it.data.userId}}
							{{? it.data.courseMap.is_publish != 1}}
                        	<button type="button" class="btn_b btn_green" onclick="courseOnlinePublish('{{=it.data.courseMap.id}}')" style="margin-top:-4px;">发布</button>
                      		{{?}}
							{{? it.data.courseMap.is_publish == 1}}
							<button type="button" class="btn_b btn_green" onclick="cancelCourseOnlinePublish('{{=it.data.courseMap.id}}');" id="cancelPublishBtn" style="margin-top:-4px;">取消发布</button>
							{{?}}
                            {{?}}    
                        </div>
                    <input type="hidden" id="isPublic" value="{{=isNullFormat(it.data.courseMap.is_public)}}">
                    <input type="hidden" id="isEdit" value="{{=isNullFormat(it.data.courseMap.is_edit)}}">
                    <div class="mt10">
                        <ul class="course_des">
                            <li>评分：<div 
									{{? it.data.courseMap.course_star == 2}} class="star2" {{?}}
									{{? it.data.courseMap.course_star == 3}} class="star3" {{?}}
									{{? it.data.courseMap.course_star == 4}} class="star4" {{?}}
									{{? it.data.courseMap.course_star == 5}} class="star5" {{??}}
									 class="star1" {{?}}
                              </div>
                            </li>
	                         <li>课程类型：<span>
								{{? it.data.courseMap.course_type == 1}}
	                           	线上课程
								{{?}}
	                       		{{? it.data.courseMap.course_type == 2}}
	                            线下课程
	                       		{{?}}
	                     		{{? it.data.courseMap.course_type == 3}}
	                            直播课程
	                      		{{?}}
	                       
	                     </span></li>
	                     <li>课程的课件数：<span id="coursewareCount">{{=isNullFormat(it.data.coursewareCount)}}</span></li>
	                     <li>培训起至时间：<span>
							
							{{? it.data.courseMap.start_time != '' && it.data.courseMap.expire_time != ''}}
								{{=dateFormat(it.data.courseMap.start_time)}} 至{{=dateFormat(it.data.courseMap.expire_time)}}
							{{?}}
							{{? it.data.courseMap.start_time == '' && it.data.courseMap.expire_time != ''}}
								_至{{=dateFormat(it.data.courseMap.expire_time)}}
							{{?}}
							{{? it.data.courseMap.start_time != '' && it.data.courseMap.expire_time == ''}}
								 {{=dateFormat(it.data.courseMap.start_time)}}至_
							{{?}}
							{{? it.data.courseMap.start_time == '' && it.data.courseMap.expire_time == ''}}
								未设置
							{{?}}
	                           </span>
	                     </li>
	                     <li>学员总数：<span id="traineeCount">{{=isNullFormat(it.data.traineeCount)}}</span></li>
	                     <li>创建时间：<span> {{=dateFormat(it.data.courseMap.create_time)}}</span></li>
	                     <li>创建人：<span>{{=isNullFormat(it.data.courseMap.nick_name)}}</span></li>
	                     <li >所属分类：
						   
                           {{~ it.data.selectedClassificationList : v : index}}
								 <em>{{=v.classification_name}}</em>
						   {{~}}
	                     </li>
                        </ul>
                       
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>

            <ul class="nav nav-tabs pl20 set_tab" id="myTab" style="height: 38px;">
                <li class="active" style="height: 38px;"><a href="#jieshao1" style="height: 38px;">课程介绍</a></li>
                <li style="height: 38px;"><a href="#dagang1" style="height: 38px;">课程大纲</a></li>
                <!--li><a href="#pingjia">课程评价</a></li-->
            </ul>


			<div id="myTabContent" class="tab-content" style="min-height:0px;">
                <div class="pre1_con tab-pane active" id="jieshao1" style="min-height:211px;padding:15px;">
                <div class="tab_introduce"> 
				<h3 class="kc_title">
                    <span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程简介
                </h3>
                <div class="kc_des" title="{{=isNullFormat(it.data.courseMap.course_description)}}">{{=subString(isNullFormat(it.data.courseMap.course_description),100)}}</div>
                </div>
				<div class="tab_introduce"> 
				<h3 class="kc_title">
                    <span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程目标
                </h3>
                <div class="kc_des" title="{{=isNullFormat(it.data.courseMap.course_objective)}}">{{=subString(isNullFormat(it.data.courseMap.course_objective),100)}}</div>
                 </div>
				<div class="tab_introduce"> 
				<h3 class="kc_title">
                    <span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程须知
                </h3>
                <div class="kc_des" title="{{=isNullFormat(it.data.courseMap.course_infomartion)}}">{{=subString(isNullFormat(it.data.courseMap.course_infomartion),100)}}</div>
				</div>
                </div>
                <div class="pre1_con tab-pane" id="dagang1" style="height: 215px;min-height: 215px;overflow-y: auto;">
                    <div class="discuss_box">
                        <div class="discuss_left" style="width: 100%;">
                            <h3 class="h3_title" style="border-top:1px solid #ddd;line-height: 38px;">目录</h3>
                            <ul class="contents_list">
								{{~ it.data.quoteList:v:index}}
                                <li>
                                    <span class="lession text-right">课时{{=index+1}}</span>
                                    <span class="round_b" style="background:#fff;"><em class="round_con"></em></span>
                                    <h4 class="h4_title">
										{{? v.item_type == 0}}<em class="green">【课程】</em>{{=v.item_name}}{{?}}
										{{? v.item_type == 1}}<em class="red">【测验】</em>{{=v.item_name}}{{?}}
										{{? v.item_type == 2}}<em class="red">【调查】</em>{{=v.item_name}}{{?}}
									</h4>
                                    <time class="pull-right"></time>
                                    <div class="line"></div>
                                </li>
								{{~}}
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="pre1_con tab-pane" id="pingjia">
                    <ul class="kcpj_list">
                        <li>
                            <div class="kcpj_list_pic"><img src="${ctx}/resources/img/courseman/kcgl_pic.png" alt=""></div>
                            <div class="kcpj_list_right">
                                <div class="row">
                                    <div class="col-md-12"><span class="l_title">学生名称：</span>用户名Jone</div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-12">
                                        <div class="conflex">
                                            <span class="l_title">评&emsp;&emsp;价：</span>
                                            <div>
                                                    美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="kcpj_list_pic"><img src="${ctx}/resources/img/courseman/kcgl_pic.png" alt=""></div>
                            <div class="kcpj_list_right">
                                <div class="row">
                                    <div class="col-md-12"><span class="l_title">学生名称：</span>用户名Jone</div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-12">
                                        <div class="conflex">
                                            <span class="l_title">评&emsp;&emsp;价：</span>
                                            <div>
                                                    美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="kcpj_list_pic"><img src="${ctx}/resources/img/courseman/kcgl_pic.png" alt=""></div>
                            <div class="kcpj_list_right">
                                <div class="row">
                                    <div class="col-md-12"><span class="l_title">学生名称：</span>用户名Jone</div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-12">
                                        <div class="conflex">
                                            <span class="l_title">评&emsp;&emsp;价：</span>
                                            <div>
                                                    美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
</script>

<script id="offline_detail_template" type="text/x-dot-template">

			<div class="p20">
                <div class="video">
					{{? it.data.courseMap.course_cover_img == ''}}
						<img src="${ctx}/resources/img/course/title_pic.png" alt="" width="300px">
					{{??}}
						<img src="${imgStatic}{{=isNullFormat(it.data.courseMap.attach_url)}}/{{=isNullFormat(it.data.courseMap.course_cover_img)}}" alt=""  width="300px" height="168.2px">
					{{?}}
                </div>
                <div class="video_r">
                    <h3 class="title pb10" title="{{=isNullFormat(it.data.courseMap.course_name)}}">{{=subString(isNullFormat(it.data.courseMap.course_name),15)}}</h3>

					 <div class="text-right" style="margin-top: -40px;margin-bottom: 18px; height: 29px;">
                            <!--<button type="button" class="btn btn_blue mr10" onclick ="onCourseOfflineSetting('{{=it.data.courseMap.id}}','2')">课程管理</button>-->
							{{? it.data.courseMap.is_publish != 1 && it.data.courseMap.create_id == userId}}
                        	<button type="button" class="btn_b btn_green " style="margin-top:-4px;" onclick="courseOfflinePublish('{{=it.data.courseMap.id}}','{{=it.data.courseMap.course_train_times}}','{{=it.data.courseMap.is_publish}}')">发布</button>
                      		{{?}}
							{{? it.data.courseMap.is_publish == 1}}
							<button type="button" class="btn_b btn_green" onclick="cancelCourseOfflinePublish('{{=it.data.courseMap.id}}');" id="cancelPublishBtn" style="margin-top:-4px;">取消发布</button>
							{{?}}
                        </div>

                    <div class="mt10">
                        <ul class="course_des">
                            <li>评分：<div 
									{{? it.data.courseMap.course_star == 2}} class="star2" {{?}}
									{{? it.data.courseMap.course_star == 3}} class="star3" {{?}}
									{{? it.data.courseMap.course_star == 4}} class="star4" {{?}}
									{{? it.data.courseMap.course_star == 5}} class="star5" {{??}}
									 class="star1" {{?}}
                              </div>
                            </li>
	                         <li>课程类型：<span>
								{{? it.data.courseMap.course_type == 1}}
	                           	线上课程
								{{?}}
	                       		{{? it.data.courseMap.course_type == 2}}
	                            线下课程
	                       		{{?}}
	                     		{{? it.data.courseMap.course_type == 3}}
	                            直播课程
	                      		{{?}}
	                       
	                     </span></li>
	                     <li>培训课时：<span>{{=isNullFormat(it.data.courseMap.course_train_times)}}</span></li>
	                     <li>培训起至时间：<span>
							
							{{? it.data.courseMap.start_time != '' && it.data.courseMap.expire_time != ''}}
								{{=dateFormat(it.data.courseMap.start_time)}} 至{{=dateFormat(it.data.courseMap.expire_time)}}
							{{?}}
							{{? it.data.courseMap.start_time == '' && it.data.courseMap.expire_time != ''}}
								_至{{=dateFormat(it.data.courseMap.expire_time)}}
							{{?}}
							{{? it.data.courseMap.start_time != '' && it.data.courseMap.expire_time == ''}}
								 {{=dateFormat(it.data.courseMap.start_time)}}至_
							{{?}}
							{{? it.data.courseMap.start_time == '' && it.data.courseMap.expire_time == ''}}
								未设置
							{{?}}
	                           </span>
	                     </li>
	                     <li>拟定人数：<span>{{=isNullFormat(it.data.courseMap.course_trainee_num)}}</span></li>
	                     <li>学员总数：<span>{{=it.data.traineeNum}}</span></li>
	                     <li>创建时间：<span> {{=dateFormat(it.data.courseMap.create_time)}}</span></li>
	                     <li>创建人：<span>{{=isNullFormat(it.data.courseMap.nick_name)}}</span></li>
	                     <li class="wb100">所属分类：
						   
                           {{~ it.data.selectedClassificationList : v : index}}
								 <em>{{=v.classification_name}}</em>
						   {{~}}
	                     </li>
                        </ul>
                       
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>

			<input type="hidden" value = "{{=it.data.courseMap.course_train_times}}" id = "courseTrainTimes"> 
			<input type="hidden" value = "{{=it.data.courseMap.id}}" id = "courseId">
			<input type="hidden" id = "stepNo">
			<input type="hidden" value ="{{=it.data.balanceNum}}" id ="balanceNum">
			<input type="hidden" value ="{{=it.data.traineeNum}}" id ="traineeNum">
			<input type="hidden" value ="{{=it.data.traineeSubstituteNum}}" id ="traineeSubstituteNum">
			<input type="hidden" value ="{{=it.data.courseMap.course_substitute_number}}" id ="courseSubstituteNumber">
			<input type="hidden" id = "itemIdValue" value="">
			<input type="hidden" value ="{{=it.data.courseMap.is_publish}}" id ="isPublish">
			<input type="hidden" value = "{{=it.data.courseMap.course_rule}}" id = "courseRule">
			<input type="hidden" value = "{{=it.data.courseMap.start_time}}" id = "trainStartTime">
			<input type="hidden" value = "{{=it.data.courseRegisteRule.expireTime}}" id = "enrollExpireTime">
			<input type="hidden" value="{{=it.data.courseMap.is_public}}" id="isPublic">

		<ul  id="myTab"  class="nav nav-tabs set_tab" role="tablist" style="padding-left: 0px; background-color: #e1eaee ! important;height: 38px;">
			<li class="active" role="presentation" style="height: 38px;">
				<a href="#jieshao1" role="tab" data-toggle="tab" style="height: 38px;">课程介绍</a></li>
			<li role="presentation" style="height: 38px;">
				<a href="#dagang" role="tab" data-toggle="tab" style="height: 38px;">课程大纲</a></li>
			<!--li role="presentation" style="height: 38px;">
				<a href="#pingjia" role="tab" data-toggle="tab" style="height: 38px;">课程评价</a></li>
			<li role="presentation" style="height: 38px;">
				<a href="#baomingshenpi" role="tab" data-toggle="tab" style="height: 38px;">报名设置及审批</a></li-->
		</ul>
		<div id="myTabContent" class="tab-content" style="min-height:0px;">
			<div class="pre1_con tab-pane active" id="jieshao1" style="min-height:206px;padding:5px;">
                <div class="tab_introduce"> 
				<h3 class="kc_title">
                    <span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程简介
                </h3>
                <div class="kc_des" title="{{=isNullFormat(it.data.courseMap.course_description)}}">{{=subString(isNullFormat(it.data.courseMap.course_description),100)}}</div>
                </div>
				<div class="tab_introduce"> 
				<h3 class="kc_title">
                    <span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程目标
                </h3>
                <div class="kc_des" title="{{=isNullFormat(it.data.courseMap.course_objective)}}">{{=subString(isNullFormat(it.data.courseMap.course_objective),100)}}</div>
                 </div>
				<div class="tab_introduce"> 
				<h3 class="kc_title">
                    <span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程须知
                </h3>
                <div class="kc_des" title="{{=isNullFormat(it.data.courseMap.course_infomartion)}}" >{{=subString(isNullFormat(it.data.courseMap.course_infomartion),100)}}</div>
				</div>
             </div>

			<div class="pre1_con tab-pane" id="dagang" style="height: 215px;min-height: 215px;overflow-y: auto;">
				<div class="dagang_box">

					{{ for (var index = 0; index < it.data.courseMap.course_train_times; index++) { }}
					<div class="discuss_box">
                        <div class="discuss_left mt20" style="width: 100%;">
						
                            <h3 class="h3_title" style="border-top:1px solid #ddd;line-height: 38px;">第{{=index+1}}次 {{~ it.data.dataList:v2:jndex}}
							{{? v2.item_step_no == index+1 && v2.item_type == 0 }} <em>培训时间：{{=dateFormat(v2.start_time)}} ~ {{=dateFormat(v2.expire_time)}}</em>  <em>培训地点：{{=isNullFormat(v2.location_name)}}</em>{{?}}{{~}}</h3>
							
                            <ul class="contents_list" style="min-height: 150px;">
                                {{var step=1;}}
                                {{~ it.data.dataList:v2:zndex}}
								{{? v2.item_step_no == index+1 && v2.item_type == 1}}
								<li>
									<span class="lession text-right">活动{{=step}}</span>
                                    <span class="round_b" style="background:#fff;"><em class="round_con"></em></span>
                                    <h4 class="h4_title"> <a href="#">
										
										<em class="green">【事件】</em>{{=isNullFormat(v2.item_train_name)}}
										
									</a></h4>
                                    <time class="pull-right">{{=dateFormat1(v2.start_time)}} - {{=dateFormat1(v2.expire_time)}}</time>
									
                                    <div class="line"></div>
                                  {{step=step+1;}}
                                </li>{{?}}{{~}}
                            </ul>
                        </div>
                    </div>

					{{ } }}
				</div>

			</div>

			<div class="pre1_con tab-pane" id="baomingshenpi">
				
				<div class="dagang_box">
					<!-- 次数列表  start-->
					<!-- 次数列表  end-->
					<div class="right_cont">
						<!-- a 上下翻页  start-->
						<!-- a 上下翻页  end-->
						<div class="dg_title_b">
							<h5 class="pull-left">
								<span class="current_step_no">第一次：</span><em></em>
							</h5>
							<div class="pxshijian">
							</div>
							<div class="clearfix"></div>
						</div>
						<div>
						<div class="select_b mt10">
							<span>报名方式：</span>
							<div class="w80 ">
								<select name="registeType" id="registeType" style="width: 60px" onchange="loadCourseOfflineTraineeList()">
									<option value="">----全部----</option>
									<option value="1">线上</option>
									<option value="2">线下</option>
								</select>
							</div>
							<span>审批意见：</span>
							<div class="w80 ">
								<select name="traineeStatus" id="traineeStatus" style="width: 60px" onchange="loadCourseOfflineTraineeList()">
									<option value="">----全部----</option>
									<option value="0">通过</option>
									<option value="1">拒绝</option>
									<option value="2">替补</option>
									<option value="3">待审核</option>
								</select>
							</div>
							<span>上课情况：</span>
							<div class="w80 ">
								<select name="joinTime" id="joinTime" style="width: 60px">
									<option value="">----全部----</option>
									<option value="0">未开始</option>
									<option value="1">正常</option>
									<option value="2">退课</option>
								</select>
							</div>

							<!-- <div class="pull-right" style="width: 230px;margin-top: 0px;"> -->

							<div class="pull-right" style="width: 323px;margin-top: -12px;">
							    
							    <input id="uploadExcel" name="uploadExcel" class="user-card pull-left" type="file"></input>

							    <button type="button" class="btn c_blue" onClick="downloadTemplate()">下载模板</button>
								<button type="button" class="btn c_green" onClick="manageTrainee('active');">通过</button>
								<button type="button" class="btn c_red" onClick="manageTrainee('suspend')">拒绝</button>
								<button type="button" class="btn c_blue" onClick="manageTrainee('substitute');">替补</button>
								<!-- <button type="button" class="btn c_blue" onClick="manageTrainee('suspend');">拒绝</button>
								<button type="button" class="btn c_red" onClick="manageTrainee('delete')">Remove</button> -->
							</div>
						</div>
						<table cellpadding="0" cellspacing="0"
							class="table table-striped table-hover td_h60 mt10">
							<thead>
								<tr>
									<th style="width: 8%;" class="text-center">
										<div class="checkbox-group">
											<input type="checkbox" name="allRelatedIdCouserUser" id="allRelatedIdCouserUser"> <label
												class="checkbox-2" for="allRelatedIdCouserUser"></label>
										</div>
									</th>
									<th class="text-center" style="width: 15%;">用户昵称</th>
									<th class="text-center" style="width: 13p%;">姓名</th>
									<th class="text-center" style="width: 15%;">电子邮件</th>
									<th class="text-center" style="width: 12%;">报名方式</th>
									<th class="text-center">审批意见</th>
									<th class="text-center" >上课情况</th>
								</tr>
							</thead>
							<tbody id="courseOfflineTraineeList">
							</tbody>
						</table>
						<!-- 分页 -->
						<div class="mt20 text-center" id="course_offline_trainee_data_page_Panel">
						</div>
						</div>
					</div>
				</div>

			</div>


		</div>


</script>


<script type="text/javascript">
	require(['modules/course/course_dialogue'],function(){
			
	});
</script>
<style>

</style>