<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.list.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/location/location.css">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=N8vgc36wB4ovE6cbRCgwe1rY5WSjrWiM"></script>

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	            
	<div class="content" id="shijuan">
		<div class="pre_center">
			<div class="con-mask"></div>
			<div class="con" style="min-height: 20px;">
					<div class="pre_left_top_fl" style="width:1205px;">
						<div class="control-bar pull-left ml20">
							
							<a onclick="mediumIconShow(1);" href="javascript:void(0)" class="pic-item on">
								<div class=""></div>
								<div class="on"></div>
								<div class=""></div>
								<div class=""></div>
							</a>
							
							<a onclick="smallIconShow(1)" href="javascript:void(0)" class="table-item">
								<div class="" style="margin-top: 2px;"></div>
								<div class=""></div>
								<div class=""></div>
								<div class=""></div>
							</a>
						</div>
						<button type="button" class="btn btn_green ml20 btn36" onclick="popLocations();">创建考场地点</button>
						<!-- <button type="button" class="btn btn_red ml10 btn36" id="delAllLocations" onclick="delAllLocations();" style="display:none;">批量删除</button> -->
						<div class="w240 pull-right mr20">
							<button type="button" class="btn-success btn36" onClick="javascript:searchLocationList()">&nbsp;</button>
							<div class="search_input"><input id="keywords" type="text" class="form-control"></div>	
						</div>
					</div>
					<!--pre_left_top_fl end-->
					<div class="clearfix"></div>
					<div class="con-corner"></div>
				</div>
		   <div>
		   </div>
	   </div>
		
		<div id="smallIcon" class="p20" style="display:none;margin-top:50px;">
			<table cellpadding="0" cellspacing="0" class="table table-striped table-hover td_h60 mt20">
				<thead>
					<tr>
						<th class="text-center">
							<!-- <div class="checkbox-group">
	            				<input type="checkbox" name="allLocationsId" id="allLocationsId">
	             				<label class="checkbox-2" for="allLocationsId"></label>
			        		</div> -->
						</th>
						<th class="text-center" style="width:25%">考场名称</th>
						<th class="text-center" style="width:40%;">考场地点</th>
						<th class="text-center" style="width:15%;">是否可用</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody id="table_panle">
				</tbody>
			</table>
			
			<div class="mt20 text-center" id="data_page_Panel">
				
			</div>
		</div>
		
		<div id="mediumIcon" style="margin-top:50px;" class="p20">
			<div class="container-fluid">
				<div id="content" style="margin-left: -16px; margin-top: 20px;">
				
				</div>
				<div class="clearfix"></div>	
				<input type="hidden" id="hid_default_page" value="1">
			</div>
		</div>	
	
	<input type="hidden" name="locationLongitude" id="locationLongitude" value="${locationLongitude }">
	<input type="hidden" name="locationLatitude" id="locationLatitude" value="${locationLatitude }">

</div>
<script id="table_data_template" type="text/x-dot-template">
 {{~it.data:v:index}}
		<tr>
			<td class="text-center">
				<div class="checkbox-group" >
          			<input type="checkbox" name="locationsId" id="{{=isNullFormat(v.id)}}" onclick="isSelectAll('allLocationsId','locationsId');">
           			<label class="checkbox-2" for="{{=isNullFormat(v.id)}}"></label>
       			</div>
			</td>
			<td>{{=isNullFormat(v.location_name)}}</td>
			<td>{{=isNullFormat(v.location_address)}}</td>
			<td class="text-center">{{? v.is_enabled == 0}}不可用{{?}}{{? v.is_enabled == 1}}可用{{?}}</td>
			<td class="text-center">
				<button type="button" class="icon_1" onClick="javascript:editLocation('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>
				<button type="button" class="icon_2" onClick="javascript:delLocation('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>

				<button type="button" class="icon_3" onClick="javascript:previewLocation('{{=isNullFormat(v.id)}}')"></button> 

			</td>
		</tr>
 {{~}}	

</script>

