<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<body>
	<div class="content" style="">
	<%@ include file="/WEB-INF/views/modules/quizs/quiz_top_menu.jsp"%>
		<div id="myTabContent" class="content tab-content">
			<div class="pre_center" style="position:absolute;">
				<div class="con-mask"></div>
				<div class="con" style="min-height: 20px;">
						<div id="div_tables_bar" class="pre_left_top_fl" style="width:1205px;">
							<button type="button" class="btn btn_green ml20 btn36" onclick="create()">新建题库</button>
							<button type="button" class="btn btn_red ml10 btn36" id="" onclick="deleteBatch()">批量删除</button>
							<div class="w240 pull-right mr20">
								<button type="button" class="btn-success btn36" onClick="javascript:loadDataList()">&nbsp;</button>
								<div class="search_input"><input type="text" id="keywords" class="form-control" ></div>	
							</div>
						</div>
						<div id="div_tables_subject_bar" class="pre_left_top_fl" style="width:1205px; display:none;">
							<div class="w240 mr20">
								<span class="pull-left mt10 mr10">题型</span>
								<select name="" id="quiz_subject_type_select" class="selectpicker " onchange="javascript:loadSubjectDataList(1)">
									<option value="0">请选择</option>
					    			<option value="1">单选题</option>
					    			<option value="2">多选题</option>
					    			<option value="3">判断题</option>
					    			<option value="4">排序题</option>
					    			<option value="5">图文简答题</option>
					    			<option value="6">文字简答题</option>
					    			<option value="7">填空题</option>
								</select>
							</div>
							<div class="w240 mr20">
								<span class="pull-left mt10 mr10">难度</span>
								<select name="" id="quiz_subject_level_select" class="selectpicker " onchange="javascript:loadSubjectDataList(1)">
									<option value="0">请选择</option>
									<option value="1">容易</option>
									<option value="2">较易</option>
									<option value="3">中等</option>
									<option value="4">较难</option>
									<option value="5">难</option>
								</select>
							</div>
							<div class="w240 pull-right mr20"">
								<button type="button" class="btn-success btn36" onClick="javascript:loadSubjectDataList(1)">&nbsp;</button>
								<div class="search_input">
									<input type="text" class="form-control" id="subjet_keywords">
								</div>
							</div>
						</div>
						<!--pre_left_top_fl end-->
						<div class="clearfix"></div>
						<div class="con-corner"></div>
					</div>
		   </div>
		   <div class="quiz_contents" >
			<div class="tab-pane active" id="tiku" style="position:relative;">
				<div class="l_menu pull-left sidebar" style="left:-380px;" id="div_sidbar">
				   <div class="text-right refresh pr10">
						<span class="pull-left text">题库分类</span>
						<span class="glyphicon glyphicon-refresh" onClick="refreshBanksList();" ></span>
						<span class="glyphicon glyphicon-menu-left ml20" onClick="javascript:closeSidebar2()"></span>
						<div class="clearfix"></div>
					</div>
					<div id="classificationPanelDiv" style="max-height:350px;min-height:220px;width:100%;overflow-y:scroll;">
						<ul id="classificationPanel" class="ztree"></ul>
					</div>
				</div>
				<!-- 左侧收起样式 -->
				<div class="s_menu pull-left" id="div_hidd" style="left:0px;">
					<a onclick="hiddenLeft2()" href="javascript:;"><span class="glyphicon glyphicon-menu-right"></span>点<br/>击<br/>展<br/>开<br/>题<br/>库<br/>分<br/>类<br/>列<br/>表</a>
				</div> 
				<form id="quiz_bank_list">
					<div id="div_tables"  class="l_right" style="padding-left:60px;min-height:800px;">
						<div class="top_form text-right">
						<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
							<thead>
								<tr>
									<th class="text-center" style="width:8%">
										<div class="checkbox-group">
				            				<input type="checkbox" name="quizBankHead" id="quizBankHead">
				             				<label class="checkbox-2" for="quizBankHead"></label>
						        		</div>
									</th>
									<th class="text-center" style="width:30%;">题库名称</th>
									<th class="text-right" style="width:10%;">是否私有</th>
									<th class="text-center" style="width:15%;">所属分类</th>
									<th class="text-center">题目数量</th>
									<th class="text-center">操作</th>
									<th class="text-center">题目管理</th>
								</tr>
							</thead>
							<tbody id="table_panle" ></tbody>
						</table>
						<div class="mt20 text-center" id="data_page_Panel">
						</div>
					</div>
					</div>
				</form>
				<form id="quizSubjectForm">
					<div class="l_right" id="div_tables_subject" style="display: none; padding-left: 400px;">
						<div class="top_form text-right mt20">
							<button type="button" class="ti_icon2 pull-left ml10" onclick="popQuizsDialogue(1);"></button>
							<button type="button" class="ti_icon1 pull-left ml10" onclick="popQuizsDialogue(2);"></button>
							<button type="button" class="ti_icon3 pull-left ml10" onclick="popQuizsDialogue(3);"></button>
							<button type="button" class="ti_icon4 pull-left ml10" onclick="popQuizsDialogue(4);"></button>
							<button type="button" class="ti_icon5 pull-left ml10" onclick="popQuizsDialogue(5);"></button>
							<button type="button" class="ti_icon6 pull-left ml10" onclick="popQuizsDialogue(6);"></button>
							<button type="button" class="ti_icon7 pull-left ml10" onclick="popQuizsDialogue(7);"></button>
							<button type="button" value="上传试题" class="btn_blue">上传试题</button>
							<button type="button" value="导出试题" class="btn_blue ml20">导出试题</button>
						</div>
						<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt10">
							<thead>
								<tr>
									<th class="text-center" style="width: 8%">
										<div class="checkbox-group">
											<input type="checkbox" name="quizSubjectHead" id="quizSubjectHead"> <label
												class="checkbox-2" for="quizSubjectHead"></label>
										</div>
									</th>
									<th class="text-center" style="width: 30%;">题目</th>
									<th class="text-center" style="width: 10p%;">题型</th>
									<th class="text-center" style="width: 15%;">难度</th>
									<th class="text-center">知识点名称</th>
									<th class="text-center">过期时间</th>
									<th class="text-center">操作</th>
								</tr>
							</thead>
							<tbody id="table_panle_subject"></tbody>
						</table>
						<div class="mt20 text-center" id="data_page_Panel_subject"></div>
					</div>
				</form>
				<div class="clearfix"></div>
			</div>
			</div>
		</div>
	<input type="hidden" name="quizBankId" id="quizBankId" value="0">
