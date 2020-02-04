
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.main.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<input type="hidden" id="_item_name" name="_item_name" value=""/>
	<div class="content">
	<%@ include file="/WEB-INF/views/modules/quizs/quiz_top_menu.jsp"%>
	
		<div id="myTabContent" class="content tab-content">
			
			<div class="pre_center" style="position:absolute;width:975px;">
				<div class="con-mask"></div>
				<div class="con" style="min-height: 20px;">
					<div id="div_tables_bar" class="pre_left_top_fl" style="width:945px;">
						<div class="control-bar pull-left"> 
								<a onclick="mediumIconShow(1);" href="javascript:void(0)" class="pic-item on">
									<div class=""></div>
									<div class="on"></div>
									<div class=""></div>
									<div class=""></div>
								</a>
								
								<a onclick="smallIconShow(1)" href="javascript:void(0)" class="table-item">
									<div class="" style="margin-top: 2px;"></div>
									<div class=""></div>
									<div class=""></div>
									<div class=""></div>
								</a>
								
							</div>
						<div class="control-bar pull-right" id="smallIconConditionBar">
							<span>问卷调查名称</span>
							<div class="w240">
								<button type="button" class="btn-success btn36" onClick="searchSurveyList();">&nbsp;</button>
								<div class="search_input"><input type="text" class="form-control" id="keywords_small"></div>	
							</div>	
							<button type="button" value="上传试题" onclick="onCreateSurvey();" class="btn btn_green ml20 btn36">新建问卷调查</button>		
						</div>
					</div>
					<!--pre_left_top_fl end-->
					<div class="clearfix"></div>
					<div class="con-corner"></div>
				</div>
		   </div>
		
			<div class="tab-pane active" id="shijuan">
				<input id="hiddenSurveyId" type="hidden">
				<div id="smallIcon" style="margin-top: 60px;padding: 20px;display:none;">
						<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
							<thead>
								<tr>
									<th class="text-center" style="width:30%;">问卷调查名称</th>
									<th class="text-center">开始日期</th>
									<th class="text-center">结束日期</th>
									<th class="text-center">调查问题数</th>
									<th class="text-center">参与用户数</th>
									<th class="text-center">参与率%</th>
									<th class="text-center">创建人</th>
									<th class="text-center">创建时间</th>
									<th class="text-center">状态</th>
									<th class="text-center">操作</th>
								</tr>
							</thead>
							<tbody id="table_panel">
							</tbody>
						</table>
				</div>
				<div id="mediumIcon" style="margin-top: 75px;">
					<!-- <div class="pre_right p10" id="mediumLeftBar">
						<div>
							<button type="button" class="btn-success btn36" onClick="searchSurveyList();">&nbsp;</button>
							<div class="search_input"><input type="text" class="form-control" id="keywords_medium"></div>
				
						</div>
						<ul class="mt20 pre_t_btn">
							<li><button type="submit" class="add" onclick="create_exam()"></button></li>
							<li><button type="submit" class="del" onclick="deleteQuizExamination()"></button></li>
							<li><button type="submit" class="share"></button></li>
							<li><button type="submit" class="copy"></button></li>
							<li><button type="submit"class="menu"></button></li>
						</ul>
						<div class="pre_t_txt mt20 pt10">文字提示内容文字提示内容</div>
					</div> -->
					<div class="pre_left p20 pt0" style="padding:20px;">
						<div class="pre_left_top_fl" style="margin:0 auto;border-bottom:0;">
							<ul class="group_list neirong" id="content">
							</ul>
						</div>
					</div>
					<div class="pre_right mr20 mt20" style="margin-top: -75px !important;">
						<h3>点击以下按钮操作</h3>
						<div class="wb100 search">
							<button type="button" class="btn-success btn36" onClick="searchSurveyList();">&nbsp;</button>
							<div class="search_input">
								<input type="text" class="form-control" id="keywords_medium">
							</div>
							<div class="clearfix"></div>
						</div>
						<ul class="p10 pre_t_btn" id="ul_li_focus_events">
							
							<li><button style="background-color:#43b4c6" type="button" onClick="javascript:onCreateSurvey()"><span class="icon-file-alt"/></span></button></li>
							<li style="display: none" id="survey_edit"><button type="button" style="background-color:#ee625e;" onClick="javascript:onEditSurvey2()"><span class="icon-edit"/></span></button></li>
							<li style="display: none" id="survey_delete"><button type="button" style="background-color:rgb(113,174,145);"  onClick="javascript:onDeleteSurvey2()"><span class="icon-trash"/></span></button></li>
	<!-- 									<li><button type="button" style="background-color:rgb(101,170,221);" ><span class="icon-cogs"/></button></li>-->
							<li style="display: none" id="survey_share"><button type="button" style="background-color:#43b4c6;" onClick="javascript:onShareSurvey()"><span class="icon-share-alt"/></span></button></li>
							<li style="display: none" id="survey_copy"><button type="button" style="background-color:#ee625e;" onClick="javascript:onCopySurvey()"><span class="icon-copy"/></span></button></li>
							<li style="display: none" id="survey_preview"><button type="button" style="background-color:rgb(113,174,145);" data-toggle="modal" data-target="#surveyPreviewModal" ><span class="icon-eye-open"/></span></button></li>
							<li style="display: none" id="survey_quote"><button type="button" style="background-color:rgb(101,170,221);" onClick="javascript:onQuoteSurvey()"><span class="icon-sitemap"></span></button></li>
							<li style="display: none"><button type="button" style="background-color:#43b4c6;" ><span class="icon-dashboard" ></span></button></li>
							<li style="display: none" id="li_cancelPublish"><button type="button" style="background-color:rgb(101,170,221);" onclick="onCancelPublish()"><span class=" icon-paper-clip"></span></button></li>
							
						</ul>
						<div class="text" id="text_tips"></div>
			
					</div>
					<div class="clearfix"></div>	
					<input type="hidden" id="hid_default_page" value="1">
				</div>	
				<!-- 分页 -->
				<div class="mt20 text-center" id="data_page_Panel">
					
				</div>
		
		</div>
	</div>

