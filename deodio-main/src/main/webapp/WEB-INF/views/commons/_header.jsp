<%@page import="com.deodio.core.utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.deodio.core.utils.CookieUtils" %>
<%@ page import="java.net.URLDecoder" %>




<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.header.footer.css">
<header>
  <c:if test="${isHomepage == true}">
  <div class="header">
      <div class="top_search">
          <div class="search_b">
              <input type="text" class="s_input"><span class="icon-search">&nbsp;</span>
          </div>
          <div class="keywords"><span style="color:#a40000">热门关键词:</span><a href="">超长关键词</a><a href="">关键词</a><a href="">关键词</a><a href="">关键词</a></div>
      </div>
  </div>
  </c:if>
  <nav class="nav header-nav">
    <div class="nav-title">
    <input type="hidden" id="hidden_header_logUrl" value="${logoUrl}">
    <input type="hidden" id="hidden_header_headerUrl" value="${headerUrl}">
    <input type="hidden" id="hidden_header_isHomepage" value="${isHomepage}">
    <input type="hidden" id="hidden_header_headerLinkUrl" value="${headerLinkUrl}">
      <div class="logo" <c:if test="${isHomepage == true}"> style="background-color:#fff;" </c:if> <c:if test="${isHomepage != true}"> style="background-color:inherit;" </c:if>>
