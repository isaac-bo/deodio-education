<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="selectedList" name="selectedList" value="{}">
<!-- 幻灯片系统库弹出窗口  start -->
<script id="slideFactoryText" type="text/x-dot-template">

<div>
	<div class="w240 pull-right">
		<button type="button" class="btn-success btn36" onclick="querySlideList();">&nbsp;</button>
		<div class="search_input"><input type="text" class="form-control" id="querySlideInfo"></div>	
	</div>
	<div class="clearfix"></div>
</div>
<table cellpadding="0" cellspacing="0" class="table course-table table-striped mt20">
	<thead>
		<tr>
			<th style="width:10%">
				<div class="checkbox-group">
         				<input type="checkbox" name="slideRemember" id="one" onclick="selectAll(this)">
          				<label class="checkbox-2" for="one"></label>
       			</div>
			</th>
			<th style="width:15%">文件类型</th>
			<th style="width:50%">文件名</th>
			<th style="width:10%">大小</th>
			<th style="width:15%">创建日期</th>
		</tr>
	</thead>
</table>
<div  style="overflow-y: hidden; margin-top: -20px; height: 250px;" id="slidesFileListPanel">
	<table cellpadding="0" cellspacing="0" class="table course-table table-striped" id="slideFileList" >
	</table>
</div>
<div class="modal-footer">
	<div style="width:400px;margin-left:-15px;">
		<div class="text-center" id="slide_data_page_panel"></div>
	</div>
{{?it.dataLength!=0}}
	<button type="button" class="btn btn_green btn_160_36" onclick="saveSlideByFactory()">添加</button>
{{?}}
	<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal" onclick="closeSlideFactory()">取消</button>
</div>
</script>
<script id="slideFactory_data_template" type="text/x-dot-template">
	<tbody>
		{{~it.data:item:index}}
			<tr>
				<td>
					<div class="checkbox-group">
		            	<input type="checkbox" name="slideRemember{{=index}}" id="one{{=index}}" onclick="selectOrUnSelect(this)" lang={{=index}}>
		             		<label class="checkbox-2" for="one{{=index}}"></label>
				      </div>
				</td>
				<td><span class="glyphicon glyphicon-{{?item.attach_ext =='ppt'||item.attach_ext =='pptx'}}duplicate{{?? item.attach_ext!='pptx'&&item.attach_ext!='ppt'}}picture{{?}} f18"></span></td>
				<td id="slideOriginalName{{=index}}">{{=isNullFormat(item.attach_name)}}</td>
				<td id="slideSize{{=index}}">{{=calFileSize(item.attach_size)}}</td>
				<td id="createTime{{=index}}">{{=dateFormat(item.create_time)}}</td>
				<td style="display:none;" id="slideId{{=index}}">{{=isNullFormat(item.id)}}</td>
                <td style="display:none;" id="slideExt{{=index}}">{{=isNullFormat(item.attach_ext)}}</td>
			</tr>
		{{~}}
   </tbody>
</script>
<!-- 幻灯片系统库弹出窗口  end -->
<script type="text/javascript">
	require([ 'modules/presentation/presentation_sync_slides_dialogue' ], function(obj) {
		
	});
</script>