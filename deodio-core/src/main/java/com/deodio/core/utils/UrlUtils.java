package com.deodio.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {
	
	/**
	 * 解析queryString 字符串  指定的参数数据
	 * @param queryStr  查询字符串  xxxxxxx?param1=fdffdfdfd&param2=fdfdfdfdf
	 * @param paramName  参数名称
	 * @return  返回参数值
	 */
	public static String parseQueryString  (String queryStr,String paramName ){
		String result = StringUtils.EMPTY_STRING;
		String regex = "\\?*" + paramName + "=([^&]+)&?";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher =  pattern.matcher(queryStr);
		while(matcher.find()){
			result = matcher.group(1);
		}
		return result;
	}
	
}
