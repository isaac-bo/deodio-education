package com.deodio.elearning.modules.course.controller;

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

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.course.service.CourseOnliveService;
import com.deodio.elearning.modules.course.service.CourseService;
import com.deodio.elearning.modules.location.service.LocationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.modules.trainers.service.TrainersService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.customize.CourseOnliveItemBo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
public class CourseOnliveController  extends BaseController{

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseOnliveService courseOnliveService;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	TrainersService trainersService;
	
	@Autowired
	LocationService locationService;
	
	@RequestMapping("/course/onlive/list"+Constants.URL_SUFFIX)
	public String toCourseOnliveList(Model model,HttpServletRequest request){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("courseType", DConstants.COURSE_TYPE_ONLIVE);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLIVE);
		//该分类的叶子分类
		List<Map<String,Object>> classificationsList = classificationService.queryClassificationById(paramsMap);
		//当前账号下课件包含的所有标签
		List<Map<String,Object>> tagsList = tagsService.queryTagsByItemType(paramsMap);
	
		model.addAttribute("classificationsList", classificationsList);
		model.addAttribute("tagsList", tagsList);
		paramsMap.put("accountId", accountId);
		return "modules/course/course_onlive_list";
	}
	
	
	/**
	 * 根据分类和标签查询课件
	 * @param model
	 * @param request
	 * @param pageNo
	 * @param classificationIdList
	 * @param tagIdList
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/course/onlive/load_data_by_classifications_and_tags"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOnlineByClassificationAndTags(Model model,HttpServletRequest request,
			@RequestParam(required=false) Integer pageNo,@RequestParam(value="classificationIdList[]",required=false) List<?> classificationIdList,
			@RequestParam(value="tagIdList[]",required=false) List<?> tagIdList,@RequestParam(required=false) Integer pageSize){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
		map.put("courseType", DConstants.COURSE_TYPE_ONLIVE);
		map.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLIVE);
		//总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		map.put("classificationIdList", classificationIdList);
		map.put("tagIdList", tagIdList);
		map.put("pagination", pageRequest);
		List<Map<String,Object>> list =  courseService.getCourseList(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	/**
	 * 设置presentation信息
	 * @param request
	 * @param courseName
	 * @param courseDesc
	 * @param courseTarget
	 * @param courseSort
	 * @param coursePassTime
	 * @param coursePassScore
	 * @param coursePassRequire
	 * @param isPublic
	 * @param courseId
	 * @param courseCover
	 * @param attachId
	 * @param tagsJson
	 * @param classificationJson
	 * @return
	 */
	@RequestMapping("/course/onlive/submit_profile"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseOnliveInfo(HttpServletRequest request,@RequestParam String courseInfoJson,
			@RequestParam String attachId, @RequestParam String tagsJson,@RequestParam String classificationJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			//Json字符串转List对象(标签)
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			String id = courseOnliveService.insertCourseOnliveInfo(courseInfoJson, attachId,
					userId, accountId, getCookieGroupId(request), tagsJson, classificationJson);
			arm.setData(id);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	} 
	
	
	@RequestMapping("/course/onlive/profile"+Constants.URL_SUFFIX)
	public String toCourseOnliveProfile(Model model,HttpServletRequest request,@RequestParam(required=false) String courseId){
		//课程编号(courseId) 不为空时，为编辑状态；为空时为插入状态
		if(StringUtils.isNotBlank(courseId)){
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("courseId", courseId);
			//获取线上课程基本数据
			Map<String,Object> courseMap =  courseOnliveService.queryCourseOnliveProfile(paramsMap);
			model.addAttribute("courseMap", courseMap);
			paramsMap.put("itemId", courseId);
			paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLIVE);
			//获取线上课程对应分类
			List<Map<String,Object>> selectedClassificationList =  classificationService.querySelectedClassificationByItemId(paramsMap);
			model.addAttribute("selectedClassificationList", selectedClassificationList);
			//获取线上课程对应标签
			String selectedTagsName =  tagsService.querySelectedTagsNameByItemId(paramsMap);
			model.addAttribute("selectedTagsName", selectedTagsName);
		}
		return "modules/course/course_onlive_profile";
	}
	
	@RequestMapping("/course/onlive/content"+Constants.URL_SUFFIX)
	public String toCourseOnliveContent(Model model,HttpServletRequest request,@RequestParam String courseId){
		String accountId = this.getCookieAccount(request);
		//获取培训讲师数据
		List<Map<String, Object>> trainerList = trainersService.getTrainerList(null,accountId,null);
		//获取线下课程内容
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("courseId", courseId);
		paramsMap.put("accountId", accountId);
		Map<String,Object> courseMap = courseOnliveService.queryCourseOnliveProfile(paramsMap);
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("courseId", courseId);
		Gson gson = new Gson();
		model.addAttribute("trainerListJson", gson.toJson(trainerList));
		return "modules/course/course_onlive_content";
	}


	@RequestMapping("/course/onlive/content/load_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOnliveContent(Model model,HttpServletRequest request,@RequestParam String courseId
			,@RequestParam(required=false) int stepNo,@RequestParam(required=false) String itemType){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			
			if(DConstants.NUMBER_ZERO == stepNo){
				stepNo = DConstants.NUMBER_ONE;
			}
			//获取线下课程内容
			Map<String,Object> params = new HashMap<String,Object>();
			if(!StringUtils.isBlank(itemType)){
				params.put("itemType",Short.valueOf(itemType));
			}
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("courseId", courseId);
			params.put("stepNo", stepNo);
			
			List<Map<String,Object>> dataList = courseOnliveService.queryCourseOnliveContent(params);
			arm.setData(dataList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	
	@RequestMapping("/course/onlive/content/submit"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseOnliveContent(Model model,HttpServletRequest request,@RequestParam String contentJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			
			Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD_HHMM).create();
			List<CourseOnliveItemBo> contentList = gson.fromJson(contentJson, new TypeToken<List<CourseOnliveItemBo>>(){}.getType());
			courseOnliveService.saveCourseOnliveContentInfo(contentList, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	} 
	
	@RequestMapping("/course/onlive/detail"+Constants.URL_SUFFIX)
	public String toCourseOnliveDetail(Model model,HttpServletRequest request){
		return "modules/course/course_onlive_detail";
	}

}
