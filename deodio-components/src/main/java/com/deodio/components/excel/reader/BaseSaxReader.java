package com.deodio.components.excel.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.deodio.core.utils.ExcelUtils;

/**
 * Excel Base Reader
 * @author pactera
 *
 */
public abstract class BaseSaxReader extends DefaultHandler  implements IExcelSaxReader{
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaxReader.class);
    
    public static final  String CELL_DATA_DELIMITER = "_#_";
    
    public static final String  BLANK_STR = " ";
    /**
     * Index for sheet
     */
    protected int sheetIndex = -1;
    protected int sheetNum = 0;
    protected int startRow = 0;
    protected int endCol = -1;
    protected int curRow = 1;
    protected int curCol;
    protected int colCount = 0;
    protected int rowCount = 0;
    protected int maxRow = 0;
    protected int maxCol = 0;
    protected boolean isCellHasVal = false;
    protected boolean isEffectivecCell = false;
    protected boolean isReadEmptyRow = false;
    
    protected ExcelUtils excelUtils = new ExcelUtils();
    protected Map<Integer,String> merCellColumnMap = new HashMap<Integer,String>();
    
    
    
    /**
     * Cell String eg: B21
     */
    protected String curCell;
    /**
     * Shared String Table
     */
    protected SharedStringsTable sst;
    /**
     * Cell data type, the default type is string.
     */
    protected CellDataType nextDataType = CellDataType.SSTINDEX;
    protected final DataFormatter formatter = new DataFormatter();
    protected short formatIndex;
    protected String formatString;
    /**
     * Last contents
     */
    protected String lastContents;

    /**
     * String flag
     */
    protected boolean nextIsString;
    
    /**
     * T Element 
     */
    protected boolean isTElement;

    /**
     * Exception message, if it was null, means there is not any exception
     */
    protected String exceptionMessage;

    /**
     * cell styles
     */
    protected StylesTable stylesTable;
    
    
    
    /**
     * Fetch the sheet parser
     * 
     * @Title: fetchSheetParser
     * @param sst
     * @return XMLReader
     * @throws SAXException
     */
    public XMLReader fetchSheetParser(SharedStringsTable sst)
            throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        this.sst = sst;
        parser.setContentHandler(this);
        return parser;
    }
    
    
    /**
     * Handle the data from the spreadsheet that you specify
     * 
     * @param filename
     * @param sheetNum
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    @Override
    public boolean process(String filePath){
         try {
             OPCPackage pkg = OPCPackage.open(filePath);
             XSSFReader xssfReader = new XSSFReader(pkg);
             stylesTable = xssfReader.getStylesTable();
             SharedStringsTable sst = xssfReader.getSharedStringsTable();
             XMLReader parser = this.fetchSheetParser(sst);
             Iterator<InputStream> sheets = xssfReader.getSheetsData();
             while (sheets.hasNext()) {
                 curRow = 1;
                 sheetIndex++;
                 InputStream sheet = sheets.next();
                 if(sheetIndex == sheetNum || sheetNum == -1){
                     InputSource sheetSource = new InputSource(sheet);
                     parser.parse(sheetSource);
                 }
                 sheet.close();
             }
             processEnd();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }

         return true;
    }
    
    public void processEnd(){
    }
    
    
    /**
     * Processing data types
     * 
     * @param attributes
     */
    protected void setNextDataType(Attributes attributes) {
        nextDataType = CellDataType.NUMBER;
        formatIndex = -1;
        formatString = null;
        String cellType = attributes.getValue("t");
        String cellStyleStr = attributes.getValue("s");

        if ("b".equals(cellType)) {
            nextDataType = CellDataType.BOOL;
        } else if ("e".equals(cellType)) {
            nextDataType = CellDataType.ERROR;
        } else if ("inlineStr".equals(cellType)) {
            nextDataType = CellDataType.INLINESTR;
        } else if ("s".equals(cellType)) {
            nextDataType = CellDataType.SSTINDEX;
        } else if ("str".equals(cellType)) {
            nextDataType = CellDataType.FORMULA;
        }

        if (cellStyleStr != null) {
            int styleIndex = Integer.parseInt(cellStyleStr);
            XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
            formatIndex = style.getDataFormat();
            formatString = style.getDataFormatString();

            if ("m/d/yy".equals(formatString)) {
                nextDataType = CellDataType.DATE;
                formatString = "yyyy-MM-dd hh:mm:ss.SSS";
            }

            if (formatString == null) {
                nextDataType = CellDataType.NULL;
                formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
            }
        }
    }
    
    
    /**
     * The parsed data type processing
     * 
     * @param value
     *            The cell value (this is a string of numbers)
     * @param thisStr
     *            An empty string
     * @return String
     */
    protected String getDataValue(String value, String thisStr) {
        String resultStr = thisStr;
        try {
            switch (nextDataType) {
            // The order can not be this a few casual exchange, exchange is likely to lead to data errors
            case BOOL:
                char first = value.charAt(0);
                resultStr = first == '0' ? "FALSE" : "TRUE";
                break;
            case ERROR:
                resultStr = "\"ERROR:" + value.toString() + '"';
                break;
            case FORMULA:
                resultStr = '"' + value.toString() + '"';
                break;
            case INLINESTR:
                resultStr = new XSSFRichTextString(new String(value.getBytes(),"UTF-8")).toString();
                break;
            case SSTINDEX:
                    resultStr = new XSSFRichTextString(sst.getEntryAt(Integer.parseInt(value.toString()))).toString();
                break;
            case NUMBER:
                if (formatString != null) {
                    resultStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
                } else {
                    resultStr = value;
                }

                resultStr = resultStr.replace("_", "").trim();
                break;
            case DATE:
                // Special treatment on the date string
                resultStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).replace(" ", "T");
                break;
            default:
                resultStr = " ";
                break;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }

        return resultStr;
    }
    
    
    public boolean isSameMergeCellColumnVal(int curCol,String cellValue) {
        if(merCellColumnMap.containsKey(curCol)){
            if(cellValue.equals(merCellColumnMap.get(curCol))){
                return true;
            }else{
            	merCellColumnMap.put(curCol, cellValue);
                return false;
            }
        }else{
        	merCellColumnMap.put(curCol, cellValue);
            return false;
        }
        
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // Get the contents of the cell value
        lastContents += new String(ch, start, length);
    }

    
    protected boolean isDataRow() {
        return curRow >= startRow;
    }
    
    protected boolean isNotEndingCol(){
        return (this.curCol <= endCol && endCol >0)||endCol <=0;
    }
    
    
    
    /**
     * Data in the cell of possible data types
     */
    enum CellDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
    }


    public Map<Integer,String>getMerCellColumnMap() {
        return merCellColumnMap;
    }

    public void setMerCellColumnMap(Map<Integer,String> merCellColumnMap) {
    	this.merCellColumnMap = merCellColumnMap;
    }
    

    
    /**
     * @return the maxRow
     */
    
    public int getMaxRow() {
        return maxRow;
    }

    /**
     * @param maxRow the maxRow to set
     */
    
    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }


    /**
     * @return the maxCol
     */
    
    public int getMaxCol() {
        return maxCol;
    }

    /**
     * @param maxCol the maxCol to set
     */
    
    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getSheetNum() {
        return sheetNum;
    }

    public void setSheetNum(int sheetNum) {
        this.sheetNum = sheetNum;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }

    public int getCurRow() {
        return curRow;
    }

    public void setCurRow(int curRow) {
        this.curRow = curRow;
    }

    public int getCurCol() {
        return curCol;
    }

    public void setCurCol(int curCol) {
        this.curCol = curCol;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    public SharedStringsTable getSst() {
        return sst;
    }

    public void setSst(SharedStringsTable sst) {
        this.sst = sst;
    }

    public CellDataType getNextDataType() {
        return nextDataType;
    }

    public void setNextDataType(CellDataType nextDataType) {
        this.nextDataType = nextDataType;
    }

    public short getFormatIndex() {
        return formatIndex;
    }

    public void setFormatIndex(short formatIndex) {
        this.formatIndex = formatIndex;
    }

    public String getFormatString() {
        return formatString;
    }

    public void setFormatString(String formatString) {
        this.formatString = formatString;
    }

    public String getLastContents() {
        return lastContents;
    }

    public void setLastContents(String lastContents) {
        this.lastContents = lastContents;
    }

    public boolean isNextIsString() {
        return nextIsString;
    }

    public void setNextIsString(boolean nextIsString) {
        this.nextIsString = nextIsString;
    }

    public boolean isTElement() {
        return isTElement;
    }

    public void setTElement(boolean isTElement) {
        this.isTElement = isTElement;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public StylesTable getStylesTable() {
        return stylesTable;
    }

    public void setStylesTable(StylesTable stylesTable) {
        this.stylesTable = stylesTable;
    }

    public DataFormatter getFormatter() {
        return formatter;
    }

    public String getCurCell() {
        return curCell;
    }

    public void setCurCell(String curCell) {
        this.curCell = curCell;
    }


    public boolean isReadEmptyRow() {
        return isReadEmptyRow;
    }


    public void setReadEmptyRow(boolean isReadEmptyRow) {
        this.isReadEmptyRow = isReadEmptyRow;
    }
    
    

    
}
