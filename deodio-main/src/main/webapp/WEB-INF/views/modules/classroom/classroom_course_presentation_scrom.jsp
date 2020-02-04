<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script id="course_classroom_presentation_scrom_data_template" type="text/x-dot-template">
	<div class="video_con" style="width:100%;">
         <div class="video_box2"  style="height:620px;">
             <div class="middle_video" style="min-height:620px !important;">
             	<div class="left_video_content" style="text-align:center;vertical-align:middle;position: absolute; width: 100%; height:100%;">
             		<iframe id ="rightSco" src="" width="100%" height="100%" style="border: 1px solid #2d2d2d;"></iframe>
             	</div>
             </div>
         </div>
     </div>
</script>


<script id="scrom_data_template" type="text/x-dot-template">
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
			<span class="c767676">回复 · 3</span> 
			<span class="ml10 glyphicon glyphicon-thumbs-up" style="color: #4985FF;font-size: 16px; top: 3px;"></span>
			<span style="color: #4985FF;">· 4 </span> 
									
		</div>
	</div>
{{~}}
</div>	
<div style="height:50px;"></div>
</script>