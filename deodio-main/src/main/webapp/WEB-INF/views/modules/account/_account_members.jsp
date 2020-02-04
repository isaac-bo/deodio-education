<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">





<div class="bradius2" id="members">
	
	<div class="pre_left" style="margin-left: -20px;width: 980px;margin-bottom: 20px;">
		<div class="con-mask"></div>
		<div class="con" style="min-height: 20px;">
				<div class="pre_left_top_fl" >
					<div class="pre_left_top_fl_tittle" style="float:right;">
						<div class="pull-right">
						<div class="w240 pull-right mr10">
							<button type="button" class="btn-success btn36" onClick="searchAccountMemberList();">&nbsp;</button>
							<div class="search_input"><input type="text" class="form-control" id="keywords_account_member"></div>	
						</div>	
					</div>
					</div>
					<div class="pre_left_top_fl_tab">
					</div>
				</div>
				<!--pre_left_top_fl end-->
				<div class="clearfix"></div>
				<div class="con-corner"></div>
			</div>
	   <div>
	   </div>
	  </div>


	<div id="member_list">

		<table class="table payment-table table-striped td_h60" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th>头像</th>
					<th>用户昵称</th>
					<th>姓名</th>
					<th>邮箱</th>
					<th>电话</th>
					<th>登陆次数</th>
					<th>最后登陆时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="members_table_panel">
			</tbody>
		</table>
		
		<div class="mt20 text_center" id="members_table_page_panel">
			 <ul class="pagination pagination-lg"></ul>
		</div>
	
	</div>
	
	<div id="member_detail" class=" " style="display:none; ">
		<div class="go_back" onclick="onMemberList()"><span class="icon-double-angle-down"></span></div>
		
		 <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid" style="border-bottom:0px;padding-bottom: 0px;">
			<div id="head_detail" class="row" tyle="padding-top: 10px; padding-bottom: 10px">
				
			</div>
		 </div>
		 
		 <div class="shadow_footer mt20"></div>
		
		
		<input type="hidden" name="account_member_id" id="account_member_id" />
		
		<div class="mt20">
			<ul class="nav nav-tabs set_tab" role="tablist" style= "background-color:#FFFFFF ">
		        <li role="presentation" class="active"><a href="#group" role="tab" data-toggle="tab">创建小组</a></li>
		        <li role="presentation"><a href="#items" role="tab" data-toggle="tab" onclick="loadItemListByCreator()">创建课程</a></li>
		        <li role="presentation"><a href="#header" role="tab" data-toggle="tab">费用</a></li>
		        <li role="presentation"><a href="#model" role="tab" data-toggle="tab">能力模型</a></li>
		    </ul>
		    
		    <div class="tab-content" style="background-color: #f6f7f8;">
		        <div role="tabpanel" class="tab-pane active" id="group">
		        	<div class="items-list">
		        		<!-- <div class="feature-tip feature-layer" style="margin-left: 867px;">
		        			<span class="icon-bar-chart icon-chart" onclick="javascript:onToggleChart('group_chart_panel')"></span>
						</div> -->
		        		<ul id="self_group_list" style="padding:10px;">
		        				
		        		</ul>
		        	</div>
		        	
		        	<!-- <div class="items-chart" id="group_chart_panel" style="display:none;">
		        		<div id="group_chart" style="height:500px;width:948px;"></div>
		        	</div> -->
		        </div>
		        
		        <div role="tabpanel" class="tab-pane" id="items">
		        	<div class="items-list">
		        		<!-- <div class="feature-tip feature-layer" style="margin-left: 867px;">
		        			<span class="icon-bar-chart icon-chart" onclick="javascript:onToggleChart('items_chart_panel')"></span>
						</div> -->
		        		<ul id="self_items_list" style="padding:10px;">
		        			
		        				
		        		</ul>
		        	</div>
		        	
		        	<!-- <div class="items-chart" id="items_chart_panel" style="display:none;">
		        		<div id="items_chart" style="height:500px;width:948px;"></div>
		        	</div> -->
		        </div>
		        
		        <div role="tabpanel" class="tab-pane" id="header">
		        	<div class="items-list">
		        		<!-- <div class="feature-tip feature-layer" style="margin-left: 867px;">
		        			<span class="icon-bar-chart icon-chart" onclick="javascript:onToggleChart('items_chart_panel')"></span>
						</div> -->
		        		<ul id="self_items_list" style="padding:10px;">
		        			<div class="null_table" style="border:0px;">NULL<span class="null_t">暂时没有相关数据</span></div>
		        				
		        		</ul>
		        	</div>
		        	
		        	<!-- <div class="items-chart" id="items_chart_panel" style="display:none;">
		        		<div id="items_chart" style="height:500px;width:948px;"></div>
		        	</div> -->
		        </div>
		        
		        <div role="tabpanel" class="tab-pane" id="model">
		        	<div class="items-list">
		        		<!-- <div class="feature-tip feature-layer" style="margin-left: 867px;">
		        			<span class="icon-bar-chart icon-chart" onclick="javascript:onToggleChart('items_chart_panel')"></span>
						</div> -->
		        		<ul id="self_items_list" style="padding:10px;">
		        			<div class="null_table" style="border:0px;">NULL<span class="null_t">暂时没有相关数据</span></div>
		        				
		        		</ul>
		        	</div>
		        	
		        	<!-- <div class="items-chart" id="items_chart_panel" style="display:none;">
		        		<div id="items_chart" style="height:500px;width:948px;"></div>
		        	</div> -->
		        </div>
		        
		     </div>
	     </div>
	</div>
