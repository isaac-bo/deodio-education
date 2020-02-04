<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/signin.detail.css">

<body class="step5_bg">
    <div class="top_menu">
        <div class="main1200">
            <img src="${ctx}/resources/img/account/logo-1.png" alt="" class="logo">
            <ul class="top_tab" id="myTab">
                <li class="active"><a href="#personalInfomation" data-toggle="tab">完成个人信息</a></li>
                <li><a href="#personalCapability" data-toggle="tab">能力</a></li>
                <li><a href="#servicePlan" data-toggle="tab">套餐</a></li>
            </ul>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="main_con tab-content">
    	<div class="pre1_con tab-pane active" id="personalInfomation">
	    		<%@ include file="/WEB-INF/views/modules/login/_signin_information.jsp"%>
	     </div>
	     <div class="pre1_con tab-pane" id="personalCapability">
	     		<%@ include file="/WEB-INF/views/modules/login/_signin_capability.jsp"%>
	     </div>
	     <div class="pre1_con tab-pane" id="servicePlan">
	     	套餐
	     </div>
	     <input type="hidden" id="userId" value="${uKeyId}">
    </div>
    
    <div class="footer">
		 <%@ include file="/WEB-INF/views/commons/_footer_content.jsp"%>
	</div>
	
    <%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
    <script type="text/javascript">
		require(['modules/login/signin.detail'],function(){
			
		});
	</script>
   </body>
   
</html>