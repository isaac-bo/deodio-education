package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.deodio.elearning.persistence.model.QuizAutoRuleBank;
import com.deodio.elearning.persistence.model.QuizAutoRuleSubject;
import com.deodio.elearning.persistence.model.QuizItems;
import com.deodio.elearning.persistence.model.QuizSubject;
import com.deodio.elearning.persistence.model.QuizSubjectRel;
import com.deodio.elearning.persistence.model.Tags;
import com.deodio.elearning.persistence.model.customize.QuizBo;

public interface QuizCustomizeMapper {

	void insertQuizSubject(List<QuizSubject> quizSubjectList);

	void insertQuizItems(List<QuizItems> quizItemsList);

	void insertQuizSubjectRel(List<QuizSubjectRel> quizRelList);

	Integer getExaminationListCount(Map<String, Object> params);

	List<Map<String, Object>> getPreviewQuizExamination(Map<String, Object> params);

	Integer getQuizExaminationCountScore(String quizId);
	
	Integer getQuizBankCountScore(String quizId);
	
	List<Tags> getClassiFication(Map<String, Object> params);

	List<Map<String, Object>> getQuizAutoRuleBankList(String quizId);

	List<Map<String, Object>> findQuizList(Map<String, Object> params);

	List<Map<String, Object>> findQuizExaminationList(Map<String, Object> param);

	Map<String, Object> getQuizExaminationInfo(Map<String, Object> param);

	List<QuizBo> findQuizExamList(Map<String, Object> param);

	QuizBo getQuizInfoById(@Param("quizId") String quizId);

	List<Map<String, Object>> findQuoteQuizList(Map<String, Object> param);

	// 查询引用该试卷的课程是否存在发布状态
	Integer queryCourseIsPublishByQuizId(@Param("quizId") String quizId);

	// 批量添加出题范围信息
	Integer insertBatchQuizAutoRuleBank(List<QuizAutoRuleBank> list);

	// 批量添加题目设置信息
	Integer insertBatchQuizAutoRuleSubject(List<QuizAutoRuleSubject> list);

	// 课程内容获取测验列表
	List<QuizBo> findContentExamList(Map<String, Object> param);

	// 获取试卷试题（必考题/随机题）
	List<Map<String, Object>> queryQuizSubjectList(@Param("quizId") String quizId,
			@Param("isRequired") Integer isRequired);

}
