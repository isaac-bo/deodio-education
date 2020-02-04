package com.deodio.elearning.modules.group.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.register.service.RegisterService;
import com.deodio.elearning.modules.survey.service.SurveyService;
import com.deodio.elearning.persistence.mapper.AccountCapabilityMapper;
import com.deodio.elearning.persistence.mapper.AccountMapper;
import com.deodio.elearning.persistence.mapper.GroupCapabilityMapper;
import com.deodio.elearning.persistence.mapper.GroupCourseMapper;
import com.deodio.elearning.persistence.mapper.GroupFunctionMapper;
import com.deodio.elearning.persistence.mapper.GroupItemsMapper;
import com.deodio.elearning.persistence.mapper.GroupMapper;
import com.deodio.elearning.persistence.mapper.GroupRoleFuncRelMapper;
import com.deodio.elearning.persistence.mapper.GroupRoleMapper;
import com.deodio.elearning.persistence.mapper.GroupRoleUserRelMapper;
import com.deodio.elearning.persistence.mapper.GroupSurveyMapper;
import com.deodio.elearning.persistence.mapper.SurveyUserItemsMapper;
import com.deodio.elearning.persistence.mapper.UserAccountRelMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.GroupCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.GroupFunctionCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.UserCustomizeMapper;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AccountCapability;
import com.deodio.elearning.persistence.model.AccountCapabilityExample;
import com.deodio.elearning.persistence.model.AccountExample;
import com.deodio.elearning.persistence.model.Group;
import com.deodio.elearning.persistence.model.GroupCapability;
import com.deodio.elearning.persistence.model.GroupCapabilityExample;
import com.deodio.elearning.persistence.model.GroupCourse;
import com.deodio.elearning.persistence.model.GroupCourseExample;
import com.deodio.elearning.persistence.model.GroupExample;
import com.deodio.elearning.persistence.model.GroupFunction;
import com.deodio.elearning.persistence.model.GroupFunctionExample;
import com.deodio.elearning.persistence.model.GroupItems;
import com.deodio.elearning.persistence.model.GroupItemsExample;
import com.deodio.elearning.persistence.model.GroupRole;
import com.deodio.elearning.persistence.model.GroupRoleFuncRel;
import com.deodio.elearning.persistence.model.GroupRoleFuncRelExample;
import com.deodio.elearning.persistence.model.GroupRoleUserRel;
import com.deodio.elearning.persistence.model.GroupRoleUserRelExample;
import com.deodio.elearning.persistence.model.GroupSurvey;
import com.deodio.elearning.persistence.model.GroupSurveyExample;
import com.deodio.elearning.persistence.model.SurveyUserItemsExample;
import com.deodio.elearning.persistence.model.UserAccountRel;
import com.deodio.elearning.persistence.model.UserAccountRelExample;
import com.deodio.elearning.persistence.model.UserModel;

@Service
public class GroupService {
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private GroupFunctionMapper groupFunctionMapper;

	@Autowired
	private GroupRoleMapper groupRoleMapper;

	@Autowired
	private GroupRoleFuncRelMapper groupRoleFuncRelMapper;

	@Autowired
	private AccountCapabilityMapper accountCapabilityMapper;

	@Autowired
	private GroupCapabilityMapper groupCapabilityMapper;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private GroupRoleUserRelMapper groupRoleUserRelMapper;

	@Autowired
	private GroupCustomizeMapper groupCustomizeMapper;

	@Autowired
	private UserModelMapper userModelMapper;
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private GroupItemsMapper groupItemsMapper;

	@Autowired
	private UserAccountRelMapper userAccountRelMapper;

	@Autowired
	private GroupFunctionCustomizeMapper groupFunctionCustomizeMapper;
	@Autowired
	private GroupSurveyMapper groupSurveyMapper;

	@Autowired
	private SurveyUserItemsMapper surveyUserItemsMapper;
	@Autowired
	private GroupCourseMapper groupCourseMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserCustomizeMapper userCustomizeMapper;
	/*
	 * 创建组基本信息
	 */
	public String insertGroupInfo(String groupName, String groupContent, String attachId, String accountId,
			String uKeyId) throws DeodioException {

		Group record = new Group();
		String groupId = AppUtils.uuid();
		record.setId(groupId);
		record.setGroupName(groupName);
		record.setGroupDescription(groupContent);
		record.setAccountId(accountId);
		record.setCreateId(uKeyId);
		record.setCreateTime(new Date());
		record.setUpdateId(uKeyId);
		record.setUpdateTime(new Date());
		groupMapper.insertSelective(record);

		GroupRoleUserRel groupRoleUserRel = new GroupRoleUserRel();
		groupRoleUserRel.setId(AppUtils.uuid());
		groupRoleUserRel.setActiveStatus(DConstants.STATUS_ONE);
		groupRoleUserRel.setGroupId(groupId);
		groupRoleUserRel.setGroupRoleId("10000");
		groupRoleUserRel.setUserId(uKeyId);
		groupRoleUserRel.setCreateTime(new Date());
		groupRoleUserRelMapper.insertSelective(groupRoleUserRel);

		uploadService.updateAttachRelId(attachId, groupId);

		return groupId;
	}

