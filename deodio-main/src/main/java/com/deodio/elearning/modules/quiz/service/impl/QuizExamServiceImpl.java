package com.deodio.elearning.modules.quiz.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.quiz.service.IQuizExamService;
import com.deodio.elearning.modules.quiz.service.IQuizSubjectService;
import com.deodio.elearning.persistence.mapper.TraineeExamRecordMapper;
import com.deodio.elearning.persistence.mapper.customize.QuizSubjectCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.TraineeExamCustomizeMapper;
import com.deodio.elearning.persistence.model.QuizItems;
import com.deodio.elearning.persistence.model.TraineeExamRecord;
import com.deodio.elearning.persistence.model.TraineeExamRecordItem;
import com.deodio.elearning.persistence.model.customize.TraineeExamRecordBo;
import com.deodio.elearning.persistence.model.customize.TraineeExamRecordSubjectBo;

public class QuizExamServiceImpl implements IQuizExamService {

	@Autowired
	private IQuizSubjectService quizSubjectService;
	
    @Autowired
    private TraineeExamRecordMapper traineeExamRecordMapper;
	
	@Autowired
	private TraineeExamCustomizeMapper traineeExamCustomizeMapper;
	
	@Autowired
	private QuizSubjectCustomizeMapper quizSubjectCustomizeMapper;
	
	@Override
	public TraineeExamRecordBo saveQuizAnswer(TraineeExamRecordBo traineeExamRecordBo, String[] dataRows) throws DeodioException {
		//查询试卷下的题目
		List<Map<String,Object>> quizSubjectList = getQuizExam(traineeExamRecordBo.getExamId());
		//考试得分
		Integer score = 0;
		for (int i = 0; i < dataRows.length; i++) {
			Boolean scoreFlag = false;
			String[] quizArray = dataRows[i].split(DConstants.DELIMITER_DATA);
			TraineeExamRecordSubjectBo recordSubject = new TraineeExamRecordSubjectBo();
			Map<String,Object> quizSubject = quizSubjectList.get(i);
			recordSubject.setId(AppUtils.uuid());
			recordSubject.setSubjectId(quizSubject.get("id").toString());
			recordSubject.setExamRecordId(traineeExamRecordBo.getExamId());
			recordSubject.setSubjectName(quizArray[1]);
			recordSubject.setCreateId(traineeExamRecordBo.getCreateId());
			recordSubject.setCreateTime(traineeExamRecordBo.getCreateTime());
			recordSubject.setUpdateId(traineeExamRecordBo.getUpdateId());
			recordSubject.setUpdteTime(traineeExamRecordBo.getUpdteTime());
			Integer qtype = Integer.parseInt(quizArray[0]);
			recordSubject.setSubjectType(qtype);
			recordSubject.setSubjectOrder(i+1);
			recordSubject.setScore((Integer) quizSubject.get("quiz_subject_score"));
			//查询题目下的选项
			List<QuizItems> quizItemList = quizSubjectService.queryQuizItemsList(recordSubject.getSubjectId());
			StringBuffer examAnswer = new StringBuffer();
			StringBuffer selfAnswer = new StringBuffer();
			String[] options = {};
			//1、单选题，2、多选题，3、判断题，4、排序题
			if (qtype == 1 || qtype == 2 || qtype == 3 || qtype == 4) {
				options = StringUtils.split(quizArray[2], DConstants.DELIMITER_ATTR);
				for (int j=0; j<options.length; j++) {
					TraineeExamRecordItem recordItem = new TraineeExamRecordItem();
					QuizItems item = quizItemList.get(j);
					String[] optionsItems = StringUtils.split(options[j], DConstants.DELIMITER_CELL_DATA);
					recordItem.setId(AppUtils.uuid());
					recordItem.setItemId(item.getId());
					recordItem.setExamRecordSubjectId(recordSubject.getId());
					recordItem.setItemName(optionsItems[0]);
					recordItem.setItemOrder(item.getQuizItemOrder());
					recordItem.setIsCorrect(Integer.parseInt(optionsItems[1]));
					recordItem.setCreateId(recordSubject.getCreateId());
					recordItem.setCreateTime(recordSubject.getCreateTime());
					recordItem.setUpdateId(recordSubject.getUpdateId());
					recordItem.setUpdteTime(recordSubject.getUpdteTime());
					recordItem.setAttachUrl("");
					examAnswer.append(item.getIsCorrect());
					selfAnswer.append(recordItem.getIsCorrect());
					recordSubject.getItemsList().add(recordItem);
					
					if (recordItem.getIsCorrect().equals(item.getIsCorrect())) {
						scoreFlag = true;
					}
				}
				recordSubject.setExamAnswer(examAnswer.toString());
				recordSubject.setSelfAnswer(selfAnswer.toString());
				if (scoreFlag) {
					score += recordSubject.getScore();
				}
				//批量添加选项
				traineeExamCustomizeMapper.insertItemByBatch(recordSubject.getItemsList());
			//6、简单题
			} else if (qtype == 5 || qtype == 6) {
				//TODO 未找到简单题正确答案
				recordSubject.setExamAnswer(null);
				
				options = StringUtils.split(quizArray[2], DConstants.DELIMITER_ATTR);
				recordSubject.setSelfAnswer(options[0]);
			}
			traineeExamRecordBo.getSubjectList().add(recordSubject);
		}
		traineeExamRecordBo.setScore(score);
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
	
	@Override
	public List<Map<String,Object>> getQuizExam(String quizId) {

		return quizSubjectCustomizeMapper.queryQuizSubjectList(quizId);
	}
}
