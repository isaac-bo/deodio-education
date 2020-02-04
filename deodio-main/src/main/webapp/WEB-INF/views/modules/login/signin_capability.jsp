<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/deodio.style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/login/signin.detail.css">

<body>
	<div class="position">
		<div class="header"></div>
		<nav class="nav text-center cfff nav-bg">
			<div class="top">
				<div class="logo pull-left">
					<img src="${ctx}/resources/img/account/logo-2.png" alt="">
				</div>
				<div class="setup-tabnav pull-right">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation"><a href="#home"
							role="tab" data-toggle="tab"> 详细信息登记</a></li>
						<li class="active" role="presentation"><a href="#profile" role="tab"
							data-toggle="tab">能力模型设置</a></li>
						<li role="presentation"><a href="#messages" role="tab"
							data-toggle="tab">订单方案</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="content" style="background: inherit;">
		<form action="${ctx}/signin/enterprise/detail.html" method="post" id="userInfoForm">
		
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
							class="input-title-span">＊</span>能力级别前缀</label> <input
							type="text" name="companyName" class="form-control h40">

					</div>
					<div class="form-group">
						<label class="input-title input-title-color"><span
							class="input-title-span">＊</span>能力级别数量</label> <input
							type="text" name="companyTel" class="form-control h40">
					</div>

				</div>
			</div>
			

			<div class="base-info-item base-info-bg">
				<div class="form-inline">
					<div class="form-group">
						<label class="input-title input-title-color"><span class="input-title-span">＊</span>知识工具</label> 
						<div class="pull-left capability_table_content" id="knowledge_capability_panel">	
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
								</div>
								<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
								<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
							</div>
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
								</div>
								<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
								<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
							</div>
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
								</div>
								<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
								<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
							</div>
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
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
						<label class="input-title input-title-color"><span class="input-title-span">＊</span>能力模型</label> 
						<div class="pull-left capability_table_content" id="knowledge_capability_panel">	
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
								</div>
								<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
								<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
							</div>
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
								</div>
								<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
								<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
							</div>
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
								</div>
								<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
								<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
							</div>
							<div class="ti">
								<div class="ti_input_w pull-left ml20">
									<input  type="text" value="" class="form-control h36 br2">
								</div>
								<button type="button" class="btn_del_18 pull-left mt10 ml10" onclick="onRemoveCapabilityItem(this,1)"></button>
								<button type="button" class="btn_add_18 pull-left mt10 ml10" onclick="onAddCapabilityItem(this,1)"></button>
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

	</div>



	<div class="footer">
		 <%@ include file="/WEB-INF/views/commons/_footer_content.jsp"%>
	</div>

	<script type="text/javascript">
		require(['modules/login/signin.detail'],function(){
			
		});
	</script>

</body>
</html>