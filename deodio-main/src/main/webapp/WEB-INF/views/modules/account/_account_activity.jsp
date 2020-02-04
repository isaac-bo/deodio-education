<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<ul class="nav nav-tabs set_tab" role="tablist" style="background-color:#FFFFFF ">
	<li role="presentation" class="active"><a href="#onlineCourese" role="tab" data-toggle="tab">我的在线课程</a></li>
	<li role="presentation"><a href="#course" role="tab" data-toggle="tab">我的线下课程</a></li>
	<li role="presentation"><a href="#onlineTest" role="tab" data-toggle="tab">我的在线考试</a></li>
	<li role="presentation"><a href="#test" role="tab" data-toggle="tab">我的调查问卷</a></li>
</ul>
<div class="tab-content">

	<div class="pull-right w240" style="position: relative; margin-top: -48px;margin-right: 10px;">
		<button type="button" onclick="searchItems();"  class="btn-success btn36">&nbsp;</button>
		<div class="search_input">
			<input type="text" id="items_keywords" class="form-control">
		</div>
		<div class="clearfix"></div>
	</div>

	<div role="tabpanel" class="tab-pane active" id="onlineCourese" style="padding:10px;">


		<table class="table table-striped table-hover td_h60">
			<thead>
				<tr>
					<th width="30%">课程名称</th>
					<th width="10%">进度</th>
					<th width="10%">创建者</th>
					<th width="10%">起始时间</th>
					<th width="10%">结束时间</th>
					<th width="10%">章节数</th>
					<th width="10%">测验数</th>
					<th width="10%">状态</th>
				</tr>
			</thead>
			<tbody id="online_course_table_panel">
			</tbody>
		</table>
		
		<div class="mt20 text_center" id="online_course_table_page_panle">
		  <ul class="pagination pagination-lg"></ul>
		</div>
	</div>
	<div role="tabpanel" class="tab-pane" id="course"  style="padding:10px;">


		<table class="table table-striped table-hover td_h60">
			<thead>
				<tr>
					<th width="30%">课程名称</th>
					<th width="10%">进度</th>
					<th width="10%">创建者</th>
					<th width="10%">起始时间</th>
					<th width="10%">结束时间</th>
					<th width="10%">章节数</th>
					<th width="10%">测验数</th>
					<th width="10%">状态</th>
				</tr>
			</thead>
			<tbody id="offline_course_table_panel">
			</tbody>
		</table>
		
		<div class="mt20 text_center" id="offline_course_table_page_panle">
		  <ul class="pagination pagination-lg"></ul>
		</div>
	</div>
	<div role="tabpanel" class="tab-pane" id="onlineTest"  style="padding:10px;">
		<table class="table table-striped table-hover td_h60">
			<thead>
				<tr>
					<th width="30%">试卷名称</th>
					<th width="18%">创建者</th>
					<th width="18%">试卷类型</th>
					<th width="18%">考试类型</th>
					<th width="10%">状态</th>
				</tr>
			</thead>
			<tbody id="quiz_table_panle">

			</tbody>
		</table>
		<div class="mt20 text_center" id="quiz_table_page_panle">
		  <ul class="pagination pagination-lg"></ul>
		</div>
	</div>
	<div role="tabpanel" class="tab-pane" id="test"  style="padding:10px;">
	   <table class="table table-striped table-hover td_h60">
			<thead>
				<tr>
					<th width="35%">问卷名称</th>
					<th width="15%">创建者</th>
					<th width="15%">起始时间</th>
					<th width="15%">结束时间</th>
					<th width="20%">状态</th>
				</tr>
			</thead>
			<tbody id="survey_table_panle">

			</tbody>
		</table>
		<div class="mt20 text_center" id="survey_table_page_panle">
		  <ul class="pagination pagination-lg"></ul>
		</div>
	</div>

</div>


<script id="online_course_table_data_template" type="text/x-dot-template">
			{{~it.data:v:index}}
				<tr>
					<td>{{=v.course_name}}</td>
					<td>
						<div class="progress">
							<div class="progress-bar" role="progressbar" aria-valuenow="60"
								aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
								<span class="sr-only">60%</span>
							</div>
						</div>
					</td>
					<td><span class="teacher-name-color">{{=v.nick_name}}</span></td>
					<td>{{=v.start_time}}</td>
					<td>{{=v.expire_time}}</td>
					<td>{{=v.presentation}}</td>
					<td>{{=v.examination}}</td>
					<td>{{?v.is_publish==0}}<button type="button" class="btn btn_green mr10 f12">可编辑</button>{{?}}
						{{?v.is_publish==1}}<button type="button" class="btn btn_red mr10 f12">已发布</button>{{?}}</td>
				</tr>
			{{~}}
</script>


<script id="offline_course_table_data_template" type="text/x-dot-template">
			{{~it.data:v:index}}
				<tr>
					<td>{{=v.course_name}}</td>
					<td>
						<div class="progress">
							<div class="progress-bar" role="progressbar" aria-valuenow="60"
								aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
								<span class="sr-only">60%</span>
							</div>
						</div>
					</td>
					<td><span class="teacher-name-color">{{=v.nick_name}}</span></td>
					<td>{{=v.start_time}}</td>
					<td>{{=v.expire_time}}</td>
					<td>{{=v.presentation}}</td>
					<td>{{=v.examination}}</td>
					<td>{{?v.is_publish==0}}<button type="button" class="btn btn_green mr10 f12">可编辑</button>{{?}}
						{{?v.is_publish==1}}<button type="button" class="btn btn_red mr10 f12">已发布</button>{{?}}</td>
				</tr>
			{{~}}
</script>

<script id="quiz_table_data_template" type="text/x-dot-template">
 	{{~it.data:v:index}}
		<tr>
			<td>{{=isNullFormat(v.quiz_name)}}</td>
            <td>{{=isNullFormat(v.nick_name)}}</td>
			<td>
				{{?v.create_type == 1}}手动创建{{?}}
				{{?v.create_type == 2}}自动创建{{?}}
			</td>
            <td>
				{{?v.quiz_category == 2}}综合试卷{{?}}
				{{?v.quiz_category == 3}}测验和试卷{{?}}</td>
          	<td>{{?v.is_publish==0 || isNullFormat(v.is_publish) == ''}}<button type="button" class="btn btn_green mr10 f12">可编辑</button>{{?}}
				{{?v.is_publish==1}}<button type="button" class="btn btn_red mr10 f12">已发布</button>{{?}}</td>
       </tr>
 {{~}}	
</script>

<script id="survey_table_data_template" type="text/x-dot-template">
 	{{~it.data:v:index}}
		<tr>
			<td>{{=isNullFormat(v.survey_name)}}</td>
            <td>{{=isNullFormat(v.username)}}</td>
			<td>{{=dateFormat(isNullFormat(v.start_time))}}</td>
            <td>{{=dateFormat(isNullFormat(v.end_time))}}</td>
           	<td class="text-center">{{? isNotStartTime(v.start_time)}}<button type="button" class="btn btn_blue mr10 f12">未开始</button>{{?? compareToDate(v.start_time,v.end_time)}}<button type="button" class="btn btn_red mr10 f12">已结束</button>{{?}} 
								    <button type="button" {{? v.is_publish == 1}}class="btn btn_red mr10 f12">已发布 {{?? v.is_publish != 1}}class="btn btn_green mr10 f12">可编辑{{?}}</button></td>
       </tr>
 {{~}}	
</script>

<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>

<script type="text/javascript">
require(['modules/account/_account_activity'],function(){
	
});
</script>