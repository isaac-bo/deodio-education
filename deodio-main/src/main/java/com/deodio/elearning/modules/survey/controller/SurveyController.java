package com.deodio.elearning.modules.survey.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.survey.service.SurveyService;
import com.deodio.elearning.persistence.mapper.CourseSurveyMapper;
import com.deodio.elearning.persistence.mapper.SurveyMapper;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CourseOnlineItem;
import com.deodio.elearning.persistence.model.CourseSurvey;
import com.deodio.elearning.persistence.model.CourseSurveyExample;
import com.deodio.elearning.persistence.model.Survey;
import com.deodio.elearning.persistence.model.SurveyExample;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.emory.mathcs.backport.java.util.Collections;

@Controller
public class SurveyController extends BaseController {

	@Autowired
	private SurveyService surveyService;




	/**
	 * 问卷调查管理
	 * 
	 * @Title: toSurveyList
	 * @return
	 * @throws DeodioException
	 * @return String
	 */
	@RequestMapping("/survey/list" + Constants.URL_SUFFIX)
	public String toSurveyList() throws DeodioException {

		return "modules/survey/survey_list";
	}

	/**
	 * 获取问卷调查列表
	 * 
	 * @param pageNo
	 * @param keywords
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/sync/content" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel surveySyncContent(HttpServletRequest request) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		List<Map<String, Object>> dataList = surveyService.getSurveyList(this.getCookieAccount(request));
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 在课程管理模块下创建问卷调查时，加载问卷调查列表
	 * 
	 * @param pageNo
	 * @param keywords
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/load_survey_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadCourseManagerSurveyList(@RequestParam Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam String keywords, HttpServletRequest request,
			@RequestParam Integer isPublish, @RequestParam(required = false) String array,
			@RequestParam(required = false) String course_expire_time,
			@RequestParam(required = false) String course_start_time) throws DeodioException
	{
		List<String> contentList = null;
		try {
			Gson gson = new Gson();
			contentList = gson.fromJson(array, new TypeToken<List<String>>() {
			}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> jsonMap = surveyService.loadCourseManagerSurveyList(pageNo, pageSize, keywords, isPublish,
				contentList, course_start_time, course_expire_time, getCookieAccount(request),
				getCookieUserId(request));
		System.err.println(getClass().getName() + "\tloadCourseManagerSurveyList\tjsonMap:\t" + jsonMap.toString());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 在课程内容下，加载问卷调查列表
	 * 
	 * @param pageNo
	 * @param keywords
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/load_course_survey_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadCourseSurveyList(@RequestParam Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam String keywords, HttpServletRequest request,
			@RequestParam Integer isPublish, @RequestParam(required = false) String array,
			@RequestParam(required = false) String course_expire_time,
			@RequestParam(required = false) String course_start_time) throws DeodioException
	{
		List<String> contentList = null;
		try {
			Gson gson = new Gson();
			contentList = gson.fromJson(array, new TypeToken<List<String>>() {
			}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> jsonMap = surveyService.loadCourseSurveyList(pageNo, pageSize, keywords, isPublish,
				contentList, course_start_time, course_expire_time, getCookieAccount(request),
				getCookieUserId(request));
		System.err.println(getClass().getName() + "\tloadCourseManagerSurveyList\tjsonMap:\t" + jsonMap.toString());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取课程管理模块下，创建的问卷调查信息
	 * 
	 * @param surveyId
	 * @param courseId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/load_add_survey_info" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadAddSurveyInfo(@RequestParam String surveyId,
			@RequestParam(required = false) String courseId, HttpServletRequest request) throws DeodioException
	{
		if (com.deodio.core.utils.StringUtils.isEmpty(courseId)) {
			courseId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_COURSE_ID);
		}
		AjaxResultModel arm = new AjaxResultModel();

		List<CourseSurvey> courseSurveys = surveyService.findCourseSurveyList(surveyId,courseId);
		CourseSurvey courseSurvey = new CourseSurvey();
		if (courseSurveys != null && !courseSurveys.isEmpty()) {
			courseSurvey = courseSurveys.get(0);
		} else {
			Survey survey = surveyService.getSurvey(surveyId);
			if (com.deodio.core.utils.StringUtils.isEmpty(survey.getSurveyDesc())) {
				courseSurvey.setSurveyDesc("");
			}
			courseSurvey.setSurveyName(survey.getSurveyName());
			courseSurvey.setSurveyModel(survey.getSurveyModel());
		}
		arm.setData(courseSurvey);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 在问卷调查模块下，加载问卷调查列表
	 * 
	 * @param pageNo
	 * @param keywords
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadSurveyList(@RequestParam Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam String keywords, HttpServletRequest request)
			throws DeodioException
	{
		AjaxResultModel arm = new AjaxResultModel();

		Map<String, Object> jsonMap = surveyService.loadSurveyList(pageNo, pageSize, keywords,
				getCookieAccount(request), getCookieUserId(request));
		System.err.println(getClass().getName() + "\tgetCookieAccount(request):\t" + getCookieAccount(request)
				+ "\tgetCookieUserId(request):\t" + getCookieUserId(request) + "\tjsonMap:\t" + jsonMap.toString());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 删除问卷调查
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delSurvey(@RequestParam String surveyId) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		surveyService.deleteSurvey(surveyId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/survey/profile" + Constants.URL_SUFFIX)
	public String profile(Model model, @RequestParam(required = false) String surveyId, HttpServletRequest request)
			throws DeodioException
	{
		if (StringUtils.isNotBlank(surveyId)) {
			Survey survey = surveyService.getSurvey(surveyId);
			survey.setAuthorityUserIsEdit(DConstants.IS_EDIT);
			surveyService.updateSurvey(survey);
			model.addAttribute("survey", survey);
			String attachId = survey.getAttachId();
			if (com.deodio.core.utils.StringUtils.isNotEmpty(attachId)) {
				Attachment attachment = surveyService.getAttachUrl(attachId);
				model.addAttribute("attachment", attachment);
			}
		}
		return "modules/survey/survey_profile";
	}

	/**
	 * 设置问卷基本信息
	 * 
	 * @param surveyName
	 * @param surveyDesc
	 * @param surveyType
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/content" + Constants.URL_SUFFIX)
	public String content(Model model, @RequestParam String surveyId, @RequestParam String surveyName,
			@RequestParam String surveyDesc, @RequestParam Integer surveyModel, @RequestParam String startTime,
			@RequestParam String endTime, @RequestParam String surveyCover, @RequestParam String attachId,
			HttpServletRequest request) throws DeodioException
	{
		System.err.println(getClass().getName() + "\tsurveyDesc.length():\t" + surveyDesc.length());
		Survey survey = new Survey();
		survey.setSurveyName(surveyName);
		survey.setSurveyDesc(surveyDesc);
		survey.setSurveyModel(surveyModel);
		survey.setStartTime(DateTimeUtils.parse(startTime));
		survey.setEndTime(DateTimeUtils.parse(endTime));
		survey.setAccountId(getCookieAccount(request));
		survey.setSurveyCover(surveyCover);
		survey.setAttachId(attachId);
		survey.setAuthorityUserIsEdit(DConstants.IS_EDIT);
		String userId = getCookieUserId(request);
		if (StringUtils.isBlank(surveyId)) {
			SurveyExample example = new SurveyExample();
			example.createCriteria().andSurveyNameEqualTo(surveyName);
			List<Survey> surveys = surveyService.selectByExample(example);
			if (null == surveys || surveys.size() == 0) {
				surveyId = AppUtils.uuid();
				survey.setId(surveyId);
				survey.setCreateTime(new Date());
				survey.setCreateId(userId);
				survey.setAuthorityUserId(userId);
				survey.setGroupId(getCookieGroupId(request));
				surveyService.insertNewSurvey(survey);
				surveyService.updateSurveyAttachment(attachId, surveyId);
				List<Map<String, Object>> dataList = surveyService.getSubjectAndItems(surveyId,
						getCookieAccount(request));
				model.addAttribute("dataList", dataList);
				model.addAttribute("surveyId", surveyId);
			}
		} else {
			survey.setId(surveyId);
			survey.setUpdateId(userId);
			survey.setUpdateTime(new Date());
			surveyService.updateSurvey(survey);
			Survey surveyNoGroupId = surveyService.getSurvey(surveyId);
			if (com.deodio.core.utils.StringUtils.isBlank(surveyNoGroupId.getGroupId())
					&& getCookieUserId(request).equals(surveyNoGroupId.getCreateId()))
			{
				surveyNoGroupId.setGroupId(getCookieGroupId(request));
				surveyService.updateSurvey(surveyNoGroupId);
			}
			surveyService.updateSurveyAttachment(attachId, surveyId);
			List<Map<String, Object>> dataList = surveyService.getSubjectAndItems(surveyId, getCookieAccount(request));
			model.addAttribute("dataList", dataList);
			model.addAttribute("surveyId", surveyId);
		}
		return "modules/survey/survey_content";
	}

	/**
	 * 获取问卷基本信息
	 * 
	 * @param surveyId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/get/survey/content" + Constants.URL_SUFFIX)
	public String getSurveyContent(Model model, @RequestParam String surveyId, HttpServletRequest request)
			throws DeodioException
	{
		String accountId = this.getCookieAccount(request);
		// 获取已存在的数据
		List<Map<String, Object>> dataList = surveyService.getSubjectAndItems(surveyId, accountId);
		model.addAttribute("dataList", dataList);
		model.addAttribute("surveyId", surveyId);
		return "modules/survey/survey_content";
	}

	@RequestMapping("/classroom/survey/present" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel presentSurvey(HttpServletRequest request, @RequestParam String surveyId) {
		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(surveyService.getSubjectAndItems(surveyId, this.getCookieAccount(request)));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 预览问卷调查内容
	 * 
	 * @Title: getPreview
	 * @param model
	 * @param surveyId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/survey_preview" + Constants.URL_SUFFIX)
	public String getPreview(Model model, @RequestParam(required = false) String surveyId, HttpServletRequest request)
			throws DeodioException
	{
		List<Map<String, Object>> dataList = surveyService.getSubjectAndItems(surveyId, this.getCookieAccount(request));
		model.addAttribute("dataList", dataList);
		model.addAttribute("surveyId", surveyId);
		return "modules/survey/survey_preview";
	}

	/**
	 * 预览问卷调查内容
	 * 
	 * @Title: getPreview
	 * @param model
	 * @param surveyId
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/survey_preview_dialogue" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getSurveyDialogPreview(Model model, @RequestParam String surveyId,
			HttpServletRequest request) throws DeodioException
	{
		AjaxResultModel arm = new AjaxResultModel();
		List<Map<String, Object>> dataList = surveyService.getSubjectAndItems(surveyId, this.getCookieAccount(request));
		System.err.println(getClass().getName() + "\tgetSurveyDialogPreview\tdataList:\t" + dataList);
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}	

	/**
	 * 保存并预览问卷调查内容
	 * 
	 * @Title: preview
	 * @param model
	 * @param dataStr
	 * @param surveyId
	 * @param request
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/preview" + Constants.URL_SUFFIX)
	public String preview(Model model, @RequestParam String dataStr, @RequestParam(required = false) String surveyId,
			HttpServletRequest request) throws DeodioException
	{
		surveyService.saveView(surveyId, DConstants.IS_EDIT, getCookieUserId(request), dataStr,
				DConstants.TYPE_NO_PUBLISH);
		List<Map<String, Object>> dataList = surveyService.getSubjectAndItems(surveyId, this.getCookieAccount(request));
		model.addAttribute("dataList", dataList);
		model.addAttribute("surveyId", surveyId);
		return "modules/survey/survey_preview";
	}

	/**
	 * 保存不发布问卷调查
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/saveSurvey" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveSurvey(HttpServletRequest request, @RequestParam Integer isPublish,
			@RequestParam String surveyId, @RequestParam String contentStr)
	{
		AjaxResultModel arm = new AjaxResultModel();
		surveyService.saveView(surveyId, DConstants.ISNOT_EDIT, getCookieUserId(request), contentStr, isPublish);

		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 发布问卷调查
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/publish" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel onPublish(HttpServletRequest request, @RequestParam String surveyId,
			@RequestParam String contentStr)
	{
		AjaxResultModel arm = new AjaxResultModel();
		// 更新问卷调查题目内容及顺序
		surveyService.saveView(surveyId, DConstants.ISNOT_EDIT, getCookieUserId(request), contentStr,
				DConstants.TYPE_IS_PUBLISH);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 复制问卷调查
	 * 
	 * @param surveyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/survey/copySurvey" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel copySurvey(@RequestParam String surveyId, @RequestParam String surveyName,
			HttpServletRequest request)
	{

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		surveyService.copySurvey(surveyId, surveyName, getCookieUserId(request));
		map.put("successFlag", true);
		arm.setData(map);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取问卷调查基本信息
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/getBaseInfo" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getBaseInfo(@RequestParam String surveyId) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		int num = 1;
		List<Integer> numList = new ArrayList<Integer>();
		Survey survey = surveyService.getSurvey(surveyId);
		List<Map<String, Object>> sameSurveylist = surveyService.getAllSurveyBySurveyName(survey.getSurveyName());
		if (sameSurveylist.size() > 1) {
			String rex = "[()]+";
			for (int i = 0; i < sameSurveylist.size(); i++) {
				String surveyName = (String) sameSurveylist.get(i).get("survey_name");
				String[] str = surveyName.split(rex);
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
		jsonMap.put("survey", survey);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 检测问卷调查名称是否已存在
	 * 
	 * @param surveyName
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/checkSurveyNameExist" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkSurveyNameExist(@RequestParam String surveyName) throws DeodioException {
		boolean isExist;
		AjaxResultModel arm = new AjaxResultModel();
		SurveyExample example = new SurveyExample();
		example.createCriteria().andSurveyNameEqualTo(surveyName);
		List<Survey> list = surveyService.selectByExample(example);
		if (null == list || list.size() == 0) {
			isExist = false;
		} else {
			isExist = true;
		}
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(isExist);
		return arm;
	}

	/**
	 * 判断问卷调查是否当前用户创建
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/checkDeleteAuthority" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkDeleteAuthority(@RequestParam String surveyId, HttpServletRequest request)
			throws DeodioException
	{
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> result = surveyService.checkDeleteAuthority(surveyId, getCookieUserId(request));
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(result);
		return arm;
	}

	/**
	 * 获取被引用的问卷调查
	 * 
	 * @param pageNo
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/survey/quote_survey" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuoteSurvey(@RequestParam Integer pageNo, @RequestParam String surveyId) {
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pagination", pageRequest);
		params.put("surveyId", surveyId);
		List<Map<String, Object>> surveyList = surveyService.findQuoteSurveyList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", surveyList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPagination().getTotalPages());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 设置问卷调查的编辑权限
	 * 
	 * @param userId
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/survey/set_share_survey_trainee" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setShareSurveyTrainee(@RequestParam String surveyId,
			@RequestParam(required = false) String userId)
	{
		AjaxResultModel arm = new AjaxResultModel();
		surveyService.setShareSurveyTrainee(surveyId, userId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 校验问卷调查的名称
	 * 
	 * @param surveyName
	 * @return
	 */
	@RequestMapping("/survey/check/surveyName" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkSurveyName(@RequestParam String surveyName) {
		AjaxResultModel arm = new AjaxResultModel();
		SurveyExample example = new SurveyExample();
		example.createCriteria().andSurveyNameEqualTo(surveyName);
		List<Survey> surveys = surveyService.selectByExample(example);
		boolean surveyExist = false;
		if (surveys != null && !surveys.isEmpty()) {
			surveyExist = true;
		}
		arm.setData(surveyExist);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取问卷调查是否可编辑
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/getSurveyIsEdit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getSurveyIsEdit(@RequestParam String surveyId, HttpServletRequest request)
			throws DeodioException
	{
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> result = surveyService.getSurveyIsEdit(surveyId, getCookieUserId(request));
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(result);
		return arm;
	}

	/**
	 * 获取问卷调查是否可编辑
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/update_edit_state" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateEditState(@RequestParam String surveyId, @RequestParam Integer isEdit,
			HttpServletRequest request) throws DeodioException
	{
		System.err.println(getClass().getName() + "\tsurveyId:\t" + surveyId);
		Survey survey = new Survey();
		survey.setId(surveyId);
		survey.setAuthorityUserIsEdit(isEdit);
		survey.setUpdateId(getCookieUserId(request));
		survey.setUpdateTime(new Date());
		return getAjaxResult(surveyService.updateSurvey(survey), AjaxResultModel.SUCCESS);
	}
	/**
	 * 设置问卷调查发布状态
	 * 
	 * @param surveyId
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/set_publish_status" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updatePublishStatus(@RequestParam String surveyId, @RequestParam Integer isPublish,
			HttpServletRequest request) throws DeodioException
	{
		System.err.println(getClass().getName() + "\tsurveyId:\t" + surveyId);
		Survey survey = new Survey();
		survey.setId(surveyId);
		survey.setIsPublish(isPublish);
		survey.setUpdateId(getCookieUserId(request));
		survey.setUpdateTime(new Date());
		return getAjaxResult(surveyService.updateSurvey(survey), AjaxResultModel.SUCCESS);
	}
	/**
	 * 取消分享
	 * 
	 * @param id
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/survey/cancel_shared" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel cancelShared(@RequestParam String id,HttpServletRequest request) throws DeodioException
	{
		Survey survey = surveyService.getSurvey(id);
		survey.setAuthorityUserId(survey.getCreateId());
		survey.setUpdateId(getCookieUserId(request));
		survey.setUpdateTime(new Date());
		return getAjaxResult(surveyService.updateSurvey(survey), AjaxResultModel.SUCCESS);
	}
}
