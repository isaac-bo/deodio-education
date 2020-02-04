<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">
	
		<div class="pre_center" style="position: absolute;">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
				<div id="div_tables_bar" class="pre_left_top_fl pre_right_top_normal">
					<ul class="top_nav">
						<li class="no1_on">设置课程信息</li>
						<li class="no2">设置课程内容</li>
					</ul>
				
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
		</div>
		<div class="con900" style="margin-top:120px;">
			<form id="courseOfflineInfoForm" method="post" >
			<div class="form-horizontal mt20 ">
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>课程名称：</label>
					<div class="col-sm-10">
						<div class="error-div">
							<input type="text" class="form-control" id="courseName" check-type="required" min-max ='0-200' 
								 data-callback="validateExistName()" required-message="请输入课程名!" call-message="当前课程名已存在"  value="${courseMap.course_name}" >
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->排课规则：</label>
					<div class="col-sm-10">
						<ul style="margin-top:10px;">
							<li>
								<div class="radio-group">
									<input type="radio" name="courseRule" id="course_rule1" value='0' checked="checked" <c:if test="${courseMap.course_rule == 0}"> checked="checked" </c:if>>
									<label class="radio-2" for="course_rule1"></label>
								</div>
								<span class="pt8 ml10">内训</span>
								<div class="radio-group">
									<input type="radio" name="courseRule" id="course_rule2" value='1' <c:if test="${courseMap.course_rule == 1}"> checked="checked" </c:if>>
									<label class="radio-2" for="course_rule2"></label>
								</div>
								<span class="pt8 ml10">外训</span>
							</li>
						</ul>
						
					</div>
				</div>
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>次数：</label>
					<div class="col-sm-10">
						<div class="error-div">
							<input type="text" class="form-control" id="courseTrainTimes" value="${courseMap.course_train_times}" check-type="number" min-max-num="1-10"  required-message="请填写1-10的数字！">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程描述：</label>
					<div class="col-sm-10">
						<div class="editor" id='courseDesc'>
							<script type="text/plain" id="course_desc_template"  style="width: 827px; height:140px;" >${courseMap.course_description}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程目标：</label>
					<div class="col-sm-10">
						<div class="editor" id='courseTarget'>
							<script type="text/plain" id="course_target_template" style="width: 827px; height:140px;" >${courseMap.course_objective}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><!-- <span class="input-title-span">＊</span> -->课程须知：</label>
					<div class="col-sm-10">
						<div class="editor" id='courseAttention'>
							<script type="text/plain" id="course_attention_template" style="width: 827px; height:140px;" >${courseMap.course_infomartion}</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>所属分类：</label>
					<div class="col-sm-10 ">
						<div class="fenlei_1 w400" id="inputGeneres" check-type="classification">
							<button type="button" class="btn_add" onclick="popClassificationPicker();"></button>
							<c:forEach items="${selectedClassificationList}" var="item">
								<span class="sel_btn">
									${item.classification_name}
									<button type="button" class="sel_del" classification="${item.id}" onclick="delLabel(this);">&times;</button></span>
							</c:forEach>
							<div class="clearfix" id="genereNodes"></div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>标签：</label>
					<div class="col-sm-10">
						<div class="fenlei_1 w400">
							<div class="clearfix" id="labelNodes"></div>
							<div class="div-tags" id="tagsDiv">  
								<input style="width:700px;" id="inputTagator" name="inputTagator" value="${selectedTagsName}" onclick="javascript:popTagsPicker();">
								<button type="button" class="btn_add" id="tagInput"></button> 
							</div>
							<!-- 点加号弹出选择分类 -->
							<div class="up" id="hotTagsList" style="display:none;width: 745px;">
							</div>
						</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>制定人数：</label>
					<div class="col-sm-10">
						<div class="error-div w400">
							<input type="text" class="form-control w400" id="courseTraineeNum" value="${courseMap.course_trainee_num}" check-type="intValidate" maxlength="7">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>替补人数百分比：</label>
					<div class="col-sm-10">
						<div class="w160 error-div pull-left" style="height:38px;">
						<input type="text" class="form-control w160 pull-left" id="substituteNumber"  data-callback="validateSubstitudeNum();" call-message="请输入0~100之间的整数!" check-type="required" value="${courseMap.course_substitute_number}">
						</div><label class="pt8 ml10 mt10">%，（替补人数为制定人数*替补人数百分比）</label>
					</div>
				</div>
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>联系人：</label>
					<div class="col-sm-10">
						<div class="error-div w400">
							<input type="text" class="form-control w400" id="courseContactName" value="${courseMap.course_contact_name}" check-type="required">
						</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>联系电话：</label>
					<div class="col-sm-10">
						<div class="error-div w400">
							<input type="text" class="form-control w400" id="courseContactTel" value="${courseMap.course_contact_tel}" check-type="mobile"  
							required-message="请输入正确电话号码" >
						</div>
					</div>
				</div>
				
				<fmt:formatDate type="date" value="${courseMap.start_time}" var="parsedStartDate"   pattern="yyyy-MM-dd"/>
				<fmt:formatDate type="date" value="${courseMap.expire_time}"  var="parsedEndDate" pattern="yyyy-MM-dd"/>
				<div class="form-group" style="height: auto;">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>有效起止日期</label>
					<div class="col-sm-10">
						<div class="w240 pull-left ml3 error-div">
							<input type="text" class="form-control date_btn form_datetime"
								name="startTime" id="startTime" style="height: 36px;margin-top: 0px;"
								check-type="dateYmd dateCompare" date-compare="from"
								aria-describedby="basic-addon1" value="${parsedStartDate}"
								data-callback="validateStartDate();" call-message="课程开始时间必须是7天后!" >
						</div>
						<span class="date_line pull-left"></span>
						<div class="w240 pull-left ml20 error-div" style="margin-top: -3px;">
							<input type="text" class="form-control date_btn form_datetime"
								name="expireTime" id="expireTime" style="height: 36px;"
								check-type="dateYmd dateCompare" 
								aria-describedby="basic-addon1" value="${parsedEndDate}">
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class="input-title-span">＊</span>发布范围：</label>
					<div class="col-sm-10">
						<ul style="margin-top:10px;"s>
							<li>
								<div class="radio-group">
									<input type="radio" name="isPublic" id="radio_is_public1" value='0' checked="checked"  <c:if test="${courseMap.is_public == 0}"> checked="checked" </c:if> >
									<label class="radio-2" for="radio_is_public1"></label>
								</div>
								<span class="pt8 ml10">公共课程－所有学员课件</span>
							</li>
							<li class="pt10">
								<div class="radio-group">
									<input type="radio" name="isPublic" id="radio_is_public2" value='1' <c:if test="${courseMap.is_public == 1}"> checked="checked" </c:if>>
									<label class="radio-2" for="radio_is_public2"></label>
								</div>
								<span class="pt8 ml10">私有课程</span>
							</li>
						</ul>
						
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">封面图像：</label>
					<div class="col-sm-10">
					<div class="user-item">
						<div class="img">
							<img src="<c:if test="${empty courseMap.course_cover_img}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if>						
							<c:if test="${not empty courseMap.course_cover_img}"> ${imgStatic}${courseMap.attach_url}${courseMap.course_cover_img} </c:if>" id="preImgId" width="160">
						</div>
						<div class="user-buttons">
							<div class="f10 ca0a0a0" style="height:50px;width: 290px; margin-bottom: 5px;">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪，大小不超过5M</div>
							<div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
                                <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
                                <input accept="image/png, image/gif, image/jpg, image/jpeg" id="courseUploadFile" type="file" name="courseUploadFile"
                                 multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
                            </div>
                            <div style="margin-left: 150px;margin-top:-34px"><button class="certify" type="button" style="display:block;margin:-34px 94spx" onclick="deleteFileImg()">删除</button></div>
						</div>
						 <div class="update-bar border-radius" id="progressBar" style="width: 354px;">
                                        <div class="bar" style="width: 0%; height: 18px;background: #00e4ff;"></div>
                                    </div>
					  </div>
					</div>
				</div>
				<input type="hidden" id="courseCover" name="courseCover" value="${courseMap.course_cover_img}">
				<input type="hidden" id="courseCoverId" name="courseCoverTemp" alue="${courseMap.attachment_id}">
				<input type="hidden" id="courseId" name="courseId" value="${courseMap.course_id}">
				<input type="hidden" id="courseOfflineId" name="courseOfflineId" value="${courseMap.course_offline_id}">
				
				<div class="btn_box">
					<button class="btn submit btn_160_36" type="button" btn-type='true'>保存/下一步</button>
					<button class="btn cancel btn_160_36" type="button" onclick="goBack()">取消</button>
				</div>
			</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/modules/classification/classification_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>


	<script type="text/javascript">
		require([ 'modules/course/course_offline_profile','modules/course/course_common' ], function(obj) {
			window.onbeforeunload = updateEditState;
	        window.onhashchange = updateEditState;
		});
	</script>
</body>
</html>