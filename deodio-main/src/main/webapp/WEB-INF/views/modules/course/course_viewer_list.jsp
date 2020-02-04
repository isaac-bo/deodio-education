<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<%-- <div class="userinfo">
	    <div class="content">
	        <div class="user_photo"><img src="${ctx}/resources/img/viewer/user_photo.jpg" alt=""></div>
	        <div class="user_name">
	            <h3>Hi , <span>Katy Jones</span></h3>
	            <p>欢迎来到滴石！</p>
	        </div>
	    </div>
	</div> --%>
	<div class="content p_border p40">
	    <div class="tab_box">
	        <ul class="nav nav-tabs set_tab" role="tablist" id="tabList">
	            <li role="presentation" class="active"><a href="#myCourse" role="tab" data-toggle="tab" data-id="content">我的课程</a></li>
	            <li role="presentation"><a href="#logo" role="tab" data-toggle="tab" data-id="packageCourseContent">我的课程包</a></li>
	            <li role="presentation"><a href="#header" role="tab" data-toggle="tab" data-id="file">收藏夹</a></li>
            </ul>
	        <div class="w240">
	            <button type="button" class="btn-success btn36">&nbsp;</button>
	            <div class="search_input"><input type="text" class="form-control"></div>
	        </div>
	    </div>
	    <div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="myCourse">
				<div class="tab_bar mt20">
					<ul class="pull-left tab_bar_left">
						<li class="active"><a href="#studyTime" data-toggle="tab" data-value='0' data-class="condition1">按学习时间排序</a></li>
						<li><a href="#addTime" data-class="condition1" data-toggle="tab" data-value='1'>按加入时间排序</a></li>
						 
					</ul>
					<ul class="pull-right tab_bar_right">
						<li class="active"><a href="#allTab"  data-class="condition2" data-toggle="tab" data-value='0'>全部</a></li>
						<li><a href="#chargeTab"  data-toggle="tab" data-class="condition2" data-value='1'>付费</a></li>
						<li><a href="#deadlineTab"  data-toggle="tab" data-class="condition2" data-value='2'>即将过期</a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="p20" id="studyTime">
					<ul class="course_list" id="content">
                        
					</ul>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="logo">
	             <%@ include file="/WEB-INF/views/modules/course/packages/my_course_package_viewer.jsp"%>
	        </div>
	        <div role="tabpanel" class="tab-pane" id="header">
	            <div class="form_bg mt20">
	33
	            </div>
	        </div>
	
	
	    </div>
	    <div class="pt20">
	
	        <div class="clearfix"></div>
	    </div>
	</div>

<script id="data_template" type="text/x-dot-template">
{{~it.data.dataList:v:index}}
    <li>
		{{? isNullFormat(v.course_type) == 1}}
 			<a href="${ctx}/course/course_viewer/online_detail.html?courseId={{=isNullFormat(v.id)}}&groupId={{=isNullFormat(v.group_id)}}">
		{{?? isNullFormat(v.course_type) == 2}}
			<a href="${ctx}/course/course_viewer/offline_detail.html?courseId={{=isNullFormat(v.id)}}&groupId={{=isNullFormat(v.group_id)}}">
		{{??}} 
			<a href="${ctx}/course/onlive/profile.html?courseId={{=isNullFormat(v.id)}}&groupId={{=isNullFormat(v.group_id)}}">
		{{?}}
      
       {{? isNullFormat(v.course_cover_img) == ''}}
          <img src="${ctx}/resources/img/viewer/pic.png" alt="">
       {{??}}
          <img src="${imgStatic}/{{=isNullFormat(v.course_cover_img)}}" alt="">
	   {{?}}
       </a>
       <h3 class="course_title"><span>课程名称:</span>{{=isNullFormat(v.course_name)}}</h3>
       {{? isNullFormat(v.chapter_finish) == '' || isNullFormat(v.chapter_finish) == 0}}
       <div class="star_btn">
		{{? isNullFormat(v.course_type) == 1}}
			<button class="btn" type="button" onclick="javascript:go2Page('/course/course_viewer/online_detail.html','courseId={{=isNullFormat(v.id)}}&groupId={{=isNullFormat(v.group_id)}}');">开始学习</button>
		{{?? isNullFormat(v.course_type) == 2}}
			<button class="btn" type="button" onclick="javascript:go2Page('/course/course_viewer/offline_detail.html','courseId={{=isNullFormat(v.id)}}&groupId={{=isNullFormat(v.group_id)}}');">开始学习</button>
		{{??}} 
			<button class="btn" type="button" onclick="javascript:go2Page('/course/onlive/profile.html','courseId={{=isNullFormat(v.id)}}&groupId={{=isNullFormat(v.group_id)}}');">开始学习</button>
		{{?}}
          
       </div>
       {{?}}
       {{? isNullFormat(v.chapter_finish) ==  isNullFormat(v.chapter_count)}}
        <div class="percentage">
             <div class="text-center percentage_bg" style="width:100%;background:#46c37b;">100%</div>
        </div>
        <div class="repeat_btn">
             <button class="btn" type="button" onclick="javascript:go2Page('/course/course_viewer/detail.html','courseId={{=isNullFormat(v.id)}}&groupId={{=isNullFormat(v.group_id)}}');">再学一遍</button>
        </div>
        <span class="mercury"></span>
      {{?}}
      {{? isNullFormat(v.chapter_finish) != '' && isNullFormat(v.chapter_finish) != 0 && isNullFormat(v.chapter_finish) !=  isNullFormat(v.chapter_count)}}
      <div class="percentage">
           <div class="text-center percentage_bg" style="width:{{=isNullFormat((v.chapter_finish/v.chapter_count).toFixed(2)*100)}}%;">{{=isNullFormat((v.chapter_finish/v.chapter_count).toFixed(2)*100)}}%</div>
      </div>
      <div class="course_text">已学习{{=isNullFormat(v.chapter_finish)}}/{{=isNullFormat(v.chapter_count)}}章节</div>
      {{?}}
    </li>
{{~}}
</script>

    <input type="hidden"  id="flag1" value="">
    <input type="hidden"  id="flag2" value="">
	<input type="hidden" id="hid_default_page" value="1">
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require([ 'modules/course/course_viewer_list' ], function(obj) {
		}); 
	</script>
</body>
</html>