<%--         <a href="${headerLinkUrl}" target="_blank" <c:if test="${isHomepage == true}"> style="background-image: url('${ctx}/resources/img/account/logo-1.png'); margin-top: 0px;" </c:if> <c:if test="${isHomepage != true}">style="background-image: url('${headerUrl}'); margin-top: 10px;" </c:if> ></a>--%>
         <c:if test="${isHomepage == ''}">
         <input type="hidden" value="居然是这个">
          <a href="${headerLinkUrl}" target="_blank" style="background-image: url('${ctx}/resources/img/account/logo-1.png'); margin-top: 0.1px;"></a>
         </c:if>
        <c:if test="${isHomepage != ''}">
        <input type="hidden" value="居然不是这个">
          <a href="${headerLinkUrl}" target="_blank" style="background-image: url('${logoUrl}'); margin-top: 10px;"  ></a>      
        </c:if>
        <div class="position-pic"></div>
      </div>
      <ul class="_head_menu">
      	<input name = "roleViewerId" type ="hidden" value = "${roleViewerId}">
      	<input name = "roleId" type="hidden" value ="${userRoleId }">
      	<input name = "ACCOUNT_SIZE" id="ACCOUNT_SIZE" type="hidden" value ="${ACCOUNT_SIZE}">
      	<!-- account企业用户 -->
      	<c:if test = "${roleAccountId == userRoleId}"> 
			<!-- <li id="_head_menu_1" isChild='1'><a  class="" href="javascript:void(0);">首页</a></li> -->
	        <li id="_head_menu_2" isChild='1'><a  class="" href="javascript:go2Page('/group/list.html');">我的小组 <!-- <span class="icons-9-5 down-1 up-1"> --></a></li>
	       <!--  <li id="_head_menu_3" isChild='0'><a  class="" href="">我的课程 <span class="icons-9-5 down-1 up-1"></span></a></li> -->
	        <!-- <li id="_head_menu_4" isChild='1'><a  class="" href="javascript:go2Page('/users/profile.html');">我的信息 <span class="icons-9-5 down-1 up-1"></a></li> -->
	        <!-- <li id="_head_menu_4" isChild='1'><a  class="" href="javascript:go2Page('/course/shortcut.html');">快捷方式</li> -->
	        <li id="_head_menu_5" isChild='1'><a  class="" href="javascript:void(0);">我的报表 <!-- <span class="icons-9-5 down-1 up-1"> --></a></li>
		</c:if> 
      	<!-- leader -->
      	<c:if test = "${roleLeaderId == userRoleId}"> 
			<!-- <li id="_head_menu_1" isChild='1'><a  class="" href="javascript:void(0);">首页</a></li> -->
	        <li id="_head_menu_2" isChild='1'><a  class="" href="javascript:go2Page('/group/list.html');">我的小组 <!-- <span class="icons-9-5 down-1 up-1"> --></a></li>
	        <!-- <li id="_head_menu_3" isChild='0'><a  class="" href="">我的课程 <span class="icons-9-5 down-1 up-1"></span></a></li> -->
	        <!-- <li id="_head_menu_4" isChild='1'><a  class="" href="javascript:go2Page('/users/profile.html');">我的信息 <span class="icons-9-5 down-1 up-1"></a></li> -->
	        <!-- <li id="_head_menu_4" isChild='1'><a  class="" href="javascript:go2Page('/course/shortcut.html');">快捷方式</li> -->
	        <li id="_head_menu_5" isChild='1'><a  class="" href="javascript:void(0);">我的报表 <!-- <span class="icons-9-5 down-1 up-1"> --></a></li>
		</c:if> 
		<!-- 教师 -->
		<c:if test = "${roleCreatorId == userRoleId}"> 
			<!-- <li id="_head_menu_1" isChild='1'><a  class="" href="javascript:void(0);">首页</a></li> -->
	        <li id="_head_menu_2" isChild='1'><a  class="" href="javascript:go2Page('/group/list.html');">我的小组 <!-- <span class="icons-9-5 down-1 up-1"> --></a></li>
	        <li id="_head_menu_3" isChild='0'><a  class="" href="">课程管理<span class="icons-9-5 down-1 up-1"></span></a></li>
	        <!-- <li id="_head_menu_4" isChild='1'><a  class="" href="javascript:go2Page('/users/profile.html');">我的信息 <span class="icons-9-5 down-1 up-1"></a></li> -->
	       <!--  <li id="_head_menu_4" isChild='1'><a  class="" href="javascript:go2Page('/course/shortcut.html');">快速管理</li> -->
	        <li id="_head_menu_5" isChild='1'><a  class="" href="javascript:void(0);">我的报表 <!-- <span class="icons-9-5 down-1 up-1"> --></a></li>
		</c:if> 
		<!-- 用户为学员时显示菜单 -->
		<c:if test = "${roleViewerId == userRoleId}"> 
			<!-- <li id="_head_menu_front" isChild='1'><a  class="" href="javascript:void(0);">首页</a></li> -->
			 <li id="_head_menu_group" isChild='1'><a  class="" href="javascript:go2Page('/group/list.html');">我的小组 <span class="icons-9-5 down-1 up-1"></span></a></li> 
	        <li id="_head_menu_course" isChild='1'><a  class="" href="javascript:go2Page('/course/course_viewer/list.html');">我的学习<!-- <span class="icons-9-5 down-1 up-1"> --></a></li>
	        <!-- <li id="_head_menu_club" isChild='1'><a  class="" href="javascript:go2Page('/users/profile.html');">会员<span class="icons-9-5 down-1 up-1"></a></li> -->
	        <!-- <li id="_head_menu_4" isChild='1'><a  class="" href="javascript:go2Page('/course/shortcut.html');">快速学习</a></li> -->
	        <!-- <li id="_head_menu_select_course" isChild='1'><a class="" href="javascript:go2Page('/course/course_viewer/list.html');">已选课</a></li> -->
	        <!-- <li id="_head_menu_about_us" isChild='1'><a  class="" href="javascript:void(0);">关于我们 <span class="icons-9-5 down-1 up-1"></a></li> -->
		</c:if> 
        
      </ul>
      <div class="personal-item">
        <div class="personal-item-layer">
          <div style="width:115px;" class="pull-left"><h1 id="header_nickName">
           <%if(StringUtils.isBlank(URLDecoder.decode((String)CookieUtils.getCookie(request, "N_NAME"),"UTF-8"))
        		  && "3".equals(URLDecoder.decode((String)CookieUtils.getCookie(request, "CURT"),"UTF-8"))){ 
        	      String cuName=URLDecoder.decode((String)CookieUtils.getCookie(request, "CUNAME"),"UTF-8");
        	      String cuNameD="";
        	      if(cuName.length()>12){
        	       cuNameD=cuName.substring(0, 13);
        	      }else{
        	       cuNameD=cuName;
        	      }
        		  %>
        		  <%=cuNameD%>
          <% }else{
              String nName=URLDecoder.decode((String)CookieUtils.getCookie(request, "N_NAME"),"UTF-8");
              String nNameD="";
              if(nName.length()>12){
                  nNameD=nName.substring(0, 13);
              }else{
            	  nNameD=nName;
              }
          %>
                 <%=nNameD%>
          <%} %>   
          </h1></div>
          <span class="glyphicon glyphicon-menu-down pull-left" aria-hidden="true" style="margin-top: 23px; cursor:pointer"></span>
        </div>
        
