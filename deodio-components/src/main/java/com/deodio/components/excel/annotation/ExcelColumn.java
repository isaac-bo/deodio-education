package com.deodio.components.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel mapping annotation
 * @author sunj
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ExcelColumn {
	public String title() default "";
	public int sortNo() default 0;
	public String type() default "";
	public String handler()default "";
}
