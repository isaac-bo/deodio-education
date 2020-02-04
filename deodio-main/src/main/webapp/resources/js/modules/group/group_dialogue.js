define(
		[ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
				"jquery.base", "jquery.validate", "bootstrap.datepicker",
				"jquery.ui", "jquery.mCustomScrollbar" ],
		function($, cookie, doT, paging) {
	
	
	var _init = function() {
		_initUI();
		loadDataList();
	};

	var _initUI = function() {
	};
	
	loadDataList = function(){
		var params = {
				keyword:$('#keyword').val()
			},url="/group/list/group_content_creator.html";
			
			postAjaxRequest(url, params, function(result){
				var template = doT.template($("#group_list_template").text());	
				$("#groupListPanel").html(template(result));
				$("#groupListPanel").mCustomScrollbar();

				$('div[id^="group_original_"]').draggable({
					   helper: "clone",	
					   cursor: "move",
					   containment: '#activeGroupPanel',
					   scroll: false 
				 });
				
				$('#activeGroupPanel').droppable({
					drop:function(event,ui){
						var str = ui.draggable.clone().attr('id').split("_");
						var isDrop = true;
						$('div[id^=group_active_]').each(function(){
							var oid = $(this).attr('id').split('_')[2];
							if(oid == str[2]){
								isDrop = false;
							}
						});
						
						if(isDrop){
						
							$('#'+ui.draggable.clone().attr('id')).html();
							var source = '<div id="group_active_'+str[2]+'" class="box2 pull-left">'+
											$('#'+ui.draggable.clone().attr('id')).html()+
										  '<div style="float: right;margin-top: 16px;cursor:pointer;position: relative;font-size: 14px;"> '+
									      ' <a class="icon-trash" style="color:white;margin-left:10px;"  onclick="javascript:remove(\''+str[2]+'\')"></a> '+
									      ' </div>' +
	                            		  '</div>';
							$('#emptyGroupPanel').before(source);
						}
					}
				});
			});
	};
	
	onSubmit = function(){
		var groupIds =[];
		$('div[id^=group_active_]').each(function(){
			var oid = $(this).attr('id').split('_')[2];
			groupIds.push(oid);
		});
		
		if(groupIds.length<=0){
			confirmMsg('您是否现在不要将该课件发布到任何小组中?',function(){
				publishItemFunc();
			});
		}else{
		
			var url="/group/set_items.html",data={
					type : $('#group_container_type').val(),
					groupIds:groupIds.join(","),
					itemId:$('#item_id').val()
			};
			postAjaxRequest(url, data, function(result){
				publishItemFunc($('#item_id').val());
				if($('#group_container_type').val()==41){
				     go2Page("/course/online/list.html");
				}else{
				     go2Page("/course/offline/list.html");
				}
			});
		}
	};
	
	onSearchGroupList = function(){
		loadDataList();
	}
	remove = function(id){
		$('#group_active_'+id).remove();
	}
	_init();
	
});
