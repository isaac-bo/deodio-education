package com.deodio.elearning.modules.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.commons.service.RegionService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.user.service.UserService;
import com.deodio.elearning.persistence.mapper.AccountMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.utils.CommonUtils;

@Controller
public class UserController extends BaseController {

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private UserModelMapper userModelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping("/users/profile"+Constants.URL_SUFFIX)
	public String toUserProfile(Model model,HttpServletRequest request,HttpServletResponse response){
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		if(StringUtils.isNotBlank((String)request.getParameter("accountId"))){
			CookieUtils.setCookie(DConstants.COOKIE_ACCOUNT_ID, (String)request.getParameter("accountId"),  86400, response,CommonUtils.COOKIE_DOMAIN);
			accountId = (String)request.getParameter("accountId");
		}
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		Account accountModel = accountMapper.selectByPrimaryKey(accountId);
		if(accountModel.getCreateId().equals(userId)){
			return redirectPage("/account/profile.html?accountId="+accountId);
		}else{

			model.addAttribute("userModel", userModelMapper.selectByPrimaryKey(userId));
			model.addAttribute("uKeyId", userId);
			model.addAttribute("accountId", accountId);
			model.addAttribute("isAccountOwner", Boolean.FALSE);
			return "/modules/account/account_profile";
		}
	}
	
	@RequestMapping("/user/profile"+Constants.URL_SUFFIX)
	public String toAccountLayer(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required=false) String accountId){
		if (accountId==null) {
			accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		}
		if(StringUtils.isNotBlank((String)request.getParameter("accountId"))){
			CookieUtils.setCookie(DConstants.COOKIE_ACCOUNT_ID, (String)request.getParameter("accountId"),  86400, response,CommonUtils.COOKIE_DOMAIN);
			accountId = (String)request.getParameter("accountId");
		}
		String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		Account accountModel = accountMapper.selectByPrimaryKey(accountId);
		if(StringUtils.isNotBlank(accountModel.getOwnerId())&& accountModel.getOwnerId().equals(userId)){
			return redirectPage("/account/layer.html?accountId="+accountId);
		}else{
			UserModel userModel = userService.queryUserInfoById(userId);
			
			resetUserIcon(userModel);
			
			model.addAttribute("userModel", userModel);
			
			if(userModel.getAddressCountry()!=null){
				Integer countryId = userModel.getAddressCountry();
				model.addAttribute("countryVal", countryId);
				model.addAttribute("provinceList",regionService.getDicProvinceModelList(countryId));
			}
			if(userModel.getAddressProvince()!=null){
				Integer provinceId = userModel.getAddressProvince();
				model.addAttribute("provinceVal", provinceId);
				model.addAttribute("cityVal", userModel.getAddressCity());
				model.addAttribute("cityList", regionService.getDicCityModelList(provinceId));
			}
			//获取身份证图片信息
			Map<String,String> attachmentUserIdCardMap = new HashMap<String,String>(); 
			attachmentUserIdCardMap.put("relId", userId);
			attachmentUserIdCardMap.put("generateName", userModel.getIdCardSnapShot());
			attachmentUserIdCardMap.put("attachType", DConstants.ATTACH_TYPE_ID_CARD_IMG);
			Attachment attachmentUserIdCard =  attachmentService.queryItemsRelAttachement(attachmentUserIdCardMap);
			model.addAttribute("attachmentUserIdCard", attachmentUserIdCard);
			
			model.addAttribute("accountDomain", accountService.querySubdomain(accountId));
			model.addAttribute("uKeyId", userId);
			model.addAttribute("accountId", accountId);
			model.addAttribute("isAccountOwner", Boolean.FALSE);
			model.addAttribute("countryList", regionService.getDicCountryModelList());
			
			return "/modules/account/account_layer";
		}
	}

	private void resetUserIcon(UserModel userModel) {
		Map<String,String> attachmentUserIconMap = new HashMap<String,String>(); 
		attachmentUserIconMap.put("relId", userModel.getId());
		attachmentUserIconMap.put("generateName", userModel.getUserIcon());
		attachmentUserIconMap.put("attachType",DConstants.ATTACH_TYPE_HEADER);
		Attachment attachmentUserIcon =  attachmentService.queryItemsRelAttachement(attachmentUserIconMap);
		String userIcon = StringUtils.EMPTY;
		String attachUrl = attachmentUserIcon.getAttachUrl();
		String generateName = attachmentUserIcon.getGenerateName();
		if(StringUtils.isNotBlank(attachUrl) && StringUtils.isNotBlank(generateName)){
			userIcon = attachmentUserIcon.getAttachUrl() + attachmentUserIcon.getGenerateName();
		}
		userModel.setUserIcon(userIcon);
	}
	
	
	@RequestMapping("/user/update_profile"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updateUserInfo(@RequestParam String uKeyId,@RequestParam String nickName,
			@RequestParam String userMail,@RequestParam String lastName,
			@RequestParam String firstName,@RequestParam Integer gender,
			@RequestParam String mobilePhone,@RequestParam String telNumber){
		AjaxResultModel arm = new AjaxResultModel();
		
		try {
			userService.updateUsrInfoById(nickName,userMail,lastName,firstName,gender,mobilePhone,telNumber,uKeyId);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
	
	@RequestMapping("/user/getUserInfoByUserName"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getUserInfoByUserName(@RequestParam String userName,
			@RequestParam(required=false) String registerType){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			short registerTypeNum = StringUtils.isBlank(registerType) ? 0 : Short.parseShort(registerType);
			arm.setData(userService.queryUserInfoByUserName(userName, registerTypeNum));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	} 
}
