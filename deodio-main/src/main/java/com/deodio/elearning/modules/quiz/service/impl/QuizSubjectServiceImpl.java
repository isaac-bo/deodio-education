package com.deodio.elearning.modules.quiz.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.quiz.service.IQuizService;
import com.deodio.elearning.modules.quiz.service.IQuizSubjectService;
import com.deodio.elearning.persistence.mapper.QuizExminationAnswerMapper;
import com.deodio.elearning.persistence.mapper.QuizItemsMapper;
import com.deodio.elearning.persistence.mapper.QuizSubjectKnowledgeRelMapper;
import com.deodio.elearning.persistence.mapper.QuizSubjectMapper;
import com.deodio.elearning.persistence.mapper.QuizSubjectRelMapper;
import com.deodio.elearning.persistence.mapper.customize.QuizCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.QuizSubjectCustomizeMapper;
import com.deodio.elearning.persistence.model.QuizExminationAnswer;
import com.deodio.elearning.persistence.model.QuizExminationAnswerExample;
import com.deodio.elearning.persistence.model.QuizItems;
import com.deodio.elearning.persistence.model.QuizItemsExample;
import com.deodio.elearning.persistence.model.QuizSubject;
import com.deodio.elearning.persistence.model.QuizSubjectExample;
import com.deodio.elearning.persistence.model.QuizSubjectKnowledgeRel;
import com.deodio.elearning.persistence.model.QuizSubjectKnowledgeRelExample;
import com.deodio.elearning.persistence.model.QuizSubjectRel;
import com.deodio.elearning.persistence.model.QuizSubjectRelExample;
import com.deodio.elearning.persistence.model.customize.QuizBo;
import com.deodio.elearning.persistence.model.customize.QuizSubjectBo;
import com.deodio.elearning.utils.CommonUtils;

@Service
public class QuizSubjectServiceImpl implements IQuizSubjectService {

	@Autowired
	private QuizSubjectMapper quizSubjectMapper;

	@Autowired
	private QuizSubjectCustomizeMapper quizSubjectCustomizeMapper;

	@Autowired
	private QuizSubjectRelMapper quizSubjectRelMapper;

	@Autowired
	private QuizItemsMapper quizItemsMapper;

	@Autowired
	private QuizCustomizeMapper quizCustomizeMapper;

	@Autowired
	private QuizExminationAnswerMapper quizExminationAnswerMapper;

	@Autowired
	private QuizSubjectKnowledgeRelMapper quizSubjectKnowledgeRelMapper;

	@Override
	public void insertSingleQuizSubject(String quizBankId, String quizData, String knowledge, Integer difficult,
			Integer score, String tagJson, String expireDate, Integer visible, String accountId, String userId) {
		// tagsService.saveTagsItemsRel(tagJson,accountId, userId, quizBankId, DConstants.TAGS_ITEMS_TYPE_EXAMINATION);

		QuizSubject quizSubject = createQuizSubject(quizData.split(DConstants.DELIMITER_DATA), accountId, userId,
				DConstants.STATUS_ZERO, quizBankId, difficult, score, expireDate, visible);
		createQuizKnowledge(quizSubject.getId(), userId, knowledge);
		createQuizItems(userId, quizData.split(DConstants.DELIMITER_DATA), quizSubject.getId());
	}

	@Override
	public QuizSubject createQuizSubject(String[] quizArray, String accountId, String userId, Integer subjectFrom,
			String quizBankId, Integer quizSubjectLevel, Integer quizSubjectScore, String quizSubjectExpireTime,
			Integer quizSubjectVisible) {

		QuizSubject quizSubject = new QuizSubject();
		String quizSubjectId = AppUtils.uuid();
		quizSubject.setId(quizSubjectId);
		quizSubject.setQuizSubjectName(quizArray[1]);
		quizSubject.setQuizSubjectType(Integer.parseInt(quizArray[0]));
		quizSubject.setAccountId(accountId);
		quizSubject.setCreateId(userId);
		quizSubject.setQuizBankId(quizBankId);
		quizSubject.setQuizSubjectExpireTime(DateTimeUtils.parse(quizSubjectExpireTime));
		quizSubject.setQuizSubjectLevel(quizSubjectLevel);
		quizSubject.setQuizSubjectScore(quizSubjectScore);
		quizSubject.setQuizSubjectVisible(quizSubjectVisible);
		quizSubject.setQuizSubjectFrom(subjectFrom);
		quizSubject.setCreateTime(new Date());
		quizSubject.setUpdateId(userId);
		quizSubject.setUpdateTime(new Date());
		quizSubjectMapper.insertSelective(quizSubject);

		return quizSubject;
	}

