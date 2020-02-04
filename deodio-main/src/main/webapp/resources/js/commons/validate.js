
(function() {
	"use strict";
	
	var validate = {
			version: '1.0.0',
			isUrl: undefined,
			isYouku:undefined,
			trimYouku:undefined
		}, global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = validate;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return validate;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.validate = validate;
	}
	
	

	validate.isUrl = function(url) {  
		var strReg = '^((https|http|ftp|rtsp|mms)?://)'
			+ '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?' //ftp的user@
			+ '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
			+ '|' // 允许IP和DOMAIN（域名）
			+ '([0-9a-z_!~*\'()-]+.)*' // 域名- www.
			+ '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名
			+ '[a-z]{2,6})' // first level domain- .com or .museum
			+ '(:[0-9]{1,4})?' // 端口- :80
			+ '((/?)|' // a slash isn't required if there is no file name
			+ '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$'; 
		var re = new RegExp(strReg);  
	    if(!re.test(url)) {  
	        return false;  
	    } else {  
	        return true;  
	    }  
	};
	
	validate.isYouku = function(url){
		
		//http://v.youku.com/v_show/id_XMTYzOTY2NTU5Mg==.html
//		var strReg = '^(http://v.youku.com/v_show/id_[a-zA-Z]{12,18}(=*).html'; 
		if(url.indexOf('player.youku.com')<0){
			return false;
		}
		return true;
		
//		var re = new RegExp(strReg);  
//	    if(!re.test(url)) {  
//	        return false;  
//	    } else {  
//	        return true;  
//	    }  
		
	};
	
	validate.trimYouku = function(url){
		var startIndex = url.indexOf('id') + 3;
		var endIndex = url.indexOf('==.html');
		var cutString=url.substring(startIndex, endIndex); //用substring获取子字符串；
	};

	
}());