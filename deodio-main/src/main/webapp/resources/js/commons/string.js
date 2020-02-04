
(function() {
	"use strict";
	
	var string = {
			version: '1.0.0',
			removeHtmlTags: undefined,
		}, global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = string;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return string;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.math = string;
	}
	
	
	string.removeHtmlTags = function(str){
		str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
        str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
        //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
        str=str.replace(/ /ig,'');//去掉 
        return str;
	 };
	
}());