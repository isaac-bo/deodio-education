package com.deodio.core.tags;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Select标签
 * @author sj
 *
 */
public class SelectTag extends TagSupport  {
	private final Logger log = LoggerFactory.getLogger(SelectTag.class);

	private static final long serialVersionUID = 2609205643844247959L;
	private String id;			
	private String name;
	private String headerKey;
	private String headerValue;
	private String classStyle;
	private String cssStyle;
	
	private String value;
	private Collection<?> collection;
	private String colKey;
	private String colValue;

	
	public int doStartTag() throws JspException {
		JspWriter writer=super.pageContext.getOut();
		StringBuffer sb=new StringBuffer();
		sb.append("<select ");
		if(StringUtils.isNotBlank(id)){
			sb.append("id=\"").append(id).append("\" ");
		}
		if(StringUtils.isNotBlank(name)){
			sb.append("name=\"").append(name).append("\" ");
		}
		if(StringUtils.isNotBlank(cssStyle)){
			sb.append("style=\"").append(cssStyle).append("\" ");
		}
		if(StringUtils.isNotBlank(classStyle)){
			sb.append("class=\"").append(classStyle).append("\" ");
		}
		sb.append(">");

		if(StringUtils.isNotBlank(headerKey)||StringUtils.isNotBlank(headerValue)){
			  sb.append("<option value=\"").append(headerKey).append("\">").append(headerValue).append("</option>");
		}
		
		try {
			if(null!=collection&&collection.size()>0){
				Iterator<?> iterator=collection.iterator();
				while(iterator.hasNext()){
					Object object=iterator.next();
					Method keyMethod=object.getClass().getMethod("get"+StringUtils.capitalize(colKey));
	                Method valyeMethod=object.getClass().getMethod("get"+StringUtils.capitalize(colValue));
	                String optionKey=String.valueOf(keyMethod.invoke(object));
	                String optionValue=String.valueOf(valyeMethod.invoke(object));
	                sb.append("<option value=\"").append(optionKey).append("\"")
	                  .append(StringUtils.isNotBlank(value)&&StringUtils.equals(optionKey, value)?"selected=\"true\"":"")
	                  .append(">").append(optionValue).append("</option>");
				}
			}
			sb.append("</select>");
			writer.print(sb.toString());
		} catch (Exception e) {
			log.error("SelectTag Render erroer",e);
		}  
		
		return SKIP_BODY;
	}

	
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getHeaderKey() {
		return headerKey;
	}



	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}



	public String getHeaderValue() {
		return headerValue;
	}



	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}


	public Collection<?> getCollection() {
		return collection;
	}





	public void setCollection(Collection<?> collection) {
		this.collection = collection;
	}





	public String getColKey() {
		return colKey;
	}



	public void setColKey(String colKey) {
		this.colKey = colKey;
	}



	public String getColValue() {
		return colValue;
	}



	public void setColValue(String colValue) {
		this.colValue = colValue;
	}





	public String getValue() {
		return value;
	}





	public void setValue(String value) {
		this.value = value;
	}





	public String getClassStyle() {
		return classStyle;
	}





	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}





	public String getCssStyle() {
		return cssStyle;
	}





	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	
	
	

	
	
}
