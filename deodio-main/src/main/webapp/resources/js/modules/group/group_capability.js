define(
		[ "jquery", "utils.cookie", "jquery.dot", "utils", "jquery.base",
				"jquery.validate", "jquery.scrolltofixed", "jquery.ui" ],
		function($, cookie, doT) {

			var init = function() {
				onLoadCapabilityItemHeader();

				$('div[id^="knowledge_tools_"]').draggable({
					helper : "clone",
					cursor : "move",
					containment : '#containment-wrapper',
					scroll : false
				});

			};
			onPopupCapabilityItem = function(type) {
				if (type == 1) {
					$("#groupToolModal").modal("show");
					$('body').addClass('modal-open').css('overflow-y','hidden');
				}
				if (type == 2) {
					$("#groupCapabilityModal").modal("show");
					$('body').addClass('modal-open').css('overflow-y','hidden');
				}
			}
			onSelectAll = function(obj) {
				var isChecked = $(obj).is(':checked');
				$('#capabilityBody').find(
						'[type=checkbox][name=capabilityItem]').each(
						function() {
							$(this).prop('checked', isChecked);
							$(this).attr('checked', isChecked);
							isChecked ? $(
									'label[for=' + $(this).attr('id') + ']')
									.addClass('checked') : $(
									'label[for=' + $(this).attr('id') + ']')
									.removeClass('checked');
						});

			};
			onSelectCapabilityToolItem = function(type) {
				var capabilityIdList = "";
				$('#capabilityToolBody').find(
						'[type=checkbox][name=capabilityToolItem]').each(
						function() {
							if ($(this).is(":checked"))
								capabilityIdList += $(this).attr('id') + "_@_";
						});

				var url = "/group/update_capability_items.html", data = {
					capabilityType : type,
					groupId : $('#groupId').val(),
					capabilityIds : capabilityIdList
				};
				postAjaxRequest(url, data, function(result) {
					go2Page("/group/capability.html", "groupId="
							+ $('#groupId').val());
					// onLoadCapabilityItem(type);

				});
			};
			onSelectCapabilityItem = function(type) {
				var capabilityIdList = "";
				$('#capabilityBody').find(
						'[type=checkbox][name=capabilityItem]').each(
						function() {
							if ($(this).is(":checked"))
								capabilityIdList += $(this).attr('id') + "_@_";
						});

				var url = "/group/update_capability_items.html", data = {
					capabilityType : type,
					groupId : $('#groupId').val(),
					capabilityIds : capabilityIdList
				};
				postAjaxRequest(url, data, function(result) {
					go2Page("/group/capability.html", "groupId="
							+ $('#groupId').val());
					// onLoadCapabilityItem(type);

				});
			};

			onLoadCapabilityItem = function(type) {
				var url = "/group/load_capability_items.html", data = {
					capabilityType : type,
					groupId : $('#groupId').val()
				};
				postAjaxRequest(url, data, function(result) {

					if (type == 1) {
						var template = doT.template($(
								"#t_body_capability_items_data_template")
								.text());
						$('#knowledge_capability_items_panel').html(template({
							data : result.data
						}));
						onDropCapabilityItems();
					} else if (type == 2) {
						var template = doT.template($(
								"#t_body_capability_items_data_template")
								.text());
						$('#ability_capability_items_panel').html(template({
							data : result.data
						}));
						onEditCapabilityItems();
					}
				});
			};

			onLoadCapabilityItemHeader = function() {
				
				var url = "/group/load_capability_items_header.html", data = {
					groupId : $('#groupId').val()
				};
				postAjaxRequest(url, data, function(result) {

					var template = doT.template($(
							"#t_head_capability_items_data_template").text());
					$('#knowledge_capability_items_header_panel').html(
							template({
								data : result.data
							}));
					$('#ability_capability_items_header_panel').html(template({
						data : result.data
					}));
					onLoadCapabilityItem(1);
					onLoadCapabilityItem(2);
				});
			};

			onDelCapabilityItemInTable = function(obj) {
				$(obj).parent().remove();
			};

			onDropCapabilityItems = function() {
				$('td[name^="knowledge_capability_item_"]')
						.droppable(
								{
									drop : function(event, ui) {
										$(this).children().remove();
										var str = ui.draggable.clone().attr(
												'id').split("_");
										var text = ui.draggable.clone().find(
												'h3').text();
										var source = "<span class='d"
												+ str[2]
												+ "'>"
												+ text
												+ "<button type='button'class='sel_del' onclick='onDelCapabilityItemInTable(this)'>x</span>";
										$(this).append(source);
									}
								});
			};

			onEditCapabilityItems = function() {

				$('td[name^="ability_capability_item_"]')
						.click(
								function() {
									if (!$(this).is('.input')) {
										$(this)
												.addClass('input')
												.html(
														'<textarea class="input_box" rows="5"  onpropertychange="this.style.height=this.scrollTop+this.scrollHeight+px" oninput="this.style.height=this.scrollTop+this.scrollHeight+px">'
																+ $(this)
																		.text()
																+ '</textarea>')
												.find('textarea')
												.focus()
												.blur(
														function() {
															if ($.trim($(this)
																	.val()).length > 0) {
																$(this)
																		.parent()
																		.removeClass(
																				'input')
																		.html(
																				"<text class='text'>"
																						+ $(
																								this)
																								.val()
																						+ "</text>");
															} else {
																$(this)
																		.parent()
																		.removeClass(
																				'input')
																		.html(
																				"");
															}

														});
									}
								});

			};

			onSubmit = function() {
				var groupId = $("#groupId").val();
				if (!groupId) {
					alertMsg("小组编号为空，请设置小组编号");
					return;
				}
				var list = [];
				$('td[name^="knowledge_capability_item_"]').each(
						function() {
							if ($.trim($(this).html()).length > 0) {
								list.push($(this).attr('id')
										+ "_@_"
										+ $(this).find('span').attr('class')
												.replace('d0', ''));
							}
						});

				$('td[name^="ability_capability_item_"]').each(
						function() {
							if ($.trim($(this).html()).length > 0) {
								list.push($(this).attr('id') + "_@_"
										+ $(this).find('text').html());
							}
						});

				var url = "/group/submit_capability_items.html", data = {
					groupId : $('#groupId').val(),
					descList : list.join("_&_")
				};
				postAjaxRequest(url, data, function(result) {
					alertMsg("更新能力模型成功！");
				});
			};
			delCapability = function(capabilityId) {
				confirmMsg("确定要删除选中记录吗？删除后无法恢复", function() {
					var url = "/group/delete_capability_items.html", data = {
							capabilityId : capabilityId,
							groupId:$('#groupId').val()
					};
					postAjaxRequest(url, data, function(result) {
					    alertMsg("更新能力模型成功！",350,200,go2Page("/group/capability.html","&groupId="+$('#groupId').val()));
					});
				})
			}
			init();

		});
