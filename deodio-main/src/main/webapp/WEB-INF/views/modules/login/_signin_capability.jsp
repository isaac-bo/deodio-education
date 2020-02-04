<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<form  method="post" id="capabilityInfoForm">
		<div class="base-info-item base-info-bg">
			<div class="form-inline" style="width:900px;">
				<h4>能力模型设置</h4>
				<div class="error-custom mt20">通过以下设置您的能力模型，具体能力模型主要分为2大类。</div>
				<div class="error-custom">
					<ul>
						<li>1. 知识工具: 根据您的能力模型，自定义知识工具的具体内容：例如：WEB前端应用程序开发 （HTML、HTML5、DOM、CSS、JS语言，JQuery、ExtJS、EasyUI前端框架）;</li>
						<li>2. 能力：该部分主要设置主关能力模型，为了保证平台能力分析模型，平台预先定义一些能力，您也可以根据自身需要设置不同的能力类型。</li>
					
					</ul>
				</div>
			</div>
		</div>
		
		<div class="base-info-item base-info-bg">
		
			<div class="form-inline">
				<div class="form-group">
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>能力级别前缀</label>
						 <input check-type="required"  value="${accountModel.capabilityPrefix}"
						type="text" name="capabilityPrefix" class="form-control h40">
					<span class="error-custom"></span> 
				</div>
				<div class="form-group">
					<label class="input-title input-title-color"><span
						class="input-title-span">＊</span>能力级别数量</label>
						 <input	check-type="number"  required-message="请填写1-5的数字!"
						  value="${accountModel.capabilityLevel}" data-callback="checkLevelNumber()" 
						  placeholder="请填写1-5的数字!" call-message="请填写1-5的数字!"
							type="text" name="capabilityLevel" id="capabilityLevel" class="form-control h40">
					<span class="error-custom"></span> 
				</div>

			</div>
		</div>
		

		<div class="base-info-item base-info-bg">
			<div class="form-inline">
				<div class="form-group">
					<label class="input-title input-title-color"><!-- <span class="input-title-span">＊</span> -->知识工具</label> 
					<div class="pull-left capability_table_content" id="knowledge_capability_panel">	
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem"  value=""  class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
						</div>
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem"  value=""  class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
						</div>
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem"  value=""  class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
						</div>
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem"  value=""  class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
						</div>
					</div>
				</div>

			</div>
		</div>



		<div class="base-info-item base-info-bg">
			<div class="form-inline">
				
				<div class="form-group">
					<label class="input-title input-title-color"><!-- <span class="input-title-span">＊</span> -->能力模型</label> 
					<div class="pull-left capability_table_content" id="ability_capability_panel">	
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem"  value=""  class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
						</div>
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem"  value=""  class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
						</div>
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem" value="" class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
						</div>
						<div class="ti">
							<div class="ti_input_w pull-left ml20">
								<input  type="text" name="capabilityItem" value="" class="form-control h36 br2">
							</div>
							<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,2)"></button>
							<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,2)"></button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<input type="hidden" name="userId" value="${uKeyId}">

		<div class="base-info-item base-info-bg">
			<div class="form-inline">
				<div class="form-group" style="margin: 0;">
					<button class="btn" type="button" btn-type="true">提交</button>
					<button class="btn cancel">取消</button>
				</div>
			</div>
		</div>
	</form>
	
	<script id="signin_data_template" type="text/x-dot-template">
			{{~it.data:v:index}}
					<div class="ti">
						<div class="ti_input_w pull-left ml20">
							<input type="text" id="{{=v.id}}" value="{{=v.capabilityItem}}" name="capabilityItem"  class="form-control h36 br2">
						</div>
						<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,{{=v.capabilityType}})"></button>
						<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,{{=v.capabilityType}})"></button>
					</div>
 			{{~}}	
	</script>
	
	<script id="signin_capability_data_template" type="text/x-dot-template">
		<div class="ti">
			<div class="ti_input_w pull-left ml20">
				<input id="{{=randomString(32)}}" type="text" name="capabilityItem" class="form-control h36 br2">
			</div>
			<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,{{=it.capabilityType}})"></button>
			<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,{{=it.capabilityType}})"></button>
		</div>
	</script>	
	<script type="text/javascript">
		require(['modules/login/_signin.capability'],function(){
			
		});
	</script>