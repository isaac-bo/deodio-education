<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.layer.css">

<div class="mess_left pull-left set-ip-left">
	<div class="user_pic dropdown">
        <img class="user_pic" style="border:2px solid #43b4c6" src="<c:if test="${empty groupModel.coverImg}">${ctx}/resources/img/account/homepaage_movie_travel.jpg</c:if><c:if test="${not empty groupModel.coverImg}"> ${groupModel.coverImg}</c:if>"  id="groupIconPic"/>
       <c:if test="${roleAccountId == userRoleId||roleLeaderId == userRoleId}" >
        <button type="button" class="btn user_pic_edit" onclick="onGroupPicEdit();"><span></span><span></span><span></span></button>
      </c:if>
    </div>

<c:choose>
<c:when test="${menu==1 && empty groupId}">        
    <ul class="left_menu_normal" id="accountMenu">
	     <li class="active"><a href="${ctx}/group/profile.html" ><img alt="" src="${ctx}/resources/img/group/left_menu_1_active.png"></img>小组简介</a></li>
	     <li><a><img alt="" src="${ctx}/resources/img/group/left_menu_2.png"></img>小组权限</a></li>
	     <li><a><img alt="" src="${ctx}/resources/img/group/left_menu_3.png"></img>小组课程</a></li>
	     <li><a><img alt="" src="${ctx}/resources/img/group/left_menu_4.png"></img>小组成员</a></li>
	     <li><a><img alt="" src="${ctx}/resources/img/group/left_menu_5.png"></img>邀请加入</a></li>
	     <li><a><img alt="" src="${ctx}/resources/img/group/left_menu_6.png"></img>创建组员</a></li>
	     <li><a><img alt="" src="${ctx}/resources/img/group/left_menu_7.png"></img>注册表单</a></li>
	     <li><a><img alt="" src="${ctx}/resources/img/group/left_menu_8.png"></img>能力模型</a></li>
	 </ul>
</c:when>
<c:when test = "${roleAccountId == userRoleId||roleLeaderId == userRoleId}">  
	 <ul class="left_menu" id="accountMenu">
	     <li <c:if test="${menu==1}"> class="active" </c:if>><a href="${ctx}/group/profile.html?groupId=${groupId}&groupRoleId=${userRoleId}"><img alt="" src="<c:if test='${menu==1}'>${ctx}/resources/img/group/left_menu_1_active.png</c:if><c:if test='${menu!=1 }'>${ctx}/resources/img/group/left_menu_1.png</c:if>"></img>小组简介</a></li>
	     <li <c:if test="${menu==2}"> class="active" </c:if>><a href="${ctx}/group/permission.html?groupId=${groupId}&groupRoleId=${userRoleId}" ><img alt="" src="<c:if test='${menu==2}'>${ctx}/resources/img/group/left_menu_2_active.png</c:if><c:if test='${menu!=2 }'>${ctx}/resources/img/group/left_menu_2.png</c:if>"></img>小组权限</a></li>
	     <li <c:if test="${menu==3}"> class="active" </c:if>><a href="${ctx}/group/features.html?groupId=${groupId}&groupRoleId=${userRoleId}" ><img alt="" src="<c:if test='${menu==3}'>${ctx}/resources/img/group/left_menu_3_active.png</c:if><c:if test='${menu!=3 }'>${ctx}/resources/img/group/left_menu_3.png</c:if>"></img>小组课程</a></li>
	     <li <c:if test="${menu==4}"> class="active" </c:if>><a href="${ctx}/group/member.html?groupId=${groupId}&groupRoleId=${userRoleId}" ><img alt="" src="<c:if test='${menu==4}'>${ctx}/resources/img/group/left_menu_4_active.png</c:if><c:if test='${menu!=4 }'>${ctx}/resources/img/group/left_menu_4.png</c:if>"></img>小组成员</a></li>
	     <li <c:if test="${menu==5}"> class="active" </c:if>><a href="${ctx}/group/invite.html?groupId=${groupId}&groupRoleId=${userRoleId}" ><img alt="" src="<c:if test='${menu==5}'>${ctx}/resources/img/group/left_menu_5_active.png</c:if><c:if test='${menu!=5 }'>${ctx}/resources/img/group/left_menu_5.png</c:if>"></img>邀请加入</a></li>
	     <li <c:if test="${menu==6}"> class="active" </c:if>><a href="${ctx}/group/member/single.html?groupId=${groupId}&groupRoleId=${userRoleId}" ><img alt="" src="<c:if test='${menu==6}'>${ctx}/resources/img/group/left_menu_6_active.png</c:if><c:if test='${menu!=6 }'>${ctx}/resources/img/group/left_menu_6.png</c:if>"></img>创建组员</a></li>
	     <li <c:if test="${menu==7}"> class="active" </c:if>><a href="${ctx}/group/form/manage.html?groupId=${groupId}&groupRoleId=${userRoleId}" ><img alt="" src="<c:if test='${menu==7}'>${ctx}/resources/img/group/left_menu_7_active.png</c:if><c:if test='${menu!=7 }'>${ctx}/resources/img/group/left_menu_7.png</c:if>"></img>注册表单</a></li>
	     <li <c:if test="${menu==9}"> class="active" </c:if>><a href="${ctx}/group/capability.html?groupId=${groupId}&groupRoleId=${userRoleId}" ><img alt="" src="<c:if test='${menu==8}'>${ctx}/resources/img/group/left_menu_8_active.png</c:if><c:if test='${menu!=8 }'>${ctx}/resources/img/group/left_menu_8.png</c:if>"></img>能力模型</a></li>
	 </ul>
	 
