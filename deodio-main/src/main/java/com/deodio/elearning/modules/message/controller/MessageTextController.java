package com.deodio.elearning.modules.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.message.service.MessageTextService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
@Controller
public class MessageTextController extends BaseController{
	
	@Autowired
	private MessageTextService messageTextService;

	@RequestMapping("/message/getMessageList"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getMessageList(HttpServletRequest request) throws DeodioException{
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> map = new HashMap<String,Object>();
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		map.put("userId", userId);
		List<Map<String, Object>> messageTestList = messageTextService.findMessageList(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("dataList", messageTestList);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
}
