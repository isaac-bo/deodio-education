package com.deodio.elearning.modules.presentation.controller;

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

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.presentation.service.PresentationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationModelSync;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;
import com.deodio.elearning.persistence.model.PresentationSyncMediaExample;
import com.deodio.elearning.persistence.model.Slides;
import com.deodio.elearning.persistence.model.customize.PresentationBo;

@Controller
public class PresentationSyncController extends BaseController {
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private PresentationMapper presentationMapper;
	
	@Autowired
	private TagsService tagsService;
	
	
	@RequestMapping("/presentation/sync/content"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getPresentation(Model model,@RequestParam String presentationId,
			@RequestParam(value="type",required=false) String type){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			
			arm.setData(presentationService.getPresentation(presentationId,type));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	} 
	
	/**
	 * 设置课件规则
	 * @param model
	 * @param presentationId
	 * @return
	 */
	@RequestMapping("/presentation/sync/profile"+Constants.URL_SUFFIX)
	public String toPresentationSyncProfilePage(Model model,@RequestParam String presentationId){

		viewSync(model, presentationId,null);
		return "modules/presentation/presentation_sync_profile";
	}
	
	/**
	 * 新建课程/设置 presentation相关跳转(相关附件)
	 * @return String
	 */
	@RequestMapping("/presentation/sync/media"+Constants.URL_SUFFIX)
	public String toRelevantAttachment(Model model,@RequestParam String presentationId
			,@RequestParam Integer showType){
		viewSync(model, presentationId,showType);
		
		
		
		return showType==1?"modules/presentation/presentation_sync_media":"modules/presentation/presentation_sync_slides";
	}
	/**
	 * 预览第三种课件
	 * @param model
	 * @param presentationId
	 * @param type
	 * @return
	 */
	@RequestMapping("/presentation/sync/preview"+Constants.URL_SUFFIX)
	public String toPrivewSyncMedia(Model model,@RequestParam(required=false) String presentationId,
			@RequestParam(required=false) String type) {
		
		// 获取视频课件形势
		
		PresentationModelSync sync = presentationService.getSildesByPresentationId(presentationId);
		
		viewSync(model, presentationId,sync.getIsSlideShow().intValue());
		model.addAttribute("type", type);
		return sync.getIsSlideShow().intValue()==1?"modules/presentation/presentation_sync_media":"modules/presentation/presentation_sync_slides";
	}

	private void viewSync(Model model, String presentationId,Integer showType) {
		
		if(showType!=null) {
			if(showType==1) {
				// 获取视频长度
				PresentationSyncMedia media = presentationService.getPresentationSyncMediaInfor(presentationId);
				model.addAttribute("mediaLength",media==null?0:media.getMediaLength()*160);
			}else {
				// 获取PPT 时长
				PresentationModelSync syncSlides = presentationService.getPresentationModelSyncInfor(presentationId);
				model.addAttribute("slideLength",syncSlides.getSlideLength()*160);
			}
		}
			
		PresentationBo presentation = presentationService.getPresentationInforById(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentation",presentation);
		
		PresentationModelSync presentationModelSync = presentationService.queryPresentationModelSync(presentationId);
		model.addAttribute("presentationModelSync",presentationModelSync);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("presentationId", presentationId);
		map.put("itemId", presentationId);
		map.put("itemType", DConstants.TAGS_ITEMS_TYPE_PRESENTATION);
		Map<String,Object> presentationMap = presentationService.queryPresentation(map);
		model.addAttribute("presentationMap",presentationMap);
		
		List<Map<String,Object>> presentationTagsList = tagsService.queryItemTagsList(map);
		model.addAttribute("presentationTagsList",presentationTagsList);
	
		
	}
	
	
	
	
	/**
	 * 新建课程/设置 presentation相关跳转(选择要导入的课件)
	 * @return String
	 */
	@RequestMapping("/presentation/sync/files"+Constants.URL_SUFFIX)
	public String toImportCoursewarePage(Model model,@RequestParam String presentationId){
		
		PresentationBo presentation = presentationService.getPresentationInforById(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentation",presentation);
		
		//t_presentation_model_sync
		PresentationModelSync presentationModelSync = presentationService.queryPresentationModelSync(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentationModelSync",presentationModelSync);
		//t_presentation
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("presentationId", presentationId);
		Map<String,Object> presentationMap = presentationService.queryPresentation(map);
		//多媒体查询
		PresentationSyncMediaExample example = new PresentationSyncMediaExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationSyncMedia> list =  presentationService.selectByExample(example);
		//幻灯片查询
		List<Slides> slidesList  = presentationService.selectSyncSlidesList(presentationId);
		model.addAttribute("presentationMap",presentationMap);
		model.addAttribute("presentationSyncMediaList",list);
		model.addAttribute("syncSlidesList",slidesList);
		return "modules/presentation/presentation_sync_files";
	}
	
	
	
	@RequestMapping("/presentation/sync/save"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel savePresentation(Model model,@RequestParam String presentationId,@RequestParam String currentUserId,@RequestParam String pointsJson,@RequestParam String mediasJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			
//			 arm.setData(presentationService.getPresentation(presentationId));
			presentationService.savePresentation(presentationId,currentUserId,pointsJson,mediasJson);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	} 
}
