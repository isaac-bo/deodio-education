package com.deodio.core.utils;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

abstract class Utils {
	
	 public static int random(final int max) {
	    double result = Math.random() * max;
	    result = Math.ceil(result);
	    return new Double(result).intValue();
	  }

	  public static String uuid() {
	    return StringUtils.remove(UUID.randomUUID().toString(), "-");
	  }
}
