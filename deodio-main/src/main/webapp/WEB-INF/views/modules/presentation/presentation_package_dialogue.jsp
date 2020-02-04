<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="selectedPackageList" name="selectedPackageList" value="{}">
<input type="hidden" id="uploadMethod" name="uploadMethod" value="1">

<input type="hidden" id="tem_file_id" value="">


<!-- 幻灯片系统库弹出窗口  start -->
<script id="packageFactoryText" type="text/x-dot-template">

<div>
	<div class="w240 pull-right">
		<button type="button" class="btn-success btn36" onclick="onSearchPackageFilesList(1);">&nbsp;</button>
		<div class="search_input"><input type="text" class="form-control" id="keyWords"></div>	
	</div>
	<div class="clearfix"></div>
</div>
<table cellpadding="0" cellspacing="0" class="table course-table table-striped mt20">
	<thead>
		<tr>
			<th style="width:5%">
				<div class="checkbox-group">
         				<input type="checkbox" name="packageRemember" id="one" onclick="onSelectAllFunc(this)">
          				<label class="checkbox-2" for="one"></label>
       			</div>
			</th>
			<th style="width:10%">文件类型</th>
			<th style="width:30%">文件名</th>
			<th style="width:15%">大小</th>
			<th style="width:20%">创建日期</th>
		</tr>
	</thead>
</table>
<div  style="overflow-y: hidden; margin-top: -20px; height: 250px;" id="packageFileListPanel">
	<table cellpadding="0" cellspacing="0" class="table course-table table-striped" id="packageFileList" >
	</table>
</div>
<div class="modal-footer">
	<div style="width:300px;margin-left:-15px;">
		<div class="text-center" id="package_data_page_panel"></div>
	</div>
	<button type="button" id="btn_add_file" class="btn btn_green btn_160_36" onclick="onSavePackageFilesByFactory()">添加</button>
	<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal" onclick="closePackageFilesFactory()">取消</button>
</div>
</script>
<script id="package_Factory_data_template" type="text/x-dot-template">
	<tbody>
		{{~it.data:item:index}}
			<tr>
				<td style="width:5%">
					<div class="checkbox-group">
		            	<input type="checkbox" name="packageRemember{{=index}}" id="one{{=index}}" onclick="onSelectOrUnSelectFunc(this)" lang={{=index}}>
		             		<label class="checkbox-2" for="one{{=index}}"></label>
				      </div>
				</td>
				<td style="width:10%"><span class="glyphicon glyphicon-picture f18"></span></td>
				<td style="width:30%" id="attachName{{=index}}">{{=isNullFormat(item.attach_name)}}</td>
				<td style="width:15%" id="attachSize{{=index}}">{{=calFileSize(item.attach_size)}}</td>
				<td style="width:20%" id="createTime{{=index}}">{{=dateFormat(item.create_time)}}</td>
				<td style="display:none;" id="id{{=index}}">{{=isNullFormat(item.id)}}</td>
                <td style="display:none;" id="attachExt{{=index}}">{{=isNullFormat(item.attach_ext)}}</td>
				<td style="display:none;" id="generateName{{=index}}">{{=isNullFormat(item.generate_name)}}</td>
                <td style="display:none;" id="attachUrl{{=index}}">{{=isNullFormat(item.attach_url)}}</td>
				<td style="display:none;" id="attachDir{{=index}}">{{=isNullFormat(item.attach_dir)}}</td>
			</tr>
		{{~}}
   </tbody>
</script>

<!-- 幻灯片系统库弹出窗口  end -->
<script type="text/javascript">
	require([ 'modules/presentation/presentation_package_dialogue' ], function(obj) {
		
	});
</script>