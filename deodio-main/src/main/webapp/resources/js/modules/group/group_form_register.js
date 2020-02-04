define(
		[ "jquery", "utils.cookie", "jquery.dot", "utils", "jquery.base",
				"jquery.scrolltofixed", "jquery.ui", "jquery.mCustomScrollbar" ],
		function($, cookie, doT) {

			var init = function() {

				$("[name^='survey']").each(function() {
					var name = $(this).attr("name");
					customInput(name);
				});
			}
			submitBtn = function() {
				var groupId = $("#groupId").val();
				var userExist = $("#userExist").val();
				if (!groupId) {
					alertMsg("小组编号为空，请设置小组编号");
					return;
				}
				var url = "/group/insertSurveyResult.html", data = {
					dataStr : getSurveyData(),
					groupId : groupId,
					um : $("#um").val()
				};

				postAjaxRequest(url, data, function(result) {
					alertMsg("操作成功", function() {
						if (userExist!="true") {
							go2Page("/group/setPwd.html", "gp=" + groupId
									+ "&userId=" + result.data.id
									+ "&userName=" + result.data.nickName
									+ "&ur=" + $("#ur").val());
						} else {
							setRolePage();
						}
					});

				});
			}
			setRolePage=function(){
				var ur=$("#ur").val();
				if(ur==$("#roleLeaderId").val()){
				go2Page("/group/profile.html","groupId="+$("#gp").val()+"&groupRoleId="+ur);
				}
				if(ur==$("#roleCreatorId").val()){
				go2Page("/group/features.html","groupId="+$("#gp").val()+"&groupRoleId="+ur);
				}
				if(ur==$("#roleViewerId").val()){
				 go2Page("/group/intro.html","groupId="+$("#gp").val()+"&groupRoleId="+ur);
				}
			}
			var getSurveyData = function() {
				var dataStr = '', dataArrays = new Array();

				$(".timu")
						.each(
								function(i, v) {
									var surveyType = $(this).attr("surveytype");
									var serveyTitle = $(this).find("h3 input")
											.val();
									var surveyOption = '', arrays = new Array();
									$(this)
											.find("div.ti")
											.each(
													function(j, z) {
														var _tempVal = ' ';
														if (surveyType != 3) {
															_tempVal = replaceAll(
																	$(this)
																			.find(
																					"input[type='text']")
																			.val(),
																	'/\]=\[/gi',
																	'');
														}
														var _options = $(this)
																.find(
																		'input[type="hidden"]');
														_options
																.each(function() {
																	var isCorrect = $(
																			this)
																			.parent()
																			.siblings(
																					"div")
																			.find(
																					'input')
																			.prop(
																					"checked") == true ? "1"
																			: 0;
																	_tempVal = _tempVal
																			+ "_&_"
																			+ isCorrect;
																});
														arrays.push(_tempVal);

													})
									surveyOption = arrays.join("]=[");

									var _tempData = surveyType + "_#_"
											+ serveyTitle + "_#_"
											+ surveyOption;

									dataArrays.push(_tempData)

								});

				return dataArrays.join("_@_");
			};

			init();

		});
