package com.deodio.elearning.modules.course.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.vod.util.StringUtil;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.components.thread.EmailThread;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.DesUtil;
import com.deodio.core.utils.ExcelUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.JsonUtils;
import com.deodio.core.utils.ServletUtil;
import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.commons.service.VelocityService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.course.service.CourseOfflineService;
import com.deodio.elearning.modules.course.service.CourseService;
import com.deodio.elearning.modules.location.service.LocationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.modules.trainers.service.TrainersService;
import com.deodio.elearning.modules.user.service.UserService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseRegisteRule;
import com.deodio.elearning.persistence.model.CourseUserRel;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.customize.CourseOfflineItemBo;
import com.deodio.elearning.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
@Controller
public class CourseOfflineController extends BaseController{
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseOfflineService courseOfflineService;
	
	@Autowired
	TrainersService trainersService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private VelocityService velocityService;
	
	
	
	@RequestMapping("/course/offline/item_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadCourseTraineeCourseItemsInfo(Model model, HttpServletRequest request, @RequestParam String courseId) {
		
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("courseId", courseId);

			List<Map<String, Object>> itemList = courseService.findCourseOfflineItems(params);
			arm.setData(itemList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	
	@RequestMapping("/course/offline/list"+Constants.URL_SUFFIX)
	public String toCourseOfflineList(Model model,HttpServletRequest request){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("courseType", DConstants.COURSE_TYPE_OFFLINE);
		//paramsMap.put("classificationId",DConstants.STRING_ZERO);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_OFFLINE);
		//该分类的叶子分类
		List<Map<String,Object>> classificationsList = classificationService.queryClassificationById(paramsMap);
		//当前账号下课件包含的所有标签
		List<Map<String,Object>> tagsList = tagsService.queryTagsByItemType(paramsMap);
	
		model.addAttribute("classificationsList", classificationsList);
		model.addAttribute("tagsList", tagsList);
		return "modules/course/course_offline_list";
	}
	
	@RequestMapping("/course/offline/profile"+Constants.URL_SUFFIX)
	public String toCourseOfflineProfile(Model model,HttpServletRequest request,@RequestParam(required=false) String courseId,
			@RequestParam(required=false) String isPublish){
		
		//课程编号(courseId) 不为空时，为编辑状态；为空时为插入状态
		if(StringUtils.isNotBlank(courseId)){
			
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("courseId", courseId);
			//获取线上课程基本数据
			Map<String,Object> courseMap =  courseOfflineService.queryCourseOfflineProfile(paramsMap);
			model.addAttribute("courseMap", courseMap);
			paramsMap.put("itemId", courseId);
			paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_OFFLINE);
			//获取线上课程对应分类
			List<Map<String,Object>> selectedClassificationList =  classificationService.querySelectedClassificationByItemId(paramsMap);
			model.addAttribute("selectedClassificationList", selectedClassificationList);
			//获取线上课程对应标签
			String selectedTagsName =  tagsService.querySelectedTagsNameByItemId(paramsMap);
			model.addAttribute("selectedTagsName", selectedTagsName);
			courseOfflineService.updateCourse(courseId);
		}
		if(StringUtils.isNotBlank(isPublish)) {//发布状态的课程编辑后取消发布状态
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("courseId", courseId);
			courseService.cancelPublishCourse(params);
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
		}
		return "modules/course/course_offline_profile";
	}
	
	
	/**
	 * 保存线下信息
	 * @param request
	 * @param courseInfoJson
	 * @param attachId
	 * @param tagsJson
	 * @param classificationJson
	 * @return
	 */
	@RequestMapping("/course/offline/profile/submit"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseOfflineInfo(HttpServletRequest request,@RequestParam String courseInfoJson,
			@RequestParam String attachId, @RequestParam String tagsJson,@RequestParam String classificationJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			String id = courseOfflineService.insertCourseOfflineInfo(courseInfoJson, attachId,
					userId, accountId, getCookieGroupId(request), tagsJson, classificationJson);
			arm.setData(id);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	
	
	/**
	 * 获取线下课程内容
	 * @param model
	 * @param request
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/course/offline/content"+Constants.URL_SUFFIX)
	public String toCourseOfflineContent(Model model,HttpServletRequest request,@RequestParam String courseId,@RequestParam(required=false) String trainerType){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		
		//获取培训讲师数据
		List<Map<String, Object>> trainerList = trainersService.getTrainerList(null,accountId,trainerType);
		//培训地点
		List<Map<String,Object>> locationList = locationService.getLocationsList(accountId, null,userId,1);
		//获取线下课程内容
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("courseId", courseId);
		paramsMap.put("accountId", accountId);
		Map<String,Object> courseMap = courseOfflineService.queryCourseOfflineProfile(paramsMap);
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put("accountId", accountId);
		paramsMap1.put("courseId", courseId);
		CourseRegisteRule courseRegisteRule = courseService.queryCourseRegisteRule(paramsMap1);
		Gson gson = new Gson();
		model.addAttribute("trainerListJson", gson.toJson(trainerList));
		model.addAttribute("locationListJson",gson.toJson(locationList));
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("courseRegisteRule", courseRegisteRule);
		courseOfflineService.updateCourse(courseId);
		return "modules/course/course_offline_content";
	}
	
	
	@RequestMapping("/course/offline/content/load_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOfflineContent(Model model,HttpServletRequest request,@RequestParam String courseId
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
			List<Map<String,Object>> dataList = courseOfflineService.queryCourseOfflineContent(params);
			arm.setData(dataList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	
	@RequestMapping("/course/offline/content/submit"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseOfflineContent(Model model,HttpServletRequest request,@RequestParam String contentJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			
			Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD_HHMM).create();
			List<CourseOfflineItemBo> contentList = gson.fromJson(contentJson, new TypeToken<List<CourseOfflineItemBo>>(){}.getType());
			courseOfflineService.updateCourseOfflineContentInfo(contentList, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	@RequestMapping("/course/offline/content/getSumTrainee"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseOfflineContentTraineeSum(Model model,HttpServletRequest request,@RequestParam String courseId,@RequestParam Integer stepNo){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("courseId", courseId);
			params.put("stepNo", stepNo);
			arm.setData(courseOfflineService.queryCourseOfflineContentTraineeSum(params));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	/**
	 * 线下课程详细信息
	 * @param model
	 * @param request
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/course/offline/detail"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toCourseOfflineDetail(Model model,HttpServletRequest request,@RequestParam String courseId,@RequestParam(required=false) String trainerType){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			//获取培训讲师数据
			List<Map<String, Object>> trainerList = trainersService.getTrainerList(null,accountId,trainerType);
			//获取培训地点数据
			List<Map<String,Object>> locationList = locationService.getLocationsList(accountId, null,userId,1);
			
			//获取线下课程内容
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("itemType",Short.valueOf(DConstants.STRING_TWO));
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("courseId", courseId);
			
			List<Map<String,Object>> dataList = courseOfflineService.queryCourseOfflineContent(params);
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("courseId", courseId);
			paramsMap.put("itemId", courseId);
			paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_OFFLINE);
			//获取线上课程对应分类
			List<Map<String,Object>> selectedClassificationList =  classificationService.querySelectedClassificationByItemId(paramsMap);
			Gson gson = new Gson();
			Map<String,Object> courseOfflineInfo = courseOfflineService.queryCourseOfflineInfo(paramsMap);
			
			Map<String,Object> courseTraineeParamsMap = new HashMap<String,Object>();
			courseTraineeParamsMap.put("courseId", courseId);
			courseTraineeParamsMap.put("userStatus", 0);
			Integer traineeNum = courseService.numCourseTrainee(courseTraineeParamsMap);
			Integer traineeActiveNum = courseService.numCourseTraineeUserStatus(courseTraineeParamsMap,Constants.COURSE_TRAINEE_STATUS_ACTIVE);
			Integer traineeSubstituteNum = courseService.numCourseTraineeUserStatus(courseTraineeParamsMap,Constants.COURSE_TRAINEE_STATUS_SUBSTITUTE);
			Map<String, String> paramsMap1 = new HashMap<String, String>();
			paramsMap1.put("accountId", accountId);
			paramsMap1.put("courseId", courseId);
			CourseRegisteRule courseRegisteRule = courseService.queryCourseRegisteRule(paramsMap1);
			resultMap.put("courseRegisteRule", courseRegisteRule);
			resultMap.put("traineeNum", traineeNum);
			resultMap.put("courseMap", courseOfflineInfo);
			resultMap.put("balanceNum", getBalanceNum(courseOfflineInfo, traineeActiveNum));
			resultMap.put("traineeSubstituteNum",traineeSubstituteNum(courseOfflineInfo,traineeSubstituteNum));
			resultMap.put("selectedClassificationList", selectedClassificationList);
			resultMap.put("trainerListJson", gson.toJson(trainerList));
			resultMap.put("locationListJson",locationList);
			resultMap.put("dataList",dataList);
			arm.setData(resultMap);
			arm.setStatus(AjaxResultModel.SUCCESS);
			
		}catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
		
		
	}
	
	
	
	@RequestMapping("/course/offline/approval"+Constants.URL_SUFFIX)
	public String toCourseOfflineApproval(Model model,HttpServletRequest request,@RequestParam String courseId,@RequestParam(required=false) String trainerType){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		
		//获取培训讲师数据
		List<Map<String, Object>> trainerList = trainersService.getTrainerList(null,accountId,trainerType);
		//获取培训地点数据
		List<Map<String,Object>> locationList = locationService.getLocationsList(accountId, null,userId,1);
		
		//获取线下课程内容
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("itemType",Short.valueOf(DConstants.STRING_TWO));
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("courseId", courseId);
		
		List<Map<String,Object>> dataList = courseOfflineService.queryCourseOfflineContent(params);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("courseId", courseId);
		paramsMap.put("itemId", courseId);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_OFFLINE);
		//获取线上课程对应分类
		List<Map<String,Object>> selectedClassificationList =  classificationService.querySelectedClassificationByItemId(paramsMap);
		Gson gson = new Gson();
		Map<String,Object> courseOfflineInfo = courseOfflineService.queryCourseOfflineInfo(paramsMap);
		
		Map<String,Object> courseTraineeParamsMap = new HashMap<String,Object>();
		courseTraineeParamsMap.put("courseId", courseId);
		courseTraineeParamsMap.put("userStatus", 0);
		Integer traineeNum = courseService.numCourseTrainee(courseTraineeParamsMap);
		Integer traineeActiveNum = courseService.numCourseTraineeUserStatus(courseTraineeParamsMap,Constants.COURSE_TRAINEE_STATUS_ACTIVE);
		Integer traineeSubstituteNum = courseService.numCourseTraineeUserStatus(courseTraineeParamsMap,Constants.COURSE_TRAINEE_STATUS_SUBSTITUTE);
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put("accountId", accountId);
		paramsMap1.put("courseId", courseId);
		CourseRegisteRule courseRegisteRule = courseService.queryCourseRegisteRule(paramsMap1);
		model.addAttribute("courseRegisteRule", courseRegisteRule);
		model.addAttribute("traineeNum", traineeNum);
		model.addAttribute("courseMap", courseOfflineInfo);
		model.addAttribute("balanceNum", getBalanceNum(courseOfflineInfo, traineeActiveNum));
		model.addAttribute("traineeSubstituteNum",traineeSubstituteNum(courseOfflineInfo,traineeSubstituteNum));
		model.addAttribute("selectedClassificationList", selectedClassificationList);
		model.addAttribute("trainerListJson", gson.toJson(trainerList));
		model.addAttribute("locationListJson",gson.toJson(locationList));
		model.addAttribute("dataList",dataList);
		
		return "modules/course/course_offline_detail";
		
	}

	private Integer getBalanceNum(Map<String, Object> courseOfflineInfo,Integer traineeActiveNum){
		if(!courseOfflineInfo.isEmpty() && courseOfflineInfo.get("course_trainee_num")!=null ) {
			return Integer.valueOf(courseOfflineInfo.get("course_trainee_num").toString())-traineeActiveNum;
		}
		return null;
	}
	private Integer traineeSubstituteNum(Map<String, Object> courseOfflineInfo,Integer traineeSubstituteNum){
		if(!courseOfflineInfo.isEmpty() && courseOfflineInfo.get("course_trainee_num")!=null ) {
			Integer num = Integer.valueOf(courseOfflineInfo.get("course_trainee_num").toString());
			if(num%10!=0) {
				num = num/10+1;
			}
			return num - traineeSubstituteNum;
		}
		return null;
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
	@RequestMapping("/course/offline/load_data_by_classifications_and_tags"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOfflineByClassificationAndTags(Model model,HttpServletRequest request,
			@RequestParam(required=false) Integer pageNo,@RequestParam(value="classificationIdList[]",required=false) List<?> classificationIdList,
			@RequestParam(value="tagIdList[]",required=false) List<?> tagIdList,@RequestParam(required=false) Integer pageSize,@RequestParam(required=false) String keyword){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
		map.put("userId", userId);
		map.put("courseType", DConstants.COURSE_TYPE_OFFLINE);
		map.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_OFFLINE);
		//总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(DConstants.NUMBER_ZERO) > DConstants.NUMBER_ZERO) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		map.put("classificationIdList", classificationIdList);
		map.put("tagIdList", tagIdList);
		map.put("pagination", pageRequest);
		map.put("keyword", keyword);
		map.put("groupId", getCookieGroupId(request));
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
	 * 查询线下课程学员列表
	 * @param keywords
	 * @param courseId
	 * @param stepNo
	 * @param pageNo
	 * @param pageSize
	 * @param traineeStatus
	 * @param request
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/course/offline/trainee/load_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel findCourseTraineeList(@RequestParam String keywords,@RequestParam String courseId,@RequestParam(required=false) int stepNo,
			@RequestParam Integer pageNo,@RequestParam(required=false) Integer pageSize,@RequestParam(required=false) String traineeStatus,
			@RequestParam(required=false) String registeType,@RequestParam(required=false) String joinTime,HttpServletRequest request) throws DeodioException{
		
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		
		AjaxResultModel arm = new AjaxResultModel();	
		
		PageRequest pageRequest = new PageRequest(pageNo);
		if(pageSize != null && pageSize.compareTo(0)>0){
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		
		if(DConstants.NUMBER_ZERO == stepNo){
			stepNo = DConstants.NUMBER_ONE;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keywords", StringUtils.isNotBlank(keywords)?keywords:null);
		params.put("courseId",courseId);
		params.put("stepNo",stepNo);
		params.put("traineeStatus",StringUtils.isNotBlank(traineeStatus)?Short.valueOf(traineeStatus):null);
		params.put("registeType", StringUtils.isNotBlank(registeType)?Short.valueOf(registeType):null);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		params.put("joinTime",StringUtils.isNotBlank(joinTime)?joinTime:null);
		List<Map<String, Object>> dataList = courseOfflineService.findCourseOfflineTrainees(params);
 		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 批量报名学员模板
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/course/down_excel_template" + Constants.URL_SUFFIX)
	public void downSubProjectExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<String[]> datas = new ArrayList<String[]>();
		String[] headerData = new String[3];
		//headerData[0] = "用户昵称";
		headerData[0] = "姓名";
		headerData[1] = "邮件地址";
		datas.add(headerData);
		ServletUtil.writeExcelFile(request, response, "模板.xls", this.exportExcel("模板", datas));
	}
	/**
	 * 
	 * @Title: exportExcel 
	 * @Description: 电子表格导出2007版本以上 （单个sheet）
	 * @param sheetName sheet 名称
	 * @param datas 数据List<String[]>
	 * @throws IOException
	 * @return HSSFWorkbook
	 * @throws 
	 */
	public HSSFWorkbook exportExcel(String sheetName,List<String[]> datas) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(sheetName);
		String [] data = null;
		if(datas !=null && !datas.isEmpty()){
			for(int i = 0 ; i < datas.size() ; i++){
				Row row = sheet.createRow(i);
				data = datas.get(i);
				if(data != null){
					for(int j = 0; j < data.length ; j++){
						Cell cell = row.createCell(j);
						if(StringUtils.isNotBlank(data[j])){
							cell.setCellValue(data[j]);
						}else{
							cell.setCellValue(" ");
						}
					}
				}
			}
		}
		return wb;
	}
	/**
	 * @param request
	 * @param courseInfoJson
	 * @param attachId
	 * @param tagsJson
	 * @param classificationJson
	 * @return
	 * 复制课程
	 */
	@RequestMapping("/course/offline/profile/copy"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel copyCourseOfflineInfo(HttpServletRequest request,@RequestParam String couseName,
			@RequestParam String courseId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			courseOfflineService.copyCourseOfflineInfo(courseId,couseName,getCookieUserId(request));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	@RequestMapping("/course/offline/profile/baseInfo"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseOfflineBaseInfo(HttpServletRequest request,
			@RequestParam String courseId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			arm.setData(courseService.selectyCourseByPrimaryId(courseId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	/**
	 * 查询学员课程详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/course/offline/preview" + Constants.URL_SUFFIX)
	public String toViewerOfflineCourseDetail(Model model, HttpServletRequest request, @RequestParam String courseId) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("courseId", courseId);

		Map<String, Object> courseMap = courseService.queryTraineeCourseInfo(params);
		//查询课程是否关注
		Boolean hasFavor = courseService.hasFavoriteCourse(params);
		//获取课程评分
		int starNum = courseService.courseApraiseAvg(params);
		//获取课程学习人数
		int traineeCount = courseService.courseLearnCount(params);
		//获取课程学习人数
		int appraiseCount = courseService.courseApraiseCount(params);
		//判断学员是否已选择该课程
		Boolean hasSelect = courseService.traineeHasSelectCourse(params);
		//查询课程报名起止时间，培训的起止时间
		Map<String, Object> courseOfflineInfo=courseOfflineService.queryCourseOfflineItemInfo(params);
		Map<String,Object> courseOfflineProfileInfo =  courseOfflineService.queryCourseOfflineProfile(params);
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put("accountId", accountId);
		paramsMap1.put("courseId", courseId);
		CourseRegisteRule courseRegisteRule = courseService.queryCourseRegisteRule(paramsMap1);
		courseMap.put("hasSelect", hasSelect.toString());
		courseMap.put("starNum", starNum);
		courseMap.put("traineeCount", traineeCount);
		courseMap.put("appraiseCount", appraiseCount);
		courseMap.put("hasFavor", hasFavor.toString());
		courseMap.put("locationName", courseOfflineInfo.get("locationName"));
		courseMap.put("trainStartTime",courseMap.get("start_time").toString().substring(0,10));
		courseMap.put("trainEndTime", courseMap.get("expire_time").toString().substring(0,10));
		courseMap.put("start_time", courseRegisteRule.getStartTime());
		courseMap.put("expire_time", courseRegisteRule.getExpireTime());
		courseMap.put("course_trainee_num", courseOfflineProfileInfo.get("course_trainee_num"));
		courseMap.put("courseOfflineProfileInfo", courseOfflineProfileInfo);
		model.addAttribute("courseMap", courseMap);
   
		return "modules/course/course_offline_preview";
	}
	
	/**
	 * 处理报名人员列表
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/course/upload_excel_template" + Constants.URL_SUFFIX)
	public void uploadDeclareExcelTemplate(HttpServletResponse response,HttpServletRequest request,
			@RequestParam String courseId,@RequestParam String itemId){
		boolean flag = true;
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		String URL =(request.getRequestURL().toString()).substring(0,(request.getRequestURL().toString()).indexOf(request.getRequestURI()))
		        + request.getContextPath();
		dataMap.put("status", 1);
		dataMap.put("data", arm);
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try{
			request.setCharacterEncoding(Constants.CHARSET_UTF_8);
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        List<FileItem> items = upload.parseRequest(request);
	        Iterator<FileItem> it = items.iterator();
	        FileItem fileItem = null;
	        String fileName = "";
	        while(it.hasNext()){
	        	fileItem = it.next();
	        	 if(!fileItem.isFormField()){
	        		 fileName = fileItem.getName();
	        		 flag = true;
	        		 break;
	        	 }else{
	        		 fileItem = null;
	        	 }
	        }
			
			if(flag){
				// 获取文件后缀
				String type = FileUtils.getExt(fileName);
				// 行数
				int row = ExcelUtils.getRowNum(fileItem.getInputStream(), 0, type)+1;
				if(row == 1){
					arm.setStatus(AjaxResultModel.FAIL);
					arm.setMessage("上传失败,数据不能为空!");
			        JsonUtils.objToJson(dataMap, response);
			        return;
				}
				// 除去表头的第一行数据开始取数
				StringBuilder sb = new StringBuilder();
				//StringBuilder noExist = new StringBuilder();
				for(int i = 1; i < row; i++){
					//String nickName = ExcelUtils.readExcelContent(fileItem.getInputStream(), 0, i, 0, type);
					String userName = ExcelUtils.readExcelContent(fileItem.getInputStream(), 0, i,0, type);
					String mail = ExcelUtils.readExcelContent(fileItem.getInputStream(), 0, i, 1, type);
					if(StringUtils.isBlank(userName)&&StringUtils.isBlank(mail)){
						break;
					}else if(StringUtils.isBlank(mail)){
						arm.setStatus(AjaxResultModel.FAIL);
						arm.setMessage(i+"行邮箱不能为空,请确认");
				        JsonUtils.objToJson(dataMap, response);
				        return;
					}
					//验证邮箱格式
					if(!courseOfflineService.checkEmail(mail)){
						arm.setStatus(AjaxResultModel.FAIL);
						arm.setMessage(i+"行邮箱格式不正确,请确认");
				        JsonUtils.objToJson(dataMap, response);
				        return;
					}
					//判断邮箱是否存在
					if(!userService.getValidateEmailExists(mail)) {
						//已存在的邮箱直接邀请为系统中的用户以学员的身份进行学习
						 courseOfflineService.insertUserAccount(userName, mail, accountId, userId);
						 Map<String, Object> objMaps = new HashMap<String, Object>();//学员通过邮件链接进入学习
						 List<Map<String, Object>> contentMaps = new ArrayList<Map<String, Object>>();
						 objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL_STUDENT));
						 objMaps.put("userRole", DConstants.GROUP_ROLE_VIEWER_ID);
						 objMaps.put("usermail", DesUtil.encrypt(mail));
						 objMaps.put("content", "课程学习邀请");
						 contentMaps.add(objMaps);
						 List<String> content = velocityService
									.getVelocityTemplate(DConstants.SYSTEM_TEMPLATE_INVITE_MAIL_TEXT_TEMPLATE_BATCH_IMPORT_MAIL, contentMaps);
						 EmailThread emailThread = new EmailThread(mail, content, "课程学习", true);
						 taskExecutor.execute(emailThread);
					}else{//用户名为空更新系统用户信息
						UserModel user=userService.queryUserInfoByUserName(mail,DConstants.USE_REGISTER_TYPE_MAIL);
						if(StringUtil.isBlank(user.getUserName())){
							userService.updateUserNameById(userName, user.getId());
						}
					}
					sb.append(userName)
					.append(DConstants.DELIMITER_DATA)
					.append(mail)
					.append(DConstants.DELIMITER_ROW);
				}
				//返回不合格的数据
				//arm.setData(noExist.toString());
				//保存报名数据sb.toString()
				courseOfflineService.importDateCourseUser(sb.toString(),courseId,itemId,userId);
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		if(flag){
			arm.setMessage("上传成功!");
			arm.setStatus(AjaxResultModel.SUCCESS);
		}else{
			arm.setStatus(AjaxResultModel.FAIL);
		}
        JsonUtils.objToJson(dataMap, response);
        
        //1.获取文件
        //2.判断用户邮箱是否存在，不存在的用户记录
       
	}

	/**
	 * 分享线下课程
	 */
	@RequestMapping("/course/offline/share_course_owner" + Constants.URL_SUFFIX)
	@ResponseBody
	public com.deodio.core.utils.AjaxResultModel shareCourse(@RequestParam String courseId,
			@RequestParam String courseOwner, HttpServletRequest request) {
		return getAjaxResult(courseOfflineService.shareCourse(courseId, courseOwner, getCookieUserId(request)),
				AjaxResultModel.SUCCESS);
	}
	@RequestMapping("/course/offline/CourseOfflineItemCount" + Constants.URL_SUFFIX)
	@ResponseBody
	public com.deodio.core.utils.AjaxResultModel shareCourse(@RequestParam String courseId, HttpServletRequest request) {
		return getAjaxResult(courseOfflineService.selectCourseOfflineItemCount(courseId),
				AjaxResultModel.SUCCESS);
	}
	@RequestMapping("course/offline/getSubstitudeNum" + Constants.URL_SUFFIX)
	@ResponseBody
	public com.deodio.core.utils.AjaxResultModel getSubstitudeNum(@RequestParam String courseId,@RequestParam String itemIdValue,@RequestParam String userStatus, HttpServletRequest request) {
		return getAjaxResult(courseOfflineService.selectSubstitudeNum(courseId,itemIdValue,userStatus),
				AjaxResultModel.SUCCESS);
	}
	 
}
