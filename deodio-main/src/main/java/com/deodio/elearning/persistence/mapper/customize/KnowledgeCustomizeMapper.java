package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.KnowledgePoints;

public interface KnowledgeCustomizeMapper {

	public List<Map<String,Object>> findKnowledgeList(Map<String,Object> params);
	
	public void delAllKnowledgePoints(List<KnowledgePoints> params);

	public List<Map<String, Object>> getKnowledgePointByClassificationId(Map<String,Object> params);
}
