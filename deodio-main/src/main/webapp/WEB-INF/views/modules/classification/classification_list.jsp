<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/knowledge/knowledge.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content g_border">
		<div class="pre_normal">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
					<div class="pre_right_top_fl">
						<div class="pre_left_top_fl_tab">
							<div class="top_form text-right">
								<button type="button" value="创建分类" class="btn_add btn_blue pull-right" onclick="createClassification();">创建分类</button>
								<button type="button" value="批量删除" class="btn_add btn_red mr10 pull-right" id="delAllKnowledgePoints" onclick="delAllClassifications();">批量删除</button>
								<div class="w240 pull-right mr20">
									<button type="button" class="btn-success btn36" onClick="javascript:searchClassificationList()">&nbsp;</button>
									<div class="search_input"><input type="text" id="keywords" class="form-control" ></div>	
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<!--pre_left_top_fl end-->
					<div class="clearfix"></div>
					<div class="con-corner"></div>
				</div>
	   </div>
	
	
	
		<div id="myTabContent" class="tab-content" style="overflow:hidden;">
			<div class="tab-pane active" id="tiku" style="position:relative;">
				<div class="l_menu pull-left sidebar" style="left:-380px;" id="div_sidbar">
				   <div class="text-right refresh pr10">
						<span class="pull-left text">分类列表</span>
						<span class="glyphicon glyphicon-refresh" onClick="refreshBanksList();" ></span>
						<span class="glyphicon glyphicon-menu-left ml20" onClick="javascript:closeSidebar2()"></span>
						<div class="clearfix"></div>
					</div>
					<div id="classificationPanelDiv" style="max-height:550px;width:100%;overflow-y:scroll;min-height:220px;">
						<ul id="classificationPanel" class="ztree"></ul>
					</div>
				</div>
				<!-- 左侧收起样式 -->
				<div class="s_menu pull-left" id="div_hidd" style="left:0px;">
					<a onclick="hiddenLeft2()" href="javascript:;"><span class="glyphicon glyphicon-menu-right"></span>点<br/>击<br/>展<br/>开<br/>分<br/>类<br/>列<br/>表</a>
				</div> 
				
				<div class="l_right" id="div_tables" style="padding-left:60px;padding-right:20px;">
						<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
							<thead>
								<tr>
									<th class="text-center" style="width:8%">
										<div class="checkbox-group">
				            				<input type="checkbox" name="allClassificationsId" id="allClassificationsId">
				             				<label class="checkbox-2" for="allClassificationsId"></label>
						        		</div>
									</th>
									<th class="text-center" style="width:30%;">分类名称</th>
									<th class="text-center" style="width:10%;">创建人</th>
									<th class="text-center" style="width:15%;">创建时间</th>
									<th class="text-center">操作</th>
									<th class="text-center">知识点管理</th>
								</tr>
							</thead>
						
							<tbody id="classification_panle" ></tbody>
						</table>
						
						<div class="mt20 text-center" id="data_page_Panel">
						
						</div>

				</div>
				<div class="clearfix"></div>
			</div>	
		</div>
	</div>


<input type="hidden" name="treeNodeId" id="treeNodeId" value="0">
<input type="hidden" name="treeNodeIdName" id="treeNodeIdName" value="0">

<script id="classification_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
		<tr>
			<td class="text-center">
				<div class="checkbox-group">
          				<input type="checkbox" name="classificationsId" cName="{{=isNullFormat(v.classification_name)}}" id="{{=isNullFormat(v.id)}}" onclick="isSelectAll('allClassificationsId','classificationsId');">
           				<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
        		</div>
			</td>
			<td>{{=isNullFormat(v.classification_name)}}</td>
			<td class="text-center">{{=isNullFormat(v.username)}}</td>
			<td class="text-center">{{=dateFormat(v.create_time)}}</td>									
			<td class="text-center">
				<button type="button" class="icon_1" onclick="editClassification('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} disabled=disabled {{?}}></button>
				<button type="button" class="icon_2" onclick="delClassification('{{=isNullFormat(v.id)}}','{{=isNullFormat(v.classification_name)}}');" {{? v.create_id != v.current_user_id }} disabled=disabled {{?}}></button>
			</td>
			<td class="text-center">
				<button type="button" class="btn_act" onclick="manageKnowledgeList('{{=isNullFormat(v.id)}}','{{=isNullFormat(v.classification_name)}}');">知识点管理</button>
			</td>
		</tr>
 {{~}}	
</script>

<script id="pop_classification" type="text/x-dot-template">
<form method="post" id="createClassification">
	<div class="form-inline">

		<ul class="shezhi b_none">
			<li>
				<div class="pull-left text-right w140 pt10 input-title-color" >分类名称：</div>
				<input type="text" value="{{=it.classificationName}}" class="form-control w400" data-callback="checkName()" call-message="当前分类名已被注册" id="classificationName" name="classificationName" check-type="required" required-message="请输入分类名称！" placeholder="请输入分类名称" aria-describedby="basic-addon1">
				<input type="hidden" id="classificationId" name="classificationId" value="{{=it.id}}"/>
                <input type="hidden" value="{{=it.classificationName}}" id="hideenclassificationName">
			</li>
			<li>
				<div class="pull-left text-right w140 pt10 input-title-color">分类描述：</div>
				<div class="r_con pt10">
					<textarea class="jj_bjq pull-left form-control" name="classificationDesc" id="classificationDesc" style="height: 100px; padding: 10px; margin-top: -10px; width: 400px;" placeholder="请输入分类描述" aria-describedby="basic-addon1">{{=it.classificationDesc}}</textarea>
				</div>
			</li>
		</ul>
	</div>
	<div class="modal-footer" id="popupModelFooter">
		<button class="btn submit" type="button" btn-type="true">提交</button>
		<button type="button" class="btn btn_gray" data-dismiss="modal">取消</button>
	</div>
</form>
</script>

<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/classification/classification_list'],function(){
			
		});
	</script>


</body>
</html>