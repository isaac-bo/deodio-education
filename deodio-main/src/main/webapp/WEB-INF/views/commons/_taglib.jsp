<%@ taglib uri="/WEB-INF/views/tags/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/views/tags/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/views/tags/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/views/tags/elTag.tld" prefix="el"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page import="com.deodio.elearning.utils.CommonUtils" %>
<%@ page import="com.deodio.core.utils.CookieUtils" %>
<%@ page import="com.deodio.core.utils.StringUtils" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.deodio.elearning.constants.DConstants" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="imgStatic" value="<%=CommonUtils.IMGS_SERVER_DIR %>" />
<c:set var="openAliyun" value="<%=CommonUtils.OPEN_ALIYUN_VOD %>" />
<c:set var="userType" value="${cookie.CUT.value}" />
<c:set var="accountStatus" value="<%=CookieUtils.getCookie(request,DConstants.ACCOUNT_STATUS) %>" />
<c:set var="httpCURL" value="<%=CommonUtils.HTTP_CLIENT_URL %>" />

<c:set var="roleViewerId" value="<%=DConstants.GROUP_ROLE_VIEWER_ID %>" />
<c:set var="roleLeaderId" value="<%=DConstants.GROUP_ROLE_GROUP_LEADER_ID %>" />
<c:set var="roleCreatorId" value="<%=DConstants.GROUP_ROLE_CONTENT_CREATOR_ID %>" />
<c:set var="roleAccountId" value="<%=DConstants.ROLE_CONTENT_ACCOUNT_ID %>" />
<c:set var="currentUserId" value="<%=CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID) %>"/>
<%-- <c:set var="userRoleId" value="${cookie.CGRID.value}" /> --%>
<c:set var="userRoleId" value="${cgrid}" />

<!-- logo -->
<c:if test = "${empty accountImgMap.CLI}"> 
	<c:set var="logoUrl" value="${ctx}/resources/img/account/logo-2.png"></c:set>
</c:if> 
<c:if test = "${not empty accountImgMap.CLI}"> 
	<c:set var="logoUrl" value="${imgStatic}${accountImgMap.CLI}"></c:set>
</c:if>
<c:if test = "${not empty accountLinkUrl.CLILU}"> 
	<c:set var="logoLinkUrl" value="${accountLinkUrl.CLILU}"></c:set>
</c:if>
<c:if test = "${empty accountLinkUrl.CLILU}"> 
	<c:set var="logoLinkUrl" value="javascript:void(0);"></c:set>
</c:if>


<!-- headerUrl -->
<c:if test = "${empty accountImgMap.CHI}"> 
	<c:set var="headerUrl" value="${ctx}/resources/img/account/logo-2.png"></c:set>
</c:if> 
<c:if test = "${not empty accountImgMap.CHI}"> 
	<c:set var="headerUrl" value="${imgStatic}${accountImgMap.CHI}"></c:set>
</c:if>
<c:if test = "${not empty accountLinkUrl.CHILU}"> 
	<c:set var="headerLinkUrl" value="${accountLinkUrl.CHILU}"></c:set>
</c:if>
<c:if test = "${empty accountLinkUrl.CHILU}"> 
	<c:set var="headerLinkUrl" value="javascript:void(0);"></c:set>
</c:if>


<!-- footer -->
<c:if test = "${empty accountImgMap.CFI}"> 
	<c:set var="footUrl" value="${ctx}/resources/img/footer_logo.png"></c:set>
</c:if> 
<c:if test = "${not empty accountImgMap.CFI}"> 
	<c:set var="footUrl" value="${imgStatic}${accountImgMap.CFI}"></c:set>
</c:if>
<c:if test = "${not empty accountLinkUrl.CFILU}"> 
	<c:set var="footLinkUrl" value="${accountLinkUrl.CFILU}"></c:set>
</c:if>
<c:if test = "${empty accountLinkUrl.CFILU}"> 
	<c:set var="footLinkUrl" value="javascript:void(0);"></c:set>
</c:if>

<!-- banner -->
<c:if test = "${empty accountImgMap.CBI}"> 
	<c:set var="bannerUrl" value="${ctx}/resources/img/footer_logo.png"></c:set>
</c:if> 
<c:if test = "${not empty accountImgMap.CBI}"> 
	<c:set var="bannerUrl" value="${imgStatic}${accountImgMap.CBI}"></c:set>
</c:if>
<c:if test = "${not empty accountLinkUrl.CBILU}"> 
	<c:set var="bannerLinkUrl" value="${accountLinkUrl.CBILU}"></c:set>
</c:if>
<c:if test = "${empty accountLinkUrl.CBILU}"> 
	<c:set var="bannerLinkUrl" value="javascript:void(0);"></c:set>
</c:if>



<!-- userHeader -->
<c:if test = "${empty cookie.CUHP.value}"> 
	<c:set var="userHeader" value="${ctx}/resources/img/leidatu.png"></c:set>
</c:if> 
<c:if test = "${not empty cookie.CUHP.value}"> 
	<c:set var="userHeader" value="${imgStatic}/${cookie.CUHP.value}"></c:set>
</c:if>