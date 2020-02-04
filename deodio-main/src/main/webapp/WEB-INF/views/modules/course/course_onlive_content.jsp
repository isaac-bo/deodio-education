<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<script type="text/javascript">
	var trainerList = eval('${trainerListJson}');
</script>
<body>
	<div class="content p_border p40">
		<ul class="top_nav">
      	<li class="no1_on">设置课程信息</li>
      	<li class="no2_on">设置课程内容</li>
      	<!-- <li class="no3">设置课程规则</li> -->
    </ul>
	<form id="courseOnliveContentForm" method="post">
    <div class="pt20" style="width:1065px;" id = "courseOnliveContent">
    		<div class="dagang_box">
					<!-- 次数列表  start-->
					<!-- 次数列表  end-->
					<div class="right_cont">
						<!-- a 上下翻页  start-->
						<!-- a 上下翻页  end-->
						<div class="dg_title_b">
							<h5 class="pull-left">
								<span class="current_step_no">第一次：</span><em></em>
							</h5>
							<div class="pxshijian">
							</div>
							<div class="clearfix"></div>

						</div>
						<div class="szkc mt20" >
							<div class="sz_thead mt20">
					            <span style="width:25%;">课次名称</span>
								<span style="width:10%;">课时</span>
					            <span style="width:34%;">起始时间</span>
					            <span style="width:22%;">培训讲师</span>
					            <span style="width:8%;">操作</span>
					        </div>
				        </div>
				</div>
				<input type="hidden" value = "${courseMap.course_unit_count}" id = "courseUnitCount"> 
				<input type="hidden" value = "${courseMap.course_id}" id = "courseId">
				<input type="hidden" value = "1" id = "stepNo"> 
			</div>
		    <div class="form_line2"></div>
		    <div class="btn_box">
		        <button class="btn submit  btn_160_36" type="button" onClick="toCourseSetting('${courseMap.course_id}');">保存</button>
				<button class="btn submit  btn_160_36" type="button" onClick="courseOnlivePublish()">保存／发布</button>
		        <button class="btn cancel btn_160_36" type="button">取消</button>
		    </div>
		</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/modules/course/course_template.jsp"%>
	<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	
	<!-- dagang_box pxshijian 页面模板-->
	<script id="course_onlive_dagang_box_pxshijian_data_template" type="text/x-dot-template">
		<span class="ml20">名称：</span>
		<div style="width:575px;" class = "error-div">
			<input type="text" name="itemName" class="form-control" value="{{=isNullFormat(it.item_name)}}" check-type="required">
		</div>
		<div>
			<button type="button" class="icon_1 ml10 mt5" btn-type='true'></button>
			<button type="button" class="icon_2 ml10 mt5" onClick=""></button>
			<button type="button" class="icon_3 ml10 mt5"></button>
		</div>
		<input type="hidden" name="itemType" value="0">
		<input type="hidden" name="operateType" value="1">
		<input type="hidden" name="itemId" value="{{=isNullFormat(it.id)}}">
	</script>		
	
	<!-- dagang_box sz_add 页面模板-->
	<script id="course_onlive_dagang_box_sz_add_data_template" type="text/x-dot-template">
	{{? it.data.length > 0 }}
		{{~it.data:v:index}}
	 	<div class="sz_add">
		  <div style="width:227px;" class = "error-div">
              <input type="text" class="form-control" name="itemName" check-type="required" value ="{{=isNullFormat(v.item_name)}}">
          </div>
		  <div style="width:90px;" class = "error-div">
              <input type="text" class="form-control" name="itemPeriod" check-type="number" value ="{{=isNullFormat(v.item_period)}}">
          </div>
		  <div style="width:290px;" class = "error-div">
			<input type="text" class="form-control w130 pull-left form_datetime min_min"  readonly name="startTime" value ="{{=dateFormat1(v.start_time)}}" check-type="required" required-message="请选择日期!">
			<span class="pull-left ml10 mr10">~</span>
			<input type="text" class="form-control w130 pull-left form_datetime min_min" readonly name="expireTime" value ="{{=dateFormat1(v.expire_time)}}" check-type="required" required-message="请选择日期!">
          </div>
          <div style="width:200px;" class = "error-div">
              <select name="itemTraines" style="width:100%" multiple id="{{=randomString()}}">
                  {{~it.trainerList:ts:i}}
					<option value="{{=isNullFormat(ts.id)}}"  {{? isSubStr(v.item_trainers,ts.id)}} selected {{?}}>{{=isNullFormat(ts.trainer_name)}}</option>
				  {{~}}
              </select>
          </div>
		  <input type="hidden" name="itemType" value="1">
		  <input type="hidden" name="operateType" value="1">
		  <input type="hidden" name="itemId" value="{{=v.id}}">
          <button type="button" class="btn_del_18 ml10" onClick="delRowData(this);"></button>
          <button type="button" class="btn_add_18 ml10" onClick="addRowData(this);"></button>
      	</div>
		{{~}}
	{{??}}
		<div class="sz_add">
		  <div style="width:227px;" class = "error-div">
              <input type="text" class="form-control" name="itemName" check-type="required">
          </div>
		  <div style="width:90px;" class = "error-div">
              <input type="text" class="form-control" name="itemPeriod" check-type="number">
          </div>
		  <div style="width:290px;" class = "error-div">
			<input type="text" class="form-control w130 pull-left form_datetime min_min"  readonly name="startTime" check-type="required" required-message="请选择日期!">
			<span class="pull-left ml10 mr10">~</span>
			<input type="text" class="form-control w130 pull-left form_datetime min_min" readonly name="expireTime" check-type="required" required-message="请选择日期!">
          </div>
          <div style="width:200px;" class = "error-div">
              <select name="itemTraines" style="width:100%" multiple >
                  {{~it.trainerList:tr:index}}
					<option value="{{=tr.id}}">{{=tr.trainer_name}}</option>
				  {{~}}
              </select>
          </div>
		  <input type="hidden" name="itemType" value="1">
		  <input type="hidden" name="operateType" value="0">
		  <input type="hidden" name="itemId" value="">
          <button type="button" class="btn_del_18 ml10" onClick="delRowData(this);"></button>
          <button type="button" class="btn_add_18 ml10" onClick="addRowData(this);"></button>
      	</div>
	{{?}}
	</script>


	<script type="text/javascript">
		require(['modules/course/course_common', 'modules/course/course_onlive_content' ], function(obj) {
		});
	</script>
</body>
</html>
