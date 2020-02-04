package com.deodio.elearning.modules.quiz.service;

import java.util.List;
import java.util.Map;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.elearning.persistence.model.QuizBanks;
import com.deodio.elearning.persistence.model.customize.QuizBankBo;

/**
 * 试卷题库
 * @author P0113869
 */
public interface IQuizBankService {
	/**
	 * 获取题库等级列表
	 */
	public List<Map<String, Object>> getCTree(String userId, String accountId, String classificationId);

	/**
	 * 校验题库名称是否重复
	 * @return true:不重复 false:重复
	 */
	public boolean checkBankName(String bankName, String accountId);

	/**
	 * 创建题库
	 */
	public void create(String accountId, String createId, String quizBankName, String quizBankDesc,
			String classificationId, Short isPrivate);

	/**
	 * 根据id进行可选性更新题库
	 */
	public void updateSelectivityById(String quizBankId, String quizBankName, String quizBankDesc,
			String classificationId, Short isPrivate, String updateId);

	/**
	 * 根据id物理删除题库 
	 */
	public int deleteById(String quizBankId);

	/**
	 * 批量删除题库
	 */
	public int batchDelete(String quizBankIds, String updateId);

	/**
	 * 删除题库内试题
	 */
	public int deleteSubject(String quizSubjectId, String updateId);

	/**
	 * 根据id获取题库
	 */
	public QuizBanks getById(String quizBankId);

	/**
	 * 分页获取题库列表
	 */
	public PageData<QuizBankBo> queryPage(String classificationId, String keywords, String[] bankIds, String userId,
			String accountId, PageRequest pageRequest);

	/**
	 * 
	 */
	public List<QuizBanks> getBanksByTags(String classificationId);
}
