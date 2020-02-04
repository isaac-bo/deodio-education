package com.deodio.elearning.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.mapper.customize.AuthVerificationCustomizeMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.google.gson.Gson;

public class AuthVerificationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AuthVerificationCustomizeMapper authVerificationCustomizeMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		 boolean resultFlg = false;
		 Object cookieAuid = CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		 Object cookieCuid = CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		 String groupId = request.getParameter("groupId");
		 
		 Map<String,Object> params = new HashMap<String,Object>();
		 params.put("userId", cookieCuid);
		 params.put("accountId", cookieAuid);
		 params.put("groupId", groupId);
		 
		 Map<String,Object> authInfo = authVerificationCustomizeMapper.getValidateInfoForGroupUser(params);
		 
		 Integer isRestrict = Integer.valueOf(authInfo.get("is_restrict").toString());
		 Integer isAuth = Integer.valueOf(authInfo.get("is_auth").toString());
		 
//		 Integer isRestrict = 1;
//		 Integer isAuth = 0;
		 
		 if(DConstants.NUMBER_ZERO == isRestrict ){
			 resultFlg = true;
		 }else if(isAuth > DConstants.NUMBER_ZERO ){
			 resultFlg = true;
		 }else{
			 resultFlg = false;
			 if(request.getHeader("x-requested-with")!= null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){//如果是ajax请求响应头会
				 AjaxResultModel arm = new AjaxResultModel();
				 arm.setStatus(AjaxResultModel.NO_AUTH);
				 Gson gson = new Gson();
				 String armJson = gson.toJson(arm);
				 ServletOutputStream out =  response.getOutputStream();
				 out.print(armJson);
			 }
			 else{
//				 response.sendRedirect("error/error.jsp");
				 String requestUrl = "/account/error_msg.html?errorMsg=权限不足，无法访问该方法！";
				 request.getRequestDispatcher(requestUrl).forward(request, response);
			 }
		 }
		 
		 return resultFlg;
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
