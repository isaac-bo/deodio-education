package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.deodio.elearning.persistence.model.QuizItems;
import com.deodio.elearning.persistence.model.QuizSubject;
import com.deodio.elearning.persistence.model.QuizSubjectRel;

public interface QuizSubjectCustomizeMapper {
	
	List<Map<String,Object>> findSubjectList(Map<String,Object> params);
	
	List<Map<String,Object>> getSubjctCount(Map<String, Object> params);
	
	List<Map<String,Object>> getSubjctList(Map<String, Object> params);
	
	List<Map<String,Object>> findSubjectByBanksId(Map<String, Object> params);
	
	List<Map<String,Object>> getSubjectDetail(List<String> params);
	
	List<Map<String,Object>> getSubjectRandoms(Map<String, Object> params);
	//查询试卷题目
	List<Map<String,Object>> queryQuizSubjectList(@Param("quizId") String quizId);
	//查询引用该试卷题目的试卷是否发布
	Integer queryQuizIsPublishBySubjectId(@Param("subjectId") String subjectId);
	//批量添加试卷题目
	void insertBatchQuizSubject(List<QuizSubject> list);
	//批量添加试卷题目关联信息
	void insertBatchQuizSubjectRel(List<QuizSubjectRel> list);
	//批量添加试卷题目选项
	void insertBatchQuizItem(List<QuizItems> list);
	//查询试卷下所有试题
	List<QuizSubject> queryQuizSubjectByQuizId(@Param("quizId") String quizId);
	//查询试题下所有选项
	List<QuizItems> queryQuizItemById(@Param("id") String id);
	//查询自动试卷下试题
	List<Map<String, Object>> queryAutoQuizSubjectList(Map<String, Object> params);
}
