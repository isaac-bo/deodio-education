<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/quizs/quizs.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	
	<div class="content p_border p40">
	    <div class="course_topbox">
	        <div class="left_pic">
	        	<%-- <img src="${ctx}/resources/img/viewer/course_pic.jpg" alt=""> --%>
	        	<img src="<c:if test="${empty courseMap.course_cover_img}">${ctx}/resources/img/viewer/course_pic.jpg</c:if><c:if test="${not empty courseMap.course_cover_img}"> ${imgStatic}${courseMap.attach_url}/${courseMap.course_cover_img} </c:if>" alt="">
	        </div>
	        
	        <div class="right_box">
	            <h3><span>课程名称：</span>${courseMap.course_name}</h3>
	            <div class="course_description" id="favorContainer">
		             <c:if test="${courseMap.hasFavor == 'true'}">
		                	已关注
		            </c:if>
	            </div>	
	            <ul class="course_icon">
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon4.png" alt=""></a></li>
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon3.png" alt=""></a></li>
	                <li><a href="javascript:void(0);" onclick="javascript:toggleCourseFavor()"><img src="${ctx}/resources/img/viewer/course_icon2.png" alt="收藏"></a></li>
	                <li><a href=""><img src="${ctx}/resources/img/viewer/course_icon1.png" alt=""></a></li>
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
            	  <div class="course_description pull-left" style="width:50%;">
            	  <strong>课程状态：</strong>${courseMap.courseValid}
                   <!--    <strong>课程状态：</strong>未开始、进行中、已过期 -->
                  </div>
                  
                  <div class="course_description pull-left" style="width:50%;">
                  		<c:if test="${courseMap.hasSelect == 'true'}">
                  			<button class="btn btn_blue" type="button">开始学习</button>
                      		<button class="btn btn_blue" type="button">继续学习</button>
                  		</c:if>
                  		<c:if test="${courseMap.hasSelect == 'false' }">
                      		<button class="btn btn_blue" type="button" onclick="enrollCourse();">我要报名</button>
                      	</c:if>
                  </div>
                  <div class="course_description">
                      <strong>学习进度：</strong>30%
                  </div>
                  <div class="course_description" >
	                	<strong>浏览（9）    报名（5）    关注（6）</strong>
	            	</div>
	            <input type="hidden" id="hiddenGroupId" value="${courseMap.groupId}">
	            <input type="hidden" id="hiddenCourseId" value="${courseMap.id}">
	            <input type="hidden" id="hiddenHasVideo" value="${courseMap.hasVideo}">
	            <input type="hidden" id="hiddenHasFavor" value="${courseMap.hasFavor}">
	            <input type="hidden" id="hiddenCourseType" value="${courseMap.course_type}">
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="tab_bar mt20">
	        <ul class="pull-left tab_bar_left tab_area">
	            <li class="active"><a href="#frontPage" data-toggle="tab">主页</a></li>
	            	<!-- 学员未选择禁止以下tab -->
	            <c:if test="${courseMap.hasSelect == 'true'}">
	            	<li><a href="#discussPage" data-toggle="tab">讨论区</a></li>
	            	<li><a href="#relatedPage" data-toggle="tab">作业与考试</a></li>
	            </c:if>
	        </ul>
	        <div class="clearfix"></div>
	    </div>
	    <div class="discuss_box">
	        <div class="discuss_left">
	        </div>
	        <div class="discuss_right">
	            <h3 class="h3_title">公告</h3>
	            <input type="hidden" id="hiddenCourseNoticePageNo">
	            <ul class="newslist">
	            </ul>
	            <div class="text-center" id="btn_more">
	                <button class="btn btm_more" type="button">查看更多</button>
	            </div>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    
             <!-- <div class="kc_hot_title">
               <span>必修课程</span>
            </div>
            <div class="container-fluit mt20">
                <div id="necessaryCourse" class="row">
                    
                </div>
            </div>
            <div class="kc_hot_title">
                <span>推荐课程</span>
            </div>
            <div class="container-fluit mt20">
                <div id="recommandCourse" class="row">
                    
                </div>
            </div> -->
	    
	</div>
	
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	
	<%@ include file="/WEB-INF/views/modules/courseselect/course_remark_tem.jsp"%>
	
	<script id = "courser_viewer_front" type="text/x-dot-template">
		<h3 class="h3_title">目录</h3>
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
						<a {{? isNullFormat(v.item_id) != '' && isNullFormat(v.item_id) != '' && courseHasVideo()}}
							href="${ctx}/classroom.html?itemId={{=isNullFormat(v.item_id)}}&itemIndex={{=index+1}}&courseId=${courseMap.id}&itemType={{=v.item_type}}&groupId=${courseMap.groupId}"
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
	            	<time class="pull-right">02:36</time>
	            	<span class="pull-right icon">
						{{? v.item_type == '0'}}
							<img src="${ctx}/resources/img/viewer/icon1.png" alt="">
						{{?? v.item_type == '1'}}
							<img src="${ctx}/resources/img/viewer/icon2.png" alt="">
						{{??}}
							<img src="${ctx}/resources/img/viewer/icon3.png" alt="">
						{{?}}
					</span>
	            	{{? isNullFormat(v.item_info) != '' }}
						{{? v.item_type == '0' }}
							<span class="pull-right green mr10">已学完{{=isNullFormat(v.item_info)}}%</span>
						{{?? v.item_type == '1'}}
							<span class="pull-right red mr10">考试得分:{{=isNullFormat(v.item_info)}}</span>
						{{?? v.item_type == '2'}}
							<span class="pull-right blue mr10">测验结果:{{= v.item_info=='1'?'通过':'未通过' }}</span>
						{{?}}
					{{?? courseHasVideo()}}
                        {{? v.item_type == '0'}}
						<button class="btn pull-right" onclick="javascript:openPreview('{{=isNullFormat(v.item_id)}}','{{=v.item_type_detail}}')">开始学习</button>
					    {{?? v.item_type == '1'}}
                        <button class="btn pull-right"  data-toggle="modal" onclick="onViewExamination('{{=isNullFormat(v.item_id)}}');">开始学习测验</button>
                        {{?? v.item_type == '2'}}
                        <button class="btn pull-right"  data-toggle="modal" onclick="onPreviewSurvey('{{=isNullFormat(v.item_id)}}');">开始学习问卷调查等</button>  
                       {{?}}
                       {{?}}
	            	<div class="line"></div>
					<input type="hidden" name="_item_id" id="_item_id" value="{{=isNullFormat(v.item_id)}}">
	        	</li>
	        {{~}}
				<li>
	            	<span class="lession text-right">评价</span>
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
							<em class="green">
								【课程评价】
							</em>${courseMap.course_name}
					</h4>
	            	<time class="pull-right">02:36</time>
	            	<span class="pull-right icon">
						{{? v.item_type == '0'}}
							<img src="${ctx}/resources/img/viewer/icon1.png" alt="">
						{{?? v.item_type == '1'}}
							<img src="${ctx}/resources/img/viewer/icon2.png" alt="">
						{{??}}
							<img src="${ctx}/resources/img/viewer/icon3.png" alt="">
						{{?}}
					</span>
	            	
					
					{{? isNullFormat(${courseMap.starNum}) != ''}}
						<span class="pull-right green mr10">已评价</span>
					{{??}}
