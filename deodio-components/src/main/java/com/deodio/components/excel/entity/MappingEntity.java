
/**
 * @Title: MappingEntity.java
 * @Package com.volkswagen.hds.components.mapping
 * @author isaac
 * @date 2014-8-15
 * @version V1.0
*/
	
package com.deodio.components.excel.entity;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * @ClassName: MappingEntity
 * @author isaac
 * @date 2014-8-15
 */

@XStreamAlias("Mapping")
public class MappingEntity {

	  @XStreamAlias("Columns")
	  private List<MappingColumnEntity> columns;

	/**
	 * @return the columns
	 */
	
	public List<MappingColumnEntity> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	
	public void setColumns(List<MappingColumnEntity> columns) {
		this.columns = columns;
	}

}