	/**
	 * 修改群基本信息
	 * 
	 * @Title: updateGroupInfo
	 * @param groupName
	 * @param groupContent
	 * @param attachId
	 * @param groupId
	 * @return void
	 */
	public void updateGroupInfo(String groupName, String groupContent, String attachId, String groupId) {
		Group record = new Group();
		record.setId(groupId);
		record.setGroupName(groupName);
		record.setGroupDescription(groupContent);
		groupMapper.updateByPrimaryKeySelective(record);

		if (StringUtils.isNotBlank(attachId)) {
			uploadService.updateAttachRelId(attachId, groupId);
		}

	}

	/**
	 * 获取用户组权限function
	 * 
	 * @Title: getGroupFunction
	 * @return
	 * @throws DeodioException
	 * @return List<GroupFunction>
	 */
	public List<GroupFunction> getGroupFunction(int groupType) throws DeodioException {
		GroupFunctionExample example = new GroupFunctionExample();
		example.createCriteria().andGroupTypeEqualTo(groupType);
		return groupFunctionMapper.selectByExample(example);
	}

	/**
	 * 获取组内角色
	 * 
	 * @Title: getGroupRole
	 * @return
	 * @throws DeodioException
	 * @return List<GroupRole>
	 */
	public List<GroupRole> getGroupRole() throws DeodioException {

		return groupRoleMapper.selectByExample(null);
	}

	/**
	 * 获取组内角色对应的权限
	 * 
	 * @Title: getGroupRoleByGroupId
	 * @param groupId
	 * @return
	 * @throws DeodioException
	 * @return List<GroupRoleFuncRel>
	 */
	public List<GroupRoleFuncRel> getGroupRoleByGroupId(String groupId) throws DeodioException {
		GroupRoleFuncRelExample example = new GroupRoleFuncRelExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		return groupRoleFuncRelMapper.selectByExample(example);
	}

	/**
	 * 获取小组当前功能模块权限
	 * 
	 * @Title: getGroupModelFunction
	 * @param groupId
	 * @return
	 * @throws DeodioException
	 * @return String
	 */
	public String getGroupModelFunction(String groupId) throws DeodioException {

		Group group = groupMapper.selectByPrimaryKey(groupId);
		return group.getGroupModelFun();
	}

	/**
	 * 保存用户组
	 * 
	 * @Title: insertGroupRoleFunc
	 * @throws DeodioException
	 * @return void
	 */
	public void insertGroupRoleFunc(String[] permission, String[] permissionModel, String groupId, String userId,
			String functionIdsStr) throws DeodioException {

		// 删除当前页面已角色功能对应关系数据
		List<String> functionIds = Arrays.asList(functionIdsStr.split(DConstants.DELIMITER_COMMA));
		GroupRoleFuncRelExample example = new GroupRoleFuncRelExample();
		example.createCriteria().andGroupIdEqualTo(groupId).andFunctionIdIn(functionIds);
		groupRoleFuncRelMapper.deleteByExample(example);
		// 插入角色功能对应关系数据
		for (int i = 0, z = permission.length; i < z; i++) {
			String[] funcRole = permission[i].split(DConstants.DELIMITER_HR);

			GroupRoleFuncRel grfcr = new GroupRoleFuncRel();
			grfcr.setId(AppUtils.uuid());
			grfcr.setCreateTime(new Date());
			grfcr.setCreator(userId);
			grfcr.setGroupId(groupId);
			grfcr.setFunctionId(funcRole[0]);
			grfcr.setGroupRoleId(funcRole[1]);
			groupRoleFuncRelMapper.insertSelective(grfcr);

		}
		// 更新组内功能模块数据
		if (permissionModel != null && permissionModel.length != 0) {

			Group group = new Group();
			group.setId(groupId);
			// TODO for jdk1.8+
			// group.setGroupModelFun(String.join(",", permissionModel));
			group.setGroupModelFun(
					com.deodio.core.utils.StringUtils.StringJoin(permissionModel, DConstants.STRING_COMMA));
			group.setUpdateId(userId);
			group.setUpdateTime(new Date());
			groupMapper.updateByPrimaryKeySelective(group);
		}

	}
	public List<GroupRoleFuncRel> getGroupRoleFuncByGrouypId(String groupId) {
		GroupRoleFuncRelExample example=new GroupRoleFuncRelExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		List<GroupRoleFuncRel> list=groupRoleFuncRelMapper.selectByExample(example);
		return list;
	}

