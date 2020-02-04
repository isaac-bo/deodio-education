<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/_taglib.jsp"%>
<%@ include file="/WEB-INF/views/commons/_meta.jsp"%>
<%@ include file="/WEB-INF/views/commons/_jslib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/course.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/modules/course/packages/package.css">
<%@ include file="/WEB-INF/views/commons/_header.jsp"%>
<body>
	<c:choose>
		<c:when test="${empty packageMap.packageBackgroundImg}">
			<div class="banner" id="banner" style="background: url('${ctx}/resources/img/course/packages/banner.jpg') center top no-repeat;"></div>
		</c:when>
		<c:otherwise>
			<div class="banner" id="banner" style="background: url('${imgStatic}${backgroundAttachment.attachUrl}${packageMap.packageBackgroundImg}') center top no-repeat;"></div>
		</c:otherwise>
	</c:choose>
	
		
	
	<div class="content p_border">
		<div class="jj_bjq pull-right" style="width: 100%; margin-bottom: 20px;">
			<div class="radio-group pull-left">
				<input type="radio" name="remember1" checked="checked" id="finalResut1" value="1"> 
				<label class="radio-2 checked" for="finalResut1"></label>
			</div> <span class="pt8 ml10 mr20 pull-left">强制逻辑(实线)</span>
			<div class="radio-group pull-left">
				<input type="radio" name="remember1" id="finalResut2" value="2"> 
				<label class="radio-2" for="finalResut2"></label>
			</div> <span class="pt8 ml10 mr20 pull-left">选择逻辑(虚线)</span>
		</div>
		<form id="coursePackageContentForm" method="post">
			<div class="left_show"></div>
			<div class="right_show"></div>
			<div class="table_list"></div>
			<input type="hidden" id="packageId" name="packageId" value="${packageMap.id}">
			<input type="hidden" id="packageSeries" name="packageSeries" value="${packageMap.packageSeries}">
			<div class="form_line2"></div>
			<div class="btn_box">
				<button class="btn submit btn_160_36" type="button" btn-type='true' btn-val='0'>保存</button>
				<button class="btn submit btn_160_36" type="button" btn-type='true' btn-val='1'>保存/发布</button>
				<button class="btn cancel btn_160_36" type="button">取消</button>
			</div>
			<canvas id="can" style="display:none;"></canvas>
		</form>
	</div>

	<script id="course_package_content_series_data_template" type="text/x-dot-template">
 	{{~it.data:v:index}}
	<div class="list_box color{{=index+1}}" style="width: {{=1198/(it.data.length) -25}}px; min-height:300px;">
		<input type="text" class="color{{=index+1}} title" value="{{=isNullFormat(v.seriesName)}}" name="seriesName">
		<input type="hidden" value="{{=index+1}}" name="seriesOrder">
		<input type="hidden" value="{{=setSeriesId(v.seriesId)}}" name="seriesId">
		<input type="hidden" value="{{=setOperateType(v.seriesId)}}" name="seriesOperateType">
		<ul class="list_con">
			{{~v.items:vi:vindex}}
				<li>
					<span class="l_point" name={{=isNullFormat(vi.itemId)}}></span>
					<input type="text" name="itemName" value="{{=isNullFormat(vi.itemName)}}" data-toggle='popover' class="color1">
					<span class="r_point" name={{=isNullFormat(vi.itemId)}}></span>
					<input type="hidden" name="itemId" value="{{=isNullFormat(vi.itemId)}}">
					<input type="hidden" name="itemOperateType" value="{{=setOperateType(vi.itemId)}}">
					<input type="hidden" value="{{=vindex+1}}" name="itemOrder">
					<input type="hidden" value="{{=isNullFormat(vi.elementsName)}}" name="elementsName">
					<div class="right_btn">
						<button type="button" class="btn add" onClick="showItemsElementsRel(this)">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
						<button type="button" class="btn minus" onClick="removeItems(this)">
							<span class="glyphicon glyphicon-minus"></span>
						</button>
					</div>
				</li>
			{{~}}
			<li class="add_btn">
				<button type="button" onClick="addItemButtonClick(this);"><span class="glyphicon glyphicon-plus"></span></button>
			</li>
		</ul>
	</div>
	{{~}}
	</script>
	
	<script id="course_package_content_items_data_template" type="text/x-dot-template">
		<li>
			<span class="l_point"></span>
			<input type="text" name="itemName" data-toggle='popover' class="color1" style="height: 29px; margin-top: 2px; position: absolute;">
			<span class="r_point"></span>
			<input type="hidden" name="itemId" value="{{=generateId()}}">
			<input type="hidden" name="itemOperateType" value="{{=setOperateType()}}">
			<input type="hidden" value="{{=it.itemOrder}}" name="itemOrder">
			<div class="right_btn">
				<button type="button" class="btn add" onClick="showItemsElementsRel(this)">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
				<button type="button" class="btn minus" onClick="removeItems(this)">
					<span class="glyphicon glyphicon-minus"></span>
				</button>
			</div>
		</li>
	</script>
	
	<%@ include file="/WEB-INF/views/modules/course/course_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/modules/group/group_dialogue.jsp"%>
	<%@ include file="/WEB-INF/views/commons/_footer.jsp"%>
	
	<script type="text/javascript">
		require([ 'modules/course/packages/packages_content' ], function(obj) {
		});
	</script>
</body>
</html>