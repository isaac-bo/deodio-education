package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.customize.GroupRoleRelBo;

public interface AccountCustomizeMapper {
	
	public List<Map<String,Object>> getAccountList(Map<String,Object> params);
	
	public List<Map<String,Object>> getLoginAccountList(Map<String,Object> params);
	
	public List<Map<String,Object>> queryUserGroupRoleCourseList(Map<String,Object> params);
	
	public List<Map<String, Object>> queryUserGroupRoleRel(Map<String,Object> params);

	public List<Map<String, Object>> findAccountMembers(Map<String, Object> params);
	
	public List<Map<String,Object>> getAccountMemberDetail(Map<String,Object> params);

	public Integer getGroupListByUserId(String userId);

	public Integer getAccountListSize(String userId);
	
	public Integer getAccountRelGroupCapalityCount(String accountId);
	
}
