<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script id="course_classroom_presentation_external_data_template" type="text/x-dot-template">
	<div class="video_con" style="width:100%;">
         <div class="video_box2"  style="height:620px;">
             <div class="middle_video" style="min-height:620px !important;">
                 <div class="left_video_content" style="text-align:center;vertical-align:middle;position: absolute; width: 100%; height: 100%;">
                    <!-- 设置课件的规则 -->
					{{? it.presentationModelExternal.externalType == 1}}
					<iframe id="itemFramePanel" class="form-control wb100" style="height: 100%; width:100%;border:1px solid #f3f3f3;border-radius: 0;" src="{{? it.presentationModelExternal.externalType == 1}}{{=it.presentationExternalItem.externalUrl}}{{?}}">
						您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。
					</iframe>
					{{?}}
						
					{{? it.presentationModelExternal.externalType == 2}}
						<div id="itemYoukuPanel" style=" background:#e3e3e3;height: 100%">
							<EMBED name=movie_player id="movie_player"  
						                               pluginspage=http://www.macromedia.com/go/getflashplayer  
						                               src='{{? it.presentationModelExternal.externalType == 2}}http://player.youku.com/player.php/Type/Folder/Fid/4699493/Ob/1/Pt/51/sid/{{=getYoukuId(it.presentationExternalItem.externalUrl)}}/v.swf{{?}}
						                               width=999 height=500 type=application/x-shockwave-flash  
						                               flashvars="isShowRelatedVideo=false&showAd=0&show_pre=0&show_next=0&isAutoPlay=false&isDebug=false&UserID=&winType=interior&playMovie=true&MMControl=false&MMout=false&RecordCode=1001,1002,1003,1004,1005,1006,2001,3001,3002,3003,3004,3005,3007,3008,9999"  
						                               allowfullscreen="true" quality="high" bgcolor="#000000"  
						                               d="movie_player"></EMBED>  
						</div>
					{{?}}
                    </div>
              </div>
         </div>
     </div>
</script>
