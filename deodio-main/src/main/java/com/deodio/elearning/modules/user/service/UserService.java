package com.deodio.elearning.modules.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.UserCustomizeMapper;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.UserModelExample;

@Service
public class UserService {

	@Autowired
	private UserModelMapper userModelMapper;
	
	@Autowired
	private UserCustomizeMapper userCustomizeMapper;
	
	/**
	 * 修改 我的信息
	 * @Title: updateUsrInfoById
	 * @param nickName
	 * @param userMail
	 * @param lastName
	 * @param firstName
	 * @param gender
	 * @param mobilePhone
	 * @param telNumber
	 * @param uKeyId
	 * @return void
	 */
	public void updateUsrInfoById(String nickName, String userMail,
			String lastName, String firstName, Integer gender,
			String mobilePhone, String telNumber, String uKeyId) throws DeodioException{
		UserModel userModel = new UserModel();
		userModel.setId(uKeyId);
		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setMobilePhone(mobilePhone);
		userModel.setNickName(nickName);
		userModel.setUserMail(userMail);
		userModel.setMobilePhone(mobilePhone);
		userModel.setTelNumber(telNumber);
		userModel.setUserGender(gender);
		userModel.setUpdateTime(new Date());
		userModel.setUpdateId(uKeyId);
		userModelMapper.updateByPrimaryKeySelective(userModel);
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws DeodioException
	 * 根据主键id查询用户信息
	 */
	public UserModel queryUserInfoById(String userId)throws DeodioException{
		return userModelMapper.selectByPrimaryKey(userId);
	}
	/**
	 * @param userEmail
	 * @return
	 * @throws DeodioException
	 * 根据用户名查询用户信息
	 */
	public UserModel queryUserInfoByUserName(String userName,short registerType)throws DeodioException{
		UserModelExample example=new UserModelExample();
		UserModelExample.Criteria criteria=example.createCriteria();
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
		}
		List<UserModel> list=userModelMapper.selectByExample(example);
		return null!=list&&list.size()>0?list.get(0):null;
	}
	/**
	 * 邮箱是否重复
	 * @Title: getValidateEmailExists
	 * @param mail
	 * @return
	 * @return boolean
	 */
	public boolean getValidateEmailExists(String mail) throws DeodioException{
		
		UserModelExample example=new UserModelExample();
		example.createCriteria().andUserMailEqualTo(mail);
		
		List<UserModel> list = userModelMapper.selectByExample(example);
		if(list.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 插入用户信息
	 */
	public void insertUserModel(String userName,String userMail){
		UserModel record=new UserModel();
		record.setId(AppUtils.uuid());
		record.setUserName(userName);
		record.setUserMail(userMail);
		record.setPassWord(DigestUtils.md5DigestAsHex(DConstants.DEFAULT_PASS_WORD.getBytes()));
		record.setRegisterType(DConstants.USE_REGISTER_TYPE_OTHERS);
		userModelMapper.insert(record);
	}
	public void updateUserNameById(String userName,String uKeyId) throws DeodioException{
		UserModel userModel = new UserModel();
		userModel.setId(uKeyId);
		userModel.setUserName(userName);
		userModelMapper.updateByPrimaryKeySelective(userModel);
	}
	/**
	 * 判断用户更改手机号码时 手机号码是否已经被注册
	 * @param userId
	 * @param mobilePhone
	 * @return
	 * @throws DeodioException
	 */
	public boolean getMobileExist(String userId,String mobilePhone)throws DeodioException{
		UserModelExample example = new UserModelExample();
		example.createCriteria().andMobilePhoneEqualTo(mobilePhone).andIdNotEqualTo(userId);
		List<UserModel> list = userModelMapper.selectByExample(example);
		return list.isEmpty();
	}

	public void updateUserIconInfo(String generateName,String userId) {
		// TODO Auto-generated method stub
		UserModel record=new UserModel();
		record.setId(userId);
		record.setUserIcon(generateName);
		userModelMapper.updateByPrimaryKeySelective(record);
	}

	public void updateUserIdCardImgInfo(String generateName, String userId) {
		// TODO Auto-generated method stub
		UserModel record=new UserModel();
		record.setId(userId);
		record.setIdCardSnapShot(generateName);
		userModelMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * @param userName
	 * @param uKeyId
	 * @throws DeodioException
	 * 更改邮箱认证状态
	 */
	public void updateEmailStatusById(String isCheckMail,String uKeyId) throws DeodioException{
		UserModel userModel = new UserModel();
		userModel.setId(uKeyId);
		userModel.setIsCheckMail(new Short(isCheckMail));
		userModelMapper.updateByPrimaryKeySelective(userModel);
	}
}
