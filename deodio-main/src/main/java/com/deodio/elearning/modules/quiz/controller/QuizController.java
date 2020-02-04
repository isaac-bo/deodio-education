package com.deodio.elearning.modules.quiz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.quiz.service.IQuizService;
import com.deodio.elearning.modules.quiz.service.IQuizSubjectService;
import com.deodio.elearning.persistence.model.Quiz;
import com.deodio.elearning.persistence.model.QuizAutoRuleSubject;
import com.deodio.elearning.persistence.model.Tags;
import com.deodio.elearning.persistence.model.customize.QuizBo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.emory.mathcs.backport.java.util.Collections;

@Controller
@RequestMapping("/quiz")
public class QuizController extends BaseController {

	@Autowired
	private IQuizService quizService;

	@Autowired
	private IQuizSubjectService quizSubjectService;

	/**
	 * 综合试卷管理
	 */
	@RequestMapping("/list" + Constants.URL_SUFFIX)
	public String toExamList() {

		return "modules/quizs/quiz_examination_list";
	}

	/**
	 * 获取考试试卷列表
	 */
	@RequestMapping("/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadExamList(@RequestParam Integer pageNo, @RequestParam String keywords,
			@RequestParam(value = "quizCategory[]") Integer[] quizCategory,
			@RequestParam(required = false) Integer isPublish, @RequestParam(required = false) Integer pageSize,
			HttpServletRequest request) {

		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}

