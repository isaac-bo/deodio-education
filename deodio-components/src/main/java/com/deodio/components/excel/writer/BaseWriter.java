package com.deodio.components.excel.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.core.exception.ExcelException;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.IOUtils;
import com.deodio.core.utils.ServletUtil;



public abstract class BaseWriter implements IExcelWriter{
    
    private final static Logger LOGGER= LoggerFactory.getLogger(BaseWriter.class);
    
    protected Workbook workbook;
    protected Sheet sheet;
    protected String filePath;
    protected int startRow = 1;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    protected CellStyle headStyle;  
    protected CellStyle titleStyle; 
    protected CellStyle dataStyle;  
    
    CreationHelper creationHelper;
    
    public final static String EXPANDED_NAME=".xlsx";
    
    
    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public CellStyle getHeadStyle() {
        return headStyle;
    }

    public void setHeadStyle(CellStyle headStyle) {
        this.headStyle = headStyle;
    }

    public CellStyle getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(CellStyle titleStyle) {
        this.titleStyle = titleStyle;
    }

    public CellStyle getDataStyle() {
        return dataStyle;
    }

    public void setDataStyle(CellStyle dataStyle) {
        this.dataStyle = dataStyle;
    }
    
    /**
     * Sheet
     * @param sheetName
     */
    public void createSheet(String sheetName){
        this.sheet=workbook.createSheet(sheetName);
    }
    
    
    public Workbook getWorkbook() {
        return workbook;
    }

    /**
     * Excel 
     * @param width     
     * @param cellCount 
     */
    public void setColumnWidth(int cellCount,int width){
        for(int i=0;i<cellCount;i++){
            sheet.setColumnWidth(i, width);
        }
    }
    
    /**
     * Excel 
     * @param width     
     * @param cellCount 
     * @param mapHashMap
     */
    public void setColumnWidth(int cellCount,int width,Map<Integer,Integer> map){
        for(int i=0;i<cellCount;i++){
            if(map.containsKey(i)){
                sheet.setColumnWidth(i, map.get(i));
            }else{
                sheet.setColumnWidth(i, width);
            }
        }
    }
    
    public void initDefaultStyle(){
       
        Font headFont = workbook.createFont();
        headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headFont.setFontHeight((short) 380);             
        headFont.setFontName("宋体");                  
       
        headStyle=workbook.createCellStyle();
        headStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headStyle.setFont(headFont);
        
     
        Font titleFont = workbook.createFont();
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleFont.setFontHeight((short) 150);             
        titleFont.setFontName("宋体");                     
     
        titleStyle=workbook.createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        titleStyle.setWrapText(true);
        titleStyle.setBorderBottom(CellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(CellStyle.BORDER_THIN);
        titleStyle.setBorderTop(CellStyle.BORDER_THIN);
        titleStyle.setBorderRight(CellStyle.BORDER_THIN);
        titleStyle.setFont(titleFont);
        
     
        Font dataFont = workbook.createFont();
        dataFont.setFontHeight((short) 150);              
        dataFont.setFontName("宋体");                   
      
        dataStyle=workbook.createCellStyle();
        dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
        dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dataStyle.setBorderTop(CellStyle.BORDER_THIN);
        dataStyle.setBorderRight(CellStyle.BORDER_THIN);
        dataStyle.setFont(dataFont);
    }
    
    /**
     * 
     * @param firstRow      
     * @param lastRow      
     * @param firstCol     
     * @param lastCol       
     * @param displayText   
     */
    public void mergedRegionDisplayText(int firstRow,int lastRow,int firstCol,int lastCol,String displayText){
        sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstCol,lastCol));
        Row row = getRow(firstRow);
        Cell cell=getCell(row,firstCol);
        
