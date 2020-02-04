package com.deodio.components.xml;

import javax.servlet.http.HttpServletRequest;

/**
 * Abstract class for importing/exporting excel file using xml format
 * @ClassName: IXmlWork
 * @author isaac
 * @date 2014-8-2
 * @param <T>
 */
public interface IXmlWork<T> {
	
	/**
	 * Init method
	 * @Title: init
	 * @param 
	 * @throws
	 */
	abstract void init();
	
	/**
	 * Convert xml file to objects
	 * @Title: xmlFile2Obj
	 * @param  xmlFile
	 * @return T
	 * @throws
	 */
	abstract  T xmlFile2Obj(String xmlFile);
	abstract  T xmlFile2Obj(HttpServletRequest request,String xmlStr);
	/**
	 * Convert xml format string to objects
	 * @Title: xmlStr2Obj
	 * @param  xmlStr
	 * @return T
	 * @throws
	 */
	abstract  T xmlStr2Obj(String xmlStr);
	
	/**
	 * Convert objects to xml format string.
	 * @Title: obj2XmlStr
	 * @param  t
	 * @return String
	 * @throws
	 */
	abstract  String obj2XmlStr(T t);
}
