define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.validate", "jquery.scrolltofixed",
		"jquery.mCustomScrollbar" ], function($, cookie, doT, paging) {
		var _init = function(){
			onLoadPackageItemList();
		};
		
		onLoadPackageItemList = function(){
			var url="/presentation/package/get_item_list.html",data={
					 presentationId:$.trim($("#presentationId").val())
			};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#pack_data_template").text());	
				$('#items').empty().append(template({"data":result.data}));
				
				for(var index = 0; index < result.data.length; index++){
					if(index == 0){
						onChoosePackageItem($('#'+result.data[index].id));
					}
				}
			});
		};
		
		onChoosePackageItem=function(obj){
//			setSelectedLiActiveStatus(obj);
			var htmlDir=null;
			var fileSrc=$(obj).attr("data-src");
			var fileExt=$(obj).attr("data-package-ext");
			var fileName=$(obj).attr("data-file-name");
			
			if(fileExt=="doc" || fileExt=="docx" || fileExt=="pdf"){
				htmlDir=fileSrc+getFileNameNoEx(fileName)+"/"+getFileNameNoEx(fileName)+".html"
			}
			console.log(htmlDir);
			$("#itemFramePanel").attr('src',htmlDir)
		};
		
		getFileNameNoEx=function(filename){
			if ((filename != null) && (filename.length> 0)) {
				var dot = filename.lastIndexOf('.'); 
				if ((dot >-1) && (dot < (filename.length))) { 
					return filename.substring(0, dot); 
				}
			}
			return filename; 
		};
		
		initPresentationPackItems = function(){
			_init();
		};

});