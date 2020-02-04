package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.TraineeExamRecordItem;
import com.deodio.elearning.persistence.model.customize.TraineeExamRecordSubjectBo;

/**
 * @author P0113869
 */
public interface TraineeExamCustomizeMapper {
	//批量添加题目选项
	void insertItemByBatch(List<TraineeExamRecordItem> traineeExamRecordItemList);
	//批量添加题目
	void insertSubjectByBatch(List<TraineeExamRecordSubjectBo> traineeExamRecordSubjectBoList);
	//学员试卷分数
	Integer getTraineeQuizCountScore(String quizId);
	//学员提交的试卷数据
	List<Map<String, Object>> getTraineePreviewExamination(Map<String, Object> params);
}