</c:when>
<c:otherwise>    
	 <ul class="left_menu" id="accountMenu">
         <li <c:if test="${menu==3}"> class="active" </c:if>><a href="${ctx}/group/features.html?groupId=${groupId}" ><img alt="" src="${ctx}/resources/img/group/left_menu_3_active.png"></img>小组课程</a></li>
   	 </ul>
</c:otherwise>
</c:choose>
	 
	 <div class="user_pic_up" style="top: 200px; left: 320px; display:none;" id="groupIcon">
		<h4>
			改变小组标识<a href="javascript:hideIcon();"><img src="${ctx}/resources/img/close_icon.png" alt=""></a>
		</h4>
		<div class="pic_con">
		</div>
		<!-- 未上传时状态 -->
		<div class="pic_con1" style="display:none;">
			<img src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" alt="" id="groupIconImg">
		</div>
		<input type="hidden" id="groupIconId" name="groupIconId">
		<input type="hidden" id="groupIconName" name="groupIconName">
		<input id="hidgoupid" name=groupPkId type="hidden" value="${groupModel.id}">
		<!-- <input id="uploadGroupFile" name="uploadGroupFile" class="btn up_color" type="file"> -->
		<!-- <button class="btn default" type="button"  onclick="cancleUserIcon()">使用默认头像</button> -->
		<div style="width:244px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: -5px;">
                  <span style="display:block;color:#fff;cursor:pointer;">上传图片</span>
                 <input accept="image/png,image/jpg, image/jpeg" id="uploadGroupFile" type="file" name="uploadGroupFile" multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
             </div>
	</div>
	 
 </div>




<%-- <c:choose>
	<c:when test="${menu==1 && empty groupId}">
			<div class="g_left">
				<ul class="g_l_tab">
					<li class="icon1"><a href="${ctx}/group/profile.html" class="on">小组简介<span></span></a></li>
					<li class="icon2"><a href="javascript:;">小组权限<span></span></a></li>
					<li class="icon3"><a href="javascript:;">小组课程<span></span></a></li>
					<li class="icon4"><a href="javascript:;">小组成员<span></span></a></li>
					<li class="icon5"><a href="javascript:;">邀请加入<span></span></a></li>
					<li class="icon6"><a href="javascript:;">创建组员<span></span></a></li>
					<li class="icon7"><a href="javascript:;">注册表单<span></span></a></li>
					<li class="icon8"><a href="javascript:;">内容管理<span></span></a></li>
					<li class="icon9"><a href="javascript:;">能力模型<span></span></a></li>
				</ul>
			</div>
	</c:when>
	<c:otherwise>
		<div class="g_left">
				<ul class="g_l_tab">
					<li class="icon1"><a href="${ctx}/group/profile.html?groupId=${groupId}" <c:if test="${menu==1}"> class="on"</c:if>>小组简介<span></span></a></li>
					<li class="icon2"><a href="${ctx}/group/permission.html?groupId=${groupId}"<c:if test="${menu==2}"> class="on"</c:if>>小组权限<span></span></a></li>
					<li class="icon3"><a href="${ctx}/group/features.html?groupId=${groupId}"<c:if test="${menu==3}"> class="on"</c:if>>小组课程<span></span></a></li>
					<li class="icon4"><a href="${ctx}/group/member.html?groupId=${groupId}"<c:if test="${menu==4}"> class="on"</c:if>>小组成员<span></span></a></li>
					<li class="icon5"><a href="${ctx}/group/invite.html?groupId=${groupId}"<c:if test="${menu==5}"> class="on"</c:if>>邀请加入<span></span></a></li>
					<li class="icon6"><a href="${ctx}/group/member/single.html?groupId=${groupId}"<c:if test="${menu==6}"> class="on"</c:if>>创建组员<span></span></a></li>
					<li class="icon7"><a href="${ctx}/group/form.html?groupId=${groupId}"<c:if test="${menu==7}"> class="on"</c:if>>注册表单<span></span></a></li>
					<li class="icon8"><a href="${ctx}/group/content.html?groupId=${groupId}"<c:if test="${menu==8}"> class="on"</c:if>>内容管理<span></span></a></li>
					<li class="icon9"><a href="${ctx}/group/capability.html?groupId=${groupId}"<c:if test="${menu==9}"> class="on"</c:if>>能力模型<span></span></a></li>
				</ul>
			</div>
	</c:otherwise>

</c:choose> --%>