</div>

<script id="members_table_data_template" type="text/x-dot-template">
 	{{~it.data:v:index}}

		<tr>
		  <td>
			 {{? v.user_icon == null}}
				<img src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" style="width:36px;height:36px;border-radius: 50%;border:2px solid {{=randomBorderColor()}};" id="img{{=isNullFormat(v.id)}}">
			 {{??}}
				<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" style="width:36px;height:36px;border-radius: 50%;border:2px solid {{=randomBorderColor()}};" id="img{{=isNullFormat(v.id)}}">
			 {{?}}
		  </td>
		  <td >{{=isNullFormat(v.nick_name)}}</td>
          <td >{{?v.first_name != null || v.last_name !=null}}{{=isNullFormat(v.first_name)}},{{=isNullFormat(v.last_name)}}{{??}}匿名{{?}}</td>
		  <td >{{=isNullFormat(v.user_mail)}}</td>
		  <td ">{{=isNullFormat(v.mobile_phone)}}</td>
		  <td >12</td>
		  <td class="text-center">{{=dateFormat(isNullFormat(v.update_time))}}</td>
		  <td><button type="button" class="btn_act" onclick="onMemberDetail('{{=isNullFormat(v.id)}}');">管理</button></td>
	   </tr>
 {{~}}	
</script>



<script id="member_detail_table_data_template" type="text/x-dot-template">
	<div class="col-lg-3 text-center">
		<div style="border-right: 1px solid #dcdcdc; padding: 20px 0">
			 {{? it.data.user_icon == null}}
				<img src="${ctx}/resources/img/account/homepaage_movie_travel.jpg" style="border-radius: 50%; width: 120px; height: 120px;border: 2px solid #43b4c6">
			 {{??}}
				<img src="${imgStatic}/{{=isNullFormat(it.data.attach_url)}}{{=isNullFormat(it.data.generate_name)}}" style="border-radius: 50%; width: 120px; height: 120px;border: 2px solid #43b4c6">
			 {{?}}
		</div>
	</div>
	<div class="col-lg-9">
		<div class="table-responsive border-0" style="margin-top: 30px">
			<table class="table th-right account_member" id="member_detail_table_data_panel">
				<tbody>
					<tr>
						<th align="right"><img src="${ctx}/resources/img/account/account_member_id.png"></img>用户ID:</th>
						<td>{{=isNullFormat(it.data.user_name)}}</td>
						<th align="right"><img src="${ctx}/resources/img/account/account_member_name.png"></img>用户姓名:</th>
						<td>{{?it.data.first_name != null || it.data.last_name !=null}}{{=isNullFormat(it.data.first_name)}},{{=isNullFormat(it.data.last_name)}}{{??}}匿名{{?}}</td>
					</tr>
					<tr>
						<th align="right"><img src="${ctx}/resources/img/account/account_member_mail.png"></img>邮箱地址:</th>
						<td align="left">{{=isNullFormat(it.data.user_mail)}}</td>
						<th align="right"><img src="${ctx}/resources/img/account/account_member_phone.png"></img>手机号:</th>
						<td align="left">{{=isNullFormat(it.data.mobile_phone)}}</td>
					</tr>
				</tbody>			
			</table>
		</div>
	</div>