        cell.setCellValue(creationHelper.createRichTextString(displayText));
    }
    
    /**
     * 
     * @param firstRow     
     * @param firstCol     
     * @param lastCol       
     * @param displayText   
     * @param style         
     */
    public void mergedRegionDisplayText(int firstRow,int lastRow,int firstCol,int lastCol,String displayText,CellStyle style){
        CellRangeAddress cellRangeAddressnew=new CellRangeAddress(firstRow,lastRow,firstCol,lastCol);
        sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstCol,lastCol));

         for (int i = cellRangeAddressnew.getFirstRow(); i <= cellRangeAddressnew.getLastRow(); i ++) {
                Row currRow = getRow(i);
                for (int j = cellRangeAddressnew.getFirstColumn(); j <= cellRangeAddressnew.getLastColumn(); j++) {
                    Cell currCell = getCell(currRow,j);
                    currCell.setCellStyle(style);
                }
            }
            Row row = getRow(firstRow);
            Cell cell=getCell(row,firstCol);
            cell.setCellValue(creationHelper.createRichTextString(displayText));
    }
    
    /**
     * Row
     * @param rowIndex  
     * @return          Row
     */
    public Row getRow(int rowIndex){
        if(null==sheet.getRow(rowIndex)){
            return sheet.createRow(rowIndex);
        }else{
            return sheet.getRow(rowIndex);
        }
    }
    
    /**
     * RowCell
     * @param row              
     * @param cellIndex    
     * @return              Cell
     */
    public  Cell getCell(Row row,int cellIndex){
        if(null==row.getCell(cellIndex)){
            return row.createCell(cellIndex);
        }else{
            return row.getCell(cellIndex);
        }
    }
    
    /**
     * 
     * @param title        
     * @param rowNumber     
     */
    public void createTitle(String[]  title,int rowIndex){
        Row row = sheet.createRow(rowIndex);
        for(int i=0;i<title.length;i++){
            Cell cell=row.createCell(i);
            cell.setCellValue(creationHelper.createRichTextString(title[i]));
            cell.setCellStyle(titleStyle);
        }
    }
    
    /**
     *
     * @param row           
     * @param cellIndex      
     * @param displayText  
     */
    public void createData(Row row,int cellIndex,String displayText){
        Cell cell=getCell(row, cellIndex);
        cell.setCellValue(creationHelper.createRichTextString(displayText));
        cell.setCellStyle(dataStyle);
    }
    
    /**
     * 
     * @param row          
     * @param cellIndex   
     * @param displayText  
     * @param style         
     */
    public void createData(Row row,int cellIndex,String displayText,CellStyle style){
        Cell cell=row.createCell(cellIndex);
        cell.setCellValue(creationHelper.createRichTextString(displayText));
        cell.setCellStyle(style);
    }
    
    /**
     *     					 HttpServletResponse
     * @param response     HttpServletResponse
     * @return
     * @throws UnsupportedEncodingException
     */
    public HttpServletResponse getSettingResponse(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
      
        ServletUtil.setContentType(response, ServletUtil.EXCEL_TYPE); 
        
      
        ServletUtil.setFileDownloadHeader(request,response, filePath+EXPANDED_NAME);
       
        ServletUtil.setDisableCacheHeader(response);
        return response;
    }
    
    
    /**
     * Excel
     * @param outputStream 
     * @throws IOException
     */
    public void export(OutputStream os){
        try {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        }finally{
            IOUtils.closeQuietly(os);
        }
    }
    
    
    /**
     *Response
     * @param response
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public void generateResponseStream() throws ExcelException{
        try {
            export(getSettingResponse(request,response).getOutputStream());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        }
    }

    public void generateFile(){
        FileOutputStream fos = null;
        try {
            final File file = new File(filePath);
            FileUtils.createDir(file.getParentFile().getPath());
            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            if(workbook instanceof SXSSFWorkbook){
                ((SXSSFWorkbook) workbook).dispose();   
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e);
        }finally{
            IOUtils.closeQuietly(fos);
        }
    }

}
