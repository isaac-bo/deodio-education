<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/account/account.info.css">

<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
<div class="content">
 	<%@include file="/WEB-INF/views/modules/account/account_top_menu.jsp"%>

   <div class="content-item bradius2">
   	<div class="form-inline form-inline-none">
	    <a href="${ctx}/account/payment/subscribe.html?userId=${uKeyId}&step=1" class="sale-btn text-center mb20">购买／升级／降级</a>
	    <div class="choose-box">
	      <div><h6>订阅类型：</h6><span>第几种</span></div>
	      <div><h6>Next Billing Date：</h6><span>2016-12-15</span></div>
	      <div>
	        <h6>选项：</h6>
	        <div class="option-item" style="margin-top: -28px; margin-left: 50px;">
	          <div class="checkbox-item">
	            <div class="checkbox-group">
	              <input type="checkbox" name="remember" id="two">
	              <label class="checkbox-2" for="two"></label>
	            </div>
	            <div>selected</div>
	          </div>
	          <div class="checkbox-item">
	            <div class="checkbox-group">
	              <input type="checkbox" name="remember" id="five">
	              <label class="checkbox-2" for="five"></label>
	            </div>
	            <div>disabled selected-</div>
	          </div>
	          <div class="checkbox-item">
	            <div class="checkbox-group">
	              <input type="checkbox" name="remember" id="six">
	              <label class="checkbox-2" for="six"></label>
	            </div>
	            <div>disabled indeterminate-</div>
	          </div>
	          <div class="checkbox-item">
	            <div class="checkbox-group">
	              <input type="checkbox" name="remember" id="seven">
	              <label class="checkbox-2" for="seven"></label>
	            </div>
	            <div>A row without a checkbox</div>
	          </div>
	            
	        </div>
	      </div>
	      <div>
	        <h6>服务类型：</h6><br />
	        <h6>打折码：</h6><input class="sale-code" type="text" readonly="" value="xabcde">
	      </div>
	    </div>
    </div>
    <div style="display:block;" class="payment-history">
	    <div class="payment-date">
	      以下列出您在deodio（滴石）订阅的交易历史纪录，具体的细节和条件请参考deodio（滴石）提供的服务和条款。
	    </div>
	    <table class="table payment-table table-striped td_h60" cellpadding="0" cellspacing="0">
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
	            <div class="icons-img icons-26-24 print on"></div>
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
	            <div class="icons-img icons-26-24 print on"></div>
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
	            <div class="icons-img icons-26-24 print on"></div>
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
	            <div class="icons-img icons-26-24 print"></div>
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
	            <div class="icons-img icons-26-24 print"></div>
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
	            <div class="icons-img icons-26-24 print"></div>
	          </td>
	        </tr>
	      </tbody>
	    </table>
    </div>
   
  </div>
</div>
<input type="hidden" value="${uKeyId}" id="uKeyId">
<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>	

	<script type="text/javascript">
		require(['modules/account/account','modules/account/account_payment']);
	</script>

</body>
</html>