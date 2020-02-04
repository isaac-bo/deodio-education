package com.deodio.elearning.modules.classification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.persistence.model.AjaxResultModel;

@Controller
public class ClassificationController extends BaseController{
	
	@Autowired
	ClassificationService classificationService;
	
	@RequestMapping("/classification/load_classification_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadClassificationList(@RequestParam String classificationId, @RequestParam String keyword,
			@RequestParam Integer pageNo, HttpServletRequest request,HttpServletResponse response) {
		
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		List<Map<String, Object>> dataList = classificationService.findClassifications(classificationId, keyword, pageRequest,accountId,userId);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	

	/**
	 * 根据id查询分类描述
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/classification/description" + Constants.URL_SUFFIX)
	@ResponseBody
	public Object queryClassificationDesc(@RequestParam(required = false) String id, HttpServletRequest request,
			HttpServletResponse response) throws DeodioException {

		return classificationService.queryClassificationDesc(id);
	}
	
	

	/**
	 * 根据Id查询分类
	 * @param model
	 * @param request
	 * @param classificationId
	 * @return
	 */
	@RequestMapping("/classification/list_by_id"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryclassificationById(Model model,HttpServletRequest request,@RequestParam(required=false) String classificationId,@RequestParam(required=false) Short itemType){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("classificationId", classificationId);
		paramsMap.put("itemType", itemType);
		//该分类的叶子分类
		List<Map<String,Object>> classificationsList = classificationService.queryClassificationById(paramsMap);
		arm.setData(classificationsList);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 子分类判断
	 * @param model
	 * @param request
	 * @param classificationId
	 * @return
	 */
	@RequestMapping("/classification/num_classification"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel numChildrenClassification(Model model,HttpServletRequest request,
			@RequestParam String classificationId,@RequestParam Short itemType){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
		map.put("classificationId", classificationId);
		map.put("itemType", itemType);
		Integer count =  classificationService.numChildrenClassification(map);
		arm.setData(count);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	
	@RequestMapping("/classification/list"+Constants.URL_SUFFIX)
	public String toKnowledgeList() throws DeodioException {
		
		return "/modules/classification/classification_list";
	}
	
	@RequestMapping("/classification/get_left_ctree" + Constants.URL_SUFFIX)
	@ResponseBody
	public Object getLeftClassificationTree(@RequestParam String treeId, HttpServletRequest request,
			HttpServletResponse response) {

		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		return classificationService.getClassificationTree(accountId, treeId);
	}
	
	@RequestMapping("/classification/get_left_ctree_by_text" + Constants.URL_SUFFIX)
	@ResponseBody
	public Object getLeftCtreeByText(@RequestParam(required = false) String queryText,
			@RequestParam String[] selectedClassification, @RequestParam(required = false) String treeId,
			HttpServletRequest request, HttpServletResponse response) throws DeodioException {

		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = this.getCookieUserId(request);
		if(treeId.equals(DConstants.STRING_ZERO))
			return classificationService.getLeftCtreeByText(accountId, treeId, queryText, selectedClassification,userId);
		return classificationService.getLeftCtreeByLevel(accountId, treeId, queryText);
	}
	
	@RequestMapping("/classification/get_left_ctree_by_level" + Constants.URL_SUFFIX)
	@ResponseBody
	public Object getLeftCtreeByLevel(@RequestParam(required = false) String queryText,
			@RequestParam(required = false) String treeId, HttpServletRequest request, HttpServletResponse response)
					throws DeodioException {

		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		return classificationService.getLeftCtreeByLevel(accountId, treeId, queryText);
	}
	
	@RequestMapping("/classification/query_reversion_ctree_by_text" + Constants.URL_SUFFIX)
	@ResponseBody
	public Object queryReversionCtreeByText(@RequestParam(required = false) String queryText,
			@RequestParam(required = false) String treeId, HttpServletRequest request, HttpServletResponse response)
					throws DeodioException {

		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		return classificationService.queryReversionCtreeByText(accountId, treeId, queryText);
	}

	
	@RequestMapping("/classification/submit_classification" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitClassificaiton(
			@RequestParam(required = false) String classificationId,@RequestParam String classificationName,
			@RequestParam String classificationDesc,@RequestParam(required = false) String classificationParentId,
			HttpServletRequest request, HttpServletResponse response) {
		
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		
		arm.setData(classificationService.submitClassification(classificationId,classificationName,classificationDesc,classificationParentId,accountId,userId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	//校验名字
	@RequestMapping("/classification/getCheck_classification" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCheckClassificationName(
			@RequestParam String classificationName,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResultModel arm = new AjaxResultModel();	
		arm.setData(classificationService.getCheckClassificationName(classificationName));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
}

	@RequestMapping("/classification/get_classification" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getClassification(@RequestParam(required = false) String classificationId,
			HttpServletRequest request, HttpServletResponse response) {
		
		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(classificationService.getClassification(classificationId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	@RequestMapping("/classification/del_classification" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delClassification(@RequestParam(required=false) String classificationId,HttpServletRequest request) throws DeodioException {
		
		AjaxResultModel arm = new AjaxResultModel();
		classificationService.delClassification(classificationId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	@RequestMapping("/classification/del_all_classifications" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delAllClassifications(@RequestParam(required = false) String classificationIds,
			HttpServletRequest request, HttpServletResponse response) {
		
		AjaxResultModel arm = new AjaxResultModel();
		classificationService.delAllClassifications(classificationIds);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
}
