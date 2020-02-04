define([ "jquery", "utils.cookie","jquery.dot","utils.list","utils", 
	"jquery.base","jquery.tagsInput","jquery.validate"], function($, cookie, doT,list) {

	var _init = function() {
		customInput("quizSet");
		customInput("scoreSet");
		var banksId = new Array();
		$("span[banksid]").each(function(){
			banksId.push($(this).attr("id"))
	    });
		setBanksTag(banksId);
		$("input[name='quizSet']").click(function(){
			var i = 0;
			if (this.checked) {
				$(this).parent().parent().parent().find('input').each(function(){
					if (i == 4) {
						return;
					}
//					if (i == 3) {
//						$(this).attr({"data-callback":"checkSubjectCount()","call-message":"请输入正确的题目数"});
//					}
					$(this).attr({"check-type":"number","required-message":"请输入数字"});
					i = i + 1;
				});
			} else {
				$(this).parent().parent().parent().find('input').each(function(){
					$(this).removeAttr("check-type");
					$(this).removeAttr("required-message");
				});
			}
		});
		
		$("#createForm").myValidate({
			formCall:function(){ submitBanksRule();},
			isAlert:false,
			formKey:false,
			errorCustom:{customFlag:true,regionText:true},
			errorStyle:{errorRegion:"error-div",errorClass:"wrong-item",rightClass:"right-item"}
		});
		
		list.onFixedItems();
		
	};
	$("input[name='quizRequired']").focus(function() {
		if($(this).attr('lable-error')){
			$(this).removeAttr('lable-error').removeAttr('style').val('')
		}
		
	})
	setDisable = function(i) {
		$("._checked"+i).prop("checked",false);
		$("._checked"+i).prop("disabled",true);
		$("._lable"+i).removeClass('checked');
		$("._order"+i).val('');
		$("._order"+i).prop("disabled",true);
		$("._score"+i).val('');
		$("._score"+i).prop("disabled",true);
		$("._opcounts"+i).val('');
		$("._opcounts"+i).prop("disabled",true);
		$("._mark"+i).val('');
		$("._mark"+i).prop("disabled",true);
	}
	
	setEnable = function(i) {
		$("._checked"+i).prop("disabled",false);
		$("._order"+i).prop("disabled",false);
		$("._score"+i).prop("disabled",false);
		$("._opcounts"+i).prop("disabled",false);
		$("._mark"+i).prop("disabled",false);
	}
	
	setQuizBank = function() {
		$("#quizBanksModal").modal("show");
		$('#quizBanksModal').on('shown.bs.modal', function () {
			$('#div-tags').find("span").each(function(){
				$(this).remove();
			})
		})
		$('#quizBanksModal').on('hidden.bs.modal', function () {
			$('body').removeClass('modal-open').css('overflow-y','auto');
			$('#banks_panel').empty();
		});
	}
	
	setBanksTag = function(bankIds) {
		if (typeof(bankIds) == "undefined") {
			var tagsArray = new Array();
			$("#banks_panel input[type='checkbox']:checked").each(function() {
				tagsArray.push($(this).val())
			});
			if (tagsArray.length == 0) {
				return;
			}
//			$("#banks_div").empty();
//			var tagsIds = new Array();
			$.each(tagsArray,function(i,v){
				var _value=v.split("_"),bankid = _value[0];			
//				tagsIds.push(bankid);
				$("#banks_div").append('<span class="sel_btn" id="'+bankid+'" banksid="'+bankid+'">'+_value[1]+'<button type="button" onclick="selDelQuizBank(\''+bankid+'\');" class="sel_del">×</button></span>')
			});
			var bankIds = new Array();
			$("span[banksid]").each(function(){
				bankIds.push($(this).attr("id"))
		    });
			var url="/quiz/subject/get_count.html",params={
				bankIds:bankIds
			}
		} else if (bankIds.length == 0) {
			$("#subjectSet tr").each(function(i,v) {
				setDisable(i + 1);
			});
			return false;
		} else {
			var url="/quiz/subject/get_count.html",
			params={
					bankIds:bankIds
				}
		}
		postAjaxRequest(url, params, function(result) {
			var typeArray = new Array();
			$.each(result.data,function(i,v) {
				typeArray.push(v.quiz_subject_type);
				$("#subjctType"+v.quiz_subject_type).text(v.sumnum);
				setEnable(v.quiz_subject_type);
			});
			Array.prototype.diff = function(a) {
			    return this.filter(function(i) {return a.indexOf(i) < 0;});
			};
			var typeArrayRemain = [1,2,3,4,5,6,7].diff(typeArray);
			$.each(typeArrayRemain,function(i,v) {
				$("#subjctType"+v).text(0);
				setDisable(v);
			})
		});
	};
	
	$("#classifications").tagInput({
		url:ctx+"/quiz/get_classifications.html",
		params:{},
		addfunction:function(data){
			addFunction(data);
		},
		removefun:function(data){
			removefun(data);
		}
	});
	
	_init();

	var addFunction = function(data){
//		var url="/quiz/bank/get_quiz_banks_by_classification.html",params={
//			classificationId:data
//		};
		var bankIds = new Array();
		$("span[banksid]").each(function(){
			bankIds.push($(this).attr("id"))
	    });
		var url="/quiz/bank/load_list.html",
		params={
			classificationId : data,
			bankIds : bankIds
		};
		postAjaxRequest(url, params, function(result){
			var template = doT.template($("#banks_template").text());	
			$('#banks_panel').append(template({data:result.data.dataList}));	
			customInput("remember");
			checkOrUncheckAll('questionall','remember');
			if(result.data.length>0){
				$("#questionall").prop("checked","");
				var label = $('label[for="questionall"]');
				label.removeClass('checked');
			}
		});
	};

	var removefun = function(data){
		$(".row_"+data).remove();
	};
	
	selectApplyOrUn=function(obj){
		var obj = $("#"+obj);
		if(obj.prop("checked")){
			$("input[id^='one']").prop("checked","checked");
		}else{
			$("input[id^='one']").prop("checked","");
		}
	};
	
	selectOrUnFunc=function(){
		var stuIds = [];
		$("#banks_panel tr").each(function(i){
	        if($(this).find("input").prop('checked')==true){
	        	stuIds.push($(this).find("td:eq(1)").text());
	        }
	    });
		if(stuIds.length == $("#banks_panel tr").length){
			$("#oneall").prop("checked","checked");
		}else{
			$("#oneall").prop("checked","");
		}
	};
	
	goBack = function(){
    	go2Page("/quiz/profile.html", "eFlag=" + $("#eFlag").val() + "&quizId=" + $("#quizId").val() + "&navTabs=2");
	}
	
	submitBanksRule = function() {
		var flag = true;
		var bankids = new Array();
		$("#banks_div .sel_btn").each(function(){
			bankids.push($(this).attr("banksid"));
		});
		if (bankids.length < 1) {
			alertMsg("请设置出题范围！");
			return;
		}
		$("#bankScope").val(bankids);
		var dataArray = new Array();
		if($("input[name='quizSet']:checked").length == 0) {
			alertMsg("请选择大题题型！");
			return;
		}
		$("input[name='quizSet']:checked").each(function(j,z) {
			debugger;
			var texArray = new Array();
		    $(this).parent().parent().parent().find('input').each(function(i,v) {
		    	debugger;
		    	if (i == 3) {
		    		if (parseInt($(this).val()) > parseInt($(this).parent().parent().find("span:last").text())) {
		    			alertMsg($(z).parent().siblings("span").text()+"的题目数大于题库内题目数，请重新设置");
		    			flag = false;
		    		}
		    	}
		    	if (!flag) {
					return false;
				}
		    	texArray.push($(this).val());  
		    });
		    if (!flag) {
				return false;
			}
		    dataArray.push(texArray.join("_"));
		});
		if (!flag) {
			return false;
		}
		$("#subjectSets").val(dataArray);
		$("#createForm").submit();
	};
	
	selDelQuizBank = function(id) {
		$("#"+id).remove();
		reloadSubjectSetting();
	};
	
	reloadSubjectSetting = function() {
		var bankIds = new Array();
		$("span[banksid]").each(function() {
			bankIds.push($(this).attr("id"))
	    });
		if (bankIds.length == 0) {
			console.log("bankIds",bankIds)
			for (var i = 1; i < 8; i++) {
				$("#subjctType"+i).text(0);
				$("._checked"+i).prop("checked",false);
				$("._checked"+i).prop("disabled",true);
				$("._lable"+i).removeClass('checked');
				$("._order"+i).val('');
				$("._order"+i).prop("disabled",true);
				$("._score"+i).val('');
				$("._score"+i).prop("disabled",true);
				$("._opcounts"+i).val('');
				$("._opcounts"+i).prop("disabled",true);
				$("._mark"+i).val('');
				$("._mark"+i).prop("disabled",true);
			}
			return;
		}
		var url="/quiz/subject/get_count.html",
		params = {
			bankIds : bankIds
		}
		postAjaxRequest(url, params, function(result) {
			var typeArray = new Array();
			$.each(result.data,function(i,v) {
				typeArray.push(v.quiz_subject_type);
				$("#subjctType"+v.quiz_subject_type).text(v.sumnum);
			});
			Array.prototype.diff = function(a) {
			    return this.filter(function(i) {return a.indexOf(i) < 0;});
			};
			var typeArrayRemain = [1,2,3,4].diff(typeArray);
			$.each(typeArrayRemain,function(i,v){
				$("#subjctType"+v).text(0);
				setDisable(v);
			})
		});
	}
	
	var getBanksInfo = function(){
		debugger;
		var url ="/quiz/generate_rules.html",data={
				quizId:$("#quizId").val()
		};
		
		postAjaxRequest(url, data, function(result){
			$.each(result.data,function(i,v){
				$("._checked"+v.subjectType).prop("checked",true);
				$("._lable"+v.subjectType).addClass("checked");
				$("._order"+v.subjectType).val(v.subjectOrder);
				$("._score"+v.subjectType).val(v.subjectScore);
				$("._opcounts"+v.subjectType).val(v.subjectItemsCount);
				$("._mark"+v.subjectType).val(v.subjectRemark);
			})
		});
	};
	
	return {getBanksInfo:getBanksInfo}
	
	checkSubjectCount = function() {
		
	}

});
