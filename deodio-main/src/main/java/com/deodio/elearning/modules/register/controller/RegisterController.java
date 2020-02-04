package com.deodio.elearning.modules.register.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.EmailUtil;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.commons.service.RegionService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.plan.service.PlanService;
import com.deodio.elearning.modules.register.service.RegisterService;
import com.deodio.elearning.modules.user.service.UserService;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AccountExample;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CompanyModel;
import com.deodio.elearning.persistence.model.CompanyModelExample;
import com.deodio.elearning.persistence.model.UserCompanyModelExt;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.UserModelExample;
import com.deodio.elearning.persistence.model.customize.UserRegisterBo;
import com.deodio.elearning.utils.CommonUtils;
import com.deodio.elearning.utils.MD5Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class RegisterController extends BaseController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private PlanService planService;

	@Autowired
	private ClassificationService classificationService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping("/signin/signin" + Constants.URL_SUFFIX)
	public String toSigninPage() {
		return "/modules/login/signin";
	}

	@RequestMapping("/signin/classification" + Constants.URL_SUFFIX)
	public String toSetClassificationPage(Model model, @RequestParam String uKeyId) {
		Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountModel.getId());
		params.put("cmoboIdentifier", DConstants.SERVICE_PLAN_CMOBO_TYPE_MAX_CLASSIFICATION_COUNT);
		Integer defaultCreateClassificationCount = planService.getCmoboSetValue(params);
		model.addAttribute("maxSelectedCount", defaultCreateClassificationCount);
		model.addAttribute("uKeyId", uKeyId);
		return "/modules/login/signin_classification";
	}

	@RequestMapping("/signin/classification/list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadClassification(HttpServletResponse response, @RequestParam String uKeyId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			CompanyModel companyModel = registerService.queryCompanyDetailByAccountId(accountModel.getId());
			List<Map<String, Object>> classificationList = classificationService
					.getDefaultTopClassification(companyModel.getCompanyIndustry());
			arm.setData(classificationList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/signin/classification/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveClassification(HttpServletResponse response, @RequestParam String uKeyId,
			@RequestParam String classificationJson)
	{
		AjaxResultModel arm = new AjaxResultModel();
		try {

			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			String accountId = accountModel.getId();

			// int classificationCount =
			// classificationService.getSelectedTopSysClassificationCount(accountId);
			// if(classificationCount > DConstants.NUMBER_ZERO){
			// throw new
			// DeodioException("register.classification.setted.err.msg");
			// }
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accountId", accountModel.getId());
			params.put("cmoboIdentifier", DConstants.SERVICE_PLAN_CMOBO_TYPE_MAX_CLASSIFICATION_COUNT);
			params.put("userId", uKeyId);

			Integer defaultCreateClassificationCount = planService.getCmoboSetValue(params);
			classificationService.insertSysClassificationsToAccount(uKeyId, accountId, classificationJson,
					DConstants.INSERT_SYS_CLASSIFICATION_FLAG_PRE_DEL_DO, defaultCreateClassificationCount);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			// e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/signin/detail" + Constants.URL_SUFFIX)
	public String toSigninDetailPage(Model model, @RequestParam String uKeyId,HttpServletRequest request) {
		// 获取国家列表
		model.addAttribute("countryList", regionService.getDicCountryModelList());
		// 获取省列表，默认显示全部省，
		model.addAttribute("provinceList", regionService.getDicProvinceModelList());
		// 获取市列表，默认显示全部市
		model.addAttribute("cityList", regionService.getDicCityModelList());
		if (uKeyId==null) {
			uKeyId=getCookieUserId(request);
		}
		// 保存用户Id
		model.addAttribute("uKeyId", uKeyId);

		// 获取用户信息
		UserModel userModel = userService.queryUserInfoById(uKeyId);
		model.addAttribute("userModel", userModel);
		// 获取头像图片信息
		Map<String, String> attachmentUserIconMap = new HashMap<String, String>();
		attachmentUserIconMap.put("relId", uKeyId);
		attachmentUserIconMap.put("generateName", userModel.getUserIcon());
		attachmentUserIconMap.put("attachType", DConstants.ATTACH_TYPE_HEADER);
		Attachment attachmentUserIcon = attachmentService.queryItemsRelAttachement(attachmentUserIconMap);
		model.addAttribute("attachmentUserIcon", attachmentUserIcon);
		// 获取身份证图片信息
		Map<String, String> attachmentUserIdCardMap = new HashMap<String, String>();
		attachmentUserIdCardMap.put("relId", uKeyId);
		attachmentUserIdCardMap.put("generateName", userModel.getIdCardSnapShot());
		attachmentUserIdCardMap.put("attachType", DConstants.ATTACH_TYPE_ID_CARD_IMG);
		Attachment attachmentUserIdCard = attachmentService.queryItemsRelAttachement(attachmentUserIdCardMap);
		model.addAttribute("attachmentUserIdCard", attachmentUserIdCard);
		// 获取公司信息
		Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
		model.addAttribute("accountModel", accountModel);
		CompanyModel companyModel = registerService.queryCompanyDetailByAccountId(accountModel.getId());
		model.addAttribute("companyModel", companyModel);
		// 获取营业执照图片信息
		Map<String, String> attachmentBusinessLicenseMap = new HashMap<String, String>();
		attachmentBusinessLicenseMap.put("relId", uKeyId);
		attachmentBusinessLicenseMap.put("generateName", companyModel.getCompanyBusinessLicenseSnapShot());
		attachmentBusinessLicenseMap.put("attachType", DConstants.ATTACH_TYPE_BUSINESS_LICENCE_IMG);
		Attachment attachmentBusinessLicense = attachmentService.queryItemsRelAttachement(attachmentBusinessLicenseMap);
		model.addAttribute("attachmentBusinessLicense", attachmentBusinessLicense);

		return "/modules/login/signin_detail";
	}

	@RequestMapping("/signin/capability" + Constants.URL_SUFFIX)
	public String toSigninCapabilityPage(Model model, @RequestParam String uKeyId) {
		// model.addAttribute("countryList",
		// regionService.getDicCountryModelList());
		model.addAttribute("uKeyId", uKeyId);
		return "/modules/login/signin_capability";
	}

	@RequestMapping("/signin/enterprise/detail" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateSigninDetail(@RequestParam String extModelJson, @RequestParam String uKeyId) {
		System.err.println(getClass().getName()+"\textModelJson:\t"+extModelJson);
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			String accountId="";
			if(null!=accountModel){
			 accountId = accountModel.getId();
			}
			registerService.updateUserSigninDetail(extModelJson, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/signin/enterprise/capability" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateSigninCapability(Model model, @RequestParam String uKeyId,
			@RequestParam String knowledgeItem, @RequestParam String capabilityItem,
			@RequestParam String capabilityPrefix, @RequestParam Integer capabilityLevel)
	{
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			String accountId = accountModel.getId();
			accountService.updateCapability(accountId, capabilityPrefix, capabilityLevel, null, null, null, uKeyId);
			accountService.updateCapabilityItem(accountId, knowledgeItem, capabilityItem, uKeyId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/signin/load_capability" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadCapability(HttpServletRequest request, @RequestParam String uKeyId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			String accountId = accountModel.getId();
			Map<String, Object> dataList = new HashMap<String, Object>();
			dataList.put("knowledge", accountService.loadCapability(accountId, DConstants.NUMBER_ONE));
			dataList.put("capability", accountService.loadCapability(accountId, DConstants.NUMBER_TWO));
			arm.setData(dataList);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 验证用户是否存在
	 * 
	 * @Title: validateUserNameExists
	 * @param userName
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/validateUserNameExists" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateUserNameExists(@RequestParam String userName,
			@RequestParam(required = false) String registerType)
	{
		AjaxResultModel arm = new AjaxResultModel();
		try {
			short registerTypeNum = StringUtils.isBlank(registerType) ? 0 : Short.parseShort(registerType);
			boolean userFlag = registerService.validateUserNameExists(userName, registerTypeNum);
			arm.setData(userFlag);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 * 验证用户是否存在
	 * 
	 * @Title: checkAccountIsValide
	 * @param userName
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkAccountIsValide" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkAccountIsValide(@RequestParam String uKeyId){
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> map=new HashMap<>();
		map.put("isValid", registerService.checkAccountIsValide(uKeyId));
		map.put("accountList", accountService.getLoginAccountList(uKeyId));
		arm.setData(map);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	/**
	 * 验证用户邮箱是否已经验证
	 * 
	 * @Title: checkUserEmailIsVerified
	 * @param userName
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkUserEmailIsVerified" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkUserEmailIsVerified(@RequestParam String email)
	{		
		AjaxResultModel arm = new AjaxResultModel();
		boolean verifyFlag=false;	
		UserModelExample example=new UserModelExample();
		if (com.deodio.core.utils.StringUtils.isNotEmpty(email)) {
			example.createCriteria().andUserMailEqualTo(email);
		}
		List<UserModel> existData=registerService.selectUserByExample(example);
		if(existData!=null&&!existData.isEmpty()) {
			UserModel userModel=existData.get(0);
			if (userModel.getIsCheckMail()==1) {
				verifyFlag=true;
			}
		}else {
			verifyFlag=false;
		}
//		Map<String, Object> userInfo=getUserInfo(email);		
//		if (userInfo != null&& !userInfo.isEmpty()) {
//			Integer is_check_mail=(Integer) userInfo.get("is_check_mail");
//			if (is_check_mail==1) {
//				verifyFlag = true;
//			}
//		}
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(verifyFlag);
		return arm;
	}
	/**
	 * 验证用户手机号是否已经验证
	 * 
	 * @Title: checkUserPhoneIsVerified
	 * @param userName
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkUserPhoneIsVerified" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkUserPhoneIsVerified(@RequestParam String phone)
	{		
		AjaxResultModel arm = new AjaxResultModel();
		boolean verifyFlag=false;	
		UserModelExample example=new UserModelExample();
		if (com.deodio.core.utils.StringUtils.isNotEmpty(phone)) {
			example.createCriteria().andMobilePhoneEqualTo(phone);
		}
		List<UserModel> existData=registerService.selectUserByExample(example);
		if(existData!=null&&!existData.isEmpty()) {
			UserModel userModel=existData.get(0);
			if (userModel.getIsCheckTel()==1) {
				verifyFlag=true;
			}
		}else {
			verifyFlag=false;
		}
//		Map<String, Object> userInfo=getUserInfo(phone);		
//		if (userInfo != null&& !userInfo.isEmpty()) {
//			Integer is_check_tel=(Integer) userInfo.get("is_check_tel");
//			if (is_check_tel==1) {
//				verifyFlag = true;
//			}
//		}
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(verifyFlag);
		return arm;
	}
	
	
	/**
	 * 获取用户信息
	 * @Title: getUserBaseInfo
	 * @param userName
	 * @return
	 * @return Map
	 */
	@RequestMapping("/signin/getUserBaseInfo" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getUserBaseInfo(String userName){
		AjaxResultModel arm=new AjaxResultModel();
		boolean isExisted=false;
		UserModel userModel=registerService.getUserModel(userName);
		if (userModel!=null) {
			isExisted=true;
		}
//		Map<String, Object> params=new HashMap<String, Object>();
//		params.put("userName", userName);
//		Map<String, Object> userInfo=registerService.getUserInfo(params);
//		if (userInfo!=null) {
//			isExisted=true;
//		}
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("isExisted", isExisted);
		data.put("userModel", userModel);
		System.err.println(getClass().getName()+"\tdata:\t"+data.toString());
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(data);
		return arm;
	}
	/**
	 * 获取用户信息
	 * @Title: selectUserByExample
	 * @param userName
	 * @return
	 * @return Map
	 */
	@RequestMapping("/signin/selectUserByExample" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel selectUserByExample(
			@RequestParam(required=false) String userName,
			@RequestParam(required=false) String userMobile,
			@RequestParam(required=false) String userEmail){
		AjaxResultModel arm=new AjaxResultModel();
		boolean isExisted=false;
		System.err.println(getClass().getName()+"\tuserName:\t"+userName
				+ "\tuserMobile:\t"+userMobile+"\tuserEmail:\t"+userEmail);
		UserModelExample example=new UserModelExample();
		if (com.deodio.core.utils.StringUtils.isNotEmpty(userName)) {
			example.createCriteria().andUserNameEqualTo(userName);
		}
		if (com.deodio.core.utils.StringUtils.isNotEmpty(userMobile)) {
			example.createCriteria().andMobilePhoneEqualTo(userMobile);
		}
		if (com.deodio.core.utils.StringUtils.isNotEmpty(userEmail)) {
			example.createCriteria().andUserMailEqualTo(userEmail);
		}
		List<UserModel> existData=registerService.selectUserByExample(example);
		Map<String,Object> data=new HashMap<String, Object>();
		if(existData==null||existData.isEmpty()){
			isExisted=false;
		}else {
			isExisted=true;
			UserModel userModel=existData.get(0);
			data.put("userInfo", userModel);
		}			
		data.put("isExisted", isExisted);
		System.err.println(getClass().getName()+"\tdata:\t"+data.toString());
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(data);
		return arm;
	}
	/**
	 * 获取用户信息
	 * @Title: getUserInfo
	 * @param userName
	 * @return
	 * @return Map
	 */
	public Map<String, Object> getUserInfo(String userName){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("userMail", userName);
		params.put("mobilePhone", userName);
		return registerService.getUserInfo(params);
	}
	/**
	 * 验证account账号是否存在
	 * @Title: checkAccountUserNameExists
	 * @param userName
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkAccountUserNameExists" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkAccountUserNameExists(@RequestParam String userName, 
			@RequestParam String registerType) {
		AjaxResultModel arm = new AjaxResultModel();
		//userFlag表示用户是否已经注册了当前输入的账号
		Map<String, Object> map = registerService.checkAccountUserNameExists(userName,registerType);
		System.err.println(getClass().getName()+"\t checkAccountUserNameExists-map :\t"+map.toString());
		arm.setData(map);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 用户注册
	 * 
	 * @Title: insertUserByRegister
	 * @param userName
	 * @param password
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/insertUserByRegister" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel insertUserByRegister(HttpServletResponse response,
			@RequestParam String registerJson) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Gson gson = new Gson();
			UserRegisterBo loginBo = gson.fromJson(registerJson, new TypeToken<UserRegisterBo>() {
			}.getType());
			String userId = "";
			System.err.println(getClass().getName()+"\t loginBo :\t "+loginBo.toString());
			UserModel userModel=registerService.getUserModel(loginBo.getUserName());
			if(userModel==null) {
				userId = registerService.insertUserAndAccountInfo(loginBo);
				loginBo.setUserId(userId);
			}
			else {
				loginBo.setCreateId(userModel.getCreateId());
				loginBo.setUserId(userModel.getId());
				registerService.insertAccountInfo(loginBo);
				registerService.updateAccountUserInfo(loginBo);				
			}
//			Map<String, Object> params = new HashMap<String, Object>();
//			if (DConstants.USE_REGISTER_TYPE_MAIL==loginBo.getRegisterType()) {
//				params.put("userMail", loginBo.getUserName());
//
//			}else if (DConstants.USE_REGISTER_TYPE_PHONE==loginBo.getRegisterType()) {
//				params.put("mobilePhone", loginBo.getUserName());
//
//			}else if (DConstants.USE_REGISTER_TYPE_COMPANY==loginBo.getRegisterType()) {
//				params.put("userName", loginBo.getUserName());
//			}		
//			Map<String, Object> userInfo = registerService.getUserInfo(params);
//			System.err.println(getClass().getName()+"\t userInfo :\t" +userInfo.toString());
//			if (userInfo == null) {
//				userId = registerService.insertUserAndAccountInfo(loginBo);
//				loginBo.setUserId(userId);
//			} else {
//				loginBo.setCreateId((String) userInfo.get("create_id"));
//				userId=(String) userInfo.get("id");
//				loginBo.setUserId(userId);
//				registerService.insertAccountInfo(loginBo);
//			}
			CookieUtils.setCookie(DConstants.COOKIE_USER_ID, userId, 604800, response,CommonUtils.COOKIE_DOMAIN);
			
			arm.setStatus(AjaxResultModel.SUCCESS);
			arm.setData(loginBo);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 检测用户输入的邮箱是否验证通过
	 * 
	 * @Title: checkEmailIsVerified
	 * @param userId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkEmailIsVerified" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkEmailIsVerified(@RequestParam String userId) {
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userId);
		// false表示邮箱未验证
		boolean isVerified = false;
		// 获取用户信息
		Map<String, Object> map = registerService.getUserInfoById(params);
		if (map != null) {
			System.err.println("map.toString():\t" + map.toString());
			Integer isCheckMail = (Integer) map.get("is_check_mail");
			if (isCheckMail != null && isCheckMail == 1) {
				isVerified = true;
			}
		}
		arm.setData(isVerified);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	/**
	 * 用户详细注册时验证邮箱
	 * 
	 * @Title: sendRegisterEmailVerify
	 * @param userEmail
	 * @param projectSrc
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/sendRegisterEmailVerify" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel sendRegisterEmailVerify(@RequestParam String userEmail,
			@RequestParam String projectSrc,@RequestParam String password, String verifyUrl,
			HttpServletRequest request) {
		System.err.println(getClass().getName()+"\tverifyUrl:\t"+verifyUrl);
		String proUrl=(request.getRequestURL().toString()).substring(0,(request.getRequestURL().toString()).indexOf(request.getRequestURI()));
		String dataUrl=registerService.sendRegisterEmailVerify(userEmail,projectSrc,password,proUrl,verifyUrl);			
		AjaxResultModel arm = new AjaxResultModel();
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(dataUrl);
		return arm;
	}
	/**
	 * 用户详细注册时验证身份证号码
	 * 
	 * @Title: checkUserIdCardCode
	 * @param userEmail
	 * @param projectSrc
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkUserIdCardCode" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkUserIdCardCode(@RequestParam String userIdCardCode,
			@RequestParam String uKeyId) {
		boolean idCardExist=registerService.checkUserIdCard(userIdCardCode,uKeyId);
		
		AjaxResultModel arm = new AjaxResultModel();
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(idCardExist);
		return arm;
	}

	/**
	 * 用户详细注册邮箱激活
	 * 
	 * @Title: userEmailVerified
	 * @param userEmail
	 * @param validateCode
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/userEmailVerified" + Constants.URL_SUFFIX)
	public String userEmailVerified(Model model, @RequestParam String email,
			@RequestParam String validateCode,HttpServletRequest request,HttpServletResponse response) {
		String userId=registerService.setUserEmailVerified(model,email,validateCode);
		if (userId!=null) {
			CookieUtils.setCookie(DConstants.COOKIE_USER_ID, userId, 604800, response,CommonUtils.COOKIE_DOMAIN);
		}
		return toSigninDetailPage(model,userId,request);
	}
	/**
	 * 用户注册邮箱激活
	 * 
	 * @Title: userRegEmailVerified
	 * @param userEmail
	 * @param validateCode
	 * @param validateCode
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/userRegEmailVerified" + Constants.URL_SUFFIX)
	public String userRegEmailVerified(Model model, @RequestParam String le,
			@RequestParam String validateCode,@RequestParam String dp,
			HttpServletRequest request,HttpServletResponse response) {
		String userId=registerService.userEmailVerified(model,le,validateCode,dp);
		if (userId!=null) {
			CookieUtils.setCookie(DConstants.COOKIE_USER_ID, userId, 604800, response,CommonUtils.COOKIE_DOMAIN);
		}		
		return "modules/login/signin";
	}
	/**
	 * 用户手机号激活
	 * 
	 * @Title: userPhoneVerified
	 * @param userId
	 * @param userMobile
	 * @param validateCode
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/userPhoneVerified" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel userPhoneVerified(Model model, @RequestParam String userId,
			@RequestParam String userMobile) {		
		UserModel userModel=new UserModel();
		userModel.setId(userId);
		userModel.setMobilePhone(userMobile);
		userModel.setIsCheckTel((short) 1);
		// 完成用户邮箱验证
		registerService.updateUserInfo(userModel);
		AjaxResultModel arm=new AjaxResultModel();
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	/**
	 * 检测企业注册信息是否存在
	 * @Title: checkCompanyRegisterCode
	 * @param companyBusinessLicenseCode
	 * @param companyBusinessLicenseCode
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkCompanyRegisterCode" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkCompanyRegisterCode(@RequestParam String companyNeedCheckCode,
			@RequestParam String type,@RequestParam String uKeyId) {		
		
		boolean isExist = registerService.checkCompanyInfoIsExist(companyNeedCheckCode,type,uKeyId);		
		AjaxResultModel arm=new AjaxResultModel();
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(isExist);
		return arm;
	}

	/**
	 * 检测企业移动电话是否存在
	 * @Title: checkUserMobile
	 * @param userMobile
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkUserMobile" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkUserMobile(@RequestParam String userMobile) {	
		UserModelExample example=new UserModelExample();
		example.createCriteria().andMobilePhoneEqualTo(userMobile);
		boolean isExist = registerService.checkUserInfoIsExist(example);		
		AjaxResultModel arm=new AjaxResultModel();
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(isExist);
		return arm;
	}
	/**
	 * 详细注册时检测电话是否存在
	 * @Title: checkUserMobile
	 * @param userMobile
	 * @param userId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/checkDetailRegisterMobile" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkDetailRegisterMobile(@RequestParam String userMobile,
			@RequestParam String userId) {	
		UserModelExample example=new UserModelExample();
		example.createCriteria().andMobilePhoneEqualTo(userMobile);
		List<UserModel> userModels= registerService.selectUserByExample(example);
		boolean isExist=false;
		boolean isSelfCheck=false;
		if (userModels.isEmpty()) {
			isExist=false;
		}else {
			UserModel userModel=userModels.get(0);
			isExist=true;
			if(userId.equals(userModel.getId())&&userModel.getIsCheckTel()==1) {
				isSelfCheck=true;
			}
					
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isExist", isExist);
		map.put("isSelfCheck", isSelfCheck);
		AjaxResultModel arm=new AjaxResultModel();
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(map);
		return arm;
	}
	/**
	 * 对密码进行加密
	 * @Title: getMd5Digest
	 * @param userMobile
	 * @param userId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/getMd5Digest" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getMd5Digest(@RequestParam String password) {
		String passwordMd5=DigestUtils.md5DigestAsHex(password.getBytes());
		System.err.println(getClass().getName()+"\t password :\t"+password
				+"\t passwordMd5 :\t"+passwordMd5);
		AjaxResultModel arm=new AjaxResultModel();
		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(passwordMd5);
		return arm;
	}

}
