
/**
 * @Title: DException.java
 * @Package com.deodio.elearning.exception
 * @author isaac
 * @date 2015-1-30
 * @version V1.0
*/
	
package com.deodio.elearning.exception;

import com.deodio.core.exception.BusinessException;


/**
 * @ClassName: DeodioException
 * @author isaac
 * @date 2015-1-30
 */

public class DeodioException extends BusinessException{

	
	
	/**
	 * 
	 * 
	 * @param msgKey
	*/
		
	public DeodioException(String msgKey) {
		super(msgKey);
	}
	
	public DeodioException(String msgKey, Object[] params) {
		super(msgKey,params);
	}

	public DeodioException(String msgKey, Throwable cause) {
		super(msgKey,cause);
	}

	/**
	 * @Fields serialVersionUID 
	*/
		
	private static final long serialVersionUID = 1L;

}