<button class="btn pull-right" data-toggle="modal" data-target="#myModal">开始评价</button>
						<span class="pull-right red mr10">未评价</span>	
					{{?}}
					
	            	<div class="line"></div>
					<input type="hidden" name="itemId" value="{{=isNullFormat(v.item_id)}}">
	        	</li>
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
		            	<li><a href="javascript:void(0)" onclick="javascript:void"><img src="${ctx}/resources/img/viewer/right_icon1.png" alt=""></a>({{=v.scanNum}})</li>
		            	<li><a href="javascript:void(0)" onclick="javascript:onReplyCourseDiscussion('{{=isNullFormat(v.id)}}',this)"><img src="${ctx}/resources/img/viewer/right_icon2.png" alt=""></a><span>({{=v.replyNum}})</span></li>
		            	<li><a href="javascript:void(0)" onclick="javascript:onAgreeCourseDiscussion('{{=isNullFormat(v.id)}}',this)" ><img src="${ctx}/resources/img/viewer/right_icon3.png" alt=""></a><span>({{=v.agreeNum}})</span></li>
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
						<div class="text-center"><button class="btn btm_more" type="button" onclick="javascript:onShowAllReplysInDiscussion(this,'{{=isNullFormat(v.id)}}')">查看更多</button></div>
					{{?}}
	            </div>
				{{?}}
			</article>
		{{~}}

			<a href="#" class="a-anchor" name="replyAnchor"></a>
			<section class="replay">
                <img src="${userHeader}" alt="" class="user_pho">
                <input type="text" class="input_box ml10" id="courseDiscussionText">
                <button class="btn pull-right" type="button" onclick="courseTraineeReply();">发起讨论</button>
           	</section>
		
	</script>
	
	<script id = "courser_viewer_related" type="text/x-dot-template">
		<h3 class="h3_title">目录</h3>
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
							<em class="green">
								{{? v.item_type == '60'}}
									【课后作业】
								{{?? v.item_type == '61'}}
									【毕业考试】
								{{?? v.item_type == '62'}}
									 【问卷调查】
								{{??}}
									 【附		件】
								{{?}}
							</em>{{=isNullFormat(v.name)}}
					</h4>
	            	
					<time class="pull-right">
						{{? '' != isNullFormat(v.finish_time)}}
							截止日期：{{=dateFormat(v.finish_time)}}
						{{?}}
					</time>
	            	
					{{? isNullFormat(v.item_type) == '60' }}
						<button class="btn pull-right"  onclick="onViewHomework('{{=isNullFormat(v.item_id)}}');">写作业</button>
						{{?? isNullFormat(v.item_type) == '61' }}
						<button class="btn pull-right"  onclick="onViewExamination('{{=isNullFormat(v.item_id)}}');">开始考试</button>
						{{?? isNullFormat(v.item_type) == '62' }}
						<button class="btn pull-right" onclick="onPreviewSurvey('{{=isNullFormat(v.item_id)}}');">参与调查</button>
						{{??}}
						<a href="${imgStatic}{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" download="{{=isNullFormat(v.generate_name)}}"><button class="btn pull-right" >下载附件</button></a>
						{{?}}
					

					{{? isNullFormat(v.type) != '63' }}
						{{? v.status == '1' }}
							<span class="pull-right red mr10">未完成</span>
						{{?? v.status == '2'}}
							<span class="pull-right  blue mr10">已提交</span>
						{{?? v.status == '3'}}
							<span class="pull-right green mr10">已完成</span>
						{{?}}
					{{?}}					

	            	<div class="line"></div>
					<input type="hidden" name="_item_id" id="_item_id" value="{{=isNullFormat(v.item_id)}}">
	        	</li>
	        {{~}}
	    </ul>


	</script>
	
	<script id = "courser_viewer_discuss_reply_data_template" type="text/x-dot-template">
		{{~it.data:vitem:vindex}}
			<div class="replaybox">
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
	 
	<script id="course_viewer_course_relate_to_list_data_template" type="text/x-dot-template">
		{{~it.data:v:index}}
		<div class="col-md-3 col-xs-6 col-sm-6">
        	<div class="kc_box">
                  <a href="${ctx}/course/course_viewer/detail.html?courseId={{=v.id}}&groupId=${courseMap.groupId}">
						{{? !v.course_cover_img}}
							<img src="${ctx}/resources/img/course/p_pic.jpg" alt="">
						{{??}}
							<img src="${imgStatic}{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.course_cover_img)}}"   alt="">		
						{{?}}
				  </a>
                  <ul class="kc_box_item">
                       <li>
                          <span class="left_title">课程名称：</span>{{=isNullFormat(v.course_name)}}
                       </li>
                       <li>
                          <span class="left_title">讲师：</span>{{=isNullFormat(v.nick_name)}}
                       </li>
                       <li>6 人学习 &nbsp; | &nbsp; 26? 人预览</li>
                   </ul>
             	  <div class="kc_box_btn text-right">
                  	<a href="${ctx}/course/course_viewer/detail.html?courseId={{=v.id}}&groupId=${courseMap.groupId}"><button type="button" class="btn btn_green">OPEN</button></a>
                 	<button type="button" class="btn btn_blue">简介</button>
             	  </div>
             </div>
          </div>
		{{~}}
	</script>
	<%@ include file="/WEB-INF/views/modules/trainee/trainee_learn_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/modules/trainee/trainee_survey_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/modules/trainee/trainee_homework_dialogue.jsp"%>
	<script type="text/javascript">
		require([ 'modules/course/course_viewer_online_detail' ], function(obj) {
		}); 
	</script>
</body>
</html>