package com.deodio.elearning.modules.survey.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CourseSurveyMapper;
import com.deodio.elearning.persistence.mapper.GroupSurveyMapper;
import com.deodio.elearning.persistence.mapper.SurveyItemsMapper;
import com.deodio.elearning.persistence.mapper.SurveyMapper;
import com.deodio.elearning.persistence.mapper.SurveySubjectMapper;
import com.deodio.elearning.persistence.mapper.SurveyUserItemsMapper;
import com.deodio.elearning.persistence.mapper.TraineeExamRecordMapper;
import com.deodio.elearning.persistence.mapper.customize.SurveyCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.TraineeExamCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CourseSurvey;
import com.deodio.elearning.persistence.model.CourseSurveyExample;
import com.deodio.elearning.persistence.model.GroupSurvey;
import com.deodio.elearning.persistence.model.GroupSurveyExample;
import com.deodio.elearning.persistence.model.Survey;
import com.deodio.elearning.persistence.model.SurveyExample;
import com.deodio.elearning.persistence.model.SurveyItems;
import com.deodio.elearning.persistence.model.SurveyItemsExample;
import com.deodio.elearning.persistence.model.SurveySubject;
import com.deodio.elearning.persistence.model.SurveySubjectExample;
import com.deodio.elearning.persistence.model.SurveyUserItems;
import com.deodio.elearning.persistence.model.SurveyUserItemsExample;
import com.deodio.elearning.persistence.model.TraineeExamRecord;
import com.deodio.elearning.persistence.model.TraineeExamRecordItem;
import com.deodio.elearning.persistence.model.customize.TraineeExamRecordBo;
import com.deodio.elearning.persistence.model.customize.TraineeExamRecordSubjectBo;

@Service
public class SurveyService {

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private CourseSurveyMapper courseSurveyMapper;
	
	@Autowired
	private SurveyCustomizeMapper surveyCustomizeMapper;

	@Autowired
	private SurveyMapper surveyMapper;

	@Autowired
	private GroupSurveyMapper groupSurveyMapper;

	@Autowired
	private SurveySubjectMapper surveySubjectMapper;

	@Autowired
	private SurveyItemsMapper surveyItemsMapper;

	@Autowired
	private SurveySubjectService surveySubjectService;

	@Autowired
	private SurveyItemsService surveyItemsService;

	@Autowired
	private TraineeExamRecordMapper traineeExamRecordMapper;

	@Autowired
	private TraineeExamCustomizeMapper traineeExamCustomizeMapper;

	@Autowired
	private SurveyUserItemsMapper surveyUserItemsMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private UploadService uploadService;

	public void insertSurveryForGroup(String[] dataArrays, String groupId, String accountId, String surveyName,
			String surveyDesc, String userId) throws DeodioException
	{

		Survey survey = new Survey();
		String surveyId = AppUtils.uuid();
		survey.setId(surveyId);
		survey.setSurveyName(surveyName);
		survey.setSurveyDesc(surveyDesc);
		survey.setAccountId(accountId);
		survey.setCreateId(userId);
		survey.setCreateTime(new Date());
		surveyMapper.insertSelective(survey);
		GroupSurvey relModel = new GroupSurvey();
		relModel.setId(AppUtils.uuid());
		relModel.setGroupId(groupId);
		relModel.setSurveyId(surveyId);
		relModel.setCreateId(userId);
		relModel.setCreateTime(new Date());
		relModel.setType(DConstants.STATUS_ONE);
		groupSurveyMapper.insertSelective(relModel);
		this.insertSurveySubjectAndItems(dataArrays, userId, surveyId);
	}

