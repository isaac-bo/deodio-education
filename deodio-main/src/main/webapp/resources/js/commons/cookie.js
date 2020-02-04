
(function() {
	"use strict";
	
	var cookie = {
			version: '1.0.0',
			getCookie: undefined,
			setCookie: undefined,
			checkBrowseCookie:undefined
		}, global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = cookie;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return cookie;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.cookie = cookie;
	}
	
	cookie.getCookie = function(Name) {	
		var search = Name + "=";
		var returnvalue = "";
		if (unescape(document.cookie).length > 0) {
			var offset = unescape(document.cookie).indexOf(search);
			if (offset != -1) {
				offset += search.length;
				var end = unescape(document.cookie).indexOf(";", offset);
				if (end == -1)
					end = unescape(document.cookie).length;
				returnvalue = unescape(document.cookie).substring(offset, end);
			}
		}
		return returnvalue;
	};
	
	cookie.setCookie = function(name, value, time) {
		jQuery.cookie(name, value, {
			expires : time,
			domain : "",
			path :"/"
		});
	};
	
	cookie.checkBrowseCookie = function(){
		cookie.setCookie("com_browse_cookie","true",1/24/60);
		var com_browse_cookie = cookie.getCookie("com_browse_cookie");
		if (com_browse_cookie != "true") {
			alertMsg($.i18n.prop('fe.warrning.support.cookie'));
			//alertMessageCheckBrowse("Your browser does not currently accept cookies. Please enable cookies in your browser to proceed.");
		}
	};

}());