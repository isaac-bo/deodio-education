<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="publishModal" tabindex="-1" role="dialog"  aria-labelledby="publishModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">发布到相关组</h4>
			</div>
			<div class="modal-body hdn">
				<!-- 这里开始是弹出内容 -->
				<div class="_up_left pull-left" style="height:515px;">
                    <div class="w240">
        				<button type="button" class="btn-success btn36" onclick="onSearchGroupList();">&nbsp;</button>
        				<div class="search_input"><input type="text" id="keyword" class="form-control"></div>
        			</div>
                    <div class="left_box left_box_extern mt10" id="groupListPanel">
                    </div>
				</div>
                <div class="_up_right pull-right" style="height:515px;">
                    <div id="myTabContent" class="tab-content">
                        <div class="pre1_con pre1_con_group tab-pane active" id="activeGroupPanel" style="height: 513px;overflow-y:auto;">
	                        <div class="box3 pull-left" id="emptyGroupPanel">
	                        	<div>
	                        		<span class="icon-plus icon-large"></span>
	                        	</div>
	                        	<label>拖拽或者添加小组</label>
	                        </div>
	                        <!-- <div class="box3 pull-left" id="emptyGroupPanel">
	                        	<div>
	                        		<span class="icon-minus icon-large"></span>
	                        	</div>
	                        	<label>拖拽或者删除小组</label>
	                        </div> -->
                        </div>
                    </div>
                </div>
			</div>
			<input id="group_container_type" type="hidden" value=""/>
			<input id="item_id" type="hidden" value=""/>
			<div class="modal-footer">
				<button type="button" class="btn btn_green btn_160_36" onclick="onSubmit()">提交</button>
				<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>	
<script type="text/javascript">
	require(['modules/group/group_dialogue'],function(){
		
	});
</script>
		
<script id="group_list_template"  type="text/x-dot-template">

{{~it.data:v:index}}
	<div id="group_original_{{=v.id}}" class="box1 mt10 pt10 pb10 pl10 pr10">
      	{{? isNullFormat(v.attach_url) == ''}}
		<img src="${ctx}/resources/img/presentation/p_pic.jpg" alt="">
		{{??}}
		<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="">
		{{?}}
           <div class="box_r f12">
               <div style="color:#f97e37;">小组名称</div>
			   <div title="{{=v.group_name}}">{{=subString(v.group_name,10)}}</div>
            </div>
     <!--div style="float: right;margin-top: -60px;cursor:pointer;position: relative;">
        <a class="icon-trash" style="color:white;margin-left:10px;"  onclick="javascript:remove('{{=isNullFormat(v.id)}}')"></a>
	</div-->
    </div>

{{~}}
</script>

