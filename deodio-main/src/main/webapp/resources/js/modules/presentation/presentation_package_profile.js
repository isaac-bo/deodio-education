	define(["jquery","utils.cookie","jquery.dot","pagination","utils","jquery.base","jquery.validate","jquery.scrolltofixed","bootstrap.select"], function($,cookie,doT,paging) {
		
		var toPage = '0';
		var init = function(){	
			
			
			//单复选按钮注册
			customInput("isCountDown");
//			customInput("isPassHours");
			customInput("isPassElements");
			customInput("isPassQuizs");
			customInput("isCourse");
		};
		
		init();
		
		
		$('#packageProfileForm').myValidate({
			formCall:function(){onSubmitPackageProfile();},
			isAlert:true,
			formKey:false,
			errorCustom:{customFlag:false,regionText:false},
			alterCall:function(msg){alertMsg(msg);}	
		});
			
		//课件规则设置
		onSubmitPackageProfile = function(){
			var url="/presentation/package/sumbit_profile.html",data={
				presentationId:$.trim($('#presentationId').val()),
				packagesItemsJson:generatePackageItemsJson(),
				packageProfileJson:generagePackageJson()
			};
			postAjaxRequest(url, data, function(result){
				if(result.status == 1){
					if(0 == toPage){
						//保存预览
						go2Page('/presentation/package/preview.html','presentationId='+$.trim($("#presentationId").val()));
					}else{
						//保存发布
						onPublish();
					}
					
				}else{
					alertMsg("设置失败！");
				}
			});
		}
		
		onSavePackageItemProfile = function(){
			toPage = '0';
		}
		
		onPublishPackageItemProfile = function(){
			toPage = '1';
		}
		
		var generatePackageItemsJson = function(){
			var dataArray = [];
			$(".package-profile").each(function(){
				var _self = $(this);
				var packageProfile = {
					id:_self.find("[name='packageItemId']").val(),
					countDown :_self.find("[name='countDown']").val(),
				};
				dataArray.push(packageProfile);
			});
			return JSON.stringify(dataArray);
		}
		
		var generagePackageJson = function(){
			var data = {
				id:$.trim($('#presentationId').val()),
				persentationPercentage:$.trim($("#persentationPercentage").val()),  //达标学习进度百分比
				isPassHours:1,  //学习进度达到要求(%|x/y)
				isPassElements:$("#one3").prop("checked")?1:0, //阅读所有文件包内课件
				isPassQuizs:$("#one4").prop("checked")?1:0, //通过Presentation的测验
				isCourse:$("#one5").prop("checked")?1:0, //发布后自动创建同名的课程	
				isCountDown:$("#isCountDown").prop("checked")?1:0
			};
			return JSON.stringify(data);
		}
		
		//设置章节压缩文件数据是否显示
		setPackageVisible = function(){
			var isShow = $("#isCountDown").prop("checked");
			if(isShow){
				$("li.package-profile-container").slideDown();
			}else{
				$("li.package-profile-container").slideUp();
			}
			
		}
		
		goBack = function(){
			go2Page('/presentation/package/files.html','presentationId='+$.trim($("#presentationId").val()));
		}
		
		
		
	});