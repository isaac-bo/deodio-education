package com.deodio.components.excel.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.common.collect.Lists;

public class XSSFSaxReader4Annotation extends BaseSaxReader{
    private final static Logger LOGGER= LoggerFactory.getLogger(XSSFSaxReader4Annotation.class);

    private String rowCellDataStr = "";
    
    private List<String> rowCellDataList = Lists.newArrayList();

    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4Annotation() {
        
    }
    
    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4Annotation(int sheetNum) {
        super.sheetNum = sheetNum;
    }
    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4Annotation(int startRow,int endCol) {
        super.startRow = startRow;
        super.endCol = endCol;
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
            if (this.isDataRow() && this.isNotEndingCol()) {
                // v => The cell value, if the cell is the string V tag value is the string in the SST index
                String value = this.getDataValue(lastContents.trim(), "");
                rowCellDataStr = rowCellDataStr + CELL_DATA_DELIMITER + value;
                curCol++;
            } 
        } else if ("c".equals(name)) {
            if (this.isDataRow() && this.isNotEndingCol()) {
                rowCellDataStr = rowCellDataStr + CELL_DATA_DELIMITER + BLANK_STR;
            } 
        } else {
            // If the tag name for the row, this note has been to the end of the line, call optRows () method
            if ("row".equals(name)) {
                curRow++;
                rowCellDataStr = StringUtils.removeStart(rowCellDataStr, CELL_DATA_DELIMITER);
                rowCellDataList.add(rowCellDataStr);
                rowCellDataStr = "";
                if (colCount > maxCol) {
                    maxCol = colCount;
                }
                colCount = 0;
                curCol = 0;
            }
        }
    }

    
    /**
     * @return the rowCellDataList
     */
    
    public List<String> getRowCellDataList() {
        return rowCellDataList;
    }

    /**
     * @param rowCellDataList the rowCellDataList to set
     */
    
    public void setRowCellDataList(List<String> rowCellDataList) {
        this.rowCellDataList = rowCellDataList;
    }

}
