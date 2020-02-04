package com.deodio.elearning.modules.quiz.service.impl;

//import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.group.service.GroupService;
import com.deodio.elearning.modules.quiz.service.IQuizService;
import com.deodio.elearning.modules.quiz.service.IQuizSubjectService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.QuizAutoRuleBankMapper;
import com.deodio.elearning.persistence.mapper.QuizAutoRuleSubjectMapper;
import com.deodio.elearning.persistence.mapper.QuizMapper;
import com.deodio.elearning.persistence.mapper.QuizSubjectRelMapper;
import com.deodio.elearning.persistence.mapper.customize.QuizCustomizeMapper;
import com.deodio.elearning.persistence.model.Quiz;
import com.deodio.elearning.persistence.model.QuizAutoRuleBank;
import com.deodio.elearning.persistence.model.QuizAutoRuleBankExample;
import com.deodio.elearning.persistence.model.QuizAutoRuleSubject;
import com.deodio.elearning.persistence.model.QuizAutoRuleSubjectExample;
import com.deodio.elearning.persistence.model.QuizExample;
import com.deodio.elearning.persistence.model.QuizSubjectRel;
import com.deodio.elearning.persistence.model.QuizSubjectRelExample;
import com.deodio.elearning.persistence.model.Tags;
import com.deodio.elearning.persistence.model.customize.QuizBo;

@Service
public class QuizServiceImpl implements IQuizService {

	@Autowired
	private QuizMapper quizMapper;

	@Autowired
	private QuizCustomizeMapper quizCustomizeMapper;

	@Autowired
	private QuizSubjectRelMapper quizSubjectRelMapper;

	@Autowired
	private QuizAutoRuleBankMapper quizAutoRuleBankMapper;

	@Autowired
	private QuizAutoRuleSubjectMapper quizAutoRuleSubjectMapper;

	@Autowired
	private IQuizSubjectService quizSubjectService;

	@Autowired
	private TagsService tagsService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private GroupService groupService;

	@Override
	public PageData<QuizBo> queryPage(String keywords, List<Integer> quizCategory, Integer isPublish, String accountId,
			String userId, PageRequest pageRequest) throws DeodioException {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		// param.put("quizCategory", quizCategory==0?null:quizCategory);
		param.put("quizCategory", quizCategory.get(0) == 0 ? null : quizCategory);
		param.put("isPublish", isPublish);
		param.put("accountId", accountId);
		param.put("userId", userId);
		param.put("pagination", pageRequest);
		PageData<QuizBo> page = new PageData<QuizBo>();
		page.setList(quizCustomizeMapper.findQuizExamList(param));
		page.setPageRequest((PageRequest) param.get("pagination"));

		return page;
	}

	@Override
	public QuizBo getQuizInfoById(String quizId) {

		return quizCustomizeMapper.getQuizInfoById(quizId);
	}

	@Override
	public int updateQuizByPKSelective(Quiz quiz) {

		return quizMapper.updateByPrimaryKeySelective(quiz);
	}

	@Override
	public int deleteQuizByPkId(String quizId) {

		// 删除试卷与试题的关联信息
		QuizSubjectRelExample record = new QuizSubjectRelExample();
		record.createCriteria().andQuizIdEqualTo(quizId);
		quizSubjectRelMapper.deleteByExample(record);
		// 删除试卷与小组的关联信息
		groupService.deleteGroupItemsByItem(DConstants.GROUP_ITEMS_TYPE_QUIZ, quizId);

		return quizMapper.deleteByPrimaryKey(quizId);
	}

	@Override
	public Boolean checkQuizName(String quizName, String accountId) {

		QuizExample example = new QuizExample();
		example.createCriteria().andQuizNameEqualTo(quizName).andAccountIdEqualTo(accountId);

		return quizMapper.selectByExample(example).isEmpty();
	}

	@Override
	public void updateQuizRule(Integer finishTime, Integer passScore, Integer maxTimes, Integer finalResut,
			Integer publisResult, String quizSafe, String quizContent, String quizId) {

		Quiz record = new Quiz();
		record.setId(quizId);
		record.setFinishTime(finishTime);
		record.setPassScore(passScore);
		record.setQuizMaxTimes(maxTimes);
		record.setFinallyQuizResultType(finalResut);
		record.setPublishResultType(publisResult);
		record.setQuizSafe(quizSafe);
		record.setQuizContent(quizContent);
		record.setIsEdit(DConstants.IS_EDIT);
		quizMapper.updateByPrimaryKeySelective(record);

	}

