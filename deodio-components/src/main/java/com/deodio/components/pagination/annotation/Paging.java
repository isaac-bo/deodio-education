package com.deodio.components.pagination.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * 
 * @author botao
 *
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Paging {
    /**
     * 
     * @return
     */
    String field() default "page";

}