	@Override
	public void createQuizKnowledge(String quizSubjectId, String userId, String knowledgeId) {

		QuizSubjectKnowledgeRel quizSubjectKnowledgeRel = new QuizSubjectKnowledgeRel();
		quizSubjectKnowledgeRel.setId(AppUtils.uuid());
		quizSubjectKnowledgeRel.setKnowledgePointId(knowledgeId);
		quizSubjectKnowledgeRel.setQuizSubjectId(quizSubjectId);
		quizSubjectKnowledgeRel.setCreateId(userId);
		quizSubjectKnowledgeRel.setCreateTime(new Date());
		quizSubjectKnowledgeRel.setUpdateId(userId);
		quizSubjectKnowledgeRel.setUpdateTime(new Date());
		quizSubjectKnowledgeRelMapper.insert(quizSubjectKnowledgeRel);
	}

	@Override
	public void createQuizItems(String userId, String[] quizArray, String quizSubjectId) {

		List<QuizItems> quizItemsList = new ArrayList<QuizItems>();
		QuizItems quizItems;
		String qtype = quizArray[0];
		if ("1".equals(qtype) || "2".equals(qtype) || "3".equals(qtype) || "4".equals(qtype)) {
			String[] options = StringUtils.split(quizArray[3], DConstants.DELIMITER_ATTR);
			String[] answers = StringUtils.split(quizArray[2], DConstants.DELIMITER_DOLLER);
			for (int z = 0; z < options.length; z++) {
				String[] optionsItems = StringUtils.split(options[z], DConstants.DELIMITER_CELL_DATA);
				quizItems = new QuizItems();
				quizItems.setId(AppUtils.uuid());
				quizItems.setCreateId(userId);
				quizItems.setCreateTime(new Date());
				quizItems.setQuizItemName(optionsItems[0]);
				quizItems.setQuizItemOrder(z);
				quizItems.setUpdateId(userId);
				quizItems.setUpdateTime(new Date());
				quizItems.setQuizSubjectId(quizSubjectId);
				quizItems.setIsCorrect(optionsItems == null || optionsItems[1] == null || optionsItems[1].equals("") ? 0
						: Integer.parseInt(optionsItems[1]));
				quizItemsList.add(quizItems);
			}
		} else if ("6".equals(qtype)) {
			String[] options = StringUtils.split(quizArray[3], DConstants.DELIMITER_ATTR);
			for (int z = 0; z < options.length; z++) {
				quizItems = new QuizItems();
				quizItems.setId(AppUtils.uuid());
				quizItems.setCreateId(userId);
				quizItems.setCreateTime(new Date());
				quizItems.setQuizItemName(options[z]);
				quizItems.setQuizItemOrder(z);
				quizItems.setUpdateId(userId);
				quizItems.setUpdateTime(new Date());
				quizItems.setQuizSubjectId(quizSubjectId);
				quizItemsList.add(quizItems);
			}
		} else if ("5".equals(qtype)) {
			String[] options = StringUtils.split(quizArray[3], DConstants.DELIMITER_ATTR);
			for (int z = 0; z < options.length; z++) {
				String[] optionsItems = StringUtils.split(options[z], DConstants.DELIMITER_CELL_DATA);
				quizItems = new QuizItems();
				quizItems.setId(AppUtils.uuid());
				quizItems.setCreateId(userId);
				quizItems.setCreateTime(new Date());
				quizItems.setQuizItemName(optionsItems[0]);
				quizItems.setQuizItemOrder(z);
				quizItems.setUpdateId(userId);
				quizItems.setUpdateTime(new Date());
				quizItems.setQuizSubjectId(quizSubjectId);
				quizItems.setAttachUrl(optionsItems[1]);
				quizItemsList.add(quizItems);
			}
		}else if("7".equals(qtype)){
			quizItems = new QuizItems();
			quizItems.setId(AppUtils.uuid());
			quizItems.setCreateId(userId);
			quizItems.setCreateTime(new Date());
			quizItems.setQuizItemName(quizArray[3]);
			quizItems.setQuizItemOrder(0);
			quizItems.setUpdateId(userId);
			quizItems.setUpdateTime(new Date());
			quizItems.setQuizSubjectId(quizSubjectId);
			quizItems.setAttachUrl(quizArray[2]);
			quizItemsList.add(quizItems);
		}
		quizCustomizeMapper.insertQuizItems(quizItemsList);
	}

