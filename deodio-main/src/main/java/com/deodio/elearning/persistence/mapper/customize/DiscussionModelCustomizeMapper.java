package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.customize.DiscussionModelBo;

public interface DiscussionModelCustomizeMapper {
    
	List<DiscussionModelBo> queryCourseDiscussionList(Map<String,Object> params);
	
	List<DiscussionModelBo> queryCourseDiscussionReplys(Map<String,Object> params);
	
	Integer updateCourseDiscussionReplyNum(Map<String,Object> params);
	
	Integer updateCourseDiscussionAgreeNum(Map<String,Object> params);
}