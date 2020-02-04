<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/group/group.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>

<div class="content g_border">


<%@ include file="/WEB-INF/views/modules/group/group_left_menu.jsp"%>

<div class="g_right mess_right">

		<ul class="nav nav-tabs set_tab" role="tablist" style= "background-color:#FFFFFF ">
			<c:forEach items="${memberCount}" var="item" varStatus="status">
		
		
	        <li role="presentation" <c:if test="${status.index==0}"> class="active"</c:if> >
	        	<a href="javascript:memberTab(${status.index});">
	        	<c:choose>
					<c:when test="${status.index==0}">邀请</c:when> 
					<c:when test="${status.index==1}">订阅</c:when>
					<c:when test="${status.index==2}">过期</c:when>
				</c:choose>
				<c:choose>
				<c:when test="${item.count>0 && (status.index==0)}">
				<div class="number_tips" id="number_tips_0">
					<c:choose>
						<c:when test="${item.count>99}">99+</c:when>
						<c:otherwise>${item.count-1}</c:otherwise>
					</c:choose>
				</div>
				</c:when>
				<c:when test="${item.count>0 && (status.index==1)}">
				<div class="number_tips" id="number_tips_1">
					<c:choose>
						<c:when test="${item.count>99}">99+</c:when>
						<c:otherwise>${item.count-1}</c:otherwise>
					</c:choose>
				</div>
				</c:when>
				<c:otherwise>
				<div class="number_tips" id="number_tips_2">
					<c:choose>
						<c:when test="${item.count>99}">99+</c:when>
						<c:otherwise>${item.count}</c:otherwise>
					</c:choose>
				</div>
				</c:otherwise>
				</c:choose>
				</a>
			</li>
	        </c:forEach>	
	        
	        
	        <div class="pull-right w240 mt10 mr10">
				<button type="button" onclick="searchGroupUser();"  class="btn-success btn36">&nbsp;</button>
				<div class="search_input">
					<input type="text" id="keywords" class="form-control">
				</div>
				<div class="clearfix"></div>
			</div>
	    </ul>



<%-- 		<ul class="cy_tab">
		
		<c:forEach items="${memberCount}" var="item" varStatus="status">
			<li><a href="javascript:memberTab(${status.index});"  >
			<c:choose>
				<c:when test="${status.index==0}">邀请</c:when>
				<c:when test="${status.index==1}">订阅</c:when>
				<c:when test="${status.index==2}">过期</c:when>
			</c:choose>
			</a><span>
			<c:choose>
				<c:when test="${item.count>99}">99+</c:when>
				<c:otherwise>${item.count}</c:otherwise>
			</c:choose>
			</span></li>
	
			
		</c:forEach>	
		</ul> --%>
		<div class="cy_tab_box">
			<div class="select_b mt20">
				<span class="pull-left mt10 mr10">状态</span>
				<div class="pull-left" name="nice-select">
					<select id="status_select" class="selectpicker ">
						<option value="4">--请选择--</option>
						<option value="2">待确认</option>
					    <option value="1">已激活</option>
					    <option value="0">已暂停</option>
					     <option value="3">已过期</option>
					</select>
				</div>
				<span class="pull-left mt10 mr10 ml20">角色</span>
				<div class="pull-left" name="nice-select1">
					<select id="role_select" class="selectpicker ">
						<option value=" ">--请选择--</option>
						<c:forEach var="item" items="${roleList}">
						<option value="${item.id}">${item.description}</option>
						</c:forEach>
					</select>
				</div>
				<div class="gfr">
					<button type="button" id="tabBtn1" onclick="popUpWin(1);" class="btn_bg_g btn_size" >激活</button>
					<button type="button" id="tabBtn2" onclick="popUpWin(0);"  class="btn_bg_y btn_size">暂停</button>
					<button type="button" id="tabBtn3" onclick="reNewUser();" class="btn_bg_g btn_size" style="display: none;">重新邀请</button>
					<button type="button" id="tabBtn4" onclick="deleteUser();" class="btn_bg_r btn_size" ">删除</button>
				</div>
			</div>
			
			
			<div class="pt20">
				<table id="table_panle" cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20">
					
				</table>
				<div class="mt20 text_center" id="">
				 <!-- 分页 -->
				 <div class="mt20 text-center" id="member_data_page_Panel"></div>
			</div>
			
		</div>
	</div>
</div>
	
	
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<input type="hidden" value="1" id="tab_Value">
<input type="hidden" value="" id="status_type">	
<input type="hidden" name="activeFormStatus" id="activeFormStatus" value="${activeFormStatus}">
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>





