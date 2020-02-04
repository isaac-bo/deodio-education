<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/editor/editor.css">

<body class="video_bg">
    <div class="out_box" id="editor_container" style="height:100%;">
	<input type="hidden" id="presentationId" value="${presentationId}"/>
	 
    <div class="left_content" id="_left_content">   
      	<c:if test="${type==null}">  <button class="backbtn" onclick="goBack();" type="button"><img src="${ctx}/resources/img/editor/back_icon.png" alt="">返回上一步</button>
      	 </c:if>
        <div class="video_box">
       	<c:if test="${type==null}">  	
       		<button class="video_save" type="button" style="right: 135px; width: 128px;" onclick="javascript:onSavePresentation()">保存</button>
        	<button class="video_save" type="button" onclick="javascript:onPublishPresentation()">保存/发布</button>
        	 </c:if>
            <div class="video_con" style="width:100%;">
                <div class="top_title">
                    <h3 style="width:100%;">SLIDES</h3>
                </div>
                <div class="video_box2">
                    <div class="middle_video">
                    	<div class="left_video_content" style="text-align:center;vertical-align:middle;position: absolute; width: 100%;">
                    		<iframe id ="rightSco" src="" width="100%" height="100%" style="border: 1px solid #2d2d2d;"></iframe>
                    	</div>
                    	
                    </div>
                </div>
            </div>
        </div>
    </div>

    <button id="toolsBar" class="hdn_btn" type="button" onclick="javascript:onToggleRightToolBar()">
    	<span class="icon-double-angle-right"></span>
    </button>
    <div class="right_sidebar" id="settingContents">
        <div class="top_con">
            <div class="pic_container">
            	<img src="<c:if test="${empty presentationMap.presentation_cover}"> ${ctx}/resources/img/editor/my_course_pic.jpg</c:if><c:if test="${not empty presentationMap.presentation_cover}"> ${imgStatic}${presentationMap.attach_url}${presentationMap.presentation_cover} </c:if>" alt="" class="course_pic">
            </div>
            <ul class="con_list">
                <li>章节名称：${presentationMap.presentation_name}</li>
                <li><div class="star4"></div></li>
                <li>发布者：${presentationMap.username}</li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="tab-pane active">
	        <ul class="catalog nav nav-tabs" role="tablist">
<!-- 	            <li  style="width: 25%;"><a href="#medias" role="tab" data-toggle="tab">课件</a></li>
	            <li style="width: 25%;"><a href="#quiz" role="tab" data-toggle="tab">测验</a></li> -->
	            <li class="active" style="width: 50%;"><a href="#items" role="tab" data-toggle="tab">课件</a></li>
	            <li style="width: 50%;"><a href="#comments" role="tab" data-toggle="tab">评论</a></li>
	        </ul>
	        <div class="catalog_box tab-content" style="overflow-y:auto;">
	        	
<!-- 	            <ul class="contents_list tab-pane " id="medias" role="tabpanel"> 
	            </ul>
	            <ul class="contents_list tab-pane" id="quiz" role="tabpanel">
	            </ul> -->
	            <ul class="contents_list tab-pane active" id="items" role="tabpanel">
	            </ul>
	            <ul class="contents_list tab-pane" id="comments" role="tabpanel">
	            	<%@ include file="/WEB-INF/views/modules/presentation/presentation_preview_discusstion.jsp"%>	  
				</ul>
	        </div>
        </div>
    </div>
    </div>
</body>


<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>


<script type="text/javascript">
	require(['modules/presentation/presentation','modules/editor/editor','modules/presentation/presentation_scrom_preview' ], function(obj) {
		
	});
</script>

<script id="data_template" type="text/x-dot-template">
<div class="r_content">
{{~it.data:v:index}}
	<div class="conment_more_box" id="bg1">
		<div class="commenter">
			<span class="point_time_a">{{=v.identifier}}</span>
		
			<div class="clearfix"></div>
		</div>
		<div class="conmenter_con pr18" id="slides_{{=index}}" style="cursor:pointer;" onclick="javascript:onChooseScromItem('{{=v.launch}}',this,'{{=index}}');">{{=v.title}}</div>
			<div class="btm_button first_conment"></div>
	</div>
	<input type="hidden" name="countdown" id="{{=index}}" value="{{=v.timeAllowed}}">	
{{~}}

<input type="hidden" value="0" id="play_content">
<input type="hidden" value="0" id="count_time">

</div>	
<div style="height:50px;"></div>
</script>

</html>
