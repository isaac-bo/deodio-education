package com.deodio.components.excel.writer;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * This is excel writer helper class for excel 2007 version.
 * 
 * @ClassName: Excel2007Writer
 * @author sunj
 * @date 2014-8-8
 */
public abstract class SXSSFWriter extends BaseWriter{
    
    protected  int writeCacheSize = 100;
    protected final static String EXPANDED_NAME=".xlsx";
    protected Map<String,CellStyle> styleRelMap = new HashMap<String,CellStyle>();
    protected Map<String,String> mergeCellsMap = new HashMap<String,String>();
    
    

    public SXSSFWriter() {
        super.workbook = new SXSSFWorkbook(writeCacheSize);
        super.sheet = workbook.createSheet();
        super.creationHelper = workbook.getCreationHelper();
    }
    
    public SXSSFWriter(XSSFWorkbook xssfWorkbook) {
        super.workbook = new SXSSFWorkbook(xssfWorkbook,writeCacheSize);
        super.sheet = workbook.getSheetAt(0);
        super.creationHelper = workbook.getCreationHelper();
    }
    
    public SXSSFWriter(XSSFWorkbook xssfWorkbook,int sheetNumber) {
        super.workbook = new SXSSFWorkbook(xssfWorkbook,writeCacheSize);
        super.sheet = workbook.getSheetAt(sheetNumber);
        super.creationHelper = workbook.getCreationHelper();
    }
    public SXSSFWriter(XSSFWorkbook xssfWorkbook,Sheet sheet) {
        super.workbook = new SXSSFWorkbook(xssfWorkbook,writeCacheSize);
        super.sheet = sheet;
        super.creationHelper = workbook.getCreationHelper();
    }
    public int getWriteCacheSize() {
        return writeCacheSize;
    }

    public void setWriteCacheSize(int writeCacheSize) {
        this.writeCacheSize = writeCacheSize;
    }

    public Map<String, CellStyle> getStyleRelMap() {
        return styleRelMap;
    }

    public void setStyleRelMap(Map<String, CellStyle> styleRelMap) {
        this.styleRelMap = styleRelMap;
    }

    public Map<String, String> getMergeCellsMap() {
        return mergeCellsMap;
    }

    public void setMergeCellsMap(Map<String, String> mergeCellsMap) {
        this.mergeCellsMap = mergeCellsMap;
    }
    
    
    
    
    
    
    
}
