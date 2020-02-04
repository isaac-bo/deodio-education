	define(["jquery","utils.menu"], function($,menu) {
		
		var init_deodio_application = function(){	
			
			
			menu.onFixedMenu();
			menu.onMouseMenu();
			menu.onSelectUser();
		};
		init_deodio_application();
	});