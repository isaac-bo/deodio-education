define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils.dmap","utils","jquery.base", "jquery.validate", "jquery.scrolltofixed",
		"jquery.mCustomScrollbar", "bootstrap.select", "bootstrap.datepicker","jquery.scroll.pagination" ], function($, cookie, doT, paging, dMap) {

	var _init = function() {
		
		//loadDataList(1,"table_panle", "table_data_template", "smallIconShow");
		$('select').selectpicker();	
		customInput("allLocationsId");
//		customInput('isEnabled');
		checkOrUncheckAll('allLocationsId','locationsId');
		//实现滚动加载列表
		$('#content').scrollPagination({
			'postUrl' : ctx + '/location/load_location_list.html',
			'postParams' : {
				pageNo : $.trim($("#hid_default_page").val()).length <= 0 ? 1 : $("#hid_default_page").val(),
				pageSize:6,
				keywords : $.trim($("#keywords").val())
			},
			'scrollTarget' : $(window),
			'callBackLoad' : function(data) {
				loadLocationsDataList(data);
			},
			'beforeLoad' : function() {
				this.postParams.pageNo = $("#hid_default_page").val();
				this.postParams.pageSize = 9;
				this.postParams.keywords = $.trim($("#keywords").val());
			}

		});
		
		mediumIconShow(1);
	};
	
	//加载考场列表
	loadDataList = function(pageNo,tablePanel,tableDataTemplate,callbackFunc) {
		if (pageNo == undefined)
			pageNo = 1;
		var params = {
			pageNo : pageNo,
			pageSize:$("#data_page_Panel").is(":hidden")?9:10,
			keywords : $('#keywords').val()
		}, url = "/location/load_location_list.html";
		postAjaxRequest(url, params, function(result) {
			if(result.status == 1){
				paging.pageTemplateDiv(result, tablePanel, tableDataTemplate, "data_page_Panel", callbackFunc);
				if ($('.pic-item').hasClass('on')) {
					var dataList = result.data.dataList;
					for(var i=0; i< dataList.length;i++){
						var mapContainer = "map_"+dataList[i].id;
						var lng = dataList[i].location_longitude;
						var lat = dataList[i].location_latitude;
						var zoom = dataList[i].location_zoom;
						dMap.loadBMap(mapContainer,lng,lat,zoom);
					}
				}
			}
			customInput("locationsId");
		});
	};
	
	//加载详细列表
	smallIconShow = function(pageNo) {
		$('#content').stopScrollPagination();
		$('.table-item').addClass('on');
		$('.pic-item').removeClass('on');
		$('#smallIcon').show();
		$("#content").children().remove();
		$("#mediumIcon").hide();
		$("#data_page_Panel").show();
		$("#hid_default_page").val(Number(pageNo) + 1);
		$('#smallIconConditionBar').show();
		$("#delAllLocations").show();
		loadDataList(pageNo,"table_panle", "table_data_template", "smallIconShow");
		
	};
	
	//加载中等图标列表
	mediumIconShow = function(pageNo) {
		$('.pic-item').addClass('on');
		$('.table-item').removeClass('on');
		$('#mediumIcon').show();
		$("#table_panle").children().remove();
		$('#smallIcon').hide();
		$("#data_page_Panel").hide();
		$("#delAllLocations").hide();
		$('#content').attr('scrollPagination', 'enabled');
		loadDataList(pageNo,"content", "data_template");

	};

	//滚动加载考场列表的回调函数
	loadLocationsDataList = function(data) {
		
		var template = doT.template($("#data_template").text());
		if ($("#content").size() == 0) {
			$('#content').append(template({
				"data" : data.dataList
			}));
		} else {
			$('#content div.row:last').after(template({
				"data" : data.dataList
			}));
		}
		if (data.dataList.length != 0) {
			var finalPageNo = Number($("#hid_default_page").val()) + 1;
			$("#hid_default_page").val(finalPageNo);
			if ($('.pic-item').hasClass('on')) {
				var dataList = data.dataList;
				for(var i=0; i< dataList.length;i++){
					var mapContainer = "map_"+dataList[i].id;
					var lng = dataList[i].location_longitude;
					var lat = dataList[i].location_latitude;
					var zoom = dataList[i].location_zoom;
					dMap.loadBMap(mapContainer,lng,lat,zoom);
				}
			}
		}
	};

	//检索考场，返回考场列表
	searchLocationList = function() {
		if ($('.table-item').hasClass('on')) {
			smallIconShow(1);
		} else {
			mediumIconShow(1);
		}
	};

	//删除该考场
	delLocation = function(locationId){
		confirmMsg('请确认您是否要删除该考场？',function(){
			var url="/location/del_location.html",
			data={locationId:locationId};
			postAjaxRequest(url,data,function(result){
				searchLocationList();
				alertMsg("操作成功");
			});
		});
	};
	
	//批量删除考场
	delAllLocations = function(){
			var arr = new Array();
			$("input[name='locationsId']:checkbox").each(function(){ 
				if($(this).next('label').hasClass("checked")){
					arr.push($(this).attr("id"));
				}
            });
	        var locationIds = arr.join(",");
	        if(locationIds){
	        confirmMsg('请确认您是否要删除该考场？',function(){
	        var url="/location/del_all_locations.html",
			data={locationIds:locationIds};
			postAjaxRequest(url,data,function(result){
				searchLocationList();
				alertMsg("操作成功");
			});
		});
	        }else {
	       	 alertMsg("请先勾选要删除的考场!");
	        }
	};
	
	//更新考场地点
	editLocation = function(locationId){
		var url="/location/get_location.html",
		data={locationId:locationId};
		postAjaxRequest(url,data,function(result){
			var template = doT.template($("#pop_template").text());
			popUpWindow(template(result),"更新考场地点","900px","470px");
			customInput('isEnabled');
			$('select').selectpicker();	
			$('select[name=dicProvince] option').each(function(){
				if($(this).val() == result.data.provinceId){
					$(this).prop('selected',true);
				}
			});
			var countryId=result.data.countryId;
			var provinceId=result.data.provinceId;
			var cityId = result.data.cityId;
			editCity(provinceId,cityId);
			$('.selectpicker').selectpicker('refresh');
			var lng = result.data.locationLongitude;
			var lat = result.data.locationLatitude;
			var zoom = result.data.locationZoom;
			setTimeout(_dMapInit('allmap',lng,lat,zoom),1000);
			$("#locationForm").myValidate({
				formCall:function(){ submitLocations();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:true,regionText:true},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		});
	};
	//预览考场
	previewLocation = function(locationId){
		var url="/location/get_preview_location.html",
		data={locationId:locationId};
		postAjaxRequest(url,data,function(result){
			var template = doT.template($("#pop_view_template").text());
			popUpWindow(template(result),"预览考场地点","900px","470px");
			customInput('isEnabled');
			$('select').selectpicker();	
			$('select[name=dicProvince] option').each(function(){
				if($(this).val() == result.data.provinceId){
					$(this).prop('selected',true);
				}
			});
			var countryId=result.data.countryId;
			var provinceId=result.data.provinceId;
			var cityId = result.data.cityId;
			editCity(provinceId,cityId);
			$('.selectpicker').selectpicker('refresh');
			var lng = result.data.locationLongitude;
			var lat = result.data.locationLatitude;
			var zoom = result.data.locationZoom;
			setTimeout(_dMapInit('allmap',lng,lat,zoom),1000);
			$("#locationForm").myValidate({
				formCall:function(){ submitLocations();},
				isAlert:false,
				formKey:false,
				errorCustom:{customFlag:true,regionText:true},
				errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
			});	
		});
	};
	//显示已选择的省市
	editCity = function(provinceId,cityId){
		var url="/commons/getCityByProvince.html",data={
				provinceId:provinceId	
		};
		postAjaxRequest(url, data, function(result){
			$("#city_select").empty().append("<option value=''>市</option>");
			$.each(result.data,function(i,v){
				$("#city_select").append("<option value='"+v.cityId+"'>"+v.name+"</option>");	
			});
			$('select[id=city_select] option').each(function(){
				if($(this).val() == cityId){
					$(this).prop('selected',true);
				}
			});
			$('#city_select').selectpicker('refresh');
		});
	};
	
	_init();
	
	//弹出创建考场地点窗口
	popLocations = function(){
		var template = doT.template($("#pop_template").text());
		popUpWindow(template({data:{id:'',locationName:'',locationAddress:'',locationDesc:'',isEnabled:0}}),"创建考场地点","900px","470px");
		$('#locationId').val('');
		customInput('isEnabled');
		$('select').selectpicker();	
		setTimeout(_dMapInit('allmap'),1000);
		$("#locationForm").myValidate({
			formCall:function(){ submitLocations();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});	
	};
	
	//初始化地图
	_dMapInit = function(mapContainer,lng,lat,zoom){
		return function(){
			dMap.init(mapContainer,lng,lat,zoom);
		}
	};
	//校验考场名字
	checkName = function(){
		var newLocationName=$("#locationName").val();
		var oldLocationName=$("#hiddenlocationName").val();
		var flag=false;
		if(newLocationName==oldLocationName){
			flag=true;
		}else{
		var url="/location/getCheck_location.html",data={
				locationName:newLocationName
		}
		postAjaxRequest(url, data,function(result){
			flag=result.data;
	    },false);
		return flag;
	}
	}
	//文本框字数校验
	createForm=function(){
	var zoom = dMap.map.getZoom();
	var url = "/location/crerate_location.html", data={
			locationName:$('#locationName').val(),
			locationId:$('#locationId').val(),
			locationAddress:$('#locationAddress').val(),
			locationDesc:$('#locationDesc').val(),
			isEnabled:$('#isEnabled').prop('checked')?1:0,
			locationLongitude:$('#locationLongitude').val(),
			locationLatitude:$('#locationLatitude').val(),
			locationZoom:zoom,
			countryId:$('#select_country').val(),
			provinceId:$('#select_province').val(),
			cityId:$('#city_select').val()
	};
	postAjaxRequest(url,data,function(result){
		closePopWindow();
		searchLocationList();
		alertMsg("操作成功");
	});
	}
	//提交表单，创建考场
	submitLocations = function(){
		var locationName=$("#locationName").val();
		var locationAddress=$("#locationAddress").val();
		var locationDesc=$("#locationDesc").val();
		var locationNameflag=true;
		var locationAddress=true;
		var locationDesc=true;
		if(locationName.length>50){
			alert('文本长度不能大于50个字符')
			locationNameflag=false;
		}else{
			locationNameflag=true;
		}
		if(locationAddress.length>200){
			alert('文本长度不能大于50个字符')
			locationAddressflag=false;
		}else{
			locationAddressflag=true;
		}
		
		if(locationDesc.length>2000){
			alert('考场简介内容过多')
			locationDescflag=false;
		}else{
			locationDescflag=true;
		}if(locationName&&locationAddress&&locationDesc){
			
			createForm();
		}
		
		/*var nameFlag;
		var url="/location/getCheck_location.html",data={
				locationName:$.trim($("#locationName").val())
		}
		postAjaxRequest(url, data,function(result){
			if(result.data==true){
				nameFlag=true;
			}else{
				nameFlag=false;
			}
	    },false);
		if(nameFlag==false){
			
			alertMsg("培训场已存在");
			return;
		}*/
//		var zoom = dMap.map.getZoom();
//		var url = "/location/crerate_location.html", data={
//				locationName:$('#locationName').val(),
//				locationId:$('#locationId').val(),
//				locationAddress:$('#locationAddress').val(),
//				locationDesc:$('#locationDesc').val(),
//				isEnabled:$('#isEnabled').prop('checked')?1:0,
//				locationLongitude:$('#locationLongitude').val(),
//				locationLatitude:$('#locationLatitude').val(),
//				locationZoom:zoom,
//				countryId:$('#select_country').val(),
//				provinceId:$('#select_province').val(),
//				cityId:$('#city_select').val()
//		};
		postAjaxRequest(url,data,function(result){
			closePopWindow();
			searchLocationList();
			alertMsg("操作成功");
		});
	};
	
    //地图校验
	mapCheck = function() {
		var centerP;
		var dicCountry = $('select[name=dicCountry]').find("option:selected").text();
		var dicProvince = $('select[name=dicProvince]').find("option:selected").text();
		var dicCity = $('select[name=dicCity]').find("option:selected").text();
		if(dicCity != ""){
			centerP = dicCity;
		}
		$("#searchResult").hide();
		var loationKeywords = $('#locationAddress').val();
		dMap.searchBMap(centerP,loationKeywords,'locationLongitude','locationLatitude');
	};
	
	//选择省份，地图显示该省位置
	selectProince = function(){
		var provinceId=$('#select_province').val();
		getCity(provinceId);
		var centerP = $('select[name=dicProvince]').find("option:selected").text();
		dMap.keywordSearch('locationLongitude','locationLatitude',centerP);
	};
	
	//选择城市，地图显示该城市位置
	selectCity = function(){
		var centerP = $('select[name=dicCity]').find("option:selected").text();
		dMap.keywordSearch('locationLongitude','locationLatitude',centerP);
	};
	//字数过多校验
//    var createForm = function(){
////		var locationDesc=um.getContent();
//		$("#locationDesc").val(locationDesc);
//		if(locationDesc.length>255){
//			alertMsg('问卷描述内容过多')
//		}else{
//			$("#createForm").submit();
//		}
//    }	
//	
});
