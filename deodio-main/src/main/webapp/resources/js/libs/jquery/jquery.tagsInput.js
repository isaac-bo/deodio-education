/**
 * 标签下拉选中
 * 	example:
 * 			$("#tagInput").tagInput({// 标签DIV ID
 * 				url:ctx+"/getcategories.html", 请求URL
 * 				params:{accountid:"1"} 参数
 * 			});
 * HTML中：
 * 	<div class="div-tags"> 外层DIV 注意样式名称 DIV-TAGS 
 *	<button type="button" class="btn_add" id="tagInput"></button> 加号图标
 *	</div>
 * 	java：后台中请将标签的ID 和 tagName 
 * 	请使用 Tags.java 实体类返回。		
 */
;(function( $ ){
	$.fn.tagInput = function(options) {
		var globalOptions  = $.extend({}, options);
			var $this = this;
			$this.on('click',function(){
			var _div_width = $(this).parent().outerWidth();	

			 $.ajax({
				  type: 'POST',
				  url: globalOptions.url,
				  data: globalOptions.params,
				  dataType: 'json',
				  async:false,
				  success: function(result){
					
						var _divPre ='<div class="tags-dropdown" style="width:'+_div_width+'px; min-height:40px;">',_divSuffix='</div>',_btnContetn='';
						$.each(result.data,function(i,v){
								
								_btnContetn+='<button pkid="'+v.id+'"  type="button">'+v.tagName+'</button>';
						})

						$this.parent().append(_divPre+_btnContetn+_divSuffix);

				  }
			 });

	
				$(".tags-dropdown").on('mouseleave',function(){
					$(this).remove();
				});


				$this.siblings('.tags-dropdown').find('button').on('click',function(){
					var _btnText=$(this).text(),_btnId=$(this).attr('pkid');
					
					if(!isHasTags($this,_btnId)){
						$this.after('<span tagsid="'+_btnId+'" class="sel_btn">'+_btnText+'<button class="sel_del"  type="button">×</button></span>');
						$this.siblings(".tags-dropdown").css("width", $this.parent().outerWidth());
						
						if(options.addfunction!=undefined){
							options.addfunction(_btnId);
						};
						
						
						$this.siblings(".sel_btn").find('button').unbind( "click" );
						$this.siblings(".sel_btn").find('button').on('click',function(){
							$(this).parent().remove();
							$this.siblings(".tags-dropdown").css("width", $this.parent().outerWidth());	
							if(options.removefun!=undefined){
								options.removefun($(this).parent().attr("tagsid"));
							};
							
						});
					};
				});
	
			});
	};
	
	var isHasTags = function(obj,value){
		var arrays = new Array(),flag=false;
		$(obj).siblings('.sel_btn').each(function(i,v){
			arrays.push($(this).attr('tagsid'));
		});
		
		if($.inArray(value,arrays) < 0){
			flag=false
		}else{
			flag=true;
		};
		
		return flag;
	};
	
	
	
})( jQuery );


