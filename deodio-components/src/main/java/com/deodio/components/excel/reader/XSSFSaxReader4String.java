package com.deodio.components.excel.reader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

public class XSSFSaxReader4String extends BaseSaxReader{
    
    private String rowCellDataStr = "";
    
    private List<String> rowCellDataList = Lists.newArrayList();
    
    private Set<Integer> disableColumnSet = new HashSet<Integer>();
    private Boolean disableColumn = Boolean.FALSE;

    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4String() {
    }
    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4String(int sheetNum) {
        super.sheetNum = sheetNum;
        
    }
    
    /**
     * Constructed function
     * 
     * 
     * @param excelEntity
     */
    public XSSFSaxReader4String(int startRow,int endCol) {
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
            isEffectivecCell = false;
            isCellHasVal = false;
            // Setting the cell type
            curCell = attributes.getValue("r");
            curCol = Integer.parseInt(StringUtils.substringBefore(excelUtils.cellConvert(curCell), "-"));
            if(isReadEmptyRow){
                int rowNo = Integer.parseInt(StringUtils.substringAfter(excelUtils.cellConvert(curCell), "-"));
                for(int r =0; r < rowNo - curRow -1;r++){
                    String prevVal = "";
                    for(int c=0;c<colCount;c++){
                        prevVal+= CELL_DATA_DELIMITER + BLANK_STR;
                    }
                    rowCellDataList.add(StringUtils.removeStart(prevVal, CELL_DATA_DELIMITER));
                }
                curRow = rowNo -1;
            }
            disableColumn= disableColumnSet.contains(curCol)?Boolean.TRUE:Boolean.FALSE;
           
            
            if(isDataRow() && isNotEndingCol()){
                colCount++;
                isEffectivecCell = true;
            }
            if(isEffectivecCell&&(curCol>colCount&&curCol!=1)){
                    int colCountTemp = colCount;
                    for(int i=0;i<curCol-colCountTemp;i++){
                        rowCellDataStr = rowCellDataStr + CELL_DATA_DELIMITER + BLANK_STR;
                        colCount++;
                    }
            }
            this.setNextDataType(attributes);
           
        } else if ("mergeCell".equals(name)&& maxRow == 0) {
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
            isTElement = false;
        } else if ("v".equals(name)) {
            if (isEffectivecCell) {
                // v => The cell value, if the cell is the string V tag value is the string in the SST index\\
                String value = BLANK_STR;
                if(!disableColumn){
                    value = this.getDataValue(lastContents.trim(), "");
                }
                rowCellDataStr = rowCellDataStr + CELL_DATA_DELIMITER + value;
                isCellHasVal = true;
            } 
           
        } else if ("c".equals(name)) {
            if (isEffectivecCell&& !isCellHasVal) {
                    rowCellDataStr = rowCellDataStr + CELL_DATA_DELIMITER + BLANK_STR;
            }
        } else {
            // If the tag name for the row, this note has been to the end of the line, call optRows () method
            if ("row".equals(name)) {
                    if(isDataRow()){
                        if(endCol > 0){
                            maxCol = endCol;
                        }else if(colCount >= maxCol){
                            maxCol = colCount;
                        }
                        for(int i=0;i<maxCol-colCount;i++){
                            rowCellDataStr = rowCellDataStr + CELL_DATA_DELIMITER + BLANK_STR;
                        }
                        rowCellDataStr = StringUtils.removeStart(rowCellDataStr, CELL_DATA_DELIMITER);
                        rowCellDataList.add(rowCellDataStr);
                     }
                    curRow++;
                    rowCellDataStr = "";
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
    
    
    public Set<Integer> getDisableColumnSet() {
        return disableColumnSet;
    }

    public void setDisableColumnSet(Set<Integer> disableColumnSet) {
        this.disableColumnSet = disableColumnSet;
    }
    
    
    
}
