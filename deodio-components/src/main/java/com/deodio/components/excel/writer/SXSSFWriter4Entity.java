package com.deodio.components.excel.writer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.components.excel.entity.DataCellEntity;
import com.deodio.components.excel.entity.ExcelEntity;
import com.deodio.components.excel.entity.MergeCellEntity;
import com.deodio.core.exception.ExcelException;


/**
 * This is excel writer helper class for excel 2007 version.
 * 
 * @ClassName: SXSSFWriter4Entity
 * @author sunj
 * @date 2014-8-8
 */
public class SXSSFWriter4Entity extends SXSSFWriter{
    private final static Logger LOGGER= LoggerFactory.getLogger(SXSSFWriter4Entity.class);
    
    private ExcelEntity excelEntity;
    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public SXSSFWriter4Entity(ExcelEntity excelEntity){
        this.excelEntity = excelEntity;
        
    }
    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     * @param workbook
     */
    public SXSSFWriter4Entity(XSSFWorkbook xssfWorkbook,ExcelEntity excelEntity){
        super(xssfWorkbook);
        this.excelEntity = excelEntity;
    }
    /**
     * 
     * more  sheet 
     * 
     * @param xssfWorkbook
     * @param excelEntity
     * @param sheet
     */
    public SXSSFWriter4Entity(XSSFWorkbook xssfWorkbook,ExcelEntity excelEntity,Sheet sheet){
        super(xssfWorkbook,sheet);
        this.excelEntity = excelEntity;
    }
    /**
     * Merge Cells
     * @Title: mergeCells
     */
     public void mergeCells(){
         for(MergeCellEntity mergeCell:excelEntity.getMergeCells()){
             String startCell = mergeCell.getStartCellNum();
             String endCell = mergeCell.getEndCellNum();
             int startCellRow = Integer.parseInt(StringUtils.substringAfter(startCell, "-"))-1;
             int startCellCol = Integer.parseInt(StringUtils.substringBefore(startCell, "-"))-1;
             int endCellRow =  Integer.parseInt(StringUtils.substringAfter(endCell, "-"))-1;
             int endCellCol = Integer.parseInt(StringUtils.substringBefore(endCell, "-"))-1;
             sheet.addMergedRegion(new CellRangeAddress(startCellRow,endCellRow,startCellCol,endCellCol));
         }
     }
     
     
     /**
      * Write cell data onto excel file
      * @Title: writeCellData
      */
     public void writeCellData(){
         int cellRow = 0;
         Row row = getRow(cellRow);
         for(DataCellEntity dataCellEntity:excelEntity.getDataCells()){
             String cellNum = dataCellEntity.getCellNum();
             int curRow =  Integer.parseInt(StringUtils.substringAfter(cellNum, "-"))-1;
             int curCol =  Integer.parseInt(StringUtils.substringBefore(cellNum, "-"))-1;
             if(curRow!=cellRow){
                 cellRow = curRow;
                 row = sheet.createRow(curRow);
             }
             Cell cell = getCell(row,curCol);
             cell.setCellValue(creationHelper.createRichTextString(dataCellEntity.getCellValue()));
         }
     }
    

    /**
     * Process methods
     * 
     * @param filePath
     */
    @Override
    public boolean process(String filePath){
        super.filePath = filePath;
        try {
            mergeCells();
            writeCellData();
            generateFile();
        } catch (ExcelException e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean process(String filePath,HttpServletRequest request,HttpServletResponse response){
        super.filePath = filePath;
        super.request = request;
        super.response = response;
            try {
                mergeCells();
                writeCellData();
                generateFile();
                generateResponseStream();
            } catch (ExcelException e) {
                LOGGER.error(e.getMessage(),e);
            }
        return true;
    }
 
}
