<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content p_border p20">
		<div  style="margin-left: 0px; z-index: 1000; position: fixed; top: 68px; margin-left: 0px; width: 222px; right: 1071.5px;">
			<div class="user_pic dropdown">
				<img class="user_pic"
					src="<c:if test="${empty groupModel.coverImg}">${ctx}/resources/img/account/homepaage_movie_travel.jpg</c:if><c:if test="${not empty groupModel.coverImg}"> ${groupModel.coverImg}</c:if>"
					id="groupIconPic" />
			</div>
			<ul class="left_menu" id="accountMenu"></br>
				<li class="active"><span id="groupName">小组名称：${groupModel.groupName}</span></li></br>
				<li><span id="groupBreif">小组简介：${groupModel.groupDescription}</span></li></br>
				<li><span>小组成员：<select id="groupMember">
							<c:if test="${not empty groupUserList}">
								<c:forEach items="${groupUserList}" var="user">
									<option value="${user.id}">${user.user_name}</option>
								</c:forEach>
							</c:if>
					</select></span></li>
			</ul>
		</div>
		<div style="width: 960px; float: right;">
				<ul class="neirong ml12" id="groupCourseContent">
				</ul>
		</div>
		<div class="clearfix"></div>
	</div>
	<input type="hidden" id="groupId" value="${groupId}">
	<input type="hidden" id="hid_default_course_online_page" value="0">
	<%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<!-- 弹出区域 -->
	<script id="classification_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<li id="{{=isNullFormat(v.id)}}"  onclick="javascript:detailGroupCourse('{{=isNullFormat(v.id)}}','{{=isNullFormat(v.course_type)}}')" {{? index == 0}} class="item_select"{{?}}>
		<div class="pic mb20">
		{{? !v.course_cover_img}}
		<img src="${ctx}/resources/img/course/p_pic.jpg" alt="">
		{{??}}
		<img src="${imgStatic}{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.course_cover_img)}}"   alt="">		
		{{?}}
		</div>
		<div class="mt10 wid-100">
			<dl>
				<dt>课程名称：</dt>
				<dd style="width: 195px;font-size:12px;">{{=isNullFormat(v.course_name)}}</dd>
			</dl>
		</div>
		<div class="mt10 f12">
			<dl>
				<dt>引用数：</dt>
				<dd>42?</dd>
			</dl>
			<dl>
				<dt>创建人：</dt>
				<dd>
					<a href="" class="mr20">{{=isNullFormat(v.nick_name)}}</a>
				</dd>
			</dl>
		</div>
		<div class="mt10 wid-100 f12">

			<dl>
				<dt>创建时间：</dt>
				<dd>{{=dateFormat(v.create_time)}}</dd>
			</dl>
		</div>
		<div class="mt10 wid-100 f12">

			<dl>
				<dt style="vertical-align: top;">简介：</dt>
				<dd class="neirong-max-height" style="width: 195px;">{{=subString(removeHtmlTags(isNullFormat(v.course_description)),30)}}</dd>
			</dl>
		</div>
		<div class="neirong-bottom">
			{{?v.is_publish == 1}}
			<button type="button" class="btn btn_green mr10">已发布</button>
			{{??}}
			<button type="button" class="btn btn_red mr10" >可编辑</button>
			{{?}}
			<button type="button" class="btn btn_gray" onclick="javascript:onDetail('{{=v.id}}')">简介</button>
		</div> <!--neirong-bottom end-->
	</li>

	{{? index == 0}}
		<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
	{{?}}	
{{~}}	
</script>
	<script type="text/javascript">
		require([ 'modules/group/group_intro' ], function(obj) {
		});
	</script>
</body>
</html>