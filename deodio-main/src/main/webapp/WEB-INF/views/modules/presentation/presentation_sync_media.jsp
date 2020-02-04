<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/editor/editor.css">

<body class="video_bg">
    <div class="out_box" id="editor_container" style="height:100%;">
	<input type="hidden" id="presentationId" value="${presentationId}"/>

	
	

    <div class="left_content" id="_left_content">   
    <c:if test="${type==null}">
        <button class="backbtn" onclick="goBack();" type="button"><img src="${ctx}/resources/img/editor/back_icon.png" alt="">返回上一步</button>
    </c:if>
        <div class="video_box">
        	<c:if test="${type==null}">
        	<button class="video_save" type="button" style="right: 135px; width: 128px;" onclick="javascript:onSavePresentation()">保存</button>
        	<button class="video_save" type="button" onclick="javascript:onPublishPresentation()">保存/发布</button>
        	</c:if>
            <div class="video_con" style="width:100%;">
                <div class="top_title">
                    <h3>SLIDES</h3>
                    <h3>多媒体</h3>
                </div>
                <div class="video_box2">
                    <div class="left_video">
                    	<div class="left_video_content" style="text-align:center;vertical-align:middle;position: absolute; width: 100%; height: 100%;"></div>
<!--                     	<button class="edit_btn" type="button"><span class="icon-edit"></span></button> -->
                    	<input type="hidden" name='_sync_point_hidden' id='_sync_point_hidden' value=''/>
                    </div>
                    <div class="right_video">
                    	<div class="right_video_content" style="position:absolute;"></div>
<!--                     	<button class="edit_btn" type="button"><span class="icon-pencil"></span></button> -->
                    </div>
                    <button class="paly_btn" type="button"><img src="${ctx}/resources/img/editor/play_icon.png" alt=""></button>
                </div>
                <div class="video_play_bar">
                    <ul class="left_btn">
                        <li><button type="button" class="prev_media"><img src="${ctx}/resources/img/editor/play_icon1.png" alt=""></button></li>
                        <li id="media_play"><button type="button" class="play_media"><img  src="${ctx}/resources/img/editor/play_icon2.png" alt=""></button></li>
                        <li style="display: none;" id="media_pause"><button type="button" onclick="onPause();" ><img src="${ctx}/resources/img/editor/play_pause.png" alt=""></button></li>
                        <li><button type="button" class="next_media"><img src="${ctx}/resources/img/editor/play_icon3.png" alt=""></button></li>
                    </ul>
                    <ul class="right_btn">
                        <li><span class="currentLength">00:00:00</span> / <span class="maxLength">00:00:00</span></li>
                        <li><button type="button"><img src="${ctx}/resources/img/editor/play_icon4.png" alt=""></button></li>
                        <li><button type="button"><img src="${ctx}/resources/img/editor/play_icon5.png" alt=""></button></li>
                    </ul>
                    <div class="playbar_box">
                        <div class="over" style="width:0%;">
                            <span class="over_point"></span>
                        </div>
                    </div>
                    
                    <!-- 打点展开样式 -->
                    <div class="btm_edit_bar" style="display:none;width:0px;">
                       
                        <div class="input_box"><input type="text" id="comment_content"></div>
                        <ul class="right_btn_box">
                            <li><button><img src="${ctx}/resources/img/editor/brush.png" alt=""></button></li>
                            <li><span id="current_point_Time">00:21</span></li>
                            <li><button type="submit" class="btn_ti comment_content_submit" style="display:none;" onclick="submitComment()">提交</button></li>
                        </ul>
                    </div><!-- 打点展开样式结束 -->
                    <!-- 打点按钮 -->
                    
                    <div class="open_up_div" style="top: 45px;">
	                     <button class="open_up" type="button" onclick="javascript:onToggleCommentToolBar();">
	                       <div class="user_img"> <img src="${userHeader}" width="36" style="border-color: #4275F4;"></div>
	                       <div class="comment_img">
	    					打点 <span class="arrow icon-double-angle-right" style="margin-left: 5px;position: absolute;font-size: 20px;"></span>
	    					</div>
	    				</button>
    				</div>
                </div>
            </div>
            
