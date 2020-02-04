package com.deodio.elearning.modules.quiz.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.modules.quiz.service.IQuizBankService;
import com.deodio.elearning.modules.quiz.service.IQuizSubjectService;
import com.deodio.elearning.persistence.mapper.QuizBanksMapper;
import com.deodio.elearning.persistence.mapper.QuizMapper;
import com.deodio.elearning.persistence.mapper.QuizSubjectMapper;
import com.deodio.elearning.persistence.mapper.QuizSubjectRelMapper;
import com.deodio.elearning.persistence.mapper.customize.ClassificationCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.QuizBanksCustomizeMapper;
import com.deodio.elearning.persistence.model.QuizBanks;
import com.deodio.elearning.persistence.model.QuizBanksExample;
import com.deodio.elearning.persistence.model.QuizExample;
import com.deodio.elearning.persistence.model.QuizSubject;
import com.deodio.elearning.persistence.model.QuizSubjectExample;
import com.deodio.elearning.persistence.model.QuizSubjectRel;
import com.deodio.elearning.persistence.model.QuizSubjectRelExample;
import com.deodio.elearning.persistence.model.customize.QuizBankBo;

@Service
public class QuizBankServiceImpl implements IQuizBankService {

	@Autowired
	private QuizBanksMapper quizBanksMapper;

	@Autowired
	private QuizBanksCustomizeMapper quizBanksCustomizeMapper;

	@Autowired
	private QuizMapper quizMapper;

	@Autowired
	private QuizSubjectMapper quizSubjectMapper;

	@Autowired
	private QuizSubjectRelMapper quizSubjectRelMapper;

	@Autowired
	private ClassificationCustomizeMapper classificationCustomizeMapper;

	@Autowired
	private IQuizSubjectService quizSubjectService;

	@Override
	public List<Map<String, Object>> getCTree(String userId, String accountId, String classificationId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("classificationId", classificationId);
		List<Map<String, Object>> resultList = classificationCustomizeMapper.getCTree(params);
		for (Map<String, Object> result : resultList) {
			result.put("isParent", (String) result.get("isparent"));
		}

		return resultList;
	}

	@Override
	public void create(String accountId, String createId, String quizBankName, String quizBankDesc,
			String classificationId, Short isPrivate) {

		QuizBanks entity = new QuizBanks();
		entity.setId(AppUtils.uuid());
		entity.setAccountId(accountId);
		entity.setCreateId(createId);
		entity.setCreateTime(Date.from(Instant.now()));
		entity.setUpdateId(createId);
		entity.setUpdateTime(entity.getCreateTime());
		entity.setQuizBankName(quizBankName);
		entity.setQuizBankDesc(quizBankDesc);
		entity.setClassificationId(classificationId);
		entity.setIsPrivate(isPrivate);
		quizBanksMapper.insertSelective(entity);
	}

	@Override
	public void updateSelectivityById(String quizBankId, String quizBankName, String quizBankDesc,
			String classificationId, Short isPrivate, String updateId) {

		QuizBanks entity = new QuizBanks();
		entity.setId(quizBankId);
		entity.setQuizBankName(quizBankName);
		entity.setQuizBankDesc(quizBankDesc);
		entity.setClassificationId(classificationId);
		entity.setIsPrivate(isPrivate);
		entity.setUpdateId(updateId);
		entity.setUpdateTime(Date.from(Instant.now()));
		quizBanksMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int deleteById(String quizBankId) {
		
		int i = 0;
		i = quizBanksMapper.deleteByPrimaryKey(quizBankId);
		QuizSubjectExample quizSubjectExample = new QuizSubjectExample();
		quizSubjectExample.createCriteria().andQuizBankIdEqualTo(quizBankId);
		List<QuizSubject> list = quizSubjectMapper.selectByExample(quizSubjectExample);
		for (QuizSubject item : list) {
			quizSubjectService.deleteQuizSubjectByPkId(item.getId());
		}

		return i;
	}

	@Override
	public int batchDelete(String quizBankIds, String updateId) {

		int i = 0;
		String[] arr = quizBankIds.split(",");
		for (String id : arr) {
			i = i + deleteById(id);
		}
		return i;
	}

	@Override
	public int deleteSubject(String quizSubjectId, String updateId) {

		QuizSubjectRelExample quizSubjectRelExample = new QuizSubjectRelExample();
		quizSubjectRelExample.createCriteria().andQuizSubjectIdEqualTo(quizSubjectId);
		List<QuizSubjectRel> quizSubjectRels = quizSubjectRelMapper.selectByExample(quizSubjectRelExample);
		// 试题未被引用，直接删除
		if (quizSubjectRels.isEmpty()) {
			return quizSubjectService.deleteQuizSubjectByPkId(quizSubjectId);
		} else {
			// 查询引用该试题的试卷是否存在已发布的
			int i = 0;
			for (QuizSubjectRel item : quizSubjectRels) {
				QuizExample quizExample = new QuizExample();
				quizExample.createCriteria().andIdEqualTo(item.getQuizId()).andIsPublishEqualTo(1);
				i = i + quizMapper.selectByExample(quizExample).size();
			}
			// 引用的试卷存在已发布的
			if (i > 0) {
				QuizSubject quizSubject = new QuizSubject();
				// 已发布的将题目内题库id置为“0”，不删除该题目
				quizSubject.setQuizBankId("0");
				quizSubject.setUpdateId(updateId);
				quizSubject.setUpdateTime(Date.from(Instant.now()));

				return quizSubjectMapper.updateByPrimaryKeySelective(quizSubject);
			}
			// 引用的试卷未发布
			return quizSubjectService.deleteQuizSubjectByPkId(quizSubjectId);
		}
	}

	@Override
	public QuizBanks getById(String quizBankId) {

		return quizBanksMapper.selectByPrimaryKey(quizBankId);
	}

	@Override
	public PageData<QuizBankBo> queryPage(String classificationId, String keywords, String[] bankIds, String userId,
			String accountId, PageRequest pageRequest) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("classificationId", classificationId);
		param.put("quizBankName", keywords);
		param.put("bankIds", bankIds);
		param.put("userId", userId);
		param.put("accountId", accountId);
		param.put("pagination", pageRequest);
		PageData<QuizBankBo> page = new PageData<QuizBankBo>();
		page.setList(quizBanksCustomizeMapper.findQuizBankList(param));
		page.setPageRequest((PageRequest) param.get("pagination"));

		return page;
	}

	@Override
	public List<QuizBanks> getBanksByTags(String classificationId) {

		QuizBanksExample example = new QuizBanksExample();
		example.createCriteria().andClassificationIdEqualTo(classificationId);

		return quizBanksMapper.selectByExample(example);
	}

	@Override
	public boolean checkBankName(String bankName, String accountId) {

		QuizBanksExample example = new QuizBanksExample();
		example.createCriteria().andQuizBankNameEqualTo(bankName).andAccountIdEqualTo(accountId);

		return quizBanksMapper.selectByExample(example).isEmpty();
	}

}
