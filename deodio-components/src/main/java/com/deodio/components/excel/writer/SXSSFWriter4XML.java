package com.deodio.components.excel.writer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.components.excel.entity.ExportColumnEntity;
import com.deodio.components.excel.entity.ExportEntity;
import com.deodio.components.excel.helper.ExporHelper;
import com.deodio.core.exception.ExcelException;
import com.google.common.collect.Lists;

/**
 * This is excel writer helper class for excel 2007 version.
 * 
 * @ClassName: SXSSFWriter4XML
 * @author sunj
 * @date 2014-8-8
 */
public class SXSSFWriter4XML<T> extends SXSSFWriter{
    private final static Logger LOGGER= LoggerFactory.getLogger(SXSSFWriter4XML.class);
    
    /*Export Entity*/
    private ExportEntity exEntity;
    
    /*Export Column Config*/
    private List<ExportColumnEntity> columns;
    
    /*<T> Data List*/
    private List<T> dataList = Lists.newArrayList();
    
    
    /**
     * Constructed function
     * 
     * 
     * @param exEntity
     */
    public SXSSFWriter4XML(ExportEntity exEntity,List<T> dataList){
        this.exEntity = exEntity;
        this.dataList = dataList;
        columns = exEntity.getColumns();
        Collections.sort(columns);
    }
    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     * @param workbook
     */
    public SXSSFWriter4XML(XSSFWorkbook xssfWorkbook,ExportEntity exEntity,List<T> dataList){
        super(xssfWorkbook);
        this.exEntity = exEntity;
        this.dataList = dataList;
        columns = exEntity.getColumns();
        Collections.sort(columns);
    }
    
     
     /**
      * Write cell data onto excel file
     * @throws ExcelException  
      * @Title: writeCellData
      */
     public void writeCellData() throws ExcelException{
         try {
            if(!dataList.isEmpty()){
                 for(int i=0; i<dataList.size(); i++){
                     int j = 0;
                     T data = dataList.get(i);
                     for(ExportColumnEntity column:columns){
                         Method method=data.getClass().getMethod("get"+StringUtils.capitalize(column.getField().trim()));
                         Object objVal=method.invoke(data);
                         
                         //ExcelData Extend Handler
                         if(null!=objVal&&StringUtils.isNotBlank(column.getHandleMethod())){  
                             Method handlemethod=ExporHelper.class.getMethod(column.getHandleMethod().trim(),Object.class);
                             objVal=handlemethod.invoke(ExporHelper.class,objVal);
                         }
                         super.createData(super.getRow(startRow+i-1), j,null==objVal?"-":String.valueOf(objVal));
                         j++;
                     }
                 }
             }
        } catch (SecurityException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException();
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException();
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException();
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException();
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException();
        }
     }
     
     public void writeCellTitle(){
         for(ExportColumnEntity column:columns){
             super.createData(super.getRow(startRow-1),column.getSortNo()-1,column.getTitle());
         }
         startRow++;
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
            writeCellTitle();
            writeCellData();
            generateFile();
        } catch (Exception e) {
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
            writeCellTitle();
            writeCellData();
            generateFile();
            generateResponseStream();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }
        return true;
    }
    
}
