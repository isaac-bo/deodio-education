package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.deodio.elearning.persistence.model.SurveyItems;
import com.deodio.elearning.persistence.model.SurveySubject;
import com.deodio.elearning.persistence.model.SurveyUserItems;

public interface SurveyCustomizeMapper {

	public void insertsurveySubject(List<SurveySubject> surveySubjectList);

	public void insertSurveyItems(List<SurveyItems> surveyItemsList);
	
	public String getGroupSurvey(String groupId);
	
	public void deleteSurveyItems(String surveyId);
	
	public List<Map<String,Object>> getSurveyList(Map<String,Object> params);
	
	public List<Map<String,Object>> getSurveyListByAccountId(Map<String,Object> params);
	
	public List<Map<String,Object>> findCourseManagerSurveyList(Map<String,Object> params);
	public List<Map<String,Object>> findCourseSurveyList(Map<String,Object> params);
	public List<Map<String,Object>> findSurveyList(Map<String,Object> params);
	
	public List<Map<String, Object>> getSubjectAndItems(Map<String, Object> params);
	
	public List<Map<String, Object>> deleteItemsBySurveyId(String surveyId);
	
	public List<Map<String, Object>> querySubjectList(@Param("surveyId")String surveyId);
	public void insertUserSurveyforGroup(List<SurveyUserItems> surveyUserItemsList);
	public List<Map<String,Object>> findSurveyResultListByGroupId(Map<String,Object> params);
	public int getSurveyResultCountByGroupId(Map<String,Object> params);
	public List<Map<String,Object>> getSurveyResultByUserId(Map<String,Object> params);
	/*获取问卷调查的引用*/
	List<Map<String, Object>> findQuoteSurveyList(Map<String, Object> params);
	/*获取相同小组的讲师*/
	List<Map<String, Object>> findShareSurveyTrainee(Map<String, Object> params);
	/*设置问卷调查的编辑权限*/
	void setShareSurveyTrainee(Map<String, Object> params);
	/*设置问卷调查是否正在被编辑*/
	void setShareSurveyIsEditNow(Map<String, Object> params);
	Map<String, Object> getSurveyBaseInfo(Map<String, Object> params);

	public List<Map<String, Object>> getAllSurveyBySurveyName(Map<String, Object> params);
}
