<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="surveyQuoteModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width: 800px;">
		<div class="modal-content" id="surveyQuoteModalContent">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">调查问卷被引用</h4>
			</div>
			<div class="modal-body hdn" style="height: 400px;">
				<!-- 调查问卷显示 -->
				<div id="right_content_survey_quote">
					<table cellpadding="0" cellspacing="0"
						class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 12%;">序号</th>
								<th class="text-center" style="width: 22%;">类别</th>
								<th class="text-center" style="width: 22%;">名称</th>
								<th class="text-center" style="width: 22%;">创建人昵称</th>
								<th class="text-center" style="width: 22%;">创建人邮箱</th>
							</tr>
						</thead>
						<tbody id="table_content_survey_quote">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="course_setting_survey_quote_Panel"></div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn_gray btn_160_36"
					data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<script id="right_content_survey_quote_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		 <td class="text-center">{{=(index+1)}}</td>
         <td class="text-center">
			{{? v.quoted_type == 'course'}}课程{{?}}
			{{? v.quoted_type == 'course_manager'}}课程管理{{?}}
			{{? v.quoted_type == 'presentation'}}章节{{?}}
         </td>
         <td class="text-center">{{=isNullFormat(v.course_name)}}</td>   
 		 <td class="text-center">{{=isNullFormat(v.nick_name)}}</td>
 		 <td class="text-center">{{=isNullFormat(v.user_mail)}}</td>
     </tr>
 {{~}}	
</script>
<script type="text/javascript">
	require([ 'modules/survey/survey_quoted_dialogue',
			'modules/survey/survey_list' ], function() {
	});
</script>
