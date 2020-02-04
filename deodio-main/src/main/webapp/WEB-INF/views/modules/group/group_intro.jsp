<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.layer.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">
			   <div class="pre_right mr20">
		<h3>点击以下按钮操作</h3>
		<div class="wb100 search">
			<button type="button" class="btn-success btn36" onClick="fingGroupCourseListByName()">&nbsp;</button>
			<div class="search_input">
				<input type="text" class="form-control" id="courseNameKeyWord">
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	
<%-- 	<div class="pre_left">
			<div class="con-mask"></div>
			<div class="con">
				<div class="pre_left_top_fl">
					<div class="pre_left_top_fl_tittle">内容分类：</div>
					<div class="pre_left_top_fl_tab">
						<c:forEach items="${classificationsList}" var="item">
							<div class="pre_left_top_fl_main" style="cursor:pointer">
								<dl class="top-tabox">
									<dt>
										<span class="top-tabox-fl" id="${item.id}">${item.classification_name}</span>
										<span class="top-tabox-close">x</span>
										<span class="top-tabox-up"><img src="${ctx}/resources/img/up.png"></span>
									</dt>
									<dd>
									</dd>
								</dl>
							</div>
						</c:forEach>
					</div>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<ul class="fenlei">
					<li><span>内容标签：</span>
						<c:forEach items="${tagsList}" var="item">
							<button type="submit" class="bule-bnt" id="${item.id}" onclick = "onFilterByTags(this)">${item.tag_name}</button>
						</c:forEach>
					</li>
				</ul>
				<div class="con-corner"></div>
			</div>
			<div class="" id="classificationContent">
				<ul class="neirong ml12" id="classification_panle">
				</ul>
			</div>
			<div class="clearfix"></div>	
				<input type="hidden" id="hid_default_course_online_page" value="1">
		</div> --%>
		
<%-- 	
	<div class="mess_left pull-left set-ip-left" style="margin-left: 20px;min-height: 430px;">
		<div class="user_pic dropdown">
	        <img class="user_pic" style="border:2px solid #43b4c6" src="<c:if test="${empty groupModel.coverImg}">${ctx}/resources/img/account/homepaage_movie_travel.jpg</c:if><c:if test="${not empty groupModel.coverImg}"> ${groupModel.coverImg}</c:if>"  id="groupIconPic"/>
	      <c:if test="${roleAccountId == userRoleId||roleLeaderId == userRoleId}" ><!--非account不能修改小组图片-->
	        <button type="button" class="btn user_pic_edit" onclick="onGroupPicEdit();"><span></span><span></span><span></span></button>
	      </c:if>
	    </div>
	    
	     <ul class="left_menu_normal_2 pull-left" id="accountMenu" style="width: 100%;">
		     <li>
		     	<dl>
			     	<dt class="pull-left"><strong>小组名称：</strong></dt>
			     	<dd class="pull-left">${groupModel.groupName}</dd>
		     	</dl>
		     </li>
		     
		     <li style="min-height: 112px;">
		     	<dl>
		     		<dt class="pull-left"><strong>小组简介：</strong></dt>
			     	<dd class="pull-left mb20" style="width: 120px;color:#999;">${groupModel.groupDescription}</dd>
		     	</dl>
		     </li>
		     
		 </ul>
    </div> --%>
	
		<%-- <div  style="margin-left: 0px; z-index: 1000; position: fixed; top: 68px; margin-left: 0px; width: 222px; right: 1071.5px;">
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
		</div> --%>
		<div class="mess_right  mr20" style="width:972px ">
			<ul class="nav nav-tabs set_tab" role="tablist" style="background-color:#FFFFFF;margin-top: -20px;margin-left: -20px;width: 963px; ">
				<li role="presentation" class="active"><a href="javascript:void(0)" onclick="loadGroupCourseList(1)" role="tab" data-toggle="tab">线上课程</a></li>
				<li role="presentation" ><a href="javascript:void(0)" onclick="loadGroupCourseList(2)" role="tab" data-toggle="tab">线下课程</a></li>
				<li role="presentation" ><a href="javascript:void(0)" onclick="loadGroupCourseList(0)" role="tab" data-toggle="tab">课程包</a></li>
			</ul>
			<ul class="neirong" id="groupCourseContent">
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
				<dt>创建时间：</dt>
				<dd>{{=dateFormat(v.create_time)}}</dd>
			</dl>
			<dl>
				<dt>创建人：</dt>
				<dd>
					<a href="" class="mr20" title="{{=isNullFormat(v.nick_name)}}">{{=subString(isNullFormat(v.nick_name),4)}}</a>
				</dd>
			</dl>
		</div>
		
		<div class="mt10 wid-100 f12">

			<dl>
				<dt style="vertical-align: top;">简介：</dt>
				<dd class="neirong-max-height" style="width: 195px;">{{=subString(removeHtmlTags(isNullFormat(v.course_description)),30)}}</dd>
			</dl>
		</div>
		<!--div class="neirong-bottom">
			{{?v.is_publish == 1}}
			<button type="button" class="btn btn_green mr10">已发布</button>
			{{??}}
			<button type="button" class="btn btn_red mr10" >可编辑</button>
			{{?}}
			<button type="button" class="btn btn_gray" onclick="javascript:onDetail('{{=v.id}}')">简介</button>
		</div-> <!--neirong-bottom end-->
	</li>

	
{{~}}	

{{~it.data:v:index}}
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