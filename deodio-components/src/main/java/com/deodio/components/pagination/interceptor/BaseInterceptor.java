package com.deodio.components.pagination.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import com.deodio.components.pagination.annotation.Paging;
import com.deodio.components.pagination.dialect.Dialect;
import com.deodio.components.pagination.page.Page;
import com.deodio.components.pagination.page.Pagination;
import com.deodio.core.utils.ReflectionUtils;

import javax.xml.bind.PropertyException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 
 * @author botao
 *
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {
    /**
     * 日志
     */
    protected Log log = LogFactory.getLog(this.getClass());


    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";


    protected Dialect dialect;

    /**
     * Intercepted ID in the mapper id can match the regular
     */
    protected String sqlPattern = "";
    private static final long serialVersionUID = 4596430444388728543L;

    /**
     * Conversion and checking the parameters
     *
     * @param parameterObject 
     * @param pageVO          
     * @return VO
     * @throws NoSuchFieldException 
     */
    protected static Page convertParameter(Object parameterObject, Page pageVO) throws NoSuchFieldException {
        if (parameterObject instanceof Page) {
            pageVO = (Pagination) parameterObject;
        } else {
            Paging paging = parameterObject.getClass().getAnnotation(Paging.class);
            String field = paging.field();
            Field pageField = ReflectionUtils.getAccessibleField(parameterObject, field);
            if (pageField != null) {
                pageVO = (Pagination) ReflectionUtils.getFieldValue(parameterObject, field);
                if (null == pageVO){
                    throw new PersistenceException("Parameters can't be empty");
                }
                ReflectionUtils.setFieldValue(parameterObject, field, pageVO);
            } else {
                throw new NoSuchFieldException(parameterObject.getClass().getName() + "There is no paging parameter attributes!");
            }
        }
        return pageVO;
    }


    protected void initProperties(Properties p) {
        String dialectClass = p.getProperty("dialectClass");
        if (StringUtils.isEmpty(dialectClass)) {
            try {
                throw new PropertyException("Can't find database dialect");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        } else {
            Dialect dialect1 = (Dialect) ReflectionUtils.instance(dialectClass);
            if (dialect1 == null) {
                throw new NullPointerException("Ddatabase dialect instance wrong.");
            }
            dialect = dialect1;
        }

        sqlPattern = p.getProperty("sqlPattern");
        if (StringUtils.isEmpty(sqlPattern)) {
            try {
                throw new PropertyException("sqlPattern property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
    }
}