	/**
	 * 获取 组基本信息
	 * 
	 * @Title: getGroupInfoByGroupId
	 * @param groupId
	 * @return void
	 */
	public Group getGroupInfoByGroupId(String groupId) throws DeodioException {

		return groupMapper.selectByPrimaryKey(groupId);
	}

	/**
	 * @param groupName
	 * @return
	 * @throws DeodioException
	 *             根据小组名称查询小组信息
	 */
	public List<Group> getGroupInfoByGroupName(String groupName) throws DeodioException {
		GroupExample example = new GroupExample();
		example.createCriteria().andGroupNameEqualTo(groupName);
		return groupMapper.selectByExample(example);
	}

	/**
	 * 保存用户到组内
	 * 
	 * @Title: insertGroupRoleUser
	 * @param users
	 * @param groupId
	 * @param userRole
	 * @return void
	 */
	public void insertGroupRoleUser(String[] users, String groupId, String userRole, String accountId, String createId,
			String activeAccount) {
		for (int i = 0, z = users.length; i < z; i++) {
			String userMail = users[i];
			UserModel userModel = new UserModel();
			userModel.setUserMail(userMail);
			insertGroupRoleUser(userModel, userRole, groupId, accountId, createId, activeAccount);
		}
	}

	/**
	 * 判断用户是否存在小组内
	 * 
	 * @Title: getExsistGroupUserByUserId
	 * @param userId
	 * @return
	 * @return boolean
	 */
	private boolean getExsistGroupUserByUserId(String userId, String groupId) throws DeodioException {
		GroupRoleUserRelExample example = new GroupRoleUserRelExample();
		example.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
		List<GroupRoleUserRel> list = groupRoleUserRelMapper.selectByExample(example);
		return list.isEmpty();
	}
	public GroupRoleUserRel getGroupUserByUserId(String userId, String groupId) throws DeodioException {
		GroupRoleUserRelExample example = new GroupRoleUserRelExample();
		example.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
		List<GroupRoleUserRel> list = groupRoleUserRelMapper.selectByExample(example);
		return (GroupRoleUserRel) (list.isEmpty()?null:(GroupRoleUserRel)list.get(0));
	}

	/**
	 * 判断当前account 中是否包含此用户
	 * 
	 * @Title: getExsistAccountRelByUserId
	 * @param userId
	 * @param accountId
	 * @return
	 * @throws DeodioException
	 * @return boolean
	 */
	private boolean getExsistAccountRelByUserId(String userId, String accountId) throws DeodioException {
		UserAccountRelExample example = new UserAccountRelExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andUserIdEqualTo(userId);
		return userAccountRelMapper.selectByExample(example).isEmpty();

	}

	public Integer getGroupUserCount(String groupId, Integer status, String roleId, String keywords, Integer tabValue,
			String userId) throws DeodioException {
		Map<String, Object> params = setGroupUserBean(groupId, status, roleId, keywords, tabValue, userId);
		return groupCustomizeMapper.getGroupUserCount(params);
	}

	private Map<String, Object> setGroupUserBean(String groupId, Integer status, String roleId, String keywords,
			Integer tabValue, String userId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		if (status == 3) {// 已过期数据查询
			tabValue = 2;
		} else {
			params.put("ativeStatus", status == 4 ? null : status);
		}
		params.put("roleId", StringUtils.isNotBlank(roleId) ? roleId : null);
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		params.put("tabValue", tabValue);
		params.put("userId", userId);
		return params;
	}

	public List<Map<String, Object>> getGroupUserList(String groupId, Integer status, String roleId, String keywords,
			PageRequest page, Integer tabValue, String userId) throws DeodioException {
		Map<String, Object> params = setGroupUserBean(groupId, status, roleId, keywords, tabValue, userId);
		params.put("end", page.getPageSize());
		params.put("start", page.getOffset());
		return groupCustomizeMapper.getGroupUserList(params);
	}

	public boolean updateGroupUserStatus(String[] userData, Integer status, String groupId) throws DeodioException {
		boolean flag = true;
		for (int i = 0, z = userData.length; i < z; i++) {
			GroupRoleUserRel groupRoleUserRel = groupRoleUserRelMapper.selectByPrimaryKey(userData[i]);
			if (groupRoleUserRel.getActiveStatus() == DConstants.STATUS_TWO && status == DConstants.STATUS_ONE) {
				flag = false;
			} else {
				GroupRoleUserRel record = null;
				GroupRoleUserRelExample example = null;
				record = new GroupRoleUserRel();
				example = new GroupRoleUserRelExample();
				GroupRoleUserRelExample.Criteria criteria = example.createCriteria();
				criteria.andIdEqualTo(userData[i]);
				criteria.andGroupIdEqualTo(groupId);
				record.setActiveStatus(status);
				groupRoleUserRelMapper.updateByExampleSelective(record, example);
			}

		}
		return flag;

	}

