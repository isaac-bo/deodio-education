package com.deodio.components.excel.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * This is macro entity for excel operation instants. 
 * 
 * @ClassName: MacroEntity
 * @Description: This is macro entity for excel operation instants. 
 * @author isaac
 * @date 2014-8-2
 */
@XStreamAlias("Macro")
public class MacroEntity {
	
	@XStreamAlias("CalcExpr")
	@XStreamAsAttribute
	public String calcExpr ;
	
	@XStreamAlias("StartRow")
	@XStreamAsAttribute
	public Integer startRow;
	
	@XStreamAlias("EndRow")
	@XStreamAsAttribute
	public Integer endRow;
	
	@XStreamAlias("EndRow")
	@XStreamConverter(value=BooleanConverter.class, booleans={false}, strings={"true", "false"})
	public boolean writable;

	/**
	 * @return the calcExpr
	 */
	
	public String getCalcExpr() {
		return calcExpr;
	}

	/**
	 * @param calcExpr the calcExpr to set
	 */
	
	public void setCalcExpr(String calcExpr) {
		this.calcExpr = calcExpr;
	}

	/**
	 * @return the startRow
	 */
	
	public Integer getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the endRow
	 */
	
	public Integer getEndRow() {
		return endRow;
	}

	/**
	 * @param endRow the endRow to set
	 */
	
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	/**
	 * @return the writable
	 */
	
	public boolean isWritable() {
		return writable;
	}

	/**
	 * @param writable the writable to set
	 */
	
	public void setWritable(boolean writable) {
		this.writable = writable;
	}
	
	
}
