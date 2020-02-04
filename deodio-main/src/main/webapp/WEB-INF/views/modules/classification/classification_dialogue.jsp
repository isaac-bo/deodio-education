<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" name="treeNodeId" id="treeNodeId" value="0">
<input type="hidden" name="treeNodeIdName" id="treeNodeIdName" value="0">
<!-- 所属分类弹出窗口  start -->
<script id="classification_data_template" type="text/x-dot-template">
<div class="form-inline" style="min-height:365px;max-height:365px;">
	<div class="pull-left up_lmenu">
		<h3>点击以下按钮操作</h3>
		<div class="w240 search">
			<button type="button" class="btn-success btn36" onclick="queryClassificationList();">&nbsp;</button>
			<div class="search_input"><input type="text" class="form-control" style="width:100%;" id="queryClassificationText"></div>	
		</div>
		<div class="mt10">
			<div id="classificationListPanel" style="min-height:260px;max-height:260px;width:100%;">
				<ul id="presentationClassificationPanel" class="ztree"></ul>
			</div>
			<div id="classificationSearchPanel" style="min-height:260px;max-height:260px;width:100%;display:none;">
			</div>
		</div>
	</div>
	<div class="up_right pull-right" style="width: 420px;">
		<h3 class="kc_title">
			<div class="glyphicon glyphicon-info-sign ml20 mr10"></div>分类名称
		</h3>
		<div style="min-height:150px;max-height:150px" id="checkedGroup" class="pt10">
		</div>

		<h3 class="kc_title pb10">
			<div class="glyphicon glyphicon-info-sign ml20 mr10"></div>分类描述
		</h3>
		<div class="txt pt10" id="treeNodeDesc">
							
		</div>
	</div>
	<div class="clearfix"></div>
</div>
<div class="modal-footer" id="popupModelFooter">
	<button class="btn submit btn_160_36" type="button" onclick="saveClassificationList()">添加</button>
	<button class="btn cancel btn_160_36" type="button" data-dismiss="modal">取消</button>
</div>
</script>


<script id="classification_search_template" type="text/x-dot-template">
	<ul class="ztree ml10">
	{{~it.data:v:index}}
		<li id="presentationClassificationPanel_{{=v.id}}" class="level0" tabindex="0" hidefocus="true" treenode="" onclick="onSelectClassification('{{=v.id}}','{{=v.name}}')">
			<span id="presentationClassificationPanel_{{=v.id}}_check" class="button chk radio_false_full" treenode_check=""></span>
				<a id="presentationClassificationPanel_{{=v.id}}_a" class="level0" treenode_a="" onclick="" target="_blank" style="" title="{{=v.name}}">
					<span id="presentationClassificationPanel_{{=v.id}}_ico" title="" treenode_ico="" class="button ico_close" style=""></span>
					<span id="presentationClassificationPanel_{{=v.id}}_span" class="node_name">{{=v.name}}</span>
				</a> <!--curSelectedNode -->
		</li>
	{{~}}
	</ul>
</script>
<script type="text/javascript">
	require(['modules/classification/classification_dialogue','modules/tags/tags_dialogue'],function(){
			
	});
</script>