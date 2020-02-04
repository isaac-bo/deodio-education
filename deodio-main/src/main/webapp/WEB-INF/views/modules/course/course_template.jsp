<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <button type="button" onclick="removeCourseContent(this);" class="del26 pull-right"></button> -->

<!-- presentation模板 -->				
<script id="course_presetation_template" type="text/x-dot-template">								
		<div class="box_pre p10 mt10 course_content" qtype="0">
            	<div class="edit_title">
					<span class="tip pull-left content-order">3</span>
					<div class="title" style="display: inline-block;">
						<a   title="{{=it.data.name}}">章节名称: {{=subString(it.data.name,10)}}</a> 

						<button type="button" class="del26" onclick="removeQuestion(this);">
						</button>
					</div>

					<input value="{{=it.data.itemId}}" type="hidden"  name="itemId">
					<input value="{{=it.data.itemName}}" type="hidden"  name="itemName">
					<input value="{{=it.data.itemType}}" type="hidden"  name="itemType">
				</div>
                <div class="nr_pic pull-left">
					{{? isNullFormat(it.data.course_cover_img) == ''}}
						<img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="" width="200" height="112">
					{{??}}
						<img src="{{=isNullFormat(it.data.course_cover_img)}}" alt="" width="200" height="112">
					{{?}}				</div>
                <div class="nr_des pull-left ml20">
                    <p class="mt10">{{=it.data.itemDesc}}</p>
                </div>
                	<div class="nr_sz pull-right pl20" style="display:none;">
							<div class="goto" id="{{=it.data.uuid}}_1">
                        		<span class="pr10 pull-left" style="margin-top: 7px;">&gt;=</span>
                        		<input type="text" class="form-control w50 pull-left" name="gtThreshold">
                       			<span class="pl10 pr10 pull-left" style="margin-top: 7px;">go to</span>
                        		<select name="" id="{{=it.data.uuid}}_1_select_1" class="pull-left selectpicker">
                             		<option value="">1</option>
                             		<option value="">2</option>
                             		<option value="">3</option>
                        		</select>
                    		</div>
                    </div>
                </div>
            </div>	
</script>		

<!-- 试题模板-->
<script id="course_examination_template" type="text/x-dot-template">
<div class="box_pre p10 mt10 course_content" qtype="1">
            	<div class="edit_title">
					<span class="tip pull-left content-order" style="background-color:#f06159;">2</span>			
					<div class="title" style="display: inline-block;">
						<a title="{{=it.data.name}}">测试名称：{{=subString(it.data.name,10)}}</a> 
						<button type="button" class="del26" onclick="removeQuestion(this);">
						</button>
					</div>
					<input value="{{=it.data.itemId}}" type="hidden"  name="itemId">
					<input value="{{=it.data.itemName}}" type="hidden"  name="itemName">
					<input value="{{=it.data.itemType}}" type="hidden"  name="itemType">
				</div>
                <div class="nr_pic pull-left">
					{{? isNullFormat(it.data.course_cover_img) == ''}}
						<img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="" width="200" height="112">
					{{??}}
						<img src="{{=isNullFormat(it.data.course_cover_img)}}" alt="" width="200" height="112">
					{{?}}
				</div>
                <div class="nr_des pull-left ml20">
                    <p class="mt10">{{=it.data.itemDesc}}</p>
                </div>
                <div class="nr_sz pull-right pl20">
                     <div class="goto" id="{{=it.data.uuid}}_1" style="display:none;">
                        <span class="pr10 pull-left" style="margin-top: 7px;">&gt;=</span>
                        <input type="text" class="form-control w50 pull-left" name="gtThreshold">
                        <span class="pl10 pr10 pull-left" style="margin-top: 7px;">go to</span>
                        <select name="" id="{{=it.data.uuid}}_1_select_1" class="pull-left selectpicker">
                             <option value="">1</option>
                             <option value="">2</option>
                             <option value="">3</option>
                        </select>
                    </div>
                    
                    <div class="goto goto2" id="{{=it.data.uuid}}_2" style="display:none;">
                        <span class="pr10 pull-left" style="margin-top: 7px;">&lt;=</span>
                        <input type="text" class="form-control w50 pull-left" name="ltThreshold">
                        <span class="pl10 pr10 pull-left" style="margin-top: 7px;">go to</span>
                        <select name="" id="{{=it.data.uuid}}_2_select_2" class="pull-left selectpicker">
                             <option value="">1</option>
                             <option value="">2</option>
                             <option value="">3</option>
                        </select>
                    </div>
                </div>
            </div>

