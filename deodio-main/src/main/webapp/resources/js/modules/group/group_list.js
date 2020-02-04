define(
		[ "jquery", "utils.cookie", "utils.list", "jquery.dot", "utils.menu",
				"pagination", "utils", "jquery.base", "bootstrap.datepicker",
				"jquery.scrolltofixed", "jquery.scroll.pagination",
				"bootstrap.select" ], function($, cookie, list, doT, menu,
				paging) {
			var ulliConfig = {
				0 : '创建一个新的小组',
				1 : '编辑小组信息和内容',
				2 : '删除此小组',
				3 : '复制此小组，快速生成一个相似小组',
			}

			var init = function() {
				//初始状态title显示
				$('#content').scrollPagination({
					'postUrl' : ctx + '/group/load_list.html',
					'postParams' : {
						pageNo : $.trim($("#hid_default_page").val()).length <= 0 ? 1 : $("#hid_default_page").val(),
						pageSize:6,
						keyword : $.trim($("#keyWords").val())
					},
					'scrollTarget' : $(window),
					'callBackLoad' : function(data) {
						if($.trim($("#hid_default_page").val()).length > 0 && Number($("#hid_default_page").val()) > 1){
							loadGroupDataList(data);
						}
						
					},
					'beforeLoad' : function() {
						this.postParams.pageNo = $("#hid_default_page").val();
						this.postParams.keyword = $.trim($("#keyWords").val());
					}

				});
				menu.onSelectMenu(2);
				list.onFixedItems();
				reloadPageData();
			};

			// 重新加载数据
			reloadPageData = function() {
				var url = "/group/load_list.html", data = {
					pageNo : 1,
					pageSize:6,
					keyword : $("#keyWords").val()
				};
				postAjaxRequest(url, data, function(result) {
					if (result.data.dataList.length!= 0) {
					   $.cookie("CGID", result.data.dataList[0].id);
					   cookie.setCookie('CGID',result.data.dataList[0].id,60);
					   initShow();
					}else{
					   $.cookie("CGID", ""); 
					   showMenu(1);
					   showRightButton(3);
					}
					loadGroupDataListInit(result.data);
				});
			}

			loadGroupDataList = function(data) {
				var template = doT.template($("#data_template").text());
				if ($("#content li").size() == 0) {
					$('#content').append(template({
						"data" : data
					}));
				} else {
					$('#content').append(template({
						"data" : data
					}));
				}
				if (data.dataList.length!=0) {
					var finalPageNo = Number($("#hid_default_page").val()) + 1;
					$("#hid_default_page").val(finalPageNo);
				}
			};
			loadGroupDataListInit = function(data) {
				var template = doT.template($("#data_template").text());
				if ($("#content li").size() == 0) {
					$('#content').append(template({
						"data" : data
					}));
				} else {
					$('#content').html("");
					$('#content').append(template({
						"data" : data
					}));
				}
				if (data.dataList.length==6) {
					var finalPageNo = Number($("#hid_default_page").val()) + 1;
					$("#hid_default_page").val(finalPageNo);
				}
			};
			onDeleteGroup = function() {
				var courseFlag=validateDeleteGroupCourse();
				var memberFlag=validateDeleteGroupMember();
                if(!courseFlag && !memberFlag){
                	alertMsg("该小组内存在成员和课程,不能删除");
                	return false;
                }else if(!courseFlag){
                	alertMsg("该小组内存在课程,不能删除");
                	return false;
                }else if(!memberFlag){
                	alertMsg("该小组内存在成员,不能删除");
                	return false;
                }
                var itemId=$(".item_select").attr("id");
            	cookie.setCookie('CGID',itemId,60);
				$.cookie("CGID", itemId); 
            	$('#_item_id').val(itemId);
				confirmMsg("确定要删除该小组吗?", function() {
					var url = "/group/delete.html", data = {
						id : $.trim($('#_item_id').val())
					};
					postAjaxRequest(url, data, function(result) {
						alertMsg("操作成功");
						go2Page('/group/list.html');
					});
				});

			};
            validateDeleteGroupCourse=function(){
            	var flag=true;
            	var url="/group/validateCourse.html",data={
            			groupId:$.trim($('#_item_id').val())
            	};
            	postAjaxRequest(url,data,function(result){
            		 if(result.status==1){
         		    	flag=result.data;
         		    }
            	},false);
            	return flag;
            };
            validateDeleteGroupMember=function(){
            	var flag=true;
            	var url="/group/validateMember.html",data={
            			groupId:$.trim($('#_item_id').val())
            	};
            	postAjaxRequest(url,data,function(result){
            		    if(result.status==1){
            		    	flag=result.data;
            		    }
            	},false);
            	return flag;
            };
            onEditGroup = function() {
            	var itemId=$(".item_select").attr("id");
            	cookie.setCookie('CGID',itemId,60);
				$.cookie("CGID", itemId); 
            	$('#_item_id').val(itemId);
				go2Page('/group/profile.html', 'groupId='
						+ $.trim($('#_item_id').val()));
			};
			onEditTeacherGroup = function() {
				var itemId=$(".item_select").attr("id");
            	cookie.setCookie('CGID',itemId,60);
				$.cookie("CGID", itemId); 
            	$('#_item_id').val(itemId);
				go2Page('/group/features.html', 'groupId='
						+ $.trim($('#_item_id').val()));
			};
			toGroupDetail = function(id) {
				showRightButton(1);
				$("#teacherEdit").hide();
				showMenu(3);
				$.cookie('cgrid', $("#roleViewerId").val()); 
				$.cookie("CGID", id);  
				go2Page('/group/intro.html', 'groupId=' + id);
			};
			onSelectGroup = function(id,groupRoleId) {
				menu.onSelectItem(id);
				//设置cookie的groupId值
				$.cookie('cgrid', groupRoleId); 
				cookie.setCookie('CGID',id,60);
				$.cookie("CGID", id);  
				if(groupRoleId==$("#roleLeaderId").val()){//右侧菜单显示,导航栏显示
					showRightButton(2);
					showMenu(1);
				}
				if(groupRoleId==$("#roleCreatorId").val()){
					showRightButton(1);
					$("#teacherEdit").show();
					showMenu(2);
				}
				if(groupRoleId==$("#roleViewerId").val()){
				    showRightButton(1);
				    $("#teacherEdit").hide();
				    showMenu(3);
				}
			};
			showRightButton=function(type){
				if(type==1){
				$("#createGroup").hide();
				$("#editGroup").hide();
				$("#deleteGroup").hide();
				$("#copyGroup").hide();
			    }
				if(type==2){
					$("#createGroup").show();
					$("#editGroup").show();
					$("#deleteGroup").show();
					$("#copyGroup").show();
				    }
				if(type==3){
					$("#createGroup").show();
					$("#editGroup").hide();
					$("#deleteGroup").hide();
					$("#copyGroup").hide();
				}
			}
			showMenu=function(type){
				if(type==1){
					$("#_head_menu_2").show();
					$("#_head_menu_5").show();
					$("#_head_menu_3").hide();
					$("#_head_menu_course").hide();
					$("#_head_menu_group").hide();
				}
				if(type==2){
					$("#_head_menu_2").show();
					$("#_head_menu_5").show();
					$("#_head_menu_3").show();
					$("#_head_menu_course").hide();
					$("#_head_menu_group").hide();
				}
				if(type==3){
					$("#_head_menu_2").hide();
					$("#_head_menu_5").hide();
					$("#_head_menu_3").hide();
					$("#_head_menu_course").show();
					$("#_head_menu_group").show();
				}
			}
			onCopyGroup = function() {
				var itemId=$(".item_select").attr("id");
            	cookie.setCookie('CGID',itemId,60);
				$.cookie("CGID", itemId); 
            	$('#_item_id').val(itemId);
				$("#groupCopyModal").modal("show");
				$('#groupCopyModal').on('hidden.bs.modal', function () {
					$('body').removeClass('modal-open').css('overflow-y','auto');
				});
			}
			submitNewGroup = function() {
				var url = "/group/groupNameExsit.html", data = {
					groupName : $("#newGroupName").val()
				};
				postAjaxRequest(url, data, function(result) {
					if (result.status == 1) {
						var url = "/group/copyGroup.html", data = {
							groupId : $.trim($('#_item_id').val()),
							groupName : $.trim($('#newGroupName').val())
						};
						postAjaxRequest(url, data, function(result) {
							go2Page("/group/list.html");
						});
					} else {
						//$("#groupCopyModal").modal("hidden");
						alertMsg("小组名称已存在!");
					}
				});

			}
			$("#ul_li_focus_events li").mouseover(function() {
				$(this).each(function() {
					$("#text_tips").html(ulliConfig[$(this).index()]);
				})
			})
			$("#ul_li_focus_events li").mouseout(function(){	
				$(this).each(function(){
					$("#text_tips").html("");	
				})		
			})
			initShow=function(){
				var userRoleId=$("#userRoleId").val();
				if(userRoleId==$("#roleLeaderId").val()||userRoleId==$("#roleAccount").val()){//右侧菜单显示,导航栏显示
					showMenu(1);
					showRightButton(2);
					$("#teacherEdit").hide();
				}
				if(userRoleId==$("#roleCreatorId").val()){
					showRightButton(1);
					$("#teacherEdit").show();
					showMenu(2);
				}
				if(userRoleId==$("#roleViewerId").val()){
					showRightButton(1);
					$("#teacherEdit").hide();
					showMenu(3);
				}
			}
			init();
		});
