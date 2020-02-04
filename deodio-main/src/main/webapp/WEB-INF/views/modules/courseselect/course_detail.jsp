<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/courseselect/mycourses.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
    <div class="container">
        <div class="p_border p40">
            <div class="row course_topbox">
                <div class="col-md-5 col-xs-5">
                    <div class="left_pic"><img src="${ctx}/resources/img/courseselect/course_pic.jpg" alt=""></div>
                </div>
                <div class="col-md-7 col-xs-7 right_box">
                    <div class="row">
                        <div class="col-md-8"><h3><span>课程名称：</span>C语言/C++学习指南（零基础入门）</h3></div>
                        <div class="col-md-4">
                            <ul class="course_icon text-right">
                                <li><a href=""><img src="${ctx}/resources/img/courseselect/course_icon4.png" alt=""></a></li>
                                <li><a href=""><img src="${ctx}/resources/img/courseselect/course_icon3.png" alt=""></a></li>
                                <li><a href=""><img src="${ctx}/resources/img/courseselect/course_icon2.png" alt=""></a></li>
                                <li><a href=""><img src="${ctx}/resources/img/courseselect/course_icon1.png" alt=""></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="number_people">
                        <span class="user_icon"><img src="${ctx}/resources/img/courseselect/user_icon.png" alt=""></span>400
                        <div class="star4"></div>
                        <span>(5人评价)</span>
                    </div>
                    <div class="course_description">
                        <strong>课程描述：</strong>不喊口号、不打鸡血、不讲概念、不谈特点，直接收获平台自我诊断、搜索引擎营销、百度360搜狗......
                    </div>
                    <div class="course_description">
                        <strong>总时长约：</strong>2:10
                    </div>
                    <div class="course_description">
                        <strong>学习进度：</strong>30%
                    </div>
                    <div class="course_description">
                        <button class="btn btn_blue" type="button">报名</button>
                        <button class="btn btn_blue" type="button">开始学习</button>
                        <button class="btn btn_blue" type="button">继续学习</button>
                    </div>
                </div>
            </div>
            <div class="tab_bar mt20">
                <ul class="tab_bar_left" id="myTab">
                    <li class="active"><a href="#tab01" data-toggle="tab">主页</a></li>
                    <li><a href="#tab02" data-toggle="tab">讨论区</a></li>
                    <li><a href="#tab03" data-toggle="tab">作业与考试</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="tab01">
                        <div class="schedule">
                            <div class="line_box">
                                <div class="line_a">
                                    <div style="width:70%;background:#46c37b;height:28px;border-radius:4px 0 0 4px;"></div>
                                </div>
                                <button class="btn" type="button">继续学习</button>
                            </div>
                            <div class="schedule_des">目前已经完成1个章节，加油！</div>
                        </div>
                        <div class="row discuss_box">
                            <div class="col-md-8">
                                <div class="discuss_left">
                                    <h3 class="h3_title">目录</h3>
                                    <ul class="contents_list">
                                        <li>
                                            <span class="lession text-right">课时1</span>
                                            <span class="round_b" style="background:#fff;"><em class="round_con" style=""></em></span>
                                            <h4 class="h4_title"><a href=""><em class="green">【课程】</em>第一章内容标题</a></h4>
                                            <time class="pull-right">02:36</time>
                                            <span class="pull-right icon"><img src="${ctx}/resources/img/courseselect/icon1.png" alt=""></span>
                                            <span class="pull-right green mr10">已学完50%</span>
                                            <div class="line"></div>
                                        </li>
                                        <li>
                                            <span class="lession text-right">课时2</span>
                                            <span class="round_b" style="background:#fff;"><em class="round_con" style="width:4px;border-radius:50% 0 0 50%;"></em></span>
                                            <h4 class="h4_title"><a href=""><em class="red">【考试】</em>快速入门</a></h4>
                                            <time class="pull-right">02:36</time>
                                            <span class="pull-right icon"><img src="${ctx}/resources/img/courseselect/icon2.png" alt=""></span>
                                            <span class="pull-right red mr10">考试得分：87</span>
                                            <div class="line"></div>
                                        </li>
                                        <li>
                                            <span class="lession text-right">课时3</span>
                                            <span class="round_b" style="background:#fff;border-color:#ddd;"></span>
                                            <h4 class="h4_title"><a href=""><em class="blue">【测试】</em>页面布局</a></h4>
                                            <time class="pull-right">02:36</time>
                                            <span class="pull-right icon"><img src="${ctx}/resources/img/courseselect/icon3.png" alt=""></span>
                                            <span class="pull-right blue mr10">测验结果</span>
                                            <div class="line"></div>
                        
                                        </li>
                                        <li>
                                            <span class="lession text-right">课时4</span>
                                            <span class="round_b" style="background:#ddd;border-color:#ddd;"><img src="${ctx}/resources/img/courseselect/lock.png" alt=""></span>
                                            <h4 class="h4_title"><a href="">论文必学：样式</a></h4>
                                            <time class="pull-right">02:36</time>
                                            <span class="pull-right icon"><img src="${ctx}/resources/img/courseselect/icon3.png" alt=""></span>
                                            <button class="btn pull-right">开始学习</button>
                                            <div class="line"></div>
                                        </li>
                                        <li>
                                            <span class="lession text-right">评价</span>
                                            <span class="round_b" style="background:#ddd;border-color:#ddd;"><img src="${ctx}/resources/img/courseselect/lock.png" alt=""></span>
                                            <h4 class="h4_title"><a href="">【讲师评价】主讲教师名称</a></h4>
                                            <time class="pull-right">02:36</time>
                                            <button class="btn pull-right" data-toggle="modal" data-target="#myModal">开始评价</button>
                                            <div class="line"></div>
                                        </li>
                                        <li>
                                            <span class="lession text-right">评价</span>
                                            <span class="round_b" style="background:#ddd;border-color:#ddd;"><img src="${ctx}/resources/img/courseselect/lock.png" alt=""></span>
                                            <h4 class="h4_title"><a href="">【课程评价】如何插入公式等特殊符号</a></h4>
                                            <time class="pull-right">02:36</time>
                                            已评价
                                            <div class="line"></div>
                                        </li>
                                    </ul>
                                </div>          
                            </div>
                            <div class="col-md-4">
                                <div class="discuss_right">
                                    <h3 class="h3_title">公告</h3>
                                    <ul class="newslist">
                                        <li>
                                            老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                            <div class="time_b">
                                                <time class="pull-left">2017-05-12</time>
                                                <a href="" class="pull-right">详细&gt;&gt;</a>
                                            </div>
                                        </li>
                                        <li>
                                            老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                            <div class="time_b">
                                                <time class="pull-left">2017-05-12</time>
                                                <a href="" class="pull-right">详细&gt;&gt;</a>
                                            </div>
                                        </li>
                                        <li>
                                            老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                            <div class="time_b">
                                                <time class="pull-left">2017-05-12</time>
                                                <a href="" class="pull-right">详细&gt;&gt;</a>
                                            </div>
                                        </li>
                                    </ul>
                                    <div class="text-center">
                                        <button class="btn btm_more" type="button">查看更多</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="tab02">
                            <div class="schedule">
                                <div class="line_box">
                                    <div class="line_a">
                                        <div style="width:70%;background:#46c37b;height:28px;border-radius:4px 0 0 4px;"></div>
                                    </div>
                                    <button class="btn" type="button">继续学习</button>
                                </div>
                                <div class="schedule_des">目前已经完成1个章节，加油！</div>
                            </div>
                            <div class="row discuss_box">
                                <div class="col-md-8">
                                    <div class="discuss_left">
                                        <div class="discuss_top">
                                            <h3 class="title">讨论区</h3>
                                            <p>欢迎进入课程讨论区，你可以与本课程的老师和同学在这里交流。如果你有课程相关的问题，请发到老师答疑区；经验、思考、创意、作品、转帖请发到综合讨论区。欢迎分享，鼓励原创，杜绝广告，请大家共同维护一个包容、积极、相互支持的交流氛围，谢谢。了解更多请点击“讨论区使用规则”↗</p>
                                        </div>
                                        <article class="discuss_b">
                                            <div class="dis_top">
                                                <img src="${ctx}/resources/img/courseselect/user_pho.png" alt="" class="user_pho">
                                                <span class="ml20">小小甜甜酸</span>
                                                <time class="ml20">2017-05-12</time>
                                                <ul class="right_icon pull-right">
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon1.png" alt="">(12)</li>
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon2.png" alt="">(3)</li>
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon3.png" alt="">(31)</li>
                                                </ul>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="dis_con">老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细</div>
                                        </article>
                                        <article class="discuss_b">
                                            <div class="dis_top">
                                                <img src="${ctx}/resources/img/courseselect/user_pho.png" alt="" class="user_pho">
                                                <span class="ml20">小小甜甜酸</span>
                                                <time class="ml20">2017-05-12</time>
                                                <ul class="right_icon pull-right">
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon1.png" alt="">(12)</li>
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon2.png" alt="">(3)</li>
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon3.png" alt="">(31)</li>
                                                </ul>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="dis_con">老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细</div>
                                        </article>
                                        <section class="replay">
                                            <img src="${ctx}/resources/img/courseselect/user_pho.png" alt="" class="user_pho">
                                            <input type="text" class="input_box ml10">
                                            <button class="btn pull-right" type="button">发起讨论</button>
                                        </section>
                                        <article class="discuss_b">
                                            <div class="dis_top">
                                                <img src="${ctx}/resources/img/courseselect/user_pho.png" alt="" class="user_pho">
                                                <span class="ml20">小小甜甜酸</span>
                                                <time class="ml20">2017-05-12</time>
                                                <ul class="right_icon pull-right">
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon1.png" alt="">(12)</li>
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon2.png" alt="">(3)</li>
                                                    <li><img src="${ctx}/resources/img/courseselect/right_icon3.png" alt="">(31)</li>
                                                </ul>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="dis_con">老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细</div>
                                            <div class="replaybox">
                                                <div class="replaybox_top">
                                                    <img src="${ctx}/resources/img/courseselect/user_pho.png" alt="" class="user_pho">
                                                    <span class="ml10">优雅的微笑</span>
                                                    <time class="ml10">2017-05-12</time>
                                                </div>
                                                <div class="replay_con">
                                                    清晰度不行，音质也不好，还有好大黑框... 云课堂logo也被遮住了一半....
                                                </div>
                                            </div>
                                        </article>
                                    </div>    
                                </div>
                                <div class="col-md-4">
                                    <div class="discuss_right">
                                        <h3 class="h3_title">公告</h3>
                                        <ul class="newslist">
                                            <li>
                                                老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                                <div class="time_b">
                                                    <time class="pull-left">2017-05-12</time>
                                                    <a href="" class="pull-right">详细&gt;&gt;</a>
                                                </div>
                                            </li>
                                            <li>
                                                老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                                <div class="time_b">
                                                    <time class="pull-left">2017-05-12</time>
                                                    <a href="" class="pull-right">详细&gt;&gt;</a>
                                                </div>
                                            </li>
                                            <li>
                                                老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                                <div class="time_b">
                                                    <time class="pull-left">2017-05-12</time>
                                                    <a href="" class="pull-right">详细&gt;&gt;</a>
                                                </div>
                                            </li>
                                        </ul>
                                        <div class="text-center">
                                            <button class="btn btm_more" type="button">查看更多</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div class="tab-pane fade" id="tab03">
                        <div class="schedule">
                            <div class="line_box">
                                <div class="line_a">
                                    <div style="width:70%;background:#46c37b;height:28px;border-radius:4px 0 0 4px;"></div>
                                </div>
                                <button class="btn" type="button">继续学习</button>
                            </div>
                            <div class="schedule_des">目前已经完成1个章节，加油！</div>
                        </div>
                        <div class="row discuss_box">
                            <div class="col-md-8">
                                <div class="discuss_left">
                                    <h3 class="h3_title">目录</h3>
                                    <ul class="contents_list">
                                        <li>
                                            <span class="lession text-right">课时1</span>
                                            <span class="round_b" style="background:#fff;"><em class="round_con" style=""></em></span>
                                            <h4 class="h4_title"><a href=""><em class="green">【课后作业】</em>第一章内容标题</a></h4>
                                            <time class="pull-right">截止日期：2018-03-08</time>
                                            <button class="btn pull-right">写作业</button>
                                            <div class="line"></div>
                                        </li>
                                        <li>
                                            <span class="lession text-right">课时2</span>
                                            <span class="round_b" style="background:#fff;"><em class="round_con" style="width:4px;border-radius:50% 0 0 50%;"></em></span>
                                            <h4 class="h4_title"><a href=""><em class="red" style="color:#0068b7;">【附件】</em>快速入门</a></h4>
                                            <time class="pull-right">截止日期：2018-03-08</time>
                                            <button class="btn pull-right" style="background:#0068b7;">下载附件</button>
                                            <div class="line"></div>
                                        </li>
                                        <li>
                                            <span class="lession text-right">课时3</span>
                                            <span class="round_b" style="background:#fff;border-color:#ddd;"></span>
                                            <h4 class="h4_title"><a href=""><em class="blue">【调查问卷】</em>页面布局</a></h4>
                                            <time class="pull-right">截止日期：2018-03-08</time>
                                            <button class="btn pull-right" style="background:#71c0e9;">参与调查</button>
                                            <div class="line"></div>
                        
                                        </li>
                                        <li>
                                            <span class="lession text-right">课时4</span>
                                            <span class="round_b" style="background:#ddd;border-color:#ddd;"><img src="${ctx}/resources/img/courseselect/lock.png" alt=""></span>
                                            <h4 class="h4_title"><a href="">论文必学：样式</a></h4>
                                            <time class="pull-right">截止日期：2018-03-08</time>
                                            <button class="btn pull-right">开始考试</button>
                                            <div class="line"></div>
                                        </li>
                                    </ul>
                                </div>          
                            </div>
                            <div class="col-md-4">
                                <div class="discuss_right">
                                    <h3 class="h3_title">公告</h3>
                                    <ul class="newslist">
                                        <li>
                                            老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                            <div class="time_b">
                                                <time class="pull-left">2017-05-12</time>
                                                <a href="" class="pull-right">详细&gt;&gt;</a>
                                            </div>
                                        </li>
                                        <li>
                                            老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                            <div class="time_b">
                                                <time class="pull-left">2017-05-12</time>
                                                <a href="" class="pull-right">详细&gt;&gt;</a>
                                            </div>
                                        </li>
                                        <li>
                                            老师逻辑很清楚，点出了运营可以发力的几点，从寻找种子用户，到收集信息数据，分析数据，调整产品 很全面很详细
                                            <div class="time_b">
                                                <time class="pull-left">2017-05-12</time>
                                                <a href="" class="pull-right">详细&gt;&gt;</a>
                                            </div>
                                        </li>
                                    </ul>
                                    <div class="text-center">
                                        <button class="btn btm_more" type="button">查看更多</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   
            </div>
            <div class="kc_hot_title">
                <span>必修课程</span>
            </div>
            <div class="container-fluit mt20">
                <div class="row">
                    <div class="col-md-3 col-xs-6 col-sm-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-6 col-sm-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="kc_hot_title">
                <span>推荐课程</span>
            </div>
            <div class="container-fluit mt20">
                <div class="row">
                    <div class="col-md-3 col-xs-6 col-sm-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-6 col-sm-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-6">
                        <div class="kc_box">
                            <a href=""><img src="${ctx}/resources/img/courseselect/p_pic.jpg" alt=""></a>
                            <ul class="kc_box_item">
                                <li>
                                    <span class="left_title">课程名称：</span>PPT数据分析报告实战
                                </li>
                                <li>
                                    <span class="left_title">讲师：</span>李老师
                                </li>
                                <li>6 人学习 &nbsp; | &nbsp; 26 人预览</li>
                            </ul>
                            <div class="kc_box_btn text-right">
                                <button type="button" class="btn btn_green">OPEN</button>
                                <button type="button" class="btn btn_blue">简介</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
