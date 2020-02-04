package com.deodio.components.excel.reader;

import com.deodio.core.exception.ExcelException;


public interface IExcelDomReader extends IExcelReader{
    
    void readData() throws ExcelException;

}
