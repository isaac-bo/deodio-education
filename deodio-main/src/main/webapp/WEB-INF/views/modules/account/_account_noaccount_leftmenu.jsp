<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
        <!-- left begin -->
        <div class="mess_left pull-left set-ip-left">
            <div class="user_pic dropdown">
            <input type="hidden" id="hiddenHeaderimgStatic" value="${imgStatic}">
            <input type="hidden" id="hiddenHeaderimgStaticUserIcon" value="${userModel.userIcon}">
                <img class="user_pic" src="<c:if test="${empty userModel.userIcon}">${ctx}/resources/img/account/homepaage_movie_travel.jpg</c:if><c:if test="${not empty userModel.userIcon}"> ${imgStatic}${userModel.userIcon}</c:if>"  id="userIconPic"/>
                <button type="button" class="btn user_pic_edit" onclick="onUserPicEdit();"><span></span><span></span><span></span></button>
                <c:if test="${userModel.isCertified == '1'}">
                	<span class="certification"><img src="${ctx}/resources/img/account/certification.png" alt=""></span>
                </c:if>
            </div>
            <ul class="left_menu" id="accountMenu">
                <li id="menu_1" class="active"><a href="#accountInfo" data-toggle="tab" onclick="javascript:onChooseLeftMenu('1')"><img alt="" src="${ctx}/resources/img/account/left_menu_1_active.png"></img>我的账户</a></li>
                <li id="menu_4"><a href="#accountCapability" data-toggle="tab" onclick="javascript:onChooseLeftMenu('4')"><img alt="" src="${ctx}/resources/img/account/left_menu_4.png"></img>我的能力模型</a></li>
            </ul>
            
            <div class="user_pic_up" style="top: 200px; left: 320px; display:none;" id="userIcon">
				<h4>
					改变自己的照片<a href="javascript:hideIcon();"><img src="${ctx}/resources/img/close_icon.png" alt=""></a>
				</h4>
				<div class="pic_con">
				</div>
				<!-- 未上传时状态 -->
				<div class="pic_con1" style="display:none;">
					<img src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" alt="" id="userIconImg">
				</div>
				<input type="hidden" id="userIconId" name="userIconId">
				<input type="hidden" id="userIconName" name="userIconName">
				<!-- <input id="uploadUserIcon" name="uploadUserIcon" class="btn up_color" type="file"> -->
				<div style="width:244px;height:34px;background:#3eaad0;position:relative;text-align:center;overflow:hidden;line-height: 34px;margin-top: -5px;">
                  <span style="display:block;color:#fff;cursor:pointer;">上传图片</span>
                 <input accept="image/png,image/jpg, image/jpeg" id="uploadUserIcon" type="file" name="presentationUploadFile" multiple="" style="position:absolute;opacity:0;width:400px;hright:34px;right:0;top:0;cursor:pointer;">
             </div>
			</div>
        </div>
        <!-- right begin -->
        <div class="mess_right pull-right tab-content" style="min-height: 650px;">
             <div  id="accountInfo" class="tab-pane active">
    			<%@ include file="/WEB-INF/views/modules/account/_noaccount_information.jsp"%>
    		 </div>
		     <div  id="accountCapability" class="tab-pane">
		    		<%@ include file="/WEB-INF/views/modules/account/_noaccount_capability.jsp"%>
		     </div>
	     </div>
	     
	<script type="text/javascript">
		require([ 'modules/account/account' ], function() {
			
		});
	</script>