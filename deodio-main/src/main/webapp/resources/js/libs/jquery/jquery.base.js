$(function(){
	
	jQuery.i18n.properties({
		name:'jmessage',
		path:ctx+'/resources/i18n/', 
		mode:'both',
		language:"zh_CN",
		cache:false,
		encoding:'UTF-8',
		callback: function() {
		}
	});
	
	
	$(document).on({
		dragleave:function(e){
			e.preventDefault();
		},
		drop:function(e){
			e.preventDefault();
		},
		dragenter:function(e){
			e.preventDefault();
		},
		dragover:function(e){
			e.preventDefault();
		}
	});
	
});