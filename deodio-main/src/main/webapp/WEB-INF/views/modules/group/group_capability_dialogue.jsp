<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="groupToolModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id = "groupToolModalContent">
		</div>
	</div>
</div>
<div class="modal fade" id="groupCapabilityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id = "groupCapabilityModalContent">
		</div>
	</div>
</div>
<input type="hidden" id="capabilityType" value="1"/>
<input type="hidden" id="groupId" value="${groupId}"/>
<script type="text/javascript">
	require(['modules/group/group_capability_dialogue','modules/group/group_capability'],function(){
		
	});
</script>
<script id="capability_item_tool_data_template" type="text/x-dot-template">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >{{=it.modal_tittle}}</h4>
</div>
<div class="modal-body hdn">
<div class="hdn">
    <div class="w240 pull-right">
		<button type="button" class="btn-success btn36" onclick="searchCapabilityItemToolToSelectList();">&nbsp;</button>
		<div class="search_input"><input type="text" class="form-control" id="groupToolsKeywords"></div>
	</div>
</div>
<div style="height: 380px;">
	<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20">
  		<thead>
     		<tr>
       			<th style="width:40px;">
         			<div class="checkbox-group">
           				<input type="checkbox" name="allCapabilityToolItem" id="allCapabilityToolItem" onclick="onSelectAll(this)">
           				<label class="checkbox-2" for="allCapabilityToolItem" ></label>
        		 	</div>
       			</th>
       			<th>名称</th>
	   			<th>创建时间</th>
     		</tr>
    	</thead>
    	<tbody id="capabilityToolBody">  
    	</tbody>
	</table>
        <!-- 分页 -->
		<div class="mt20 text-center" id="group_capability_tool_to_select_data_page_Panel">
		</div>
</div>
</div>
<div class="modal-footer" id="popupModelFooter">
	<button class="btn submit btn_160_36" type="button" onclick="onSelectCapabilityToolItem(1)">添加</button>
	<button class="btn cancel btn_160_36" type="button" data-dismiss="modal">取消</button>
</div>
</script>
<script id="capability_item_tool_data_content_template" type="text/x-dot-template">
   {{~it.data:v:index}}
         <tr>
	      <td>
    	   <div class="checkbox-group">
        	<input type="checkbox" name="capabilityToolItem" id="{{=v.id}}" onclick="isSelectAll('allCapabilityToolItem','capabilityToolItem');">
        	<label class="checkbox-2" for="{{=v.id}}"></label>
    	   </div>
	     </td>
	   <td>{{=v.capability_item}}</td>
	   <td>{{=dateFormat(v.create_time)}}</td>
    </tr>
    {{~}}
</script>
<script id="capability_item_data_template" type="text/x-dot-template">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >{{=it.modal_tittle}}</h4>
</div>
<div class="modal-body hdn">
<div class="hdn">
    <div class="w240 pull-right">
		<button type="button" class="btn-success btn36" onclick="searchCapabilityItemToSelectList(2);">&nbsp;</button>
		<div class="search_input"><input type="text" class="form-control" id="groupCapabilityKeywords"></div>
	</div>
</div>
<div style="height: 380px;">
	<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20">
  		<thead>
     		<tr>
       			<th style="width:40px;">
         			<div class="checkbox-group">
           				<input type="checkbox" name="allCapabilityItem" id="allCapabilityItem" onclick="onSelectAll(this)">
           				<label class="checkbox-2" for="allCapabilityItem" ></label>
        		 	</div>
       			</th>
       			<th>名称</th>
	   			<th>创建时间</th>
     		</tr>
    	</thead>
    	<tbody id="capabilityBody">  
    	</tbody>
	</table>
        <!-- 分页 -->
		<div class="mt20 text-center" id="group_capability_to_select_data_page_Panel">
		</div>
</div>
</div>
<div class="modal-footer" id="popupModelFooter">
	<button class="btn submit btn_160_36" type="button" onclick="onSelectCapabilityItem(2)">添加</button>
	<button class="btn cancel btn_160_36" type="button" data-dismiss="modal">取消</button>
</div>
</script>
<script id="capability_item_data_content_template" type="text/x-dot-template">
   {{~it.data:v:index}}
         <tr>
	      <td>
    	   <div class="checkbox-group">
        	<input type="checkbox" name="capabilityItem" id="{{=v.id}}" onclick="isSelectAll('allCapabilityItem','capabilityItem');">
        	<label class="checkbox-2" for="{{=v.id}}"></label>
    	   </div>
	     </td>
	   <td>{{=v.capability_item}}</td>
	   <td>{{=dateFormat(v.create_time)}}</td>
    </tr>
    {{~}}
</script>