package com.deodio.components.excel.writer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.components.excel.annotation.ExcelColumn;
import com.deodio.components.excel.entity.ExportColumnEntity;
import com.deodio.components.excel.entity.ExportEntity;
import com.deodio.core.exception.ExcelException;
import com.google.common.collect.Lists;

/**
 * This is excel writer helper class for excel 2007 version.
 * 
 * @ClassName: SXSSFWriter4Annotation
 * @author sunj
 * @date 2014-8-8
 */
public class SXSSFWriter4Annotation extends SXSSFWriter{
    private static final Logger LOGGER = LoggerFactory.getLogger(SXSSFWriter4Annotation.class);
    
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
    public SXSSFWriter4Annotation(ExportEntity exEntity,List<T> dataList){
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
    public SXSSFWriter4Annotation(XSSFWorkbook xssfWorkbook,ExportEntity exEntity,List<T> dataList){
        super(xssfWorkbook);
        this.exEntity = exEntity;
        this.dataList = dataList;
        columns = exEntity.getColumns();
        Collections.sort(columns);
    }
    
    /**
     * Write cell title onto excel file
     */
    public void writeCellTitle(){
        for(ExportColumnEntity column:columns){
            super.createData(super.getRow(startRow),column.getSortNo(),column.getTitle());
        }
        startRow++;
    }
    
     
     /**
      * Write cell data onto excel file
     * @throws ExcelException  
      * @Title: writeCellData
      */
     public void writeCellData() throws ExcelException{
         try {
            if(!dataList.isEmpty()){
                 for(int i=0;i<dataList.size();i++){
                     T data=dataList.get(i);
                      Method[] methods = data.getClass().getMethods(); 
                      List<SortEntity> valList=Lists.newArrayList();
                      for(Method method:methods){
                          if(method.isAnnotationPresent(ExcelColumn.class)){
                              ExcelColumn excelColumn = method.getAnnotation(ExcelColumn.class);
                              SortEntity excelColumnval=new SortEntity();
                              excelColumnval.setSortNo(excelColumn.sortNo());
                              excelColumnval.setValue(String.valueOf(method.invoke(data)));
                              valList.add(excelColumnval);
                          }
                      }
                      Collections.sort(valList);
     
                      for(SortEntity entity: valList){
                          super.createData(super.getRow(startRow+i), entity.getSortNo(),entity.getValue());
                      }
                      
                 }
             }
        } catch (SecurityException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ExcelException(e.getMessage(),e);
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
            writeCellData();
            generateResponseStream();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }
        return true;
    }
    
}

class SortEntity implements Comparable<SortEntity>{
    private String value;
    private int sortNo;
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getSortNo() {
        return sortNo;
    }
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
    
    @Override
    public int compareTo(SortEntity o) {
        return this.getSortNo()-o.getSortNo();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sortNo;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        SortEntity other = (SortEntity) obj;
        if (sortNo != other.sortNo){
            return false;
        }
        if (value == null) {
            if (other.value != null){
                return false;
            }
        } else if (!value.equals(other.value)){
            return false;
        }
        return true;
    }
    
    
}
