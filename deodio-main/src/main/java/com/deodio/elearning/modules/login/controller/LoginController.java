package com.deodio.elearning.modules.login.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
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
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.login.service.LoginService;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.utils.CommonUtils;

import flex.messaging.util.URLDecoder;

@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	
	/**
	 * 等出
	 * @Title: toLoginPage
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/logout"+Constants.URL_SUFFIX)
	public String toLogoutPage(HttpServletRequest request,HttpServletResponse response){
		String redirectPage="redirect:/login.html";
		CookieUtils.clearCookie(request, response, "/",CommonUtils.COOKIE_DOMAIN);
		request.getSession().removeAttribute(DConstants.USER_ACCOUNT_LIST_SIZE);
		request.getSession().removeAttribute(DConstants.USER_CURRENT_GROUP_ID);
		//删除seesion中角色信息
		request.getSession().removeAttribute("roleListSession");
		return redirectPage;
	}
	
	/**
	 * 进入登录页面
	 * @Title: toLoginPage
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/login"+Constants.URL_SUFFIX)
	public String toLoginPage(HttpServletRequest request,HttpServletResponse response){
		String redirectPage="/modules/login/login";
		try {
			String password = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_PASS);
			if(password!=null){
				String userName=URLDecoder.decode(String.valueOf(CookieUtils.getCookie(request, DConstants.COOKIE_USER_NAME)),"UTF-8");
				System.err.println(getClass().getName()+"\t userName :\t"+userName);
				//验证cookie 密码与数据库密码是否一致
				boolean flag = this.getUserLogin(userName, response, false, password,request);
				if(!flag){
					CookieUtils.clearCookie(request, response, "/",CommonUtils.COOKIE_DOMAIN);
				}else{
					redirectPage="redirect:/account/list.html";
				}
			}
			String jsessionId = request.getSession().getId();
			CookieUtils.setCookie(DConstants.COOKIE_JSESSION_ID,jsessionId, 604800, response,CommonUtils.COOKIE_DOMAIN);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		return redirectPage;
	}
	
	/**
	 * 忘记密码
	 * @Title: toForgetPasswordPage
	 * @param hidStep
	 * @param userName
	 * @param model
	 * @return
	 * @return String
	 */
	@RequestMapping("/signin/forget_pwd"+Constants.URL_SUFFIX)
	public String toForgetPasswordPage(@RequestParam(required = false, value = "s") String hidStep,
				@RequestParam(required = false, value = "userName") String userName, @RequestParam(required = false, value = "uKeyId") String uKeyId,
				Model model) {
		if (StringUtils.isNotBlank(hidStep)) {

			model.addAttribute("userName", userName);
			model.addAttribute("hidStep", "1");
		}
		
		return "/modules/login/forget_password";
	}
	
	@RequestMapping("/signin/forget_pwd_2"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toForgetPasswordPage2(@RequestParam(required = false, value = "s") String hidStep,
			@RequestParam(required = false, value = "userName") String userName, @RequestParam(required = false, value = "uKeyId") String uKeyId){
		AjaxResultModel arm = new AjaxResultModel();
		
		try {
			if ("2".equals(hidStep)) {
				UserModel userModel = accountService.getUserModeInforByCondition(userName);
				arm.setData(userModel);
				arm.setStatus(AjaxResultModel.SUCCESS);
			}
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		
		return arm;
	}
	
	@RequestMapping("/signin/forget_pwd_3"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel toForgetPasswordPage3(@RequestParam(required = false, value = "s") String hidStep,
			@RequestParam(required = false, value = "userName") String userName, @RequestParam(required = false, value = "uKeyId") String uKeyId){
		AjaxResultModel arm = new AjaxResultModel();
		
		try {
			if ("3".equals(hidStep)) {
				UserModel userModel = accountService.getUserModeInforByCondition(userName);
				arm.setData(userModel);
				arm.setStatus(AjaxResultModel.SUCCESS);
			}
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		
		return arm;
	}
	
	/**
	 * 密码修改
	 * @Title: updatePassWord
	 * @param uKeyId
	 * @param password
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/signin/update_pwd"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel updatePassWord(@RequestParam String uKeyId,@RequestParam String password){
		AjaxResultModel arm = new AjaxResultModel();
		
		try {
			accountService.updateUserPassWord(uKeyId, DigestUtils.md5DigestAsHex(password.getBytes()));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		
		return arm;
	}
	
	
	
	
	/**
	 * 用户登录
	 * @Title: userLogin
	 * @param userName
	 * @param passWord
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/login/submit"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel userLogin(@RequestParam String userName,
			@RequestParam String passWord,@RequestParam Boolean isRemeber,
			HttpServletResponse response,HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
			try {
//				boolean passStatus = false;
				String	password = DigestUtils.md5DigestAsHex(passWord.getBytes());
				boolean flag = getUserLogin(userName, response, isRemeber, password,request);			
				arm.setData(flag);
				arm.setStatus(AjaxResultModel.SUCCESS);
			} catch (DeodioException e) {
				arm.setStatus(AjaxResultModel.FAIL);
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return arm;
	}
	/**
	 * account账户登录时检测课程类别
	 * @Title: checkClassfication
	 * @param userName
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/login/checkClassfication"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel checkClassfication(@RequestParam String userName,
			HttpServletRequest request,HttpServletResponse response){
		AjaxResultModel arm = new AjaxResultModel();
		//emailVerifyFlag表示邮箱注册方式的account账号是否通过了邮箱验证
		boolean emailVerifyFlag=true;
		//falg表示account账户是否已经选择过课程类别
		boolean flag = false;
		//validFlag表示account账号是否审核通过
		boolean validFlag=false;
		//accountType 表示用户的身份
		String accountType;
		//获取account信息
		String userId=getCookieUserId(request);
		Account account=loginService.getAccountInfo(userId);
		Map<String, Object> accountInfo=new HashMap<String, Object>();
		//account为空表示为普通用户
		if (account==null) {
			flag=true;//true表示通过
			validFlag=true;//true表示通过
			accountType="user";
		}else {
			accountType="account";
			//判断account账户是否选择过课程分类
			Integer classficationNum=loginService.getclassficationNum(account.getId());
			if(classficationNum>0) {
				flag=true;//true表示通过
			}else {
				flag=false;//false表示不通过
			}
			//判断account账号是否验证通过,0表示不通过,1表示通过
			if(account.getIsValid()==1) {
				validFlag=true;//true表示通过
			}
			//获取注册方式
			UserModel userModel=loginService.getUseModel(userId);
			Short registerType=userModel.getRegisterType();
			if(registerType==DConstants.USE_REGISTER_TYPE_MAIL) {
				//判断通过邮箱注册的account账号,是否邮箱验证通过,0表示不通过,1表示通过			
				if(userModel.getIsCheckMail()==0) {
					emailVerifyFlag=false;//false表示未进行邮箱验证
				}
			}		
		}
		Map<String, Object> accountMap = accountService.getLoginAccountList(userId);
		// completeSelfInfo为true表示个人信息验证通过
		boolean completeSelfInfo = accountService.checkCompleteSelfInfo(userId);
		// isCreateGroup为true表示创建小组验证通过
		boolean isCreateGroup = accountService.checkAddInOrCreateGroup(userId);
		if (completeSelfInfo&&isCreateGroup) {
			CookieUtils.setCookie(DConstants.USER_CHANGE_ACCOUNT_TO, DConstants.USER_CHANGE_ACCOUNT_TO_OTHER, 604800, response,CommonUtils.COOKIE_DOMAIN);
		}else {
			CookieUtils.setCookie(DConstants.USER_CHANGE_ACCOUNT_TO, DConstants.USER_CHANGE_ACCOUNT_TO_SELF, 604800, response,CommonUtils.COOKIE_DOMAIN);
		}
		accountInfo.put("userid", userId);
		accountInfo.put("flag", flag);
		accountInfo.put("valid", validFlag);
		accountInfo.put("emailVerifyFlag", emailVerifyFlag);
		accountInfo.put("accountMap", accountMap);
		accountInfo.put("accountType", accountType);
//
		accountInfo.put("completeSelfInfo", completeSelfInfo);
//		accountInfo.put("isCreateGroup", isCreateGroup);
		System.err.println(getClass().getName()+"\taccountInfo:\t"+accountInfo.toString());
		arm.setData(accountInfo);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}


	private boolean getUserLogin(String userName, HttpServletResponse response,
			boolean passStatus, String password,HttpServletRequest request) throws UnsupportedEncodingException {
		UserModel userModel = loginService.getLoginUserName(userName, password);
		boolean flag=false;
		String accountId = StringUtils.EMPTY;
		if(userModel!=null){
			flag=true;
			loginService.setLoginInfo(userModel,accountId,response);
			if(passStatus){
				CookieUtils.setCookie(DConstants.COOKIE_USER_PASS, password, 604800, response,CommonUtils.COOKIE_DOMAIN);
				CookieUtils.setCookie(DConstants.COOKIE_USER_NAME, userName, 604800, response,CommonUtils.COOKIE_DOMAIN);
			}
		}
		return flag;
	}
	
}
