package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.AccountClassificationRel;
import com.deodio.elearning.persistence.model.Classification;
import com.deodio.elearning.persistence.model.ClassificationItemsRel;

public interface ClassificationCustomizeMapper {
	
	public List<Map<String,Object>> getCTree(Map<String,Object> params);
	
	public List<Map<String,Object>> getKnowledgeTree(Map<String,Object> params);
	
	public List<Map<String,Object>> findClassifications(Map<String,Object> params);
	
	public void delAllClassifications(List<Classification> params);
	
	public int deleteClassificationItemsRel(Map<String,Object> params);
	
	public int insertClassificationItemsRel(List<ClassificationItemsRel> list);
	
	public List<Map<String,Object>>  queryClassificationById(Map<String,Object> params);
	
	public Integer numChildrenClassification(Map<String,Object> params);
	
	public List<Map<String, Object>> getLeftCtreeByText(Map<String, Object> params);
	
	public List<Map<String, Object>> getLeftCtreeByLevel(Map<String, Object> params);
	
	public List<Map<String, Object>> queryReversionCtreeByText(Map<String, Object> params);
	
	public Map<String, Object> queryClassificationDesc(String id);
	
	public String querySelectedClassificationNameByItemId(Map<String,Object> params);
	
	public List<Map<String, Object>> querySelectedClassificationByItemId(Map<String,Object> params);
	
	public int delAllClassificationsInAccount(Map<String,Object> params);
	
	public int getSelectedTopSysClassificationCount(Map<String,Object> params);
	
	public List<Map<String,Object>> getRegisterClassificationList(Map<String,String> paramsMap);
	
	public int insertSysClassificationsAccountRel(List<AccountClassificationRel> list);
	
	public int getUpdateClassificationName(Map<String,String> paramsMap);
	
}
