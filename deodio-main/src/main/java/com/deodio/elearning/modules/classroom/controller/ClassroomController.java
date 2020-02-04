package com.deodio.elearning.modules.classroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.deodio.elearning.modules.course.service.CourseService;
import com.deodio.elearning.persistence.model.AjaxResultModel;

@Controller
public class ClassroomController extends BaseController {
	

	
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping("/classroom" + Constants.URL_SUFFIX)
	public String toClassroom(Model model, HttpServletRequest request, @RequestParam String courseId,@RequestParam String itemId,@RequestParam String itemIndex,@RequestParam String itemType
			,@RequestParam(required=false) String groupId){
		
		
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("courseId", courseId);
		//查询课程信息
		Map<String, Object> courseMap = courseService.queryTraineeCourseInfo(params);
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("courseId", courseId);
		model.addAttribute("itemIndex",itemIndex);
		model.addAttribute("itemId",itemId);
		
		model.addAttribute("groupId", groupId);
		
		if(itemType.equals(DConstants.STRING_ZERO)){
			model.addAttribute("presentationId", itemId);
		}else if(itemType.equals(DConstants.STRING_ONE)){
			model.addAttribute("quizId", itemId);
		}else if(itemType.equals(DConstants.STRING_TWO)){
			model.addAttribute("surveyId", itemId);
		}
		
		Map<String,Object> queryCondition = new HashMap<String,Object>();
		queryCondition.put("courseId", courseId);
		
		queryCondition.put("itemId",itemId);
		Map<String,Object> courseItemSortInfo = courseService.queryTraineeCourseItemSortInfo(queryCondition);
		model.addAttribute("courseItemSortInfo",courseItemSortInfo);
		
		return "modules/classroom/classroom";
	}
	
	/**
	 * 获取学员课程章节信息
	 * 
	 * @param model
	 * @param request
	 * @param dataJson
	 * @return
	 */
	@RequestMapping("/classroom/itemList" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loasClassroomItemList(Model model, HttpServletRequest request,@RequestParam String courseId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("courseId", courseId);

			List<Map<String, Object>> itemList = courseService.queryTraineeCourseItemInfo(params);
			arm.setData(itemList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}


}
