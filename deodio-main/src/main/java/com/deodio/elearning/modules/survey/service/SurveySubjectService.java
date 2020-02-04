package com.deodio.elearning.modules.survey.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.SurveyItemsMapper;
import com.deodio.elearning.persistence.mapper.SurveySubjectMapper;
import com.deodio.elearning.persistence.model.SurveyItemsExample;
import com.deodio.elearning.persistence.model.SurveySubject;
import com.deodio.elearning.persistence.model.SurveySubjectExample;

@Service
public class SurveySubjectService {

	@Autowired
	private SurveySubjectMapper surveySubjectMapper;

	@Autowired
	private SurveyItemsMapper surveyItemsMapper;

	public String insertSurveySubject(String surveyId, String surveySubject, Integer surveySubjectOrder,
			Integer surveySubjectType, String userId) throws DeodioException {

		String surveySubjectId = AppUtils.uuid();
		SurveySubject record = new SurveySubject();
		record.setId(surveySubjectId);
		record.setSurveyId(surveyId);
		record.setSurveySubject(surveySubject);
		record.setSurveySubjectOrder(surveySubjectOrder);
		record.setSurveySubjectType(surveySubjectType);
		record.setCreateId(userId);
		record.setCreateTime(new Date());
		surveySubjectMapper.insertSelective(record);
		return surveySubjectId;
	}

	public void updateSurveySubject(String surveySubjectId, String surveyId, String surveySubject,
			Integer surveySubjectOrder, Integer surveySubjectType, String userId) throws DeodioException {

		SurveySubject record = new SurveySubject();
		record.setId(surveySubjectId);
		record.setSurveyId(surveyId);
		record.setSurveySubject(surveySubject);
		record.setSurveySubjectOrder(surveySubjectOrder);
		record.setSurveySubjectType(surveySubjectType);
		record.setUpdateId(userId);
		record.setUpdateTime(new Date());
		surveySubjectMapper.updateByPrimaryKeySelective(record);
	}

	public void deleteSurveySubject(String surveySubjectId) throws DeodioException {

		SurveyItemsExample example = new SurveyItemsExample();
		example.createCriteria().andSurveySubjectIdEqualTo(surveySubjectId);
		surveyItemsMapper.deleteByExample(example);
		surveySubjectMapper.deleteByPrimaryKey(surveySubjectId);
	}
	
	/**
	 * 查询问卷调查下的题目
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	public List<SurveySubject> querySurveySubjectList(String surveyId) throws DeodioException {
		
		SurveySubjectExample example = new SurveySubjectExample();
		example.createCriteria().andSurveyIdEqualTo(surveyId);
		example.setOrderByClause("survey_subject_order ASC");
		return surveySubjectMapper.selectByExample(example);
	}
}
