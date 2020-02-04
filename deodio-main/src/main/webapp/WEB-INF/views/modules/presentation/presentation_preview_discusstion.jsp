<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="r_content" id="r_content_div">

           
               
          


  </div>
<script type="text/javascript">
	require(['modules/presentation/presentation_preview_discusstion' ], function(obj) {
		
	});
</script>  
<script id="content_div_data_template" type="text/x-dot-template">
{{~it.data:v:index}}
	     <div class="user_top border_line">
                    <img src="../img/user_pic.jpg" alt="" class="user_40" style="border-color:#4275F4;">
                    <span class="user_name">{{=v.userName}}</span>
                </div>
                <div class="conment_more_box" id="bg1">
                    <div class="commenter">
                      
                        <span class="time_a">{{=formatDate2Char(v.createDate)}}</span>
                     
                        <div class="clearfix"></div>
                    </div>
                    <div class="conmenter_con pr18">
                        	{{=v.discussionContent}}
                    </div>
                    <div class="btm_button first_conment">
                        <span class="c767676"><img src="${ctx}/resources/img/right_icon2.png" class="mt-3"> · {{=v.replyCount}}</span>
                        <span class="ml10" style="color:#4985FF;"><img src="${ctx}/resources/img/right_icon3.png" class="mt-3"> · {{=v.likeCount}} </span>
                        <span class="pull-right">
                    		<button class="btn ok" style="color:#110f0f" onclick="replyDiscusstion('{{=v.id}}')" type="button">回&nbsp;复</button>
                            <a href="javascript:;" onclick="deleteDiscusstion('{{=v.id}}')" class="ml10 delete"></a>
                        </span>
                        <button class="open_btn _open_conment" type="button" id="open_conment"></button>
                        <div class="clearfix"></div>
                        <div class="input-group"><input class="form-control" id="reply_{{=v.id}}" placeholder="输入回复内容" type="text"></div>
                    </div>
                </div>
       <div class="conment_more_box" style="display:none;" id="conment_more_show">
			
			{{~v.pdrlist:obj:index2}}
                    <div class="com_rel">
                        <div class="com_rel2">
                            <div class="user_top">
                                <img src="../img/user_pic.jpg" alt="" class="user_40" style="border-color:#4275F4;">
                                <span class="user_name">{{=obj.userName}}</span>
                                <span class="time_a">{{=formatDate2Char(obj.replyDate)}}</span>
                            </div>
                            <div class="conmenter_relcon">
                             		{{=obj.replyContent}}
                            </div>
                            <div class="btm_button pl0">
                                <span class="pull-right">
                                    <a href="javascript:;"  onclick="deleteReplyComfirm('{{=obj.id}}')"  class="ml10 delete"></a>
                                </span>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
               
                    <div class="up_box _deletereply" id="deleteReplyComfirm_{{=obj.id}}" style="display:none;">
                        <div class="up_box_btn text-center">
                            <p>您确定是否删除这条评论?</p>
                            <button type="button"  onclick="deleteCanle()" class="btn cancel">取消</button>
                            <button type="button"  onclick="deleteReply('{{=obj.id}}')"   style="color:#110f0f" class="btn ok">确定</button>
							
                        </div>
                    </div>

					
	
			{{~}}
		
          </div>
{{~}}
</script>
  
  