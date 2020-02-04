define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "utils",
		"jquery.base", "jquery.validate"], function($, cookie, doT, paging) {
		var init = function(){
			  
			  $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
				 if(e.target.hash=='#comments'){
					 getPresentationDiscussion();
				 }
			  });
			  

		};
		
		
		
		getPresentationDiscussion = function(){
			var url="/presentation/getPresentationDiscussion.html",data={
					 presentationId:$.trim($("#presentationId").val())
			};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#content_div_data_template").text());	
				$('#r_content_div').html(template({"data":result.data}));
				  $("._open_conment").on("click",function(){
					  $("#conment_more_show").slideToggle();
						$(".open_btn").toggleClass("open_btn_down"); 
				  });
			});
		};
		replyDiscusstion = function(id){
			var url="/presentation/savePresentationDiscussion.html",data={
					 discussContent:$.trim($("#reply_"+id).val()),
					 discussPkId:id
			};
			postAjaxRequest(url, data, function(result){
				getPresentationDiscussion();
			});
			
			
		};
		
		deleteReplyComfirm = function(id){
			$("._deletereply").hide();
			$("#deleteReplyComfirm"+id).show();
		};
		deleteCanle = function(){
			$("._deletereply").hide();
		};
		deleteReply = function(id){
			var url="/presentation/deleteReply.html",data={
					 discussPkId:id
			};
			postAjaxRequest(url, data, function(result){
				getPresentationDiscussion();
			});
		};
		deleteDiscusstion = function(id){
			
			confirmMsg("删除后将无法恢复，确定删除该评论吗？",function(){
				var url="/presentation/deleteDiscusstion.html",data={
						 id:id
				};
				postAjaxRequest(url, data, function(result){
					getPresentationDiscussion();
				});
			});
			
			
		
		}
		
		init();

	});