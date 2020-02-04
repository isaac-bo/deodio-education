package com.deodio.components.excel.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * This is Export column entity for excel operation instants. 
 * 
 * @ClassName: ExportColumnEntity
 * @Description: This is import column entity for excel operation instants. 
 * @author sunj
 * @date 2014-8-8
 */
@XStreamAlias("Column")
public class ExportColumnEntity implements Comparable<ExportColumnEntity>{
	
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
	
	private String value;
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSumField() {
		return sumField;
	}
	public void setSumField(String sumField) {
		this.sumField = sumField;
	}
	
	
	@Override
	public int compareTo(ExportColumnEntity o) {
		return this.getSortNo()-o.getSortNo();
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((defaultVal == null) ? 0 : defaultVal.hashCode());
        result = prime * result + ((field == null) ? 0 : field.hashCode());
        result = prime * result + ((handleMethod == null) ? 0 : handleMethod.hashCode());
        result = prime * result + sortNo;
        result = prime * result + ((sumField == null) ? 0 : sumField.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        
        ExportColumnEntity other = (ExportColumnEntity) obj;
        if (defaultVal == null) {
            if (other.defaultVal != null){
                return false;
            }
        } else if (!defaultVal.equals(other.defaultVal)){
            return false;
        }
        if (field == null) {
            if (other.field != null){
                return false;
            }
        } else if (!field.equals(other.field)){
            return false;
        }
        if (handleMethod == null) {
            if (other.handleMethod != null){
                return false;
            }
        } else if (!handleMethod.equals(other.handleMethod)){
            return false;
        }
        if (sortNo != other.sortNo){
            return false;
        }
        if (sumField == null) {
            if (other.sumField != null){
                return false;
            }
        } else if (!sumField.equals(other.sumField)){
            return false;
        }
        if (title == null) {
            if (other.title != null){
                return false;
            }
        } else if (!title.equals(other.title)){
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
