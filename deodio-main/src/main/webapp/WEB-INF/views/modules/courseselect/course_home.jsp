<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/courseselect/mycourses.css">
<body>
    <div class="container">
        <div class="p_border p40">
            <ul class="fenlei">
                <li id="classificationsList">
                    <span>内容分类：</span>
                    <c:forEach items="${classificationsList}" var="item">
                        <button type="submit" class="bule-bnt" id="${item.id}" onclick="onFilterByTags(this)">${item.classification_name}</button>
                    </c:forEach>
                </li>
                <li id="tagsList">
                    <span>内容标签：</span>
                    <c:forEach items="${tagsList}" var="item">
                        <button type="submit" class="bule-bnt" id="${item.id}" onclick="onFilterByTags(this)">${item.tag_name}</button>
                    </c:forEach>
                </li>
            </ul>
            <div class="tab_box">
                <ul class="nav nav-tabs set_tab" role="tablist" id="tabList">
                    <li role="presentation" class="active"><a href="#name" role="tab" data-toggle="tab" data-id="all">全部</a></li>
                    <li role="presentation"><a href="#logo" role="tab" data-toggle="tab" data-id="newest">最新</a></li>
                    <li role="presentation"><a href="#header" role="tab" data-toggle="tab" data-id="recommended">推荐</a></li>
                    <li role="presentation"><a href="#hot" role="tab" data-toggle="tab" data-id="hot">热门</a></li>
                </ul>
                <div class="w240">
                    <form>
                    <button type="button" class="btn-success btn36" id="search">&nbsp;</button>
                    <div class="search_input"><input type="text" class="form-control" placeholder="请输入搜索内容" id="searchCourseName"></div>
                    </form>
                </div>
            </div>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="name">
                    <div class="p20">
                        <div class="container-fluit">
                            <div class="row" id="dataPanel">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
			<div class="mt20 text-center">
                <div id="dataPage"></div>
            </div>
        </div>
    </div>

<script id="course_home_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<li id="{{=isNullFormat(v.id)}}" onclick="javascript:onSelectOnlineCourese('{{=isNullFormat(v.id)}}')" {{? index == 0}} class="item_select"{{?}}>
		<div class="col-md-3 col-xs-6 col-sm-6">
			<div class="kc_box">
				<a href="">
				{{? !v.course_cover_img}}
				<img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt="">
				{{??}}
				<img src="${imgStatic}{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.courseCoverImg)}}"   alt="">		
				{{?}}
				</a>
				<ul class="kc_box_item">
					<li>
                        <span class="left_title">课程名称：</span>{{=isNullFormat(v.courseName)}}
                    </li>
					<li>
                        <span class="left_title">讲师：</span>{{=isNullFormat(v.nickName)}}
                    </li>
					<li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
				</ul>
				<div class="kc_box_btn text-right">
                	<button type="button" class="btn btn_green">OPEN</button>
                    <button type="button" class="btn btn_blue" onclick="javascript:onDetail('{{=v.id}}')">简介</button>
                </div>			
			</div>
		</div>
	</li>
	{{? index == 0}}
		<input type="hidden" id="_item_id" name="_item_id" value="{{=isNullFormat(v.id)}}"/>
	{{?}}	
{{~}}	
</script>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<script type="text/javascript">
	require([ 'modules/courseselect/course_home' ], function() {}); 
</script>
</body>
</html>
