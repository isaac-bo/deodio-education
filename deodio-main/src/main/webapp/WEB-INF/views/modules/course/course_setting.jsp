<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/account/account.layer.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<input type="hidden" value="${courseMap.is_publish}"/>
	<div class="content">
             <c:set var="v_course_file" value = ""></c:set>
             <c:set var="v_test" value=""></c:set>
             <c:set var="v_course_notice" value=""></c:set>
             <c:set var="v_course_relate" value=""></c:set>
             <c:set var="v_course_homework" value=""></c:set>
             <c:set var="v_course_remark" value=""></c:set>
              <c:set var="v_upload_file" value=""></c:set>
             <c:set var="v_download_file" value=""></c:set>
             <c:forEach items="${groupRoleFuncList}" var="item">
             <c:if test="${item.functionId == '10000'}"><!-- 毕业考试 -->
             	<c:set var="v_test" value = "10000"></c:set>
             </c:if>
             <c:if test="${item.functionId == '10001' }"><!-- 课程文件 -->
             	<c:set var="v_course_file" value = "10001"></c:set>
             </c:if>
             <c:if test="${item.functionId == '10002' }"><!-- 课程通知 -->
             	<c:set var="v_course_notice" value = "10002"></c:set>
             </c:if>
             <c:if test="${item.functionId == '10003' }"><!-- 关联课程 -->
             	<c:set var="v_course_relate" value = "10003"></c:set>
             </c:if>
             <c:if test="${item.functionId == '10004' }"><!-- 课后作业 -->
             	<c:set var="v_course_homework" value = "10004"></c:set>
             </c:if>
             <c:if test="${item.functionId == '10015' }"><!-- 课程调查-->
             	<c:set var="v_course_remark" value = "10015"></c:set>
             </c:if>
              <c:if test="${item.functionId == '10013' }"><!-- 上传附件-->
              <c:set var="v_upload_file" value = "10013"></c:set>
               <input type="hidden" value="10013" id="uploadAttachment">
             </c:if>
              <c:if test="${item.functionId == '10014' }"><!-- 下载附件-->
              <c:set var="v_download_file" value = "10014"></c:set>
               <input type="hidden" value="10014" id="downloadAttachment">
             </c:if>
             
             </c:forEach> 
             <c:if test="${v_upload_file != '10013' }">
             <input type="hidden" value="0" id="uploadAttachment">
             </c:if>
             <c:if test="${v_download_file != '10014' }"><!-- 下载附件-->
             <input type="hidden" value="0" id="downloadAttachment">
             </c:if>
	<%-- 	<ul class="top_nav">
			<li class="no1_on">设置课程信息</li>
			<li class="no2_on">设置课程内容</li>
			<!-- <li class="no3_on">设置课程规则</li> -->
			      	<c:if test="${courseMap.course_type==2}">
			<li class="no4">报名设置及审批</li>
		</c:if>

		</ul> --%>
			<%-- <ul class="pre1_tab pl20">
				<li class="active"><a href="javascript:onDetail('${courseId}')">返回课程详细</a></li>
			</ul> --%>
