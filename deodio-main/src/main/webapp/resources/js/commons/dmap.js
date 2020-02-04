
/*
 * deodio dMap
 * http://deodio.com.cn/
 * Date: 2016-06-015
 */
(function(){
	"use strict";
	var dMap = {
			map:undefined,
			init: undefined
		},global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = dMap;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return dMap;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.dMap = dMap;
	}
	
	dMap.init = function(mapContainer,lng,lat,zoom){
    	// 百度地图API功能
		dMap.map = undefined;
		dMap.map = new BMap.Map(mapContainer);
		dMap.map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
		dMap.map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		if(lng == undefined){
			lng=116.414704;
		}
		if(lat == undefined){
			lat=39.91367;
		}
		if(zoom == undefined){
			zoom=11;
		}
		var point = new BMap.Point(lng,lat); 	//北京市
		dMap.map.centerAndZoom(point,zoom);
		dMap.map.addOverlay(new BMap.Marker(point));
		dMap.getLngAndLat('locationLongitude','locationLatitude');
	};
	
	dMap.centerAndZoom = function(centerP,zoom){
		dMap.map.centerAndZoom(centerP, zoom);//重新定位地图
	};
	
	dMap.searchBMap = function(centerP,keywords,lngId,latId){
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 清除所有
		dMap.map.clearOverlays();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(keywords, function(point){
			if (point) {
				$("#"+lngId).val(point.lng);
				$("#"+latId).val(point.lat);
				dMap.map.centerAndZoom(point, 15);
				dMap.map.addOverlay(new BMap.Marker(point));
			}else{
				dMap.keywordSearch(lngId,latId,keywords);
			}
		}, centerP);
		
	};
	
	dMap.keywordSearch = function(lngId,latId,keywords) {
		var options = {renderOptions: {map: dMap.map}};
		var localSearch = new BMap.LocalSearch(dMap.map,options);
		localSearch.enableAutoViewport();	//允许自动调节窗体大小
	    dMap.map.clearOverlays();//清空原来的标注
		localSearch.setSearchCompleteCallback(function(result) {
	        var poi = result.getPoi(0);
	        if(poi == undefined){
	        	$("#searchResult").show()
	        }else{
		        $("#"+lngId).val(poi.point.lng);
				$("#"+latId).val(poi.point.lat);
		        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
		        dMap.map.addOverlay(marker);
		        dMap.map.closeInfoWindow();
	        }
	    });
		localSearch.search(keywords);
	};
	
	//添加click事件，获取经纬度
	dMap.getLngAndLat = function(lngId,latId){
		dMap.map.addEventListener("click", function(e){
			var pt = e.point;
		    $("#"+lngId).val(pt.lng);
		    $("#"+latId).val(pt.lat);
		    dMap.map.clearOverlays();//清空原来的标注
		    var marker = new BMap.Marker(new BMap.Point(pt.lng,pt.lat));  // 创建标注，为要查询的地方对应的经纬度
	        dMap.map.addOverlay(marker);
		});
	};
	
	dMap.loadBMap = function(mapContainer,lng,lat,zoom){
		// 百度地图API功能
		var map = new BMap.Map(mapContainer,{enableMapClick : false});
		map.disableDragging();
		map.disableDoubleClickZoom();
		var point = new BMap.Point(lng,lat);
		map.centerAndZoom(point,zoom);
		var marker = new BMap.Marker(point); 
		map.addOverlay(marker);            
		map.closeInfoWindow();
	};
	
	/*dMap.getCurrentPosition = function() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(getPositionSuccess,getPositionError);
		}
	};

	getPositionSuccess = function(geolocationResult){
		console.log(geolocationResult.point.lng)
	};
	getPositionError = function(error){
		switch(error.code){
			case error.TIMEOUT :
				alertMsg( " 连接超时，请重试 " );
				break;
			case error.PERMISSION_DENIED :
				alertMsg( " 您拒绝了使用位置共享服务，查询已取消 " );
				break;
			case error.POSITION_UNAVAILABLE : 
				alertMsg( " 亲爱的火星网友，非常抱歉，我们暂时无法为您所在的星球提供位置服务 " );
				break;
		}
	};*/
	
}());

