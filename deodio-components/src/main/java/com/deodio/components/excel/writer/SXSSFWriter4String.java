package com.deodio.components.excel.writer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.core.exception.ExcelException;
import com.google.common.collect.Lists;

/**
 * This is excel writer helper class for excel 2007 version.
 * 
 * @ClassName: SXSSFWriter4XML
 * @author sunj
 * @date 2014-8-8
 */
public class SXSSFWriter4String extends SXSSFWriter{
    private final static Logger LOGGER= LoggerFactory.getLogger(SXSSFWriter4String.class);
    
    /*<T> Data List*/
    private List<String> dataList = Lists.newArrayList();
    
    public static final String CELL_DATA_DELIMITER = "_#_";
    public static final String CELL_STYLE_DELIMITER = "]@[";
    private int styleTitleRow = 0;
    
    
    /**
     * Constructed function
     * 
     * 
     * @param dataList
     */
    public SXSSFWriter4String(List<String> dataList){
        this.dataList = dataList;
    }
    
    
    /**
     * Constructed function
     * 
     * 
     * @param xssfWorkbook
     * @param dataList
     */
    public SXSSFWriter4String(XSSFWorkbook xssfWorkbook,List<String> dataList){
        super(xssfWorkbook);
        this.dataList = dataList;
    }
    
    public SXSSFWriter4String(XSSFWorkbook xssfWorkbook,List<String> dataList,Sheet sheet){
        super(xssfWorkbook,sheet);
        this.dataList = dataList;
    }
    
    
    
    
    /**
     * Write cell data onto excel file
     * @Title: writeCellData
     */
    public void writeCellData(){
            int colTotalNum =dataList.get(0).split(CELL_DATA_DELIMITER).length;
            titleStyle= sheet.getWorkbook().createCellStyle();
            
            Font titleFont = workbook.createFont();
            titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            titleFont.setFontHeight((short) 250);          
            titleFont.setFontName("宋体");                     
         
            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
            titleStyle.setBorderBottom(CellStyle.BORDER_THIN);
            titleStyle.setBorderLeft(CellStyle.BORDER_THIN);
            titleStyle.setBorderTop(CellStyle.BORDER_THIN);
            titleStyle.setBorderRight(CellStyle.BORDER_THIN);
            titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            titleStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
            titleStyle.setWrapText(true);
            titleStyle.setFont(titleFont);
            
            for(int i=0;i<dataList.size();i++){
                Row row = getRow(i+startRow-1);
                String[] rowData = dataList.get(i).split(CELL_DATA_DELIMITER);
                for(int y=0 ;y<colTotalNum;y++){
                    String cellVal = rowData[y] ; 
                    if(StringUtils.contains(cellVal, CELL_STYLE_DELIMITER)){
                        String styleKey = StringUtils.substringAfterLast(cellVal, CELL_STYLE_DELIMITER);
                        if(styleRelMap.containsKey(styleKey)){
                            createData(row,y,StringUtils.substringBeforeLast(cellVal,CELL_STYLE_DELIMITER),styleRelMap.get(styleKey));
                        }else{
                            createData(row,y,StringUtils.substringBeforeLast(cellVal,CELL_STYLE_DELIMITER));
                        }
                    }else if(i<styleTitleRow){
                        createData(row,y,cellVal,titleStyle);
                    }else{
                        createData(row,y,cellVal);
                    }
                    
                }
            }
            for(int z=0 ;z<colTotalNum;z++){
                sheet.autoSizeColumn(z, true);
            }
    }
    
    public void mergeCells(){
        for(String mKey:mergeCellsMap.keySet()){
            addMergedRegion(mKey,mergeCellsMap.get(mKey));
        }
    }
    
    private void addMergedRegion(String startCell,String endCell){
        int startCellRow = Integer.parseInt(StringUtils.substringAfter(startCell, "-"))-1;
        int startCellCol = Integer.parseInt(StringUtils.substringBefore(startCell, "-"))-1;
        int endCellRow =  Integer.parseInt(StringUtils.substringAfter(endCell, "-"))-1;
        int endCellCol = Integer.parseInt(StringUtils.substringBefore(endCell, "-"))-1;
        sheet.addMergedRegion(new CellRangeAddress(startCellRow,endCellRow,startCellCol,endCellCol));
    }
    

    /**
     * Process methods
     * 
     * @param filePath
     */
    public boolean process(String filePath){
        super.filePath = filePath;
        try {
            if(!mergeCellsMap.isEmpty()){
                mergeCells();
            }
            if(!dataList.isEmpty()){
                writeCellData();
            }
            generateFile();
        } catch (ExcelException e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean process(String filePath,HttpServletRequest request,HttpServletResponse response) {
        super.filePath = filePath;
        super.request = request;
        super.response = response;
        try {
            if(!mergeCellsMap.isEmpty()){
                mergeCells();
            }
            if(!dataList.isEmpty()){
                writeCellData();
            }
            generateResponseStream();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }
        return true;
    }
    
    
    public int getStyleTitleRow() {
        return styleTitleRow;
    }


    public void setStyleTitleRow(int styleTitleRow) {
        this.styleTitleRow = styleTitleRow;
    }
    

}
