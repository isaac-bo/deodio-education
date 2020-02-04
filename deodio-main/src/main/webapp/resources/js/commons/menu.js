
(function() {
	"use strict";
	
	var menu = {
			version: '1.0.0',
			onMouseMenu: undefined,
			onSelectMenu:undefined,
			onSelectItem:undefined,
			onSelectUser:undefined,
			onLeftMenu:undefined,
			onInitLeftMenu:undefined
		}, global;
	
	if (typeof module !== 'undefined' && module.exports) {
		module.exports = menu;
	} else if (typeof define === 'function' && define.amd) {
		define(function(){return menu;});
	} else {
		global = (function(){ return this || (0,eval)('this'); }());
		global.menu = menu;
	}
	
	
	menu.onLeftMenu = function(){
		
		$('.left_menu li').mouseover(function(){
			if(!$(this).hasClass('active')){
				$(this).find('a img').attr('src',$(this).find('a img').attr('src').replace('_active.png','.png'));
				$(this).find('a img').attr('src',$(this).find('a img').attr('src').replace('.png','')+'_active.png');
			}
			
		}).mouseout(function(){
			if(!$(this).hasClass('active')){
				$(this).find('a img').attr('src',$(this).find('a img').attr('src').replace('_active.png','.png'));
			}
		});
	};
	
	
	menu.onMouseMenu = function(){
		$('._head_menu li').mouseover(function(){
			
			$(this).find('a').addClass('on');
			if($(this).attr('isChild')=='0'){
				$(this).find('a span').removeClass('up-1');
				$('.drop-down-list').show();
				$('li[id^=_sub]').hide();
				$('#_sub' + $(this).attr('id')).show();
			}
		}).mouseout(function(){
			
			var subMenu = $('#_sub' + $(this).attr('id'));
			var headMenu = $(this).attr('id');
			$('.drop-down-list').hide();
			$('li[id^=_sub]').hide();
			$(this).find('a').removeClass('on');
			$(this).find('a span').addClass('up-1');
			
			$('.drop-down-list').mouseover(function(){
				$('li[id^=_sub]').hide();
				if($('#'+headMenu).attr('isChild')=='0'){
					$(this).show();
					subMenu.show();  
					$('#'+headMenu+' a').addClass('on');
					$('#'+headMenu+' a span').removeClass('up-1');
				}
			}).mouseout(function(){
				
				$(this).hide();
				$('#'+headMenu+' a').removeClass('on');
				$('#'+headMenu+' a span').addClass('up-1');
				subMenu.hide();
			});
			
		});
	 };
	 
	 menu.onSelectMenu = function(menuPosition){
		 $('#_head_menu_'+menuPosition+' a').addClass('on');
	 };
	 
	 menu.onSelectItem = function(itemId){
		 $("#_item_id").val(itemId);
		 $(".item_select").removeClass('item_select');
		 $("#"+itemId).addClass('item_select');
	 };

	 menu.onFixedMenu = function(){
	 	 $('.header-nav').scrollToFixed({
	 		 preFixed: function() {
	 	
	 			 			$(this).find('.logo').css('background-color', 'inherit'); 
	 			 			//$(this).find('.logo a').css('background-image', 'url("'+ctx+'/resources/img/account/logo-2.png")').css('margin-top','10px');
	 			 			$(this).find('.logo a').css('background-image', 'url("'+logoUrl+'")').css('margin-top','10px');
	 		           },
	 	     postFixed: function() {
	 	    	 			$(this).find('.logo').css('background-color', '#fff'); 
	 	    	 			$(this).find('.logo a').css('background-image', 'url("'+ctx+'/resources/img/account/logo-1.png")').css('margin-top','0px');
	 	    	 		},
	 	    zIndex: 2000
	 	 });
	 };
	 
	 menu.onSelectUser = function(){
		 $('.personal-item').mouseover(function(){
			 $(".personal-item .user_up").show();
			 $(".personal-item .arrow").show();
		 })
		 .mouseout(function(){
			 $(".personal-item .user_up").hide();
			 $(".personal-item .arrow").hide();
		 })
		 ;
				 
	 };
	
}());