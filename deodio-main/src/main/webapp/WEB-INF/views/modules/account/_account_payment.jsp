<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="content-item bradius2" id="step">
	<button type="button" onclick="toPaySubStep1Page();" class="sale-btn text-center mb20">购买／升级／降级</button>
	<div class="choose-box">
		<div>
			<h6>订阅类型：</h6>
			<span>第几种</span>
		</div>
		<div>
			<h6>Next Billing Date：</h6>
			<span>Dec 19,2015</span>
		</div>
		<div>
			<h6>选项：</h6>
			<div class="option-item">
				<div class="checkbox-item">
					<div class="checkbox-group">
						<input type="checkbox" name="remember" id="one"> 
						<label class="checkbox-3 on" for="one"></label>
					</div>
					<div>not selected</div>
				</div>
				<div class="checkbox-item">
					<div class="checkbox-group">
						<input type="checkbox" name="remember" id="two">
					    <label class="checkbox-3" for="two"></label>
					</div>
					<div>selected</div>
				</div>
				<div class="checkbox-item">
					<div class="checkbox-group">
						<input type="checkbox" name="remember" id="three">
					    <label class="checkbox-3 on" for="three"></label>
					</div>
					<div>indeterminate</div>
				</div>
				<div class="checkbox-item">
					<div class="checkbox-group">
						<input type="checkbox" name="remember" id="four"> 
						<label class="checkbox-3 on" for="four"></label>
					</div>
					<div>disabled-</div>
				</div>
				<div class="checkbox-item">
					<div class="checkbox-group">
						<input type="checkbox" name="remember" id="five"> 
						<label class="checkbox-3" for="five"></label>
					</div>
					<div>disabled selected-</div>
				</div>
				<div class="checkbox-item">
					<div class="checkbox-group">
						<input type="checkbox" name="remember" id="six"> 
						<label class="checkbox-3" for="six"></label>
					</div>
					<div>disabled indeterminate-</div>
				</div>
				<div class="checkbox-item">
					<div class="checkbox-group">
						<input type="checkbox" name="remember" id="seven"> 
						<label class="checkbox-3" for="seven"></label>
					</div>
					<div>A row without a checkbox</div>
				</div>

			</div>
		</div>
		<div>
			<h6>服务类型：</h6>
			<br />
			<h6>打折码：</h6>
			<input class="sale-code" type="text" readonly="" value="xabcde">
		</div>
	</div>
	<div class="payment-date">说明文字说明文字说明文字说明文字</div>
	<table class="table payment-table table-striped td_h60" cellpadding="0"
		cellspacing="0">
		<thead>
			<tr>
				<th>Payment Date</th>
				<th>类型</th>
				<th>Amout Due</th>
				<th>Paid Amount</th>
				<th>Staus</th>
				<th>Description</th>
				<th>Statement</th>
				<th>发票</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>2015-12-16</td>
				<td>37</td>
				<td>16</td>
				<td>12</td>
				<td>03</td>
				<td>12</td>
				<td>12</td>
				<td>
					<div class="icon-img icon-26-24 print on"></div>
				</td>
			</tr>
			<tr>
				<td>2015-12-16</td>
				<td>37</td>
				<td>16</td>
				<td>12</td>
				<td>03</td>
				<td>12</td>
				<td>12</td>
				<td>
					<div class="icon-img icon-26-24 print on"></div>
				</td>
			</tr>
			<tr>
				<td>2015-12-16</td>
				<td>37</td>
				<td>16</td>
				<td>12</td>
				<td>03</td>
				<td>12</td>
				<td>12</td>
				<td>
					<div class="icon-img icon-26-24 print on"></div>
				</td>
			</tr>
			<tr>
				<td>2015-12-16</td>
				<td>37</td>
				<td>16</td>
				<td>12</td>
				<td>03</td>
				<td>12</td>
				<td>12</td>
				<td>
					<div class="icon-img icon-26-24 print"></div>
				</td>
			</tr>
			<tr>
				<td>2015-12-16</td>
				<td>37</td>
				<td>16</td>
				<td>12</td>
				<td>03</td>
				<td>12</td>
				<td>12</td>
				<td>
					<div class="icon-img icon-26-24 print"></div>
				</td>
			</tr>
			<tr>
				<td>2015-12-16</td>
				<td>37</td>
				<td>16</td>
				<td>12</td>
				<td>03</td>
				<td>12</td>
				<td>12</td>
				<td>
					<div class="icon-img icon-26-24 print"></div>
				</td>
			</tr>
		</tbody>
	</table>
</div>


<div class="content-item bradius2" id="step1">

	<div class="nav-pay-list">
		<div class="price-img pay-step-1 on">Select Your Plan</div>
		<div class="price-img pay-step-2">Enter Payment Info</div>
		<div class="price-img pay-step-3">Confirmation</div>
	</div>
	<p>花费您的一些时间，请选择相应的计划等。</p>
	<div class="pay-price-list">
		<div class="pay-price-item">
			<div class="price-img price-list-1">
				<button type="button" onclick="toPaySubStep2Page();">choose plus</button>
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
				<button type="button" onclick="toPaySubStep2Page();">choose plus</button>
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
				<button type="button" onclick="toPaySubStep2Page();">choose plus</button>
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
				<button type="button" onclick="toPaySubStep2Page();">choose plus</button>
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
				<button type="button" onclick="toPaySubStep2Page();">choose plus</button>
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

