<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/login.css">

<body class="body-bg">
<input type="hidden" value="${logoUrl}">
<input type="hidden" id="userType" value ="${userType}">
<input type="hidden" id="accountStatus" value ="<%=CookieUtils.getCookie(request, DConstants.ACCOUNT_STATUS) %>">
<input type="hidden" id="var_accountStatus" value ="${accountStatus}">
<input type="hidden" id="uKeyId" value ="<%=URLDecoder.decode((String)CookieUtils.getCookie(request, "CAID"),"UTF-8")%>">
	<div class="login_b">
            <div class="l_box" style="width:950px;">
            	<c:if test = "${userType == 2}"> 
                <div class="left_b pull-left left_bg" style="width:550px;height:440px;margin-left:200px;" id="self_account">
                    <div class="logo_icon text-center">
                        <img src="${logoUrl}" alt="">
                    </div>
                    <div class="bg_fff">
                        <h2 class="acc_h2 text-center">你的账户</h2>
                        <div class="acc_pic pl20">
                            <div class="mt10 pb10 text-center">
                                <a href="javascript:void(0)" onclick="toAccountRolePage('<%=URLDecoder.decode((String)CookieUtils.getCookie(request, "SELF_ACCOUNT_ID"),"UTF-8")%>')"
                                class="mt10 pb10 text-center">
                                	<img src="${ctx}/resources/img/account-1.png">
                               	    <h3 class="mt10 pb10 text-center"><%=URLDecoder.decode((String)CookieUtils.getCookie(request, "N_NAME"),"UTF-8")%></h3>
                                </a>
                            </div>
<!--                             <ul class="user_list pull-left pl10">
                                <li>你的角色：<span>viewer</span></li>
                                <li><span>Group Leader</span></li>
                                <li>你的角色：<span>content creator</span></li>
                            </ul> -->
                            <div class="clearfix"></div>
                        </div>

                    </div>
                    <!-- <div class="left_icon2 mt20">We use Wipster within our business to help people connect on video projects.It saves us a whole lot of time and we love it.</div> -->
                    <div class="left_icon2 mt20 f12">We use Wipster within our business to
						help people connect on video projects.It saves us a whole lot of
						time and we love it.</div>
					<div class="text-center user_con">
						<h4>Rachel-Kate Loyd</h4>
						<p>Global Video Editor at Contiki</p>
					</div>
                </div>
                </c:if>
                <div class="right_b pull-right joined-account" style="width:580px;height:440px;position:relative;background:#f2f2f5;display:none;">
                    <div class="top_c"></div>
                    <h2 class="acc_h2 text-center" style="background:#fff;">你加入的账户</h2>
                    <div id="data_panel" class="joined-account-roll carousel slide" data-ride="carousel">
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>


		<div class="footer">
			<%@ include file="/WEB-INF/views/commons/_copyright.jsp"%>
		</div>

		<%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
		
<script id="account_leader_table_data_template" type="text/x-dot-template">
	<div class="pull-left">
       <a href="javascript:void(0)" onclick="toAccountRolePage('{{=it.data.account_id}}')">
          <img src="${ctx}/resources/img/account-1.png">
          <h3 class="mt10 pb10 text-center">{{=it.data.nick_name}}</h3>
       </a>
    </div>
    <ul class="user_list pull-left pl10">
		{{var roleArray = it.data.role_name.split("#");}}
		{{for (var i in roleArray) { }}
			<li>你的角色：<span>{{=isNullFormat(roleArray[i])}}</span></li>
		{{ } }}
    </ul>
    <div class="clearfix"></div>
</script>
		
		
<script id="table_data_template" type="text/x-dot-template">

  <ol class="carousel-indicators"  style="position: absolute;left: 20%;bottom: 0px;margin-left: 0; top:335px">
	{{~it.data:v:index}}
   	 <li data-target="#data_panel" data-slide-to="{{=index}}" {{?index==0}} class="active" {{?}}></li>
   	{{~}}
  </ol>

<div class="carousel-inner">
{{~it.data:v:index}}
	<div class="item {{?index==0}}active{{?}}">
    {{~v:obj:ii}}
	   {{? ii%2 == 0}}
       <div class="pt20 pb10" style="background:#fff;">
	      <div class="acc_pic4" style="padding:0 100px;">
	           <div class="pull-left">
			       <a href="javascript:void(0)" onclick="toAccountRolePage('{{=obj.account_id}}')">
	       		       <img src="${ctx}/resources/img/account/account-2.png">
	       		       <h3 class="mt10 pb10 text-center">账户： {{=obj.nick_name}}</h3>
			       </a>
	           </div>
	           <ul class="user_list pull-left pl10">
	     			{{var roleArray = obj.role_name.split("#");}}
					{{for (var i in roleArray) { }}
							<li>你的角色：<span>{{=isNullFormat(roleArray[i])}}</span></li>
					{{ } }}
	  	       </ul>
	  	       <div class="clearfix"></div>
	       </div>
        </div>
		{{??}}
          <div class="acc_pic4 mt10 pt20" style="padding:20px 100px 10px;height:155px;">
	         <div class="pull-left">
	            <a href="javascript:void(0)" onclick="toAccountRolePage('{{=obj.account_id}}')">
	       		       <img src="${ctx}/resources/img/account/account-2.png">
	       		       <h3 class="mt10 pb10 text-center">账户： {{=obj.nick_name}}</h3>
		         </a>
	         </div>
	         <ul class="user_list pull-left pl10">
				 {{var roleArray = obj.role_name.split("#");}}
						{{for (var i in roleArray) { }}
							<li>你的角色：<span>{{=isNullFormat(roleArray[i])}}</span></li>
					{{ } }}
	          </ul>
	          <div class="clearfix"></div>
	       </div>
		{{?}}
	{{~}}
	</div>
{{~}}
</div>
</script>


<script type="text/javascript">
	require([ 'modules/account/account_list' ], function(obj) {
		obj.onloadDataList(1);
	});
</script>

</body>
</html>