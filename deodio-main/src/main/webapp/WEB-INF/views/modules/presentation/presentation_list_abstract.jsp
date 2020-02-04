<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">

<div style="width: 43%"></div>

<script id="abstract_data_template" type="text/x-dot-template">
<div class="content p_border p40" style="width:950px; height:700px;">
		<div>
			<div style="width: 280px;height: 200px;" class="video">
			      {{?it.data.presentation_cover==null || it.data.presentation_cover==''}}
						<img width="280" height="200"   src="${ctx}/resources/img/course/title_pic.png" alt="">
						{{??}}
						  <img width="280" height="200"   src="${imgStatic}{{=it.data.attach_url}}{{=it.data.presentation_cover}}" alt="">	
			      {{?}}
				
			</div>
			<div class="video_r" style="padding-left: 300px">
				<h3 class="title pb10">{{=it.data.presentation_name}}</h3>
				<div class="mt10">
			        <ul class="course_des">
			    
			          <li style="width: 30%">章节名称：<span>
			     
							{{=it.data.presentation_name}}
					
			          	
			          </span></li>
			 
			          
			          <li style="width: 30%">课件数：<span>{{=it.data.itemCount}}</span></li>
			          <li style="width: 30%">课件格式：<span>
						{{?it.data.presentation_model==0}}
							SCORM 标准课件				
						{{?}}
	{{?it.data.presentation_model==1}}
							非标准课件包				
						{{?}}
	{{?it.data.presentation_model==2}}
							自行设置课件包				
						{{?}}
	{{?it.data.presentation_model==3}}
							直接填入外部链接的课件				
						{{?}}
					
							</span></li>
			          <li style="width: 30%">创建时间：<span>{{=dateFormat(it.data.create_time)}}</span></li>
			          <li style="width: 30%">创建人：<span>{{=it.data.username}}</span></li>
			          <li class="wb100">所属分类：
							
						 {{var classNames=it.data.classification_name.split(",");for(var i in classNames) { }}
								<em>{{=classNames[i]}}</em>
							{{ } }}
						
			          </li>
			        </ul>
			        <div class="text-left">
						{{var userId =$.cookie('CUID');}}
					{{?it.data.create_id == userId || userId ==it.data.presentation_owner}}
			          {{?it.data.is_publish==0}}
							<button type="button" class="btn_b mt20" onclick="presentaPublish(1,'{{=it.data.id}}')">发布</button>
							{{??}}
							<button type="button" class="btn_b mt20" onclick="presentaPublish(0,'{{=it.data.id}}')">取消发布</button>
			       		 {{?}}
			     	 {{?}}	
			          
			        </div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<input type="hidden" id="courseId" name="courseId" value="">
		
		<ul  id="myTab"  class="nav nav-tabs set_tab mt20" role="tablist" style="padding-left: 0px; background-color: #e1eaee ! important;">
			<li class="active" role="presentation">
				<a href="#jieshao" role="tab" onclick="setTab('myTabContent')" data-toggle="tab">章节介绍</a></li>
			<li role="presentation">
				<a href="#dagang" role="tab" onclick="setTab('dagang')" data-toggle="tab">章节大纲</a></li>
	
		</ul>
		
	<div id="myTabContent" style="word-break: break-all;word-wrap: break-word;" class="tab-content _tabs">
			{{=it.data.presentation_desc}}
		
	</div>
		
	<div class="pre1_con tab-pane _tabs" id="dagang" style="height:500px;border:0px">
				<div class="row discuss_box">
                            <div class="col-md-8">
                                <div class="discuss_left" style="border:0px;overflow-y: auto;width: 850px;">
                                 
                                    <ul class="contents_list" style="width: 950px;height: 320px;">
										{{~it.data.itemsList:v:index}}
                                        <li>
                                            <span class="lession text-right">课件{{=index+1}}</span>
                                            <span class="round_b" style="background:#fff;"><em class="round_con" style=""></em></span>
                                            <h4 class="h4_title"><a href=""><em class="green"></em>
												{{?it.data.presentation_model==0}}
														{{=v.title}}
														{{??}}
														{{=v.packageName}}
												{{?}}
															
												</a></h4>
                                          
                                            <div class="line"></div>
                                        </li>
                                        {{~}}
                                    </ul>
                                </div>          
                            </div>
                          
                        </div>


		</div>
		

	</div>


</script>


	