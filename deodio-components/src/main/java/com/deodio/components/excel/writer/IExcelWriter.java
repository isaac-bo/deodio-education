package com.deodio.components.excel.writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Excel Writer Interface
 * @author pactera
 *
 */
public interface IExcelWriter {
	/**
	 * Process method
	 * @Title: process
	 * @param filePath
	 * @return boolean
	 * @throws
	 */
    boolean process(String filePath);
    
    /**
     * Process method
     * @Title: process
     * @param HttpServletRequest
     * @return HttpServletResponse
     * @throws
     */
    boolean process(String filePath,HttpServletRequest request,HttpServletResponse response);
}