</body>
</html>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">评价</h4>
            </div>
            <div class="modal-body">
                <!-- 这里开始是弹出内容 -->
                <h2 class="kc_popup_title text-center">课程评价</h2>
                <div class="row mt20">
                    <div class="col-md-4 text-right">评分：</div>
                    <div class="col-md-6"><div class="star4"></div></div>
                </div>
                <div class="row mt20">
                    <div class="col-md-4 text-right">评价：</div>
                    <div class="col-md-6">
                        <textarea name="" id="" class="form-control" style="width:100%;"></textarea>
                    </div>
                </div>
                <h2 class="kc_popup_title text-center">老师评价</h2>
                <div class="row mt20">
                    <div class="col-md-4 text-right">评分：</div>
                    <div class="col-md-6"><div class="star4"></div></div>
                </div>
                <div class="row mt20">
                    <div class="col-md-4 text-right"></div>
                    <div class="col-md-6">
                        <div class="checkbox-item">
                            <div class="checkbox-group">
                                <input type="checkbox" name="remember" id="one">
                                <label class="checkbox-2 checked" for="one"></label>
                                
                            </div>
                            <div>匿名评论</div>
                        </div>
                    </div>
                </div>
                <div class="btn_box">
                    <button class="btn submit btn_160_36" type="button" data-dismiss="modal" aria-label="Close">提交</button>
                    <button class="btn cancel btn_160_36" type="button" data-dismiss="modal" aria-label="Close">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>