package com.deodio.elearning.modules.courseman.controller;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.deodio.components.pagination.page.CommonUtils;
import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.courseman.service.ICourseManageService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.model.customize.CourseDto;

@Controller
@RequestMapping("/course/online")
public class CourseManageController extends BaseController {
	
	@Autowired
	private ICourseManageService courseManageService;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private TagsService tagsService;
	
	@RequestMapping("/page"+Constants.URL_SUFFIX)
	public String toCourseOnlineList(Model model,HttpServletRequest request){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("courseType", DConstants.COURSE_TYPE_ONLINE);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
		//该分类的叶子分类
		List<Map<String,Object>> classificationsList = classificationService.queryClassificationById(paramsMap);
		//当前账号下课件包含的所有标签
		List<Map<String,Object>> tagsList = tagsService.queryTagsByItemType(paramsMap);
	
		model.addAttribute("classificationsList", classificationsList);
		model.addAttribute("tagsList", tagsList);
//		return "modules/course/course_online_list";
		return "modules/courseman/online_course";
	}
	
	@RequestMapping("/load_data"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOnlineByClassificationAndTags(
			@RequestParam(required=false) Integer pageNo,
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(value="classificationIdList[]",required=false) String[] classificationIdList,
			@RequestParam(value="tagIdList[]",required=false) String[] tagIdList){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		CourseDto courseDto = new CourseDto();
		courseDto.setAccountId((String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID));
		courseDto.setCourseType(DConstants.COURSE_TYPE_ONLINE);
		courseDto.setItemType(DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
		courseDto.setClassificationIdList(classificationIdList);
		courseDto.setTagIdList(tagIdList);
		courseDto.setPagination(pageRequest);
		PageData<CourseDto> pageData = courseManageService.queryCourseList(courseDto);
		
		return getAjaxResult(CommonUtils.outPrintJsonMapForPage(pageData), AjaxResultModel.SUCCESS);
	}
}