<script id="member_table_data_template" type="text/x-dot-template">
			<thead>
				<tr>
					<th>
						<div class="checkbox-group">
							<input type="checkbox" onclick="selectAllCheckBox(this)" name="remember" id="select_all">
							<label class="checkbox-2" for="select_all"></label>
						</div>
					</th>
					<th>用户昵称</th>
					<th>姓</th>
					<th>名</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>状态</th>
					<th>加入时间</th>
				</tr>
			</thead>
			<tbody>
    		{{~it.data:v:index}}
	
				<tr>
					<td>
						<div class="checkbox-group">
							<input type="checkbox" value="{{=v.id}}" onclick="singleCheckBox(this)" name="remember" id="one_{{=v.id}}">
							<label class="checkbox-2" for="one_{{=v.id}}"></label>
						</div>
					</td>
					<td><a href="${ctx}/group/member/preview.html?userId={{=v.userid}}&groupId=${groupId}">{{=isNullFormat(v.nick_name)}}</a></td>
					<td>{{=isNullFormat(v.last_name)}}</td>
					<td>{{=isNullFormat(v.first_name)}}</td>
					<td>{{=isNullFormat(v.name)}}</td>
					<td>{{=isNullFormat(v.user_mail)}}</a></td>
					<td>
				 {{?v.active_status==0}}
					已暂停
                 {{??v.active_status==2}}
                                                                        待确认
                 {{??v.active_status==3}}
                                                                      已过期
				 {{??}}
					已激活
				 {{?}}
					</td>
					<td><span class="c_929292 small">{{=dateFormat(v.create_time)}}</span></td>
				</tr>
				
 			{{~}}	
		</tbody>
</script>

<script id="pop_template" type="text/x-dot-template">


<div class="box1">
		
		<div class="table_box">
			<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20">
				<thead>
				<tr>
					<th>用户昵称</th>
					<th>用户名</th>
					<th>邮箱</th>
				</tr>
				</thead>
				<tbody>
			{{~it.data:v:index}}
				<tr>
					<td class="tleft">{{=v.userid}}</td>
					<td>{{=v.fullname}}</td>
					<td><a href="javascript:;">{{=v.usermail}}</a></td>
				</tr>
			{{~}}	
				</tbody>
			</table>

		</div>
	</div>
	<div class="modal-footer" style=" text-align: right;">
		<div class="checkbox-item inblock pull-left" style="width: 106px; margin-top: 8px; margin-bottom: 7px;">
			<div class="checkbox-group">
				<input type="checkbox" name="mailbox" id="mail_box">
				<label class="checkbox-2" for="mail_box"></label>
			</div>
			<div>&nbsp;发通知邮件</div>
		</div>
		<button type="button" class="btn cancel btn_160_36 pull-right ml10" data-dismiss="modal">取消</button>
        <button onclick="activeUser();" type="button" class="btn btn_green btn_160_36 pull-right" btn-type="true" data-dismiss="modal">提交</button>
	</div>
	

</script>
<script id="reinvite_template" type="text/x-dot-template">


<div class="box1">
		
		<div class="table_box">
			<table cellspacing="0" cellpadding="0" class="table table-striped table-hover td_h60 mt20">
				<thead>
				<tr>
					<th>用户昵称</th>
					<th>姓名</th>
					<th>邮箱</th>
				</tr>
				</thead>
				<tbody>
			{{~it.data:v:index}}
				<tr>
					<td class="tleft">{{=v.userid}}</td>
					<td>{{=v.fullname}}</td>
					<td><a href="javascript:;">{{=v.usermail}}</a></td>
				</tr>
			{{~}}	
				</tbody>
			</table>

		</div>
	</div>
	<div class="modal-footer" style=" text-align: right;">
		<div class="checkbox-item inblock pull-left" style="width: 150px; margin-top: 8px; margin-bottom: 10px;">
			<div class="checkbox-group">
				<input type="checkbox" name="mailbox" id="mail_box">
				<label class="checkbox-2" for="mail_box" onclick="validateSendForm();" id="mailboxLabel"></label>
			</div>
			<div>同时发送注册问卷调查</div>
		</div>
		<button type="button" class="btn cancel btn_160_36 pull-right ml10" data-dismiss="modal">取消</button>
        <button onclick="reInvite();" type="button" class="btn btn_green btn_160_36 pull-right" btn-type="true" data-dismiss="modal">提交</button>
	</div>
	

</script>
<input value="" type="hidden" id="hid_mails">
<input value="${groupId}" type="hidden" id="groupPkId">
	
<script type="text/javascript">
		require(['modules/group/group','modules/group/group_member'],function(group,obj){
			obj.loadDataList(1);
		});
	</script>
</body>
</html>