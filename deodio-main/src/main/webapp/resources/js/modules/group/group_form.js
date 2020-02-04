define([ "jquery", "utils.cookie", "jquery.dot", "utils", "jquery.base",
		"jquery.validate", "jquery.scrolltofixed", "jquery.ui" ], function($,
		cookie, doT) {

	var init = function() {
		$('select').selectpicker();
        var activeFormStatus=$('#activeFormStatus').val();
        if(activeFormStatus=="1"){
			$("#active_status").attr("checked", true);
        }else{
        	$("#active_status").attr("checked", false);
        }
		customInput("activestatus");
		customInput("survey");
		$('#preViewForm').myValidate({
			formCall : function() {
				submitBtn();
			},
			isAlert : false,
			formKey : false,
			errorCustom : {
				customFlag : true,
				regionText : true
			},
			errorStyle : {
				errorRegion : "error-custom",
				errorClass : "txt-wrong",
				rightClass : "txt-right"
			}
		});
	}

	init();

	// 拖拽函数
	var draggableFun = function(id, value) {
		$("#" + id).draggable({
			helper : "clone",
			cursor : "move",
			connectToSortable : '#draggableContent',
			start : function(event, ui) {
				$("#draggableVle").val(value);
			}
		});
	};

	$("#draggableContent").sortable({
		start : function(event, ui) {
			ui.item.css('border', '1px dashed #dddddd');
		},
		stop : function(event, ui) {
			ui.item.css('border', '');
			customInput("survey");
		},
		receive : function(event, ui) {
			var htmlTmpl = "";
			switch (Number($("#draggableVle").val())) {
			case 1:
				htmlTmpl = $("#radio_draggable_template").text();
				break;
			case 2:
				htmlTmpl = $("#checkbox_draggable_template").text();
				break;
			case 3:
				htmlTmpl = $("#validation_draggable_template").text();
				break;

			}
			;

			var doTtmpl = doT.template(htmlTmpl);

			var html = doTtmpl({
				"data" : UUID.prototype.createUUID()
			});
			if ($("#draggableContent div.timu").size() == 0) {
				$(this).append(html);
			} else {
				$("#draggableContent div.timu:last").after(html);
			}
			$("#draggableContent a.ui-draggable").remove();

		}
	});

	removeQuestion = function(obj) {
		$(obj).parent().parent().remove();

	}

	submitBtn = function() {
		var surveyData = getSurveyData();
		if (!surveyData) {
			alertMsg("请填写表单数据");
			return;
		}
		var groupId = $("#groupId").val();
		if (!groupId) {
			alertMsg("小组编号为空，请设置小组编号");
			return;
		}
		var url = "/group/form/submit.html", data = {
			dataStr : getSurveyData(),
			groupId : $("#groupId").val(),
			status : $("#active_status").prop("checked") ? "1" : 0
		};

		postAjaxRequest(url, data, function(result) {
			alertMsg("操作成功");
		});
	}

	var getSurveyData = function() {
		var dataStr = '', dataArrays = new Array();

		$(".timu").each(
				function(i, v) {
					var surveyType = $(this).attr("surveytype");
					var serveyTitle = $(this).find("h3 input").val();
					var surveyOption = '', arrays = new Array();
					$(this).find("div.ti").each(
							function(j, z) {
								var _tempVal = ' ';
								if (surveyType != 3) {
									_tempVal = replaceAll($(this).find(
											"input[type='text']").val(),
											'/\]=\[/gi', '');
								}
								arrays.push(_tempVal);

							})
					surveyOption = arrays.join("]=[");

					var _tempData = surveyType + "_#_" + serveyTitle + "_#_"
							+ surveyOption;

					dataArrays.push(_tempData)

				});

		return dataArrays.join("_@_");
	};

	addOptions = function(obj, optionsType) {
		var _timuDiv = $(obj).parent().parent(), _tiLastDiv = _timuDiv
				.find("div.ti:last"), _divFind = '', _class = '';

		if (optionsType == 1) {
			_divFind = "input[type='radio']";
			_class = 'radio-group';
			_inputType = "radio";

		} else if (optionsType == 2) {
			_divFind = "input[type='checkbox']";
			_class = 'checkbox-group';
			_inputType = "checkbox";
		}

		var _tiIds = _tiLastDiv.find(_divFind).attr("id").split("_");
		var data = {
			tiId : _tiIds[0] + "_" + (Number(_tiIds[1]) + 1),
			name : "survey" + _tiIds[0],
			values : (_timuDiv.find("div.ti").size() + 1),
			divClass : _class,
			inputType : _inputType

		};

		var doTtmpl = doT.template($("#options_add_template").text());
		var html = doTtmpl({
			"data" : data
		});
		_tiLastDiv.after(html);
		customInput("survey");
	};

	deleteOptions = function(obj) {
		$(obj).parent().remove();
	};

	previewBtn = function() {
		var surveyData = getSurveyData();
		if (surveyData) {
			$("#pre_data").val(surveyData);
			$("#preViewForm").submit();
		} else {
			alertMsg("请填写表单数据");
		}
	}
	return {
		draggableFun : draggableFun
	}

});
