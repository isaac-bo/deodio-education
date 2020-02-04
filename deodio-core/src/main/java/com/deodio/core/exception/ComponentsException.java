package com.deodio.core.exception;


/**
 * This is exception class, it will throws ExcelException when there are something wrong with importing/exporting excel file
 * @ClassName: ExcelException
 * @author isaac
 * @date 2014-8-2
 */
public class ComponentsException extends RuntimeException {

   

    /**
     * 
     */
    private static final long serialVersionUID = 6444946076240482265L;

    /**
     * Creates a new GroupException object.
     */
    public ComponentsException() {

        super();
    }

    /**
     * @param message
     */
    public ComponentsException(final String message) {

        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ComponentsException(final String message, final Throwable cause) {

        super(message, cause);
    }

    /**
     * @param cause
     */
    public ComponentsException(final Throwable cause) {

        super(cause);
    }
}