</div>




<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
		<tr>
			<td>{{=isNullFormat(v.survey_name)}}</td>
			<td class="text-center">{{=dateFormat(v.start_time)}}</td>
			<td class="text-center">{{=dateFormat(v.end_time)}}</td>
			<td class="text-center">{{=isNullFormat(v.count_issue)}}</td>
			<td class="text-center">{{=isNullFormat(v.count_person)}}</td>	
			
			<td class="text-center">{{? v.count_all_user != 0}}{{=isNullFormat(v.count_person)/isNullFormat(v.count_all_user)}}％{{?}}
			{{? v.count_all_user == 0}}0％{{?}}</td>													
			<td class="text-center">{{=isNullFormat(v.user_name)}}</td>
			<td class="text-center">{{=dateFormat(v.create_time)}}</td>
			<td class="text-center">			
				{{? isNotStartTime(v.end_time)}}<button type="button" class="btn_publish">已结束</button>
				{{?? isNotStartTime(v.start_time)}}<button type="button" class="btn_publish">有效</button>
				{{??}} <button type="button" class="btn_publish">未开始</button>
				{{?}}
				<button type="button" {{? v.is_publish == 1}}class="btn btn_green">已发布 {{?? v.is_publish != 1}}class="btn btn_red">可编辑{{?}}</button></td>
			<td class="text-right" style="padding-top:12px;">
				{{? v.is_publish != 1}}<span  class="icon-edit mr5 f16" style="color:rgb(201,201,201)" onClick="javascript:onEditSurvey('{{=isNullFormat(v.id)}}')"></span>{{?}}
				{{? v.authority_user_id == v.create_id&& v.authority_user_is_edit==0}}<span class="icon-trash f16 mr5" style="color:rgb(201,201,201)" onClick="javascript:onDeleteSurvey('{{=isNullFormat(v.id)}}')"></span>{{?}}
				<span class="icon-eye-open f16 mr5" style="color:rgb(201,201,201)" onClick="javascript:onPreviewSurvey('{{=isNullFormat(v.id)}}')"></span>
				{{? v.is_publish ==1}}<span class="icon-paper-clip f16 mr5" style="color:rgb(201,201,201)" onClick="cancelPublishSurvey('{{=isNullFormat(v.id)}}','{{=isNullFormat(v.count_quote)}}')"></span>{{?}}
				
				<input type="hidden" id="_item_name" name="_item_name" value="{{=isNullFormat(v.survey_name)}}"/>
				<input type="hidden" id="_item_isPublish" name="_item_isPublish" value="{{=isNullFormat(v.is_publish)}}"/>	
				<input type="hidden" id="_item_owner" name="_item_owner" value="{{=isNullFormat(v.authority_user_id)}}"/>	
				<input type="hidden" id="_item_edit" name="_item_edit" value="{{=isNullFormat(v.authority_user_is_edit)}}"/>	
				<input type="hidden" id="_item_createId" name="_item_createId" value="{{=isNullFormat(v.create_id)}}"/>	
				<input type="hidden" id="_item_count_quote" name="_item_createId" value="{{=isNullFormat(v.count_quote)}}"/>
			</td>
		</tr>
 {{~}}	
{{~it.data:v:index}}
		{{? index == 0}}
			<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
			<input type="hidden" id="_item_name" name="_item_name" value="{{=isNullFormat(v.survey_name)}}"/>
			<input type="hidden" id="_item_isPublish" name="_item_isPublish" value="{{=isNullFormat(v.is_publish)}}"/>	
			<input type="hidden" id="_item_owner" name="_item_owner" value="{{=isNullFormat(v.authority_user_id)}}"/>	
			<input type="hidden" id="_item_edit" name="_item_edit" value="{{=isNullFormat(v.authority_user_is_edit)}}"/>	
			<input type="hidden" id="_item_createId" name="_item_createId" value="{{=isNullFormat(v.create_id)}}"/>	
			<input type="hidden" id="_item_count_quote" name="_item_createId" value="{{=isNullFormat(v.count_quote)}}"/>
		{{?}}	
	{{~}}


</script>