	public void insertSurveySubjectAndItems(String[] dataArrays, String userId, String surveyId)
			throws DeodioException
	{

		SurveySubject surveySubject = null;
		SurveyItems items = null;
		List<SurveySubject> surveySubjectList = new ArrayList<SurveySubject>();
		List<SurveyItems> surveyItemsList = new ArrayList<SurveyItems>();
		for (int i = 0, z = dataArrays.length; i < z; i++) {
			surveySubject = new SurveySubject();
			String[] datas = dataArrays[i].split(DConstants.DELIMITER_DATA);
			String surveySubjectId = AppUtils.uuid();
			surveySubject.setId(surveySubjectId);
			surveySubject.setSurveySubject(datas[1]);
			surveySubject.setCreateId(userId);
			surveySubject.setCreateTime(new Date());
			surveySubject.setSurveySubjectType(Integer.parseInt(datas[0]));
			surveySubject.setSurveySubjectOrder(i);
			surveySubject.setSurveyId(surveyId);
			if (Integer.parseInt(datas[0]) == 3) {
				datas[2] = "是]=[否";
			}
			String[] options = StringUtils.split(datas[2], DConstants.DELIMITER_ATTR);
			surveySubjectList.add(surveySubject);
			for (int j = 0, x = options.length; j < x; j++) {
				items = new SurveyItems();
				items.setId(AppUtils.uuid());
				items.setCreateId(userId);
				items.setSurveySubjectId(surveySubjectId);
				items.setSurveyItemsName(options[j]);
				items.setSurveyItemsOrder(j);
				items.setCreateTime(new Date());
				surveyItemsList.add(items);
			}
		}
		surveyCustomizeMapper.insertsurveySubject(surveySubjectList);
		surveyCustomizeMapper.insertSurveyItems(surveyItemsList);
	}

	public void saveSurveySubjectAndItems(String dataStr, String userId, String surveyId) throws DeodioException {
		System.err.println(getClass().getName() + "\tdataStr:\t" + dataStr);
		String[] dataArrays = dataStr.split(DConstants.DELIMITER_ROW);
		SurveySubject surveySubject = null;
		SurveyItems items = null;
		List<SurveySubject> surveySubjectList = new ArrayList<SurveySubject>();
		List<SurveyItems> surveyItemsList = new ArrayList<SurveyItems>();
		for (int i = 0, j = dataArrays.length; i < j; i++) {
			String[] surveyArray = dataArrays[i].split(DConstants.DELIMITER_DATA);
			String stype = surveyArray[0];
			surveySubject = new SurveySubject();
			String surveySubjectId = AppUtils.uuid();
			surveySubject.setId(surveySubjectId);
			surveySubject.setSurveySubject(surveyArray[1]);
			surveySubject.setCreateId(userId);
			surveySubject.setCreateTime(new Date());
			surveySubject.setSurveySubjectType(Integer.parseInt(surveyArray[0]));
			surveySubject.setSurveySubjectOrder(i);
			surveySubject.setSurveyId(surveyId);
			surveySubjectList.add(surveySubject);
			System.err.println(getClass().getName() + "\tstype:\t" + stype);
			if ("1".equals(stype) || "2".equals(stype) || "3".equals(stype) ) {
				String[] options = StringUtils.split(surveyArray[2], DConstants.DELIMITER_ATTR);
				for (int z = 0, x = options.length; z < x; z++) {
					items = new SurveyItems();
					items.setId(AppUtils.uuid());
					items.setCreateId(userId);
					items.setSurveySubjectId(surveySubjectId);
					items.setSurveyItemsName(StringUtils.split(options[z], DConstants.DELIMITER_ROW)[0]);
					items.setSurveyItemsOrder(z);
					items.setCreateTime(new Date());
					surveyItemsList.add(items);
				}
			}
		}
		surveyCustomizeMapper.insertsurveySubject(surveySubjectList);
//		System.err.println(getClass().getName() + "\tsurveyItemsList:\t" + surveyItemsList.toString());
		if(surveyItemsList.size() > 0){
			surveyCustomizeMapper.insertSurveyItems(surveyItemsList);
		}
	}

	public void deleteSurveryForGroup(String groupId) throws DeodioException {

		String surveyId = surveyCustomizeMapper.getGroupSurvey(groupId);
		if (StringUtils.isNotBlank(surveyId)) {
			surveyCustomizeMapper.deleteSurveyItems(surveyId);
			SurveySubjectExample example = new SurveySubjectExample();
			example.createCriteria().andSurveyIdEqualTo(surveyId);
			surveySubjectMapper.deleteByExample(example);
			GroupSurveyExample groupExample = new GroupSurveyExample();
			groupExample.createCriteria().andGroupIdEqualTo(groupId);
			groupSurveyMapper.deleteByExample(groupExample);
		}
	}

