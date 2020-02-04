package com.deodio.core.utils;


/**
 * The Common Model For Controller Return
 * @ClassName: AjaxResultModel
 * @author isaac
 * @date 2014-8-2
 */
public class AjaxResultModel {
    public static final int FAIL = 0;
    public static final int SUCCESS = 1;
    public static final int NO_LOGIN = 2;
    public static final int NO_AUTH = 3;
    private int status;
    private String urlLink;
    private String message;
    private String exceptionMessage;
    private Object data;

   
    /**
	 * @return the status
	 */
	
	public int getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	
	public void setStatus(int status) {
		this.status = status;
	}


	/**
	 * @return the urlLink
	 */
	
	public String getUrlLink() {
		return urlLink;
	}


	/**
	 * @param urlLink the urlLink to set
	 */
	
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}


	/**
	 * @return the message
	 */
	
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the exceptionMessage
	 */
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}


	/**
	 * @param exceptionMessage the exceptionMessage to set
	 */
	
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}


	/**
	 * @return the data
	 */
	
	public Object getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	
	public void setData(Object data) {
		this.data = data;
	}

}
