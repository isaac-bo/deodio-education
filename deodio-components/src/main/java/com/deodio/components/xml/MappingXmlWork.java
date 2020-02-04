
/**
 * @Title: MappingXMLWork.java
 * @author isaac
 * @date 2014-8-15
 * @version V1.0
*/
package com.deodio.components.xml;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.components.excel.entity.MappingColumnEntity;
import com.deodio.components.excel.entity.MappingEntity;
import com.thoughtworks.xstream.XStream;


/**
 * @ClassName: MappingXMLWork
 * @author isaac
 * @date 2014-8-15
 */

public class MappingXmlWork implements IXmlWork<MappingEntity>{
    private final static Logger LOGGER= LoggerFactory.getLogger(MappingXmlWork.class);
	
	private XStream xs ;
	private MappingEntity mappingEntity;
	
	/**
	 * 
	 * 
	 *
	 */
	public MappingXmlWork(){
		this.init();
	}

	
	/** 
	 * 
	 * 
	*/
	@Override
	public void init() {
		mappingEntity = new MappingEntity();
		xs = new XStream();
		xs.processAnnotations(MappingEntity.class);
		xs.processAnnotations(MappingColumnEntity.class);
	}

	
	/**
	 * 
	 * 
	 * @param xmlFile
	 * @return
	*/
		
	@Override
	public MappingEntity xmlFile2Obj(String xmlFile) {
		try {
			URL url =  MappingXmlWork.class.getClassLoader().getResource(xmlFile);
			String path = URLDecoder.decode(url.getPath(), "UTF-8");
			FileInputStream fis = new FileInputStream(path);
			mappingEntity = (MappingEntity)xs.fromXML(fis);
		} catch (Exception e) {
//			LOGGER.error(e.getMessage(),e);
		} 
		return mappingEntity;
	}
	
	@Override
	public MappingEntity xmlFile2Obj(HttpServletRequest request,String xmlFile) {
		try {
			FileInputStream fis = new FileInputStream(request.getSession().getServletContext().getRealPath(xmlFile));
			mappingEntity = (MappingEntity)xs.fromXML(fis);
		} catch (Exception e) {
//			LOGGER.error(e.getMessage(),e);
		} 
		return mappingEntity;
	}
	
	/**
	 * 
	 * 
	 * @param xmlStr
	 * @return
	*/
		
	@Override
	public MappingEntity xmlStr2Obj(String xmlStr) {
		mappingEntity = (MappingEntity)xs.fromXML(xmlStr);
		return mappingEntity;
	}

	
	/**
	 * 
	 * 
	 * @param t
	 * @return
	*/
		
	@Override
	public String obj2XmlStr(MappingEntity entity) {
		return xs.toXML(entity);
	}

}
