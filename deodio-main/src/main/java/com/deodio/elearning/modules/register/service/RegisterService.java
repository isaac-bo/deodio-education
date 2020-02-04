package com.deodio.elearning.modules.register.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONObject;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.EmailUtil;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.AccountAttachmentMapper;
import com.deodio.elearning.persistence.mapper.AccountItemsRelMapper;
import com.deodio.elearning.persistence.mapper.AccountMapper;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CompanyModelMapper;
import com.deodio.elearning.persistence.mapper.ServicePlanMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.UserCustomizeMapper;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AccountAttachment;
import com.deodio.elearning.persistence.model.AccountExample;
import com.deodio.elearning.persistence.model.AccountItemsRel;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.AttachmentExample;
import com.deodio.elearning.persistence.model.CompanyModel;
import com.deodio.elearning.persistence.model.CompanyModelExample;
import com.deodio.elearning.persistence.model.ServicePlan;
import com.deodio.elearning.persistence.model.ServicePlanExample;
import com.deodio.elearning.persistence.model.UserCompanyModelExt;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.UserModelExample;
import com.deodio.elearning.persistence.model.UserModelExample.Criteria;
import com.deodio.elearning.persistence.model.customize.UserRegisterBo;
import com.deodio.elearning.utils.MD5Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
@Service
public class RegisterService{
	
	@Autowired
	private UserModelMapper userModelMapper;
	
	@Autowired
	private UserCustomizeMapper userCustomizeMapper;
	
	
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private CompanyModelMapper companyModelMapper;
	
	@Autowired
	private AccountAttachmentMapper accountAttachmentMapper;
	
	@Autowired
	private ServicePlanMapper servicePlanMapper;
	
	@Autowired
	private AccountItemsRelMapper accountItemsRelMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Autowired
	private UploadService uploadService;
	
