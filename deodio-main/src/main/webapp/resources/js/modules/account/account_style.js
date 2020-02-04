	define(["jquery","utils.cookie","utils","jquery.base","jquery.scrolltofixed","upload.common","jquery.Jcrop","jquery.validate"], function($,cookie) {
		
		var _init = function(){
			 
			
		};
	
		_init();
		
		
		
		$('#urlForm').myValidate({
			formCall:function(){ submitUrl();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:false},
			errorStyle:{errorRegion:"error-custom",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		
		
		
		
		var submitUrl = function(){
			
			var url="/account/update_style_url.html",data={
					url:$.trim($("#input_url").val()),
					type:$("#input_type").val(),
					uKeyId:$("#uKeyId").val()
			}
			
			postAjaxRequest(url, data, function(result){
				alertMsg("保存成功");
			});
		}
		
		var isTheumb="";
		if($("#input_type").val()==1){
			isTheumb=true;
		}else{
			false;
		};
		
		
		uploadProcess('uploadFile','*.jpg;*.png;',{isTheumb:isTheumb,accountId:cookie.getCookie('CAID'),attachRelType:"account",},ctx+'/commons/uploadAttach.html?r=' + Math.floor(Math.random() * 100)
				,function(fileName){
					$("#uploadFileName").html(fileName);
				}
				,function(data){
					$("#hidAttDir").val(data.attachDir);
					$("#hidAttUrl").val(data.attachUrl);
					$("#upload_div").hide();
					$("#upload_info").hide();
					var srcimg = imgStatic+data.attachDir;
					if($("#input_type").val()==1){
						
						jcropFunc("log_img",srcimg,360,360,214,38);						
					}else{
						$("#log_img_div").show();
						$("#img_id").attr("src",srcimg);
					}
					
				},true);
		
	   
		uploadFun =  function(){
			var url="/account/update_style_logo.html",data={
					attachDir:$("#hidAttDir").val(),
					attachUrl:$("#hidAttUrl").val(),
					x:Math.round($("#x1").val()),
					y:Math.round($("#y1").val()),
					logWidth:Math.round($("#w").val()),
					logHeight:Math.round($("#h").val()),
					setType:$("#input_type").val()
			};
			postAjaxRequest(url, data, function(result){
				$("#log_img_div img").attr('src',imgStatic+data.attachUrl+"?r="+new Date().getTime());
			});
		}
		
		var  jcrop_api;
		 jcropFunc  = function(imgId,imgSrc,defaultH,defaultW,cutImgH,cutImgW){
			var realHeight = 0,realWidth = 0;
			$("#"+imgId).attr("src",imgSrc).load(function(){
				realWidth = this.width;
				realHeight = this.height;
				if(realHeight>=defaultH){
					$("#"+imgId).attr("height",defaultH);
				};
				if(realWidth>=defaultW){
					$("#"+imgId).attr("width",defaultW);
				};
				$("#log_img_div").show();
				
				$("#"+imgId).Jcrop({					
				     aspectRatio: cutImgH / cutImgW,
				     addClass:"image-center",
				     onChange: showCoords,
				     onSelect: showCoords
				},function(){
					 jcrop_api = this;
					this.setSelect([0, 0, 214, 38]);
					
					
				});
				
				$(".image-center").css("top",(defaultH-$(".jcrop-holder").height())/2+"px")
				
				
			});
		}
		
		var showCoords =  function(c){
	
			  $('#x1').val(c.x);
			  $('#y1').val(c.y);

			  $('#w').val(c.w);
			  $('#h').val(c.h);
		};
		
		deleteUploadFun = function(type){
		
			var url="/account/delete_style_img.html",data={
				attachDir:$("#hidAttDir").val()	
			};
			
			postAjaxRequest(url, data, function(result){
				if(type==1){
					jcrop_api.destroy();
				};
				$("#log_img_div").hide();
				$("#log_img").attr("src","");
				$("#upload_div").show();
			})
			
			
		}
		
	});

