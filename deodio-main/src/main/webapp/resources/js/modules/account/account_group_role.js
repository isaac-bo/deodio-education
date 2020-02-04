	define(["jquery","utils.cookie","utils.string","jquery.dot","jquery.slider","utils","jquery.base","jquery.scrolltofixed"], function($,cookie,doT) {
		
		var targetPageCourseShortcup = "/course/shortcut.html";
        var targetPageManageGroup = "/group/list.html";
//        var targetPageManageGroup_Leader = "group/profile.html";
//        var targetPageManageGroup_creator = "group/feature.html";
		var _init = function(){
			

			$('a').click(function(e) {

					// debugger;
					e.preventDefault();// 阻止a链接的跳转行为
					var _this = $(this);
	
					// 如果为切换group_role a
					if (_this.is(".switch_a")) {
						var index = _this.attr("href");
	
						$(".login-item-color .cen_left_list")
								.removeClass("active");
						$("#" + index).addClass("active");
						return;
					}
	
					// group_role a 点击
					var type = _this.nextAll("[name='type']").val();
					var roleId = _this.nextAll("[name='groupRoleId']").val();
	
					var targePage = targetPageCourseShortcup;
					var param = "";
					$.cookie('cgrid', roleId);
					if($.cookie('CHANGE_TO')=='SELF'){
						//window.location.href = ctx + "/user/profile.html"+ "&r=" + Math.floor(Math.random() * 100);
						go2Page('/user/profile.html');
					}else{
						 // 菲快捷入口
						if (type == '1') {
							console.log('cookieCompletSelf---'+$.cookie('CHANGE_TO'));

							debugger;
							var groupId = _this.nextAll("[name='groupId']").val();
							var hasLeader = _this.nextAll("[name='hasLeader']").val();
							if (hasLeader == '1') {
								//targePage = targetPageManageGroup;
								targePage = targetPageManageGroup_Leader;
							} else {
								
								if(roleId == '10000'){
									targePage = "/group/profile.html";
								}else if(roleId == '10001'){
									targePage = "/group/features.html";
								}else if(roleId == '10002'){
//									targePage = "/course/course_viewer/list.html";
									targePage = "/group/intro.html";
								}
								 
								param = "groupId=" + groupId + "&groupRoleId=" + roleId;
								go2Page(targePage, param);
							}
							
							
							// 角色列表    快捷入口
						} else {
							
							debugger;
							param = "groupRoleId=" + roleId;
//							alert(roleId);
							if(roleId == '10001'){
								go2Page(targePage, param);
								
							}else if(roleId == '10002'){
//								targePage = "/course/course_viewer/list.html";
								targePage = "/group/intro.html";
								go2Page(targePage, param);
							}else{
								go2Page(targePage, param);
							}
						}
					}

					
				});
			
		
				var options = {
					$AutoPlay : true,
					$SlideshowOptions: {
                        $Class: $JssorSlideshowRunner$,
                        $Transitions: [],
                        $TransitionsOrder: 1,
                        $ShowLink: true
                     },
					$ArrowNavigatorOptions: {
                        $Class: $JssorArrowNavigator$
                    },
                    $BulletNavigatorOptions: {
                        $Class: $JssorBulletNavigator$
                    }
				};
                var jssor_slider1 = new $JssorSlider$('group_slider_container', options);
                $('#group_slider_container div').css('top','inherit').css('left','inherit');
                $('#group_slider_container ul').css('padding-top','3px');
                $('#group_slider_container li').css('height','25px').css('line-height','25px');
                $('.slidetip').css('width','100%').css('left','22%');
                $('.slidetip div').css('float','left').css('position','relative').css('margin-right','20px');
                
                
                $('.group_name').each(function(){
                	$(this).html(subString($(this).html(),8));
                });
                
                $('.group_desc').each(function(){
                	$(this).html(subString(removeHtmlTags($(this).html()),24));
                });
		};
		
		_init();
		 
	});

