
/**
 * @Title: FilePathUtils.java
 * @Package com.deodio.elearning.utils
 * @author isaac
 * @date 2015-1-12
 * @version V1.0
*/
	
package com.deodio.elearning.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.deodio.core.utils.AppUtils;


/**
 * @ClassName: FilePathUtils
 * @author isaac
 * @date 2015-1-12
 */

public class FilePathUtils extends AppUtils{

	
	private static final DateFormat DF_YEAR = new SimpleDateFormat("yyyy");
	private static final DateFormat DF_MONTH = new SimpleDateFormat("MM");
	private static final DateFormat DF_DAY = new SimpleDateFormat("dd");
	private static final DateFormat DF_HOUR = new SimpleDateFormat("HH");
	private static final DateFormat DF_MINUTE = new SimpleDateFormat("mm");

	public static String convertPath(final String path) {
		String str = StringUtils.replace(path, "\\", File.separator);
		str = StringUtils.replace(str, "/", File.separator);

		boolean isUNCPath = false;
		if (str.startsWith("\\\\"))
			isUNCPath = true;

		str = StringUtils.replace(str, File.separator + File.separator,
				File.separator);

		if (isUNCPath)
			str = "\\" + str;

		return str;
	}

	public static String convertURI(final String path) {
		boolean isUNCPath = false;
		String str = path;
		if (str.startsWith("\\\\")){
			str = path.substring(2);
			isUNCPath = true;
		}
		str = StringUtils.replace(str, "\\", "/");
		str = StringUtils.replace(str, "//", "/");

		if(isUNCPath){
			str = "\\\\" + str;
		}
		try {
			return new String(str.getBytes(), "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			return str;
		}
	}

	public static String getProfileVirtualPath(final String localPath) {

		final String[] str = StringUtils.split(convertPath(localPath),
				File.separator);

		if (str.length < 2)
			return "";
		if (str.length > 7 && StringUtils.isNumeric(str[str.length - 7]))
			return MessageFormat.format("/{0}/{1}/{2}/{3}/{4}/{5}/{6}",
					str[str.length - 7], str[str.length - 6],
					str[str.length - 5], str[str.length - 4],
					str[str.length - 3], str[str.length - 2],
					str[str.length - 1]);
		else if (str.length > 6 && StringUtils.isNumeric(str[str.length - 6]))
			return MessageFormat.format("/{0}/{1}/{2}/{3}/{4}/{5}",
					str[str.length - 6], str[str.length - 5],
					str[str.length - 4], str[str.length - 3],
					str[str.length - 2], str[str.length - 1]);
		else
			return MessageFormat.format("/{0}/{1}", str[str.length - 2],
					str[str.length - 1]);
	}

	public static String getVirtualPath(final String localPath) {

		final String[] str = StringUtils.split(convertPath(localPath),
				File.separator);

		if (str.length < 2)
			return "";
		if (str.length > 7 && StringUtils.isNumeric(str[str.length - 7]))
			return MessageFormat.format("/{0}/{1}/{2}/{3}/{4}/{5}/{6}",
					str[str.length - 7], str[str.length - 6],
					str[str.length - 5], str[str.length - 4],
					str[str.length - 3], str[str.length - 2],
					str[str.length - 1]);
		else if (str.length > 6 && StringUtils.isNumeric(str[str.length - 6]))
			return MessageFormat.format("/{0}/{1}/{2}/{3}/{4}/{5}",
					str[str.length - 6], str[str.length - 5],
					str[str.length - 4], str[str.length - 3],
					str[str.length - 2], str[str.length - 1]);
		else if (StringUtils.isNumeric(str[str.length - 2]))
			return MessageFormat.format("/{0}/{1}", str[str.length - 2],
					str[str.length - 1]);
		else
			return MessageFormat.format("/{0}/{1}/{2}", str[str.length - 3],
					str[str.length - 2], str[str.length - 1]);
	}

	public static String path() {
		return MessageFormat.format("/{0}/{1}/{2}/{3}/{4}/", DF_YEAR
				.format(new Date()), DF_MONTH.format(new Date()), DF_DAY
				.format(new Date()), DF_HOUR.format(new Date()), DF_MINUTE
				.format(new Date()));
	}
}
