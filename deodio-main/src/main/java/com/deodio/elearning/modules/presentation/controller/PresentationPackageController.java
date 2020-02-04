package com.deodio.elearning.modules.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.plexus.util.StringUtils;
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
import com.deodio.elearning.modules.presentation.service.PresentationPackageService;
import com.deodio.elearning.modules.presentation.service.PresentationService;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.mapper.PresentationPackageItemMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationModelPackage;
import com.deodio.elearning.persistence.model.PresentationModelSync;
import com.deodio.elearning.persistence.model.PresentationPackageItem;
import com.deodio.elearning.persistence.model.PresentationPackageItemExample;
import com.deodio.elearning.persistence.model.customize.PresentationBo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class PresentationPackageController extends BaseController {

	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private PresentationPackageService presentationPackageService;
	
	@Autowired
	private PresentationMapper presentationMapper;
	
	@Autowired
	private PresentationPackageItemMapper presentationPackageItemMapper;
	
	/**
	 * 非标准课件包
	 * @param model
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/package/profile"+Constants.URL_SUFFIX)
	public String toPresentationPackageProfilePage(Model model,@RequestParam String presentationId,HttpServletRequest request){
		//获取presetation 共通信息
		presentationService.initPresentationProfile(model,presentationId);
//		String accountId = this.getCookieAccount(request);
		//课件内容设置查询
		PresentationModelPackage packageinfo = presentationPackageService.queryPresentationPackageInfo(presentationId);
		model.addAttribute("presetationPackage",packageinfo);
		List<PresentationPackageItem> presentationPackageItem=presentationPackageService.queryPresentationPackageItem(presentationId);
		model.addAttribute("packageItemList",presentationPackageItem);
		
		return "modules/presentation/presentation_package_profile";
	}
	
	
	@RequestMapping("/presentation/package/preview"+Constants.URL_SUFFIX)
	public String toPresentationPackagePreview(Model model,@RequestParam(required=false) String presentationId,
			@RequestParam(required=false) String type){
		presentationService.initPresentationProfile(model, presentationId);
		Presentation presentation = presentationMapper.selectByPrimaryKey(presentationId);
		PresentationModelPackage packageinfo = presentationPackageService.queryPresentationPackageInfo(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentation",presentation);
		model.addAttribute("isPreview",Boolean.TRUE);
		model.addAttribute("type",StringUtils.isBlank(type)?null:type);
		model.addAttribute("autoPlay",packageinfo.getIsCountDown());
		return "modules/presentation/presentation_package_preview";
	}
	
	public static String getFileNameNoEx(String filename) { 
        if ((filename != null) && (filename.length() > 0)) { 
            int dot = filename.lastIndexOf('.'); 
            if ((dot >-1) && (dot < (filename.length()))) { 
                return filename.substring(0, dot); 
            } 
        } 
        return filename; 
    }
	
	
	/**
	 * 非标准课件包设置
	 * @param presentationId
	 * @param isCountDown
	 * @param countDown
	 * @param persentationPercentage
	 * @param isPassHours
	 * @param isPassElements
	 * @param isPassQuizs
	 * @param isCourse
	 * @param request
	 * @return
	 */
	@RequestMapping("/presentation/package/sumbit_profile"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitPresentationPackageProfile(@RequestParam String presentationId,@RequestParam(required=false) String packagesItemsJson,
			@RequestParam(required=false) String packageProfileJson,HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			//非标准课件包设置
			presentationPackageService.submitPresentationPackageProfile(packagesItemsJson,userId,presentationId,packageProfileJson);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	/**
	 * 非标准课件
	 * @param model
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/package/files"+Constants.URL_SUFFIX)
	public String toImportPackagePage(Model model,@RequestParam String presentationId,HttpServletRequest request){
		
		PresentationBo presentation = presentationService.getPresentationInforById(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentation",presentation);
		
		//t_presentation_model_sync
		PresentationModelSync presentationModelSync = presentationService.queryPresentationModelSync(presentationId);
		model.addAttribute("presentationModelSync",presentationModelSync);
		//t_presentation
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("presentationId", presentationId);
		Map<String,Object> presentationMap = presentationService.queryPresentation(map);
		model.addAttribute("presentationMap",presentationMap);
		
//		Map<String,Object> presetationPackage = presentationService.queryPresentationModelPackage(map);
//		model.addAttribute("presetationPackage",presetationPackage);
		
		//课件包查询
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		map.put("accountId", accountId);
		List<Map<String,Object>> presentationFilesList =  presentationPackageService.selectFileList(map);
		model.addAttribute("presentationFilesList",presentationFilesList);
		return "modules/presentation/presentation_package_files";
	}
	
	/**
	 * 删除非标准课件
	 * @param request
	 * @param presentationFilesId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/presentation/package/del_package_files"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delPackageFiles(HttpServletRequest request,@RequestParam String presentationFilesId,@RequestParam String presentationId) throws Exception{
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("presentationFilesId", presentationFilesId);
		map.put("presentationId", presentationId);
		presentationService.delPresentationFiles(map);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 非标准课件系统库查询(所有的)
	 * @param model
	 * @param accountId
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/presentation/package/load_package_files"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadPackageFiles(HttpServletRequest request,Model model,@RequestParam String accountId,@RequestParam Integer pageNo,
			@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> map = new HashMap<String,Object>();
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		map.put("userId", userId);
		//总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.setPageSize(DConstants.NUMBER_SIX);
		pageRequest.getPagination().setPageSize(DConstants.NUMBER_SIX);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		map.put("pagination", pageRequest);
		map.put("presentationId", presentationId);
		jsonMap.put("dataList", presentationPackageService.findAttachmentPresentationPackageList(map));
		jsonMap.put("currePage", pageNo);
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 系统课件包查询
	 * @param model
	 * @param accountId
	 * @param keyWords
	 * @param pageNo
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/package/search_package_files"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryPackageInfoList(Model model,@RequestParam String accountId,@RequestParam String keyWords,
			@RequestParam Integer pageNo,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountId", accountId);
		if(null==keyWords||"".equals(keyWords)){
			keyWords = null;
		}
		map.put("keyWords", keyWords);
		//总条数
		//Integer total = presentationService.getSlideCount(map);
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.setPageSize(DConstants.NUMBER_SIX);
		pageRequest.getPagination().setPageSize(DConstants.NUMBER_SIX);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		map.put("pagination", pageRequest);
		map.put("presentationId", presentationId);
		jsonMap.put("dataList", presentationPackageService.findAttachmentPresentationPackageList(map));
		jsonMap.put("currePage", pageNo);
	    jsonMap.put("totalPage",pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * ppt和图片系统图库上传
	 * @param request
	 * @param sysSlideJson
	 * @return
	 */
	@RequestMapping("/presentation/package/save_package_files"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveSysPackages(HttpServletRequest request,@RequestParam String sysPackageJson,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = this.getCookieAccount(request);
		try {
			//Json字符串转List对象
			Gson gson = new Gson();  
			List<Map<String,Object>> syncPackageList = gson.fromJson(sysPackageJson, new TypeToken<List<Map<String,Object>>>(){}.getType());
			//获取要插入的MediaList插入t_presentation_sync_media
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			presentationPackageService.saveSyncPackageList(syncPackageList,userId,accountId,presentationId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/presentation/package/get_item_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getPackageItemList(HttpServletRequest request,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		try{
//			PresentationPackageItemExample presentationPackageItemExample = new PresentationPackageItemExample();
//			presentationPackageItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
			arm.setData(presentationPackageService.queryPresentationPackageItem(presentationId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		}catch(Exception e){
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
}