<%-- 			<div class="w240 pull-left">
			<button type="button" class="pull-left btn btn_green btn36" onclick ="onDetail('${courseId}')">返回课程详细</button> 
			</div> --%>
		<div class="con900">
			<div class="content-header-panel">
				<div class="pull-left">
					<img style="height: 130px;"
						src="<c:if test="${empty courseMap.course_cover_img}">${ctx}/resources/img/course/title_pic.png</c:if><c:if test="${not empty courseMap.course_cover_img}"> ${imgStatic}${courseMap.attach_url}/${courseMap.course_cover_img} </c:if>"
						alt="">
				</div>
				<div class="pull-right right_content">
					<h3 class="title pb10">${courseMap.course_name}</h3>
					<div class="mt10">
						<ul class="course_des">
							<li>评分：
								<div
									<c:choose>
										<c:when test="${courseMap.course_star==2}">class="star2"</c:when>
										<c:when test="${courseMap.course_star==3}">class="star3"</c:when>
										<c:when test="${courseMap.course_star==4}">class="star4"</c:when>
										<c:when test="${courseMap.course_star==5}">class="star5"</c:when>
										<c:otherwise>class="star1"</c:otherwise>
									</c:choose>>
								</div>
							</li>
							<li>课程类型：<span> <c:if
										test="${courseMap.course_type==1}">
							线上课程
						</c:if> <c:if test="${courseMap.course_type==2}">
							线下课程
						</c:if> <c:if test="${courseMap.course_type==3}">
							直播课程
						</c:if>

							</span></li>
							<li>培训课时：<span>${courseMap.course_train_times}</span></li>
							<li>培训起至时间：<span > <c:choose>
										<c:when test="${not empty courseMap.start_time &&  not empty courseMap.expire_time}">
											<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd" />
											至<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${courseMap.expire_time}" />
											
											<input type="hidden" id="trained_time_start" value="<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd" />">
			          					<input type="hidden" id="trained_time_expire" value="<fmt:formatDate type="date" value="${courseMap.expire_time}" pattern="yyyy-MM-dd" />">
										</c:when>
										<c:when test="${empty courseMap.start_time &&  not empty courseMap.expire_time}">
			          						 至<fmt:formatDate type="date" value="${courseMap.expire_time}" pattern="yyyy-MM-dd" />
			          						 
			          					 <input type="hidden" id="trained_time_start" value="">
			          					<input type="hidden" id="trained_time_expire" value="<fmt:formatDate type="date" value="${courseMap.expire_time}" pattern="yyyy-MM-dd" />">
										</c:when>
										<c:when test="${not empty courseMap.start_time && empty courseMap.expire_time}">
											<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd" />至
											<input type="hidden" id="trained_time_start" value="<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd" />">
			          					<input type="hidden" id="trained_time_expire" value="">
							
			          				
			          					</c:when>
			          					
							
			          					<c:otherwise>
			          			未设置
			          		</c:otherwise>
									</c:choose>
							</span>
							</li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="pt20 pl40 pr40">
			<div class="mess_left pull-left set-ip-left"
				style="margin-left: 0px;">
				<ul class="left_menu">
					<c:if test="${courseMap.is_publish != 1}">
						<li id="menu_0">
						<a href="javascript:onDetail('${courseId}')"><img alt="" src="${ctx}/resources/img/course/left_menu_0.png">编辑课程</a></li>
					</c:if>
					<c:if test="${courseType == 1}">
						<li id="menu_1" class="active">
						<a href="javascript:onSelectItems(1)"><img alt="" src="${ctx}/resources/img/course/left_menu_1_active.png">测验成绩</a></li>
					</c:if>
					<li id="menu_2"  <c:if test="${courseType != 1}"> class="active" </c:if>><a href="javascript:onSelectItems(2)">
					
						<c:if test="${courseType != 1}">
							<img alt="" src="${ctx}/resources/img/course/left_menu_2.png">
						</c:if>
						<c:if test="${courseType == 1}">
							<img alt="" src="${ctx}/resources/img/course/left_menu_2_active.png">
						</c:if>毕业考试</a></li>
					<%-- <c:if test="${v_course_remark=='10015'}"> --%>
					<li id="menu_3"><a href="javascript:onSelectItems(3)"><img alt="" src="${ctx}/resources/img/course/left_menu_3.png">问卷调查</a></li>
					<%-- </c:if> --%>
					<c:if test="${v_course_file=='10001' }">
					<li id="menu_4"><a href="javascript:onSelectItems(4)"><img alt="" src="${ctx}/resources/img/course/left_menu_4.png">相关文件</a></li>
					</c:if>
					<c:if test="${v_course_homework=='10004' }">
					<li id="menu_5"><a href="javascript:onSelectItems(5)"><img alt="" src="${ctx}/resources/img/course/left_menu_5.png">课后作业</a></li>
					</c:if>
					<c:if test="${v_course_notice=='10002' }">
					<li id="menu_6"><a href="javascript:onSelectItems(6)"><img alt="" src="${ctx}/resources/img/course/left_menu_6.png">发布通知</a></li>
					</c:if>
					<c:if test="${courseType == 1 && v_course_relate=='10003'}">
						<li id="menu_7"><a href="javascript:onSelectItems(7)"><img alt="" src="${ctx}/resources/img/course/left_menu_7.png">关联课程</a></li>
					</c:if>
					<%-- <c:if test="${courseType == 2}">
						<li id="menu_8"><a href="javascript:onSelectItems(8)"><img alt="" src="${ctx}/resources/img/course/left_menu_8.png">报名设置及审批</a></li>
					</c:if> --%>
					<!--           <li><a href="javascript:onSelectItems(7)" class="m2"></a></li>
                <li><a href="javascript:onSelectItems(8)" class="m3"></a></li> -->
				</ul>
			</div>

			<div class="mess_right pull-right tab-content"
				style="width: 920px; margin-right: 0px; min-height: 425px;">
				<!-- <div id="right_content_default" class="tuoru">选择右侧内容拖拽到此处</div> -->
				<!-- 测验成绩显示 -->
				<div id="right_content_test_quiz" style="display: none;">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36"
								onclick="loadTestQuizDataList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="testQuizKeyWord" class="form-control">
							</div>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 28%;">测验名称</th>
								<th class="text-center" style="width: 12%;">问题总数</th>
								<th class="text-center" style="width: 12%;">试卷总分</th>
								<th class="text-center" style="width: 12%;">通过人数</th>
								<th class="text-center" style="width: 12%;">测验人数</th>
								<th class="text-center" style="width: 24%;">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_test_quiz">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="course_setting_test_quiz_page_Panel">

					</div>
				</div>
				<!-- 毕业考试显示 -->
				<div id="right_content_quiz" style="display: none;">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36"
								onclick="loadQuizDataList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="quizKeyWord" class="form-control">
							</div>
						</div>
						<button type="button" class="pull-right btn btn_green btn36"
							data-toggle="modal" data-target="#quizModal">创建毕业考试</button>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 28%;">考试名称</th>
								<th class="text-center" style="width: 12%;">问题总数</th>
								<th class="text-center" style="width: 12%;">试卷总分</th>
								<th class="text-center" style="width: 12%;">课程人数</th>
								<th class="text-center" style="width: 12%;">考试人数</th>
								<th class="text-center" style="width: 24%;">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_quiz">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="course_setting_page_Panel">

					</div>
				</div>

				<!-- 问卷调查显示 -->
				<div id="right_content_survey" style="display: none;">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36"
								onclick="loadSurveyDataList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" class="form-control" id="surveyKeyWord">
							</div>
						</div>
						<button type="button" class="pull-right btn btn_green btn36"
							data-toggle="modal" data-target="#surveyModal">创建问卷调查</button>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 30%;">问卷调查名称</th>
								<th class="text-center">开始日期</th>
								<th class="text-center">结束日期</th>
								<th class="text-center">调查问题数</th>
								<th class="text-center">参与用户数</th>
								<th class="text-center">参与率%</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_survey">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="course_setting_survey_page_Panel">

					</div>
				</div>

				<!-- 相关文件显示 -->
				<div id="right_content_material" style="display: none;">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36"
								onclick="loadMaterialDataList()">&nbsp;</button>
							<div class="search_input">
								<input id="materialKeyWord" type="text" class="form-control">
							</div>
						</div>
						<c:if test="${v_upload_file=='10013' }">
						<button type="button" class="pull-right btn btn_green btn36"
							data-toggle="modal" data-target="#materialModal">上传文件</button>
						</c:if>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 30%;">名称</th>
								<th class="text-center">大小</th>
								<th class="text-center">创建时间</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_material">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="material_data_page_Panel">
					</div>
				</div>

				<!-- 课后作业显示 -->
				<div id="right_content_homework" style="display: none;">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36"
								onclick="loadHomeworkDataList()">&nbsp;</button>
							<div class="search_input">
								<input id="homeworkKeyWord" type="text" class="form-control">
							</div>
						</div>
						<button type="button" class="pull-right btn btn_green btn36"
							data-toggle="modal" data-target="#homeworkModal">创建作业</button>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 30%;">作业名称</th>
								<th class="text-center">发布日期</th>
								<th class="text-center">截收日期</th>
								<th class="text-center">创建日期</th>
								<th class="text-center">已交作业人数</th>
								<th class="text-center">状态</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_homework">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="homework_data_page_Panel">
					</div>
				</div>

				<div id="right_content_notice" style="display: none;">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36"
								onclick="loadNoticeDataList();">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="noticeKeyWord" class="form-control">
							</div>
						</div>
						<button type="button" class="pull-right btn btn_green btn36"
							data-toggle="modal" data-target="#noticeModal">发布通知</button>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 30%;">通知名称</th>
								<th class="text-center">发布日期</th>
								<th class="text-center">发布范围</th>
								<th class="text-center">创建日期</th>
								<th class="text-center">创建人</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_notice">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="notice_data_page_Panel"></div>
				</div>


				<div id="right_content_related_course" style="display: none;">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36"
								onclick="loadRelatedCourseDataList();">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="relatedKeyWord" class="form-control">
							</div>
						</div>
						<button type="button" class="pull-right btn btn_green btn36"
							data-toggle="modal" data-target="#relatedCourseModal">创建关联课程</button>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 30%;">课程名称</th>
								<th class="text-center">相关类型</th>
								<th class="text-center">线上／线下</th>
								<th class="text-center">创建日期</th>
								<th class="text-center">创建人</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_related_course">
						</tbody>
					</table>
				</div>

				<div id="right_content_registed_users" style="display: none;">
				</div>
				<!-- 测验成绩显示 -->
				<div id="right_content_manager_test_quiz" style="display: none;">
					<div>
						<!-- <input type="button" value="返回" class="course_manager_back"
							onclick="backManage('right_content_manager_test_quiz',1,'right_content_test_quiz');" /> -->
						<div class="go_back"  style="margin-top:-40px;" onclick="backManage('right_content_manager_test_quiz',1,'right_content_test_quiz');"><span class="icon-double-angle-down"></span></div>
					</div>
					<div class="course_setting_select_div">
						是否通过
						<div class="course_setting_select_select">
							<select id="course_test_quiz_pass">
								<option value="0">未通过</option>
								<option value="1">已通过</option>
								<option value="2" selected="selected">全部</option>
							</select>
						</div>
						试卷状态
						<div class="course_setting_select_select">
							<select id="course_test_quiz_type">
								<option value="1">新提交</option>
								<option value="2">已阅</option>
								<option value="0" selected="selected">全部</option>
							</select>
						</div>
						<div class="w240 pull-right">
							<button type="button" class="btn-success btn36"
								onclick="loadTestQuizManagerDataList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="managerTestQuizKeyWord" class="form-control">
							</div>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 10%;">序号</th>
								<th class="text-center" style="width: 18%;">姓名</th>
								<th class="text-center" style="width: 18%;">测验次数</th>
								<th class="text-center" style="width: 18%;">测验成绩</th>
								<th class="text-center" style="width: 18%;">测验结果</th>
								<th class="text-center" style="width: 18%;">最后一次测验时间</th>
							</tr>
						</thead>
						<tbody id="table_content_manager_test_quiz">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center"
						id="course_setting_manager_test_quiz_page_Panel"></div>
				</div>
				<!-- 成绩管理显示 -->
				<div id="right_content_manager_quiz" style="display: none;">
					<div>
						<div class="go_back"  style="margin-top:-40px;" onclick="backManage('right_content_manager_quiz',1,'right_content_quiz');"><span class="icon-double-angle-down"></span></div>
					<!-- <input type="button" value="返回" class="course_manager_back"
							onclick="backManage('right_content_manager_quiz',1,'right_content_quiz');" /> -->
					</div>
					<div class="course_setting_select_div">
						是否通过
						<div class="course_setting_select_select">
							<select id="course_quiz_pass">
								<option value="0">未通过</option>
								<option value="1">已通过</option>
								<option value="2" selected="selected">全部</option>
							</select>
						</div>
						试卷状态
						<div class="course_setting_select_select">
							<select id="course_quiz_type">
								<option value="1">新提交</option>
								<option value="2">已阅</option>
								<option value="0" selected="selected">全部</option>
							</select>
						</div>
						<div class="w240 pull-right">
							<button type="button" class="btn-success btn36"
								onclick="loadQuizManagerDataList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="managerQuizKeyWord" class="form-control">
							</div>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 10%;">序号</th>
								<th class="text-center" style="width: 15%;">姓名</th>
								<th class="text-center" style="width: 15%;">考试次数</th>
								<th class="text-center" style="width: 15%;">考试成绩</th>
								<th class="text-center" style="width: 15%;">考试结果</th>
								<th class="text-center" style="width: 15%;">最后一次考试时间</th>
								<th class="text-center" style="width: 15%;">试卷状态</th>
							</tr>
						</thead>
						<tbody id="table_content_manager_quiz">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center"
						id="course_setting_manager_page_Panel"></div>
				</div>
				<!-- 问卷调查结果显示 -->
				<div id="right_content_manager_survey" style="display: none;">
					<div>
						<!-- <input type="button" value="返回" class="course_manager_back"
							onclick="backManage('right_content_manager_survey',2,'right_content_survey');" /> -->
							<div class="go_back" style="margin-top:-40px;"  onclick="backManage('right_content_manager_survey',2,'right_content_survey');"><span class="icon-double-angle-down"></span></div>
					</div>
					<div class="course_setting_select_div">
						<div class="w240 pull-right">
							<button type="button" class="btn-success btn36"
								onclick="loadSurveyManagerDataList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="managerSurveyKeyWord"
									class="form-control">
							</div>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 10%;">序号</th>
								<th class="text-center" style="width: 20%;">姓名</th>
								<th class="text-center" style="width: 20%;">提交日期</th>
								<th class="text-center" style="width: 35%;"></th>
								<th class="text-center" style="width: 20%;">操作</th>
							</tr>
						</thead>
						<tbody id="table_content_manager_survey">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center"
						id="course_setting_manager_Surveypage_Panel"></div>
				</div>
				<!-- 作业管理显示 -->
				<div id="right_content_manager_homework" style="display: none;">
					<div>
					<div class="go_back" style="margin-top:-40px;"  onclick="backManage('right_content_manager_homework',3,'right_content_homework');"><span class="icon-double-angle-down"></span></div>
						<!-- <input type="button" value="返回" class="course_manager_back"
							onclick="backManage('right_content_manager_homework',3,'right_content_homework');" /> -->
					</div>
					<div class="course_setting_select_div">
						<div class="w240 pull-right">
							<button type="button" class="btn-success btn36"
								onclick="loadHomeworkManagerDataList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="managerHomeworkKeyWord" class="form-control">
							</div>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 10%;">序号</th>
								<th class="text-center" style="width: 15%;">姓名</th>
								<th class="text-center" style="width: 25%;">作业文件</th>
								<th class="text-center" style="width: 35%;">备注</th>
								<th class="text-center" style="width: 15%;">提交时间</th>


							</tr>
						</thead>
						<tbody id="table_content_manager_homework">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center"
						id="course_setting_manager_homeworkpage_Panel"></div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>

	<input type="hidden" id="courseId" name="courseId" value="${courseId}">
	<input type="hidden" id="courseType" name="courseType"
		value="${courseType}">
	<input type="hidden" id="startTime" name="startTime"
		value="${startTime}">
	<!-- 测验成绩数据 -->
	<script id="table_content_test_quiz_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
        <td>{{=isNullFormat(v.quiz_name)}}</td>
        <td class="text-center">{{=isNullFormat(v.couse_num)}}</td>
        <td class="text-center">{{=isNullFormat(v.score)}}</td>
        <td class="text-center">{{=isNullFormat(v.test_pass_num)}}</td>
        <td class="text-center">{{=isNullFormat(v.test_num)}}</td>
        <td class="text-center">
            <a href="javascript:loadManager(1,'{{=isNullFormat(v.id)}}');">查看成绩</a>
		</td>
    </tr>
 {{~}}
