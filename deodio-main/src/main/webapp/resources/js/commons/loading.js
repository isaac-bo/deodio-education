(function(){
        $.extend($.fn,{
            mask: function(msg,maskDivClass){
                this.unmask();
                var op = {
                    opacity:1,
                    z: 999999,
                    bgcolor: '#e4e4e5'
                };
                var original=$(window);
                var position={top:0,left:0};
                            if(this[0] && this[0]!==window.document){
                                original=this;
                                //position=original.position();
                            }
                var maskDiv=$('<div class="maskdivgen">'+
			                	'<div class="loaders" style="width:120px;height:120px;margin-top:'+((window.screen.height - 130)/2-50)+'px">'+
			                    	'<div class="loader">'+
			                    		'<div style="width: 55px; margin-top: -50px; margin-left: -18px;"><img alt="" src="'+logoUrl+'" style="width: 100px;"></div>'+
			                    		'<div class="loader-inner ball-beat" style="margin-top: 23px; margin-left: -24px;">'+
					                    	'<div></div>'+
					                    	'<div></div>'+
					                    	'<div></div>'+
					                     '</div>'+
			                    	'</div>'+
			                    '</div>'+
                			   '</div>');
                
                maskDiv.appendTo(original);
                var maskWidth=original.width();
                if(!maskWidth){
                    maskWidth=original.width();
                }
                var maskHeight=document.body.scrollHeight;
                if(!maskHeight){
                    maskHeight=original.height();
                }
                maskDiv.css({
                    position: 'absolute',
                    top:0,
                    left:0,
                    'z-index': op.z,
                    width: maskWidth,
                    height:maskHeight,
                    'background': 'rgba(35,36,42,0.4)',
                    opacity: 1
                });
                if(maskDivClass){
                    maskDiv.addClass(maskDivClass);
                }
                if(msg){
                    var msgDiv=$(msg);
                    msgDiv.appendTo(maskDiv);
                    var widthspace=(original.width()-msgDiv.width());
                    var heightspace=(original.height()-msgDiv.height());
                    maskDiv.css({
                                cursor:'wait',
                                top: $(document).scrollTop(),
                                left: $(document).scrollLeft()
                      });
                  }
                
                

                 this.disable_scroll();
 
                
                  maskDiv.fadeIn('fast', function(){
                    $(this).fadeTo('slow', op.opacity);
                });
                return maskDiv;
            },
            unmask: function(){
                     var original=$(document.body);
                         if(this[0] && this[0]!==window.document){
                            original=$(this[0]);
                      }
                      original.find("> div.maskdivgen").fadeOut('slow',0,function(){
                          $(this).remove();
                      });
                      this.enable_scroll();
                      
            },

           preventDefault:function(e) {
              e = e || window.event;
              if (e.preventDefault)
                  e.preventDefault();
              e.returnValue = false;  
            },

            keydown:function(e) {
            	var keys = [37, 38, 39, 40];
                for (var i = keys.length; i--;) {
                    if (e.keyCode === keys[i]) {
                    	e = e || window.event;
                        if (e.preventDefault)
                            e.preventDefault();
                        e.returnValue = false;  
                        return;
                    }
                }
            },

            wheel:function(e){
            	e = e || window.event;
                if (e.preventDefault)
                    e.preventDefault();
                e.returnValue = false;  
            },

            disable_scroll:function () {
              if (window.addEventListener) {
                  window.addEventListener('DOMMouseScroll', this.wheel, false);
              }
              window.onmousewheel = document.onmousewheel = this.wheel;
              document.onkeydown = this.keydown;
              $("body").css('overflow-y','hidden');
            },

            enable_scroll:function() {
                if (window.removeEventListener) {
                    window.removeEventListener('DOMMouseScroll', this.wheel, false);
                }
                window.onmousewheel = document.onmousewheel = document.onkeydown = null;  
                if(!$('body').hasClass('modal-open')){
                	 $("body").css('overflow-y','auto');
                }
            }
        });
    })();