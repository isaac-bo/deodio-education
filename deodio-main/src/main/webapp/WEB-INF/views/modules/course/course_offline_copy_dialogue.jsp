<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
   <div class="modal fade" id="courseCopyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
   <div class="modal-dialog" role="document" style="width:400px;">
   <div class="modal-content" id="courseCopyModalContent">
   <div class="modal-header" >
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" >复制课程</h4>
	</div>
	<div class="modal-body hdn" style="height: 150px;" >
				<div class="clearfix">
					<div class="input-title input-title-color">新课程名称</div>
					<input type="text" name="newCourseName" id="newCourseName" 
						class="form-control group_title" style="width:202px !important;" 
						maxlength="15" autocomplete="off">
					<span id="errorMsg" style="color:red;"></span>
				</div>
			</div>
<!-- 			
	<div class="modal-body hdn" style="height: 150px;" >
		 <div class="select_b mt10">
		</div> 
		<div class="clearfix"></div>
		<table cellpadding="0" cellspacing="0"	class="table table-striped table-hover td_h60 mt10">
			<tbody>
			     <tr>
			     <td>
			     <div class="clearfix">请输入新课程名称</div></td>
			     <td><input type="text" name="newCourseName" id="newCourseName" value=""></td>
			    </tr>
			</tbody>
		</table>
	</div> -->
	
	<div class="modal-footer">
		<button type="button" class="btn btn_green btn_160_36" onclick="submitNewCourse();" btn-type='true'>提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
	</div>
	</div>
</div>
<script type="text/javascript">
	require(['modules/course/course_offline_copy_dialogue','modules/course/course_offline_list'],function(){	
	});
</script>
