<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script id = "course_classroom_presentation_sync_data_template" type="text/x-dot-template">
 	<div class="top_title">
         <h3 id="slidesTitle">SLIDES</h3>
         <h3 id="mediaTitle">多媒体</h3>
     </div>
     <div class="video_box2" style="height: 541px;">
 		<div class="left_video" style="height: 541px;">
         	<div class="left_video_content" style="text-align:center;vertical-align:middle;position: absolute; width: 100%; height: 100%;"></div>
         	<input type="hidden" name='_sync_point_hidden' id='_sync_point_hidden' value=''/>
   	</div>
      <div class="right_video" style="height: 541px;">
      	<div class="right_video_content" style="position:absolute;"></div>
  	</div>
  	<button class="paly_btn" type="button"><img src="${ctx}/resources/img/editor/play_icon.png" alt=""></button>
  </div>
  <div class="video_play_bar">
         <ul class="left_btn">
             <li><button type="button" class="prev_media"><img src="${ctx}/resources/img/editor/play_icon1.png" alt=""></button></li>
             <li><button type="button" class="play_media"><img src="${ctx}/resources/img/editor/play_icon2.png" alt=""></button></li>
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
         <!--div class="btm_edit_bar" style="">
            
             <div class="input_box"><input type="text" id="comment_content"></div>
             <ul class="right_btn_box">
                 <li><button><img src="${ctx}/resources/img/editor/brush.png" alt=""></button></li>
                 <li><span>00:21</span></li>
                 <li><button type="submit" class="btn_ti comment_content_submit" style="display:block;" onclick="submitComment()">提交</button></li>
             </ul>
         </div>

		 <div class="open_up_div">
	         <button class="open_up" type="button" onclick="javascript:onToggleCommentToolBar();">
	            <div class="user_img"> <img src="${userHeader}" width="36" style="border-color: #4275F4;"></div>
	            <div class="comment_img">
	    			打点 <span class="arrow icon-double-angle-right" style="margin-left: 5px;position: absolute;font-size: 20px;"></span>
	    		</div>
	    	</button>
    	</div->

     </div>
</script>

<script id="comment_point_template" type="text/x-dot-template">

<span class="set_point" id="comment_{{=it.commentId}}" style="border:4px solid #4275f4;background:#fff;left:{{=it.posX}}px;top:{{=it.posY}}px">
	<input type="hidden" id="comment_content_{{=it.commentId}}" value=""/>
</span>

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