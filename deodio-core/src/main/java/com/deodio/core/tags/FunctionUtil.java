package com.deodio.core.tags;

/**
 * el工具类
 * 
 * @author sj
 * 
 */
public class FunctionUtil {

	public static String htmlEncode(String htmlStr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < htmlStr.length(); ++i) {
			char c = htmlStr.charAt(i);

			switch (c) {
			case '\n':
				sb.append("</br>");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '\'':
				sb.append("&apos;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();

	}

}
