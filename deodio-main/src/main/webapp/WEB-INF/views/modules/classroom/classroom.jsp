<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/editor/editor.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">

<%@ include file="/WEB-INF/views/modules/classroom/classroom_course_presentation_sync.jsp"%>
<%@ include file="/WEB-INF/views/modules/classroom/classroom_course_presentation_scrom.jsp"%>
<%@ include file="/WEB-INF/views/modules/classroom/classroom_course_presentation_pack.jsp"%>  
<%@ include file="/WEB-INF/views/modules/classroom/classroom_course_presentation_external.jsp"%>  
<%@ include file="/WEB-INF/views/modules/classroom/classroom_course_quiz.jsp"%> 
<%@ include file="/WEB-INF/views/modules/classroom/classroom_course_survey.jsp"%>  

<body class="video_bg">
    <div class="out_box" style="height:100%;">


    <div class="left_content">
        <button class="backbtn" style="z-index: 9999;"  type="button"><img src="${ctx}/resources/img/viewer/back_icon.png" alt="" onclick="goBack();">返回课程首页</button>
        <div class="left_sidebar">
            <div class="line"></div>
<%--             <c:if test="${courseItemSortInfo.current_sort < courseItemSortInfo.max_sort }"> --%>
            	<button id="btnNextItem" class="top_btn" style="z-index: 9999;" onclick="onNextItem();"><img src="${ctx}/resources/img/viewer/top_img.png" alt=""></button>
<%--             </c:if> --%>
           <%--  <c:if test="${courseItemSortInfo.current_sort > courseItemSortInfo.min_sort }"> --%>
            	<button id="btnPreItem" class="btm_btn" style="z-index: 9999;" onclick="onPreItem();"><img src="${ctx}/resources/img/viewer/btm_img.png" alt=""></button>
