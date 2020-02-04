<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<body>
	<div class="content p40">
	    <div class="course_topbox">
	        <div class="left_pic">
	        	<%-- <img src="${ctx}/resources/img/viewer/course_pic.jpg" alt=""> --%>
	        	<img src="<c:if test="${empty courseMap.course_cover_img}">${ctx}/resources/img/viewer/course_pic.jpg</c:if><c:if test="${not empty courseMap.course_cover_img}"> ${imgStatic}${courseMap.attach_url}/${courseMap.course_cover_img} </c:if>" alt="">
	        </div>
	        <div class="right_box">
	            <h3><strong>课程名称：</strong>${courseMap.course_name}</h3>
	            <ul class="course_icon">
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon4.png" alt="下载"></a></li>
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon3.png" alt="信息"></a></li>
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon2.png" alt="收藏"></a></li>
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon1.png" alt="分享"></a></li>
	            </ul>
	            <div class="number_people">
	                <span class="user_icon"><img src="${ctx}/resources/img/viewer/user_icon.png" alt=""></span><span>${courseMap.traineeCount}人学习</span>
	                <c:if test="${courseMap.starNum == 0}"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：暂无评分</c:if>
	                <c:if test="${courseMap.starNum == 1}"><div class="star1"></div></c:if>
	                <c:if test="${courseMap.starNum == 2}"><div class="star2"></div></c:if>
	                <c:if test="${courseMap.starNum == 3}"><div class="star3"></div></c:if>
	                <c:if test="${courseMap.starNum == 4}"><div class="star4"></div></c:if>
	                <c:if test="${courseMap.starNum == 5}"><div class="star5"></div></c:if> 
	                <span>(${courseMap.appraiseCount}人评价)</span>
	            </div>
				<div class="course_description">
                	<strong>课程描述：</strong>${courseMap.course_description}
            	</div>	
            	<div class="course_description">
            		<strong>有效起至时间：</strong><span>
			          	<c:choose>
			          		<c:when test="${not empty courseMap.start_time &&  not empty courseMap.expire_time}">
			          			<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${courseMap.expire_time}"/>
			          		</c:when>
			          		<c:when test="${empty courseMap.start_time &&  not empty courseMap.expire_time}">
			          			 _ 至 <fmt:formatDate type="date" value="${courseMap.expire_time}" pattern="yyyy-MM-dd"/>
			          		</c:when>
			          		<c:when test="${not empty courseMap.start_time && empty courseMap.expire_time}">
			          			<fmt:formatDate type="date" value="${courseMap.start_time}" pattern="yyyy-MM-dd"/> 至 _
			          		</c:when>
			          		<c:otherwise>
			          			未设置
			          		</c:otherwise>
			          	</c:choose>
			          		</span>
			           
            	</div>
                <div class="course_description">
                    <strong>学习进度：</strong>0%
                </div>
                <div class="course_description">
            		<!-- <button class="btn btn_blue" type="button">开始学习</button>
                	<button class="btn btn_blue" type="button">继续学习</button> 
                   	<button class="btn btn_blue" type="button">报名</button>-->
                </div>
	            <input type="hidden" id="hiddenGroupId" value="${courseMap.groupId}">
	            <input type="hidden" id="hiddenCourseId" value="${courseMap.id}">
	            <input type="hidden" id="hiddenHasVideo" value="${courseMap.hasVideo}">
	            <input type="hidden" id="hiddenHasFavor" value="${courseMap.hasFavor}">
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class=" mb20">
	        <ul class="pull-left tab_bar_left tab_area nav nav-tabs set_tab" role="tablist" style="background-color:#FFFFFF ">
	            <li class="active"><a href="#frontPage" data-toggle="tab" role="tab" >主页</a></li>
	            	<!-- 学员未选择禁止以下tab -->
            	<!-- <li><a href="#discussPage" data-toggle="tab">讨论区</a></li> -->
            	<li><a href="#relatedPage" data-toggle="tab" role="tab" >作业与考试</a></li>
	        </ul>
	        <div class="clearfix"></div>
	    </div>
	   <!-- <div class="schedule">
	        <div class="line_box">
	            <div class="line_a">
	                <div style="width:0%;background:#46c37b;height:28px;border-radius:4px 0 0 4px;"></div>
	            </div>
	            <button class="btn" type="button">继续学习</button>
	        </div>
	        <div class="schedule_des">目前已经完成0个章节，加油！</div>
	    </div> -->
	    <div class="discuss_box">
	        <div class="discuss_left">
	        </div>
	        <div class="discuss_right">
	            <h3 class="h3_title">公告</h3>
	            <input type="hidden" id="hiddenCourseNoticePageNo">
	            <ul class="newslist">
	            </ul>
	           <!--  <div class="text-center" id="btn_more">
	                <button class="btn btm_more" type="button">查看更多</button>
	            </div> -->
	        </div>
	        <div class="clearfix"></div>
	    </div>
	</div>
	
	<script id="courser_viewer_front" type="text/x-dot-template">
		<h3 class="h3_title">目录</h3>
		
	    <ul class="contents_list">
			{{? it.data.length > 0}}
			{{~it.data:v:index}}
	        	<li>
	            	<span class="lession text-right">课时{{= index+1 }}</span>
	            	<span class="round_b" style="background:#fff;">
							<em class="round_con" style=""></em>
					</span>
	            	<h4 class="h4_title">
							<em class="green">
								{{? v.item_type == '0'}}
									【章节】
								{{?? v.item_type == '1'}}
									【测验】
								{{?? v.item_type == '2'}}
									 【问卷调查】
								{{??}}
									 【论文必学】
								{{?}}
							</em>{{=isNullFormat(v.item_name)}}
						<!-- </a> -->
					</h4>
	            	<div class="line"></div>
					<input type="hidden" name="itemId" value="{{=isNullFormat(v.item_id)}}">
	        	</li>
	        {{~}}
			{{??}}
				<div class="null_table" style="border:0px;">NULL<span class="null_t">暂时没有相关数据</span></div>
			{{?}}
	    </ul>
	</script>
	
	<script id = "courser_viewer_discuss" type="text/x-dot-template">
		<div class="discuss_top">
		    <h3 class="title">讨论区</h3>
		    <p>欢迎进入课程讨论区，你可以与本课程的老师和同学在这里交流。如果你有课程相关的问题，请发到老师答疑区；经验、思考、创意、作品、转帖请发到综合讨论区。欢迎分享，鼓励原创，杜绝广告，请大家共同维护一个包容、积极、相互支持的交流氛围，谢谢。了解更多请点击“讨论区使用规则”↗</p>
		</div>
		{{~it.data:v:index}}
			<article class="discuss_b">
		    	<div class="dis_top">
					{{? isNullFormat(v.userIcon) == ''}}
						<img src="${ctx}/resources/img/viewer/user_pho.png" alt="" class="user_pho">
					{{??}}
						<img src="${imgStatic}{{=v.attachUrl}}{{=v.userIcon}}" alt="" class="user_pho">
					{{?}}
		       	 	<span class="ml20">{{=isNullFormat(v.nickName)}}</span>
		        	<time class="ml20">{{=dateFormat1(v.createTime)}}</time>
		        	<ul class="right_icon pull-right">
		            	<li><a><img src="${ctx}/resources/img/viewer/right_icon1.png" alt=""></a>({{=v.scanNum}})</li>
		            	<li><a><img src="${ctx}/resources/img/viewer/right_icon2.png" alt=""></a><span>({{=v.replyNum}})</span></li>
		            	<li><a><img src="${ctx}/resources/img/viewer/right_icon3.png" alt=""></a><span>({{=v.agreeNum}})</span></li>
		        	</ul>
		        	<div class="clearfix"></div>
		    	</div>
		    	<div class="dis_con">{{=isNullFormat(v.discussionText)}}</div>
				
				{{? v.replys.length != 0 }}
					{{~v.replys:vitem:vindex}}
						<div class="replaybox" {{? vindex > 2}} hidden {{?}}>
                    		<div class="replaybox_top">
                        		{{? isNullFormat(vitem.userIcon) == ''}}
									<img src="${ctx}/resources/img/viewer/user_pho.png" alt="" class="user_pho">
								{{??}}
									<img src="${imgStatic}{{=vitem.attachUrl}}{{=vitem.userIcon}}" alt="" class="user_pho">
								{{?}}
                        		<span class="ml10">{{=isNullFormat(vitem.nickName)}}</span>
                        		<time class="ml10">{{=dateFormat1(vitem.createTime)}}</time>
                    		</div>
                    		<div class="replay_con">{{=isNullFormat(vitem.discussionText)}}</div>
                		</div>
					{{~}}
					{{? v.replys.length >= 2}}
						<div class="text-center"><button class="btn btm_more" type="button">查看更多</button></div>
					{{?}}
	            </div>
				{{?}}
			</article>
		{{~}}
			<a href="#" class="a-anchor" name="replyAnchor"></a>
			<section class="replay">
                <img src="${userHeader}" alt="" class="user_pho">
                <input type="text" class="input_box ml10" id="courseDiscussionText">
                <button class="btn pull-right" type="button" >发起讨论</button>
           	</section>
	</script>
	
		<script id = "courser_viewer_newslist_data_template" type="text/x-dot-template">
		{{~it.data.dataList:v:index}}
			<li class="newslistItem">
				<div name="noticeTxt">
					{{=showPartNoticeTxt(v.notice_content)}}
				</div>
				<input name="hiddenFullNoticeTxt" value="{{=isNullFormat(v.notice_content)}}" type="hidden">
	        	<div class="time_b">
	        		<time class="pull-left">{{=dateFormat1(v.notice_publish_time)}}</time>
					{{? isShowPartNoticeTxt(v.notice_content) }}
						<a href="javascript:void(0)" name="showFullTxtBtn" onclick="javascript:onShowNoticeTxt(this,1)" class="pull-right">详细&gt;&gt;</a>
						<a href="javascript:void(0)" name="showPartTxtBtn" hidden onclick="javascript:onShowNoticeTxt(this,0)" class="pull-right">收起&gt;&gt;</a>
					{{?}}
	        	</div>
	    	</li>
		{{~}}
	 </script>
	
	<script id = "courser_viewer_related" type="text/x-dot-template">
		<h3 class="h3_title">目录</h3>
	    <ul class="contents_list">
			{{? it.data.length > 0}}
			{{~it.data:v:index}}
	        	<li>
					<span class="lession text-right">内容{{= index+1 }}</span>
	            	<span class="round_b" style="background:#fff;">
							<em class="round_con" style=""></em>
					</span>
	            	<h4 class="h4_title">
						<em class="green">
							{{? v.item_type == '1'}}
								【课后作业】
							{{?? v.item_type == '2'}}
								【毕业考试】
							{{?? v.item_type == '3'}}
								【问卷调查】
							{{??}}
								【附		件】
							{{?}}
						</em>{{=isNullFormat(v.item_name)}}
					</h4>
					<time class="pull-right">
						{{? '' != isNullFormat(v.item_finish_time)}}
							截止日期：{{=dateFormat(v.item_finish_time)}}
						{{?}}
					</time>
							
	            	<div class="line"></div>
					<input type="hidden" name="itemId" value="{{=isNullFormat(v.item_id)}}">
	        	</li>
	        {{~}}
			{{??}}
				<div class="null_table" style="border:0px;">NULL<span class="null_t">暂时没有相关数据</span></div>
			{{?}}
	    </ul>
	</script>
	
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	<%-- <%@ include file="/WEB-INF/views/modules/courseselect/course_remark_tem.jsp"%> --%>
	<script type="text/javascript">
		require([ 'modules/course/course_online_preview' ], function(obj) {
		}); 
	</script>
</body>
</html>