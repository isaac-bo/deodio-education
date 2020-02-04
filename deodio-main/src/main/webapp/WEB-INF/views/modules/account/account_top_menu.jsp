<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <div>
 	<c:if test='${isAccountOwner == false}' >
 	  	<a href="${ctx}/users/profile.html?accountId=${accountId}" class="on">我的信息</a>
 	 	 <a href="${ctx}/account/activity.html?uKeyId=${uKeyId}" class="">我的活动</a>
 	</c:if>
 	
   <c:if test='${isAccountOwner != false}' >
   		<a href="${ctx}/users/profile.html" <c:if test='${navTab==1}' >class="on" </c:if>>我的信息</a>
  	 	<a href="${ctx}/account/organization.html" <c:if test='${navTab==2}' >class="on" </c:if>>我的组织</a>
  	 	<a href="${ctx}/account/setting.html" <c:if test='${navTab==3}' >class="on" </c:if>>我的设置</a>
  	 	<a href="${ctx}/account/capability.html" <c:if test='${navTab==4}' >class="on" </c:if>>我的能力模型</a>
   		<a href="${ctx}/account/activity.html" <c:if test='${navTab==5}' >class="on" </c:if>>我的活动</a>
   		<a href="${ctx}/account/payment.html" <c:if test='${navTab==6}' >class="on" </c:if>>我的付款</a>
   </c:if>
 </div>