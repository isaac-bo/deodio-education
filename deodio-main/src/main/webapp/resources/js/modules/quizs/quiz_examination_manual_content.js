define([ "jquery", "utils.cookie", "jquery.dot", "utils.list", "utils",
		"jquery.base", "jquery.validate", "jquery.scrolltofixed", "jquery.ui",
		 "fileupload.common" ], function($, cookie, doT, list) {

	var _init = function() {

		$("[name^='quiz']").each(function() {
			var name = $(this).attr("name");
			customInput(name);
		});
		$("#saveQuizForm").myValidate({
			formCall : function() {
				saveQuiz();
			},
			isAlert : false,
			formKey : false,
			errorCustom : {
				customFlag : true,
				regionText : true
			},
			errorStyle : {
				errorRegion : "error-div",
				errorClass : "wrong-item",
				rightClass : "right-item"
			}
		});

		list.onFixedItems();

		initPageImg();
	};

	// var getExaminationData = function() {
	// var dataArray = new Array();
	//
	// $(".chouti").each(function(i, v) {
	// var _qtype = $(this).attr('qtype');
	// var _question = $(this).find('div.edit_title input').val();
	// var optionArray = new Array();
	// if (_qtype <= 4) {
	// $(this).find('div.ti').each(function() {
	// var _options = $(this).find('input[type="text"]');
	// _options.each(function() {
	// var isCorrect =
	// $(this).parent().siblings("div").find('input').prop("checked") == true ?
	// "1": 0;
	// optionArray.push($(this).val()+ "_&_"+ isCorrect);
	// });
	//
	// });
	// } else if (_qtype == 6) {
	// var dirId = $(this).find('div.uploadify').arrt("id").split("_")[1];
	// optionArray.push($("#uploadFilePath_" + dirId).val());
	// }
	// var dataStr = _qtype + "_#_" + _question + "_#_" +
	// optionArray.join("]=[");
	// dataArray.push(dataStr);
	// });
	// return dataArray.join("_@_");
	//				
	//				
	// };

	saveQuiz = function() {

		var listStr = getQuestionList();
		if (!listStr) {
//			alertMsg("请添加试卷内容");
			return;
		}

		var quizId = $("#quizId").val();

		var url = "/quiz/manual/save.html", data = {
			quizId : quizId,
			dataStr : listStr

		};

		postAjaxRequest(url, data, function(result) {
			go2Page("/quiz/preview.html", "eFlag=" + $("#eFlag").val()
					+ "&quizId=" + quizId + "&navTabs=1");
		});

	}

	uploadInit = function(id) {	
		uploadFileJqProcess('uploadFile_' + id, '*.jpg;*.png;', {
			accountId : cookie.getCookie('CAID'),
			attachRelType : "20",
			isModel : true
		}, ctx + '/commons/uploadAndUpdateAttach.html?presentationId='
				+ $.trim($("#quizId").val()) + '&r='
				+ Math.floor(Math.random() * 100), function(fileName) {

		}, function(data) {
			var srcimg = imgStatic + data.attachUrl + data.generateName;
			var srcimgTemp = data.generateName;
			$(".l_pic").css({
				"background" : "url(" + srcimg + ") no-repeat"
			});
//			$("#quizQuistionPicture").attr("src",srcimg);
			$("#uploadFilePath_" + id).val(srcimg);
		}, true, '题目图片');
	};

	goBack = function() {
		var quizId = $("#quizId").val();
		go2Page("/quiz/profile.html", "eFlag=" + $("#eFlag").val() + "&quizId="
				+ quizId + "&navTabs=1");
	};

	initPageImg = function() {
		$('div[qtype="5"]').each(
				function() {
					var id = $(this).find('input[name="filePath"]').attr("id")
							.split("_")[1];
					uploadInit(id);
				});
	};

	_init();

	
	quizAttachUpload=function(obj,attachRelType,id){
		console.log("quizAttachUpload---   attachRelType=",attachRelType)
		var uploadId=$(obj).attr('id');
		var uploadParentId=$(obj).parent().attr('id');
		console.log('id====',id);
		console.log('uploadId====',uploadId);	
		
		console.log('uploadParentId====',uploadParentId);		
		var uploadUrl = '/commons/uploadAndUpdateAttachment.html?presentationId='+id+'&attachRelType='+attachRelType;	
		var f=$(obj).val();
		if(f == null || f ==undefined || f == ''){
            return false;
        }
        if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
        {
            $(obj).val('');
            return false;
        }            
        uploadFileJqProcess(uploadId,uploadUrl,{accountId:cookie.getCookie('CAID'),attachRelType:attachRelType,isModel:true},
			1,/(.|\/)(jpe?g|png)$/i,true,function(e,data){
        	console.log("data ====",data.result.data)
        		var srcAttachTemp=data.result.data.attachUrl;
        		var srcimgTemp = data.result.data.generateName;
        		var srcimg = imgStatic+srcAttachTemp+srcimgTemp;

        		
				$("#"+uploadParentId).attr('style','background : url(' + srcimg + ') center no-repeat;background-size: 100%  100%')
//    			$("#"+preNodeId).attr("src",srcimg);
//        		$("#"+uploadParentId).css({
//    				"background" : "url(" + srcimg + ") center no-repeat;background-size: 100%  100%"
//    			});
    			$("#uploadFilePath_" + id).val(srcimg);
        },function(e,data){
	
        });
	}
});
