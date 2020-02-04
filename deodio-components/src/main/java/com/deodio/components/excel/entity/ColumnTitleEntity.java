package com.deodio.components.excel.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * This is column title entity for excel operation instants. 
 * 
 * @ClassName: ColumnTitleEntity
 * @Description: This is column title entity for excel operation instants. 
 * @author isaac
 * @date 2014-8-2
 */
@XStreamAlias("ColumnTitle")
public class ColumnTitleEntity {
	@XStreamAlias("Title")
	private String title;
	
	@XStreamAlias("CellNum")
	private String cellNum;
	
	@XStreamAlias("CellGap")
	private int cellGap;
	
	@XStreamAlias("RowGap")
	private int rowGap;
	
	@XStreamAlias("ColumnOrder")
	private int columnOrder;

	/**
	 * 
	 * @Title: getTitle
	 * @Description: Getting method
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @Title: setTitle
	 * @Description: Setting method
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @Title: getCellNum
	 * @Description: Getting method
	 * @return String
	 */
	public String getCellNum() {
		return cellNum;
	}

	/**
	 * 
	 * @Title: setCellNum
	 * @Description: Setting method
	 * @param cellNum
	 */
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}


	/**
	 * 
	 * @Title: getCellNum
	 * @Description: Getting method
	 * @return String
	 */
	public int getColumnOrder() {
		return columnOrder;
	}

	
	/**
	 * 
	 * @Title: setColumnOrder
	 * @Description: Setting method
	 * @param columnOrder
	 */
	public void setColumnOrder(int columnOrder) {
		this.columnOrder = columnOrder;
	}


	/**
	 * 
	 * @Title: getCellGap
	 * @Description: Getting method
	 * @return int
	 */
	public int getCellGap() {
		return cellGap;
	}

	/**
	 * 
	 * @Title: setCellGap
	 * @Description: Setting method
	 * @param cellGap
	 */
	public void setCellGap(int cellGap) {
		this.cellGap = cellGap;
	}


	/**
	 * 
	 * @Title: getRowGap
	 * @Description: Getting method
	 * @return int
	 */
	public int getRowGap() {
		return rowGap;
	}

	/**
	 * 
	 * @Title: setRowGap
	 * @Description: Setting method
	 * @param rowGap
	 */
	public void setRowGap(int rowGap) {
		this.rowGap = rowGap;
	}
	
	
	

}
