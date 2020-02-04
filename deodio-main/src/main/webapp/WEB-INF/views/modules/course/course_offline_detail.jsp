<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<script type="text/javascript">
	var trainerList = eval('${trainerListJson}');
	var locationList = eval('${locationListJson}');
</script>
<style>
.bootstrap-select:not([class*="col-"]):not([class*="form-control"]):not(.input-group-btn){width:100px;}
</style>
<body style="overflow-y: auto;font-size:13px">
	<div class="content p20">
		
		<input type="hidden" value = "${courseMap.course_train_times}" id = "courseTrainTimes"> 
		<input type="hidden" value = "${courseMap.id}" id = "courseId">
		<input type="hidden" id = "stepNo">
		<input type="hidden" value ="${balanceNum}" id ="balanceNum">
		<input type="hidden" value ="${traineeNum}" id ="traineeNum">
		<input type="hidden" value ="${traineeSubstituteNum}" id ="traineeSubstituteNum">
		<input type="hidden" value ="${courseMap.course_substitute_number}" id ="courseSubstituteNumber">
		<input type="hidden" id = "itemIdValue" value="">
		<input type="hidden" value ="${courseMap.is_publish}" id ="isPublish">
		<input type="hidden" value = "${courseMap.course_rule}" id = "courseRule"> 
		<input type="hidden" value = "${courseMap.start_time}" id = "trainStartTime">
		<input type="hidden" value = "${courseRegisteRule.expireTime}" id = "enrollExpireTime">
		<input type="hidden" value = "${courseRegisteRule.startTime}" id = "enrollStartTime">
		<input type="hidden" value="${courseMap.is_public}" id="isPublic">
		
		<div class="pre1_tab set_tab mt20 pl20" id="myTab" style="height:47px;">
				
				<h4>线下课程：${courseMap.course_name}</h4>
				<button type="button" class="btn btn_green  pull-right" onClick="go2Page('/course/offline/list.html')" style="margin-top:10px !important;margin-right:10px;">返回</button>
			
		</div>
		
		<div id="myTabContent" class="tab-content" style="min-height:200px;">
			
			<div class="pre1_con tab-pane active" id="baomingshenpi">
				
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
						<div>
						<div class="select_b mt10">
							<span>报名方式：</span>
							<div class="w80 ">
								<select name="registeType" id="registeType" style="width: 60px" onchange="loadCourseOfflineTraineeList()">
									<option value="">----全部----</option>
									<option value="1">线上</option>
									<option value="2">线下</option>
								</select>
							</div>
							<span>审批意见：</span>
							<div class="w80 ">
								<select name="traineeStatus" id="traineeStatus" style="width: 60px" onchange="loadCourseOfflineTraineeList()">
									<option value="">----全部----</option>
									<option value="0">通过</option>
									<option value="1">拒绝</option>
									<option value="2">替补</option>
									<option value="3">待审核</option>
								</select>
							</div>
							<span>上课情况：</span>
							<div class="w80 ">
								<select name="joinTime" id="joinTime" style="width: 60px" onchange="loadCourseOfflineTraineeList()">
									<option value="">----全部----</option>
									<option value="0">未开始</option>
									<option value="1">正常</option>
									<option value="2">退课</option>
								</select>
							</div>

							<!-- <div class="pull-right" style="width: 230px;margin-top: 0px;"> -->

							<div class="pull-right tools" style="width: 327px;margin-top: -12px;">
							    <c:if test="${courseRegisteRule.isRegisteDesk==1}">
							    <input id="uploadExcel" name="uploadExcel" class="user-card pull-left" type="file"></input>
								<button type="button" class="btn c_blue pull-right " onClick="manageTrainee('substitute');">替补</button>
								<button type="button" class="btn c_red pull-right mr5" onClick="manageTrainee('suspend')">拒绝</button>
								<button type="button" class="btn c_green pull-right mr5" onClick="manageTrainee('active');">通过</button>
								</c:if>
							    <button type="button" class="btn c_blue pull-right mr5" onClick="downloadTemplate()">下载模板</button>
								
								<!-- <button type="button" class="btn c_blue" onClick="manageTrainee('suspend');">拒绝</button>
								<button type="button" class="btn c_red" onClick="manageTrainee('delete')">Remove</button> -->
							</div>
						</div>
						<table cellpadding="0" cellspacing="0"
							class="table table-striped table-hover td_h60 mt10">
							<thead>
								<tr>
									<th style="width: 8%;" class="text-center">
										<div class="checkbox-group">
											<input type="checkbox" name="allRelatedIdCouserUser" id="allRelatedIdCouserUser"> <label
												class="checkbox-2" for="allRelatedIdCouserUser"></label>
										</div>
									</th>
									<th class="text-center" style="width: 15%;">用户昵称</th>
									<th class="text-center" style="width: 13p%;">姓名</th>
									<th class="text-center" style="width: 15%;">电子邮件</th>
									<th class="text-center" style="width: 12%;">报名方式</th>
									<th class="text-center">审批意见</th>
									<th class="text-center" >上课情况</th>
								</tr>
							</thead>
							<tbody id="courseOfflineTraineeList">
							</tbody>
						</table>
						<!-- 分页 -->
						<div class="mt20 text-center" id="course_offline_trainee_data_page_Panel">
						</div>
						</div>
					</div>
				</div>

			</div>


		</div>

	</div>
	<%@ include file="/WEB-INF/views/modules/course/course_template.jsp"%>
	<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<script type="text/javascript">
		require(['modules/course/course_offline_detail','modules/course/course_common'], function(obj) {
			
		});
	</script>
</body>
</html>