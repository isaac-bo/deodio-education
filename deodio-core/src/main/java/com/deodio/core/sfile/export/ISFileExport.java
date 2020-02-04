package com.deodio.core.sfile.export;


import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public interface ISFileExport {
	
	public Boolean isDefaultPath = Boolean.FALSE;

	public void setFreeMarkerEngine(FreeMarkerConfigurer freeMarkerEngine);
	public void setVelocityEngine(VelocityEngine velocityEngine);
	public Boolean export(Boolean is2Template,String toFile, String tempFile,Object obj, Boolean isConfig) ;
}
