<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<body>
	<div class="content">
		<div class="pre_left">
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
		</div>
		<div class="pre_right mr20" style="margin-right: 0px;">
			<h3>点击以下按钮操作</h3>
			<div class="wb100 search">
				<button type="button" class="btn-success btn36" onClick="onSearchCourse()">&nbsp;</button>
				<div class="search_input">
					<input type="text" class="form-control" id="keyword">
				</div>
				<div class="clearfix"></div>
			</div>
			<ul class="p10 pre_t_btn" id="ul_li_focus_events">
				<li><button type="button" style="background-color:#43b4c6;" onClick="javascript:go2Page('/course/online/profile.html')"><span class="icon-file-alt"/></button></li>
				<li id="li_edit"><button type="button" style="background-color:#ee625e;" onClick="javascript:onToOnlineCourseEdit()"><span class="icon-edit"/></button></li>
				<li id="li_delete"><button type="button" style="background-color:rgb(113,174,145);"  onClick="javascript:onDeleteOnlineCourse()"><span class="icon-trash"/></button></li>
				<li id="li_quote"><button type="button" style="background-color:rgb(101,170,221);" onClick="javascript:onQuoteOnlineCourse()"><span class="icon-cogs"/></button></li>
				<li id="li_share"><button type="button" style="background-color:#43b4c6;" onClick="javascript:onShareOnlineCourse()"><span class="icon-share-alt"/></button></li>
				<li id="li_copy"><button type="button" style="background-color:#ee625e;" onClick="javascript:onCopyOnlineCourse()"><span class="icon-copy"/></button></li>
				<li id="li_preview"><button type="button" style="background-color:rgb(113,174,145);" onClick="javascript:onPreviewOnlineCourse()"><span class="icon-eye-open"/></button></li>
			<!-- 	<li><button type="button" style="background-color:rgb(101,170,221);" onClick=""><span class="icon-cogs"/></button></li> 
				<li><button type="button" style="background-color:#43b4c6;" ><span class="icon-dashboard"></button></li> -->
			</ul>
			<div class="text" id="text_tips"></div>
		</div>
		<div class="clearfix"></div>
	</div>
	<input type="hidden" id="group_container_type">
<!-- 弹出区域 -->
<script id="classification_data_template" type="text/x-dot-template">
{{var currentPage=it.currentPage;}}
{{~it.data:v:index}}
	<li id="{{=isNullFormat(v.id)}}" onclick="javascript:onSelectOnlineCourse(
        '{{=isNullFormat(v.id)}}','{{=isNullFormat(v.course_name)}}',
        '{{=isNullFormat(v.is_publish)}}','{{=isNullFormat(v.course_owner)}}',
        '{{=isNullFormat(v.is_edit)}}','{{=isNullFormat(v.create_id)}}',
        '{{=isNullFormat(v.student_num)}}')"
		 {{? index == 0 && !currentPage}} class="item_select"{{?}}>
		<div class="pic mb20">
		{{? !v.generate_name}}
		<img src="${ctx}/resources/img/course/p_pic.jpg" alt="">
		{{??}}
		<img src="${imgStatic}{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="">		
		{{?}}
		</div>
		<div class="mt10 wid-100">
			<dl>
				<dt>课程名称：</dt>
				<dd style="width: 195px;font-size:12px;" title="{{=isNullFormat(v.course_name)}}">{{=subString(isNullFormat(v.course_name),15)}}</dd>
			</dl>
		</div>
		<div class="mt10 wid-100">
			<dl>
				<dt>创建人：</dt>
				<dd style="width: 195px;font-size:12px;" title="{{=isNullFormat(v.nick_name)}}">{{=subString(isNullFormat(v.nick_name),15)}}</a></dd>
			</dl>
		</div>
		<div class="mt10 f12">
			<dl>
				<dt>引用数：</dt>
				<dd>{{=isNullFormat(v.quote_num)}}</dd>
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
				<dd class="neirong-max-height" style="width: 195px;" title="{{=isNullFormat(v.course_description)}}">{{=subString(removeHtmlTags(isNullFormat(v.course_description)),30)}}</dd>
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
	
{{~}}

{{~it.data:v:index}}
	{{? index == 0}}
		<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
		<input type="hidden" id="_item_course_name" name="_item_course_name" value="{{=isNullFormat(v.course_name)}}"/>
		<input type="hidden" id="_item_is_publish" name="_item_is_publish" value="{{=isNullFormat(v.is_publish)}}"/>
		<input type="hidden" id="_item_course_owner" name="_item_course_owner" value="{{=isNullFormat(v.course_owner)}}"/>
		<input type="hidden" id="_item_is_edit" name="_item_is_edit" value="{{=isNullFormat(v.is_edit)}}"/>
        <input type="hidden" id="_item_create_id" name="_item_create_id" value="{{=isNullFormat(v.create_id)}}"/>
        <input type="hidden" id="_item_student_num" name="_item_student_num" value="{{=isNullFormat(v.student_num)}}"/>
	{{?}}	
{{~}}	
</script>
<script id="chooseEntrance" type="text/x-dot-template">
    <div class="geshi1 p20">
        
		<div style="width: 270px;min-height: 40px; margin-bottom:10px;">
			因为现在的课程属于正在编辑的状态，如果您需要继续编辑课程相关管理内容，请点击课程管理入口。
		</div>

		<div><button type="button" class="btn btn_green" style="width: 270px;" onclick="chooseModule(0)">课程管理入口</button></div>
		
		<div style="width: 270px;min-height: 40px;margin-top:10px;margin-bottom:10px;">
			如果您需要继续编辑课程相关基本信息，请点击课程详细入口。
		</div>

        <div><button type="button" class="btn btn_green" style="width: 270px;" id="detailEntrance" onclick="chooseModule(1)">课程详细入口</button></div>
    </div>
    <div class="mt20 f12 c929292 p20" id="format1">
            当前课程已发布，点击课程详细入口将自动取消发布。
    </div>
    <div class="mt20 f12 c929292 p20" id="format2" style="display:none">
            当前课程有学员正在学习，因此不能编辑课程详细。
    </div>
</script>
<%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>  
<%@ include file="/WEB-INF/views/modules/course/course_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/course/course_copy_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/course/course_share_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/modules/course/course_quote_dialogue.jsp"%>
<%-- <%@ include file="/WEB-INF/views/modules/course/course_preview_dialogue.jsp"%> --%>
<script type="text/javascript">
	require([ 'modules/course/course_common','modules/course/course_online_list'], function(obj) {
	});
</script>
</body>
</html>