	public void updateEmailGroupUserStatus(String userId, Integer status, String groupId) throws DeodioException {
		GroupRoleUserRel record = null;
		GroupRoleUserRelExample example = null;
		record = new GroupRoleUserRel();
		example = new GroupRoleUserRelExample();
		GroupRoleUserRelExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andGroupIdEqualTo(groupId);
		record.setActiveStatus(status);
		record.setCreateTime(new Date());
		groupRoleUserRelMapper.updateByExampleSelective(record, example);
	}

	public void updateGroupUserCreateTime(String[] userData, Integer status, String groupId) throws DeodioException {

		GroupRoleUserRel record = null;

		for (int i = 0, z = userData.length; i < z; i++) {
			record = new GroupRoleUserRel();
			record.setCreateTime(new Date());
			record.setId(userData[i]);
			groupRoleUserRelMapper.updateByPrimaryKeySelective(record);
		}

	}

	public boolean deleteUserFromGroup(String[] userData,HttpServletRequest request) throws DeodioException {
		boolean flag = true;
		GroupRoleUserRel groupRoleUserRel = null;
		for (int i = 0, z = userData.length; i < z; i++) {
			groupRoleUserRel = groupRoleUserRelMapper.selectByPrimaryKey(userData[i]);
			if (groupRoleUserRel.getActiveStatus() == DConstants.STATUS_ONE) {
				flag = false;
			}else {
				groupRoleUserRelMapper.deleteByPrimaryKey(userData[i]);
				// 用户删除后将其填写的email表单删除
				SurveyUserItemsExample example = new SurveyUserItemsExample();
				SurveyUserItemsExample.Criteria criteria = example.createCriteria();
				criteria.andGroupIdEqualTo(groupRoleUserRel.getGroupId());
				criteria.andUserIdEqualTo(groupRoleUserRel.getUserId());
				surveyUserItemsMapper.deleteByExample(example);
			}
			if (groupRoleUserRel.getActiveStatus() == DConstants.STATUS_TWO) {// 待确认状态的用户如果没有其他小组关联并且是非account账户直接删除系统用户
				AccountExample example=new AccountExample();
				example.createCriteria().andOwnerIdEqualTo(groupRoleUserRel.getUserId());
				List<Account> accountList = accountMapper.selectByExample(example);
				GroupRoleUserRelExample groupRoleUserRelExample=new GroupRoleUserRelExample();
				groupRoleUserRelExample.createCriteria().andUserIdEqualTo(groupRoleUserRel.getUserId());
				List<GroupRoleUserRel> list=groupRoleUserRelMapper.selectByExample(groupRoleUserRelExample);
				if((null==accountList||accountList.size()==0)&&(null==list||list.size()==0)){
				  userModelMapper.deleteByPrimaryKey(groupRoleUserRel.getUserId());
				}
			} 
		}
		return flag;
	}

	public boolean validateMemberStatus(String[] userData, Integer status, String type) throws DeodioException {
		boolean flag = true;
		GroupRoleUserRel groupRoleUserRel = null;
		for (int i = 0, z = userData.length; i < z; i++) {
			groupRoleUserRel = groupRoleUserRelMapper.selectByPrimaryKey(userData[i]);
			if ("delete".equals(type) && groupRoleUserRel.getActiveStatus() == DConstants.STATUS_ONE) {// 激活的账户不允许删除
				flag = false;
				break;
			}
			if (null != status && groupRoleUserRel.getActiveStatus() == DConstants.STATUS_TWO
					&& (status == DConstants.STATUS_ONE || status == DConstants.STATUS_ZERO)) {// 待确认的账户不允许激活,暂停
				flag = false;
				break;
			}
		}
		return flag;
	}

	public List<Integer> getMemberJoinCount(String groupId) throws DeodioException {
		return groupCustomizeMapper.getMemberCount(groupId);
	}

	public Map<String, Object> getUserInfoFromGroup(String groupId, String userId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("userId", userId);
		return groupCustomizeMapper.getUserInfoFromGroup(params);
	}

	public void updateUserGroupRole(String relId, String groupRoleId) {
		GroupRoleUserRel record = new GroupRoleUserRel();
		record.setId(relId);
		record.setGroupRoleId(groupRoleId);
		groupRoleUserRelMapper.updateByPrimaryKeySelective(record);
	}

	public void insertUserFromGroup(String userName, String firstName, String lastName, String userMail,
			String mobilePhone, String groupRole, String groupId, String accountId, String createId,
			String activeAccount) throws DeodioException {

		UserModel userModel = new UserModel();
		if (StringUtils.isNotBlank(userName)) {
			userModel.setNickName(userName);
		}
		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setUserMail(userMail);
		userModel.setMobilePhone(mobilePhone);
		userModel.setCreateTime(new Date());

		
		insertGroupRoleUser(userModel, groupRole, groupId, accountId, createId, activeAccount);
	}

