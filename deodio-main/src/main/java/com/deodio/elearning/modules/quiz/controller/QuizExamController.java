package com.deodio.elearning.modules.quiz.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.quiz.service.IQuizExamService;
import com.deodio.elearning.modules.quiz.service.IQuizService;
import com.deodio.elearning.modules.survey.service.SurveyService;
import com.deodio.elearning.persistence.model.Quiz;
import com.deodio.elearning.persistence.model.customize.TraineeExamRecordBo;

@Controller
@RequestMapping("/quiz/exam")
public class QuizExamController  extends BaseController {
	
	@Autowired
	private IQuizService quizService;
	
	@Autowired
	private IQuizExamService quizExamService;
	
	@Autowired
	private SurveyService surveyService;
	
	/**
	 * 开始考试
	 */
	@RequestMapping("/start"+Constants.URL_SUFFIX)
	public String getExamination(
			Model model, 
			HttpServletRequest request,
			@RequestParam String examId,
			@RequestParam String courseId,
			@RequestParam Integer examType) {	
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		String examUrl = "";
		//调查问卷
		if (examType.equals(DConstants.TRAINEE_EXAM_TYPE_SURVEY)) {
			dataList = surveyService.getSubjectList(examId);
			examUrl = "modules/survey/survey_exam";
		//考试
		} else if (examType.equals(DConstants.TRAINEE_EXAM_TYPE_QUIZ)) {
//			dataList = quizService.getPreviewQuizExamination(examId,null);
			dataList = quizExamService.getQuizExam(examId);
			Quiz quizModel = quizService.getQuizInfoById(examId);
			LocalDateTime localDateTime = LocalDateTime.now();
			LocalDateTime finishTime = localDateTime.plusMinutes(quizModel.getFinishTime());
			ZoneId zone = ZoneId.systemDefault();
			//获取考试开始时间
			Instant startInstant = localDateTime.atZone(zone).toInstant();
			//设置考试结束时间
			Instant finishInstant = finishTime.atZone(zone).toInstant();
			model.addAttribute("startTimestamp", startInstant.toEpochMilli());
			model.addAttribute("finishTimestamp", finishInstant.toEpochMilli());
			model.addAttribute("passScore", quizModel.getPassScore());
			examUrl = "modules/quizs/quiz_exam";
		}
		model.addAttribute("dataList", dataList);
		model.addAttribute("courseId", courseId);
		model.addAttribute("groupId", (String)CookieUtils.getCookie(request, DConstants.COOKIE_GROUP_ID));
		model.addAttribute("examId", examId);
		
		return examUrl;
	}
	
	/**
	 * 提交答案
	 */
	@RequestMapping("/save"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveAnswer(
			HttpServletRequest request,
			@RequestParam String dataStr,
			@RequestParam String examId,
			@RequestParam Integer examType,
			@RequestParam String courseId,
			@RequestParam(required=false) Date startTime) {
		
		String[] dataRows = dataStr.split(DConstants.DELIMITER_ROW);
		TraineeExamRecordBo record = new TraineeExamRecordBo();
		record.setId(AppUtils.uuid());
		record.setExamId(examId);
		record.setCourseId(courseId);
		record.setExamType(examType);
		record.setStatus(DConstants.TRAINEE_EXAM_STATUS_NEWSUBMIT);
		record.setAccountId((String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID));
		record.setTraineeId((String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID));
		record.setGroupId((String)CookieUtils.getCookie(request, DConstants.COOKIE_GROUP_ID));
		record.setCreateId((String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID));
		record.setUpdateId((String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID));
		record.setCreateTime(Date.from(Instant.now()));
		record.setUpdteTime(Date.from(Instant.now()));
		//考试
		if (examType.equals(DConstants.TRAINEE_EXAM_TYPE_QUIZ)) {
			record.setStartTime(startTime);
			record.setFinishTime(Date.from(Instant.now()));
			record = quizExamService.saveQuizAnswer(record, dataRows);
		//问卷调查
		} else if (examType.equals(DConstants.TRAINEE_EXAM_TYPE_SURVEY)) {
			record = surveyService.saveSurveyAnswer(record, dataRows);
		}
		
		return getAjaxResult(record, AjaxResultModel.SUCCESS);
	}
	
	/**
	 * 跳转到标准答案页面
	 */
	@RequestMapping("/answer_detail"+Constants.URL_SUFFIX)
	public String toAnswerDetail(
			Model model,
			String quizId, 
			String courseId, 
			String score,
			HttpServletRequest request) {
		
		List<Map<String,Object>> dataList = quizService.getPreviewQuizExamination(quizId,null);
		model.addAttribute("dataList", dataList);
		model.addAttribute("quizId", quizId);
		model.addAttribute("score", score);
		model.addAttribute("courseId", courseId);
		model.addAttribute("groupId", (String)CookieUtils.getCookie(request, DConstants.COOKIE_GROUP_ID));
		
		return "modules/quizs/quiz_exam_view";
	}
}