</script>
	<!-- 毕业考试数据 -->
	<script id="table_content_quiz_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
        <td>{{=isNullFormat(v.quiz_name)}}</td>
        <td class="text-center">{{=isNullFormat(v.quiz_num)}}</td>
        <td class="text-center">{{=isNullFormat(v.score)}}</td>
        <td class="text-center">{{=isNullFormat(v.course_num)}}</td>
        <td class="text-center">{{=isNullFormat(v.exam_num)}}</td>
        <td class="text-center">
            <button type="button" class="icon_1" onclick="editQuiz('{{=isNullFormat(v.id)}}');"></button>
            <button type="button" class="icon_2" onclick="deleteQuiz('{{=isNullFormat(v.id)}}');"></button>
            <a href="javascript:loadManager(2,'{{=isNullFormat(v.id)}}');">成绩管理</a>
		</td>
    </tr>
 {{~}}
</script>
	<!-- 问卷调查数据 -->
	<script id="table_content_survey_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
         <td>{{=isNullFormat(v.survey_name)}}</td>
         <td class="text-center">{{=dateFormat(v.survey_start_time)}}</td>
         <td class="text-center">{{=dateFormat(v.survey_end_time)}}</td>
         <td class="text-center">{{=isNullFormat(v.survey_num)}}</td>
         <td class="text-center">待定</td>
         <td class="text-center">待定</td>
         <td class="text-center">
             <button type="button" class="icon_1" onclick="editSurvey('{{=isNullFormat(v.id)}}');"></button>
             <button type="button" class="icon_2" onclick="deleteSurvey('{{=isNullFormat(v.id)}}');"></button>
			 <a href="javascript:loadManager(3,'{{=isNullFormat(v.id)}}');">调查结果</a>
			 
         </td>
     </tr>
 {{~}}	
