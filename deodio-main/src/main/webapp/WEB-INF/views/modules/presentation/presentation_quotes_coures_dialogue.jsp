<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script id="right_content_presentation_quote_course_template" type="text/x-dot-template">
	<div id="right_content_presentation_quote">
					<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 6%;">序号</th>
								<th class="text-center" style="width: 30%;">课程名称</th>
								<th class="text-center" style="width: 15%;">课程状态</th>
								<th class="text-center" style="width: 20%;">创建者</th>
								<th class="text-center" style="width: 30%;">创建人邮箱</th>
							</tr>
						</thead>
						<tbody id="table_content_presentation_course_quote">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="presentation_quote_course_panel"></div>
				</div>

</script>	

<script id="data_presentation_quote_course_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<label >{{=(index+1)}}</label>
		</td>
		<td class="text-center">
			<label >{{=v.course_name}}</label>
		</td>
        <td class="text-center">
			<label >
			{{?v.is_publish==0}}
						未发布
				{{??}}
						已发布
			{{?}}
			</label>
		</td>
 <td class="text-center">
			<label >{{=v.user_name}}</label>
		</td>
 <td class="text-center">
			<label >{{=v.user_mail}}</label>
		</td>
     </tr>
{{~}}	
</script>				