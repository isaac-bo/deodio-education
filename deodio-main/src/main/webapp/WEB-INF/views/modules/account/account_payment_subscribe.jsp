<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.info.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<div class="content">
		<%@include file="/WEB-INF/views/modules/account/account_top_menu.jsp"%>
		<c:choose>
			<c:when test="${step==1}">
				<div class="content-item bradius2">
					<div class="nav-pay-list">
						<div class="price-img pay-step-1 on">Select Your Plan</div>
						<div class="price-img pay-step-2">Enter Payment Info</div>
						<div class="price-img pay-step-3">Confirmation</div>
					</div>
					<p>花费您的一些时间，请选择相应的计划等。</p>
					<div class="pay-price-list">
						<div class="pay-price-item">
							<div>Price</div>
							<div>Hours/mo.</div>
							<div>Users</div>
							<div>Group & Virtual</div>
							<div>Easy & Fast Content</div>
							<div>Multiple Publishing</div>
						</div>
						<div class="pay-price-item">
							<div class="price-img price-list-1">
								<button type="button">chose plus</button>
							</div>
							<div>50hrs</div>
							<div>Unlimited</div>
							<div>Unlimited</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
						</div>
						<div class="pay-price-item">
							<div class="price-img price-list-2">
								<button type="button"
									onclick="javascrtpt:go2Page('/account/payment/subscribe.html','userId=${uKeyId}&step=2')">chose
									plus</button>
							</div>
							<div>50hrs</div>
							<div>Unlimited</div>
							<div>Unlimited</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
						</div>
						<div class="pay-price-item">
							<div class="price-img price-list-3">
								<button type="button"
									onclick="javascrtpt:go2Page('/account/payment/subscribe.html','userId=${uKeyId}&step=2')">chose
									plus</button>
							</div>
							<div>50hrs</div>
							<div>Unlimited</div>
							<div>Unlimited</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
						</div>
						<div class="pay-price-item">
							<div class="price-img price-list-4">
								<button type="button"
									onclick="javascrtpt:go2Page('/account/payment/subscribe.html','userId=${uKeyId}&step=2')">chose
									plus</button>
							</div>
							<div>50hrs</div>
							<div>Unlimited</div>
							<div>Unlimited</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
						</div>
						<div class="pay-price-item">
							<div class="price-img price-list-5">
								<button type="button"
									onclick="javascrtpt:go2Page('/account/payment/subscribe.html','userId=${uKeyId}&step=2')">chose
									plus</button>
							</div>
							<div>50hrs</div>
							<div>Unlimited</div>
							<div>Unlimited</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
							<div>
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							</div>
						</div>
					</div>

				</div>
			</c:when>
			<c:when test="${step==2}">
				<div class="content-item border-radius">
					<div class="nav-pay-list">
						<div class="price-img pay-step-1">Select Your Plan</div>
						<div class="price-img pay-step-2 on">Enter Payment Info</div>
						<div class="price-img pay-step-3">Confirmation</div>
					</div>
					<h1 class="account-pay-title">Biuing information</h1>
					<form class="form-inline">
						<div class="form-group">
							<label class="input-title input-title-color"><span
								class="input-title-span">＊</span>Services Plan</label> <input
								type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color"><span
								class="input-title-span">＊</span>Diacount Code</label> <input
								type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color"><span
								class="input-title-span">＊</span>Payment Options</label>
							<div class="pay-way">
								<div>
									<div class="radio-item">
										<div class="radio-group">
											<input type="radio" name="pay-way" id="paypal" value="paypal">
											<label class="radio-2" for="paypal"></label>
										</div>
										<div class="price-img price-img-border paypal" style="width: 70px; height: 30px;"></div>
									</div>
								</div>
								<div>
									<div class="radio-item">
										<div class="radio-group">
											<input type="radio" name="pay-way" id="alipay" value="alipay">
											<label class="radio-2" for="alipay"></label>
										</div>
										<div class="price-img price-img-border alipay" style="width: 70px; height: 30px;"></div>
									</div>
								</div>
								<div>
									<div class="radio-item">
										<div class="radio-group">
											<input type="radio" name="pay-way" id="visa" value="visa">
											<label class="radio-2" for="visa"></label>
										</div>
										<div class="price-img price-img-border visa" style="width: 70px; height: 30px;"></div>
									</div>
									<div class="radio-item">
										<div class="radio-group">
											<input type="radio" name="pay-way" id="visa-electron"
												value="visa-electron"> <label class="radio-2"
												for="visa-electron"></label>
										</div>
										<div class="price-img price-img-border visa" style="width: 70px; height: 30px;"></div>
									</div>
									<div class="radio-item">
										<div class="radio-group">
											<input type="radio" name="pay-way" id="discover"
												value="discover"> <label class="radio-2"
												for="discover"></label>
										</div>
										<div class="price-img price-img-border discover" style="width: 70px; height: 30px;"></div>
									</div>
									<div class="radio-item">
										<div class="radio-group">
											<input type="radio" name="pay-way" id="mastercard"
												value="mastercard"> <label class="radio-2"
												for="mastercard"></label>
										</div>
										<div class="price-img price-img-border mastercard" style="width: 70px; height: 30px;"></div>
									</div>
									<div class="radio-item">
										<div class="radio-group">
											<input type="radio" name="pay-way" id="unionpay"
												value="unionpay"> <label class="radio-2"
												for="unionpay"></label>
										</div>
										<div class="price-img price-img-border unionpay" style="width: 70px; height: 30px;"></div>
									</div>
								</div>

							</div>


						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Card Number</label>
							<input type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Explry
								Date(MM)</label>
							<div class="ul-select">
								<div class="nice-select" name="nice-select">
									<input type="text" value="Select" readonly> <span
										class="select-down"></span>
									<ul>
										<li data-value="1">中国</li>
										<li data-value="2">美国</li>
										<li data-value="3">日本</li>
										<li data-value="4">韩国</li>
										<li data-value="5">大不列颠及北爱尔兰联合王国</li>
										<li data-value="6">澳大利亚</li>
										<li data-value="7">俄罗斯</li>
									</ul>
								</div>
								<div class="nice-select" name="nice-select">
									<input type="text" value="Select" readonly> <span
										class="select-down"></span>
									<ul>
										<li data-value="1">辽宁省</li>
										<li data-value="2">吉林省</li>
										<li data-value="3">黑龙江省</li>
										<li data-value="4">河北省</li>
										<li data-value="5">河南省</li>
										<li data-value="6">湖北省</li>
										<li data-value="7">湖南省</li>
									</ul>
								</div>

							</div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">CVV</label> <input
								type="text" class="form-control">
							<div class="form-group-description">
								<h1 class="cvv" onmouseover="javascript:onMouseoverCVV()" onmouseout="javascript:onMouseoutCVV()"><span class="icon-question-sign">&nbsp;</span>什么是CVV?</h1>
								<div class="price-img cvv-tip"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Name On Card</label>
							<input type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Country</label>
							<div class="ul-select">
								<div class="nice-select" name="nice-select">
									<input type="text" value="Country" readonly> <span
										class="select-down"></span>
									<ul>
										<li data-value="1">中国</li>
										<li data-value="2">美国</li>
										<li data-value="3">日本</li>
										<li data-value="4">韩国</li>
										<li data-value="5">大不列颠及北爱尔兰联合王国</li>
										<li data-value="6">澳大利亚</li>
										<li data-value="7">俄罗斯</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Address1</label> <input
								type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Address2</label> <input
								type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">City</label> <input
								type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Starte</label> <input
								type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Postal Code</label>
							<input type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Contact
								Phone Number</label> <input type="text" class="form-control">
						</div>
						<div class="form-group">
							<label class="input-title input-title-color">Email
								Address</label> <input type="text" class="form-control">
						</div>
					</form>
					<form class="form-inline last-child">
						<div class="form-group" style="margin: 0;">
							<button class="btn submit btn_160_36" type="button" onclick="javascrtpt:go2Page('/account/payment/subscribe.html','userId=${uKeyId}&step=3')">提交</button>
							<button class="btn cancel btn_160_36" type="button" onclick="javascrtpt:go2Page('/account/payment/subscribe.html','userId=${uKeyId}&step=1')">取消</button>
						</div>
					</form>
				</div>
			</c:when>
			<c:when test="${step==3}">
				<div class="content-item bradius2">
					<div class="nav-pay-list">
						<div class="price-img pay-step-1">Select Your Plan</div>
						<div class="price-img pay-step-2">Enter Payment Info</div>
						<div class="price-img pay-step-3 on">Confirmation</div>
					</div>
					<div class="payment-info">
						<table>
							<tbody>
								<tr>
									<td>
										<h6>Date:</h6> <span>Sep 14.2010</span>
									</td>
									<td>
										<h6>Transaction:</h6> <span>TS100203</span>
									</td>
								</tr>
								<tr>
									<td>
										<h6>Billing Information:</h6>
									</td>
									<td>
										<h6>Payment Methed:</h6> <span>AmexAmex</span>
									</td>
								</tr>
								<tr>
									<td>
										<h6></h6> <span>Joe Kith</span>
									</td>
									<td>
										<h6>Credit Card:</h6> <span>Amex</span>
									</td>
								</tr>
								<tr>
									<td>
										<h6></h6> <span>111 Row house st</span>
									</td>
									<td>
										<h6>Name on Card:</h6> <span>James Kith</span>
									</td>
								</tr>
								<tr>
									<td>
										<h6></h6> <span>Flower City,CAc94112</span>
									</td>
									<td>
										<h6>Gredit Card Number:</h6> <span>####1122</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>

					<table class="table payment-table table-striped td_h60"
						cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th width="50%">项目</th>
								<th width="25%">数量</th>
								<th width="25%">价格</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Giacomo Guilizzoni</td>
								<td>37</td>
								<td>49.9</td>
							</tr>
							<tr>
								<td>Giacomo Guilizzoni</td>
								<td>37</td>
								<td>49.9</td>
							</tr>
							<tr>
								<td>Giacomo Guilizzoni</td>
								<td>37</td>
								<td>49.9</td>
							</tr>
							<tr>
								<td>Giacomo Guilizzoni</td>
								<td>37</td>
								<td>49.9</td>
							</tr>
							<tr>
								<td>Giacomo Guilizzoni</td>
								<td>37</td>
								<td>49.9</td>
							</tr>
							<tr>
								<td>Giacomo Guilizzoni</td>
								<td>37</td>
								<td>49.9</td>
							</tr>
							<tr>
								<td>Giacomo Guilizzoni</td>
								<td>37</td>
								<td>49.9</td>
							</tr>
						</tbody>
					</table>

					<form class="form-inline last-child">
						<div class="form-group" style="margin: 0;">
							<button class="btn submit btn_160_36" type="button">打印</button>
							<button class="btn cancel btn_160_36" type="button" onclick="javascrtpt:go2Page('/account/payment/subscribe.html','userId=${uKeyId}&step=2')">返回</button>
						</div>
					</form>

				</div>

			</c:when>
		</c:choose>
	</div>
	</div>
	<input type="hidden" value="${uKeyId}" id="uKeyId">
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>

	<script type="text/javascript"> 
		require(['modules/account/account','modules/account/account_payment_subscribe']);
	</script>

</body>
</html>