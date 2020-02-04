package com.deodio.elearning.modules.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.CommonUtils;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.convert.socket.model.MRResult;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.convert.service.ConvertService;
import com.deodio.elearning.modules.presentation.service.PresentationPackageService;
import com.deodio.elearning.modules.presentation.service.PresentationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationPackageItem;
import com.deodio.elearning.persistence.model.PresentationScromItem;
import com.deodio.elearning.persistence.model.Slides;
import com.deodio.elearning.persistence.model.customize.PresentationBo;
import com.deodio.elearning.persistence.model.customize.PresentationDiscussionDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 课程presentation相关设置Controller
 * @author P0076724
 *
 */
@Controller
public class PresentationController  extends BaseController{
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private PresentationMapper presentationMapper;
	
	@Autowired
	private ConvertService convertService;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private PresentationPackageService presentationPackageService;
	
	/**
	 * 选课/首页课程列表
	 * @return String
	 */
	@RequestMapping("/presentation/list"+Constants.URL_SUFFIX)
	public String toPresentationPage(Model model,HttpServletRequest request){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("classificationId",DConstants.STRING_ZERO);
		paramsMap.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_PRESENTATION);
		//该分类的叶子分类
		List<Map<String,Object>> classificationsList = classificationService.queryClassificationById(paramsMap);
		//当前账号下课件包含的所有标签
		List<Map<String,Object>> tagsList = tagsService.queryTagsByItemType(paramsMap);
		//当前账号下的所有课件
		//List<Map<String,Object>> presentationList = presentationService.getPresentationList(paramsMap);
		model.addAttribute("classificationsList", classificationsList);
		model.addAttribute("tagsList", tagsList);
		//model.addAttribute("presentationList", presentationList);
		return "modules/presentation/presentation_list";
	}
	
	
	/**
	 * 删除指定章节
	 * @param model
	 * @param request
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/delete"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delPresetation(Model model,HttpServletRequest request,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		int result = AjaxResultModel.FAIL;
		try{
		String userId = this.getCookieUserId(request);
		
		//判断当前章节是否被引用
		
		boolean flag = presentationService.getPresentationIsQuoteForCourse(presentationId);
		if(!flag) {
			arm.setData(0);
			result = AjaxResultModel.SUCCESS;
		}else {
			int rowCount = presentationService.deletePresetation(presentationId, userId);
			if(rowCount > DConstants.NUMBER_ZERO){
				result = AjaxResultModel.SUCCESS;
			}
		}
		

		}catch(Exception ex){
			ex.printStackTrace();
		}
		arm.setStatus(result);
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
	@RequestMapping("/presentation/load_data_by_classifications"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryPresentationByClassification(Model model,HttpServletRequest request,
			@RequestParam(required=false) Integer pageNo,@RequestParam(value="classificationIdList[]",required=false) List classificationIdList,
			@RequestParam(value="tagIdList[]",required=false) List tagIdList,@RequestParam(required=false) Integer pageSize,
			@RequestParam(value="keyword",required=false) String keyword){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
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
		map.put("searchWord", StringUtils.isBlank(keyword)?null:keyword);
		map.put("loginUserId", (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID));
		
		
		
		
		List<Map<String,Object>> list =  presentationService.getPresentationList(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	@RequestMapping("/presentation/publish"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel onPublish(HttpServletRequest request,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("presentationId", presentationId);
		params.put("userId", this.getCookieUserId(request));
		params.put("accountId", this.getCookieAccount(request));
		params.put("groupId", this.getCookieGroupId(request));
		try{
			presentationService.presentationPublish(params);
			arm.setStatus(AjaxResultModel.SUCCESS);
		}catch(Exception ex){
			arm.setStatus(AjaxResultModel.FAIL);
			ex.printStackTrace();
		}
		return arm;
	}
	
	/**
	 * 设置presentation信息页面跳转
	 * @param model
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/profile"+Constants.URL_SUFFIX)
	public String toPresentationSyncProfile(Model model,@RequestParam(required=false) String presentationId){
		//presentationId为空设置presentation相关不可跳转
		Map<String,Object> map = new HashMap<String,Object>();
		if(presentationId != null){
			map.put("presentationId", presentationId);
			Map<String,Object> presentationMap = presentationService.queryPresentation(map);
			model.addAttribute("presentationId", presentationId);
			model.addAttribute("presentationMap",presentationMap);
			//所属已选择分类获取
			List<Map<String,Object>> selectedClassificationList = presentationService.queryClassificationList(map);
			model.addAttribute("selectedClassificationList",selectedClassificationList);
		}
		//所属分类查询（全部）
		map.put("presentationId", "");
		List<Map<String,Object>> classificationList = presentationService.queryClassificationList(map);
		model.addAttribute("classificationList",classificationList);
		return "modules/presentation/presentation_profile";
	}
	
	/**
	 * 设置presentation信息
	 * @param presentationName
	 * @param presentationDesc
	 * @return
	 */
	@RequestMapping("/presentation/submit_profile"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setPresentationInfo(@RequestParam String presentationName,@RequestParam String presentationDesc,
			@RequestParam String presentationId,HttpServletRequest request,@RequestParam String presentationCover,
			@RequestParam String attachId, @RequestParam String tagsJson,@RequestParam String classificationJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String id = "";
			if(null!=presentationId&&!"".equals(presentationId)){
				id = presentationId;
			}else{
				id = AppUtils.uuid();
			}
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			presentationService.insertPresentationInfo(presentationName,presentationDesc, id, userId, presentationCover, attachId,tagsJson,accountId,classificationJson);
			arm.setData(id);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	
	/**
	 * 弹出选择课件格式
	 * @param model
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/content"+Constants.URL_SUFFIX)
	public String toPresentationContentPage(Model model,@RequestParam String presentationId){
		//t_presentation_model_sync
		PresentationBo presentation = presentationService.getPresentationInforById(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentation",presentation);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", presentationId);
		map.put("itemType",DConstants.TAGS_ITEMS_TYPE_PRESENTATION);
		//t_presentation
		//分类标签查询
		List<Map<String,Object>> presentationTagsList = tagsService.queryItemTagsList(map);
		model.addAttribute("presentationTagsList",presentationTagsList);
		return "modules/presentation/presentation_content";
	}
	
	
	/**
	 * 新建课程/设置课件类型
	 * @param presentationModel
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/presentation/set_model"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setModel(@RequestParam String presentationModel,@RequestParam String presentationId,
			HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			presentationService.updatePresentationInfo(presentationId,presentationModel);
			 arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			 arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	
	/**
	 * 新建课程/课件规则设置
	 * @param presentationId
	 * @param isSlideShow
	 * @param isManually
	 * @param length
	 * @param interval
	 * @param isInitSyncPoint
	 * @param persentationPercentage
	 * @param isPassHours
	 * @param isPassElements
	 * @param isPassQuizs
	 * @param isCourse
	 * @return
	 */
	@RequestMapping("/presentation/setFlashRule"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setFlashRule(@RequestParam String presentationId,@RequestParam Integer isSlideShow,@RequestParam(required=false) Integer isManually,
			@RequestParam Long length,@RequestParam Long interval,@RequestParam Boolean isInitSyncPoint,@RequestParam String persentationPercentage,
			@RequestParam Boolean isPassHours,@RequestParam Boolean isPassQuizs,@RequestParam Boolean isCourse,
			HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String uuid = AppUtils.uuid();
			//	课件起点是否设置初始化Slide
			int ifInitSyncPoint = 0;
			if(isInitSyncPoint){
				ifInitSyncPoint = 1;
			}
			//学时间到达要求 
			int ifPassHours = 0;
			if(isPassHours){
				ifPassHours = 1;
			}
//			//通过所有的课件学习和测验 
//			int ifPassElements = 0;
//			if(isPassElements){
//				ifPassElements = 1;
//			}
			//通过Presentation的测验 
			int ifPassQuizs = 0;
			if(isPassQuizs){
				ifPassQuizs = 1;
			}
			//	发布后自动创建同名的课程 
			int ifCourse = 0;
			if(isCourse){
				ifCourse = 1;
			}
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			boolean flag  = presentationService.insertFlashRule(uuid,presentationId,isSlideShow,isManually,length,interval,ifInitSyncPoint,persentationPercentage,
					ifPassHours,ifPassQuizs,ifCourse,userId);
			if(flag) {
				arm.setData("FAIL");
			}else {
				arm.setData(isSlideShow);
			}
			
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	} 
	
	
	/**
	 * 多媒体库查询
	 * @param model
	 * @param accountId
	 * @return
	 */
	@RequestMapping("/presentation/queryMediaFactory"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryMediaFactoryById(HttpServletRequest request,Model model,@RequestParam String accountId,@RequestParam Integer pageNo,
			@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		String userId = this.getCookieUserId(request);
		accountId = this.getCookieAccount(request);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
		map.put("userId", userId);
		//总条数
		//Integer total = presentationService.getMediaCount(map);
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.setPageSize(DConstants.NUMBER_SIX);
		pageRequest.getPagination().setPageSize(DConstants.NUMBER_SIX);
		map.put("pagination", pageRequest);
		map.put("presentationId", presentationId);
		List<Map<String,Object>> list =  presentationService.queryMediaInfoList(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
	    //jsonMap.put("totalRow",pageRequest.getCount());
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 多媒体条件检索
	 * @param model
	 * @param accountId
	 * @param queryMediaInfo
	 * @return
	 */
	@RequestMapping("/presentation/queryMediaInfoList"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryMediaInfoList(HttpServletRequest request,Model model,@RequestParam String accountId,@RequestParam String queryMediaInfo,
			@RequestParam Integer pageNo,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		String  userId = this.getCookieUserId(request);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("accountId", accountId);
		if(null==queryMediaInfo||"".equals(queryMediaInfo)){
			queryMediaInfo = null;
		}
		map.put("queryMediaInfo", queryMediaInfo);
		//总条数
		//Integer total = presentationService.getMediaCount(map);
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.setPageSize(DConstants.NUMBER_SIX);
		pageRequest.getPagination().setPageSize(DConstants.NUMBER_SIX);
		map.put("pagination", pageRequest);
		map.put("presentationId", presentationId);
		List<Map<String,Object>> list =  presentationService.queryMediaInfoList(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
	    jsonMap.put("totalRow",pageRequest.getCount());
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 系统多媒体库存储
	 * @param request
	 * @param sysMediaJson
	 * @return
	 */
	@RequestMapping("/presentation/saveSysMedia"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveSysMedia(HttpServletRequest request,@RequestParam String sysMediaJson,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = this.getCookieUserId(request);
			String accountId = this.getCookieAccount(request);
			//Json字符串转List对象
			Gson gson = new Gson();  
			List<Map<String,Object>> presentationSyncMediaList = gson.fromJson(sysMediaJson, new TypeToken<List<Map<String,Object>>>(){}.getType());
			//获取要插入的MediaList插入t_presentation_sync_media
			presentationService.saveMediaList(presentationSyncMediaList,presentationId,userId,accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	/**
	 * 删除多媒体
	 * @param request
	 * @param id
	 * @param jobId
	 * @param fid
	 * @param logicFilePath
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/presentation/delMedia"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delMedia(HttpServletRequest request,@RequestParam String  mediaId) throws Exception{
		AjaxResultModel arm = new AjaxResultModel();
		
		
		try {
			presentationService.delMedia(mediaId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		
		
//		int num = 0;
//		boolean ifDelete = true;
//		if(null!=jobId&&!"".equals(jobId)){
//			MRResult mRResult = convertService.delConverMedia(jobId,fid);
//			//判断终止转换进程是否成功
//			if(null!=mRResult.getStatus()&&!"".equals(mRResult.getStatus())){
//				ifDelete = false;
//			}
//		}
//		if(ifDelete){
//			if(null!=logicFilePath&&!"".equals(logicFilePath)){
//				//删除源文件
//				uploadService.deleteAttachByAttachDir(logicFilePath);
//				//删除封面
//				String coverPath = logicFilePath.split("\\.")[0]+"/cover.jpg";
//				uploadService.deleteAttachByAttachDir(coverPath);
//			}
//			num = presentationService.delMedia(syncMediaId,mediaId);
//		}
//		if(num>0){
//			arm.setStatus(AjaxResultModel.SUCCESS);
//		}else{
//			arm.setStatus(AjaxResultModel.FAIL);
//		}
		return arm;
	}
	
	/**
	 * 幻灯片系统库查询(所有的)
	 * @param model
	 * @param accountId
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/presentation/querySlideFactory"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel querySlideFactoryById(Model model,@RequestParam String accountId,@RequestParam Integer pageNo,
			@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
		//总条数
		//Integer total = presentationService.getSlideCount(map);
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.setPageSize(DConstants.NUMBER_SIX);
		pageRequest.getPagination().setPageSize(DConstants.NUMBER_SIX);
		/*map.put("end", pageRequest.getPageSize());
		map.put("start",pageRequest.getOffset());*/
		map.put("pagination", pageRequest);
		map.put("presentationId", presentationId);
		List<Map<String,Object>> list =  presentationService.querySlideInfoList(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
	    //jsonMap.put("totalRow",pageRequest.getCount());
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 幻灯片媒体库关键字模糊匹配
	 * @param model
	 * @param accountId
	 * @param querySlideInfo
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/presentation/querySlideInfoList"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel querySlideInfoList(HttpServletRequest request,Model model,@RequestParam String accountId,@RequestParam String querySlideInfo,
			@RequestParam Integer pageNo,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		String userId = this.getCookieUserId(request);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("accountId", accountId);
		if(null==querySlideInfo||"".equals(querySlideInfo)){
			querySlideInfo = null;
		}
		map.put("querySlideInfo", querySlideInfo);
		//总条数
		//Integer total = presentationService.getSlideCount(map);
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.setPageSize(DConstants.NUMBER_SIX);
		pageRequest.getPagination().setPageSize(DConstants.NUMBER_SIX);
		map.put("pagination", pageRequest);
		map.put("presentationId", presentationId);
		List<Map<String,Object>> list =  presentationService.querySlideInfoList(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
	    //jsonMap.put("totalRow",pageRequest.getCount());
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * ppt和图片删除
	 * @param request
	 * @param slideId
	 * @param presentationId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/presentation/delSyncSlide"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delSyncSlide(HttpServletRequest request,@RequestParam String slideId,@RequestParam String presentationId,
			@RequestParam Integer sysFlag) throws Exception{
		AjaxResultModel arm = new AjaxResultModel();
		
		presentationService.delSyncSlide(slideId,presentationId,sysFlag);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * ppt和图片系统图库上传
	 * @param request
	 * @param sysSlideJson
	 * @return
	 */
	@RequestMapping("/presentation/saveSysSlides"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveSysSlides(HttpServletRequest request,@RequestParam String sysSlideJson,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = this.getCookieAccount(request);
			//Json字符串转List对象
			Gson gson = new Gson();  
			List<Map<String,Object>> syncSlideList = gson.fromJson(sysSlideJson, new TypeToken<List<Map<String,Object>>>(){}.getType());
			//获取要插入的MediaList插入t_presentation_sync_media
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			presentationService.saveSyncList(syncSlideList,userId,presentationId,accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	/**
	 * 删除ppt和图片系统图库上传
	 * @param request
	 * @param slideId
	 * @param logicFilePath
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/presentation/delSlide"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delSlide(HttpServletRequest request,@RequestParam String slideId,@RequestParam String logicFilePath) throws Exception{
		AjaxResultModel arm = new AjaxResultModel();
		presentationService.delSlide(slideId,logicFilePath);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 转换完成状态更新
	 * @param request
	 * @param syncMediaId
	 * @return
	 */
	@RequestMapping("/presentation/updateMediaConvertStatus"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateMediaConvertStatus(HttpServletRequest request,@RequestParam String syncMediaId){
		AjaxResultModel arm = new AjaxResultModel();
		//更新转换状态
		presentationService.updateMediaConvertStatus(syncMediaId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 
	 * @param model
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/toMediaEdit{presentationId}"+Constants.URL_SUFFIX)
	public String toMediaEdit(Model model,@PathVariable(value="presentationId") String presentationId){
		//presentationId为空设置presentation相关不可跳转
		model.addAttribute("presentationId", presentationId);
		return "modules/presentation/presentation_media_edit";
	}
	
	/**
	 * 编辑Slides列表数据查询(列表显示)
	 * @param model
	 * @param accountId
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/presentation/sync/edit_slides"+Constants.URL_SUFFIX)
	public String toEditSlidesPage(Model model,@RequestParam String presentationId,@RequestParam(required=false) String querySlidesInfo){
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("presentationId", presentationId);
		map.put("querySlidesInfo", querySlidesInfo);
		List<Map<String,Object>> list =  presentationService.getEditSlidesList(map);
		model.addAttribute("editSlidesList", list);*/
		model.addAttribute("presentationId", presentationId);
		return "modules/presentation/presentation_sync_edit_slides";
	}
	
	/**
	 * 编辑Slides列表数据查询(缩略图)
	 * @param model
	 * @param presentationId
	 * @param querySlidesInfo
	 * @return
	 */
	@RequestMapping("/presentation/sync/edit_slides_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getEditSlidesList(Model model,@RequestParam String presentationId,@RequestParam(required=false) String querySlidesInfo){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> map = new HashMap<String,Object>();
		model.addAttribute("presentationId", presentationId);
		map.put("presentationId", presentationId);
		map.put("querySlidesInfo", querySlidesInfo);
		List<Map<String,Object>> list =  presentationService.getEditSlidesList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", list);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 编辑Medias列表跳转
	 * @param model
	 * @param presentationId
	 * @param queryMediasInfo
	 * @return
	 */
	@RequestMapping("/presentation/sync/edit_medias"+Constants.URL_SUFFIX)
	public String toEditMediasPage(Model model,@RequestParam String presentationId){
		model.addAttribute("presentationId", presentationId);
		return "modules/presentation/presentation_sync_edit_medias";
	}
	
	/**
	 * 编辑Medias列表数据查询(列表显示)
	 * @param model
	 * @param presentationId
	 * @param queryMediasInfo
	 * @return
	 */
	@RequestMapping("/presentation/sync/edit_medias_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getEditMediasList(Model model,@RequestParam String presentationId,@RequestParam(required=false) String queryMediasInfo){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> map = new HashMap<String,Object>();
		model.addAttribute("presentationId", presentationId);
		map.put("presentationId", presentationId);
		map.put("queryMediasInfo", queryMediasInfo);
		List<Map<String,Object>> list =  presentationService.getEditMediasList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", list);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * ppt转换信息查询
	 * @param model
	 * @param slideId
	 * @return
	 */
	@RequestMapping("/presentation/convert_slides_info"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getConvertSlidesInfo(Model model,@RequestParam String slideId){
		AjaxResultModel arm = new AjaxResultModel();
		Slides slides =  presentationService.getConvertSlidesInfo(slideId);
		arm.setData(slides);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 多媒体转换信息查询
	 * @param model
	 * @param slideId
	 * @return
	 */
	@RequestMapping("/presentation/convert_medias_info"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getConvertMediasInfo(Model model,@RequestParam String mediaId,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("presentationId", presentationId);
		paramsMap.put("mediaId", mediaId);
		Map<String,Object> map =  presentationService.getConvertMediasInfo(paramsMap);
		arm.setData(map);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	/**
	 * 判断课程名是否存在
	 * @param model
	 * @param presentationName
	 * @return
	 */
	@RequestMapping("/presentation/isExistName"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel isExistName(Model model,@RequestParam String presentationName,HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("presentationName", presentationName);
		paramsMap.put("accountId", accountId);
		List<Map<String,Object>> list = presentationService.isExistName(paramsMap);
		arm.setData(list);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	@RequestMapping("/presentation/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadPresentationDataList(@RequestParam Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam String keywords,
			@RequestParam(required = false) Integer isPublish,
			HttpServletRequest request,@RequestParam(required = false) String array)
					throws DeodioException {
		List<String> contentList = null;
		try {
			Gson gson = new Gson();
			contentList = gson.fromJson(array, new TypeToken<List<String>>() {
			}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		params.put("keywords", StringUtils.isNotBlank(keywords)?keywords:null);
		params.put("userId", userId);
		params.put("isPublish", isPublish);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		params.put("list", contentList!=null&&!contentList.isEmpty()?contentList:null);
		List<Map<String, Object>> dataList = presentationService.findPresentationListByKeyword(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	@RequestMapping("/presentation/generate_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel generateCourseFromPresentation(@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		 try{
			 arm.setStatus(AjaxResultModel.SUCCESS);
		 }catch(Exception ex){
			 arm.setStatus(AjaxResultModel.FAIL);
		 }
		return arm;
	}
	
	/**
	 * 章节管理-通过简介查看
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/view_detail" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel viewPresentationDetail(@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		 try{
			Map<String,Object> map = new HashMap<String,Object>();
			 map.put("presentationId", presentationId);
			 Map<String,Object> presentationMap = presentationService.queryPresentation(map);	
			 
			 if("0".equals(presentationMap.get("presentation_model").toString())) {
				 
				 List<PresentationScromItem> list = presentationPackageService.queryPresentationScromItem(presentationId);
				 presentationMap.put("itemsList", list);
				 presentationMap.put("itemCount", list.size());
			 }else {
				 
				 List<PresentationPackageItem> list = presentationPackageService.queryPresentationPackageItem(presentationId);
				 presentationMap.put("itemsList", list);
				 presentationMap.put("itemCount", list.size());
			 }
			 
		
			
			 
			 arm.setData(presentationMap);
			 arm.setStatus(AjaxResultModel.SUCCESS);
		 }catch(Exception ex){
			 arm.setStatus(AjaxResultModel.FAIL);
		 }
		return arm;
	}
	/**
	 * 修改章节状态-发布 or 取消发布
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/updatePresentationStatus" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updatePresentationStatus(@RequestParam String presentationId,@RequestParam Short status, HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		 try{
			 String accountId = this.getCookieAccount(request);
			 
			 
			 boolean flag = presentationService.getPresentationIsQuoteForCourse(presentationId);
			 if(flag) {
				 String userId = this.getCookieUserId(request);
				 String groupId = this.getCookieGroupId(request);
				 presentationService.updatePresentationStatus(presentationId,status,accountId,userId,groupId);
			 }else {
				 arm.setData(0);
			 }
			
			
			 arm.setStatus(AjaxResultModel.SUCCESS);
		 }catch(Exception ex){
			 arm.setStatus(AjaxResultModel.FAIL);
		 }
		return arm;
	}
	/**
	 * 分享章节给同组讲师
	 * @param presentationId
	 * @param teachUserId
	 * @param request
	 * @return
	 */
	@RequestMapping("/presentation/insertPresentationOwner" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel insertPresentationOwner(@RequestParam String presentationId,@RequestParam String teachUserId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			
			boolean flag = presentationService.getPresentationOwner(presentationId);
			if(flag) {
				presentationService.insertPresentationOwner(presentationId,teachUserId);				
			}else {
				 arm.setData(Constants.STATUS_ONE);
			}
			 arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			 arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	/**
	 * 查询章节预览评论
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/getPresentationDiscussion" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getPresentationDiscussion(@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			List<PresentationDiscussionDto> list = presentationService.getPresentationDiscussion(presentationId);
			 arm.setData(list);
			 arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			 arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 * 老师章节回复评论
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/savePresentationDiscussion" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel savePresentationDiscussion(@RequestParam String discussContent,@RequestParam String discussPkId, HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			 String userId = this.getCookieUserId(request);
			presentationService.savePresentationDiscussion(discussContent,discussPkId,userId);
			
			 arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			 arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 * 删除帖子回复
	 * @param discussPkId
	 * @return
	 */
	@RequestMapping("/presentation/deleteReply" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteReply(@RequestParam String discussPkId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			
			presentationService.deleteReply(discussPkId);
			
			 arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			 arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 * 删除整个评论包含子评论帖子
	 * @param id
	 */
	@RequestMapping("/presentation/deleteDiscusstion" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteDiscusstion(@RequestParam String id){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			
			presentationService.deleteDiscusstion(id);
			
			 arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			 arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 *  复制章节
	 * @param id
	 * @return
	 */
	@RequestMapping("/presentation/saveCopyPresentation" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCopyPresentation(@RequestParam String presentationId,@RequestParam String copyName,HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("presentationName", copyName);
			List<Map<String,Object>> list = presentationService.isExistName(paramsMap);
			
			if(!list.isEmpty()) {
				 arm.setData(Constants.STATUS_ONE);
			}else {
				//获取章节基本信息
				Presentation  presentaton = presentationService.getPresentationById(presentationId);
				if(copyName.equals(presentaton.getPresentationName())) {
					 arm.setData(Constants.STATUS_ONE);
				}else {
					 String accountId = this.getCookieAccount(request);
					 String userId = this.getCookieUserId(request);
					 presentaton.setPresentationName(copyName);
					 presentationService.saveCopyPresentation(presentaton,userId,accountId,presentaton.getId());
				}
			}
			
			
			 arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			 arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 * 查看当前章节引用
	 * @param presentationId
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/presentation/getPresentationQuote" + Constants.URL_SUFFIX)
	@ResponseBody
	public com.deodio.core.utils.AjaxResultModel getPresentationQuote(@RequestParam String presentationId,@RequestParam Integer pageNo) {
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		

		return getAjaxResult(
				CommonUtils.outPrintJsonMapForPage(presentationService.findPresentationQuoteList(presentationId, pageRequest)),
				AjaxResultModel.SUCCESS);
		
	}
	/**
	 * 查看当前章节被哪些课程引用
	 * @param presentationId
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/presentation/findQuoteForCourse" + Constants.URL_SUFFIX)
	@ResponseBody
	public com.deodio.core.utils.AjaxResultModel findQuoteForCourse(@RequestParam String presentationId,@RequestParam Integer pageNo) {
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		

		return getAjaxResult(
				CommonUtils.outPrintJsonMapForPage(presentationService.findQuoteForCourse(presentationId, pageRequest)),
				AjaxResultModel.SUCCESS);
		
	}
	
}
