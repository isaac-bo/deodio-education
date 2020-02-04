package com.deodio.components.excel.entity;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * This is export entity for excel operation instants. 
 * 
 * @ClassName: ExportEntity
 * @Description: This is export entity for excel operation instants. 
 * @author sunj
 * @date 2014-8-8
 */
@XStreamAlias("Import")
public class ImportEntity {
	
    @XStreamAlias("Columns")
    private List<ImportColumnEntity> columns;

    public List<ImportColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ImportColumnEntity> columns) {
        this.columns = columns;
    }
	
}
