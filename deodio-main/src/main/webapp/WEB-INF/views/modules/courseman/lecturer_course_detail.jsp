<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/courseman/mycourses.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
    <div class="container">
        <div class="p_border p40">
            <div>
                <div class="video">
                    <img src="${ctx}/resources/img/courseman/title_pic.png" alt="">
                </div>
                <div class="video_r">
                    <h3 class="title pb10">Course Name文字内容</h3>
                    <div class="mt10">
                        <ul class="course_des">
                            <li>评分：
                                <div  
                                    <c:choose>
                                        <c:when test="${courseMap.course_star==2}">class="star2"</c:when>
                                        <c:when test="${courseMap.course_star==3}">class="star3"</c:when>
                                        <c:when test="${courseMap.course_star==4}">class="star4"</c:when>
                                        <c:when test="${courseMap.course_star==5}">class="star5"</c:when>
                                        <c:otherwise>class="star1"</c:otherwise>
                                    </c:choose> >
                                </div>
                            </li>
                            <li>类型：<span>集中培训</span></li>
                            <li>课程类型：<span>线上线下同步/只线下</span></li>
                            <li>培训课时：<span>300 小时</span></li>
                            <li>培训起至时间：<span>2015-08-03 至 2016-08-02</span></li>
                            <li>拟定人数：<span>12</span></li>
                            <li>学员总数：<span>6</span></li>
                            <li>创建时间：<span>2016-01-04</span></li>
                            <li>创建人：<span>ISaac</span></li>
                            <li class="wb100">所属分类：<em>分类文字</em><em>分类文字</em><em>分类文字</em><em>分类文字</em><em>分类文字</em><em>分类文字</em><em>分类文字</em><em>分类文字</em><em>分类文字</em></li>
                        </ul>
                        <div class="text-right">
                            <button type="submit" class="btn_b mt20 mr20">管理课程</button>
                            <button type="submit" class="btn_b mt20">发布/暂停/草稿</button>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <ul class="pre1_tab mt20 pl20" id="myTab">
                <li class="active"><a href="#jieshao1">课程介绍</a></li>
                <li><a href="#dagang1">课程大纲</a></li>
                <li><a href="#pingjia">课程评价</a></li>
            </ul>
        
            <!-- Presentation 介绍 -->
        
            <div id="myTabContent" class="tab-content">
                <div class="pre1_con tab-pane active" id="jieshao1">
                    <h3 class="kc_title"><span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程简介</h3>
                    <div class="kc_des">
                        SCORM 共享内容对象参考模型（Sharable Content Object Reference Model）是由美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息请访问SCORM官方网站 ：www.adl.net.gov
                    </div>
                    <h3 class="kc_title mt20"><span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程内容</h3>
                    <div class="kc_des">
                        SCORM 共享内容对象参考模型（Sharable Content Object Reference Model）是由美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息请访问SCORM官方网站 ：www.adl.net.gov
                    </div>
                    <h3 class="kc_title mt20"><span class="glyphicon glyphicon-info-sign ml20 mr10"></span>课程须知</h3>
                    <div class="kc_des">
                        SCORM 共享内容对象参考模型（Sharable Content Object Reference Model）是由美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息请访问SCORM官方网站 ：www.adl.net.gov
                    </div>
                </div>
                <div class="pre1_con tab-pane" id="dagang1">
                    <div class="discuss_box">
                        <div class="discuss_left">
                            <h3 class="h3_title" style="border-top:1px solid #ddd;">目录</h3>
                            <ul class="contents_list">
                                <li>
                                    <span class="lession text-right">课时1</span>
                                    <span class="round_b" style="background:#fff;"><em class="round_con"></em></span>
                                    <h4 class="h4_title"><a href=""><em class="green">【课程】</em>第一章内容标题</a></h4>
                                    <time class="pull-right">02:36</time>
                                    <div class="line"></div>
                                </li>
                                <li>
                                    <span class="lession text-right">课时2</span>
                                    <span class="round_b" style="background:#fff;"><em class="round_con"></em></span>
                                    <h4 class="h4_title"><a href=""><em class="red">【考试】</em>快速入门</a></h4>
                                    <time class="pull-right">02:36</time>
                                    <div class="line"></div>
                                </li>
                                <li>
                                    <span class="lession text-right">课时3</span>
                                    <span class="round_b" style="background:#fff;"><em class="round_con"></em></span>
                                    <h4 class="h4_title"><a href=""><em class="blue">【测试】</em>页面布局</a></h4>
                                    <time class="pull-right">02:36</time>
                                    <span class="pull-right icon"><img src="${ctx}/resources/img/courseman/icon3.png" alt=""></span>
                                    <div class="line"></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="pre1_con tab-pane" id="pingjia">
                    <ul class="kcpj_list">
                        <li>
                            <div class="kcpj_list_pic"><img src="${ctx}/resources/img/courseman/kcgl_pic.png" alt=""></div>
                            <div class="kcpj_list_right">
                                <div class="row">
                                    <div class="col-md-12"><span class="l_title">学生名称：</span>用户名Jone</div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-12">
                                        <div class="conflex">
                                            <span class="l_title">评&emsp;&emsp;价：</span>
                                            <div>
                                                    美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="kcpj_list_pic"><img src="${ctx}/resources/img/courseman/kcgl_pic.png" alt=""></div>
                            <div class="kcpj_list_right">
                                <div class="row">
                                    <div class="col-md-12"><span class="l_title">学生名称：</span>用户名Jone</div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-12">
                                        <div class="conflex">
                                            <span class="l_title">评&emsp;&emsp;价：</span>
                                            <div>
                                                    美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="kcpj_list_pic"><img src="${ctx}/resources/img/courseman/kcgl_pic.png" alt=""></div>
                            <div class="kcpj_list_right">
                                <div class="row">
                                    <div class="col-md-12"><span class="l_title">学生名称：</span>用户名Jone</div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                    <div class="col-md-4"><span class="l_title">老师评分：</span><div class="star3"></div></div>
                                </div>
                                <div class="row mt10">
                                    <div class="col-md-12">
                                        <div class="conflex">
                                            <span class="l_title">评&emsp;&emsp;价：</span>
                                            <div>
                                                    美国国防部ADL（Advanced Distributed Learning）组织所拟定的标准，对于数字内容教材的制作、内容开发提供一套共通的规范。SCORM可透过统一的格式跨平台使用课件， 可真正达到重复使用教材、统一方式追踪学习记录，更能符合学习者的需要。SCORM是事实上的国标通用电子课件制作标准。 更多信息
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
         </div>
     </div>
          
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
    <script>
      $(function () {
        $('#myTab a:first').tab('show');//初始化显示哪个tab
        $('#myTab a').click(function (e) {
          e.preventDefault();//阻止a链接的跳转行为
          $(this).tab('show');//显示当前选中的链接及关联的content
        })
      })
    </script>
</body>
</html>

