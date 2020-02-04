package com.deodio.components.excel.constants;

/**
 * This is constants class for all the excel operation in HDS project.
 * 		
 * 1. For importing function : System need to import the excel file. It will use the import constants to find out the configuration file.
 * 		
 * 		For example: 
 * 				PRODUCT_PLAN_IMPORT_CONFIG_FILE =  "/excel/import/productplan.xml"; System will read PRODUCT_PLAN_IMPORT_CONFIG_FILE firstly.
 * if system want to import product plan. From this xml file, system can understand the relate information.
 * 		
 * 		For example:
 * 
 * 				<Macros>
 *					<Macro CalcExpr="${X} = ${AT} + ${AU} + ${AV}"/>
 *					<Macro CalcExpr="${Y} = ${BI}"/>
 *				</Macros>
 *					
 * 
 * 
 * 2. For exporting function : System need to export the excel file, it will use the export constants to find out the configuration file.
 *  	
 *  	For example: 
 * 				PORDUCT_PLAN_EXPORT_CONFIG_FILE =  "/excel/export/productplan.xml"; System will read PRODUCT_PLAN_IMPORT_CONFIG_FILE firstly.
 * if system want to import product plan. From this xml file, system can understand the relate information.
 * 				
 *
 *				<Export>
 *					<Columns>
 *						<Column>
 *							<Title>Vehicle model</Title>
 *							<ColumnIndex>1</ColumnIndex>
 *							<TargetIndex>1</TargetIndex>
 *						</Column>
 *					<Columns>
 *				<Export>
 * 
 * 
 * @ClassName: ExcelConstants
 * @Description: This is constants class.
 * @author isaac
 * @date 2014-8-1
 */
public class ExcelConstants {
    
    
    
	
	private ExcelConstants() {
    }
	/** 
	 * Excel Import Configuration XML for Effective List
	 */
	public static final String CCC_EFFECTIVE_LIST_IMPORT_CONFIG_FILE="/WEB-INF/template/excel/import/im_effective_3c_list.xml";
}