</script>

<!-- 问卷模板 -->				
<script id="course_survey_template" type="text/x-dot-template">								
		<div class="box_pre p10 mt10 course_content" qtype="2">
            	<div class="edit_title">
					<span class="tip pull-left content-order" style="background-color:#a6c3cd;">3</span>
					<div class="title" style="display: inline-block;">
						<a title="{{=it.data.name}}">调查名称: {{=subString(it.data.name,10)}}</a> 
						<button type="button" class="del26" onclick="removeQuestion(this);">
						</button>
					</div>
					<input value="{{=it.data.itemId}}" type="hidden"  name="itemId">
					<input value="{{=it.data.itemName}}" type="hidden"  name="itemName">
					<input value="{{=it.data.itemType}}" type="hidden"  name="itemType" id="hiddenSurveyItemType">
					<input value="{{=it.data.course_cover_img}}" type="hidden"  name="courseCoverImg" id="hiddenSurveyItemCourseCoverImg">
					<input value="{{=it.data.attach_url}}" type="hidden"  name="attachUrl" id="hiddenSurveyItemAttachUrl">
					<input value="{{=it.data.generate_name}}" type="hidden"  name="generateName" id="hiddenSurveyItemGenerateName">
					
				</div>
                <div class="nr_pic pull-left">
					{{? isNullFormat(it.data.course_cover_img) == ''}}
						<img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="" width="200" height="112">
					{{??}}
						<img src="{{=isNullFormat(it.data.course_cover_img)}}" alt="" width="200" height="112">
					{{?}}
				</div>
                <div class="nr_des pull-left ml20">
                    <p class="mt10">{{=it.data.itemDesc}}</p>
                </div>
                <div class="nr_sz pull-right pl20">
					
                    </div>
                </div>
            </div>	
</script>		


<!-- tab pane  数据模板 -->				
<script id="course_presentation_tab_pane_template" type="text/x-dot-template">
 {{~it.data:v:index}}
 <div class="nr_r_box mt10 pt10 pb10 pl10 pr10 content-resource" id="course_presentation_{{=v.id}}">
 	<div class="pull-left">
		{{? isNullFormat(v.attach_url) == ''}}
			<img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="" width="145" height="85">
		{{??}}
			<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="" width="145" height="85">
		{{?}}
	</div>
    <div class="r_des pull-left ml10">
         <h4 name="courseName"  title="{{=isNullFormat(v.presentation_name)}}">章节名称：{{=subString(isNullFormat(v.presentation_name),5)}}</h4>
		 <p class="mt10" style="color: #888;font-size: 12px;" title="{{? isNullFormat(v.presentation_model) == 0}}
				Scrom标准课件
			{{?? isNullFormat(v.presentation_model) == 1}}
				压缩文件包课件
			{{?? isNullFormat(v.presentation_model) == 2}}
				自定义课件
			{{?? isNullFormat(v.presentation_model) == 3}}
				超链接课件
			{{?}}" >章节类型：
			
			{{? isNullFormat(v.presentation_model) == 0}}
				Scrom标准课件
			{{?? isNullFormat(v.presentation_model) == 1}}
				压缩文件包课件
			{{?? isNullFormat(v.presentation_model) == 2}}
				自定义课件
			{{?? isNullFormat(v.presentation_model) == 3}}
				超链接课件
			{{?}}
		</p>
         <p class="" style="color: #888;font-size: 12px;" name="itemDesc" title="{{=removeHtmlTags(isNullFormat(v.presentation_desc))}}">章节描述：{{=subString(removeHtmlTags(isNullFormat(v.presentation_desc)),5)}}</p>
		<input value="{{=v.id}}" type="hidden"  name="itemId">
		<input value="{{=v.presentation_name}}" type="hidden"  name="itemName">
    </div>
    <div class="clearfix"></div>
 </div>
 {{~}}								
</script>	

