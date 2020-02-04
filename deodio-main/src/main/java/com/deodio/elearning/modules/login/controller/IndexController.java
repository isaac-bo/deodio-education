package com.deodio.elearning.modules.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;

@Controller
public class IndexController extends BaseController{
	@RequestMapping("/index"+Constants.URL_SUFFIX)
	public String toLogoutPage(HttpServletRequest request,HttpServletResponse response){
		return "/modules/login/index";
	}
}
