package com.deodio.components.xml;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.components.excel.entity.ExportColumnEntity;
import com.deodio.components.excel.entity.ExportEntity;
import com.thoughtworks.xstream.XStream;

/**
 * This class for exporting excel file using xml format
 * @ClassName: ExportXmlWork
 * @author isaac
 * @date 2014-8-2
 */
public class ExportXmlWork implements IXmlWork<ExportEntity> {
    private final static Logger LOGGER= LoggerFactory.getLogger(ExportXmlWork.class);

	private XStream xs ;
	private ExportEntity exEntity;
	
	/**
	 *  Creates a new ExportXmlWork object.
	 * 
	 *
	 */
	public ExportXmlWork(){
		this.init();
	}
	
	/**
	 * Init method
	 */
	@Override
	public void init() {
		exEntity = new ExportEntity();
		xs = new XStream();
		xs.processAnnotations(ExportEntity.class);
		xs.processAnnotations(ExportColumnEntity.class);
		
	}

	/**
	 * Convert xml file to objects
	 */
	@Override
	public ExportEntity xmlFile2Obj(String xmlFile){
		try {
			URL url =  ExportXmlWork.class.getClassLoader().getResource(xmlFile);
			String path = URLDecoder.decode(url.getPath(), "UTF-8");
			FileInputStream fis = new FileInputStream(path);
			exEntity = (ExportEntity)xs.fromXML(fis);
		} catch (Exception e) {
//			LOGGER.error(e.getMessage(),e);
		} 
		return exEntity;
	}

	/**
	 * Convert xml format string to objects
	 */
	@Override
	public ExportEntity xmlStr2Obj(String xmlStr) {
		exEntity = (ExportEntity)xs.fromXML(xmlStr);
		return exEntity;
	}

	/**
	 * Convert objects to xml format string
	 */
	@Override
	public String obj2XmlStr(ExportEntity entity) {
		return xs.toXML(entity);
	}

	@Override
	public ExportEntity xmlFile2Obj(HttpServletRequest request, String xmlStr) {

	    try {
	        FileInputStream fis = new FileInputStream(request.getSession().getServletContext().getRealPath(xmlStr));
	        exEntity = (ExportEntity)xs.fromXML(fis);
        } catch (Exception e) {
//            LOGGER.error(e.getMessage(),e);
        } 
        return exEntity;

	}

}
