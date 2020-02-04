<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/courseman/mycourses.css">
<body>
    <div class="container">
        <div class="p_border p40">
            <div class="row">
                <div class="col-md-9">
					<ul class="fenlei">
					   <li id="classificationsList"><span>内容分类：</span> 
						    <c:forEach items="${classificationsList}" var="item">
								<button type="submit" class="bule-bnt" id="${item.id}"
									onclick="onFilterByTags(this)">${item.classification_name}</button>
							</c:forEach>
					   </li>
					   <li id="tagsList"><span>内容标签：</span> 
					       <c:forEach items="${tagsList}" var="item">
								<button type="submit" class="bule-bnt" id="${item.id}"
									onclick="onFilterByTags(this)">${item.tag_name}</button>
							</c:forEach>
					   </li>
					</ul>
					<div class="row" id="dataPanel">
                        
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="left_menu">
                        <h3>点击以下按钮操作</h3>
                        <ul>
                            <li><a href=""></a></li>
                            <li><a href="" class="m2"></a></li>
                            <li><a href="" class="m3"></a></li>
                            <li><a href="" class="m4"></a></li>
                            <li><a href="" class="m5"></a></li>
                        </ul>
                        <div class="text">文字提示内容文字提示内容</div>
                    </div>
                </div>
            </div>
            <div class="mt20 text-center">
               <div id="dataPage"></div>
            </div>
        </div>
    </div>
<script id="online_course_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
<li id="{{=isNullFormat(v.id)}}" onclick="javascript:onSelectOnlineCourese('{{=isNullFormat(v.id)}}')" {{? index == 0}} class="item_select"{{?}}>
    <div class="col-md-4 col-xs-6 col-sm-6">
        <div class="kc_box">
        <a href="">
            {{? !v.course_cover_img}}
            <img src="${ctx}/resources/img/courseman/p_pic.jpg" alt="">{{??}}
            <img src="${imgStatic}{{=isNullFormat(v.attachUrl)}}{{=isNullFormat(v.courseCoverImg)}}" alt="">       
            {{?}}
        </a>
            <ul class="kc_box_item">
                <li>
                    <span class="left_title">课程名称：</span>{{=isNullFormat(v.courseName)}}
                </li>
                <li>
                    <div class="row">
                        <div class="col-md-6"><span class="left_title">引用数：</span>23</div>
                        <div class="col-md-6"><span class="left_title">创建人：</span><a href="">Isaac</a></div>
                    </div>
                    <span class="left_title">讲师：</span>{{=isNullFormat(v.nick_name)}}
                </li>
                <li><span class="left_title">创建时间：</span>2015-12-15</li>
                <li>
                    <span class="left_title">简介：</span>
                    课程简介文字课程简介文字课程简介文字课程简介文字课程简介
                </li>
            </ul>
            <div class="kc_box_btn text-right">
                <button type="button" class="btn btn_green">可编辑</button>
                <button type="button" class="btn btn_blue">简介</button>
            </div>
        </div>
    </div>
</li>  
{{~}}   
</script>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<script type="text/javascript">
    require([ 'modules/courseman/online_course' ], function() {}); 
</script>
</body>
</html>

