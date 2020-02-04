<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/courseselect/mycourses.css">

<body class="kc_bg_blue">
    <div class="kc_que">
        <div class="row mt20">
            <div class="col-md-6"><img src="${ctx}/resources/img/courseselect/kaoshi.png" alt=""></div>
            <div class="col-md-6 text-right">
                <a href=""><img src="${ctx}/resources/img/courseselect/kc_close.png" alt="" class="mt20"></a>
            </div>
        </div>
        <div class="kc_que_con">
            <h3 class="kc_top_title">题目总数：20</h3>
            <div class="mt20">
                <div class="ti_left">
                    <div class="chouti">
                        <div class="edit_title">
                            <span class="pull-left">1</span>
                            <em class="pull-right t_gray">本题8分</em>
                            &emsp;题目一单选题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex" id="man" value="">
                                <label class="radio-2" for="man"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目一">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex1" id="man1" value="">
                                <label class="radio-2" for="man1"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目二">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex2" id="man2" value="">
                                <label class="radio-2" for="man2"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目三">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex2" id="man2" value="">
                                <label class="radio-2" for="man2"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目四">
                            </div>
                        </div>
                    </div>
                    <div class="chouti mt20">
                        <div class="edit_title">
                            <span class="pull-left">2</span>
                            &emsp;题目二多选题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="ti">
                            <div class="checkbox-group pull-left">
                                <input type="checkbox" name="remember2" id="three2">
                                <label class="checkbox-2" for="three2"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目一">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="checkbox-group pull-left">
                                <input type="checkbox" name="remember2" id="three2">
                                <label class="checkbox-2" for="three2"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目二">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="checkbox-group pull-left">
                                <input type="checkbox" name="remember2" id="three2">
                                <label class="checkbox-2" for="three2"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目三">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="checkbox-group pull-left">
                                <input type="checkbox" name="remember2" id="three2">
                                <label class="checkbox-2" for="three2"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control" value="题目四">
                            </div>
                        </div>
                    </div>
                    <div class="chouti mt20">
                        <div class="edit_title">
                            <span class="pull-left">3</span>
                            &emsp;题目三判断题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex" id="man" value="">
                                <label class="radio-2" for="man"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20 mt10">
                                对
                            </div>
                        </div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex" id="man" value="">
                                <label class="radio-2" for="man"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20 mt10">
                                错
                            </div>
                        </div>

                    </div>
                    <div class="chouti mt20">
                        <div class="edit_title">
                            
                            <span class="pull-left">4</span>
                            &emsp;题目三判断题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex" id="man" value="">
                                <label class="radio-2" for="man"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20 mt10">
                                对
                            </div>
                        </div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex" id="man" value="">
                                <label class="radio-2" for="man"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20 mt10">
                                错
                            </div>
                        </div>
                    </div>
                    <div class="chouti mt20">
                        <div class="edit_title" style="border-bottom:1px solid #cdd5d9;">
                            <span class="pull-left">5</span>
                            <div class="pl10" style="background:#cdd5d9;border-radius:0 2px 0 0;">题目三判断题题目三判断题题目三判断题题目三判断题题目三判断题</div>
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex" id="man" value="">
                                <label class="radio-2" for="man"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20 mt10">
                                对
                            </div>
                        </div>
                        <div class="ti">
                            <div class="radio-group pull-left">
                                <input type="radio" name="sex" id="man" value="">
                                <label class="radio-2" for="man"></label>
                            </div>
                            <div class="ti_input_w pull-left ml20 mt10">
                                错
                            </div>
                        </div>
                    </div>
                    <div class="chouti mt20">
                        <div class="edit_title">
                            <span class="pull-left">6</span>
                            &emsp;简答题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="jianda">
                            <textarea name="" id=""></textarea>
                        </div>
                    </div>
                    <div class="chouti mt20">
                        <div class="edit_title">
                            <span class="pull-left">7</span>
                            &emsp;图形题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="tuxing p20">
                                <div class="l_pic">
                                    <button type="button" class="hdp">选择需要上传的图片</button>
                                </div>
                                <textarea name="" id="" cols="30" rows="10" class="pic_textarea ml20 pull-left"></textarea>
                                <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="chouti mt20">
                        <div class="edit_title">
                            <span class="pull-left">8</span>
                            &emsp;简答题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="jianda">
                            <textarea name="" id=""></textarea>
                        </div>
                    </div>

                    <div class="chouti mt20">
                        <div class="edit_title">
                            <span class="pull-left">9</span>
                            &emsp;排序题
                        </div>
                        <div class="c929292 f12 p20">如不需要设置必考题，请跳到下一步。</div>
                        <div class="ti">
                            <div class="pull-left pt8">1</div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control h36 br2" value="题目一">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="pull-left pt8">1</div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control h36 br2" value="题目一">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="pull-left pt8">1</div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control h36 br2" value="题目一">
                            </div>
                        </div>
                        <div class="ti">
                            <div class="pull-left pt8">1</div>
                            <div class="ti_input_w pull-left ml20">
                                <input type="text" class="form-control h36 br2" value="题目一">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="btn_box">
                    <button class="btn submit btn_160_36" type="button">提交</button>
                    <button class="btn btn_green btn_160_36" type="button">保存</button>
                    <button class="btn cancel btn_160_36" type="button">返回</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>