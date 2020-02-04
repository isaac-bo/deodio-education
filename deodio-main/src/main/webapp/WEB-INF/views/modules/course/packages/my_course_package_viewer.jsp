<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<body>

	<div class="tab_bar mt20">
		<ul class="pull-left tab_bar_left">
			<li class="active"><a href="" data-value="0" data-toggle="tab" data-class="choose1">按学习时间排序</a></li>
			<li><a href="" data-value="1" data-toggle="tab" data-class="choose1">按加入时间排序</a></li>
		</ul>
		<ul class="pull-right tab_bar_right">
			<li class="active"><a href="" data-value="0" data-toggle="tab" data-class="choose2">全部</a></li>
			<li><a href="" data-value="1" data-toggle="tab" data-class="choose2">付费</a></li>
			<li><a href="" data-value="2" data-toggle="tab" data-class="choose2">即将过期</a></li>
		</ul>
		<div class="clearfix"></div>
	</div>
	<div class="p20">
		
		<ul class="my_course_list" id="packageCourseContent">
		
		
		</ul>
	</div>
	
	<input type="hidden" id="hidden_page" value="1">
	
	<script id="my_course_package_template" type="text/x-dot-template">
       {{~it.data.dataList:v:index}}
         <li>
            <a href="">
               {{? isNullFormat(v.package_cover_img) == ''}}
                 <img src="${ctx}/resources/img/viewer/my_course_pic.jpg" alt="">
               {{??}}
                 <img src="${imgStatic}/{{=isNullFormat(v.package_cover_img)}}" alt="">
	           {{?}}
            </a>
			<h3>
			   <span>课程名称：</span>{{=isNullFormat(v.package_name)}}
			</h3>
		    <p>
			   <span>课程简介：</span>{{=isNullFormat(v.package_desc)}}
		    </p>
          </li>
       {{~}}
	</script>
	
	
	
	<script type="text/javascript">
			require([ 'modules/course/packages/my_course_package_viewer' ], function(obj) {
			}); 
	</script>
	



</body>
</html>