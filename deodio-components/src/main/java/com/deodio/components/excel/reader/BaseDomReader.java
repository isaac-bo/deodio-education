package com.deodio.components.excel.reader;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * Excel Base Reader
 * @author pactera
 *
 */
public abstract class BaseDomReader implements IExcelDomReader{
    
    protected Workbook workbook;
    protected Sheet sheet;
    protected int sheetNum = 0;
    protected int startRow = 0;
    protected int curRow;
    /*Map<cellColumn,CellVal>*/
    protected Map<Integer,String> merCellColumnMap = new HashMap<Integer,String>();
    /*Map<cellColumn,StringVal>*/
    protected Map<Integer,String> endRowExprMap = new HashMap<Integer,String>();
    protected boolean isEndRow = false;
    
    
    
    protected Row row;
    protected Cell cell;
    private SimpleDateFormat sdf;
    protected String dateFormatPattern = "yyyy-MM-dd";
    
    public BaseDomReader(){
        sdf = new SimpleDateFormat(dateFormatPattern);
    }
    
    protected boolean isDataRow() {
        return curRow >= startRow;
    }
    
    /**
     * Row object
     * @param rowIndex  
     * @return          Row
     */
    protected Row getRow(int rowIndex){
       return sheet.getRow(rowIndex);
    }
    
    /**
     *RowCell
     * @param row           Row   
     * @param cellIndex     
     * @return              Cell
     */
    protected  Cell getCell(Row row,int cellIndex){
        return row.getCell(cellIndex);
    }
    
    
    protected boolean isSameMergeCellColumnVal(int curCol,String cellValue) {
        if(merCellColumnMap.containsKey(curCol)){
            if(cellValue.equals(merCellColumnMap.get(curCol))){
                return true;
            }else{
                if(StringUtils.isNotBlank(cellValue)){
                    merCellColumnMap.put(curCol, cellValue);
                }
                return true;
            }
        }else{
            return false;
        }
        
    }
    
    /**
     * It is determined whether the end of the line
     * @return boolean
     */
    protected boolean endRowCheck(int curCol,String cellValue){
        if(endRowExprMap.isEmpty()){
            isEndRow = false;
        }else{
            if(endRowExprMap.containsKey(curCol)){
                if(cellValue.equals(endRowExprMap.get(curCol))){
                    isEndRow = true;
                }else{
                    isEndRow = false;
                }
            }else{
                isEndRow = false;
            }
        }
        return isEndRow;
    }
    
    protected String getCellValue(Cell cell) {  
        String cellValue = "";  
        DecimalFormat df = new DecimalFormat("#");  
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_STRING:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                cellValue =sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
            }else{
                cellValue = df.format(cell.getNumericCellValue()).toString();  
            }
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
            break;  
        case Cell.CELL_TYPE_FORMULA:  
            cellValue = cell.getCellFormula();  
            break;  
        default:  
            cellValue = "";  
        }  
        return cellValue;  
    }  
    

    public Map<Integer,String> getMerCellColumnMap() {
        return merCellColumnMap;
    }

    public void setMerCellColumnMap(Map<Integer,String> merCellColumnMap) {
        this.merCellColumnMap = merCellColumnMap;
    }

    public Map<Integer, String> getEndRowExprMap() {
        return endRowExprMap;
    }

    public void setEndRowExprMap(Map<Integer, String> endRowExprMap) {
        this.endRowExprMap = endRowExprMap;
    }
   
}