<script id="course_quiz_exmination_tab_pane_template" type="text/x-dot-template">
 {{~it.data:v:index}}
 <div class="nr_r_box mt10 pt10 pb10 pl10 pr10 content-resource" id="course_presentation_{{=v.id}}">
 		<div class="pull-left">
		{{? isNullFormat(v.attachUrl) == ''}}
			<img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="" width="145" height="85">
			{{??}}
			<img src="${imgStatic}/{{=isNullFormat(v.attachUrl)}}{{=isNullFormat(v.generateName)}}" alt="" width="145" height="85">
			{{?}}
		</div>
    	<div class="r_des pull-left ml10">
         	<h4 name="courseName" title="{{=isNullFormat(v.quizName)}}">测验名称：{{=subString(isNullFormat(v.quizName),5)}}</h4>
			<p class="mt10" style="color: #888;font-size: 12px;" title="{{?v.createType ==1}}
					手动创建
				{{??}}
					自动创建
				{{?}}">测验类型：
				
				{{?v.createType ==1}}
					手动创建
				{{??}}
					自动创建
				{{?}}
			</p>

         	<p class="" name="itemDesc" style="color: #888;font-size: 12px; "title="{{=removeHtmlTags(isNullFormat(v.quizDesc))}}">测验描述：{{=subString(removeHtmlTags(isNullFormat(v.quizDesc)),5)}}</p>

			<input value="{{=v.id}}" type="hidden"  name="itemId">
			<input value="{{=v.quizName}}" type="hidden"  name="itemName">
    	</div>
    <div class="clearfix"></div>
 </div>
 {{~}}								
</script>

<script id="course_survey_tab_pane_template" type="text/x-dot-template">
 {{~it.data:v:index}}
 <div class="nr_r_box mt10 pt10 pb10 pl10 pr10 content-resource" id="course_presentation_{{=v.id}}">
 	<div class="pull-left">
		{{? isNullFormat(v.attach_url) == ''}}
			<img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="" width="145" height="85">
		{{??}}
			<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="" width="145" height="85">

		{{?}}
	</div>
    <div class="r_des pull-left ml10">
         <h4 name="courseName" title="{{=isNullFormat(v.survey_name)}}" >调查名称：{{=subString(isNullFormat(v.survey_name),5)}}</h4>
         <p class="mt10" name="itemDesc" title="{{=removeHtmlTags(isNullFormat(v.survey_desc))}}">调查描述：{{=subString(removeHtmlTags(isNullFormat(v.survey_desc)),5)}}</p>
		<input value="{{=v.id}}" type="hidden"  name="itemId">
		<input value="{{=v.survey_name}}" type="hidden"  name="itemName">
    </div>

    <div class="clearfix"></div>
 </div>
 {{~}}								
</script>		


<script id="course_package_course_tab_pane_template" type="text/x-dot-template">
 {{~it.data:v:index}}
 <div class="nr_r_box mt10 pt10 pb10 pl10 pr10 content-resource">
 	<div class="pull-left"><img src="${ctx}/resources/img/course/pic.png" alt=""></div>
    	<div class="r_des pull-left ml10">
         	<h4 name="courseName">{{=isNullFormat(v.course_name)}}</h4>
         	<p class="mt10" name="courseDesc" style="color: #888;font-size: 12px;">{{=isNullFormat(v.course_description)}}</p>
			<input value="{{=v.id}}" type="hidden"  name="courseId">
    	</div>
    <div class="clearfix"></div>
 </div>
 {{~}}								
</script>	

<!-- 课程包课程列表 -->
<script id="course_package_course_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<div class="checkbox-group">
				<input type="checkbox" name="relatedIdCheckBox" id="{{=isNullFormat(v.id)}}" onclick="isSelectAll('allRelatedId','relatedIdCheckBox');"> 
				<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
			</div>
		</td>
		<td class="text-right">{{=isNullFormat(v.course_id)}}</td>
		<td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
		<td class="text-center">{{=isNullFormat(v.user_mail)}}</td>
		<td class="text-center">{{?v.registe_type == 1}}线上报名{{??}}前台报名{{?}}</td>
		<td class="text-center">{{?v.user_status == 0}}ACTIVE {{??v.user_status == 1}}SUSPEND{{??}}其他{{?}}</td>
		<td class="text-center">{{=dateFormat(v.join_time)}}</td>
		<input type="hidden" name="relatedId" value="{{=isNullFormat(v.rel_id)}}">
	</tr>
{{~}}
</script>

