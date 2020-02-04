/**
 * 提交 ajax 请求的共通方法
 * @param url
 * @param data
 * @param successCallback
 * @param isMask
 */
 var postAjaxRequest = function( url , data , successCallback, async, contentType, dataType, isMask){
 
	if (async == undefined){
		async = true;
	}
	
	if(contentType == undefined) {
		contentType = "application/x-www-form-urlencoded";
	}
	
	if (dataType == undefined){
		dataType = 'json';
	}
	
	if(isMask == undefined){
		isMask = true;
	}
	
	if(isMask){
		 $("body").mask();
	}
	
	$.ajax({
		   type: "POST",
		   url: ctx + url,
		   data:data,
		   async:async,
		   contentType : contentType,
		   dataType: dataType,
		   success: function(result){
		     if(isMask){
				 $("body").unmask();
			 }
		     if(result.status == 1 || dataType!=undefined ){
		    	 successCallback(result);
		     }else if (result.status == 0 ){
		    	 alertMsg("error");
		     }
		   }
	});	
};

/**
 * 短信倒计时
 */
var countDownTime = function(btn_id,msg1,msg2){
	var second = 60;
	
	var t = setInterval(function(){
		second--;
		$("#"+btn_id).text(second+msg1).attr("disabled",true);						
		if(second==0){
			$("#"+btn_id).text(msg2).attr("disabled",false);	
			clearInterval(t);
		}
	},1000);
};	

/**
 * 获取省
 */
var getProvince = function(countryId,provinceSelectId){
	var url="/commons/getProvinceByCountry.html",data={
			countryId:countryId	
	};
	
	if(!provinceSelectId){
		provinceSelectId = "province_select";
	}
	
	postAjaxRequest(url, data, function(result){
		$("#"+provinceSelectId).empty().append("<option value=''>省</option>");
		$.each(result.data,function(i,v){
			$("#"+provinceSelectId).append("<option value='"+v.provinceId+"'>"+v.name+"</option>");	
		});
		 $('#'+provinceSelectId).selectpicker('refresh');
	});
}
/**
 * 获取城市
 */
var getCity = function(provinceId,citySelectId){
	var url="/commons/getCityByProvince.html",data={
			provinceId:provinceId	
	};
	
	if(!citySelectId){
		citySelectId = "city_select";
	}
	
	postAjaxRequest(url, data, function(result){
		$("#"+citySelectId).empty().append("<option value=''>市</option>");
		$.each(result.data,function(i,v){
			$("#"+citySelectId).append("<option value='"+v.cityId+"'>"+v.name+"</option>");	
		});
		 $('#'+citySelectId).selectpicker('refresh');
	});
}

var getRadioVal = function(nameVal){
	var radioVal="";
	$("input[name='"+nameVal+"']").each(function(){
	  if($(this).prop("checked")){
	    radioVal=$(this).val();
	  };
	  
	});
	return radioVal;
};

var go2Page = function(url,param){
	
	if(param){
		window.location.href = ctx + url+"?"+param+ "&r=" + Math.floor(Math.random() * 100);
	}else{
		window.location.href = ctx + url+"?r=" + Math.floor(Math.random() * 100);
	}
};

var open2Page = function(url,param){
	if(param){
		window.open(ctx + url+"?"+param+ "&r=" + Math.floor(Math.random() * 100));
	}else{
		window.open(ctx + url+"?r=" + Math.floor(Math.random() * 100));
	}
};



var upperCase = function(str){
	return str.toLocaleUpperCase();
};

var lowerCase = function(str){
	return str.toLocaleLowerCase();
};


var isNullFormat = function(str){
	return (str==null||str=="null")?"":str;
};

var randomBorderColor = function(){
	
	var colors = ['#43b4c6','#ee625e','#46c37b','#d81e06','#1296db'];
	return colors[Math.floor(Math.random()*colors.length)];
};

