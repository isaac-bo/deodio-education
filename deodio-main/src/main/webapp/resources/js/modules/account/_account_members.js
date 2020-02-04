define(["jquery","utils.cookie","utils.list","jquery.dot","pagination","echarts","utils","jquery.scrolltofixed","jquery.base","jquery.validate"], function($,cookie,list,doT,paging,echarts) {
	
	_init=function(){
		loadAccountMemberTableList(1);	
	};
	
	
	
	onToggleChart = function(id){
		$('#' + id).slideToggle();
	};
	
	loadItemListByCreator = function(){
		
		var url="/account/members/load_item_list.html",data={
			userId:$('#account_member_id').val()	
		};
		postAjaxRequest(url, data, function(result){
			$('#self_items_list').html('');
			if(result.data.itemList.length > 0){
				var template = doT.template($("#self_items_data_template").text());
				$('#self_items_list').html(template({data:result.data.itemList}));
			}else{
				$('#self_items_list').html('<div class="null_table" style="border:0px;">'+
						 'NULL'+
						 '<span class="null_t">暂时没有相关数据</span>'+
						 '</div>');
			}
		});
	};
	
	loadGoupListByCreator = function(){
		var url="/account/members/load_group_list.html",data={
			userId:$('#account_member_id').val()	
		};
		postAjaxRequest(url, data, function(result){
			$('#self_group_list').html('');
			if(result.data.groupList.length > 0){
				var template = doT.template($("#self_group_data_template").text());
				$('#self_group_list').html(template({data:result.data.groupList}));
			}else{
				$('#self_group_list').html('<div class="null_table" style="border:0px;">'+
								 'NULL'+
								 '<span class="null_t">暂时没有相关数据</span>'+
								 '</div>');
			}
			

		});
		
	};
	
	loadAccountMemberTableList=function(pageNo){
		var url="/account/load_members.html",data={
				pageNo:pageNo,
				keywords:$.trim($('#keywords_account_member').val())
				
		};
		postAjaxRequest(url, data, function(result){
			paging.pageTemplateDiv(result,"members_table_panel","members_table_data_template","members_table_page_panel","loadAccountMemberTableList");
		});
	};
	
	setItemPieChart = function(data){
			
		 var itemName=['课程'];
		 var itemData = [];
		 var itemCourse = {name:'课程',value:0};
		 var itemCoursePackage = {name:'课程包',value:0};
		 
		 for(var i = 0; i < data.length; i++){
			  itemName.push(data[i].name);
			  if(data[i].item_type == 1 || data[i].item_type == 2 || data[i].item_type == 3){
				  itemCourse.value += data[i].value;
			  }else{
				  itemCoursePackage.value += data[i].value;
			  }
			  
		  }
		 itemData.push(itemCourse);
		 itemData.push(itemCoursePackage);
		
		  var myChart = echarts.init(document.getElementById("items_chart"));
		  var option = {
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'horizontal',
				        x: 'left',
				        data:itemName
				    },
				    series: [
				        {
				            name:'内容',
				            type:'pie',
				            selectedMode: 'single',
				            radius: [0, '30%'],

				            label: {
				                normal: {
				                    position: 'inner'
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:itemData
				        },
				        {
				            name:'内容',
				            type:'pie',
				            radius: ['40%', '55%'],

				            data:data
				        }
				    ]
				};
		  myChart.setOption(option);
	};
	
	
	setGroupPieChart = function(data){
		
		 var currentCreateId = $('#account_member_id').val();
		 var isIncludeOwner = false;
		
		 var creators = ['其他'];
		 var creatorsData = [];
		 var tempCreatorsData = {name:'其他',value:0};
		 
		 for(var i = 0; i < data.length; i++){
			  if(data[i].create_id == $('#account_member_id').val()){
				  isIncludeOwner = true;
			  }
		 }
		
		 for(var i = 0; i < data.length; i++){
			  creators.push(data[i].name);
			  if(data[i].create_id == $('#account_member_id').val()){
				  creatorsData.push(data[i]);
			  }else{
				  tempCreatorsData.value += 1;
			  }
			  
		  }
		  creatorsData.push(tempCreatorsData);
		  
		  
		  var myChart = echarts.init(document.getElementById("group_chart"));
		  var option = {
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'horizontal',
				        x: 'left',
				        data:creators
				    },
				    series: [
				        {
				            name:'小组创建者',
				            type:'pie',
				            selectedMode: 'single',
				            radius: [0, '30%'],

				            label: {
				                normal: {
				                    position: 'inner'
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:creatorsData
				        },
				        {
				            name:'小组创建者',
				            type:'pie',
				            radius: ['40%', '55%'],

				            data:data
				        }
				    ]
				};
		  myChart.setOption(option);
	};
	
	searchAccountMemberList=function(){
		loadAccountMemberTableList(1);
	};
	
	onMemberList = function(){
		$('.pre_left').show();
		$('#member_detail').slideUp();
		$('#member_list').slideDown();

	};
	
	onMemberDetail = function(id){
		$('#account_member_id').val(id);
		var url="/account/load_member_detail.html",data={
				userId:id
		};
		postAjaxRequest(url, data, function(result){
			var template = doT.template($("#member_detail_table_data_template").text());
			$('#head_detail').html(template({data:result.data}));
			
			$('#member_list').slideUp();
			$('#member_detail').slideDown();
			
			$('.pre_left').hide();
			
			loadGoupListByCreator();
		});
	};

	
	_init();
	

	
}); 