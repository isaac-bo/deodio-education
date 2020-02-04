package com.deodio.elearning.modules.group.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
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
import com.deodio.core.utils.DesUtil;
import com.deodio.core.utils.ExcelUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.JsonUtils;
import com.deodio.core.utils.ServletUtil;
import com.deodio.elearning.commons.service.VelocityService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.course.packages.service.CoursePackagesService;
import com.deodio.elearning.modules.course.service.CourseService;
import com.deodio.elearning.modules.group.service.GroupService;
import com.deodio.elearning.modules.login.service.LoginService;
import com.deodio.elearning.modules.message.service.MessageTextService;
import com.deodio.elearning.modules.register.service.RegisterService;
import com.deodio.elearning.modules.survey.service.SurveyService;
import com.deodio.elearning.modules.user.service.UserService;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Group;
import com.deodio.elearning.persistence.model.GroupRole;
import com.deodio.elearning.persistence.model.GroupRoleUserRel;
import com.deodio.elearning.persistence.model.MyCourseExt;
import com.deodio.elearning.persistence.model.SurveyUserItems;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.emory.mathcs.backport.java.util.Collections;

@Controller
public class GroupController extends BaseController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private VelocityService velocityService;
	@Autowired
	private MailService mailSenderService;
	@Autowired
	private SurveyService surveyService;

	@Autowired
	private AccountService accountService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CoursePackagesService coursePackagesService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MessageTextService messageTextService;

	@Autowired
	private RegisterService registerService;
	@Autowired
	private UserService userService;

	/**
	 * 组列表
	 * 
	 * @Title: toGroupPage
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/list" + Constants.URL_SUFFIX)
	public String toGroupPage() {

		return "modules/group/group_list";
	}

	/**
	 * 获取列表数据
	 * 
	 * @Title: getGroupList
	 * @param keyword
	 * @param pageNo
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/group/load_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getGroupList(@RequestParam(required = false) String keyword, @RequestParam Integer pageNo,
			HttpServletRequest request, @RequestParam(required = false) Integer pageSize) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(DConstants.NUMBER_ZERO) > DConstants.NUMBER_ZERO) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		List<Map<String, Object>> dataList = groupService.findGroupList(keyword, pageRequest, userId, accountId);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPagination().getTotalPages());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	@RequestMapping("/group/list/group_content_creator" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getGroupListByContentCreator(@RequestParam String keyword, HttpServletRequest request)
			throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);

		AjaxResultModel arm = new AjaxResultModel();
		List<Map<String, Object>> dataList = groupService.getGroupListbyContentCreator(keyword, userId, accountId);
		arm.setData(dataList);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	@RequestMapping("/group/set_items" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setGroupItems(@RequestParam Short type, @RequestParam String itemId,
			@RequestParam String groupIds, HttpServletRequest request) throws DeodioException {

		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		AjaxResultModel arm = new AjaxResultModel();
		groupService.insertGroupItems(type, itemId, groupIds, userId);
		arm.setStatus(AjaxResultModel.SUCCESS);

		return arm;
	}

	/**
	 * 删除小组
	 * 
	 * @Title: deleteGroup
	 * @param id
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/group/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteGroup(@RequestParam String id) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			groupService.deleteGroupByPkId(id);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	@RequestMapping("/group/validateCourse" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateCourse(HttpServletRequest request, @RequestParam String groupId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			map.put("accountId", accountId);
			map.put("groupId", groupId);
			List<Map<String, Object>> list = courseService.findGroupCourseSelectList(map);
			arm.setData((null != list && list.size() > 0) ? false : true);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	@RequestMapping("/group/validateMember" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateMember(@RequestParam String groupId) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			Integer total = groupService.getGroupUserCount(groupId, 4, null, null, null, null);
			arm.setData(total > 1 ? false : true);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * @param id
	 * @return 更改小组封面图
	 */
	@RequestMapping("/group/updateImg" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateGroup(@RequestParam String groupId, @RequestParam String coverImg) {
		AjaxResultModel arm = new AjaxResultModel();
		try {
			groupService.updateGroupImg(groupId, coverImg);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/******************************************
	 * 创建组基本信息 START
	 ***************************************************************/
	/**
	 * 创建组基本信息
	 * 
	 * @Title: toGroupInfo
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/profile" + Constants.URL_SUFFIX)
	public String toGroupInfo(Model model, @RequestParam(required = false) String groupId,
			@RequestParam(required = false) String  groupRoleId,HttpServletRequest request,
			HttpServletResponse response) {
		String resultPage = "modules/group/group_profile";
		if (StringUtils.isNotBlank(groupId)) {
			Group groupModel = groupService.getGroupInfoByGroupId(groupId);
			CookieUtils.setCookie(DConstants.COOKIE_GROUP_ID, groupId, 604800, response, CommonUtils.COOKIE_DOMAIN);
			model.addAttribute("groupModel", groupModel);
			model.addAttribute("groupId", groupId);
			model.addAttribute("groupRoleId", groupRoleId);
		}

		model.addAttribute("menu", 1);
		/*String pageToInfo = (String) CookieUtils.getCookie(request, DConstants.USER_CHANGE_ACCOUNT_TO);
		if (pageToInfo.equals(DConstants.USER_CHANGE_ACCOUNT_TO_SELF)) {
			resultPage = "redirect:/user/profile.html?accountId=" + getCookieAccount(request);
		}*/
		return resultPage;
	}

	/**
	 * 创建组信息
	 * 
	 * @Title: createGroupBaseInfo
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/submit" + Constants.URL_SUFFIX)
	public String createGroupBaseInfo(@RequestParam String groupName, @RequestParam String groupContent,
			@RequestParam String attachId, HttpServletRequest request,
			@RequestParam(value = "groupPkId", required = false) String groupPkId) throws DeodioException {

		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String groupId = "";
		if (StringUtils.isNotBlank(groupPkId)) {
			groupId = groupPkId;
			groupService.updateGroupInfo(groupName, groupContent, attachId, groupId);
		} else {
			groupId = groupService.insertGroupInfo(groupName, groupContent, attachId, accountId, userId);
		}

		return redirectPage("/group/permission.html?groupId=" + groupId);
	}

	@RequestMapping("/group/groupNameExsit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getGroupInfoByGroupName(@RequestParam String groupName) throws DeodioException {
		AjaxResultModel model = new AjaxResultModel();
		List<Group> groupList = null;
		if (StringUtils.isNotBlank(groupName)) {
			groupList = groupService.getGroupInfoByGroupName(groupName);
		}
		if (null != groupList && groupList.size() > 0) {
			model.setStatus(AjaxResultModel.FAIL);
		} else {
			model.setStatus(AjaxResultModel.SUCCESS);
		}
		return model;
	}

	/***************************************** 创建组权限START **************************************************************/
	/**
	 * 小组权限
	 * 
	 * @Title: toGroupPermission
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/permission" + Constants.URL_SUFFIX)
	public String toGroupPermission(@RequestParam(required = false) String groupId, Model model,@RequestParam(required = false) String groupRoleId,HttpServletRequest request) {

		model.addAttribute("menu", 2);
		model.addAttribute("groupId", groupId);
		model.addAttribute("groupRoleId", groupRoleId);
		return "modules/group/group_permission";
	}

	/**
	 * 保存用户组权限
	 * 
	 * @Title: saveGroupPermission
	 * @param permission
	 * @param permissionModel
	 * @param groupId
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/permission/save" + Constants.URL_SUFFIX)
	public String saveGroupPermission(@RequestParam(required = false) String[] permission,
			@RequestParam(required = false) String[] permissionModel, @RequestParam String functionIds,
			@RequestParam String groupId, HttpServletRequest request) {
		if (null != permission && permission.length != 0) {
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			groupService.insertGroupRoleFunc(permission, permissionModel, groupId, userId, functionIds);
		}
		return redirectPage("/group/features.html?groupId=" + groupId);
	}

	/***************************************** 创建小组课程START **************************************************************/
	/**
	 * 小组课程
	 * 
	 * @Title: toGroupCourse
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/features" + Constants.URL_SUFFIX)
	public String toGroupCourse(@RequestParam(required = false) String groupId, 
			Model model,HttpServletRequest request,
			HttpServletResponse response) {
		String resultPage="modules/group/group_features";
		model.addAttribute("menu", 3);
		model.addAttribute("groupId", groupId);
		CookieUtils.setCookie(DConstants.COOKIE_GROUP_ID, groupId, 604800, response, CommonUtils.COOKIE_DOMAIN);
//		String pageToInfo = (String) CookieUtils.getCookie(request, DConstants.USER_CHANGE_ACCOUNT_TO);
//		if (pageToInfo.equals(DConstants.USER_CHANGE_ACCOUNT_TO_SELF)) {
//			resultPage = "redirect:/user/profile.html?accountId=" + getCookieAccount(request);
//		}
		return resultPage;
	}

	/***************************************** 小组成员START **************************************************************/
	/**
	 * 小组成员
	 * 
	 * @Title: toGroupMember
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/member" + Constants.URL_SUFFIX)
	public String toGroupMember(@RequestParam(required = false) String groupId, Model model) {
		List<GroupRole> roleList = groupService.getGroupRole();

		List<Integer> memberCount = groupService.getMemberJoinCount(groupId);

		model.addAttribute("roleList", roleList);
		model.addAttribute("menu", 4);
		model.addAttribute("groupId", groupId);
		model.addAttribute("memberCount", memberCount);
		Group group = groupService.getGroupInfoByGroupId(groupId);
		if (null != group) {
		model.addAttribute("activeFormStatus", group.getActiveFormStatus());
		}
		return "modules/group/group_member";
	}

	@RequestMapping("/group/member/list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadGroupUserList(@RequestParam Integer pageNo, @RequestParam String groupId,
			@RequestParam Integer status, @RequestParam String roleId, @RequestParam String keywords,
			@RequestParam Integer tabValue, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			Integer total = groupService.getGroupUserCount(groupId, status, roleId, keywords, tabValue, userId);

			PageRequest pageRequest = new PageRequest(pageNo, total);

			List<Map<String, Object>> groupUserList = groupService.getGroupUserList(groupId, status, roleId, keywords,
					pageRequest, tabValue, userId);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("dataList", groupUserList);
			jsonMap.put("currePage", pageNo);
			jsonMap.put("totalRow", pageRequest.getCount());
			jsonMap.put("totalPage", pageRequest.getPageTotal());
			arm.setData(jsonMap);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 激活/暂停组内人员
	 * 
	 * @Title: activeOrStopGroupUser
	 * @param userData
	 * @param status
	 * @param groupId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/group/member/action" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel activeOrStopGroupUser(@RequestParam String userData, @RequestParam Integer status,
			@RequestParam String groupId, @RequestParam String umails, @RequestParam Integer isSendMail) {
		AjaxResultModel arm = new AjaxResultModel();

		try {

			boolean flag = groupService.updateGroupUserStatus(userData.split(","), status, groupId);
			if (isSendMail == 1) {
				umails = umails.replace(DConstants.DELIMITER_COMMA, DConstants.DELIMITER_SEMICOLON);

				Map<String, Object> contentMaps = new HashMap<String, Object>();
				contentMaps.put("content", status);
				String content = velocityService.getVelocityTemplate(10003, contentMaps);
				EmailThread emailThread = new EmailThread(umails, content, mailSenderService, "[deodio-邮件]:组状态变更",
						null);
				taskExecutor.execute(emailThread);
			}
			arm.setData(flag);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * @param userData
	 * @param status
	 * @param groupId
	 * @param umails
	 * @param isSendMail
	 * @return 已过期的用户重新邀请加入小组
	 */
	@RequestMapping("/group/member/reInvite" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel reInviteGroupUser(@RequestParam String userData, @RequestParam Integer status,
			@RequestParam String groupId, @RequestParam String umails, @RequestParam Integer isSendMail,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			groupService.updateGroupUserCreateTime(userData.split(","), status, groupId);
			String createId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String URL = (request.getRequestURL().toString()).substring(0,
					(request.getRequestURL().toString()).indexOf(request.getRequestURI())) + request.getContextPath();
			Group group = groupService.getGroupInfoByGroupId(groupId);
			UserModel user = userService.queryUserInfoById(createId);
			List<Map<String, Object>> contentMaps = new ArrayList<Map<String, Object>>();
			String[] users = umails.split(",");
			// 去重user
			List<String> userList = Arrays.asList(users);
			Set<String> userSet = new HashSet<String>(userList);
			users = (String[]) userSet.toArray(new String[userSet.size()]);
			Map<String, Object> objMaps = null;
			for (int i = 0, z = users.length; i < z; i++) {
				// 查看该邮箱是否已存在在系统中
				umails = users[i];
				UserModel userInfo = registerService.getUserModel(umails);
				GroupRoleUserRel groupRoleUserRel = groupService.getGroupUserByUserId(userInfo.getId(), groupId);
				boolean userExist = userService.getValidateEmailExists(users[i]);
				objMaps = new HashMap<String, Object>();
				if (1==isSendMail) {// 填写注册表单
					objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL));
				} else {
					if (userExist) {// 已存在的用户直接激活
						objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL_ACTIVE));
					} else {// 不填写注册表单直接设置密码
						objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL_SETPWD));
					}
				}
				objMaps.put("groupid", groupId);
				objMaps.put("usermail", DesUtil.encrypt(users[i]));
				objMaps.put("userRole", groupRoleUserRel.getGroupRoleId());
				objMaps.put("content", user.getNickName() + "邀请您加入" + group.getGroupName() + "小组");
				objMaps.put("userExist", userExist);
				contentMaps.add(objMaps);
				List<String> content = velocityService
						.getVelocityTemplate(DConstants.SYSTEM_TEMPLATE_INVITE_MAIL_REINVITE, contentMaps);
				EmailThread emailThread = new EmailThread(umails, content, "邀请加入小组通知", true);
				taskExecutor.execute(emailThread);
				// 查詢用戶是否已填写注册表单
				if (1==isSendMail) {
					UserModel usermodel = userService.queryUserInfoByUserName(umails,
							DConstants.USE_REGISTER_TYPE_MAIL);
					List<Map<String, Object>> surveyList = surveyService.getSurveyList(groupId, DConstants.STATUS_ONE);
					if (null != surveyList && surveyList.size() > 0) {
						surveyService.insertSurveryUserItemForGroup(surveyList, groupId, usermodel.getId());
					}
				}
			}
			arm.setData(userData.split(",").length);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	@RequestMapping("/group/member/delete" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteUserFromGroup(@RequestParam String userData, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			boolean flag = groupService.deleteUserFromGroup(userData.split(","), request);
			arm.setData(flag);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	@RequestMapping("/group/member/validateStatus" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateMemberStatus(@RequestParam String userData, @RequestParam Integer status,
			@RequestParam(required = false) String type) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			boolean flag = groupService.validateMemberStatus(userData.split(","), status, type);
			arm.setData(flag);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 查看单个小组成员
	 * 
	 * @Title: getUserGroupInfo
	 * @param groupId
	 * @param model
	 * @param userId
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/member/preview" + Constants.URL_SUFFIX)
	public String getUserGroupInfo(@RequestParam String groupId, Model model, @RequestParam String userId) {
		List<GroupRole> roleList = groupService.getGroupRole();
		Map<String, Object> userMap = groupService.getUserInfoFromGroup(groupId, userId);
		List<Map<String, Object>> surveyResultList = surveyService.getSurveyResultList(groupId, userId, null);
		model.addAttribute("userMap", userMap);
		model.addAttribute("roleList", roleList);
		model.addAttribute("menu", 4);
		model.addAttribute("groupId", groupId);
		model.addAttribute("userId", userId);
		model.addAttribute("surveyList", surveyResultList);
		return "modules/group/group_member_preview";
	}

	/**
	 * 小组成员修改组员角色
	 * 
	 * @Title: updateUserGroupRole
	 * @param roleId
	 * @param relId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/group/member/update_role" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateUserGroupRole(@RequestParam String roleId, @RequestParam String relId,
			@RequestParam String groupId) {
		AjaxResultModel arm = new AjaxResultModel();

		try {
			groupService.updateUserGroupRole(relId, roleId);
			arm.setData(groupId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 邀请加入
	 * 
	 * @Title: toGroupJoin
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/invite" + Constants.URL_SUFFIX)
	public String toGroupJoin(HttpServletRequest request, @RequestParam(required = false) String groupId, Model model) {

		String mailSubject = StringUtils.EMPTY;
		String mailContent = StringUtils.EMPTY;
		List<GroupRole> roleList = groupService.getGroupRole();
		Group groupInfo = groupService.getGroupInfoByGroupId(groupId);

		Map<String, Object> objMaps = new HashMap<String, Object>();
		try {
			objMaps.put("userName",
					URLDecoder.decode((String) CookieUtils.getCookie(request, DConstants.COOKIE_NICK_NAME), "UTF-8"));
			objMaps.put("groupName", groupInfo.getGroupName());
			// 获取Access Link todo
			// 获取 访问地址 todo
			mailSubject = velocityService.getVelocityTemplate(DConstants.SYSTEM_TEMPLATE_INVITE_MAIL_SUBJECT, objMaps);
			mailContent = velocityService.getVelocityTemplate(DConstants.SYSTEM_TEMPLATE_INVITE_MAIL_CONTENT, objMaps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("mailFrom", CommonUtils.MAIL_SMTP_FROM);
		model.addAttribute("mailSubject", mailSubject);
		model.addAttribute("mailContent", mailContent);

		model.addAttribute("menu", 5);
		model.addAttribute("groupId", groupId);
		model.addAttribute("roleList", roleList);
		model.addAttribute("groupInfo", groupInfo);
		if (null != groupInfo) {
			model.addAttribute("activeFormStatus", groupInfo.getActiveFormStatus());
		}
		return "modules/group/group_invite";
	}

	@RequestMapping("/group/invite/mail" + Constants.URL_SUFFIX)
	public String previewJoinMail(Model model, @RequestParam String groupId, @RequestParam String umails,
			@RequestParam String joinTitle, @RequestParam String userRole, @RequestParam String joinContent,
			HttpServletRequest request, @RequestParam String sendForm) {

		try {
			String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			String createId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String URL = (request.getRequestURL().toString()).substring(0,
					(request.getRequestURL().toString()).indexOf(request.getRequestURI())) + request.getContextPath();
			// 获取logo
			String logoImg = (String) CookieUtils.getCookie(request, DConstants.COOKIE_LOGO_IMG);
			if (StringUtils.isNotBlank(logoImg)) {
				logoImg = CommonUtils.IMGS_LOCAL_DIR + logoImg;
			} else {
				logoImg = request.getSession().getServletContext().getRealPath("/resources/img/account/logo-2.png");
			}

			List<Map<String, Object>> contentMaps = new ArrayList<Map<String, Object>>();

			String[] users = umails.split(";");
			// 去重user
			List<String> userList = Arrays.asList(users);
			Set<String> userSet = new HashSet<String>(userList);
			users = (String[]) userSet.toArray(new String[userSet.size()]);
			// umails = StringUtils.join(users, DConstants.DELIMITER_SEMICOLON);
			String form = "";
			Map<String, Object> objMaps = null;
			for (int i = 0, z = users.length; i < z; i++) {
				// 查看该邮箱是否已存在在系统中
				umails = users[i];
				boolean userExist = userService.getValidateEmailExists(users[i]);
				objMaps = new HashMap<String, Object>();
				if ("1".equals(sendForm)) {// 填写注册表单
					// String[] dataArrays =
					// dataStr.split(DConstants.DELIMITER_ROW);
					//
					objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL));
				} else {
					if (userExist) {// 已存在的用户直接激活
						objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL_ACTIVE));
					} else {// 不填写注册表单直接设置密码
						objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL_SETPWD));
					}
				}
				objMaps.put("groupid", groupId);
				objMaps.put("usermail", DesUtil.encrypt(users[i]));
				objMaps.put("userRole", userRole);
				objMaps.put("content", joinContent);
				objMaps.put("logo", logoImg);
				objMaps.put("form", form);
				objMaps.put("userExist", userExist);
				contentMaps.add(objMaps);
				List<String> content = velocityService.getVelocityTemplate(
						DConstants.SYSTEM_TEMPLATE_INVITE_MAIL_TEXT_TEMPLATE_COMMON_MAIL, contentMaps);
				messageTextService.insertMessage(joinTitle, joinContent, createId,
						DConstants.MESSAGE_TEXT_STATUS_PUBLISH, DConstants.MESSAGE_TEXT_TYPE_JOIN,
						DConstants.MESSAGE_TEXT_REC_TYPE_TEACHER, users[i], DConstants.MESSAGE_VIEW_STATUS_ZERO,
						logoImg);
				EmailThread emailThread = new EmailThread(umails, content, joinTitle, true);
				taskExecutor.execute(emailThread);
				// 创建用户信息
				groupService.insertUserFromGroup("", "", "", umails, "", userRole, groupId, accountId, createId, "2");
				// 查詢用戶是否已填写注册表单
				if ("1".equals(sendForm)) {
					UserModel usermodel = userService.queryUserInfoByUserName(umails,
							DConstants.USE_REGISTER_TYPE_MAIL);
					List<Map<String, Object>> surveyList = surveyService.getSurveyList(groupId, DConstants.STATUS_ONE);
					surveyService.insertSurveryUserItemForGroup(surveyList, groupId, usermodel.getId());
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/group/member/single.html?groupId=" + groupId;
	}

	/**
	 * @param gp
	 * @param um
	 * @param ur
	 * @return 邮件激活参加成员
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group/active_join_group" + Constants.URL_SUFFIX)
	public String atciveEmailJoinUser(@RequestParam String gp, @RequestParam String um, @RequestParam String ur,
			HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		String userMail = DesUtil.decrypt(um);
		System.err.println(getClass().getName()+"\t userMail  :\t"+userMail);
		UserModel userModel = accountService.getUserModeInforByCondition(userMail);
		if(userModel!=null) {
			Group group=groupService.getGroupInfoByGroupId(gp);
			loginService.setLoginInfo(userModel,group.getAccountId(),response);
		}

		GroupRoleUserRel groupRoleUserRel = groupService.getGroupUserByUserId(userModel.getId(), gp);
		if (!groupRoleUserRel.getGroupRoleId().equals(ur)) {//激活前更改了用户角色
			return this.redirectPage("/group/group_email_link.html?tipsMessageType=1");
		} else {
			// 准备进入系统
			clearCookie(response);
			setCookie(request, response, userModel, ur);
			groupService.updateEmailGroupUserStatus(userModel.getId(), DConstants.STATUS_ONE, gp);//激活
			userService.updateEmailStatusById("1", userModel.getId());//更改邮箱认证状态
			if (ur.equals(DConstants.GROUP_ROLE_GROUP_LEADER_ID)) {
				return this.redirectPage("/group/profile.html?groupId=" + gp);
			}
			if (ur.equals(DConstants.GROUP_ROLE_CONTENT_CREATOR_ID)) {
				return this.redirectPage("/group/features.html?groupId=" + gp);
			}
			if (ur.equals(DConstants.GROUP_ROLE_VIEWER_ID)) {
				return this.redirectPage("/group/intro.html?groupId=" + gp);
			} else {
				return this.redirectPage("/login.html");
			}
		}
	}

	@RequestMapping("/group/group_email_link" + Constants.URL_SUFFIX)
	public String toGroupEmailTips(@RequestParam(required = false) Integer tipsMessageType, Model model) {
		String tipsMessage = "";
		if (tipsMessageType == 1) {
			tipsMessage = "邮件已失效";
		}
		model.addAttribute("tipsMessage", tipsMessage);
		return "modules/group/group_email_link";
	}

	/**
	 * @param gp
	 * @param um
	 * @param ur
	 * @return 管理员手动激活
	 */
	@RequestMapping("/group/invite/action" + Constants.URL_SUFFIX)
	public String atciveJoinUser(@RequestParam String gp, @RequestParam String um, @RequestParam String ur) {
		String userMail = DesUtil.decrypt(um);
		UserModel userModel = accountService.getUserModeInforByCondition(userMail);
		groupService.updateGroupUserStatus(new String[] { userModel.getId() }, DConstants.STATUS_ONE, gp);
		return "";
	}

	/**
	 * 创建组人员
	 * 
	 * @Title: toGroupCreateUser
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/member/single" + Constants.URL_SUFFIX)
	public String toGroupCreateUser(@RequestParam(required = false) String groupId, Model model) {
		List<GroupRole> roleList = groupService.getGroupRole();
		Group group = groupService.getGroupInfoByGroupId(groupId);
		model.addAttribute("roleList", roleList);
		model.addAttribute("menu", 6);
		model.addAttribute("groupId", groupId);
		if (null != group) {
			model.addAttribute("activeFormStatus", group.getActiveFormStatus());
		}
		return "modules/group/group_member_single";
	}

	@RequestMapping("/group/member/submit_single" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel createUserByGroup(HttpServletRequest request, @RequestParam String userName,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userMail,
			@RequestParam String mobile, @RequestParam String groupRole, @RequestParam String groupId,
			@RequestParam Integer isSendMail, @RequestParam String activeAccount) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = this.getCookieAccount(request);
		String createId = this.getCookieUserId(request);
		try {
			// 获取logo
			String logoImg = (String) CookieUtils.getCookie(request, DConstants.COOKIE_LOGO_IMG);
			String URL = (request.getRequestURL().toString()).substring(0,
					(request.getRequestURL().toString()).indexOf(request.getRequestURI())) + request.getContextPath();
			boolean flag = registerService.validateUserNameExists(userName, DConstants.USE_REGISTER_TYPE_COMPANY);
			if (StringUtils.isNotBlank(logoImg)) {
				logoImg = CommonUtils.IMGS_LOCAL_DIR + logoImg;
			} else {
				logoImg = request.getSession().getServletContext().getRealPath("/resources/img/account/logo-2.png");
			}
			// 将用户和小组关联
			groupService.insertUserFromGroup(userName, firstName, lastName, userMail, mobile, groupRole, groupId,
					accountId, createId, activeAccount);
			Group group = groupService.getGroupInfoByGroupId(groupId);
			UserModel user = userService.queryUserInfoById(createId);
			Map<String, Object> objMaps = null;
			List<Map<String, Object>> contentMaps = new ArrayList<Map<String, Object>>();
			objMaps = new HashMap<String, Object>();
			if (1 == isSendMail) {// 发送注册表单
				objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL));
			} else {
				if (flag) {// 用户名已存在直接激活
					objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL_ACTIVE));
				} else {// 用户名不存在设置密码
					objMaps.put("server", URL.concat(CommonUtils.HTTP_SERVER_URL_SETPWD));
				}
			}
			objMaps.put("groupid", groupId);
			objMaps.put("userRole", groupRole);
			objMaps.put("content", user.getNickName() + "邀请您加入" + group.getGroupName() + "小组");
			objMaps.put("usermail", DesUtil.encrypt(userMail));
			objMaps.put("password", DConstants.DEFAULT_PASS_WORD);
			objMaps.put("logo", logoImg);
			objMaps.put("userExist", flag);
			contentMaps.add(objMaps);
			// 使用common-mail 发送邀请邮件 （可使用本地图片和网络图片）
			List<String> content = velocityService
					.getVelocityTemplate(DConstants.SYSTEM_TEMPLATE_INVITE_MAIL_TEXT_TEMPLATE_COMMON_MAIL, contentMaps);
			EmailThread emailThread = new EmailThread(userMail, content, "邀请加入小组通知", true);
			taskExecutor.execute(emailThread);
			if (1 == isSendMail) {
				UserModel usermodel = userService.queryUserInfoByUserName(userMail, DConstants.USE_REGISTER_TYPE_MAIL);
				List<Map<String, Object>> surveyList = surveyService.getSurveyList(groupId, DConstants.STATUS_ONE);
				surveyService.insertSurveryUserItemForGroup(surveyList, groupId, usermodel.getId());
			}
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}

		return arm;
	}

	/**
	 * 注册表单列表页
	 * 
	 * @Title: toGroupContent
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/form" + Constants.URL_SUFFIX)
	public String toGroupformList(@RequestParam(required = false) String groupId, Model model) {
		model.addAttribute("menu", 7);
		model.addAttribute("groupId", groupId);
		return "modules/group/group_form_list";
	}

	/**
	 * @param groupId
	 * @return
	 * 
	 */
	@RequestMapping("/group/loadFromList" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadGroupformList(@RequestParam(required = false) String groupId,
			@RequestParam(required = false) String userId, @RequestParam Integer pageNo) {
		AjaxResultModel arm = new AjaxResultModel();
		Integer total = surveyService.getSurveyResultCount(groupId, userId);
		PageRequest pageRequest = new PageRequest(pageNo, total);
		List<Map<String, Object>> surveyResultList = surveyService.getSurveyResultList(groupId, userId, null);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", surveyResultList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalRow", pageRequest.getCount());
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/toUserFormPage" + Constants.URL_SUFFIX)
	public String toUserFormPage(@RequestParam String groupId, @RequestParam String userId, Model model) {
		List<Map<String, Object>> surveyList = surveyService.getSurveyResultByUserId(groupId, userId);
		model.addAttribute("menu", 7);
		model.addAttribute("groupId", groupId);
		model.addAttribute("surveyList", surveyList);
		return "modules/group/group_form_user";
	}

	/**
	 * 注册表单
	 * 
	 * @Title: toGroupContent
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/form/manage" + Constants.URL_SUFFIX)
	public String toGroupform(@RequestParam(required = false) String groupId, Model model,@RequestParam(required = false) String preData) {

		List<Map<String, Object>> surveyList = surveyService.getSurveyList(groupId, DConstants.STATUS_ONE);
		
		if(StringUtils.isNotEmpty(preData)){
			preData=preData.replaceAll(" ", "");
			String[] dataArrays = preData.split(DConstants.DELIMITER_ROW);
			surveyList=groupService.getSurveySubjectAndItems(dataArrays);
		}
		Group groupInfo = groupService.getGroupInfoByGroupId(groupId);
		if (null != groupInfo) {
			model.addAttribute("activeFormStatus", groupInfo.getActiveFormStatus());
		}
		model.addAttribute("menu", 7);
		model.addAttribute("groupId", groupId);
		model.addAttribute("surveyList", surveyList);
		return "modules/group/group_form";
	}

	/**
	 * 保存注册表单
	 * 
	 * @Title: insertSurvey
	 * @param dataStr
	 * @param groupId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/group/form/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel insertSurvey(@RequestParam String dataStr, @RequestParam String groupId,
			HttpServletRequest request, @RequestParam Integer status) {
		AjaxResultModel arm = new AjaxResultModel();
		// 1_#_11111_#_1_|_2_|_3_@_2_#_22222_#_1_|_2_|_3_@_3_#_33333_#_1_|_2

		String[] dataArrays = dataStr.split(DConstants.DELIMITER_ROW);
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		groupService.insertSurvey(dataArrays, groupId, accountId, userId, DConstants.STATUS_ONE, status);
		return arm;
	}

	/**
	 * 设置密码
	 * 
	 * @Title: setPwdInit
	 * @return
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group/setPwd" + Constants.URL_SUFFIX)
	public String setPwdInit(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String gp, @RequestParam(required = false) String userId,
			@RequestParam(required = false) String userName, @RequestParam(required = false) String um,
			@RequestParam(required = false) String ur) throws UnsupportedEncodingException {
		UserModel userModel = null;
		if (StringUtils.isNotEmpty(um)) {
			userModel = userService.queryUserInfoByUserName(DesUtil.decrypt(um), DConstants.USE_REGISTER_TYPE_MAIL);
			GroupRoleUserRel groupRoleUserRel = groupService.getGroupUserByUserId(userModel.getId(), gp);
			if (!groupRoleUserRel.getGroupRoleId().equals(ur)) {
				return this.redirectPage("/group/group_email_link.html?tipsMessageType=1");
			}
			userId = userModel.getId();
			userName = userModel.getNickName();
			groupService.updateEmailGroupUserStatus(userId, DConstants.STATUS_ONE, gp);

		}
		// 准备进入系统
		clearCookie(response);
		setCookie(request, response, userModel, ur);
		if ("null".equals(userName) || StringUtils.isEmpty(userName)) {
			userName = "";
		}
		if(userModel!=null) {
			Group group=groupService.getGroupInfoByGroupId(gp);
			loginService.setLoginInfo(userModel,group.getAccountId(),response);
		}
		model.addAttribute("ur", ur);
		model.addAttribute("uKeyId", userId);
		model.addAttribute("gp", gp);
		model.addAttribute("nickName", userName);
		return "modules/group/group_form_add_password";
	}

	/**
	 * 设置密码保存并激活用户
	 * 
	 * @Title: setPwd
	 * @return
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/group/setPwd/submit" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel setPwdSubmit(@RequestParam(required = false) String userId,
			@RequestParam(required = false) String nickName, @RequestParam(required = false) String password,
			Model model, @RequestParam(required = false) String gp, HttpServletResponse response)
					throws UnsupportedEncodingException {
		AjaxResultModel arm = new AjaxResultModel();
		registerService.updateNickNameAndPassword(userId, nickName, DigestUtils.md5DigestAsHex(password.getBytes()));
		userService.updateEmailStatusById("1", userId);//邮箱验证状态
		CookieUtils.deleteCookie(response, DConstants.COOKIE_NICK_NAME, CommonUtils.COOKIE_DOMAIN);
		CookieUtils.setCookie(DConstants.COOKIE_NICK_NAME, URLEncoder.encode(nickName, "UTF-8"), 604800, response,
				CommonUtils.COOKIE_DOMAIN);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 问卷预览
	 * 
	 * @Title: toPreviewSurvey
	 * @param preData
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/group/form/preview" + Constants.URL_SUFFIX)
	public String toPreviewSurvey(@RequestParam String preData, Model model,@RequestParam String  groupId) {
		model.addAttribute("menu", 7);
		String[] dataArrays = preData.split(DConstants.DELIMITER_ROW);
		model.addAttribute("surveyList", groupService.getSurveySubjectAndItems(dataArrays));
		model.addAttribute("preData", preData);
		model.addAttribute("groupId", groupId);
		return "modules/group/group_form_preview";
	}

	@RequestMapping("/group/content" + Constants.URL_SUFFIX)
	public String toGroupContent(@RequestParam String groupId, Model model) {

		model.addAttribute("menu", 8);
		model.addAttribute("groupId", groupId);
		return "modules/group/group_content";
	}

	@RequestMapping("group/capability" + Constants.URL_SUFFIX)
	public String toGroupCapability(@RequestParam(required = false) String groupId, Model model,
			HttpServletRequest request) {
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		List<Map<String, Object>> list = groupService.loadGroupCapabilityItemHeader(accountId, groupId, userId);
		model.addAttribute("menu", 9);
		model.addAttribute("groupId", groupId);
		model.addAttribute("headLength", null != list ? list.size() : 0);
		return "modules/group/group_capability";
	}

	@RequestMapping("/group/capability_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel listCapability(@RequestParam(required = false) Integer capabilityType,
			@RequestParam(required = false) String groupId, @RequestParam(required = false) String keywords,
			HttpServletRequest request, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("capabilityType", capabilityType);
		map.put("keywords", keywords);
		map.put("groupId", groupId);
		// 总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", groupService.findAccountCapabilityItems(map));
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPagination().getTotalPages());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/update_capability_items" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateCapabilityItem(@RequestParam Integer capabilityType,
			@RequestParam String capabilityIds, @RequestParam String groupId, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		groupService.updateGroupCapabilityItems(accountId, groupId, userId, capabilityType, capabilityIds);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/load_capability_items" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadGroupCapabilityItem(@RequestParam Integer capabilityType, @RequestParam String groupId,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		arm.setData(groupService.loadGroupCapabilityItem(accountId, groupId, userId, capabilityType));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/load_capability_items_header" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadGroupCapabilityItemHeader(@RequestParam String groupId, HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		arm.setData(groupService.loadGroupCapabilityItemHeader(accountId, groupId, userId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/submit_capability_items" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel submitGroupCapabilityItems(@RequestParam String groupId, @RequestParam String descList,
			HttpServletRequest request) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		groupService.submitGroupCapabilityItems(groupId, userId, descList);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/delete_capability_items" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteGroupCapabilityItems(@RequestParam String groupId, @RequestParam String capabilityId) {
		AjaxResultModel arm = new AjaxResultModel();
		groupService.deleteGroupItem(groupId, capabilityId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 查询小组课程列表
	 * 
	 * @param model
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/group/load_course_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadGroupCourseList(Model model, HttpServletRequest request,
			@RequestParam String groupId,@RequestParam(required = false) Integer pageNo, 
			@RequestParam(required = false) Integer courseType,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String courseNameKeyWord, 
			@RequestParam(required = false) String flag) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("groupId", groupId);
		map.put("courseNameKeyWord", courseNameKeyWord);
		if(courseType!=null) {
			map.put("courseType", courseType);
		}
		// 总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}

//		map.put("studyTime",DConstants.GROUP_COURSE_ORDER_BY_STUDY_TIME);

		map.put("pagination", pageRequest);
		System.err.println(getClass().getName()+"\t 查询前的map-:\t"+map.toString());
		List<Map<String, Object>> list = courseService.findGroupCourseSelectList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		System.err.println(getClass().getName()+"\t 查询后的jsonMap-:\t"+jsonMap.toString());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 删除小组课程
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/group/delete_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel deleteGroupCourse(Model model, HttpServletRequest request, @RequestParam String groupId,
			@RequestParam String relId) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("groupId", groupId);
		map.put("relId", relId);
		try {
			groupService.deleteGroupCourse(map);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	/**
	 * 查询小组课程包列表
	 * 
	 * @param model
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/group/load_course_package_to_select" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryCourseOnlineByClassificationAndTags(Model model, HttpServletRequest request,
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(value = "classificationIdList[]", required = false) List<?> classificationIdList,
			@RequestParam(value = "tagIdList[]", required = false) List<?> tagIdList,
			@RequestParam(required = false) Integer pageSize) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_COURSEPACKAGE);
		// 总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		map.put("classificationIdList", classificationIdList);
		map.put("tagIdList", tagIdList);
		map.put("pagination", pageRequest);
		List<Map<String, Object>> list = coursePackagesService.findCoursePackageList(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("dataList", list);
		resultMap.put("currePage", pageNo);
		resultMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(resultMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 批量添加邮件模板
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/group/down_excel_template" + Constants.URL_SUFFIX)
	public void downSubProjectExcelTemplate(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<String[]> datas = new ArrayList<String[]>();
		String[] headerData = new String[2];
		headerData[0] = "姓名";
		headerData[1] = "邮件地址";
		datas.add(headerData);
		ServletUtil.writeExcelFile(request, response, "模板.xls", this.exportExcel("模板", datas));
	}

	/**
	 * 
	 * @Title: exportExcel @Description: 电子表格导出2007版本以上 （单个sheet） @param
	 *         sheetName sheet 名称 @param datas 数据List<String[]> @throws
	 *         IOException @return HSSFWorkbook @throws
	 */
	public HSSFWorkbook exportExcel(String sheetName, List<String[]> datas) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(sheetName);
		String[] data = null;
		if (datas != null && !datas.isEmpty()) {
			for (int i = 0; i < datas.size(); i++) {
				Row row = sheet.createRow(i);
				data = datas.get(i);
				if (data != null) {
					for (int j = 0; j < data.length; j++) {
						Cell cell = row.createCell(j);
						if (StringUtils.isNotBlank(data[j])) {
							cell.setCellValue(data[j]);
						} else {
							cell.setCellValue(" ");
						}
					}
				}
			}
		}
		return wb;
	}

	/**
	 * 处理小组上传邀请人员信息列表
	 * 
	 * @param response
	 * @param request
	 * @author 逻辑变更20180521 许向东
	 */
	@RequestMapping(value = "/group/upload_excel_template" + Constants.URL_SUFFIX)
	public void uploadDeclareExcelTemplate(HttpServletResponse response, HttpServletRequest request) {
		boolean flag = true;
		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("status", 1);
		dataMap.put("data", arm);

		try {
			request.setCharacterEncoding(Constants.CHARSET_UTF_8);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> it = items.iterator();
			FileItem fileItem = null;
			String fileName = "";
			while (it.hasNext()) {
				fileItem = it.next();
				if (!fileItem.isFormField()) {
					fileName = fileItem.getName();
					flag = true;
					break;
				} else {
					fileItem = null;
				}
			}

			if (flag) {
				// 获取文件后缀
				String type = FileUtils.getExt(fileName);
				// 行数
				int row = ExcelUtils.getRowNum(fileItem.getInputStream(), 0, type) + 1;
				if (row == 1) {
					arm.setStatus(AjaxResultModel.FAIL);
					arm.setMessage("上传失败,数据不能为空!");
					JsonUtils.objToJson(dataMap, response);
					return;
				}
				// 头列数
				// int headerNum = ExcelUtils.getColNum(inputStream, 0, 0,
				// type);
				// 除去表头的第一行数据开始取数
				// Date date = new Date();
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < row; i++) {
					String value = ExcelUtils.readExcelContent(fileItem.getInputStream(), 0, i, 1, type);
					sb.append(value).append(DConstants.MAIL_HEADER_DATA);
				}
				arm.setData(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		if (flag) {
			arm.setStatus(AjaxResultModel.SUCCESS);
		} else {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		JsonUtils.objToJson(dataMap, response);

	}

	/**
	 * 小组选择课程
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/group/add_course" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel addGroupCourse(Model model, HttpServletRequest request, @RequestParam String groupId,
			@RequestParam String courseIds) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		try {
			groupService.addGroupCourse(DConstants.GROUP_ITEMS_TYPE_COURSE, courseIds, groupId, userId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	@RequestMapping("/group/add_course_package" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel addGroupPackageCourse(Model model, HttpServletRequest request, @RequestParam String groupId,
			@RequestParam String coursePackagesIds) {
		AjaxResultModel arm = new AjaxResultModel();
		String userId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		try {
			groupService.addGroupCourse(DConstants.GROUP_ITEMS_TYPE_COURSE, coursePackagesIds, groupId, userId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception ex) {
			arm.setStatus(AjaxResultModel.FAIL);
		}
		return arm;
	}

	/**
	 * 获取可选择课程
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/group/load_course_to_select" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadGroupCourseToSelect(Model model, HttpServletRequest request,
			@RequestParam String groupId, @RequestParam String keywords, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("groupId", groupId);
		map.put("keywords", keywords);
		// 总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		List<Map<String, Object>> list = courseService.findUnSelectedGroupCourseList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取小组权限
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/group/load_permission_role" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel findGroupFunctionForRole(Model model, HttpServletRequest request,
			@RequestParam String groupId, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("groupId", groupId);
		map.put("groupType", DConstants.GROUP_TYPE_PERMISSION);
		// 总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		List<Map<String, Object>> list = groupService.findGroupFunctionForRole(map);
		List<String> functionIds = (List<String>) map.get("functionIds");
		// String functionIdsStr = functionIds.toArray().toString();

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("ids", map.get("functionIds"));
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 获取组内组内工具模块启用状况
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/group/load_model_function" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel findGroupModelFunction(Model model, HttpServletRequest request, @RequestParam String groupId,
			@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
		AjaxResultModel arm = new AjaxResultModel();
		String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("groupId", groupId);
		// 总条数
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		map.put("pagination", pageRequest);
		List<Map<String, Object>> list = groupService.findGroupModelFunction(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", list);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	/**
	 * 小组简介节课程
	 * 
	 * @param model
	 * @param groupId
	 * @return
	 */
	@RequestMapping("/group/intro" + Constants.URL_SUFFIX)
	public String toGroupDetail(Model model,HttpServletRequest request,
			@RequestParam(required = false) String groupId) {
		String resultPage="modules/group/group_intro";
		if (StringUtils.isNotBlank(groupId)) {
			PageRequest pageRequest = new PageRequest(1);
			List<Map<String, Object>> groupUserList = groupService.getGroupUserList(groupId, 2, null, null, pageRequest,
					null, null);
			Group groupModel = groupService.getGroupInfoByGroupId(groupId);
			model.addAttribute("groupModel", groupModel);
			model.addAttribute("groupId", groupId);
			model.addAttribute("groupUserList", groupUserList);
		}
		model.addAttribute("menu", 1);
//		String pageToInfo = (String) CookieUtils.getCookie(request, DConstants.USER_CHANGE_ACCOUNT_TO);
//		if (pageToInfo.equals(DConstants.USER_CHANGE_ACCOUNT_TO_SELF)) {
//			resultPage = "redirect:/user/profile.html?accountId=" + getCookieAccount(request);
//		}
		return resultPage;
		
	}

	@RequestMapping("/group/baseInfo" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getGroupBaseInfo(Model model, String groupId) {
		AjaxResultModel arm = new AjaxResultModel();
		Group groupModel = groupService.getGroupInfoByGroupId(groupId);
		arm.setData(groupModel);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/copyGroup" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel copyGroup(Model model, String groupId, String groupName) {
		AjaxResultModel arm = new AjaxResultModel();
		groupService.copyGroup(groupId, groupName);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/toGroupFormRegister" + Constants.URL_SUFFIX)
	public String toGroupFormRegister(Model model, @RequestParam(required = false) String gp,
			@RequestParam(required = false) String um, @RequestParam(required = false) boolean userExist,
			@RequestParam(required = false) String ur, HttpServletResponse response, HttpServletRequest request)
					throws UnsupportedEncodingException {

		// 查詢用戶是否已填写注册表单
		UserModel usermodel = userService.queryUserInfoByUserName(DesUtil.decrypt(um),
				DConstants.USE_REGISTER_TYPE_MAIL);
		GroupRoleUserRel groupRoleUserRel = groupService.getGroupUserByUserId(usermodel.getId(), gp);
		if (!groupRoleUserRel.getGroupRoleId().equals(ur)) {
			return this.redirectPage("/group/group_email_link.html?tipsMessageType=1");
		}
		// 验证用户是否被管理员删除
		boolean userGroupRoleExist = groupService.getUserExistGroupRole(gp, usermodel.getId());
		// 验证用户是否过期
		boolean userGroupRoleOutDateExist = groupService.getUserExistGroupRoleOutDate(gp, usermodel.getId());
		if (!userGroupRoleExist) {// 已被删除链接失效
			model.addAttribute("tipsMessage", "小组管理员已撤销邀请!");
			return "modules/group/group_email_link";
		}
		if (userGroupRoleOutDateExist) {// 邀请已过期
			model.addAttribute("tipsMessage", "邀请已过期!");
			return "modules/group/group_email_link";
		} else {
			// 准备进入系统
			clearCookie(response);
			setCookie(request, response, usermodel, ur);
			List<SurveyUserItems> surveyUserList = surveyService.getSurveyUserItems(usermodel.getId(), gp);

			if (null != surveyUserList && surveyUserList.size() > 0) {
				if (!userExist) {
					return this.redirectPage(
							"/group/setPwd.html?gp=" + gp + "&userId=" + usermodel.getId() + "&um=" + um + "&ur=" + ur);
				} else {
					if(usermodel!=null) {
						Group group=groupService.getGroupInfoByGroupId(gp);
						loginService.setLoginInfo(usermodel,group.getAccountId(),response);
					}
					if (ur.equals(DConstants.GROUP_ROLE_GROUP_LEADER_ID)) {
						return this.redirectPage("/group/profile.html?groupId=" + gp);
					}
					if (ur.equals(DConstants.GROUP_ROLE_CONTENT_CREATOR_ID)) {
						return this.redirectPage("/group/features.html?groupId=" + gp);
					}
					if (ur.equals(DConstants.GROUP_ROLE_VIEWER_ID)) {
						return this.redirectPage("/group/intro.html?groupId=" + gp);
					} else {
						return this.redirectPage("/login.html");
					}
				}
			} else {
				List<Map<String, Object>> surveyList = surveyService.getSurveyResultByUserId(gp, usermodel.getId());
				model.addAttribute("menu", 7);
				model.addAttribute("groupId", gp);
				model.addAttribute("um", DesUtil.decrypt(um));
				model.addAttribute("surveyList", surveyList);
				model.addAttribute("userExist", userExist);
				model.addAttribute("ur", ur);
				return "modules/group/group_form_register";
			}
		}
	}

	@RequestMapping("/group/insertSurveyResult" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel insertSurveyResult(@RequestParam String dataStr, @RequestParam String groupId,
			@RequestParam String um, HttpServletRequest request,HttpServletResponse response) {
		AjaxResultModel arm = new AjaxResultModel();
		// 1_#_11111_#_1_|_2_|_3_@_2_#_22222_#_1_|_2_|_3_@_3_#_33333_#_1_|_2
		String[] dataArrays = dataStr.split(DConstants.DELIMITER_ROW);
		UserModel model = userService.queryUserInfoByUserName(um, DConstants.USE_REGISTER_TYPE_MAIL);
		surveyService.insertUserSurveryForGroup(dataArrays, groupId, model.getId());
		groupService.updateEmailGroupUserStatus(model.getId(), DConstants.STATUS_ONE, groupId);
		if(model!=null) {
			Group group=groupService.getGroupInfoByGroupId(groupId);
			loginService.setLoginInfo(model,group.getAccountId(),response);
		}
		arm.setData(model);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/group/getGroupNameNextNumber" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getGroupNameNextNumber(Model model, String groupId) {
		AjaxResultModel arm = new AjaxResultModel();
		int num = 1;
		List<Integer> numList = new ArrayList<Integer>();
		Group groupModel = groupService.getGroupInfoByGroupId(groupId);
		List<Map<String, Object>> sameGrouplist = groupService.getAllGroupByGroupName(groupModel.getGroupName());
		if (sameGrouplist.size() > 1) {
			String rex = "[()]+";
			for (int i = 0; i < sameGrouplist.size(); i++) {
				String groupName = (String) sameGrouplist.get(i).get("group_name");
				String[] str = groupName.split(rex);
				if (null != str && str.length > 1) {
					numList.add(Integer.parseInt(str[str.length - 1]) + 1);
				}
			}
			Collections.sort(numList);
			num = numList.get(numList.size() - 1);
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("num", num);
		jsonMap.put("groupModel", groupModel);
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	@RequestMapping("/group/validateGroupRoleExist" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel validateGroupRoleExist(Model model, String groupId,String userName) {
		AjaxResultModel arm = new AjaxResultModel();
		UserModel userModel=registerService.getUserModel(userName);
		if(null!=userModel){
		   arm.setData(groupService.getUserExistGroupRole(groupId, userModel.getId()));
		}else{
		   arm.setData(false);	
		}
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	private void clearCookie(HttpServletResponse response) {
		CookieUtils.deleteCookie(response, DConstants.COOKIE_USER_ID, CommonUtils.COOKIE_DOMAIN);
		CookieUtils.deleteCookie(response, DConstants.COOKIE_NICK_NAME, CommonUtils.COOKIE_DOMAIN);
		CookieUtils.deleteCookie(response, DConstants.COOKIE_GROUP_ROLE_ID, CommonUtils.COOKIE_DOMAIN);
		CookieUtils.deleteCookie(response, DConstants.COOKIE_USER_NAME, CommonUtils.COOKIE_DOMAIN);
	}

	private void setCookie(HttpServletRequest request, HttpServletResponse response, UserModel userModel, String ur)
			throws UnsupportedEncodingException {
		CookieUtils.setCookie(DConstants.COOKIE_USER_ID, userModel.getId(), 604800, response,
				CommonUtils.COOKIE_DOMAIN);
		if (StringUtils.isNotBlank(userModel.getNickName()) && !"null".equals(userModel.getNickName())) {
			CookieUtils.setCookie(DConstants.COOKIE_NICK_NAME, URLEncoder.encode(userModel.getNickName(), "UTF-8"),
					604800, response, CommonUtils.COOKIE_DOMAIN);
		} else {
			CookieUtils.setCookie(DConstants.COOKIE_NICK_NAME, "", 604800, response, CommonUtils.COOKIE_DOMAIN);
		}
		request.getSession().removeAttribute(DConstants.USER_ACCOUNT_LIST_SIZE);
		Integer accountNum = groupService.getAccountNum(userModel.getId());
		request.getSession().setAttribute(DConstants.USER_ACCOUNT_LIST_SIZE, accountNum);
		if (StringUtils.isNotBlank(userModel.getUserName())) {
			CookieUtils.setCookie(DConstants.COOKIE_USER_NAME, URLEncoder.encode(userModel.getUserName(), "UTF-8"),
					604800, response, CommonUtils.COOKIE_DOMAIN);
		} else {
			CookieUtils.setCookie(DConstants.COOKIE_USER_NAME, "", 604800, response, CommonUtils.COOKIE_DOMAIN);
		}
		CookieUtils.setCookie(DConstants.COOKIE_GROUP_ROLE_ID, ur, 604800, response, CommonUtils.COOKIE_DOMAIN);
	}
}
