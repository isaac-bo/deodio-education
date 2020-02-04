package com.deodio.elearning.modules.trainee.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.CourseHomework;
import com.deodio.elearning.persistence.model.TraineeCourseHomework;

public  interface  TraineeService {
	public void saveTraineeQuizSubject(String[] dataStr, String quizId, String courseId,String accountId, String userId,Date startTime,Integer status);
	public void submitTraineeQuizSubject(String[] dataStr, String quizId, String courseId,String accountId, String userId,Date startTime,Integer status);
	public void saveTraineeSurveySubject(String[] dataStr, String surveyId, String courseId,String accountId, String userId,Date startTime,Integer status);
	public void submitTraineeSurveySubject(String[] dataStr, String surveyId, String courseId,String accountId, String userId,Date startTime,Integer status);
	public Integer getQuizExaminationCountScore(String quizId);
	public List<Map<String, Object>> getPreviewQuizExamination(String quizId);
	public void submitTraineeHomework(TraineeCourseHomework courseHomework, String attachId, String userId, String accountId);
	
}
