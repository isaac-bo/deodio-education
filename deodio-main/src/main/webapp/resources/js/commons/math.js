
(function() {
	"use strict";
	
	var math = {
			version: '1.0.0',
			isDigit: undefined,
		}, global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = math;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return math;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.math = math;
	}
	
	
	math.isDigit = function(value){
	    var reg = /^\d+$/;
	    var re = new RegExp(reg);
		if (re.test(value)) {
			return true;
		}
		return false;
	 };
	
}());