package com.deodio.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	protected String encoding;
	protected FilterConfig  filterConfig;
	protected boolean ignore = true;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(ignore || (request.getCharacterEncoding() == null)){
			String encoding = this.encoding;
			if(encoding != null){
				request.setCharacterEncoding(encoding);
				response.setContentType("text/html;charset=" + encoding + "\"");
			}
		}
		
//		HttpSession session = ((HttpServletRequest)request).getSession(true);
//		Locale locale = null; // (Locale)session.getAttribute(Globals.LOCALE_KEY);
//		if(locale == null){
//			locale = request.getLocale();
//		}
		//Config.set(session, Config.FMT_LOCALE,locale);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
		this.encoding = arg0.getInitParameter("encoding");
		String value = arg0.getInitParameter("ignore");
		if(null == value || "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value)){
			this.ignore = true;
		}else{
			this.ignore = false;
		}
	}

}
