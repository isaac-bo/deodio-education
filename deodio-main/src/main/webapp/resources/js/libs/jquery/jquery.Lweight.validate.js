/* =========================================================
 * jquery-Lightweight-validation.js 
 * Original Idea: (Copyright 2013 Viken)
 * Updated by 大猫 
 * version 1.1.5 
 * =========================================================
 * http://vikenlove.github.io/jquery-Lweight-validate
 * http://www.oschina.net/p/jquery-lweight-validate 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================= */
;(function($) {     	
	$.fn.myValidate = function(options) {
		var globalOptions  = $.extend({}, defaults, options);
			var $this = this;
			$this.find('[btn-type=true]').unbind().click(function(){
					validateClick($this,globalOptions,$(this).attr("btn-val"));
			});
		
			if(globalOptions.formKey){
				$(document).keyup(function(event){
				  switch(event.keyCode) {
					case 13:
						validateClick($this,globalOptions);
						break; 
					}
				});
			};
			validateBlur($this,globalOptions);
	};


	var validateClick = function(obj,globalOptions,btnVal){
		if(!validateForm(obj,globalOptions)){
			if(globalOptions.formCall != undefined){
				if(btnVal!=undefined){
					globalOptions.formCall(btnVal);
				}else{
					globalOptions.formCall();
				}
			}	
		}	
	};
	
 
  var defaults = {
        validRules : [
            {name: 'required', validate: function(value) {return ($.trim(value) == ''||$.trim(value).length==0||$.trim(value)==null);}, defaultMsg: '请输入内容。'},
			{name: 'unRequired', validate: function(value) {return false;}, defaultMsg: '请输入内容。'},
            {name: 'number', validate: function(value) {return (!/^[0-9]\d*$/.test($.trim(value)));}, defaultMsg: '请输入数字。'},
            {name: 'scoreNumber', validate: function(value) {return checkScoreNumber($.trim(value));}, defaultMsg: '请输入0-100之间整数。'},
            {name: 'mail', validate: function(value) {return (!/^[a-zA-Z0-9]{1}([\._a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+){1,3}$/.test($.trim(value)));}, defaultMsg: '请输入邮箱地址。'},
            {name: 'char', validate: function(value) {return (!/^[a-z\_\-A-Z]*$/.test($.trim(value)));}, defaultMsg: '请输入英文字符。'},
            {name: 'chinese', validate: function(value) {return (!/^[\u4e00-\u9fff]+$/.test($.trim(value)));}, defaultMsg: '请输入汉字。'},
            //update by cheng.wang start
            //修改之前的 {name: 'mobile', validate: function(value) {return (!/^(13|15|18)[0-9]{9}$/.test($.trim(value)));}, defaultMsg: '请输入正确手机号码。'},
            //修改之后的 添加145、147、149、166、170、171、173、175、176、177、178、198、199号段
            {name: 'mobile', validate: function(value) {return (!/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test($.trim(value)));}, defaultMsg: '请输入正确手机号码。'},
            //update by cheng.wang end 
            {name: 'tell', validate: function(value) {return (!/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test($.trim(value)));}, defaultMsg: '请输入正确电话号码格式:区号-号码。'},
			//update by cheng.wang start
			//修改  密码长度必须在6~20之间  为   密码长度必须在6~16之间
			{name: 'passWord', validate: function(value) {return checkPwd($.trim(value));}, defaultMsg: '密码长度必须在6~16之间。'},
			//update by cheng.wang end 
			{name: 'confirmPwd', validate: function(value,objel) {return confirmPwd($.trim(value),objel);}, defaultMsg: '两次密码不一致'},
			{name: 'dateYmd', validate: function(value) {return checkDate($.trim(value));}, defaultMsg: '请输入YYYY--MM--DD格式'},
			{name: 'idCard', validate: function(value) {return checkIdCard($.trim(value));}, defaultMsg: '请输入正确的身份证号码'},
			//add by cheng.wang start
			{name: 'companyOrganizationCode', validate: function(value) {return !isValidOrgCode(value);}, defaultMsg: '请输入正确的企业组织机构代码'},
			{name: 'companyBusinessLicenseCode', validate: function(value) {return !isValidBusCode(value);}, defaultMsg: '请输入正确的企业营业执照注册号'},
			//add by cheng.wang end
			{name: 'dateCompare', validate: function(value) {return dateCompare();}, defaultMsg: '起始日期不能大于结束日期'},
			{name: 'url', validate: function(value) {return (!/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test($.trim(value)))}, defaultMsg: '请输正确的网址。'},
			{name: 'telephone', validate: function(value) {return (!/^(\d{3,4}\-)?\d{7,8}(\-\d{2,6})?$/.test($.trim(value)));}, defaultMsg: '请输入正确手机号码。'},
			{name: 'intValidate', validate: function(value) {return (!/^\+?[1-9]\d*$/.test($.trim(value)));}, defaultMsg: '请输入大于0的整数。'},
			
			
        ],
		city : [
			{11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",
			23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",
			41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",
			52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
			65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}		
		],
		errorCustom : {
				customFlag:false,
				regionText:false
		}
    };
	


			
var validateBlur = function(obj,globalOptions){
	$(obj).find("input,textarea,select").each(function(){
	var el = $(this);
	var valid = (el.attr('check-type')==undefined)?null:el.attr('check-type').split(' ');
	if(el.attr("srcvalue")==undefined){
		el.attr("srcvalue",el.val());
	};
	//适配bootstrap-select xuxiangdong 2017.01.23
	var  tempObj = el;
	if(el.is(".bootstrap-select pull-left")){//小组-邀请加入下拉框后加pull-left样式
		tempObj = el.siblings("button");
	}
		if(valid!==null && valid.length>0){
			tempObj.focus(function(){
//				debugger;
				var curTextDiv=el.parent(), curErrorEl = curTextDiv.children('.help-inline');
				el.removeAttr("lable-error");
				el.removeAttr("style");
				if(globalOptions.errorCustom.customFlag){
					
						if(globalOptions.errorCustom.regionText){
							var checkType = el.attr("check-type");
							if(checkType=='passWord' || checkType=="confirmPwd"){
								el.attr("type","password");
							};
							if(el.val()!=''){
								// 移除对老版本浏览器 不支持  placeholder，不在支持就浏览器
								// el.val(el.attr("srcvalue")).css("color","");
								el.val(el.attr("srcvalue"));
			                  }else{
			                	  el.val("");
			                  }
						}else{
							
							//适配bootstrap-select xuxiangdong 2017.01.23
							var  temp = el;
							if(el.is(".bootstrap-select")){
								temp = el.parent();
							}
							
							temp.siblings("."+globalOptions.errorStyle.errorRegion).text("").removeClass(globalOptions.errorStyle.errorClass);
						}					
				}else{
					if(curErrorEl.hasClass('help-inline')){
						curErrorEl.remove();
					}else if(curTextDiv.parent().children('.help-inline').hasClass('help-inline')){
						curTextDiv.parent().children('.help-inline').remove();
					}
				}				
			});
			tempObj.blur(function() { 
//				if(el.attr("check-type")=='dateYmd'){
//					setTimeout(function(){validateField(el, valid,globalOptions);},500); 
//				}else{
//					validateField(el, valid,globalOptions);
//				}
				//modify by xu.xiangdong 2016.12.29
				if(el.is("[check-type*='dateCompare']")){
					setTimeout(function(){validateField(el, valid,globalOptions);},8000); 
				}else if(el.is("[check-type*='dateYmd']")){
					setTimeout(function(){validateField(el, valid,globalOptions);},5000); 
				}else{
					validateField(el, valid,globalOptions);
				}
            });
			
			
		}			
	});
		
};				
var validateForm=function(obj,globalOptions){
	//TODO shuangjia hou 能提交
	 var validationError = false;

	$(obj).find("input:visible,textarea:visible,select:visible").each(function(){
		var el = $(this);
		 if(obj.length==1){
			 if(el.attr("checktype")=='inputAttach'){
				 if(($("#"+el.attr('attachcheckid')).val()).length==0){			 
					 $("#"+el.attr('attachshow')).attr("style","border:1px solid red");
					 error=true;
				 }
			 }
		 }
		
		if(!el.prop("disabled")){
			var valid = (el.attr('check-type')==undefined)?null:el.attr('check-type').split(' ');
			
//			if(el.attr("lable-error")){
//				validationError=true;
//				return;
//			};
		
			if(valid!==null && valid.length>0){
				if(!validateField(el,valid,globalOptions)){
					validationError=true;
				}
			};
			if(globalOptions.isAlert){
				if(validationError){
					return false;
				}				
			};
		}		
	});
	return validationError;
};
var validateField = function(field,valid,globalOptions){
	 var el = $(field);
	 var error = false,isNonFlag=false, errorMsg = '',pwdStatus=0,elLength=el.val().length;		
	 var isNon = (el.attr('non-required')==undefined||el.attr('non-required')=='false')?false:true;
	 var rules = globalOptions.validRules;
		for(var i=0,j=rules.length;i<j;i++){
			var rule = rules[i];
				for(var index = 0;index<valid.length;index++){//实现多个check-type 同时 限定一个Input
					var ruleName = valid[index];
					
					if(ruleName == 'dateCompare' && el.attr("date-compare") == 'from'){//add by xu.xiangdong 201.12.29
						continue;
					}

					if(ruleName==rule.name){
						var ruleVal = rule.validate(el.val(),el);
						if(isNon){
							if($.trim(el.val()).length > 0){
								if(ruleVal==true||ruleVal==-1){
									error=true;
									errorMsg=(el.attr('required-message')==undefined)?rule.defaultMsg:el.attr('required-message');
									break;
								}else{
									isNonFlag=true;
								}					
							}
						}else if(ruleVal==true||ruleVal==-1){
							error=true;
							errorMsg=(el.attr('required-message')==undefined)?rule.defaultMsg:el.attr('required-message');
							break;
						}	
					}
				}
		}
	if(!error){
	
		if(el.val().length >0){
			var minMax = (el.attr('min-max')==undefined)?null:el.attr('min-max').split(' ');	
			var _callBack = (el.attr('data-callback')==undefined)?null:el.attr('data-callback').split(' ');
			
			//add maxNum 
			var minMaxNum =  (el.attr('min-max-num')==undefined)?null:el.attr('min-max-num').split(' ');
			
			if(minMax!==null && minMax.length>0){
				var min = el.attr('min-max').split('-')[0],max=el.attr('min-max').split('-')[1];
				if(elLength < Number(min)){
					error=true;
					errorMsg=(el.attr('min-message')==undefined)?"文本长度不能小于"+min+"个字符":el.attr('min-message');
				}else if(max != undefined){
					if(elLength > Number(max)){
						error=true;
						errorMsg=(el.attr('max-message')==undefined)?"文本长度不能大于"+max+"个字符":el.attr('max-message');
					}
				}else{
					isNonFlag=true;
				}
			};
			if(minMaxNum!==null && minMaxNum.length>0){
				var minNum = el.attr('min-max-num').split('-')[0],maxNum=el.attr('min-max-num').split('-')[1];
				if(el.val() < Number(minNum)){
					error=true;
					errorMsg=(el.attr('min-num-message')==undefined)?"文本数值不能小于"+minNum:el.attr('min-num-message');
				};
				if(maxNum != undefined){
					if(el.val() > Number(maxNum)){
						error=true;
						errorMsg=(el.attr('max-num-message')==undefined)?"文本数值不能大于"+maxNum:el.attr('max-num-message');
					}
				}else{
					isNonFlag=true;
				}
			};
			
			
			
			if(!error){
				if(_callBack!==null && _callBack.length>0){
					var _ajaxCallBack = el.attr('data-callback');
					error = eval(_ajaxCallBack);
					if(error){
						errorMsg=(el.attr('call-message')==undefined)?"校验无法通过，请重新输入":el.attr('call-message');
					}
				}	
			}		
		}			
	}
	 
	var curTextDiv=el.parent(), curErrorEl = curTextDiv.children('.help-inline'),uniformDiv=curTextDiv.attr("id");
	



	if(error){
		if(globalOptions.isAlert){
			if(globalOptions.alterCall != undefined){
				globalOptions.alterCall(errorMsg);	
			}else{
				alert(errorMsg);				
			}	
		}else{
			if(uniformDiv!=undefined && uniformDiv.indexOf('uniform-')>-1){
				if(curTextDiv.parent().children('.help-inline').hasClass('help-inline')){
					curTextDiv.parent().data('help-inline',errorMsg);	
				}else{
					if(globalOptions.errorCustom.customFlag){
						el.siblings("."+globalOptions.errorStyle.errorRegion).append(errorMsg);
					}else{
						curTextDiv.parent().append('<span class="help-inline error">'+errorMsg+'</span>');
					}
					
				}						
			}else{

					if(globalOptions.errorCustom.customFlag){
						if(globalOptions.errorCustom.regionText){
								if(el.attr("type")=='password'){
									el.attr("type","text");
								};
								var textValue=el.val();
								//  更换错误新以 placeholder 代替原始value 赋值形势，避免刷新value缓存 和默认值 存储
								//el.val(errorMsg).css("color","#cccccc");
								el.val("").attr("placeholder",errorMsg);
						}else{
							
							//适配bootstrap-select xuxiangdong 2017.01.23
							var  temp = el;
							if(el.is(".bootstrap-select")){
								temp = el.parent();
							}
							
							temp.siblings("."+globalOptions.errorStyle.errorRegion).text(errorMsg);					
						}
						el.attr("lable-error",true);

					}else{
						if(curErrorEl.hasClass('help-inline')){
							curTextDiv.data('help-inline',errorMsg);
						}else{
							curTextDiv.append('<span class="help-inline error">'+errorMsg+'</span>');
						}
					}			
			}		
			//el.removeClass('right').addClass('error');
			removeElStyleClass(el,globalOptions,1);

		}
	}else if(ruleVal > 0){
	
		var pwdStrong = passWordStatus(ruleVal);
		var classpic = classStatus(ruleVal);
		

	if(globalOptions.errorCustom.customFlag){
		
		if(globalOptions.errorCustom.regionText){
				var textValue=el.val();
				if(error){
					el.val(errorMsg).css("color","#cccccc");
				}else{
					el.attr("srcvalue",textValue);
				}
				
			}else{
					el.siblings("."+globalOptions.errorStyle.errorRegion).text(pwdStrong);					
			}
	}else{
		if(curErrorEl.hasClass('help-inline')){
				curTextDiv.data('help-inline',pwdStrong);
			}else{		
				curTextDiv.append('<span class="help-inline '+classpic+'">'+pwdStrong+'</span>');
		};

	}
		//el.removeClass('error').removeClass('right');
		removeElStyleClass(el,globalOptions,2);
		
	}else{
		if(!globalOptions.isAlert){
			curErrorEl.remove();
			//isNonFlag==true?el.removeClass('error').addClass('right'):el.removeClass('error').removeClass('right');	
			removeElStyleClass(el,globalOptions,2);
			
				if(globalOptions.errorCustom.regionText){
					el.attr("srcvalue",el.val());
				}
			
						
		}
	}
	
	return !error;
};

var removeElStyleClass = function(objel,globalOptions,reomveType){
		if(globalOptions.errorStyle!=undefined){
				var styleOption =globalOptions.errorStyle,$objelProperty;
					if(globalOptions.errorCustom.regionText){
						$objelProperty = objel.parents("."+styleOption.errorRegion);															
					}else{
						//适配bootstrap-select
						var  temp = objel;
						if(objel.is(".bootstrap-select")){
							temp = objel.parent();
						}
						$objelProperty = temp.siblings("."+styleOption.errorRegion);					
					};
			
					if(reomveType==1){
						$objelProperty.removeClass(styleOption.rightClass).addClass(styleOption.errorClass);
					}else{
						$objelProperty.removeClass(styleOption.errorClass).addClass(styleOption.rightClass);
					};	
					
					
			}else{					
					if(reomveType==1){
						objel.removeClass('right').addClass('error');
					}else{
						objel.removeClass('error').addClass('right');
					};
			};
};


var trimReplaceHtml = function(value){
	return $.trim(value.replace( /\r\n/g,""));
}
			
var checkIdCard = function(value){ 
	var iSum=0,birthday;
	if(!/^\d{17}(\d|x)$/i.test(value)){
		return true; 
	} 
	value=value.replace(/x$/i,"a"); 
	
	//在本框架内报错，更改js 语句
	//	if($(this).defaults.city[0][parseInt(value.substr(0,2))]==null){
	//		return true; 
	//	}
	//modify by xu.xiangdong  2016.12.27
	if(defaults.city[0][parseInt(value.substr(0,2))]==null){
		return true; 
	}
	
	birthday=value.substr(6,4)+"-"+Number(value.substr(10,2))+"-"+Number(value.substr(12,2)); 
	var d=new Date(birthday.replace(/-/g,"/")) ;
	if(birthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
		return true; 
	}
	for(var i = 17;i >= 0;i --){
		iSum += (Math.pow(2,i) % 11) * parseInt(value.charAt(17 - i),11) ;
	} 
	if(iSum%11!=1) {
		return true;
	} 
	return false;
}; 
	
	
var dateCompare = function(){
//	var $this = $("input[check-type='dateCompare']"),flag=false;
	var $this = $("input[check-type*='dateCompare']"),flag=false;//modify by xu.xiangdong 2016.12.29
	/*modify by cheng.wang 2018.6.27*/
	var startTime=$this.eq(0).val();
	var endTime=$this.eq(1).val();
	if(startTime.length >0 && endTime.length >0){
		if(startTime!=endTime){		
			var startDate = Number(startTime.replace(/-/g,'')),endDate=Number(endTime.replace(/-/g,''));	
			if(isNaN(startDate)||isNaN(endDate)){
				flag=false;
			}else{
				flag = startDate < endDate?false:true;
			}
		}
	}
	return flag;
};	
var checkDate = function(value){
	var r = value.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
		if(r==null)return true; 
	var d= new Date(r[1], r[3]-1, r[4]); 
		return !(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
};
var checkScoreNumber = function(value){
	var flag=false;
	if(!/^[0-9]\d*$/.test($.trim(value))){
		flag=true;
	}else{
		if(value<0||value>100){
			flag=true;
		}
	}
	return flag;
};
	
var confirmPwd = function(value,objel) {
    var inputObj = $(objel).parents("form").find("input[type='password']");
    var pwd1="",pwd2="";
    if(inputObj.size()==3){
        pwd1 = $.trim(inputObj.eq(1).val());
        pwd2 = $.trim(inputObj.eq(2).val());
    }else{
        pwd1 = $.trim(inputObj.eq(0).val());
        pwd2 = $.trim(inputObj.eq(1).val());
    }
    return (pwd2.length >= 0?(pwd1 == pwd2?false:true):true);    
};


var checkPwd = function(value){
	//update by cheng.wang start
	//修改length<=20 为 length<=16
	if(value.length >= 6 && value.length<=16)
	////update by cheng.wang end 
	{		
		if(/[a-zA-Z]+/.test(value) && /[0-9]+/.test(value) && /\W+\D+/.test(value)) {
				return 1;
		}else if(/[a-zA-Z]+/.test(value) || /[0-9]+/.test(value) || /\W+\D+/.test(value)) {
			if(/[a-zA-Z]+/.test(value) && /[0-9]+/.test(value)) {
				return 2;
			}else if(/\[a-zA-Z]+/.test(value) && /\W+\D+/.test(value)) {
				return 2;
			}else if(/[0-9]+/.test(value) && /\W+\D+/.test(value)) {
				return 2;
			}else{
				return 3;
			}
		}	
	}else{
		return true;
	}
};
//add by cheng.wang start 
function isValidOrgCode(value){
	var ret=false;
	if(value!=""){
		var valueIndexOf=value.indexOf('-');	
		if(value.length==10){				
			if(valueIndexOf==-1||valueIndexOf!=value.length-2){
				ret=true;
			}
		}else if(value.length==9){
			if(valueIndexOf!=-1){
				ret=true;
			}else{
				var value1=value.substring(0,value.length-1);
				var insertValue='-';
				var value2=value.substring(value.length-1,value.length);
				value=value1.concat(insertValue).concat(value2);
			}		
		}else{
			ret=true;
		}	
//		console.log('value---after',value);	
		   var values=value.split("-");
		    var ws = [3, 7, 9, 10, 5, 8, 4, 2];  
		    var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';  
		    var reg = /^([0-9A-Z]){8}$/;   
		    if (!reg.test(values[0])) {  
		    	ret= true;
		    }  
		    var sum = 0;  
		    for (var i = 0; i < 8; i++) {  
		        sum += str.indexOf(values[0].charAt(i)) * ws[i];  
		    }  
		    var C9 = 11 - (sum % 11);
		    var YC9=values[1]+'';
		    if (C9 == 11) {  
		    	C9 = '0';  
		    } else if (C9 == 10) {  
		    	C9 = 'X'  ;
		    } else {  
		    	C9 = C9+'';  
		    } 
		    if(YC9!=C9){
		    	ret=false;
		    }else{
		    	ret=true;
		    }
	}
	return ret;
}


function isValidBusCode(busCode){
	var ret=false;
	if(busCode.length===15){//15位
		var sum=0;
		var s=[];
		var p=[];
		var a=[];
		var m=10;
		p[0]=m;
		for(var i=0;i<busCode.length;i++){
			a[i]=parseInt(busCode.substring(i,i+1),m);
			s[i]=(p[i]%(m+1))+a[i];
			if(0==s[i]%m){
				p[i+1]=10*2;
			}else{
				p[i+1]=(s[i]%m)*2;
			}
		}
		if(1==(s[14]%m)){//营业执照编号正确!
			ret=true;
		}else{//营业执照编号错误!
			ret=false;
		}
	}else{//营业执照格式不对，必须是15位数的！
		ret=false;
	}
	return ret;
}
//add by cheng.wang end
var classStatus = function(i){
	var status ='';
	switch(i){
		case 1:
			status="passWord3";
			break;
		case 2:
			status="passWord2";
			break;
		case 3:
			status="passWord1";
			break;	
	}
	return status;
};

var passWordStatus = function(i){
	var status ='';
	switch(i){
		case 1:
			status="强";
			break;
		case 2:
			status="中";
			break;
		case 3:
			status="弱";
			break;	
	}
	return status;
};
     
})(jQuery);   