</script>
	<!-- 课后作业数据 -->
	<script id="table_content_homework_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
	    <td>{{=isNullFormat(v.homework_name)}}</td>
	    <td class="text-center">{{=dateFormat(v.homework_publish_time)}}</td>
	    <td class="text-center">{{=dateFormat(v.homework_end_time)}}</td>
	    <td class="text-center">{{=dateFormat(v.create_time)}}</td>
	    <td class="text-center">0</td>
	    <td class="text-center"><button type="button" class="btn_act">
				{{?v.homework_is_publish == 1}}
				已发布
				{{??}}
				未发布
				{{?}}
			</button>
		</td>
	    <td class="text-center">
	    	<button type="button" class="icon_1" onclick="editHomework('{{=isNullFormat(v.id)}}');"></button>
	    	<button type="button" class="icon_2" onclick="deleteHomework('{{=isNullFormat(v.id)}}');"></button>
			<a href="javascript:loadManager(4,'{{=isNullFormat(v.id)}}');">作业管理</a>
			
	 	</td>
	</tr>
 {{~}}	
</script>

	<script id="table_content_notice_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
	    <td>{{=isNullFormat(v.notice_name)}}</td>
	    <td class="text-center">{{=dateFormat(v.notice_publish_time)}}</td>
	    <td class="text-center">
			{{?v.notice_publish_scope == 0}}未开始
			{{??v.notice_publish_scope == 1}}学习中
			{{??}}所有
			{{?}}
		</td>
	    <td class="text-center">{{=dateFormat(v.create_time)}}</td>
	    <td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
	                        
	    <td class="text-center">
	        <button type="button" class="icon_1" onclick="editNotice('{{=isNullFormat(v.id)}}');"></button>
	        <button type="button" class="icon_2" onclick="deleteNotice('{{=isNullFormat(v.id)}}');"></button>
	    	
		</td>
	 </tr>
 {{~}}	
