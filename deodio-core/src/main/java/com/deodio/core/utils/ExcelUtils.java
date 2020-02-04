package com.deodio.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This is utils for excel
 * 
 * @ClassName: ExcelUtils
 * @author isaac
 * @date 2014-8-2
 */
public class ExcelUtils {
	
	
	 private static Workbook wb;
	 private static Sheet sheet;
	 private static Row row;

    /**
     * @Title: cellConvert
     * @param cell
     * @return String
     */
    public String cellConvert(String cell) {
        return convertLetter2Number(StringUtils.substring(cell, 0,
                numberSplitLen(cell)))
                + "-"
                + StringUtils.substring(cell, numberSplitLen(cell),
                        cell.length());
    }

    /**
     * Returns the number of digits
     * 
     * @Title: numberSplitLen
     * @param str
     * @return int
     */
    public int numberSplitLen(String str) {
        int size = 0;
        int chr;
        for (int i = 0; i < str.length(); i++) {
            chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }

    /**
     * Convert string to number
     * 
     * @Title: convertLetter2Number
     * @param letter
     * @return int
     */
    public int convertLetter2Number(String letter) {
        if (letter.length() == 1) {
            return letter.charAt(0) - 64;
        } else {
            return (letter.charAt(0) - 64) * 26 + letter.charAt(1) - 64;
        }
    }

    /**
     * format excel cell
     * 
     * @Title: getCellValue
     * @param cell
     * @return String
     */
    public static String getCellValue(XSSFCell cell) {
        if (cell == null) {
            return "";
        } else {
            String cellValue = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date d = cell.getDateCellValue();
                    cellValue = sdf.format(d);
                    sdf.format(d);
                } else {
                	cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            default:
                cellValue = "";
            }
            return cellValue;
        }
    }

    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                    return true;
            }
        }
        return false;
    }
    
    
    
    public static boolean isFirstMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int firstRow = ca.getFirstRow();
            if (row ==firstRow && column == firstColumn  ) {
                return true;
            }
        }
        return false;
    }
    
    public static int getRowNum(InputStream is,int sheetNum,String type){
    	try {
			if("xlsx".equals(type)){
				wb = new XSSFWorkbook(is);
			}else{
				wb = new HSSFWorkbook(is);
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// 读取指定sheet
    	sheet = wb.getSheetAt(sheetNum);
    	
    	if(sheet == null){
    		return 0;
    	}else{
    		return sheet.getLastRowNum();
    	}
    	
    }
    
    public static int getColNum(InputStream is,int sheetNum,int rowNum,String type){
    	try {
			if("xlsx".equals(type)){
				wb = new XSSFWorkbook(is);
			}else{
				wb = new HSSFWorkbook(is);
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// 读取指定sheet
    	sheet = wb.getSheetAt(sheetNum);
    	// 读取指定行
    	row = sheet.getRow(rowNum);
    	if(row == null){
    		return 0;
    	}else{
    		return row.getPhysicalNumberOfCells();
    	}
    }
    
    public static String readExcelContent(InputStream is ,int sheetNum , int rowNum , int colNum,String type){

		try {
			if("xlsx".equals(type)){
				wb = new XSSFWorkbook(is);
			}else{
				wb = new HSSFWorkbook(is);
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		// 读取指定sheet
		sheet = wb.getSheetAt(sheetNum);
		// 读取指定行
		row = sheet.getRow(rowNum); 
		if(row == null){
			return "";
		}else{
			return getNewCellValue(row.getCell(colNum));
		}
		
	}
    
    public static String getNewCellValue(Cell cell) {
        if (cell == null) {
            return "";
        } else {
            String cellValue = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue().trim();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date d = cell.getDateCellValue();
                    cellValue = sdf.format(d);
                    sdf.format(d);
                } else {
                	cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case Cell.CELL_TYPE_FORMULA:
                double value_temp = cell.getNumericCellValue();
                BigDecimal bd = new BigDecimal(value_temp);
                cellValue = String.valueOf(bd.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
                break;
            default:
                cellValue = "";
            }
            return cellValue;
        }
    }
    

}
