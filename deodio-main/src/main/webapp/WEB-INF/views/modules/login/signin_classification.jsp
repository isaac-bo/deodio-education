<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/signin.classification.css">

<body class="body-bg">
       <div class="login_b" style="height:auto;">
           <div class="l_box" style="width:800px;height:auto;margin:90px auto;">
               <div class="step4">
                   <div class="logo_icon text-center">
                       <img src="${ctx}/resources/img/account/logo-2.png" alt="">
                   </div>
                   <div class="login-item login-item-color">
                       <h4 class="h4">请选择你关注的课程类别！</h4>
                       <ul class="select_list" id="selectList">
                       </ul>
                       <div class="next_btn">
                           <button class="btn btn-default" onclick="saveSelectedClassifications();">保存/下一步</button>
                       </div>
                   </div>
               </div>
               <div class="clearfix"></div>
           </div>
       </div>
       
       <%@ include file="/WEB-INF/views/commons/_dialogue.jsp"%>
       
       <input value="${uKeyId}" type="hidden" id="uKeyId">
       <input value="${maxSelectedCount}" type="hidden" id="maxSelectedCount">
       <script id="signin_top_classification_data_template" type="text/x-dot-template">
		{{~it.data:v:index}}
			<li>
                <span><img src="${ctx}/resources/img/course/{{=isNullFormat(v.cover_img_before)}}" alt="" style="width: 50px; height: 50px;"></span>
				<input name="imgAfter" value="${ctx}/resources/img/course/{{=isNullFormat(v.cover_img_after)}}" type="hidden">
				<input name="imgBefore" value="${ctx}/resources/img/course/{{=isNullFormat(v.cover_img_before)}}" type="hidden">
                <h5>{{=isNullFormat(v.classification_name)}}</h5>
				<input name="classificationId" value="{{=isNullFormat(v.id)}}" type="hidden">
            </li>
		{{~}}	
		</script>
		<script type="text/javascript">
			require(['modules/login/signin.classification'],function(){
				
			});
		</script>
   </body>
</html>