<!-- 时间轴 --><!--每移动一个图片距离是多加160像素-->
            <div style="width:100%;position: relative;top: -58px;">
            <div id="time_pointer" class="pointer" style="left:330px;top:43px;position: absolute"></div>
                <div class="time_scale"  style="margin-top:60px;">
                    <div class='ruler _time_line' id="time_scale_up" style="width:899px;"> <!-- 计算宽度 -->
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        <div class='cm'>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                            <div class='mm'></div>
                        </div>
                        
                    </div>
                </div>
                <div class="height_auto" style="height:300px;overflow-y:scroll;position:relative;"><!--高度根据窗口尺寸计算-->
                    <ul class="bottom_list" style="width:180px;float:left;">
                        <li><span class="icon_pic"><img src="${ctx}/resources/img/editor/icon1.png" alt=""></span></li>
                        <li><span class="icon_pic"><img src="${ctx}/resources/img/editor/icon2.png" alt=""></span></li>
                        <li><span class="icon_pic"><img src="${ctx}/resources/img/editor/icon3.png" alt=""></span></li>
                    </ul>
                    
                    <div id="time_line_down" class="right_con_height" style="width:899px;overflow-x:auto;float:left;"> <!-- 计算宽度 -->
                        <ul class="bottom_list" style="width:${mediaLength}px;"><!--宽度根据里面内容多少来算-->
                          <li class="mediaLine"></li>
                        <li class="syncPointLine"></li>
                        <li class="quizPointLine"></li>
                        </ul>
                        
                    </div>
                </div>
                
                
                
                
            </div>


       
            <!-- end -->
            
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
	            <li class="active"  style="width: 25%;"><a href="#medias" role="tab" data-toggle="tab">课件</a></li>
	            <li style="width: 25%;"><a href="#quiz" role="tab" data-toggle="tab">测验</a></li>
	            <li style="width: 25%;"><a href="#survey" role="tab" data-toggle="tab">问卷</a></li>
	            <li style="width: 25%;"><a href="#comments" role="tab" data-toggle="tab">评论</a></li>
	        </ul>
	        <div class="catalog_box tab-content" style="overflow-y:auto;">
	        	
	            <ul class="contents_list tab-pane active" id="medias" role="tabpanel"> 
	            </ul>
	            <ul class="contents_list tab-pane" id="quiz" role="tabpanel">
	            </ul>
	            <ul class="contents_list tab-pane" id="survey" role="tabpanel">
	            </ul>
	            <ul class="contents_list tab-pane" id="comments" role="tabpanel">
	

		</ul>
	            
	        </div>
        </div>
    </div>
    </div>
</body>


<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>


<script type="text/javascript">
	require(['modules/presentation/presentation','modules/editor/editor','modules/editor/sync.comment','modules/presentation/presentation_sync_media' ], function(obj) {
		
	});
</script>


