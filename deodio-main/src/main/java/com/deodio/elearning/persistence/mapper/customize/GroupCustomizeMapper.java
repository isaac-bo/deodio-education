package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

public interface GroupCustomizeMapper {

	public Integer getGroupUserCount(Map<String, Object> params);

	public List<Map<String, Object>> getGroupUserList(Map<String, Object> params);

	public List<Integer> getMemberCount(String groupId);

	public Map<String, Object> getUserInfoFromGroup(Map<String, Object> params);

	public Map<String, Object> getCurrentGroupSurvey(String groupId);

	public List<Map<String, Object>> findGroupList(Map<String, Object> params);

	public List<Map<String, Object>> loadGroupCapabilityItems(Map<String, Object> params);
	
	public List<Map<String, Object>> loadGroupCapabilityItemsHeader(Map<String, Object> params);

	public List<Map<String, Object>> loadGroupListbyContentCreator(Map<String, Object> params);
	
	public List<Map<String, Object>> queryGroupListForUser(Map<String, Object> params);
	
	public List<Map<String,Object>> getGroupListByCreator(Map<String,Object> params);
	
	public List<Map<String,Object>> getGroupChartByCreator(Map<String,Object> params);
	
	public List<Map<String, Object>> queryGroupRoleForUser(Map<String, Object> params);
	
	public List<Map<String, Object>> queryGroupFunctionForRole(Map<String, Object> params);
	
	public List<Map<String, Object>> findGroupModelFunction(Map<String, Object> params);
	public List<Map<String, Object>> findAccountCapability(Map<String, Object> params);
    public List<Map<String, Object>> getGroupRoleUserOutDate(Map<String,Object> params);
    public List<Map<String, Object>> getAllGroupByGroupName(Map<String, Object> params);
}
