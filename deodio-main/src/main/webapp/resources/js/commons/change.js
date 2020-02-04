define(["jquery","jquery.dot"], function($,doT) {
	//阿拉伯数字转换为汉字算法
	var ary0  = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"];
	var ary1 = ["", "十", "百", "千"];
	var ary2 = ["", "万", "亿", "兆"];
	var name ="";
	var init = function (name) {
		name = name;
	};
	 strrev = function () {
		console.log(name);
		var ary = []
		for (var i = name.length; i >= 0; i--) {
			ary.push(name[i])
		}
		return ary.join("");
	}; //倒转字符串。
			
	 pri_ary = function () {
		var $this = this;
		var ary = strrev();
		var zero = "";
		var newary = "";
		var i4 = -1;
		for (var i = 0; i < ary.length; i++)
		{
			if (i % 4 == 0) { //首先判断万级单位，每隔四个字符就让万级单位数组索引号递增
				i4++;
				newary = ary2[i4] + newary; //将万级单位存入该字符的读法中去，它肯定是放在当前字符读法的末尾，所以首先将它叠加入$r中，
				zero = ""; //在万级单位位置的“0”肯定是不用的读的，所以设置零的读法为空
			
			}
			//关于0的处理与判断。
			if (ary[i] == '0') //如果读出的字符是“0”，执行如下判断这个“0”是否读作“零”
			{
				switch (i % 4) {
					case 0:
					break;
					//如果位置索引能被4整除，表示它所处位置是万级单位位置，这个位置的0的读法在前面就已经设置好了，所以这里直接跳过
					case 1:
					case 2:
					case 3:
						if (ary[i - 1] != '0') {
							zero = "零"
						}
					; //如果不被4整除，那么都执行这段判断代码：如果它的下一位数字（针对当前字符串来说是上一个字符，因为之前执行了反转）也是0，那么跳过，否则读作“零”
					break;
				}
			
				newary = zero + newary;
				zero = '';
			}
			else { //如果不是“0”
				// 就将该当字符转换成数值型,并作为数组ary0的索引号,以得到与之对应的中文读法，其后再跟上它的的一级单位（空、十、百还是千）最后再加上前面已存入的读法内容。
				newary = ary0[parseInt(ary[i])] + ary1[i % 4] + newary;
			}
		
		}
		if (newary.indexOf("零") == 0) {
			newary = newary.substr(1)
		}//处理前面的0
		return newary;
	}
	
	changeNumToChinese = function(num,customizeFormatPrefix,customizeFormatSuffix){
		name = num.toString();
		if(!customizeFormatPrefix){
			customizeFormatPrefix = '第';
		}
		if(!customizeFormatSuffix){
			customizeFormatSuffix = '次';
		}
		return customizeFormatPrefix + changeTen(pri_ary()) + customizeFormatSuffix;
	}
	
	changeTen = function(num){
		if(num.indexOf('一十') == 0){
			return num.substring(1,num.length);
		}
		return num;
	}
	
	return {
		changeNumToChinese :changeNumToChinese
	}
	
});