<script id="comment_list_template" type="text/x-dot-template">
<div class="r_content">
{{~it.data:v:index}}


	<div class="user_top border_line" id="con_comment_header_{{=v.id}}">
		<img src="${imgStatic}{{=v.user_icon}}" alt="" class="user_40" style="border-color: #4275F4;"> <span class="user_name">{{=v.username}}</span>
	</div>
	
	{{~v.itemList:c:jndex}}
	{{? jndex == 0}}					
	<div class="conment_more_box" id="con_comment_{{=v.id}}">
	    <div class="commenter">
			<span class="point_time_a">{{=formatTimes(v.comment_time)}}</span> <span class="time_a">{{=formatDate2Char(c.create_time)}}</span>
			<span id="handle_comment_{{=v.id}}" class="top_right_btn pull-right {{? v.comment_handle == 1}}check_active{{?}}" onclick="javascript:onHandleComments('{{=v.id}}');"></span>
			<div class="clearfix"></div>
		</div>
		<div class="conmenter_con pr18" id="edit_comment_text_{{=c.id}}">{{=c.comment}}</div>
		<div class="btm_button" style="padding-left: 20px; padding-right: 20px;border-bottom-width: 0px;display:none;" id="edit_comment_area_{{=c.id}}">
			<div class="input-group">
				<textarea style="font-size:12px;word-break: break-all;line-height: 23px;height: 60px;padding: 10px;"  
						  class="form-control edit_comment_area_con_{{=c.id}}" rows="3" placeholder="按回车键发送" onkeypress="onUpdateComments(event,'{{=c.id}}',false)">{{=c.comment}}</textarea>
			</div>	
		</div>
		<div class="btm_button first_conment">
			<span class="c767676" style="cursor: pointer;" onclick="javascript:onFeedComments('{{=v.id}}',false)">回复 · {{=v.itemList.length - 1}}</span> 
			<span class="ml10 glyphicon glyphicon-thumbs-up" style="color: #4985FF;font-size: 16px; top: 3px;cursor:pointer;" onclick="javascript:onFollowComments('{{=c.id}}')"></span>
			<span id="follow_comment_{{=c.id}}" style="color: #4985FF;margin-left: 5px; {{? c.follows_num <= 0 || c.follows_num == undefined}} display:none; {{?}}">· {{=c.follows_num}} </span>
			<span class="pull-right"> 
				<a href="javascript:onDelComments('{{=v.id}}',false)" class="ml10 delete"></a>
				<a href="javascript:onCopyComments('{{=c.id}}',false)" class="ml10 copy" data-clipboard-demo="" id="copy_{{=c.id}}"  data-clipboard-action="copy" data-clipboard-target="#edit_comment_text_{{=c.id}}"></a> 
				<a href="javascript:onEditComments('{{=c.id}}',false)" class="ml10 edit"></a>
			</span>
			{{? v.itemList.length > 1}}
			<button class="open_btn" type="button" id="open_comment_{{=v.id}}" onclick="javascript:onDisplaySubComments('{{=v.id}}')"></button>
			{{?}}
			<div class="clearfix"></div>
			<div class="input-group" id="feed_comment_{{=v.id}}" style="display:none;">
				<input type="text" id="feed_con_comment_{{=v.id}}" class="form-control" placeholder="回复" onkeypress="javascript:onSubmitComments(event,'{{=v.id}}',false)">
			</div>
		</div>
	</div>
	
	<div class="conment_more_box" style="display:none;" id="del_comment_{{=v.id}}">
		<div class="com_rel" style="height: 150px;">
			<div class="up_box">
				<div class="up_box_btn text-center">
					<p>您确定是否删除这条评论?</p>
					<button type="button" class="btn cancel" onclick="onCancelDelComments('{{=v.id}}',false)">取消</button>
					<button type="button" class="btn ok" onclick="onSubmitDelComments('{{=v.id}}',false)">确定</button>
				</div>
			</div>
		</div>
	</div>
	{{?}}
	  
	  {{? jndex > 0}}
			
		  {{? jndex == 1}}
	 <div class="conment_more_box" style="display: none; " id="conment_more_show_{{=v.id}}">
		  {{?}}
		  
		<div class="com_rel" style="background:#3f475a none repeat scroll 0 0;" id="con_comment_{{=c.id}}">
			<div class="com_rel2">
				<div class="user_top">
					<img src="${imgStatic}{{=c.user_icon}}" alt="" class="user_40"  style="border-color: #4275F4;"> 
				    <span class="user_name">{{=c.username}}</span> <span class="time_a">{{=formatDate2Char(c.create_time)}}</span>
				</div>
				<div class="conmenter_relcon" id="edit_comment_text_{{=c.id}}">{{=c.comment}}</div>
				<div class="btm_button" style="padding-left: 0px; padding-right: 0px;border-bottom-width: 0px;display:none;" id="edit_comment_area_{{=c.id}}">
					<div class="input-group">
						<textarea style="font-size:12px;word-break: break-all;line-height: 23px;height: 60px;padding: 10px;"  
						  	class="form-control edit_comment_area_con_{{=c.id}}" rows="3" placeholder="按回车键发送" onkeypress="onUpdateComments(event,'{{=c.id}}',false)">{{=c.comment}}</textarea>
					</div>	
				</div>

				<div class="btm_button pl0">
					<!--span class="c767676">回复 · 3</span--> 
					<span class="ml10 glyphicon glyphicon-thumbs-up" style="color: #4985FF;font-size: 16px; top: 3px;cursor:pointer;" onclick="javascript:onFollowComments('{{=c.id}}')"></span>
					<span id="follow_comment_{{=c.id}}" style="color: #4985FF;margin-left: 5px; {{? c.follows_num <= 0 || c.follows_num == undefined}} display:none; {{?}}">· {{=c.follows_num}} </span>
					<span class="pull-right"> 
						<a href="javascript:onDelComments('{{=c.id}}',true)" class="ml10 delete"></a>
						<a href="javascript:onCopyComments('{{=c.id}}',false)" class="ml10 copy" id="copy_{{=c.id}}" data-clipboard-demo=""  data-clipboard-action="copy" data-clipboard-target="#edit_comment_text_{{=c.id}}"></a> 
 						<a href="javascript:onEditComments('{{=c.id}}',true)" class="ml10 edit"></a>
					</span>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	
		<div class="com_rel" style="height: 150px;display:none;" id="del_comment_{{=c.id}}">
			<div class="up_box">
				<div class="up_box_btn text-center">
					<p>您确定是否删除这条评论?</p>
					<button type="button" class="btn cancel" onclick="onCancelDelComments('{{=c.id}}',true)">取消</button>
					<button type="button" class="btn ok" onclick="onSubmitDelComments('{{=c.id}}',true)">确定</button>
				</div>
			</div>
		</div>

		  {{? jndex == v.itemList.length -1}}
	 </div>
		  {{?}}
	  {{?}}

	  {{~}}
{{~}}
</div>
<div style="height:50px;"></div>

