<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/trainers/trainers.css">
<input type="hidden" id="userId" value="${userId}" >
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	
	<div class="content" id="shijuan">
		<div class="pre_center">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
					<div class="pre_left_top_fl" style="width:1205px;">
						<div class="control-bar pull-left ml20">
							<a onclick="mediumIconShow(1);" href="javascript:void(0)" class="pic-item on">
								<div class=""></div>
								<div class="on"></div>
								<div class=""></div>
								<div class=""></div> 
							</a>
							<a onclick="smallIconShow(1)" href="javascript:void(0)" class="table-item">
								<div class="" style="margin-top: 2px;"></div>
								<div class=""></div>
								<div class=""></div>
								<div class=""></div>
							</a>
							
						</div>
						<button type="button" class="btn btn_green ml20 btn36" onclick="popTrainerDialogue();">创建讲师</button>
						<!-- <button type="button" class="btn btn_red ml10  btn36" id="delAllTrainers" onclick="delAllTrainers();" style="display:none;">批量删除</button> -->
						
						<div class="w240 pull-right mr20">
							<button type="button" class="btn-success btn36" onClick="javascript:searchTrainerList()">&nbsp;</button>
							<div class="search_input"><input type="text" id="keywords" class="form-control" ></div>	
						</div>
					</div>
					<!--pre_left_top_fl end-->
					<div class="clearfix"></div>
					<div class="con-corner"></div>
				</div>
		   <div>
		   </div>
	   </div>
		
		<div id="smallIcon" class="p20" style="display:none;margin-top:70px;">
			<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60">
				<thead>
					<tr>
						<th class="text-center">
							<!-- <div class="checkbox-group">
	            				<input type="checkbox" name="allTrainersId" id="allTrainersId">
	             				<label class="checkbox-2" for="allTrainersId"></label>
			        		</div> -->
						</th>
						<th class="text-center" style="width:10%">讲师姓名</th>
						<th class="text-center" style="width:10%;">讲师类型</th>
						<th class="text-center" style="width:10%;">职称/职务</th>
						<th class="text-center" style="width:10%;">级别</th>
						<th class="text-center">电话</th>
						<th class="text-center">电子邮箱</th>
						<th class="text-center" style="width:30%;">所属企业</th>
						<th class="text-center" style="width:10%;">操作</th>
					</tr>
				</thead>
				<tbody id="table_panle">
				
				</tbody>
			</table>
			
			<div class="mt20 text-center" id="data_page_Panel">
				
			</div>
		</div>
		<div id="mediumIcon" style="margin-top:70px;" class="p20">
			<div class="container-fluid">
				<div id="content" style="margin-left: -16px; margin-top: 20px;">
				
				</div>
				<div class="clearfix"></div>	
				<input type="hidden" id="hid_default_page" value="1">
			</div>
		</div>	
	</div>


<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
	<tr>
		<td class="text-center">
			<div class="checkbox-group" >
          		<input type="checkbox" name="trainersId" id="{{=isNullFormat(v.id)}}"  onclick="isSelectAll('allTrainersId','trainersId');">
           		<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
       		</div>
		</td>
		<td class="text-center">{{=isNullFormat(v.trainer_name)}}</td>
		<td class="text-center">{{? v.trainer_type == 0}}内部讲师{{?}}{{? v.trainer_type == 1}}外部讲师{{?}}</td>
		<td class="text-center">{{=isNullFormat(v.trainer_title)}}</td>
		<td class="text-center">{{? v.trainer_level == 0}}初级{{?}}{{? v.trainer_level == 1}}中级{{?}}
			{{? v.trainer_level == 2}}高级{{?}}{{? v.trainer_level == 3}}钻石级{{?}}</td>
		<td class="text-center">{{=isNullFormat(v.trainer_mobile_phone)}}</td>
		<td class="text-center">{{=isNullFormat(v.trainer_mail)}}</td>
		<td class="text-center">{{=isNullFormat(v.trainer_organization)}}</td>
		<td class="text-center">
			<button type="button" class="icon_1" onClick="javascript:editTrainer('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>
			<button type="button" class="icon_2" onClick="javascript:delTrainer('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>
			<button type="button" class="icon_3"onClick="javascript:previewTrainer('{{=isNullFormat(v.id)}}')"></button> 
		</td>
	</tr>
 {{~}}	

</script>