</script>

	<script id="table_content_material_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
         <td>{{=isNullFormat(v.material_name)}}</td>
         <td class="text-center">{{=isNullFormat(v.material_size)}}</td>
         <td class="text-center">{{=dateFormat(v.create_time)}}</td>
         <td class="text-center">
             <button type="button" class="icon_1" onclick="editMaterial('{{=isNullFormat(v.id)}}');"></button>
             <button type="button" class="icon_2" onclick="deleteMaterial('{{=isNullFormat(v.id)}}');"></button>
         </td>
     </tr>
 {{~}}	
</script>

	<script id="table_content_related_course_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
	    <td>{{=isNullFormat(v.course_name)}}</td>
		<td class="text-center">
			{{?v.course_related_type == 0}}
				必要
			{{??}}
				推荐
			{{?}}
		</td>
	    <td class="text-center">
			{{?v.course_type == 0}}线上
			{{??v.course_type == 1}}线下
			{{??}}直播
			{{?}}
		</td>
	    <td class="text-center">{{=dateFormat(v.create_time)}}</td>
	    <td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
	                        
	    <td class="text-center">
	        <button type="button" class="icon_1" onclick="editRelatedCourse('{{=isNullFormat(v.related_id)}}');"></button>
	        <button type="button" class="icon_2" onclick="deleteRelatedCourse('{{=isNullFormat(v.related_id)}}');"></button>
	    </td>
	 </tr>
 {{~}}	