</script>


<script id="comment_point_template" type="text/x-dot-template">

<span class="set_point" id="comment_{{=it.commentId}}" style="border:4px solid #4275f4;background:#fff;left:{{=it.posX}}px;top:{{=it.posY}}px">
	<input type="hidden" id="comment_content_{{=it.commentId}}" value=""/>
</span>

</script>

<script id="comment_point_line_template" type="text/x-dot-template">
	<span id="comment_line_{{=it.commentId}}" style="top:8px;margin-left:-4px;background-color:#4275f4;width:8px;height:8px;border-radius:50%;position:absolute;left:{{=it.position}}"></span>
</script>

<script id="slides_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
<li  id= "_orginal_slides_{{=v.id}}">
	<h4 class="h4_title">
		<span class="h4_title_span" >
			<div class="slides_pic">
				<img src="${imgStatic}{{=v.slideUrl}}" alt="" class="pic_size">
			</div>
		</span>
	</h4>
</li>
{{~}}
<div style="height:50px;"></div>
</script>


<script id="quiz_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
<li  id= "_orginal_quiz_{{=v.id}}">
	<h4 class="h4_title">
		<span class="h4_title_span" style="border-color:rgb(101, 170, 221);">
			<div class="panel_pic_left" style="background-color:rgb(101, 170, 221);">
				<img src="${ctx}/resources/img/editor/icon4.png" alt="" class="pic_size" style="height: 55px; width: 55px; float: left; margin-top: 15px;">
			</div>

			
			<div class="panel_pic_right">	
				<dt style="color:#fff;">
					名称：{{=subString(v.quizName,10)}}
				</dt>
				<dd style="color:#fff;">
					描述：{{=removeHtmlTags(isNullFormat(v.quizDesc))}}
				</dd>
			</div>
		</span>
	</h4>
</li>
{{~}}
<div style="height:50px;"></div>
</script>


<script id="survey_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
<li id= "_orginal_survey_{{=v.id}}">
	<h4 class="h4_title">
		<span class="h4_title_span"  style="border-color:rgb(113, 174, 145);">
			<div class="panel_pic_left" style="background-color:rgb(113, 174, 145);">
				<img src="${ctx}/resources/img/editor/icon5.png" alt="" class="pic_size" style="height: 55px; width: 55px; float: left; margin-top: 15px;">
			</div>
			<div class="panel_pic_right">	
				<dt style="color:#fff;">
					名称：{{=subString(v.survey_name,10)}}
				</dt>
				<dd style="color:#fff;">
					描述：{{=removeHtmlTags(isNullFormat(v.survey_desc))}}
				</dd>
			</div>
		</span>
	</h4>
</li>
{{~}}
<div style="height:50px;"></div>
</script>

</html>
