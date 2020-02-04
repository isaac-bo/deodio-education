package com.deodio.core.sfile.context;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import com.deodio.core.constants.Constants;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class SFileContext {

	public static Map<String, Object> configMap = new HashMap<String, Object>();

	private static Configuration getConfiguration(String templatePath) {
		return (Configuration)initContext(Boolean.TRUE,templatePath);
	}

	private static VelocityContext getVelocityContext(String templatePath) {
		return (VelocityContext)initContext(Boolean.FALSE,templatePath);
	}
	
	private static Object initContext(Boolean isFreeMarker,String templatePath){
		if (configMap.containsKey(templatePath)) {
			return configMap.get(templatePath);
		} else {
			if(isFreeMarker){
				Configuration config = new Configuration();
				configMap.put(templatePath, config);
				return config;
			}else{
				VelocityContext context = new VelocityContext();
				configMap.put(templatePath, context);
				return context;
			}
		}
	}
	
	public static Configuration initConfiguration(String loadTemplatePath) throws IOException{
		Configuration config=getConfiguration(loadTemplatePath);
		config.setDirectoryForTemplateLoading(new File(loadTemplatePath));
		config.setObjectWrapper(new DefaultObjectWrapper());
		config.setDefaultEncoding(Constants.CHARSET_UTF_8);
		return config;
	}
	
	public static VelocityContext initVelocityContext(String loadTemplatePath) throws IOException{
		return getVelocityContext(loadTemplatePath); 
	}
}
