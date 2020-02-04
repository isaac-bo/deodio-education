<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="selectedMediaList" name="selectedMediaList" value="{}">
<!-- 多媒体库弹出窗口  start -->
<script id="mediaFactoryText" type="text/x-dot-template">
<div>
	<div class="w240 pull-right">
		<button type="button" class="btn-success btn36" onclick="queryMediaList();">&nbsp;</button>
		<div class="search_input"><input type="text" class="form-control" id="queryMediaInfo"></div>	
	</div>
	<div class="clearfix"></div>
</div>
<table cellpadding="0" cellspacing="0" class="table course-table table-striped mt20">
	<thead>
		<tr>
			<th style="width:10%">
				<div class="checkbox-group">
        				<input type="checkbox" name="mediaRemember" id="one" onclick="selectAllMedia(this)">
         				<label class="checkbox-2" for="one"></label>
      				</div>
			</th>
			<th style="width:15%">文件类型</th>
			<th style="width:35%">文件名</th>
			<th style="width:10%">大小</th>
			<th style="width:15%">长度</th>
			<th style="width:20%">创建日期</th>
		</tr>
	</thead>
</table>
<div  style="overflow-y: hidden; margin-top: -20px; height: 250px;" id="mediasFileListPanel">
	<table cellpadding="0" cellspacing="0" class="table course-table table-striped" id="mediaFileList" >
	</table>
</div>

<div class="modal-footer">
	<div style="width:400px;margin-left:-15px;">
		<div class="mt20 text-center" id="media_data_page_panel"></div>
	</div>
	{{?it.dataLength!=0}}
	<button type="button" class="btn btn_green btn_160_36" onclick="saveMediaByFactory()">添加</button>
	{{?}}
	<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal" onclick="closeMediaFactory()">取消</button>
</div>
</script>
<script id="mediaFactory_data_template"  type="text/x-dot-template">
<tbody>
	{{~it.data:item:index}}
		<tr>
			<td>
				<div class="checkbox-group">
		            <input type="checkbox" name="mediaRemember{{=index}}" id="one{{=index}}" onclick="selectMediaOrUnSelect(this)" lang={{=index}}>
		            <label class="checkbox-2" for="one{{=index}}"></label>
				</div>
			</td>
			<td>
				<span class="glyphicon glyphicon-{{?item.mediaExt =='mp3'}}music{{?? item.mediaExt!='mp3'}}facetime-video{{?}} f18"></span>
			</td>
			<td id="mediaOriginalName{{=index}}">{{=isNullFormat(item.attach_name)}}</td>
			<td id="mediaSize{{=index}}">{{=calFileSize(item.attach_size)}}</td>
			<td id="mediaLength{{=index}}">{{=formatTimes(item.media_length)}}</td>
			<td id="createTime{{=index}}">{{=dateFormat(item.create_time)}}</td>
			<td style="display:none;" id="mediaId{{=index}}">{{=isNullFormat(item.id)}}</td>
			<td style="display:none;" id="mediaExt{{=index}}">{{=isNullFormat(item.attach_ext)}}</td>
		</tr>
	{{~}}
</tbody>
</script>
<!-- 多媒体库弹出窗口  end -->
<script type="text/javascript">
	require([ 'modules/presentation/presentation_sync_media_dialogue' ], function(obj) {
		
	});
</script>