package com.deodio.core.exception;

public class CommonException extends RuntimeException {
	/**
		 * 
		 */
	private static final long serialVersionUID = -4535356599717560623L;

	/**
	 * Creates a new GroupException object.
	 */
	public CommonException() {

		super();
	}

	/**
	 * @param message
	 */
	public CommonException(final String message) {

		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CommonException(final String message, final Throwable cause) {

		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public CommonException(final Throwable cause) {

		super(cause);
	}
}
