define(["jquery","utils.cookie", "utils.list", "utils.menu","utils","jquery.base","jquery.ui"], function($,cookie,list,menu) {
	
	var init = function(){
		menu.onSelectMenu(4);
		menu.onLeftMenu();
//		list.onFixedItems();
	};
	
	var onChangeLeftMenu = function(menuId){
		$('.left_menu li').each(function(){
			
			if($(this).attr('id') != 'menu_' + menuId){
				$(this).find('a img').attr('src',$(this).find('a img').attr('src').replace('_active.png','.png'));
			}
		});
	};
	
	
	onChooseLeftMenu = function(menuId){

		$('.left_menu li').each(function(){
			if($(this).attr('id') == 'menu_' + menuId){
				if(!$(this).hasClass('active')){
					onChangeLeftMenu(menuId);
				}
			}
			
		});
	};
	
	init();
});

