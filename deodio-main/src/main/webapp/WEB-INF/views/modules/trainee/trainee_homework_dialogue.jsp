<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 写作业弹出 -->
<div class="modal fade" id="homeworkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
	<div class="modal-dialog" role="document" style="width:900px;">
		<div class="modal-content" id = "homeworkModalContent" data-callback="checkName()" call-message="当前作业已被注册">
		</div>
	</div>
</div>

<!-- 课程作业模板 -->
<script id="course_homework_template_modal"  type="text/x-dot-template">
			<form id="courseHomeworkForm" method="post" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">{{=it.modal_tittle}}</h4>
			</div>
			<div class="modal-body hdn">
                <div class="">
                    <div id="myHomeworkTabContent" class="tab-content" style="min-height:510px">
                        <div class="pre1_con tab-pane active" id="nr01" style="height: 513px;">
                            <ul class="shezhi">
                                <li>
                                    <div class="pull-left  w200 pt10"><span style="font-weight:bold">作业要求</span></div>
                                   
                                </li>
                                <li> <div class="pull-left  w200 pt10">{{=isNullFormat(it.homework_require)}}</div>
                                </li>
                                <li>
                                    <div class="pull-left  w200 pt10"><span style="font-weight:bold">提交截止日期：</span></div>
                                    <div class="pull-left  pt10">{{=dateFormat(it.homework_end_time)}}</div>
                                </li>
                                <li>
                                    <div class="pull-left  w200 pt8"><span style="font-weight:bold">作业文件</span></div>
                                    <div class="pull-left  pt8">{{=it.homework_name}}.{{=it.attach_ext}}&nbsp;&nbsp; <a href="${imgStatic}{{=isNullFormat(it.attach_url)}}{{=isNullFormat(it.generate_name)}}" download="{{=isNullFormat(it.homework_name)}}">下载文件</a>
                                    </div>
                                </li>
                                 <li>
                                    <div class="pull-left w200 pt10"><span style="font-weight:bold">上传附件</span></div>
                                    <div class="pull-left">
										<div class="pull-left">
											<input type="text" id="homeworkFileName" name="homeworkFileName" class="form-control w230" readonly value="{{=isNullFormat(it.attach_name)}}"> 
										</div>
										<div class="user-item pull-left" style="height: 35px;">


											<div style="width:90px;height:36px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 36px;margin-left: 5px;">
	                           					 <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
	                            				<input accept="image/png, image/gif, image/jpg, image/jpeg,.doc,.csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,
																text/plain,.doc,.pdf" id="xhomeworkUploadFile" type="file" name="xhomeworkUploadFile"
	                             					multiple="" style="position:absolute;opacity:0;width:400px;hright:36px;right:0;top:0;cursor:pointer;">
                            				</div>
											<div style="margin-left: 40px;">
												<button class="certify" type="button" style="display:block;margin:-36px 60px;height: 36px;width:70px;background:rgb(177,205,92)" 
													onclick="deleteFileAttach('homeworkFileName','homeworkAttachment','homeworkAttachmentId')">删除</button>
											</div>
											<input type="hidden" id="uploadDir" name="uploadDir">
											<input type="hidden" id="homeworkAttachment" name="homeworkAttachment" value = "{{=it.homework_attach}}">
											<input type="hidden" id="homeworkId" name="homeworkId" value="{{=isNullFormat(it.id)}}">
											<input type="hidden" id="homeworkAttachmentId" name="homeworkAttachmentId">
											
										</div>
									</div>
                                </li>
                                <li>
                                    <div class="pull-left  w200 pt8"><span style="font-weight:bold">作业描述</span></div>
                                    <div class="pull-left  pt8">
                                         <textarea rows="8" name="remark" id="remark" cols="" class="form-control w230" check-type="required" required-message="请输入作业描述" srcvalue="" placeholder="请输入作业描述" lable-error="true"></textarea>
                                    </div>
                                </li>
                              </ul>
                        </div>
                    </div>
                    <input type="hidden" id="homeworkAttachment" name="homeworkAttachment" value="{{=isNullFormat(it.homework_attach)}}">
					<input type="hidden" id="homeworkAttachmentId" name="homeworkAttachmentId">
					<input type="hidden" id="homeworkId" name="homeworkId" value="{{=isNullFormat(it.id)}}">
                    <input type="hidden" id="hiddenCourseId" name="hiddenCourseId">
                </div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn_green btn_160_36" btn-type='true' onclick="traineeSubmitHomework();">保存</button>
				<button type="button" class="btn btn_green btn_160_36" btn-type='true' onclick="traineeSubmitHomework();">提交</button>
				<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
			</div>
			</form>
</script>






<script type="text/javascript">
	require([ 'modules/trainee/trainee_homework_dialogue' ], function() {

	});
</script>