var randomString = function(len) {
	len = len || 32;
	var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
};

	
var customInput = function(inputId,isPrefix){
	var searchReg =  "[name='"+inputId+"']";
	if(isPrefix){
		searchReg = "[name^='"+inputId+"']";
	}
	return $(searchReg).each(function(){	
		if($(this).is('[type=checkbox],[type=radio]')){
			var input = $(this);
			var label = $('label[for='+input.attr('id')+']');
			
			input.bind('updateState', function(){
				input.is(':checked') ? label.addClass('checked') : label.removeClass('checked'); 
			}).trigger('updateState').click(function(){ 
				$('input[name='+ $(this).attr('name') +']').trigger('updateState'); 
			})
		}
	});
};


var alertMsg = function(message,width,height,callback){
	if(width!=undefined || height!=undefined){
		$(".alert-modal-dialog").css({ "width": width, "height": height });
		$(".alert-modal-content").css({"width": width, "height": height });		
		$(".modal-message").css({"height": height - 110});	
	}else{
		$(".alert-modal-dialog").css({ "width": 350, "height": 210 });
		$(".alert-modal-content").css({"width": 350, "height": 210 });	
		$(".modal-message").css({"height": 110});	
	}
	
	
	$("#alertModelBody").html(message);
	$("#alertModel").modal('show');
	$('#alertModel').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
	$('#colseSubmitButton').on('click',function(){
//		debugger;
		if(callback){
			callback();
		}
	});
}

var confirmMsg = function(message,callback,width,height){
	
	$('#confirmSubmitButton').unbind('click');
	
	if(width!=undefined || height!=undefined){
		$(".confirm-modal-dialog").css({ "width": width, "height": height });
		$(".confirm-modal-content").css({"width": width, "height": height });		
		$(".modal-message").css({"height": height - 150});	
	}else{
		$(".confirm-modal-dialog").css({ "width": 400, "height": 250 });
		$(".confirm-modal-content").css({"width": 400, "height": 250 });	
		$(".modal-message").css({"height": 140});	
	}
	
	$("#alertConfirmModelBody").html(message);
	$("#alertConfirmModel").modal('show');
	
	$('#confirmSubmitButton').on('click',function(){
		callback();
	});
	
	$('#alertConfirmModel').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
}


var popUpWindows = function(index,html,title,width,height){
	
	if($("#popupModel"+index).length > 0){
		if(width!=undefined || height!=undefined){
			$("#popupModel"+index +" #modal_dialog").css({ "width": width, "height": height });
			$("#popupModel"+index +" #modal_content").css({"width": width, "height": height });	
			$("#popupModel"+index +" #popupModelBody").css({"width": width });	
		}
		$("#popupModel"+index +" #popupModelBody").html(html);
		$("#popupModel"+index +" #popupModalLabel").html(title);
		$("#popupModel"+index).modal('show');
	}else{
		var i = 0;
		var popupModelWindow = $("#popupModel").clone();
		$("#popupModel").before(popupModelWindow);
		$("#popupModel").each(function(){
			if(i == 0){
				$("#popupModel").attr("id","popupModel"+index).css("z-index","9999");
				if(width!=undefined || height!=undefined){
					$("#popupModel"+index +" #model_dialog").css({ "width": width, "height": height });
					$("#popupModel"+index +" #modal_content").css({ "width": width, "height": height });
					$("#popupModel"+index +" #popupModelBody").css({"width": width });	
				}
				$("#popupModel"+index +" #popupModelBody").html(html);
				$("#popupModel"+index +" #popupModalLabel").html(title);
				$("#popupModel"+index).modal('show');
				
				i=i+1;
			}
		});
	}
	$('#popupModel').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
};

var popUpWindow = function(html,title,width,height){
	
	if(width!=undefined || height!=undefined){
		$("#popupModel #modal_dialog").css({ "width": width, "height": height });
		$("#popupModel #modal_content").css({"width": width, "height": height });	
		$("#popupModel #popupModelBody").css({"width": width });	
	}
	$("#popupModel #popupModelBody").html(html);
	$("#popupModel #popupModalLabel").html(title);
	$("#popupModel").modal('show');
	$('#popupModel').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
};

