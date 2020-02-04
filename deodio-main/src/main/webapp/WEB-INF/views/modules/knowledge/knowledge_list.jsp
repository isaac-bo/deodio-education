<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/knowledge/knowledge.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<input type="hidden" name="classificationId" id="classificationId" value="${classificationId}"/> 
	<input type="hidden"  id="classificationName" value="${classificationName}"/>
	<input type="hidden"  id="returnflag" value="${returnflag}"/>
	<div class="content g_border" >
		<div class="pre_normal">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
					<%-- <div class="con-left" id="returnbutton"><img onclick="javascript:onJump2Classification()" src="${ctx}/resources/img/goback.png"></img></div> --%>
					<div class="pre_right_top_fl">
						<div class="pre_left_top_fl_tab">
							<div class="top_form text-right">
								<button type="button" value="创建知识" class="btn_add btn_blue pull-right" onclick="createKnowledgePoint();">创建知识</button>
								<button type="button" value="批量删除" class="btn_add btn_red mr10 pull-right" id="delAllKnowledgePoints" onclick="delAllKnowledgePoints();">批量删除</button>
								<div class="w240 pull-right mr20">
									<button type="button" class="btn-success btn36" onClick="javascript:searchKnowledgePointList()">&nbsp;</button>
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
	   
		<div id="myTabContent" class="tab-content p20" style="overflow:hidden;padding-top:0px !important;">
			<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
				<thead>
					<tr>
						<th class="text-center" style="width:8%">
							<div class="checkbox-group">
	            				<input type="checkbox" name="allKnowledgePointsId" id="allKnowledgePointsId">
	             				<label class="checkbox-2" for="allKnowledgePointsId"></label>
			        		</div>
						</th>
						<th class="text-center" style="width:30%;">知识点名称</th>
						<th class="text-center" style="width:25%;">所属分类</th>
						<th class="text-center" style="width:15%;">创建人</th>
						<th class="text-center" style="width:15%;">创建时间</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody id="table_panle" ></tbody>
			</table>
			
			<div class="mt20 text-center" id="data_page_Panel">
			
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
<script id="knowledge_data_template" type="text/x-dot-template">

 {{~it.data:v:index}}
		<tr>
			<td class="text-center">
				<div class="checkbox-group">
          				<input type="checkbox" name="knowledgePointsId" id="{{=isNullFormat(v.id)}}" onclick="isSelectAll('allKnowledgePointsId','knowledgePointsId');">
           				<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
        		</div>
			</td>
			<td>{{=isNullFormat(v.knowledge_point_name)}}</td>
			<td class="text-center">{{=isNullFormat(v.classification_name)}}</td>		
			<td class="text-center">{{=isNullFormat(v.username)}}</td>
			<td class="text-center">{{=dateFormat(v.create_time)}}</td>									
			<td class="text-center">
				<button type="button" class="icon_1" onclick="editKnowledgePoint('{{=isNullFormat(v.id)}}','{{=isNullFormat(v.classification_id)}}');"></button>
				<button type="button" class="icon_2" onclick="delKnowledgePoint('{{=isNullFormat(v.id)}}','{{=isNullFormat(v.classification_id)}}');"></button>
			</td>
		</tr>
 {{~}}	

</script>


<script id="pop_template" type="text/x-dot-template">
<form method="post" id="knowledgePointsForm">
	<div class="form-inline">

		<ul class="shezhi b_none">
			<li>
				<div class="pull-left text-right w140 h40 input-title-color">知识点名称：</div>
				<input type="text" value="{{=it.knowledgePointName}}" class="form-control w400 h40" id="knowledgePointName" name="knowledgePointName" check-type="required" required-message="请输入知识点名称！" placeholder="请输入知识点名称" aria-describedby="basic-addon1">
				<input type="hidden" id="knowledgePointId" name="knowledgePointId" value="{{=it.id}}"/>
 				<input type="hidden" value="{{=it.knowledgePointName}}" id="hiddenknowledgePointName">
			</li>
			<li>
				<div class="pull-left text-right w140 h40 input-title-color">所属分类：</div>
				<div class="fenlei_1" id="presentationGeneres" check-type="classification" style="min-width:400px;">
							<button type="button"  id="btnPopClassificationPicker" class="btn_add" onclick="popClassificationPicker(treeLocalSetting);"></button>
							{{? isNullFormat(it.classificationId) == ''}}
												
							<c:forEach items="${selectedClassificationList}" var="item">
								<span class="sel_btn">
									${item.classification_name}
									<button type="button" class="sel_del" id="delclassbutton" classification="${item.id}" onclick="delLabel(this);">&times;</button></span>
							</c:forEach>
							{{??}}
								<span class="sel_btn">
									{{=it.classificationName}}
									<button type="button" class="sel_del" classification="{{=it.classificationId}}"></button></span>
							{{?}}
							<div class="clearfix" id="genereNodes"></div>
						</div>
			</li>
			<li>
				<div class="pull-left text-right w140 h40 input-title-color">知识点描述：</div>
				<div class="r_con pt10">
					<textarea class="jj_bjq pull-left form-control" name="knowledgePointDesc" id="knowledgePointDesc" style="height: 100px; padding: 0px; margin-top: -10px; width: 400px;"  placeholder="请输入知识点描述" aria-describedby="basic-addon1">{{=it.knowledgePointDesc}}</textarea>
				</div>
			</li>
		</ul>
	</div>
	<div class="modal-footer" id="popupModelFooter">
		<button class="btn submit" type="button" btn-type='true' >提交</button>
		<button type="button" class="btn btn_gray" data-dismiss="modal">取消</button>
	</div>
</form>
</script>
<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/knowledge/knowledge_list'],function(){
			
		});
	</script>


</body>
</html>