	public void insertGroupRoleUser(UserModel userModel, String groupRole, String groupId, String accountId,
			String createId, String activeAccount) {

		String userId = AppUtils.uuid();
		// 系统提供手机，用户名，邮箱注册；三个中有一个已存在则视为已创建用户;对不存在的用户创建账号，不创建account(只有公司用户才具有)
		UserModel userModelDatabase = registerService.vialidateUserModel(userModel);
		if (null == userModelDatabase) {
			userModel.setId(userId);
			userModel.setRegisterType(DConstants.USE_REGISTER_TYPE_OTHERS);

			// 设置默认密码
			//userModel.setPassWord(DigestUtils.md5DigestAsHex(DConstants.DEFAULT_PASS_WORD.getBytes()));
			userModelMapper.insertSelective(userModel);
		} else {
			userId = userModelDatabase.getId();
			userModel.setId(userId);
			userModelMapper.updateByPrimaryKeySelective(userModel);
		}
		// 插入用户小组关联数据
		if (this.getExsistGroupUserByUserId(userId, groupId)) {
			GroupRoleUserRel record = new GroupRoleUserRel();
			record.setId(AppUtils.uuid());
			record.setActiveStatus(activeAccount.isEmpty() ? DConstants.STATUS_TWO : Integer.parseInt(activeAccount));
			record.setGroupId(groupId);
			record.setGroupRoleId(groupRole);
			record.setUserId(userId);
			record.setCreator(createId);
			record.setCreateTime(new Date());
			groupRoleUserRelMapper.insertSelective(record);
		}else{
			GroupRoleUserRelExample example = new GroupRoleUserRelExample();
			GroupRoleUserRel record = new GroupRoleUserRel();
			example.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
			record.setGroupRoleId(groupRole);
			record.setCreator(createId);
			record.setUserId(userId);
			record.setCreateTime(new Date());
			groupRoleUserRelMapper.updateByExampleSelective(record, example);
		}
		// 插入用户账户关联数据
		if (this.getExsistAccountRelByUserId(userId, accountId)) {			
			/*根据userId获取account信息,
			如果该userId对应的accountId等于邀请加入的小组所属的accountId,
			则不插入用户关联数据*/
			AccountExample example = new AccountExample();
			example.createCriteria().andOwnerIdEqualTo(userId);
			List<Account> accounts=accountMapper.selectByExample(example);
			String userAccountId="";
			if (!accounts.isEmpty()) {
				userAccountId=accounts.get(0).getId();
			}
			System.err.println(getClass().getName()+"\t userAccountId :\t"
			+userAccountId+"\t accountId :\t"+accountId);
			if (!userAccountId.equals(accountId)) {
				UserAccountRel record2 = new UserAccountRel();
				record2.setId(AppUtils.uuid());
				record2.setAccountId(accountId);
				record2.setUserId(userId);
				record2.setCreateTime(new Date());
				record2.setCreateId(createId);
				userAccountRelMapper.insertSelective(record2);
			}
		}
	}

	public void insertSurvey(String[] dataArrays, String groupId, String accountId, String userId, Integer relType,
			Integer status) throws DeodioException {
		surveyService.deleteSurveryForGroup(groupId);
		surveyService.insertSurveryForGroup(dataArrays, groupId, accountId, "组内问卷调查", "这是一个组内问卷调查，感谢您的参与", userId);
		Group record = new Group();
		record.setId(groupId);
		record.setActiveFormStatus(status);
		groupMapper.updateByPrimaryKeySelective(record);
	}

