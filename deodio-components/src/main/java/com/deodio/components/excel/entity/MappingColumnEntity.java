
/**
 * @Title: MappingColumnEntity.java
 * @Package com.volkswagen.hds.components.mapping
 * @author isaac
 * @date 2014-8-15
 * @version V1.0
*/
	
package com.deodio.components.excel.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * @ClassName: MappingColumnEntity
 * @author isaac
 * @date 2014-8-15
 */
@XStreamAlias("Column")
public class MappingColumnEntity {

	
    @XStreamAlias("actual")
    private String actual;           
    
    @XStreamAlias("original")
    private String original;
    
    @XStreamAlias("type")
    private String type;
    
    @XStreamAlias("ftcol")
    private String ftcol;
    
	@XStreamAlias("designation")
    private String designation;
	
	@XStreamAlias("designationInEn")
    private String designationInEn;
	
	@XStreamAlias("designationInCh")
    private String designationInCh;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDesignationInEn() {
		return designationInEn;
	}

	public void setDesignationInEn(String designationInEn) {
		this.designationInEn = designationInEn;
	}

	public String getDesignationInCh() {
		return designationInCh;
	}

	public void setDesignationInCh(String designationInCh) {
		this.designationInCh = designationInCh;
	}
    
    /**
	 * @return the actual
	 */
	
	public String getActual() {
		return actual;
	}

	/**
	 * @param actual the actual to set
	 */
	
	public void setActual(String actual) {
		this.actual = actual;
	}

	/**
	 * @return the description
	 */
	
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}

	@XStreamAlias("description")
    private String description;

	/**
	 * @return the original
	 */
	
	public String getOriginal() {
		return original;
	}

	/**
	 * @param original the original to set
	 */
	
	public void setOriginal(String original) {
		this.original = original;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFtcol() {
		return ftcol;
	}

	public void setFtcol(String ftcol) {
		this.ftcol = ftcol;
	}   
	
	
}