var popUpPreviewWindow = function(html,title,width,height){
	if(width!=undefined || height!=undefined){
		$("#popupPreviewModel #modal_dialog").css({ "width": width, "height": height });
		$("#popupPreviewModel #modal_content").css({"width": width, "height": height });	
		$("#popupPreviewModel #popupPreviewModelBody").css({"width": width, "height": height-122});	
	}
	$("#popupPreviewModel #popupPreviewModelBody").html(html);
	$("#popupPreviewModel #popupPreviewModalLabel").html(title);
	$("#popupPreviewModel").modal('show');
	$('#popupPreviewModel').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open').css('overflow-y','auto');
	});
}

var popUpWindowRegular = function(html,title){
	popUpWindow(html,title,700,540);
}

var popUpWindowLarge = function(html,title){
	popUpWindow(html,title,900,540);
}

var closePopWindow = function(index){
	if(index == undefined){
		$("#popupModel").modal('hide');
	}else{
		$("#popupModel" + index).modal('hide');
	}
	
};

 
var dateFormat =function(str){
	if(str){
		return new Date(str).Format("yyyy-MM-dd");
	}else{
		return '';
	}
	
};


var dateFormat1 =function(str){
	if(str){
		return new Date(str).Format("yyyy-MM-dd hh:mm");
	}else{
		return '';
	}
	
};
function addDate(date,days){ 
    var d=new Date(date); 
    d.setDate(d.getDate()+days); 
    var m=d.getMonth()+1; 
    return d.getFullYear()+'-'+m+'-'+d.getDate(); 
  } 
var getYoukuId = function(url){
	var startIndex = url.indexOf("id") + 3;
	var endIndex = url.indexOf("==.html");
	return url.substring(startIndex, endIndex);
}

var calFileSize = function(num){
	var fileSize = 0;
    if (num > 1024 * 1024)
        fileSize = (Math.round(num * 100 / (1024 * 1024)) / 100).toString() + 'MB';
    else
        fileSize = (Math.round(num * 100 / 1024) / 100).toString() + 'KB';
    return fileSize;
};

var formatTimes = function(time){

		var seco_dig=Math.ceil(time % 60);
		var mins_dig=0;
		var hour_dig=0;

		var seco="";
		var mins="";
		var hour="";

		if (time / 60 >= 60) {
			mins_dig=Math.floor(time / 60) % 60;
			hour_dig=Math.floor(Math.floor(time / 60) / 60);
		} else {
			mins_dig=Math.floor(time / 60);
			hour_dig=0;
		}

		hour=hour_dig >= 10 ? hour_dig + "" : "0" + hour_dig;
		mins=mins_dig >= 10 ? mins_dig + "" : "0" + mins_dig;
		seco=seco_dig >= 10 ? seco_dig + "" : "0" + seco_dig;

		return hour + ":" + mins + ":" + seco;
};

var isNotStartTime=function(str){
	if((new Date(str))<=new Date()){
		return true;
	}
	return false;
}

var compareToDate=function(str,str2){
	return new Date(str) > new Date(str2);
}

var replaceAll = function(str1,regex,str2){
	return str1.replace(regex,str2)
};

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, 
        "d+": this.getDate(), 
        "h+": this.getHours(), 
        "m+": this.getMinutes(),  
        "s+": this.getSeconds(),  
        "q+": Math.floor((this.getMonth() + 3) / 3), 
        "S": this.getMilliseconds() 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

var randomFun = function(str){
	var arr = str.split('#');
	var re = [];
	for(var i=0;i<arr.length;i++){
	  (function(i){
           var m=Math.floor(Math.random()*arr.length);
           _this.re[i]=arr[m];
           _this.splice(m,1);
       })(i)
	}
	
	return re;
};

