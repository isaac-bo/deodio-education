package com.deodio.components.excel.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.deodio.components.excel.entity.ColorCellEntity;
import com.deodio.components.excel.entity.DataCellEntity;
import com.deodio.components.excel.entity.ExcelEntity;
import com.deodio.components.excel.entity.MergeCellEntity;
import com.deodio.core.utils.ExcelUtils;


/**
 * This is excel convert to entity class, which can help system to convert the excel template to entity instance.
 * @ClassName: XSSFSaxReader4Entity
 * @author isaac
 * @date 2014-11-15
 */
public class XSSFSaxReader4Entity extends BaseSaxReader{
    private static final Logger LOGGER = LoggerFactory.getLogger(XSSFSaxReader4Entity.class);

    /**
     * T Element 
     */
    private boolean isTElement;

    /**
     * Exception message, if it was null, means there is not any exception
     */
    private String exceptionMessage;

    /**
     * Cell data type, the default type is string.
     */
    private CellDataType nextDataType = CellDataType.SSTINDEX;

    private final DataFormatter formatter = new DataFormatter();

    private short formatIndex;

    private String formatString;

    /**
     * cell styles
     */
    private StylesTable stylesTable;

    /**
     * merge cell collection
     */
    private List<MergeCellEntity> mergeCells;
    
    /**
     * color cell collection
     */
    private List<ColorCellEntity> colorCells;

    /**
     * data cell collection
     */
    private List<DataCellEntity> dataCells;

    private Map<String, String> dataMap = new HashMap<String, String>();

    private ExcelEntity excelEntity;
    private MergeCellEntity mergeCell;
    private ColorCellEntity colorCell;
    private DataCellEntity dataCell;

    private ExcelUtils excelUtils = new ExcelUtils();

    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4Entity(ExcelEntity excelEntity) {
        this.excelEntity = excelEntity;
        mergeCells = null == excelEntity.getMergeCells() ? new ArrayList<MergeCellEntity>()
                : excelEntity.getMergeCells();
        colorCells = null == excelEntity.getColorCells() ? new ArrayList<ColorCellEntity>()
                : excelEntity.getColorCells();
        dataCells = null == excelEntity.getDataCells() ? new ArrayList<DataCellEntity>()
                : excelEntity.getDataCells();
    }
    
    
    /**
	 * @return the dataCells
	 */
	
	public List<DataCellEntity> getDataCells() {
		return dataCells;
	}


	/**
	 * @param dataCells the dataCells to set
	 */
	
	public void setDataCells(List<DataCellEntity> dataCells) {
		this.dataCells = dataCells;
	}


	/**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4Entity(ExcelEntity excelEntity,int sheetNum) {
        this.excelEntity = excelEntity;
        super.sheetNum = sheetNum;
        mergeCells = null == excelEntity.getMergeCells() ? new ArrayList<MergeCellEntity>()
                : excelEntity.getMergeCells();
        colorCells = null == excelEntity.getColorCells() ? new ArrayList<ColorCellEntity>()
                : excelEntity.getColorCells();
        dataCells = null == excelEntity.getDataCells() ? new ArrayList<DataCellEntity>()
                : excelEntity.getDataCells();
    }
    
    
    @Override
    public void processEnd(){
        excelEntity.setColorCells(colorCells);
        excelEntity.setMergeCells(mergeCells);
        excelEntity.setDataCells(dataCells);
        excelEntity.setMaxRow(maxRow);
        excelEntity.setMaxCol(maxCol);
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
        if ("c".equals(name)) {
            curCell = attributes.getValue("r");
            colCount++;
            this.setNextDataType(attributes);
        } else if ("mergeCell".equals(name)) {
            if (maxRow == 0) {
                maxRow = curRow+1;
            }
            String mergeCellStr =  attributes.getValue("ref");
            if (Integer.parseInt(getMergeCellDStartCol(mergeCellStr)) < excelEntity.getDataStartRow()
                    || !excelEntity.isIsParseHeader()) {
                mergeCell = new MergeCellEntity(attributes.getValue("ref"));
                mergeCells.add(mergeCell);
            }
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
            // The content of cells into rowlist, before this, first remove the blank after a string of symbols
            curCol++;
            isTElement = false;
        } else if ("v".equals(name) && this.isEndingCol()) {
            // v => The cell value, if the cell is the string V tag value is the string in the SST index
            String value = this.getDataValue(lastContents.trim(), "");
            dataMap.put(curCell, value);

            if (this.isDataRow()) {
                dataCell = new DataCellEntity();
                dataCell.setCellNum(excelUtils.cellConvert(curCell));
                dataCell.setCellValue(value);
                dataCells.add(dataCell);
            } else{
                if (excelEntity.isIsParseHeader()) {
                    dataCell = new DataCellEntity();
                    dataCell.setCellNum(excelUtils.cellConvert(curCell));
                    dataCell.setCellValue(value);
                    dataCell.setCellType(1);
                    dataCells.add(dataCell);
                }
            }

            curCol++;
        } else if ("c".equals(name) && this.isEndingCol()) {
                if (!dataMap.containsKey(curCell) ) {
                    dataMap.put(curCell, "");
                 
                    if (this.isDataRow() && this.isEndingCol()) {
                        dataCell = new DataCellEntity();
                        dataCell.setCellNum(excelUtils.cellConvert(curCell));
                        dataCell.setCellValue("");
                        dataCells.add(dataCell);
    
                    } else{
                        if (excelEntity.isIsParseHeader()) {
                            dataCell = new DataCellEntity();
                            dataCell.setCellNum(excelUtils.cellConvert(curCell));
                            dataCell.setCellValue("");
                            dataCell.setCellType(1);
                            dataCells.add(dataCell);
                        }
                    }
                }
            } else {
                // If the tag name for the row, this note has been to the end of the line, call optRows () method
                if ("row".equals(name)) {
                    curRow++;
                    if (colCount > maxCol) {
                        maxCol = colCount;
                    }
                    colCount = 0;
                    curCol = 0;
                }
            }
    }


    
    private boolean isEndingCol(){
        return (this.curCol <= excelEntity.getDataEndCol() && excelEntity.getDataEndCol() >0)||excelEntity.getDataEndCol() <=0;
    }
    

    /**
     * @return the exceptionMessage
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    

    
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }


    private String getMergeCellDStartCol(String mergeCellStr){
        String startCell = StringUtils.substringBefore(mergeCellStr, ":");
        return StringUtils.substring(startCell, excelUtils.numberSplitLen(startCell), startCell.length());
    }


  
    
    /**
     * @return the dataMap
     */
    
    public Map<String, String> getDataMap() {
        return dataMap;
    }

    /**
     * @param dataMap the dataMap to set
     */
    
    public void setDataMap(Map<String, String> dataMap) {
        this.dataMap = dataMap;
    }


	/**
	 * @return the excelEntity
	 */
	
	public ExcelEntity getExcelEntity() {
		return excelEntity;
	}


	/**
	 * @param excelEntity the excelEntity to set
	 */
	
	public void setExcelEntity(ExcelEntity excelEntity) {
		this.excelEntity = excelEntity;
	}



}
