package com.deodio.elearning.modules.tags.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.utils.CommonUtils;


@Controller
public class TagsController extends BaseController {
	
	@Autowired
	private TagsService tagsService;

	/**
	 * 查询热点标签
	 * @param model
	 * @param presentationName
	 * @param presentationDesc
	 * @param classificationJson
	 * @return
	 */
	@RequestMapping("/tags/hot/list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryHotTagsList(HttpServletRequest request,Model model,@RequestParam(required = false) String name,@RequestParam(required = false) String desc
			,@RequestParam(required = false) String itemType){
		
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(name)){
			paramsMap.put("itemName", name);
		}
		if(StringUtils.isNotBlank(desc)){
			paramsMap.put("itemDesc", desc);
		}
		if(StringUtils.isNotBlank(itemType)){
			paramsMap.put("itemType", Short.valueOf(itemType));
		}
		paramsMap.put("accountId",accountId);
//		List<Map<String,Object>> tagsList = new ArrayList<Map<String,Object>>();
		//TODO: 调用热门标签算法
		if(CommonUtils.HOT_TAGS_SWITCH.equals("0")){
			arm.setData(tagsService.queryHotTagsList(paramsMap));
		}
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

}
