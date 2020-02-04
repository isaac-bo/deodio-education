package com.deodio.elearning.modules.trainee.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.quiz.service.impl.QuizServiceImpl;
import com.deodio.elearning.modules.trainee.service.TraineeService;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.QuizMapper;
import com.deodio.elearning.persistence.mapper.TraineeCourseHomeworkMapper;
import com.deodio.elearning.persistence.mapper.TraineeExamRecordItemMapper;
import com.deodio.elearning.persistence.mapper.TraineeExamRecordMapper;
import com.deodio.elearning.persistence.mapper.TraineeExamRecordSubjectMapper;
import com.deodio.elearning.persistence.mapper.customize.TraineeExamCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CourseHomework;
import com.deodio.elearning.persistence.model.Quiz;
import com.deodio.elearning.persistence.model.TraineeCourseHomework;
import com.deodio.elearning.persistence.model.TraineeCourseHomeworkExample;
import com.deodio.elearning.persistence.model.TraineeExamRecord;
import com.deodio.elearning.persistence.model.TraineeExamRecordExample;
import com.deodio.elearning.persistence.model.TraineeExamRecordItem;
import com.deodio.elearning.persistence.model.TraineeExamRecordItemExample;
import com.deodio.elearning.persistence.model.TraineeExamRecordSubject;
import com.deodio.elearning.persistence.model.TraineeExamRecordSubjectExample;
import com.deodio.elearning.persistence.model.customize.TraineeExamRecordSubjectBo;