<!-- course_setting 页面模板-->
<script id="table_data_template_quiz" type="text/x-dot-template">

 {{~it.data:v:index}}
		<tr>
			<td class="text-center">
				<div class="checkbox-group">
          				<input type="checkbox" name="quizBank" id="{{=isNullFormat(v.id)}}">
           				<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
        		</div>
			</td>
			<td>{{=isNullFormat(v.quiz_bank_name)}}</td>
			<td class="text-right"><button type="button" class="{{? v.is_private == 1}}btn_edit{{??}}btn_publish{{?}}">{{? v.is_private == 1}}私有{{??}}公有{{?}}</button></td>
			<td class="text-center">{{=isNullFormat(v.classification_name)}}</td>									
			<td class="text-right">231</td>
			<td class="text-center">
				<button type="button" class="icon_1" onclick="editQuizBanks('{{=isNullFormat(v.id)}}');"></button>
				<button type="button" class="icon_2" onclick="deleteQuizBanks('{{=isNullFormat(v.id)}}');"></button>
			</td>
			<td class="text-center"><button type="button" class="btn_act" onclick="manageQuizSubjectList('{{=isNullFormat(v.id)}}');">题目管理</button></td>
		</tr>
 {{~}}	

</script>

<!-- dagang_box left_lev 页面模板-->
<script id="dagang_box_left_lev_data_template" type="text/x-dot-template">
	<div class="left_lev">
		<ul class="num pull-left">
			{{~it.stepNo:s:index}}
				<li 
					{{? s.num == it.current}}
						class="active"
					{{?? it.current > index + 1 && it.current-index-1 <= 4}}
						class="c{{=it.current-index -1}}"	
					{{?? it.current < index + 1 && index + 1-it.current <= 4}}
						class="c{{=index + 1-it.current}}"	
					{{??}}
						style="opacity:0"
					{{?}}
					>{{= s.chinese}}
				</li>
			{{~}}
		</ul>
		<div class="line_box pull-left"></div>
		<div class="clearfix"></div>
	</div>	
</script>
<!-- dagang_box left_lev 页面模板-->
<script id="dagang_box_center_con_data_template" type="text/x-dot-template">
		<div class="center_con">
			<div class="line"></div>
			<div class="pre">
				{{? it.nex < it.totalStep + 1}}
				<a href="javascript:{{=it.dataFun}}({{=it.nex}},{{=it.current}});"><span class=""></span></a>
				{{?}}
			</div>
			<div class="num_bg num_bg_pre" style="top: -17px; display:none;">{{=it.current - 1}}</div>
			<div class="num_bg num_bg_cur">{{=it.current}}</div>
			<div class="num_bg num_bg_nex" style="bottom: -4px;display:none;">{{=it.current + 1}}</div>
			<div class="nex">
				{{? it.pre > 0}}
				<a href="javascript:{{=it.dataFun}}({{=it.pre}},{{=it.current}});"><span class=""></span></a>
				{{?}}
			</div>
		</div>
</script>				

<!-- dagang_box left_lev 页面模板-->
<script id="course_offline_strainee_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		<td class="text-center">
			{{?v.user_status == 3}}<div class="checkbox-group">
				<input type="checkbox" name="relatedIdCheckBoxCouserUser" id="{{=isNullFormat(v.rel_id)}}" onclick="isSelectAll('allRelatedId','relatedIdCheckBoxCouserUser');"> 
				<label class="checkbox-2" for="{{=isNullFormat(v.rel_id)}}"></label>
			</div>{{?}}
		</td>
		<td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
		<td class="text-center">{{=isNullFormat(v.user_name)}}</td>
		<td class="text-center">{{=isNullFormat(v.user_mail)}}</td>
		<td class="text-center">{{?v.registe_type == 1}}线上报名{{??}}线下报名{{?}}</td>
		<td class="text-center">{{?v.user_status == 0}}通过 {{??v.user_status == 1}}拒绝{{??v.user_status == 3}}待审核{{??v.user_status == 2}}替补{{?}}</td>
		<td class="text-center">{{?v.user_status == 0}}{{=validateStartDate()}}{{??v.class_status==2}}退课{{??}}未开始{{?}}</td>
		<input type="hidden" name="relatedId" value="{{=isNullFormat(v.rel_id)}}">
	</tr>
{{~}}
</script>

