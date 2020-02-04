
/**
 * @Title: BusinessException.java
 * @Package com.deodio.core.exception
 * @author isaac
 * @date 2015-1-30
 * @version V1.0
*/
	
package com.deodio.core.exception;

import org.apache.commons.lang.StringUtils;

import com.deodio.core.utils.MessageUtils;


/**
 * @ClassName: BusinessException
 * @author isaac
 * @date 2015-1-30
 */

public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = -6983227709594164508L;
	private final String errorCode;
	private Object[] params;


	public BusinessException(String msgKey) {
		super(msgKey);
		this.errorCode = msgKey;
	}

	public BusinessException(String msgKey, Object[] params) {
		super(msgKey);
		this.errorCode = msgKey;
		this.params = params;
	}

	public BusinessException(String msgKey, Throwable cause) {
		super(msgKey, cause);
		this.errorCode = msgKey;
	}


	@Override
	public String getMessage() {
		if (StringUtils.isNotBlank(this.errorCode)) {
			if (params != null && params.length > 0) {
				return MessageUtils.getMessage(this.errorCode, params);
			} else {
				return MessageUtils.getMessage(this.errorCode);
			}
		}
		return super.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}
}
