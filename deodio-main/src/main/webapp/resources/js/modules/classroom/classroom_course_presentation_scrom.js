define(["jquery","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.validate","jquery.scrolltofixed","ueditor","jquery.mCustomScrollbar",
	"ueditor.config","upload.ui","upload.common","upload.handler","bootstrap.select","utils.scroll","scrom"], function($,cookie,doT,paging) {
		var _init = function(){
			_onLoadScormItemList();
		};
		
		var _onLoadScormItemList = function(){
			var url="/presentation/scrom/get_item_list.html",data={
					 presentationId:$.trim($("#presentationId").val())
			};
			postAjaxRequest(url, data, function(result){
				
				var template = doT.template($("#scrom_data_template").text());	
				$('#items').empty().append(template({"data":result.data}));
				for(var index = 0 ; index < result.data.length; index++){
					if(index == 0){
						onChooseScromItem(result.data[index].launch);
					}
				}
			});
		};
		
		onChooseScromItem = function(url,e){
//			setSelectedLiActiveStatus(e);
			$("#rightSco").attr("src",imgStatic+url);
		};
		
		initApi();
		
		
		initPresentationScromItems = function(){
			_init();
		};
		
	});