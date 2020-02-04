<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/presentation/presentation.css">
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
							<button type="submit" class="bule-bnt" id="${item.id}" onclick="onFilterByTags(this)">${item.tag_name}</button>
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
				<input type="hidden" id="hid_default_presentation_page" value="1">
		</div>
		
		<div class="pre_right">
			<h3>点击以下按钮操作</h3>
			<div class="wb100 search">
				<button type="button" onclick="loadPageDataList(1);" class="btn-success btn36">&nbsp;</button>
				<div class="search_input">
					<input type="text" id="searchWord" class="form-control">
				</div>
				<div class="clearfix"></div>
			</div>
			<ul class="p10 pre_t_btn">
				<li onmouseover="setClickInfo('创建一个新的章节')"><button style="background-color:#43b4c6" type="button" onClick="javascript:go2Page('/presentation/profile.html');"><span class="icon-file-alt"/></button></li>
			
			<c:if test="${not empty classificationsList}">
				<li class="hid_btn" id="editor_id" onmouseover="setClickInfo('编辑章节信息和内容')"><button type="button" style="background-color:#ee625e;" onClick="javascript:onToPresetationDetail()"><span class="icon-edit"/></button></li>
				<li class="hid_btn" id="delete_id" onmouseover="setClickInfo('删除此章节')"><button type="button" style="background-color:rgb(113,174,145);"  onClick="javascript:onDelPresetation()"><span class="icon-trash"/></button></li>
				<li class="hid_btn" id="draw_id" onmouseover="setClickInfo('查看章节引用内容')"><button onclick="quotesContent();" type="button" style="background-color:rgb(101,170,221);" ><span class="icon-cogs"/></button></li>
				<li class="hid_btn" id="share_id" onmouseover="setClickInfo('章节编辑权限共享')"><button type="button" onclick="onSharePresentation();" style="background-color:#43b4c6;" ><span class="icon-share-alt"/></button></li>
				<li class="hid_btn" onmouseover="setClickInfo('复制此章节，快速生成一个相似章节')"><button type="button" onclick="copyPresentation();" style="background-color:#ee625e;" ><span class="icon-copy"/></button></li>
				<li class="hid_btn" onmouseover="setClickInfo('章节学习预览')"><button onclick="openPreview();" type="button" style="background-color:rgb(113,174,145);" ><span class="icon-eye-open"/></button></li>
				<li class="hid_btn" onmouseover="setClickInfo('查看引用此章节的课程列表')"><button onclick="quotesCourseContent();" type="button" style="background-color:rgb(101,170,221);" ><span class="icon-sitemap"></button></li>
			</c:if>
			</ul>
			<div class="text" id="click_info"></div>

		</div>
		<div class="clearfix"></div>

	</div>
	<%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %>
	<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<!-- 弹出区域 -->
	<%@ include file="/WEB-INF/views/modules/presentation/presentation_list_abstract.jsp"%>
	<%@ include file="/WEB-INF/views/modules/presentation/presentation_share_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/modules/presentation/presentation_copy.jsp"%>
	<%@ include file="/WEB-INF/views/modules/presentation/presentation_quotes_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/modules/presentation/presentation_quotes_coures_dialogue.jsp"%>
	
	<input type="hidden" id="share_owner_id">
	<input type="hidden" id="preview_type_id">
	
	
<script id="classification_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<li id="{{=isNullFormat(v.id)}}" onclick="javascript:onSelectPresentation('{{=isNullFormat(v.id)}}','{{=v.presentation_model}}','{{=v.is_publish}}','{{=isNullFormat(v.presentation_owner)}}','{{=v.create_id}}','{{=isNullFormat(v.presentation_name)}}')" {{? index == 0}} class="item_select"{{?}}>

		<div class="pic mb20">
			{{? isNullFormat(v.presentation_cover) != ''}}
				<img src="${imgStatic}{{=v.attach_url}}{{=v.generate_name}}" alt=""  width="276" height="155">

			{{??}}
				<img src="${ctx}/resources/img/presentation/p_pic.jpg" alt="" width="276" height="155">
			{{?}}
		</div>

		<div class="feature-tip 
				{{? isNullFormat(v.presentation_model) == 0}}
					feature-tip-scrom
				{{?? isNullFormat(v.presentation_model) == 1}}
					feature-tip-compress
				{{?? isNullFormat(v.presentation_model) == 2}}
					feature-tip-multiplemedia
				{{?? isNullFormat(v.presentation_model) == 3}}
					feature-tip-superlink
				{{?}}" style="margin-top: -10px;">
		</div>
			
		<div class="feature-icon">
				{{? isNullFormat(v.presentation_model) == 0}}
					<span class="icon-sign-blank" style="position: absolute;"></span>
					<span class="character">S</span>
				{{?? isNullFormat(v.presentation_model) == 1}}
					<span class="glyphicon glyphicon-compressed" style="position: absolute;"></span>
				{{?? isNullFormat(v.presentation_model) == 2}}
					<span class="icon-film" style="position: absolute;"></span>
				{{?? isNullFormat(v.presentation_model) == 3}}
					<span class="icon-link" style="position: absolute;"></span>
				{{?}}
		</div>


		<div class="mt10 wid-100">
			<dl>
				<dt>章节名称：</dt>
				<dd style="width: 195px;" title="{{=isNullFormat(v.presentation_name)}}">{{=subString(v.presentation_name,14)}}</dd>
			</dl>
		</div>

		<div class="mt10 f12">
			<dl>
				<dt>引用数：</dt>
				<dd >42</dd>
			</dl>
		
		</div>

	<div class="mt10 f12">
		
			<dl style="width: 100%;" title="{{=isNullFormat(v.nick_name)}}">
				<dt>创建人：</dt>
				<dd style="width: 180px;">
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
				<dd class="neirong-max-height" style="width: 195px;" title="{{=isNullFormat(v.presentation_desc)}}">{{=subString(isNullFormat(v.presentation_desc),30)}}</dd>
			</dl>
		</div>
		<div class="neirong-bottom">
			{{?v.is_publish == 1}}
			<button id="btn_status_{{=v.id}}" type="button" class="btn btn_green mr10">已发布</button>
			{{??}}
			<button type="button" class="btn btn_red mr10">可编辑</button>
			{{?}}
			<button onclick="viewDetail('{{=isNullFormat(v.id)}}');" type="button" class="btn btn_gray">简介</button>
		</div> <!--neirong-bottom end-->

		{{? index == 0}}
			<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
			<input type="hidden" id="_item_name" name="_item_name" value="{{=isNullFormat(v.presentation_name)}}"/>
		{{?}}	
	</li>

{{~}}	
</script>
	<script type="text/javascript">
		require([ 'modules/presentation/presentation_list' ], function(obj) {
		});
	</script>
</body>
</html>