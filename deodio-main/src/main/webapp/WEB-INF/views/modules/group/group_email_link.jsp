<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ taglib uri="/WEB-INF/views/tags/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/views/tags/fn.tld" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.layer.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.header.footer.css">
<%@ page import="com.deodio.core.utils.CookieUtils" %>
<%@ page import="java.net.URLDecoder" %>
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
      <div class="logo" <c:if test="${isHomepage == true}"> style="background-color:#fff;" </c:if> <c:if test="${isHomepage != true}"> style="background-color:inherit;" </c:if>>
        <a href="${headerLinkUrl}" target="_blank" <c:if test="${isHomepage == true}"> style="background-image: url('${ctx}/resources/img/account/logo-1.png'); margin-top: 0px;" </c:if> <c:if test="${isHomepage != true}">style="background-image: url('${headerUrl}'); margin-top: 10px;" </c:if> ></a>
        <div class="position-pic"></div>
      </div>
      <ul class="_head_menu">
      	<input name = "roleViewerId" type ="hidden" value = "${roleViewerId}">
      	<input name = "roleId" type="hidden" value ="${userRoleId }"> 
      </ul>

    </div>
      
  </nav>
</header>
<body>
<div style="height:500px;">
        
         <p align="left">  <font size="3" color="red">${tipsMessage}</font></p>
</div>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	
<script type="text/javascript">
		require(['modules/group/group'],function(obj){
		
		});
	</script>
</body>
</html>