package com.deodio.components.excel.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * This is import column entity for excel operation instants. 
 * 
 * @ClassName: ImportColumnEntity
 * @Description: This is import column entity for excel operation instants. 
 * @author isaac
 * @date 2014-8-2
 */
@XStreamAlias("Column")
public class ImportColumnEntity {
	
    /*Excel Column Title*/
    @XStreamAlias("Title")
    private String title;           
    
    /*Mapping class attr*/
    @XStreamAlias("Field")
    private String field;           
    
    /*Sum Cell Mapping class attr*/
    @XStreamAlias("SumField")
    private String sumField;        
    
    /*Excel Col Display Sort Number*/
    @XStreamAlias("SortNo")
    private int sortNo;             
    
    /*Cell default value*/
    @XStreamAlias("DefaultVal")
    private String defaultVal;      
    
    
    /*default method*/
    @XStreamAlias("HandleMethod")
    private String handleMethod;    


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getField() {
        return field;
    }


    public void setField(String field) {
        this.field = field;
    }


    public String getSumField() {
        return sumField;
    }


    public void setSumField(String sumField) {
        this.sumField = sumField;
    }


    public int getSortNo() {
        return sortNo;
    }


    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }


    public String getDefaultVal() {
        return defaultVal;
    }


    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }


    public String getHandleMethod() {
        return handleMethod;
    }


    public void setHandleMethod(String handleMethod) {
        this.handleMethod = handleMethod;
    }
	
	

	

}