/**
 * 字符串拼接
 * example: 1,2,3,4
 * [1,2,3,4]转成1#2#3
 */
var arrayToString = function(str,arrays,chars){
	arrays.push(str);
	return arrays.join(chars);
}
/**
 * 滑动展开
 * div_hidd：点击按钮-ID
 * sideDiv：滑动区域DIV-ID
 * emPx：滑动像素为JSON数据，注意 +,- 符号；
 * tableCssOptions：滑动后 是否有相关表格进行伸缩，JSO那数据格式，分别为：divid表格ID，cssOptions：为移动像素和方向
 * var hiddenLeft2 = function(){
 *	var empx ={left: '+0px'},tables={"cssOptions":{"padding-left":"380px"},"divid":"div_tables"};
 *	sidbarOpen('div_hidd','div_sidbar',empx,tables);
 *}
 *
 *
 *var closeSidebar2 = function(){
 *	var empx ={left: '-380px'},tables={"cssOptions":{"padding-left":"60px"},"divid":"div_tables"};
 *	
 *	closeSidebar('div_hidd','div_sidbar',empx,tables);
 *}
 */
var sidbarOpen = function(btnId,sideDiv,emPx,tableCssOptions){
	$("#"+btnId).hide();
  	if(tableCssOptions!=undefined){
  		$("#"+tableCssOptions.divid).css(tableCssOptions.cssOptions); 		
  	}
  	$("#"+sideDiv).animate(emPx, "normal");
};
/**
 * 滑动关闭
 */
var closeSidebar = function(btnId,sideDiv,emPx,tableCssOptions){
	$("#"+sideDiv).animate(emPx, "fast",function(){
		$("#"+btnId).show();
		if(tableCssOptions!=undefined){
	  		$("#"+tableCssOptions.divid).css(tableCssOptions.cssOptions); 		
	  	}		
	});
};

var toggleSliderBar = function(btnDivId,slideDivId,emPx,emPx2,emPx_1,emPx2_1){
	if($("#"+btnDivId).css('left')==emPx.left){
		$("#"+btnDivId).animate(emPx_1, "normal");
		$("#"+slideDivId).animate(emPx2_1, "normal");
	}else{
		$("#"+btnDivId).animate(emPx, "normal");
		$("#"+slideDivId).animate(emPx2, "normal");
	}
};


var toggleSliderBarRight = function(btnDivId,slideDivId,emPx,emPx2,emPx_1,emPx2_1){
	if($("#"+slideDivId).css('right')==emPx2.right){
		$("#"+btnDivId).animate(emPx_1, "normal");
		$("#"+slideDivId).animate(emPx2_1, "normal");
	}else{
		$("#"+btnDivId).animate(emPx, "normal");
		$("#"+slideDivId).animate(emPx2, "normal");
	}
};


var initSliderBar = function(btnDivId,slideDivId,emPx,emPx2,emPx_1,emPx2_1){
	$("#"+btnDivId).animate(emPx_1, "normal");
	$("#"+slideDivId).animate(emPx2_1, "normal");
};

var command = function(swfId,itemId,message,param){
	var swf = document.getElementById('file_swf_panel');
	var status=swf.command('command.save.sync.content',null,null);
	
	return status
};

var getQtype = function(qtype){
	var _type = '';
	if (qtype == 1 || qtype == 3) {
		_type = 'input[type="radio"]:checked';
	} else if (qtype == 2) {
		_type = 'input[type="checkbox"]:checked';
	} else if (qtype == 4) {
		_type = 'input[type="text"]';
	}
	;
	return _type;
};

var isCorrect = function(correct,result){
	if(correct.indexOf('_$_') > 0){
		var c = correct.split('_$_');
		for(var i = 0; i<c.size(); i++){
			if(c[i] == result){
				return true;
			}
		}
	}else if(correct == result){
		return true;
	}
	return false;
};

