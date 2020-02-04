<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.error.css">

<body>
	<div class="header">
	  <nav class="nav header-nav">
	    <div class="nav-title">
	      <div class="logo">
	        <a href="javascript:void(0);" style="background-image: url('${ctx}/resources/img/account/logo-1.png')"></a>
	      </div>
	    </div>
	  </nav>
	</div> <!-- end of header -->
	<div class="con">
		<img src="${ctx}/resources/img/error/403.png" alt="">
		<p>${errMsg}</p><a href="javascript:history.go(-1)">Go back</a></p>

	</div>	<!-- end of con -->
	<div class="footer">
		<%@ include file="/WEB-INF/views/commons/_copyright.jsp"%>
	</div>
</body>
</html>