</div>

<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
		<tr>
			<td class="text-center">
				<div class="checkbox-group">
          			<input type="checkbox" name="quizBank" id="{{=isNullFormat(v.id)}}" onclick="isSelectAll('quizBankHead','quizBank');">
           			<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
        		</div>
			</td>
			<td class="text-center"title="{{=isNullFormat(v.quizBankName)}}">{{=subString(isNullFormat(v.course_name),15)}}</td>
			<td class="text-right"><button type="button" class="{{? v.isPrivate == 1}}btn_edit{{??}}btn_publish{{?}}">{{? v.isPrivate == 1}}私有{{??}}公有{{?}}</button></td>
			<td class="text-center">{{=isNullFormat(v.classificationName)}}</td>									
			<td class="text-right">{{=isNullFormat(v.subjectCount)}}</td>
			<td class="text-center">
                {{? v.createId == '${currentUserId}' }} {{? v.id != 999999}}
				<button type="button" class="icon_1" onclick="create('{{=isNullFormat(v.id)}}');"></button>
				<button type="button" class="icon_2" onclick="deleteQuizBanks('{{=isNullFormat(v.id)}}');"></button>
                {{?}} {{?}}
			</td>
			<td class="text-center"><button type="button" class="btn_act" onclick="manageQuizSubjectList('{{=isNullFormat(v.id)}}');">题目管理</button></td>
		</tr>
 {{~}}	
</script>

