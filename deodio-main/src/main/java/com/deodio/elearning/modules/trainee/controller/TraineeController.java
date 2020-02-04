package com.deodio.elearning.modules.trainee.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.quiz.service.IQuizService;
import com.deodio.elearning.modules.trainee.service.TraineeService;
import com.deodio.elearning.persistence.model.TraineeCourseHomework;
import com.deodio.elearning.persistence.model.customize.QuizBo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping("/trainee")
public class TraineeController extends BaseController{
	
	@Autowired
	private IQuizService quizService;
	@Autowired
	private TraineeService  traineeService;
	/**
	 * 试卷学习
	 */
	@RequestMapping("/testQuiz" + Constants.URL_SUFFIX)
	public String toExam(@RequestParam String quizId,
			@RequestParam(value = "eFlag", required = false) String eFlag,
			@RequestParam(value = "navTabs", required = false) String navTabs, Model model) {
		Integer countScore= quizService.getQuizExaminationCountScore(quizId);
		List<Map<String, Object>> dataList = quizService.getPreviewQuizExamination(quizId, null);
		model.addAttribute("count_score", countScore);
		model.addAttribute("eFlag", eFlag);
		model.addAttribute("quizId", quizId);
		model.addAttribute("navTabs", navTabs);
		model.addAttribute("dataList", dataList);

		return "modules/trainee/trainee_test_quiz";
	}
	/**
	 * @param dataStr
	 * @param quizId
	 * @param courseId
	 * @param request
	 * @return
	 * @throws DeodioException
	 * 保存试卷
	 */
	@RequestMapping("/saveQuiz" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveTrainerQuiz(@RequestParam String dataStr, @RequestParam String quizId,@RequestParam String courseId,@RequestParam(required=false) Date startTime,@RequestParam String status,HttpServletRequest request)
					throws DeodioException {
		traineeService.saveTraineeQuizSubject(dataStr.split(DConstants.DELIMITER_ROW), quizId,courseId,
				this.getCookieAccount(request), this.getCookieUserId(request),startTime,Integer.parseInt(status));
		AjaxResultModel arm = new AjaxResultModel();
	    arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	/**
	 * @param dataStr
	 * @param quizId
	 * @param courseId
	 * @param request
	 * @return
	 * @throws DeodioException
	 * 学生提交试卷:计算分数判定是否通过 
	 */
	@RequestMapping("/submitQuiz" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitTrainerQuiz(@RequestParam String dataStr, @RequestParam String quizId,@RequestParam String courseId,@RequestParam(required=false) Date startTime,@RequestParam String status,HttpServletRequest request)
					throws DeodioException {
		traineeService.submitTraineeQuizSubject(dataStr.split(DConstants.DELIMITER_ROW), quizId,courseId,
				this.getCookieAccount(request), this.getCookieUserId(request),startTime,Integer.parseInt(status));
		AjaxResultModel arm = new AjaxResultModel();
	    arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	/**
	 * @param dataStr
	 * @param quizId
	 * @param courseId
	 * @param startTime
	 * @param request
	 * @return
	 * @throws DeodioException
	 * 保存问卷调查
	 */
	@RequestMapping("/saveSurvey" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveTrainerSurvey(@RequestParam String dataStr, @RequestParam String surveyId,@RequestParam String courseId,@RequestParam(required=false) Date startTime,@RequestParam String status,HttpServletRequest request)
					throws DeodioException {
		traineeService.saveTraineeSurveySubject(dataStr.split(DConstants.DELIMITER_ROW), surveyId,courseId,
				this.getCookieAccount(request), this.getCookieUserId(request),startTime,Integer.parseInt(status));
		AjaxResultModel arm = new AjaxResultModel();
	    arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	@RequestMapping("/submitSurvey" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitTrainerSurvey(@RequestParam String dataStr, @RequestParam String surveyId,@RequestParam String courseId,@RequestParam(required=false) Date startTime,@RequestParam String status,HttpServletRequest request)
					throws DeodioException {
		traineeService.submitTraineeSurveySubject(dataStr.split(DConstants.DELIMITER_ROW), surveyId,courseId,
				this.getCookieAccount(request), this.getCookieUserId(request),startTime,Integer.parseInt(status));
		AjaxResultModel arm = new AjaxResultModel();
	    arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	/**
	 * 试卷预览
	 */
	@RequestMapping("/preview" + Constants.URL_SUFFIX)
	public String toPreviewExam(@RequestParam String quizId,
			@RequestParam(value = "eFlag", required = false) String eFlag,
			@RequestParam(value = "navTabs", required = false) String navTabs, Model model) {
		Integer countScore= quizService.getQuizExaminationCountScore(quizId);
		List<Map<String, Object>> dataList = traineeService.getPreviewQuizExamination(quizId);
		model.addAttribute("count_score", countScore);
		model.addAttribute("eFlag", eFlag);
		model.addAttribute("quizId", quizId);
		model.addAttribute("navTabs", navTabs);
		model.addAttribute("dataList", dataList);
		return "modules/trainee/trainee_examination_preview";
	}
	/**
	 * 获取试卷信息
	 */
	@RequestMapping("/getQuizBaseInfo" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuizBaseInfo(@RequestParam String quizId) {
		AjaxResultModel arm = new AjaxResultModel();
		QuizBo quizBo=quizService.getQuizInfoById(quizId);
		Integer countScore= quizService.getQuizExaminationCountScore(quizId);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("quizBo", quizBo);
		jsonMap.put("countScore", countScore);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	@RequestMapping("/submitHomework" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitHomework(HttpServletRequest request, @RequestParam String homeworkJson,
			@RequestParam String attachId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		try {
			Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
			TraineeCourseHomework record = gson.fromJson(homeworkJson, new TypeToken<TraineeCourseHomework>() {
			}.getType());
			traineeService.submitTraineeHomework(record, attachId, userId, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
}