@Service
public class TraineeServiceImpl implements TraineeService {
	@Autowired
	private TraineeExamRecordMapper traineeExamRecordMapper;
	@Autowired
	private TraineeExamRecordSubjectMapper traineeExamRecordSubjectMapper;
	@Autowired
	private TraineeExamRecordItemMapper traineeExamRecordItemMapper;
	@Autowired
	private TraineeExamCustomizeMapper traineeExamCustomizeMapper;
	@Autowired
	private QuizMapper quizMapper;
	@Autowired
	private TraineeCourseHomeworkMapper traineeCourseHomeworkMapper;
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Override
	public void saveTraineeQuizSubject(String[] dataStr, String examId, String courseId, String accountId,
			String userId, Date startTime,Integer status) {
		//删除历史试卷记录
		TraineeExamRecordExample example = new TraineeExamRecordExample();
		example.createCriteria().andExamIdEqualTo(examId).andCourseIdEqualTo(courseId).andExamTypeEqualTo(1);
		List<TraineeExamRecord> listTraineeExamRecord = traineeExamRecordMapper.selectByExample(example);
		traineeExamRecordMapper.deleteByExample(example);
		if (!listTraineeExamRecord.isEmpty()) {
			List<String> recordIds = new ArrayList<String>();
			for (TraineeExamRecord rel : listTraineeExamRecord) {
				recordIds.add(rel.getId());
			}
			TraineeExamRecordSubjectExample subjectExample = new TraineeExamRecordSubjectExample();
			subjectExample.createCriteria().andExamRecordIdIn(recordIds);
			List<TraineeExamRecordSubject> listExamRecordSubject = traineeExamRecordSubjectMapper
					.selectByExample(subjectExample);
			traineeExamRecordSubjectMapper.deleteByExample(subjectExample);
			if (!listExamRecordSubject.isEmpty()) {
				List<String> subjectIds = new ArrayList<String>();
				for (TraineeExamRecordSubject rel : listExamRecordSubject) {
					subjectIds.add(rel.getId());
				}
				TraineeExamRecordItemExample recordItemExample = new TraineeExamRecordItemExample();
				recordItemExample.createCriteria().andExamRecordSubjectIdIn(subjectIds);
				traineeExamRecordItemMapper.deleteByExample(recordItemExample);
			}
		}
		//登记考试记录,包括题目,试题明细记录
		TraineeExamRecord traineeExamRecord = new TraineeExamRecord();
		String examRecordId = AppUtils.uuid();
		traineeExamRecord.setId(examRecordId);
		traineeExamRecord.setCourseId(courseId);
		traineeExamRecord.setExamId(examId);
		traineeExamRecord.setExamType(1);
		traineeExamRecord.setStatus(status);
		traineeExamRecord.setAccountId(accountId);
		traineeExamRecord.setTraineeId(userId);
		traineeExamRecord.setCreateId(userId);
		traineeExamRecord.setCreateTime(new Date());
		traineeExamRecord.setStartTime(startTime);
		traineeExamRecord.setFinishTime(Date.from(Instant.now()));
		traineeExamRecordMapper.insert(traineeExamRecord);
		TraineeExamRecordItem traineeExamRecordItem = new TraineeExamRecordItem();
		traineeExamRecordItem.setId(AppUtils.uuid());
		List<TraineeExamRecordSubjectBo> examRecordSubjectList = new ArrayList<TraineeExamRecordSubjectBo>();
		List<TraineeExamRecordItem> examRecordSubjectItemsList = new ArrayList<TraineeExamRecordItem>();
		for (int i = 0, j = dataStr.length; i < j; i++) {
			String[] quizArray = dataStr[i].split(DConstants.DELIMITER_DATA);
			String qtype = quizArray[0];
			TraineeExamRecordSubjectBo examRecordSubject = new TraineeExamRecordSubjectBo();
			String examRecordSubjectId = AppUtils.uuid();
			examRecordSubject.setId(examRecordSubjectId);
			examRecordSubject.setExamRecordId(examRecordId);
			examRecordSubject.setSubjectName(quizArray[1]);
			examRecordSubject.setSubjectType(Integer.parseInt(quizArray[0]));
			examRecordSubject.setScore(1);
			examRecordSubject.setCreateId(userId);
			examRecordSubject.setCreateTime(new Date());
			examRecordSubject.setSubjectOrder(i);
			examRecordSubjectList.add(examRecordSubject);
			if (!qtype.equals(String.valueOf(DConstants.NUMBER_SIX))) {
				if (!qtype.equals(String.valueOf(DConstants.NUMBER_SEVEN))) {
					String[] options = StringUtils.split(quizArray[2], DConstants.DELIMITER_ATTR);
					for (int z = 0, x = options.length; z < x; z++) {
						String[] optionsItems = StringUtils.split(options[z], DConstants.DELIMITER_CELL_DATA);
						TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
						examRecordItems.setId(AppUtils.uuid());
						examRecordItems
								.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
						examRecordItems.setItemOrder(z);
						examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
						if (String.valueOf(DConstants.NUMBER_ONE).equals(qtype)
								|| String.valueOf(DConstants.NUMBER_TWO).equals(qtype)
								|| String.valueOf(DConstants.NUMBER_THREE).equals(qtype)
								|| String.valueOf(DConstants.NUMBER_FOUR).equals(qtype)) {
							examRecordItems.setIsCorrect(Integer.parseInt(optionsItems[1]));
						}
						if (String.valueOf(DConstants.NUMBER_FIVE).equals(qtype)) {
							examRecordItems.setAttachUrl(
									optionsItems == null || optionsItems.length < 2 ? "" : optionsItems[1]);
						}
						examRecordItems.setCreateId(userId);
						examRecordItems.setCreateTime(new Date());
						examRecordSubjectItemsList.add(examRecordItems);
					}
				} else {
					String[] optionsItems = String.valueOf(quizArray[2]).split(DConstants.DELIMITER_CELL_DATA);
					TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
					examRecordItems.setId(AppUtils.uuid());
					examRecordItems
							.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
					examRecordItems.setItemOrder(0);
					examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
					examRecordItems.setCreateId(userId);
					examRecordItems.setCreateTime(new Date());
					examRecordSubjectItemsList.add(examRecordItems);
				}
			}
		}
		traineeExamCustomizeMapper.insertSubjectByBatch(examRecordSubjectList);
		traineeExamCustomizeMapper.insertItemByBatch(examRecordSubjectItemsList);
	}
	@Override
	public void submitTraineeQuizSubject(String[] dataStr, String examId, String courseId, String accountId,
			String userId,Date startTime,Integer status) {
		Quiz quiz=quizMapper.selectByPrimaryKey(examId);
		TraineeExamRecordExample example = new TraineeExamRecordExample();
		example.createCriteria().andExamIdEqualTo(examId).andCourseIdEqualTo(courseId).andExamTypeEqualTo(1);
		List<TraineeExamRecord> listTraineeExamRecord = traineeExamRecordMapper.selectByExample(example);
		traineeExamRecordMapper.deleteByExample(example);
		if (!listTraineeExamRecord.isEmpty()) {
			List<String> recordIds = new ArrayList<String>();
			for (TraineeExamRecord rel : listTraineeExamRecord) {
				recordIds.add(rel.getId());
			}
			TraineeExamRecordSubjectExample subjectExample = new TraineeExamRecordSubjectExample();
			subjectExample.createCriteria().andExamRecordIdIn(recordIds);
			List<TraineeExamRecordSubject> listExamRecordSubject = traineeExamRecordSubjectMapper
					.selectByExample(subjectExample);
			traineeExamRecordSubjectMapper.deleteByExample(subjectExample);
			if (!listExamRecordSubject.isEmpty()) {
				List<String> subjectIds = new ArrayList<String>();
				for (TraineeExamRecordSubject rel : listExamRecordSubject) {
					subjectIds.add(rel.getId());
				}
				TraineeExamRecordItemExample recordItemExample = new TraineeExamRecordItemExample();
				recordItemExample.createCriteria().andExamRecordSubjectIdIn(subjectIds);
				traineeExamRecordItemMapper.deleteByExample(recordItemExample);
			}
		}
		TraineeExamRecord traineeExamRecord = new TraineeExamRecord();
		String examRecordId = AppUtils.uuid();
		traineeExamRecord.setId(examRecordId);
		traineeExamRecord.setCourseId(courseId);
		traineeExamRecord.setExamId(examId);
		traineeExamRecord.setExamType(1);
		traineeExamRecord.setStatus(status);
		traineeExamRecord.setAccountId(accountId);
		traineeExamRecord.setTraineeId(userId);
		traineeExamRecord.setCreateId(userId);
		traineeExamRecord.setCreateTime(new Date());
		traineeExamRecord.setLeaveTimes(quiz.getQuizMaxTimes()-1);
		traineeExamRecordMapper.insert(traineeExamRecord);
		TraineeExamRecordItem traineeExamRecordItem = new TraineeExamRecordItem();
		traineeExamRecordItem.setId(AppUtils.uuid());
		List<TraineeExamRecordSubjectBo> examRecordSubjectList = new ArrayList<TraineeExamRecordSubjectBo>();
		List<TraineeExamRecordItem> examRecordSubjectItemsList = new ArrayList<TraineeExamRecordItem>();
		for (int i = 0, j = dataStr.length; i < j; i++) {
			String[] quizArray = dataStr[i].split(DConstants.DELIMITER_DATA);
			String qtype = quizArray[0];
			TraineeExamRecordSubjectBo examRecordSubject = new TraineeExamRecordSubjectBo();
			String examRecordSubjectId = AppUtils.uuid();
			examRecordSubject.setId(examRecordSubjectId);
			examRecordSubject.setExamRecordId(examRecordId);
			examRecordSubject.setSubjectName(quizArray[1]);
			examRecordSubject.setSubjectType(Integer.parseInt(quizArray[0]));
			examRecordSubject.setScore(1);
			examRecordSubject.setCreateId(userId);
			examRecordSubject.setCreateTime(new Date());
			examRecordSubject.setSubjectOrder(i);
			examRecordSubjectList.add(examRecordSubject);
			if (!qtype.equals(String.valueOf(DConstants.NUMBER_SIX))) {
				if (!qtype.equals(String.valueOf(DConstants.NUMBER_SEVEN))) {
					String[] options = StringUtils.split(quizArray[2], DConstants.DELIMITER_ATTR);
					for (int z = 0, x = options.length; z < x; z++) {
						String[] optionsItems = StringUtils.split(options[z], DConstants.DELIMITER_CELL_DATA);
						TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
						examRecordItems.setId(AppUtils.uuid());
						examRecordItems
								.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
						examRecordItems.setItemOrder(z);
						examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
						if (String.valueOf(DConstants.NUMBER_ONE).equals(qtype)
								|| String.valueOf(DConstants.NUMBER_TWO).equals(qtype)
								|| String.valueOf(DConstants.NUMBER_THREE).equals(qtype)
								|| String.valueOf(DConstants.NUMBER_FOUR).equals(qtype)) {
							examRecordItems.setIsCorrect(Integer.parseInt(optionsItems[1]));
						}
						if (String.valueOf(DConstants.NUMBER_FIVE).equals(qtype)) {
							examRecordItems.setAttachUrl(
									optionsItems == null || optionsItems.length < 2 ? "" : optionsItems[1]);
						}
						examRecordItems.setCreateId(userId);
						examRecordItems.setCreateTime(new Date());
						examRecordSubjectItemsList.add(examRecordItems);
					}
				} else {
					String[] optionsItems = String.valueOf(quizArray[2]).split(DConstants.DELIMITER_CELL_DATA);
					TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
					examRecordItems.setId(AppUtils.uuid());
					examRecordItems
							.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
					examRecordItems.setItemOrder(0);
					examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
					examRecordItems.setCreateId(userId);
					examRecordItems.setCreateTime(new Date());
					examRecordSubjectItemsList.add(examRecordItems);
				}
			}
		}
		traineeExamCustomizeMapper.insertSubjectByBatch(examRecordSubjectList);
		traineeExamCustomizeMapper.insertItemByBatch(examRecordSubjectItemsList);
	}
	@Override
	public void saveTraineeSurveySubject(String[] dataStr, String surveyId, String courseId, String accountId,
			String userId, Date startTime,Integer status) {
		// TODO Auto-generated method stub
		//删除历史试卷记录
				TraineeExamRecordExample example = new TraineeExamRecordExample();
				example.createCriteria().andExamIdEqualTo(surveyId).andCourseIdEqualTo(courseId).andExamTypeEqualTo(2);
				List<TraineeExamRecord> listTraineeExamRecord = traineeExamRecordMapper.selectByExample(example);
				traineeExamRecordMapper.deleteByExample(example);
				if (!listTraineeExamRecord.isEmpty()) {
					List<String> recordIds = new ArrayList<String>();
					for (TraineeExamRecord rel : listTraineeExamRecord) {
						recordIds.add(rel.getId());
					}
					TraineeExamRecordSubjectExample subjectExample = new TraineeExamRecordSubjectExample();
					subjectExample.createCriteria().andExamRecordIdIn(recordIds);
					List<TraineeExamRecordSubject> listExamRecordSubject = traineeExamRecordSubjectMapper
							.selectByExample(subjectExample);
					traineeExamRecordSubjectMapper.deleteByExample(subjectExample);
					if (!listExamRecordSubject.isEmpty()) {
						List<String> subjectIds = new ArrayList<String>();
						for (TraineeExamRecordSubject rel : listExamRecordSubject) {
							subjectIds.add(rel.getId());
						}
						TraineeExamRecordItemExample recordItemExample = new TraineeExamRecordItemExample();
						recordItemExample.createCriteria().andExamRecordSubjectIdIn(subjectIds);
						traineeExamRecordItemMapper.deleteByExample(recordItemExample);
					}
				}
				//登记调查问卷记录,包括题目,试题明细记录
				TraineeExamRecord traineeExamRecord = new TraineeExamRecord();
				String examRecordId = AppUtils.uuid();
				traineeExamRecord.setId(examRecordId);
				traineeExamRecord.setCourseId(courseId);
				traineeExamRecord.setExamId(surveyId);
				traineeExamRecord.setExamType(2);
				traineeExamRecord.setStatus(status);
				traineeExamRecord.setAccountId(accountId);
				traineeExamRecord.setTraineeId(userId);
				traineeExamRecord.setCreateId(userId);
				traineeExamRecord.setCreateTime(new Date());
				traineeExamRecord.setStartTime(startTime);
				traineeExamRecord.setFinishTime(Date.from(Instant.now()));
				traineeExamRecordMapper.insert(traineeExamRecord);
				TraineeExamRecordItem traineeExamRecordItem = new TraineeExamRecordItem();
				traineeExamRecordItem.setId(AppUtils.uuid());
				List<TraineeExamRecordSubjectBo> examRecordSubjectList = new ArrayList<TraineeExamRecordSubjectBo>();
				List<TraineeExamRecordItem> examRecordSubjectItemsList = new ArrayList<TraineeExamRecordItem>();
				for (int i = 0, j = dataStr.length; i < j; i++) {
					String[] quizArray = dataStr[i].split(DConstants.DELIMITER_DATA);
					String qtype = quizArray[0];
					TraineeExamRecordSubjectBo examRecordSubject = new TraineeExamRecordSubjectBo();
					String examRecordSubjectId = AppUtils.uuid();
					examRecordSubject.setId(examRecordSubjectId);
					examRecordSubject.setExamRecordId(examRecordId);
					examRecordSubject.setSubjectName(quizArray[1]);
					examRecordSubject.setSubjectType(Integer.parseInt(quizArray[0]));
					examRecordSubject.setSubjectOrder(i);
					examRecordSubject.setScore(1);
					examRecordSubject.setCreateId(userId);
					examRecordSubject.setCreateTime(new Date());
					if (qtype.equals(String.valueOf(DConstants.NUMBER_SIX))) {
						examRecordSubject.setSelfAnswer(quizArray[2]);
					}
					examRecordSubjectList.add(examRecordSubject);
					if (!qtype.equals(String.valueOf(DConstants.NUMBER_SIX))) {
						if (!qtype.equals(String.valueOf(DConstants.NUMBER_SEVEN))) {
							String[] options = StringUtils.split(quizArray[2], DConstants.DELIMITER_ATTR);
							for (int z = 0, x = options.length; z < x; z++) {
								String[] optionsItems = StringUtils.split(options[z], DConstants.DELIMITER_CELL_DATA);
								TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
								examRecordItems.setId(AppUtils.uuid());
								examRecordItems
										.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
								examRecordItems.setItemOrder(z);
								examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
								if (String.valueOf(DConstants.NUMBER_ONE).equals(qtype)
										|| String.valueOf(DConstants.NUMBER_TWO).equals(qtype)
										|| String.valueOf(DConstants.NUMBER_THREE).equals(qtype)
										|| String.valueOf(DConstants.NUMBER_FOUR).equals(qtype)) {
									examRecordItems.setIsCorrect(Integer.parseInt(optionsItems[1]));
								}
								if (String.valueOf(DConstants.NUMBER_FIVE).equals(qtype)) {
									examRecordItems.setAttachUrl(
											optionsItems == null || optionsItems.length < 2 ? "" : optionsItems[1]);
								}
								examRecordItems.setCreateId(userId);
								examRecordItems.setCreateTime(new Date());
								examRecordSubjectItemsList.add(examRecordItems);
							}
						} else {
							String[] optionsItems = String.valueOf(quizArray[2]).split(DConstants.DELIMITER_CELL_DATA);
							TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
							examRecordItems.setId(AppUtils.uuid());
							examRecordItems
									.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
							examRecordItems.setItemOrder(0);
							examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
							examRecordItems.setCreateId(userId);
							examRecordItems.setCreateTime(new Date());
							examRecordSubjectItemsList.add(examRecordItems);
						}
					}
				}
				traineeExamCustomizeMapper.insertSubjectByBatch(examRecordSubjectList);
				traineeExamCustomizeMapper.insertItemByBatch(examRecordSubjectItemsList);
			}
	@Override
	public void submitTraineeSurveySubject(String[] dataStr, String surveyId, String courseId, String accountId,
			String userId, Date startTime,Integer status) {
		// TODO Auto-generated method stub
		//删除历史试卷记录
				TraineeExamRecordExample example = new TraineeExamRecordExample();
				example.createCriteria().andExamIdEqualTo(surveyId).andCourseIdEqualTo(courseId).andExamTypeEqualTo(2);
				List<TraineeExamRecord> listTraineeExamRecord = traineeExamRecordMapper.selectByExample(example);
				traineeExamRecordMapper.deleteByExample(example);
				if (!listTraineeExamRecord.isEmpty()) {
					List<String> recordIds = new ArrayList<String>();
					for (TraineeExamRecord rel : listTraineeExamRecord) {
						recordIds.add(rel.getId());
					}
					TraineeExamRecordSubjectExample subjectExample = new TraineeExamRecordSubjectExample();
					subjectExample.createCriteria().andExamRecordIdIn(recordIds);
					List<TraineeExamRecordSubject> listExamRecordSubject = traineeExamRecordSubjectMapper
							.selectByExample(subjectExample);
					traineeExamRecordSubjectMapper.deleteByExample(subjectExample);
					if (!listExamRecordSubject.isEmpty()) {
						List<String> subjectIds = new ArrayList<String>();
						for (TraineeExamRecordSubject rel : listExamRecordSubject) {
							subjectIds.add(rel.getId());
						}
						TraineeExamRecordItemExample recordItemExample = new TraineeExamRecordItemExample();
						recordItemExample.createCriteria().andExamRecordSubjectIdIn(subjectIds);
						traineeExamRecordItemMapper.deleteByExample(recordItemExample);
					}
				}
				//登记考试记录,包括题目,试题明细记录
				TraineeExamRecord traineeExamRecord = new TraineeExamRecord();
				String examRecordId = AppUtils.uuid();
				traineeExamRecord.setId(examRecordId);
				traineeExamRecord.setCourseId(courseId);
				traineeExamRecord.setExamId(surveyId);
				traineeExamRecord.setExamType(2);
				traineeExamRecord.setStatus(status);
				traineeExamRecord.setAccountId(accountId);
				traineeExamRecord.setTraineeId(userId);
				traineeExamRecord.setCreateId(userId);
				traineeExamRecord.setCreateTime(new Date());
				traineeExamRecord.setStartTime(startTime);
				traineeExamRecord.setFinishTime(Date.from(Instant.now()));
				traineeExamRecordMapper.insert(traineeExamRecord);
				TraineeExamRecordItem traineeExamRecordItem = new TraineeExamRecordItem();
				traineeExamRecordItem.setId(AppUtils.uuid());
				List<TraineeExamRecordSubjectBo> examRecordSubjectList = new ArrayList<TraineeExamRecordSubjectBo>();
				List<TraineeExamRecordItem> examRecordSubjectItemsList = new ArrayList<TraineeExamRecordItem>();
				for (int i = 0, j = dataStr.length; i < j; i++) {
					String[] quizArray = dataStr[i].split(DConstants.DELIMITER_DATA);
					String qtype = quizArray[0];
					TraineeExamRecordSubjectBo examRecordSubject = new TraineeExamRecordSubjectBo();
					String examRecordSubjectId = AppUtils.uuid();
					examRecordSubject.setId(examRecordSubjectId);
					examRecordSubject.setExamRecordId(examRecordId);
					examRecordSubject.setSubjectName(quizArray[1]);
					examRecordSubject.setSubjectType(Integer.parseInt(quizArray[0]));
					examRecordSubject.setScore(1);
					examRecordSubject.setCreateId(userId);
					examRecordSubject.setCreateTime(new Date());
					examRecordSubject.setSubjectOrder(i);
					examRecordSubjectList.add(examRecordSubject);
					if (!qtype.equals(String.valueOf(DConstants.NUMBER_SIX))) {
						if (!qtype.equals(String.valueOf(DConstants.NUMBER_SEVEN))) {
							String[] options = StringUtils.split(quizArray[2], DConstants.DELIMITER_ATTR);
							for (int z = 0, x = options.length; z < x; z++) {
								String[] optionsItems = StringUtils.split(options[z], DConstants.DELIMITER_CELL_DATA);
								TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
								examRecordItems.setId(AppUtils.uuid());
								examRecordItems
										.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
								examRecordItems.setItemOrder(z);
								examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
								if (String.valueOf(DConstants.NUMBER_ONE).equals(qtype)
										|| String.valueOf(DConstants.NUMBER_TWO).equals(qtype)
										|| String.valueOf(DConstants.NUMBER_THREE).equals(qtype)
										|| String.valueOf(DConstants.NUMBER_FOUR).equals(qtype)) {
									examRecordItems.setIsCorrect(Integer.parseInt(optionsItems[1]));
								}
								if (String.valueOf(DConstants.NUMBER_FIVE).equals(qtype)) {
									examRecordItems.setAttachUrl(
											optionsItems == null || optionsItems.length < 2 ? "" : optionsItems[1]);
								}
								examRecordItems.setCreateId(userId);
								examRecordItems.setCreateTime(new Date());
								examRecordSubjectItemsList.add(examRecordItems);
							}
						} else {
							String[] optionsItems = String.valueOf(quizArray[2]).split(DConstants.DELIMITER_CELL_DATA);
							TraineeExamRecordItem examRecordItems = new TraineeExamRecordItem();
							examRecordItems.setId(AppUtils.uuid());
							examRecordItems
									.setItemName(optionsItems == null || optionsItems.length <= 0 ? "" : optionsItems[0]);
							examRecordItems.setItemOrder(0);
							examRecordItems.setExamRecordSubjectId(examRecordSubjectId);
							examRecordItems.setCreateId(userId);
							examRecordItems.setCreateTime(new Date());
							examRecordSubjectItemsList.add(examRecordItems);
						}
					}
				}
				traineeExamCustomizeMapper.insertSubjectByBatch(examRecordSubjectList);
				traineeExamCustomizeMapper.insertItemByBatch(examRecordSubjectItemsList);
			}
	@Override
	public Integer getQuizExaminationCountScore(String quizId) {
		// TODO Auto-generated method stub
			Quiz quiz=quizMapper.selectByPrimaryKey(quizId);
			Integer quizScore=0;
			if(quiz.getCreateType()==DConstants.QUIZ_CREATE_TYPE_MANUAL) {//手动创建试卷
				quizScore=traineeExamCustomizeMapper.getTraineeQuizCountScore(quizId);
			}else if (quiz.getCreateType()==DConstants.QUIZ_CREATE_TYPE_AUTO) {//自动创建试卷
				if (quiz.getScoreSet()==DConstants.QUIZ_EXAMINATION_SCORE) {//指定题型分数
					//quizScore=quizCustomizeMapper.getQuizExaminationCountScore(quizId);	
				}else if (quiz.getScoreSet()==DConstants.QUIZ_BANK_SCORE) {//题库分数
					//quizScore=quizCustomizeMapper.getQuizBankCountScore(quizId);	
				}
				
			}
			return quizScore;		
	}
	@Override
	public List<Map<String, Object>> getPreviewQuizExamination(String quizId) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("quizId", quizId);
			return traineeExamCustomizeMapper.getTraineePreviewExamination(params);
	}
	/**
	 * 更新课程作业
	 * 
	 * @param courseHomework
	 * @param attachId
	 * @param userId
	 * @param accountId
	 */
	public void submitTraineeHomework(TraineeCourseHomework courseHomework, String attachId, String userId, String accountId)
			throws DeodioException
	{
		Date now = new Date();
		TraineeCourseHomeworkExample example=new TraineeCourseHomeworkExample();
		example.createCriteria().andHomeworkIdEqualTo(courseHomework.getHomeworkId()).andCourseIdEqualTo(courseHomework.getCourseId()).andTraineeIdEqualTo(userId);
		List<TraineeCourseHomework> list=traineeCourseHomeworkMapper.selectByExample(example);
		
		String id = "";
		if (list.isEmpty()) {
			id = AppUtils.uuid();
			courseHomework.setId(id);
		}else{
			id=(String)list.get(0).getId();
		}
		courseHomework.setAccountId(accountId);
		courseHomework.setTraineeId(userId);
		TraineeCourseHomework temp = traineeCourseHomeworkMapper.selectByPrimaryKey(id);
		if (temp == null) {
			courseHomework.setCreateId(userId);
			courseHomework.setCreateTime(now);
			traineeCourseHomeworkMapper.insertSelective(courseHomework);
		} else {
			courseHomework.setId(temp.getId());
			courseHomework.setUpdateId(userId);
			courseHomework.setUpdateTime(now);
			traineeCourseHomeworkMapper.updateByPrimaryKeySelective(courseHomework);
		}

		// 图片表更新
		if (StringUtils.isNotBlank(attachId)) {
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
	}

}