<script id="data_template" type="text/x-dot-template">
{{var currentPage=it.currentPage;}}
	{{~it.data:v:index}}

		<li id="{{=isNullFormat(v.id)}}" name="{{=isNullFormat(v.survey_name)}}" 
			onclick="javascript:onSelectSurvey('{{=isNullFormat(v.id)}}',
			'{{=isNullFormat(v.survey_name)}}','{{=isNullFormat(v.is_publish)}}',
			'{{=isNullFormat(v.authority_user_id)}}','{{=isNullFormat(v.authority_user_is_edit)}}',
			'{{=isNullFormat(v.create_id)}}','{{=isNullFormat(v.count_quote)}}')"
	 {{? index == 0 && !currentPage}} class="item_select"{{?}} >
		<div class="pic">
			{{? isNullFormat(v.attach_url) == ''}}
			<img src="${ctx}/resources/img/account/course-title-img-1.jpg" alt="">
			{{??}}
			<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="">
			{{?}}
		</div>
		<div class="mt10 wid-100">
			<dl class="mt10">
				<dt>问卷调查：</dt>
				<dd class="w200" title="{{=isNullFormat(v.survey_name)}}">{{=subString(isNullFormat(v.survey_name),14)}}</dd>
			</dl>
		</div>
		<div class="mt10 wid-100">

			<dl>
				<dt style="vertical-align: top;">简介：</dt>
				<dd class="neirong-max-height f12 w200" title="{{=removeHtmlTags(isNullFormat(v.survey_desc))}}">{{=subString(removeHtmlTags(isNullFormat(v.survey_desc)),30)}}</dd>
			</dl>
		</div>
		<div class="mt10 f12">
			<dl>
				<dt>调查问题：</dt>
				<dd>{{=isNullFormat(v.count_issue)}}</dd>
			</dl>
			<dl>
				<dt>参与用户：</dt>
				<dd>{{=isNullFormat(v.count_person)}}</dd>
			</dl>
		</div>
		<div class="mt10 f12">
			<dl>
				<dt>参与率%：</dt>
				<dd>{{? v.count_all_user != 0}}{{=isNullFormat(v.count_person)/isNullFormat(v.count_all_user)}}％{{?}}
			{{? v.count_all_user == 0}}0％{{?}}</dd>
			</dl>
			<dl>
				<dt>引用数：</dt>
				<dd>{{=isNullFormat(v.count_quote)}}</dd>
			</dl>
		</div>
		<div class="mt10 f12">

			<dl>
				<dt>开始日期：</dt>
				<dd>{{=dateFormat(v.start_time)}}</dd>
			</dl>
			<dl>
				<dt>结束时间：</dt>
				<dd>{{=dateFormat(v.end_time)}}</dd>
			</dl>
		</div>
		<div class="neirong-bottom">
			{{? v.authority_user_id==v.create_id&&v.is_publish != 1}}
				<button type="button" class="btn btn_unlock_icon">
					<span class="icon-unlock"></span>
				</button>
			{{?}}
			{{? v.authority_user_id!=v.create_id&&v.is_publish != 1}}
				<button type="button" class="btn btn_lock_icon" 
					onclick="onCancelShare('{{=isNullFormat(v.id)}}')">
					<span class="icon-lock"></span>
				</button>
			{{?}}
			{{? isNotStartTime(v.end_time)}}<button type="button" class="btn btn_publish">已结束</button>
				{{?? isNotStartTime(v.start_time)}}<button type="button" class="btn btn_publish">有效</button>
				{{??}} <button type="button" class="btn btn_publish">未开始</button>
				{{?}} 
			{{? v.is_publish != 1}}<button type="button" class="btn btn_red mr10">可编辑</button>{{?}}
			{{? v.is_publish == 1}}<button type="button" class="btn btn_green">已发布</button>{{?}}
		</div> <!--neirong-bottom end-->
	</li>
	{{~}}	


	{{~it.data:v:index}}
		{{? index == 0}}
			<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
			<input type="hidden" id="_item_name" name="_item_name" value="{{=isNullFormat(v.survey_name)}}"/>
			<input type="hidden" id="_item_isPublish" name="_item_isPublish" value="{{=isNullFormat(v.is_publish)}}"/>	
			<input type="hidden" id="_item_owner" name="_item_owner" value="{{=isNullFormat(v.authority_user_id)}}"/>	
			<input type="hidden" id="_item_edit" name="_item_edit" value="{{=isNullFormat(v.authority_user_is_edit)}}"/>	
			<input type="hidden" id="_item_createId" name="_item_createId" value="{{=isNullFormat(v.create_id)}}"/>	
			<input type="hidden" id="_item_count_quote" name="_item_createId" value="{{=isNullFormat(v.count_quote)}}"/>	
			
		{{?}}	
	{{~}}

</script>
<%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<%@ include file="/WEB-INF/views/modules/survey/survey_copy_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/survey/survey_quoted_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/survey/survey_share_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/survey/survey_preview_dialogue.jsp"%>

	<script type="text/javascript">
		require(['modules/quizs/quiz','modules/survey/survey_list']);
	</script>


</body>
</html>