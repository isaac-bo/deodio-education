package com.deodio.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.deodio.core.exception.CommonException;

public class CookieUtils extends Utils {

    public String getCookie(final String cookieName,
            final HttpServletRequest request, final String defaultValue) {
        final Cookie[] cookies = request.getCookies();

        if (StringUtils.isBlank(cookieName) || request == null
                || cookies == null) {
            return defaultValue;
        }

        for (final Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return defaultValue;
    }

    public static HttpServletResponse setCookie(final String cookieName,
            final String cookieValue, final int maxAge,
            final HttpServletResponse response, String domain) {
        if (StringUtils.isBlank(cookieName) || cookieValue == null
                || response == null) {
            return response;
        }
        final Cookie cookie = new Cookie(cookieName, cookieValue);
        // if(maxAge>=0){
        cookie.setMaxAge(maxAge);
        // }
        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
        return response;
    }

    public static final Object getCookie(HttpServletRequest request, String name) {

        final Cookie[] cookie = request.getCookies();
        Object object = null;

        if (cookie == null) {
            return object;
        }

        for (final Cookie element : cookie) {

            if (element != null && element.getName().equalsIgnoreCase(name)) {
                object = element.getValue();
                break;
            }
        }
        return object;
    }
    
    public static final void clearCookie(HttpServletRequest request,
            HttpServletResponse response, String path){
    	clearCookie(request,response,path,null);
    }

    public static final void clearCookie(HttpServletRequest request,
            HttpServletResponse response, String path, String domain) {
        Cookie[] cookies = request.getCookies();
        try {
            for (int i = 0; i < cookies.length; i = i + 1) {
                Cookie cookie = new Cookie(cookies[i].getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath(path);
                if (!StringUtils.isBlank(domain)) {
                    cookie.setDomain(domain);
                }
                response.addCookie(cookie);
            }
        } catch (Exception ex) {
            throw new CommonException(ex.getMessage(), ex);
        }

    }
    
    //add by xu.xiangdong  2016.12.27   删除指定cookie
    public static final void deleteCookie(HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }
}
