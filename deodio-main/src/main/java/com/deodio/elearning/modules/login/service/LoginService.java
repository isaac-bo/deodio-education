package com.deodio.elearning.modules.login.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.persistence.mapper.AccountMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.UserCustomizeMapper;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AccountExample;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.UserModelExample;
import com.deodio.elearning.utils.CommonUtils;

@Service
public class LoginService {
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private UserCustomizeMapper userCustomizeMapper;
	
	@Autowired
	private UserModelMapper userModelMapper;
	
	@Autowired	
	private AttachmentService attachmentService;
	
	@Autowired	
	private AccountService accountService;
	
	
	/**
	 * 通过用户密码登录
	 * @Title: getLoginUserName
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws DeodioException	
	 * @return boolean
	 */
	public UserModel getLoginUserName(String userName,String passWord)throws DeodioException{
		UserModelExample example = new UserModelExample();
		example.createCriteria().andUserNameEqualTo(userName).andPassWordEqualTo(passWord);
		example.or().andUserMailEqualTo(userName).andPassWordEqualTo(passWord);
		example.or().andMobilePhoneEqualTo(userName).andPassWordEqualTo(passWord);
		
		List<UserModel>  userModle = userModelMapper.selectByExample(example);
		if(userModle.isEmpty()){
			return null;
		}else{
			return userModle.get(0);
		}
	}
	/**
	 * 获取account账户选择的课程类别数目
	 * @Title: getclassficationNum
	 * @param accountId
	 * @return
	 * @throws DeodioException	
	 * @return Integer
	 */
	public Integer getclassficationNum(String accountId)throws DeodioException{
		return userCustomizeMapper.getclassficationNum(accountId);
	}
	public Integer getAccountNum(String id) {
		// TODO Auto-generated method stub
		return userCustomizeMapper.getAccountNum(id);
	}
	public Account getAccountInfo(String userId) {
		// TODO Auto-generated method stub
		AccountExample example=new AccountExample();
		example.createCriteria().andOwnerIdEqualTo(userId);
		List<Account> accountList= accountMapper.selectByExample(example);
		if(accountList.isEmpty()){
			return null;
		}else{
			return accountList.get(0);
		}
	}
	public UserModel getUseModel(String userId) {
		// TODO Auto-generated method stub		
		return userModelMapper.selectByPrimaryKey(userId);
	}
	public void setLoginInfo(UserModel userModel,String accountId,HttpServletResponse response) {

		String nickName = userModel.getNickName();
		String userName = userModel.getUserName();
		if(StringUtils.isBlank(nickName)){
			nickName = StringUtils.EMPTY;
		}
		if(StringUtils.isBlank(userName)){
			userName = StringUtils.EMPTY;
		}
		
		Map<String,String> attachmentUserIconMap = new HashMap<String,String>(); 
		attachmentUserIconMap.put("relId", userModel.getId());
		attachmentUserIconMap.put("generateName", userModel.getUserIcon());
		attachmentUserIconMap.put("attachType", DConstants.ATTACH_TYPE_HEADER);
		Attachment attachmentUserIcon =  attachmentService.queryItemsRelAttachement(attachmentUserIconMap);
		String userIconUrl = StringUtils.EMPTY;
		short userType = DConstants.USER_TYPE_PERSONAL;
		if(attachmentUserIcon != null) {
//			String attachDir = attachmentUserIcon.getAttachDir();
			String attachUrl=attachmentUserIcon.getAttachUrl();
			String attachGenerateName = attachmentUserIcon.getGenerateName();
//			if(StringUtils.isNotBlank(attachDir)&& StringUtils.isNotBlank(attachGenerateName)){
//				userIconUrl = attachDir + attachGenerateName;
//			}
			if(StringUtils.isNotBlank(attachUrl)&& StringUtils.isNotBlank(attachGenerateName)){
				userIconUrl = attachUrl + attachGenerateName;
			}
		}

		
		Account accountModel = accountService.getAccountInfoByOwnerId(userModel.getId());
		
		CookieUtils.setCookie(DConstants.COOKIE_USER_ID, userModel.getId(), 604800, response,CommonUtils.COOKIE_DOMAIN);
		try {
			CookieUtils.setCookie(DConstants.COOKIE_USER_NAME, URLEncoder.encode(userName,"UTF-8"), 604800, response,CommonUtils.COOKIE_DOMAIN);
			CookieUtils.setCookie(DConstants.COOKIE_NICK_NAME, URLEncoder.encode(nickName,"UTF-8"), 604800, response,CommonUtils.COOKIE_DOMAIN);
			CookieUtils.setCookie(DConstants.COOKIE_USER_HEADER_PIC, URLEncoder.encode(userIconUrl,"UTF-8"), 604800, response,CommonUtils.COOKIE_DOMAIN);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(accountModel != null && StringUtils.isNotBlank(accountModel.getId())){
//			//获取account审核状态
//			System.err.println(getClass().getName()+"\taccount_valid_status:\t"+accountModel.getIsValid());
//			if (accountModel.getIsValid()==DConstants.ACCOUNT_STATUS_APPROVED_SHORT) {//审核通过
//				CookieUtils.setCookie(DConstants.ACCOUNT_STATUS, DConstants.ACCOUNT_STATUS_APPROVED_STRING, 604800, response,CommonUtils.COOKIE_DOMAIN);
//			}else {//审核未通过
//				CookieUtils.setCookie(DConstants.ACCOUNT_STATUS, DConstants.ACCOUNT_STATUS_APPROVING_STRING, 604800, response,CommonUtils.COOKIE_DOMAIN);
//			}
			if (com.deodio.core.utils.StringUtils.isEmpty(accountId)) {
				accountId = accountModel.getId();
			}
			userType = DConstants.USER_TYPE_COMPANY;
		}
		//用户类型  
		CookieUtils.setCookie(DConstants.COOKIE_USER_TYPE, String.valueOf(userType), 604800, response,CommonUtils.COOKIE_DOMAIN);
		CookieUtils.setCookie(DConstants.COOKIE_ACCOUNT_ID, accountId, 604800, response,CommonUtils.COOKIE_DOMAIN);
		CookieUtils.setCookie(DConstants.USER_SELF_ACCOUNT_ID, accountId, 604800, response,CommonUtils.COOKIE_DOMAIN);
		//cookie 特殊字符处理
		//保存注册类型
		CookieUtils.setCookie(DConstants.COOKIE_USER_REGISTER_TYPE, userModel.getRegisterType().toString(), 604800, response,CommonUtils.COOKIE_DOMAIN);

	}
	
}
