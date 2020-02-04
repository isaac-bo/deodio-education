<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/editor/editor.css">

<body class="video_bg">
    <div class="out_box" id="editor_container" style="height:100%;">
	<input type="hidden" id="presentationId" value="${presentationId}"/>

    <div class="left_content" id="_left_content">   
     	<c:if test="${type==null}">   <button class="backbtn" onclick="goBack()" type="button"><img src="${ctx}/resources/img/editor/back_icon.png" alt="">返回上一步</button></c:if>
        <div class="video_box">
        	<c:if test="${type==null}">
        	<button class="video_save" type="button" style="right: 135px; width: 128px;" onclick="javascript:onSavePresentation()">保存</button>
        	<button class="video_save" type="button" onclick="javascript:onPublishPresentation()">保存/发布</button>
        	</c:if>
            <div class="video_con" style="width:100%;">
                <div class="top_title">
                    <h3 style="width:100%;">Video</h3>
                </div>
                <div class="video_box2">
                    <div class="middle_video">
                    	<div class="left_video_content" style="text-align:center;vertical-align:middle;position: absolute; width: 100%; height: 100%;">
                    		<!-- 设置课件的规则 -->
							<c:if test="${presentationModelExternal.externalType == 1}">
							<iframe id="itemFramePanel" class="form-control wb100" style="height: 100%; width:100%;border:1px solid #f3f3f3;border-radius: 0;" src="<c:if test="${presentationModelExternal.externalType == 1}">${presentationExternalItem.externalUrl}</c:if>">
								您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。
							</iframe>
							</c:if>
						
							<c:if test="${presentationModelExternal.externalType == 2}">	
							<div id="itemYoukuPanel" style=" background:#e3e3e3;height: 100%">
									
								<c:choose>
									<c:when test="${fn:contains(presentationExternalItem.externalVideoUrl, '.swf')}">
											<embed src='${presentationExternalItem.externalVideoUrl}' allowFullScreen='true' quality='high' width='100%' height='100%' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash'></embed>
									</c:when>
									<c:otherwise>
										<iframe height='100%' width='100%' src='${presentationExternalItem.externalVideoUrl}' frameborder=0 'allowfullscreen'></iframe>
									</c:otherwise>
								
								</c:choose>
								
								
								
							
							</div>
							</c:if>
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
<!-- 	            <li class="active" style="width: 50%;"><a href="#items" role="tab" data-toggle="tab">课件</a></li> -->
	            <li  class="active"  style="width: 100%;"><a href="#comments" role="tab" data-toggle="tab">评论</a></li>
	        </ul>
	        <div class="catalog_box tab-content" style="overflow-y:auto;">
	        	
<!-- 	            <ul class="contents_list tab-pane " id="medias" role="tabpanel"> 
	            </ul>
	            <ul class="contents_list tab-pane" id="quiz" role="tabpanel">
	            </ul>
	            <ul class="contents_list tab-pane active" id="items" role="tabpanel">
	            </ul> -->
	            <ul class="contents_list tab-pane active" id="comments" role="tabpanel">
	            	<%@ include file="/WEB-INF/views/modules/presentation/presentation_preview_discusstion.jsp"%>	  
				</ul>
	        </div>
        </div>
    </div>
    </div>
</body>


<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>


<script type="text/javascript">
	require(['modules/presentation/presentation','modules/editor/editor','modules/presentation/presentation_external_preview' ], function(obj) {
		
	});
</script>

<script id="data_template" type="text/x-dot-template">
<div class="r_content">
{{~it.data:v:index}}
	<div class="conment_more_box" id="bg1">
		<div class="commenter">
			<span class="point_time_a">{{=v.identifier}}</span>
			<span class="top_right_btn pull-right"></span>
			<div class="clearfix"></div>
		</div>
		<div class="conmenter_con pr18" style="cursor:pointer;" onclick="javascript:onChooseScromItem('{{=v.launch}}',this);">{{=v.title}}</div>
		<div class="btm_button first_conment">
		
									
		</div>
	</div>
{{~}}
</div>	
<div style="height:50px;"></div>
</script>

</html>