	@Override
	public void updateSingleQuizSubject(String quizBankId, String quizSubjectId, String quizData, String knowledge,
			Integer difficult, Integer score, String tags, String expireDate, Integer visible, String userId,
			String accountId) {

		// tagsService.saveTagsItemsRel(tagJson,accountId, userId, quizBankId, DConstants.TAGS_ITEMS_TYPE_EXAMINATION);
		QuizSubject quizSubject = quizSubjectMapper.selectByPrimaryKey(quizSubjectId);
		quizSubject.setQuizSubjectName(quizData.split(DConstants.DELIMITER_DATA)[1]);
		quizSubject.setQuizSubjectExpireTime(DateTimeUtils.parse(expireDate));
		quizSubject.setQuizSubjectLevel(difficult);
		quizSubject.setQuizSubjectScore(score);
		quizSubject.setQuizSubjectVisible(visible);
		quizSubject.setUpdateId(userId);
		quizSubject.setUpdateTime(new Date());
		quizSubjectMapper.updateByPrimaryKeySelective(quizSubject);
		QuizItemsExample example = new QuizItemsExample();
		example.createCriteria().andQuizSubjectIdEqualTo(quizSubjectId);
		quizItemsMapper.deleteByExample(example);
		createQuizItems(userId, quizData.split(DConstants.DELIMITER_DATA), quizSubjectId);
		QuizSubjectKnowledgeRelExample quizSubjectKnowledgeRelExample = new QuizSubjectKnowledgeRelExample();
		quizSubjectKnowledgeRelExample.createCriteria().andQuizSubjectIdEqualTo(quizSubjectId);
		quizSubjectKnowledgeRelMapper.deleteByExample(quizSubjectKnowledgeRelExample);
		createQuizKnowledge(quizSubject.getId(), userId, knowledge);
	}

	@Override
	public List<Map<String, Object>> getPerPageQuizsSubjectList(String quizBankId, String keywords, Integer type,
			Integer level, PageRequest pageRequest, HttpServletRequest request) {

		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("quizBankId", quizBankId);
		params.put("keywords", StringUtils.isBlank(keywords) ? null : keywords);
		params.put("type", type == 0 ? null : type);
		params.put("level", level == 0 ? null : level);
		params.put("pagination", pageRequest);

		return quizSubjectCustomizeMapper.findSubjectList(params);
	}

	@Override
	public List<Map<String, Object>> findSubjectByBanksId(String quizId, String[] subjectArray, String subjectTile,
			PageRequest pageRequest, Integer subjectLevel, Integer subjectType, Integer limitCount) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quizId", quizId);
		params.put("subjectArray", subjectArray != null ? Arrays.asList(subjectArray) : null);
		params.put("subjectTile", StringUtils.isBlank(subjectTile) ? null : subjectTile);
		params.put("pagination", pageRequest);
		params.put("subjectLevel", subjectLevel == 0 ? null : subjectLevel);
		params.put("subjectType", subjectType);
		params.put("limitCount", limitCount);