</script>

	<script id="right_content_registed_users_template" type="text/x-dot-template">
<form id="courseRegisteForm" method="post" >
 <div class="zb_title"><span class="glyphicon glyphicon-info-sign mr10"></span>若要在前台“课程中心”中显示该培训，您必须设置该培训项目为可报名状态且在允许报名的时间内。</div>
 <div class="pxshijian mt20 pb10 wb100">
  	<div class="checkbox-group" style="margin-top:11px">
        <input type="checkbox" name="isRegisteDesk" id="isRegisteDesk" value="1" {{?it.isRegisteDesk == 1 }} checked="checked" {{?}}>
        <label class="checkbox-2" for="isRegisteDesk"></label>
      </div>
      <span class="w200" style="width: 220px ! important;">开启学员前台报名（需要审核）</span>
      
      <span class="w100 text-right">报名起至时间：</span>
      <div class="w180">
<input type="text" class="form-control date_btn form_datetime"	name="startTime" id="startTime" style="height: 36px;"
									aria-describedby="basic-addon1" value="{{=dateFormat(it.startTime)}}">
</div>
      <div class="line50"></div>
      <div class="w180">
<input type="text" class="form-control date_btn form_datetime"	name="expireTime" id="expireTime" style="height: 36px;"
									aria-describedby="basic-addon1" value="{{=dateFormat(it.expireTime)}}">
