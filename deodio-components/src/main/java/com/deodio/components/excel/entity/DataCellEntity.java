package com.deodio.components.excel.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * This is data cell entity for excel operation instants. 
 * 
 * @ClassName: DataCellEntity
 * @Description: This is data cell entity for excel operation instants. 
 * @author isaac
 * @date 2014-8-2
 */
@XStreamAlias("DataCell")
public class DataCellEntity {
	@XStreamAlias("CellNum")
	private String cellNum;
	
	@XStreamAlias("CellValue")
	private String cellValue;
	
	@XStreamAlias("CellType")
	private Integer cellType;

	/**
	 * @return the cellNum
	 */
	
	public String getCellNum() {
		return cellNum;
	}

	/**
	 * @param cellNum the cellNum to set
	 */
	
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	/**
	 * @return the cellValue
	 */
	
	public String getCellValue() {
		return cellValue;
	}

	/**
	 * @param cellValue the cellValue to set
	 */
	
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	/**
	 * @return the cellType
	 */
	
	public Integer getCellType() {
		return cellType;
	}

	/**
	 * @param cellType the cellType to set
	 */
	
	public void setCellType(Integer cellType) {
		this.cellType = cellType;
	}

	
	
}
