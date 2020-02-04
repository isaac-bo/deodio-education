package com.deodio.elearning.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.deodio.core.constants.Constants;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.commons.service.SysActionService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.SysAction;
import com.deodio.elearning.utils.CommonUtils;

public class UrlFilter implements Filter {
    private static final String DOMAIN_END = CommonUtils.DOMAIN_POSTFIX; 
    private static final String URL_SUFFIX = Constants.URL_SUFFIX;
    private static final String DOMIAN_CONTENT_ROOT = DConstants.DOMIAN_CONTENT_ROOT; 
//    private static final String DELIMITER_SLASH = DConstants.DELIMITER_SLASH;
    private static final String DOMAIN_WWW  = DConstants.DOMAIN_WWW;
    private static final String DELIMITER_FULL = DConstants.DELIMITER_FULL;
    private static final String DOMAIN_SESSION_ACCOUNT_ID = DConstants.DOMAIN_SESSION_ACCOUNT_ID;
    private static final String APPLICATION_DOMAIN_SECOND_OPEN = CommonUtils.APPLICATION_DOMAIN_SECOND_OPEN;
    
    private AccountService accountService; 
    private SysActionService sysActionService;
    
    
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	log.debug("init");
    	ServletContext sc = filterConfig.getServletContext();  
    	XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);    
    	if(cxt != null){
    		if( cxt.getBean("accountService") != null && accountService == null){
    			accountService = (AccountService) cxt.getBean("accountService");     
    		}
    		if( cxt.getBean("sysActionService") != null && sysActionService == null){
    			sysActionService = (SysActionService) cxt.getBean("sysActionService");     
    		}
    	}
    }

    @Override
    public void destroy() {
        log.debug("------------------------------destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	
    	 HttpServletRequest request = (HttpServletRequest) servletRequest;
         HttpServletResponse response = (HttpServletResponse) servletResponse;
         String requestURI = request.getRequestURI();
         String serverName = request.getServerName().toLowerCase();
         String realURI = getRealRequestURI(serverName, requestURI,request);
         
        //开启二级域名是，检验二级域名有效性，    是否开启二级域名功能判断修改  modify by 许向东  20180521
        if(APPLICATION_DOMAIN_SECOND_OPEN.equals(Boolean.TRUE.toString())){
             boolean isRedirect = redirectToSecondDomain(request,response,requestURI);	
             if(!isRedirect){
	            request.getRequestDispatcher(realURI).forward(request, response);
             }
        }else{
        	filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private String getRealRequestURI(String serverName, String requestURI,HttpServletRequest request) {
    	String resultUrl = StringUtils.EMPTY;
    	//域名 www 或者  请求非.html结尾的
        if (serverName.startsWith(DOMAIN_WWW)|| (!requestURI.endsWith(URL_SUFFIX))) {
        	resultUrl =  requestURI;
        }else{
	        if (serverName.endsWith(DOMAIN_END)) {
	        	//获取二级域名
	            String secondDomain = serverName.substring(0, serverName.indexOf(DELIMITER_FULL));
	        	Account account = accountService.queryAccountByHostName(secondDomain);
	        	if(account == null || StringUtils.isBlank(account.getId())){
	//        		throw new  DeodioException("403");
	        		resultUrl = "/account/error_msg.html?errorMsg=二级域名无效！";
	        	}else{
	        		request.getSession().setAttribute(DOMAIN_SESSION_ACCOUNT_ID, account.getId());
	        		resultUrl =  requestURI;
	        	}         
	        }else{
	        	resultUrl =  requestURI;
	        }
        }
 
        if(resultUrl.startsWith(DOMIAN_CONTENT_ROOT)){
        	resultUrl = resultUrl.replace(DOMIAN_CONTENT_ROOT,StringUtils.EMPTY);
        }
        
        //获取更新 action 信息
        if(CommonUtils.APPLICATION_AUTO_GET_SYS_ACTION.equals(Boolean.TRUE.toString()) && requestURI.contains(URL_SUFFIX)){
        	String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
        	SysAction info = new SysAction();
        	info.setActionUrl(resultUrl);
        	info.setCreateId(userId);
        	info.setUpdateId(userId);
        	sysActionService.saveSysActioin(info);
        }
        
        return resultUrl;
    }
    
    private  boolean redirectToSecondDomain(HttpServletRequest request, HttpServletResponse response,String requestURI) throws IOException{
    	boolean isRedirect = false;
		String cookieAuid = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String serverName = request.getServerName().toLowerCase();
		String secondDomainInDataBase = StringUtils.EMPTY;
		
		if(StringUtils.isNotBlank(cookieAuid) &&  requestURI.endsWith(URL_SUFFIX) && APPLICATION_DOMAIN_SECOND_OPEN.equals(Boolean.TRUE.toString())){
			String secondDomain = StringUtils.EMPTY;
			int index = serverName.indexOf(DELIMITER_FULL); 
			if( index > 0){
				secondDomain = serverName.substring(0,index);
			}
			Account account = accountService.queryAccountModelById(cookieAuid);
			secondDomainInDataBase = account.getAccountDomain();
			
			if(StringUtils.isBlank(secondDomainInDataBase) && !DConstants.DOMAIN_WWW.equals(secondDomain)){
				secondDomainInDataBase = DConstants.DOMAIN_WWW;
				isRedirect = true;
			}else if(StringUtils.isNotBlank(secondDomainInDataBase) && !secondDomainInDataBase.equals(secondDomain)){
				isRedirect = true;
			}
		}
		
		if(isRedirect){
			String basePath = request.getScheme()+"://"+ secondDomainInDataBase + DOMAIN_END +":"+request.getServerPort()+ requestURI;
			
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location",basePath);
			//---分割线---
			response.sendRedirect(basePath);
		}
		
    	return isRedirect;
    }

//    private static String getURI(String siteId, String requestURI) {
//        if (requestURI.equals("/")) {
//            return "/" + siteId;
//        } else {
//            return "/" + siteId + requestURI;
//        }
//    }
}