<script id="data_template" type="text/x-dot-template">
	{{~it.data:v:index}}
		{{? index%3 == 0}}<div class="row" style="margin-bottom:30px;">{{?}}
			<div class="col-md-4">
				<div class="t_box p10">
				<div class="pull-left" id="map_{{=isNullFormat(v.id)}}" style="width:150px;height:200px;border: 1px solid #eaeaea;"></div>
					
					<div style="height:152px;">
					<table class="pull-left ml20">
						<tr>
							<td style="width: 50px;"><strong>名称：</strong></td>
							<td title="{{=isNullFormat(v.location_name)}}">{{=subString(isNullFormat(v.location_name),10)}}</td>
						</tr>
						<tr>
							<td style="width: 50px;"><strong>地点：</strong></td>
							<td title="{{=isNullFormat(v.location_address)}}">{{=subString(isNullFormat(v.location_address),10)}}</td>
						</tr>
						<tr>
							<td style="width: 50px;"><strong>描述：</strong></td>
							<td title="{{=isNullFormat(v.location_desc)}}">{{=subString(isNullFormat(v.location_desc),40)}}</td>
						</tr>
					</table>
					</div>

					<!--ul class="pull-left ml20" style="min-height: 135px;">
						<li><strong>名称：</strong>{{=isNullFormat(v.location_name)}}</li>
						<li><strong>地点：</strong>{{=isNullFormat(v.location_address)}}</li>
						<li><strong>描述：</strong>{{=isNullFormat(v.location_desc)}}</li>
					</ul-->
					<ul class="pull-left ml20">
						<li class="text-right mt20 pt10 b_top">
							<button type="button" class="icon_1 ml10" onClick="javascript:editLocation('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>
							<button type="button" class="icon_2 ml10" onClick="javascript:delLocation('{{=isNullFormat(v.id)}}');" {{? v.create_id != v.current_user_id }} style="display:none" {{?}}></button>

							 <button type="button" class="icon_3 ml10" onClick="javascript:previewLocation('{{=isNullFormat(v.id)}}')"></button> 

						</li>
					</ul>
				</div>
			</div>
			
		{{? index%3 == 2}}</div>{{?}}
	{{~}}	

</script>

<script id="pop_template" type="text/x-dot-template">
<form id="locationForm" method="POST">
	<div>
		<ul class="kaochang">
			<input type="hidden" id="locationId" value="{{=it.data.id}}">
			<li>
				<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
					<span class="input-title-span">＊</span></label>考场名称：</div>
				<input id="locationName" type="text" value="{{=it.data.locationName}}" class="form-control w400" data-callback="checkName()" call-message="当前考场名已被注册" check-type="required"min-max="0-50" required-message="请输入考场名称！" placeholder="请输入考场名称" aria-describedby="basic-addon1">
				<input type="hidden" value="{{=it.data.locationName}}" id="hiddenlocationName"> 
			</li>
			<li>
				<div class="pull-left text-right w100 pt10"><label class="input-title input-title-color">
					<span class="input-title-span">＊</span></label>考场地点：</div>
				<div style="position: absolute; left: 100px; margin-top: -5px;">
				<select  onchange="selectCountry()" name="dicCountry" id="select_country" data-width="100px" class="selectpicker ">
		           <option value="1">中国</option>
		           <c:forEach items="${countryList}" var="item">
			          <option  <c:if test="${item.countryId ==countryVal}">selected="selected" </c:if> value="${item.countryId}">${item.name}</option>                  
		           </c:forEach>
		        </select>

	            <select  id="select_province" name="dicProvince" onchange="selectProince()" data-width="100px" class="selectpicker ">
		           <option value="">省</option>
		             <c:forEach items="${provinceList}" var="item">
			            <option <c:if test="${item.provinceId ==provinceVal}">selected="selected" </c:if>  value="${item.provinceId}">${item.name}</option>              
		             </c:forEach>  
		        </select>
	           
	            <select id="city_select"  name="dicCity" onchange="selectCity()" data-width="100px" class="selectpicker ">
		 	       <option value="22">市</option>
		 	       <option value="23">市</option>
<!--		           <c:forEach items="${cityList}" var="item">
			           <option <c:if test="${item.cityId ==cityVal}">selected="selected" </c:if> value="${item.cityId}">${item.name}</option>              
		           </c:forEach>
-->
		        </select>
				</div>
			</li>
			<li class="rel">
				<div class="pull-left text-right w100 pt10">&nbsp;</div>
				<input id="locationAddress" type="text" value="{{=it.data.locationAddress}}"  class="form-control w400" check-type="required" min-max="0-200" required-message="请输入考场地点！" placeholder="请输入考场地点" aria-describedby="basic-addon1">
				<span id="searchResult" style="display:none;color:red">* 没有搜索到结果，请重新输入搜索地址</span>
				<button type="button" onclick="mapCheck();">地图校验</button>
			</li>
			<li>
				<div class="pull-left text-right w100 pt8"><label class="input-title input-title-color">
					<span class="input-title-span">＊</span></label>考场简介：</div>
				<div class="r_con" style="height:100px;">
					<textarea  id="locationDesc" class="r_con" style="height:100px;border:1px solid #ccc;" name="" check-type="required" min-max="0-2000" required-message="请输入考场简介！" placeholder="请输入考场简介" aria-describedby="basic-addon1">{{=it.data.locationDesc}}</textarea>
				</div>
			</li>
			<li>
				<div class="pull-left text-right w100 pt8">&nbsp;</div>
				<div class="checkbox-group pull-left pt8" >
					<input type="checkbox" name="isEnabled" id="isEnabled" {{? it.data.isEnabled == 1}} checked {{?}}>
					<label class="checkbox-2" for="isEnabled"></label>
				</div>
				<span class="pt8 ml10 mr20 pull-left">可用</span>
			</li>
		</ul>
		<div class="w340 pull-left ml20" id="allmap" style="width:340px;height:290px;margin-top:15px;"></div>
		<div class="clearfix"></div>
	</div>
	
	<div class="modal-footer" style="margin-top: 30px;">
		<button type="button" class="submit btn btn_green btn_160_36" btn-type='true'>提交</button>
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
</form>
</script>


