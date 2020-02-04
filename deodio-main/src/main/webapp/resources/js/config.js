APP_JS_LIBS = ctx + "/resources/js/libs";
ROOT_JS = ctx + "/resources/js";
ROOT_CSS = ctx + "/resources/css";

var requirejs = {
	 urlArgs: 'v='+Math.floor(Math.random() * 100),
	 baseUrl: ctx+"/resources/js",
	 map: {
		  '*': {
		    'css': APP_JS_LIBS+'/require/require.css.js'
		  }
		},
	 paths: {
		 "bootstrap"				: APP_JS_LIBS + "/bootstrap/bootstrap.min",
		 "bootstrap.masonry"		: APP_JS_LIBS + "/bootstrap/bootstrap.masonry",
		 "bootstrap.select"			: APP_JS_LIBS + "/bootstrap/bootstrap.select",
		 "bootstrap.imagesloader"	: APP_JS_LIBS + "/bootstrap/bootstrap.imagesloader",
		 "bootstrap.datepicker"		: APP_JS_LIBS + "/bootstrap/bootstrap.datepicker",
		 "echarts"					: APP_JS_LIBS + "/echarts/echarts",
		 "jquery"					: APP_JS_LIBS + "/jquery/jquery.main",
		 "jquery.base"				: APP_JS_LIBS + "/jquery/jquery.base",
		 "jquery.dot"				: APP_JS_LIBS + "/jquery/jquery.do.template",
		 "jquery.i18n"				: APP_JS_LIBS + "/jquery/jquery.i18n.properties",
		 "jquery.mCustomScrollbar"	: APP_JS_LIBS + "/jquery/jquery.mCustomScrollbar",
		 "jquery.validate"			: APP_JS_LIBS + "/jquery/jquery.Lweight.validate",
		 "jquery.flow"				: APP_JS_LIBS + "/jquery/jquery.flow.auto",
		 "jquery.serializejson" 	: APP_JS_LIBS + "/jquery/jquery.serializejson.min",
		 "jquery.cookie"			: APP_JS_LIBS + "/jquery/jquery.cookie",
		 "jquery.pagination"		: APP_JS_LIBS + "/jquery/jquery.pagination",
		 "jquery.scrolltofixed"		: APP_JS_LIBS + "/jquery/jquery.scrolltofixed",
		 "jquery.jOrgChart"			: APP_JS_LIBS + "/jquery/jquery.jOrgChart",
		 "jquery.ui"				: APP_JS_LIBS + "/jquery/jquery-ui.min",
		 "jquery.uploadify"			: APP_JS_LIBS + "/uploadify/jquery.uploadify",
		 "jquery.Jcrop"			    : APP_JS_LIBS + "/jquery/jquery.Jcrop",		 
		 "jquery.hotkeys"		    : APP_JS_LIBS + "/jquery/jquery.hotkeys",	
		 "jquery.scroll.pagination"	: APP_JS_LIBS + "/jquery/jquery.scroll.pagination",	
		 "jquery.tagsInput"			: APP_JS_LIBS + "/jquery/jquery.tagsInput",	
		 "jquery.ztree"             : APP_JS_LIBS + "/jquery/jquery.ztree.all",
		 "jquery.tagator"			: APP_JS_LIBS + "/jquery/jquery.tagator",
		 "jquery.autoIMG"			: APP_JS_LIBS + "/jquery/jquery.autoIMG",
		 "jquery.clipboard"			: APP_JS_LIBS + "/jquery/jquery.clipboard",
		 "jquery.slider"			: APP_JS_LIBS + "/jquery/jquery.slider.min",
		 "jquery.countdown"			: APP_JS_LIBS + "/jquery/jquery.countdown.min",
		 "jquery.fileupload"               : APP_JS_LIBS + "/jquery/jquery.fileupload",
		 "jquery.fileupload.iframe"        : APP_JS_LIBS + "/jquery/jquery.iframe-transport",
		 "ueditor"					: APP_JS_LIBS + "/umeditor/umeditor",
		 "ueditor.config"			: APP_JS_LIBS + "/umeditor/umeditor.config",
		 "ueditor.language.en"		: APP_JS_LIBS + "/umeditor/lang/en/en",
		 "ueditor.language.zh"		: APP_JS_LIBS + "/umeditor/lang/zh-cn/zh-cn",
		 "pagination"				: APP_JS_LIBS + "/pagination/pagination",
		 "calendar"					: APP_JS_LIBS + "/calendar/calendar",
		 "underscore"				: APP_JS_LIBS + "/calendar/underscore-min",
		 "scrom"					: APP_JS_LIBS + "/scrom/scrom",
		 "media"					: APP_JS_LIBS + "/media/media",
		 "webuploader"				: APP_JS_LIBS + "/webuploader/webuploader",
		 "upload.ui"				: ROOT_JS + "/upload/upload.ui",
		 "upload.common"			: ROOT_JS + "/upload/upload.common",
		 "fileupload.common"		: ROOT_JS + "/upload/fileupload.common",		 
		 "upload.handler"			: ROOT_JS + "/upload/upload.handler",
		 "utils.dmap"				: ROOT_JS + "/commons/dmap",
		 "utils.cookie" 			: ROOT_JS + "/commons/cookie",
		 "utils.dtree"				: ROOT_JS + "/commons/dtree",
		 "utils.math"               : ROOT_JS + "/commons/math",
		 "utils.validate"       	: ROOT_JS + "/commons/validate",
		 "utils.scroll"				: ROOT_JS + "/commons/scroll",
		 "utils.menu"               : ROOT_JS + "/commons/menu",
		 "utils.list"				: ROOT_JS + "/commons/list",
		 "utils.loading"			: ROOT_JS + "/commons/loading",
		 "utils.string"				: ROOT_JS + "/commons/string",
		 "utils"				    : ROOT_JS + "/commons/utils",
		 "flexpaper.handlers.debug" : ROOT_JS + "/modules/preview/flexpaper.handlers.debug",
		 "flexpaper.handlers" 		: ROOT_JS + "/modules/preview/flexpaper.handlers",
		 "flexpaper" 				: ROOT_JS + "/modules/preview/flexpaper",
		 "scromapi"					: ROOT_JS + "/scromapi/scromapi",
		 "webuploader.common"			: ROOT_JS + "/upload/webuploader.common",
	  },
	 shim: {
			"bootstrap": {
				 "deps": ["jquery"]
			},
			"bootstrap.masonry": {
				 "deps": ["jquery","bootstrap"]
			},
			"bootstrap.imagesloader": {
				 "deps": ["jquery","bootstrap"]
			},
			"bootstrap.select": {
				 "deps": ["jquery","css!" + ROOT_CSS +"/framework/bootstrap/bootstrap.select.css"]
			},
			"bootstrap.datepicker": {
				 "deps": ["jquery","bootstrap","css!" + ROOT_CSS +"/framework/bootstrap/bootstrap.datetimepicker"]
			},
			"echarts":{
				
			},
			"jquery":{
				
			},
			"jquery.validate":{
				 "deps": ["jquery"]
			},
			"jquery.i18n": {
				 "deps": ["jquery"]
			},
			"jquery.mCustomScrollbar": {
				 "deps": ["jquery","css!" + ROOT_CSS +"/framework/jquery/jquery.mCustomScrollbar.css"],
			},
			"jquery.flow":{
				"deps": ["jquery"]
			},
			"jquery.base": {
				 "deps": ["jquery","bootstrap","bootstrap.select","jquery.i18n","utils"]
			},
			"jquery.dot" : {
				"deps": ["jquery"]
			},
			"jquery.cookie" :{
				"deps" : ["jquery"]
			},
			"jquery.pagination" :{
				"deps" : ["jquery"]
			},
			"jquery.scrolltofixed":{
				"deps" :["jquery"]
			},
			"jquery.uploadify" : {
				"deps" : ["jquery","css!" +APP_JS_LIBS + "/uploadify/uploadify.css"]
			},
			"jquery.serializejson" :{
				"deps" : ["jquery"]
			},
			"jquery.jOrgChart" :{
				"deps" : ["jquery","css!" +ROOT_CSS +"/framework/jquery/jquery.jOrgChart.css"]
			},
			"jquery.ui" :{
				"deps" : ["jquery","css!" +ROOT_CSS +"/framework/jquery/jquery-ui.min.css"]
			},
			"jquery.Jcrop" :{
				"deps" : ["jquery","css!" +ROOT_CSS +"/framework/jquery/jquery.Jcrop.min.css"]
			},
			"jquery.scroll.pagination" :{
				"deps" : ["jquery"]
			},
			"jquery.tagsInput" :{
				"deps" : ["jquery","css!" +ROOT_CSS +"/framework/jquery/jquery.tagsInput.css"]
			},
			"jquery.ztree" :{
				"deps" : ["jquery","css!" +ROOT_CSS +"/framework/jquery/jquery.ztree.css","css!" +ROOT_CSS +"/framework/awesome/font-awesome.min.css"]
			},
			"jquery.tagator": {
				 "deps": ["jquery","css!" +ROOT_CSS +"/framework/jquery/jquery.tagator.css"]
			},
			"jquery.autoIMG": {
				"deps" : ["jquery"]
			},
			"jquery.clipboard":{
				"deps" : ["jquery"]
			},
			"jquery.slider":{
				"deps" : ["jquery"]
			},
			"jquery.fileupload":{
				"deps" : ["jquery","jquery.fileupload.iframe","css!" + ROOT_CSS +"/framework/jquery/jquery.fileupload.css"]
			},
			"upload.ui":{
				"deps": ["jquery"]
			},
			"upload.common":{
				"deps": ["jquery","jquery.uploadify"]
			},
			"fileupload.common":{
				"deps": ["jquery","jquery.fileupload"]
			},
			"upload.handler":{
				"deps": ["jquery"]
			},
			"utils.dmap": {
				"deps": ["jquery"]
			},
			"utils.cookie" : {
				"deps" : ["jquery","jquery.cookie"]
			},
			"utils.dtree": {
				"deps": ["jquery","jquery.ztree"]
			},
			"utils.math" : {
				"deps" : ["jquery"]
			},
			"utils.validate" : {
				"deps" : ["jquery"]
			},
			"utils.scroll"	:{
				"deps" : ["jquery"] 
			},
			"utils.menu"	:{
				"deps" : ["jquery","jquery.scrolltofixed"] 
			},
			"utils.list"	:{
				"deps" : ["jquery"] 
			},
			"utils.string"	:{
				"deps" : ["jquery"] 
			},
			"utils.loading"	:{
				"deps" : ["jquery","css!" +ROOT_CSS +"/deodio.loading.css",] 
			},
			"ueditor.config" :{
				"deps" : ["jquery","ueditor"]
			},
			"ueditor" :{
				"deps" : ["jquery","css!" +APP_JS_LIBS + "/umeditor/themes/default/css/umeditor.css"]
			},
			"scrom"   :{
				"deps": ["jquery"]
			},
			"media"   :{
				"deps": ["jquery"]
			},
			"pagination":{
				 "deps": ["jquery","jquery.pagination"]
			},
			"calendar":{
				 "deps": ["jquery","underscore","css!" + ROOT_CSS +"/framework/bootstrap/bootstrap.calendar.css"]
			},
			"utils": {
				 "deps": ["jquery","utils.loading"]
			},
			"flexpaper.handlers.debug":{
				"deps": ["jquery"]
			},
			"flexpaper.handlers":{
				"deps": ["jquery"]
			},
			"flexpaper":{
				"deps": ["jquery"]
			},
			"webuploader":{
				 "deps": ["jquery","css!" + ROOT_CSS +"/webuploader.css"]
			},
			"webuploader.common":{
				"deps": ["jquery","webuploader"]
			}
			
	 }
};

