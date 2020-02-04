package com.deodio.components.excel.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.components.excel.entity.ImportColumnEntity;
import com.deodio.components.excel.entity.ImportEntity;
import com.deodio.core.exception.ExcelException;
import com.google.common.collect.Lists;

public class XSSFDomReader4Xml<T> extends BaseDomReader{
    private final static Logger LOGGER= LoggerFactory.getLogger(XSSFDomReader4Xml.class);
    
    /*Import Entity*/
    private ImportEntity imEntity;
    
    /*Import Column Config*/
    private List<ImportColumnEntity> columns;
    
    /*<T> Data List*/
    private List<T> dataList = Lists.newArrayList();
    
    private Class<T> clazz;
    
    private T data ;
    
    private Map<Integer,ImportColumnEntity> columnMappingMap = new HashMap<Integer,ImportColumnEntity>();

    
    
    public XSSFDomReader4Xml(Class<T> clazz,ImportEntity imEntity) {
        this.clazz = clazz;
        this.imEntity = imEntity;
        this.columns = imEntity.getColumns();
        this.bulidColumnMapping();
    }
    
    public XSSFDomReader4Xml(Class<T> clazz,ImportEntity imEntity, int startRow,int sheetNum) {
        this.clazz = clazz;
        this.imEntity = imEntity;
        this.columns = imEntity.getColumns();
        super.startRow = startRow;
        super.sheetNum = sheetNum;
        this.bulidColumnMapping();
    }

    @Override
    public boolean process(String filePath) {
                try {
                    workbook =new XSSFWorkbook(new FileInputStream(new File(filePath)));
                    sheet = workbook.getSheetAt(sheetNum);
                    readData();
                } catch (FileNotFoundException e) {
                    LOGGER.error(e.getMessage(),e);
                } catch (ExcelException e) {
                    LOGGER.error(e.getMessage(),e);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(),e);
                }
        return true;
    }
    
    
    @Override
    public void readData() throws ExcelException {
        try {
            int rows = sheet.getLastRowNum();
            for(int j =  startRow -1; j <= rows; j ++) {  
                row = getRow(j);  
                int cells = row.getLastCellNum();
                data = clazz.newInstance();
                for(int k = 0 ; k < cells ; k ++) {  
                    cell = getCell(row,k);
                    if(null==cell){
                        continue;
                    }
                    String val = getCellValue(cell);
                    if(endRowCheck(k+1, val) == Boolean.FALSE && columnMappingMap.containsKey(k+1)){
                            if(isSameMergeCellColumnVal(k+1,val)){
                                setClassAttrValue(columnMappingMap.get(k+1),merCellColumnMap.get(k+1));
                            }else{
                                setClassAttrValue(columnMappingMap.get(k+1),val);
                            }
                        
                    }
                    if(isEndRow){
                        break;
                    }
                }
                if(isEndRow){
                    break;
                }else{
                    dataList.add(data);
                }
            }
        } catch (SecurityException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        }  
    }

    
    
    private void bulidColumnMapping(){
        for(ImportColumnEntity column:columns){
            columnMappingMap.put(column.getSortNo(), column);
        }
    }
    
    private void  setClassAttrValue(ImportColumnEntity column, String val) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method=clazz.getMethod("set"+StringUtils.capitalize(column.getField().trim()));
        method.invoke(data,val);
    }



}
