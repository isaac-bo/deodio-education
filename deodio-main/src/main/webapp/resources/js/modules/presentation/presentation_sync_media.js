	define(["jquery","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.clipboard","jquery.validate","jquery.scrolltofixed","ueditor",
			"ueditor.config","upload.ui","upload.common","upload.handler","media","jquery.ui"], function($,cookie,doT,paging) {

		var valite = false;
		var isMouseOverFlag = false;
		var isPlay = false;
		var media_index = 0;
		
		clipsLength = 0;
		currentTime = 0;
		medias = [];
		points = [];
		comments = [];
		timeLineWidth = 0;
		var _init = function(){	
			
			var _screen = window.screen.height;
			if(_screen==768){
				$(".left_video").css("min-height",260);
				$(".right_video").css("min-height",260);
			};
			
			_loadSlidesContents();
			_loadQuizsContents();
			_loadSurveyContents();      
			_initProcessBar();
			_drawFirstSlide();
//			
			_dropItems();
			timeOnscroll();
			
		};
		
		var _pointContainer = function(type,id){
			var container = '';
			if(type == 'sync'){
				container = $('.sync_point_thumbnail_' + id +' .btm');
			}else if(type == 'quiz'){
				container = $('.quiz_point_thumbnail_' + id +' .btm');
			}else if(type == 'survey'){
				container = $('.survey_point_thumbnail_' + id +' .btm');
			}
			return container;
		}
		
		var _dragItems = function(){
			
			$('li[id^="_orginal_"]').draggable({
				   revert:true,
				   revertDuration:0,
				   helper: function(){
					   var clone = $('<div class="ui-clone">' +$(this).html()+ '</div>');
					   clone.appendTo('body');
					   return clone;
				   },	
				   cursor: "move",
				   containment: "body",
				   appendTo:'body',
				   scroll: false,
				   zIndex: 2700
			 });
			
			
//			$('li[id^="_orginal_quiz_"]').draggable({
//				   revert:true,
//				   revertDuration:0,
//				   helper: function(){
//					   var clone = $('<div class="ui-clone">' +$(this).html()+ '</div>');
//					   clone.appendTo('body');
//					   return clone;
//				   },	
//				   cursor: "move",
//				   containment: "body",
//				   appendTo:'body',
//				   scroll: false,
//				   zIndex: 2700
//			 });
		}
		
		var _dropItems = function(){
			
			$('.syncPointLine').droppable({
				drop:function(event,ui){
					//$(this).children().remove();
					if(ui.draggable.clone().attr('id').split('_')[2] == 'slides'){
						var slideId = ui.draggable.clone().attr('id').split("_")[3];
						var pointId = randomString(32);
						console.log((event.clientX - 200 + $('.right_con_height').scrollLeft()) + '======' +$('.right_con_height').scrollLeft()+ '=========' + Math.floor((event.clientX - 200 + $('.right_con_height').scrollLeft())/160));
						var time  = Math.floor((event.clientX - 200 + $('.right_con_height').scrollLeft())/160);
						var imgSrc = ui.draggable.clone().find('.pic_size').attr('src');
						points.push({'id':pointId,'slideId':slideId,'type':'sync','time':time,'thumbnail':imgSrc});
						$(this).children().remove();
						_initPoints(1);
					}
				}
			});
			
			
			$('.quizPointLine').droppable({
				drop:function(event,ui){
					//$(this).children().remove();
					if(ui.draggable.clone().attr('id').split('_')[2] == 'quiz' || ui.draggable.clone().attr('id').split('_')[2] == 'survey'){
						
						var quizId = ui.draggable.clone().attr('id').split("_")[3];
						var type = ui.draggable.clone().attr('id').split('_')[2] == 'quiz' ? 'quiz' : 'survey';
						var pointId = randomString(32);
						var name= ui.draggable.clone().find('dt').html();
						var desc = ui.draggable.clone().find('dd').html();
						var time  = Math.floor((event.clientX - 200 + $('.right_con_height').scrollLeft())/160);
						points.push({'id':pointId,'quizId':quizId,'type':type,'time':time,'name':name,'desc':desc});
						$(this).children().remove();
						_initPoints(2);
					}
				}
			});
		};
		
		var _jump2MediaPosition = function(){
			var duration = 0;
			var m_index = 0;
			
			for(var index = 0; index < medias.length;index++){
				duration += medias[index].mediaLength;
				if(currentTime < duration){
					m_index = index;
					break;
				}
			}
			
			media_index = m_index;
			$(".right_video_content").jPlayer("clearMedia");
			$(".right_video_content").jPlayer("setMedia", {
				// 播放内容
				m4v: imgStatic + '/' + medias[media_index].mediaUrl
			});
			
			
		};
		
		var _initPoints = function(type){
			
			var sIndex = 0;
			var qIndex = 0;
			
			for(var index = 0; index < points.length; index++){
				if(points[index].type == 'sync' && (type == null||type == undefined||type == 1) ){
					var syncPointImage = " <div class='material_pic sync_point_thumbnail_"+points[index].id+"_"+points[index].slideId+"' onmouseover='javascript:onMouseOverPointThumbnail(event,\""+points[index].id+"_"+points[index].slideId+"\",\"sync\")' onmouseleave='javascript:onMouseOutPointThumbnail(event,\""+points[index].id+"_"+points[index].slideId+"\",\"sync\")' style='position: relative;left:" + (points[index].time*160 - sIndex*160)+"px'>"+
		         	 					 "  <img src='"+points[index].thumbnail+"' alt='' class='pic_size'>"+
		         	 					 "  <span class='btm' onmouseover='javascript:onMouseOverSubPointThumbnail(event,\""+points[index].id+"_"+points[index].slideId+"\",\"sync\")' onmouseleave='javascript:onMouseOutSubPointThumbnail(event,\""+points[index].id+"_"+points[index].slideId+"\",\"sync\")' style='display:none;'><button type='button' style='float: right;' onclick='javascript:onRemovePoint(\""+points[index].id+"_"+points[index].slideId+"\",\"sync\",this)'><img src='"+ctx+"/resources/img/editor/del_icon.png' alt=''></button></span>" 
		        	 					 " </div>";
					$('.syncPointLine').append(syncPointImage);
					sIndex += 1;
				}else if((points[index].type == 'quiz' || points[index].type == 'survey') && (type == null||type == undefined||type == 2)){
					var quizPointImage = "";
					if(points[index].type == 'quiz'){
						quizPointImage = " <div class='material_pic quiz_point_thumbnail_"+points[index].id+"_"+points[index].quizId+"' onmouseover='javascript:onMouseOverPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"quiz\")' onmouseleave='javascript:onMouseOutPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"quiz\")' style='position: relative;left:" + (points[index].time*160 - qIndex*160)+"px;background-color: #45526c; border: 2px solid rgb(101, 170, 221);'>"+
										 "  <div class='quiz_thumbnail_left'></div>"+
										 "  <div class='thumbnail_right'>"+points[index].name+"</div>"+
										 "  <span class='btm' onmouseover='javascript:onMouseOverSubPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"quiz\")' onmouseleave='javascript:onMouseOutSubPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"quiz\")' style='display:none;'><button type='button' style='float: right;' onclick='javascript:onRemovePoint(\""+points[index].id+"_"+points[index].quizId+"\",\"quiz\",this)'><img src='"+ctx+"/resources/img/editor/del_icon.png' alt=''></button></span>" 
										 " </div>";
					}else{
						quizPointImage = " <div class='material_pic survey_point_thumbnail_"+points[index].id+"_"+points[index].quizId+"' onmouseover='javascript:onMouseOverPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"survey\")' onmouseleave='javascript:onMouseOutPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"survey\")' style='position: relative;left:" + (points[index].time*160 - qIndex*160)+"px;background-color: #45526c; border: 2px solid rgb(113, 174, 145);'>"+
										 "  <div class='surv_thumbnail_left'></div>"+
										 "  <div class='thumbnail_right'>"+points[index].name+"</div>"+
										 "  <span class='btm' onmouseover='javascript:onMouseOverSubPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"survey\")' onmouseleave='javascript:onMouseOutSubPointThumbnail(event,\""+points[index].id+"_"+points[index].quizId+"\",\"survey\")' style='display:none;'><button type='button' style='float: right;' onclick='javascript:onRemovePoint(\""+points[index].id+"_"+points[index].quizId+"\",\"survey\",this)'><img src='"+ctx+"/resources/img/editor/del_icon.png' alt=''></button></span>" 
										 " </div>";
					}
					
					$('.quizPointLine').append(quizPointImage);
					qIndex += 1;
				}
			}
		};
		
		var _drawComments = function(){
			for(var jndex = 0; jndex < comments.length; jndex++){
				var template = doT.template($("#comment_point_line_template").text());
				$('.playbar_box').append(template({'commentId':comments[jndex].id,'position':(comments[jndex].comment_time/clipsLength * 100) + '%'}));
			}
		};
		
		var _drawFirstSlide = function(){
			$('#_sync_point_hidden').val('');
			for(var index = 0; index < points.length; index++){
				if(points[index].type == 'sync' && points[index].time == Math.floor(0)){
					
					if($('#_sync_point_hidden').val() != points[index].id){
						$('.left_video_content').html("<img class='_sync_point_"+points[index].id+"' src='"+points[index].thumbnail+"' alt='' class='pic_size'>");
						$('#_sync_point_hidden').val(points[index].id);
						_calcImagesSize($('.left_video_content ._sync_point_' + points[index].id),$('.left_video_content').width(),$('.left_video_content').height(),false);
					}
				}
			};
		};
		
		var _drawPoints = function(time){
			for(var index = 0; index < points.length; index++){
				console.log(points[index].time + '-----------' + Math.floor(time) + '---------' + time);
				if(points[index].type == 'sync' && points[index].time == Math.floor(time)){

					if($('#_sync_point_hidden').val() != points[index].id){
						$('.left_video_content').html("<img class='_sync_point_"+points[index].id+"' src='"+points[index].thumbnail+"' alt='' class='pic_size'>");
//						$('._sync_point_' + points[index].id).autoIMG();
						$('#_sync_point_hidden').val(points[index].id);
						_calcImagesSize($('.left_video_content ._sync_point_' + points[index].id),$('.left_video_content').width(),$('.left_video_content').height(),false);
					}
				}
			};
			
			for(var jndex = 0; jndex < comments.length; jndex++){
//				alert(comments[jndex].comment_time + '=====' + comments[jndex].comment_source_type + "==========" + comments[jndex].comment_y + "=========" +comments[jndex].    comment_x);
				if($("#comment_" + comments[jndex].id).length <= 0){
					if(time >= comments[jndex].comment_time  && time <= comments[jndex].comment_time + 3){
						var template = doT.template($("#comment_point_template").text());
						var commentId = comments[jndex].id;
						var containerType = comments[jndex].comment_source_type;
						var posX = containerType == 1 ? comments[jndex].comment_x * $('.right_video_content').width() / 1600 : comments[jndex].comment_x * $('.left_video_content').width()/1600;
						var posY = containerType == 1 ? comments[jndex].comment_y * $('.right_video_content').height() / 900 : comments[jndex].comment_y * $('.left_video_content').height()/900;
						if(containerType == 1){
							$('.right_video_content').append(template({'commentId':commentId,'posX':posX,'posY':posY}));
						}else{
							$('.left_video_content').append(template({'commentId':commentId,'posX':posX,'posY':posY}));
						}
					}
				}else{
					if(time < comments[jndex].comment_time  || time > comments[jndex].comment_time + 3){
						$("#comment_" + comments[jndex].id).remove();
					}
				}
				
				
				
			}
		};
		
		var _playMedia = function(){
			try{
				
				
				if(!$("#media_play").hasClass("jq_playing")){
					$("#media_play").hide().addClass("jq_playing");
					$("#media_pause").show();
				};
			
				
				isPlay = true;	
				var playTime = currentTime;
				for(var index = 0; index < media_index; index++){
					playTime = currentTime - medias[index].mediaLength;
				}
			
				console.log('==播放====' + currentTime + '====播放时间==' + playTime +'==播放index===' + media_index);
			
				$(".right_video_content").jPlayer("play",playTime); 
				
				
				
				if(!$('.paly_btn').is(':hidden')){
					$('.paly_btn').hide();
				}
			}catch(e){
				
			}
		};
		
	
		
		var _prevMedia = function(){
			console.log('--------- previouse media -------------' + currentTime);
			try{
				if(isPlay != true){
					var _previouseTime = -1;
					for(var index = 0; index < points.length; index ++){
						if(points[index].time < currentTime && points[index].type == 'sync'){
							if(_previouseTime != -1 && _previouseTime <  points[index].time){
								_previouseTime = points[index].time;
							}else if(_previouseTime == -1){
								_previouseTime = points[index].time;
							}
						}
					}
					currentTime = _previouseTime;
					_jump2MediaPosition();
					currentTime = _previouseTime;
					var playTime = _previouseTime;	
					for(var index = 0; index < media_index; index++){
						playTime = currentTime - medias[index].mediaLength;
					}
				
					console.log('======' + currentTime + '======' + playTime +'=====' + media_index);
					
					$(".right_video_content").jPlayer("play",playTime); 
					$(".currentLength").html(formatTimes(currentTime));
					$(".over").css('width',currentTime/clipsLength*100+"%");
					$(".right_video_content").jPlayer("pause"); 
				}
				
			}catch(e){
				
			}
			
		};
		
		var _nextMedia = function(){
			console.log('--------- next media -------------' + currentTime);
			try{
				if(isPlay != true){
					var _nextTime = -1;
					for(var index = 0; index < points.length; index ++){
						if(points[index].time > currentTime && points[index].type == 'sync'){
							if(_nextTime != -1 && _nextTime >  points[index].time){
								_nextTime = points[index].time;
							}else if(_nextTime == -1){
								_nextTime = points[index].time;
							}
						}
					}
					currentTime = _nextTime;
					_jump2MediaPosition();
					currentTime = _nextTime;
					var playTime = _nextTime;	
					for(var index = 0; index < media_index; index++){
						playTime = currentTime - medias[index].mediaLength;
					}
				
					console.log('======' + currentTime + '======' + playTime +'=====' + media_index);
					
					$(".right_video_content").jPlayer("play",playTime); 
					$(".currentLength").html(formatTimes(currentTime));
					$(".over").css('width',currentTime/clipsLength*100+"%");
					$(".right_video_content").jPlayer("pause"); 
				}
				
			}catch(e){
				
			}
		};
		
		var _calcImagesSize = function(obj,w,h,isCss){
			
			obj.each(function(){
				
				var img = $(this);
				var theImage = new Image();
				theImage.src = $(this).attr("src");
				theImage.onload = function(){
					var nW = adjustW(theImage.width,theImage.height,w,h);
					var nH = adjustH(theImage.width,theImage.height,w,h);
					//img.css('width',nW + 'px').css('height',nH + 'px');
					img.css('width',nW + 'px').css('height','100%');
					
					if(isCss == true){
					 
						if(w/h < nW/nH){
							img.css('margin-top', (h-nH)/2 + 'px');
						}else{
							img.css('margin-left', (w-nW)/2 + 'px');
						}	
					}
				};
			});
		};
		
		var _initIndicator = function(length){
			
			var indicator = "";
			for(var index = 0; index<length; index++){
				indicator += " <div class='cm' style='left:"+index*160+"px;'><span>"+formatTimes(index)+"</span>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
								" <div class='mm'></div>"+
							" </div>";
			}
			return indicator;
		};
		
		var _initMediaImage = function(index,mediaLength,mediaCover){
			var mediaImage = " <div class='material_pic' style='position: relative;left:" + (mediaLength*160 - index*160)+"px'>"+
                              "  <img src='"+imgStatic+"/"+mediaCover+"' alt='' class='pic_size'>"+
                              "</div>";
			
			$('.mediaLine').append(mediaImage);
		};
		
		var _initSyncPoints = function(data){
			for(var index = 0; index < data.length; index++){
				points.push({'id':data[index].id,'slideId':data[index].syncSlideId,'type':'sync','time':data[index].pointTime,'thumbnail':imgStatic+"/"+data[index].pointUrl})	
			}
			_initPoints(1);
			_drawPoints(0);
			
		};
		
		var _initSyncQuizs = function(data){
			for(var index = 0; index < data.length; index++){
				points.push({'id':data[index].id,'quizId':data[index].quizId,'type':'quiz','time':data[index].quizTime,'name':data[index].quizName,'desc':data[index].quizDesc});
			}
			_initPoints(2);
		};
		
		var _initSyncSurvey = function(data){
			for(var index = 0; index < data.length; index++){
				points.push({'id':data[index].id,'quizId':data[index].surveyId,'type':'survey','time':data[index].surveyTime,'name':data[index].surveyName,'desc':data[index].surveyDesc});
			}
			_initPoints(2);
		};
		
		
		var _initMedia = function(){
			media_index = 0;
			$(".right_video_content").jPlayer({
				ready: function () {
					$(this).jPlayer("setMedia", {
						// 播放内容
						m4v: imgStatic + '/' + medias[0].mediaUrl
						
					});
				},
//				cssSelectorAncestor: '.video-item2-right',  
//				cssSelector: {  
//		            fullScreen: ".media-fullscreen",
//		            playBar: ".play-bar-box-over" 
//		        },
		        size:{
		        	width:"100%",
		        	height:"100%"
		        },
				//swf文件地址 不支持可不填
				swfPath: "",
				supplied: "webmv, ogv, m4v, m4a",//视频格式
				globalVolume: true,
				useStateClassSkin: true,
				autoBlur: false,
				smoothPlayBar: true,
				keyEnabled: true,
				fullScreen:true
			});
			
			
			
			//Bind the progress event, this event will be invoked by initialization.
			$(".right_video_content").bind($.jPlayer.event.progress , function(event) {
			
				console.log('----------- media ---------init----------');
			});
			
			$(".right_video_content").bind($.jPlayer.event.loadedmetadata,function(event){
				console.log('----------- media ---------loadedmetadata----------');
				try{
					$(".right_video_content video").css("width","100%").css("height","100%").css('background-color','#000');
					var playTime = currentTime;
					for(var index = 0; index < media_index; index++){
							
						playTime = currentTime - medias[index].mediaLength;
					}
				
					$(".right_video_content").jPlayer("play",playTime); 
					if(isPlay != true){
						$(".right_video_content").jPlayer("pause"); 
					}
					
					timeLineWidth = $("#time_line_down .bottom_list").outerWidth()-$("#time_line_down").outerWidth();
				}catch(e){
					
				}
				
				//check whether exist the sync point, quiz, survey or comment at the init timeline.
				//_drawPoints(playTime);
			});
			
			//Bind the ended event, this event will be invoked by ending.
			$(".right_video_content").bind($.jPlayer.event.ended,function(event){
				console.log('----------- media ---------ended----------' + (media_index + 1) + '--------' + medias.length);
				_index=1,_tempScroll=0;
				if((media_index + 1) < medias.length ){
					
					media_index += 1;
					$(".right_video_content").jPlayer("clearMedia");
					$(".right_video_content").jPlayer("setMedia", {
						// 播放内容
						m4v: imgStatic + '/' + medias[media_index].mediaUrl
					});
				}else{
					try{
						media_index = 0;
						currentTime = 0;
						$("#media_play").show().removeClass("jq_playing");
						$("#media_pause").hide();
						$(".right_video_content").jPlayer("pause"); 
						$(".over").css('width',"0%");
						_drawPoints(0);
						isPlay = false;
					}catch(e){
						
					}
				}
			});
			
			//Bind the timeup event, this event will be invoked by time update on process bar.
			$(".right_video_content").bind($.jPlayer.event.timeupdate, function(event) {
				console.log(isPlay+'----------- media ---------timeupdate----------' + valite);
				if(isPlay == true && valite == false){
					currentTime = 0;
					for(var index = 0; index < media_index; index++){
						
						currentTime += medias[index].mediaLength;
					}
					currentTime += event.jPlayer.status.currentTime;
					$(".currentLength").html(formatTimes(currentTime));
					
				
					
					$(".over").css('width',currentTime/clipsLength*100+"%");
					//时间轴
					//var _currentTimePointer = $("#time_pointer").position().left;
					
					
					console.log(170+(160*currentTime)-(currentTime-1)*160+'-----------time_pointer----------'+currentTime);
					
					
					
					if($("#time_line_down").scrollLeft()==timeLineWidth){
						$("#time_pointer").css('left',$("#time_pointer").position().left+160);
						//console.log(_index+"-----------------index------"+(clipsLength - currentTime)+"-------");
					}else{
						$("#time_pointer").css('left',170+(160*currentTime)-(currentTime-1)*160);
					};
					
					$("#time_line_down").scrollLeft((160 * currentTime)-160);
					$("#time_pointer").css('left',170+(160*currentTime)-(currentTime-1)*160);
					
					_drawPoints(currentTime);
				}
			});
			
			//Bind the resize event, this event will be invoked by resize the media screen. such as zoom in / out.
			$(".right_video_content").bind($.jPlayer.event.resize, function(event) {
//				isFullScreen == false?_screenZoomOut():_screenZoomIn();
				$(".over").css('width',currentTime/currentTime*100+"%");
			});
			
			$('.play_media').click(function(){
				_playMedia();
				
			});
			
			$('.prev_media').click(function(){
				_prevMedia();
			});
			
			$('.next_media').click(function(){
				_nextMedia();
			});
			
			$('.paly_btn').click(function(){
				_playMedia();
			});
			 
		};
		
		var _loadMediaContents = function(data){
			
			for(var index = 0; index < data.length; index++){
				_initMediaImage(index,clipsLength,data[index].mediaCover);
				medias.push(data[index]);
				clipsLength += data[index].mediaLength;
			}
			
			//$('.right_con_height .bottom_list').css('width', 160 * clipsLength + 'px');
			
			
			
			//$('.ruler').css('width', 160 * clipsLength + 'px');
			$('.maxLength').html(formatTimes(clipsLength+1));
			$('.ruler').html(_initIndicator(clipsLength));
			
			_initMedia();
			
			//初始化时间轴高度
			var bodyHeight = document.body.clientHeight;
			var vidoHeight = $(".video_con").height()+130;
			var timeScaleHeight =  $(".time_scale").height()+40;
			var timeHeight = (bodyHeight-(timeScaleHeight+vidoHeight));
			
			
			$(".height_auto").css("height",timeHeight+'px');
		
		};
		
		var _loadSyncComments = function(){
			var url="/presentation/sync/comment/list.html",data={
					presentationId:$.trim($('#presentationId').val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					comments = result.data;
					var template = doT.template($("#comment_list_template").text());
					$('#comments').html(template({data:result.data}));
					_drawComments();
					
//					$('[id^=copy_]').each(function(){
//						var clipboard = new Clipboard('#'+$(this).attr('id'));
//					});
					
				
				}
			});
		};
		
		var _loadSlidesContents = function(){
			
			var url="/presentation/sync/content.html",data={
					presentationId:$.trim($('#presentationId').val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var template = doT.template($("#slides_data_template").text());
					$('#medias').html(template({data:result.data.slides}));
					
					_loadMediaContents(result.data.medias);
					_initSyncPoints(result.data.points);
					_initSyncQuizs(result.data.quizs);
					_initSyncSurvey(result.data.survey);
					_loadSyncComments();
					_calcImagesSize($('.material_pic .pic_size'),160,90,true);
					_calcImagesSize($('.slides_pic .pic_size'),265,142,true);
					_dragItems();
				}
			});
		};
		
		var _loadQuizsContents = function(){
			var url="/quiz/sync/content.html",data={
				
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var template = doT.template($("#quiz_data_template").text());
					$('#quiz').html(template({data:result.data}));
					_dragItems();
				}
			});
		};
		
		var _loadSurveyContents = function(){
			var url="/survey/sync/content.html",data={
					
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var template = doT.template($("#survey_data_template").text());
					$('#survey').html(template({data:result.data}));
					_dragItems();
				}
			});
		};
		
		
		var _initProcessBar = function(){
		
			$(".over_point").mousedown(function (event) {
				_prevent(event);
				var changeX,process;
				valite = true;
				$(".video_play_bar").mousemove(function (event) {
					if (valite == false) return;
					console.log('--play-bar-box-black--- track move x --------- mousemove $(".play-bar-box-over").offset().left---- :' + $(".over_point").offset().left);
//					alert($(".over_point").offset().left);
					changeX = event.clientX - 194;
				 	changeX = changeX <= -2 ? -2 : 
					  			(changeX >= $('.playbar_box').css('width').replace('px','') - 2 ? 
					  					$('.playbar_box').css('width').replace('px','') - 2: changeX);
//				  
				  	process = changeX / ($('.playbar_box').css('width').replace('px',''))*100;
				  	currentTime = clipsLength * changeX / $('.playbar_box').css('width').replace('px','');
				  	_jump2MediaPosition();
				  	$(".over").css('width', process + "%");
	              	$(".currentLength").html(formatTimes(currentTime));
//	              
//	              _echoComments();
	              
	              
				}).mouseup(function(event){
					_prevent(event); 	
					valite = false;
				});
				
				
			
		    }).mouseup(function(event){
		    	_prevent(event); 	
		    	valite = false;
		    	return false;
		    });
			
			
			//init click handler for clicking indicator
			$('.playbar_box').click(function(event){
				
				if(isPlay == false){
				
					var changeX = event.clientX - 194;
					var process;
					changeX = changeX <= -2 ? -2 : 
			  			(changeX >= $('.playbar_box').css('width').replace('px','') - 2 ? 
			  					$('.playbar_box').css('width').replace('px','') - 2: changeX);
	//	  
				  	process = changeX / ($('.playbar_box').css('width').replace('px',''))*100;
				  	currentTime = clipsLength * changeX / $('.playbar_box').css('width').replace('px','');
				  	console.log('--------- current time -------------' + currentTime);
				  	_jump2MediaPosition();
				  	
				  	$(".over").css('width', process + "%");
			      	$(".currentLength").html(formatTimes(currentTime));
				}
			});
		};
		
		_slideUp = function(){
			var _leftContainerCss_BEG = {width:'100%'};
			$(".btm_edit_bar").animate(_leftContainerCss_BEG, "normal");
			$('.btn_ti').show();
			$(".btm_edit_bar").show();
			$('.arrow').addClass('icon-double-angle-left').removeClass('icon-double-angle-right');
			$('#comment_content').val('');
			$("#current_point_Time").text(formatTimes(currentTime));
		};
		
		_slideDown = function(){
			var _leftContainerCss_END = {width:'0px'};
			$(".btm_edit_bar").animate(_leftContainerCss_END, function(){
				$('.btn_ti').hide();
				$(".btm_edit_bar").hide();
				$('.arrow').addClass('icon-double-angle-right').removeClass('icon-double-angle-left');
			});
				
		};
		
		
		onToggleCommentToolBar = function(){
			
			if($('.btm_edit_bar').is(":hidden")){
				_slideUp();
			}else{
				_slideDown();
			}
		};
		
		
		
		
		onMouseOverPointThumbnail = function(event,id,type){
			console.log('-------- onMouseOverSyncPointThumbnail ---------' + isMouseOverFlag);
			_prevent(event); 	
			if(isMouseOverFlag == false){
				
				var container = _pointContainer(type,id);
				if(container.is(':hidden')){
					container.slideDown();
				}
				setInterval(function(){
					if(!container.is(':hidden')&&isMouseOverFlag == false){
						container.slideUp(500);
					}
				}, 3000 ); 
				
			}
		};
		
		
		onMouseOutPointThumbnail = function(event,id,type){
			isMouseOverFlag = false;
//			_prevent(event); 	
////			if(checkHover(event,this)){
//				setInterval(function(){
//					console.log($('.sync_point_thumbnail_' + id +' .btm').is(':hidden') + '-------- onMouseOutSyncPointThumbnail ---------' + isMouseOverFlag);
//					if(!$('.sync_point_thumbnail_' + id +' .btm').is(':hidden')&&isMouseOverFlag == false){
//						$('.sync_point_thumbnail_' + id +' .btm').slideUp(500);
//					}
//				}, 1000 ); 
////			}
		};
		
		
		onMouseOverSubPointThumbnail = function(event,id,type){
			console.log('-------- onMouseOverSubSyncPointThumbnail ---------' + isMouseOverFlag);
			_prevent(event); 
			isMouseOverFlag = true;
			var container = _pointContainer(type,id);
			if(!container.is(':hidden')){
				container.show();
			}
		};

		
		onMouseOutSubPointThumbnail = function(event,id,type){
			isMouseOverFlag = false;
			_prevent(event); 
		};_pointContainer
		
		onRemovePoint = function(id,type,obj){
			var pointId = id.split('_')[0];
			var compId = id.split('_')[1];
			var _points = [],_index=0;
			for(var index = 0; index < points.length; index++){
				if((points[index].id == pointId && points[index].slideId == compId && type == 'sync') ||
						(points[index].id == pointId && points[index].quziId == compId && (type == 'survey' || type == 'quiz'))){
					_index = index; 
					break;
				}
				
			}
			
//			points = _points;
			points=$.grep(points,function(n,i){
				return i!=_index;
			});
			if(type == 'sync'){
//				$('.syncPointLine').children().remove();
				$(obj).parent().parent().remove();
				$('.syncPointLine').empty();
				_initPoints(1);
			}else  if(type == 'quiz' || type == 'survey'){
//				$('.quizPointLine').children().remove();
				$(obj).parent().parent().remove();
				$('.quizPointLine').empty();
				_initPoints(2);
			}
			
		};
		
		loadComments = function(){
			_loadSyncComments();
		};
		

		onPause = function(){
			if(isPlay == true){
				$(".right_video_content").jPlayer("pause"); 
				isPlay =false;
				$("#media_play").show().removeClass("jq_playing");
				$("#media_pause").hide();
			};
		};
		
		_onSavePresentation = function(callback){
			var url="/presentation/sync/save.html",data={
					presentationId:$.trim($("#presentationId").val()),
					currentUserId:cookie.getCookie('CUID'),
					pointsJson:JSON.stringify(points),
					mediasJson:JSON.stringify(medias)
				};
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						callback();
					}
				});
		};
		
		onSavePresentation = function(){
			
			onSave(_onSavePresentation,function(){
				 go2Page('/presentation/list.html'); 
			});
		};
		
		onPublishPresentation = function(){
			onSave(_onSavePresentation,onPublish);
		};

		
		timeOnscroll = function(){
			$("#time_line_down").scroll(function(){
				
				console.log('-------- onMouseOverSubSyncPointThumbnail ---------' + $(this).scrollLeft());
				
				var _scrollPx = $(this).scrollLeft(),_pointer = $("#time_pointer").position().left;
		    	 $("#time_scale_up").scrollLeft(_scrollPx);
		    	 
		    	var _moveLeft = _pointer - _scrollPx;
		    	
		   		console.log('-------- _moveLeft ---------' +_moveLeft );
		    	
//		    	if(_moveLeft<173){
//		    		 $("#time_pointer").hide();
//		    	};
//		    	
		    	 
	         }).mouseup(function(){
	        		console.log('-------- mouseup ---------' );
	        	 var _pointer = $("#time_pointer").position().left,_moveleft = $(this).scrollLeft();
	        	 
	        	 //var _subPx = (_pointer - _moveleft);
	        	 
	        	 var _moveLeftPx = _moveleft==0?170:170-Math.abs(_pointer - _moveleft);
	        	 
	        	 if(_moveLeftPx<=170){
	        		 if(_moveLeftPx >=170){
	        			 $("#time_pointer").show();
	        		 }else{
	        			 $("#time_pointer").hide();
	        		 };
	        		 
		    		 $("#time_pointer").css("left",_moveLeftPx); 
	        	 };
	         });

		};
		
		
		
		
		_init();
		
		
		
		//返回上一步
//		prevStep = function(){
//			go2Page('/presentation/profile.html','presentationId='+$('#presentationId').val());
//		}
		
//		popupVoiceOver = function(pointId){
//			$('#swf-body').html('');
//			 createSWFObject4LastestVersion("/DeodioDubbing.swf", "swf-body", "900", "500", {presentationId:$.trim($("#presentationId").val()),userId:$.cookie('CUID'),syncPointId:pointId,quality:'high',wmode:'opaque'}, { allowFullScreen:'true',wmode:'transparent'}, {},function(){
//		        });
//			$("#popupSWFModalLabel").html("在线录制VoiceOver");
//			$("#popupSWFModel").modal('show');
//		};
//		
		//编辑Slides
		editSlides = function(){
			go2Page('/presentation/sync/edit_slides.html','presentationId='+$('#presentationId').val());
		}
		
		editMedias = function(){
			go2Page('/presentation/sync/edit_medias.html','presentationId='+$('#presentationId').val());
		}
		goBack = function(){
			go2Page('/presentation/sync/files.html','presentationId='+$("#presentationId").val()); 
		}
	});