<!-- dagang_box pxshijian 页面模板-->
	<script id="dagang_box_pxshijian_data_template_1" type="text/x-dot-template">
		<!--span class="ml20">培训时间：</span-->
		<div class="w100 ml20 error-div" style="margin-right:3px;">
			<input type="text" class="form-control form_datetime min_day" id="stepStartTime" readonly name="startTime" value ="{{=dateFormat(it.start_time)}}" check-type="dateYmd">
		</div>
		<button type="button" class="shijian_btn"></button>
		<div class="line50"></div>
		<div class="w100 error-div" style="margin-right:3px;">
			<input type="text" class="form-control form_datetime min_day" id="stepExpireTime" readonly name="expireTime" value ="{{=dateFormat(it.expire_time)}}" check-type="dateYmd">
		</div>
		<button type="button" class="shijian_btn"></button>
		<span class="ml20">培训地点：</span>
		<div class="w260" error-div >

			<select name="trainLocation" id="trainLocation" style="width: 100%;" check-type="required">
				{{~it.locationList:lo:index}}
				<option value="{{=isNullFormat(lo.id)}}"   {{? it.item_train_location == lo.id}} selected {{?}}>{{=isNullFormat(lo.location_name)}}</option>
				{{~}}
			</select>
		</div>
        <span class="ml20">制定人数：</span>
        <div class="error-div" style="width: 80px;height: 34px;">
        <input type="text"  class="form-control" id="trainNumber"  name="trainNumber" value ="{{=isNullFormat(it.trainee_join_num)}}" check-type="intValidate">
        </div>
        <span class="ml20"></span>
        <div style="width: 90px;height: 34px;">
        <button class="btn submit btn_160_36" style="width:80px" type="button" btn-type='true' >提交</button>
        </div>
		<input type="hidden" name="itemType" value="0">
		<input type="hidden" name="operateType" value="1">
        <input type="hidden" name="itemId" value="{{=isNullFormat(it.id)}}">
	</script>		
	<!-- dagang_box pxshijian 页面模板-->
	<script id="dagang_box_pxshijian_data_template" type="text/x-dot-template">
		<span class="ml20">培训时间：</span>
		<div class="w100 ml20 error-div" >
			<input type="text" class="form-control form_datetime min_day" id="stepStartTime" readonly name="startTime" value ="{{=dateFormat(it.start_time)}}" check-type="dateYmd">
		</div>
		<button type="button" class="shijian_btn"></button>
		<div class="line50"></div>
		<div class="w100 error-div">
			<input type="text" class="form-control form_datetime min_day" id="stepExpireTime" readonly name="expireTime" value ="{{=dateFormat(it.expire_time)}}" check-type="dateYmd">
		</div>
		<button type="button" class="shijian_btn"></button>
		<span class="ml20">培训地点：</span>
		<div class="w260" error-div>
			<select name="trainLocation" id="trainLocation" style="width: 100%;" check-type="required">

				{{~it.locationList:lo:index}}
				<option value="{{=isNullFormat(lo.id)}}"   {{? it.item_train_location == lo.id}} selected {{?}}>{{=isNullFormat(lo.location_name)}}</option>
				{{~}}
			</select>
		</div>
        <span class="ml20" >制定人数：{{=isNullFormat(it.trainee_join_num)}}人</span>
		<button class="btn submit btn_160_36 pull-right submit-item" type="button" btn-type='true' >提交</button>
		<input type="hidden" name="itemType" value="0">
		<input type="hidden" name="operateType" value="1">
        <input type="hidden" name="itemId"  id="itemId" value="{{=isNullFormat(it.id)}}">
        <input type="hidden" id="trainNumber"  name="trainNumber" value="{{=isNullFormat(it.trainee_join_num)}}">
