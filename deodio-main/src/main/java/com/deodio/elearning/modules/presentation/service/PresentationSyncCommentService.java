package com.deodio.elearning.modules.presentation.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.mapper.PresentationSyncCommentsFollowMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncCommentsItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncCommentsMapper;
import com.deodio.elearning.persistence.mapper.customize.PresentationSyncCommentCustomizeMapper;
import com.deodio.elearning.persistence.model.PresentationSyncComments;
import com.deodio.elearning.persistence.model.PresentationSyncCommentsFollow;
import com.deodio.elearning.persistence.model.PresentationSyncCommentsFollowExample;
import com.deodio.elearning.persistence.model.PresentationSyncCommentsItem;
import com.deodio.elearning.persistence.model.PresentationSyncCommentsItemExample;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class PresentationSyncCommentService {

	
	@Autowired
	private PresentationSyncCommentsMapper presentationSyncCommentsMapper;
	
	@Autowired
	private PresentationSyncCommentsItemMapper presentationSyncCommentsItemMapper;
	
	@Autowired
	private PresentationSyncCommentsFollowMapper presentationSyncCommentsFollowMapper;
	
	@Autowired
	private PresentationSyncCommentCustomizeMapper presentationSyncCommentCustomizeMapper;
	
	public void addComment(String presentationId,String commentJson){
		
		
		Gson _commentJson = new Gson(); 
		Map<String,Object> comment = _commentJson.fromJson(commentJson, new TypeToken<Map<String,Object>>(){}.getType());
		
		addComment(presentationId,comment);
		addCommentItems(comment);
	}
	
	public void addComment(String presentationId,String commentId,String commentItemId,String commentContent,String commentUserId){
		Map<String,Object> comment = new HashMap<String,Object>();
		comment.put("commentItemId", commentItemId);
		comment.put("commentId", commentId);
		comment.put("commentContent", commentContent);
		comment.put("commentUserId", commentUserId);
		addCommentItems(comment);
	}
	
	
	public void addComment(String presentationId,Map<String,Object> comment){
		PresentationSyncComments presentationSyncComments = new PresentationSyncComments();
		presentationSyncComments.setId((String)comment.get("commentId"));
		presentationSyncComments.setPresentationId(presentationId);
		presentationSyncComments.setCommentSourceType((BigDecimal.valueOf((Double)comment.get("commentContainer"))).shortValue());
		presentationSyncComments.setCommentX((BigDecimal.valueOf((Double)comment.get("commentPosX"))).intValue());
		presentationSyncComments.setCommentY((BigDecimal.valueOf((Double)comment.get("commentPosY"))).intValue());
		presentationSyncComments.setCreateId((String)comment.get("commentUserId"));
		presentationSyncComments.setUpdateId((String)comment.get("commentUserId"));
		presentationSyncComments.setCommentTime(Integer.parseInt(comment.get("commentDuration").toString()));
		presentationSyncComments.setCreateTime(new Date());
		presentationSyncComments.setUpdateTime(new Date());
		
		presentationSyncCommentsMapper.insertSelective(presentationSyncComments);
	}
	
	private void addCommentItems(Map<String,Object> comment){
		
		PresentationSyncCommentsItem presentationSyncCommentsItem = new PresentationSyncCommentsItem();
		presentationSyncCommentsItem.setId((String)comment.get("commentItemId"));
		presentationSyncCommentsItem.setCommentId((String)comment.get("commentId"));
		presentationSyncCommentsItem.setComment(StringUtils.trim((String)comment.get("commentContent")));
		presentationSyncCommentsItem.setCreateId((String)comment.get("commentUserId"));
		presentationSyncCommentsItem.setUpdateId((String)comment.get("commentUserId"));
		presentationSyncCommentsItem.setCreateTime(new Date());
		presentationSyncCommentsItem.setUpdateTime(new Date());
		
		presentationSyncCommentsItemMapper.insertSelective(presentationSyncCommentsItem);
	}


	public List<Map<String,Object>> listComment(String presentationId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("presentationId", presentationId);
		List<Map<String,Object>> commentList = presentationSyncCommentCustomizeMapper.getCommentList(params);
		return commentList;
	}
	
	public List<Map<String,Object>> listCommentItems(String presentationId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("presentationId", presentationId);
		List<Map<String,Object>> commentList = presentationSyncCommentCustomizeMapper.getCommentItemList(params);
		return commentList;
	}

	public void delComment(String presentationId, String commentId,String isItem) {

		if(isItem.equalsIgnoreCase("false")){
			delComment(commentId);
		}else{
			delCommentItem(commentId);	
		}
		
	}
	
	public void delComment(String commentId){
		presentationSyncCommentsMapper.deleteByPrimaryKey(commentId);
		PresentationSyncCommentsItemExample example = new PresentationSyncCommentsItemExample();
		example.createCriteria().andCommentIdEqualTo(commentId);
		presentationSyncCommentsItemMapper.deleteByExample(example);
	}
	
	public void delCommentItem(String itemId){
		presentationSyncCommentsItemMapper.deleteByPrimaryKey(itemId);
	}

	public void updateComment(String presentationId, String commentId, String isItem,String comment,String userId) {
		PresentationSyncCommentsItem item = new PresentationSyncCommentsItem();
		item.setComment(comment);
		item.setId(commentId);
		item.setUpdateId(userId);
		item.setUpdateTime(new Date());
		presentationSyncCommentsItemMapper.updateByPrimaryKeySelective(item);
	}

	public void handleComment(String presentationId, String commentId,String userId) {
		PresentationSyncComments presentationSyncComments = presentationSyncCommentsMapper.selectByPrimaryKey(commentId);
		presentationSyncComments.setId(commentId);
		presentationSyncComments.setCommentHandle(presentationSyncComments.getCommentHandle() == null
				|| presentationSyncComments.getCommentHandle().toString().equals(DConstants.STRING_ZERO) 
				? Short.valueOf(DConstants.STRING_ONE) : Short.valueOf(DConstants.STRING_ZERO));
		presentationSyncComments.setUpdateId(userId);
		presentationSyncComments.setUpdateTime(new Date());
		presentationSyncCommentsMapper.updateByPrimaryKeySelective(presentationSyncComments);
		
	}

	public Integer followComment(String presentationId, String commentId,String commentUserId) {
		PresentationSyncCommentsFollowExample commentsFollowExample = new PresentationSyncCommentsFollowExample();
		commentsFollowExample.createCriteria().andCommentItemIdEqualTo(commentId).andCreateIdEqualTo(commentUserId);
		List<PresentationSyncCommentsFollow> commentsFollows = presentationSyncCommentsFollowMapper.selectByExample(commentsFollowExample);
		if(commentsFollows.size() > 0){
			presentationSyncCommentsFollowMapper.deleteByExample(commentsFollowExample);
		}else{
			PresentationSyncCommentsFollow commentsFollow = new PresentationSyncCommentsFollow();
			commentsFollow.setId(AppUtils.uuid());
			commentsFollow.setCommentItemId(commentId);
			commentsFollow.setCreateId(commentUserId);
			commentsFollow.setCreateTime(new Date());
			commentsFollow.setUpdateId(commentUserId);
			commentsFollow.setUpdateTime(new Date());
			presentationSyncCommentsFollowMapper.insertSelective(commentsFollow);
		}
		
		PresentationSyncCommentsFollowExample commentsFollowExample2 = new PresentationSyncCommentsFollowExample();
		commentsFollowExample2.createCriteria().andCommentItemIdEqualTo(commentId);
		return (presentationSyncCommentsFollowMapper.selectByExample(commentsFollowExample2)).size();
		
	}
	

}
