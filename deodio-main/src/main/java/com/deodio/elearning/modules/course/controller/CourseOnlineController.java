package com.deodio.elearning.modules.course.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.deodio.components.pagination.page.CommonUtils;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DesUtil;
import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.course.service.CourseOnlineService;
import com.deodio.elearning.modules.course.service.CourseService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseOnlineItem;
import com.deodio.elearning.persistence.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.emory.mathcs.backport.java.util.Collections;

@Controller
@RequestMapping("/course/online")
public class CourseOnlineController extends BaseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseOnlineService courseOnlineService;

	@Autowired
	private ClassificationService classificationService;

	@Autowired
	private TagsService tagsService;
	
	@RequestMapping("/item_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadCourseTraineeCourseItemsInfo(Model model, HttpServletRequest request,
			@RequestParam String courseId) {
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

	/**
	 * 跳转至课程显示列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/list" + Constants.URL_SUFFIX)
	public String toCourseOnlineList(Model model, HttpServletRequest request) {

		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("courseType", DConstants.COURSE_TYPE_ONLINE);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
		// 该分类的叶子分类
		List<Map<String, Object>> classificationsList = classificationService.queryClassificationById(paramsMap);
		// 当前账号下课件包含的所有标签
		List<Map<String, Object>> tagsList = tagsService.queryTagsByItemType(paramsMap);

		model.addAttribute("classificationsList", classificationsList);
		model.addAttribute("tagsList", tagsList);

		return "modules/course/course_online_list";
	}

	/**
	 * 根据分类和标签查询课件
	 */
	@RequestMapping("/load_data_by_classifications_and_tags" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOnlineByClassificationAndTags(@RequestParam(required = false) Integer pageNo,
			@RequestParam(value = "classificationIdList[]", required = false) List<?> classificationIdList,
			@RequestParam(value = "tagIdList[]", required = false) List<?> tagIdList,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String keyword,
			Model model, HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", getCookieAccount(request));
		map.put("courseType", DConstants.COURSE_TYPE_ONLINE);
		map.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
		// 总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		map.put("classificationIdList", classificationIdList);
		map.put("tagIdList", tagIdList);
		map.put("keyword", keyword);
		map.put("pagination", pageRequest);
		map.put("userId", getCookieUserId(request));
		map.put("groupId", getCookieGroupId(request));
		List<Map<String, Object>> list = courseService.getCourseList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * TODO
	 */
	@RequestMapping("/profile" + Constants.URL_SUFFIX)
	public String toCourseOnlineProfile(@RequestParam(required = false) String courseId,
			@RequestParam(required = false) String isPublish, Model model, HttpServletRequest request) {

		// 课程编号(courseId) 不为空时，为编辑状态；为空时为插入状态
		if (StringUtils.isNotBlank(courseId)) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("courseId", courseId);
			// 获取线上课程基本数据
			Map<String, Object> courseMap = courseOnlineService.queryCourseOnlineProfile(paramsMap);
			model.addAttribute("courseMap", courseMap);
			paramsMap.put("itemId", courseId);
			paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
			// 获取线上课程对应分类
			List<Map<String, Object>> selectedClassificationList = classificationService
					.querySelectedClassificationByItemId(paramsMap);
			model.addAttribute("selectedClassificationList", selectedClassificationList);
			// 获取线上课程对应标签
			String selectedTagsName = tagsService.querySelectedTagsNameByItemId(paramsMap);
			model.addAttribute("selectedTagsName", selectedTagsName);

			Course course = new Course();
			course.setId(courseId);
			course.setIsEdit(DConstants.IS_EDIT);
			courseOnlineService.updateCourse(course);
		}
		if (StringUtils.isNotBlank(isPublish)) {
			courseOnlineService.cancelPublish(courseId, getCookieUserId(request));
		}

		return "modules/course/course_online_profile";
	}

	/**
	 * 设置presentation信息
	 */
	@RequestMapping("/submit_profile" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseOnlineInfo(HttpServletRequest request, @RequestParam String courseInfoJson,
			@RequestParam String attachId, @RequestParam String tagsJson, @RequestParam String classificationJson) {

		AjaxResultModel arm = new AjaxResultModel();
		try {
			// Json字符串转List对象(标签)
			String id = courseOnlineService.insertCourseOnlineInfo(courseInfoJson, attachId, getCookieUserId(request),
					getCookieAccount(request), getCookieGroupId(request), tagsJson, classificationJson);
			arm.setData(id);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 跳转到课程内容显示
	 */
	@RequestMapping("/content" + Constants.URL_SUFFIX)
	public String toCourseOnlineContent(Model model, HttpServletRequest request, @RequestParam String courseId) {

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("courseId", courseId);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
		// 获取线上课程对应分类
		List<Map<String, Object>> selectedClassificationList = classificationService
				.querySelectedClassificationByItemId(paramsMap);
		model.addAttribute("classificationList", selectedClassificationList);
		// 获取线上课程对应标签
		String selectedTagsName = tagsService.querySelectedTagsNameByItemId(paramsMap);
		model.addAttribute("tagList", selectedTagsName);
		// 获取线上课程基本数据
		Map<String, Object> courseMap = courseOnlineService.queryCourseOnlineProfile(paramsMap);
		model.addAttribute("courseMap", courseMap);
		List<Map<String, Object>> contentList = courseOnlineService.queryCourseOnlineContent(paramsMap);
		for (Map<String, Object> map : contentList) {
			map.put("item_desc", StringUtils.removeHtmlTags((String) map.get("item_desc")));
		}
		model.addAttribute("courseId", courseId);
		model.addAttribute("contentList", contentList);
		// model.addAttribute("course", courseService.getCourseById(courseId));
		System.err.println(getClass().getName()+"\t===228===contentList:\t"+contentList.toString());
		Course course = new Course();
		course.setId(courseId);
		course.setIsEdit(DConstants.IS_EDIT);
		courseOnlineService.updateCourse(course);

		return "modules/course/course_online_content";
	}

	@RequestMapping("/get_homework_quiz" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseHomeworkQuiz(@RequestParam String courseId){
		AjaxResultModel arm = new AjaxResultModel();
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("courseId", courseId);
			arm.setData(courseOnlineService.findCourseHomeworkQuiz(params));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}
	
	/**
	 * 设置课程内容
	 */
	@RequestMapping("/submit_content" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseOnlineContent(@RequestParam String dataStr, @RequestParam String courseId,
			@RequestParam(required = false) Integer isPublish, Model model, HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		try {
			// Json字符串转List对象(课程内容)
			Gson gson = new Gson();
			List<CourseOnlineItem> contentList = gson.fromJson(dataStr, new TypeToken<List<CourseOnlineItem>>() {
			}.getType());
			courseOnlineService.updateCourseOnlineContent(contentList, courseId, getCookieUserId(request),
					getCookieAccount(request));
			if (isPublish == DConstants.TYPE_IS_PUBLISH) {
				courseOnlineService.publishCourse(courseId);
			} else {
				Course course = new Course();
				course.setId(courseId);
				course.setIsEdit(DConstants.ISNOT_EDIT);
				course.setUpdateId(getCookieUserId(request));
				course.setUpdateTime(new Date());
				courseOnlineService.updateCourse(course);
			}
			arm.setData(courseOnlineService.queryCourse(courseId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	@RequestMapping("/publish" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updatePublishState(@RequestParam String courseId, @RequestParam Short isPublish) {
		
		Course course = new Course();
		course.setId(courseId);
		course.setIsPublish(isPublish);
		
		return getAjaxResult(courseOnlineService.updateCourse(course), AjaxResultModel.SUCCESS);
	}
	
	/**
	 * 跳转至课程详细页面
	 * @param model
	 * @param request
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/detail" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toCourseOnlineDetail(HttpServletRequest request, @RequestParam String courseId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("courseId", courseId);
		Map<String, Object> courseOnlineInfo = courseOnlineService.queryCourseOnlineInfo(paramsMap);

		paramsMap.put("itemId", courseId);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
		// 获取线上课程对应分类
		List<Map<String, Object>> selectedClassificationList = classificationService
				.querySelectedClassificationByItemId(paramsMap);

		paramsMap.put("userId", getCookieUserId(request));
		paramsMap.put("accountId", getCookieAccount(request));
		paramsMap.put("courseId", courseId);
		// 获取课程学习人数


		PageRequest pageRequest = new PageRequest(1);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);

		resultMap.put("userId", getCookieUserId(request));
		resultMap.put("courseMap", courseOnlineInfo);
		resultMap.put("selectedClassificationList", selectedClassificationList);
		resultMap.put("traineeCount", courseService.courseLearnCount(paramsMap));
		resultMap.put("quoteList",
				CommonUtils.outPrintJsonMapForPage(courseOnlineService.findQuoteCoursewareList(courseId, pageRequest))
						.get("dataList"));
		resultMap.put("coursewareCount",
				CommonUtils.outPrintJsonMapForPage(courseOnlineService.findQuoteCoursewareList(courseId, pageRequest))
						.get("totalRow"));

		return getAjaxResult(resultMap, AjaxResultModel.SUCCESS);

		// return "modules/course/course_online_detail";
		// return "modules/course/course_lecturer_detail";
	}

	/**
	 * 校验课程名称是否重复
	 */
	@RequestMapping("/validate_course_name" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateCourseName(@RequestParam String courseName, HttpServletRequest request) {

		return getAjaxResult(courseOnlineService.validateCourseName(courseName, getCookieAccount(request)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 复制课程
	 */
	@RequestMapping("/copy_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel copyCourse(@RequestParam String courseId, @RequestParam String courseName,
			HttpServletRequest request) {

		courseOnlineService.copyCourse(courseId, courseName, getCookieUserId(request), getCookieAccount(request),
				getCookieGroupId(request));

		return getAjaxResult("", AjaxResultModel.SUCCESS);
	}

	/**
	 * 分享课程
	 */
	@RequestMapping("/share_course_owner" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel shareCourse(@RequestParam String courseId, @RequestParam String courseOwner,
			HttpServletRequest request) {

		return getAjaxResult(courseOnlineService.shareCourse(courseId, courseOwner, getCookieUserId(request)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 获取课程引用课件列表
	 */
	@RequestMapping("/find_quote_courseware" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel findQuoteCoursewareList(@RequestParam String courseId, @RequestParam Integer pageNo,
			HttpServletRequest request) {

		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);

		return getAjaxResult(
				CommonUtils.outPrintJsonMapForPage(courseOnlineService.findQuoteCoursewareList(courseId, pageRequest)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 课程预览
	 */
	@RequestMapping("/preview" + Constants.URL_SUFFIX)
	public String toViewerCourseDetail(@RequestParam String courseId, @RequestParam(required = false) String groupId,
			Model model, HttpServletRequest request) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("courseId", courseId);
		// params.put("groupId", groupId);
		Map<String, Object> courseMap = courseService.queryTraineeCourseInfo(params);
		// 查询课程是否关注
		Boolean hasFavor = courseService.hasFavoriteCourse(params);
		// 获取课程评分
		int starNum = courseService.courseApraiseAvg(params);
		// 获取课程学习人数
		int traineeCount = courseService.courseLearnCount(params);
		// 获取课程学习人数
		int appraiseCount = courseService.courseApraiseCount(params);
		// 判断学员是否已选择该课程
		Boolean hasSelect = courseService.traineeHasSelectCourse(params);
		courseMap.put("hasSelect", hasSelect.toString());
		courseMap.put("starNum", starNum);
		courseMap.put("traineeCount", traineeCount);
		courseMap.put("appraiseCount", appraiseCount);
		courseMap.put("hasFavor", hasFavor.toString());
		courseMap.put("groupId", groupId);
		System.err.println(getClass().getName()+"\t===433===courseMap:\t"+courseMap.toString());
		model.addAttribute("courseMap", courseMap);

		return "modules/course/course_online_preview";
	}

	/**
	 * 课程预览
	 */
	// @RequestMapping("/preview_course" + Constants.URL_SUFFIX)
	// @ResponseBody
	// public AjaxResultModel toCoursePreview(@RequestParam String courseId,
	// HttpServletRequest request) {
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("courseId", courseId);
	// Map<String, Object> courseMap = courseOnlineService.queryCourseOnlineProfile(params);
	//
	// return getAjaxResult(courseMap, AjaxResultModel.SUCCESS);
	// }
	/**
	 * 更新课程编辑状态
	 */
	@RequestMapping("/update_edit_state" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateEditState(@RequestParam String courseId, @RequestParam Integer isEdit,
			HttpServletRequest request) {

		Course course = new Course();
		course.setId(courseId);
		course.setIsEdit(isEdit);
		course.setUpdateId(getCookieUserId(request));
		course.setUpdateTime(new Date());

		return getAjaxResult(courseOnlineService.updateCourse(course), AjaxResultModel.SUCCESS);
	}

	/**
	 * 
	 */
	@RequestMapping("/getBaseInfo" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getBaseInfo(@RequestParam String courseId, @RequestParam String courseName,
			HttpServletRequest request) {

		int num = 1;
		List<Integer> numList = new ArrayList<Integer>();
		List<Course> courseList = courseOnlineService.queryCourseByCourseName(courseName, getCookieAccount(request));
		if (courseList.size() > 1) {
			String rex = "[()]+";
			for (int i = 0; i < courseList.size(); i++) {
				String[] str = courseList.get(i).getCourseName().split(rex);
				if (null != str && str.length > 1) {
					numList.add(Integer.parseInt(str[str.length - 1]) + 1);
				}
			}
			if (numList.size()>0) {
				Collections.sort(numList);
				num = numList.get(numList.size() - 1);
			}	
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("num", num);
		jsonMap.put("course", courseOnlineService.queryCourse(courseId));

		return getAjaxResult(jsonMap, AjaxResultModel.SUCCESS);
	}

}
