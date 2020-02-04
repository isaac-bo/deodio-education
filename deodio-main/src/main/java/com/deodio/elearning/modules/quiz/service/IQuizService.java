package com.deodio.elearning.modules.quiz.service;

import java.util.List;
import java.util.Map;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.elearning.persistence.model.Quiz;
import com.deodio.elearning.persistence.model.QuizAutoRuleSubject;
import com.deodio.elearning.persistence.model.QuizSubjectRel;
import com.deodio.elearning.persistence.model.Tags;
import com.deodio.elearning.persistence.model.customize.QuizBo;

/**
 * 试卷管理
 * @author P0113869
 */
public interface IQuizService {
	/**
	 * 分页获取试卷列表
	 */
	PageData<QuizBo> queryPage(String keywords, List<Integer> quizCategory, Integer isPublish, String accountId,
			String userId, PageRequest pageRequest);

	/**
	 * 根据id获取试题
	 */
	QuizBo getQuizInfoById(String quizId);

	/**
	 * 根据id进行可选性更新试卷
	 */
	int updateQuizByPKSelective(Quiz quiz);

	/**
	 * 根据id进行删除
	 */
	int deleteQuizByPkId(String quizId);

	/**
	 * 校验试卷名称是否重复
	 * @return true:不重复 false:重复
	 */
	Boolean checkQuizName(String quizName, String accountId);

	/**
	 * 通过试卷名称查询试卷
	 */
	List<Quiz> queryQuizByQuizName(String quizName, String accountId);
	
	/**
	 * 更新考试规则
	 */
	void updateQuizRule(Integer finishTime, Integer passScore, Integer maxTimes, Integer finalResut,
			Integer publishResult, String quizSafes, String quizContent, String quizId);

	/**
	 * 保存试卷基本信息
	 */
	String insertOrUpdateQuiz(String quizId, String quizName, String quizDesc, Integer quizType, String tagsJson,
			String attachId, String userId, String groupId, String accountId);

	/**
	 * 更新试卷类型
	 */
	void updateQuizCreateType(Integer createType, String quizId);

	/**
	 * 
	 */
	List<Map<String, Object>> getPreviewQuizExamination(String quizId, Integer isRequired);
	
	/**
	 * 获取试卷题库规则
	 */
	List<Map<String, Object>> getQuizAutoRuleBankList(String quizId);

	/**
	 * 获取试卷题目规则
	 */
	List<QuizAutoRuleSubject> getQuizAutoRuleSubjectList(String quizId);

	/**
	 * 
	 */
	Map<String, Object> insertAutoRule(String[] bankScope, String[] subjectSets, String quizId, String userId,
			Integer scoreSet);

	/**
	 * 
	 */
	List<QuizBo> getQuizExamList(String accountId);

	/**
	 * 
	 */
	List<Tags> getClassification(String account, String userId);

	/**
	 * 
	 */
	List<QuizSubjectRel> queryQuizSubjectRelList(String quizId, Integer isRequired);

	/**
	 * 获取试卷试题（必考题/随机题）
	 */
	List<Map<String, Object>> queryQuizSubjectList(String quizId, Integer isRequired);
	
	/**
	 * 获取在线考试列表
	 */
	List<Map<String, Object>> findQuizList(Map<String, Object> params);

	/**
	 * 获取被引用综合试卷
	 */
	PageData<Map<String, Object>> findQuoteQuizList(String quizId, PageRequest pageRequest);

	/**
	 * 复制综合试卷
	 */
	void copyQuiz(String quizId, String quizName, String userId);

	/**
	 * 复制出题范围
	 */
	void copyQuizAutoRuleBank(String quizId, String newQuizId, String userId);

	/**
	 * 复制题目设置
	 */
	void copyQuizAutoRuleSubject(String quizId, String newQuizId, String userId);

	/**
	 * 查询引用该试卷的课程是否存中发布的
	 * @return 0 均未发布
	 */
	int queryCourseIsPublishByQuizId(String quizId);

	/**
	 * 课程内容分页获取测验列表
	 * 测验+测验和考试
	 * @param contentList 
	 */
	PageData<QuizBo> queryTestPage(List<String> contentList, String keywords, String accountId, String userId, PageRequest pageRequest);

	Integer getQuizExaminationCountScore(String quizId);
}

