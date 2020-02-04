package com.deodio.elearning.modules.quiz.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.quiz.service.IQuizSubjectService;

@Controller
@RequestMapping("/quiz/subject")
public class QuizSubjectController extends BaseController {

	@Autowired
	private IQuizSubjectService quizSubjectService;

	/**
	 * 创建试卷题目
	 */
	@RequestMapping("/create_quiz_subject" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel createQuizExamination(@RequestParam String quizBankId,
			@RequestParam(value = "quizSubjectId", required = false) String quizSubjectId,
			@RequestParam String quizData, @RequestParam String knowledge, @RequestParam Integer difficult,
			@RequestParam Integer score, @RequestParam String tags, @RequestParam String expireDate,
			@RequestParam Integer visible, HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		if (StringUtils.isEmpty(quizSubjectId)) {
			quizSubjectService.insertSingleQuizSubject(quizBankId, quizData, knowledge, difficult, score, tags,
					expireDate, visible, this.getCookieAccount(request), this.getCookieUserId(request));
		} else {
			quizSubjectService.updateSingleQuizSubject(quizBankId, quizSubjectId, quizData, knowledge, difficult, score,
					tags, expireDate, visible, this.getCookieUserId(request), this.getCookieAccount(request));
		}
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 删除题
	 */
	@RequestMapping("/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteQuizSubject(@RequestParam String quizSubjectId, HttpServletRequest request) {

		return getAjaxResult(quizSubjectService.deleteQuizSubjectByPkId(quizSubjectId), AjaxResultModel.SUCCESS);
	}

	@RequestMapping("/get_by_id" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuizSubject(@RequestParam String quizSubjectId, HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(quizSubjectService.getQuizSubject(quizSubjectId));
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 获取题目列表
	 */
	@RequestMapping("/list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toLoadSubjectList(@RequestParam(required = false) String classificationId,
			@RequestParam String keyword, @RequestParam Integer type, @RequestParam Integer level,
			@RequestParam Integer pageNo, HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		List<Map<String, Object>> dataList = quizSubjectService.getPerPageQuizsSubjectList(classificationId, keyword,
				type, level, pageRequest, request);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	@RequestMapping("/list_by_banks_id" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel findSubjectByBanksId(@RequestParam String quizId,
			@RequestParam(value = "subjectArray[]", required = false) String[] subjectArray,
			@RequestParam String subjectTile, @RequestParam Integer pageNo, @RequestParam Integer subjectLevel,
			@RequestParam Integer subjectType, @RequestParam Integer limitCount) {

		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.setPageSize(DConstants.NUMBER_SIX);
		pageRequest.getPagination().setPageSize(DConstants.NUMBER_SIX);
		List<Map<String, Object>> dataList = quizSubjectService.findSubjectByBanksId(quizId, subjectArray, subjectTile,
				pageRequest, subjectLevel, subjectType, limitCount);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);

		return arm;
	}

	/**
	 * 获取
	 */
	@RequestMapping("/get_count" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getSubjectCount(@RequestParam(value = "bankIds[]", required = false) String[] bankIds,
			HttpServletRequest request) {
		Map<String, Object> params=new HashMap<>();
		params.put("bankIds", Arrays.asList(bankIds));
		params.put("accountId", getCookieAccount(request));
		return getAjaxResult(quizSubjectService.getSubjctCount(params),
				AjaxResultModel.SUCCESS);
	}

	@RequestMapping("/detail" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getSubjectDetail(@RequestParam(value = "subjectIds[]") String[] subjectIds) {

		AjaxResultModel arm = new AjaxResultModel();
		List<Map<String, Object>> dataList = quizSubjectService.getSubjectDetail(subjectIds);
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

}
