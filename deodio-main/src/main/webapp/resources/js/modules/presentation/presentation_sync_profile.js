	define(["jquery","utils.cookie","jquery.dot","utils.list","pagination","utils","jquery.base","jquery.validate","jquery.scrolltofixed","ueditor",
			"ueditor.config","upload.ui","upload.common","upload.handler","bootstrap.select"], function($,cookie,doT,list,paging) {
		
		var init = function(){	
			
			
			
			
			//单复选按钮注册
			customInput("isSlideShow");
			customInput("isManually");
			customInput("isInitSyncPoint");
			customInput("isPassHours");
			customInput("isPassElements");
			customInput("isPassQuizs");
			customInput("isCourse");
			
			 if($('#man8').prop("checked")){
				 $("#slidesShowSettingPanel").show();
			 }
			
			 $("input:radio[name=isSlideShow]").change(function () {
	                var radioId = $(this).attr("id");
	                if(radioId == "man7"){
	                	$("#slidesShowSettingPanel").hide();
	                }else if(radioId == "man8"){
	                	$("#slidesShowSettingPanel").show();
	                	var radioValue = $('input:radio[name="isManually"]:checked').val();
	                	if(undefined==radioValue){
	                		$("#man9").prop("checked",true);
		                	$("#man9").next('label').addClass('checked');
		                	$("#initialLength").prop("disabled","disabled");
	                	}
	                }
	            });
	        
	        //调节方式单选按钮
	        $("input:radio[name=isManually]").change(function () {
                var radioId = $(this).attr("id");
                if(radioId == "man9"){
                	$("#slideLength").removeAttr("disabled");
                	$("#initialLength").attr("disabled","disabled");
                	$("#initialLength").val("");
                }else if(radioId == "man6"){
                	$("#slideLength").attr("disabled","disabled");
                	$("#slideLength").val("");
                	$("#initialLength").removeAttr("disabled");
                }
            });
	        
	        list.onFixedItems();
		}
		
		init();
		
		//返回上一步
		prevStep = function(){
			go2Page('/presentation/profile.html','presentationId='+$('#presentationId').val());
		}
		
		//是否已设置课件规则判断
		ifHaveSetUp = function(type){//type:0导入课件；1:相关附件
			var presentationId = $.trim($('#presentationId').val());
			var url="/presentation/if_setted.html",data={
					presentationId:presentationId
			}
			postAjaxRequest(url, data, function(result){
				if(null != result.data){
					if(type==0){
						
						go2Page('/presentation/sync/files.html','presentationId='+presentationId);
					}else if(type==1){
						window.location.href = ctx + "/presentation/toRelevantAttachment"+presentationId+".html?&r=" + Math.floor(Math.random() * 100);
					}
				}else{
					alertMsg("请先设置课件规则！");
				}
			});
		}
		
		$('#syncProfileForm').myValidate({
			formCall:function(){setFlashRule();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:false,regionText:false}
			//errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		var checkScoreNumber = function(value){
			var flag=false;
			if(!/^[0-9]\d*$/.test($.trim(value))){
				flag=true;
			}else{
				if(value<0||value>100){
					flag=true;
				}
			}
			return flag;
		};
		//课件规则设置
		setFlashRule = function(){
			
			if(!$("#one2").prop("checked") && !$("#one4").prop("checked") ){
				alertMsg("课件通过条件未设置，请至少选择一个通过条件");
				return;
			};
			
			if($("#one2").prop("checked") && $("#persentationPercentage").val()==""){
				alertMsg("学习进度到达要求请输入0~100之间的整数");
				return;
			};
			
			
		
			var url="/presentation/setFlashRule.html",data={
					presentationId:$.trim($("#presentationId").val()),
					isSlideShow:$("input[name='isSlideShow']:checked").val(), //Slide Show 还是 Sync Media
					isManually: $("input[name='isManually']:checked").val(),//自动调节 还是 手动调节
					length:$.trim($("#initialLength").val()),
					interval:$.trim($("#slideLength").val()),
					isInitSyncPoint:$("#one1").prop("checked"),
					persentationPercentage:$.trim($("#persentationPercentage").val()),
					isPassHours:$("#one2").prop("checked"),
				//	isPassElements:$("#one3").prop("checked"),
					isPassQuizs:$("#one4").prop("checked"),
					isCourse:$("#one5").prop("checked") 	//达标学时百分比
			}
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					if(result.data=='FAIL'){
						alertMsg("手动时间不能小于更改前自动时间！");
					}else{
						go2Page('/presentation/sync/files.html','presentationId='+$.trim($("#presentationId").val()));	
					}
					
				}else{
					alertMsg("设置失败！");
				}
			});
		}
		
		goBack = function(){
			go2Page('/presentation/profile.html','presentationId='+$.trim($("#presentationId").val()));
		}
		
		
	});