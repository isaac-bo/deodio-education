package com.deodio.components.excel.reader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.deodio.components.excel.entity.ImportColumnEntity;
import com.deodio.components.excel.entity.ImportEntity;
import com.google.common.collect.Lists;

public class XSSFSaxReader4Xml<T> extends BaseSaxReader{
    private static final Logger LOGGER = LoggerFactory.getLogger(XSSFSaxReader4Xml.class);

    /*Import Entity*/
    private ImportEntity imEntity;
    
    /*Import Column Config*/
    private List<ImportColumnEntity> columns;
    
    /*<T> Data List*/
    private List<T> dataList = Lists.newArrayList();
    
    private Class<T> clazz;
    
    private T data ;
    
    private Map<Integer,ImportColumnEntity> columnMappingMap = new HashMap<Integer,ImportColumnEntity>();

    
    /**
     * Constructed function
     * 
     * 
     */
    public XSSFSaxReader4Xml(Class<T> clazz) {
        this.clazz = clazz;
    }
    /**
     * Constructed function
     * 
     * 
     * @param sheetNum
     */
    public XSSFSaxReader4Xml(Class<T> clazz,int sheetNum) {
        this.clazz = clazz;
        super.sheetNum = sheetNum;
        
    }
    
    
    /**
     * Constructed function
     * 
     * 
     * @param imEntity
     */
    public XSSFSaxReader4Xml(Class<T> clazz,ImportEntity imEntity,int startRow) {
        this.clazz = clazz;
        this.imEntity = imEntity;
        this.columns = imEntity.getColumns();
        super.startRow = startRow;
        this.bulidColumnMapping();
        try {
            data = clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
    
    
    /**
     * This is calculate the start element. Base on uri, local name, name and attributes of each cells.
     * @Title: startElement
     * @param uri
     * @param localName
     * @param name
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String name,
            Attributes attributes) throws SAXException {
        // c => cell
        if ("c".equals(name)) {
            // Setting the cell type
            curCell = attributes.getValue("r");
            colCount++;
            this.setNextDataType(attributes);
        } else if ("mergeCell".equals(name) && maxRow == 0) {
            maxRow = curRow+1;
        }

        // if the element is T
        if ("t".equals(name)) {
            isTElement = true;
        } else {
            isTElement = false;
        }

        lastContents = "";
    }
    
    /**
     * This is calculate the end element. Base on uri, local name and name.
     * @Title: endElement
     * @param uri
     * @param localName
     * @param name
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        // According to the SST index value to the cells to store the string
        // Then characters () method may be called many times
        if (nextIsString) {
            int idx = Integer.parseInt(lastContents);
            lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
                    .toString();
            nextIsString = false;
        }

        // The T element contains a string
        if (isTElement) {
            curCol++;
            isTElement = false;
        } else if ("v".equals(name)) {
            curCol++;
            // v => The cell value, if the cell is the string V tag value is the string in the SST index
            String value = this.getDataValue(lastContents.trim(), "");
            try {
                if (this.isDataRow()&&columnMappingMap.containsKey(curCol)) {
                    setClassAttrValue(columnMappingMap.get(curCol),value);
                }
            } catch (Exception e) {
            	LOGGER.error(e.getMessage(),e);
            }
                
        } else if ("c".equals(name)) {
        	// do nothing
        } else {
            if ("row".equals(name)) {
                curRow++;
                dataList.add(data);
                try {
                    data = clazz.newInstance();
                }catch (Exception e) {
                	LOGGER.error(e.getMessage(),e);
                }
                if (colCount > maxCol) {
                    maxCol = colCount;
                }
                colCount = 0;
                curCol = 0;
            }
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
