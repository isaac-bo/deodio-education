<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="presentationShareModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width: 800px;">
		<div class="modal-content" id="presentationShareModalContent">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">分享章节</h4>
			</div>
			<div class="modal-body hdn" style="height: 400px;">
				<div id="right_content_course_share">
					<div class="hdn">
						<div class="w240 pull-left">
							<button type="button" class="btn-success btn36" onclick="loadLecturerList()">&nbsp;</button>
							<div class="search_input">
								<input type="text" id="lecturerKeyWord" class="form-control">
							</div>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 10%;"></th>
								<th class="text-center" style="width: 10%;">序号</th>
								<th class="text-center" style="width: 40%;">讲师用户名</th>
								<th class="text-center" style="width: 40%;">讲师邮箱</th>
							</tr>
						</thead>
						<tbody id="table_content_presentation_share">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="presentation_setting_presentation_share_panel"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_green btn_160_36"
					onclick="shareCourse();" id="btn_submit_share" btn-type='true' data-dismiss="modal">提交</button>
				<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<script id="right_content_presentation_share_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr onclick="alertLabel('{{=isNullFormat(v.id)}}')">
		<td class="text-center">
         	<input type="radio" name="lecturerId" id="{{=isNullFormat(v.id)}}" style="display: inline-block;">	 		
		</td>
		 <td class="text-center">
			<label for="{{=isNullFormat(v.id)}}">{{=(index+1)}}</label>
		</td>
        <td class="text-center">
			<label for="{{=isNullFormat(v.id)}}">{{=isNullFormat(v.nickname)}}</label>
		</td>
 		<td class="text-center">
			<label for="{{=isNullFormat(v.id)}}">{{=isNullFormat(v.usermail)}}</label>
		</td>
     </tr>
 {{~}}	
</script>
<script type="text/javascript">
	require([ 'modules/presentation/presentation_share_dialogue' ], function() {
	});
</script>
