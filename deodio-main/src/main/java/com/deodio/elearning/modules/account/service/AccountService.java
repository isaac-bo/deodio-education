package com.deodio.elearning.modules.account.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.util.StringUtil;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.AccountAttachmentMapper;
import com.deodio.elearning.persistence.mapper.AccountCapabilityMapper;
import com.deodio.elearning.persistence.mapper.AccountMapper;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CompanyModelMapper;
import com.deodio.elearning.persistence.mapper.UserAccountRelMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.AccountCustomizeMapper;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AccountAttachment;
import com.deodio.elearning.persistence.model.AccountAttachmentExample;
import com.deodio.elearning.persistence.model.AccountCapability;
import com.deodio.elearning.persistence.model.AccountCapabilityExample;
import com.deodio.elearning.persistence.model.AccountExample;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CompanyModel;
import com.deodio.elearning.persistence.model.CompanyModelExample;
import com.deodio.elearning.persistence.model.GroupItemsExample;
import com.deodio.elearning.persistence.model.UserAccountRel;
import com.deodio.elearning.persistence.model.UserAccountRelExample;
import com.deodio.elearning.persistence.model.UserCompanyModelExt;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.UserModelExample;
import com.deodio.elearning.persistence.model.UserModelExt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class AccountService extends BaseService {

	@Autowired
	private UserModelMapper userModelMapper;

	@Autowired
	private CompanyModelMapper companyModelMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private AccountCustomizeMapper accountCustomizeMapper;

	@Autowired
	private AccountAttachmentMapper accountAttachmentMapper;

	@Autowired
	private AccountCapabilityMapper accountCapabilityMapper;

	@Autowired
	private UserAccountRelMapper userAccountRelMapper;

	/**
	 * 获取用户基本信息
	 * 
	 * @Title: getUserModeInforByUserName
	 * @param userNmae
	 * @return
	 * @throws DeodioException
	 * @return UserModel
	 */
	public UserModel getUserModeInforByCondition(String conditionStr) throws DeodioException {
		UserModelExample example = new UserModelExample();
		example.createCriteria().andUserNameEqualTo(conditionStr);
		example.or().andUserMailEqualTo(conditionStr);
		example.or().andMobilePhoneEqualTo(conditionStr);
		List<UserModel> userList = userModelMapper.selectByExample(example);
		if (userList.isEmpty()) {
			return null;
		} else {
			return userList.get(0);
		}
	}

	/**
	 * 根据用户ID获取用户基本信息
	 * 
	 * @Title: getUserModeInforByUserId
	 * @param userId
	 * @throws DeodioException
	 * @return UserModel
	 */
	public UserModel getUserModeInforByUserId(String userId) throws DeodioException {
		return userModelMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 修改用户登录密码
	 * 
	 * @Title: updateUserPassWord
	 * @param userId
	 * @param password
	 * @throws DeodioException
	 * @return void
	 */
	public void updateUserPassWord(String userId, String passWord) throws DeodioException {

		UserModel record = new UserModel();
		record.setId(userId);
		record.setPassWord(passWord);
		record.setUpdateTime(new Date());
		userModelMapper.updateByPrimaryKeySelective(record);

	}

	/**
	 * 模块：我的信息
	 * 
	 * @Title: getUserInfoByUserId
	 * @param userId
	 * @return
	 * @throws DeodioException
	 * @return UserModel
	 */
	public UserModel getUserInfoByUserId(String userId) throws DeodioException {
		return userModelMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 获取当前登录者 公司信息
	 * 
	 * @Title: getCompanyModelByUserId
	 * @param userId
	 * @return
	 * @throws DeodioException
	 * @return CompanyModel
	 */
	public CompanyModel getCompanyModelByUserId(String userId) throws DeodioException {
		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andCreateIdEqualTo(userId);
		List<CompanyModel> list = companyModelMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 获取当前公司信息
	 * 
	 * @Title: getCompanyModelByUserId
	 * @param userId
	 * @return
	 * @throws DeodioException
	 * @return CompanyModel
	 */
	public CompanyModel getCompanyModelByAccountId(String accountId) throws DeodioException {
		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<CompanyModel> list = companyModelMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 修改 修改 我的账号（个人企业账号t）
	 * 
	 * @param uKeyId
	 * @param extModelJson
	 */
	public void updateUserGroupInfo(String extModelJson, String accountId, String userId) throws DeodioException {

		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		;
		UserCompanyModelExt extModel = gson.fromJson(extModelJson, new TypeToken<UserCompanyModelExt>() {
		}.getType());
		UserModel orginialUser = userModelMapper.selectByPrimaryKey(userId);
		String orginalMobilePhone=orginialUser.getMobilePhone();
		//String userIconId = extModel.getUserIconId();
		String userIconName = extModel.getUserIconName();

		//String idCardImgId = extModel.getIdCardImgId();
		String idCardImgName = extModel.getIdCardImgName();

		UserModel userModel = new UserModel();
		// userModel.setNickName(extModel.getNickName());
		userModel.setUserName(extModel.getUserName());
		userModel.setUserGender(extModel.getUserSetGender());
		userModel.setMobilePhone(extModel.getUserMobile());
		userModel.setTelNumber(extModel.getUserTel());
		userModel.setId(userId);
		userModel.setUserIcon(userIconName);
		userModel.setUpdateTime(new Date());
		userModel.setUpdateId(userId);
		userModel.setIsCheckTel(new Short("1"));
		// userModel.setIdCardCode(extModel.getUserIdCardCode());
		userModel.setIdCardSnapShot(idCardImgName);

		userModelMapper.updateByPrimaryKeySelective(userModel);

		// updateUserIconOrCardOrLicense(userId, userIconId);

		// updateUserIconOrCardOrLicense(userId, idCardImgId);

		//String businessLicenceImgId = extModel.getBusinessLicenceImgId();
		String businessLicenceImgName = extModel.getBusinessLicenceImgName();
		String id = extModel.getId();
		extModel.setCompanyBusinessLicenseSnapShot(businessLicenceImgName);
		extModel.setAccountId(accountId);

		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<CompanyModel> existData = companyModelMapper.selectByExample(example);

		if (existData.isEmpty()) {
			id = AppUtils.uuid();
			extModel.setId(id);
			extModel.setUpdateId(userId);
			extModel.setUpdateTime(new Date());
			extModel.setCreateId(userId);
			extModel.setCreateTime(new Date());
			extModel.setCompanyOperationId(extModel.getUserIdCardCode());
			extModel.setCompanyOperationName(extModel.getNickName());
			extModel.setCompanyOperationMobile(extModel.getUserMobile());
			extModel.setCompanyOperationTelephone(extModel.getUserTel());
			companyModelMapper.insert(extModel);
		} else {
			id = existData.get(0).getId();
			extModel.setId(id);
			extModel.setUpdateId(userId);
			extModel.setUpdateTime(new Date());
			extModel.setCompanyOperationId(extModel.getUserIdCardCode());
			extModel.setCompanyOperationName(extModel.getNickName());
			extModel.setCompanyOperationMobile(extModel.getUserMobile());
			extModel.setCompanyOperationTelephone(extModel.getUserTel());
			isUpdateLoginCheck(accountId, userId, extModel, userModel, existData,orginalMobilePhone);

			companyModelMapper.updateByExampleSelective(extModel, example);
		}

		// updateUserIconOrCardOrLicense(id, businessLicenceImgId);
		// 更新二级域名
		if (accountId != null && !accountId.equals("")) {
			Account record = new Account();
			record.setId(accountId);
			record.setAccountDomain(extModel.getAccountDomain());
			accountMapper.updateByPrimaryKeySelective(record);
		}
	}

	/**
	 * 是否修改account登录有效性
	 */
	private void isUpdateLoginCheck(String accountId, String userId, UserCompanyModelExt extModel, UserModel userModel,
			 List<CompanyModel> existData,String orginalMobilePhone) {

		// 判断运营者身份和营业执照信息是否有改动
		if (isUpdateBusinessLicenseSnapShot(extModel, userModel, existData)
				|| isUpdateIdCardSnapShot(userModel, userId,orginalMobilePhone)) {
			// 修改account登录有效性
			Account record = new Account();
			record.setId(accountId);
			record.setIsValid(DConstants.LOGIN_IS_VALID_NO);
			record.setIsNeedCheck(DConstants.LOGIN_IS_NEED_CHECK_YES);
			accountMapper.updateByPrimaryKeySelective(record);
		}
	}

	/**
	 * 营业执照是否被修改
	 */
	private boolean isUpdateBusinessLicenseSnapShot(UserCompanyModelExt extModel, UserModel userModel,
			List<CompanyModel> existData) {
		boolean status = false;
		if (existData.isEmpty()) {
			return status;
		}

		CompanyModel commodel = new CompanyModel();
		commodel = existData.get(0);
		if (StringUtils.isNotBlank(extModel.getCompanyBusinessLicenseSnapShot())
				&& !extModel.getCompanyBusinessLicenseSnapShot().equals(commodel.getCompanyBusinessLicenseSnapShot())
				|| (StringUtils.isNotBlank(extModel.getCompanyBusinessLicenseCode()) &&
				!extModel.getCompanyBusinessLicenseCode().equals(commodel.getCompanyBusinessLicenseCode()))
				||(StringUtils.isNotBlank(extModel.getCompanyOrganizationCode()) &&
						!extModel.getCompanyOrganizationCode().equals(commodel.getCompanyOrganizationCode()))
				||(StringUtils.isNotBlank(extModel.getCompanyOperationName()) &&
						!extModel.getCompanyOperationName().equals(commodel.getCompanyOperationName()))
				||(StringUtils.isNotBlank(extModel.getCompanyOperationId()) &&
						!extModel.getCompanyOperationId().equals(commodel.getCompanyOperationId()))
				||(StringUtils.isNotBlank(extModel.getCompanyOperationPosition()) &&
						!extModel.getCompanyOperationPosition().equals(commodel.getCompanyOperationPosition()))
				  ) {
			// 修改account登录有效性
			status = true;
		}
		return status;
	}

	/**
	 * 身份证件是否被修改
	 */
	private boolean isUpdateIdCardSnapShot(UserModel userModel, String userId,String orginalMobilePhone) {
		boolean status = false;
		UserModelExample example = new UserModelExample();
		example.createCriteria().andIdEqualTo(userId);
		List<UserModel> existUserData = userModelMapper.selectByExample(example);
		if (existUserData.isEmpty()) {
			return status;
		}

		UserModel model = existUserData.get(0);
		;
		if (!userModel.getIdCardSnapShot().equals(model.getIdCardSnapShot())
				||!userModel.getMobilePhone().equals(orginalMobilePhone)) {
			// 修改account登录有效性
			status = true;
		}
		return status;
	}

	/**
	 * 注册成功-更新详细基本信息
	 * 
	 * @Title: updateUserSigninDetail
	 * @param extModel
	 * @return void
	 */
	public void updateUserSigninDetail(String extModelJson, String accountId) throws DeodioException {

		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		;
		UserCompanyModelExt extModel = gson.fromJson(extModelJson, new TypeToken<UserCompanyModelExt>() {
		}.getType());

		String userId = extModel.getUserId();
		String userIconId = extModel.getUserIconId();
		String userIconName = extModel.getUserIconName();

		String idCardImgId = extModel.getIdCardImgId();
		String idCardImgName = extModel.getIdCardImgName();

		UserModel userModel = new UserModel();
		userModel.setNickName(extModel.getNickName());
		userModel.setUserGender(extModel.getUserSetGender());
		userModel.setMobilePhone(extModel.getUserMobile());
		userModel.setTelNumber(extModel.getUserTel());
		userModel.setId(userId);
		userModel.setUserIcon(userIconName);
		userModel.setUpdateTime(new Date());
		userModel.setUpdateId(userId);
		userModel.setIdCardCode(extModel.getUserIdCardCode());
		userModel.setIdCardSnapShot(idCardImgName);

		userModelMapper.updateByPrimaryKeySelective(userModel);

		updateUserIconOrCardOrLicense(userId, userIconId);

		updateUserIconOrCardOrLicense(userId, idCardImgId);

		String businessLicenceImgId = extModel.getBusinessLicenceImgId();
		String businessLicenceImgName = extModel.getBusinessLicenceImgName();
		String id = extModel.getId();
		extModel.setCompanyBusinessLicenseSnapShot(businessLicenceImgName);
		extModel.setAccountId(accountId);

		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<CompanyModel> existData = companyModelMapper.selectByExample(example);

		if (existData.isEmpty()) {
			id = AppUtils.uuid();
			extModel.setId(id);
			extModel.setCreateId(userId);
			extModel.setCreateTime(new Date());
			companyModelMapper.insertSelective(extModel);
		} else {
			id = existData.get(0).getId();
			extModel.setId(id);
			extModel.setUpdateId(userId);
			extModel.setUpdateTime(new Date());
			companyModelMapper.updateByExampleSelective(extModel, example);
		}

		updateUserIconOrCardOrLicense(id, businessLicenceImgId);
	}

	/**
	 * 更新图片（头像，身份证，营业执照）
	 * 
	 * @Title: updateUserIconOrCardOrLicense
	 * @param userId
	 * @param userId
	 * @return void
	 */
	private void updateUserIconOrCardOrLicense(String userId, String iconId) {
		// 更新图片
		if (StringUtils.isNotBlank(iconId)) {
			Attachment attachmentUserIcon = new Attachment();
			attachmentUserIcon.setId(iconId);
			attachmentUserIcon.setRelId(userId);
			attachmentMapper.updateByPrimaryKeySelective(attachmentUserIcon);
		}
	}

	/**
	 * 企业邮箱和电话认证状态修改
	 * 
	 * @Title: updateCheckMail
	 * @param accountId
	 * @param cm
	 * @param type(mail,tel)
	 * @return void
	 */
	public void updateCheckMailAndTel(String accountId, Short cm, String type) throws DeodioException {

		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andAccountIdEqualTo(accountId);

		CompanyModel record = new CompanyModel();
		if (type.equals("mail")) {
			record.setIsCheckCompanyEmail(cm);
		} else {
			record.setIsCheckCompanyTel(cm);
		}

		companyModelMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 二级域名是否重复
	 * 
	 * @Title: getValidateHostExists
	 * @param subdomain
	 * @return
	 * @return boolean
	 */
	public boolean getValidateHostExists(String subdomain, String accountId) throws DeodioException {

		AccountExample example = new AccountExample();
		example.createCriteria().andAccountDomainEqualTo(subdomain).andIdNotEqualTo(accountId);

		List<Account> list = accountMapper.selectByExample(example);
		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 身份证号是否重复
	 * 
	 * @Title: getValidateIdCardCodeExists
	 * @param idCardCode
	 * @return
	 * @return boolean
	 */
	public boolean getValidateIdCardCodeExists(String idCardCode, String userId) throws DeodioException {

		UserModelExample example = new UserModelExample();
		example.createCriteria().andIdCardCodeEqualTo(idCardCode).andIdNotEqualTo(userId);

		List<UserModel> list = userModelMapper.selectByExample(example);
		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 查询二级域名
	 * 
	 * @param accountId
	 * @return
	 * @return AjaxResultModel
	 */
	public String querySubdomain(String accountId) throws DeodioException {
		String subdomain = "";
		Account accountInfo = accountMapper.selectByPrimaryKey(accountId);
		if (accountInfo != null) {
			subdomain = accountInfo.getAccountDomain();
		}
		return subdomain;
	}

	/**
	 * 修改二级域名(账号拥有者修改)
	 * 
	 * @Title: udpateSubdoMain
	 * @param subdomain
	 * @param uKeyId
	 * @return
	 * @return AjaxResultModel
	 */
	public int udpateSubdomain(String subdomain, String uKeyId, String accountId) throws DeodioException {
		AccountExample example = new AccountExample();
		example.createCriteria().andOwnerIdEqualTo(uKeyId).andIdEqualTo(accountId);
		Account record = new Account();
		record.setAccountDomain(subdomain);
		return accountMapper.updateByExampleSelective(record, example);

	}

	/**
	 * 获得Account信息
	 * 
	 * @Title: getAccountModleByCreateId
	 * @param uKeyId
	 * @return
	 * @throws DeodioException
	 * @return Account
	 */
	public Account getAccountModleByCreateId(String uKeyId) throws DeodioException {
		AccountExample example = new AccountExample();
		example.createCriteria().andCreateIdEqualTo(uKeyId);
		List<Account> list = accountMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 获取用户的account信息
	 * 
	 * @Title: getAccountInfo
	 * @param ownerId
	 * @return
	 * @throws DeodioException
	 * @return AccountModel
	 */
	public Account getAccountInfoByOwnerId(String ownerId) throws DeodioException {
		AccountExample example = new AccountExample();
		example.createCriteria().andOwnerIdEqualTo(ownerId);
		List<Account> accountList = accountMapper.selectByExample(example);
		if (accountList.isEmpty()) {
			return null;
		} else {
			return accountList.get(0);
		}
	}

	/**
	 * 获取accountId
	 * 
	 * @Title: getAccountId
	 * @param uKeyId
	 * @param request
	 * @return
	 * @throws DeodioException
	 * @return String
	 */
	public String getAccountId(String uKeyId, HttpServletRequest request) throws DeodioException {
		Object cookieValue = CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String accountId = "";
		if (cookieValue != null) {
			accountId = String.valueOf(cookieValue);
		} else {
			// accountId = getAccountModleByCreateId(uKeyId).getCreateId();
			Account account = getAccountInfoByOwnerId(uKeyId);
			if (account != null) {
				accountId = account.getCreateId();
			} else {
				accountId = StringUtils.EMPTY;
			}
		}
		return accountId;
	}

	/**
	 * 根据userId在UserAccountRel中获取accountId
	 * 
	 * @Title: getAccountId
	 * @param uKeyId
	 * @param request
	 * @return
	 * @throws DeodioException
	 * @return String
	 */
	public String getAccountIdFromUserAccountRel(String uKeyId) throws DeodioException {
		UserAccountRelExample example = new UserAccountRelExample();
		example.createCriteria().andUserIdEqualTo(uKeyId);
		List<UserAccountRel> accountList = userAccountRelMapper.selectByExample(example);
		if (accountList.isEmpty()) {
			return null;
		} else {
			return accountList.get(0).getAccountId();
		}

	}

	/**
	 * 修改当前account账号背景图
	 * 
	 * @Title: updateAccountImg
	 * @param attachDir
	 * @param attachUrl
	 * @param accountId
	 * @param type
	 * @throws DeodioException
	 * @return void
	 */
	public void updateAccountImg(String attachDir, String attachUrl, String accountId, Integer type)
			throws DeodioException {

		AccountAttachmentExample example = new AccountAttachmentExample();
		example.createCriteria().andAccountIdEqualTo(accountId);

		List<AccountAttachment> list = accountAttachmentMapper.selectByExample(example);
		AccountAttachment accout = new AccountAttachment();
		if (!list.isEmpty()) {
			accout = list.get(0);
		}
		switch (type) {
		case 1:
			accout.setLogoImgDir(attachDir);
			accout.setLogoImgUrl(attachUrl);
			break;
		case 2:
			accout.setHeaderImgDir(attachDir);
			accout.setHeaderImgUrl(attachUrl);
			break;
		case 3:
			accout.setBannerImgDir(attachDir);
			accout.setBannerImgUrl(attachUrl);
			break;
		case 4:
			accout.setFooterImgDir(attachDir);
			accout.setFooterImgUrl(attachUrl);
			break;

		}
		accountAttachmentMapper.updateByPrimaryKey(accout);
	}

	/**
	 * 修改背景设置URL
	 * 
	 * @Title: updateBackGroundUrl
	 * @param url
	 * @param type
	 * @param uKeyId
	 * @return void
	 */
	public void updateBackgroundUrl(String url, int type, String accountId) throws DeodioException {
		Account accout = accountMapper.selectByPrimaryKey(accountId);
		switch (type) {
		case 1:
			accout.setLogoLinkUrl(url);
			break;
		case 2:
			accout.setHeaderLinkUrl(url);
			break;
		case 3:
			accout.setBannerLinkUrl(url);
			break;
		case 4:
			accout.setFooterLinkUrl(url);
			break;

		}
		// accout.setId(accountId);
		// accountMapper.updateByPrimaryKeySelective(accout);
		accountMapper.updateByPrimaryKey(accout);
	}

	/**
	 * 修改页面布局
	 * 
	 * @Title: updateSetLayout
	 * @param type
	 * @param accountId
	 * @return void
	 */
	public void updateSetLayout(Integer type, String accountId) throws DeodioException {
		Account accout = new Account();
		accout.setLayout(type);
		accout.setId(accountId);
		accountMapper.updateByPrimaryKeySelective(accout);

	}

	/**
	 * 修改页面主题
	 * 
	 * @Title: updateThemeval
	 * @param themeval
	 * @param accountId
	 * @return void
	 */
	public void updateTheme(Integer themeval, String accountId) throws DeodioException {
		Account accout = new Account();
		accout.setTheme(themeval);
		accout.setId(accountId);
		accountMapper.updateByPrimaryKeySelective(accout);

	}

	/**
	 * 获取 所在的account
	 * 
	 * @Title: getAccountList
	 * @param userId
	 * @return
	 * @throws DeodioException
	 * @return List<Map<String,Object>>
	 */
	public Map<String, Object> getLoginAccountList(String userId) throws DeodioException {
		// 获取当前用户所有加入account,如果是account账户,则包含自己创建的account
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<Map<String, Object>> mapList = accountCustomizeMapper.getLoginAccountList(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mapList", mapList);
		return map;

	}

	public boolean checkCompleteSelfInfo(String userId) {
		boolean completeSelfInfo = false;
		// 根据userId获取企业信息,为空则为普通用户
		CompanyModel companyModel = getCompanyModelByUserId(userId);
		if (companyModel == null) {
			completeSelfInfo = true;
		} else {
			UserModel userModel = userModelMapper.selectByPrimaryKey(userId);
			short registerType = userModel.getRegisterType();
			/*企业注册时判断企业注册号是否为空,个人账号则判断昵称是否为空*/
			if (registerType == 3) {
				if (StringUtils.isNotBlank(companyModel.getCompanyBusinessLicenseCode())) {
					completeSelfInfo = true;
				}
			} // 手机或邮箱注册时判断昵称是否为空
			else if (registerType == 1 || registerType == 2) {
				if (com.deodio.core.utils.StringUtils.isNotBlank(userModel.getNickName())) {
					completeSelfInfo = true;
				}
			}else if(registerType == 5) {
				completeSelfInfo = true;
			}
		}
		return completeSelfInfo;

	}

	public boolean checkAddInOrCreateGroup(String userId) {
		boolean checkAddInOrCreateGroup = false;
		// 根据userId获取企业信息,为空则为普通用户
		CompanyModel companyModel = getCompanyModelByUserId(userId);
		if (companyModel == null) {
			checkAddInOrCreateGroup = true;
		} else {
			Integer accountListSize=accountCustomizeMapper.getAccountListSize(userId);
			if (accountListSize>0) {//判断account下是否加入了其他account
				checkAddInOrCreateGroup=true;
			}else {
				Integer groupListSize = accountCustomizeMapper.getGroupListByUserId(userId);
				if (groupListSize > 0 ) {//判断account下是否创建了小组
					checkAddInOrCreateGroup=true;
				}
			}
			
		}
		return checkAddInOrCreateGroup;

	}

	public List<Map<String, Object>> getAccountList(String userId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		// return accountCustomizeMapper.getLoginAccountList(params);
		return accountCustomizeMapper.getAccountList(params);
	}

	public void updateCapability(String accountId, String capabilityPrefix, Integer capabilityLevel,
			Short capabilityLevelDistribution, Short capabilityCalScore, Integer capabilityCustomizeScore,
			String userId) throws DeodioException {
		Account account = new Account();
		account.setId(accountId);
		account.setCapabilityPrefix(capabilityPrefix);
		account.setCapabilityLevel(capabilityLevel);
		account.setCapabilityLevelDistribution(capabilityLevelDistribution);
		account.setCapabilityCalScore(capabilityCalScore);
		account.setCapabilityCustomizeScore(capabilityCustomizeScore);
		account.setUpdateId(userId);
		account.setUpdateTime(new Date());
		accountMapper.updateByPrimaryKeySelective(account);
	}

	public List<AccountCapability> loadCapability(String accountId, Integer capabilityType) throws DeodioException {
		AccountCapabilityExample accountCapabilityExample = new AccountCapabilityExample();
		accountCapabilityExample.createCriteria().andAccountIdEqualTo(accountId)
				.andCapabilityTypeEqualTo(capabilityType);
		accountCapabilityExample.setOrderByClause("capability_type asc");
		return accountCapabilityMapper.selectByExample(accountCapabilityExample);
	}

	public void updateCapabilityItem(String accountId, String knowledgeItem, String capabilityItem, String userId)
			throws DeodioException {

		deleteCapabilityItem(accountId);
		updateCapabilityItem(accountId, knowledgeItem, DConstants.NUMBER_ONE, userId);
		updateCapabilityItem(accountId, capabilityItem, DConstants.NUMBER_TWO, userId);

	}

	private void deleteCapabilityItem(String accountId) {
		AccountCapabilityExample accountCapabilityExample = new AccountCapabilityExample();
		accountCapabilityExample.createCriteria().andAccountIdEqualTo(accountId);
		accountCapabilityMapper.deleteByExample(accountCapabilityExample);
	}

	private void updateCapabilityItem(String accountId, String values, Integer type, String userId)
			throws DeodioException {
		String[] items = values.split(DConstants.DELIMITER_DATA);
		for (int index = 0; index < items.length; index++) {
			if (StringUtils.isNotBlank(items[index])) {
				String item[] = items[index].split(DConstants.DELIMITER_ROW);
				for (int jndex = 0; jndex < item.length; jndex++) {
					AccountCapability accountCapability = accountCapabilityMapper.selectByPrimaryKey(item[0]);
					Boolean isUpdate = true;
					if (accountCapability == null) {
						accountCapability = new AccountCapability();
						accountCapability.setCreateId(userId);
						accountCapability.setCreateTime(new Date());
						isUpdate = false;
					}

					accountCapability.setAccountId(accountId);
					accountCapability.setId(item[0]);
					accountCapability.setCapabilityItem(item[1]);
					accountCapability.setCapabilityType(type);
					accountCapability.setUpdateId(userId);
					accountCapability.setUpdateTime(new Date());

					if (isUpdate) {
						accountCapabilityMapper.updateByPrimaryKeySelective(accountCapability);
					} else {
						accountCapabilityMapper.insertSelective(accountCapability);
					}
				}
			}
		}
	}

	/**
	 * 获取当前account账号背景图
	 * 
	 * @Title: updateAccountImg
	 * @param attachDir
	 * @param attachUrl
	 * @param accountId
	 * @param type
	 * @throws DeodioException
	 * @return void
	 */
	public Map<String, Object> queryAccountUrl(Map<String, Object> paramsMap) throws DeodioException {

		String accountId = String.valueOf(paramsMap.get("accountId"));
		Integer type = (Integer) paramsMap.get("setType");

		AccountAttachmentExample example = new AccountAttachmentExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<AccountAttachment> list = accountAttachmentMapper.selectByExample(example);
		String imgUrl = StringUtils.EMPTY;
		// String linkUrl = StringUtils.EMPTY;
		if (!list.isEmpty()) {
			AccountAttachment accountAttachment = list.get(0);
			switch (type) {
			case 1:// log
				imgUrl = accountAttachment.getLogoImgUrl();
				break;
			case 2:
				imgUrl = accountAttachment.getHeaderImgUrl();
				break;
			case 3:
				imgUrl = accountAttachment.getBannerImgUrl();
				break;
			case 4:
				imgUrl = accountAttachment.getFooterImgUrl();
				break;
			}
		}

		Account account = accountMapper.selectByPrimaryKey(accountId);
		String linkUrl = StringUtils.EMPTY;
		if (account != null) {
			switch (type) {
			case 1:// log
				linkUrl = account.getLogoLinkUrl();
				break;
			case 2:
				linkUrl = account.getHeaderLinkUrl();
				break;
			case 3:
				linkUrl = account.getBannerLinkUrl();
				break;
			case 4:
				linkUrl = account.getFooterLinkUrl();
				break;
			}
		}

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("imgUrl", imgUrl);
		resMap.put("linkUrl", linkUrl);
		return resMap;
	}

	/**
	 * 获取当前account账号背景图
	 * 
	 * @Title: updateAccountImg
	 * @param attachDir
	 * @param attachUrl
	 * @param accountId
	 * @param type
	 * @throws DeodioException
	 * @return void
	 */
	public Map<String, Object> queryAccountUrlsByAccountId(String accountId) throws DeodioException {

		Map<String, Object> resMap = new HashMap<String, Object>();

		AccountAttachmentExample example = new AccountAttachmentExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<AccountAttachment> list = accountAttachmentMapper.selectByExample(example);
		// String linkUrl = StringUtils.EMPTY;
		if (!list.isEmpty()) {
			AccountAttachment accountAttachment = list.get(0);
			resMap.put("accountAttachment", accountAttachment);
		}
		Account account = accountMapper.selectByPrimaryKey(accountId);
		if (account != null) {
			resMap.put("account", account);
		}
		return resMap;
	}

	public boolean getUserNickNamelList(String nickName, String userId) throws DeodioException {
		UserModelExample example = new UserModelExample();
		example.createCriteria().andNickNameEqualTo(nickName).andIdNotEqualTo(userId);
		List<UserModel> list = userModelMapper.selectByExample(example);
		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public Account queryAccountByHostName(String hostName) throws DeodioException {
		AccountExample example = new AccountExample();
		example.createCriteria().andAccountDomainEqualTo(hostName);
		List<Account> list = accountMapper.selectByExample(example);
		Account account = new Account();
		if (!list.isEmpty()) {
			account = list.get(DConstants.NUMBER_ZERO);
		}
		return account;
	}

	/**
	 * 获取用户唯一小组及课程信息;若用户拥有N个小组或者课程，则返回值为null
	 * 
	 * @param params
	 *            accountId,userId
	 * @return
	 */
	public Map<String, Object> queryUserUniqueGroupAndCourseInfo(Map<String, Object> params) throws DeodioException {
		Map<String, Object> resultMap = null;
		List<Map<String, Object>> list = accountCustomizeMapper.queryUserGroupRoleCourseList(params);
		if (list != null && DConstants.NUMBER_ONE == list.size()) {
			resultMap = list.get(DConstants.NUMBER_ZERO);
		}
		return resultMap;
	}

	/**
	 * 获取用户对应小组，角色信息
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryUserGroupRoleRel(Map<String, Object> params) throws DeodioException {
		return accountCustomizeMapper.queryUserGroupRoleRel(params);
	}

	public List<Map<String, Object>> getAccountMembers(Map<String, Object> params) throws DeodioException {
		return accountCustomizeMapper.findAccountMembers(params);
	}

	public List<Map<String, Object>> getAccountMemberDetail(Map<String, Object> params) throws DeodioException {
		return accountCustomizeMapper.getAccountMemberDetail(params);
	}

	/**
	 * 判断当前用户是佛为 该账户的 所有者
	 * 
	 * @param userId
	 * @param accountId
	 * @return
	 * @throws DeodioException
	 */
	public boolean isAccountOwner(String userId, String accountId) throws DeodioException {
		boolean result = false;
		AccountExample example = new AccountExample();
		example.createCriteria().andIdEqualTo(accountId).andOwnerIdEqualTo(userId);

		List<Account> accountList = accountMapper.selectByExample(example);

		if (accountList != null && !accountList.isEmpty()) {
			result = true;
		}
		return result;
	}

	public Account queryAccountModelById(String accountId) {
		return accountMapper.selectByPrimaryKey(accountId);
	}

	/**
	 * 修改 我的账号（个人account）
	 * 
	 * @Title: updateUserInfoById
	 * @param extModelJson
	 * @param uKeyId
	 * @param accountId
	 * @return void
	 */
	public void updateUserInfoById(String extModelJson, String uKeyId, String accountId, String valCode,String orginialIdCardSnapShot)
			throws DeodioException {
		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		;
		UserModelExt extModel = gson.fromJson(extModelJson, new TypeToken<UserModelExt>() {
		}.getType());

		UserModel userModel = transferModel(uKeyId, extModel);
		UserModel orignal = userModelMapper.selectByPrimaryKey(userModel.getId());
		if (orignal != null) {
			String orignalmail = orignal.getUserMail();
			if (StringUtils.isNotBlank(extModel.getValCode())) {
				userModel.setIsCheckTel(Short.valueOf("1"));
			}
			if (StringUtils.isNotBlank(orignalmail) && !orignalmail.equals(userModel.getUserMail())) {
				userModel.setIsCheckMail(Short.valueOf("0"));
			}
		}
		//更新身份证信息
		updateUserInfo(userModel,uKeyId,accountId,orginialIdCardSnapShot);
		userModelMapper.updateByPrimaryKeySelective(userModel);

		// 更新身份证照片
		updateUserIconOrCardOrLicense(uKeyId, extModel.getIdCardImgId());
		// 更新二级域名
		if (accountId != null && !accountId.equals("") && StringUtils.isNotBlank(extModel.getAccountDomain())) {
			Account record = new Account();
			record.setId(accountId);
			record.setAccountDomain(extModel.getAccountDomain());
			accountMapper.updateByPrimaryKeySelective(record);
		}
	}
	/**
	 * 修改 我的账号(非account)
	 * 
	 * @Title: updateUserInfoNoAccountById
	 * @param extModelJson
	 * @param uKeyId
	 * @param accountId
	 * @return void
	 */
	public void updateUserInfoNoAccountById(String extModelJson, String uKeyId )
			throws DeodioException {
		JSONObject jsonObject=JSONObject.parseObject(extModelJson);
		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		;
		UserModelExt extModel = gson.fromJson(extModelJson, new TypeToken<UserModelExt>() {
		}.getType());
		System.err.println(getClass().getName()+"\t extModel.toString() :\t"+extModel.toString());
		UserModel userModel = transferModel(uKeyId, extModel);
		UserModel orignal = userModelMapper.selectByPrimaryKey(userModel.getId());
		if (orignal != null) {
			String orignalmail = orignal.getUserMail();
			if (StringUtils.isNotBlank(extModel.getValCode())) {
				userModel.setIsCheckTel(Short.valueOf("1"));
			}
			if (StringUtils.isNotBlank(orignalmail) && !orignalmail.equals(userModel.getUserMail())) {
				userModel.setIsCheckMail(Short.valueOf("0"));
			}
		}

		userModelMapper.updateByPrimaryKeySelective(userModel);		
	}
	private void updateUserInfo(UserModel userModel,String userId,String accountId,String orginialIdCardSnapShot) {
			UserModelExample example = new UserModelExample();
			example.createCriteria().andIdEqualTo(userId);
			List<UserModel> existUserData = userModelMapper.selectByExample(example);
			UserModel model = existUserData.get(0);
		// 判断运营者身份和营业执照信息是否有改动
		if (!existUserData.isEmpty() &&
				(!StringUtil.isBlank(userModel.getIdCardSnapShot()) && !userModel.getIdCardSnapShot().equals(orginialIdCardSnapShot)||
						!userModel.getIdCardName().equals(model.getIdCardName())||
						!userModel.getIdCardCode().equals(model.getIdCardCode()))) {
			// 修改account登录有效性
			Account record = new Account();
			record.setId(accountId);
			record.setIsValid(DConstants.LOGIN_IS_VALID_NO);
			record.setIsNeedCheck(DConstants.LOGIN_IS_NEED_CHECK_YES);
			accountMapper.updateByPrimaryKeySelective(record);
		}
	}
	private UserModel transferModel(String uKeyId, UserModelExt extModel) {
		UserModel userModel = new UserModel();
		userModel.setId(uKeyId);
		userModel.setMobilePhone(StringUtils.isBlank(extModel.getMobilePhone()) ? "" : extModel.getMobilePhone());
		userModel.setNickName(extModel.getNickName());
		userModel.setUserMail(StringUtils.isBlank(extModel.getUserMail()) ? "" : extModel.getUserMail());
		userModel.setIdCardSnapShot(extModel.getIdCardImgName());
		userModel.setIdCardCode(extModel.getIdCardCode());
		userModel.setAddressCountry(extModel.getAddressCountry());
		userModel.setAddressProvince(extModel.getAddressProvince());
		userModel.setAddressCity(extModel.getAddressCity());
		userModel.setAddressDetail(extModel.getAddressDetail());
		userModel.setLastName(extModel.getLastName());
		userModel.setUserName(extModel.getUserName());
		userModel.setUpdateTime(new Date());
		userModel.setUpdateId(uKeyId);
		userModel.setUserGender(extModel.getUserGender());
		userModel.setIdCardName(extModel.getIdCardName());
		return userModel;
	}

	public int getAcountRelGroupCapability(String accountId) {
		return accountCustomizeMapper.getAccountRelGroupCapalityCount(accountId);
	}

	public void updateBussinceImgInfo(String generateName, String userId) {
		// TODO Auto-generated method stub
		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andCreateIdEqualTo(userId);
		List<CompanyModel> list = companyModelMapper.selectByExample(example);
		if (!list.isEmpty()) {
			CompanyModel record = list.get(0);
			record.setCompanyBusinessLicenseSnapShot(generateName);
			companyModelMapper.updateByPrimaryKeySelective(record);
		}
	}

	public Short getAcountStatus(String accountId) {
		// TODO Auto-generated method stub
		return accountMapper.selectByPrimaryKey(accountId).getIsValid();
	}
}
