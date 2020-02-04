<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.layer.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
    <div class="content">
        <!-- left begin -->
         <c:choose>
                <c:when test="${accountModel.id != null}">
		    		 <%@ include file="/WEB-INF/views/modules/account/_account_left_menu.jsp"%>
                </c:when>
                <c:otherwise>
                	<%@ include file="/WEB-INF/views/modules/account/_account_noaccount_leftmenu.jsp"%>
                </c:otherwise>
          </c:choose>
        <div class="clearfix"></div>
    </div>
    
    <input type="hidden" id="uKeyId" value="${uKeyId}">

	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	
	<script type="text/javascript">
		require(['modules/account/account.layer'],function(){
			
		});
	</script>
</body>
