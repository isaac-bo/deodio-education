	define(["jquery","utils.cookie","jquery.dot","utils","jquery.base","jquery.validate",
	        "bootstrap.select","jquery.scrolltofixed"], function($,cookie,doT) {
		
		_init = function(){
			loadTopClassifications();
		}
		
		loadTopClassifications = function(){
			var url="/signin/classification/list.html",data ={
				uKeyId:$("#uKeyId").val()
			};
			postAjaxRequest(url, data, function(result){
				var template = doT.template($("#signin_top_classification_data_template").text());
				$('#selectList').html(template(result));
				setClassificationClick();
			});
		}
		
		setClassificationClick = function(){
			
			var maxSelectedCount = $("#maxSelectedCount").val();
		
			$("#selectList").find("li").off();
			$("#selectList").find("li").on("click",function(e){
				
				var _this = $(e.target);
				var container = $(_this).is("li")?$(_this):$(_this).parents("li");
				var imgcontainer=$(container).children("span").children("img");
				var imgAfter = $(this).find("[name='imgAfter']").val();
				var imgBefore = $(this).find("[name='imgBefore']").val();
				if($(container).is(".active")){
					$(container).removeClass("active");
					$(imgcontainer).attr("src",imgBefore);
				}else{
					var nowSelectedCount = $("#selectList").find("li.active").size();
					if(nowSelectedCount >= maxSelectedCount){
						alertMsg("当前套餐可选类别已达最大数量：" + nowSelectedCount + ",请谨慎选择。");
//						alertMsg("当前套餐可选类别已达最大数量：" + nowSelectedCount);
						return;
					}
					$(container).addClass("active");
					$(imgcontainer).attr("src",imgAfter);
				}
			});
			
			$("#selectList").find("li").on("mouseenter",function(e){
				var _this = $(e.target);
				var container = $(_this).is("li")?$(_this):$(_this).parents("li");
				$(container).css("border","1px solid #3faad0");
				var imgcontainer=$(container).children("span").children("img");
				var imgAfter = $(this).find("[name='imgAfter']").val();
				$(imgcontainer).attr("src",imgAfter);
			}).on("mouseleave",function(e){
				var _this = $(e.target);
				var container = $(_this).is("li")?$(_this):$(_this).parents("li");
				$(container).css("border","");
				if($(container).is(".active")){
					return
				}else{
					var imgcontainer=$(container).children("span").children("img");
					var imgBefore = $(this).find("[name='imgBefore']").val();
					$(imgcontainer).attr("src",imgBefore);
				}
				
			});
			
		}
		
		saveSelectedClassifications = function(){
			var dataArray = [];
			$("#selectList").find("li.active").each(function(){
				var classificationId = $(this).find("[name='classificationId']").val();
				dataArray.push(classificationId);
			});
			var uKeyId = $("#uKeyId").val();
			var url="/signin/classification/submit.html",data = {
				uKeyId: uKeyId,
				classificationJson:JSON.stringify(dataArray)
			};
			if(dataArray.length>0){
				postAjaxRequest(url, data, function(result){
					if(result.status == 1){
						var url="/signin/checkAccountIsValide.html",data = {
								uKeyId: uKeyId
							};
							postAjaxRequest(url, data, function(result){
								console.log("result---",result);
								console.log("cookieAccountId---",$.cookie("CAID"));
								console.log("cookieUserId---",$.cookie("CUID"));
//								alert('afdasfasd');
								if(result.data.isValid){
									onLoadAccountList(uKeyId);
								}else{
									if(result.data.accountList.length>1){
										onLoadAccountList(uKeyId);
									}else{
										if($.cookie("CAID")!=null&&$.cookie("CUID")!=null){
											go2Page('/user/profile.html');
										}else{
											go2Page('/signin/detail.html',"uKeyId="+uKeyId);
										}
																			}
//									toSigninDetailSetting(uKeyId);
								}
							});
//						alertMsg("保存成功！");
//						var flag=checkAccountIsValide(uKeyId);
//						if(flag){
//							onLoadAccountList(uKeyId);
//						}else{
//							toSigninDetailSetting(uKeyId);
//						}
//						
					}else{
						alertMsg("非常抱歉，设置失败，请重新设置。");
					}
				});
			}else{
				alertMsg("请至少选中一个课程类别！");
			}
	
			
		}
//		checkAccountIsValide=function(uKeyId){
//			var valideFlag=false;
//			var url="/signin/checkAccountIsValide.html",data = {
//					uKeyId: uKeyId
//				};
//				postAjaxRequest(url, data, function(result){
//					valideFlag=result.data;
//				},false);
//				return valideFlag;
//		}
		
		//调整到编辑页面
		toSigninDetailSetting = function(uKeyId){
			go2Page('/signin/detail.html',"uKeyId="+uKeyId);
		};
		onLoadAccountList = function(userId){
			//alert(username + " " + password + '--11---' + $.i18n.prop('fe.error.files.upload.type'));
			cookie.setCookie('CUID',userId,60);
			var url= "/account/load_login_list.html?r=" + Math.floor(Math.random() * 100),data={userId:userId},flag=false;
			postAjaxRequest(url, data, function(result){
				if(result.data.mapList.length == 1){
					var account = result.data.mapList[0];
					cookie.setCookie('CAID',account.account_id,60);
					loadGroupList();
				}else{
					window.location.href = ctx + "/account/list.html?r=" + Math.floor(Math.random() * 100);
				}			
			},false);
			return flag;
		};		
	     loadGroupList=function(){
	    	var url= "/group/load_list.html?r=" + Math.floor(Math.random() * 100),data={pageNo:1};
			postAjaxRequest(url, data, function(result){
				if(result.data.dataList.length <= 0){			
					window.location.href = ctx + "/account/profile.html?r=" + Math.floor(Math.random() * 100);
				}else if(result.data.dataList.length == 1){			
					window.location.href = ctx + "/group/profile.html?groupId="+result.data.dataList[0].id+"&r=" + Math.floor(Math.random() * 100);
				}else{				
					window.location.href = ctx + "/group/list.html?r=" + Math.floor(Math.random() * 100);
				}
			},false);
	    }
		_init();
		
	});

