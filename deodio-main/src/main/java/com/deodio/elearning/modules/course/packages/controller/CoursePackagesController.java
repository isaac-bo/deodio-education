package com.deodio.elearning.modules.course.packages.controller;

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
import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.course.packages.service.CoursePackagesService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CoursePackage;
import com.deodio.elearning.persistence.model.customize.CoursePackageBo;
import com.deodio.elearning.persistence.model.customize.CoursePackageItemsElementsRelBo;

@Controller
public class CoursePackagesController extends BaseController {
	
	@Autowired
	private CoursePackagesService coursePackagesService;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping("/course/packages/list"+Constants.URL_SUFFIX)
	public String toCoursePackagesList(Model model,HttpServletRequest request){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSEPACKAGE);
		//该分类的叶子分类
		List<Map<String,Object>> classificationsList = classificationService.queryClassificationById(paramsMap);
		//当前账号下课件包含的所有标签
		List<Map<String,Object>> tagsList = tagsService.queryTagsByItemType(paramsMap);
	
		model.addAttribute("classificationsList", classificationsList);
		model.addAttribute("tagsList", tagsList);
		return "modules/course/packages/packages_list";
	}
	
	
	@RequestMapping("/course/packages/profile"+Constants.URL_SUFFIX)
	public String toCoursePackagesProfile(Model model,HttpServletRequest request,@RequestParam(required=false) String packageId){
		
		//课程包编号(packageId) 不为空时，为编辑状态；为空时为插入状态
		if(StringUtils.isNotBlank(packageId)){
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("packageId", packageId);
			paramsMap.put("accountId", accountId);
			//获取课程包数据
			CoursePackage packageInfo =  coursePackagesService.queryCoursePackageInfo(paramsMap);
			model.addAttribute("packageMap", packageInfo);
			//设置分类与标签查询参数
			paramsMap.put("itemId", packageId);
			paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSEPACKAGE);
			//获取分类
			List<Map<String,Object>> selectedClassificationList =  classificationService.querySelectedClassificationByItemId(paramsMap);
			model.addAttribute("selectedClassificationList", selectedClassificationList);
			//获取标签
			String selectedTagsName =  tagsService.querySelectedTagsNameByItemId(paramsMap);
			model.addAttribute("selectedTagsName", selectedTagsName);
			
			//获取封面图片信息
			Map<String,String> attachmentQueryMap = new HashMap<String,String>(); 
			attachmentQueryMap.put("relId", packageId);
			attachmentQueryMap.put("generateName", packageInfo.getPackageCoverImg());
			Attachment attachment =  attachmentService.queryItemsRelAttachement(attachmentQueryMap);
			model.addAttribute("uploadedAttachment", attachment);
		}
		return "modules/course/packages/packages_profile";
	}
	
	
	@RequestMapping("/course/packages/submit_profile"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseOnlineInfo(HttpServletRequest request,@RequestParam String packageInfoJson,
			@RequestParam String attachId, @RequestParam(required=false) String tagsJson,@RequestParam(required=false) String classificationJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			//Json字符串转List对象(标签)
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			String id = coursePackagesService.saveCoursePackageInfo(packageInfoJson, attachId, userId, accountId, tagsJson, classificationJson);
			arm.setData(id);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	
	
	/**
	 * 发布课程
	 * @param model
	 * @param courseId  课程编号
	 * @return
	 */
	@RequestMapping("/course/packages/publish"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel publishCourse(Model model,@RequestParam String packageId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("packageId", packageId);
		Integer rowCount = coursePackagesService.publishCoursePackage(paramsMap);
		arm.setData(rowCount);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 判断课程名是否存在
	 * @param model
	 * @param courseName   课程名称
	 * @param courseId	          课程编号
	 * @return
	 */
	@RequestMapping("/course/packages/isExistName"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel isExistName(Model model,@RequestParam String packageName,@RequestParam(required=false) String packageId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("packageName", packageName);
		paramsMap.put("packageId", packageId);
		Integer count = coursePackagesService.queryPackageNameCount(paramsMap);
		arm.setData(count);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	 
	@RequestMapping("/course/packages/content"+Constants.URL_SUFFIX)
	public String toCoursePackagesContent(Model model,HttpServletRequest request,@RequestParam String packageId){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("packageId", packageId);
		paramsMap.put("accountId", accountId);
		//获取课程包数据
		CoursePackage packageInfo =  coursePackagesService.queryCoursePackageInfo(paramsMap);
		model.addAttribute("packageMap", packageInfo);
		
		//获取背景图信息
		Map<String,String> attachmentQueryMap = new HashMap<String,String>(); 
		attachmentQueryMap.put("relId", packageId);
		attachmentQueryMap.put("generateName", packageInfo.getPackageBackgroundImg());
		Attachment attachment =  attachmentService.queryItemsRelAttachement(attachmentQueryMap);
		model.addAttribute("backgroundAttachment", attachment);
		
		return "modules/course/packages/packages_content";
	}
	
	@RequestMapping("/course/packages/content/load_data"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCoursePackagesContent(Model model,HttpServletRequest request,@RequestParam String packageId){
		
		AjaxResultModel arm = new AjaxResultModel();
		try {
			//Json字符串转List对象(标签)
//			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("packageId", packageId);
			paramsMap.put("accountId", accountId);
			CoursePackageBo packageBo = coursePackagesService.queryPackageContent(paramsMap);
			arm.setData(packageBo);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/course/packages/content/submit"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCoursePackagesContent(Model model,HttpServletRequest request,@RequestParam String packageId,
			@RequestParam String seriesJson,@RequestParam String itemsJson,@RequestParam String itemsRelJson){
		
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			coursePackagesService.saveCoursePackageContent(packageId,seriesJson, itemsJson, itemsRelJson, userId,accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/course/packages/content/elements/load_data"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCoursePackagesItemsElementsRel(Model model,HttpServletRequest request,@RequestParam String itemId){
		
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String,String> paramsMap = new HashMap<String,String>();
			paramsMap.put("itemId", itemId);
			paramsMap.put("accountId", accountId);
			List<CoursePackageItemsElementsRelBo> elements = coursePackagesService.queryCoursePackageItemsElementsRelList(paramsMap);
			arm.setData(elements);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/course/packages/content/elements/submit_data"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCoursePackagesItemsElementsRel(Model model,HttpServletRequest request,@RequestParam String  elementsRelJson){
		
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			coursePackagesService.saveCoursePackageItemsElementsRel(elementsRelJson, userId,accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
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
	@RequestMapping("/course/packages/load_data_by_classifications_and_tags"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOnlineByClassificationAndTags(Model model,HttpServletRequest request,
			@RequestParam(required=false) Integer pageNo,@RequestParam(value="classificationIdList[]",required=false) List<?> classificationIdList,
			@RequestParam(value="tagIdList[]",required=false) List<?> tagIdList,@RequestParam(required=false) Integer pageSize){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
		map.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSEPACKAGE);
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
		List<Map<String,Object>> list =  coursePackagesService.findCoursePackageList(map);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("dataList", list);
		resultMap.put("currePage", pageNo);
	    resultMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(resultMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

}
