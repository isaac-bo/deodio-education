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
		<div class="top_btn">
			<div class="control-bar gfl">
				<a onclick="smallIconShow(1)" href="javascript:void(0)" class="table-item on">
					<div class="" style="margin-top: 2px;"></div>
					<div class=""></div>
					<div class=""></div>
					<div class=""></div>
				</a>
				<a onclick="mediumIconShow(1);" href="javascript:void(0)"  class="pic-item">
					<div class=""></div>
					<div class=""></div>
					<div class=""></div>
					<div class=""></div>
				</a>
			</div>
			
			<div class="gfr">
			    <button type="button" class="btn2 btn-success" onclick="addGroupCourse();">添加课程</button>
				<!-- <button type="button" class="btn2 btn-success" onclick="addGroupCoursePackage();">添加课程包</button> -->
			</div>
		</div>
		
		<div id="smallIcon">
			<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
				<thead>
					<tr>
						<th width="25%" style="text-align:center;">课程名称</th>
						<th width="10%" style="text-align:center;">课程类型</th>
						<th width="20%" style="text-align:center;">来自</th>
						<th width="8%" style="text-align:center;">观看次数</th>
						<th width="10%" style="text-align:center;">评分</th>
						<th width="9%" style="text-align:center;">添加时间</th>
						<th width="10%" style="text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody id="table_panle">
				</tbody>
			</table>
		</div>
			
		<div id="mediumIcon" style="display:none;">
			<div lass="pre_right mr20 mt20" style="min-height:40px;">
				<ul class="course-list" id="content">
				</ul>
			</div>
			<div class="clearfix"></div>	
			<input type="hidden" id="hid_default_page" value="2">
		</div>
		
		<!-- 分页 -->
		<div class="mt20 text-center" id="data_page_Panel">
		</div>

		<input type="hidden" id="groupId" value="${groupId}"/>
		<!-- <div class="form_line2"></div> -->
		<!-- <div class="btn_box">
			<button class="btn submit  btn_160_36" type="button">提交</button>
			<button class="btn cancel  btn_160_36" type="button">取消</button>
		</div> -->

	</div>
</div>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/modules/course/course_template.jsp"%>
<%@ include file="/WEB-INF/views/modules/course/course_dialogue.jsp"%>

<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
		<tr>
			<td>{{=isNullFormat(v.course_name)}}</td>
			<td class="text-center">
				{{? isNullFormat(v.course_type) == 1}}
					线上课程
				{{?? isNullFormat(v.course_type) == 2}}
					线下课程
				{{?? isNullFormat(v.course_type) == 3}}
					直播课程
				{{?? isNullFormat(v.course_type) == 4}}
					课程包
				{{?}}
				
			</td>
			<td class="text-center" title="{{=isNullFormat(v.nick_name)}}">{{=subString(isNullFormat(v.nick_name),15)}}</td>
			<td class="text-center">37</td>
			<td class="text-center">
				{{? v.course_star == 2}}
					<div class="star2"></div>
				{{?? v.course_star == 3}}
					<div class="star3"></div>
				{{?? v.course_star == 4}}
					<div class="star4"></div>
				{{?? v.course_star == 5}}
					<div class="star5"></div>
				{{??}}
					<div class="star1"></div>
				{{?}}
			</td>		
			<td class="text-center">{{=dateFormat(v.create_time)}}</td>							
			<td class="text-center">
				<a onclick="delGroupCourse('{{=isNullFormat(v.rel_id)}}');" href="javascript:void(0)" class="icon_btn1"></a>
				<a onclick="detailGroupCourse('{{=isNullFormat(v.course_id)}}','{{=isNullFormat(v.course_type)}}');" href="javascript:void(0)" class="icon_btn2"></a>
			</td>
		</tr>
 {{~}}	

</script>

<script id="data_template" type="text/x-dot-template">
	{{~it.data:v:index}}
		<li>
			<div class="course-title-img">
				{{? isNullFormat(v.cover_img) != ''}}
					<img src="${imgStatic}{{=isNullFormat(v.cover_img)}}" alt=""  width="278">
				{{??}}
					<img src="${ctx}/resources/img/account/course-title-img-1.jpg" width="278">
				{{?}}
			</div>
			<div class="feature-tip 
				{{? isNullFormat(v.course_type) == 1}}
					feature-tip-online
				{{?? isNullFormat(v.course_type) == 2}}
					feature-tip-online
				{{?? isNullFormat(v.course_type) == 3}}
					feature-tip-online
				{{?? isNullFormat(v.course_type) == 4}}
					feature-tip-package
				{{?}}">
			</div>
			
			<div class="feature-icon" style="z-index:9;">
				{{? isNullFormat(v.course_type) == 1}}
					<span class="icon-desktop"></span>
				{{?? isNullFormat(v.course_type) == 2}}
					<span class="icon-coffee"></span>
				{{?? isNullFormat(v.course_type) == 3}}
					<span class="icon-facetime-video"></span>
				{{?? isNullFormat(v.course_type) == 4}}
					<span class="icon-gift"></span>
				{{?}}
			</div>

			<div class="course-text">
				<div class="mt10 wid-100">
					<dl>
						<dt>课程名称：</dt>
						<dd>{{=isNullFormat(v.course_name)}}</dd>
					</dl>
				</div>
				<div class="mt10 wid-100">
					<dl>
						<dt>课程评分：</dt>
						<dd>
							{{? isNullFormat(v.course_star) == 2}}
								<div class="star2"></div>
							{{?? isNullFormat(v.course_star) == 3}}
								<div class="star3"></div>
							{{?? isNullFormat(v.course_star) == 4}}
								<div class="star4"></div>
							{{?? isNullFormat(v.course_star) == 5}}
								<div class="star5"></div>
							{{??}}
								<div class="star1"></div>
							{{?}}
						</dd>
					</dl>
				</div>
				<div class="mt10 wid-100">
					<dl>
						<dt>简介：</dt>
						<dd class="neirong-max-height f12" style="height:60px;">{{=subString(removeHtmlTags(isNullFormat(v.course_desc)),30)}}</dd>
					</dl>
				</div>

				<div class="mt10 wid-100">
					<div class="detail50">课程来自：<span class="teacher-name-color" title="{{=isNullFormat(v.nick_name)}}">{{=subString(isNullFormat(v.nick_name),8)}}</span></div>
					<div class="detail50">观看次数：<span>37</span></div>
				</div>
				<div class="mt10 wid-100">
					<div class="detail50">添加时间：<span>{{=dateFormat(v.create_time)}}</span></div>
				</div>

				<div class="neirong-bottom" style="margin-top: 10px !important;">
					<button type="button" class="icon_2 mr10" onclick="delGroupCourse('{{=isNullFormat(v.rel_id)}}');"></button>
					<button type="button" class="icon_3 mr10" onclick="detailGroupCourse('{{=isNullFormat(v.course_id)}}','{{=isNullFormat(v.course_type)}}');"></button>
				</div> <!--neirong-bottom end-->
			</div>
		</li>
	{{~}}	
</script>

	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_features']);
	</script>
</body>
</html>