//全选与全不选
var checkOrUncheckAll = function(allId,id){
	$("#"+allId).bind("click", function () {
		var input = $(this);
    	if($(this).prop("checked")){
    		 $("input[name = "+id+"]:checkbox").not(":checked").trigger('updateState').trigger('click');
    	} else{
    		 $("input[name = "+id+"]:checked").trigger('updateState').trigger('click');
    	}
    });
};

//当最后一个复选框选中时，全选复选框选中，当最后一个复选框取消选中时，全选复选框取消选中
var isSelectAll = function (allId,id){
	
	if($('input[name='+id+']:checked').length == $('input[name='+id+']').length){
		$('input[name='+allId+']').prop('checked',true);
		$('input[name='+allId+']:checkbox').next('label').addClass('checked');
	} else {
		$('input[name='+allId+']').prop('checked',false);
		$('input[name='+allId+']:checkbox').next('label').removeClass('checked');
	}
};

//设置checkbox 选中
var setCheckboxChecked = function(valStr,checkBoxName){
	if(valStr){
		var varStrs = valStr + ',';
		$("input[name='" + checkBoxName + "']").each(function(){
			  if($(this).is('[type=checkbox],[type=radio]')){
					var value = $(this).val() + ",";
					var id = $(this).prop("id");
					if(varStrs.indexOf(value) > -1){
						$("#" + id).prop("checked",true);
						$("#" + id).next('label').addClass('checked');
					}else{
						$("#" + id).prop("checked",false);
						$("#" + id).next('label').removeClass('checked');
					}
			  }
		});
	}
}

//判断给定字符串是否为指定字符串子字符串
var isSubStr = function(strObj,searchStr){
	var resultFlg = false;
	if(strObj){
		strObj = strObj + ",";
		searchStr = searchStr + ",";
		if( strObj.indexOf(searchStr) > -1){
			resultFlg = true;
		}
	}
	return resultFlg;
};

var subString = function(str,endIndex){
	if(str.length > endIndex){
		return str.substring(0,endIndex)+'...';
	}
	return str;
}

var removeHtmlTags = function(str){
	str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
    //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/ /ig,'');//去掉 
    return str;
 };
 
 
 var adjustW = function(ow,oh,nw,nh){
	if (nw / nh > ow / oh) {
		return ow * nh / oh;
	}

	if (nw / nh <= ow / oh) {
		return nw;
	}

};

var adjustH = function(ow, oh, nw, nh) {
	if (nw / nh > ow / oh) {
		return nh;
	}

	if (nw / nh <= ow / oh) {
		return oh * nw / ow;
	}
};

var _prevent = function(e){
	e.preventDefault && e.preventDefault();
    e.stopPropagation && e.stopPropagation();
    e.cancelBubble = true;
    e.returnValue = false;
};



var minute = 1000 * 60;
var hour = minute * 60;
var day = hour * 24;
var halfamonth = day * 15;
var month = day * 30;
var formatDate2Char = function(dateTimeStamp) {
	var now = new Date().getTime();

	var diffValue = now - dateTimeStamp;

	if (diffValue < 0) {
		return "刚刚";
	}
	var monthC = diffValue / month;
	var weekC = diffValue / (7 * day);
	var dayC = diffValue / day;
	var hourC = diffValue / hour;
	var minC = diffValue / minute;
	if (monthC >= 1) {
		result = parseInt(monthC) + "个月前";
	} else if (weekC >= 1) {
		result = parseInt(weekC) + "周前";
	} else if (dayC >= 1) {
		result = parseInt(dayC) + "天前";
	} else if (hourC >= 1) {
		result = parseInt(hourC) + "个小时前";
	} else if (minC >= 1) {
		result = parseInt(minC) + "分钟前";
	} else
		result = "刚刚";
	return result;
};

var isMobilePhone=function(value){
	return (/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test($.trim(value)));}