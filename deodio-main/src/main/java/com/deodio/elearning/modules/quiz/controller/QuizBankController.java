package com.deodio.elearning.modules.quiz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.CommonUtils;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.quiz.service.IQuizBankService;
import com.deodio.elearning.persistence.model.QuizBanks;

@Controller
@RequestMapping("/quiz/bank")
public class QuizBankController extends BaseController {

	@Autowired
	private IQuizBankService quizBankService;

	/**
	 * 获取题库分类列表
	 */
	@RequestMapping("/get_left_ctree" + Constants.URL_SUFFIX)
	@ResponseBody
	public Object getLeftClassificationTree(@RequestParam String treeId, HttpServletRequest request) {

		return quizBankService.getCTree(getCookieUserId(request), getCookieAccount(request), treeId);
	}

	/**
	 * 题库列表
	 */
	@RequestMapping("/list" + Constants.URL_SUFFIX)
	public String toQuizsBankList() {

		return "/modules/quizs/quiz_bank_list";
	}

	/**
	 * 获取题库列表
	 */
	@RequestMapping("/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toLoadBankList(@RequestParam(required = false) String classificationId,
			@RequestParam(required = false) String keyword,
			@RequestParam(value = "bankIds[]", required = false) String[] bankIds,
			@RequestParam(required = false) Integer pageNo, HttpServletRequest request) {

		PageRequest pageRequest = new PageRequest(pageNo == null ? 1 : pageNo);

		return getAjaxResult(CommonUtils.outPrintJsonMapForPage(quizBankService.queryPage(classificationId, keyword, bankIds,
				getCookieUserId(request), getCookieAccount(request), pageRequest)), AjaxResultModel.SUCCESS);
	}

	/**
	 * 校验题库名称是否重复
	 */
	@RequestMapping("/check_name" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkBankName(String bankName, HttpServletRequest request) {

		return getAjaxResult(quizBankService.checkBankName(bankName, getCookieAccount(request)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 创建题库
	 */
	@RequestMapping("/create" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel addQuizBank(@RequestParam String quizBankName, @RequestParam String quizBankDesc,
			@RequestParam String classificationId, @RequestParam Short isPrivate, HttpServletRequest request) {

		quizBankService.create(getCookieAccount(request), getCookieUserId(request), quizBankName, quizBankDesc,
				classificationId, isPrivate);

		return getAjaxResult("", AjaxResultModel.SUCCESS);
	}

	/**
	 * 删除题库
	 */
	@RequestMapping("/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteQuizBank(@RequestParam String quizBankId) {

		return getAjaxResult(quizBankService.deleteById(quizBankId), AjaxResultModel.SUCCESS);
	}

	/**
	 * 批量删除题库
	 */
	@RequestMapping("/batchDelete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel batchDeleteQuizBank(@RequestParam String quizBankIds, HttpServletRequest request) {

		return getAjaxResult(quizBankService.batchDelete(quizBankIds, getCookieUserId(request)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 更新题库
	 */
	@RequestMapping("/update" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateQuizBank(@RequestParam String quizBankId, @RequestParam String quizBankName,
			@RequestParam String quizBankDesc, @RequestParam String classificationId, @RequestParam Short isPrivate,
			HttpServletRequest request) {

		String updateId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		quizBankService.updateSelectivityById(quizBankId, quizBankName, quizBankDesc, classificationId, isPrivate,
				updateId);

		return getAjaxResult("", AjaxResultModel.SUCCESS);
	}

	/**
	 * 删除题库中试题
	 */
	@RequestMapping("/delete_subject" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteQuizBankSubject(@RequestParam String quizSubjectId, HttpServletRequest request) {

		return getAjaxResult(quizBankService.deleteSubject(quizSubjectId, getCookieUserId(request)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 获取题库详情
	 */
	@RequestMapping("/get_list_by_id" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuizBank(@RequestParam String quizBankId) {

		return getAjaxResult(quizBankService.getById(quizBankId), AjaxResultModel.SUCCESS);
	}

	@RequestMapping("/get_quiz_banks_by_classification" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuizBanksByClassifications(@RequestParam String classificationId,
			HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		List<QuizBanks> banksList = quizBankService.getBanksByTags(classificationId);
		arm.setData(banksList);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}
}
