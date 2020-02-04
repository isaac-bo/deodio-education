package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

public interface PresentationSyncCommentCustomizeMapper {

	public List<Map<String,Object>> getCommentList(Map<String,Object> params);

	public List<Map<String, Object>> getCommentItemList(Map<String, Object> params);
	
	
	
}