<script id="table_data_subject_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<div class="checkbox-group">
         				<input type="checkbox" name="quizSubject" id="{{=isNullFormat(v.id)}}" onclick="isSelectAll('quizSubjectHead','quizSubject');">
          				<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
       		</div>
		</td>
		<td>{{=isNullFormat(v.quiz_subject_name)}}</td>
		<td class="text-center">{{? v.quiz_subject_type == 2}}多选题{{?}}{{? v.quiz_subject_type == 1}}单选题{{?}}{{? v.quiz_subject_type == 3}}判断题{{?}}{{? v.quiz_subject_type == 4}}排序题{{?}}{{? v.quiz_subject_type == 5}}图文简答题{{?}}{{? v.quiz_subject_type == 6}}文字简答题{{?}}{{? v.quiz_subject_type == 7}}填空题{{?}}</td>
		<td class="text-center">{{? v.quiz_subject_level == 1}}容易{{?}}{{? v.quiz_subject_level == 2}}较易{{?}}{{? v.quiz_subject_level == 3}}中等{{?}}{{? v.quiz_subject_level == 4}}较难{{?}}{{? v.quiz_subject_level == 5}}难{{?}}</td>
		<td class="text-center">{{=isNullFormat(v.knowledge_point_name)}}</td>
		<td class="text-center c929292">
			{{=dateFormat(v.quiz_subject_expire_time)}}
		</td>
		<td class="text-center">
            {{? v.create_id == '${currentUserId}' }}
			<button type="button" class="icon_1" onclick="editQuizSubjects('{{=isNullFormat(v.id)}}');" ></button>
			<button type="button" class="icon_2" onclick="deleteQuizSubjects('{{=isNullFormat(v.id)}}');" ></button>
            {{?}}
			<button type="button" class="icon_3" onclick="viewQuizSubjects('{{=isNullFormat(v.id)}}');"></button>
		</td>
	</tr>
 {{~}}	
</script>

<script id="bank_template" type="text/x-dot-template">
<form method="post" id="createQuizBankForm">
<div class="form-inline">
	<input type="hidden" name="quizBankId" id="quizBankId">
	<div class="form-group">
		<label class="input-title input-title-color"><span class="input-title-span">＊</span>题库名称</label>
		<input type="text" class="form-control" name="quizBankName" id="quizBankName"
			check-type="required" required-message="请输入题库名称！" placeholder="请输入题库名称"
			data-callback="checkBankName()" call-message="此题库名称已存在！" 
			maxlength="10" autocomplete="off" style="width:406px;">
		<input id="oldQuizBankName" name="quizBankName" type="hidden">
	</div>
	<div class="form-group">
		<label class="input-title input-title-color"><span class="input-title-span">＊</span>题库描述</label>
		<textarea class="jj_bjq pull-left form-control ml3" name="quizBankDesc" id="quizBankDesc"
			check-type="required" required-message="请输入题库描述！" placeholder="请输入题库描述"
			style="height:120px;width:405px;" maxlength="200"></textarea>
	</div>
	<div class="form-group">
		<label class="input-title input-title-color">所属分类</label>
		<div class="jj_bjq pull-left ml3">
			<span class="btn_act l_36" id="span_classification_name"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="input-title" style="border-right-width: 0px;"></label>
		<div class="jj_bjq pull-left ml3">
			<div class="l_chickbox l_36">
				<div class="checkbox-group">
					<input type="checkbox" name="isPrivate" id="isPrivate">
					<label class="checkbox-2" for="isPrivate"></label>
				</div>	
				&nbsp;设置为私有题库	
			</div>
		</div>
	</div>
</div>
<div class="modal-footer" id="popupModelFooter">
	<button type="button" class="btn btn_green btn_160_36" btn-type="true">提交</button>
	<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
</div>
</form>
</script>

<!-- <input type="hidden" name="treeNodeId" id="treeNodeId" value="0"> -->
<input type="hidden" name="treeNodeId" id="treeNodeId">
<!-- <input type="hidden" name="treeNodeIdName" id="treeNodeIdName" value="0"> -->
<input type="hidden" name="treeNodeIdName" id="treeNodeIdName">

<%@ include file="/WEB-INF/views/modules/quizs/quiz_dialogue.jsp" %>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<script type="text/javascript">
	require(['modules/quizs/quiz','modules/quizs/quiz_bank_list'],function(){
		
	});
</script>
</body>
</html>