</script>

<script id="self_group_data_template" type="text/x-dot-template">

{{~it.data:v:index}}
<li id="group_{{=isNullFormat(v.id)}}">
	<div class="item-image">
		{{? isNullFormat(v.attach_url) == ''}}
		<img src="${ctx}/resources/img/presentation/p_pic.jpg" alt="">
		{{??}}
		<img src="${imgStatic}/{{=isNullFormat(v.attach_url)}}{{=isNullFormat(v.generate_name)}}" alt="">
		{{?}}
	</div>

	<!--div class="feature-tip feature-layer {{? v.create_id == $('#account_member_id').val()}}feature-tip-self{{?}}">
	</div>
			
	<div class="feature-icon feature-layer" style="margin-left:530px;">
		{{? v.create_id == $('#account_member_id').val()}}
		<span class="icon-user"></span>
		{{??}}
		<span class="icon-group"></span>
		{{?}}
	</div-->

	<div class="item-content">
		<p>小组名称：{{=subString(isNullFormat(v.group_name),20)}}</p>
		<p style="height:50px;color:#ccccd4;">{{=subString(removeHtmlTags(isNullFormat(v.group_description)),30)}}</p>
		
		<div class="pull-right">
			<span class="icon-user"></span><span>{{=isNullFormat(v.activecount)}}</span>
			<span class="icon-envelope"></span><span>{{=isNullFormat(v.allcount)}}</span>
		</div>
	</div>
</li>
{{~}}	
</script>


<script id="self_items_data_template" type="text/x-dot-template">

{{~it.data:v:index}}
<li id="item_{{=isNullFormat(v.id)}}">
	<div class="item-image">
		{{? isNullFormat(v.cover_img) != ''}}
			<img src="${imgStatic}{{=isNullFormat(v.cover_img)}}" alt=""  width="278">
		{{??}}
			<img src="${ctx}/resources/img/account/course-title-img-1.jpg" width="278">
		{{?}}
	</div>

	<!--div class="feature-tip feature-layer 
		{{? isNullFormat(v.item_type) == 1}}
			feature-tip-online
		{{?? isNullFormat(v.item_type) == 2}}
			feature-tip-online
		{{?? isNullFormat(v.item_type) == 3}}
			feature-tip-online
		{{?? isNullFormat(v.item_type) == 4}}
			feature-tip-package
		{{?}}">
	</div-->
			
	<div class="feature-icon feature-layer" style="margin-left: 8px;">
		{{? isNullFormat(v.item_type) == 1}}
			<span class="icon-desktop" style="color:#43b4c6"></span>
		{{?? isNullFormat(v.item_type) == 2}}
			<span class="icon-coffee" style="color:#43b4c6"></span>
		{{?? isNullFormat(v.item_type) == 3}}
			<span class="icon-facetime-video" style="color:#43b4c6"></span>
		{{?? isNullFormat(v.item_type) == 4}}
			<span class="icon-gift" style="color:#f06159"></span>
		{{?}}
	</div>


	<div class="item-content">
		<p>课程名称：{{=subString(isNullFormat(v.item_name),20)}}</p>
		<p>课程评分：{{? isNullFormat(v.item_star) == 2}}
								<div class="star2"></div>
							{{?? isNullFormat(v.item_star) == 3}}
								<div class="star3"></div>
							{{?? isNullFormat(v.item_star) == 4}}
								<div class="star4"></div>
							{{?? isNullFormat(v.item_star) == 5}}
								<div class="star5"></div>
							{{??}}
								<div class="star1"></div>
							{{?}}</p>
		<p style="height:35px;color:#ccccd4;">{{=subString(removeHtmlTags(isNullFormat(v.item_desc)),30)}}</p>
	</div>
</li>
{{~}}	
</script>

<script type="text/javascript">
	require([ 'modules/account/_account_members' ], function() {

	});
</script>
