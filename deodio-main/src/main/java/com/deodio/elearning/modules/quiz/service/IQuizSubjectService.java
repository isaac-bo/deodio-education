package com.deodio.elearning.modules.quiz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.elearning.persistence.model.QuizItems;
import com.deodio.elearning.persistence.model.QuizSubject;
import com.deodio.elearning.persistence.model.customize.QuizBo;
import com.deodio.elearning.persistence.model.customize.QuizSubjectBo;

/**
 * 试卷题目
 * @author P0113869
 */
public interface IQuizSubjectService {
	/**
	 * 
	 */
	void insertSingleQuizSubject(String quizBankId, String quizData, String knowledge, Integer difficult, Integer score,
			String tagJson, String expireDate, Integer visible, String accountId, String userId);

	/**
	 * 
	 */
	QuizSubject createQuizSubject(String[] quizArray, String accountId, String userId, Integer subjectFrom,
			String quizBankId, Integer quizSubjectLevel, Integer quizSubjectScore, String quizSubjectExpireTime,
			Integer quizSubjectVisible);

	/**
	 * 
	 */
	void updateSingleQuizSubject(String quizBankId, String quizSubjectId, String quizData, String knowledge,
			Integer difficult, Integer score, String tags, String expireDate, Integer visible, String userId,
			String accountId);

	/**
	 * 
	 */
	void createQuizKnowledge(String quizSubjectId, String userId, String knowledgeId);

	/**
	 * 
	 */
	void createQuizItems(String userId, String[] quizArray, String quizSubjectId);

	/**
	 * 题库获取题目列表
	 */
	List<Map<String, Object>> getPerPageQuizsSubjectList(String quizBankId, String keywords, Integer type,
			Integer level, PageRequest pageRequest, HttpServletRequest request);

	/**
	 * 
	 */
	List<Map<String, Object>> findSubjectByBanksId(String quizId, String[] subjectArray, String subjectTile,
			PageRequest pageRequest, Integer subjectLevel, Integer subjectType, Integer limitCount);

	/**
	 * 
	 */
	List<Map<String, Object>> getSubjctCount(Map<String, Object> params);

	/**
	 * 
	 */
	List<Map<String, Object>> getSubjectDetail(String[] subjectIds);

	/**
	 * 
	 */
	QuizSubjectBo getQuizSubject(String quizSubjectId);

	/**
	 * 
	 */
	QuizSubject getQuizSubjectInfoByPkId(String quizSubjectId);

	/**
	 * 
	 */
	List<QuizItems> getQuizItemsBySubjectId(String quizSubjectId);

	/**
	 * 
	 */
	String getQuizKnowledgePointId(String quizSubjectId);

	/**
	 * 根据id删除试题和试题下选项和知识点
	 * @return 0：没有删除 
	 */
	int deleteQuizSubjectByPkId(String quizSubjectId);

	/**
	 * 
	 */
	List<List<Map<String, Object>>> insertRandomQuizSubject(String subjectIds, String quizId, String accountId,
			String userId, String answers, String quizSubjectOrder, String[] banksId);

	/**
	 * 生成随机试题
	 */
	List<List<Map<String, Object>>> createRandomQuizSubject(List<String> subjectIds, String quizId,
			String[] subjectCountArray);

	/**
	 * 保存试卷题目(手动试卷)
	 */
	void saveQuizSubject(String[] dataStr, String quizId, String quizBankId, String accountId, String userId);

	/**
	 * 保存发布之后试卷题目
	 */
	void saveIsPublishQuizSubject(String quizId, String userId);

	/**
	 * 添加试卷题目关系
	 */
	void insertQuizSubjectRel(List<String> subjectList, String quizId, String accountId, String userId, String order,
			Integer isRequired);

	/**
	 * 
	 */
	void insertAnswerForSubject(String[] answers, String accountId, String quizId, Integer subjectType);

	/**
	 * 
	 */
	void insertSubjectAutoPreivew(String[] subjectIds, String quizId, String accountId, String userId,
			String[] answers);

	/**
	 * 查询考试题目下选项
	 */
	List<QuizItems> queryQuizItemsList(String subjectId);

	/**
	 * 查询引用该试卷题目的试卷是否发布
	 */
	Integer queryQuizIsPublishBySubjectId(String subjectId);

	/**
	 * 复制试题
	 */
	void copyQuizSubject(String quizId, String newQuizId, String userId);

	/**
	 * 查询自动试卷试题
	 */
	List<Map<String, Object>> queryAutoSubject(String quizId, String[] bankScope, Integer isRequired);

	/**
	 * 保存试卷试题关系
	 */
	int saveQuizSubjectRel(QuizBo quiz, String accountId, String userId);

	/**
	 * 查询自动试卷随机试题
	 */
	List<List<Map<String, Object>>> queryAutoQuizRandomSubjectList(String quizId);

	/**
	 * 
	 */
	List<String> queryAutoQuizRequiredSubjectIdList(String quizId);
}
