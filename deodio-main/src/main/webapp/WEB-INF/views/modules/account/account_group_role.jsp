<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/login.css">

<body class="body-bg">
	<div class="login_b" style="height:auto;">
         <div class="l_box" style="width:840px;height:auto;margin:10px auto;">
             <div class="step4 h620" style="width:630px;float:left;margin:0 auto;margin-top: 80px;position:relative;">
                 <div class="logo_icon text-center">
                     <%-- <input type="hidden" value="${logoUrl}" id="hiddenLogoURL"> --%>
                     <input type="hidden" value="${ctx}/resources/img/account/logo-2.png " id="hiddenLogoURL">
                     <img src="${ctx}/resources/img/account/logo-2.png" alt="">
                 </div>
                 <c:set var="tabPanePre" value="list"></c:set>
                 <div class="login-item-color h500 min_h500 tab-content" id="group_slider_container">
                    <div u="slides" class="slides" style="position: absolute; overflow: hidden; width:630px;height:500px;">
                    <c:forEach items="${groupList}" var="item" varStatus="status">
             			<!-- div 头尾设置 -->
                    	<c:if test="${status.index % 6 == 0 && status.index != 0}">
                    		</div>
                    		<fmt:formatNumber var="statusIndex" type="number" value="${status.index/1}" maxFractionDigits="0" pattern="#"/>
                    		<div class="cen_left_list tab-pane" id="${tabPanePre}${statusIndex}">
                    	</c:if>
                    	
                    	<!-- div 头尾设置 -->
                    	<c:if test="${status.index == 0 }">
                    		<div class="cen_left_list tab-pane active" id="${tabPanePre}0">
                    	</c:if>
                    
                    	<div class="list_box box4">
				      		<a href=""><img src="${ctx}/resources/img/account/pic1.jpg" alt="" class="pic">
				        	<ul>
				            	<li><dt>组名:</dt><dd class="group_name" title="${item.group_name}">${item.group_name}</dd></li>
				            	<li><dt name="groupRoleName">角色:</dt> <dd>${item.group_role_name}</dd></li>
				            	<li><dt>简介:</dt> <dd class="group_desc" title="${item.group_description}">${item.group_description}</dd></li>
				        	</ul>
				        	</a>
				        	<input type="hidden" name="hasLeader" value="${item.has_leader}">
	       		        	<input type="hidden" name="groupId" value="${item.group_id}">
	       		        	<input type="hidden" name="groupRoleId" value="${item.group_role_id}">
	       		        	<input type="hidden" name="accountId" value="${item.account_id}">
	       		        	<input type="hidden" name="type" value="1">
				    	</div>
				    	
                    </c:forEach>
                    <c:if test="${ groupList != null && fn:length(groupList) != 0 }">
                    	</div>
                    </c:if>
                    </div>
                    
                    <div data-u="navigator" class="slidetip">                                      
		               <div data-u="prototype" style="width:12px; height:12px; background-color:#a7a7a7; border-radius:12px;"></div>
		            </div>
                    
                 </div>
                 <div class="text-center">
              <%-- 	<div data-u="navigator" class="slidetip">  
                 	   <c:forEach items="${groupList}" step = '6' varStatus="status">                                    
		               <div data-u="prototype" style="width:12px; height:12px; background-color:#a7a7a7; border-radius:12px;"></div>
		                </c:forEach>
		          </div>  --%>
		          
	    			<%-- <ul class="more_icon">
	                    <c:forEach items="${groupList}" step = '6' varStatus="status">
	             			<li <c:if test='${status.index == 0}'> class="active" </c:if> ><a href="${tabPanePre}${status.index}" data-toggle="tab" class="switch_a"></a></li>
	                    </c:forEach>
                   	</ul> --%>
				</div>
             </div>
             
             <c:set var="v_content_creator" value = ""></c:set>
             <c:set var="v_viewer" value=""></c:set>
             
             <c:forEach items="${roleList}" var="item">
             <c:if test="${item.group_role_id == '10001' && v_content_creator != '10001' }">
             	<c:set var="v_content_creator" value = "10001"></c:set>
             </c:if>
             
             <c:if test="${item.group_role_id == '10002' && v_viewer != '10002' }">
             	<c:set var="v_viewer" value = "10002"></c:set>
             </c:if>
             </c:forEach>
             
             
             
             <c:if test="${v_content_creator == '10001' || v_viewer == '10002'}">  
             <div class="cen_right h620 step4" style="width:200px;float:right;margin:0 auto;margin-top: 80px;position:relative;">
                 <div class="top_title text-center">快捷入口</div>
                 
                 <c:if test="${v_content_creator == '10001'}">
                 		<div class="group_icon text-center">
		                     <a href="javascript:void(0);"><img src="${ctx}/resources/img/account/group_pic.png" alt="" class="mt70"></a>
		                     <%-- <span class="g_icon"><img src="${ctx}/resources/img/account/group_icon.png" alt=""></span> --%>
		                     <input type="hidden" name="type" value="2">
		                     <input type="hidden" name="groupRoleId" value="10001">
		                 </div>
                 </c:if>
                 	
                 <c:if test="${v_viewer == '10002'}">
                 		<div class="viewer_icon text-center">
		                     <a href="javascript:void(0);"><img src="${ctx}/resources/img/account/view_pic.png" alt="" class="mt70"></a>
		                    <%--  <span class="v_icon"><img src="${ctx}/resources/img/account/viewer_icon.png" alt=""></span> --%>
		                     <input type="hidden" name="type" value="2">
		                     <input type="hidden" name="groupRoleId" value="10002">
		                 </div>
                  </c:if>
             </div>
             </c:if>
             <div class="clearfix"></div>
         </div>
     </div>
        

	<div class="footer">
		<%@ include file="/WEB-INF/views/commons/_copyright.jsp"%>
	</div>

	<%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
	
	<script type="text/javascript">
		require([ 'modules/account/account_group_role' ], function(obj) {
			//obj.onloadDataList(1);
		});
	</script>

</body>
</html>