package com.deodio.elearning.modules.discussion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.discussion.service.DiscussionService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.DiscussionModel;
import com.deodio.elearning.persistence.model.customize.DiscussionModelBo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class DiscussionController extends BaseController{

	@Autowired
	private DiscussionService discussionService;
	
	/**
	 * 查询课程评论数据
	 * @param model
	 * @param courseId	          课程编号
	 * @return
	 */
	@RequestMapping("/course/discussion/load_list"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseDiscussionList(HttpServletRequest request,@RequestParam String courseId){
		AjaxResultModel arm = new AjaxResultModel();
		try{
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("courseId",courseId);
			List<DiscussionModelBo>  list = discussionService.getCourseDiscussionList(params);
			arm.setData(list);
			arm.setStatus(AjaxResultModel.SUCCESS);
		}catch(Exception ex){
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}
	
	
	/**
	 * 保存/回复课程评论
	 * @param model
	 * @param courseId	          课程编号
	 * @param discussionText  评论内容
	 * @return
	 */
	@RequestMapping("/course/discussion/reply"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveCourseDiscussion(HttpServletRequest request,@RequestParam String discussionJson){
		AjaxResultModel arm = new AjaxResultModel();
		try{
			if(StringUtils.isBlank(discussionJson)){
				throw new DeodioException("发布课程评论：传入参数为空！");
			}
			
			Gson gson = new Gson();
			DiscussionModel discussionModel = gson.fromJson(discussionJson, new TypeToken<DiscussionModel>(){}.getType());
			
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			discussionService.replayCourseDiscussion(discussionModel, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		}catch(Exception ex){
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}
	
	/**
	 * 保存/回复课程评论
	 * @param model
	 * @param courseId	          课程编号
	 * @param discussionText  评论内容
	 * @return
	 */
	@RequestMapping("/course/discussion/load_reply"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCourseDiscussionReplys(HttpServletRequest request,@RequestParam String discussionId){
		AjaxResultModel arm = new AjaxResultModel();
		try{
			if(StringUtils.isBlank(discussionId)){
				throw new DeodioException("加载课程评论回复：传入参数为空！");
			}
			
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("accountId", accountId);
			params.put("discussionId",discussionId);
			List<DiscussionModelBo>  list = discussionService.getCourseDiscussionReplys(params);
			arm.setStatus(AjaxResultModel.SUCCESS);
			arm.setData(list);
		}catch(Exception ex){
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}
	
	/**
	 * 赞同课程评论
	 * @param model
	 * @param courseId	          课程编号
	 * @param discussionText  评论内容
	 * @return
	 */
	@RequestMapping("/course/discussion/agree"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel agreeCourseDiscussion(HttpServletRequest request,@RequestParam String discussionId){
		AjaxResultModel arm = new AjaxResultModel();
		try{
			if(StringUtils.isBlank(discussionId)){
				throw new DeodioException("传入评论编号参数为空！");
			}
			
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			
			discussionService.agreeCourseDiscussion(discussionId, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		}catch(Exception ex){
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}
}