	/**
	 * 验证用户名是否存在
	 * @Title: validateUserNameExists
	 * @param userName
	 * @return true：存在；false：不存在
	 * @throws DeodioException
	 * @return boolean
	 */
	public  boolean validateUserNameExists(String userName,short registerType)throws DeodioException{
		UserModelExample example = new UserModelExample();
		Criteria  criteria = example.createCriteria();
		switch(registerType){
		case DConstants.USE_REGISTER_TYPE_COMPANY:
			criteria.andNickNameEqualTo(userName);
			break;
		case DConstants.USE_REGISTER_TYPE_MAIL:
			criteria.andUserMailEqualTo(userName);
			break;
		case DConstants.USE_REGISTER_TYPE_PHONE:
			criteria.andMobilePhoneEqualTo(userName);
			break;
		default:
			//缺省状态三个中有一个符合则，判定数据存在
			criteria.andUserMailEqualTo(userName);
			example.or().andMobilePhoneEqualTo(userName);
			example.or().andNickNameEqualTo(userName);
			break;
		}
		List<UserModel> userList = userModelMapper.selectByExample(example);
		if(userList.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	
	public UserModel vialidateUserModel(UserModel userModel){
		UserModelExample example = new UserModelExample();
		String userMail = userModel.getUserMail();
		String mobilePhone = userModel.getMobilePhone();
		String userName = userModel.getNickName();
		
		if(StringUtils.isNotBlank(userMail)){
			example.or().andUserMailEqualTo(userMail);
		}
		if(StringUtils.isNotBlank(mobilePhone)){
			example.or().andMobilePhoneEqualTo(mobilePhone);
		}
		if(StringUtils.isNotBlank(userName)){
			example.or().andNickNameEqualTo(userName);
		}
		List<UserModel> userList = userModelMapper.selectByExample(example);
		if(userList.isEmpty()){
			return null;
		}else{
			return userList.get(0);
		}
	}
	/**
	 * 注册user账户和account账户
	 * @Title: insertUserAndAccountInfo
	 * @param registerBo
	 * @throws DeodioException
	 * @return void
	 */
	public String insertUserAndAccountInfo(UserRegisterBo registerBo)throws DeodioException{	
		return insertAccountInfo(insertUserInfo(registerBo));
	}
	/**
	 * 用户注册
	 * @Title: insertUserInfo
	 * @param registerBo
	 * @throws DeodioException
	 * @return void
	 */
	public UserRegisterBo insertUserInfo(UserRegisterBo registerBo)throws DeodioException{
		UserModel userModel = new UserModel();
		String userId = AppUtils.uuid();
		String userName = registerBo.getUserName();
		userModel.setId(userId);
		userModel.setPassWord(DigestUtils.md5DigestAsHex(registerBo.getPassword().getBytes()));
		userModel.setCreateId(registerBo.getCreateId());
		userModel.setCreateTime(new Date());	
		Short registerType = registerBo.getRegisterType();
		if(registerType == DConstants.NUMBER_ZERO){
			registerType = DConstants.USE_REGISTER_TYPE_PHONE;
		}
		userModel.setRegisterType(registerType);
		switch(registerType){
			case DConstants.USE_REGISTER_TYPE_PHONE:
				userModel.setMobilePhone(userName); 
				userModel.setIsCheckTel(DConstants.PHONE_NUMBER_IS_CHECKED);
			break;
			case DConstants.USE_REGISTER_TYPE_MAIL:
				userModel.setUserMail(userName);
			break;
			default:userModel.setUserName(userName);break;
		}
		userModelMapper.insertSelective(userModel);		
		registerBo.setUserId(userId);
		return registerBo;
	}
	
	/**
	 * account注册
	 * @Title: insertAccountInfo
	 * @param registerBo
	 * @throws DeodioException
	 * @return void
	 */
	public String insertAccountInfo(UserRegisterBo registerBo)throws DeodioException{
//		
		Account accountModel;
		AccountExample example=new AccountExample();
		example.createCriteria().andOwnerIdEqualTo(registerBo.getUserId());
		List<Account> accounts=accountMapper.selectByExample(example);
		if (accounts.isEmpty()) {
			accountModel = new Account();		
			String accountId = AppUtils.uuid();
			accountModel.setId(accountId);
			setUserRegisteAccountInfo(registerBo, accountModel);
			accountMapper.insertSelective(accountModel);
		}else {
			accountModel=accounts.get(0);
			setUserRegisteAccountInfo(registerBo, accountModel);
			accountMapper.updateByPrimaryKeySelective(accountModel);
		}
		
		AccountAttachment atta = new AccountAttachment();
		atta.setAccountId(accountModel.getId());
		atta.setId(AppUtils.uuid());
		accountAttachmentMapper.insertSelective(atta);
		CompanyModel companyModel = new CompanyModel();
		companyModel.setId(AppUtils.uuid());
		companyModel.setAccountId(accountModel.getId());
		companyModel.setCompanyIndustry(registerBo.getCompanyIndustry());
		companyModel.setCreateId(registerBo.getUserId());
		companyModel.setCreateTime(new Date());	
		if(registerBo.getRegisterType() == DConstants.USE_REGISTER_TYPE_COMPANY){
			boolean isOrgnizationCode = isUserNameCompanyOrginzationCode(registerBo.getUserName());
			if(isOrgnizationCode){
				companyModel.setCompanyOrganizationCode(registerBo.getUserName());
			}else{
				companyModel.setCompanyName(registerBo.getUserName());
			}
		}
		companyModelMapper.insertSelective(companyModel);
		
		//获取默认体验套餐主键编号
		ServicePlanExample servicePlanExample = new ServicePlanExample();
		servicePlanExample.createCriteria().andPlanTypeEqualTo(DConstants.SERVICE_PLAN_TRIAL);
		List<ServicePlan> servicePlans = servicePlanMapper.selectByExample(servicePlanExample);
		if(servicePlans.isEmpty()){
			throw new DeodioException("register.query.service.plan.not.exist.err.msg");
		}
		//插入默认服务计划与账户关联数据
		AccountItemsRel accountItemsRel = new AccountItemsRel();
		accountItemsRel.setId(AppUtils.uuid());
		accountItemsRel.setItemId(servicePlans.get(0).getId());
		accountItemsRel.setItemType(DConstants.ACCOUNT_ITEMS_REL_ITEMS_TYPE_PLAN);
		accountItemsRel.setAccountId(accountModel.getId());
		accountItemsRel.setAccountType(DConstants.ACCOUNT_ITEMS_REL_ACCOUNT_TYPE_STANDARD);
		accountItemsRel.setCreateId(registerBo.getUserId());
		accountItemsRel.setCreateTime(new Date());
		accountItemsRelMapper.insert(accountItemsRel);		
		return registerBo.getUserId();
	}


	public void setUserRegisteAccountInfo(UserRegisterBo registerBo, Account accountModel) {
		accountModel.setAccountPaypalStatus(0);
		accountModel.setAccountStatus(1);
		accountModel.setCreateTime(new Date());
		accountModel.setCreateId(registerBo.getCreateId());
		accountModel.setOwnerId(registerBo.getUserId());
	}
	/**
	 * account注册更新
	 * @Title: updateAccountUserInfo
	 * @param registerBo
	 * @throws DeodioException
	 * @return void
	 */
	public void updateAccountUserInfo(UserRegisterBo registerBo)throws DeodioException{
		UserModel userModel=userModelMapper.selectByPrimaryKey(registerBo.getUserId());
		userModel.setRegisterType(registerBo.getRegisterType());
		userModel.setUpdateTime(new Date());
		userModel.setPassWord(DigestUtils.md5DigestAsHex(registerBo.getPassword().getBytes()));
		userModelMapper.updateByPrimaryKeySelective(userModel);
		
	}
	/**
	 * 注册成功-更新详细基本信息
	 * @Title: updateUserSigninDetail
	 * @param extModel
	 * @return void
	 */
	public void updateUserSigninDetail(String extModelJson,String accountId) throws DeodioException{
		
		Gson gson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();;
		UserCompanyModelExt extModel = gson.fromJson(extModelJson, new TypeToken<UserCompanyModelExt>(){}.getType());
		
		String userId = extModel.getUserId();
		String userIconId = extModel.getUserIconId();
		String userIconName = extModel.getUserIconName();
		
		String idCardImgId = extModel.getIdCardImgId();
		String idCardImgName = extModel.getIdCardImgName();
		
		UserModel userModel = new UserModel();
//		userModel.setNickName(extModel.getNickName());
		userModel.setNickName(null);
		//add by wangcheng start
		if(extModel.getUserMail()!=null) {
			userModel.setUserMail(extModel.getUserMail());
		}
		if(extModel.getUserMobile()!=null){
			
			try {
				JSONObject jsonObject=JSONObject.parseObject(extModelJson);
				String is_check_tel=jsonObject.getString("is_check_tel");
				System.err.println(getClass().getName()+"\tis_check_tel:\t"+is_check_tel);
				userModel.setIsCheckTel(Short.parseShort(is_check_tel));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	
		//add by wangcheng end
		userModel.setUserGender(extModel.getUserGender());
		userModel.setMobilePhone(extModel.getUserMobile());
		userModel.setTelNumber(extModel.getUserTel());
		userModel.setId(userId);
		userModel.setUserIcon(userIconName);
		userModel.setUpdateTime(new Date());
		userModel.setUpdateId(userId);
		userModel.setIdCardCode(extModel.getUserIdCardCode());
		userModel.setIdCardSnapShot(idCardImgName);
		userModel.setAddressCity(extModel.getCompanyAddressCity());
		userModel.setAddressCountry(extModel.getCompanyAddressCountry());
		userModel.setAddressProvince(extModel.getCompanyAddressProvince());
		userModel.setAddressDetail(extModel.getCompanyAddressDetails());
		userModel.setIdCardName(extModel.getIdCardName());
		userModelMapper.updateByPrimaryKeySelective(userModel);
		
//		//更新用户头像
//		if(StringUtils.isNotBlank(userIconName)){
//			AttachmentExample example=new AttachmentExample();
//			example.createCriteria().andRelIdEqualTo(userId).andAttachTypeEqualTo(DConstants.ATTACH_TYPE_HEADER);
//            String absDir=uploadService.generateDir("3");
//			Attachment attachmentUserIcon = new Attachment();
//			attachmentUserIcon.setGenerateName(userIconName);
//			attachmentUserIcon.setAttachDir(absDir);
//			attachmentUserIcon.setAttachUrl(absDir);
//			attachmentMapper.updateByExampleSelective(attachmentUserIcon, example);
//		}
		
		//更新身份证图片
		if(StringUtils.isNotBlank(idCardImgId)){
			Attachment attachmentIdentification = new Attachment();
			attachmentIdentification.setId(idCardImgId);
			attachmentIdentification.setRelId(userId);
			attachmentMapper.updateByPrimaryKeySelective(attachmentIdentification);
		}
		
		
		String businessLicenceImgId = extModel.getBusinessLicenceImgId();
		String businessLicenceImgName = extModel.getBusinessLicenceImgName();
		String id = extModel.getId();
		extModel.setCompanyBusinessLicenseSnapShot(businessLicenceImgName);
		extModel.setAccountId(accountId);
		
		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<CompanyModel> existData = companyModelMapper.selectByExample(example);
		
		if(existData.isEmpty()){
			id = AppUtils.uuid();
			extModel.setId(id);
			extModel.setCreateId(userId);
			extModel.setCreateTime(new Date());
			extModel.setCompanyOperationId(extModel.getUserIdCardCode());
			extModel.setCompanyOperationName(extModel.getIdCardName());
			
			extModel.setCompanyOperationMobile(extModel.getUserMobile());
			extModel.setCompanyOperationTelephone(extModel.getUserTel());
			
			companyModelMapper.insertSelective(extModel);
		}else{
			id = existData.get(0).getId();
			extModel.setId(id);
			extModel.setUpdateId(userId);
			extModel.setUpdateTime(new Date());
			extModel.setCompanyOperationId(extModel.getUserIdCardCode());
			extModel.setCompanyOperationName(extModel.getIdCardName());
			companyModelMapper.updateByExampleSelective(extModel, example);
		}
		
		//更新营业执照图片
		/*if(StringUtils.isNotBlank(businessLicenceImgId)){
			Attachment attachmentLicense = new Attachment();
			attachmentLicense.setId(businessLicenceImgId);
			attachmentLicense.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(attachmentLicense);
		}*/
	}
	
	public CompanyModel queryCompanyDetailByAccountId(String accountId){
		CompanyModel companyModel = new CompanyModel();
		CompanyModelExample example = new CompanyModelExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<CompanyModel> list = companyModelMapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			companyModel = list.get(DConstants.NUMBER_ZERO);
		}
		return companyModel;
	}
	
	
	
	
	/**
	 * 企业机构编号为全数字   数字-数字
	 * @param userName
	 * @return
	 */
	private boolean isUserNameCompanyOrginzationCode(String userName){
		boolean flag =false;
		Pattern pattern = Pattern.compile("^\\d{8}-\\d$");
		Matcher matcher = pattern.matcher(userName);
		flag = matcher.matches();
		return flag;
	}

	/**
	 * @Title: getUserInfoById
	 * @param params
	 * @return Map
	 */
	public Map<String, Object> getUserInfoById(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userCustomizeMapper.getUserInfoById(params);
	}
	/**
	 * @Title: getUserInfo
	 * @param params
	 * @return Map
	 */
	public Map<String, Object> getUserInfo(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userCustomizeMapper.getUserInfo(params);
	}
	/**
	 * @Title: getUserModel
	 * @param userName
	 * @return UserModel
	 */
	public UserModel getUserModel(String userName) {
		// TODO Auto-generated method stub
		UserModelExample example = new UserModelExample();
		Criteria  criteria = example.createCriteria();
		//缺省状态三个中有一个符合则，判定数据存在
		criteria.andUserMailEqualTo(userName);
		example.or().andMobilePhoneEqualTo(userName);
		example.or().andUserNameEqualTo(userName);		
		List<UserModel> userList = userModelMapper.selectByExample(example);
		if(userList.isEmpty()){
			return null;
		}else{
			return userList.get(0);
		}
	}
	/**
	 * @Title: updateUserEmailVerified
	 * @param params
	 * @return Map
	 */
	public int updateUserEmailVerified(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userCustomizeMapper.updateUserEmailVerified(params);
	}
	/**
	 * 获取getAccoutInfoByOwnerId
	 * @Title: getAccoutInfo
	 * @param userName
	 * @return
	 * @throws DeodioException	
	 * @return Map
	 */
	public Map<String, Object> getAccoutInfoByOwnerId(String ownerId)throws DeodioException{
		return userCustomizeMapper.getAccoutInfoByOwnerId(ownerId);
	}
	
	/**
	 * 组员设置用户昵称和密码
	 * @Title: updateNickNameAndPassword
	 * @param userId
	 * @param nickName
	 * @param passWord
	 * @return void
	 */
	public void updateNickNameAndPassword(String userId,String nickName,String passWord) throws DeodioException{
		
		UserModel userModel = new UserModel();
		if(userId!=null) {
			userModel.setId(userId);
			userModel.setUpdateTime(new Date());
			userModel.setUpdateId(userId);
			userModel.setNickName(nickName);
			userModel.setPassWord(passWord);
			userModelMapper.updateByPrimaryKeySelective(userModel);
		}
		
	}


	public void updateUserInfo(UserModel userModel) {
		// TODO Auto-generated method stub
		userModelMapper.updateByPrimaryKeySelective(userModel);
	}


	public boolean checkCompanyInfoIsExist(String companyNeedCheckCode,String type, String uKeyId) {
		// TODO Auto-generated method stub
		CompanyModelExample example = new CompanyModelExample();
		if(type.equals(DConstants.REGISTER_CHECK_OGCODE)) {
			example.createCriteria().andCompanyOrganizationCodeEqualTo(companyNeedCheckCode);
		}else if(type.equals(DConstants.REGISTER_CHECK_LSCODE)) {
			example.createCriteria().andCompanyBusinessLicenseCodeEqualTo(companyNeedCheckCode);
		}
		List<CompanyModel> companyModels = companyModelMapper.selectByExample(example);	
		if(companyModels.isEmpty()){
			return false;
		}else if(companyModels.size()==1){
			if (companyModels.get(0).getCreateId().equals(uKeyId)) {
				return false;
			}else {
				return true;
			}
		}else {
			return true;
		}
	}


	public boolean checkUserInfoIsExist(UserModelExample example) {
		// TODO Auto-generated method stub
		List<UserModel> existData=userModelMapper.selectByExample(example);
		if(existData.isEmpty()){
			return false;
		}else {
			return true;
		}
	}
	public List<UserModel> selectUserByExample(UserModelExample example) {
		return userModelMapper.selectByExample(example);
	}
	public String sendRegisterEmailVerify(String userEmail, String projectSrc,String password, String proUrl,String verifyUrl)
	{
		String validateCode = MD5Util.MD5Encode(userEmail);
		String dp = MD5Util.MD5Encode(password);
		StringBuffer sb = new StringBuffer(
				"您收到这封邮件，是由于在 e-learing在线教育平台 进行了新用户注册，或用户修改 Email 使用 了这个邮箱地址。如果您并没有访问过e-learing在线教育平台，或没有进行上述操作，请忽 略这封邮件。您不需要退订或进行其他进一步的操作。如果您是 e-learing在线教育平台的新用户，或在修改您的注册 Email 时使用了本地址，我们需 要对您的地址有效性进行验证以避免垃圾邮件或地址被滥用。\\n</br>");
		StringBuffer dataUrl=new StringBuffer();
		dataUrl.append(proUrl);
		dataUrl.append(projectSrc);
		dataUrl.append(verifyUrl);
		dataUrl.append(userEmail);
		dataUrl.append("&validateCode=");
		dataUrl.append(validateCode);
		dataUrl.append("&dp=");
		dataUrl.append(dp);
		sb.append("<a href=\"");
		sb.append(proUrl);
		sb.append(projectSrc);
		sb.append(verifyUrl);
		sb.append(userEmail);
		sb.append("&validateCode=");
		sb.append(validateCode);
		sb.append("&dp=");
		sb.append(dp);
		sb.append("\">");
		sb.append(dataUrl);
		sb.append("</a>");
		
		System.err.println("sb.toString():\t" + sb.toString());
		try {
			EmailUtil.SendHtmlEmailWithImg(userEmail, "账户激活", sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataUrl.toString();
	}


	public String userEmailVerified(Model model, String email, String validateCode, String dp) {
		String id=null;
		if (validateCode.equals(MD5Util.MD5Encode(email))) {
			UserModelExample example = new UserModelExample();
			example.createCriteria().andUserMailEqualTo(email);		
			List<UserModel> userModelList=userModelMapper.selectByExample(example);
			UserModel userModel=new UserModel();
			
			String password=dp;
			UserRegisterBo registerBo=new UserRegisterBo();
			if (!userModelList.isEmpty()) {
				userModel=userModelList.get(0);
				userModel.setPassWord(dp);
				userModel.setRegisterType(DConstants.USE_REGISTER_TYPE_MAIL);
				userModel.setIsCheckMail(DConstants.EMAIL_IS_CHECKED);
				userModelMapper.updateByPrimaryKeySelective(userModel);
			}else {
				id=AppUtils.uuid();
				userModel.setId(id);
				userModel.setUserMail(email);
				userModel.setPassWord(password);
				userModel.setIsCheckMail(DConstants.EMAIL_IS_CHECKED);
				userModel.setRegisterType(DConstants.USE_REGISTER_TYPE_MAIL);
				userModel.setCreateTime(new Date());
				userModelMapper.insert(userModel);
				registerBo.setCompanyIndustry("");
			}

			registerBo.setCreateId(userModel.getId());
			registerBo.setPassword(userModel.getPassWord());
			registerBo.setRegisterType(userModel.getRegisterType());
			registerBo.setUserId(userModel.getId());
			registerBo.setUserName(userModel.getUserMail());
			id=insertAccountInfo(registerBo);
			model.addAttribute("uKeyId", id);
			model.addAttribute("emailVerified", DConstants.EMAIL_IS_CHECKED);
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("user_mail", email);
//			params.put("is_check_mail", DConstants.EMAIL_IS_CHECKED);
//			// 完成用户邮箱验证
//			updateUserEmailVerified(params);
//			UserModelExample example=new UserModelExample();
//			example.createCriteria().andUserMailEqualTo(email);
//			List<UserModel> userModels=selectUserByExample(example);
//			if (userModels!=null&&!userModels.isEmpty()) {
//				model.addAttribute("uKeyId", id);
//				model.addAttribute("emailVerified", userModels.get(0).getIsCheckMail());
//			}
			
		} else {
			model.addAttribute("errorVerifyCode", "激活码不正确");
		}
		return id;
	}


	public boolean checkAccountIsValide(String uKeyId) {
		// TODO Auto-generated method stub
		boolean isValide=false;
		AccountExample example=new AccountExample();
		example.createCriteria().andOwnerIdEqualTo(uKeyId);
		System.err.println(getClass().getName()+":\tuKeyId:\t"+uKeyId);
		List<Account> accounts= accountMapper.selectByExample(example);
		if (accounts!=null&&!accounts.isEmpty()) {
			if(accounts.get(0).getIsValid()==1) {
				isValide=true;			
			}			
		}
		return isValide;
	}


	public Map<String, Object> checkAccountUserNameExists(String userName, String registerType) {
		// TODO Auto-generated method stub	
		System.err.println(getClass().getName()+"\t userName :\t"+userName+"\t registerType :\t"+registerType);
		Map<String, Object> map=new HashMap<>();
		UserModel userModel=null;
		Account account=null;
		UserModelExample example = new UserModelExample();
		if (registerType.equals(DConstants.REGISTER_TYPE_PHONE)) {
			 example.createCriteria().andMobilePhoneEqualTo(userName);
		}else if(registerType.equals(DConstants.REGISTER_TYPE_MAIL)) {
			 example.createCriteria().andUserMailEqualTo(userName);
		}else if(registerType.equals(DConstants.REGISTER_TYPE_COMPANY)) {
			 example.createCriteria().andUserNameEqualTo(userName);
		}
		List<UserModel> userList = userModelMapper.selectByExample(example);
		if (!userList.isEmpty()) {
			userModel=userList.get(0);		
			AccountExample accountExample=new AccountExample();
			accountExample.createCriteria().andOwnerIdEqualTo(userModel.getId());
			List<Account> accountList=accountMapper.selectByExample(accountExample);			
			if (!accountList.isEmpty()) {
				account=accountList.get(0);
				
			}			
		}
		map.put("userInfo", userModel);
		map.put("accountInfo", account);
		return map;
	}


	public boolean checkUserIdCard(String userIdCardCode, String uKeyId) {
		// TODO Auto-generated method stub
		UserModelExample example=new UserModelExample();
		example.createCriteria().andIdCardCodeEqualTo(userIdCardCode);
		List<UserModel> list=userModelMapper.selectByExample(example);
		if(list.isEmpty()){
			return false;
		}else if (list.size()==1) {
			if (list.get(0).getId().equals(uKeyId)) {
				return false;
			}else {
				return true;
			}
		}else {					
			return true;
		}
	
	}


	public String setUserEmailVerified(Model model, String email, String validateCode) {
		// TODO Auto-generated method stub
		String id=null;
		if (validateCode.equals(MD5Util.MD5Encode(email))) {
			UserModelExample example=new UserModelExample();
			example.createCriteria().andUserMailEqualTo(email);
			List<UserModel> userModels=selectUserByExample(example);
			if (!userModels.isEmpty()) {
				UserModel userModel= userModels.get(0);
				userModel.setIsCheckMail(DConstants.EMAIL_IS_CHECKED);
				userModelMapper.updateByPrimaryKeySelective(userModel);
				id= userModel.getId();
				model.addAttribute("uKeyId",id);
				model.addAttribute("emailVerified", userModels.get(0).getIsCheckMail());
			}
		}
		return id;
	}





}
