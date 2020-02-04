package com.deodio.components.excel.writer;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * This is XSSF wabstract class, XSSF is POI API for writing excel 2007 version
 * @ClassName: XssfWriter
 * @author sunj
 * @date 2014-8-8
 */
public abstract class XSSFWriter extends BaseWriter{
    
    public XSSFWriter(){
        super.workbook = new XSSFWorkbook();
        super.sheet = workbook.createSheet();
        super.creationHelper = workbook.getCreationHelper();
    }
    
}

