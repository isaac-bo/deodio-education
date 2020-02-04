package com.deodio.components.excel.entity;

import org.apache.commons.lang3.StringUtils;

import com.deodio.core.utils.ExcelUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * This is merge cell entity for excel operation instants. 
 * 
 * @ClassName: MergeCellEntity
 * @Description: This is merge cell entity for excel operation instants. 
 * @author isaac
 * @date 2014-8-2
 */
@XStreamAlias("MergeCell")
public class MergeCellEntity {
	
	public MergeCellEntity(){
	}
	
	public MergeCellEntity(String cellStr){
		calcParms(cellStr);
	}
	
	@XStreamAlias("StartCellNum")
	private String startCellNum;
	
	@XStreamAlias("EndCellNum")
	private String endCellNum;
	
	@XStreamAlias("RowGap")
	private int rowGap;
	
	@XStreamAlias("CellGap")
	private int cellGap;
	
	@XStreamAlias("StartCell")
	private String startCell;
	
	@XStreamAlias("EndCell")
	private String endCell;
	
	private ExcelUtils  excelUtils = new ExcelUtils();
	
	/**
	 * @return the startCellNum
	 */
	
	public String getStartCellNum() {
		return startCellNum;
	}

	/**
	 * @param startCellNum the startCellNum to set
	 */
	
	public void setStartCellNum(String startCellNum) {
		this.startCellNum = startCellNum;
	}

	/**
	 * @return the endCellNum
	 */
	
	public String getEndCellNum() {
		return endCellNum;
	}

	/**
	 * @param endCellNum the endCellNum to set
	 */
	
	public void setEndCellNum(String endCellNum) {
		this.endCellNum = endCellNum;
	}

	/**
	 * @return the rowGap
	 */
	
	public int getRowGap() {
		return rowGap;
	}

	/**
	 * @param rowGap the rowGap to set
	 */
	
	public void setRowGap(int rowGap) {
		this.rowGap = rowGap;
	}

	/**
	 * @return the cellGap
	 */
	
	public int getCellGap() {
		return cellGap;
	}

	/**
	 * @param cellGap the cellGap to set
	 */
	
	public void setCellGap(int cellGap) {
		this.cellGap = cellGap;
	}

	/**
	 * @return the startCell
	 */
	
	public String getStartCell() {
		return startCell;
	}

	/**
	 * @param startCell the startCell to set
	 */
	
	public void setStartCell(String startCell) {
		this.startCell = startCell;
	}

	/**
	 * @return the endCell
	 */
	
	public String getEndCell() {
		return endCell;
	}

	/**
	 * @param endCell the endCell to set
	 */
	
	public void setEndCell(String endCell) {
		this.endCell = endCell;
	}

	/**
	 * @return the excelUtils
	 */
	
	public ExcelUtils getExcelUtils() {
		return excelUtils;
	}

	/**
	 * @param excelUtils the excelUtils to set
	 */
	
	public void setExcelUtils(ExcelUtils excelUtils) {
		this.excelUtils = excelUtils;
	}

	/**
	 * Calculate the the region for each cell, which has been merged
	 * 		Such as A12:AB32. It is a merge cell. this method will return the A12 - AB32.
	 * 
	 * @Title: calcParms
	 * @param cell
	 * @throws
	 */
	private void calcParms(String cell){
		startCell = StringUtils.split(cell, ":")[0];
		endCell = StringUtils.split(cell, ":")[1];
		startCellNum = excelUtils.cellConvert(startCell);
		endCellNum = excelUtils.cellConvert(endCell);
		cellGap	= Integer.parseInt(StringUtils.substringBefore(endCellNum, "-")) - Integer.parseInt(StringUtils.substringBefore(startCellNum, "-"));
		rowGap = Integer.parseInt(StringUtils.substringAfter(endCellNum, "-")) - Integer.parseInt(StringUtils.substringAfter(startCellNum, "-"));
	}


	@Override
	public String toString() {
		return "MergeCell [startCellNum=" + startCellNum + ", endCellNum="
				+ endCellNum + ", rowGap=" + rowGap + ", cellGap=" + cellGap
				+ ", startCell=" + startCell + ", endCell=" + endCell + "]";
	}
	
	

}