	public List<Map<String, Object>> findGroupList(String keyword, PageRequest pageRequest, String userId,
			String accountId) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("userId", userId);
		params.put("accountId", accountId);
		params.put("pagination", pageRequest);
		return groupCustomizeMapper.findGroupList(params);
	}

	public List<Map<String, Object>> getGroupListbyContentCreator(String keyword, String userId, String accountId)
			throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("userId", userId);
		params.put("accountId", accountId);
		return groupCustomizeMapper.loadGroupListbyContentCreator(params);
	}

	public List<Map<String, Object>> getGroupListByCreator(String userId, String accountId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		return groupCustomizeMapper.getGroupListByCreator(params);
	}

	public List<Map<String, Object>> getGroupChartByCreator(String userId, String accountId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accountId", accountId);
		return groupCustomizeMapper.getGroupChartByCreator(params);
	}

	public void deleteGroupByPkId(String id) throws DeodioException {

		GroupRoleFuncRelExample example = new GroupRoleFuncRelExample();
		example.createCriteria().andGroupIdEqualTo(id);
		groupRoleFuncRelMapper.deleteByExample(example);

		GroupRoleUserRelExample example2 = new GroupRoleUserRelExample();
		example2.createCriteria().andGroupIdEqualTo(id);
		groupRoleUserRelMapper.deleteByExample(example2);
		groupMapper.deleteByPrimaryKey(id);
	}

	public List<AccountCapability> loadAccountCapabilityItems(String accountId, Integer capabilityType) {
		AccountCapabilityExample accountCapabilityExample = new AccountCapabilityExample();
		accountCapabilityExample.createCriteria().andAccountIdEqualTo(accountId)
				.andCapabilityTypeEqualTo(capabilityType);
		return accountCapabilityMapper.selectByExample(accountCapabilityExample);
	}

	public List<Map<String, Object>> findAccountCapabilityItems(Map<String, Object> params) throws DeodioException {
		return groupCustomizeMapper.findAccountCapability(params);
	}

	public void deleteGroupCapabilityItems(String groupId, Integer capabilityType) throws DeodioException {
		GroupCapabilityExample groupCapabilityExample = new GroupCapabilityExample();
		groupCapabilityExample.createCriteria().andGroupIdEqualTo(groupId).andCapabilityTypeEqualTo(capabilityType);
		groupCapabilityMapper.deleteByExample(groupCapabilityExample);
	}

	public void updateGroupCapabilityItems(String accountId, String groupId, String userId, Integer capabilityType,
			String capabilityIdString) throws DeodioException {
		// deleteGroupCapabilityItems(groupId, capabilityType);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		String[] capabilityIds = capabilityIdString.split(DConstants.DELIMITER_ROW);
		for (int index = 0; index < capabilityIds.length; index++) {
			if (account.getCapabilityLevel() != null) {
				for (int jndex = 1; jndex < account.getCapabilityLevel() + 1; jndex++) {
					GroupCapability groupCapability = new GroupCapability();
					groupCapability.setId(AppUtils.uuid());
					groupCapability.setGroupId(groupId);
					groupCapability.setCapabilityLevel(jndex);
					groupCapability.setCapabilityId(capabilityIds[index]);
					groupCapability.setCreateId(userId);
					groupCapability.setCreateTime(new Date());
					groupCapability.setUpdateId(userId);
					groupCapability.setUpdateTime(new Date());
					groupCapability.setCapabilityType(capabilityType);
					groupCapabilityMapper.insertSelective(groupCapability);
				}
			}
		}
	}

	public List<Map<String, Object>> loadGroupCapabilityItem(String accountId, String groupId, String userId,
			Integer capabilityType) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("accountId", accountId);
		params.put("capabilityType", capabilityType);
		return groupCustomizeMapper.loadGroupCapabilityItems(params);

	}

	public List<Map<String, Object>> loadGroupCapabilityItemHeader(String accountId, String groupId, String userId)
			throws DeodioException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Account account = accountMapper.selectByPrimaryKey(accountId);
		String prefix = account.getCapabilityPrefix();
		Integer index = account.getCapabilityLevel();
		for (int i = 0; i < index; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("capabilitylevelfull", prefix.concat(String.valueOf(i + 1)));
			list.add(params);
		}
		return list;
		/*
		 * params.put("groupId", groupId); params.put("accountId", accountId);
		 * return groupCustomizeMapper.loadGroupCapabilityItemsHeader(params);
		 */

	}

	public void submitGroupCapabilityItems(String groupId, String userId, String descList) throws DeodioException {

		GroupCapabilityExample groupCapabilityExample = new GroupCapabilityExample();
		groupCapabilityExample.createCriteria().andGroupIdEqualTo(groupId);
		GroupCapability record = new GroupCapability();
		record.setCapabilityDesc("");
		groupCapabilityMapper.updateByExampleSelective(record, groupCapabilityExample);

		String[] descs = descList.split(DConstants.DELIMITER_CELL_DATA);
		for (int index = 0; index < descs.length; index++) {
			String[] items = descs[index].split(DConstants.DELIMITER_ROW);
			GroupCapability groupCapability = new GroupCapability();
			groupCapability.setId(items[0]);
			groupCapability.setCapabilityDesc(items[1]);
			groupCapability.setUpdateId(userId);
			groupCapability.setUpdateTime(new Date());
			groupCapabilityMapper.updateByPrimaryKeySelective(groupCapability);
		}
	}

	public void insertGroupItems(Short itemType, String itemId, String groupIds, String userId) throws DeodioException {
		GroupItemsExample example = new GroupItemsExample();
		example.createCriteria().andItemIdEqualTo(itemId).andItemTypeEqualTo(itemType);
		groupItemsMapper.deleteByExample(example);

		String[] ids = groupIds.split(",");
		for (int index = 0; index < ids.length; index++) {
			GroupItems groupItems = new GroupItems();
			groupItems.setId(AppUtils.uuid());
			groupItems.setGroupId(ids[index]);
			groupItems.setItemId(itemId);
			groupItems.setItemType(itemType);
			groupItems.setCreateId(userId);
			groupItems.setCreateTime(new Date());
			groupItems.setUpdateId(userId);
			groupItems.setUpdateTime(new Date());
			groupItemsMapper.insert(groupItems);

		}
	}

	public void deleteGroupItem(String groupId,String capabilityId) {
		GroupCapabilityExample example=new GroupCapabilityExample();
		example.createCriteria().andGroupIdEqualTo(groupId).andCapabilityIdEqualTo(capabilityId);
		groupCapabilityMapper.deleteByExample(example);
	}

	public void deleteGroupCourse(Map<String, String> params) {
		// String userId = params.get("userId");
		String relId = params.get("relId");
		String groupId = params.get("groupId");
		GroupCourseExample example = new GroupCourseExample();
		example.createCriteria().andIdEqualTo(relId).andGroupIdEqualTo(groupId);
		int rowCount = groupCourseMapper.deleteByExample(example);
		if (rowCount == DConstants.NUMBER_ZERO) {
			throw new DeodioException("未删除数据");
		}
	}

	public void addGroupCourse(Short itemType, String itemIds, String groupId, String userId) throws DeodioException {

		String[] ids = itemIds.split(DConstants.STRING_COMMA);
		for (int index = 0; index < ids.length; index++) {
			GroupCourse groupCourse = new GroupCourse();
			groupCourse.setId(AppUtils.uuid());
			groupCourse.setGroupId(groupId);
			groupCourse.setItemId(ids[index]);
			groupCourse.setItemType(itemType);
			groupCourse.setCreateId(userId);
			groupCourse.setCreateTime(new Date());
			groupCourse.setUpdateId(userId);
			groupCourse.setUpdateTime(new Date());
			groupCourseMapper.insert(groupCourse);
		}
	}

	public List<Map<String, Object>> findGroupFunctionForRole(Map<String, Object> params) throws DeodioException {
		// 获取小组功能分页数据
		List<String> functionIds = groupFunctionCustomizeMapper.findGroupFunctionIdList(params);
		params.put("functionIds", functionIds);
		return groupCustomizeMapper.queryGroupFunctionForRole(params);
	}

	public List<Map<String, Object>> findGroupModelFunction(Map<String, Object> params) throws DeodioException {
		return groupCustomizeMapper.findGroupModelFunction(params);
	}

	public void updateGroupImg(String groupId, String coverImg) {
		Group record = new Group();
		record.setId(groupId);
		record.setCoverImg(coverImg);
		groupMapper.updateByPrimaryKeySelective(record);
	}

	public List<Map<String, Object>> getSurveySubjectAndItems(String[] dataArrays) {
		List<Map<String, Object>> surveyItemsList = new ArrayList<Map<String, Object>>();
		for (int i = 0, z = dataArrays.length; i < z; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String[] datas = dataArrays[i].split(DConstants.DELIMITER_DATA);
			String surveySubjectId = AppUtils.uuid();
			map.put("survey_subject_id", surveySubjectId);
			map.put("survey_subject", datas[1]);
			map.put("survey_subject_type", Integer.parseInt(datas[0]));
			map.put("survey_subject_order", i);
			String[] options = StringUtils.split(datas[2], DConstants.DELIMITER_ATTR);
			String item_options = "";
			for (int j = 0, x = options.length; j < x; j++) {
				if (j != 0) {
					item_options = item_options.concat("#");
				}
				item_options = item_options.concat(options[j]);
			}
			map.put("item_options", item_options);
			surveyItemsList.add(map);
		}
		return surveyItemsList;
	}

	/**
	 * 复制小组
	 */
	public void copyGroup(String groupId, String groupName) {
		Group group = groupMapper.selectByPrimaryKey(groupId);
		String id = AppUtils.uuid();
		group.setId(id);
		group.setGroupName(groupName);
		groupMapper.insert(group);
		copyGroupRoleUser(groupId, id);
		copySurvey(groupId, id);
		copySurveyItem(groupId, id);
		copyGroupCapabilityItems(groupId, id);
		copyGroupPermission(groupId, id);
	}

	/**
	 * @param groupId
	 * @param newGroupId
	 * @throws DeodioException
	 *             复制小组角色
	 */
	public void copyGroupRoleUser(String groupId, String newGroupId) throws DeodioException {
		GroupRoleUserRelExample example = new GroupRoleUserRelExample();
		GroupRoleUserRelExample.Criteria criteria = example.createCriteria();
		criteria.andGroupIdEqualTo(groupId);
		List<GroupRoleUserRel> groupRoleUserRelList = groupRoleUserRelMapper.selectByExample(example);
		for (int i = 0; i < groupRoleUserRelList.size(); i++) {
			GroupRoleUserRel groupRoleUserRel = groupRoleUserRelList.get(i);
			groupRoleUserRel.setId(AppUtils.uuid());
			groupRoleUserRel.setGroupId(newGroupId);
			groupRoleUserRel.setCreateTime(new Date());
			groupRoleUserRelMapper.insert(groupRoleUserRel);
		}
	}

	/**
	 * @param groupId
	 * @param newGroupId
	 * @throws DeodioException
	 *             复制调查问卷
	 */
	public void copySurvey(String groupId, String newGroupId) throws DeodioException {
		GroupSurveyExample example = new GroupSurveyExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		List<GroupSurvey> groupSurveyList = groupSurveyMapper.selectByExample(example);
		for (int i = 0; i < groupSurveyList.size(); i++) {
			GroupSurvey groupSurvey = groupSurveyList.get(i);
			groupSurvey.setId(AppUtils.uuid());
			groupSurvey.setGroupId(newGroupId);
			groupSurvey.setCreateTime(new Date());
			groupSurveyMapper.insert(groupSurvey);
		}
	}

	/**
	 * @param groupId
	 * @param newGroupId
	 * @throws DeodioException
	 *             复制调查问卷详细
	 */
	public void copySurveyItem(String groupId, String newGroupId) throws DeodioException {
		GroupItemsExample example = new GroupItemsExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		List<GroupItems> groupItemsList = groupItemsMapper.selectByExample(example);
		for (int i = 0; i < groupItemsList.size(); i++) {
			GroupItems groupItem = groupItemsList.get(i);
			groupItem.setId(AppUtils.uuid());
			groupItem.setGroupId(newGroupId);
			groupItem.setCreateTime(new Date());
			groupItemsMapper.insert(groupItem);
		}
	}

	/**
	 * @param groupId
	 * @param userId
	 * @param descList
	 * @throws DeodioException
	 *             复制能力模型
	 */
	public void copyGroupCapabilityItems(String groupId, String newGroupId) throws DeodioException {
		GroupCapabilityExample groupCapabilityExample = new GroupCapabilityExample();
		groupCapabilityExample.createCriteria().andGroupIdEqualTo(groupId);
		List<GroupCapability> groupCapabilityList = groupCapabilityMapper.selectByExample(groupCapabilityExample);
		for (int index = 0; index < groupCapabilityList.size(); index++) {
			GroupCapability groupCapability = groupCapabilityList.get(index);
			groupCapability.setId(AppUtils.uuid());
			groupCapability.setGroupId(newGroupId);
			groupCapability.setCreateTime(new Date());
			groupCapabilityMapper.insert(groupCapability);
		}
	}

	public void copyGroupPermission(String groupId, String newGroupId) {
		GroupRoleFuncRelExample example = new GroupRoleFuncRelExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		List<GroupRoleFuncRel> groupRoleFuncRelList = groupRoleFuncRelMapper.selectByExample(example);
		for (int index = 0; index < groupRoleFuncRelList.size(); index++) {
			GroupRoleFuncRel grfcr = groupRoleFuncRelList.get(index);
			grfcr.setId(AppUtils.uuid());
			grfcr.setGroupId(newGroupId);
			grfcr.setCreateTime(new Date());
			groupRoleFuncRelMapper.insertSelective(grfcr);
		}
	}

	/**
	 * @return
	 * @throws DeodioException
	 *             查看用户是否被管理员或leader删除
	 */
	public boolean getUserExistGroupRole(String groupId, String userId) throws DeodioException {
		GroupRoleUserRelExample example = new GroupRoleUserRelExample();
		GroupRoleUserRelExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andGroupIdEqualTo(groupId);
		List roleUserList = groupRoleUserRelMapper.selectByExample(example);
		if (roleUserList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * @return
	 * @throws DeodioException
	 *             查看该邀请是否过期 存在即过期，否则未过期
	 */
	public boolean getUserExistGroupRoleOutDate(String groupId, String userId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("groupId", groupId);
		List<Map<String, Object>> roleUserList = groupCustomizeMapper.getGroupRoleUserOutDate(params);
		if (roleUserList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public List<Map<String, Object>> getAllGroupByGroupName(String groupName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupName", groupName);
		return groupCustomizeMapper.getAllGroupByGroupName(params);
	}
	public List<Group> getAllGroupByAccountId(String accountId) {
		GroupExample example=new GroupExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<Group> list=groupMapper.selectByExample(example);
		return list;
	}
	public Integer getAccountNum(String id) {
		// TODO Auto-generated method stub
		return userCustomizeMapper.getAccountNum(id);
	}
	
	/**
	 * 删除与小组关联信息
	 */
	public int deleteGroupItemsByItem(Short itemType, String itemId) {
		
		GroupItemsExample groupItemsExample = new GroupItemsExample();
		groupItemsExample.createCriteria().andItemTypeEqualTo(itemType).andItemIdEqualTo(itemId);
		
		return groupItemsMapper.deleteByExample(groupItemsExample);
	}
}
