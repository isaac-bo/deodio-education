define(["jquery","utils.cookie","jquery.dot","utils","jquery.base"], function($,cookie,doT) {
	
	
	var isFullScreen = false;
	var commentContainer = 1;
	var commentPosX = 0;
	var commentPosY = 0;
	var commentId = 0;
	
	var comment = {};
	
	_init_sync_comment = function(){
		$('.right_video_content').click(function(event){
			_createSyncComment(event,1);
		});
		
		$('.left_video_content').click(function(event){
			$("#current_point_Time").text(formatTimes($("#currentTime").val()));
			_createSyncComment(event,2);
		});
	};
	
	_createSyncComment = function(event,containerType){
		
		_slideUp();
		
//		_disVolume();
		if(isFullScreen == false && ((containerType == 1 && (event.target.id.indexOf('jp_video')>=0 || event.target.id.indexOf('jp_image_mask')>=0))||(containerType == 2)) ){
//			 onCloseComment(0, false);
			 
//		 
			 if($("#syncType").val()=='slides'){
				 pauseSlides();
			 }else{
				 onPause();
			 }
		
			 var posX = event.offsetX - 8;
			 var posY = event.offsetY - 8;
			 console.log(posX+"--------------"+posY);
			 commentId = randomString();
			 commentContainer = containerType;
			 if($("#syncType").val()=='slides'){
				 commentPosX = posX*1600/$(".left_video_content img").width(); 
			 }else{
				 commentPosX = containerType == 1 ? posX*1600/$('.right_video_content').width() : posX*1600/$('.left_video_content').width(); 
			 }
			 
			 
			
			 commentPosY = containerType == 1 ? posY*900/$('.right_video_content').height() : posY*900/$('.left_video_content').height();
			 
			 console.log(commentPosX+"------000022220--------"+commentPosY);
			 
			 
			 _drawComment(commentContainer,commentId,posX, posY);
			 
			 
			 
			 
//			 var posX = isFullScreen == true? (event.offsetX - 20)*820/window.screen.width: event.offsetX - 20;
//			 var posY = isFullScreen == true? (event.offsetY - 20)*461/window.screen.height: event.offsetY - 20;
//			 
//			 _posX = posX;
//			 _posY = posY;
//			 _commentComponent(commentId,posX,posY);
//			  
//				// alert($(".newcommenttextera_"+commentId));
//				
//				 $(".newcommenttextera_"+commentId).focus();
//			
//			 if(valite == false){
//				 onMoveSyncPoint(commentId);
//			 }
//			 
//			 _colorComments(commentId,_brushColor);
//			 $('#comment_value_input').val('');
//			 $("#comment_id_input_hidden").val(commentId);
//			 
//			 $('.point_time').html('<span>'+ $.jPlayer.convertTime(currentTime)  + '</span>')
		 }
	};
	
	
	_clearComment = function(){
		
		$('input[id^=comment_content]').each(function(){
			if($(this).val() == ''){
				var commentId = $(this).attr('id').split('_')[2];
				$('#comment_'+commentId).remove();
			}
		});

	};
	
	_drawComment = function(containerType,commentId,posX,posY){
		_clearComment();
		var template = doT.template($("#comment_point_template").text());
		if(containerType == 1){
			$('.right_video_content').append(template({'commentId':commentId,'posX':posX,'posY':posY}));
		}else{
			$('.left_video_content').append(template({'commentId':commentId,'posX':posX,'posY':posY}));
		}
		
	};
	
	
	_initComment = function(){
				comment = {
					commentId : commentId,
					commentItemId: randomString(),
					commentContainer : commentContainer,
					commentPosX : commentPosX,
					commentPosY : commentPosY,
					commentContent : $('#comment_content').val(),
					commentUserId: cookie.getCookie('CUID'),
					commentAccountId:cookie.getCookie('CAID'),
					commentDuration:currentTime==0?$("#currentTime").val():currentTime
					
				};
	};
	
	submitComment = function(){
		if($('#comment_content').val() != ''){
			_initComment();
//			comments.push(comment);
			$('#comment_content_' + commentId).val($('#comment_content').val());
			
			var url="/presentation/sync/comment/add.html",data={
					presentationId:$.trim($("#presentationId").val()),
					commentJson:JSON.stringify(comment)
			};
			postAjaxRequest(url, data, function(result){
				loadComments();
			},undefined,undefined,false);
		}
		
	};
	
	onDisplaySubComments = function(id){
		
		$('#conment_more_show_' + id).slideToggle();
		$("#open_comment_"+id).toggleClass("open_btn_down");
		$('div[id^=feed_comment_]').hide();
	};
	
	onDelComments = function(id,isItem){
		
		if(isItem == true){
			$('#del_comment_'+id).show();
			$('#con_comment_'+id).hide();
		}else{
			$('#con_comment_header_'+id).hide();
			$('#con_comment_'+id).hide();
			$('#del_comment_'+id).show();
		}
//		$('div[id^=conment_more_show_').hide();
//		$('[id^=open_comment_').removeClass('open_btn_down');
//		$('div[id^=edit_comment_]').hide();
	};
	
	onFeedComments = function(id,isItem){
		
		$('#feed_comment_'+id).show();
		$('#feed_con_comment_'+id).focus();
	};
	
	onFollowComments = function(id){
		var url="/presentation/sync/comment/follow.html",data={
				presentationId:$.trim($("#presentationId").val()),
				commentId:id,
				commentUserId:cookie.getCookie('CUID')
		};
		postAjaxRequest(url, data, function(result){
			//loadComments();
			$('#follow_comment_' + id).html('· ' + result.data);
			if(result.data > 0){
				$('#follow_comment_' + id).show();
			}else{
				$('#follow_comment_' + id).hide();
			}
		},undefined,undefined,false);
	};
	
	onEditComments = function(id,isItem){
		$('#edit_comment_text_'+id).hide();
		$('#edit_comment_area_'+id).show();
	};
	
	onSubmitComments = function(event,id,isItem){
		var keycode = (event.keyCode ? event.keyCode : event.which); 
		$('#feed_con_comment_'+id).focus();
		if(keycode == '13'){
			
			var url="/presentation/sync/comment/add_item.html",data={
					presentationId:$.trim($("#presentationId").val()),
					commentId:id,
					commentItemId:randomString(),
					commentContent:$('#feed_con_comment_'+id).val(),
					commentUserId:cookie.getCookie('CUID')
			};
			postAjaxRequest(url, data, function(result){
				loadComments();
			},undefined,undefined,false);
		}
	};
	
	onUpdateComments = function(event,id,isItem){
		var keycode = (event.keyCode ? event.keyCode : event.which); 
		if(keycode == '13'){
			
			$('#edit_comment_text_'+id).html($('.edit_comment_area_con_'+id).val());
			$('.edit_comment_area_con_'+id).val($('.edit_comment_area_con_'+id).val());
			$('#edit_comment_text_'+id).show();
			$('#edit_comment_area_'+id).hide();
//			
//			alert($('.edit_comment_area_con_'+id).val());
			
			var url="/presentation/sync/comment/update.html",data={
					presentationId:$.trim($("#presentationId").val()),
					commentId:id,
					commentContent:$('.edit_comment_area_con_'+id).val(),
					commentUserId:cookie.getCookie('CUID'),
					isItem:isItem
			};
			postAjaxRequest(url, data, function(result){
				//loadComments();
			},undefined,undefined,false);
		}
	};
	
	onCancelDelComments = function(id,isItem){
		if(isItem == true){
			$('#del_comment_'+id).hide();
			$('#con_comment_'+id).show();
		}else{
			$('#con_comment_header_'+id).show();
			$('#con_comment_'+id).show();
			$('#del_comment_'+id).hide();
		}
	};
	
	onSubmitDelComments = function(id,isItem){
		var url="/presentation/sync/comment/del.html",data={
				presentationId:$.trim($("#presentationId").val()),
				commentId:id,
				isItem:isItem
		};
		postAjaxRequest(url, data, function(result){
			loadComments();
		},undefined,undefined,false);
	};
	
	onCopyComments = function(id,isItem){
//		var clipboard = new Clipboard('#copy_'+id);
//		clipboard.on('success', function(e) {  
//		      console.log(e);  
//		      alert("测试2复制成功！")  
//		});  
//		clipboard.on('error', function(e) {  
//		      console.log(e);  
//		      alert("测试2复制失败！请手动复制")  
//		}); 
	};
	
	onHandleComments = function(id){
		var url="/presentation/sync/comment/handle.html",data={
				presentationId:$.trim($("#presentationId").val()),
				commentId:id,
				commentUserId:cookie.getCookie('CUID')
		};
		postAjaxRequest(url, data, function(result){
			$('#handle_comment_'+id).toggleClass('check_active');
//			loadComments();
		},undefined,undefined,false);
	};
	
	
//	var _commentComponent = function(commentId,posX,posY, type, eposX,eposY){
//		console.log('----- private method --------- _commentComponent ---- ');
//		
//		if(type == 1){
//			var template = doT.template($("#comment_rectangle_template").text());
//			$('.video-item2-right-top').append(template(commentId));
//		}else if(type == 3){
//			var template = doT.template($("#comment_line_template").text());
//			$('.video-item2-right-top').append(template(commentId));
//		}else{
//			var template = doT.template($("#comment_template").text());
//			$('.video-item2-right-top').append(template(commentId));
//		}
//	  
//		
//		_posComment(commentId,posX,posY,type,eposX,eposY);
////		_initComment(commentId,posX,posY,type,eposX,eposY);
//		// listenner the mouse over and out on comment box.
//		$('#comment_'+commentId+" .comment-box").mouseover(function(event){
//			 console.log('--------comment mouseover-------------');
//			 _prevent(event);
//			 $('.video-item2-right-top').unbind('click');
//			 $('#comment_'+commentId+" .comment-box").bind('mousedown',function(event){
//				 console.log('-------comment mousedown 1------');
//				 _prevent(event);
//				 palite = true;
//				 onMoveComment(commentId);
//			 }).bind('mouseup',function(event){
//				  console.log('-------comment mouseup 1------');
//				  _prevent(event);
//				  palite = false;
//				  currentCommentId = '';
//			 });
//		 }).mouseout(function(event){
//			 console.log('--------comment mouseout-------------');
//			 _prevent(event);
//			 $('.video-item2-right-top').bind('click',onCreateSyncPoint);
//			 $('.video-item2-right-top').bind('mousedown',onCreateSyncPoint4MD);
//			 $('.video-item2-right-top').bind('mouseup',onCreateSyncPoint4MU);
//			 $('.video-item2-right-top').bind('mousemove',onCreateSyncPoint4MM);
//			 $('#comment_'+commentId+" .comment-box").unbind('mousedown').unbind('mouseup');
//		 }).mousedown(function(event){
//			 console.log('-------comment mousedown 1------');
//			 _prevent(event);
//			 palite = true;
//			 onMoveComment(commentId);
//		 }).mouseup(function(event){
//			 console.log('-------comment mouseup 1------');
//			 _prevent(event);
//			 palite = false;
//			 currentCommentId = '';
//		 }); 
//		console.log('----- 2 ---- '); 
//		// listenner the mouse over and out on tip box.
//		$('#comment_' + commentId + " .comment-box-bottom").mouseover(function(event){
//			 console.log('--------comment mouseover 2-------------');
//			 _prevent(event);
//			$('#comment_'+commentId+" .comment-box").unbind('mousedown');
//		}).mouseout(function(event){
//			 console.log('--------comment mouseout 2-------------');
//			_prevent(event);
//			$('#comment_'+commentId+" .comment-box").bind('mousedown',function(event){
//				console.log('-------comment mousedown 2------');
//				 _prevent(event);
//				 palite = true;
//				 onMoveComment(commentId);
//			});
//		});
	
	
	_init_sync_comment();
});
		