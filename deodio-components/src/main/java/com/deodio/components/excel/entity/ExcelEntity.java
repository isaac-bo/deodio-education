package com.deodio.components.excel.entity;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * This is import entity for excel operation instants. 
 * 
 * @ClassName: ImportEntity
 * @Description: This is import entity for excel operation instants. 
 * @author isaac
 * @date 2014-8-2
 */
@XStreamAlias("Import")
public class ExcelEntity {
	
	@XStreamAlias("Macros")
	private List<MacroEntity> macros;
	
	@XStreamAlias("DataStartRow")
	private int dataStartRow;
	
	@XStreamAlias("DataEndCol")
	private int dataEndCol;

	@XStreamAlias("MaxRow")
	private int maxRow;
	
	@XStreamAlias("MsaxCol")
	private int maxCol;
	
	@XStreamAlias("IsParseHeader")
	@XStreamConverter(value=BooleanConverter.class, booleans={false}, strings={"true", "false"})
	private boolean isParseHeader;
	
	@XStreamAlias("MergeCells")
	private List<MergeCellEntity> mergeCells = new ArrayList<MergeCellEntity>();
	
	@XStreamAlias("DataCells")
	private List<DataCellEntity> dataCells = new ArrayList<DataCellEntity>();
	
	@XStreamAlias("ColorCells")
	private List<ColorCellEntity> colorCells = new ArrayList<ColorCellEntity>();
	
	@XStreamAlias("ColumnTitles")
	private List<ColumnTitleEntity> columnTitles = new ArrayList<ColumnTitleEntity>();

	/**
	 * @return the macros
	 */
	
	public List<MacroEntity> getMacros() {
		return macros;
	}

	/**
	 * @param macros the macros to set
	 */
	
	public void setMacros(List<MacroEntity> macros) {
		this.macros = macros;
	}

	/**
	 * @return the dataStartRow
	 */
	
	public int getDataStartRow() {
		return dataStartRow;
	}

	/**
	 * @param dataStartRow the dataStartRow to set
	 */
	
	public void setDataStartRow(int dataStartRow) {
		this.dataStartRow = dataStartRow;
	}

	/**
	 * @return the dataEndCol
	 */
	
	public int getDataEndCol() {
		return dataEndCol;
	}

	/**
	 * @param dataEndCol the dataEndCol to set
	 */
	
	public void setDataEndCol(int dataEndCol) {
		this.dataEndCol = dataEndCol;
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

	/**
	 * @return the isParseHeader
	 */
	
	public boolean isIsParseHeader() {
		return isParseHeader;
	}

	/**
	 * @param isParseHeader the isParseHeader to set
	 */
	
	public void setIsParseHeader(boolean isParseHeader) {
		this.isParseHeader = isParseHeader;
	}

	/**
	 * @return the mergeCells
	 */
	
	public List<MergeCellEntity> getMergeCells() {
		return mergeCells;
	}

	/**
	 * @param mergeCells the mergeCells to set
	 */
	
	public void setMergeCells(List<MergeCellEntity> mergeCells) {
		this.mergeCells = mergeCells;
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
	 * @return the colorCells
	 */
	
	public List<ColorCellEntity> getColorCells() {
		return colorCells;
	}

	/**
	 * @param colorCells the colorCells to set
	 */
	
	public void setColorCells(List<ColorCellEntity> colorCells) {
		this.colorCells = colorCells;
	}

	/**
	 * @return the columnTitles
	 */
	
	public List<ColumnTitleEntity> getColumnTitles() {
		return columnTitles;
	}

	/**
	 * @param columnTitles the columnTitles to set
	 */
	
	public void setColumnTitles(List<ColumnTitleEntity> columnTitles) {
		this.columnTitles = columnTitles;
	}

	
	

}
