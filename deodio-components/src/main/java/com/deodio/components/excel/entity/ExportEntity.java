package com.deodio.components.excel.entity;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * This is export entity for excel operation instants. 
 * 
 * @ClassName: ExportEntity
 * @Description: This is export entity for excel operation instants. 
 * @author isaac
 * @date 2014-8-2
 */
@XStreamAlias("Export")
public class ExportEntity {
	
    @XStreamAlias("Columns")
    private List<ExportColumnEntity> columns;

    public List<ExportColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ExportColumnEntity> columns) {
        this.columns = columns;
    }
	
}
