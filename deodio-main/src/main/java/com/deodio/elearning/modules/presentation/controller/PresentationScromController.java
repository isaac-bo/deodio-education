package com.deodio.elearning.modules.presentation.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.presentation.service.PresentationService;
import com.deodio.elearning.persistence.mapper.PresentationFilesMapper;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelScromMapper;
import com.deodio.elearning.persistence.mapper.PresentationScromItemMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationFiles;
import com.deodio.elearning.persistence.model.PresentationFilesExample;
import com.deodio.elearning.persistence.model.PresentationModelScrom;
import com.deodio.elearning.persistence.model.PresentationModelScromExample;
import com.deodio.elearning.persistence.model.PresentationScromItem;
import com.deodio.elearning.persistence.model.PresentationScromItemExample;

/**
 * 课程presentation相关设置Controller
 * 
 * @author P0076724
 *
 */
@Controller
public class PresentationScromController extends BaseController {

	@Autowired
	private PresentationService presentationService;

	@Autowired
	private PresentationMapper presentationMapper;
	
	@Autowired
	private PresentationModelScromMapper presentationModelScromMapper;
	
	@Autowired
	private PresentationFilesMapper presentationFilesMapper;
	
	@Autowired
	private PresentationScromItemMapper presentationScromItemMapper;
	

	@Autowired
	private UploadService uploadService;

	/**
	 * 新建课程/课件规则设置
	 * 
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
	@RequestMapping("/presentation/scrom/save_scrom_info"+ Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveScromInfo(@RequestParam String presentationId, @RequestParam Integer isCountDown,
			@RequestParam(value="isPassPercentage",required=false)  Integer isPassPercentage,
					@RequestParam String persentationPercentage,@RequestParam Integer isPassElements,@RequestParam Integer isCourse,
					@RequestParam(value="itemIdList[]",required=false) List itemIdList,
					@RequestParam(value="itemTimeList[]",required=false) List itemTimeList,HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			presentationService.saveScromInfo(presentationId,isPassPercentage,persentationPercentage,isPassElements,isCourse,userId,isCountDown,itemIdList,itemTimeList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/presentation/scrom/profile"+Constants.URL_SUFFIX)
	public String toPresentationScromProfilePage(Model model,@RequestParam String presentationId){

		presentationService.initPresentationProfile(model, presentationId);
		
		PresentationFilesExample presentationFilesExample = new PresentationFilesExample();
		presentationFilesExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationFiles> presentationFilesList = presentationFilesMapper.selectByExample(presentationFilesExample);
		if(!presentationFilesList.isEmpty()){
			model.addAttribute("presentationFiles",presentationFilesList.get(0));
		}
		
		PresentationModelScromExample presentationModelScromExample = new PresentationModelScromExample();
		presentationModelScromExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationModelScrom> presentationModelScromList = presentationModelScromMapper.selectByExample(presentationModelScromExample);
		
		if(!presentationModelScromList.isEmpty()){
			model.addAttribute("presentationModelScrom",presentationModelScromList.get(0));
		}
		return "modules/presentation/presentation_scrom_profile";
	}

	
	/**
	 * 设置presentation信息页面跳转
	 * @param model
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/scrom/preview"+Constants.URL_SUFFIX)
	public String toPresentationScromView(Model model,@RequestParam(required=false) String presentationId,
			@RequestParam(required=false) String type,HttpServletResponse response){
		 response.setHeader("Access-Control-Allow-Headers", "accept, content-type");  
		 response.setHeader("Access-Control-Allow-Origin", "*");  
		presentationService.initPresentationProfile(model, presentationId);
		
		Presentation presentation = presentationMapper.selectByPrimaryKey(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentation",presentation);
		model.addAttribute("isPreview",Boolean.TRUE);
		model.addAttribute("type",StringUtils.isBlank(type)?null:type);
		return "modules/presentation/presentation_scrom_preview";
	}
	
	
	@RequestMapping("/presentation/scrom/get_item_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getScromItemList(HttpServletRequest request,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		try{
			PresentationScromItemExample presentationScromItemExample = new PresentationScromItemExample();
			presentationScromItemExample.createCriteria().andPresentationIdEqualTo(presentationId).andIdentifierrefIsNotNull();
			presentationScromItemExample.setOrderByClause("sequence asc,the_level asc");
			
			List <PresentationScromItem> itemList = presentationScromItemMapper.selectByExample(presentationScromItemExample);
			//修改循环方式
			Iterator<PresentationScromItem> itr = itemList.iterator();
			while(itr.hasNext()){
				PresentationScromItem item = itr.next();
				if(StringUtils.isBlank(item.getLaunch())){
					itr.remove();
				}
			}
			arm.setMessage(Integer.toString(itemList.size()));
			arm.setStatus(AjaxResultModel.SUCCESS);
			arm.setData(itemList);
		}catch(Exception e){
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/presentation/scrom/deleteFile"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteScromFile(@RequestParam String presentationId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			uploadService.deleteAttachmentRelId(presentationId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

}
