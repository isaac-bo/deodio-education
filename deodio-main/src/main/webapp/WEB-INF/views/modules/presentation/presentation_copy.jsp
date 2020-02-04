<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script id="copy_data_template" type="text/x-dot-template">
<div class="pre1_con" style="min-height: 0px;">
		<div id="myTabContent"  >
			
			<div class="tab-pane" id="daoru1">
				<div class="c929292 f12">
					您可以通过此功能复制所选中的章节，快速生成一个相似章节
					<br><br>
					您可以通过在下面文本框中输入章节复制后新的名称： 
					<br><br>
					默认名称： 章节名称(1)<br>
								
				</div>
				<div class="upload mCustomScrollbar" id="packagePanel" >
						<h2>章节名称:</h2>
						
						<div class="search_input">
							<input id="copy_presentation" value="{{=it.data.presentationName}}(1)" class="form-control" type="text">
						</div>
						<div >
							<button type="button" id="btn_add_file" class="btn btn_green btn_160_36" style="margin:20px 20px 0 0;" onclick="onSaveCopyPresentation('{{=it.data.presentationId}}')">确定</button>
							<button type="button" class="btn btn_gray btn_160_36" style="margin:20px 20px 0 0;" data-dismiss="modal" onclick="closePopWindow()">取消</button>
						</div>
					
				</div>
		</div>
	</div>

</div>
</script>

	