<div class="content-item bradius2" id="step2">
	<div class="nav-pay-list">
		<div class="price-img pay-step-1">Select Your Plan</div>
		<div class="price-img pay-step-2 on">Enter Payment Info</div>
		<div class="price-img pay-step-3">Confirmation</div>
	</div>
	<h1 class="account-pay-title">Biuing information</h1>
	<form class="form-inline">
		<div class="form-group">
			<label class="input-title input-title-color"><span
				class="input-title-span">＊</span>Services Plan</label><input type="text"
				class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color"><span
				class="input-title-span">＊</span>Diacount Code</label><input type="text"
				class="form-control">
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
						<div class="price-img paypal"></div>
					</div>
				</div>
				<div>
					<div class="radio-item">
						<div class="radio-group">
							<input type="radio" name="pay-way" id="alipay" value="alipay">
							<label class="radio-2" for="alipay"></label>
						</div>
						<div class="price-img alipay"></div>
					</div>
				</div>
				<div>
					<div class="radio-item">
						<div class="radio-group">
							<input type="radio" name="pay-way" id="visa" value="visa">
							<label class="radio-2" for="visa"></label>
						</div>
						<div class="price-img visa"></div>
					</div>
					<div class="radio-item">
						<div class="radio-group">
							<input type="radio" name="pay-way" id="visa-electron"
								value="visa-electron"> <label class="radio-2"
								for="visa-electron"></label>
						</div>
						<div class="price-img visa"></div>
					</div>
					<div class="radio-item">
						<div class="radio-group">
							<input type="radio" name="pay-way" id="discover" value="discover">
							<label class="radio-2" for="discover"></label>
						</div>
						<div class="price-img discover"></div>
					</div>
					<div class="radio-item">
						<div class="radio-group">
							<input type="radio" name="pay-way" id="mastercard"
								value="mastercard"> <label class="radio-2"
								for="mastercard"></label>
						</div>
						<div class="price-img mastercard"></div>
					</div>
					<div class="radio-item">
						<div class="radio-group">
							<input type="radio" name="pay-way" id="unionpay" value="unionpay">
							<label class="radio-2" for="unionpay"></label>
						</div>
						<div class="price-img unionpay"></div>
					</div>
				</div>

			</div>


		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Card Number</label><input
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Explry Date(MM)</label>
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
			<label class="input-title input-title-color">CVV</label><input
				type="text" class="form-control">
			<div class="form-group-description">
				<h1 class="cvv" onmouseover="javascript:onMouseoverCVV()" onmouseout="javascript:onMouseoutCVV()">什么是CVV?</h1>
				<div class="price-img cvv-tip"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Name On Card</label><input
				type="text" class="form-control">
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
			<label class="input-title input-title-color">Address1</label><input
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Address2</label><input
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">City</label><input
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Starte</label><input
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Postal Code</label><input
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Contact Phone
				Number</label><input type="text" class="form-control">
		</div>
		<div class="form-group">
			<label class="input-title input-title-color">Email Address</label><input
				type="text" class="form-control">
		</div>
	</form>
	<form class="form-inline">
		<div class="form-group" style="margin: 0;">
			<button class="btn submit btn_160_36" type="button" onclick="toPaySubStep3Page();">提交</button>
			<button class="btn cancel btn_160_36" type="button" onclick="toPaySubStep1Page();">取消</button>
		</div>
	</form>

</div>


<div class="content-item bradius2" id="step3">
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
						<h6>Date:</h6>
						<span>Sep 14.2010</span>
					</td>
					<td>
						<h6>Transaction:</h6>
						<span>TS100203</span>
					</td>
				</tr>
				<tr>
					<td>
						<h6>Billing Information:</h6>
					</td>
					<td>
						<h6>Payment Methed:</h6>
						<span>AmexAmex</span>
					</td>
				</tr>
				<tr>
					<td>
						<h6></h6>
						<span>Joe Kith</span>
					</td>
					<td>
						<h6>Credit Card:</h6>
						<span>Amex</span>
					</td>
				</tr>
				<tr>
					<td>
						<h6></h6>
						<span>111 Row house st</span>
					</td>
					<td>
						<h6>Name on Card:</h6>
						<span>James Kith</span>
					</td>
				</tr>
				<tr>
					<td>
						<h6></h6>
						<span>Flower City,CAc94112</span>
					</td>
					<td>
						<h6>Gredit Card Number:</h6>
						<span>####1122</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<table class="table payment-table table-striped" cellspacing="0"
		cellpadding="0">
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

	<form class="form-inline">
		<div class="form-group" style="margin: 0;">
			<button class="btn submit btn_160_36" type="button">打印</button>
			<button class="btn cancel btn_160_36" type="button" onclick="toPaySubStep2Page();">返回</button>
		</div>
	</form>

</div>


<script type="text/javascript">
	require([ 'modules/account/_account_payment' ], function() {

	});
</script>
