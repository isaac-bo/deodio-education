<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <form id="capabilityForm">
    	<div class="form_bg">
			<div class="form-inline">
				<div class="form-group">
					<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力级别前缀</label> 
					<input type="text" id="prefixCapability" name="prefixCapability" value="${accountModel.capabilityPrefix}" class="form-control h40" check-type="required" required-message="请输入能力级别前缀">
					<div class="form-group-description error-custom"></div>
				</div>
				<div class="form-group">
					<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力级别数量</label> 
					<input type="text" id="numCapability" name="numCapability" check-type="number" min-max-num="1-5"  required-message="请填写1-5的数字！" value="${accountModel.capabilityLevel}" class="form-control"
					 data-callback="validateNumCapability();"  call-message="账户下已有小组设置能力模型,可删除小组的能力模型后更改!">
					<div class="form-group-description error-custom"></div>
				</div>
			</div>
			<div class="form-inline">
				<div class="form-group">
					<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力级别分布</label> 
					<div class="system-set">
			          <div class="radio-item" >
			            <div class="radio-group">
			              <input type="radio" <c:if test="${accountModel.capabilityLevelDistribution==1}">checked="checked"</c:if> name="capabilityLevelDistribution" id="capabilityLevelDistribution-1" value="1" <c:if test="${empty accountModel.capabilityLevelDistribution}">checked="checked"</c:if>>
			              <label class="radio-2" for="capabilityLevelDistribution-1"></label>
			            </div>
			            <div>平均分布</div>
			          </div>
			          <div class="radio-item" >
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
			          <div class="radio-item" >
			            <div class="radio-group">
			              <input type="radio" <c:if test="${accountModel.capabilityCalScore==1}">checked="checked"</c:if> name="capabilityCalScore" id="capabilityCalScore-1" value="1" <c:if test="${empty accountModel.capabilityCalScore}">checked="checked"</c:if>>
			              <label class="radio-2" for="capabilityCalScore-1" onclick="setParam(1);"></label>
			            </div>
			            <div>依据课件通过分数</div>
			          </div>
			          
			           <div class="radio-item" >
			            <div class="radio-group">
			              <input type="radio" <c:if test="${accountModel.capabilityCalScore==2}">checked="checked"</c:if> name="capabilityCalScore" id="capabilityCalScore-2" value="2">
			              <label class="radio-2" for="capabilityCalScore-2" onclick="setParam(2);"></label>
			              
			            </div>
			            <div>自定义起始分数</div>
			            <div class="r_con2">
							<input type="text" name="interval" id="capabilityCustomizeScore"  class="text form-control"  value="${accountModel.capabilityCustomizeScore}"  <c:if test="${accountModel.capabilityCalScore==2}"> check-type="number"   required-message="必填" </c:if> <c:if test="${accountModel.capabilityCalScore!=2}">readonly</c:if>>
						</div>
			          </div>
					</div>
				</div>
			</div>
			
			<div class="form-inline">
				
				<div class="c929292 f12">
					<p class="ml20">通过以下设置您的能力模型，具体能力模型主要分为2大类</p>
				<!-- 	<p class="ml20 mt20">1. 知识工具: 根据您的能力模型，自定义知识工具的具体内容：例如：WEB前端应用程序开发 （HTML、HTML5、DOM、CSS、JS语言，JQuery、ExtJS、EasyUI前端框架）; 该部分能力分为5级别：<p class="ml20 mt20">
					 -->
				</div>
			<!-- 	<div class="c929292 f12">
					
					<p class="ml20 mt20">2. 能力：该部分主要设置主关能力模型，为了保证平台能力分析模型，平台预先定义一些能力，您也可以根据自身需要设置不同的能力类型。</p>
				</div> -->
	
				<div class="form-group capability_table">
					<div class="pull-left capability_table_header"><label class="input-title input-title-color"><span class="input-title-span">＊</span><span class="icon-question-sign" data-toggle='popover'></span><input type="hidden" name="tipHidden" value="知识工具: 根据您的能力模型，自定义知识工具的具体内容：例如：WEB前端应用程序开发 （HTML、HTML5、DOM、CSS、JS语言，JQuery、ExtJS、EasyUI前端框架）; "/>&nbsp;知识工具</label></div>
					<div class="pull-right capability_table_content" id="knowledge_capability_panel">
					</div>
				</div>
				
				<div class="form-group capability_table">
					<div class="pull-left capability_table_header"><label class="input-title input-title-color"><span class="input-title-span">＊</span><span class="icon-question-sign" data-toggle='popover'></span><input type="hidden" name="tipHidden" value="能力：该部分主要设置主关能力模型，为了保证平台能力分析模型，平台预先定义一些能力，您也可以根据自身需要设置不同的能力类型。"/>&nbsp;能力</label> </div>
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
		        <button class="btn submit btn_160_36" btn-type="true" type="button">提交</button>
		        <!-- <button class="btn cancel btn_160_36" type="button" onclick="onCancel()">取消</button> -->
		      </div>
		    </div>
		</div>
</form>	    
<script id="knowledge_data_template" type="text/x-dot-template">
{{? it.data.length > 0 }}
	{{~it.data:v:index}}
	{{? v.capabilityType == 1}}
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input type="text" id="{{=v.id}}" value="{{=v.capabilityItem}}" class="form-control h36 br2" check-type="required" required-message="请输入知识工具" onfocus="changeStyle(this);">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
	</div>
	{{?}}
 	{{~}}	
{{??}}
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="知识工具1" class="form-control h36 br2" check-type="required" required-message="请输入知识工具" placeholder="请输入知识工具" onfocus="changeStyle(this);">
		</div>		
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="知识工具2" class="form-control h36 br2" check-type="required" required-message="请输入知识工具"  placeholder="请输入知识工具" onfocus="changeStyle(this);">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="知识工具3" class="form-control h36 br2" check-type="required" required-message="请输入知识工具"  placeholder="请输入知识工具" onfocus="changeStyle(this);">
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
			<input type="text" id="{{=v.id}}" value="{{=v.capabilityItem}}" class="form-control h36 br2" check-type="required" required-message="请输入能力"  placeholder="请输入能力" onfocus="changeStyle(this);">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>
	{{?}}
 	{{~}}	
{{??}}
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="能力1" class="form-control h36 br2" check-type="required" required-message="请输入能力" placeholder="请输入能力" onfocus="changeStyle(this);">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="能力2" class="form-control h36 br2" check-type="required" required-message="请输入能力" placeholder="请输入能力" onfocus="changeStyle(this);">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="能力3" class="form-control h36 br2" check-type="required" required-message="请输入能力" placeholder="请输入能力" onfocus="changeStyle(this);">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
	</div>

{{?}}
</script>
		
<script id="capability_item_data_template" type="text/x-dot-template">
	<div class="ti">
		<div class="ti_input_w pull-left ml20">
			<input id="{{=randomString(32)}}" type="text" value="能力模型" class="form-control h36 br2" check-type="required" required-message="请输入" placeholder="请输入" onfocus="changeStyle(this);">
		</div>
		<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,{{=it}})"></button>
		<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,{{=it}})"></button>
	</div>
</script>
		
		<script type="text/javascript">
			require(['modules/account/_account.capability.set'],function(){
				
			});
		</script>
