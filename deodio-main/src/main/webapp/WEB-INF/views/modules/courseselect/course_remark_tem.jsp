<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/courseselect/mycourses.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/courseselect/star.score.css">

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">评价</h4>
            </div>
            <div class="modal-body">
            	<form id="appraiseForm">
            	<input type="hidden" id="contentId" name="contentId" value="${courseId}" />
                <!-- 这里开始是弹出内容 -->
                <h2 class="kc_popup_title text-center">课程评价</h2>
                <div class="row mt20">
                    <div class="col-md-4 text-right">评分：</div>
                    <div class="col-md-6">
                    	<!-- <div class="star2"></div> -->
						<div id="course_score" class="star_bg">                    	
    						<input type="radio" id="courseScore1" class="score score_1" value="1" name="courseScore">
    						<a href="#starScore1" class="star star_1" title="差"><label for="courseScore1"></label></a>
    						<input type="radio" id="courseScore2" class="score score_2" value="2" name="courseScore">
    						<a href="#starScore2" class="star star_2" title="较差"><label for="courseScore2"></label></a>
    						<input type="radio" id="courseScore3" class="score score_3" value="3" name="courseScore">
    						<a href="#starScore3" class="star star_3" title="普通"><label for="courseScore3"></label></a>
    						<input type="radio" id="courseScore4" class="score score_4" value="4" name="courseScore">
    						<a href="#starScore4" class="star star_4" title="较好"><label for="courseScore4"></label></a>
    						<input type="radio" id="courseScore5" class="score score_5" value="5" name="courseScore">
    						<a href="#5" class="star star_5" title="好"><label for="courseScore5"></label></a>
						</div>
					</div>
                </div>
                <div class="row mt20">
                    <div class="col-md-4 text-right">评价：</div>
                    <div class="col-md-6">
                        <textarea name="courseEvaluation" id="courseEvaluation" class="form-control" style="width:100%;"></textarea>
                    </div>
                </div>
                <h2 class="kc_popup_title text-center">老师评价</h2>
                <div class="row mt20">
                    <div class="col-md-4 text-right">评分：</div>
                    <div class="col-md-6">
                    	<!-- <div class="star3"></div> -->
                    	<div id="teacher_score" class="star_bg">                    	
    						<input type="radio" id="teacherScore1" class="score score_1" value="1" name="teacherScore">
    						<a href="#starScore1" class="star star_1" title="差"><label for="teacherScore1"></label></a>
    						<input type="radio" id="teacherScore2" class="score score_2" value="2" name="teacherScore">
    						<a href="#starScore2" class="star star_2" title="较差"><label for="teacherScore2"></label></a>
    						<input type="radio" id="teacherScore3" class="score score_3" value="3" name="teacherScore">
    						<a href="#starScore3" class="star star_3" title="普通"><label for="teacherScore3"></label></a>
    						<input type="radio" id="teacherScore4" class="score score_4" value="4" name="teacherScore">
    						 <a href="#starScore4" class="star star_4" title="较好"><label for="teacherScore4"></label></a>
    						<input type="radio" id="teacherScore5" class="score score_5" value="5" name="teacherScore">
    						<a href="#5" class="star star_5" title="好"><label for="teacherScore5"></label></a>
						</div>
                    </div>
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
                    <button id="submit" class="btn submit btn_160_36" type="button" 
                    	data-dismiss="modal" aria-label="Close">提交</button>
                    <button class="btn cancel btn_160_36" type="button" data-dismiss="modal" aria-label="Close">取消</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>

	<script type="text/javascript">
		require([ 'modules/courseselect/course_remark_tem' ], function(obj) {
		}); 
	</script>
</body>
</html>