<script id="data_template" type="text/x-dot-template">
	{{~it.data:v:index}}
		{{? index%3 == 0}}<div class="row" style="margin-bottom:30px;">{{?}}
			<div class="col-md-4">
				<div class="t_box p2 border_hover img-border">
					<div class="p20" style="padding-bottom: 10px;">
						<img src="${ctx}/resources/img/trainers/pic2.jpg" alt="" class="pull-left img-border">
						<ul class="">
							<li><strong>讲师姓名：</strong>{{=subString(isNullFormat(v.trainer_name),10)}}</li>
							<li><strong>讲师类型：</strong>{{? v.trainer_type == 0}}内部讲师{{?}}{{? v.trainer_type == 1}}外部讲师{{?}}</li>
							<li><strong>企业名称：</strong>{{=subString(isNullFormat(v.trainer_organization),10)}}</li>
							<li><strong>电话：</strong>{{=isNullFormat(v.trainer_mobile_phone)}}</li>
							<li><strong>邮件：</strong>{{=isNullFormat(v.trainer_mail)}}</li>
						</ul>
					</div>
					<div class="text-right pb10 pt10 b_top">
						<button type="button" class="icon_1 mr10" onClick="javascript:editTrainer('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>
						<button type="button" class="icon_2 mr10" onClick="javascript:delTrainer('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>
						 <button type="button" class="icon_3 mr10"onClick="javascript:previewTrainer('{{=isNullFormat(v.id)}}')"></button> 
					</div>
				</div>
			</div>
		{{? index%3 == 2}}</div>{{?}}
	{{~}}	

</script>
<script id="pop_template" type="text/x-dot-template">
<form method="post" id="trainerForm">
	<ul class="shezhi b_none">
		<input type="hidden" id="trainerId" value="{{=it.data.id}}"/>
		<li class="w400">
			<div class="pull-left text-right w100 pt8">讲师类型：</div>
			<div class="radio-group pull-left" >
				<input type="radio" name="trainerType" id="internalTrainer"  value="0"  {{?it.data.trainerType == 0}} checked {{?}}>
				<label class="radio-2" for="internalTrainer"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">内部讲师</span>
			<div class="radio-group pull-left" >
				<input type="radio" name="trainerType" id="externalTrainer" value="1" {{?it.data.trainerType == 1}} checked {{?}}>
				<label class="radio-2" for="externalTrainer"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">外部讲师</span>
			
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt8">性别：</div>
			<div class="radio-group pull-left">
				<input type="radio"  name="trainerGender" id="male" value="0" {{?it.data.trainerGender == 0}} checked {{?}}>
				<label class="radio-2" for="male"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">男</span>
			<div class="radio-group pull-left">
				<input type="radio"  name="trainerGender" id="female" value="1" {{?it.data.trainerGender == 1}} checked {{?}}>
				<label class="radio-2" for="female"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">女</span>
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
			<span class="input-title-span">＊</span></label>讲师姓名：</div>
			<input type="text" id="trainerName" value="{{=it.data.trainerName}}" class="form-control w300"   call-message="当前讲师名已被注册" non-required="true" check-type="required" min-max="0-50" required-message="请输入讲师姓名！" placeholder="请输入讲师姓名" aria-describedby="basic-addon1">
			<input type="hidden" value="{{=it.data.trainerName}}" id="hiddentrainerName"> 
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
			<span class="input-title-span">＊</span></label>职称职务：</div>
			<input type="text" id="trainerTitle" value="{{=it.data.trainerTitle}}" class="form-control w300" check-type="required" min-max="0-50"  required-message="请输入职称职务！" placeholder="请输入职称职务" aria-describedby="basic-addon1">
		</li>
		
		<li class="w400">
			<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
			<span class="input-title-span">＊</span></label>联系电话：</div>
			<input type="text" id="trainerMobilePhone" value="{{=it.data.trainerMobilePhone}}" class="form-control w300" check-type="mobile" required-message="请输入11位正确手机号码！"  placeholder="请输入手机号码" aria-describedby="basic-addon1">
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
			<span class="input-title-span">＊</span></label>电子邮箱：</div>
			<input type="text" id="trainerEmail" value="{{=it.data.trainerMail}}" class="form-control w300" check-type="mail" placeholder="请输入邮箱" aria-describedby="basic-addon1">
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt8">级别：</div>
			<div class="radio-group pull-left">
				<input type="radio" name="trainerLevel" id="trainerLevelJunior" value="0" {{?it.data.trainerLevel == 0}} checked {{?}}>
				<label class="radio-2" for="trainerLevelJunior"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">初级</span>
			<div class="radio-group pull-left">
				<input type="radio" name="trainerLevel" id="trainerLevelStandard" value="1" {{?it.data.trainerLevel == 1}} checked {{?}}>
				<label class="radio-2" for="trainerLevelStandard"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">中级</span>
			<div class="radio-group pull-left">
				<input type="radio" name="trainerLevel" id="trainerLevelExpert" value="2" {{?it.data.trainerLevel == 2}} checked {{?}}>
				<label class="radio-2" for="trainerLevelExpert"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">高级</span>
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
			<span class="input-title-span">＊</span></label>所属企业：</div>
			<input type="text" value="{{=it.data.trainerOrganization}}" id="trainerOrganization" class="form-control w300" check-type="required" min-max="0-50" required-message="请输入所属企业！" placeholder="请输入所属企业" aria-describedby="basic-addon1">
		</li>
		<li>
			<div class="pull-left text-right w100 pt8">其他：</div>
			<div class="checkbox-group pull-left pt8" >
				<input type="checkbox" name="isRecommended" id="trainerIsRecommended" {{?it.data.isRecommended == 1}} checked {{?}}>
				<label class="checkbox-2" for="trainerIsRecommended"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">是否推荐讲师</span>
		</li>
		
	<li class="w400" style="height:135px;">
			<div class="pull-left text-right w100 ">上传图片：</div>
			<div class="user-item" style="width: 395px; margin-left: 30px; height: 135px;">
				<div class="img" style="height: 94px; width: 160px;">
		                              <img src="<c:if test="${empty it.data.trainerCover}"> ${ctx}/resources/img/account/user-card-1.jpg</c:if>
		                                  <c:if test="${not empty it.data.trainerCover}"> ${imgStatic}${attachment.attachUrl}${it.data.trainerCover} </c:if>"
		                                  id="preImgId" width="160">
		                            </div>
		                            <div class="user-buttons" style="width: 200px;">
                                        <div class="f10 ca0a0a0" style="height:50px;width: 200px; margin-bottom: 10px;">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪，大小不超过5M</div>
                                        <div style="width:130px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;">
                                            <span style="display:block;color:#fff;cursor:pointer;">上传文件</span>
                                            <input accept="image/png, image/gif, image/jpg, image/jpeg" id="trainerFile" type="file" name="trainerFile"
                                             multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;" onchange="registerKeyUp(this,'preImgId','trainerCover','attachId')">
                                        </div>
                                        <div style="margin-left: 40px;"><button class="certify" type="button" style="display:block;margin:-34px 93px;width:70px;background:rgb(177,205,92)" 
											onclick="deleteFileImg('preImgId','trainerCover','attachId','userIdCardDefaultSnapShot')">删除</button></div>
                                    </div>
                                    <div class="update-bar border-radius" id="progressBar" style="width: 370px; bottom:30px;">
                                        <div class="bar" style="width: 0%; height: 18px;background: #00e4ff;"></div>
                                    </div>
                                    <input type="hidden" id="trainerCover" name="trainerCover" value="${it.data.trainerCover}">
		                            <input type="hidden" id="attachId" name="attachId" value="${trainer.attachId}">
		                        	<input type="hidden" name="trainerId" id="trainerId" value="${it.data.id}"> 
		                        </div>
		                    </div>
                        </div>
		<li class="w400">
						<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
			<span class="input-title-span">＊</span></label>简介说明：</div>
			<div class="r_con" style="width:300px;height:135px;">
				<textarea id="trainerDesc" class="form-control textarea2" style="width: 300px;" name=""  cols="30" rows="6" check-type="required" min-max="0-255" required-message="请输入简介说明！" placeholder="请输入简介说明" aria-describedby="basic-addon1">{{=it.data.trainerDesc}}</textarea>
			</div>
		</li>

	</ul>

	<div class="modal-footer">
		<button type="button" class="btn submit btn_green btn_160_36" btn-type='true'>提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
