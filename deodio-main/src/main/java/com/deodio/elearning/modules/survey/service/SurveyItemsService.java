package com.deodio.elearning.modules.survey.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.SurveyItemsMapper;
import com.deodio.elearning.persistence.model.SurveyItems;
import com.deodio.elearning.persistence.model.SurveyItemsExample;

@Service
public class SurveyItemsService {

	@Autowired
	private SurveyItemsMapper surveyItemsMapper;

	public String insertSurveyItems(String surveySubjectId, String surveyItemsName, Integer surveyItemsOrder,
			String userId) throws DeodioException {

		String surveyItemsId = AppUtils.uuid();
		SurveyItems surveyItems = new SurveyItems();
		surveyItems.setId(surveyItemsId);
		surveyItems.setSurveyItemsName(surveyItemsName);
		surveyItems.setSurveyItemsOrder(surveyItemsOrder);
		surveyItems.setSurveySubjectId(surveySubjectId);
		surveyItems.setCreateId(userId);
		surveyItems.setCreateTime(new Date());
		surveyItemsMapper.insertSelective(surveyItems);
		return surveyItemsId;
	}

	public void updateSurveyItems(String surveyItemsId, String surveySubjectId, String surveyItemsName,
			Integer surveyItemsOrder, String userId) throws DeodioException {

		SurveyItems surveyItems = new SurveyItems();
		surveyItems.setId(surveyItemsId);
		surveyItems.setSurveyItemsName(surveyItemsName);
		surveyItems.setSurveyItemsOrder(surveyItemsOrder);
		surveyItems.setSurveySubjectId(surveySubjectId);
		surveyItems.setUpdateId(userId);
		surveyItems.setUpdateTime(new Date());
		surveyItemsMapper.updateByPrimaryKeySelective(surveyItems);
	}

	public void deleteSurveyItems(String surveyItemsId) throws DeodioException {

		surveyItemsMapper.deleteByPrimaryKey(surveyItemsId);
	}

	/**
	 * 查询问卷调查题目下选项
	 * @param surveySubjectId
	 * @return
	 * @throws DeodioException
	 */
	public List<SurveyItems> querySurveyItemsList(String surveySubjectId) throws DeodioException {
		
		SurveyItemsExample example = new SurveyItemsExample();
		example.createCriteria().andSurveySubjectIdEqualTo(surveySubjectId);
		example.setOrderByClause("survey_items_order ASC");
		return surveyItemsMapper.selectByExample(example);
	}
}
