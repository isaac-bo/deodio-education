package com.deodio.core.tags;

import com.deodio.core.utils.StringUtils;

public class DeodioElTag {
	
	 public static String randomFun(String name){  
	        return StringUtils.randomStr(name);  
	 }  
	 
	 public static String isCorrect(String data,Integer index){
		 String[] str = data.split("#");
		 return "1".equals(str[index])?"1":"0";
	 }
	 
	 public static String arrayOutIndex(String data,String chars,Integer index){
		 String[] str = data.split(chars);
		 return String.valueOf(str[index]);
	 }
}
