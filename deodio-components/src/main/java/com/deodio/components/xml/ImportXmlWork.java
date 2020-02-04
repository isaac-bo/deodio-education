package com.deodio.components.xml;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.components.excel.entity.ColorCellEntity;
import com.deodio.components.excel.entity.ColumnTitleEntity;
import com.deodio.components.excel.entity.DataCellEntity;
import com.deodio.components.excel.entity.ExcelEntity;
import com.deodio.components.excel.entity.MacroEntity;
import com.deodio.components.excel.entity.MergeCellEntity;
import com.thoughtworks.xstream.XStream;

/**
 * This class for importing excel file using xml format
 * @ClassName: ExportXmlWork
 * @author isaac
 * @date 2014-8-2
 */
public class ImportXmlWork implements IXmlWork<ExcelEntity>{
    private final static Logger LOGGER= LoggerFactory.getLogger(ImportXmlWork.class);
	
	private XStream xs ;
	private ExcelEntity excelEntity;
	
	/**
	 * Creates a new ImportXmlWork object.
	 * 
	 *
	 */
	public ImportXmlWork(){
		this.init();
	}
	
	/**
	 * Init method
	 */
	@Override
	public void init() {
		excelEntity = new ExcelEntity();
		xs = new XStream();
		xs.processAnnotations(ExcelEntity.class);
		xs.processAnnotations(MacroEntity.class);
		xs.processAnnotations(ColumnTitleEntity.class);
		xs.processAnnotations(DataCellEntity.class);
		xs.processAnnotations(MergeCellEntity.class);
		xs.processAnnotations(ColorCellEntity.class);
	}

	/**
	 * Convert xml file to objects
	 */
	@Override
	public ExcelEntity xmlFile2Obj(String xmlFile){
		try {
			URL url =  ImportXmlWork.class.getClassLoader().getResource(xmlFile);
			String path = URLDecoder.decode(url.getPath(), "UTF-8");
			FileInputStream fis = new FileInputStream(path);
			excelEntity = (ExcelEntity)xs.fromXML(fis);
		} catch (Exception e) {
//			LOGGER.error(e.getMessage(),e);
		} 
		return excelEntity;
	}
	
	/**
	 * Convert xml format string to objects
	 */
	@Override
	public ExcelEntity xmlStr2Obj(String xmlStr) {
		excelEntity = (ExcelEntity)xs.fromXML(xmlStr);
		return excelEntity;
	}

	/**
	 * Convert objects to xml format string
	 */
	@Override
	public String obj2XmlStr(ExcelEntity entity) {
		return xs.toXML(entity);
	}

	@Override
	public ExcelEntity xmlFile2Obj(HttpServletRequest request, String xmlFile) {
	    try {
            FileInputStream fis = new FileInputStream(request.getSession().getServletContext().getRealPath(xmlFile));
            excelEntity = (ExcelEntity)xs.fromXML(fis);
        } catch (Exception e) {
//            LOGGER.error(e.getMessage(),e);
        } 
        return excelEntity;
	}

}
