package com.deodio.elearning.modules.quiz.service;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.customize.TraineeExamRecordBo;
/**
 * 试卷考试
 * @author P0113869
 */
public interface IQuizExamService {
	/**
	 * 保存考试答案
	 */
	public TraineeExamRecordBo saveQuizAnswer(TraineeExamRecordBo traineeExamRecordBo,
			String[] dataRows);
	/**
	 * 获取考试试题
	 */
	public List<Map<String,Object>> getQuizExam(String quizId);
}
