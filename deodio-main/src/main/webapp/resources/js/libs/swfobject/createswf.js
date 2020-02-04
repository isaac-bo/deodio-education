function createSWFObject(src, replaceElemId, width, height, flashvars, params,attributes) {

	// if($(replaceElemId)) $(replaceElemId).innerHTML = "Please Install Flash
	// Player!";
	if (chkFlash() == true) {
		swfobject.embedSWF(ctx + "/resources/flex" + src + '?00000', replaceElemId, width,
				height, '11.8.0', ctx + '/js/libs/swfobject/expressInstall.swf', flashvars,
				params, attributes);
	} else {
		// $("#"+replaceElemId).html("Please Install Flash Player!");
		alertMsg("The Adobe Flash player is currently disabled in your browser. Please enable the Flash Player to proceed.",function() {});
	}
}

function createSWFObject4LastestVersion(src, replaceElemId, width, height,flashvars, params, attributes,callback) {

	// if($(replaceElemId)) $(replaceElemId).innerHTML = "Please Install Flash
	// Player!";
	if (chkFlash() == true) {
		var flashPlayerOfLastVersion = 11.1;
		if (flashPlayerOfLastVersion > getFlashVersionOfCurrent()) {
			if (showIntroduceFlg) {
				showIntroduceFlg = false;
			}
			alertMsg("Please udpate your Adobe Flash Player plug-in to the latest version in order to ensure optimal perfomance with Deodio.",function() {});
		}
		swfobject.embedSWF(ctx + "/resources/flex" + src + '?00000', replaceElemId, width,height, '11.8.0', ctx+ '/js/libs/swfobject/expressInstall.swf', flashvars,params, attributes,function(){
			if(typeof(callback) != undefined && callback instanceof Function){
				callback();
			}
		});
	} else {
		// $("#"+replaceElemId).html("Please Install Flash Player!");
		alertMsg("The Adobe Flash player is currently disabled in your browser. Please enable the Flash Player to proceed.",function() {});
	}
}

function removeSWFObject(replaceElemId) {
	if ($(replaceElemId))
		$(replaceElemId).innerHTML = "";
}
function chkFlash() {
	var isIE = (navigator.appVersion.indexOf("MSIE") >= 0);
	var hasFlash = true;

	if (isIE) {
		try {
			var objFlash = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
		} catch (e) {
			hasFlash = false;
		}
	} else {
		if (!navigator.plugins["Shockwave Flash"]) {
			hasFlash = false;
		}
	}
	return hasFlash;
}

function getFlashVersionOfCurrent() {

	var flashVersion;
	if (navigator.plugins && navigator.plugins.length) {
		for ( var i = 0; i < navigator.plugins.length; i++) {
			if (navigator.plugins[i].name.indexOf('Shockwave Flash') != -1) {
				flashVersion = parseFloat(navigator.plugins[i].description
						.split(' ')[2]);
				break;
			}
		}
	} else if (window.ActiveXObject) {
		try {
			var swf = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
			if (swf) {
				VSwf = swf.GetVariable("$version");
				flashVersion = VSwf.split(" ")[1];
				flashVersion = parseFloat(flashVersion.replace(/,/g, "."));

			}
		} catch (e) {
			hasFlash = false;
		}
	}
	return flashVersion;
}
