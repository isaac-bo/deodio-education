package com.deodio.elearning.modules.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.elearning.modules.presentation.service.PresentationSyncCommentService;
import com.deodio.elearning.persistence.model.AjaxResultModel;

@Controller
public class PresentationSyncCommentController extends BaseController{
	
	@Autowired
	private PresentationSyncCommentService presentationSyncCommentService;

	
	@RequestMapping("/presentation/sync/comment/add"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel addComment(Model model,@RequestParam String presentationId,@RequestParam String commentJson){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			presentationSyncCommentService.addComment(presentationId, commentJson);
//			arm.setData(presentationService.getPresentation(presentationId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	
	@RequestMapping("/presentation/sync/comment/add_item"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel addItemComment(Model model, @RequestParam String presentationId,
			@RequestParam String commentId, @RequestParam String commentItemId,
			@RequestParam String commentContent,@RequestParam String commentUserId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			presentationSyncCommentService.addComment(presentationId,commentId,commentItemId,commentContent,commentUserId);
			// arm.setData(presentationService.getPresentation(presentationId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	
	@RequestMapping("/presentation/sync/comment/del"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delComment(Model model, @RequestParam String presentationId,
			@RequestParam String commentId, @RequestParam String isItem) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			presentationSyncCommentService.delComment(presentationId,commentId,isItem);
			// arm.setData(presentationService.getPresentation(presentationId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/presentation/sync/comment/update"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateComment(Model model, @RequestParam String presentationId,
			@RequestParam String commentId, @RequestParam String commentContent,@RequestParam String commentUserId,@RequestParam String isItem) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			presentationSyncCommentService.updateComment(presentationId,commentId,isItem,commentContent,commentUserId);
			// arm.setData(presentationService.getPresentation(presentationId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	

	@RequestMapping("/presentation/sync/comment/handle"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel handleComment(Model model, @RequestParam String presentationId,@RequestParam String commentId,@RequestParam String commentUserId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			presentationSyncCommentService.handleComment(presentationId,commentId,commentUserId);
			// arm.setData(presentationService.getPresentation(presentationId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	@RequestMapping("/presentation/sync/comment/follow"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel followComment(Model model, @RequestParam String presentationId,@RequestParam String commentId,@RequestParam String commentUserId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			// arm.setData(presentationService.getPresentation(presentationId));
			arm.setData(presentationSyncCommentService.followComment(presentationId,commentId,commentUserId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	
	@RequestMapping("/presentation/sync/comment/list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel listComment(Model model,@RequestParam String presentationId){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			List<Map<String,Object>> commentList = presentationSyncCommentService.listComment(presentationId);
			List<Map<String,Object>> itemList = presentationSyncCommentService.listCommentItems(presentationId);
			
			for(Map<String,Object> comment : commentList){
				List<Map<String,Object>> _itemList = new ArrayList<Map<String,Object>>();
				for(Map<String,Object> item : itemList){
					if(comment.get("id").equals(item.get("comment_id"))){
						_itemList.add(item);
					}
				}
				
				comment.put("itemList", _itemList);
			}
			
			
			arm.setData(commentList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
}
