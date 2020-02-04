<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
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
										<span class="top-tabox-fl" id="${item.id}">${item.classification_name}</span><span
											class="top-tabox-close">x</span><span class="top-tabox-up"><img
											src="${ctx}/resources/img/up.png"></span>
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
			<div class="" id="courseOfflineContent">
				<ul class="neirong ml12" id="course_offline_panle">
				</ul>
			</div>
			<div class="clearfix"></div>	
				<input type="hidden" id="hid_default_course_offline_page" value="1">
		</div>
		<div class="pre_right mr20" style="margin-right: 0px;">
			<h3>点击以下按钮操作</h3>
			<div class="wb100 search">
				<button type="button" class="btn-success btn36">&nbsp;</button>
				<div class="search_input">
					<input type="text" class="form-control">
				</div>
				<div class="clearfix"></div>
			</div>
			<ul class="p10 pre_t_btn">
				
				<li><button style="background-color:#43b4c6" type="button" onClick="javascript:go2Page('/course/onlive/profile.html')"><span class="icon-file-alt"/></button></li>
				<li><button type="button" style="background-color:#ee625e;" onClick="javascript:onToOnliveCourseDetail()"><span class="icon-edit"/></button></li>
				<li><button type="button" style="background-color:rgb(113,174,145);"  onClick="javascript:onDeleteOnliveCourse()"><span class="icon-trash"/></button></li>
				<li><button type="button" style="background-color:rgb(101,170,221);" ><span class="icon-cogs"/></button></li>
				<li><button type="button" style="background-color:#43b4c6;" ><span class="icon-share-alt"/></button></li>
				<li><button type="button" style="background-color:#ee625e;" ><span class="icon-copy"/></button></li>
				<li><button type="button" style="background-color:rgb(113,174,145);" ><span class="icon-eye-open"/></button></li>
<!-- 				<li><button type="button" style="background-color:rgb(101,170,221);" ><span class="icon-sitemap"></button></li>
				<li><button type="button" style="background-color:#43b4c6;" ><span class="icon-dashboard"></button></li> -->
				
			</ul>
			<div class="text">文字提示内容文字提示内容</div>

		</div>
		<div class="clearfix"></div>

	</div>
	<%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %>
	<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<!-- 弹出区域 -->
	<script id="course_onlive_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<li id="{{=isNullFormat(v.id)}}"  onclick="javascript:onSelectOnlineCourese('{{=isNullFormat(v.id)}}')" {{? index == 0}} class="item_select"{{?}}>
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
				<dd><a href="" class="mr20" title="{{=isNullFormat(v.nick_name)}}">{{=subString(isNullFormat(v.nick_name),4)}}</a></dd>
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

	
{{~}}	

{{~it.data:v:index}}
	{{? index == 0}}
		<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
	{{?}}	
{{~}}
</script>
	<script type="text/javascript">
		require([ 'modules/course/course_onlive_list' ], function(obj) {
		});
	</script>
</body>
</html>