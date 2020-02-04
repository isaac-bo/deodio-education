<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.info.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
<div class="content">
	
  <%@ include file="/WEB-INF/views/modules/account/account_top_menu.jsp"%>
  
  <div class="content-item border-radius">
    <form id="infoForm">
		<div class="form-inline">
			<div class="form-group">
				<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力级别前缀</label> 
				<input type="text" id="prefixCapability" name="prefixCapability" value="${accountModel.capabilityPrefix}" class="form-control h40">
				<div class="form-group-description error-custom"></div>
			</div>
			<div class="form-group">
				<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力级别数量</label> 
				<input type="text" id="numCapability" name="numCapability" check-type="number" required-message="请填写1-10的数字！" value="${accountModel.capabilityLevel}" class="form-control">
				<div class="form-group-description error-custom"></div>
			</div>
		</div>
		<div class="form-inline">
			<div class="form-group">
				<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力级别分布</label> 
				<div class="system-set">
		          <div class="radio-item" style="display:block">
		            <div class="radio-group">
		              <input type="radio" <c:if test="${accountModel.capabilityLevelDistribution==1}">checked="checked"</c:if> name="capabilityLevelDistribution" id="capabilityLevelDistribution-1" value="1">
		              <label class="radio-2" for="capabilityLevelDistribution-1"></label>
		            </div>
		            <div>平均分布</div>
		          </div>
		          <div class="radio-item" style="display:block">
		            <div class="radio-group">
		              <input type="radio" <c:if test="${accountModel.capabilityLevelDistribution==2}">checked="checked"</c:if> name="capabilityLevelDistribution" id="capabilityLevelDistribution-2" value="2">
		              <label class="radio-2" for="capabilityLevelDistribution-2"></label>
		              
		            </div>
		            <div>正态分布</div>
		          </div>
				</div>
			</div>
			<div class="form-group">
				<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力级别起始分数</label> 
				<div class="system-set">
		          <div class="radio-item" style="display:block">
		            <div class="radio-group">
		              <input type="radio" <c:if test="${accountModel.capabilityCalScore==1}">checked="checked"</c:if> name="capabilityCalScore" id="capabilityCalScore-1" value="1">
		              <label class="radio-2" for="capabilityCalScore-1"></label>
		            </div>
		            <div>依据课件通过分数</div>
		          </div>
		          
		           <div class="radio-item" style="display:block">
		            <div class="radio-group">
		              <input type="radio" <c:if test="${accountModel.capabilityCalScore==2}">checked="checked"</c:if> name="capabilityCalScore" id="capabilityCalScore-2" value="2">
		              <label class="radio-2" for="capabilityCalScore-2"></label>
		              
		            </div>
		            <div>自定义起始分数</div>
		            <div class="r_con2">
						<input type="text" name="interval" id="capabilityCustomizeScore"  class="text form-control"  value="${accountModel.capabilityCustomizeScore}">
					</div>
		          </div>
				</div>
			</div>
		</div>
		
		<div class="form-inline">
			
			<div class="c929292 f12">
				<p class="ml20">通过以下设置您的能力模型，具体能力模型主要分为2大类</p>
				<p class="ml20 mt20">1. 知识工具: 根据您的能力模型，自定义知识工具的具体内容：例如：WEB前端应用程序开发 （HTML、HTML5、DOM、CSS、JS语言，JQuery、ExtJS、EasyUI前端框架）; 该部分能力分为5级别：<p class="ml20 mt20">
				
			</div>
			<div class="dengji">
	            <div class="dj_box bg_c1">
	                <h3>了解</h3>
	                <p>在指导下实践过相关知识，可以尝试独立操作</p>
	                <div><span class="glyphicon glyphicon-star"></span></div>
	            </div>
	            <div class="dj_box bg_c2">
	                <h3>熟悉</h3>
	                <p>无需指导，可以独立使用，完成常规性任务</p>
	                <div>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                </div>
	            </div>
	            <div class="dj_box bg_c3">
	                <h3>掌握</h3>
	                <p>掌握某一领域的知识和原理，能够触类旁通，成功完成大多数的任务</p>
	                <div>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                </div>
	            </div>
	            <div class="dj_box bg_c4">
	                <h3>深入掌握</h3>
	                <p>全面掌握相关知识和原理，能够融会贯通，灵活应用并指导其他人有效运用</p>
	                <div>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                </div>
	            </div>
	            <div class="dj_box bg_c5">
	                <h3>精通</h3>
	                <p>在某一领域或者多个领域可以给出专家级的意见，能指导其他人成功应用</p>
	                <div>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                    <span class="glyphicon glyphicon-star"></span>
	                </div>
	            </div>
	        </div>
			<div class="c929292 f12">
				
				<p class="ml20 mt20">2. 能力：该部分主要设置主关能力模型，为了保证平台能力分析模型，平台预先定义一些能力，您也可以根据自身需要设置不同的能力类型。</p>
			</div>

			<div class="form-group capability_table">
				<div class="pull-left capability_table_header"><label class="input-title input-title-color"><span class="input-title-span">＊</span>知识工具</label> </div>
				<div class="pull-right capability_table_content" id="knowledge_capability_panel">
				</div>
			</div>
			
			<div class="form-group capability_table">
				<div class="pull-left capability_table_header"><label class="input-title input-title-color"><span class="input-title-span">＊</span>能力</label> </div>
				<div class="pull-right capability_table_content" id="ability_capability_panel">
					<!-- <div class="ti">
						<div class="ti_input_w pull-left ml20">
							<span class="form-control capability_span h36 br2">资源整合</span>
						</div>
					</div>
				</div> -->
			</div>
			</div>
		</div>
	
	    <div class="form-inline last-child">
	      <div class="form-group" style="margin:0;">
	        <button class="btn submit btn_160_36" btn-type="true" type="button" onclick="onSubmit()">提交</button>
	       <!--  <button class="btn cancel btn_160_36" type="button">取消</button> -->
	      </div>
	    </div>
   		 <input value="${uKeyId}" type="hidden" id="uKeyId" name="uKeyId">
    </form>
  </div>
</div>

<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

<script id="knowledge_data_template" type="text/x-dot-template">

{{? it.data.length > 0 }}
	{{~it.data:v:index}}
	{{? v.capabilityType == 1}}
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input type="text" id="{{=v.id}}" value="{{=v.capabilityItem}}" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
	</div>
	{{?}}
 	{{~}}	
{{??}}
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
	</div>

{{?}}
</script>


<script id="ability_data_template" type="text/x-dot-template">
{{? it.data.length > 0 }}
	{{~it.data:v:index}}
	{{? v.capabilityType == 2 }}
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input type="text" id="{{=v.id}}" value="{{=v.capabilityItem}}" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>
	{{?}}
 	{{~}}	
{{??}}
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>

{{?}}
</script>

<script id="capability_item_data_template" type="text/x-dot-template">

	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="" class="form-control h36 br2">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,{{=it}})"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,{{=it}})"></button>
	</div>

</script>

<script type="text/javascript">
	require(['modules/account/account','modules/account/account_capability'],function(){
		
	});
</script>


</body>
</html>