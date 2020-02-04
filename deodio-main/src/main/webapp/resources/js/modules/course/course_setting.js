define(
		[ "jquery", "utils.dtree", "utils.cookie", "utils.menu", "jquery.dot",
				"pagination", "utils", "jquery.base", "jquery.validate",
				"ueditor", "jquery.mCustomScrollbar", "bootstrap.select",
				"upload.common", "bootstrap.datepicker" ],
		function($, tree, cookie, menu, doT, paging) {

			// 课程规则配置常量
			var courseSettingConfigs = {
				divConfig : {
					1 : 'right_content_test_quiz',
					2 : 'right_content_quiz',
					3 : 'right_content_survey',
					4 : 'right_content_material',
					5 : 'right_content_homework',
					6 : 'right_content_notice',
					7 : 'right_content_related_course',
					8 : 'right_content_registed_users'
				},
				callBackConfig : {
					1 : {
						'url' : '/course/test_quiz/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程测验提交失败，请重试！"'
					},
					2 : {
						'url' : '/course/quiz/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程毕业考试提交失败，请重试！"'
					},
					3 : {
						'url' : '/course/survey/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程问卷调查提交失败，请重试！"'
					},
					4 : {
						'url' : '/course/material/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程文件上传失败，请重试！"'
					},
					5 : {
						'url' : '/course/homework/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程作业提交失败，请重试！"'
					},
					6 : {
						'url' : '/course/notice/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程消息发布失败，请重试！"'
					},
					7 : {
						'url' : '/course/related/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该相关课程设置失败，请重试！"'
					},
					8 : {
						'url' : '/course/registed_users/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程作业发布失败，请重试！"'
					}
				},
				refreshData : {
					1 : 'loadTestQuizDataList',
					2 : 'loadQuizDataList',
					3 : 'loadSurveyDataList',
					4 : 'loadMaterialDataList',
					5 : "loadHomeworkDataList",
					6 : 'loadNoticeDataList',
					7 : 'loadRelatedCourseDataList',
					8 : 'loadRegistedUsersData'
				}
			};
			// 课程规则配置常量
			var courseManagerConfigs = {
				managerDivConfig : {
					1 : 'right_content_manager_test_quiz',
					2 : 'right_content_manager_quiz',
					3 : 'right_content_manager_survey',
					4 : 'right_content_manager_homework'
				},
				managercallBackConfig : {
					1 : {
						'url' : '/course/test_quiz/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程测验提交失败，请重试！"'
					},
					2 : {
						'url' : '/course/quiz/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程毕业考试提交失败，请重试！"'
					},
					3 : {
						'url' : '/course/survey/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程问卷调查提交失败，请重试！"'
					},
					4 : {
						'url' : '/course/homework/submit.html',
						'alertMsg' : '"抱歉，因为网络问题该课程文件上传失败，请重试！"'
					}
				},
				managerRefreshData : {
					1 : 'loadTestQuizManagerDataList',
					2 : 'loadQuizManagerDataList',
					3 : 'loadSurveyManagerDataList',
					4 : 'loadHomeworkManagerDataList'
				}
			};
			// 是否为编辑模式
			var editFlg = false;
			var targetId = null;

			// 当前设置项目
			var currentCourseSettingItem = "";

			// 页面初始化函数
			var init = function() {
				$('select').selectpicker();
				$('.bootstrap-select').css('width', '139px').css('top', '-1px');
				$('.bootstrap-select button').css('height', '34px');
				// 设置默认选中项目
				var courseType = $("#courseType").val();
				if (courseType == 1) {
					onSelectItems(1);
				} else if (courseType == 2) {
					onSelectItems(2);
				}

				menu.onLeftMenu();
			};

			// 调用函数方法
			doSettingCallBack = function(fn, args) {
				fn.apply(this, args);
			}

			// 选中页面按钮触发事件
			onSelectItems = function(buttonIndex) {
				$("div[id^=right_content_]").hide();
				$('li[id^=menu_]').removeClass('active');
				$('#menu_' + buttonIndex).addClass('active');
				// alert(buttonIndex);
				// alert(courseSettingConfigs.divConfig[buttonIndex]);
				// alert(eval(courseSettingConfigs.refreshData[buttonIndex]))
				currentCourseSettingItem = buttonIndex;
				var courseType = $("#courseType").val();
				// if((buttonIndex == 7) && courseType == '1'){
				// return;
				// }
				// 显示 现在的div层
				$(
						"#"
								+ courseSettingConfigs.divConfig[currentCourseSettingItem])
						.show();
				// 初始化查询数据
				doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));

				onChooseLeftMenu(buttonIndex);
			};

			// 课程规则设置回调函数
			courseSettingCallBackFunc = function(dataObj) {

				var url = courseSettingConfigs.callBackConfig[currentCourseSettingItem].url;
				var alertMsgs = courseSettingConfigs.callBackConfig[currentCourseSettingItem].alertMsg;
				var data = dataObj;
				postAjaxRequest(
						url,
						data,
						function(result) {
							if (result.status == 1) {
								// 关闭对应modal
								closeSelecteItemModal();
								// 刷新页面数据
								doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));
								// modal页面提交后 重置editFlg
								editFlg = false;
							} else {
								alertMsg(alertMsgs);
							}
						});
			};

			// 页面意外关闭后重置editFlg
			courseSettingModelCloseCallBack = function() {
				editFlg = false;
				targetId = null;
			}

			// 关闭modal
			closeSelecteItemModal = function() {
				var divId = courseSettingConfigs.divConfig[currentCourseSettingItem];
				var modalId = $("#" + divId).find("[data-toggle='modal']")
						.attr("data-target");
				// alert(modalId);
				$(modalId).modal('hide');
			}

			// 显示modal
			openSelectItemModal = function() {
				var divId = courseSettingConfigs.divConfig[currentCourseSettingItem];
				var modalId = $("#" + divId).find("[data-toggle='modal']")
						.attr("data-target");
				$(modalId).modal('show');
			}

			// 获取页面模式 编辑货值新建
			setModalState = function() {
				return {
					editFlg : editFlg,
					targetId : targetId
				};
			}

			loadTestQuizDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					keyword : $.trim($('#testQuizKeyWord').val()),
					courseId : $('#courseId').val(),
				}, url = "/course/test_quiz/load_list.html";
				postAjaxRequest(url, params, function(result) {
					paging.pageTemplateDiv(result, "table_content_test_quiz",
							"table_content_test_quiz_template",
							"course_setting_test_quiz_page_Panel",
							"loadTestQuizDataList");
				});
			};
			// -------------------------------------------- quiz
			// start-----------------------------------
			// 加载课程题库列表
			loadQuizDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					keyword : $.trim($('#quizKeyWord').val()),
					courseId : $('#courseId').val(),
				}, url = "/course/quiz/load_list.html";
				postAjaxRequest(url, params, function(result) {
					paging.pageTemplateDiv(result, "table_content_quiz",
							"table_content_quiz_template",
							"course_setting_page_Panel", "loadQuizDataList");
				});
			};

			// 编辑课程文件
			editQuiz = function(quizId) {
				editFlg = true;
				targetId = quizId;
				openSelectItemModal();

			}

			// 删除课程文件
			deleteQuiz = function(quizId) {
				confirmMsg(
						"确定要删除当前毕业考试？",
						function() {
							var url = "/course/quiz/delete.html", data = {
								quizId : quizId
							};
							postAjaxRequest(
									url,
									data,
									function(result) {
										if (result.status == 1) {
											// 刷新页面数据
											doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));
										} else {
											alertMsg("抱歉，因为网络问题该课程毕业考试删除失败，请重试！");
										}
									});
						});

			}

			// ---------------------------------------------- quiz end
			// ---------------------------------------

			// -------------------------------------------- survey
			// start-----------------------------------
			// 加载课程问卷调查列表
			loadSurveyDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					keyword : $.trim($('#surveyKeyWord').val()),
					courseId : $('#courseId').val(),
				}, url = "/course/survey/load_list.html";
				postAjaxRequest(url, params, function(result) {
					// var template =
					// doT.template($("#table_content_survey_template").text());
					// $("#table_content_survey").html(template(result));
					paging.pageTemplateDiv(result, "table_content_survey",
							"table_content_survey_template",
							"course_setting_survey_page_Panel",
							"loadSurveyDataList");
				});
			};

			// 编辑课程问卷调查
			editSurvey = function(surveyId) {
				editFlg = true;
				targetId = surveyId;
				openSelectItemModal();
			}

			// 删除课程问卷调查
			deleteSurvey = function(id) {

				confirmMsg(
						"确定要删除当前问卷调查？",
						function() {
							var url = "/course/survey/delete.html", data = {
								id : id
							};
							postAjaxRequest(
									url,
									data,
									function(result) {
										if (result.status == 1) {
											// 刷新页面数据
											doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));
										} else {
											alertMsg("抱歉，因为网络问题该课程问卷调查删除失败，请重试！");
										}
									});
						});

			}

			// ---------------------------------------------- survey end
			// ---------------------------------------

			// -------------------------------------------- material
			// start-----------------------------------
			// 加载课程文件列表
			loadMaterialDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					pageSize : 5,
					keyword : $.trim($('#materialKeyWord').val()),
					courseId : $('#courseId').val(),
				}, url = "/course/material/load_list.html";
				postAjaxRequest(url, params,
						function(result) {
							paging.pageTemplateDiv(result,
									"table_content_material",
									"table_content_material_template",
									"material_data_page_Panel",
									"loadMaterialDataList");
						});
			};

			// 编辑课程文件
			editMaterial = function(materialId) {
				editFlg = true;
				targetId = materialId;
				openSelectItemModal();
			}

			// 删除课程文件
			deleteMaterial = function(materialId) {

				confirmMsg(
						"确定要删除当前文件？",
						function() {
							var url = "/course/material/delete.html", data = {
								materialId : materialId
							};
							postAjaxRequest(
									url,
									data,
									function(result) {
										if (result.status == 1) {
											// 刷新页面数据
											doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));
										} else {
											alertMsg("抱歉，因为网络问题该课程作业删除失败，请重试！");
										}
									});
						});
			}

			// ---------------------------------------------- material end
			// ---------------------------------------

			// -------------------------------------------- homework
			// start-----------------------------------
			// 加载课程作业列表
			loadHomeworkDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					pageSize : 5,
					keyword : $.trim($('#homeworkKeyWord').val()),
					courseId : $('#courseId').val(),
				}, url = "/course/homework/load_list.html";
				postAjaxRequest(url, params,
						function(result) {
							paging.pageTemplateDiv(result,
									"table_content_homework",
									"table_content_homework_template",
									"homework_data_page_Panel",
									"loadHomeworkDataList");
						});
			};

			// 编辑课程作业
			editHomework = function(homeworkId) {
				editFlg = true;
				targetId = homeworkId;
				openSelectItemModal();

			}

			// 删除课程作业
			deleteHomework = function(homeworkId) {

				confirmMsg(
						"确定要删除当前课后作业？",
						function() {
							var url = "/course/homework/delete.html", data = {
								homeworkId : homeworkId
							};
							postAjaxRequest(
									url,
									data,
									function(result) {
										if (result.status == 1) {
											// 刷新页面数据
											doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));
										} else {
											alertMsg("抱歉，因为网络问题该课程作业删除失败，请重试！");
										}
									});
						});
			}

			// ---------------------------------------------- homework end
			// ---------------------------------------

			// -------------------------------------------- notice
			// start-----------------------------------
			// 加载通知列表
			loadNoticeDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var params = {
					pageNo : pageNo,
					keyword : $.trim($('#noticeKeyWord').val()),
					courseId : $('#courseId').val(),
				}, url = "/course/notice/load_list.html";
				postAjaxRequest(url, params, function(result) {
					paging.pageTemplateDiv(result, "table_content_notice",
							"table_content_notice_template",
							"notice_data_page_Panel", "loadNoticeDataList");
				});
			};

			// 编辑课程通知
			editNotice = function(noticeId) {
				editFlg = true;
				targetId = noticeId;
				openSelectItemModal();

			}

			// 删除课程通知
			deleteNotice = function(noticeId) {

				confirmMsg(
						"确定要删除当前通知？",
						function() {
							var url = "/course/notice/delete.html", data = {
								noticeId : noticeId
							};
							postAjaxRequest(
									url,
									data,
									function(result) {
										if (result.status == 1) {
											// 刷新页面数据
											doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));
										} else {
											alertMsg("抱歉，因为网络问题该课程作业删除失败，请重试！");
										}
									});

						});
			}

			// ---------------------------------------------- notice end
			// ---------------------------------------

			// -------------------------------------------- related_course
			// start-----------------------------------
			// 加载相关课程列表
			loadRelatedCourseDataList = function(pageNo) {
				var params = {
					keywords : $.trim($('#relatedKeyWord').val()),
					courseId : $('#courseId').val(),
				}, url = "/course/related/load_list.html";
				postAjaxRequest(url, params, function(result) {
					var template = doT.template($(
							"#table_content_related_course_template").text());
					$("#table_content_related_course").html(template(result));
				});
			};

			// 编辑课程作业
			editRelatedCourse = function(relatedId) {
				editFlg = true;
				targetId = relatedId;
				openSelectItemModal();

			}

			// 删除课程作业
			deleteRelatedCourse = function(relatedId) {

				confirmMsg(
						"确定要删除当前相关课程？",
						function() {
							var url = "/course/related/delete.html", data = {
								relatedId : relatedId
							};
							postAjaxRequest(
									url,
									data,
									function(result) {
										if (result.status == 1) {
											// 刷新页面数据
											doSettingCallBack(eval(courseSettingConfigs.refreshData[currentCourseSettingItem]));
										} else {
											alertMsg("抱歉，因为网络问题该相关课程删除失败，请重试！");
										}
									});

						});
			}

			// ---------------------------------------------- related_course end
			// ---------------------------------------

			// ---------------------------------------------- registed_users
			// start ---------------------------------------
			loadRegistedUsersData = function() {
				var params = {
					courseId : $('#courseId').val(),
				}, url = "/course/registed_users/load_data.html";
				postAjaxRequest(
						url,
						params,
						function(result) {
							var template = doT.template($(
									"#right_content_registed_users_template")
									.text());
							$(
									'#'
											+ courseSettingConfigs.divConfig[currentCourseSettingItem])
									.html(template(result.data));
							initRegistedUsersControl();
						});
			};

			// 删除课程作业
			submitRegistedUsers = function() {
				var data = {
					id : $("#registedUsersId").val(),
					courseId : $("#courseId").val(),
					isRegisteDesk : getRadioVal("isRegisteDesk") || 0,
					startTime : $("#startTime").val(),
					expireTime : $("#expireTime").val()
				};

				var dataJson = {
					dataJson : JSON.stringify(data)
				}
				courseSettingCallBackFunc(dataJson);
			}

			initRegistedUsersControl = function() {
				customInput('isRegisteDesk');
				$('#courseRegisteForm').myValidate({
					formCall : function() {
						submitRegistedUsers();
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

				// 日期控件
				$(".form_datetime").datetimepicker({
					format : "yyyy-mm-dd",
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					minView : 2,
					forceParse : 0
				});
			}
			// ---------------------------------------------- registed_users end
			// ---------------------------------------

			// 页面初始化
			$(document).ready(function() {
				init();
			});
			// -----------------managerCourse start------------------
			// 当前管理的id
			var currentCourseManagerId = "";
			// 管理加载的页面
			loadManager = function(currentCourseManagerItem, managerId) {
				// alert(currentCourseManagerItem)
				// alert(managerId)
				// alert(courseManagerConfigs.managerDivConfig[currentCourseManagerItem])
				$("div[id^=right_content_]").hide();
				currentCourseManagerId = managerId;
				$(
						"#"
								+ courseManagerConfigs.managerDivConfig[currentCourseManagerItem])
						.show();
				// 清除上次搜索结果
				$("#managerTestQuizKeyWord").val("");
				// 清除上次搜索结果
				$("#managerQuizKeyWord").val("");
				// 增加姓名或成绩进行搜索的功能
				// $("#managerQuizKeyWord").attr("placeholder","输入姓名或成绩进行搜索");
				// 清除上次搜索结果
				$("#managerSurveyKeyWord").val("");
				// 清除上次搜索结果
				$("#managerHomeworkKeyWord").val("");
				// 增加姓名或文件名进行搜索的功能
				// $("#managerHomeworkKeyWord").attr("placeholder","输入姓名或文件名进行搜索");
				// alert(eval(courseManagerConfigs.managerRefreshData[currentCourseManagerItem]))
				// 初始化查询数据
				doSettingCallBack(eval(courseManagerConfigs.managerRefreshData[currentCourseManagerItem]));

			}
			// 管理页面返回按钮点击事件
			backManage = function(currentManagerDiv, currentCourseSettingItem,
					current_quiz) {
				// 隐藏 现在的manager层
				$("#" + currentManagerDiv).hide();
				// 显示 现在的div层
				$("#" + current_quiz).show();
				// 初始化查询数据
				doSettingCallBack(eval(current_quiz));
			}
			// 加载测验成绩数据
			loadTestQuizManagerDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var passValue = $("#course_test_quiz_pass").children(
						'option:selected').val();
				var typeValue = $("#course_test_quiz_type").children(
						'option:selected').val();
				var keyValue = $("#managerTestQuizKeyWord").val();
				var courseId = $('#courseId').val();
				var params = {
					courseQuizId : currentCourseManagerId,
					pageNo : pageNo,
					passValue : passValue,
					typeValue : typeValue,
					keyValue : keyValue,
					courseId : courseId,
				}, url = "/course/test_quiz/managerquiz.html";
				postAjaxRequest(url, params, function(result) {
					paging.pageTemplateDiv(result,
							"table_content_manager_test_quiz",
							"right_content_manager_test_quiz_template",
							"course_setting_manager_test_quiz_page_Panel",
							"loadTestQuizManagerDataList");
				});
			};
			// 加载成绩管理数据
			loadQuizManagerDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var passValue = $("#course_quiz_pass").children(
						'option:selected').val();
				var typeValue = $("#course_quiz_type").children(
						'option:selected').val();
				var keyValue = $("#managerQuizKeyWord").val();
				var courseId = $('#courseId').val();
				var params = {
					courseQuizId : currentCourseManagerId,
					pageNo : pageNo,
					passValue : passValue,
					typeValue : typeValue,
					keyValue : keyValue,
					courseId : courseId,
				}, url = "/course/quiz/managerquiz.html";
				postAjaxRequest(url, params, function(result) {
					paging.pageTemplateDiv(result,
							"table_content_manager_quiz",
							"right_content_manager_quiz_template",
							"course_setting_manager_page_Panel",
							"loadQuizManagerDataList");
				});
			};
			// 加载调查结果数据
			loadSurveyManagerDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var keyValue = $("#managerSurveyKeyWord").val();
				var params = {
					pageNo : pageNo,
					courseSurveyId : currentCourseManagerId,
					keyValue : keyValue,
					courseId : $('#courseId').val(),
				}, url = "/course/survey/managersurvey.html";
				postAjaxRequest(url, params, function(result) {
					paging.pageTemplateDiv(result,
							"table_content_manager_survey",
							"right_content_manager_survey_template",
							"course_setting_manager_Surveypage_Panel",
							"loadSurveyManagerDataList");
				});
			};
			// 加载作业管理数据
			loadHomeworkManagerDataList = function(pageNo) {
				if (pageNo == undefined)
					pageNo = 1;
				var keyValue = $("#managerHomeworkKeyWord").val();
				var params = {
					pageNo : pageNo,
					courseHomeworkId : currentCourseManagerId,
					courseId : $('#courseId').val(),
					keyValue : keyValue,
				}, url = "/course/homework/managerhomework.html";
				postAjaxRequest(url, params, function(result) {
					paging.pageTemplateDiv(result,
							"table_content_manager_homework",
							"right_content_manager_homework_template",
							"course_setting_manager_homeworkpage_Panel",
							"loadHomeworkManagerDataList");
				});
			};
			$("#course_quiz_pass").change(function() {
				loadQuizManagerDataList();
			});

			$("#course_quiz_type").change(function() {
				loadQuizManagerDataList();
			});
			$("#course_test_quiz_pass").change(function() {
				loadTestQuizManagerDataList();
			});

			$("#course_test_quiz_type").change(function() {
				loadTestQuizManagerDataList();
			});
			onDetail = function(id) {
				var courseType = $("#courseType").val();
				debugger;
				if (courseType == 2) {
					go2Page('/course/offline/profile.html', "courseId=" + id);
				} else {
					go2Page('/course/online/profile.html', "courseId=" + id);
				}
			};
			cancleFile = function() {
				$("#zipFileName").val("");
				$("#materialId").val('');
				$("#progressBar").val('');
				$("#materialGenerateName").val('');
			}

			var onChangeLeftMenu = function(menuId) {
				$('.left_menu li')
						.each(
								function() {

									if ($(this).attr('id') != 'menu_' + menuId) {
										$(this).find('a img').attr(
												'src',
												$(this).find('a img').attr(
														'src').replace(
														'_active.png', '.png'));
									}
								});
			};

			onChooseLeftMenu = function(menuId) {
				onChangeLeftMenu(menuId);
				$('.left_menu li').each(function() {
					if ($(this).attr('id') == 'menu_' + menuId) {
						if (!$(this).hasClass('active')) {
							onChangeLeftMenu(menuId);
						}
					}

				});
			};

		});
