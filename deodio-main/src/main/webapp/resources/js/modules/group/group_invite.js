define(
		[ "jquery", "utils.cookie", "utils", "jquery.base", "jquery.validate",
				"ueditor", "ueditor.config", "bootstrap.select",
				"upload.common" ],
		function($, cookie) {

			um = UM.getEditor('content_template');

			uploadProcess('uploadExcel', '*.xlsx;*.xls;', {}, ctx
					+ '/group/upload_excel_template.html', function(fileName) {
				$("#tempUp1").uploadify('settings', 'formData', {});
			},
			// 回调函数
			function(result) {
				if (result.status == 1) {
					$("#user_mail").val(result.data);
				} else {
					if (result.message != null) {
						alertMsg(result.message);
					} else {
						alertMsg("上传失败");
					}

				}

			}, true, "批量导入");

			var init = function() {
				$('select').selectpicker();
				// $('.edui-container').css('border',"1px solid #cdd5d9;")
				$('#uploadExcel').css('margin-bottom', '0px').css('margin-top', '-10px');
				customInput('remember');
			};

			init();

			$('#previewMailForm').myValidate({
				formCall : function() {
					submitPreview();
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

			// 开始预览
			submitPreview = function() {
				var groupId = $("#groupId").val();
				if (!groupId) {
					alertMsg("小组编号为空，请设置小组编号");
					return;
				}
				var userMail = $.trim($("#user_mail").val());
				var sendSearch = $("#sendSearch").prop("checked") ? 1 : 0;
				if (!validateUserMailInput(userMail)) {
					alertMsg("请确认邀请用户是否填写或者填写格式是否正确");
					return;
				}
				$('body').scrollTop(0);
				$("#div_preview").show();
				$("#srcContent_div").hide();
				$("#eamil_values").val(userMail);
				$("#joinTitle").val($.trim($("#subTitle").val()))
				$("#userRole").val($("#role_select").val())
				$("#joinContent").val(um.getContentTxt());
				$("#sendForm").val(sendSearch);
				generatePriveiwMailText();
			};

			contentBack = function() {
				$("#srcContent_div").show();
				$("#div_preview").hide();
			};

			// 提交，发送邀请邮件
			submitJoin = function() {
				confirmMsg('您确认要邀请以下用户加入小组吗？确认后将发送邀请邮件？', function() {
					$("#joinForm").submit();
				},'400px','180px');
			}

			var generatePriveiwMailText = function() {
				var joinTitle = $("#joinTitle").val();
				var joinContent = $("#joinContent").val();

				$("#mailSubject").text(joinTitle);
				$("#mailContent").text(joinContent);
			}

			downloadTemplate = function() {
				go2Page("/group/down_excel_template.html");
			}

			var validateUserMailInput = function(userMailInput) {

				var DELIMITER_SEMICOLON = ";";
				var isValid = false;
				// 邮件正则
				var mailRegex = new RegExp(
						/^[a-zA-Z0-9]{1}([\._a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+){1,3}$/);
				var endWithRegex = new RegExp(/;$/);

				if (endWithRegex.test(userMailInput)) {
					userMailInput = userMailInput.substr(0,
							userMailInput.length - 1);
				}

				var userMails = userMailInput.split(DELIMITER_SEMICOLON);

				if (userMails != null && userMails.length > 0) {
					for ( var index in userMails) {
						if (mailRegex.test(userMails[index])) {
							isValid = true;
						} else {
							isValid = false;
							break;
						}
					}
				}
				return isValid;
			}
			validateSendForm = function() {
				var formStatus = $("#activeFormStatus").val();
				if ($("#sendSearch").is(":checked") == false) {
					if (!formStatus || formStatus == 0) {
						alertMsg("表单未激活!", 350, 200, function() {
							$("#sendSearchLabel").removeClass("checked");
							$("#sendSearch").attr("checked", false);
						});
						return false;
					}
				}
			}
		});
