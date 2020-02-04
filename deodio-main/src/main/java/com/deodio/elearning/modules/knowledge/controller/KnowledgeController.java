package com.deodio.elearning.modules.knowledge.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.knowledge.service.KnowledgeService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.KnowledgePoints;

@Controller
public class KnowledgeController extends BaseController {

	@Autowired
	KnowledgeService knowledgeService;

	@RequestMapping("/knowledge/load_list" + Constants.URL_SUFFIX)
	public String toKnowledgeList(Model model, HttpServletRequest request, HttpServletResponse response)
			throws DeodioException {
		model.addAttribute("classificationName", (String) request.getParameter("classificationName"));
		model.addAttribute("classificationId", (String) request.getParameter("classificationId"));
		model.addAttribute("returnflag", "true");
		return "/modules/knowledge/knowledge_list";
	}
	@RequestMapping("/knowledge/list" + Constants.URL_SUFFIX)
	public String toKnowledgeListManage(Model model, HttpServletRequest request, HttpServletResponse response)
			throws DeodioException {
		model.addAttribute("classificationName", (String) request.getParameter("classificationName"));
		model.addAttribute("classificationId", (String) request.getParameter("classificationId"));
		return "/modules/knowledge/knowledge_list";
	}

	@RequestMapping("/knowledge/load_knowledge_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadKnowledgeList(@RequestParam String classificationId, @RequestParam String keyword,
			@RequestParam Integer pageNo, HttpServletRequest request) throws DeodioException {
		
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		List<Map<String, Object>> dataList = knowledgeService.findKnowledgeList(StringUtils.isEmpty(classificationId)?null:classificationId, keyword, pageRequest,
				accountId, userId);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	
	@RequestMapping("/knowledge/get_knowledge_by_classificationId" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getKnowledgeByClassificationId(@RequestParam String classificationId,HttpServletRequest request, HttpServletResponse response)
					throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		arm.setData(knowledgeService.getKnowledgePointByClassificationId(classificationId,accountId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/knowledge/submit_knowledge" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitClassificaiton(@RequestParam(required = false) String knowledgePointId,@RequestParam(required = false) Date updateTime,
			@RequestParam String knowledgePointName, @RequestParam String knowledgePointDesc,
			@RequestParam String classificationIds, HttpServletRequest request, HttpServletResponse response)
					throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		knowledgeService.submitKnowledgePoint(knowledgePointId, knowledgePointName, knowledgePointDesc,updateTime,
				classificationIds, accountId, userId);		
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/knowledge/get_knowledge" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getKnowledge(@RequestParam(required = false) String knowledgePointId,String classificationId,
			HttpServletRequest request, HttpServletResponse response) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(knowledgeService.getKnowledgePoint(knowledgePointId,classificationId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/knowledge/del_knowledge" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delKnowledge(@RequestParam(required = false) String knowledgePointId,String classificationId,
			HttpServletRequest request, HttpServletResponse response) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		knowledgeService.delKnowledgePoint(knowledgePointId,classificationId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	//名字校验
//	@RequestMapping("/knowledge/getCheck_knowledge" + Constants.URL_SUFFIX)
//	@ResponseBody
//	public AjaxResultModel getCheckKnowledgePointName(
//			@RequestParam String KnowledgePointName,
//			
//			HttpServletRequest request, HttpServletResponse response) {
//		AjaxResultModel arm = new AjaxResultModel();	
//		arm.setData(knowledgeService.getCheckKnowledgePointName(KnowledgePointName));
//		arm.setStatus(AjaxResultModel.SUCCESS);
//		return arm;
//	}
	@RequestMapping("/knowledge/del_all_knowledge_points" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delAllKnowledgePoints(@RequestParam String knowledgePointIds, HttpServletRequest request,
			HttpServletResponse response) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		knowledgeService.delAllKnowledgePoints(knowledgePointIds);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
}
