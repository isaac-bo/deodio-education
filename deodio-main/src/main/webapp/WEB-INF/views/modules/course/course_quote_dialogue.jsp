<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="courseQuoteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width: 800px;">
		<div class="modal-content" id="courseQuoteModalContent">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">引用课件</h4>
			</div>
			<div class="modal-body hdn" style="height: 400px;">
				<div id="right_content_course_quote">
					<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 10%;">序号</th>
								<th class="text-center" style="width: 10%;">课件分类</th>
								<th class="text-center" style="width: 40%;">课件名称</th>
							</tr>
						</thead>
						<tbody id="table_content_course_quote">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="course_setting_course_quote_panel"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<script id="right_content_course_quote_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<label for="{{=isNullFormat(v.item_id)}}">{{=(index+1)}}</label>
		</td>
		<td class="text-center">
			<label for="{{=isNullFormat(v.item_id)}}">{{? v.item_type == 0}}章节{{?}}{{? v.item_type == 1}}测验{{?}}
			{{? v.item_type == 2}}问卷调查{{?}}</label>
		</td>
        <td class="text-center">
			<label for="{{=isNullFormat(v.item_id)}}">{{=isNullFormat(v.item_name)}}</label>
		</td>
     </tr>
{{~}}	
</script>
<script type="text/javascript">
	require([ 'modules/course/course_quote_dialogue' ], function() {
	});
</script>