	public void deleteSurvey(String surveyId) throws DeodioException {
		Survey survey=surveyMapper.selectByPrimaryKey(surveyId);
		if (com.deodio.core.utils.StringUtils.isNotBlank(survey.getAttachId())) {
			uploadService.deleteAttach(survey.getAttachId());
		}
		surveyMapper.deleteByPrimaryKey(surveyId);
		SurveySubjectExample example = new SurveySubjectExample();
		example.createCriteria().andSurveyIdEqualTo(surveyId);
		surveySubjectMapper.deleteByExample(example);
	}

	public List<Map<String, Object>> getSurveyList(String groupId, int statusOne) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("type", statusOne);
		return surveyCustomizeMapper.getSurveyList(params);
	}

	public List<Map<String, Object>> getSurveyResultList(String groupId, String userId, PageRequest pageRequest)
			throws DeodioException
	{

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("userId", userId);
		params.put("pagination", pageRequest);
		return surveyCustomizeMapper.findSurveyResultListByGroupId(params);
	}

	public int getSurveyResultCount(String groupId, String userId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("userId", userId);
		return surveyCustomizeMapper.getSurveyResultCountByGroupId(params);
	}

	public List<Map<String, Object>> getSurveyResultByUserId(String groupId, String userId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("groupId", groupId);
		return surveyCustomizeMapper.getSurveyResultByUserId(params);
	}

	public List<Map<String, Object>> getSurveyList(String accountId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		return surveyCustomizeMapper.getSurveyListByAccountId(params);
	}

	/**
	 * 获取问卷调查列表
	 * 
	 * @param accountId
	 * @param pageRequest
	 * @param keywords
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findSurveyList(Map<String, Object> params) throws DeodioException {
		return surveyCustomizeMapper.findSurveyList(params);
	}

	/**
	 * 新增问卷调查
	 */
	public void insertNewSurvey(Survey survey) throws DeodioException {
		surveyMapper.insert(survey);
	}

	/**
	 * 编辑问卷调查
	 */
	public int updateSurvey(Survey survey) throws DeodioException {
		return surveyMapper.updateByPrimaryKeySelective(survey);
	}

	/**
	 * 添加问卷调查
	 * 
	 * @param surveyName
	 * @param surveyDesc
	 * @param surveyModel
	 * @param accountId
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws DeodioException
	 */
	public String insertSurvey(String surveyName, String surveyDesc, Integer surveyModel, String userId,
			String accountId, Date startTime, Date endTime) throws DeodioException
	{

		String surveyId = AppUtils.uuid();
		Survey survey = new Survey();
		survey.setId(surveyId);
		survey.setSurveyName(surveyName);
		survey.setSurveyDesc(surveyDesc);
		// survey.setSurveyType(surveyType);
		survey.setSurveyModel(surveyModel);
		survey.setCreateId(userId);
		survey.setStartTime(startTime);
		survey.setEndTime(endTime);
		survey.setAccountId(accountId);
		survey.setCreateTime(new Date());
		surveyMapper.insert(survey);
		return surveyId;
	}

	/**
	 * 复制问卷调查
	 * 
	 * @Title copySurvey
	 * @param surveyId
	 * @param surveyName
	 * @return
	 * @author cheng.wang
	 * @param userId 
	 * @param accountId 
	 * @param string 
	 * @throws Exception 
	 */
	public void copySurvey(String surveyId, String surveyName, String userId) {		
		Survey surveyInfo = surveyMapper.selectByPrimaryKey(surveyId);
		// 设置新的问卷调查id
		String newSurveyId = AppUtils.uuid();
		surveyInfo.setId(newSurveyId);
		// 设置新的问卷调查的创建时间和修改时间
		Date nowDate = new Date();
		// 更新问卷调查附件
		try {
			if (com.deodio.core.utils.StringUtils.isNotBlank(surveyInfo.getAttachId())) {				
				surveyInfo.setAttachId(attachmentService.copyNewAttachement(surveyId,newSurveyId,userId));				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			surveyInfo.setAttachId("");
		}

		// 设置新的问卷调查为未发布
		surveyInfo.setIsPublish(0);
		// 设置新的问卷调查名字
		surveyInfo.setSurveyName(surveyName);
		// 设置新的问卷创建者
		surveyInfo.setCreateId(userId);
		// 设置新的问卷权限拥有者
		surveyInfo.setAuthorityUserId(userId);
		surveyInfo.setCreateTime(nowDate);
		surveyInfo.setUpdateTime(nowDate);
		// 增加新的问卷调查
		surveyMapper.insert(surveyInfo);
		// 获取待复制问卷调查的题目
		SurveySubjectExample subjectExample = new SurveySubjectExample();
		subjectExample.createCriteria().andSurveyIdEqualTo(surveyId);
		List<SurveySubject> surveySubjects = surveySubjectMapper.selectByExample(subjectExample);
		if (surveySubjects != null) {
			for (SurveySubject surveySubject : surveySubjects) {
				System.err.println(getClass().getName() + "\tsurveySubjectInfo:\t" + surveySubjects.toString());
				// 获取旧的问卷调查题目的id
				String oldSurveySubjectId = surveySubject.getId();
				// 设置新的问卷调查题目的id
				String newSurveySubjectId = AppUtils.uuid();
				surveySubject.setId(newSurveySubjectId);
				// 设置新的问卷调查题目，对应新的问卷调查id
				surveySubject.setSurveyId(newSurveyId);
				// 设置新的问卷调查题目的创建时间和修改时间
				surveySubject.setCreateTime(nowDate);
				surveySubject.setUpdateTime(nowDate);
				// 添加新的问卷调查的题目
				surveySubjectMapper.insert(surveySubject);
				SurveyItemsExample surveyItemsExample = new SurveyItemsExample();
				// 通过旧的问卷调查id获取旧的答案信息
				surveyItemsExample.createCriteria().andSurveySubjectIdEqualTo(oldSurveySubjectId);
				List<SurveyItems> surveyItemsList = surveyItemsMapper.selectByExample(surveyItemsExample);
				if (surveyItemsList != null) {
					System.err.println(getClass().getName() + "\tsurveyItemsList:\t" + surveyItemsList.toString());
					for (SurveyItems surveyItems : surveyItemsList) {
						// 设置新的问卷调查问题选项的id
						String newSurveyItemsId = AppUtils.uuid();
						surveyItems.setId(newSurveyItemsId);
						// 设置新的问卷调查问题选项,关联的问卷调查题目的id
						surveyItems.setSurveySubjectId(newSurveySubjectId);
						// 设置新的问卷调查题目选项的创建时间和修改时间
						surveyItems.setCreateTime(nowDate);
						surveyItems.setUpdateTime(nowDate);
						surveyItems.setCreateId(userId);
						surveyItems.setUpdateId(userId);
						// 添加新的问卷调查的问题选项
						surveyItemsMapper.insert(surveyItems);
					}
				}
			}
		}

	}

	public String insertOrUpdateSurvey(String surveyId, String surveyName, String surveyDesc, Integer surveyModel,
			String userId, String accountId, Date startTime, Date endTime, String surveyCover, String attachId)
			throws DeodioException
	{

		Survey survey = new Survey();
		survey.setSurveyName(surveyName);
		survey.setSurveyDesc(surveyDesc);
		// survey.setSurveyType(surveyType);
		survey.setSurveyModel(surveyModel);
		survey.setSurveyCover(surveyCover);
		survey.setStartTime(startTime);
		survey.setEndTime(endTime);
		survey.setAccountId(accountId);
		if (StringUtils.isBlank(surveyId)) {
			surveyId = AppUtils.uuid();
			survey.setId(surveyId);
			survey.setCreateId(userId);
			survey.setAuthorityUserId(userId);
			survey.setCreateTime(new Date());
			surveyMapper.insert(survey);
		} else {
			survey.setId(surveyId);
			survey.setUpdateId(userId);
			survey.setUpdateTime(new Date());
			surveyMapper.updateByPrimaryKeySelective(survey);
		}
		// 图片表更新
		if (null != attachId && !"".equals(attachId)) {
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(surveyId);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
		return surveyId;
	}

	/**
	 * 更新问卷调查附件
	 */
	public void updateSurveyAttachment(String attachId, String surveyId) {
		// 图片表更新
		if (null != attachId && !"".equals(attachId)) {
			// AttachmentExample example =new AttachmentExample();
			// example.createCriteria().andRelIdEqualTo(surveyId);
			// List<Attachment>
			// attachments=attachmentMapper.selectByExample(example);
			// if (!attachments.isEmpty()) {
			// for (Attachment attachment : attachments) {
			// if (!attachId.equals(attachment.getId())) {
			// attachmentMapper.deleteByPrimaryKey(attachment.getId());
			// }
			// }
			// }
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(surveyId);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
	}

	/**
	 * 删除问卷调查
	 * 
	 * @param surveyId
	 * @throws DeodioException
	 */
	public void deleteByPrimaryKey(String surveyId) throws DeodioException {

		surveyMapper.deleteByPrimaryKey(surveyId);
	}

	public List<Map<String, Object>> getSubjectAndItems(String surveyId, String accountId) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("surveyId", surveyId);
		params.put("accountId", accountId);
		return surveyCustomizeMapper.getSubjectAndItems(params);
	}

	public void deleteSubjectAndItems(String surveyId) throws DeodioException {

		surveyCustomizeMapper.deleteItemsBySurveyId(surveyId);
		SurveySubjectExample example = new SurveySubjectExample();
		example.createCriteria().andSurveyIdEqualTo(surveyId);
		surveySubjectMapper.deleteByExample(example);
	}

	public Survey getSurvey(String surveyId) throws DeodioException {

		return surveyMapper.selectByPrimaryKey(surveyId);
	}

	/**
	 * 获取问卷调查
	 * 
	 * @param surveyId
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> getSubjectList(String surveyId) throws DeodioException {

		return surveyCustomizeMapper.querySubjectList(surveyId);
	}

	/**
	 * 保存问卷调查答案
	 * 
	 * @param traineeExamRecordBo
	 * @param dataRows
	 * @return
	 * @throws DeodioException
	 */
	public TraineeExamRecordBo saveSurveyAnswer(TraineeExamRecordBo traineeExamRecordBo, String[] dataRows)
			throws DeodioException
	{
		// 查询试卷下的题目
		List<SurveySubject> surveySubjectList = surveySubjectService
				.querySurveySubjectList(traineeExamRecordBo.getExamId());
		for (int i = 0; i < dataRows.length; i++) {
			String[] surveyArray = dataRows[i].split(DConstants.DELIMITER_DATA);
			TraineeExamRecordSubjectBo recordSubject = new TraineeExamRecordSubjectBo();
			SurveySubject surveySubject = surveySubjectList.get(i);
			recordSubject.setId(AppUtils.uuid());
			recordSubject.setSubjectId(surveySubject.getId());
			recordSubject.setExamRecordId(traineeExamRecordBo.getExamId());
			recordSubject.setSubjectName(surveyArray[1]);
			recordSubject.setCreateId(traineeExamRecordBo.getCreateId());
			recordSubject.setCreateTime(traineeExamRecordBo.getCreateTime());
			recordSubject.setUpdateId(traineeExamRecordBo.getUpdateId());
			recordSubject.setUpdteTime(traineeExamRecordBo.getUpdteTime());
			Integer qtype = Integer.parseInt(surveyArray[0]);
			recordSubject.setSubjectType(qtype);
			recordSubject.setSubjectOrder(i + 1);
			// 查询题目下的选项
			List<SurveyItems> surveyItemsList = surveyItemsService.querySurveyItemsList(recordSubject.getSubjectId());
			String[] options = StringUtils.split(surveyArray[2], DConstants.DELIMITER_ATTR);
			for (int j = 0; j < options.length; j++) {
				TraineeExamRecordItem recordItem = new TraineeExamRecordItem();
				SurveyItems item = surveyItemsList.get(j);
				String[] optionsItems = StringUtils.split(options[j], DConstants.DELIMITER_CELL_DATA);
				recordItem.setId(AppUtils.uuid());
				recordItem.setItemId(item.getId());
				recordItem.setExamRecordSubjectId(recordSubject.getId());
				recordItem.setItemName(optionsItems[0]);
				recordItem.setItemOrder(item.getSurveyItemsOrder());
				recordItem.setIsCorrect(Integer.parseInt(optionsItems[1]));
				recordItem.setCreateId(recordSubject.getCreateId());
				recordItem.setCreateTime(recordSubject.getCreateTime());
				recordItem.setUpdateId(recordSubject.getUpdateId());
				recordItem.setUpdteTime(recordSubject.getUpdteTime());
				recordItem.setAttachUrl("");
				recordSubject.getItemsList().add(recordItem);
			}
			// 批量添加选项
			traineeExamCustomizeMapper.insertItemByBatch(recordSubject.getItemsList());
			traineeExamRecordBo.getSubjectList().add(recordSubject);
		}
		traineeExamCustomizeMapper.insertSubjectByBatch(traineeExamRecordBo.getSubjectList());
		TraineeExamRecord record = new TraineeExamRecord();
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(record, traineeExamRecordBo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		traineeExamRecordMapper.insert(record);

		return traineeExamRecordBo;
	}

	/**
	 * 获取被引用的课程问卷调查
	 * 
	 * @param Map
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findQuoteSurveyList(Map<String, Object> params) throws DeodioException {
		return surveyCustomizeMapper.findQuoteSurveyList(params);
	}

	/**
	 * 根据id获取问卷调查的所有讲师
	 * 
	 * @param Map
	 * @throws DeodioException
	 */
	public Map<String, Object> findShareSurveyTrainee(String surveyId, String userName, String userId, Integer pageNo)
			throws DeodioException
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			Survey survey=surveyMapper.selectByPrimaryKey(surveyId);
			String groupId=survey.getGroupId();
			PageRequest pageRequest = new PageRequest(pageNo);
			pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
			Map<String, Object> params = new HashMap<String, Object>();
			if (userName != null && !"".equals(userName)) {
				params.put("userName", userName);
			}
			params.put("groupId", groupId);
			params.put("userId", userId);
			params.put("pagination", pageRequest);
			System.err.println(getClass().getName() + "\tparams:\t" + params.toString());
			List<Map<String, Object>> surveyTraineeList = surveyCustomizeMapper.findShareSurveyTrainee(params);
			jsonMap.put("dataList", surveyTraineeList);
			jsonMap.put("currePage", pageNo);
			jsonMap.put("totalPage", pageRequest.getPagination().getTotalPages());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return jsonMap;
	}

	public void insertUserSurveryForGroup(String[] dataArrays, String groupId, String userId) throws DeodioException {
		// 刪除原数据
		SurveyUserItemsExample example = new SurveyUserItemsExample();
		example.createCriteria().andGroupIdEqualTo(groupId).andUserIdEqualTo(userId);
		surveyUserItemsMapper.deleteByExample(example);
		List<SurveyUserItems> surveyUserItemsList = new ArrayList<SurveyUserItems>();
		for (int i = 0, z = dataArrays.length; i < z; i++) {
			// userItem.setSurveySubjectId(surveySubjectId);
			String[] datas = dataArrays[i].split(DConstants.DELIMITER_DATA);
			String subjectName = datas[1];
			String[] options = StringUtils.split(datas[2], DConstants.DELIMITER_ATTR);
			for (int j = 0, x = options.length; j < x; j++) {
				SurveyUserItems userItem = new SurveyUserItems();
				userItem.setId(AppUtils.uuid());
				userItem.setGroupId(groupId);
				userItem.setUserId(userId);
				userItem.setSurveySubjectName(subjectName);
				userItem.setType(datas[0]);
				String[] checkStatus = StringUtils.split(options[j], DConstants.DELIMITER_CELL_DATA);
				if (!"3".equals(datas[0])) {
					userItem.setSurveyItemName(checkStatus[0]);
				}
				if ("3".equals(datas[0]) && j == 0) {
					userItem.setSurveyItemName("对");
				}
				if ("3".equals(datas[0]) && j == 1) {
					userItem.setSurveyItemName("错");
				}
				userItem.setCheckFlag(checkStatus[1]);
				userItem.setSurveyItemOrder(i);
				surveyUserItemsList.add(userItem);
			}
		}
		surveyCustomizeMapper.insertUserSurveyforGroup(surveyUserItemsList);
	}

	public void insertSurveryUserItemForGroup(List<Map<String, Object>> surveyUserlist, String groupId, String userId)
			throws DeodioException
	{
		List<SurveyUserItems> surveyUserItemsList = new ArrayList<SurveyUserItems>();
		for (int i = 0, z = surveyUserlist.size(); i < z; i++) {
			String subjectName = (String) surveyUserlist.get(i).get("survey_subject");
			String item_options = (String) surveyUserlist.get(i).get("item_options");
			Integer surveySubjectType = (Integer) surveyUserlist.get(i).get("survey_subject_type");
			Integer surveyItemOrder = (Integer) surveyUserlist.get(i).get("survey_subject_order");
			String[] options = item_options.split("#");
			for (int j = 0, x = options.length; j < x; j++) {
				SurveyUserItems userItem = new SurveyUserItems();
				userItem.setId(AppUtils.uuid());
				userItem.setGroupId(groupId);
				userItem.setUserId(userId);
				userItem.setSurveySubjectName(subjectName);
				userItem.setType(String.valueOf(surveySubjectType));
				userItem.setSurveyItemName(options[j]);
				userItem.setSurveyItemOrder(surveyItemOrder);
				userItem.setCheckFlag("0");
				surveyUserItemsList.add(userItem);
			}
		}
		surveyCustomizeMapper.insertUserSurveyforGroup(surveyUserItemsList);
	}

	public List<SurveyUserItems> getSurveyUserItems(String userId, String groupId) {
		SurveyUserItemsExample example = new SurveyUserItemsExample();
		SurveyUserItemsExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andGroupIdEqualTo(groupId);
		criteria.andCheckFlagEqualTo("1");
		return surveyUserItemsMapper.selectByExample(example);
	}

	
	// 获取问卷调查的基本信息
	public Map<String, Object> getSurveyBaseInfo(Map<String, Object> params) {
		return surveyCustomizeMapper.getSurveyBaseInfo(params);
	}

	// 获取问卷调查
	public List<Survey> selectByExample(SurveyExample example) {
		return surveyMapper.selectByExample(example);
	}

	public Attachment getAttachUrl(String attachId) {
		// TODO Auto-generated method stub
		return attachmentMapper.selectByPrimaryKey(attachId);
	}

	//加载课程管理的问卷调查列表
	public Map<String, Object> loadCourseManagerSurveyList(Integer pageNo, Integer pageSize, String keywords,
			Integer isPublish, List<String> contentList, String course_start_time, String course_expire_time, 
			String accountId, String userId){
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		params.put("pagination", pageRequest);
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		params.put("isPublish", isPublish);
		params.put("startDate", DateTimeUtils.parse(course_start_time));					
		List<Map<String, Object>> dataList = surveyCustomizeMapper.findCourseManagerSurveyList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		return jsonMap;
	}
	//加载课程内容的问卷调查列表
	public Map<String, Object> loadCourseSurveyList(Integer pageNo, Integer pageSize, String keywords,
			Integer isPublish, List<String> contentList, String course_start_time, String course_expire_time, String accountId, String userId)
	{
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		params.put("pagination", pageRequest);
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		params.put("isPublish", isPublish);
		params.put("list", contentList!=null&&!contentList.isEmpty()?contentList:null);		
		params.put("startDate", DateTimeUtils.parse(course_start_time));			
		params.put("endDate", DateTimeUtils.parse(course_expire_time));
		
		List<Map<String, Object>> dataList = surveyCustomizeMapper.findCourseSurveyList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		return jsonMap;
	}

	public Map<String, Object> loadSurveyList(Integer pageNo, Integer pageSize, String keywords, String accountId,
			String userId)
	{
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		params.put("pagination", pageRequest);
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		List<Map<String, Object>> dataList = surveyCustomizeMapper.findSurveyList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		return jsonMap;
	}

	public Map<String, Object> getSurveyIsEdit(String surveyId, String userId) {
		// TODO Auto-generated method stub
		// isPublished为false表示未发布
		boolean isPublished = false;
		// surveyIsEdit为false表示对方未编辑
		boolean surveyIsEdit = false;
		// surveyIsEdit为false表示编辑权限不是自己
		boolean surveyIsSelf = false;
		// isCreated为false表示不是自己创建
		boolean isCreated = false;

		Survey survey = surveyMapper.selectByPrimaryKey(surveyId);
		String createId = survey.getCreateId();
		Integer isPublish = survey.getIsPublish();
		String authorityUserId = survey.getAuthorityUserId();
		Integer authorityUserIsEdit = survey.getAuthorityUserIsEdit();
		if (createId.equals(userId)) {
			isCreated = true;
		}
		if (isPublish != null && isPublish == 1) {
			isPublished = true;
		}
		if (createId.equals(authorityUserId)) {
			surveyIsSelf = true;
		} else {
			if (authorityUserIsEdit == null || authorityUserIsEdit == 0) {
				surveyIsEdit = false;
			} else {
				surveyIsEdit = true;
			}
		}
		Map<String, Object> surveyIsInEdit = new HashMap<String, Object>();
		surveyIsInEdit.put("isPublished", isPublished);
		surveyIsInEdit.put("surveyIsSelf", surveyIsSelf);
		surveyIsInEdit.put("surveyIsEdit", surveyIsEdit);
		surveyIsInEdit.put("isCreated", isCreated);
		System.err.println(getClass().getName() + "\tsurveyIsInEdit:\t" + surveyIsInEdit.toString());
		return surveyIsInEdit;
	}

	public Map<String, Object> checkDeleteAuthority(String surveyId, String userId) {
		// TODO Auto-generated method stub
		// isNowCreated为false表示不是当前用户创建的
		boolean isNowCreated = false;
		// isQuoted为false表示该问卷调查没有被引用
		boolean isQuoted = false;
		// hasEditAuthority为false表示没有编辑权限
		boolean hasEditAuthority = false;
		// isPublished为false表示未发布
		boolean isPublished=false;
		Survey survey = surveyMapper.selectByPrimaryKey(surveyId);
		if (survey.getCreateId().equals(userId)) {// 当前用户创建
			isNowCreated = true;
		}
		if (survey.getAuthorityUserId().equals(userId)) {
			hasEditAuthority = true;
		}
		if(survey.getIsPublish()==DConstants.TYPE_IS_PUBLISH) {
			isPublished=true;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("surveyId", surveyId);
		List<Map<String, Object>> surveyList = surveyCustomizeMapper.findQuoteSurveyList(params);
		if (surveyList.size() > 0) {// 被引用,无法删除
			isQuoted = true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isNowCreated", isNowCreated);
		map.put("isQuoted", isQuoted);
		map.put("hasEditAuthority", hasEditAuthority);
		map.put("isPublished", isPublished);
		return map;
	}

	public void setShareSurveyTrainee(String surveyId, String userId) {
		// TODO Auto-generated method stub
		if (com.deodio.core.utils.StringUtils.isEmpty(userId)) {
			// 查找survey的createID
			Survey survey = surveyMapper.selectByPrimaryKey(surveyId);
			System.err.println(getClass().getName() + "\t----\t" + survey.getCreateId());
			userId = survey.getCreateId();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("surveyId", surveyId);
		params.put("userId", userId);
		// 设置问卷调查的编辑权限
		surveyCustomizeMapper.setShareSurveyTrainee(params);

	}

	public List<Map<String, Object>> getAllSurveyBySurveyName(String surveyName) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("surveyName", surveyName);
		return surveyCustomizeMapper.getAllSurveyBySurveyName(params);
	}

	public void saveView(String surveyId, Integer isEdit, String userId, String dataStr,Integer isPublish) {
		// TODO Auto-generated method stub
		if (!com.deodio.core.utils.StringUtils.isEmpty(dataStr)) {

			deleteSubjectAndItems(surveyId);
			saveSurveySubjectAndItems(dataStr, userId, surveyId);
			Survey survey = surveyMapper.selectByPrimaryKey(surveyId);
			survey.setIsPublish(isPublish);		
			survey.setAuthorityUserIsEdit(isEdit);
			surveyMapper.updateByPrimaryKeySelective(survey);
			
		}
	
	}

	public List<CourseSurvey> findCourseSurveyList(String surveyId, String courseId) {
		CourseSurveyExample example = new CourseSurveyExample();
		example.createCriteria().andSurveyIdEqualTo(surveyId).andCourseIdEqualTo(courseId);
		List<CourseSurvey> courseSurveys = courseSurveyMapper.selectByExample(example);
		return courseSurveys;
	}
}
