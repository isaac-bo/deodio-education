package com.deodio.elearning.modules.trainers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.JsonUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.trainers.service.TrainersService;

@Controller
public class TrainersController extends BaseController {

	@Autowired
	TrainersService trainersService;

	@RequestMapping("/trainers/list" + Constants.URL_SUFFIX)
	public String toTrainersList(Model model, HttpServletRequest request) throws DeodioException {
		String userId = this.getCookieUserId(request);
		model.addAttribute("userId", userId);
		return "/modules/trainers/trainer_list";
	}

	@RequestMapping("/trainers/load_trainer_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadTrainerList(@RequestParam Integer pageNo, @RequestParam String keywords,
			@RequestParam Integer pageSize, HttpServletRequest request,@RequestParam(required=false) String trainType) throws DeodioException {
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();

		keywords = "1001";
		List<Map<String, Object>> dataList = trainersService.getTrainerList(keywords,accountId,trainType);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/trainers/load_trainer_data_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadTrainerDataList(@RequestParam Integer pageNo, @RequestParam String keywords,
			@RequestParam Integer pageSize, HttpServletRequest request) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageNo(pageNo);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		List<Map<String, Object>> dataList = trainersService.findTrainerList(pageRequest,
				this.getCookieAccount(request), keywords, this.getCookieUserId(request));
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/trainers/submit_trainer" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitTrainer(@RequestParam(required = false) String trainerId,
			@RequestParam Integer trainerType, @RequestParam Integer trainerGender, @RequestParam String trainerName,
			@RequestParam String trainerTitle, @RequestParam String trainerMobilePhone,
			@RequestParam String trainerEmail, @RequestParam Integer trainerLevel,
			@RequestParam String trainerOrganization, @RequestParam Integer isRecommended,
			@RequestParam String trainerDesc, @RequestParam String trainerPortraitUrl, HttpServletRequest request)
					throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		trainersService.saveUpdateTrainer(trainerId, trainerType, trainerGender, trainerName, trainerTitle,
				trainerMobilePhone, trainerEmail, trainerLevel, trainerOrganization, isRecommended, trainerDesc,
				trainerPortraitUrl, this.getCookieUserId(request), this.getCookieAccount(request));
		
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	@RequestMapping("/trainers/get_trainer" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getTrainer(@RequestParam(required = false) String trainerId, HttpServletRequest request)
			throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(trainersService.getTrainers(trainerId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	//预览
	@RequestMapping("/trainers/get_preview_trainer" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel previewTrainer(@RequestParam(required = false) String trainerId, HttpServletRequest request)
			throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(trainersService.getPreviewTrainers(trainerId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	//校验名字
		@RequestMapping("/trainers/getCheck_trainers" + Constants.URL_SUFFIX)
		@ResponseBody
		public AjaxResultModel getCheckTrainerName(
				@RequestParam String trainerName,
				
				HttpServletRequest request, HttpServletResponse response) {
			AjaxResultModel arm = new AjaxResultModel();	
			arm.setData(trainersService.getCheckTrainerName(trainerName));
			arm.setStatus(AjaxResultModel.SUCCESS);
			return arm;
	}

	@RequestMapping("/trainers/del_trainer" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delTrainer(@RequestParam(required = false) String trainerId, HttpServletRequest request)
			throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		trainersService.delTrainers(trainerId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/trainers/del_all_trainers" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delAllTrainers(@RequestParam(required = false) String trainerIds, HttpServletRequest request)
			throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		trainersService.delAllTrainers(trainerIds);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	@RequestMapping("/trainers/course_relate/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getTrainerCourseRelate(@RequestParam String queryJson, HttpServletRequest request)
			throws DeodioException {
		String userId = this.getCookieUserId(request);
		String accountId = this.getCookieAccount(request);
		Map<String,Object> params = JsonUtils.jsonToObject(queryJson,Map.class);
		if(null == params){
			params = new HashMap<String,Object>();
		}
		params.put("accountId", accountId);
		params.put("traineeId", userId);
		AjaxResultModel arm = new AjaxResultModel();
		List<Map<String,Object>> list = trainersService.getTrainerCourseRelate(params);
		arm.setData(list);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 获取分享的讲师列表
	 */
	@RequestMapping("/trainers/find_share_lecturer" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadLecturerList(@RequestParam(required = false) String keyWord,
			@RequestParam(required = false) String groupId, @RequestParam Integer pageNo,
		    HttpServletRequest request) {
		
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		
		return getAjaxResult(com.deodio.components.pagination.page.CommonUtils.outPrintJsonMapForPage(
				trainersService.findLecturerList(getCookieAccount(request), 
						StringUtils.isBlank(groupId)?getCookieGroupId(request):groupId, 
						getCookieUserId(request), keyWord, pageRequest)), AjaxResultModel.SUCCESS);
	}
}
