package com.deodio.core.controller;

import com.deodio.core.constants.Constants;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public abstract class BaseController extends MultiActionController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.FORMAT_YYYYMMDD, Locale.getDefault());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        super.initBinder(request, binder);
    }

    public void binder(Object o, HttpServletRequest request) {
        ServletRequestDataBinder binder = new ServletRequestDataBinder(o);
        binder.bind(request);

    }

    public String redirectPage(String page) {
        return "redirect:" + page;
        // return page;
    }

    public String forwardPage(String page) {
        return "forward:" + page;
        // return page;
    }

    protected ModelAndView renderHtml(final HttpServletRequest request, final HttpServletResponse response,
            final String text) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(text);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (final IOException e) {

            log.error(e.getMessage(), e);
        }

        return null;
    }

    protected ModelAndView renderText(final HttpServletRequest request, final HttpServletResponse response,
            final String text) {
        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(text);
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    protected ModelAndView renderXML(final HttpServletRequest request, final HttpServletResponse response,
            final String text) {
        try {
            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().write(text);
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    
    public String getCookieUserId(final HttpServletRequest request) {
    	return (String)CookieUtils.getCookie(request, Constants.COOKIE_USER_ID);
    }
    public String getCookieAccount(final HttpServletRequest request) {
    	return (String)CookieUtils.getCookie(request, Constants.COOKIE_ACCOUNT_ID);
    }
    public String getCookieGroupId(final HttpServletRequest request) {
    	return (String)CookieUtils.getCookie(request, Constants.COOKIE_GROUP_ID);
    }

    /**
     * 返回JSON状态
     * @param data
     * @param status
     * @return
     */
    public AjaxResultModel getAjaxResult(Object data, int status) {
        AjaxResultModel arm = new AjaxResultModel();
        if(data!=null) {
            arm.setData(data);
        }
        arm.setStatus(status);
        return arm;
    }
	
    
}