	@Override
	public String insertOrUpdateQuiz(String quizId, String quizName, String quizDesc, Integer quizType, String tagsJson,
			String attachId, String userId, String groupId, String accountId) {

		Quiz quiz = new Quiz();
		quiz.setQuizName(quizName);
		quiz.setQuizDesc(quizDesc);
		quiz.setQuizCategory(quizType);
		quiz.setUpdateId(userId);
		quiz.setUpdateTime(new Date());

		quiz.setIsEdit(DConstants.IS_EDIT);
		if (StringUtils.isNotBlank(quizId)) {
			quiz.setId(quizId);
			quiz.setIsPublish(DConstants.TYPE_NO_PUBLISH);
			quizMapper.updateByPrimaryKeySelective(quiz);
		} else {
			quizId = AppUtils.uuid();
			quiz.setId(quizId);
			quiz.setCreateId(userId);
			quiz.setCreateTime(new Date());
			quiz.setAccountId(accountId);
			quiz.setQuizOwner(userId);
			quizMapper.insert(quiz);
			groupService.insertGroupItems(DConstants.GROUP_ITEMS_TYPE_QUIZ, quizId, groupId, userId);
		}
		tagsService.saveTagsItemsRel(tagsJson, accountId, userId, quizId, DConstants.TAGS_ITEMS_TYPE_EXAMINATION);
		// 图片表更新
		if (StringUtils.isNotBlank(attachId)) {
			attachmentService.updateAttachement(attachId, quizId, userId);
		}
		return quizId;
	}

	@Override
	public void updateQuizCreateType(Integer createType, String quizId) {

		Quiz record = new Quiz();
		record.setCreateType(createType);
		record.setId(quizId);
		record.setIsEdit(DConstants.IS_EDIT);
		quizMapper.updateByPrimaryKeySelective(record);

	}

	@Override
	public List<Map<String, Object>> getPreviewQuizExamination(String quizId, Integer isRequired) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quizId", quizId);
		params.put("isRequired", isRequired);

