define([ "jquery", "utils.cookie","pagination","jquery.dot","utils.list",
	"utils", "jquery.base","bootstrap.select"], function($, cookie, paging,doT,list) {

	var _init = function() {
		
		$('select').selectpicker();
		$("#limitCount").val("");
		list.onFixedItems();
	};


	_init();

	
	onAddRequiredSubject = function() {
		var subjects = new Array();
		$("#table_panle input[type='checkbox']:checked").each(function() {
		    subjects.push($(this).val());
		});
		if (subjects.length == 0) {
			$("#requiredQuizBanksModal").modal('hide');
			return false;
		}
		if (subjects.length > $("#limitCount").val()) {
			alertMsg('对应的题型选择数目大于出题规则中设置的题目数');
			return false;
		}
		var url="/quiz/subject/detail.html", 
		params = {
			subjectIds : subjects
		};
		postAjaxRequest(url, params, function(result) {
			onSetRequiredSubject(result);
			$("span[subtype="+$("#subjectType").val()+"]").text($("span[subtype="+$("#subjectType").val()+"]").text()-subjects.length);
			$("#requiredQuizBanksModal").modal('hide');
		},false);
	};
	
	onSetRequiredSubject = function(result) {
		var key = Number($("#subjectType").val()),doTtmpl='';
		switch (key) {
		case 1:
			doTtmpl =doT.template($("#subject_radio_template").text()); 
			break;
		case 2:
			doTtmpl =doT.template($("#subject_checkbox_template").text()); 
			break;
		case 3:
			doTtmpl =doT.template($("#subject_alternative_template").text()); 
			break;
		case 4:
			doTtmpl =doT.template($("#subject_order_template").text()); 
			break;
		case 5:
			doTtmpl =doT.template($("#subject_picture_template").text()); 
			break;
		case 6:
			doTtmpl =doT.template($("#subject_short_template").text()); 
			break;
		case 7:
			doTtmpl =doT.template($("#subject_space_template").text()); 
			break;
		}
		
		$("#draggableContent").append(doTtmpl({data:result.data}));
		refreshQuizNo();
		
	};
	
	submitRequiredQuizSubject = function() {
		var url = "/quiz/save/required.html",
		quiz = {
			id : $("#quizId").val(),
			subjectList : new Array(),
			subjectCount : new Array()
		};
		$(".chouti").each(function(i, v) {
			subject = {
				id : v.id,
				quizSubjectOrder : $(this).find('._subject-order').html()
			}
			quiz.subjectList[i] = subject;
		});
		$(".ti_right span").each(function(i, v) {
			quiz.subjectCount[i] = $(this).text() - $("._subjct" + i+1).size();
		});
		$("#subjectIds").val(quiz.subjectCount);
		postAjaxRequest(url, JSON.stringify(quiz), function(result) {
			go2Page("/quiz/auto/random.html", 
					"bankScope=" + $("#bankScope").val() +
					"&subjectCountArray=" + $("#subjectIds").val() + 
					"&quizId=" + $("#quizId").val());
		},false,"application/json");
		
//		debugger;
//		$("#subjectIds").val(function(){
//			var suArray = new Array();
//			for (var i = 1; i <= 7; i++) {
//				suArray.push($("._subjct" + i).size());
//			};
//			var subjects = new Array();
//			$(".ti_right span").each(function(i, v) {
//				var _sc = $(this).text() - suArray[i],
//				_st = $(this).attr("subtype");
//				subjects.push(_sc+","+_st);
//			});
//			var paramsData = new Array();
//			$.each(subjects, function(i, v) {
//				var _index = i + 1;
//				var ids = new Array();
//				var $subject = $("._subjct" + _index);
//				if ($subject.length == 0) {
//					ids.push(" ");
//				} else {
//					$subject.each(function() {
//						ids.push($(this).attr("id"));
//					});
//				};
//				var finalVal = v + "_#_" + ids.join(',');
//				paramsData.push(finalVal)
//			});
//			return paramsData.join("_@_");
//		});
//		$("#subjectAnswer").val(function(){
//			var answer = new Array();
//			$(".chouti").each(function(){
//				  var subjectId =$(this).attr('id'),_answers = new Array();
//			  	var $Subjects = $(this).find("input[name^='quiz']:checked");
//			  	if($Subjects.length==0){
//				  if($(this).attr('sutype')=='4'){
//					 $(this).find("div.ti_input_w").each(function(){
//						  _answers.push($.trim($(this).text()));
//					 });
//					
//				  };
//			  }else{
//				  $Subjects.each(function(){
//					  _answers.push($(this).val());				  
//				  });			  
//			  };
//			  answer.push(subjectId+"_@_"+_answers.join('#'));
//			  
//			});
//			
//			return answer;
//		});
		
//		$("#quizSubjectOrder").val(function(){
//			var order=new Array();
//			$(".chouti").each(function(){
//				var _order=$(this).find('._subject-order').html();
//				order.push(_order);
//			});
//			return order;
//		});
		
//		$("#saveForm").submit();
	};
	
	
	 arrayOutIndex = function(data,chars,index){
		var arrays = data.split(chars);
		return arrays[index];
	} ;
	
	goBack = function() {
		var quizId = $("#quizId").val();
		go2Page("/quiz/auto/content.html", "eFlag=" + $("#eFlag").val() + "&quizId=" +quizId + "&navTabs=2");
	};
	
	//查看题目
	viewQuizBankDetail = function(subjectId) {
		var url = "/quiz/subject/get_by_id.html", data = {
			quizSubjectId : subjectId
		};
		postAjaxRequest(url, data, function(result) {
			if(result.status == 1) {
				viewQuizsDialogue(result.data.quizSubjectType, result.data);
			}
		});
	}
});