		return getAjaxResult(
				CommonUtils.outPrintJsonMapForPage(quizService.queryPage(keywords, Arrays.asList(quizCategory),
						isPublish, getCookieAccount(request), getCookieUserId(request), pageRequest)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 试卷创建
	 */
	@RequestMapping("/profile" + Constants.URL_SUFFIX)
	public String toCreateExam(@RequestParam(value = "navTabs", required = false) String navTabs,
			@RequestParam(value = "quizId", required = false) String quizId,
			@RequestParam(value = "eFlag", required = false) String eFlag, Model model, HttpServletRequest request) {

		if (StringUtils.isNotBlank(quizId) && StringUtils.isNotBlank(eFlag)) {
			QuizBo quizModel = quizService.getQuizInfoById(quizId);
			model.addAttribute("quizModel", quizModel);
			model.addAttribute("eFlag", eFlag);
//			quizModel.setQuizOwner(getCookieUserId(request));
			quizModel.setIsEdit(DConstants.IS_EDIT);
			quizService.updateQuizByPKSelective(quizModel);
		}
		model.addAttribute("navTabs", navTabs);
		model.addAttribute("quizId", quizId);

		return "modules/quizs/quiz_examination_profile";
	}

	/**
	 * 校验试卷名称是否重复
	 */
	@RequestMapping("/check/quizname" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkQuizName(String quizName, HttpServletRequest request) {

		return getAjaxResult(quizService.checkQuizName(quizName, getCookieAccount(request)), AjaxResultModel.SUCCESS);
	}

	/**
	 * 根据试卷类型保存
	 */
	@RequestMapping("/save_profile" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toUpdateExam(@RequestParam(value = "quizId", required = false) String quizId,
			@RequestParam String quizName, @RequestParam(value = "quizDesc", required = false) String quizDesc,
			@RequestParam Integer quizType, @RequestParam String tagsJson, @RequestParam Integer finishTime,
			@RequestParam Integer passScore, @RequestParam Integer maxTimes, @RequestParam Integer finalResut,
			@RequestParam Integer publishResult, @RequestParam String quizSafes, @RequestParam String quizContent,
			@RequestParam String attachId, HttpServletRequest request, Model model) {

		quizId = quizService.insertOrUpdateQuiz(StringUtils.isBlank(quizId) ? null : quizId, quizName, quizDesc,
				quizType, tagsJson, attachId, this.getCookieUserId(request), this.getCookieGroupId(request),
				this.getCookieAccount(request));
		quizService.updateQuizRule(finishTime, passScore, maxTimes, finalResut, publishResult, quizSafes, quizContent,
				quizId);

		return getAjaxResult(quizId, AjaxResultModel.SUCCESS);
	}

	/**
	 * 手动试卷创建
	 */
	@RequestMapping("/manual/content" + Constants.URL_SUFFIX)
	public String toExaminationSetcontent(@RequestParam String quizId, @RequestParam(required = false) String eFlag,
			Model model) {

		Quiz quiz = quizService.getQuizInfoById(quizId);
		List<Map<String, Object>> dataList = quizService.getPreviewQuizExamination(quizId, null);
		quizService.updateQuizCreateType(1, quizId);
		if (!dataList.isEmpty()) {
			model.addAttribute("dataList", dataList);
		}
		model.addAttribute("eFlag", eFlag);
		model.addAttribute("quizId", quizId);
		model.addAttribute("quiz", quiz);

		return "modules/quizs/quiz_examination_manual_content";
	}

	/**
	 * 手动试卷保存
	 */
	@RequestMapping("/manual/save" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveQuizExamContent(@RequestParam String dataStr, @RequestParam String quizId,
			HttpServletRequest request) {

		quizSubjectService.saveQuizSubject(dataStr.split(DConstants.DELIMITER_ROW), quizId, DConstants.DEFAULT_BANK_ID,
				this.getCookieAccount(request), this.getCookieUserId(request));

		return getAjaxResult("", AjaxResultModel.SUCCESS);
	}

	/**
	 * 试卷预览
	 */
	@RequestMapping("/preview" + Constants.URL_SUFFIX)
	public String toPreviewExam(@RequestParam String quizId,
			@RequestParam(value = "eFlag", required = false) String eFlag,
			@RequestParam(value = "navTabs", required = false) String navTabs, Model model) {
		Integer countScore= quizService.getQuizExaminationCountScore(quizId);
		List<Map<String, Object>> dataList = quizService.getPreviewQuizExamination(quizId, null);
		model.addAttribute("count_score", countScore);
		model.addAttribute("eFlag", eFlag);
		model.addAttribute("quizId", quizId);
		model.addAttribute("navTabs", navTabs);
		model.addAttribute("dataList", dataList);

		return "modules/quizs/quiz_examination_preview";
	}

	/**
	 * 
	 */
	@RequestMapping("/view" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toView(@RequestParam String quizId) {

		return getAjaxResult(quizService.getPreviewQuizExamination(quizId, null), AjaxResultModel.SUCCESS);
	}

	/**
	 * 发布/保存试卷
	 */
	@RequestMapping("/publish" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateQuizStatus(@RequestParam String quizId,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "navTabs", required = false) String navTabs, HttpServletRequest request) {

		QuizBo quiz = quizService.getQuizInfoById(quizId);
		if (type != null && type.equals("0")) {
			quiz.setIsPublish(DConstants.TYPE_NO_PUBLISH);
		} else {
			quiz.setIsPublish(DConstants.TYPE_IS_PUBLISH);
		}
		quizService.updateQuizByPKSelective(quiz);
//		System.err.println(getClass().getName()+"\tnavTabs:\t"+navTabs);
//
//		if (StringUtils.isNotBlank(navTabs) && Integer.parseInt(navTabs) == 1) {
//			quizSubjectService.saveIsPublishQuizSubject(quizId, getCookieUserId(request));
//		}
		
		return getAjaxResult("", AjaxResultModel.SUCCESS);
	}

	/**
	 * 删除考试试卷
	 */
	@RequestMapping("/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delExamination(@RequestParam String quizId) {

		return getAjaxResult(quizService.deleteQuizByPkId(quizId), AjaxResultModel.SUCCESS);
	}

	/**
	 * 自动试卷设置出题规则
	 */
	@RequestMapping("/auto/content" + Constants.URL_SUFFIX)
	public String toAutoQuizRule(@RequestParam String quizId,
			@RequestParam(value = "eFlag", required = false) String eFlag,
			@RequestParam(value = "navTabs", required = false) String navTabs, Model model) {

		Quiz quiz = quizService.getQuizInfoById(quizId);
		if (DConstants.STRING_ONE.equals(eFlag)) {
			List<Map<String, Object>> tagList = quizService.getQuizAutoRuleBankList(quizId);
			// List<Map<String, Object>> subjectList = quizService.getQuizAutoRuleSubjectList(quizId);
			List<QuizAutoRuleSubject> subjectList = quizService.getQuizAutoRuleSubjectList(quizId);
			model.addAttribute("tagList", tagList);
			model.addAttribute("subjectList", subjectList);
		}
		quizService.updateQuizCreateType(2, quizId);
		model.addAttribute("quiz", quiz);
		model.addAttribute("eFlag", eFlag);
		model.addAttribute("quizId", quizId);
		model.addAttribute("navTabs", navTabs);

		return "modules/quizs/quiz_examination_auto_content";
	}

	/**
	 * 自动试卷，跳转到必考题页面
	 */
	@RequestMapping("/auto/required" + Constants.URL_SUFFIX)
	public String goExaminationRequired(@RequestParam String quizId, @RequestParam(required = false) String bankScope,
			@RequestParam(required = false) String subjectSets, @RequestParam(required = false) Integer scoreSet,
			@RequestParam(required = false) String eFlag, @RequestParam(required = false) String returnFlag,
			HttpServletRequest request, Model model) {

		Map<String, Object> maps = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(returnFlag)) {
			List<QuizAutoRuleSubject> quizAutoRuleSubjects = quizService.getQuizAutoRuleSubjectList(quizId);
			for (QuizAutoRuleSubject quizAutoRuleSubject : quizAutoRuleSubjects) {
				maps.put(quizAutoRuleSubject.getSubjectType().toString(),
						quizAutoRuleSubject.getSubjectItemsCount().toString());
			}
		} else {
			if (StringUtils.isNotBlank(bankScope)) {
				maps = quizService.insertAutoRule(bankScope.split(DConstants.DELIMITER_COMMA),
						subjectSets.split(DConstants.DELIMITER_COMMA), quizId, this.getCookieUserId(request), scoreSet);
			}
		}
		if (DConstants.STRING_ONE.equals(eFlag)) {
			// List<Map<String, Object>> list = quizService.getPreviewQuizExamination(quizId, 1);
			List<Map<String, Object>> list = quizSubjectService.queryAutoSubject(quizId,
					bankScope != null ? bankScope.split(DConstants.DELIMITER_COMMA) : null,
					DConstants.QUIZ_SUBJECT_IS_REQUIRED);
			for (Map<String, Object> map : list) {
				Iterator<Map.Entry<String, Object>> iter = maps.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
					if (Integer.parseInt(entry.getKey()) == (Integer) map.get("quiz_subject_type")) {
						entry.setValue(String.valueOf(Integer.parseInt((String) entry.getValue()) - 1));
					}
				}
			}
			model.addAttribute("dataList", list);
		}

		model.addAttribute("maps", maps);
		model.addAttribute("quizCategory", quizService.getQuizInfoById(quizId).getQuizCategory());
		model.addAttribute("quizId", quizId);
		model.addAttribute("bankScope", bankScope);
		model.addAttribute("eFlag", eFlag);

		return "modules/quizs/quiz_examination_auto_required";
	}

	/**
	 * 保存必考题
	 */
	@RequestMapping("/save/required" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveRequiredSubject(@RequestBody QuizBo quiz, HttpServletRequest request) {

		quiz.setIsRequired(DConstants.QUIZ_SUBJECT_IS_REQUIRED);

		return getAjaxResult(
				quizSubjectService.saveQuizSubjectRel(quiz, getCookieAccount(request), getCookieUserId(request)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 跳转到随机题页面
	 */
	@RequestMapping("/auto/random" + Constants.URL_SUFFIX)
	public String toRandomQuiz(@RequestParam String quizId,
			@RequestParam(value = "eFlag", required = false) String eFlag,
			@RequestParam(value = "subjectCountArray", required = false) String subjectCountArray, Model model) {

		List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
		if (StringUtils.isNotBlank(eFlag)) {
			list.add(quizSubjectService.queryAutoSubject(quizId, null, DConstants.QUIZ_SUBJECT_ISNOT_REQUIRED));
		} else {
			list = quizSubjectService.createRandomQuizSubject(
					quizSubjectService.queryAutoQuizRequiredSubjectIdList(quizId), quizId,
					subjectCountArray.split(DConstants.DELIMITER_COMMA));
		}

		model.addAttribute("quizId", quizId);
		model.addAttribute("list", list);

		return "modules/quizs/quiz_examination_auto_random";
	}

	/**
	 * 保存随机题
	 */
	@RequestMapping("/save/random" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveRandomSubject(@RequestBody QuizBo quiz, HttpServletRequest request) {

		quiz.setIsRequired(DConstants.QUIZ_SUBJECT_ISNOT_REQUIRED);

		return getAjaxResult(
				quizSubjectService.saveQuizSubjectRel(quiz, getCookieAccount(request), getCookieUserId(request)),
				AjaxResultModel.SUCCESS);

		// AjaxResultModel arm = new AjaxResultModel();
		// if (subjectIds != null) {
		// quizSubjectService.insertSubjectAutoPreivew(subjectIds, quizId, this.getCookieAccount(request),
		// this.getCookieUserId(request), answers);
		// }
		// arm.setData(quizId);
		// arm.setStatus(AjaxResultModel.SUCCESS);

	}

	/**
	 * TODO
	 */
	@RequestMapping("/sync/content" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuizsList(Model model, HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			arm.setData(quizService.getQuizExamList(accountId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * TODO
	 */
	@RequestMapping("/classroom/present" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel presentClassroomQuizExam(@RequestParam String quizId, HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		List<Map<String, Object>> dataList = quizService.getPreviewQuizExamination(quizId, null);
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 规则设置页
	 */
	@RequestMapping("/rules" + Constants.URL_SUFFIX)
	public String toSetQuizRule(@RequestParam String quizId,
			@RequestParam(value = "eFlag", required = false) String eFlag,
			@RequestParam(value = "navTabs", required = false) String navTabs, Model model) {

		if (DConstants.STRING_ONE.equals(eFlag)) {
			Quiz quizModel = quizService.getQuizInfoById(quizId);
			model.addAttribute("quizModel", quizModel);
		}
		model.addAttribute("eFlag", eFlag);
		model.addAttribute("quizId", quizId);
		model.addAttribute("navTabs", navTabs);

		return "modules/quizs/quiz_examination_rule";
	}

	/**
	 * 设置试卷规则
	 */
	@RequestMapping("/set_rules" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setExaminationRules(@RequestParam Integer finishTime, @RequestParam Integer passScore,
			@RequestParam Integer maxTimes, @RequestParam Integer finalResut, @RequestParam Integer publishResult,
			@RequestParam String quizSafe, @RequestParam String quizContent, @RequestParam String quizId) {

		AjaxResultModel arm = new AjaxResultModel();
		quizService.updateQuizRule(finishTime, passScore, maxTimes, finalResut, publishResult, quizSafe, quizContent,
				quizId);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	@RequestMapping("/generate_rules" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuizExaminationGenerateRules(@RequestParam String quizId) {

		AjaxResultModel arm = new AjaxResultModel();
		List<QuizAutoRuleSubject> list = quizService.getQuizAutoRuleSubjectList(quizId);
		arm.setData(list);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 
	 */
	@RequestMapping("/get_quiz.html")
	public String getQuizExamination(Model model, @RequestParam String quizId, HttpServletRequest request) {

		Quiz quiz = quizService.getQuizInfoById(quizId);
		model.addAttribute("quiz", quiz);

		return "modules/quizs/quiz_examination_create";
	}

	/**
	 * TODO
	 */
	@RequestMapping("/get_classifications" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getClassifications(HttpServletRequest request) {

		AjaxResultModel arm = new AjaxResultModel();
		List<Tags> tagList = quizService.getClassification(this.getCookieAccount(request),
				this.getCookieUserId(request));
		arm.setData(tagList);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 获取试卷被引用列表
	 */
	@RequestMapping("/quote_quiz" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getQuoteQuiz(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@RequestParam String quizId) {

		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);

		return getAjaxResult(CommonUtils.outPrintJsonMapForPage(quizService.findQuoteQuizList(quizId, pageRequest)),
				AjaxResultModel.SUCCESS);
	}

	/**
	 * 获取试卷基本信息
	 */
	@RequestMapping("/getBaseInfo" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getBaseInfo(@RequestParam String quizId, @RequestParam String quizName,
			HttpServletRequest request) {

		int num = 1;
		List<Integer> numList = new ArrayList<Integer>();
		Quiz quiz = quizService.getQuizInfoById(quizId);
		List<Quiz> quizlist = quizService.queryQuizByQuizName(quizName, getCookieAccount(request));
		if (quizlist.size() > 1) {
			String rex = "[()]+";
			for (int i = 0; i < quizlist.size(); i++) {
				String[] str = quizlist.get(i).getQuizName().split(rex);
				if (null != str && str.length > 1) {
					numList.add(Integer.parseInt(str[str.length - 1]) + 1);
				}
			}
			if (numList.size()>0) {
				Collections.sort(numList);
				num = numList.get(numList.size() - 1);
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("num", num);
		jsonMap.put("quiz", quiz);

		return getAjaxResult(jsonMap, AjaxResultModel.SUCCESS);
	}

	/**
	 * 复制综合试卷
	 */
	@RequestMapping("/copyQuiz" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel copyQuiz(@RequestParam String quizId, @RequestParam String quizName,
			HttpServletRequest request) {

		quizService.copyQuiz(quizId, quizName, getCookieUserId(request));

		return getAjaxResult("", AjaxResultModel.SUCCESS);
	}

	/**
	 * 更新试卷编辑状态
	 */
	@RequestMapping("/update_edit_state" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateEditState(@RequestParam String quizId, @RequestParam Integer isEdit,
			HttpServletRequest request) {

		Quiz quiz = new Quiz();
		quiz.setId(quizId);
		quiz.setIsEdit(isEdit);
		quiz.setUpdateId(getCookieUserId(request));
		quiz.setUpdateTime(new Date());

		return getAjaxResult(quizService.updateQuizByPKSelective(quiz), AjaxResultModel.SUCCESS);
	}

	/**
	 * 更新试卷拥有者
	 */
	@RequestMapping("/update_quiz_owner" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateQuizOwner(@RequestParam String quizId, @RequestParam String quizOwner,
			HttpServletRequest request) {

		Quiz quiz = new Quiz();
		quiz.setId(quizId);
		quiz.setQuizOwner(quizOwner);
		quiz.setIsEdit(DConstants.ISNOT_EDIT);
		quiz.setUpdateId(getCookieUserId(request));
		quiz.setUpdateTime(new Date());

		return getAjaxResult(quizService.updateQuizByPKSelective(quiz), AjaxResultModel.SUCCESS);
	}

	/**
	 * 课程内容获取测验列表
	 */
	@RequestMapping("/content_load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel contentLoadExamList(@RequestParam Integer pageNo, @RequestParam String keywords,
			@RequestParam(required = false) Integer pageSize, HttpServletRequest request,
			@RequestParam(required = false) String array) {
		List<String> contentList = null;
		try {
			Gson gson = new Gson();
			contentList = gson.fromJson(array, new TypeToken<List<String>>() {
			}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}

		return getAjaxResult(CommonUtils.outPrintJsonMapForPage(
				quizService.queryTestPage(contentList,keywords, getCookieAccount(request), getCookieUserId(request), pageRequest)),
				AjaxResultModel.SUCCESS);
	}
	//
	//// @RequestMapping("/quizs/examination_auto_preview_{quizId}"+Constants.URL_SUFFIX)
	//// public String toAutoPreviewExam(@PathVariable(value="quizId") String quizId,Model
	// model,@RequestParam(value="eFlag",required=false) String eFlag)throws DeodioException{
	////
	//// List<Map<String,Object>> list = quizService.getPreviewQuizExamination(quizId,null);
	////
	//// model.addAttribute("quizId", quizId);
	//// model.addAttribute("dataList", list);
	//// model.addAttribute("navTabs", 2);
	//// model.addAttribute("eFlag", eFlag);
	////
	//// return "modules/quizs/quiz_examination_preview";
	//// }
	//
	//
	// @RequestMapping("/quizs/examination/submit"+Constants.URL_SUFFIX)
	// @ResponseBody
	// public AjaxResultModel saveFinalQuiz(@RequestParam String quizContent,@RequestParam String quizId,
	// HttpServletRequest request)throws DeodioException{
	// AjaxResultModel arm = new AjaxResultModel();
	//
	// quizService.saveFinalQuiz(quizContent,quizId,this.getCookieUserId(request));
	// arm.setData(quizId);
	// arm.setStatus(AjaxResultModel.SUCCESS);
	// return arm;
	// }

	// @RequestMapping("/getQuizList"+Constants.URL_SUFFIX)
	// @ResponseBody
	// public AjaxResultModel getQuizList(HttpServletRequest request,@RequestParam(required=false) Integer pageNo)
	// throws DeodioException{
	// AjaxResultModel arm = new AjaxResultModel();
	// Map<String,Object> params=new HashMap<String, Object>();
	// PageRequest pageRequest = new PageRequest(pageNo);
	// pageRequest.getPagination().setPageSize(DConstants.NUMBER_FIVE);
	// params.put("pagination", pageRequest);
	// List<Map<String, Object>> quizList=quizService.findQuizList(params);
	// Map<String, Object> jsonMap = new HashMap<String, Object>();
	// jsonMap.put("currePage", pageNo);
	// jsonMap.put("totalPage", pageRequest.getPageTotal());
	// jsonMap.put("dataList", quizList);
	// arm.setData(jsonMap);
	// arm.setStatus(AjaxResultModel.SUCCESS);
	// return arm;
	// }
	
	/**
	 * 取消分享
	 *  
	 * @param id
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/cancel_shared" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel cancelShared(@RequestParam String id,HttpServletRequest request) {
		Quiz quiz = quizService.getQuizInfoById(id);	
		quiz.setQuizOwner(quiz.getCreateId());
		quiz.setUpdateId(getCookieUserId(request));
		quiz.setUpdateTime(new Date());
		return getAjaxResult(quizService.updateQuizByPKSelective(quiz), AjaxResultModel.SUCCESS);
	}
}
