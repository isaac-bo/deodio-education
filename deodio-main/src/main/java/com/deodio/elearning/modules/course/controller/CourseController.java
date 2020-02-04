package com.deodio.elearning.modules.course.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.components.thread.EmailThread;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.DesUtil;
import com.deodio.core.utils.JsonUtils;
import com.deodio.elearning.commons.service.GroupRoleService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.course.service.CourseOfflineService;
import com.deodio.elearning.modules.course.service.CourseOnlineService;
import com.deodio.elearning.modules.course.service.CourseOnliveService;
import com.deodio.elearning.modules.course.service.CourseService;
import com.deodio.elearning.modules.group.service.GroupService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseHomework;
import com.deodio.elearning.persistence.model.CourseMaterial;
import com.deodio.elearning.persistence.model.CourseNotice;
import com.deodio.elearning.persistence.model.CourseQuiz;
import com.deodio.elearning.persistence.model.CourseRegisteRule;
import com.deodio.elearning.persistence.model.CourseRelated;
import com.deodio.elearning.persistence.model.CourseSurvey;
import com.deodio.elearning.persistence.model.CourseUserRel;
import com.deodio.elearning.persistence.model.MyCourseExt;
import com.deodio.elearning.persistence.model.TraineeCourse;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.customize.TraineeFavoriteBo;
import com.deodio.elearning.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseOnlineService courseOnlineService;

	@Autowired
	private CourseOfflineService courseOfflineService;

	@Autowired
	private CourseOnliveService courseOnliveService;

	@Autowired
	private GroupRoleService groupRoleService;

	@Autowired
	private AccountService accountService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private GroupService groupService;

	@RequestMapping("/course/setting" + Constants.URL_SUFFIX)
	public String toCourseSetting(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam String courseId, @RequestParam(required = false) Integer courseType) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseType", courseType);
		// model.addAttribute("course",courseService.getCourseById(courseId));
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("courseId", courseId);
		try {
			CookieUtils.setCookie(DConstants.COOKIE_COURSE_ID, URLEncoder.encode(courseId, "UTF-8"), 604800, response,
					CommonUtils.COOKIE_DOMAIN);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map<String, Object> courseMap = new HashMap<String, Object>();
		if (DConstants.COURSE_TYPE_ONLINE.equals(courseType)) {
			courseMap = courseOnlineService.queryCourseOnlineProfile(paramsMap);

		} else if (DConstants.COURSE_TYPE_OFFLINE.equals(courseType)) {
			courseMap = courseOfflineService.queryCourseOfflineProfile(paramsMap);
		} else if (DConstants.COURSE_TYPE_ONLIVE.equals(courseType)) {
			courseMap = courseOnliveService.queryCourseOnliveProfile(paramsMap);
		}
		try {
			Date startTime = (Date) courseMap.get("start_time");
			model.addAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").format(startTime));
			Date endTime = (Date) courseMap.get("expire_time");
			model.addAttribute("endTime", new SimpleDateFormat("yyyy-MM-dd").format(endTime));
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("groupRoleFuncList", groupService
				.getGroupRoleFuncByGrouypId((String) CookieUtils.getCookie(request, DConstants.COOKIE_GROUP_ID)));
		model.addAttribute("courseMap", courseMap);
		return "modules/course/course_setting";
	}

	@RequestMapping("/course/shortcut" + Constants.URL_SUFFIX)
	public String toCourseShortcutList(Model model, HttpServletRequest request,
			@RequestParam(required = false) String groupId, @RequestParam(required = false) String groupRoleId) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		// 获取角色类别
		List<Map<String, Object>> roles = groupRoleService.getGroupRoleModelList(params);
		// 获取用户对应小组
		List<Map<String, Object>> groupList = groupRoleService.queryGroupListForUser(params);

		model.addAttribute("groupId", groupId);
		model.addAttribute("groupRoleId", groupRoleId);
		model.addAttribute("roleList", roles);
		model.addAttribute("groupList", groupList);
		return "modules/course/course_shortcut_list";
	}

	/**
	 * 删除课程（只有创建者可以删除）
	 * 
	 * @param model
	 * @param courseName
	 *            课程名称
	 * @param courseId
	 *            课程编号
	 * @return
	 */
	@RequestMapping("/course/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteCourse(HttpServletRequest request, @RequestParam String courseId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("courseId", courseId);
			boolean result = courseService.deleteCourse(params);
			if (result) {
				arm.setStatus(AjaxResultModel.SUCCESS);
			} else {
				arm.setStatus(AjaxResultModel.FAIL);
			}
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	/**
	 * 判断课程名是否存在
	 * 
	 * @param model
	 * @param courseName
	 *            课程名称
	 * @param courseId
	 *            课程编号
	 * @return
	 */
	@RequestMapping("/course/shortcut/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseShortcutList(Model model, @RequestParam String groupRole,
			@RequestParam(required = false) String keywords, @RequestParam Integer pageNo,
			@RequestParam(required = false) Integer pageSize, HttpServletRequest request,
			@RequestParam String groupId) {
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("groupRole", groupRole);
		paramsMap.put("groupId", groupId);
		paramsMap.put("keywords", keywords);
		paramsMap.put("userId", userId);
		paramsMap.put("accountId", accountId);
		paramsMap.put("pagination", pageRequest);
		List<Map<String, Object>> list = courseService.findCourseShortCutList(paramsMap);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(jsonMap);
		return arm;
	}

	/**
	 * 判断课程名是否存在
	 * 
	 * @param model
	 * @param courseName
	 *            课程名称
	 * @param courseId
	 *            课程编号
	 * @return
	 */
	@RequestMapping("/course/isExistName" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel isExistName(Model model, @RequestParam String courseName,
			@RequestParam(required = false) String courseId, @RequestParam(required = false) Integer courseType) {
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("courseName", courseName);
		paramsMap.put("courseId", courseId);
		paramsMap.put("courseType", courseType);
		List<Map<String, Object>> list = courseService.isExistName(paramsMap);
		arm.setData(list);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 发布课程
	 * 
	 * @param model
	 * @param courseId
	 *            课程编号
	 * @return
	 */
	@RequestMapping("/course/publish" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel publishCourse(Model model, @RequestParam String courseId) {
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("courseId", courseId);
		Integer rowCount = courseService.publishCourse(paramsMap);
		Course course = courseService.selectyCourseByPrimaryId(courseId);
		List<CourseUserRel> list = courseService.selectyCourseUserRel(courseId);
		if (null != list && list.size() > 0) {
			List<String> content = new ArrayList<String>();
			content.add("培训" + course.getCourseName() + "课程已重新发布，请您进入培训详细页进行报名.");
			for (int i = 0; i < list.size(); i++) {
				CourseUserRel courseUserRel = list.get(i);
				EmailThread emailThread = new EmailThread(courseUserRel.getUserMail(), content, "课程重新发布提醒", true);
				taskExecutor.execute(emailThread);
			}
		}
		arm.setData(rowCount);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 取消发布课程
	 * 
	 * @param model
	 * @param courseId
	 *            课程编号
	 * @return
	 */
	@RequestMapping("/course/cancelPublish" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel cancelPublishCourse(Model model, @RequestParam String courseId) {
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("courseId", courseId);
		Integer rowCount = courseService.cancelPublishCourse(paramsMap);
		Course course = courseService.selectyCourseByPrimaryId(courseId);
		if (2==course.getCourseType()) {// 线下课程给报名学员发邮件
			List<String> content = new ArrayList<String>();
			content.add(course.getCourseName() + "课程培训已取消");
			List<CourseUserRel> list = courseService.selectyCourseUserRel(courseId);
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					CourseUserRel courseUserRel = list.get(i);
					EmailThread emailThread = new EmailThread(courseUserRel.getUserMail(), content, "课程取消提醒", true);
					taskExecutor.execute(emailThread);
				}
				// 删除已报名的学员
				courseService.deleteCourseUserRel(courseId);
			}
		}
		arm.setData(rowCount);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * @param keyword
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/homework/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getHomeworkList(@RequestParam String keyword, @RequestParam String courseId,
			@RequestParam Integer pageNo, @RequestParam(required = false) Integer pageSize, HttpServletRequest request)
					throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();

		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseHomeworkList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 获取课程作业数据
	 * 
	 * @param homeworkId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/homework/load_data" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryHomeworkById(@RequestParam String homeworkId, HttpServletRequest request)
			throws DeodioException {

		// String userId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_USER_ID);
		// String accountId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> homeworkMap = courseService.queryCourseHomeworkByPk(homeworkId);

		arm.setData(homeworkMap);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 删除课程作业
	 * 
	 * @Title: deleteGroup
	 * @param id
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/course/homework/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteHomework(@RequestParam String homeworkId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			courseService.delCourseHomework(homeworkId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 保存课程作业
	 * 
	 * @param request
	 * @param homeworkJson
	 * @param attachId
	 * @return
	 */
	@RequestMapping("/course/homework/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitHomework(HttpServletRequest request, @RequestParam String homeworkJson,
			@RequestParam String attachId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
			CourseHomework record = gson.fromJson(homeworkJson, new TypeToken<CourseHomework>() {
			}.getType());
			courseService.updateCourseHomework(record, attachId, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 获取课程消息列表
	 * 
	 * @param keyword
	 * @param courseId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/notice/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getNoticeList(@RequestParam(required = false) String keyword, @RequestParam String courseId,
			@RequestParam Integer pageNo, @RequestParam(required = false) Integer pageSize, HttpServletRequest request)
					throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();

		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);

		}
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseNoticeList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		jsonMap.put("pageSize", pageRequest.getPageSize());
		jsonMap.put("currentSize", dataList.size());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 通过主键获取课程消息信息
	 * 
	 * @param noticeId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/notice/load_data" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryNoticeById(@RequestParam String noticeId, HttpServletRequest request)
			throws DeodioException {

		// String userId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_USER_ID);
		// String accountId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> noticeMap = courseService.queryCourseNoticeByPk(noticeId);
		arm.setData(noticeMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 校验课程消息名称
	 * 
	 * @param noticeId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/notice/checkNoticeName" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkNoticeName(@RequestParam String noticeName) throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		boolean isExist = courseService.checkNoticeName(noticeName);
		arm.setData(isExist);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 删除课程通知
	 * 
	 * @param noticeId
	 * @return
	 */
	@RequestMapping("/course/notice/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteNotice(@RequestParam String noticeId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			courseService.delCourseNotice(noticeId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 提交课程消息
	 * 
	 * @param request
	 * @param noticeJson
	 * @param attachId
	 * @return
	 */
	@RequestMapping("/course/notice/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitNotice(HttpServletRequest request, @RequestParam String noticeJson) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
			CourseNotice record = gson.fromJson(noticeJson, new TypeToken<CourseNotice>() {
			}.getType());
			courseService.updateCourseNotice(record, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 获取课程附件列表
	 * 
	 * @param keyword
	 * @param courseId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/material/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getMaterialList(@RequestParam String keyword, @RequestParam String courseId,
			@RequestParam Integer pageNo, @RequestParam(required = false) Integer pageSize, HttpServletRequest request)
					throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();

		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseMaterialList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 删除课程附件
	 * 
	 * @param materialId
	 * @return
	 */
	@RequestMapping("/course/material/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteMaterial(@RequestParam String materialId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			courseService.delCourseMaterial(materialId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 通过主键获取课程文件信息
	 * 
	 * @param materialId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/material/load_data" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryMaterialById(@RequestParam String materialId, HttpServletRequest request)
			throws DeodioException {

		// String userId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_USER_ID);
		// String accountId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		CourseMaterial noticeMap = courseService.queryCourseMaterialByPk(materialId);
		arm.setData(noticeMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 提交课程附件
	 * 
	 * @param request
	 * @param noticeJson
	 * @param attachId
	 * @return
	 */
	@RequestMapping("/course/material/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitMaterial(HttpServletRequest request, @RequestParam String materialJson,
			@RequestParam String attachId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Gson gson = new Gson();
			CourseMaterial record = gson.fromJson(materialJson, new TypeToken<CourseMaterial>() {
			}.getType());
			courseService.updateCourseMaterial(record, userId, accountId, attachId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 查询课程考试信息
	 * 
	 * @param quizId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/quiz/load_data" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryQuizById(@RequestParam String quizId, HttpServletRequest request)
			throws DeodioException {

		// String userId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_USER_ID);
		// String accountId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		CourseQuiz quizMap = courseService.queryCourseQuizByPk(quizId);
		arm.setData(quizMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 删除课程考试列表
	 * 
	 * @param quizId
	 * @return
	 */
	@RequestMapping("/course/quiz/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteQuiz(@RequestParam String quizId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			courseService.delCourseQuiz(quizId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	// /**
	// * 提交课程考试
	// *
	// * @param request
	// * @param noticeJson
	// * @param attachId
	// * @return
	// */
	// @RequestMapping("/course/quiz/submit" + Constants.URL_SUFFIX)
	// @ResponseBody
	// public AjaxResultModel submitQuiz(HttpServletRequest request,
	// @RequestParam String quizJson) {
	// AjaxResultModel arm = new AjaxResultModel();
	// String userId = (String) CookieUtils.getCookie(request,
	// DConstants.COOKIE_USER_ID);
	// String accountId = (String) CookieUtils.getCookie(request,
	// DConstants.COOKIE_ACCOUNT_ID);
	// String courseId = (String) CookieUtils.getCookie(request,
	// DConstants.COOKIE_COURSE_ID);
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("courseId", courseId);
	// params.put("accountId", accountId);
	// courseService.delAllCourseQuizByCourseId(params);
	// if (com.deodio.core.utils.StringUtils.isNotEmpty(quizJson)) {
	// System.err.println(getClass().getName() + "\t quizJson:\t" + quizJson);
	// try {
	// Gson gson = new
	// GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
	// String[] quizData = quizJson.split(Constants.STRING_ROW);
	// for (int index = 0; index < quizData.length; index++) {
	// CourseQuiz record = gson.fromJson(quizData[index], new
	// TypeToken<CourseQuiz>() {
	// }.getType());
	// courseService.updateCourseQuiz(record, userId, accountId);
	// }
	// // courseService.updateCourseQuiz(record, userId, accountId);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// arm.setStatus(AjaxResultModel.SUCCESS);
	// return arm;
	// }
	/**
	 * 课程管理添加问卷调查
	 * 
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/course/quiz/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitCourseQuiz(HttpServletRequest request,
			@RequestParam(value = "quizJson", required = false) String quizJson,
			@RequestParam(value = "subType", required = false) String subType,
			@RequestParam(value = "courseId", required = false) String courseId) {

		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		courseService.saveBantchCourseQuiz(quizJson.split(Constants.STRING_ROW), userId, accountId, courseId);

		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取测验成绩列表
	 * 
	 * @param keyword
	 * @param courseId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/test_quiz/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getTestQuizList(@RequestParam Integer pageNo, @RequestParam String keyword,
			@RequestParam String courseId, HttpServletRequest request) throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> jsonMap = courseService.findCourseTestQuizList(courseId, keyword, pageNo);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取课程考试列表
	 * 
	 * @param keyword
	 * @param courseId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/quiz/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuizList(@RequestParam Integer pageNo, @RequestParam String keyword,
			@RequestParam String courseId, HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseQuizList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 创建课程考试时，获取已添加课程考试列表
	 * 
	 * @param keyword
	 * @param courseId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/quiz/load_added_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getAddedQuizList(@RequestParam String keyword, @RequestParam String courseId,
			HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);

		List<Map<String, Object>> dataList = courseService.getCourseQuizList(params);
		System.err.println(getClass().getName() + ":\tgetAddedQuizList.dataList:\t" + dataList.toString());
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 查询课程问卷调查
	 * 
	 * @param surveyId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/survey/load_data" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel querySurveyById(@RequestParam String surveyId, HttpServletRequest request)
			throws DeodioException {

		// String userId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_USER_ID);
		// String accountId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		CourseSurvey surveyMap = courseService.queryCourseSurveyByPk(surveyId);
		arm.setData(surveyMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 删除问卷调查
	 * 
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/course/survey/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteSurvey(@RequestParam String id) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			courseService.delCourseSurvey(id);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 课程管理添加问卷调查
	 * 
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/course/survey/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitSurvey(HttpServletRequest request,
			@RequestParam(value = "surveyJson", required = false) String surveyJson,
			@RequestParam(value = "subType", required = false) String subType,
			@RequestParam(value = "courseId", required = false) String courseId,
			@RequestParam(value = "courseSurveyId", required = false) String courseSurveyId) {

		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		try {
			courseService.saveBantchCourseSurvey(surveyJson.split(Constants.STRING_ROW), userId, accountId, courseId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arm.setStatus(AjaxResultModel.FAIL);
			arm.setExceptionMessage(e.getMessage());
		}
		
		return arm;
	}

	/**
	 * 获取课程问卷调查列表
	 * 
	 * @param keyword
	 * @param courseId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/survey/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getSurveyList(@RequestParam Integer pageNo, @RequestParam String keyword,
			@RequestParam String courseId, HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseSurveyList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取已添加课程问卷调查列表
	 * 
	 * @param keyword
	 * @param courseId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/survey/load_added_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getAddedSurveyList(@RequestParam String keyword, @RequestParam String courseId,
			HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		List<Map<String, Object>> dataList = courseService.getCourseSurveyList(params);
		System.err.println(getClass().getName() + "\tdataList:\t" + dataList.toString());
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取课程信息
	 * 
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel findCourseDataListByKeywords(@RequestParam String keywords, @RequestParam Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer isPublish,
			@RequestParam(required = false) Integer isNotEdit, HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();

		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		params.put("isPublish", isPublish);
		params.put("isNotEdit", isNotEdit);
		List<Map<String, Object>> dataList = courseService.findCourseDataListByKeywords(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 查询课程相关
	 * 
	 * @param relatedId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/related/load_data" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseRelatedById(@RequestParam String relatedId, HttpServletRequest request)
			throws DeodioException {

		// String userId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_USER_ID);
		// String accountId = (String)CookieUtils.getCookie(request,
		// DConstants.COOKIE_ACCOUNT_ID);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("relatedId", relatedId);

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> relatedMap = courseService.queryCourseRelatedByPk(params);
		arm.setData(relatedMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 删除问卷调查
	 * 
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/course/related/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteCourseRelated(@RequestParam String relatedId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			courseService.delCourseRelated(relatedId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 提交课程相关
	 * 
	 * @param request
	 * @param surveyJson
	 * @return
	 */
	@RequestMapping("/course/related/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitCourseRelated(HttpServletRequest request, @RequestParam String relatedJson) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Gson gson = new Gson();
			String[] relatedCourses = relatedJson.split(Constants.STRING_ROW);
			for (int index = 0; index < relatedCourses.length; index++) {
				CourseRelated record = gson.fromJson(relatedCourses[index], new TypeToken<CourseRelated>() {
				}.getType());
				courseService.updateCourseRelated(record, userId, accountId);
			}

			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 查询课程相关列表
	 * 
	 * @param keywords
	 * @param courseId
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/related/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getRelatedCourseList(@RequestParam String keywords, @RequestParam String courseId,
			@RequestParam(required = false) String relatedType, HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		if (relatedType != null) {
			params.put("relatedType", Integer.valueOf(relatedType));// 关联属性 0：必须
																	// 1：推荐
		}

		List<Map<String, Object>> dataList = courseService.getCourseRelatedList(params);
		System.err.println(getClass().getName() + "\t====995====dataList:\t" + dataList.toString());
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 课程人员激活
	 * 
	 * @param request
	 * @param relatedId
	 * @return
	 */
	@RequestMapping("/course/trainee/active" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel activeCourseTrainee(HttpServletRequest request, @RequestParam String relatedId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("relatedId", relatedId);
			courseService.activeCourseTrainees(params);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 课程人员替补
	 * 
	 * @param request
	 * @param relatedId
	 * @return
	 */
	@RequestMapping("/course/trainee/substitute" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel substituteCourseTrainee(HttpServletRequest request, @RequestParam String relatedId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("relatedId", relatedId);
			courseService.updateCourseTraineesUserStatus(params, Constants.COURSE_TRAINEE_STATUS_SUBSTITUTE);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 课程人员暂停
	 * 
	 * @param request
	 * @param relatedId
	 * @return
	 */
	@RequestMapping("/course/trainee/suspend" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel suspendCourseTrainee(HttpServletRequest request, @RequestParam String relatedId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("relatedId", relatedId);
			courseService.suspendCourseTrainees(params);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/course/trainee/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteCourseTrainee(HttpServletRequest request, @RequestParam String relatedId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("relatedId", relatedId);
			courseService.deleteCourseTrainees(params);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/course/trainee/num" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel numCourseTrainee(Model model, HttpServletRequest request,
			@RequestParam(required = false) String courseId, @RequestParam(required = false) String itemId) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("courseId", courseId);
		paramsMap.put("itemId", itemId);
		Integer count = courseService.numCourseTrainee(paramsMap);
		arm.setData(count);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/course/registed_users/load_data" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseRegisteRule(Model model, HttpServletRequest request,
			@RequestParam String courseId) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("courseId", courseId);
		CourseRegisteRule courseRegisteRule = courseService.queryCourseRegisteRule(paramsMap);
		arm.setData(courseRegisteRule);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/course/registed_users/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateCourseRegisteRule(Model model, HttpServletRequest request,
			@RequestParam String dataJson) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
			CourseRegisteRule courseRegisteRule = gson.fromJson(dataJson, new TypeToken<CourseRegisteRule>() {
			}.getType());
			courseService.updateCourseRegisteRule(courseRegisteRule, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 查询学员课程列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/course/course_viewer/list" + Constants.URL_SUFFIX)
	public String toViewerCourseList(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String um) {
		if (StringUtils.isNotBlank(um)) {// 被邀请学习课程的学员
			CookieUtils.deleteCookie(response, DConstants.COOKIE_USER_ID, CommonUtils.COOKIE_DOMAIN);
			// CookieUtils.deleteCookie(response, DConstants.COOKIE_NICK_NAME,
			// CommonUtils.COOKIE_DOMAIN);
			String userMail = DesUtil.decrypt(um);
			UserModel userModel = accountService.getUserModeInforByCondition(userMail);
			CookieUtils.setCookie(DConstants.COOKIE_USER_ID, userModel.getId(), 604800, response,
					CommonUtils.COOKIE_DOMAIN);
			CookieUtils.setCookie(DConstants.COOKIE_NICK_NAME, " ", 604800, response, CommonUtils.COOKIE_DOMAIN);
		}
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		return "modules/course/course_viewer_list";
	}

	/**
	 * 查询学员线上课程详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/course/course_viewer/online_detail" + Constants.URL_SUFFIX)
	public String toViewerCourseDetail(Model model, HttpServletRequest request, @RequestParam String courseId,
			@RequestParam(required = false) String groupId) {

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
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		Date nowDate = new Date();
		Date expireTime=(Date) courseMap.get("expire_time");
		Date startTime=(Date) courseMap.get("start_time");
		if (nowDate.after(expireTime)) {
			courseMap.put("courseValid", "已过期");
		}else {
			if(startTime.before(nowDate)) {
				courseMap.put("courseValid", "未开始 ");
			}else {
				courseMap.put("courseValid", "进行中");
			}	
		}

		System.err.println(getClass().getName()+"\t courseMap :\t"+courseMap.toString());
		model.addAttribute("courseMap", courseMap);

		return "modules/course/course_viewer_online_detail";
	}
	/**
	 * @param model
	 * @param request
	 * @param courseId
	 * @param groupId
	 * @return
	 * 线下课程详细
	 */
	@RequestMapping("/course/course_viewer/offline_detail" + Constants.URL_SUFFIX)
	public String toViewerCourseOfflineDetail(Model model, HttpServletRequest request, @RequestParam String courseId,
			@RequestParam(required = false) String groupId) {

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
		model.addAttribute("courseMap", courseMap);

		return "modules/course/course_viewer_offline_detail";
	}
	/**
	 * 获取学员课程课时列表
	 * 
	 * @param model
	 * @param request
	 * @param dataJson
	 * @return
	 */
	@RequestMapping("/course/course_viewer/item_list" + Constants.URL_SUFFIX)
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
	 * 获取学员课程列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/course/load_course_viewer_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseList(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String flag, @RequestParam(required = false) Integer pageNo,
			HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();

		MyCourseExt flagModel = gson.fromJson(flag, new TypeToken<MyCourseExt>() {
		}.getType());

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> params = new HashMap<String, Object>();

		PageRequest pageRequest = new PageRequest(pageNo);

		// 按学习时间排序
		if (flagModel.getCondition1().equals("0") && flagModel.getCondition1() != null) {
			params.put("studyTime", flagModel.getCondition1());
		}
		// 按加入时间排序
		if (flagModel.getCondition1().equals("1") && flagModel.getCondition1() != null) {
			params.put("purchaseTime", flagModel.getCondition1());
		}
		// 全部
		// if(flagModel.getCondition2().equals("0") && flagModel.getCondition2()
		// != null){
		// params.put("all", 0);
		// }
		// 付费
		if (flagModel.getCondition2().equals("1") && flagModel.getCondition2() != null) {
			params.put("isCharge", flagModel.getCondition2());
		}
		// 即将过期
		if (flagModel.getCondition2().equals("2") && flagModel.getCondition2() != null) {
			params.put("deadlineTime", flagModel.getCondition2());
		}

		params.put("pagination", pageRequest);
		params.put("userId", userId);
		params.put("accountId", accountId);
		List<Map<String, Object>> dataList = courseService.findCourseViewerList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	@RequestMapping("/course/load_package_course_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getPackageCourseList(@RequestParam(required = false) String flag,
			@RequestParam(required = false) Integer pageNo, HttpServletRequest request) throws DeodioException {
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		;
		MyCourseExt flagModel = gson.fromJson(flag, new TypeToken<MyCourseExt>() {
		}.getType());

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> params = new HashMap<String, Object>();

		PageRequest pageRequest = new PageRequest(pageNo);

		// 按学习时间排序
		if (flagModel.getCondition1().equals("0") && flagModel.getCondition1() != null) {
			params.put("studyTime", flagModel.getCondition1());
		}
		// 按加入时间排序
		if (flagModel.getCondition1().equals("1") && flagModel.getCondition1() != null) {
			params.put("purchaseTime", flagModel.getCondition1());
		}
		// 付费
		if (flagModel.getCondition2().equals("1") && flagModel.getCondition2() != null) {
			params.put("isCharge", flagModel.getCondition2());
		}
		// 即将过期
		if (flagModel.getCondition2().equals("2") && flagModel.getCondition2() != null) {
			params.put("deadlineTime", flagModel.getCondition2());
		}

		params.put("pagination", pageRequest);
		params.put("userId", userId);
		params.put("accountId", accountId);
		List<Map<String, Object>> dataList = courseService.findPackageCourseList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 获取学员课程列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/course/load_course_item_by_offset" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseItemByOffset(@RequestParam(required = false) Integer offset,
			@RequestParam(required = false) Boolean isNext, @RequestParam(required = false) Boolean isPre,
			@RequestParam(required = false) String itemId, @RequestParam String courseId,
			@RequestParam Integer itemSort, HttpServletRequest request) throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("offset", offset);
			params.put("isNext", isNext);
			params.put("isPre", isPre);
			params.put("courseId", courseId);
			params.put("itemSort", itemSort);
			params.put("itemId", itemId);
			Map<String, Object> offsetItem = courseService.queryCourseItemOffset(params);
			arm.setData(offsetItem);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	@RequestMapping("/course/course_viewer/complete_item" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel completeCourseItem(@RequestParam String itemId, @RequestParam String courseId,
			HttpServletRequest request) throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("offset", offset);
			// params.put("isNext", isNext);
			// params.put("isPre", isPre);
			// params.put("courseId", courseId);
			// params.put("itemSort", itemSort);
			params.put("itemId", itemId);
			Map<String, Object> offsetItem = courseService.queryCourseItemOffset(params);
			arm.setData(offsetItem);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	@RequestMapping("/course/course_viewer/select_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel traineeSelectCourseToLearn(@RequestParam String queryJson, HttpServletRequest request)
			throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		TraineeCourse traineeCourse = JsonUtils.jsonToObject(queryJson, TraineeCourse.class);
		try {
			if (null == traineeCourse) {
				traineeCourse = new TraineeCourse();
			}
			traineeCourse.setAccountId(accountId);
			traineeCourse.setTraineeId(userId);
			courseService.saveTraineeSelectCourseToLearn(traineeCourse, accountId, userId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	/**
	 * 关注课程
	 * 
	 * @param queryJson
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/favorite/save" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseFavorite(@RequestParam String queryJson, HttpServletRequest request)
			throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		TraineeFavoriteBo traineeFavoriteBo = JsonUtils.jsonToObject(queryJson, TraineeFavoriteBo.class);
		try {
			traineeFavoriteBo.setAccountId(accountId);
			traineeFavoriteBo.setTraineeId(userId);
			courseService.saveCourseFavorite(traineeFavoriteBo, userId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	/**
	 * 学员课程课后练习页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/course/course_viewer/exercise" + Constants.URL_SUFFIX)
	public String toViewerCourseExercise(Model model, HttpServletRequest request, @RequestParam String itemId,
			@RequestParam String courseId, @RequestParam String type) {

		System.err.println(getClass().getName()+"\t courseId :\t"+courseId
				+"\t type :\t"+type
				+"\t itemId :\t"+itemId);
		String groupId=getCookieGroupId(request);		
		String homeworkPage = "";
		String quizPage = "";
		String surveyPage = "";
		String attachPaeg = "";
		String resultPage = "";
		String remarkTemPage = "modules/courseselect/course_remark_tem";

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		Map<String, Object> params = new HashMap<String, Object>();

		if (DConstants.TRAINEE_COURSE_ATTACH.equals(type)) {
			resultPage = attachPaeg;
		} else if (DConstants.TRAINEE_COURSE_HOMEWORK.equals(type)) {
			resultPage = homeworkPage;
		} else if (DConstants.TRAINEE_COURSE_QUIZ.equals(type)) {
			resultPage = quizPage;
		} else if (DConstants.TRAINEE_COURSE_SURVEY.equals(type)) {
			resultPage = surveyPage;
		} else if (DConstants.TRAINEE_COURSE_APPRAISE.equals(type)) {
			resultPage = remarkTemPage;
		}

		params.put("type", type);
		params.put("itemId", itemId);
		params.put("courseId", courseId);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("groupId", groupId);
		model.addAttribute("map", params);
		// return "modules/course/course_viewer_quizs";
		return resultPage;

	}

	/**
	 * 获取测验成绩列表
	 * 
	 * @param courseQuizId
	 * @param courseId
	 * @param request
	 * @param typeValue
	 * @param passValue
	 * @param keyValue
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/test_quiz/managerquiz" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getManagerTestQuizList(@RequestParam Integer pageNo, @RequestParam String passValue,
			@RequestParam String typeValue, @RequestParam String keyValue, @RequestParam String courseId,
			@RequestParam String courseQuizId, HttpServletRequest request) throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		// is_pass 0表示未通过 1表示通过 2表示全部
		Integer is_pass = Integer.parseInt(passValue);
		// status 1表示提交状态 2表示已阅 0表示全部
		Integer status = Integer.parseInt(typeValue);
		if (is_pass == 2) {// 2表示查询全部全部
			is_pass = null;
		}
		if (status == 0) {
			status = null;
		}
		Integer scoreWord = null;

		if ("".equals(keyValue)) {
			keyValue = null;
		} else {
			if (CommonUtils.isInteger(keyValue)) {
				try {
					scoreWord = Integer.parseInt(keyValue);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		params.put("is_pass", is_pass);
		params.put("status", status);
		params.put("scoreWord", scoreWord);
		params.put("keyword", keyValue);
		params.put("courseId", courseId);
		params.put("quizId", courseQuizId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseQuizManagerList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取成绩管理列表
	 * 
	 * @param courseQuizId
	 * @param courseId
	 * @param request
	 * @param typeValue
	 * @param passValue
	 * @param keyValue
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/quiz/managerquiz" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getManagerQuizList(@RequestParam Integer pageNo, @RequestParam String passValue,
			@RequestParam String typeValue, @RequestParam String keyValue, @RequestParam String courseId,
			@RequestParam String courseQuizId, HttpServletRequest request) throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		// is_pass 0表示未通过 1表示通过 2表示全部
		Integer is_pass = Integer.parseInt(passValue);
		// status 1表示提交状态 2表示已阅 0表示全部
		Integer status = Integer.parseInt(typeValue);
		if (is_pass == 2) {// 2表示查询全部全部
			is_pass = null;
		}
		if (status == 0) {
			status = null;
		}
		Integer scoreWord = null;

		if ("".equals(keyValue)) {
			keyValue = null;
		} else {
			if (CommonUtils.isInteger(keyValue)) {
				try {
					scoreWord = Integer.parseInt(keyValue);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		params.put("is_pass", is_pass);
		params.put("status", status);
		params.put("scoreWord", scoreWord);
		params.put("keyword", keyValue);
		params.put("courseId", courseId);
		params.put("quizId", courseQuizId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseQuizManagerList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取调查结果列表
	 * 
	 * @param courseSurveyId
	 * @param courseId
	 * @param request
	 * @param keyValue
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/survey/managersurvey" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getManagerSurveyList(@RequestParam Integer pageNo, @RequestParam String courseSurveyId,
			@RequestParam String courseId, HttpServletRequest request, @RequestParam String keyValue)
					throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		if ("".equals(keyValue)) {
			keyValue = null;
		}
		params.put("keyword", keyValue);
		params.put("courseId", courseId);
		params.put("surveyId", courseSurveyId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseSurveyManagerList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取作业管理列表
	 * 
	 * @param courseHomeworkId
	 * @param courseId
	 * @param request
	 * @param keyValue
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/homework/managerhomework" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getManagerHomeworkList(@RequestParam Integer pageNo, @RequestParam String courseHomeworkId,
			@RequestParam String courseId, @RequestParam String keyValue, HttpServletRequest request)
					throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		if ("".equals(keyValue)) {
			keyValue = null;
		}
		params.put("keyword", keyValue);
		params.put("courseId", courseId);
		params.put("homeworkId", courseHomeworkId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseService.findCourseHomeworkManagerList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	// 校验名字
	@RequestMapping("/course/homework/getCheck_homework" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCheckHomeworkName(@RequestParam String homeworkName, @RequestParam String courseId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(courseService.getCheckHomeworkName(homeworkName, courseId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	/**
	 * @param queryJson
	 * @param request
	 * @return
	 * @throws DeodioException
	 * 学员报名课程
	 */
	@RequestMapping("/course/course_viewer/enroll_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel traineeEnrollCourseToLearn(@RequestParam String courseId,@RequestParam String itemType,@RequestParam String groupId, HttpServletRequest request)
			throws DeodioException {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		TraineeCourse traineeCourse = new TraineeCourse();
		try {
			traineeCourse.setAccountId(accountId);
			traineeCourse.setTraineeId(userId);
			traineeCourse.setItemId(courseId);
			traineeCourse.setItemType(new Short(itemType));
			traineeCourse.setGroupId(groupId);
			courseService.saveTraineeSEnrollCourseToLearn(traineeCourse, userId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}
	@RequestMapping("/course/get_homework_info" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseHomeworkInfo(@RequestParam String homeworkId){
		AjaxResultModel arm = new AjaxResultModel();
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("homeworkId", homeworkId);
			arm.setData(courseService.findCourseHomeworkInfo(params));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}
}