<%--         <img src="<c:if test="${empty userModel.userIcon}">${ctx}/resources/img/account/homepaage_movie_travel.jpg</c:if><c:if test="${not empty userModel.userIcon}"> ${imgStatic}${userModel.userIcon}</c:if>"  id="userIconPicHead" width="36">
 --%>         
 		<input type="hidden" id="hiddenheader_header" value="<%=URLDecoder.decode((String)CookieUtils.getCookie(request, "CUHP"),"UTF-8")%>">    
 		<%if(StringUtils.isBlank(URLDecoder.decode((String)CookieUtils.getCookie(request, "CUHP"),"UTF-8"))){ %>
          		    <img src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" id="userIconPicHead" width="40" style="height: 34px;">
        <% }else{%>
              <img src="${imgStatic}<%=URLDecoder.decode((String)CookieUtils.getCookie(request, "CUHP"),"UTF-8")%>" id="userIconPicHead" width="40" style="height: 34px;">
        <%} %>
        <div class="arrow" style="display:none;"></div>
        <div class="user_up" style="display:none;">
            <div class="l_list">
                <ul class="user_list">
                    <li><a href="javascript:go2Page('/user/profile.html');" class="active"><span class="user_icon1"></span>个人中心</a></li>                                       
                    <c:if test = "${hidden_change_account =='CACCS'}"> 
                    	<li><a href="javascript:go2Page('/account/list.html')"><span class=""></span>切换账户</a></li>
                    </c:if>
                    
             		<c:set var="v_content_creator" value = ""></c:set>
             		<c:set var="v_viewer" value=""></c:set>

                    
                    <input type="hidden" id="hiddenUserChangeAccount" value="${hidden_change_account}">
                    <input type="hidden" id="hiddenUserRoleId" value="${userRoleId}">
                    <input type="hidden" id="hiddenRoleCreatorId" value="${roleCreatorId}">
                    <input type="hidden" id="hiddenRoleViewerId" value="${roleViewerId}">
                    <input type="hidden" id="hidden_v_content_creator" value="${v_content_creator}">
                    <input type="hidden" id="hidden_v_viewer" value="${v_viewer}">
                    <input type="hidden" id="hidden_group_role_id" value="${hidden_group_role_id}">

            	 	<c:if test="${hidden_group_role_id =='GCMASS'}">
             	 		<li><a href="javascript:go2Page('/course/shortcut.html')"><span class=""></span>快速管理</a></li>
             	 		<li><a href="javascript:go2Page('/group/intro.html')"><span class=""></span>快速学习</a></li>
            		 </c:if>
            		 <c:if test="${hidden_group_role_id =='GCMS'}">
             	 		<li><a href="javascript:go2Page('/course/shortcut.html')"><span class=""></span>快速管理</a></li>
             		</c:if>
             		<c:if test="${hidden_group_role_id =='GCSS'}">
             			 <li><a href="javascript:go2Page('/group/intro.html')"><span class=""></span>快速学习</a></li>
             		</c:if>
                    <li><a href=""><span class="user_icon4"></span>操作手册</a></li>
                </ul>
            </div>
            <div class="r_pic text-center">
                <img src="${ctx}/resources/img/leidatu.png" alt="" style="margin-top:20px;">
            </div>
            <div class="clearfix"></div>
            <div class="quit"><a href="javascript:go2Page('/logout.html')">退出</a></div>
        </div>

      </div>
    </div>
    
    <div class="drop-down-list" style="display:none;">
      <ul>
        <li id="_sub_head_menu_3" style="display:none;">
          <ol>
            <li>
              <h5><span class="glyphicon glyphicon-tasks pr5" style="color:#fff;"></span>课件管理</h5><br>
              <a href="javascript:go2Page('/presentation/list.html')"> <span class="icon-tasks pr5" style="color:#fff;"></span>章节管理</a>
              <a href="javascript:go2Page('/quiz/list.html')"> <span class="icon-file pr5" style="color:#fff;"></span>试卷管理</a>
              <a href="javascript:go2Page('/survey/list.html')"> <span class="icon-bar-chart pr5" style="color:#fff;"></span>问卷调查管理</a>
            </li>
             <li>
              <h5><span class="glyphicon glyphicon-book pr5" style="color:#fff;"></span>课程管理</h5><br>
              <a href="javascript:go2Page('/course/online/list.html')"> <span class="icon-desktop pr5" style="color:#fff;"></span>线上课程管理</a>
              <a href="javascript:go2Page('/course/offline/list.html')"> <span class="icon-coffee pr5" style="color:#fff;"></span>线下课程管理</a>
              <!--a href="javascript:go2Page('/course/onlive/list.html')"> <span class="icon-facetime-video pr5" style="color:#fff;"></span>直播课程管理</a-->
            </li>
            <li class="none">
              <h5><span class="icon-cogs pr5" style="color:#fff;"></span>基础设置</h5><br>
              <a href="javascript:go2Page('/classification/list.html')"> <span class="icon-sitemap pr5" style="color:#fff;"></span>分类管理</a>
              <a href="javascript:go2Page('/knowledge/load_list.html')"> <span class="icon-leaf pr5" style="color:#fff;"></span>知识库管理</a>
            </li>
             <li>
              <h5><span class="icon-paste pr5" style="color:#fff;"></span>试题管理</h5><br>
              <a href="javascript:go2Page('/quiz/bank/list.html')"> <span class="icon-copy pr5" style="color:#fff;"></span>综合题库管理</a>
              <a href="javascript:go2Page('/quiz/list.html')"> <span class="icon-file pr5" style="color:#fff;"></span>综合试卷管理</a>
              <a href="javascript:go2Page('/survey/list.html')"> <span class=" icon-bar-chart pr5" style="color:#fff;"></span>问卷调查管理</a>
            </li>
            
            <li>
              <!--h5><span class="glyphicon glyphicon-gift pr5" style="color:#fff;"></span>课程包管理</h5><br>
              <a href="javascript:go2Page('/course/packages/list.html')"> <span class="icon-gift pr5" style="color:#fff;"></span>课程包管理</a-->
            </li>
            <li class="none">
              <h5><span class="glyphicon glyphicon-home pr5" style="color:#fff;"></span>课程设置</h5><br>
              <a href="javascript:go2Page('/trainers/list.html')"> <span class="icon-user pr5" style="color:#fff;"></span>培训讲师管理</a>
              <a href="javascript:go2Page('/location/list.html')"> <span class="icon-map-marker pr5" style="color:#fff;"></span>培训场地管理</a>
            </li>
          </ol>
        </li>
      </ul>
    </div>
      
  </nav>
</header>
<script type="text/javascript">
	require(['./modules/init' ], function(obj) {

	});
</script>