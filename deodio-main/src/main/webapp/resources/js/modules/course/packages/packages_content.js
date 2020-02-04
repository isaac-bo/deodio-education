define(["jquery","utils.cookie","jquery.dot","libs/drawing/drawLine","utils","jquery.base"], function($,cookie,doT,drawing) {
		
	var delFlg = '2';
	var insertFlg = '0';
	var updateFlg = '1';
	
	var currentOperate ="";
	var showItemsElements = "showItemsElements";
	var currentItemId = "";
		
	//重置页面参数
	resetPageParams = function(){
		currentOperate = "";
		currentItemId ="";
	}
	
	var init = function(){
		
		customInput("remember1");
		
		//点击背景，弹出更换背景图片modal
		$("#banner").click(function(){
			 $("#uploadAttachmentModal").modal('show');
		});
		
		//form提交验证
		$("#coursePackageContentForm").myValidate({
			formCall : function(data) {
				if(data == 0)
					submitCoursePackageContent();
				else 
					publishCoursePackageContent();
			},
			isAlert : false,
			formKey : false,
			errorCustom : {
				customFlag : true,
				regionText : true
			},
			errorStyle : {
				errorRegion : "error-div",
				errorClass : "wrong-item",
				rightClass : "right-item"
			}
		});
		
		//加载课程包内容
		loadCoursePackageContent();
	};
	
	
	//popover 设置
	enabledPopover = function(){
		$('[data-toggle="popover"]').each(function () {
			var element = $(this);
//			var id = element.attr('id');
//			var txt = element.html();
			var elementsName = $(this).parent().find("[name='elementsName']").val();
			if(elementsName != undefined && elementsName != ''){
				var elementArr = elementsName.split(",");
				var formatEelementsName = '<ul>';
				for(i=0; i<elementArr.length;i++){
					formatEelementsName += '<li> <div class="pull-left" style="height:39px;width:300px;"><span class="icon-caret-right" >&nbsp;</span>'
							+ elementArr[i]
							+ '</div><div class="pull-right"><span class="icon-trash" style="color:#ff0000;"></span></div></li>';
				}
				formatEelementsName += '</ul>';
				element.popover({
					container:"body",
					trigger: 'manual',
					placement: 'right', //top, bottom, left or right
					title: "已经选择课程",
					html: 'true',
					content: formatEelementsName
				}).on("mouseenter", function () {
	//				debugger;
					var drawFlg = drawing.getDrawFlg();
					if(!drawFlg){
						var _this = this;
						$(this).popover("show");
						$(this).siblings(".popover").on("mouseleave", function () {
							$(_this).popover('hide');
						});
					}
				}).on("mouseleave", function () {
					var _this = this;
					var drawFlg = drawing.getDrawFlg();
					if(!drawFlg){
						setTimeout(function () {
							if (!$(".popover:hover").length) {
								$(_this).popover("hide")
							}
						}, 100);
					}
				}).on("mouseout", function () {
					var _this = this;
					var drawFlg = drawing.getDrawFlg();
					if(!drawFlg){
						setTimeout(function () {
							if (!$(".popover:hover").length) {
								$(_this).popover("hide")
							}
						}, 100);
					}
				});
			}
		});
	}
	
	//获取页面操作， 编辑背景图片或者管理课程包item关联的课程
	setModalState = function(){
		if(currentOperate == showItemsElements){
			return {
				targetId:currentItemId
			};
		}else{		
			return {
				targetType:'6',
				targetId:$("#packageId").val()
			};
		}
	}
	
	//弹出框回调函数
	courseDialogCallBackFun = function(dataJson){
		//无数据时返回
		if(!dataJson){
			return;
		}
		//课程包元素弹出框
		if(currentOperate == showItemsElements){
			var url="/course/packages/content/elements/submit_data.html",data={
				elementsRelJson :dataJson
			};
			//更新课程包背景图
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					alertMsg("课程包元素设置成功！",function(){
						//refresh
						$('#coursePackageItemsElementsModal').modal('hide');
						submitCoursePackageContent();
					});
				}else{
					alertMsg("课程包元素设置失败！");
				}
			});
			return;
		}
		//课程包背景图片弹出框回调
		var dataObj = JSON.parse(dataJson);
		if(!$.isEmptyObject(dataObj)){
//			debugger;
			var attachId = dataObj.attachId;
			var attachName = dataObj.attachName;
			var attachUrl = dataObj.attachUrl;
			
			var packageInfo = {
				packageBackgroundImg : attachName,
				id : $.trim($("#packageId").val())
			};
			var url="/course/packages/submit_profile.html",data={
				packageInfoJson :JSON.stringify(packageInfo),
				attachId : $.trim(attachId)
			};
			//更新课程包背景图
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					$("#uploadAttachmentModal").modal('hide');
					//更新页面显示的背景图   ajax
					var backgroundUrl = imgStatic + attachUrl + attachName;
					$("#banner").attr("style","background: url('" + backgroundUrl + "') center top no-repeat;")
				}else{
					alertMsg("课程包背景图设置失败！");
				}
			});
		}
		
		//重置状态
		resetPageParams();
	}
	
	//加载课程包数据
	loadCoursePackageContent = function(){
		var params = {
			packageId:$("#packageId").val(),
		},url="/course/packages/content/load_data.html";
		postAjaxRequest(url, params, function(result){
			if(result.status == 1){
				var packageSeriesInt = Number($("#packageSeries").val());
				//获取课程包数据
				var coursePackage = result.data;
				//获取课程包列数据
				var seriesObject = convertSeriesArrayToObject(coursePackage.series);
				var dataObj = setDataObj(packageSeriesInt,seriesObject);
				var template = doT.template($("#course_package_content_series_data_template").text());
				$('.table_list').html(template(dataObj));
				enabledPopover();
				//初始化画布
				drawing.initDraw(coursePackage.itemsRel);
			}
		});
	}
	
	//提交课程包数据
	submitCoursePackageContent = function(){
		var packageData = getPackageData();
		var courseId = $("#courseId").val();
		var url = "/course/packages/content/submit.html", data = {
				packageId:$("#packageId").val(),
				seriesJson : JSON.stringify(packageData.seriesDataArray),
				itemsJson :	JSON.stringify(packageData.itemsDataArray),
				itemsRelJson : JSON.stringify(packageData.itemsRelDataArray)
		};
		postAjaxRequest(url, data, function(result) {
			if(result.status == 1){
				alertMsg("保存成功！");
			}else{
				alertMsg("设置失败！");
			}
		});
	};
	
	publishCoursePackageContent = function(){
		var packageData = getPackageData();
		var courseId = $("#courseId").val();
		var url = "/course/packages/content/submit.html", data = {
				packageId:$("#packageId").val(),
				seriesJson : JSON.stringify(packageData.seriesDataArray),
				itemsJson :	JSON.stringify(packageData.itemsDataArray),
				itemsRelJson : JSON.stringify(packageData.itemsRelDataArray)
		};
		
		postAjaxRequest(url, data, function(result) {
			if(result.status == 1){
				coursePackagePublish();
			}else{
				alertMsg("创建在线课程失败！");
			}
		});
	};
	
	
	coursePackagePublish = function(){
		var courseGroupItemType = 5;
		$("#publishModal").modal("show");
		$('#group_container_type').val(courseGroupItemType);
		$('#item_id').val($("#packageId").val());
	};
	
	
	publishItemFunc = function(){
		var url="/course/packages/publish.html",data={
			packageId:$.trim($("#packageId").val())
		};
		postAjaxRequest(url, data, function(result){
			if(result.status == 1){
				$("#publishModal").modal("hide");
				alertMsg("课程包发布完成！",function(){
					go2Page("/course/packages/list.html");
				});
			}else{
				alertMsg("抱歉，因为网络问题该course发布失败，请重新发布！");
			}
		});
	};
	
	//转换为页面dot  模板数据格式
	setDataObj = function(length,seriesObject){
		var dataObj = {};
		var data = [];
		for(var i =0;i<length;i++){
			var seriesOrder = i + 1;
			var key = seriesOrder.toString();
			var series = seriesObject[key];
			if(series){
				data.push(series);
			}else{
				data.push({});
			}
		}
		dataObj.data = data;
		return dataObj;
	}
	
	//转换series数据为对象
	convertSeriesArrayToObject = function(seriesArray){
		var seriesObject = new Object();
		for(var item in seriesArray){
			var seriesOrder = seriesArray[item].seriesOrder.toString();
			seriesObject[seriesOrder] = seriesArray[item];
		}
		return seriesObject;
	}
	
	//Item 增加按钮事件
	addItemButtonClick = function(e){
		var container = $(e).parent();
		var template = doT.template($("#course_package_content_items_data_template").text());
		var data = {};
		var itemOder = setItemOrder(e);
		data.itemOrder = itemOder;
		$(container).before(template(data));
		enabledPopover();
		
		//draw事件重新增加
		//drawing.setDrawMouseEvents();
		drawing.addPackageItem();
	}
	
	//生成主键编号
	generateId = function(){
		return UUID.prototype.createUUID();
	}
	
	//设置series 主键编号
	setSeriesId =function(seriesId){
		if(!seriesId){
			seriesId = UUID.prototype.createUUID();
		}
		return seriesId;
	}
	//设置操作类型
	setOperateType = function(seriesId){
		//默认为更新状态
		var status = updateFlg;
		//seriesId为空时，operateType 为插入状态
		if(!seriesId){
			status = insertFlg;
		}
		return status;
	}
	//设置Item 次序编号
	setItemOrder = function(e){
		var ulContainer = $(e).parent().parent();
		return $(ulContainer).find("li").size();
	}
	
	//获取课程包数据
	getPackageData = function(){
		var packageData = {};
		var seriesDataArray = [];
		var itemsDataArray = [];
		var packageId = $("#packageId").val();
		$(".table_list").find(".list_box").each(function(){
			var seriesId = $(this).find("[name='seriesId']").val();
			var seriesName = $(this).find("[name='seriesName']").val();
			var seriesOrder = $(this).find("[name='seriesOrder']").val();
			var operateType = $(this).find("[name='seriesOperateType']").val();
			
			seriesDataArray.push({
				packageId : packageId,
				id : seriesId,
				seriesName : seriesName,
				seriesOrder : seriesOrder,
				operateType : operateType
			});
			var tempItemsDataArray  = getItemsDataArray(this);
			$.merge(itemsDataArray,tempItemsDataArray);
		});
//		debugger;
		packageData.seriesDataArray = seriesDataArray;
		packageData.itemsDataArray = itemsDataArray;
		packageData.itemsRelDataArray = convertItemsObjToArray();
		return packageData;
	}
	
	//转换画线数据为数组形式
	convertItemsObjToArray = function(){
		//使用draw 数据
		var resultArray = [];
		var itemsRelObj = drawing.itemsRelObj;
		for(var p in itemsRelObj){
			var item = itemsRelObj[p];
			if(item){
				resultArray.push(itemsRelObj[p]);
			}
		}
		return resultArray;
	}
	
	//获取所有Items包数据
	getItemsDataArray = function(container){
		var seriesId = $(container).find("[name='seriesId']").val();
		var packageId = $("#packageId").val();
		var itemsDataArray = [];
		$(container).find(".list_con").find("li:not(.add_btn)").each(function(){
			var itemName = $(this).find("[name='itemName']").val();
			var itemId = $(this).find("[name='itemId']").val();
			var itemOrder = $(this).find("[name='itemOrder']").val();
			var operateType = $(this).find("[name='itemOperateType']").val();
			
			itemsDataArray.push({
				packageId : packageId,
				seriesId : seriesId,
				id : itemId,
				itemName : itemName,
				itemOrder : itemOrder,
				operateType : operateType
			});
		});
		return itemsDataArray;
	}
	
	//显示item 关联的课程数据列表
	showItemsElementsRel = function(e){
		var containerLi = $(e).parent().parent();
		currentItemId = $(containerLi).find("[name='itemId']").val();
		currentOperate = showItemsElements;
		$("#coursePackageItemsElementsModal").modal('show');
	}
	
	//删除item
	removeItems = function(e){
		var containerLi = $(e).parent().parent();
		$(containerLi).find("[name='itemOperateType']").val(delFlg);
		$(containerLi).hide();
		var itemId = $(containerLi).find("[name='itemId']").val();
		//删除draw划线
		drawing.delItemsDraw(itemId);
	}
	
	init();
});