		return quizSubjectCustomizeMapper.findSubjectByBanksId(params);
	}

	@Override
	public List<Map<String, Object>> getSubjctCount(Map<String, Object> params) {

		return quizSubjectCustomizeMapper.getSubjctCount(params);
	}

	@Override
	public List<Map<String, Object>> getSubjectDetail(String[] subjectIds) {

		return quizSubjectCustomizeMapper.getSubjectDetail(Arrays.asList(subjectIds));
	}

	@Override
	public QuizSubjectBo getQuizSubject(String quizSubjectId) {

		QuizSubject quizSubject = getQuizSubjectInfoByPkId(quizSubjectId);
		QuizSubjectBo quizSubjectBo = new QuizSubjectBo();
		BeanUtils.copyProperties(quizSubject, quizSubjectBo);
		quizSubjectBo.setQuizItems(getQuizItemsBySubjectId(quizSubjectId));
		quizSubjectBo.setQuizKnowledgePointId(getQuizKnowledgePointId(quizSubjectId));

		return quizSubjectBo;
	}

	@Override
	public QuizSubject getQuizSubjectInfoByPkId(String quizSubjectId) {

		return quizSubjectMapper.selectByPrimaryKey(quizSubjectId);
	}

	@Override
	public List<QuizItems> getQuizItemsBySubjectId(String quizSubjectId) {

		QuizItemsExample example = new QuizItemsExample();
		example.createCriteria().andQuizSubjectIdEqualTo(quizSubjectId);

		return quizItemsMapper.selectByExample(example);
	}

	@Override
	public String getQuizKnowledgePointId(String quizSubjectId) {

		QuizSubjectKnowledgeRelExample quizSubjectKnowledgeRelExample = new QuizSubjectKnowledgeRelExample();
		quizSubjectKnowledgeRelExample.createCriteria().andQuizSubjectIdEqualTo(quizSubjectId);
		List<QuizSubjectKnowledgeRel> knowledgeRels = quizSubjectKnowledgeRelMapper
				.selectByExample(quizSubjectKnowledgeRelExample);
		if (knowledgeRels.isEmpty() || knowledgeRels.size() <= 0) {
			return "";
		} else {
			return (knowledgeRels.get(0)).getKnowledgePointId();
		}
	}

	@Override
	public int deleteQuizSubjectByPkId(String quizSubjectId) {
		// 删除试题选项
		QuizItemsExample quizItemsExample = new QuizItemsExample();
		quizItemsExample.createCriteria().andQuizSubjectIdEqualTo(quizSubjectId);
		quizItemsMapper.deleteByExample(quizItemsExample);
		// 删除试题知识点
		QuizSubjectKnowledgeRelExample quizSubjectKnowledgeRelExample = new QuizSubjectKnowledgeRelExample();
		quizSubjectKnowledgeRelExample.createCriteria().andQuizSubjectIdEqualTo(quizSubjectId);
		quizSubjectKnowledgeRelMapper.deleteByExample(quizSubjectKnowledgeRelExample);
		// 删除试题
		return quizSubjectMapper.deleteByPrimaryKey(quizSubjectId);
	}

	@Override
	public List<List<Map<String, Object>>> insertRandomQuizSubject(String subjectIds, String quizId, String accountId,
			String userId, String answers, String quizSubjectOrder, String[] banksId) {
		// 13,sub_type1_#_1002,1003
		// 2,sub_type2_#_
		// 0,sub_type3_#_
		List<List<Map<String, Object>>> listMap = new ArrayList<List<Map<String, Object>>>();
		String[] subjectRow = subjectIds.split(DConstants.DELIMITER_ROW);
		String[] anSwers = answers.split(DConstants.DELIMITER_COMMA);
		String[] orders = quizSubjectOrder.split(DConstants.DELIMITER_COMMA);
		// 删除必考题
		QuizSubjectRelExample example = new QuizSubjectRelExample();
		example.createCriteria().andQuizIdEqualTo(quizId).andIsRequiredEqualTo(DConstants.STATUS_ONE);
		quizSubjectRelMapper.deleteByExample(example);
		int i = 0;
		for (String str : subjectRow) {
			String[] dataStr = str.split(DConstants.DELIMITER_DATA);
			String[] subType = dataStr[0].split(DConstants.DELIMITER_COMMA);
			if (!DConstants.STRING_ZERO.equals(subType[0])) {
				Map<String, Object> params = new HashMap<String, Object>();
				List<String> subjectList = new ArrayList<String>();
				String sbStr = dataStr[1].trim();
				if (StringUtils.isNotBlank(sbStr)) {
					subjectList = Arrays.asList(sbStr.split(DConstants.DELIMITER_COMMA));
				}
				params.put("subjectCount", Integer.parseInt(subType[0]));
				params.put("subjectType", Integer.parseInt(subType[1]));
				params.put("list", subjectList.isEmpty() ? null : subjectList);
				params.put("bankIds", Arrays.asList(banksId));
				List<Map<String, Object>> list = quizSubjectCustomizeMapper.getSubjectRandoms(params);
				listMap.add(list);
				if (!subjectList.isEmpty()) {
					this.insertQuizSubjectRel(subjectList, quizId, accountId, userId, orders[i], DConstants.STATUS_ONE);
					i++;
					this.insertAnswerForSubject(anSwers, accountId, quizId, DConstants.STATUS_ONE);
				}
			}
		}

		return listMap;
	}

	@Override
	public void saveQuizSubject(String[] dataStr, String quizId, String quizBankId, String accountId, String userId) {

		QuizSubjectRelExample example = new QuizSubjectRelExample();
		example.createCriteria().andQuizIdEqualTo(quizId);
		List<QuizSubjectRel> listSubjectIs = quizSubjectRelMapper.selectByExample(example);
		if (!listSubjectIs.isEmpty()) {
			List<String> subjectIds = new ArrayList<String>();
			for (QuizSubjectRel rel : listSubjectIs) {
				subjectIds.add(rel.getQuizSubjectId());
			}
			QuizSubjectExample quizSubjectExample = new QuizSubjectExample();
			quizSubjectExample.createCriteria().andIdIn(subjectIds);
			quizSubjectMapper.deleteByExample(quizSubjectExample);
			QuizItemsExample quizItemsExample = new QuizItemsExample();
			quizItemsExample.createCriteria().andQuizSubjectIdIn(subjectIds);
			quizItemsMapper.deleteByExample(quizItemsExample);
			quizSubjectRelMapper.deleteByExample(example);
		}
		List<QuizSubject> quizSubjectList = new LinkedList<QuizSubject>();
		List<QuizItems> quizItemsList = new LinkedList<QuizItems>();
		List<QuizSubjectRel> quizRelList = new LinkedList<QuizSubjectRel>();
		for (int i = 0, j = dataStr.length; i < j; i++) {
			String[] quizArray = dataStr[i].split(DConstants.DELIMITER_DATA);
			String qtype = quizArray[0];
			QuizSubject quizSubject = new QuizSubject();
			String quizSubjectId = AppUtils.uuid();
			quizSubject.setId(quizSubjectId);
			quizSubject.setQuizSubjectName(quizArray[1]);
			quizSubject.setQuizSubjectType(Integer.parseInt(quizArray[0]));
			quizSubject.setAccountId(accountId);
			quizSubject.setCreateId(userId);
			quizSubject.setQuizBankId(quizBankId);
			quizSubject.setQuizSubjectFrom(DConstants.QUIZ_SUBJECT_FROM_MANUAL);
			quizSubject.setQuizSubjectScore(1);
			quizSubject.setQuizSubjectIsPublish(DConstants.ISNOT_EDIT);
			quizSubject.setCreateId(userId);
			quizSubject.setCreateTime(new Date());
			quizSubject.setUpdateId(userId);
			quizSubject.setUpdateTime(new Date());
			quizSubjectList.add(quizSubject);
			if(!qtype.equals(String.valueOf(DConstants.NUMBER_SIX))){
				if(!qtype.equals(String.valueOf(DConstants.NUMBER_SEVEN))){
					String[] options =StringUtils.split(quizArray[2],DConstants.DELIMITER_ATTR);
					for (int z = 0, x = options.length; z < x; z++) {
						String[] optionsItems = StringUtils.split(options[z],DConstants.DELIMITER_CELL_DATA);
						QuizItems quizItems = new QuizItems();
						quizItems.setId(AppUtils.uuid());
						quizItems.setQuizItemName(optionsItems == null || optionsItems.length <= 0  ? "" : optionsItems[0]);
						quizItems.setQuizItemOrder(z);
						quizItems.setQuizSubjectId(quizSubjectId);
						if (String.valueOf(DConstants.NUMBER_ONE).equals(qtype) || String.valueOf(DConstants.NUMBER_TWO).equals(qtype) || String.valueOf(DConstants.NUMBER_THREE).equals(qtype) || String.valueOf(DConstants.NUMBER_FOUR).equals(qtype)) {
							quizItems.setIsCorrect(Integer.parseInt(optionsItems[1]));
						}
						if (String.valueOf(DConstants.NUMBER_FIVE).equals(qtype)) {
							quizItems.setAttachUrl(optionsItems == null || optionsItems.length < 2 ? "" : optionsItems[1]);
						}
						quizItems.setCreateId(userId);
						quizItems.setCreateTime(new Date());
						quizItems.setUpdateId(userId);
						quizItems.setUpdateTime(new Date());
						quizItemsList.add(quizItems);
					}
				}else{
					String[] optionsItems = String.valueOf(quizArray[2]).split(DConstants.DELIMITER_CELL_DATA);
					QuizItems quizItems = new QuizItems();
					quizItems.setId(AppUtils.uuid());
					quizItems.setQuizItemName(optionsItems == null || optionsItems.length <= 0  ? "" : optionsItems[0]);
					quizItems.setAttachUrl(optionsItems == null || optionsItems.length <= 0  ? "" : optionsItems[1]);
					quizItems.setQuizItemOrder(0);
					quizItems.setQuizSubjectId(quizSubjectId);
					quizItems.setCreateId(userId);
					quizItems.setCreateTime(new Date());
					quizItems.setUpdateId(userId);
					quizItems.setUpdateTime(new Date());
					quizItemsList.add(quizItems);
				}
			}
			QuizSubjectRel quizSubjectRel = new QuizSubjectRel();
			quizSubjectRel.setId(AppUtils.uuid());
			quizSubjectRel.setAccountId(accountId);
			quizSubjectRel.setQuizId(quizId);
			quizSubjectRel.setQuizSubjectId(quizSubjectId);
			quizSubjectRel.setCreateId(userId);
			quizSubjectRel.setCreateTime(new Date());
			quizSubjectRel.setUpdateId(userId);
			quizSubjectRel.setUpdateTime(new Date());
			quizSubjectRel.setQuizSubjectOrder(i);
			quizRelList.add(quizSubjectRel);
		}
		quizSubjectCustomizeMapper.insertBatchQuizSubject(quizSubjectList);
		quizSubjectCustomizeMapper.insertBatchQuizItem(quizItemsList);
		quizSubjectCustomizeMapper.insertBatchQuizSubjectRel(quizRelList);
	}

	@Override
	public void saveIsPublishQuizSubject(String quizId, String userId) {
		// 保存试题
		List<QuizSubject> subjectList = new LinkedList<QuizSubject>();
		List<QuizItems> itemList = new LinkedList<QuizItems>();
		List<QuizSubject> quizSubjects = quizSubjectCustomizeMapper.queryQuizSubjectByQuizId(quizId);
		for (QuizSubject quizSubject : quizSubjects) {
			String subjectId = AppUtils.uuid();
			// 更新试卷试题关系
			QuizSubjectRelExample quizSubjectRelExample = new QuizSubjectRelExample();
			quizSubjectRelExample.createCriteria().andQuizIdEqualTo(quizId)
					.andQuizSubjectIdEqualTo(quizSubject.getId());
			List<QuizSubjectRel> quizSubjectRels = quizSubjectRelMapper.selectByExample(quizSubjectRelExample);
			quizSubjectRels.get(0).setQuizSubjectId(subjectId);
			quizSubjectRelExample.clear();
			quizSubjectRelExample.createCriteria().andIdEqualTo(quizSubjectRels.get(0).getId());
			quizSubjectRelMapper.updateByExampleSelective(quizSubjectRels.get(0), quizSubjectRelExample);
			// 保存试题选项
			List<QuizItems> quizItems = quizSubjectCustomizeMapper.queryQuizItemById(quizSubject.getId());
			for (QuizItems quizItem : quizItems) {
				quizItem.setId(AppUtils.uuid());
				quizItem.setIsPublish(DConstants.TYPE_IS_PUBLISH);
				quizItem.setQuizSubjectId(subjectId);
				quizItem.setCreateId(userId);
				quizItem.setCreateTime(new Date());
				quizItem.setUpdateId(userId);
				quizItem.setUpdateTime(new Date());
				itemList.add(quizItem);
			}
			quizSubject.setId(subjectId);
			quizSubject.setQuizSubjectIsPublish(DConstants.TYPE_IS_PUBLISH);
			quizSubject.setCreateId(userId);
			quizSubject.setCreateTime(new Date());
			quizSubject.setUpdateId(userId);
			quizSubject.setUpdateTime(new Date());
			subjectList.add(quizSubject);
		}
		quizSubjectCustomizeMapper.insertBatchQuizItem(itemList);
		quizSubjectCustomizeMapper.insertBatchQuizSubject(subjectList);
	}

	@Override
	public void insertQuizSubjectRel(List<String> subjectList, String quizId, String accountId, String userId,
			String order, Integer isRequired) {

		QuizSubjectRel record = null;
		for (String str : subjectList) {
			record = new QuizSubjectRel();
			record.setQuizId(quizId);
			record.setId(AppUtils.uuid());
			record.setAccountId(accountId);
			record.setCreateId(userId);
			record.setCreateTime(new Date());
			record.setQuizSubjectId(str);
			record.setIsRequired(isRequired);
			if (order != null) {
				record.setQuizSubjectOrder(Integer.parseInt(order));
			}
			quizSubjectRelMapper.insertSelective(record);
		}
	}

	@Override
	public void insertAnswerForSubject(String[] answers, String accountId, String quizId, Integer subjectType) {

		QuizExminationAnswerExample example = new QuizExminationAnswerExample();
		example.createCriteria().andQuizIdEqualTo(quizId).andSubjectTypeEqualTo(subjectType);
		quizExminationAnswerMapper.deleteByExample(example);
		for (String str : answers) {
			if (StringUtils.isNotBlank(str)) {
				String[] datas = str.split(DConstants.DELIMITER_ROW);
				QuizExminationAnswer record = new QuizExminationAnswer();
				record.setId(AppUtils.uuid());
				record.setCreateId(accountId);
				record.setCreateTime(new Date());
				record.setQuizId(quizId);
				record.setSubjectId(datas[0]);
				if (datas.length > 1) {
					record.setSubjectOpstionsAnswer(datas[1]);
				}
				record.setSubjectType(subjectType);
				quizExminationAnswerMapper.insertSelective(record);
			}
		}
	}

	@Override
	public void insertSubjectAutoPreivew(String[] subjectIds, String quizId, String accountId, String userId,
			String[] answers) {
		// 删除随机题
		QuizSubjectRelExample example = new QuizSubjectRelExample();
		example.createCriteria().andQuizIdEqualTo(quizId).andIsRequiredEqualTo(DConstants.STATUS_ZERO);
		quizSubjectRelMapper.deleteByExample(example);
		insertQuizSubjectRel(Arrays.asList(subjectIds), quizId, accountId, userId, null, DConstants.STATUS_ZERO);
		if (answers.length > 0) {
			insertAnswerForSubject(answers, accountId, quizId, DConstants.STATUS_ZERO);
		}
	}

	@Override
	public List<QuizItems> queryQuizItemsList(String subjectId) {

		QuizItemsExample quizItemsExample = new QuizItemsExample();
		quizItemsExample.createCriteria().andQuizSubjectIdEqualTo(subjectId);
		quizItemsExample.setOrderByClause("quiz_item_order ASC");

		return quizItemsMapper.selectByExample(quizItemsExample);
	}

	@Override
	public Integer queryQuizIsPublishBySubjectId(String subjectId) {

		return quizSubjectCustomizeMapper.queryQuizIsPublishBySubjectId(subjectId);
	}

	@Override
	public void copyQuizSubject(String quizId, String newQuizId, String userId) {

		QuizSubjectRelExample quizSubjectRelExample = new QuizSubjectRelExample();
		quizSubjectRelExample.createCriteria().andQuizIdEqualTo(quizId);
		List<QuizSubjectRel> quizSubjectRels = quizSubjectRelMapper.selectByExample(quizSubjectRelExample);
		if (!quizSubjectRels.isEmpty()) {
			List<QuizSubjectRel> list = new LinkedList<QuizSubjectRel>();
			for (QuizSubjectRel item : quizSubjectRels) {
				item.setId(AppUtils.uuid());
				item.setQuizId(newQuizId);
				item.setCreateId(userId);
				item.setCreateTime(Date.from(Instant.now()));
				item.setUpdateId(userId);
				item.setUpdateTime(Date.from(Instant.now()));
				list.add(item);
			}
			quizSubjectCustomizeMapper.insertBatchQuizSubjectRel(list);
		}
	}

	@Override
	public List<Map<String, Object>> queryAutoSubject(String quizId, String[] bankScope, Integer isRequired) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quizId", quizId);
		params.put("isRequired", isRequired);
		if (bankScope != null) {
			List<Map<String, Object>> list = quizCustomizeMapper.getPreviewQuizExamination(params);
			if (!list.isEmpty()) {
				for (Map<String, Object> map : list) {
					if (!Arrays.asList(bankScope).contains(map.get("quiz_bank_id"))) {
						quizSubjectRelMapper.deleteByPrimaryKey((String) map.get("quiz_subject_rel_id"));
					}
				}
			}
		}

		return quizCustomizeMapper.getPreviewQuizExamination(params);
	}

	@Override
	public int saveQuizSubjectRel(QuizBo quiz, String accountId, String userId) {

		QuizSubjectRelExample example = new QuizSubjectRelExample();
		example.createCriteria().andQuizIdEqualTo(quiz.getId()).andIsRequiredEqualTo(quiz.getIsRequired());
		quizSubjectRelMapper.deleteByExample(example);

		List<QuizSubjectRel> quizSubjectRels = new LinkedList<QuizSubjectRel>();
		if (!quiz.getSubjectList().isEmpty()) {
			for (QuizSubjectBo quizSubject : quiz.getSubjectList()) {
				QuizSubjectRel quizSubjectRel = new QuizSubjectRel();
				quizSubjectRel.setId(AppUtils.uuid());
				quizSubjectRel.setQuizId(quiz.getId());
				quizSubjectRel.setQuizSubjectId(quizSubject.getId());
				quizSubjectRel.setQuizSubjectOrder(quizSubject.getQuizSubjectOrder());
				quizSubjectRel.setIsRequired(quiz.getIsRequired());
				quizSubjectRel.setCreateId(userId);
				quizSubjectRel.setCreateTime(new Date());
				quizSubjectRel.setUpdateId(userId);
				quizSubjectRel.setUpdateTime(new Date());
				quizSubjectRel.setAccountId(accountId);
				quizSubjectRels.add(quizSubjectRel);
			}
			quizSubjectCustomizeMapper.insertBatchQuizSubjectRel(quizSubjectRels);
		}
		
		return 0;
	}

	@Override
	public List<List<Map<String, Object>>> createRandomQuizSubject(List<String> subjectIds, String quizId,
			String[] subjectCountArray) {

		List<List<Map<String, Object>>> listMap = new ArrayList<List<Map<String, Object>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < subjectCountArray.length; i++) {
			params.put("subjectCount", Integer.parseInt(subjectCountArray[i]));
			params.put("subjectType", i + 1);
			params.put("list", subjectIds.isEmpty() ? null : subjectIds);
			params.put("quizId", quizId);
			List<Map<String, Object>> list = quizSubjectCustomizeMapper.getSubjectRandoms(params);
			listMap.add(list);
		}

		return listMap;
	}

	@Override
	public List<List<Map<String, Object>>> queryAutoQuizRandomSubjectList(String quizId) {

		List<List<Map<String, Object>>> listMap = new ArrayList<List<Map<String, Object>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quizId", quizId);
		params.put("isRequired", DConstants.QUIZ_SUBJECT_ISNOT_REQUIRED);
		listMap.add(quizSubjectCustomizeMapper.queryAutoQuizSubjectList(params));

		return listMap;
	}

	@Override
	public List<String> queryAutoQuizRequiredSubjectIdList(String quizId) {

		QuizSubjectRelExample quizSubjectRelExample = new QuizSubjectRelExample();
		quizSubjectRelExample.createCriteria().andQuizIdEqualTo(quizId)
				.andIsRequiredEqualTo(DConstants.QUIZ_SUBJECT_IS_REQUIRED);
		List<QuizSubjectRel> quizSubjectRels = quizSubjectRelMapper.selectByExample(quizSubjectRelExample);
		List<String> subjectIdList = new LinkedList<String>();
		for (QuizSubjectRel quizSubjectRel : quizSubjectRels) {
			subjectIdList.add(quizSubjectRel.getQuizSubjectId());
		}

		return subjectIdList;
	}

	public void updateQuizItemImg(String attachUrl, String generateName, String id) {
		// TODO Auto-generated method stub
		
		QuizItemsExample example=new QuizItemsExample();
		example.createCriteria().andQuizSubjectIdEqualTo(id);
		List<QuizItems> qItems=quizItemsMapper.selectByExample(example);
		if (!qItems.isEmpty()) {
			QuizItems quizItem=qItems.get(0);
			quizItem.setAttachUrl(CommonUtils.IMGS_SERVER_DIR+attachUrl+generateName);
			System.err.println(getClass().getName()+"\tqitem---attachUrl:\t"+CommonUtils.IMGS_SERVER_DIR+attachUrl+generateName);
			quizItemsMapper.updateByPrimaryKeySelective(quizItem);
		}
	}
}