</script>	
	<!-- dagang_box sz_add 页面模板-->
	<script id="dagang_box_sz_add_data_template" type="text/x-dot-template">
	{{? it.data.length > 0 }}
		{{~it.data:v:index}}
	 	<div class="sz_add">
          <div class="w130 error-div"><input type="text" class="form-control form_datetime min_min" readonly name="startTime" value ="{{=dateFormat1(v.start_time)}}" check-type="required" required-message="请选择日期!" ></div>
          <button type="button" class="shijian_btn"></button>
          <div class="line50"></div>
          <div class="w130 error-div"><input type="text" class="form-control form_datetime min_min" readonly name="expireTime" value ="{{=dateFormat1(v.expire_time)}}" check-type="required" required-message="请选择日期!"></div>
          <button type="button" class="shijian_btn"></button>
          <div class="w260 ml20 error-div">
              <input type="text" class="form-control" name="trainName" value ="{{=isNullFormat(v.item_train_name)}}" check-type="required">
          </div>
          <div class="w210 ml20 error-div ">
             <select name="itemTraines" id="itemTraines" style="width:100%" multiple id = "{{=randomString()}}" check-type="required">
                  {{~it.trainerList:ts:i}}
					<option value="{{=isNullFormat(ts.id)}}"  {{? isSubStr(v.item_trainers,ts.id)}} selected {{?}}>{{=isNullFormat(ts.trainer_name)}}{{?ts.is_recommended==1}}(推荐){{?}}</option>
				  {{~}}
              </select>
          </div>
		  <input type="hidden" name="itemType" value="1">
		  <input type="hidden" name="operateType" value="1">
		  <input type="hidden" name="itemId" value="{{=v.id}}">
          <button type="button" class="btn_del_18 ml10" onClick="delRowData(this);"></button>
          <button type="button" class="btn_add_18 ml10" onClick="addRowData(this);"></button>
      	</div>
		{{~}}
	{{??}}
		<div class="sz_add">
          <div class="w130 error-div"><input type="text" class="form-control form_datetime min_min" readonly name="startTime" check-type="required" required-message="请选择日期!"></div>
          <button type="button" class="shijian_btn"></button>
          <div class="line50"></div>
          <div class="w130 error-div"><input type="text" class="form-control form_datetime min_min" readonly name="expireTime" check-type="required" required-message="请选择日期!"></div>
          <button type="button" class="shijian_btn"></button>
          <div class="w260 ml20 error-div">
              <input type="text" class="form-control" name="trainName" check-type="required">
          </div>
          <div class="w210 ml20">
              <select name="itemTraines" style="width:100%" multiple >
                  {{~it.trainerList:tr:index}}
                    {{?index==0}}
					<option value="{{=tr.id}}" selected>{{=tr.trainer_name}}{{?tr.is_recommended==1}}(推荐){{?}}</option>
                    {{??}}
                    <option value="{{=tr.id}}">{{=tr.trainer_name}}{{?tr.is_recommended==1}}(推荐){{?}}</option>
                    {{?}}
				  {{~}}
              </select>
          </div>
		  <input type="hidden" name="itemType" value="1">
		  <input type="hidden" name="operateType" value="0">
		  <input type="hidden" name="itemId" value="">
          <button type="button" class="btn_del_18 ml10" onClick="delRowData(this);"></button>
          <button type="button" class="btn_add_18 ml10" onClick="addRowData(this);"></button>
      	</div>
	{{?}}
	</script>
	
	<!-- dagang_box sz_add 页面模板-->
	<script id="course_offline_detail_dagang_data_template" type="text/x-dot-template">
 	{{~it.data:v:index}}
	<tr>
		<td class="text-center">{{=dateFormat1(v.start_time)}}</td>
		<td class="text-center">{{=isNullFormat(v.item_train_name)}}</td>
		<td class="text-center">
                  {{~it.param:ts:i}}
					{{? isSubStr(v.item_trainers,ts.id)}}{{=isNullFormat(ts.trainer_name)}}{{?}}
				  {{~}}
		</td>
	</tr>	
	{{~}}
	</script>		
	
	
	<!-- dagang_box left_lev 页面模板-->
<script id="group_course_to_select_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<div class="checkbox-group">
				<input type="checkbox" name="groupCourseRelatedIdCheckBox" id="{{=isNullFormat(v.course_id)}}" onclick="isSelectAll('allGroupCourseRelatedId','groupCourseRelatedIdCheckBox');"> 
				<label class="checkbox-2" for="{{=isNullFormat(v.course_id)}}"></label>
			</div>
		</td>
		<td class="text-right">{{=isNullFormat(v.course_name)}}</td>
		<td class="text-right">
			{{? v.course_type == '1'}}
				线上课程
			{{?? v.course_type == '2'}}
				线下课程
			{{?? v.course_type == '3'}}
				直播课程
			{{??}}
				课程包
			{{?}} 
		</td>
		<td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
		<td class="text-center">{{=dateFormat(v.create_time)}}</td>
		<input type="hidden" name="courseId" value="{{=isNullFormat(v.course_id)}}">
	</tr>
{{~}}
</script>
<style>
.nr_right .nr_r_box .r_des{width: 140px}
</style>