</div>
	  <input type="hidden" value="{{=isNullFormat(it.id)}}" id ="registedUsersId">
  </div>
 </div>

	<div class="text-right mt20 mb10 pull-right wb100">
      <button type="button" class="btn submit btn_160_36" btn-type='true'>提交</button>
  </div>

</form>
</script>
	<!-- 成绩管理数据 -->
	<script id="right_content_manager_quiz_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		 <td class="text-center">{{=(index+1)}}</td>
         <td class="text-center">{{=isNullFormat(v.user_name)}}</td>
         <td class="text-center">{{=isNullFormat(v.quiz_num)}}</td>
         <td class="text-center">{{=isNullFormat(v.score)}}</td>
         <td class="text-center">
			 {{?v.is_pass == 1}}
				通过
				{{??}}
				未通过
				{{?}}
		 </td>
         <td class="text-center">
			{{=dateFormat(v.last_quiz_time)}}
		 </td>
         <td class="text-center">
			{{?v.status == 1}}<a href="readQuizPaper('{{=isNullFormat(v.id)}}')">待阅卷</a>
				{{??}}
				已阅
				{{?}}
		 </td>
     </tr>
 {{~}}	
</script>
	<!-- 成绩管理数据 -->
	<script id="right_content_manager_test_quiz_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		 <td class="text-center">{{=(index+1)}}</td>
         <td class="text-center">{{=isNullFormat(v.user_name)}}</td>
         <td class="text-center">{{=isNullFormat(v.quiz_num)}}</td>
         <td class="text-center">{{=isNullFormat(v.score)}}</td>
         <td class="text-center">
			 {{?v.is_pass == 1}}
				通过
				{{??}}
				未通过
				{{?}}
		 </td>
         <td class="text-center">
			{{=dateFormat(v.last_quiz_time)}}
		 </td>
         <td class="text-center">
			{{?v.status == 1}}<a href="readQuizPaper('{{=isNullFormat(v.id)}}')">待阅卷</a>
				{{??}}
				已阅
				{{?}}
		 </td>
     </tr>
 {{~}}	
</script>
	<!-- 问卷调查管理数据 -->
	<script id="right_content_manager_survey_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		 <td class="text-center">{{=(index+1)}}</td>
         <td class="text-center">{{=isNullFormat(v.user_name)}}</td>
         <td class="text-center">{{=dateFormat(v.create_time)}}</td>   
 		 <td class="text-center"></td>
		 <td class="text-center"><a href=''>查看</a></td>
     </tr>
 {{~}}	
</script>
<!-- 课后作业管理数据 -->
	<script id="right_content_manager_homework_template"
		type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		 <td class="text-center">{{=(index+1)}}</td>
         <td class="text-center">{{=isNullFormat(v.user_name)}}</td>
         <td class="text-center">{{=isNullFormat(v.attach_upload)}}</td>   
 		 <td class="text-center">{{=isNullFormat(v.remark)}}</td>
		 <td class="text-center">{{=dateFormat(v.create_time)}}</td>
     </tr>
 {{~}}	
</script>

	<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
	<%@ include file="/WEB-INF/views/modules/course/course_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require([ 'modules/course/course_setting' ], function(obj) {
			
		});
	</script>
</body>
</html>