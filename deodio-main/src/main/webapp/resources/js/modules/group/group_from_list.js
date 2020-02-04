define([ "jquery", "utils.cookie", "jquery.dot", "pagination", "echarts",
		"utils", "jquery.scrolltofixed", "jquery.base", "jquery.validate" ],
		function($, cookie, doT, paging, echarts) {

			_init = function() {
				//loadGroupFormTableList(1);
			};


			/*loadGroupFormTableList = function(pageNo) {
				var url = "/group/loadFromList.html", data = {
					pageNo : pageNo,
					groupId : $.trim($('#groupId').val())

				};
				postAjaxRequest(url, data, function(result) {
					paging.pageTemplateDiv(result, "form_table_panel",
							"group_detail_table_data_template",
							"form_table_page_panel",
							"loadGroupFormTableList");
				});
			};*/
			toUserForm=function(userId){
				go2Page("/group/toUserFormPage.html","userId="+userId+"&groupId="+$.trim($('#groupId').val()));
			}
			toManageForm=function(userId){
				go2Page("/group/form/manage.html","groupId="+$.trim($('#groupId').val()));
			}
			_init();

		});