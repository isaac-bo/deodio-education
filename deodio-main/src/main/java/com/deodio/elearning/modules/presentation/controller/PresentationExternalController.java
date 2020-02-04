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

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.presentation.service.PresentationExternalService;
import com.deodio.elearning.modules.presentation.service.PresentationService;
import com.deodio.elearning.persistence.mapper.PresentationExternalItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelExternalMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.PresentationExternalItem;
import com.deodio.elearning.persistence.model.PresentationExternalItemExample;
import com.deodio.elearning.persistence.model.PresentationModelExternal;
import com.deodio.elearning.persistence.model.PresentationModelExternalExample;

@Controller
public class PresentationExternalController extends BaseController{
	
	@Autowired
	private PresentationModelExternalMapper presentationModelExternalMapper;
	
	@Autowired
	private PresentationExternalItemMapper presentationExternalItemMapper;
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private PresentationExternalService presentationExternalService;

	@RequestMapping("/presentation/external/profile"+Constants.URL_SUFFIX)
	public String toPresentationExternalProfilePage(Model model,@RequestParam String presentationId){

		presentationService.initPresentationProfile(model, presentationId);
		
		PresentationModelExternalExample presentationModelExternalExample = new PresentationModelExternalExample();
		presentationModelExternalExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationModelExternal> presentationModelExternalList = presentationModelExternalMapper.selectByExample(presentationModelExternalExample);
		
		if(!presentationModelExternalList.isEmpty()){
			model.addAttribute("presentationModelExternal",presentationModelExternalList.get(0));
		}
		
		PresentationExternalItemExample presentationExternalItemExample = new PresentationExternalItemExample();
		presentationExternalItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationExternalItem> presentationExternalItemList = presentationExternalItemMapper.selectByExample(presentationExternalItemExample);
		
		if(!presentationExternalItemList.isEmpty()){
			model.addAttribute("presentationExternalItem",presentationExternalItemList.get(0));
		}
		
		return "modules/presentation/presentation_external_profile";
	}
	
	@RequestMapping("/presentation/external/preview"+Constants.URL_SUFFIX)
	public String toPresentationExternalPreviewPage(Model model,@RequestParam String presentationId,
			@RequestParam(required=false) String type){

		presentationService.initPresentationProfile(model, presentationId);
		
		PresentationModelExternalExample presentationModelExternalExample = new PresentationModelExternalExample();
		presentationModelExternalExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationModelExternal> presentationModelExternalList = presentationModelExternalMapper.selectByExample(presentationModelExternalExample);
		
		if(!presentationModelExternalList.isEmpty()){
			model.addAttribute("presentationModelExternal",presentationModelExternalList.get(0));
		}
		
		PresentationExternalItemExample presentationExternalItemExample = new PresentationExternalItemExample();
		presentationExternalItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationExternalItem> presentationExternalItemList = presentationExternalItemMapper.selectByExample(presentationExternalItemExample);
		
		if(!presentationExternalItemList.isEmpty()){
			model.addAttribute("presentationExternalItem",presentationExternalItemList.get(0));
		}
		
		model.addAttribute("isPreview",Boolean.TRUE);
		model.addAttribute("type",StringUtils.isBlank(type)?null:type);
		return "modules/presentation/presentation_external_preview";
	}
	
	
	@RequestMapping("/presentation/external/items"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getExternalItems(Model model,@RequestParam String presentationId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			
			Map<String,Object> result = new HashMap<String,Object>();
			presentationService.initPresentationProfile(model, presentationId);
			
			PresentationModelExternalExample presentationModelExternalExample = new PresentationModelExternalExample();
			presentationModelExternalExample.createCriteria().andPresentationIdEqualTo(presentationId);
			List<PresentationModelExternal> presentationModelExternalList = presentationModelExternalMapper.selectByExample(presentationModelExternalExample);
			
			if(!presentationModelExternalList.isEmpty()){
				result.put("presentationModelExternal",presentationModelExternalList.get(0));
			}
			
			PresentationExternalItemExample presentationExternalItemExample = new PresentationExternalItemExample();
			presentationExternalItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
			List<PresentationExternalItem> presentationExternalItemList = presentationExternalItemMapper.selectByExample(presentationExternalItemExample);
			
			if(!presentationExternalItemList.isEmpty()){
				result.put("presentationExternalItem",presentationExternalItemList.get(0));
			}
			
			arm.setData(result);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	
	@RequestMapping("/presentation/external/save_external_info"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveExternalInfo(@RequestParam String presentationId, @RequestParam Integer externalType,@RequestParam Integer isCourse,
					@RequestParam String externalUrl,HttpServletRequest request,@RequestParam String externalVideoUrl) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			presentationExternalService.saveExternalInfo(presentationId, isCourse, userId, externalType, externalUrl,externalVideoUrl);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
}
