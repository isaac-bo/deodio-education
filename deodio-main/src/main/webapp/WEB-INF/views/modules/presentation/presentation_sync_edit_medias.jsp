<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/presentation/presentation.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
<!-- <iframe src="header.html" style="width:100%;height:242px;border:none;display:block;"></iframe> -->
	<div class="content p_border">
		<ul class="top_nav">
			<li class="no1_on">设置Presentation信息</li>
			<li class="no2_on">设置Presentation内容</li>
			<li class="no3_on">制作课件</li>
			<li class="no4">设置Presentation预览</li>
		</ul>
		<div class="con900">
			<h3 class="mt20">编辑多媒体</h3>
			<div class="mt20">
				<div class="control-bar pull-left">
					<a href="javascript:void(0)" onclick="smallIconShow()" class="table-item on">
						<div class="" style="margin-top: 2px;"></div>
						<div class=""></div>
						<div class=""></div>
						<div class=""></div>
					</a>
					<a href="javascript:void(0)"  onclick="mediumIconShow();" class="pic-item">
						<div class=""></div>
						<div class=""></div>
						<div class=""></div>
						<div class=""></div>
					</a>
				</div>
				<div class="w240 pull-right">
					<button type="button" class="btn-success btn36" onclick="queryMediasListByInfo()">&nbsp;</button>
					<div class="search_input"><input type="text" class="form-control" id="queryMediasListInfo"></div>	
				</div>				
				<button type="button" class="pull-right mr20 btn_red">删除多媒体</button>
				<!-- <select name="" id="" class="pull-right mr20 selectpicker">
					<option value="添加多媒体">添加多媒体</option>
					<option value="">系统多媒体库</option>
					<option value="">本地上传</option>
					<option value="">在线录制</option>
				</select> -->
				<div class="text-right">
						<div class="btn-group bootstrap-select" style="margin-right:20px">
							<button class="btn dropdown-toggle btn-default" data-toggle="dropdown" type="button" data-id="">
								<span class="filter-option pull-left">本地上传</span> <span class="bs-caret"> <span class="caret"></span></span>
							</button>
							<div class="dropdown-menu open" style="max-height: 559px; overflow: hidden; min-height: 0px;">
								<ul class="dropdown-menu inner" role="menu" style="max-height: 547px; overflow-y: hidden; min-height: 0px;">
									<li data-original-index="0">
										<a class="" data-tokens="null" style="" tabindex="0"> 
											<span class="text">添加多媒体</span> <span class="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
									<li data-original-index="0">
										<a class="" data-tokens="null" style="" tabindex="0"> 
											<span class="text" onclick="sysMediaFactory()">系统多媒体库</span> <spanclass="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
									<li class="selected" data-original-index="1">
										<a class="" data-tokens="null" style="height: 30px;" tabindex="0">
											<div>
												<span class="text">本地上传</span>
											</div>
											<div id="file_swf_multimedia"></div> <span class="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
									<li data-original-index="0">
										<a class="" data-tokens="null" style="" tabindex="0"> 
											<span class="text">在线录制</span> <span class="glyphicon glyphicon-ok check-mark"></span>
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>

				<div class="clearfix"></div>
			</div>
			<div id="mediaList">
			<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
				<thead>
					<tr>
						<th class="text-center" style="width:8%">
							<div class="checkbox-group">
	            				<input type="checkbox" name="remember" id="editMedia">
	             				<label class="checkbox-2" for="editMedia"></label>
			        		</div>
						</th>
						<th class="text-center" style="width:30%;">文件名</th>
						<th class="text-right" style="width:15%;">文件长度</th>
						<th class="text-right" style="width:10%;">大小</th>
						<th class="text-center" style="width:15%;">创建日期</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody id="medias_table_panle">
			    </tbody>
			</table>
			</div>
			<div id="mediaItem" style="display:none;">
				<ul class="pre_list mt20">
					<div>
						<div id="media_item_panel"> 
						</div>
					</div>
				</ul>
			</div>
			<div class="form_line2"></div>
			<div class="btn_box">
				<button class="btn submit btn_160_36" type="button">保存</button>
				<button class="btn cancel btn_160_36" type="button">取消</button>
			</div>
		
	</div>

<script id="medias_table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
		<tr>
			<td class="text-center">
				<div class="checkbox-group">
          				<input type="checkbox" name="rememberMediaList{{=index}}" id="editMedia{{=index}}">
           				<label class="checkbox-2" for="editMedia{{=index}}"></label>
        		</div>
			</td>
			<td>{{=isNullFormat(v.media_original_name)}}</td>
			<td class="text-center">{{=formatTimes(v.media_length)}}</td>
			<td class="text-right">{{=calFileSize(v.media_size)}}</td>
			<td class="text-center">{{=dateFormat(v.create_time)}}</td>
			<td class="text-right">
				<button type="button" class="icon_btn24_1"></button>
				<button type="button" class="icon_btn24_2"></button>
				<button type="button" class="icon_btn24_3"></button>
				<button type="button" class="icon_btn24_4"></button>
			</td>
		</tr>
 {{~}}	

</script>

<script id="medias_item_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
				<li>
					<a href=""><img src="${imgStatic}{{=isNullFormat(v.media_cover)}}" alt=""></a>
					<div class="mt10 hdn">
						<div class="checkbox-group">
            				<input type="checkbox" name="rememberMediaItem{{=index}}" id="editMedia{{=index}}">
             				<label class="checkbox-2" for="editMedia{{=index}}"></label>
		        		</div>
		        		<span>{{=isNullFormat(v.media_original_name)}}</span>
		        		<span class="pull-right">{{=isNullFormat(v.media_size)}}.kb</span>
					</div>
					<div class="mt10 pt10 text-right btn_line">
						<button type="button" class="icon_btn24_1"></button>
						<button type="button" class="icon_btn24_2"></button>
						<button type="button" class="icon_btn24_3"></button>
						<button type="button" class="icon_btn24_4"></button>
					</div>
				</li>
 {{~}}	

</script>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
<input type="hidden" id="presentationId" name="presentationId" value="${presentationId}">

<%@ include
	file="/WEB-INF/views/modules/presentation/presentation_sync_media_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<script type="text/javascript">
	require([ 'modules/presentation/presentation_sync_edit_medias' ], function(obj) {
		
	});
</script>
</body>
</html>