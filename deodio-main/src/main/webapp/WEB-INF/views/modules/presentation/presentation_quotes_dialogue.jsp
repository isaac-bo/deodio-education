<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script id="right_content_presentation_quote_template" type="text/x-dot-template">
	<div id="right_content_presentation_quote">
					<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
						<thead>
							<tr>
								<th class="text-center" style="width: 10%;">序号</th>
								<th class="text-center" style="width: 10%;">课件分类</th>
								<th class="text-center" style="width: 40%;">课件名称</th>
							</tr>
						</thead>
						<tbody id="table_content_presentation_quote">
						</tbody>
					</table>
					<!-- 分页 -->
					<div class="mt20 text-center" id="presentation_quote_panel"></div>
				</div>

</script>	

<script id="data_presentation_quote_template" type="text/x-dot-template">
{{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<label >{{=(index+1)}}</label>
		</td>
		<td class="text-center">
			<label >{{=v.quote_type}}</label>
		</td>
        <td class="text-center">
			<label >{{=v.quote_name}}</label>
		</td>
     </tr>
{{~}}	
</script>				