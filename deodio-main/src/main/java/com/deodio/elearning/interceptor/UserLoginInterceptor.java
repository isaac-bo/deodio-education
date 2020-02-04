package com.deodio.elearning.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;

public class UserLoginInterceptor implements HandlerInterceptor {
	
	
	/**
	 * 用户登录拦截器，如果未登录/清除cookie后，统一跳转登陆页重新登录
	 * @Title: preHandle
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @return boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 Object cookieAuid = CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		 Object cookieCuid = CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		 if(cookieAuid==null || cookieCuid==null){
			 System.err.println(getClass().getName()+"\t URI :\t"+request.getRequestURI()+"\t 用户未登录  :\t");
			 if(DConstants.USER_JOIN_GROUP.equals(request.getRequestURI())||
					 DConstants.USER_GROUP_REGISTER.equals(request.getRequestURI())||
							 DConstants.USER_GROUP_SETPWD.equals(request.getRequestURI())) {
				 return true;
			 }else {
				 String contentPath = request.getContextPath();
				 response.sendRedirect(contentPath + "/login.html");
				 return false; 
			 }	
		 }else{
			 return true;
		 }
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		}

}
