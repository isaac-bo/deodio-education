package com.deodio.components.excel.writer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * This is XSSF writer helper class, XSSF is POI API for writing excel 2007 version
 * @ClassName: XssfWriter
 * @author sunj
 * @date 2014-8-8
 */
public abstract class HSSFWriter extends BaseWriter{
    
    
    public HSSFWriter() {
        super.workbook = new HSSFWorkbook();
        super.sheet = workbook.createSheet();
        super.creationHelper = workbook.getCreationHelper();
    }
}

