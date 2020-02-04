<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
<footer class="footer_bg footer">
    <div class="footer_con">
        <ul class="list">
            <li><a href="${footLinkUrl}" target="_blank"><img src="${footUrl}" alt=""></a></li>
            <li>
                <p>
                    <a href="">公司</a>
                    <a href="">博客/论坛</a>
                    <a href="">加入我们</a>
                    <a href="">市场</a>
                </p>
            </li>
            <li>
                <p>
                    <a href="">社区</a>
                    <a href="">开发者&lt;API&gt;</a>
                    <a href="">百宝箱</a>
                    <a href="">帮助&教程</a>
                </p>
            </li>

            <li>
                <img src="${ctx}/resources/img/erweima.png" alt="" class="pull-right mt20">
                <img src="${ctx}/resources/img/erweima.png" alt="" class="pull-right mr20 mt20">
                <h3 class="cfff">在线客服</h3>
                <dl>
                    <dd>400-820-8888</dd>
                    <dd>9:00 - 17:00</dd>
                    <dt><input type="text" class="email" value="E-mail address"></dt>
                </dl>
                <div class="clearfix"></div>
            </li>
        </ul>
    </div>
    <%@ include file="/WEB-INF/views/commons/_footer_content.jsp"%>
</footer>
	<%-- <div><%@ include file="/WEB-INF/views/commons/_copyright.jsp"%></div>
</footer> --%>