<script id="pop_view_template" type="text/x-dot-template">
<form id="locationForm" method="POST">
	<div>
		<ul class="kaochang">
			<input type="hidden" id="locationId" value="{{=it.data.id}}" readonly="readonly">
			<li>
				<div class="pull-left text-right w100 pt10">考场名称：</div>
				<input id="locationName" type="text" value="{{=it.data.locationName}}" readonly="readonly" disabled="disabled" class="form-control w400" check-type="required" required-message="请输入考场名称！" placeholder="请输入考场名称" aria-describedby="basic-addon1" readonly="readonly" disabled="disabled">
			</li>
			<li>
				<div class="pull-left text-right w100 pt10">考场地点：</div>
				<div style="position: absolute; left: 100px; margin-top: -5px;" >
				<select  disabled="disabled" onchange="selectCountry()" name="dicCountry" id="select_country" data-width="100px" class="selectpicker ">
		           <option value="1" >中国</option>
		           <c:forEach items="${countryList}" var="item">
			          <option  <c:if test="${item.countryId ==countryVal}">selected="selected" </c:if> value="${item.countryId}">${item.name}</option>                  
		           </c:forEach>
		        </select>

	            <select disabled="disabled" id="select_province" name="dicProvince" onchange="selectProince()" data-width="100px" class="selectpicker ">
		           <option value="">省</option>
		             <c:forEach items="${provinceList}" var="item">
			            <option <c:if test="${item.provinceId ==provinceVal}">selected="selected" </c:if>  value="${item.provinceId}">${item.name}</option>              
		             </c:forEach>  
		        </select>
	           
	            <select disabled="disabled" id="city_select"  name="dicCity" onchange="selectCity()" data-width="100px" class="selectpicker ">
		 	       <option value="22">市</option>
		 	       <option value="23">市</option>

		        </select>
				</div>
			</li>
			<li class="rel">
				<div class="pull-left text-right w100 pt10">&nbsp;</div>
				<input id="locationAddress" readonly="readonly" disabled="disabled" type="text" value="{{=it.data.locationAddress}}" class="form-control w400" check-type="required" required-message="请输入考场地点！" placeholder="请输入考场地点" aria-describedby="basic-addon1" readonly="readonly">
				<span id="searchResult" style="display:none;color:red">* 没有搜索到结果，请重新输入搜索地址</span>
				<button type="button" onclick="mapCheck();">地图校验</button>
			</li>
			<li>
				<div class="pull-left text-right w100 pt8">考场简介：</div>
				<div class="r_con" style="height:100px;">
					<textarea  id="locationDesc" class="r_con" readonly="readonly" disabled="disabled" style="height:100px;border:1px solid #ccc;" name="" check-type="required" required-message="请输入考场简介！" placeholder="请输入考场简介" aria-describedby="basic-addon1" >{{=it.data.locationDesc}}</textarea>
				</div>
			</li>
			<li>
				<div class="pull-left text-right w100 pt8">&nbsp;</div>
				<div class="checkbox-group pull-left pt8" >
					<input type="checkbox" name="isEnabled" id="isEnabled" disabled="disabled" {{? it.data.isEnabled == 1}} checked {{?}}>
					<label class="checkbox-2" for="isEnabled"></label>
				</div>
				<span class="pt8 ml10 mr20 pull-left">可用</span>
			</li>
		</ul>
		<div class="w340 pull-left ml20" id="allmap" style="width:340px;height:290px;margin-top:15px;"></div>
		<div class="clearfix"></div>
	</div>
	
	<div class="modal-footer" style="margin-top: 30px;">
		<button type="button" class="btn btn_gray btn_160_36" data-dismiss="modal">取消</button>
	</div>
</form>
</script>

<%-- <%@ include file="/WEB-INF/views/commons/_dialogue.jsp" %> --%>
<%@ include file="/WEB-INF/views/commons/_pagination.jsp"%>
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript">
		require(['modules/location/location_list']);
	</script>


</body>
</html>