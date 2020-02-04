package com.deodio.components.excel.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * This is color entity for excel operation instants. 
 * Such as the A1:1 cell has RGB:#FFFFFF color.
 * 
 * @ClassName: ColorCellEntity
 * @Description: This is color entity.
 * @author isaac
 * @date 2014-8-1
 */
@XStreamAlias("ColorCell")
public class ColorCellEntity {
	@XStreamAlias("CellNum")
	private String cellNum; 
	
	@XStreamAlias("RGB")
	private String rgb;

	/**
	 * 
	 * @Title: getCellNum
	 * @Description: getting method
	 * @return String
	 */
	public String getCellNum() {
		return cellNum;
	}

	
	/**
	 * 
	 * @Title: setCellNum
	 * @Description: Setting method
	 * @param  cellNum
	 */
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	/**
	 * 
	 * @Title: getRgb
	 * @Description: Getting method
	 * @return String
	 */
	public String getRgb() {
		return rgb;
	}

	/**
	 * 
	 * @Title: setRgb
	 * @Description: Setting method
	 * @param rgb
	 */
	public void setRgb(String rgb) {
		this.rgb = rgb;
	}
	
	
	
}