		return quizCustomizeMapper.getPreviewQuizExamination(params);
	}
	@Override
	public Integer getQuizExaminationCountScore(String quizId){
		Quiz quiz=quizMapper.selectByPrimaryKey(quizId);
		Integer quizScore=0;
		if(quiz.getCreateType()==DConstants.QUIZ_CREATE_TYPE_MANUAL) {//手动创建试卷
			quizScore=quizCustomizeMapper.getQuizBankCountScore(quizId);	
		}else if (quiz.getCreateType()==DConstants.QUIZ_CREATE_TYPE_AUTO) {//自动创建试卷
			if (quiz.getScoreSet()==DConstants.QUIZ_EXAMINATION_SCORE) {//指定题型分数
				quizScore=quizCustomizeMapper.getQuizExaminationCountScore(quizId);	
			}else if (quiz.getScoreSet()==DConstants.QUIZ_BANK_SCORE) {//题库分数
				quizScore=quizCustomizeMapper.getQuizBankCountScore(quizId);	
			}
			
		}
		return quizScore;		
	}
	@Override
	public List<Map<String, Object>> getQuizAutoRuleBankList(String quizId) {

		return quizCustomizeMapper.getQuizAutoRuleBankList(quizId);
	}

	@Override
	public List<QuizAutoRuleSubject> getQuizAutoRuleSubjectList(String quizId) {

		QuizAutoRuleSubjectExample quizAutoRuleSubjectExample = new QuizAutoRuleSubjectExample();
		quizAutoRuleSubjectExample.createCriteria().andQuizIdEqualTo(quizId);

		return quizAutoRuleSubjectMapper.selectByExample(quizAutoRuleSubjectExample);
	}

	@Override
	public Map<String, Object> insertAutoRule(String[] bankScope, String[] subjectSets, String quizId, String userId,
			Integer scoreSet) {
		// 删除
		QuizAutoRuleBankExample example = new QuizAutoRuleBankExample();
		example.createCriteria().andQuizIdEqualTo(quizId);
		quizAutoRuleBankMapper.deleteByExample(example);
		QuizAutoRuleSubjectExample example1 = new QuizAutoRuleSubjectExample();
		example1.createCriteria().andQuizIdEqualTo(quizId);
		quizAutoRuleSubjectMapper.deleteByExample(example1);
		QuizAutoRuleBank quizAutoRuleBank = null;
		QuizAutoRuleSubject quizAutoRuleSubject = null;
		for (String bankId : bankScope) {
			quizAutoRuleBank = new QuizAutoRuleBank();
			quizAutoRuleBank.setId(AppUtils.uuid());
			quizAutoRuleBank.setQuizId(quizId);
			quizAutoRuleBank.setBankId(bankId);
			quizAutoRuleBank.setCreateId(userId);
			quizAutoRuleBank.setCreateTime(new Date());
			quizAutoRuleBankMapper.insertSelective(quizAutoRuleBank);
		}
		Map<String, Object> typeMap = new HashMap<String, Object>();
		for (String subjects : subjectSets) {
			String[] items = subjects.split(DConstants.DELIMITER_HR);
			typeMap.put(items[0], items[3]);
			quizAutoRuleSubject = new QuizAutoRuleSubject();
			quizAutoRuleSubject.setId(AppUtils.uuid());
			quizAutoRuleSubject.setSubjectType(Integer.parseInt(items[0]));
			quizAutoRuleSubject.setSubjectOrder(Integer.parseInt(items[1]));
			if (!items[2].equals("") && scoreSet == 0) {
				quizAutoRuleSubject.setSubjectScore(Integer.parseInt(items[2]));
			}
			quizAutoRuleSubject.setSubjectItemsCount(Integer.parseInt(items[3]));
			quizAutoRuleSubject.setQuizId(quizId);
			quizAutoRuleSubjectMapper.insertSelective(quizAutoRuleSubject);
		}
		Quiz quiz = new Quiz();
		quiz.setId(quizId);
		quiz.setScoreSet(scoreSet);
		quizMapper.updateByPrimaryKeySelective(quiz);

		return typeMap;
	}

	@Override
	public List<QuizBo> getQuizExamList(String accountId) {

		QuizExample example = new QuizExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<Quiz> quizs = quizMapper.selectByExample(example);
		List<QuizBo> quizExaminations = new ArrayList<QuizBo>();
		for (Quiz quiz : quizs) {
			QuizBo quizExaminationBo = new QuizBo();
			BeanUtils.copyProperties(quiz, quizExaminationBo);
			quizExaminations.add(quizExaminationBo);
		}

		return quizExaminations;
	}

	@Override
	public List<Tags> getClassification(String account, String userId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", account);
		params.put("userId", userId);

		return quizCustomizeMapper.getClassiFication(params);
	}

	@Override
	public List<QuizSubjectRel> queryQuizSubjectRelList(String quizId, Integer isRequired) {

		QuizSubjectRelExample quizSubjectRelExample = new QuizSubjectRelExample();
		quizSubjectRelExample.createCriteria().andQuizIdEqualTo(quizId).andIsRequiredEqualTo(isRequired);
		quizSubjectRelExample.setOrderByClause("quiz_subject_order ASC");

		return quizSubjectRelMapper.selectByExample(quizSubjectRelExample);
	}

	@Override
	public List<Map<String, Object>> queryQuizSubjectList(String quizId, Integer isRequired) {

		return quizCustomizeMapper.queryQuizSubjectList(quizId, isRequired);
	}

	@Override
	public List<Map<String, Object>> findQuizList(Map<String, Object> params) {

		return quizCustomizeMapper.findQuizList(params);
	}

	@Override
	public PageData<Map<String, Object>> findQuoteQuizList(String quizId, PageRequest pageRequest) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("quizId", quizId);
		param.put("pagination", pageRequest);
		PageData<Map<String, Object>> page = new PageData<Map<String, Object>>();
		page.setList(quizCustomizeMapper.findQuoteQuizList(param));
		page.setPageRequest((PageRequest) param.get("pagination"));

		return page;
	}

	@Override
	public void copyQuiz(String quizId, String quizName, String userId) {

		Quiz quizInfo = quizMapper.selectByPrimaryKey(quizId);
		String newQuizId = AppUtils.uuid();
		// 复制试卷题目
		quizSubjectService.copyQuizSubject(quizId, newQuizId, userId);
		// 复制出题范围
		copyQuizAutoRuleBank(quizId, newQuizId, userId);
		// 复制题目设置
		copyQuizAutoRuleSubject(quizId, newQuizId, userId);
		// 复制标签
		tagsService.copyTag(quizId, newQuizId, userId);
		// 复制图片
		// attachmentService.copyAttachement(quizId, newQuizId, userId);
		try {
			attachmentService.copyNewAttachement(quizId, newQuizId, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		quizInfo.setId(newQuizId);
		quizInfo.setQuizName(quizName);
		quizInfo.setIsPublish(DConstants.TYPE_NO_PUBLISH);
		quizInfo.setCreateId(userId);
//		quizInfo.setCreateTime(Date.from(Instant.now()));
		quizInfo.setUpdateId(userId);
//		quizInfo.setUpdateTime(Date.from(Instant.now()));
		quizInfo.setQuizOwner(userId);
		quizInfo.setIsEdit(DConstants.ISNOT_EDIT);
		quizMapper.insert(quizInfo);
	}

	@Override
	public int queryCourseIsPublishByQuizId(String quizId) {

		return quizCustomizeMapper.queryCourseIsPublishByQuizId(quizId);
	}

	@Override
	public void copyQuizAutoRuleBank(String quizId, String newQuizId, String userId) {

		QuizAutoRuleBankExample quizAutoRuleBankExample = new QuizAutoRuleBankExample();
		quizAutoRuleBankExample.createCriteria().andQuizIdEqualTo(quizId);
		List<QuizAutoRuleBank> autoRuleBanks = quizAutoRuleBankMapper.selectByExample(quizAutoRuleBankExample);
		if (!autoRuleBanks.isEmpty()) {
			List<QuizAutoRuleBank> list = new LinkedList<QuizAutoRuleBank>();
			for (QuizAutoRuleBank item : autoRuleBanks) {
				item.setId(AppUtils.uuid());
				item.setQuizId(newQuizId);
				item.setCreateId(userId);
//				item.setCreateTime(Date.from(Instant.now()));
				item.setUpdateId(userId);
//				item.setUpdateTime(Date.from(Instant.now()));
				list.add(item);
			}
			quizCustomizeMapper.insertBatchQuizAutoRuleBank(list);
		}
	}

	@Override
	public void copyQuizAutoRuleSubject(String quizId, String newQuizId, String userId) {

		QuizAutoRuleSubjectExample quizAutoRuleSubjectExample = new QuizAutoRuleSubjectExample();
		quizAutoRuleSubjectExample.createCriteria().andQuizIdEqualTo(quizId);
		List<QuizAutoRuleSubject> autoRuleSubjects = quizAutoRuleSubjectMapper
				.selectByExample(quizAutoRuleSubjectExample);
		if (!autoRuleSubjects.isEmpty()) {
			List<QuizAutoRuleSubject> list = new LinkedList<QuizAutoRuleSubject>();
			for (QuizAutoRuleSubject item : autoRuleSubjects) {
				item.setId(AppUtils.uuid());
				item.setQuizId(newQuizId);
				list.add(item);
			}
			quizCustomizeMapper.insertBatchQuizAutoRuleSubject(list);
		}
	}

	@Override
	public PageData<QuizBo> queryTestPage(List<String> contentList,String keywords, String accountId, String userId, PageRequest pageRequest)
			throws DeodioException {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("accountId", accountId);
		param.put("userId", userId);
		param.put("pagination", pageRequest);
		param.put("list", contentList!=null&&!contentList.isEmpty()?contentList:null);
		PageData<QuizBo> page = new PageData<QuizBo>();
		page.setList(quizCustomizeMapper.findContentExamList(param));
		page.setPageRequest((PageRequest) param.get("pagination"));

		return page;
	}

	@Override
	public List<Quiz> queryQuizByQuizName(String quizName, String accountId) {

		QuizExample quizExample = new QuizExample();
		if (StringUtils.isNotBlank(quizName)) {
			quizName = "%" + quizName + "%";
		}
		quizExample.createCriteria().andQuizNameLike(quizName).andAccountIdEqualTo(accountId);

		return quizMapper.selectByExample(quizExample);
	}

}
