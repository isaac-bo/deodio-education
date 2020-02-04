package com.deodio.core.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.slf4j.LoggerFactory;

public class Logger {

    public void debug(Class<?> clazz, String methods, String message) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);

        logger.debug("Exceute Time: {}, Method: {}, Message: {}", new Date(), methods, message);
    }

    public void debug(Class<?> clazz, String methods, Exception ex) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);
        StringWriter sw = setPrintStackTrace(ex);
        logger.debug("Exceute Time: {}, Method: {}, Message: {}", new Date(), methods, sw.toString());
    }

    public void info(Class<?> clazz, String methods, String message) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);
        logger.info("Exceute Time: {}, Method: {}, Message: {}", new Date(), methods, message);
    }

    public void info(Class<?> clazz, String methods, Exception ex) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);
        StringWriter sw = setPrintStackTrace(ex);
        logger.info("Exceute Time: {}, Method: {}, Message: {}", new Date(), methods, sw.toString());
    }

    public void error(Class<?> clazz, String methods, String message) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);
        logger.error("Exceute Time: {}, Method: {}, Message: {}", new Date(), methods, message);
    }

    public void error(Class<?> clazz, String methods, Exception ex) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);
        StringWriter sw = setPrintStackTrace(ex);
        logger.error("Exceute Time: {}, Method: {}, Message: {}", new Date(), methods, sw.toString());
    }

    private StringWriter setPrintStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw, true));
        return sw;
    }

}