</form>
</script>


<script id ="pop_view_template" type="text/x-dot-template">
<form method="post" id="trainerForm">
	<ul class="shezhi b_none">
		<input type="hidden" id="trainerId" value="{{=it.data.id}}" readonly="readonly"  />
		<li class="w400">
			<div class="pull-left text-right w100 pt8">讲师类型：</div>
			<div class="radio-group pull-left" >
				<input type="radio" name="trainerType" id="internalTrainer"  disabled="disabled" value="0"  {{?it.data.trainerType == 0}} checked {{?}}>
				<label class="radio-2" for="internalTrainer" ></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">内部讲师</span>
			<div class="radio-group pull-left" >
				<input type="radio" name="trainerType" id="externalTrainer"  disabled="disabled" value="1" {{?it.data.trainerType == 1}} checked {{?}}>
				<label class="radio-2" for="externalTrainer"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">外部讲师</span>
			
		</li>
			<li class="w400">
			<div class="pull-left text-right w100 pt8">性别：</div>
			<div class="radio-group pull-left">
				<input type="radio"  name="trainerGender" id="male" disabled="disabled" value="0" {{?it.data.trainerGender == 0}} checked {{?}}>
				<label class="radio-2" for="male"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">男</span>
			<div class="radio-group pull-left">
				<input type="radio"  name="trainerGender" id="female" disabled="disabled" value="1" {{?it.data.trainerGender == 1}} checked {{?}}>
				<label class="radio-2" for="female"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">女</span>
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10">讲师姓名：</div>
			<input type="text" id="trainerName" value="{{=it.data.trainerName}}" class="form-control w300" check-type="required" required-message="请输入讲师姓名！" placeholder="请输入讲师姓名" aria-describedby="basic-addon1" readonly="readonly">
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10">职称职务：</div>
			<input type="text" id="trainerTitle" value="{{=it.data.trainerTitle}}" class="form-control w300" check-type="required" required-message="请输入职称职务！" placeholder="请输入职称职务" aria-describedby="basic-addon1" readonly="readonly">
		</li>
		
		<li class="w400">
			<div class="pull-left text-right w100 pt10">联系电话：</div>
			<input type="text" id="trainerMobilePhone" value="{{=it.data.trainerMobilePhone}}" class="form-control w300" check-type="mobile" required-message="请输入11位正确手机号码！"  placeholder="请输入手机号码" aria-describedby="basic-addon1" readonly="readonly">
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10">电子邮箱：</div>
			<input type="text" id="trainerEmail" value="{{=it.data.trainerMail}}" class="form-control w300" check-type="mail" placeholder="请输入邮箱" aria-describedby="basic-addon1" readonly="readonly">
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt8">级别：</div>
			<div class="radio-group pull-left">
				<input type="radio" name="trainerLevel" id="trainerLevelJunior" disabled="disabled" value="0" {{?it.data.trainerLevel == 0}} checked {{?}}>
				<label class="radio-2" for="trainerLevelJunior" ></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">初级</span>
			<div class="radio-group pull-left">
				<input type="radio" name="trainerLevel" id="trainerLevelStandard" disabled="disabled" value="1" {{?it.data.trainerLevel == 1}} checked {{?}}>
				<label class="radio-2" for="trainerLevelStandard" ></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">中级</span>
			<div class="radio-group pull-left">
				<input type="radio" name="trainerLevel" id="trainerLevelExpert" disabled="disabled" value="2" {{?it.data.trainerLevel == 2}} checked {{?}}>
				<label class="radio-2" for="trainerLevelExpert" ></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">高级</span>
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt10">所属企业：</div>
			<input type="text" value="{{=it.data.trainerOrganization}}" id="trainerOrganization" class="form-control w300" check-type="required" required-message="请输入所属企业！" placeholder="请输入所属企业" aria-describedby="basic-addon1" readonly="readonly">
		</li>
		<li>
			<div class="pull-left text-right w100 pt8">其他：</div>
			<div class="checkbox-group pull-left pt8" >
				<input type="checkbox" name="isRecommended" id="trainerIsRecommended" disabled="disabled" {{?it.data.isRecommended == 1}} checked {{?}}>
				<label class="checkbox-2" for="trainerIsRecommended"></label>
			</div>
			<span class="pt8 ml10 mr20 pull-left">是否推荐讲师</span>
		</li>
		<li class="w400" style="height:135px;">
			<div class="pull-left text-right w100 ">上传图片：</div>
			<div class="user-item" style="width: 300px; margin-left: 0px; height: 135px;">
				<div class="img" style="height: 110px; width: 100px;">
					<img src="{{?it.data.trainerPortraitUrl == null}}${ctx}/resources/img/trainers/pic2.jpg{{?}}
						{{?it.data.trainerPortraitUrl != null}}{{=it.data.trainerPortraitUrl}}{{?}}" id="trainerPortraitUrl" style="width:100px;height:133.333px;">
				</div>
				<div class="user-buttons" style="width: 155px;">
					<span  class="f10 ca0a0a0">支持JPG, PNG, GIF 格式的图片，系统会自动裁剪。</span><br>
					<!--<input id="trainerUploadFile" name="presentationUploadFile" class="user-card" type="file" readonly="readonly">-->
					<!--<button class="up_del" type="button"  onclick="delPortraitPic()">删除</button>-->
				</div>
				<div class="update-bar border-radius">
					<div class="bar"></div>
				</div>
			</div>
		</li>
		<li class="w400">
			<div class="pull-left text-right w100 pt8">简介说明：</div>
			<div class="r_con" style="width:300px;height:135px;">
				<textarea id="trainerDesc" class="form-control textarea2" readonly="readonly" style="width: 300px;" name="" cols="30" rows="6" check-type="required" required-message="请输入简介说明！" placeholder="请输入简介说明" aria-describedby="basic-addon1">{{=it.data.trainerDesc}}</textarea>
			</div>
		</li>

	</ul>

	<div class="modal-footer">
		
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
</form>
</script>
<%-- <%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %> --%>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/trainers/trainer_list']);
	</script>


</body>
</html>