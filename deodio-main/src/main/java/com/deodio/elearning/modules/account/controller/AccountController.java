package com.deodio.elearning.modules.account.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.components.thread.EmailThread;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.service.MailService;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.commons.service.RegionService;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.commons.service.VelocityService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.course.service.CourseService;
import com.deodio.elearning.modules.group.service.GroupService;
import com.deodio.elearning.modules.quiz.service.IQuizService;
import com.deodio.elearning.modules.survey.service.SurveyService;
import com.deodio.elearning.modules.user.service.UserService;
import com.deodio.elearning.persistence.mapper.AccountMapper;
import com.deodio.elearning.persistence.mapper.CompanyModelMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.UserCustomizeMapper;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AccountAttachment;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CompanyModel;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.UserModelExt;
import com.deodio.elearning.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private UserService userService;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private GroupService groupService;

	@Autowired
	private IQuizService quizService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private MailService mailSenderService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private VelocityService velocityService;

	@RequestMapping("/account/list" + Constants.URL_SUFFIX)
	public String toAccountListPage() {
		return "/modules/account/account_list";
	}

	@RequestMapping("/account/load_login_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getLoginAccountList(@RequestParam String userId) {
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> map = accountService.getLoginAccountList(userId);
		arm.setData(map);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取当前用户归属的Acccount
	 * 
	 * @Title: getAccountList
	 * @param pageNo
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getAccountList(@RequestParam Integer pageNo, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String acId = accountService.getAccountId(userId, request);
			if (StringUtils.isNotEmpty(acId)) {// account用户有小组管理员的权限
				CookieUtils.setCookie(DConstants.ROLE_CONTENT_ACCOUNT_ID, DConstants.ROLE_CONTENT_ACCOUNT_ID, 604800,
						response, CommonUtils.COOKIE_DOMAIN);
			}
			List<Map<String, Object>> mapList = accountService.getAccountList(userId);
			Account account=accountService.getAccountInfoByOwnerId(userId);
			if (account!=null) {
				if (account.getIsValid()==DConstants.ACCOUNT_STATUS_APPROVED_SHORT) {//审核通过
					CookieUtils.setCookie(DConstants.ACCOUNT_STATUS, DConstants.ACCOUNT_STATUS_APPROVED_STRING, 604800, response,CommonUtils.COOKIE_DOMAIN);
				}else {//审核未通过
					CookieUtils.setCookie(DConstants.ACCOUNT_STATUS, DConstants.ACCOUNT_STATUS_APPROVING_STRING, 604800, response,CommonUtils.COOKIE_DOMAIN);
				}
				account.getIsValid();
			}
			System.err.println(getClass().getName()+"\t mapList:\t"+mapList.toString());
			List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
			List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> maps : mapList) {
				tempList.add(maps);
				if (DConstants.NUMBER_TWO == tempList.size()) {
					list.add(tempList);
					tempList = new ArrayList<Map<String, Object>>();
				}
			}

			if (!tempList.isEmpty()) {
				list.add(tempList);
			}
			System.err.println(getClass().getName() + "\tlist:\t" + list.toString());
			arm.setData(list);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 获取 个人基础信息
	 * 
	 * @Title: toAccountInfoPage
	 * @param uKeyId
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/profile" + Constants.URL_SUFFIX)
	public String toAccountInfoPage(@RequestParam(required = false) String accountId, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		String resultPage = "redirect:/account/layer.html";

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String acId = "";
		if (StringUtils.isNotBlank(accountId)) {
			acId = accountId;
		} else {
			acId = accountService.getAccountId(userId, request);
		}
		CookieUtils.setCookie(DConstants.COOKIE_ACCOUNT_ID, acId, 604800, response, CommonUtils.COOKIE_DOMAIN);

		String pageToInfo = (String) CookieUtils.getCookie(request, DConstants.USER_CHANGE_ACCOUNT_TO);
		System.err.println(getClass().getName() + "\tpageToInfo:\t" + pageToInfo);

		if (pageToInfo.equals(DConstants.USER_CHANGE_ACCOUNT_TO_SELF)) {
			resultPage = "redirect:/user/profile.html?accountId=" + acId;
		} else {

			model.addAttribute("userModel", accountService.getUserInfoByUserId(userId));
			model.addAttribute("uKeyId", userId);
			model.addAttribute("navTab", DConstants.NUMBER_ONE);

			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("accountId", acId);
			paramsMap.put("userId", userId);

			// 查询指定用户当前account下是否有唯一小组,有则调整该小组默认数据页。
			Map<String, Object> resultMap = accountService.queryUserUniqueGroupAndCourseInfo(paramsMap);
			if (null != resultMap && !resultMap.isEmpty()) {
				System.err.println(getClass().getName() + "\tresultMap:\t" + resultMap.toString());

				String groupRoleId = (String) resultMap.get("group_role_id");
				if (DConstants.GROUP_ROLE_GROUP_LEADER_ID.equals(groupRoleId)) {

				} else if (DConstants.GROUP_ROLE_GROUP_LEADER_ID.equals(groupRoleId)) {
					resultPage = "redirect:/course/shortcut.html";
				} else if (DConstants.GROUP_ROLE_VIEWER_ID.equals(groupRoleId)) {
					resultPage = "redirect:/course/shortcut.html";
				}
			} else {
				System.err.println(getClass().getName() + "\t209============acId:\t" + acId);
				if (StringUtils.isNotEmpty(acId)) {// account用户有小组管理员的权限
					CookieUtils.setCookie(DConstants.COOKIE_GROUP_ROLE_ID, DConstants.GROUP_ROLE_GROUP_LEADER_ID,
							604800, response, CommonUtils.COOKIE_DOMAIN);
					resultPage = "redirect:/group/profile.html";

				}
			}
		}
		return resultPage;
	}

	// /**
	// * 我的账号企业营业执照和运营者身份相关信息
	// *
	// * @Title: getUserModel
	// * @param accountId
	// * @param userId
	// * @param model
	// * @return
	// * @return String
	// */
	// private void getUserModel(Model model, String accountId, String userId,
	// CompanyModel companyModel) {
	// // 获取用户信息
	// UserModel userModel = userService.queryUserInfoById(userId);
	// model.addAttribute("userModel", userModel);
	// // 获取身份证图片信息
	// Map<String, String> attachmentUserIdCardMap = new HashMap<String, String>();
	// attachmentUserIdCardMap.put("relId", userId);
	// attachmentUserIdCardMap.put("generateName", userModel.getIdCardSnapShot());
	// attachmentUserIdCardMap.put("attachType",
	// DConstants.ATTACH_TYPE_ID_CARD_IMG);
	// Attachment attachmentUserIdCard =
	// attachmentService.queryItemsRelAttachement(attachmentUserIdCardMap);
	// model.addAttribute("attachmentUserIdCard", attachmentUserIdCard);
	// // 获取营业执照图片信息
	// Map<String, String> attachmentBusinessLicenseMap = new HashMap<String,
	// String>();
	// attachmentBusinessLicenseMap.put("relId", companyModel.getId());
	// attachmentBusinessLicenseMap.put("generateName",
	// companyModel.getCompanyBusinessLicenseSnapShot());
	// attachmentBusinessLicenseMap.put("attachType",
	// DConstants.ATTACH_TYPE_BUSINESS_LICENCE_IMG);
	// Attachment attachmentBusinessLicense =
	// attachmentService.queryItemsRelAttachement(attachmentBusinessLicenseMap);
	// model.addAttribute("attachmentBusinessLicense", attachmentBusinessLicense);
	// }

	/**
	 * 修改我的账号（个人account）
	 * 
	 * @Title: updateUserInformation
	 * @param uKeyId
	 * @param extModelJson
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_information_profile" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateUserInformation(@RequestParam String extModelJson, @RequestParam String uKeyId,
			@RequestParam String orginialIdCardSnapShot, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			// String accountId =
			// accountService.getAccountIdFromUserAccountRel(uKeyId);
			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			String accountId = "";
			if (accountModel != null) {
				accountId = accountModel.getId();
			}
			String valCode = "123456";
			accountService.updateUserInfoById(extModelJson, uKeyId, accountId, valCode, orginialIdCardSnapShot);
			resetNickname(extModelJson, response);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}
	/**
	 * 修改个人中心信息(非account)
	 * 
	 * @Title: updateUserInformationNoAccount
	 * @param uKeyId
	 * @param extModelJson
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_information_profile_noaccount" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateUserInformationNoAccount(@RequestParam String extModelJson, @RequestParam String uKeyId,
			HttpServletRequest request, HttpServletResponse response) {
		
		AjaxResultModel arm = new AjaxResultModel();		
		try {
			accountService.updateUserInfoNoAccountById(extModelJson, uKeyId);
			resetNickname(extModelJson, response);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	}

	private void resetNickname(String extModelJson, HttpServletResponse response) throws UnsupportedEncodingException {
		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		;
		UserModelExt extModel = gson.fromJson(extModelJson, new TypeToken<UserModelExt>() {
		}.getType());
		CookieUtils.setCookie(DConstants.COOKIE_NICK_NAME, URLEncoder.encode(extModel.getNickName(), "UTF-8"), 604800,
				response, CommonUtils.COOKIE_DOMAIN);
	}

	/**
	 * 修改 我的账号（企业account）
	 * 
	 * @param uKeyId
	 * @param extModelJson
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_organization_profile" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateUserGroupInfo(@RequestParam String extModelJson, @RequestParam String uKeyId,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			String accountId = accountModel.getId();
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			accountService.updateUserGroupInfo(extModelJson, accountId, userId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 企业邮箱认证发送邮件
	 * 
	 * @param uKeyId
	 * @param extModelJson
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/checkCompanyEmail" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkCompanyEmail(@RequestParam String mail, @RequestParam String uKeyId,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			Account accountModel = accountService.getAccountInfoByOwnerId(uKeyId);
			String accountId = accountModel.getId();

			Map<String, Object> contentMaps = new HashMap<String, Object>();
			contentMaps.put("data.server", CommonUtils.CHECK_MAIL_SERVER_URL);
			contentMaps.put("data.content", "企业邮箱认证");
			contentMaps.put("data.accountid", accountId);
			String content = velocityService.getVelocityTemplate(10009, contentMaps);

			EmailThread emailThread = new EmailThread(mail, content, mailSenderService, "[deodio-邮件]:企业邮箱认证", null);
			taskExecutor.execute(emailThread);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 企业邮箱认证
	 * 
	 * @param uKeyId
	 * @param extModelJson
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/check_mail" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel check_mail(@RequestParam String accountId, @RequestParam Short cm,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			accountService.updateCheckMailAndTel(accountId, cm, "mail");
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 基本信息设置
	 * 
	 * @Title: toAccountSetRealm
	 * @param uKeyId
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/setting" + Constants.URL_SUFFIX)
	public String toAccountSetRealm(Model model, HttpServletRequest request) {
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("account", account);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("layout", DConstants.NUMBER_ZERO);
		model.addAttribute("navTab", DConstants.NUMBER_THREE);
		return "/modules/account/account_domain";
	}

	/**
	 * 背景设置
	 * 
	 * @Title: toAccountSetBackgroud
	 * @param uKeyId
	 * @param layout
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/style" + Constants.URL_SUFFIX)
	public String toAccountSetBackgroud(@RequestParam(required = false) String layout, Model model,
			HttpServletRequest request) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("accountModel", account);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("layout", layout);
		model.addAttribute("navTab", DConstants.NUMBER_THREE);

		return "/modules/account/account_style";
	}

	/**
	 * 布局
	 * 
	 * @Title: toAccountSetBackgroud
	 * @param uKeyId
	 * @param layout
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/layout" + Constants.URL_SUFFIX)
	public String toAccountSetLayout(Model model, HttpServletRequest request) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("accountModel", account);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("layout", DConstants.NUMBER_FIVE);
		model.addAttribute("navTab", DConstants.NUMBER_THREE);

		return "/modules/account/account_layout";
	}

	/**
	 * 布局
	 * 
	 * @Title: toAccountSetBackgroud
	 * @param uKeyId
	 * @param layout
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/theme" + Constants.URL_SUFFIX)
	public String toAccountSetTheme(Model model, HttpServletRequest request) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("accountModel", account);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("layout", DConstants.NUMBER_SIX);
		model.addAttribute("navTab", DConstants.NUMBER_THREE);

		return "/modules/account/account_theme";
	}

	/**
	 * 查询二级域名
	 * 
	 * @Title: getValidateHostExists
	 * @param subdomain
	 * @return
	 * @return boolean
	 */
	@RequestMapping("/account/query_domain" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryAccountSubdomain(HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			String subdomain = accountService.querySubdomain(accountId);
			arm.setData(subdomain);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 二级域名是否重复
	 * 
	 * @Title: getValidateHostExists
	 * @param subdomain
	 * @return
	 * @return boolean
	 */
	@RequestMapping("/account/validate/domain_exist" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateHostExists(HttpServletRequest request, @RequestParam String subdomain) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			boolean flag = accountService.getValidateHostExists(subdomain, accountId);
			arm.setData(flag);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 身份证号是否重复
	 * 
	 * @Title: validateIdCardExists
	 * @param idCardCode
	 * @return
	 * @return boolean
	 */
	@RequestMapping("/account/validate/idCard_exist" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateIdCardExists(HttpServletRequest request, @RequestParam String idCardCode) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			boolean flag = accountService.getValidateIdCardCodeExists(idCardCode, userId);
			arm.setData(flag);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 修改二级域名
	 * 
	 * @Title: udpateSubdoMain
	 * @param subdomain
	 * @param uKeyId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_domain" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel udpateSubdomain(HttpServletRequest request, @RequestParam String subdomain,
			@RequestParam String uKeyId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			int rowCount = accountService.udpateSubdomain(subdomain, uKeyId, accountId);
			if (rowCount == DConstants.NUMBER_ZERO) {
				arm.setStatus(AjaxResultModel.FAIL);
			} else {
				arm.setStatus(AjaxResultModel.SUCCESS);
			}
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * log 图片裁剪
	 * 
	 * @Title: saveLogImg
	 * @param attachId
	 * @param xone
	 * @param yone
	 * @param xtwo
	 * @param ytwo
	 * @param logWidth
	 * @param logHeight
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_style_logo" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel saveLogImg(@RequestParam String attachDir, @RequestParam String attachUrl,
			@RequestParam Integer x, @RequestParam Integer y, @RequestParam Integer logWidth,
			@RequestParam Integer logHeight, @RequestParam Integer setType, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String srcImg = StringUtils.EMPTY;
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			if (!StringUtils.isBlank(attachDir)) {
				String serverDir = CommonUtils.IMGS_LOCAL_DIR;
				srcImg = serverDir + attachDir;
				FileUtils.thumbnailsImgByRegion(srcImg, x, y, logWidth, logHeight, srcImg);
			}
			accountService.updateAccountImg(attachDir, attachUrl, accountId, setType);

			arm.setData(srcImg);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		} catch (IOException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 删除背景log等图片设置
	 * 
	 * @Title: deleteAccountSetImg
	 * @param attachDir
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/delete_style_img" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteAccountSetImg(@RequestParam String attachDir) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			if (StringUtils.isNotBlank(attachDir)) {
				uploadService.deleteAttachByAttachDir(attachDir);
			}
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 修改背景设定URL
	 * 
	 * @Title: updateBackGroudUrl
	 * @param url
	 * @param type
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_style_url" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateBackgroundUrl(@RequestParam String url, @RequestParam int type,
			@RequestParam String uKeyId, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {

			/**
			 * 如果 cookie 为空，根据 userID 获取当前登录者自己的accountId
			 */
			String accountId = accountService.getAccountId(uKeyId, request);
			accountService.updateBackgroundUrl(url, type, accountId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 修改背景设定URL
	 * 
	 * @Title: updateBackGroudUrl
	 * @param url
	 * @param type
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_layout" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setLayout(@RequestParam Integer layval, @RequestParam String uKeyId,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {

			/**
			 * 如果 cookie 为空，根据 userID 获取当前登录者自己的accountId
			 */
			String accountId = accountService.getAccountId(uKeyId, request);
			accountService.updateSetLayout(layval, accountId);

			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 修改背景设定URL
	 * 
	 * @Title: updateBackGroudUrl
	 * @param url
	 * @param type
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/account/update_theme" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setTheme(@RequestParam Integer theme, @RequestParam String uKeyId,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {

			/**
			 * 如果 cookie 为空，根据 userID 获取当前登录者自己的accountId
			 */
			String accountId = accountService.getAccountId(uKeyId, request);
			accountService.updateTheme(theme, accountId);

			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 我的活动
	 * 
	 * @Title: toActivity
	 * @param uKeyId
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/activity" + Constants.URL_SUFFIX)
	public String toActivity(Model model, HttpServletRequest request) {
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("accountModel", account);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("navTab", DConstants.NUMBER_FIVE);
		return "modules/account/account_activity";
	}

	/**
	 * 我的付款
	 * 
	 * @Title: toPayPal
	 * @param uKeyId
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/payment" + Constants.URL_SUFFIX)
	public String toPayPal(Model model, HttpServletRequest request) {
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("accountModel", account);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("navTab", DConstants.NUMBER_SIX);
		return "modules/account/account_payment";
	}

	/**
	 * 
	 * 支付步骤页面跳转
	 * 
	 * @Title: toPayPalStep
	 * @param uKeyId
	 * @param step
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/payment/subscribe" + Constants.URL_SUFFIX)
	public String toPayPalStep(@RequestParam String userId, @RequestParam int step, Model model,
			HttpServletRequest request) {

		model.addAttribute("uKeyId", userId);
		model.addAttribute("step", step);
		model.addAttribute("navTab", DConstants.NUMBER_SIX);
		return "modules/account/account_payment_subscribe";
	}

	@RequestMapping("/account/capability" + Constants.URL_SUFFIX)
	public String toAccountCapability(Model model, HttpServletRequest request) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("accountModel", account);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("navTab", DConstants.NUMBER_FOUR);
		return "modules/account/account_capability";
	}

	@RequestMapping("/account/update_capability" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateCapability(@RequestParam String capabilityPrefix,
			@RequestParam Integer capabilityLevel, @RequestParam Short capabilityLevelDistribution,
			@RequestParam Short capabilityCalScore, @RequestParam Integer capabilityCustomizeScore,
			@RequestParam String knowledgeItem, @RequestParam String capabilityItem, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			accountService.updateCapability(accountId, capabilityPrefix, capabilityLevel, capabilityLevelDistribution,
					capabilityCalScore, capabilityCustomizeScore, userId);
			accountService.updateCapabilityItem(accountId, knowledgeItem, capabilityItem, userId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/account/load_capability" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadCapability(HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
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
	 * 获取个人中心我的组织信息
	 * 
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/account/layer" + Constants.URL_SUFFIX)
	public String toAccountLayer(Model model, HttpServletRequest request) {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		model.addAttribute("accountModel", account);
		AccountAttachment accountAttachment = attachmentService.queryAccountAttachementByAccountId(accountId);
		model.addAttribute("accountAttachment", accountAttachment);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("navTab", DConstants.NUMBER_FOUR);
		CompanyModel companyModel = accountService.getCompanyModelByUserId(userId);
		model.addAttribute("userCompany", companyModel);

		if (companyModel != null) {

			// getUserModel(model, accountId, userId, companyModel);

			// 获取用户信息
			UserModel userModel = userService.queryUserInfoById(userId);
			model.addAttribute("userModel", userModel);
			// 获取身份证图片信息
			Map<String, String> attachmentUserIdCardMap = new HashMap<String, String>();
			attachmentUserIdCardMap.put("relId", userId);
			attachmentUserIdCardMap.put("generateName", userModel.getIdCardSnapShot());
			attachmentUserIdCardMap.put("attachType", DConstants.ATTACH_TYPE_ID_CARD_IMG);
			Attachment attachmentUserIdCard = attachmentService.queryItemsRelAttachement(attachmentUserIdCardMap);
			model.addAttribute("attachmentUserIdCard", attachmentUserIdCard);
			// 获取营业执照图片信息
			Map<String, String> attachmentBusinessLicenseMap = new HashMap<String, String>();
			attachmentBusinessLicenseMap.put("relId", userId);
			attachmentBusinessLicenseMap.put("generateName", companyModel.getCompanyBusinessLicenseSnapShot());
			attachmentBusinessLicenseMap.put("attachType", DConstants.ATTACH_TYPE_BUSINESS_LICENCE_IMG);
			Attachment attachmentBusinessLicense = attachmentService
					.queryItemsRelAttachement(attachmentBusinessLicenseMap);
			model.addAttribute("attachmentBusinessLicense", attachmentBusinessLicense);

			setCompanyAddressInfo(model, companyModel);

		}

		UserModel userModel = accountService.getUserInfoByUserId(userId);
		// 查询用户个人图片信息
		if (userModel != null) {
			Map<String, String> attachmentUserIconMap = new HashMap<String, String>();
			attachmentUserIconMap.put("relId", userModel.getId());
			attachmentUserIconMap.put("generateName", userModel.getUserIcon());
			attachmentUserIconMap.put("attachType", DConstants.ATTACH_TYPE_HEADER);
			Attachment attachmentUserIcon = attachmentService.queryItemsRelAttachement(attachmentUserIconMap);
			String userIcon = StringUtils.EMPTY;
			String attachUrl = attachmentUserIcon.getAttachUrl();
			String generateName = attachmentUserIcon.getGenerateName();
			if (StringUtils.isNotBlank(attachUrl) && StringUtils.isNotBlank(generateName)) {
				userIcon = attachmentUserIcon.getAttachUrl() + attachmentUserIcon.getGenerateName();
			}
			/*
			 * model.addAttribute("userIcon", userIcon); model.addAttribute("isCertified",
			 * userModel.getIsCertified());
			 */

			if (userModel.getAddressCountry() != null) {
				Integer countryId = userModel.getAddressCountry();
				model.addAttribute("countryVal", countryId);
				model.addAttribute("provinceList", regionService.getDicProvinceModelList(countryId));
			}
			if (userModel.getAddressProvince() != null) {
				Integer provinceId = userModel.getAddressProvince();
				model.addAttribute("provinceVal", provinceId);
				model.addAttribute("cityVal", userModel.getAddressCity());
				model.addAttribute("cityList", regionService.getDicCityModelList(provinceId));
			}

			userModel.setUserIcon(userIcon);
			model.addAttribute("userModel", userModel);
		}
		model.addAttribute("navTab", DConstants.NUMBER_TWO);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("accountDomain", accountService.querySubdomain(accountId));
		model.addAttribute("countryList", regionService.getDicCountryModelList());

		return "modules/account/account_layer";
	}

	private void setCompanyAddressInfo(Model model, CompanyModel companyModel) {
		if (companyModel.getCompanyAddressCountry() != null) {
			Integer countryId = companyModel.getCompanyAddressCountry();
			model.addAttribute("countryVal", countryId);
			model.addAttribute("provinceList", regionService.getDicProvinceModelList(countryId));
		}
		if (companyModel.getCompanyAddressProvince() != null) {
			Integer provinceId = companyModel.getCompanyAddressProvince();
			model.addAttribute("provinceVal", provinceId);
			model.addAttribute("cityVal", companyModel.getCompanyAddressCity());
			model.addAttribute("cityList", regionService.getDicCityModelList(provinceId));
		}
		if (companyModel.getCompanyBusinessLicenseCountry() != null) {
			Integer countryId = companyModel.getCompanyBusinessLicenseCountry();
			model.addAttribute("countryLicenseVal", countryId);
			model.addAttribute("provinceLicenseList", regionService.getDicProvinceModelList(countryId));
		}
		if (companyModel.getCompanyBusinessLicenseProvince() != null) {
			Integer provinceId = companyModel.getCompanyBusinessLicenseProvince();
			model.addAttribute("provinceLicenseVal", provinceId);
			model.addAttribute("cityLicenseVal", companyModel.getCompanyBusinessLicenseCity());
			model.addAttribute("cityLicenseList", regionService.getDicCityModelList(provinceId));
		}
	}

	@RequestMapping("/account/activity/load_offline_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadOfflineCourse(HttpServletRequest request, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) String keywords) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		PageRequest pageRequest = new PageRequest(pageNo);
		params.put("pagination", pageRequest);
		params.put("accountId", accountId);
		params.put("keywords", keywords);
		List<Map<String, Object>> onlineCourseList = courseService.getOfflineCourseList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		jsonMap.put("dataList", onlineCourseList);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/account/activity/load_online_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadOnlineCourse(HttpServletRequest request, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) String keywords) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		PageRequest pageRequest = new PageRequest(pageNo);
		params.put("pagination", pageRequest);
		params.put("accountId", accountId);
		params.put("keywords", keywords);
		List<Map<String, Object>> onlineCourseList = courseService.getOnlineCourseList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		jsonMap.put("dataList", onlineCourseList);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/account/activity/load_quizs" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadQuizs(HttpServletRequest request, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) String keywords) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		PageRequest pageRequest = new PageRequest(pageNo);
		params.put("pagination", pageRequest);
		params.put("accountId", accountId);
		params.put("keywords", keywords);
		List<Map<String, Object>> quizList = quizService.findQuizList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		jsonMap.put("dataList", quizList);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/account/activity/load_survey" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadSurvey(HttpServletRequest request, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) String keywords) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		PageRequest pageRequest = new PageRequest(pageNo);
		params.put("pagination", pageRequest);
		params.put("accountId", accountId);
		params.put("keywords", keywords);
		List<Map<String, Object>> surveyList = surveyService.findSurveyList(params);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		jsonMap.put("dataList", surveyList);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 检测用户昵称是否存在
	 * 
	 * @param request
	 * @param nickName
	 * @return
	 */
	@RequestMapping("/account/isExistNickName" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateNickName(HttpServletRequest request, @RequestParam String nickName,
			@RequestParam String userId) {
		AjaxResultModel arm = new AjaxResultModel();
		boolean userFlag = accountService.getUserNickNamelList(nickName, userId);
		arm.setData(userFlag);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/account/load_style_log" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadAccountStyleImg(@RequestParam Integer setType, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("setType", setType);
			paramsMap.put("accountId", accountId);
			Map<String, Object> resMap = accountService.queryAccountUrl(paramsMap);
			arm.setData(resMap);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 布局
	 * 
	 * @Title: queryAccountSetLayout
	 */
	@RequestMapping("/account/load_layout" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryAccountSetLayout(Model model, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			// String userId = (String)CookieUtils.getCookie(request,
			// DConstants.COOKIE_USER_ID);
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Account account = accountMapper.selectByPrimaryKey(accountId);
			arm.setData(account.getLayout());
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 主题
	 * 
	 * @Title: queryAccountSetTheme
	 */
	@RequestMapping("/account/load_theme" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryAccountSetTheme(Model model, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Account account = accountMapper.selectByPrimaryKey(accountId);
			arm.setData(account.getTheme());
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/account/load_member_detail" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryAccountMemberDetail(Model model, @RequestParam String userId,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accountId", accountId);
			map.put("userId", userId);

			List<Map<String, Object>> userList = accountService.getAccountMemberDetail(map);
			arm.setData(userList.get(0));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/account/load_members" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryAccountMembers(Model model, @RequestParam Integer pageNo, @RequestParam String keywords,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			// Integer pageSize = 10;
			// 总条数
			PageRequest pageRequest = new PageRequest(pageNo);
			// if (pageSize != null && pageSize.compareTo(0) > 0) {
			// pageRequest.setPageSize(pageSize);
			// pageRequest.getPagination().setPageSize(pageSize);
			// }

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keywords", keywords);
			map.put("accountId", accountId);
			map.put("pagination", pageRequest);

			List<Map<String, Object>> userList = accountService.getAccountMembers(map);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("dataList", userList);
			jsonMap.put("currePage", pageNo);
			jsonMap.put("totalPage", pageRequest.getPageTotal());
			arm.setData(jsonMap);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/account/members/load_item_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getItemList(Model model, @RequestParam String userId, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

			List<Map<String, Object>> itemList = courseService.getItemsByCreator(userId, accountId);
			List<Map<String, Object>> groupChart = courseService.getItemsChartByCreator(userId, accountId);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("itemList", itemList);
			data.put("itemChart", groupChart);
			arm.setData(data);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	@RequestMapping("/account/members/load_group_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getGroupList(Model model, @RequestParam String userId, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

			List<Map<String, Object>> groupList = groupService.getGroupListByCreator(userId, accountId);
			List<Map<String, Object>> groupChart = groupService.getGroupChartByCreator(userId, accountId);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("groupList", groupList);
			data.put("groupChart", groupChart);
			arm.setData(data);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}

	/**
	 * 调整至错误信息页面
	 * 
	 * @param model
	 * @param request
	 * @param errorMsg
	 * @return
	 */
	@RequestMapping("/account/error_msg" + Constants.URL_SUFFIX)
	public String toAccountLayer(Model model, HttpServletRequest request, @RequestParam String errorMsg) {
		model.addAttribute("errMsg", errorMsg);
		return "error/error";
	}

	/**
	 * 跳转到账号小组，角色列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param accountId
	 * @return
	 */
	@RequestMapping("/account/group_role" + Constants.URL_SUFFIX)
	public String toAccountGroupRolePage(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String accountId) {

		String resultPage = "modules/account/account_group_role";

		// 刷新logo
		request.getSession().setAttribute(DConstants.REFRESH_IMG_LOGO, true);
		// 删除seesion中角色信息
		request.getSession().removeAttribute("roleListSession");
		// 删除cookie中小组信息
		CookieUtils.deleteCookie(response, DConstants.COOKIE_GROUP_ID, CommonUtils.COOKIE_DOMAIN);
		CookieUtils.deleteCookie(response, DConstants.COOKIE_GROUP_ROLE_ID, CommonUtils.COOKIE_DOMAIN);
		// 用户id
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		// 用户类型
		String userType = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_TYPE);
		String acId = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(accountId)) {
			acId = accountId;
			CookieUtils.setCookie(DConstants.COOKIE_ACCOUNT_ID, accountId, 604800, response, CommonUtils.COOKIE_DOMAIN);
		} else {
			acId = accountService.getAccountId(userId, request);
		}

		String pageToInfo = (String) CookieUtils.getCookie(request, DConstants.USER_CHANGE_ACCOUNT_TO);
		System.err.println(getClass().getName() + "\tpageToInfo:\t" + pageToInfo);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", userId);
		paramsMap.put("accountId", acId);
		// 获取小组角色列表
		List<Map<String, Object>> groupRoleRelList = accountService.queryUserGroupRoleRel(paramsMap);
		// 角色列表
		List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();
		// 已加入小组
		if (groupRoleRelList != null && !groupRoleRelList.isEmpty()) {

			Set<Map<String, Object>> roleSet = new HashSet<Map<String, Object>>(groupRoleRelList);
			roleList.addAll(roleSet);
			request.getSession().setAttribute("roleListSession", roleList);
			System.err.println(getClass().getName()+"\t groupRoleRelList.size() :\t"+groupRoleRelList.size());
			if (groupRoleRelList.size() > DConstants.NUMBER_ONE) {
				model.addAttribute("groupList", groupRoleRelList);
				model.addAttribute("roleList", roleList);
				resultPage = "modules/account/account_group_role";
			} else {
				if (pageToInfo.equals(DConstants.USER_CHANGE_ACCOUNT_TO_SELF)) {
					resultPage = "redirect:/user/profile.html?accountId=" + acId;
				}else {
					String groupRoleId = (String) roleList.get(0).get("group_role_id");
					String groupId = (String) groupRoleRelList.get(0).get("group_id");
					short hasLeaderPriviledge = Short.parseShort((String) groupRoleRelList.get(0).get("has_leader"));
					model.addAttribute("groupRoleId", groupRoleId);
					model.addAttribute("groupId", groupId);
					CookieUtils.setCookie(DConstants.COOKIE_GROUP_ID, groupId, 604800, response,
							CommonUtils.COOKIE_DOMAIN);
					CookieUtils.setCookie(DConstants.COOKIE_GROUP_ROLE_ID, groupRoleId, 604800, response,
							CommonUtils.COOKIE_DOMAIN);
					// viewer
					if (DConstants.GROUP_ROLE_VIEWER_ID.equals(groupRoleId)) {
						// resultPage = "redirect:/course/course_viewer/list.html";
						resultPage = "redirect:/group/intro.html";
					} else if (DConstants.GROUP_ROLE_CONTENT_CREATOR_ID.equals(groupRoleId)) {// creater // /*路径参数重复添加*/
						resultPage = "redirect:/group/features.html";
					} else {// 角色不为 group_leader
						resultPage = "redirect:/group/profile.html";
					}
				}

			}
		} else {
			if (pageToInfo.equals(DConstants.USER_CHANGE_ACCOUNT_TO_SELF)) {
				resultPage = "redirect:/user/profile.html?accountId=" + acId;
			}else {
				if (userType.equals(String.valueOf(DConstants.USER_TYPE_COMPANY)) && StringUtils.isNotEmpty(acId)) {
					CookieUtils.setCookie(DConstants.COOKIE_GROUP_ROLE_ID, DConstants.ROLE_CONTENT_ACCOUNT_ID, 604800,
							response, CommonUtils.COOKIE_DOMAIN);
					resultPage = "redirect:/group/list.html";
				}
			}

		}
 
		return resultPage;
	}

	/**
	 * @param request
	 * @return 获取Account下有多少小组设置能力模型
	 */
	@RequestMapping("/account/getAcountRelGroupCapability" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getAcountRelGroupCapabilityCount(HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Account account=accountService.queryAccountModelById(accountId);
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("count", accountService.getAcountRelGroupCapability(accountId));
			resMap.put("capabilityLevel", account.getCapabilityLevel());
			arm.setData(resMap);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 * @param getAcountStatus
	 * @param request
	 * @return 获取Account下有多少小组设置能力模型
	 */
	@RequestMapping("/account/load_account_status" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getAcountStatus(@RequestParam String accountId) {
		AjaxResultModel arm = new AjaxResultModel();
		Short accountStatus=accountService.getAcountStatus(accountId);		
		arm.setData(accountStatus);
		arm.setStatus(AjaxResultModel.SUCCESS);	
		return arm;
	}
}
