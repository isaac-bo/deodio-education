	define(["jquery","utils.dtree","utils.cookie","utils.list","jquery.dot","pagination","utils","jquery.scrolltofixed","jquery.scroll.pagination",
	        "bootstrap.select","jquery.base","media"], function($,tree,cookie,list,doT,paging) {

		var valite = false;
		var isMouseOverFlag = false;
		var isPlay = false;
		var media_index = 0;
		
		clipsLength = 0;
		currentTime = 0;
		medias = [];
		points = [];
		comments = [];

		var _init = function(){	
			
			_loadSlidesContents();
			_initProcessBar();
//			_loadCourseItemsInfo();

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
						_calcImagesSize($('.left_video_content ._sync_point_' + points[index].id),$('.left_video_content').width(),$('.left_video_content').height(),true);
						
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
						_calcImagesSize($('.left_video_content ._sync_point_' + points[index].id),$('.left_video_content').width(),$('.left_video_content').height(),true);
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
				
				var playTime = currentTime;
				for(var index = 0; index < media_index; index++){
					playTime = currentTime - medias[index].mediaLength;
				}
			
				console.log('======' + currentTime + '======' + playTime +'=====' + media_index);
				
				$(".right_video_content").jPlayer("play",playTime); 
				
				isPlay = true;
				
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
				theImage.onload = function(){
					var nW = adjustW(theImage.width,theImage.height,w,h);
					var nH = adjustH(theImage.width,theImage.height,w,h);
					img.css('width',nW + 'px').css('height',nH + 'px');
					
					if(isCss == true){
					 
						if(w/h < nW/nH){
							img.css('margin-top', (h-nH)/2 + 'px');
						}else{
							img.css('margin-left', (w-nW)/2 + 'px');
						}	
					}
				};
				
				theImage.src = $(this).attr("src");

			});
		};
		
		var _initSyncPoints = function(data){
			for(var index = 0; index < data.length; index++){
				points.push({'id':data[index].id,'slideId':data[index].syncSlideId,'type':'sync','time':data[index].pointTime,'thumbnail':imgStatic+"/"+data[index].pointUrl})	
			}
			_drawPoints(0);
			
		};
		
		var _initSyncQuizs = function(data){
			for(var index = 0; index < data.length; index++){
				points.push({'id':data[index].id,'quizId':data[index].quizId,'type':'quiz','time':data[index].quizTime,'name':data[index].quizName,'desc':data[index].quizDesc});
			}
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
				}catch(e){
					
				}
				
				//check whether exist the sync point, quiz, survey or comment at the init timeline.
				//_drawPoints(playTime);
			});
			
			//Bind the ended event, this event will be invoked by ending.
			$(".right_video_content").bind($.jPlayer.event.ended,function(event){
				console.log('----------- media ---------ended----------' + (media_index + 1) + '--------' + medias.length);
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
//				debugger;
				_playMedia();
			});
			 
		};
		
		var _loadMediaContents = function(data){
			
			for(var index = 0; index < data.length; index++){
//				_initMediaImage(index,clipsLength,data[index].mediaCover);
				medias.push(data[index]);
				clipsLength += data[index].mediaLength;
			}
			
			$('.right_con_height .bottom_list').css('width', 160 * clipsLength + 'px');
			$('.maxLength').html(formatTimes(clipsLength));
//			$('.ruler').html(_initIndicator(clipsLength));
			
			_initMedia();
		};
		
		var _loadSyncComments = function(){
			var url="/presentation/sync/comment/list.html",data={
					presentationId:$.trim($('#hiddenCourseItemId').val())
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
					presentationId:$.trim($('#hiddenCourseItemId').val())
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					var template = doT.template($("#slides_data_template").text());
					$('#medias').html(template({data:result.data.slides}));
					
					_loadMediaContents(result.data.medias);
					_initSyncPoints(result.data.points);
					_initSyncQuizs(result.data.quizs);
					_loadSyncComments();
					_drawFirstSlide();
//					_calcImagesSize($('.material_pic .pic_size'),160,90,true);
//					_calcImagesSize($('.slides_pic .pic_size'),265,142,true);
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
			$('#comment_content').val('');
		};
		
		_slideDown = function(){
			var _leftContainerCss_END = {width:'0px'};
			$(".btm_edit_bar").animate(_leftContainerCss_END, function(){
				$('.btn_ti').hide();
				$(".btm_edit_bar").hide();
//				$('.arrow').addClass('icon-double-angle-right').removeClass('icon-double-angle-left');
			});
				
		};
		
		
		onToggleCommentToolBar = function(){
			
			if($('.btm_edit_bar').is(":hidden")){
				_slideUp();
			}else{
				_slideDown();
			}
		};
		
		
		
		
//		onMouseOverPointThumbnail = function(event,id,type){
//			console.log('-------- onMouseOverSyncPointThumbnail ---------' + isMouseOverFlag);
//			_prevent(event); 	
//			if(isMouseOverFlag == false){
//				
//				var container = _pointContainer(type,id);
//				if(container.is(':hidden')){
//					container.slideDown();
//				}
//				setInterval(function(){
//					if(!container.is(':hidden')&&isMouseOverFlag == false){
//						container.slideUp(500);
//					}
//				}, 3000 ); 
//				
//			}
//		};
//		
//		
//		onMouseOutPointThumbnail = function(event,id,type){
//			isMouseOverFlag = false;
////			_prevent(event); 	
//////			if(checkHover(event,this)){
////				setInterval(function(){
////					console.log($('.sync_point_thumbnail_' + id +' .btm').is(':hidden') + '-------- onMouseOutSyncPointThumbnail ---------' + isMouseOverFlag);
////					if(!$('.sync_point_thumbnail_' + id +' .btm').is(':hidden')&&isMouseOverFlag == false){
////						$('.sync_point_thumbnail_' + id +' .btm').slideUp(500);
////					}
////				}, 1000 ); 
//////			}
//		};
//		
//		
//		onMouseOverSubPointThumbnail = function(event,id,type){
//			console.log('-------- onMouseOverSubSyncPointThumbnail ---------' + isMouseOverFlag);
//			_prevent(event); 
//			isMouseOverFlag = true;
//			var container = _pointContainer(type,id);
//			if(!container.is(':hidden')){
//				container.show();
//			}
//		};
//
//		
//		onMouseOutSubPointThumbnail = function(event,id,type){
//			isMouseOverFlag = false;
//			_prevent(event); 
//		};
//		
		loadComments = function(){
			_loadSyncComments();
		};
		

		onPause = function(){
			if(isPlay == true){
				$(".right_video_content").jPlayer("pause"); 
			}
		};
		
//		//生成课程章节信息页面
//		var _generateCourseItemData = function(result){
//			var template = doT.template($("#courser_viewer_course_content_list_data_template").text());
//			$("#courseItemlist").empty().append(template(result));
//		}
//		
//		//加载学员课程章节列表信息
//		var _loadCourseItemsInfo = function(){
//			var url="/course/course_viewer/item_list.html",data={
//					courseId:$.trim($('#hiddenCourseId').val())
//			};
//			postAjaxRequest(url, data, function(result){
////				debugger;
//				if(result.status == 1){
//					//加载数据
//					_generateCourseItemData(result);
//				}else{
//					alertMsg("课程章节信息加载失败！");
//				}
//			});
//		}
		
//		//返回课程首页
//		goBack = function(){
//			go2Page('/course/course_viewer/detail.html','courseId=' + $.trim($('#hiddenCourseId').val()));
//		};
//		
//		//获取课程指定偏移量的课时信息
//		var _loadCourseItemOffset = function(queryParams){
//			var url="/course/load_course_item_by_offset.html",data = queryParams;
//			postAjaxRequest(url, data, function(result){
//				debugger;
//				if(result.status == 1){
//					//数据加载成功跳转到对应页面
//					var targetItemId = result.data.item_id;
//					var targetItemType = result.data.item_type;
//					var targetItemIndex = result.data.item_sort;
//					var targetCourseId = result.data.course_id;
//					_toPageUrl(targetItemId,targetItemType,targetItemIndex,targetCourseId);
//				}else{
//					alertMsg("课程章节信息加载失败！");
//				}
//			});
//		}
		
//		//点击偏移跳转到对应页面
//		var _toPageUrl = function(itemId,itemType,itemIndex,courseId){
//			var url = "";
//			var params = "itemIndex=" + itemIndex + "&courseId=" + courseId;
//			switch(itemType){
//				case '0':
//				case  0:
//					url = "/classroom.html";
//					params += "&presentationId=" + itemId; 
//					break;
//				case '1':
//				case  1:
//					url = "/course/course_viewer/quizs.html";
//					params += "&quizsId=" + itemId;
//					break;
//				case '2':
//				case  2:
//					url = "/course/course_viewer/survey.html";
//					params += "&surveyId=" + itemId;
//					break;
//				default:break;
//			}
//			go2Page(url,params);
//		}
		
//		//查询前一个课程课时
//		onPreItem = function(){
//			var data = {
//				isPre :true,
//				courseId:$.trim($('#hiddenCourseId').val()),
//				itemSort:$.trim($('#hiddenCourseItemIndex').val())
//			};
//			_loadCourseItemOffset(data);
//		}
//		//查询后一个课程课时
//		onNextItem = function(){
//			var data = {
//				isNext :true,
//				courseId:$.trim($('#hiddenCourseId').val()),
//				itemSort:$.trim($('#hiddenCourseItemIndex').val())
//			};
//			_loadCourseItemOffset(data);
//		}
		
		initPresentationSyncItems = function(){
			_init();
			_init_sync_comment();
		};
	});