define(["jquery"], function($) {
	
	//数据库插入，更新，删除数据标志
	var delFlg = '2';
	var insertFlg = '0';
	var updateFlg = '1';
	
	//虚实线标识
	var necessaryFlg = '1';
	var unnecessaryFlg = '0';
	
	//
	var leftPointClass = "l_point";
	var rightPointClass = "r_point";
	//item 关联类型
	var itemRelType = null;
	
	//画布保存数据
	var itemsRelObj = {};
	var firstSelectedPointId,secondSelectedPointId;
	
	//实线
	var lineDashReal = [9,0];
	//虚线
	var lineDashDotted = [9,9];
	//画布虚实线设置标识
	var isSetlineDash = false;
	//画布 划线默认为虚线
	var lineDash = lineDashDotted;
	
	//连线course_package_items 之间关系  start
	var canvas = document.getElementById('can'), context;
	 if(!canvas.getContext) {
      alertMsg('你的浏览器不支持canvas!');
      return;
    }
	var context = canvas.getContext('2d');
	
	var drawingSurfaceData;
	var oX,oY;//坐标
	
	//是否开始画线标识
	var drawFlg = false;
	//items 间 间距
	var halfWidth;
	var halfHeight;
	//item width,height
	var comWidth;
	var comHeight;
	//item top,left值
	var comTop;
	var comLeft;
	//允许偏离起始点的横纵坐标
	var allowX = 5;
	var allowY = 5;
	
	//画线函数
	function draw(sx,sy,ex,ey){       
		var initX = sx;
		var initY = sy;    
		var tempHeight = 1;  
		var tempWidth  = 1;
	   //终止点是否在起始点右侧
	   if(ex > (halfWidth+sx) ){
		   	//设置虚实线
			context.setLineDash(lineDash);
			
	   		if(ex < (sx + halfWidth*2 + allowX) && ex > (sx + halfWidth*2  - allowX) && ey > (sy-allowY) && ey < (sy+allowY)){
	   			//横线
	   			context.clearRect(0,0,$("#can").width(),$("#can").height());
			    context.beginPath();
		   		context.moveTo(initX,initY);
		    	context.lineTo((halfWidth*2+initX),initY);
			    restoreDrawingSurface(drawingSurfaceData);
			    context.stroke();
		   }else if(ex < (sx + halfWidth*2 + allowX) && ex > (sx + halfWidth*2  - allowX) && ey < sy){
		   		//向上画斜线
		   		context.clearRect(0,0,$("#can").width(),$("#can").height());
			    context.beginPath();
		   		context.moveTo(initX,initY);
		    	context.lineTo((halfWidth*2+initX),ey);
			    restoreDrawingSurface(drawingSurfaceData);
			    context.stroke();
		   
		   }else if(ex < (sx + halfWidth*2 + allowX) && ex > (sx + halfWidth*2  - allowX) && ey > sy){
		   		//向下画斜线
		   		context.clearRect(0,0,$("#can").width(),$("#can").height());
			    context.beginPath();
		   		context.moveTo(initX,initY);
		    	context.lineTo((halfWidth*2+initX),ey);
			    restoreDrawingSurface(drawingSurfaceData);
			    context.stroke();
		   
		   }else  if(ex > (sx + halfWidth*2 + allowX) && ey > (sy-allowY) && ey < (sy+allowY)){ 
		   		//画横线 -- 跨标示
		   		context.clearRect(0,0,$("#can").width(),$("#can").height());
		   		context.beginPath();
		   		context.moveTo(initX,initY);
		    	sx = halfWidth+initX;
		    	sy = initY - halfHeight;
		    	
		    	context.lineTo(sx,sy);
		    	context.stroke();
		    	
		    	tempWidth = ex - halfWidth;
		    	context.lineTo(tempWidth,sy);
		    	context.stroke();
			    context.lineTo(ex,ey);
		    	
			    restoreDrawingSurface(drawingSurfaceData);
			    context.stroke();
		   }else  if(ex > (sx + halfWidth*2 + allowX) && ey > (sy+halfHeight)){ 
		   		//画横线 -- 跨标示
		   		context.clearRect(0,0,$("#can").width(),$("#can").height());
		   		context.beginPath();
		   		context.moveTo(initX,initY);
		    	sx = halfWidth+initX;
		    	sy = ey - halfHeight;
		    	context.lineTo(sx,sy);
		    	context.stroke();
		    	
		    	tempWidth = ex - halfWidth;
		    	context.lineTo(tempWidth,sy);
		    	context.stroke();
			    context.lineTo(ex,ey);
		    	
			    restoreDrawingSurface(drawingSurfaceData);
			    context.stroke();
		   }else  if(ex > (sx + halfWidth*2 + allowX) && ey < (sy-halfHeight)){ 
		   		//画横线 -- 跨标示
		   		context.clearRect(0,0,$("#can").width(),$("#can").height());
		   		context.beginPath();
		   		context.moveTo(initX,initY);
		    	sx = halfWidth+initX;
		    	sy = ey + halfHeight;
		    	context.lineTo(sx,sy);
		    	context.stroke();
		    	tempWidth = ex - halfWidth;
		    	context.lineTo(tempWidth,sy);
		    	context.stroke();
			    context.lineTo(ex,ey);
			    restoreDrawingSurface(drawingSurfaceData);
			    context.stroke();
		   }else{
				context.clearRect(0,0,$("#can").width(),$("#can").height());
		  		context.beginPath();
		   		context.moveTo(initX,initY);
		   		tempHeight = ey - halfHeight;
		   		tempWidth = ex - halfWidth;
		    	context.lineTo(halfWidth+initX,tempHeight);
		    	context.stroke();
		    	sx = tempWidth;
		    	sy = tempHeight;
		    	//第二个折点初始
		    	if(ex > sx && ey > sy){
//		    		tempWidth = Math.floor(ex/comWidth)*comWidth;
		    		context.lineTo(tempWidth,sy);
		    		context.stroke();
		    		context.moveTo(tempWidth,sy);
			    	context.lineTo(ex,ey);
			    	restoreDrawingSurface(drawingSurfaceData);
			    	context.stroke();
			    	sx = tempWidth;
		    	}else{
		    	    context.moveTo(sx,sy);
				    context.lineTo(ex,ey);
				    restoreDrawingSurface(drawingSurfaceData);
				    context.stroke();
		    	}
		   }
	   		return true;
	   }else{
		   alertMsg("前置项目应在后置项目之前！");
		   //绘画模式终止
//		   reverseDrawFlg();
		   return false;
	   }
	   
	}
	
	 /*保存画布数据 */
    function saveDrawingSurface(){
        return context.getImageData(0,0,$("#can").width(),$("#can").height());
    }
    
     /*恢复画布数据 */
    function restoreDrawingSurface(data){
        if(!data) return false;
        context.putImageData(data,0,0);
    } 

    //设置画布目标
    setListConBackgroundImage = function(){
		$(".table_list").attr("style","background-image: url('" +  context.canvas.toDataURL() + "')");
	}
    
    //设置画布初始参数
    setDrawParams = function(){
    	
    	$("#can").attr("width", $(window).get(0).innerWidth);  
	    $("#can").attr("height", $(window).get(0).innerHeight);  
	 	context.fillStyle="#fff";
	    context.fillRect(0, 0, $("#can").width(), $("#can").height());  
	    context.lineWidth = 1;
	   // 控制实线虚线的
	 	context.setLineDash(lineDashDotted); 
	    context.lineCap = "round"; 

    	if(!halfWidth){
    		halfWidth= parseInt($(".list_box:first").css("marginRight"))/2;
    	}
		 if(!halfHeight){
			 halfHeight= parseInt($(".list_con:first").find("li:first").outerHeight(true))/2;
		 }
		 if(!comWidth){
			 comWidth=parseInt($(".list_box:first").outerWidth());
		 }
		 if(!comHeight){
			 comHeight= parseInt($(".list_con:first").outerHeight());
		 }
		 if(!comTop){
			 comTop = $(".table_list").offset().top;
		 }
		 if(!comLeft){
			 comLeft = $(".table_list").offset().left;
		 }
		 
		 //重置画布默认数据
		 resetLineDash();
    }
    
    //获取选中的ItemId
    getSelectedPointId = function(e){
    	var container = $(e).parent();
    	return $(container).find("[name='itemId']").val();
    }
    
    //保存画布连线 items关系数据
    saveItemsRelObj = function(dataObj){
    	var id = dataObj.itemFrontId + ';' + dataObj.itemEndId;
    	itemsRelObj[id] = dataObj;
    }
    
    //设置画布画线触发事件
    setDrawMouseEvents =  function(){
    	//解绑事件
    	$('.l_point,.r_point').off("mousedown mouseup mouseleave");
    	//重新绑定事件
    	$('.l_point,.r_point').on("mousedown",function(e){
    		//第一次点击事件,前为false,后为true
//    		e.isDefaultPrevented=true;
	   		if(!drawFlg){
	   			 if($(e.target).is(".r_point")){
		   			if($(e.target).css('background-color') == 'rgb(0, 160, 233)'){
	   	    			$(e.target).css('background-color','#ff0000');
	   	    		}
		   		
			   	    oX = e.pageX-comLeft;
			        oY = e.pageY-comTop;
			        firstSelectedPointId = getSelectedPointId(e.target);
			        reverseDrawFlg();
	   			 }
	   	    }else{
	   	    	
	   	    	
	   	    	if($(e.target).is(".l_point")){
		   	    	if($(e.target).css('background-color') == 'rgb(0, 160, 233)'){
	   	    			$(e.target).css('background-color','#ff0000');
	   	    		}
		   	    	
		   	    	//第二次点击,结束节点，对当前线条做判断;前为true,
					 if(oX != null && oY != null){
						 var isSuccessed = draw(oX,oY,e.pageX-comLeft,e.pageY-comTop);
						 if(!isSuccessed){
							 return;
						 }
						 secondSelectedPointId = getSelectedPointId(e.target);
						 //保存画布连线数据
						 saveItemsRelObj({
							 itemFrontId:firstSelectedPointId,
							 itemEndId:secondSelectedPointId,
							 itemRelType:itemRelType,
							 operateType:insertFlg,
							 id:UUID.prototype.createUUID(),
							 packageId:$("#packageId").val()
						 });
					 }
					 setListConBackgroundImage();
					 //恢复默认虚线连线
					 resetLineDash();
					 reverseDrawFlg();
	   	    	}
	   	    }
    	}).on("mouseup",function(e){
	   		 if(!drawFlg){
		   	       //第一次点击
	   	    }else{
	   	    	//第二次触发时间，
	   	    	drawingSurfaceData = saveDrawingSurface();
	   	    }
//	   		reverseDrawFlg();
	    });
    	
    	$('.l_point,.r_point').on("mouseleave",function(e){
//    		debugger;
    		if(drawFlg && (!isSetlineDash)){
    			isSetlineDash = true;
    			setLineDash($('#finalResut1').prop('checked')?1:0);
    		}
    	});
    };
    
    //设置画布画线类型
    setLineDash =function(currentItemRelType){
//    	debugger;
    	itemRelType = currentItemRelType;
    	if(necessaryFlg == itemRelType){
    		lineDash = lineDashReal;
    	}else{
    		lineDash = lineDashDotted; 
    	}
    }
    
    //反转画线标识
    reverseDrawFlg = function(){
    	drawFlg = !drawFlg; 
    }
    
    //通过items数组 绘画连线
    doItemsDraw = function(itemsArray,isReload){
    	for(var index in itemsArray){
    		var item = itemsArray[index];
    		var itemFrontId = item.itemFrontId;
			var itemEndId = item.itemEndId;
			var itemRelType = item.itemRelType;
			 
			//获取item 坐标数据
			var frontPosition = getPositionByItemId(itemFrontId,rightPointClass);
			var endPosition = getPositionByItemId(itemEndId,leftPointClass);
			//数据同时存在
			if(frontPosition && endPosition){
				setLineDash(itemRelType);
				
				$("." + rightPointClass + "[name='" + itemFrontId + "']").css('background-color','#ff0000');
				$("." + leftPointClass + "[name='" + itemEndId + "']").css('background-color','#ff0000');
				
				//画线
				draw(frontPosition.x,frontPosition.y,endPosition.x,endPosition.y);
				setListConBackgroundImage();
				drawingSurfaceData = saveDrawingSurface();
				//保存已连线数据
				if(!isReload){
					saveItemsRelObj(item);
				}
    		}
    	}
    	resetLineDash();
    }
    
    //删除绘画连线
    delItemsDraw = function(itemId){
    	var regStr = "^" + $.trim(itemId) + ";" + "|" + ";" + $.trim(itemId) + "$";
    	var regExp = new RegExp(regStr);
    	for(var p in itemsRelObj){
    		var isExisted = regExp.test(p);
    		if(isExisted){
    			delete itemsRelObj[p];
    		}
    	}
    	context.clearRect(0,0,$("#can").width(),$("#can").height());
    	drawingSurfaceData = saveDrawingSurface();
    	setListConBackgroundImage();
    	doItemsDraw(itemsRelObj);
    }
    
    //通过ItemId获取坐标
    getPositionByItemId = function(itemId,className){
    	var itemPoint = $("." + className + "[name='" + itemId + "']");
    	if(!itemPoint){
    		return null;
    	}
    	return {
    		"x": $(itemPoint).offset().left - comLeft + $(itemPoint).width()/2,
    		"y":$(itemPoint).offset().top - comTop + $(itemPoint).height()/2
    	}
    }
    
    //重置画线数据参数
    resetLineDash = function(){
    	//恢复默认虚线连线
		 isSetlineDash = false;
		 lineDash = lineDashDotted;
		 itemRelType = unnecessaryFlg;
    }
    
    //通过Item数据加载画线连线
    initDraw = function(itemsRel){
    	setDrawParams();
		setDrawMouseEvents();
		setListConBackgroundImage();
		//绘画课程包Item 连线
		doItemsDraw(itemsRel);
    }
    
    //获取画线标识
    getDrawFlg = function(){
    	return drawFlg;
    }
    
    //增加课程包item  画线部分相关逻辑
    addPackageItem = function(){
    	setDrawParams();
		setDrawMouseEvents();
//		setListConBackgroundImage();
    }
    
	return{
		getDrawFlg:getDrawFlg,
		itemsRelObj:itemsRelObj,
		initDraw:initDraw,
		setDrawMouseEvents:setDrawMouseEvents,
		delItemsDraw:delItemsDraw,
		addPackageItem:addPackageItem
	}
});