package com.deodio.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is utils fro servlet, it extends utils
 * @ClassName: ServletUtil
 * @author isaac
 * @date 2014-8-2
 */
public final class ServletUtil extends Utils {
    private final static Logger LOGGER= LoggerFactory.getLogger(MathUtils.class);
    
    /** -- Content Type Definiation --*/
    public static final String EXCEL_TYPE = "application/vnd.ms-excel";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String TEXT_TYPE = "text/plain";
    public static final String PDF_TYPE = "application/pdf";


    private ServletUtil() {
    }

    /**
     * Set the type
     * 
     * @param response
     * @param contentType
     */
    public static void setContentType(HttpServletResponse response, String contentType) {
        response.setContentType(contentType + ";charset=UTF-8");
    }

    /**
     * The set up client cache expiration time of the Header.
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        // Http 1.0 header
        response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
        // Http 1.1 header
        response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
    }

    /**
     * Set against the client cache Header.
     */
    public static void setDisableCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader("Expires", 1L);
        response.addHeader("Pragma", "no-cache");
        // Http 1.1 header
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
    }

    /**
     * Set LastModified Header.
     */
    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
        response.setDateHeader("Last-Modified", lastModifiedDate);
    }

    /**
     * Set Etag Header.
     */
    public static void setEtag(HttpServletResponse response, String etag) {
        response.setHeader("ETag", etag);
    }

    /**
     * According to the browser If-Modified-Since Header, computing whether a file has been modified.
     * 
     * If no, checkIfModify returns false, 304 not modify status.
     * 
     * @param lastModified
     *            The content of the last modification time.
     */
    public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
            long lastModified) {
        long ifModifiedSince = request.getDateHeader("If-Modified-Since");
        if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return false;
        }
        return true;
    }

    /**
     * According to the If-None-Match Header browser, Etag is invalid.
     * 
     * If the Etag is valid, checkIfNoneMatch returns false, 304 not modify status.
     * 
     * @param etag
     *           The contents of the ETag.
     */
    public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
        String headerValue = request.getHeader("If-None-Match");
        if (headerValue != null) {
            boolean conditionSatisfied = false;
            if (!"*".equals(headerValue)) {
                StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

                while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(etag)) {
                        conditionSatisfied = true;
                    }
                }
            } else {
                conditionSatisfied = true;
            }

            if (conditionSatisfied) {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                response.setHeader("ETag", etag);
                return false;
            }
        }
        return true;
    }

    /**
     * Set the browser pop-up download dialog Header.
     * 
     * @param fileName
     *            After downloading the file name.
     */
    public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        String encodedfileName;
        try {
            // Chinese file name support
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                encodedfileName = URLEncoder.encode(fileName, "UTF-8");
            } else if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0
                    || request.getHeader("User-Agent").toLowerCase().indexOf("opera") > 0) {
                encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                encodedfileName = URLEncoder.encode(fileName, "UTF-8");
            }

            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    /**
     * Made with the same prefix in Request Parameters.
     * 
     * Returns the Parameter name has been removed prefixes.
     */
    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefixStr) {
        String prefix = prefixStr;
        Validate.notNull(request, "Request must not be null");
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {
                    continue;
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

    /**
     * Get real IP
     * 
     * @param request
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    
	
    /**
     * Write excel file
     * @Title: writeFile
     * @param request
     * @param response
     * @param fileName
     * @param workbook
     * @throws IOException
     * @return void
     */
    public static void writeExcelFile(HttpServletRequest request, HttpServletResponse response, String fileName,HSSFWorkbook workbook) throws IOException{
		ServletUtil.setContentType(response, ServletUtil.EXCEL_TYPE);
		ServletUtil.setFileDownloadHeader(request, response, fileName);
		ServletOutputStream fileOut = response.getOutputStream();
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
    }
    
    /**
     * Write excel file
     * @Title: writeFile
     * @param request
     * @param response
     * @param fileName
     * @param workbook
     * @throws IOException
     * @return void
     */
    public static void writeExcelFile(HttpServletRequest request, HttpServletResponse response, String fileName,XSSFWorkbook workbook) throws IOException{
		ServletUtil.setContentType(response, ServletUtil.EXCEL_TYPE);
		ServletUtil.setFileDownloadHeader(request, response, fileName);
		ServletOutputStream fileOut = response.getOutputStream();
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
    }
    /**
     * Write excel file
     * @Title: writeExcelFilePath
     * @param request
     * @param response
     * @param filePath
     * @param workbook
     * @return void
     * @throws IOException 
     */
    public static void writeExcelFilePath(HttpServletRequest request, HttpServletResponse response, String filePath,XSSFWorkbook workbook) throws IOException{
    	writeExcelFile( request,  response, FileUtils.getName(filePath, true), workbook);
		FileOutputStream fileOut = null;
		
	       try {
			final File file = new File(filePath);
			   FileUtils.createDir(file.getParentFile().getPath());
			   fileOut = new FileOutputStream(filePath);
			   workbook.write(fileOut);
			   fileOut.flush();
			   fileOut.close();
		} catch (Exception e) {
		    LOGGER.error(e.getMessage(),e);
		} finally{
            IOUtils.closeQuietly(fileOut);
        }
    }
    
}
