 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

      <div class="left-part">
        <div class="list-item">
          <a href="${ctx}/account/setting.html"  class="icons-220px layout-img layout-domain <c:if test="${layout==0}">on</c:if>"></a>
        </div>

        <div class="list-item">
          <a href="${ctx}/account/style.html?layout=1" class="icons-100px layout-img layout-logo  <c:if test="${layout==1}">on</c:if>"></a>
          <a href="${ctx}/account/style.html?layout=2" class="icons-100px layout-img layout-header  <c:if test="${layout==2}">on</c:if>"></a>
          <a href="${ctx}/account/style.html?layout=3" class="icons-100px layout-img layout-banner  <c:if test="${layout==3}">on</c:if>"></a>
          <a href="${ctx}/account/style.html?layout=4" class="icons-100px layout-img layout-footer  <c:if test="${layout==4}">on</c:if>"></a>
        </div>
        <div class="list-item">
          <a href="layout.html" class="icons-220px layout-img layout-list <c:if test="${layout==5}">on</c:if>"></a>
        </div>
        <div class="list-item">
          <a href="theme.html" class="icons-220px layout-img layout-theme <c:if test="${layout==6}">on</c:if>"></a>
        </div>

      </div>
    