<%--             </c:if> --%>
        </div>
        
        <div class="item_container" style="position:relative;">
	        <div class="left_sidebar" style="position:relative;">           
	            <div class="title">
	                <span class="item_type">章节<em>${itemIndex}</em></span><text class="item_name"></text>
	                <input type="hidden" id="hiddenCourseItemId" value="${itemId}">
	                <input type="hidden" id="presentationId" value="${presentationId}">
	                <input type="hidden" id="quizId" value="${quizId}">
	                <input type="hidden" id="surveyId" value="${surveyId}">
	                <input type="hidden" id="hiddenCourseId" value="${courseId}">
	                <input type="hidden" id="hiddenCourseItemIndex" value="${itemIndex}">
	                <input type="hidden" id="hiddenItemsCount" value="${courseItemSortInfo.max_sort}">
	            </div>
	        </div>
	        
	        <div class="video_box"  style="width: 95%; margin-left: 40px; position: absolute; top: 0px;">
	            <div class="video_con" style="width:100%;height:620px;">
	            	
	            </div>
	            <div class="btm_bar" style="position: relative;">
	                <ul class="pull-left" style="position:absolute;z-index:99;">
	                    <li><img src="${ctx}/resources/img/viewer/btm_icon1.png" alt=""><a href="">章节介绍</a></li>
	                    <li><img src="${ctx}/resources/img/viewer/btm_icon2.png" alt=""><a href="">分享</a></li>
	                    <li><img src="${ctx}/resources/img/viewer/btm_icon3.png" alt=""><a href="">报告问题</a></li>
	                    
	                    
	                    <li class="sync_comment_button" style="display:none;"><span class="icon-comments" style="color:#fff;font-size: 16px;margin-right:10px;"></span><a href="javascript:void(0);" onclick="javascript:onToggleCommentToolBar();">打点</a></li>
	                </ul>
	
		             <div class="btm_edit_bar" style="display:none;width:0px;">
		         
			             <div class="input_box"><input type="text" id="comment_content"></div>
			             <ul class="right_btn_box">
			                 <li><button><img src="${ctx}/resources/img/editor/brush.png" alt=""></button></li>
			                 <li><span>00:21</span></li>
			                 <li><button type="submit" class="btn_ti comment_content_submit" style="display:block;" onclick="submitComment()">提交</button></li>
			             </ul>
			         </div><!-- 打点展开样式结束 -->
	
	                <button class="btn learn pull-right" id="presentation_learning_type" style="display:none;">学过了</button>
	                <button class="btn learn pull-right" id="quizSurvey_learning_type" style="display:none;">提交</button>
	                <button class="important pull-right"><img src="${ctx}/resources/img/viewer/btm_icon4.png" alt="">重要</button>
	                <div class="clearfix"></div>
	            </div>
	        </div>
        </div>
    </div>
    
    <button id="toolsBar" class="hdn_btn" type="button" onclick="javascript:onToggleRightToolBar(400)" style="right:400px">
       	<span class="icon-double-angle-right"></span>
   	</button>
    
    <div class="right_sidebar" id="settingContents">
        <div class="top_con">
            <img src="${ctx}/resources/img/viewer/my_course_pic.jpg" alt="" class="course_pic">
            <ul class="con_list">
                <li>课程名称：${courseMap.course_name}</li>
                <li><div class="star4"></div></li>
                <li>发布者：${courseMap.publisher}</li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <ul class="right_bar catalog  nav nav-tabs" role="tablist">
            <li class="active"><a href="#agenda" role="tab" data-toggle="tab">目录</a></li>
            <li><a href="#discuss" role="tab" data-toggle="tab">讨论区</a></li>
            <li class="comments_tab" style="display:none;"><a href="#comments" role="tab" data-toggle="tab">评论</a></li>
            <li class="items_tab" style="display:none;"><a href="#items" role="tab" data-toggle="tab">条目</a></li>
        </ul>
        <div class="catalog_box tab-content" style="overflow-y:auto;">
            <ul class="contents_list tab-pane active" id="agenda" role="tabpanel"> 
            </ul>
            <ul class="contents_list tab-pane" id="discuss" role="tabpanel"> 
            </ul>
            <ul class="contents_list tab-pane" id="comments" role="tabpanel"> 
            </ul>
            <ul class="contents_list tab-pane" id="items" role="tabpanel"> 
            </ul>
        </div>
    </div>
    </div>
    <%-- <%@ include file="/WEB-INF/views/commons/_footer.jsp"%> --%>
    
    <script id = "courser_viewer_course_content_list_data_template" type="text/x-dot-template">
	    <ul class="contents_list">
			{{~it.data:v:index}}
	        	<li>
	            	<span class="lession text-right">课时{{= index+1 }}</span>
	            	<span class="round_b" style="background:#fff;">
						{{? v.is_pass == '1'}}
							<em class="round_con" style=""></em>
						{{?? v.is_pass == '0'}}
							<em class="round_con" style="width:4px;border-radius:50% 0 0 50%;"></em>
						{{?? v.is_required == '1'}}
							<img src="${ctx}/resources/img/viewer/lock.png" alt="">
						{{?}}
					</span>
	            	<h4 class="h4_title">
						<a {{? isNullFormat(v.item_id) != '' }}
									href="javascript:go2Page('/classroom.html','itemId={{=isNullFormat(v.item_id)}}&itemIndex={{=index+1}}&courseId=${courseMap.id}&itemType={{=v.item_type}}&groupId=${groupId}')"
						   {{?}}>
							<em class="green">
								{{? v.item_type == '0'}}
									【课程】
								{{?? v.item_type == '1'}}
									【测试】
								{{?? v.item_type == '2'}}
									 【问卷调查】
								{{??}}
									 【论文必学】
								{{?}}
							</em>{{=isNullFormat(v.item_name)}}
						</a>
					</h4>
					<input type="hidden" name="itemId" value="{{=isNullFormat(v.item_id)}}">
	        	</li>
	        {{~}}
	    </ul>

	</script>

	<script type="text/javascript">
	require([ 'modules/classroom/classroom','modules/editor/editor','modules/classroom/classroom_course_presentation_scrom','modules/classroom/classroom_course_presentation_sync','modules/classroom/classroom_course_presentation_external','modules/classroom/classroom_course_presentation_pack','modules/editor/sync.comment','modules/classroom/classroom_course_quiz','modules/classroom/classroom_course_survey'], function(obj,editor) {
		editor.initToolBarRight('400');
	}); 
	</script>
</body>
</html>